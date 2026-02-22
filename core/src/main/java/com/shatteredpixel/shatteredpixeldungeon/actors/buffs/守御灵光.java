package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.particles.Emitter;

public class 守御灵光 extends FlavourBuff {

		public static float DURATION = 5;

		private Emitter particles;

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.PROT_AURA;
		}

		@Override
		public void fx(boolean on) {
			if(on&&(particles==null||particles.parent==null)){
				particles=target.sprite.emitter(); //emitter is much bigger than char so it needs to manage itself
				particles.pos(target.sprite,-32,-32,80,80);
				particles.fillTarget=false;
				particles.pour(Speck.factory(Speck.LIGHT),0.02f);
			}else{
				if(!on&&particles!=null){
					particles.on=false;
				}
			}
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}