

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战斧 extends Weapon {

	{
		image = 物品表.BATTLE_AXE;
		hitSound = Assets.Sounds.巨剑;
		
		延迟= 1.75f;
		
		tier = 3;
		技能=new 无情铁手();
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{手斧.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 9;

			output = 战斧.class;
			outQuantity = 1;
		}

	}

}
