

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 魄罗 extends Weapon{
	
	{
		image = 物品表.魄罗;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		return new ArrayList<>();
	}

	@Override
	public String defaultAction() {
		return null;
	}
}
