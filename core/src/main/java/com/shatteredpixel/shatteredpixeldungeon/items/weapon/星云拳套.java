

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 星云拳套 extends Weapon{
	{
		image = 物品表.星云拳套;
		hitSound = Assets.Sounds.攻击棍;
		技能=new 连击();
		延迟= 0.5f;
		特别=true;
		粉色=true;
		tier=5;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe{

		{
			inputs=new Class[]{魔岩拳套.class,
					浮空药剂.class,
					器灵.class,};
			inQuantity=new int[]{1,
					1,1,};

			cost=15;

			output=星云拳套.class;
			outQuantity=1;
		}
	}
}
