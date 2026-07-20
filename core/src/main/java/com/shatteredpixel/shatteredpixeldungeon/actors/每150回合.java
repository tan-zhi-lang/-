package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

public class 每150回合 extends FlavourBuff{
    @Override
    public boolean act(){
        spend( 150 );
        return true;
    }
}