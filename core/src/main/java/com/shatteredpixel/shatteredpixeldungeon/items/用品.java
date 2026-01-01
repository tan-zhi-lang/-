

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;

import java.util.ArrayList;

public class 用品 extends Item {
	
	protected static final String AC_USE = "USE";
	
	{
		defaultAction = AC_USE;
		
		特别= true;
		可堆叠=true;
		物品 = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_USE );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {
		
		super.execute( hero, action );
		
		if (action.equals(AC_USE)) {
			Catalog.countUse(getClass());
			hero.sprite.operate(hero.pos);
			hero.spend( 1f );
			hero.busy();
			detach(hero.belongings.backpack);
			使用(hero);
		}
	}
	public void 使用(Hero hero){
	
	}
}
