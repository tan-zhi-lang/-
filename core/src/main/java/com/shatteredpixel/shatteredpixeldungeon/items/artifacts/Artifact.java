

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Artifact extends KindofMisc {

	protected Buff passiveBuff;
	protected Buff activeBuff;

	//level is used internally to track upgrades to artifacts, size/logic varies per artifact.
	//already inherited from item superclass
	//exp is used to count progress towards levels for some artifacts
	protected int exp = 0;
	//levelCap is the artifact's maximum level
	protected int levelCap = 0;

	//the current artifact charge
	protected int charge = 0;
	//the build towards next charge, usually rolls over at 1.
	//better to keep charge as an int and use a separate float than casting.
	protected float partialCharge = 0;
	//the maximum charge, varies per artifact, not all artifacts use this.
	protected int chargeCap = 0;

	//used by some artifacts to keep track of duration of effects or cooldowns to use.
	protected int cooldown = 0;

	@Override
	public boolean doEquip( final Hero hero ) {

		if ((hero.belongings.misc != null && hero.belongings.misc.getClass() == this.getClass())
				|| (hero.belongings.misc2 != null && hero.belongings.misc2.getClass() == this.getClass())
				|| (hero.belongings.misc3 != null && hero.belongings.misc3.getClass() == this.getClass())){

			GLog.w( Messages.get(Artifact.class, "cannot_wear_two") );
			return false;

		} else {

			if (super.doEquip( hero )){

				鉴定();
				return true;

			} else {

				return false;

			}

		}

	}

	public void activate( Char ch ) {
		if (passiveBuff != null){
			if (passiveBuff.target != null) passiveBuff.detach();
			passiveBuff = null;
		}
		if(Dungeon.炼狱(炼狱设置.诅咒神器)&&!(
				this instanceof CloakOfShadows||
				this instanceof 神圣法典||
				this instanceof 本命玉佩||
				this instanceof 叛忍护额
				)){
			
		}else{
			passiveBuff = passiveBuff();
			passiveBuff.attachTo(ch);
		}
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {

			if (passiveBuff != null) {
				if (passiveBuff.target != null) passiveBuff.detach();
				passiveBuff = null;
			}

			return true;

		} else {

			return false;

		}
	}

	@Override
	public boolean 可升级() {
		return false;
	}

	@Override
	public int visiblyUpgraded() {
		return levelKnown ? Math.round((等级()*10)/(float)levelCap): 0;
	}

	@Override
	public int buffedVisiblyUpgraded() {
		return visiblyUpgraded();
	}

	@Override
	public int 强化等级() {
		//level isn't affected by buffs/debuffs
		return 等级();
	}

	//transfers upgrades from another artifact, transfer level will equal the displayed level
	public void transferUpgrade(int transferLvl) {
		升级(Math.round((transferLvl*levelCap)/10f));
	}

	public void resetForTrinity(int visibleLevel){
		等级(Math.round((visibleLevel*levelCap)/10f));
		exp = Integer.MIN_VALUE; //ensures no levelling
		charge = chargeCap;
		cooldown = 0;
	}

	public static void artifactProc(Char target, int artifLevel, int chargesUsed){
	

	}

	@Override
	public String info() {
		if (cursed && cursedKnown && !isEquipped( Dungeon.hero )) {
			return super.info() + "\n\n" + Messages.get(Artifact.class, "curse_known");
			
		} else if (!已鉴定() && cursedKnown && !isEquipped( Dungeon.hero)) {
			return super.info() + "\n\n" + Messages.get(Artifact.class, "not_cursed");
			
		} else {
			return super.info();
			
		}
	}

	@Override
	public String status() {
		
		//if the artifact isn't IDed, or is cursed, don't display anything
		if (!已鉴定() || cursed){
			return null;
		}

		//display the current cooldown
		if (cooldown != 0)
			return Messages.format( "%d", cooldown );

		//display as percent
		if (chargeCap == 100)
			return Messages.format( "%d%%", charge );

		//display as #/#
		if (chargeCap > 0)
			return Messages.format( "%d/%d", charge, chargeCap );

		//if there's no cap -
		//- but there is charge anyway, display that charge
		if (charge != 0)
			return Messages.format( "%d", charge );

		//otherwise, if there's no charge, return null.
		return null;
	}

	@Override
	public Item random() {
		//always +0
		
		//30% chance to be cursed
		if (Random.Float() < 0.3f) {
			cursed = true;
		}
		return this;
	}

	@Override
	public int 金币() {
		int price = 100;
		if (等级() > 0)
			price += 20*visiblyUpgraded();
		if (cursed && cursedKnown) {
			price /= 2;
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}

	@Override
	public int 能量() {
		return Math.round(金币()*0.025f+1);
	}
	protected ArtifactBuff passiveBuff() {
		return null;
	}

	protected ArtifactBuff activeBuff() {return null; }
	
	public void charge(Hero target, float amount){
		//do nothing by default;
	}

	public class ArtifactBuff extends Buff {

		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				//if we're loading in and the hero has partially spent a turn, delay for 1 turn
				if (target instanceof Hero && Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
					spend(TICK);
				}
				return true;
			}
			return false;
		}

		public int itemLevel() {
			return 等级();
		}

		public boolean isCursed() {
			return target.buff(MagicImmune.class) == null && cursed;
		}

		public void charge(Hero target, float amount){
			Artifact.this.charge(target, amount);
		}
	}
	
	private static final String EXP = "exp";
	private static final String CHARGE = "charge";
	private static final String PARTIALCHARGE = "partialcharge";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( EXP , exp );
		bundle.put( CHARGE , charge );
		bundle.put( PARTIALCHARGE , partialCharge );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		exp = bundle.getInt( EXP );
		if (chargeCap > 0)  charge = Math.min( chargeCap, bundle.getInt( CHARGE ));
		else                charge = bundle.getInt( CHARGE );
		partialCharge = bundle.getFloat( PARTIALCHARGE );
	}
}
