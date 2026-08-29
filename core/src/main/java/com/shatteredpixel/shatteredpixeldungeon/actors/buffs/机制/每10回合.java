package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

public class 每10回合 extends FlavourBuff{
    @Override
    public boolean act(){
        spend( 10 );
        return true;
    }
}