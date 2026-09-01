

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.玄武秘药;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.自然箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.particles.Emitter;

public class 自然之力 extends 短弓{
	
	{
		image = 物品表.自然之力;
		
		tier = 5;
		子弹 = new 自然箭矢();
		image2 = 物品表.自然箭矢;
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
		e.pour(LeafParticle.GENERAL, 0.01f);
		return e;
	}

	@Override
	protected int 命中颜色() {
		return 0x00FF00;
	}

	@Override
	protected Emitter.Factory 命中粒子() {
		return LeafParticle.GENERAL;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{灵能短弓.class,
					玄武秘药.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 自然之力.class;
			outQuantity = 1;
		}

	}
}
