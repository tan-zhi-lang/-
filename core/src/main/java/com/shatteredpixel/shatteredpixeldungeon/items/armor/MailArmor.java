

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class MailArmor extends Armor {

	{
		image = 物品表.ARMOR_MAIL;
		换甲=Assets.Sounds.链甲;
	}
	
	public MailArmor() {
		super( 3 );
	}

}
