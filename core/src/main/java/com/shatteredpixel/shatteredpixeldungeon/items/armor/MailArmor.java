

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class MailArmor extends Armor {

	{
		image = 物品表.ARMOR_MAIL;
		换甲=Assets.Sounds.链甲;
	}
	
	public MailArmor() {
		super( 3 );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{LeatherArmor.class,
					护甲修理工具包.class,};
			inQuantity = new int[]{1,1,};

			cost = 6;

			output = MailArmor.class;
			outQuantity = 1;
		}

	}
}
