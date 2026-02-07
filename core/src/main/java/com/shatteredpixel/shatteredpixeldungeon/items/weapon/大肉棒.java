

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.吃下;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Bundle;

public class 大肉棒 extends Weapon{

	{
		image = 物品表.大肉棒;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		技能=new 吃下();
		双手=true;
		tier = 5;
	}

	public float 保鲜=3600;
	private static final String 保鲜x=        "保鲜";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(保鲜x,保鲜);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		保鲜= bundle.getFloat(保鲜x);
	}
}
