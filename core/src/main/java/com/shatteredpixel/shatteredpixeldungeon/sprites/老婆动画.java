

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.老婆;
import com.watabou.noosa.TextureFilm;

public class 老婆动画 extends MobSprite{

	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 16;
	private static final int RUN_FRAMERATE	= 20;

	public 老婆动画() {
		super();

		texture(Assets.Sprites.老婆);
		updateArmor( 0 );
		idle();
	}

	@Override
	public void link( Char ch) {
		super.link( ch );
		updateArmor();
	}
	public void updateArmor(){
		updateArmor( ((老婆)ch).armTier);
	}
	public void updateArmor( int x) {
		texture(Assets.Sprites.老婆);
		x*=16;
		TextureFilm film = new TextureFilm(texture,FRAME_WIDTH,FRAME_HEIGHT );
		
		idle = new Animation( 1, true );
		idle.frames(film,0+x,0+x,0+x,1+x,0+x,0+x,1+x,1+x);
		
		run = new Animation(RUN_FRAMERATE+x,true );
		run.frames(film,2+x+x,3+x+x,4+x+x,5+x+x,6+x+x,7+x);
		
		die = new Animation(20+x,false );
		die.frames(film,8+x,9+x,10+x,11+x,12+x,11+x);
		
		attack = new Animation(15+x,false );
		attack.frames(film,13+x,14+x,15+x,0+x);
		
		zap = attack.clone();

		play( idle );
	}
	
}
