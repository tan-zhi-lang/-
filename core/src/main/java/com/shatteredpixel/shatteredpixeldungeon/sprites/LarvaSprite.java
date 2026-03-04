

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.watabou.noosa.TextureFilm;

public class LarvaSprite extends MobSprite {
	
	public LarvaSprite() {
		super();
		
		texture( Assets.Sprites.LARVA );
		int x=16;
		TextureFilm frames = new TextureFilm( texture, 12, 8 );
		
		idle = new Animation( 5, true );
		idle.frames( frames, 0+x, 1+x);
		
		run = new Animation( 12, true );
		run.frames( frames, 0+x, 1+x, 2+x, 3+x );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 4+x, 5+x, 6+x , 7+x );
		
		die = new Animation( 10, false );
		die.frames( frames, 2+x ,3+x );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0xbbcc66;
	}
	
	@Override
	public void die() {
		Splash.at( center(), blood(), 10 );
		super.die();
	}
}
