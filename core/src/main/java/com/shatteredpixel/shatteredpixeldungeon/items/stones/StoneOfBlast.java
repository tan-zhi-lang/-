

package com.shatteredpixel.shatteredpixeldungeon.items.stones;

import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class StoneOfBlast extends Runestone {
	
	{
		image = 物品表.STONE_BLAST;
	}
	
	@Override
	protected void activate(int cell) {
		new Bomb.ConjuredBomb().explode(cell);
	}
	
}
