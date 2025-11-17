

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

import java.util.ArrayList;

public class 用品 extends Item {
	
	protected static final String AC_USE = "WEAR";
	
	{
		defaultAction = AC_USE;
		
		特别= true;
		物品 = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_USE );
		return actions;
	}

}
