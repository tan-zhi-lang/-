

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HeroDisguise;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;
import com.watabou.utils.RectF;

public class HeroSprite extends CharSprite {
	
	private static final int FRAME_WIDTH	= 12;
	private static final int FRAME_HEIGHT	= 15;
	
	private static final int RUN_FRAMERATE	= 20;
	
	private static TextureFilm tiers;
	
	private Animation fly;
	private Animation read;

	public HeroSprite() {
		super();
		
		texture( Dungeon.hero.heroClass.spritesheet() );
		updateArmor();
		
		link( Dungeon.hero );

		if (ch.isAlive())
			idle();
		else
			die();
	}

	public void disguise(HeroClass cls){
		texture( cls.spritesheet() );
		updateArmor();
	}
//	public HeroSprite set(){
//
//	}
	public void updateArmor() {
		
		if(Dungeon.玩法(玩法设置.重生怪物)){
			if(Dungeon.hero.重生怪物.equals("")){
				
				Dungeon.hero.重生怪物=Random.oneOf("下水道巨蛇","下水道螃蟹","史莱姆","吸血蝙蝠","矮人武僧");
			}
			TextureFilm frames;
			switch(Dungeon.hero.重生怪物){
				case "下水道巨蛇":
					texture( Assets.Sprites.SNAKE );
					
					frames = new TextureFilm( texture, 12, 11 );
					
					//many frames here as we want the rising/falling to be slow but the tongue to be fast
					idle = new Animation( 10, true );
					idle.frames( frames, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
								 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 1, 1);
					
					run = new Animation( 8, true );
					run.frames( frames, 4, 5, 6, 7 );
					
					attack = new Animation( 15, false );
					attack.frames( frames, 8, 9, 10, 9, 0);
					
					die = new Animation( 10, false );
					die.frames( frames, 11, 12, 13 );
					
					
					zap = attack.clone();
					operate = attack.clone();
					fly = run.clone();
					read = attack.clone();
					
					if (Dungeon.hero.isAlive())
						idle();
					else
						die();
					return;
				case "下水道螃蟹":
					texture( Assets.Sprites.CRAB );
					
					frames = new TextureFilm( texture );
					
					idle = new Animation( 5, true );
					idle.frames( frames, 0, 1, 0, 2 );
					
					run = new Animation( 15, true );
					run.frames( frames, 3, 4, 5, 6 );
					
					attack = new Animation( 12, false );
					attack.frames( frames, 7, 8, 9 );
					
					die = new Animation( 12, false );
					die.frames( frames, 10, 11, 12, 13 );
					
					zap = attack.clone();
					operate = attack.clone();
					fly = run.clone();
					read = attack.clone();
					
					if (Dungeon.hero.isAlive())
						idle();
					else
						die();
					return;
				case "史莱姆":
					
					texture( Assets.Sprites.SLIME );
					
					frames = new TextureFilm( texture, 14, 12 );
					
					idle = new Animation( 3, true );
					idle.frames( frames, 0, 1, 1, 0 );
					
					run = new Animation( 10, true );
					run.frames( frames, 0, 2, 3, 3, 2, 0 );
					
					attack = new Animation( 15, false );
					attack.frames( frames, 2, 3, 4, 6, 5 );
					
					die = new Animation( 10, false );
					die.frames( frames, 0, 5, 6, 7 );
					
					zap = attack.clone();
					operate = attack.clone();
					fly = run.clone();
					read = attack.clone();
					
					if (Dungeon.hero.isAlive())
						idle();
					else
						die();
					return;
				case "吸血蝙蝠":
					
					texture( Assets.Sprites.BAT );
					
					frames = new TextureFilm( texture, 15, 15 );
					
					idle = new Animation( 8, true );
					idle.frames( frames, 0, 1 );
					
					run = new Animation( 12, true );
					run.frames( frames, 0, 1 );
					
					attack = new Animation( 12, false );
					attack.frames( frames, 2, 3, 0, 1 );
					
					die = new Animation( 12, false );
					die.frames( frames, 4, 5, 6 );
					
					zap = attack.clone();
					operate = attack.clone();
					fly = run.clone();
					read = attack.clone();
					
					if (Dungeon.hero.isAlive())
						idle();
					else
						die();
					return;
				case "矮人武僧":
					
					texture( Assets.Sprites.MONK );
					
					frames = new TextureFilm( texture, 15, 14 );
					
					idle = new Animation( 6, true );
					idle.frames( frames, 1, 0, 1, 2 );
					
					run = new Animation( 15, true );
					run.frames( frames, 11, 12, 13, 14, 15, 16 );
					
					attack = new Animation( 12, false );
					attack.frames( frames, 3, 4, 3, 4 );
					
					zap = new Animation( 10, false );
					zap.frames( frames, 5, 6, 5 );
					
					die = new Animation( 15, false );
					die.frames( frames, 1, 7, 8, 8, 9, 10 );
					
					operate = zap.clone();
					fly = run.clone();
					read = zap.clone();
					
					if (Dungeon.hero.isAlive())
						idle();
					else
						die();
					return;
			}
			return;
		}
		TextureFilm film = new TextureFilm( tiers(), Dungeon.hero.tier(), FRAME_WIDTH, FRAME_HEIGHT );
		
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
		
		if (Dungeon.hero.isAlive())
			idle();
		else
			die();
	}
	
	@Override
	public void place( int p ) {
		super.place( p );
		if (Game.scene() instanceof GameScene) Camera.main.panFollow(this, 5f);
	}

	@Override
	public void move( int from, int to ) {
		super.move( from, to );
		if (ch != null && ch.flying) {
			play( fly );
		}
		Camera.main.panFollow(this, 20f);
	}

	@Override
	public void idle() {
		super.idle();
		if (ch != null && ch.flying) {
			play( fly );
		}
	}

	@Override
	public void jump( int from, int to, float height, float duration,  Callback callback ) {
		super.jump( from, to, height, duration, callback );
		play( fly );
		Camera.main.panFollow(this, 20f);
	}

	public synchronized void read() {
		animCallback = new Callback() {
			@Override
			public void call() {
				idle();
				ch.onOperateComplete();
			}
		};
		play( read );
	}

	@Override
	public void bloodBurstA(PointF from, int damage) {
		//Does nothing.

		/*
		 * This is both for visual clarity, and also for content ratings regarding violence
		 * towards human characters. The heroes are the only human or human-like characters which
		 * participate in combat, so removing all blood associated with them is a simple way to
		 * reduce the violence rating of the game.
		 */
	}

	@Override
	public void update() {
		sleeping = ch.isAlive() && ((Hero)ch).resting;
		
		super.update();
	}
	
	public void sprint( float speed ) {
		run.delay = 1f / speed / RUN_FRAMERATE;
	}
	
	public static TextureFilm tiers() {
		if (tiers == null) {
			SmartTexture texture = TextureCache.get( Assets.Sprites.ROGUE );
			tiers = new TextureFilm( texture, texture.width, FRAME_HEIGHT );
		}
		
		return tiers;
	}

	public static Image avatar( Hero hero ){
		if (hero.buff(HeroDisguise.class) != null){
			return avatar(hero.buff(HeroDisguise.class).getDisguise(), hero.tier());
		} else {
			return avatar(hero.heroClass, hero.tier());
		}
	}
	
	public static Image avatar( HeroClass cl, int armorTier ) {
		RectF patch = tiers().get( armorTier );
		Image avatar = new Image( cl.spritesheet() );
		RectF frame = avatar.texture.uvRect( 0, 0, FRAME_WIDTH, FRAME_HEIGHT );
		frame.shift( patch.left, patch.top );
		avatar.frame( frame );
		
		return avatar;
	}
}
