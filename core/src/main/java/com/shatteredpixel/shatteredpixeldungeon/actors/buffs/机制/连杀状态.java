package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class 连杀状态 extends FlavourBuff{
        {
            name="连杀状态";
        }
        public int icon() { return BuffIndicator.TERROR; }
    @Override
    public void tintIcon(Image icon) {
        icon.hardlight(1f, 0, 0);
    }
    @Override
    public float iconFadePercent() {
        return Math.max(0, (10f - visualcooldown()) / 10f);
    }
    @Override
    public String name(){
        return count+super.name();
    }@Override
    	public String iconTextDisplay() {
    		return (int)(count)+"";
    	}

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