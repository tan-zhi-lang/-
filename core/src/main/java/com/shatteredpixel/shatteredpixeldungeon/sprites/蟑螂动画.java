

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 蟑螂动画 extends MobSprite {

	public 蟑螂动画() {
		super();
		
		texture( Assets.Sprites.蟑螂 );
		
		TextureFilm frames = new TextureFilm( texture, 12, 8 );

		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 0, 1 );
		
		run = new Animation( 5, true );
		run.frames( frames, 2,3 );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 2, 3, 4, 5, 0 );
		
		die = new Animation( 5, false );
		die.frames( frames, 6,7,8);
		
		play( idle );
	}
}
