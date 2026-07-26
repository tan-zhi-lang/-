

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 小吞噬怪动画 extends MobSprite {

	public 小吞噬怪动画() {
		super();
		
		texture( Assets.Sprites.小吞噬怪 );
		TextureFilm frames = new TextureFilm( texture, 8, 7 );

		idle = new Animation( 1, true );
		idle.frames( frames, 0);

		attack = new Animation( 1, true );
		attack.frames( frames, 1);
		run = new Animation( 2, true );
		run.frames( frames, 0,1);

		die = idle.clone();
		
		play( idle );
	}

}
