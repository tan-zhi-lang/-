package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;

public class 武技{
	int 消耗法力=10;
	boolean 选择目标=false;
	public Weapon wep;
	public void 武技(Hero hero,Integer target){
	
	}
	public String 武技名字(){
		return getClass().getSimpleName();
	}
	public String 武技介绍(){
		return Messages.get(this,"desc");
	}
}
