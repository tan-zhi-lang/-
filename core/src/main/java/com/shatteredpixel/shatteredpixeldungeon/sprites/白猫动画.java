

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 白猫动画 extends MobSprite{
	
	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 15;
	private static final int RUN_FRAMERATE	= 20;
	public 白猫动画(){
		super();
		
		texture(Assets.Sprites.白猫);
		
		TextureFilm film = new TextureFilm(texture,FRAME_WIDTH,FRAME_HEIGHT );
		
		idle = new Animation( 1, true );
		idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );
		
		run = new Animation( RUN_FRAMERATE, true );
		run.frames( film, 2, 3, 4, 5, 6, 7 );
		
		die = new Animation( 20, false );
		die.frames( film, 8, 9, 10, 11, 12, 11 );
		
		attack = new Animation( 15, false );
		attack.frames( film, 13, 14, 15, 0 );
		
		zap = attack.clone();
		
		operate = new Animation( 8, false );
		operate.frames( film, 16, 17, 16, 17 );
		play( idle );
	}
	
}
