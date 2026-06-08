

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;

public class TargetedCell extends Image {

	private float alpha;

	public TargetedCell( int pos, int color ) {
		super(Icons.get(Icons.TARGET));
		hardlight(color);

		origin.set( width/2f );

		point( DungeonTilemap.tileToWorld( pos ) );

		alpha = 2f;//1
	}

	@Override
	public void update() {
		if ((alpha -= Game.elapsed/2f) > 0) {
			alpha( alpha );
			scale.set( alpha/2f );//1
		} else {
			killAndErase();
		}
	}
}
