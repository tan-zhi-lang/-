

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.职业.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

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
	
	public static float 额外(){
		Hero hero=Dungeon.hero;
		float dmg=0;
		if(Dungeon.hero()&&hero.力量()-10+2*tier()>0)dmg+=hero.力量()-10+2*tier();

		int level=getBuffedBonus(hero,Force.class);
		int tier=tier();
		if(Dungeon.hero()&&hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
			dmg+=Hero.英雄伤害(2,Math.round(1.5f*(Dungeon.hero.力量()-8)));
		}
		return dmg;
	}
	
	public static float heromin(){
		if(Dungeon.hero())
		return 0.05f * Dungeon.hero.力量();
		return 0.05f * 10;
	}
	
	public static float heromax(){
		if(Dungeon.hero())
		return Dungeon.hero.力量()-8.5f;
		return 10-8.5f;
	}
	public static float max(){
		int x=0;
		if(Dungeon.hero()){
			if(Dungeon.hero.hasbuff(Force.class)){
				x=Dungeon.hero.buff(Force.class).buffedLvl();
			}
		}
		return max(x,tier());
	}
	
	//same as equivalent tier weapon
	public static float max(int lvl,float tier){
		if(lvl<=0){
			lvl=0;
		}
		
		return Math.max(0,tier*(lvl+1)
									 )*5/2f;
	}
	
	@Override
	public String statsInfo(){
		float tier=tier();
		if(已鉴定()){
			int level=soloBuffedBonus();
			String info=Messages.get(this,"stats",
									 max(level,tier));
			if(isEquipped(Dungeon.hero)&&soloBuffedBonus()!=combinedBuffedBonus(Dungeon.hero)){
				level=combinedBuffedBonus(Dungeon.hero);
				info+="\n\n"+Messages.get(this,"combined_stats",
										  max(level,tier));
			}
			return info;
		}else{
			return Messages.get(this,"stats",max(0,tier));
		}
	}
	
	@Override
	public String upgradeStat1(int level){
		if(cursed&&cursedKnown){
			level=Math.min(-1,level-6);
		}
		int tier=tier();
		return ""+max(level,tier);
	}

	
	public class Force extends RingBuff{}

	
	public static boolean 空手(Hero hero){

		if(hero.belongings!=null){
			if(hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
				return true;
			}
			if(hero.belongings.weapon==null
			   &&hero.belongings.secondWep==null
			&&hero.belongings.thrownWeapon==null
			)
				return true;
		}
		return false;
	}

}

