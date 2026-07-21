

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 血药 extends 用品 {
	
	
	{
		icon = Dungeon.isChallenged(Challenges.NO_HEALING)?物品表.Icons.毒粹:物品表.Icons.POTION_HEALING;
		image = 物品表.血药;
		红色=true;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{治疗药剂.class,};
			inQuantity = new int[]{1,};

			cost = 5;

			output = 血药.class;
			outQuantity = 5;
		}

	}
	public static void pharmacophobiaProc( Hero hero ){
		// harms the hero for ~40% of their max HP in poison
		Buff.施加(hero,Poison.class).set(1+hero.等级/8f);
	}

	@Override
	public void 使用(Hero hero){
		if (Dungeon.isChallenged(Challenges.NO_HEALING)){
			pharmacophobiaProc(hero);
		} else {
			hero.回百分比血(0.2f);
		}
		super.使用(hero);
	}

	@Override
	public int 金币() {
		return Math.round(7.5f * quantity*1.125f);
	}
}
