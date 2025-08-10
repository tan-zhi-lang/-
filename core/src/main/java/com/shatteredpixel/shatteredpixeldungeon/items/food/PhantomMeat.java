

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PhantomMeat extends Food {

	{
		image = 物品表.PHANTOM_MEAT;
		energy = Hunger.STARVING;
	}

	@Override
	protected void satisfy(Hero hero) {
		super.satisfy(hero);
		effect(hero);
	}

	public int 金币() {
		return 30 * quantity;
	}

	public static void effect(Hero hero){

		Barkskin.conditionallyAppend( hero, hero.最大生命 / 4, 1 );
		Buff.施加( hero, Invisibility.class, Invisibility.DURATION );
		hero.生命 = Math.min( hero.生命 + hero.最大生命 / 4, hero.最大生命);
		hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(hero.最大生命 / 4), FloatingText.HEALING );
		PotionOfHealing.cure(hero);

	}


}
