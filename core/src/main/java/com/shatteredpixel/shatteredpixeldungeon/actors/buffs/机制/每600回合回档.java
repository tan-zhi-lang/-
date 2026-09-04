package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

import java.io.IOException;

public class 每600回合回档 extends FlavourBuff{
    @Override
    public boolean act(){

        try {
            Dungeon.saveAll();
            Dungeon.备份回溯存档("回档");
        } catch (IOException e) {
            ShatteredPixelDungeon.reportException(e);
        }
        spend( 600 );
        return true;
    }
}
