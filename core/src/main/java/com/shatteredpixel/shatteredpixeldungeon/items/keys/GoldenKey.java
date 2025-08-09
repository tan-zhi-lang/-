

package com.shatteredpixel.shatteredpixeldungeon.items.keys;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class GoldenKey extends Key {
	
	{
		image = 物品表.GOLDEN_KEY;
	}

	public GoldenKey() {
		this( 0 );
	}
	
	public GoldenKey( int depth ) {
		super();
		this.depth = depth;
	}

}
