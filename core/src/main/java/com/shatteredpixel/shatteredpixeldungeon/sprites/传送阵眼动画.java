package com.shatteredpixel.shatteredpixeldungeon.sprites;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 传送阵眼动画 extends MobSprite{


	public 传送阵眼动画(){
		super();
		
		texture(Assets.Sprites.传送阵眼);


		TextureFilm frames = new TextureFilm(texture,9,16 );

		idle = new Animation( 1, true );
		idle.frames( frames, 0);

		run = new Animation( 1, true );
		run.frames( frames, 0);

		attack = new Animation( 1, false );
		attack.frames( frames, 0 );

		die = new Animation( 1, false );
		die.frames( frames, 0);

		play( idle );
	}

}