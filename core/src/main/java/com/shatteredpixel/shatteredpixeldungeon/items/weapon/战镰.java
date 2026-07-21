

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.横扫;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战镰 extends Weapon{

	{
		image = 物品表.WAR_SCYTHE;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		技能=new 横扫();
		
		tier = 4;
		延迟= 1.34f;
		伤害= 1.34f;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{短柄镰.class,
					LiquidMetal.class,};
			inQuantity = new int[]{1,1,};

			cost = 12;

			output = 战镰.class;
			outQuantity = 1;
		}

	}


}
