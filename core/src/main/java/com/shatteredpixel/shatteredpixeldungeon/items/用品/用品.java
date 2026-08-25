

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;

import java.util.ArrayList;

public class 用品 extends Item{
	
	protected static final String AC_USE = "USE";
	
	{
		defaultAction = AC_USE;
		
		特别= true;
		可堆叠=true;
		物品 = true;
	}
	public boolean 完全消耗=false;
	boolean 重复使用=false;
	boolean 动作=true;
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
			if(动作){
				Catalog.countUse(getClass());
				hero.sprite.operate(hero.pos);
				hero.spend(1f);
				hero.busy();
			}
			hero.回血(hero.天赋点数(Talent.备战));

			if(!重复使用){
				if(完全消耗)detachAll();
				else
				detach();
			}
			使用(hero);
		}
	}
	public void 使用(Hero hero){
	
	}
}
