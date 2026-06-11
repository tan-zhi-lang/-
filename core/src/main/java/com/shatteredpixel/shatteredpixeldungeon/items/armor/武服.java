

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 武服 extends Armor {

	{
		image = 物品表.武服;
		换甲=Assets.Sounds.皮甲;
		嬗变= false;
		专属=true;
	}

	public 武服(){
		super(1);
	}

	
}