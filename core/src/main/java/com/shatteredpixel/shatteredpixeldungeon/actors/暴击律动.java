package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;

public class 暴击律动 extends CountBuff{
    {
        maxcount=10;
    }
    @Override
    public boolean act() {
        if (count>0) {
            set(-1);
        }
        spend(TICK*5);
        return true;
    }
    }