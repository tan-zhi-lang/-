

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.PointF;

public class 替身动画 extends MobSprite {
	
	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 15;
	
	public 替身动画() {
		super();
		
		texture( Dungeon.hero() ? Dungeon.hero.heroClass.spritesheet() : HeroClass.WARRIOR.spritesheet() );
		updateArmor();
		idle();
	}
	
	@Override
	public void update(){
		color(0);
		super.update();
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		updateArmor();
	}

	@Override
	public void bloodBurstA(PointF from, float damage) {
		//do nothing
	}

	public void updateArmor() {
		TextureFilm film = new TextureFilm(HeroSprite.tiers(),Dungeon.hero.tier(),FRAME_WIDTH,FRAME_HEIGHT );
		
		idle = new Animation( 1, true );
		idle.frames( film, 0, 0, 0, 1, 0, 0, 1, 1 );
		
		run = new Animation( 20, true );
		run.frames( film, 2, 3, 4, 5, 6, 7 );
		
		die = new Animation( 20, false );
		die.frames( film, 0 );
		
		attack = new Animation( 15, false );
		attack.frames( film, 13, 14, 15, 0 );
		
		idle();
	}
}
