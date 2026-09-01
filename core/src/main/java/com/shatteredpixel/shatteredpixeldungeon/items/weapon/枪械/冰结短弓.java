

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.冰霜箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.particles.Emitter;

public class 冰结短弓 extends 短弓{
	
	{
		image = 物品表.冰结短弓;
		
		tier = 5;
		冻结=0.15f;
		子弹 = new 冰霜箭矢();
		image2 = 物品表.冰霜箭矢;
		hitSound2 = Assets.Sounds.攻击灵箭;
		item_Miss2 = Assets.Sounds.攻击灵箭;

		无限子弹=true;
		掉落子弹=false;
	}

	@Override
	protected Emitter 发射粒子() {
		Emitter e = new Emitter();
		e.pos(5, 5);
		e.fillTarget = false;
		e.pour(MagicMissile.MagicParticle.FACTORY, 0.01f);
		return e;
	}

	@Override
	protected int 命中颜色() {
		return 0x3399FF;
	}

	@Override
	protected Emitter.Factory 命中粒子() {
		return MagicMissile.MagicParticle.FACTORY;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{灵能短弓.class,
					冰霜药剂.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 冰结短弓.class;
			outQuantity = 1;
		}

	}
}
