

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import static com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand.wandProc;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.WondrousResin;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 灵魂焰灯 extends Artifact {

	{
		image = 物品表.灵魂焰灯;
		defaultAction=AC_PRICK;
		levelCap = 3;
		charge = Math.min(2+2*等级(),6);
		partialCharge = 0;
		chargeCap = Math.min(2+2*等级(),6);
	}

	public static final String AC_PRICK = "PRICK";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero )
				&& charge>0
				&& !cursed
				&& !hero.是无敌(getClass())
				&& hero.buff(MagicImmune.class) == null)
			actions.add(AC_PRICK);
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action ) {
		super.execute(hero, action);
		
		if (action.equals(AC_PRICK)
			&& charge>0
			&&isEquipped(hero)){
			curUser = hero;
			curItem = this;
			GameScene.selectCell(zapper);
	}
	}

	public void wandUsed(Hero hero) {


		charge--;
		hero.sprite.operate( hero.pos );
		hero.busy();
		hero.spend(Actor.TICK);


		Catalog.countUse(getClass());

		Invisibility.notimedispel();

		updateQuickslot();

		curUser.spendAndNext( 1 );
	}

	public float 魔力(){
		return 魔力(0.1f,1);
	}
	public float 魔力(float 魔力收益){
		return 魔力(魔力收益,0);
	}
	public float 魔力(float 魔力收益,float 等收益){
		return 魔力(魔力收益,等收益,强化等级());
	}

	public float 魔力加(float 魔力收益,float 等收益){
		return 魔力(魔力收益,等收益,1+强化等级());
	}
	public float 魔力(float 魔力收益,float 等收益,float 等级){

		if(Dungeon.hero())
			return Dungeon.hero.魔力(魔力收益*(1+等收益*(
					等级
			+(Dungeon.hero.击杀数量*(Dungeon.符文("升级灵魂焰灯")?2:1))
			)));
		else
			return 10*魔力收益*(1+等收益*等级);
	}
	public void onZap(Ballistica bolt) {

		Char ch = Actor.findChar(bolt.collisionPos);
		if (ch != null) {

			wandProc(ch,Math.round(等级()+Dungeon.hero.击杀数量), 1);
			ch.受伤时(Random.NormalFloat(
					魔力(0.3f,1f),
					魔力(1.2f,0.75f)
									 ), this);
			if(!ch.isAlive()){
				GLog.w( Messages.get(this, "onprick") );
				Dungeon.hero.击杀数量+=ch.强度()*(Dungeon.符文("升级灵魂焰灯")?2:1);

				if(Dungeon.hero.击杀数量%6==0&&等级() < levelCap)
					升级();

			}
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f) );

			ch.sprite.burst(0x000000);

		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}


	protected int collisionProperties = Ballistica.MAGIC_BOLT;
	public int collisionProperties(int target){
		if (cursed)     return Ballistica.PROJECTILE;
		else            return collisionProperties;
	}
	public boolean tryToZap( Hero owner, int target ){

		if (owner.buff(WildMagic.WildMagicTracker.class)==null&&owner.buff(MagicImmune.class)!=null){
			GLog.w( Messages.get(Wand.class, "no_magic") );
			return false;
		}

		//if we're using wild magic, then assume we have charges
		if ( owner.buff(WildMagic.WildMagicTracker.class) != null || charge >= 1){
			return true;
		} else {
			GLog.w(Messages.get(Wand.class, "fizzles"));
			return false;
		}
	}
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
								  MagicMissile.SHADOW,
								  curUser.sprite,
								  bolt.collisionPos,
								  callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}
	protected static CellSelector.Listener zapper = new  CellSelector.Listener() {

		@Override
		public void onSelect( Integer target ) {

			if (target != null) {

				//FIXME this safety check shouldn't be necessary
				//it would be better to eliminate the curItem static variable.
				final 灵魂焰灯 curWand;
				if (curItem instanceof 灵魂焰灯) {
					curWand = (灵魂焰灯) Wand.curItem;
				} else {
					return;
				}

				final Ballistica
						shot = new Ballistica(curUser.pos,target,curWand.collisionProperties(target));
				int cell = shot.collisionPos;

				if (target == curUser.pos || cell == curUser.pos) {
					GLog.i( Messages.get(Wand.class, "self_target") );
					return;
				}

				curUser.sprite.zap(cell);

				//attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
				if (Actor.findChar(target) != null)
					QuickSlotButton.target(Actor.findChar(target));
				else
					QuickSlotButton.target(Actor.findChar(cell));

				if (curWand.tryToZap(curUser, target)) {

					curUser.busy();

					//backup barrier logic
					//This triggers before the wand zap, mostly so the barrier helps vs skeletons
					//					if (curWand.curCharges == curWand.chargesPerCast()
					//							&& curWand.charger != null && curWand.charger.target == curUser){
					//
					//					}

					if (curWand.cursed){
						Badges.解锁巫女();
						if (!curWand.cursedKnown){
							GLog.n(Messages.get(Wand.class, "curse_discover", curWand.name()));
							Dungeon.hero.sprite.哭泣();
						}
						CursedWand.cursedZap(curWand,
											 curUser,
											 new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT),
											 new Callback() {
												 @Override
												 public void call() {
													 curWand.wandUsed(curUser);
												 }
											 });
					} else {
						curWand.fx(shot, new Callback() {
							public void call() {
								curWand.onZap(shot);
								if (Random.Float()<WondrousResin.extraCurseEffectChance()){
									WondrousResin.forcePositive = true;
									CursedWand.cursedZap(curWand,
														 curUser,
														 new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT), new Callback() {
												@Override
												public void call() {
													WondrousResin.forcePositive = false;
													curWand.wandUsed(curUser);
												}
											});
								} else {

									curWand.wandUsed(curUser);
								}
							}
						});

					}
					curWand.cursedKnown = true;

				}

			}
		}

		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};
	@Override
	public Item 升级() {
		chargeCap = Math.min(2+2*等级(),6);
		if (等级() >= 3)
			image = 物品表.灵魂焰灯4;
		else if (等级() >= 2)
			image = 物品表.灵魂焰灯3;
		else if (等级() >= 1)
			image = 物品表.灵魂焰灯2;
		return super.升级();
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (等级() >= 3) image = 物品表.灵魂焰灯4;
		else if (等级() >= 2) image = 物品表.灵魂焰灯3;
		else if (等级() >= 1) image = 物品表.灵魂焰灯2;
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new 燃烧();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null){
			partialCharge += 0.025f*amount;
			while (partialCharge >= 1){
				partialCharge--;
				charge++;
			}
			if (charge >= chargeCap){
				partialCharge = 0;
			}
			updateQuickslot();
		}
	}
	
	@Override
	public String desc() {
		String desc = "";

		if (isEquipped (Dungeon.hero)){
			desc += "\n\n";
			if (cursed)
				desc += Messages.get(this, "desc_cursed");
			else
				desc += Messages.get(this, "desc",魔力(0.3f,1f)
						,魔力(1.2f,0.75f));
		}



		return desc;
	}

	public class 燃烧 extends ArtifactBuff {
		@Override
		public boolean act() {
			if (charge < chargeCap
				&&!cursed
				&& target.buff(MagicImmune.class) == null
				&&再生.regenOn()) {
				//120 turns to charge at full, 60 turns to charge at 0/8
				float chargeGain = 1 / (44 - (chargeCap - charge)*4);
				chargeGain *= 能量之戒.artifactChargeMultiplier(target);
				partialCharge += chargeGain;

				while (partialCharge >= 1) {
					partialCharge --;
					charge ++;

					if (charge == chargeCap){
						partialCharge = 0;
					}
				}
			}

			updateQuickslot();

			spend( TICK );

			return true;
		}
	}

}
