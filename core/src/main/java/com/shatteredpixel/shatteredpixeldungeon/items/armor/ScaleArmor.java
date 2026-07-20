

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ScaleArmor extends Armor {

	{
		image = 物品表.ARMOR_SCALE;
		换甲=Assets.Sounds.鳞甲;
	}
	
	public ScaleArmor() {
		super( 4 );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{MailArmor.class,
					护甲修理工具包.class,};
			inQuantity = new int[]{1,1,};

			cost = 8;

			output = ScaleArmor.class;
			outQuantity = 1;
		}

	}
}
