package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.护盾;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.watabou.utils.Bundle;

public class 星蚀 extends FlavourBuff{

    public float count = 0;
    public float set(float x,Char c){
        count+=x;
        if(c!=null&&count>=2&&target instanceof Hero hero){
            c.受伤时(c.最大生命(0.1f),hero);
            Buff.施加(hero, 护盾.class).增加(0.2f*hero.最大攻击());
            Buff.施加(hero,星蚀冷却.class,5/能量之戒.weaponChargeMultiplier(hero));
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