

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PointF;

public class Beam extends Image {
	
	private static final double A = 180 / Math.PI;
	
	private  float duration;
	
	private float timeLeft;

	private Beam(PointF s, PointF e, Effects.Type asset, float duration) {
		super( Effects.get( asset ) );
		
		origin.set( 0, height / 2 );
		
		x = s.x - origin.x;
		y = s.y - origin.y;
		
		float dx = e.x - s.x;
		float dy = e.y - s.y;
		angle = (float)(Math.atan2( dy, dx ) * A);
		scale.x = (float)Math.sqrt( dx * dx + dy * dy ) / width;
		
		Sample.INSTANCE.play( Assets.Sounds.RAY );
		
		timeLeft = this.duration = duration;
	}

	public static class DeathRay extends Beam{
		public DeathRay(PointF s, PointF e){
			super(s, e, Effects.Type.DEATH_RAY, 0.5f);
		}
		public DeathRay(Char s,Char e){
			super(s.sprite.center(), e.sprite.center(), Effects.Type.DEATH_RAY, 0.5f);
		}
		public DeathRay(CharSprite s,CharSprite e){
			super(s.center(), e.center(), Effects.Type.DEATH_RAY, 0.5f);
		}
	}

	public static class LightRay extends Beam{
		public LightRay(PointF s, PointF e){
			super(s, e, Effects.Type.LIGHT_RAY, 1f);
		}
		public LightRay(Char s,Char e){
			super(s.sprite.center(), e.sprite.center(), Effects.Type.HEALTH_RAY, 1);
		}
		public LightRay(CharSprite s,CharSprite e){
			super(s.center(), e.center(), Effects.Type.LIGHT_RAY, 1);
		}
	}

	public static class SunRay extends Beam{
		public SunRay(PointF s, PointF e){
			super(s, e, Effects.Type.LIGHT_RAY, 1f);
			tint(1, 1, 0.25f, 1);
		}
		public SunRay(Char s,Char e){
			super(s.sprite.center(), e.sprite.center(), Effects.Type.LIGHT_RAY, 1);
			tint(1, 1, 0.25f, 1);
		}
		public SunRay(CharSprite s,CharSprite e){
			super(s.center(), e.center(), Effects.Type.LIGHT_RAY, 1);
			tint(1, 1, 0.25f, 1);
		}
	}

	public static class HealthRay extends Beam{
		public HealthRay(PointF s, PointF e){
			super(s, e, Effects.Type.HEALTH_RAY, 0.75f);
		}
		public HealthRay(Char s,Char e){
			super(s.sprite.center(), e.sprite.center(), Effects.Type.HEALTH_RAY, 0.75f);
		}
		public HealthRay(CharSprite s,CharSprite e){
			super(s.center(), e.center(), Effects.Type.HEALTH_RAY, 0.75f);
		}
	}
	
	@Override
	public void update() {
		super.update();
		
		float p = timeLeft / duration;
		alpha( p );
		scale.set( scale.x, p );
		
		if ((timeLeft -= Game.elapsed) <= 0) {
			killAndErase();
		}
	}
	
	@Override
	public void draw() {
		Blending.setLightMode();
		super.draw();
		Blending.setNormalMode();
	}
}
