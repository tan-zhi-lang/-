

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.交易NPC;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.TextureFilm;

public class 交易NPC动画 extends MobSprite{

	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 15;

	private static final int RUN_FRAMERATE	= 20;

	private static TextureFilm tiers;
	private Animation fly;
	private Animation read;
	public 交易NPC动画() {
		super();

		updateArmor();
		play(idle);
	}

	@Override
	public void link( Char ch) {
		super.link( ch );
		updateArmor(((交易NPC)ch).heroClass);
	}
	public void updateArmor() {
		updateArmor(HeroClass.盗贼);
	}
	public void updateArmor(HeroClass heroClass) {
		texture( HeroClass.spritesheet(heroClass) );

		SmartTexture texture = TextureCache.get(HeroClass.spritesheet(heroClass));
		tiers = new TextureFilm( texture, texture.width, FRAME_HEIGHT );

		TextureFilm film = new TextureFilm( tiers, Dungeon.区域(), FRAME_WIDTH, FRAME_HEIGHT );

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

		fly = new Animation( 1, true );
		fly.frames( film, 18 );

		read = new Animation( 20, false );
		read.frames( film, 19, 20, 20, 20, 20, 20, 20, 20, 20, 19 );

	}
	public static TextureFilm tiers() {
		if (tiers == null) {
			SmartTexture texture = TextureCache.get(Assets.Sprites.ROGUE);
			tiers = new TextureFilm( texture, texture.width, FRAME_HEIGHT );
		}

		return tiers;
	}
	public void setArmor(HeroClass heroClass) {
		SmartTexture texture = TextureCache.get( HeroClass.spritesheet(heroClass));
		tiers = new TextureFilm( texture, texture.width, FRAME_HEIGHT );
	}
}
