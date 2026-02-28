package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.watabou.utils.Bundle;

public  class 吸收痛苦 extends Buff{
		private float preservedDamage;
		
		public void 吸收(float bonus){
			preservedDamage += bonus;
		}
		
		public float 痛苦(){
			float d=preservedDamage;
			preservedDamage*=0.85f;
			return d;
		}
		
		private static final String PRESERVED_DAMAGE = "preserve_damage";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(PRESERVED_DAMAGE, preservedDamage);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			preservedDamage = bundle.getFloat(PRESERVED_DAMAGE);
		}
	}