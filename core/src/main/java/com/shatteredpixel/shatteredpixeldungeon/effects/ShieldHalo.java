

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.glwrap.Blending;
import com.watabou.noosa.Game;
import com.watabou.noosa.Halo;
import com.watabou.utils.PointF;

public class ShieldHalo extends Halo {
	
	private CharSprite target;
	
	private float phase;
	boolean 护盾=true;
	public ShieldHalo( CharSprite sprite ) {
		
		//rectangular sprite to circular radius. Pythagorean theorem
		super( (float)Math.sqrt(Math.pow(sprite.width()/2f, 2) + Math.pow(sprite.height()/2f, 2)), 0xBBFFFF, 1f );
		
		am = -0.33f;
		aa = +0.33f;
		
		target = sprite;
		
		phase = 1;
	}

	public ShieldHalo( CharSprite sprite ,int color) {

		//rectangular sprite to circular radius. Pythagorean theorem
		super( (float)Math.sqrt(Math.pow(sprite.width()/2f, 2) + Math.pow(sprite.height()/2f, 2)), color, 0 );

//		am = -0.33f;
//		aa = +0.33f;
		scale=new PointF(16,16);
		护盾=false;
		target = sprite;

		phase = 1;
	}
	public ShieldHalo( CharSprite sprite ,int color,int sc) {

		//rectangular sprite to circular radius. Pythagorean theorem
		super( (float)Math.sqrt(Math.pow(sprite.width()/2f, 2) + Math.pow(sprite.height()/2f, 2)), color, 1f );

		am = -0.33f;
		aa = +0.33f;
		scale=new PointF(sc*0.16f,sc*0.16f);
		护盾=false;
		target = sprite;

		phase = 1;
	}

	@Override
	public void update() {
		super.update();
			if(phase<1){
				if((phase-=Game.elapsed)<=0){
					killAndErase();
				}else{
					scale.set((2-phase)*radius/RADIUS);
					am=phase*(-1);
					aa=phase*(+1);
				}
			}

			if(visible=target.visible){
				PointF p=target.center();
				if(护盾)
				point(p.x,p.y);
				else
					point(p.x,p.y+4.5f);
			}
	}
	
	@Override
	public void draw() {
		Blending.setLightMode();
		super.draw();
		Blending.setNormalMode();
	}
	
	public void putOut() {
		phase = 0.999f;
	}
	
}
