

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class EnergyCrystal extends Item {

	{
		image = 物品表.ENERGY;
		可堆叠= true;
		物品= true;
		无动作= true;
	}

	public EnergyCrystal() {
		this(Random.IntRange(1,2));
	}

	public EnergyCrystal( int value ) {
		this.quantity = value;
	}


	@Override
	public boolean 放背包(Bag container) {
		if (super.放背包(container)){
			if (container.owner instanceof Hero){
				Dungeon.energy(quantity);

				Catalog.setSeen(getClass());
				Statistics.itemTypesDiscovered.add(getClass());

				Badges.validateGoldCollected();
			}
			detachAll();
			return true;
		} else{
			return false;
		}
	}
//	@Override
//	public boolean doPickUp(Hero hero, int pos) {
//
//		Catalog.setSeen(getClass());
//		Statistics.itemTypesDiscovered.add(getClass());
//
//		Dungeon.energy(quantity);
//		//TODO track energy collected maybe? We do already track recipes crafted though..
//
//		GameScene.pickUp( this, pos );
//		hero.spendAndNext( pickupDelay() );
//
//
//		updateQuickslot();
//
//		return true;
//	}
}
