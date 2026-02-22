package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class 战斗状态 extends FlavourBuff{
        {name="战斗状态";}
        public int icon() { return BuffIndicator.WEAPON; }

    @Override
    public void detach(){
        if(target instanceof Hero hero&&hero.符文 ("踢踏舞")){
            Buff.施加(target,踢踏舞.class).clearc();
        }
        if(target instanceof Hero hero&&hero.符文 ("物法皆修")){
            Buff.施加(target,物法皆修.class).clearc();
        }
        super.detach();
    }
}