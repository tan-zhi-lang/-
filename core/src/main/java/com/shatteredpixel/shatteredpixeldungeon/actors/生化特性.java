package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;

public class 生化特性 extends CountBuff{
    {
        maxcount=4.5f;
    }
    @Override
    public boolean act() {
        if (count>0) {
            set(-0.2f);
        }
        spend(TICK);
        return true;
    }

    }