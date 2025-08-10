

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Spinner;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

//TODO improvements here
public class SpinnerSprite extends MobSprite {
	
	public SpinnerSprite() {
		super();

		perspectiveRaise = 0f;

		texture( Assets.Sprites.修复);
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 10, true );
		idle.frames( frames, 0, 0, 0, 0, 0, 1, 0, 1 );
		
		run = new Animation( 15, true );
		run.frames( frames, 0, 2, 0, 3 );
		
		attack = new Animation( 12, false );
		attack.frames( frames, 0, 4, 5, 0 );
		
		zap = attack.clone();
		
		die = new Animation( 12, false );
		die.frames( frames, 6, 7, 8, 9 );
		
		play( idle );
	}

	@Override
	public void link(Char ch) {
		super.link(ch);
		if (parent != null) {
			parent.sendToBack(this);
			if (aura != null){
				parent.sendToBack(aura);
			}
		}
		renderShadow = false;
	}
	
	public void zap( int cell ) {

		super.zap( cell );
		
		MagicMissile.boltFromChar( parent,
				MagicMissile.MAGIC_MISSILE,
				this,
				cell,
				new Callback() {
					@Override
					public void call() {
						((Spinner)ch).shootWeb();
					}
				} );
		Sample.INSTANCE.play( Assets.Sounds.MISS );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == zap) {
			play( run );
		}
		super.onComplete( anim );
	}

	@Override
	public int blood() {
		return 0xFFBFE5B8;
	}
}
