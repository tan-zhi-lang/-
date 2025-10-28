

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HermitCrabSprite;

public class HermitCrab extends Crab {

	{
		spriteClass = HermitCrabSprite.class;

		生命 = 最大生命 = 25; //+67% HP
		baseSpeed = 1f; //-50% speed

		//3x more likely to drop meat, and drops a guaranteed armor
		lootChance = 0.5f;
	}

	@Override
	public void rollToDropLoot() {
		super.rollToDropLoot();

		if (Dungeon.hero.等级 <= 最大等级 + 2){
			Dungeon.level.drop(Generator.randomArmor(), pos).sprite.drop();
		}
	}

	@Override
	public int 最小防御() {
		return super.最小防御()+2; //2-6 DR total, up from 0-4
	}
	@Override
	public int 最大防御() {
		return super.最大防御()+2; //2-6 DR total, up from 0-4
	}

}
