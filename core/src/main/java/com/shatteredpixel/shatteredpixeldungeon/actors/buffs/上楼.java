package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Food;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.上楼标;
import com.watabou.utils.Bundle;

public class 上楼 extends Buff  implements 上楼标.Action{

	public Food food= null;

	@Override
	public boolean attachTo(Char target){
		return super.attachTo(target);
	}


	@Override
	public boolean act(){
		if(target instanceof Hero hero&&Dungeon.level.visited[Dungeon.level.entrance()]&&hero.cellIsPathable(Dungeon.level.entrance())){
			上楼标.setAction(this);
			BuffIndicator.refreshHero();
		}else {
			上楼标.clearAction(this);
		}

		spend( TICK );

		return true;
	}
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		上楼标.setAction(this);
		BuffIndicator.refreshHero();

	}
	@Override
	public String actionName(){
		return name();
	}

	@Override
	public int indicatorColor() {
		return 0x999999;//紫色0x5500BB
	}


	@Override
	public void doAction(){
		Dungeon.hero.resting = false;
		if(Dungeon.hero.handle( Dungeon.level.entrance() ))
		Dungeon.hero.next();
	}

	@Override
	public void doAction2(){

	}

}