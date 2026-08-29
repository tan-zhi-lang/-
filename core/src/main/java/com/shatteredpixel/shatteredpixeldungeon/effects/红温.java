

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.Game;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.audio.Sample;

public class 红温 extends Gizmo {

	private float phase;

	private CharSprite target;

	public 红温(CharSprite target) {
		super();

		this.target = target;
		phase = 0;
	}
	
	@Override
	public void update() {
		super.update();

		if ((phase += Game.elapsed * 2) < 1) {
			target.tint( 1.0f,0.2706f, 0, phase * 0.6f );
		} else {
			target.tint( 1.0f, 0.2706f,0, 0.6f );
		}
	}
	
	public void melt() {

		target.resetColor();
		killAndErase();

		if (visible) {
			Splash.at( target.center(), 0xFFFF4500, 5 );
			Sample.INSTANCE.play( Assets.Sounds.GAS );
		}
	}
	
	public static 红温 freeze(CharSprite sprite) {
		
		红温 iceBlock = new 红温(sprite );
		if (sprite.parent != null)
			sprite.parent.add( iceBlock );
		
		return iceBlock;
	}
}
