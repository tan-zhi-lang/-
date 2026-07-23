package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CountBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;

public class 广告5秒 extends CountBuff{
    {
        maxcount=15;
    }

    @Override
    public boolean act(){
        if(max()){
            clearCount();
            Buff.detach(target,Paralysis.class);
            detach();
        }else{
            if(Dungeon.hero.nobuff(Paralysis.class))
            Buff.施加(target,Paralysis.class,maxcount-count);
        }
        return true;
    }
}