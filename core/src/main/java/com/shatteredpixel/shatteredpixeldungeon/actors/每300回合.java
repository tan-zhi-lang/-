package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.watabou.utils.Random;

public class 每300回合 extends FlavourBuff{
    @Override
    public boolean act(){
        spend( 300 );
        if(Dungeon.符文("股市")){
            float x=Random.Float(0.01f,200f);
            if(x>=1){
                Dungeon.gold(Math.round(Dungeon.gold*(x-1f)));
            }else
                Dungeon.gold(Math.round(Dungeon.gold*(1f-x)));
        }
        if(Dungeon.符文("期货季节")){
            Dungeon.打折=Random.Float(0.1f,10f);
        }
        return true;
    }
}