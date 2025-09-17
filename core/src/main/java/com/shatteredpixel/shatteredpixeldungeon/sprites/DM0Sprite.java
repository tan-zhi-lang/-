

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class DM0Sprite extends MobSprite {
	
	public DM0Sprite() {
		super();
		
		texture( Assets.Sprites.DM0 );
		
		TextureFilm frames = new TextureFilm( texture );
		
		idle = new Animation( 8, true );
		idle.frames( frames, 0, 1 );
		
		run = new Animation( 12, true );
		run.frames( frames, 0, 1 );
		
		attack = new Animation( 12, false );
		attack.frames( frames, 2, 2, 0, 1 );
		
		die = new Animation( 12, false );
		die.frames( frames, 3, 4, 5 );
		
		play( idle );
	}
}
