

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.watabou.noosa.TextureFilm;

public class 蠕虫动画 extends MobSprite {

	public 蠕虫动画() {
		super();
		
		texture( Assets.Sprites.LARVA );
		
		TextureFilm frames = new TextureFilm( texture, 12, 8 );

		idle = new Animation( 5, true );
		idle.frames( frames, 0, 1);

		run = new Animation( 12, true );
		run.frames( frames, 0, 1, 2, 3 );

		attack = new Animation( 15, false );
		attack.frames( frames, 4, 5, 6 , 7 );

		die = new Animation( 10, false );
		die.frames( frames, 2 ,3 );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0x000000;
	}
	
	@Override
	public void die() {
		Splash.at( center(), blood(), 10 );
		super.die();
	}
}
