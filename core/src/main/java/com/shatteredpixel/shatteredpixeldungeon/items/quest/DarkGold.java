

package com.shatteredpixel.shatteredpixeldungeon.items.quest;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class DarkGold extends Item {
	
	{
		image = 物品表.ORE;
		
		可堆叠= true;
		特别= true;
		物品 = true;
		defaultAction = Dungeon.符文("吞噬星空")?AC_USE:null;
	}
	protected static final String AC_USE = "USE";
	boolean 重复使用=false;
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		if(hero.符文("吞噬星空"))
		actions.add( AC_USE );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)&&hero.符文("吞噬星空")) {
				hero.属性成长+=0.01f;
				hero.sprite.operate(hero.pos);
				hero.spend(1f);
				hero.busy();
				detach(hero.belongings.backpack);
		}
	}
}
