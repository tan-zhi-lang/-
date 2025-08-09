

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ToxicImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.PoisonParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ElixirOfToxicEssence extends Elixir {
	
	{
		image = 物品表.ELIXIR_TOXIC;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.施加(hero, ToxicImbue.class).set(ToxicImbue.DURATION);
		hero.sprite.emitter().burst(PoisonParticle.SPLASH, 10);
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfCorrosiveGas.class};
			inQuantity = new int[]{1};
			
			cost = 8;
			
			output = ElixirOfToxicEssence.class;
			outQuantity = 1;
		}
		
	}
	
}
