

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;

import java.util.ArrayList;

public abstract class RemainsItem extends Item {

	{
		bones = false;

		defaultAction = AC_USE;
	}

	public static final String AC_USE =  "USE";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_USE);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);

		if (action.equals(AC_USE)){
			hero.sprite.operate(hero.pos);

			Catalog.countUse(getClass());
			doEffect(hero);

			hero.spendAndNext(Actor.TICK);
			detach(hero.belongings.backpack);
		}
	}

	protected abstract void doEffect(Hero hero);

	@Override
	public boolean 已鉴定() {
		return true;
	}

	@Override
	public boolean 可升级() {
		return false;
	}

	@Override
	public int 金币() {
		return 50;
	}

	public static RemainsItem get(HeroClass cls){
		switch (cls){
			case WARRIOR: default:
				return new SealShard();
			case MAGE:
				return new BrokenStaff();
			case 盗贼:
				return new CloakScrap();
			case HUNTRESS:
				return new BowFragment();
			case DUELIST:
				return new BrokenHilt();
			case CLERIC:
				return new TornPage();
			case 巫女:
				return new TornPage();
		}
	}

}
