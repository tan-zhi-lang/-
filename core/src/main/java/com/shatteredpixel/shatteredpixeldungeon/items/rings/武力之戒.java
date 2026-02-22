

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 武力之戒 extends Ring{
	
	{
		icon=物品表.Icons.RING_FORCE;
		buffClass=Force.class;
	}
	
	@Override
	protected RingBuff buff(){
		return new Force();
	}
	
	public static int armedDamageBonus(Char ch){
		return getBuffedBonus(ch,Force.class);
	}
	
	@Override
	public boolean doUnequip(Hero hero,boolean collect,boolean single){
		if(super.doUnequip(hero,collect,single)){
			if(hero.buff(BrawlersStance.class)!=null&&hero.buff(Force.class)==null){
				//clear brawler's stance if no ring of force is equipped
				hero.buff(BrawlersStance.class).active=false;
			}
			return true;
		}else{
			return false;
		}
	}
	
	// *** Weapon-like properties ***
	
	public static int tier(){
		float str=Dungeon.hero!=null?
				Dungeon.hero.力量():
				10;
		int tier=Math.round(Math.max(1,(str-8)/2f));
		//each str point after 18 is half as effective
		if(tier>5){
			tier=5+Math.round((tier-5)/2f);
		}
		return tier;
	}
	
	public static int notier(){
		int str=10;
		int tier=Math.round(Math.max(1,(str-8)/2f));
		//each str point after 18 is half as effective
		if(tier>5){
			tier=5+Math.round((tier-5)/2f);
		}
		return tier;
	}
	
	public static int 额外(Hero hero){
		int dmg=0;
		int level=getBuffedBonus(hero,Force.class);
		int tier=tier();
		if(hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
			dmg+=Hero.heroDamage(2,Math.round(1.5f*(Dungeon.hero.力量()-8)));
		}
		if(hero.buff(BrawlersStance.class)!=null&&hero.buff(BrawlersStance.class).active){
			// 3+tier base dmg, roughly +60%->45% dmg at T1->5
			// lvl*((4+2*tier)/8) scaling, +50% dmg
			dmg+=Math.round(3+tier+(level*((4+2*tier)/8f)));
		}
		return dmg;
	}
	
	public static int heromin(){
		return Math.round(0.1f * Dungeon.hero.力量());
	}
	
	public static int heromax(){
		return Math.round(Dungeon.hero.力量()-8);
	}
	public static int min(){
		int x=0;
		if(Dungeon.hero()){
			if(Dungeon.hero.hasbuff(Force.class)){
				x=Dungeon.hero.buff(Force.class).buffedLvl();
			}
		}
		return min(x,tier());
	}
	
	public static int max(){
		int x=0;
		if(Dungeon.hero()){
			if(Dungeon.hero.hasbuff(Force.class)){
				x=Dungeon.hero.buff(Force.class).buffedLvl();
			}
		}
		return max(x,tier());
	}
	
	public static int min(int lvl,float tier){
		if(lvl<=0){
			lvl=0;
		}
		
		return Math.max(0,Math.round((tier+  //base
									 lvl     //level scaling
									 )/2));
	}
	
	//same as equivalent tier weapon
	public static int max(int lvl,float tier){
		if(lvl<=0){
			lvl=0;
		}
		
		return Math.max(0,Math.round(2.5f*(tier+1)+    //base
									  lvl*(tier+1)    //level scaling
									 ));
	}
	
	@Override
	public String statsInfo(){
		float tier=tier();
		if(已鉴定()){
			int level=soloBuffedBonus();
			String info=Messages.get(this,"stats",heromin()+min(level,tier),
									 heromax()+max(level,tier),
									 max(level,1));
			if(isEquipped(Dungeon.hero)&&soloBuffedBonus()!=combinedBuffedBonus(Dungeon.hero)){
				level=combinedBuffedBonus(Dungeon.hero);
				info+="\n\n"+Messages.get(this,"combined_stats",heromin()+min(level,tier),
										  heromax()+max(level,tier),
										  max(level,1));
			}
			return info;
		}else{
			return Messages.get(this,"stats",heromin()+min(0,tier),
								heromax()+max(0,tier),max(0,1));
		}
	}
	
	@Override
	public String upgradeStat1(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		int tier=tier();
		return (heromin()+min(level+1,tier))+"~"+(heromax()+max(level+1,tier));
	}
	
	@Override
	public String upgradeStat2(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		return Integer.toString(max(level,1));
	}
	
	@Override
	public String upgradeStat3(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		if(Dungeon.hero!=null&&Dungeon.hero.heroClass(HeroClass.DUELIST)){
			int tier=tier();
			int bonus=Math.round(3+tier+(level*((4+2*tier)/8f)));
			return (min(level+1,tier)+bonus)+"-"+(max(level+1,tier)+bonus);
		}else{
			return null;
		}
	}
	
	public class Force extends RingBuff{}
	
	//Duelist stuff
	
	public static String AC_ABILITY="ABILITY";
	
	@Override
	public void activate(Char ch){
		super.activate(ch);
		if(ch instanceof Hero&&((Hero)ch).heroClass(HeroClass.DUELIST)){
//			Buff.施加(ch,MeleeWeapon.Charger.class);
		}
	}
	
	@Override
	public String defaultAction(){
		if(Dungeon.hero!=null&&Dungeon.hero.heroClass(HeroClass.DUELIST)){
			return AC_ABILITY;
		}else{
			return super.defaultAction();
		}
	}
	
	@Override
	public ArrayList<String> actions(Hero hero){
		ArrayList<String> actions=super.actions(hero);
		if(isEquipped(hero)&&hero.heroClass(HeroClass.DUELIST)){
			actions.add(AC_ABILITY);
		}
		return actions;
	}
	
	@Override
	public String actionName(String action,Hero hero){
		if(action.equals(AC_ABILITY)){
			return Messages.upperCase(Messages.get(this,"ability_name"));
		}else{
			return super.actionName(action,hero);
		}
	}
	
	@Override
	public void execute(Hero hero,String action){
		if(action.equals(AC_ABILITY)){
			if(hero.buff(BrawlersStance.class)!=null){
				if(!hero.buff(BrawlersStance.class).active){
					hero.buff(BrawlersStance.class).reset();
				}else{
					hero.buff(BrawlersStance.class).active=false;
				}
				BuffIndicator.refreshHero();
				AttackIndicator.updateState();
				hero.sprite.operate();
			}else if(!isEquipped(hero)){
				GLog.w(Messages.get(Weapon.class,"ability_need_equip"));
				
			}else{
				Buff.施加(hero,BrawlersStance.class).reset();
				AttackIndicator.updateState();
				hero.sprite.operate();
			}
		}else{
			super.execute(hero,action);
		}
	}
	
	@Override
	public String info(){
		String info=super.info();
		
//		if(Dungeon.hero!=null&&Dungeon.hero.heroClass(HeroClass.DUELIST)&&(anonymous||已鉴定()||isEquipped(Dungeon.hero))){
//			//0 if unidentified, solo level if unequipped, combined level if equipped
//			int level=已鉴定()?
//					(isEquipped(Dungeon.hero)?
//							 getBuffedBonus(Dungeon.hero,Force.class):
//							 soloBuffedBonus()):
//					0;
//			int tier=已鉴定()?
//					tier():
//					notier();
//			int dmgBoost=Math.round(3+tier+(level*((4+2*tier)/8f)));
//			if(已鉴定()){
//				info+="\n\n"+Messages.get(this,"ability_desc",min(level,tier)+dmgBoost,max(level,tier)+dmgBoost);
//			}else{
//				info+="\n\n"+Messages.get(this,"typical_ability_desc",min(level,tier)+dmgBoost,max(level,tier))+dmgBoost;
//			}
//		}
		
		return info;
	}
	
	public static boolean fightingUnarmed(Hero hero){
		
		if(hero.belongings.attackingWeapon()==null||hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
			return true;
		}
		if(hero.belongings.thrownWeapon!=null||hero.belongings.abilityWeapon!=null){
			return false;
		}
		BrawlersStance stance=hero.buff(BrawlersStance.class);
		if(stance!=null&&stance.active){
			//clear the buff if no ring of force is equipped
			if(hero.buff(武力之戒.Force.class)==null){
				stance.active=false;
				AttackIndicator.updateState();
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	public static boolean unarmedGetsWeaponEnchantment(Hero hero){
		if(hero.belongings.attackingWeapon()==null){
			return false;
		}
		if(hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
			return hero.buff(MonkEnergy.MonkAbility.FlurryEmpowerTracker.class)!=null;
		}
		BrawlersStance stance=hero.buff(BrawlersStance.class);
		if(stance!=null&&stance.active){
			return true;
		}
		return false;
	}
	
	public static boolean unarmedGetsWeaponAugment(Hero hero){
		if(hero.belongings.attackingWeapon()==null||hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
			return false;
		}
		BrawlersStance stance=hero.buff(BrawlersStance.class);
		if(stance!=null&&stance.active){
			return true;
		}
		return false;
	}
	
	public static class BrawlersStance extends Buff{
		
		{
			announced=true;
			type=buffType.POSITIVE;
		}
		
		//buff must be active for at least 50 turns, to discourage micro-managing for max charges
		public boolean active=true;
		private int minTurnsLeft;
		
		public void reset(){
			if(!active){
				//announce the buff
				target.sprite.showStatus(CharSprite.增强,Messages.titleCase(name()));
			}
			active=true;
			minTurnsLeft=50;
		}
		
		@Override
		public int icon(){
			return active?
					BuffIndicator.DUEL_BRAWL:
					BuffIndicator.NONE;
		}
		
		@Override
		public boolean act(){
			minTurnsLeft--;
			
			if(!active&&minTurnsLeft<=0){
				detach();
			}
			
			spend(TICK);
			return true;
		}
		
		public static final String ACTIVE="active";
		public static final String MIN_TURNS_LEFT="min_turns_left";
		
		@Override
		public void storeInBundle(Bundle bundle){
			super.storeInBundle(bundle);
			bundle.put(ACTIVE,active);
			bundle.put(MIN_TURNS_LEFT,minTurnsLeft);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle){
			super.restoreFromBundle(bundle);
			active=bundle.getBoolean(ACTIVE);
			minTurnsLeft=bundle.getInt(MIN_TURNS_LEFT);
		}
	}
}

