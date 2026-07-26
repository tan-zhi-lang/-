

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺击;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 海神三叉戟 extends Weapon{
	
	{
		image = 物品表.海神三叉戟;
		hitSound = Assets.Sounds.长枪;
		
		技能=new 刺击();
		特别=true;
		蓝色=true;
		延迟= 1.2f;
//		连招范围=2;
		范围 = 2;
		
		tier = 5;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{三叉戟.class,
					ElixirOfAquaticRejuvenation.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 海神三叉戟.class;
			outQuantity = 1;
		}

	}
}
