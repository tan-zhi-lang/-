

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;

import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 魔岩拳套 extends Weapon {
	
	{
		image = 物品表.GAUNTLETS;
		hitSound = Assets.Sounds.攻击棍;

		
		tier = 4;
		技能=new 连击();
		延迟= 0.5f;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe{

		{
			inputs=new Class[]{镶钉手套.class,
					LiquidMetal.class,};
			inQuantity=new int[]{1,
					1,};

			cost=12;

			output=魔岩拳套.class;
			outQuantity=1;
		}
	}

	}
