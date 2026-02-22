package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class 静止状态 extends CountBuff{
    {
        name="静止状态";
    }

    public int icon() { return BuffIndicator.SHADOWS; }
    public int pos = -1;

    @Override
    public boolean act() {
        if (pos != target.pos) {
            count=0;
            detach();
        } else {
            spend(TICK);
        }
        return true;
    }

    @Override
    public String desc(){
        return Messages.get(this,"desc",count);
    }

    private static final String POS = "pos";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(POS, pos);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        pos = bundle.getInt(POS);
    }
    }