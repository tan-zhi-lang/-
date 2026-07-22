

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfEarthenArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.立地;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 地裂镰 extends Weapon{

	{
		image = 物品表.地裂镰;
		hitSound = Assets.Sounds.巨剑;
		
		技能=new 立地();
		
		tier = 5;
		延迟= 2;
		特别=true;
		黄色=true;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{战镰.class,
					PotionOfEarthenArmor.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 地裂镰.class;
			outQuantity = 1;
		}

	}


}
