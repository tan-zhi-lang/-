

package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.算法.金额;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class Gold extends Item {

	{
		image = 物品表.GOLD;
		可堆叠= true;
		无动作= true;
		物品=true;
	}

	public Gold() {
		this(Math.round(Random.IntRange(30, 60)/金额));
	}
	
	public Gold( int value ) {
		this.quantity = value;
	}

	@Override
	public boolean 放背包(Bag container) {
		if (super.放背包(container)){
			if (container.owner instanceof Hero){
					Dungeon.gold(Math.round((价值提升?quantity*1.25f:quantity)));

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
//		Dungeon.gold(quantity);
//		Badges.validateGoldCollected();
//
//		GameScene.pickUp( this, pos );
//		hero.spendAndNext( pickupDelay() );
//
//		updateQuickslot();
//
//		return true;
//	}
	
	@Override
	public Item random() {
		quantity = Math.round(Random.IntRange(30, 60)/金额);
//		quantity = Random.IntRange( 30 + Dungeon.depth * 10, 60 + Dungeon.depth * 20 );
		return this;
	}

	public Item random(float f) {
		quantity = Math.round(Random.IntRange(30, 60)/金额*f);
		//		quantity = Random.IntRange( 30 + Dungeon.depth * 10, 60 + Dungeon.depth * 20 );
		return this;
	}
}
