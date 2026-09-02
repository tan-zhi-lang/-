package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

import java.io.IOException;

//每2400回合自动把当前存档快照到 回档/ 子目录，供玩家死亡后回到更早的存档
public class 每900回合回档 extends FlavourBuff{
    @Override
    public boolean act(){

        try {
            Dungeon.saveAll();
            Dungeon.备份回溯存档("回档");
        } catch (IOException e) {
            ShatteredPixelDungeon.reportException(e);
        }
        spend( 900 );
        return true;
    }
}
