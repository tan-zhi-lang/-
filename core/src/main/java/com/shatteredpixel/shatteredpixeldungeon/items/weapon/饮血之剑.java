

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.嗜血;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 饮血之剑 extends Weapon{
	{
		image = 物品表.饮血之剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier=5;
		延迟=1.25f;
		吸血=0.15f;
		特别=true;
		红色=true;
		技能=new 嗜血();
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{巨剑.class,
					治疗药剂.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 饮血之剑.class;
			outQuantity = 1;
		}

	}
}
