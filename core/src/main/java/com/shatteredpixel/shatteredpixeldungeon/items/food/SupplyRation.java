

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class SupplyRation extends Food {

	{
		image = 物品表.SUPPLY_RATION;
		energy = 2*Hunger.HUNGRY/3f; //200 food value
		
		遗产= false;
	}

	@Override
	public float eatingTime(){
		return super.eatingTime()-1;
	}

	@Override
	protected void satisfy(Hero hero) {
		super.satisfy(hero);

		hero.回血(5);

		CloakOfShadows cloak = hero.belongings.getItem(CloakOfShadows.class);
		if (cloak != null) {
			cloak.directCharge(1);
			ScrollOfRecharging.charge(hero);
		}
	}

	@Override
	public int 金币() {
		return 10 * quantity;
	}

}
