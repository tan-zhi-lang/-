

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战甲 extends Armor {

	{
		image = 物品表.战甲;
		换甲=Assets.Sounds.板甲;
		嬗变= false;
		专属=true;
	}
	public 战甲(){
        super(1);
    }

	@Override
	public float 最大防御(int lvl){
		return super.最大防御(lvl)+augment.defenseFactor(1);
	}
}