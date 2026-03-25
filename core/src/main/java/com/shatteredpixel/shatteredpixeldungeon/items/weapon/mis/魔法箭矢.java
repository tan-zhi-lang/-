package com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 魔法箭矢 extends Weapon{
	{

		image = 物品表.魔法箭矢;

		hitSound = Assets.Sounds.HIT_ARROW;
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
