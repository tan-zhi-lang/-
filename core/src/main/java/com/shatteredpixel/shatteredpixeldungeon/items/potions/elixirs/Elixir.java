

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;

public abstract class Elixir extends Potion {
	
	public abstract void apply( Hero hero );
	
	@Override
	public boolean isKnown() {
		return true;
	}

	@Override
	public int 金币() {
		return quantity * 60;
	}

	@Override
	public int 能量() {
		return quantity * 12;
	}
}
