

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;

public abstract class NPC extends Mob {

	{
		生命 = 最大生命 = 1;
		经验 = 0;

		alignment = Alignment.NEUTRAL;
		state = PASSIVE;
	}

	@Override
	protected boolean act() {
		if (Dungeon.level.heroFOV[pos]){
			Bestiary.setSeen(getClass());
		}

		return super.act();
	}

	@Override
	public void beckon( int cell ) {
	}
	
}