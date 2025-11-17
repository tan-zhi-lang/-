

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 骷髅战士动画 extends SkeletonSprite {
	
	public 骷髅战士动画() {
		super();
		
		texture( Assets.Sprites.骷髅战士 );
		
		TextureFilm frames = new TextureFilm( texture, 12, 15 );
		
		idle = new Animation( 12, true );
		idle.frames( frames, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3 );
		
		run = new Animation( 15, true );
		run.frames( frames, 4, 5, 6, 7, 8, 9 );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 14, 15, 16 );
		
		die = new Animation( 12, false );
		die.frames( frames, 10, 11, 12, 13 );
		
		play( idle );
	}
	
}
