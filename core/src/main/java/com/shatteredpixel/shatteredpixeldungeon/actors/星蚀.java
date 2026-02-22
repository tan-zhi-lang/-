package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.watabou.utils.Bundle;

public class 星蚀 extends FlavourBuff{

    public float count = 0;
    public float set(float x,Char c){
        count+=x;
        if(c!=null&&count>=2&&target instanceof Hero hero){
            c.受伤时(c.最大生命(1),hero);
            hero.恢复百分比护甲(1);
            Buff.施加(hero,星蚀冷却.class,5);
            detach();
        }
        return count;
    }

    public void clearc(){
        count=0;
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