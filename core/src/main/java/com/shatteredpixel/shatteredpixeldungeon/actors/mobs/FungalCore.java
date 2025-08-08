

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FungalCoreSprite;

public class FungalCore extends Mob {

	{
		生命 = 最大生命 = 300;
		spriteClass = FungalCoreSprite.class;

		经验 = 20;

		state = PASSIVE;

		properties.add(Property.IMMOVABLE);
		properties.add(Property.BOSS);
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	public float spawningWeight() {
		return 0;
	}

	@Override
	public void die(Object cause) {
		super.die(cause);
		Blacksmith.Quest.beatBoss();
	}
}
