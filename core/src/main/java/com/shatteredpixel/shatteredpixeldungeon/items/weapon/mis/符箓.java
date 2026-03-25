package com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 符箓 extends Weapon{

		{
			image = 物品表.符箓;
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