

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 石虱动画 extends MobSprite {

	public 石虱动画() {
		super();
		
		texture( Assets.Sprites.石虱 );
		
		TextureFilm frames = new TextureFilm( texture );
		
		idle = new Animation( 15, true );
		idle.frames( frames, 0, 1, 2, 3, 4, 5 );
		
		run = new Animation( 15, true );
		run.frames( frames, 0, 1, 2, 3, 4, 5 );
		
		attack = new Animation( 20, false );
		attack.frames( frames, 6, 7, 8, 9 );
		
		die = new Animation( 15, false );
		die.frames( frames, 10, 11, 12, 13, 14 );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0x000000;
	}
}
