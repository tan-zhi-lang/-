

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.watabou.utils.Bundle;

//A buff whose only purposes is to keep track of a count of some form
public class CountBuff extends Buff {

	public float count = 0;
	public float set(float x){
		count+=x;
		return count;
	}

	public void clearc(){
		count=0;
	}

	private static final String COUNT = "count";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COUNT, count);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		count = bundle.getFloat(COUNT);
	}
}
