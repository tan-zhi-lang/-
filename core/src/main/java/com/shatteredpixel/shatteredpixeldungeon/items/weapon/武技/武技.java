package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public abstract class 武技{
	
	Hero hero;
	Weapon wep;
	String desc="";

	public Class<?>[] all=new Class<?>[]{
				暴伤.class,//
				背刺.class,//
				吃下.class,
				充能射击x.class,
				刺击.class,//
				刺退.class,//
				大杀四方.class,
				防御姿态.class,
				符文x.class,
				割草.class,
				横扫.class,
				剑舞.class,//
				立地.class,
				连击.class,//
				劈斩.class,//
				破击.class,//
				潜行.class,
				群魔乱舞.class,
				嗜血.class,//
				甩击.class,
				突刺.class,//
				无情铁手.class,//
				斩击.class,//
				重击.class//
	};
	public boolean 目标=false;
	public int 消耗=1;
	public abstract void 武技(Hero hero,Weapon wep);
	public String name(){
		return getClass().getSimpleName();
	}
	public String desc(){
		return desc+"。";
	}
}
