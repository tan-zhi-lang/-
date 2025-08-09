

package com.shatteredpixel.shatteredpixeldungeon.items.keys;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class IronKey extends Key {
	
	{
		image = 物品表.IRON_KEY;
	}

	public IronKey() {
		this( 0 );
	}
	
	public IronKey( int depth ) {
		super();
		this.depth = depth;
	}

}
