

package com.shatteredpixel.shatteredpixeldungeon.items.keys;

import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class CrystalKey extends Key {
	
	{
		image = 物品表.CRYSTAL_KEY;
	}
	
	public CrystalKey() {
		this( 0 );
	}
	
	public CrystalKey( int depth ) {
		super();
		this.depth = depth;
	}
	
}
