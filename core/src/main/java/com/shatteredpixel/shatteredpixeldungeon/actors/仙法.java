package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Bundle;

public class 仙法 extends FlavourBuff{

    public float count = 0;
    public float set(float x,Hero hero){
        if(hero.天赋概率(Talent.更之以蝶,25))
            count+=x;
        count+=x;
        float 满层=3;

        if(hero.天赋概率(Talent.更之以蝶,25))满层--;
        if(target!=null&&count>=满层){
            float 倍=1+hero.天赋点数(Talent.灵爆之至,0.25f);
            if(hero.职业精通())倍*=3;

            target.受伤(target.最大生命(0.075f*倍));
            hero.回百分比血(0.05f*倍);
            算法.修复效果(()->{
                Buff.施加(target,Cripple.class,4);
            });
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