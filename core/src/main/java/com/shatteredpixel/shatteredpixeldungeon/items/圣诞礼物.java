

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣诞礼物 extends 用品 {
	
	
	{
		image = 物品表.圣诞礼物;
	}
	
	@Override
	public void 使用(Hero hero){
		Dungeon.level.drop(Generator.random(),curUser.pos).sprite().drop();
		super.使用(hero);
	}
}
