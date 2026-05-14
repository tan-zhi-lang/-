package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.watabou.utils.Bundle;

public class 丛刃 extends FlavourBuff{

	public float count = 3;
	public float set(float x){
		count+=x;
		return count;
	}

	public void clearc(){
		count=0;
	}

	@Override
	public boolean act(){
		if(count<=0)detach();
		return super.act();
	}

	@Override
	public void detach(){
		clearc();
		super.detach();
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