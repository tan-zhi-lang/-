

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 霰弹枪 extends 枪械{
	
	{
		image = 物品表.霰弹枪;
		tier = 3;
		伤害=0.6f;
		投掷=1.75f;
		精度=0.4f;
		射速=2;

		霰弹效果=true;
		子弹=new 霰弹枪();
		image2 = 物品表.霰弹枪子弹;
		hitSound2 = Assets.Sounds.霰弹枪;
		item_Miss2 = Assets.Sounds.霰弹枪;
	}
	@Override
	public int initialCharges() {
		return 2;
	}
}
