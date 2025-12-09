

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand.wandProc;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.WondrousResin;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 魔攻之戒 extends Ring {

	{
		icon = 物品表.Icons.魔攻之戒;
		buffClass = 法杖.class;
		defaultAction = AC_ZAP;
		usesTargeting = true;
	}
	public static final String AC_ZAP	= "ZAP";
	
	public String defaultAction(){
		if(levelKnown){
			return defaultAction;
		}
		return null;
	}
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		if(Dungeon.炼狱(炼狱设置.诅咒法杖)){
		
		}else{
			if (curCharges > 0 &&levelKnown&&isEquipped(hero)) {
				actions.add( AC_ZAP );
			}
		}
		
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {
		
		super.execute( hero, action );
		if(Dungeon.炼狱(炼狱设置.诅咒法杖)){
		
		}else{
			if (action.equals( AC_ZAP )&&levelKnown) {
				curUser = hero;
				curItem = this;
				GameScene.selectCell(zapper);
			}
		}
	}
	
	public void onZap(Ballistica bolt) {
		
		Char ch = Actor.findChar( bolt.collisionPos );
		if (ch != null) {
			
			wandProc(ch,soloBuffedBonus(), 1);
			ch.受伤时(Hero.heroDamageIntRange(
					5+soloBuffedBonus(),
					9+6*soloBuffedBonus()
					), this);
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f) );
			
			ch.sprite.burst(0xFFFFFFFF, 强化等级() / 2 + 2);
			
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
		if ( owner.buff(WildMagic.WildMagicTracker.class) != null || curCharges >= 1){
			return true;
		} else {
			GLog.w(Messages.get(Wand.class, "fizzles"));
			return false;
		}
	}
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
								  MagicMissile.MAGIC_MISSILE,
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
				final 魔攻之戒 curWand;
				if (curItem instanceof 魔攻之戒) {
					curWand = (魔攻之戒) Wand.curItem;
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
						}
						CursedWand.cursedZap(curWand,
											 curUser,
											 new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT),
											 new Callback() {
												 @Override
												 public void call() {
													 curWand.wandUsed();
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
													curWand.wandUsed();
												}
											});
								} else {
									
									curWand.wandUsed();
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
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",5+soloBuffedBonus(),9+6*soloBuffedBonus());
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",5+combinedBuffedBonus(Dungeon.hero),9+combinedBuffedBonus(Dungeon.hero)*6);
			}
			return info;
		} else {
			return Messages.get(this, "stats",5,9);
		}
	}
	@Override
	public String status() {
		if (levelKnown) {
			return curCharges + "/" + maxCharges;
		} else {
			return null;
		}
	}
	
	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return (5+level)+"~"+(9+6*level);
	}
	@Override
	protected RingBuff buff( ) {
		return new 法杖();
	}
	public void updateLevel() {
		maxCharges = Math.min( 2 + 等级()/2, 10);
		curCharges = Math.min( curCharges,maxCharges);
	}
	@Override
	public Item 升级() {
		
		super.升级();
		updateLevel();
		updateQuickslot();
		
		return this;
	}
	
	@Override
	public Item 降级() {
		super.降级();
		
		updateLevel();
		updateQuickslot();
		
		return this;
	}
	public float partialCharge = 0f;
	
	public int maxCharges = 2;
	public int curCharges = maxCharges;
	
	private static final String CUR_CHARGES         = "curCharges";
	private static final String MAX_CHARGES         = "maxcharges";
	private static final String PARTIALCHARGE       = "partialCharge";
	
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( CUR_CHARGES, curCharges );
		bundle.put( MAX_CHARGES, maxCharges );
		bundle.put( PARTIALCHARGE , partialCharge );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		curCharges = bundle.getInt( CUR_CHARGES );
		maxCharges = bundle.getInt( MAX_CHARGES );
		partialCharge = bundle.getFloat( PARTIALCHARGE );
	}
	public void gainCharge( float amt ){
		gainCharge( amt, false );
	}
	
	public void gainCharge( float amt, boolean overcharge ){
		partialCharge += amt;
		while (partialCharge >= 1) {
			if (overcharge) curCharges = Math.min(maxCharges+(int)amt, curCharges+1);
			else curCharges = Math.min(maxCharges, curCharges+1);
			partialCharge--;
			updateQuickslot();
		}
	}
	
	public void wandUsed() {
		
		curCharges -= 1;
		
		Invisibility.notimedispel();
		updateQuickslot();
		
		curUser.spendAndNext( TIME_TO_ZAP );
	}
	private static final float TIME_TO_ZAP	= 1f;
	public class 法杖 extends RingBuff {
		
		private static final float BASE_CHARGE_DELAY = 10f;
		private static final float SCALING_CHARGE_ADDITION = 40f;
		private static final float NORMAL_SCALE_FACTOR = 0.875f;
		
		private static final float CHARGE_BUFF_BONUS = 0.25f;
		
		float scalingFactor = NORMAL_SCALE_FACTOR;
		
		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				//if we're loading in and the hero has partially spent a turn, delay for 1 turn
				if (target instanceof Hero&&Dungeon.hero==null&&cooldown()==0&&target.cooldown()>0) {
					spend(TICK);
				}
				return true;
			}
			return false;
		}
		
		@Override
		public boolean act() {
			if (curCharges < maxCharges &&target.buff(MagicImmune.class)==null)
				recharge();
			
			while (partialCharge >= 1 && curCharges < maxCharges) {
				partialCharge--;
				curCharges++;
				updateQuickslot();
			}
			
			if (curCharges == maxCharges){
				partialCharge = 0;
			}
			
			spend( TICK );
			
			return true;
		}
		
		public void recharge(){
			int missingCharges = maxCharges - curCharges;
			missingCharges = Math.max(0, missingCharges);
			float turnsToCharge = (float) (BASE_CHARGE_DELAY
										   + ((SCALING_CHARGE_ADDITION) * Math.pow(scalingFactor, missingCharges)));
			
			if (再生.regenOn())
				partialCharge += 1f/turnsToCharge/2f;
			
			for (Recharging bonus : target.buffs(Recharging.class)){
				if (bonus != null && bonus.remainder() > 0f) {
					partialCharge += CHARGE_BUFF_BONUS * bonus.remainder();
				}
			}
		}
		
		
		public void gainCharge(float charge){
			if (curCharges < maxCharges) {
				partialCharge += charge;
				while (partialCharge >= 1f) {
					curCharges++;
					partialCharge--;
				}
				if (curCharges >= maxCharges){
					partialCharge = 0;
					curCharges = maxCharges;
				}
				updateQuickslot();
			}
		}
		
		private void setScaleFactor(float value){
			this.scalingFactor = value;
		}
	}
}
