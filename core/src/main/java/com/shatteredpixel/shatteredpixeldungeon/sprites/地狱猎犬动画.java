

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 地狱猎犬动画 extends MobSprite {

	private Animation cast;

	public 地狱猎犬动画() {
		super();

		texture( Assets.Sprites.GNOLL );

		TextureFilm frames = new TextureFilm( texture, 12, 15 );

		int c = 63;

		idle = new Animation( 2, true );
		idle.frames( frames, 0+c, 0+c, 0+c, 1+c, 0+c, 0+c, 1+c, 1+c );

		run = new Animation( 12, true );
		run.frames( frames, 4+c, 5+c, 6+c, 7+c );

		attack = new Animation( 12, false );
		attack.frames( frames, 2+c, 3+c, 0+c );

		cast = attack.clone();

		die = new Animation( 12, false );
		die.frames( frames, 8+c, 9+c, 10+c );

		play( idle );
	}
}
