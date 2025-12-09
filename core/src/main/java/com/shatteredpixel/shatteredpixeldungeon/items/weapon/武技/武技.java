package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public abstract class 武技{
	public static float 伤害75=0.75f;
	public static float 伤害81=0.8125f;
	public static float 伤害87=0.875f;
	public static float 伤害93=0.9375f;
	public static float 伤害100=1f;
	public static float 伤害106=1.0625f;
	public static float 伤害112=1.125f;
	public static float 伤害118=1.1875f;
	public static float 伤害125=1.25f;
	public static float 伤害131=1.3125f;
	public static float 伤害137=1.375f;
	public static float 伤害143=1.4375f;
	public static float 伤害150=1.5f;
	public static float 伤害156=1.5625f;
	
	Hero hero;
	Weapon wep;
	String desc="";
	
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
