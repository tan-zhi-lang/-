package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;

import java.io.IOException;

public class 每45回合备份 extends FlavourBuff{
    @Override
    public boolean act(){

        if(Dungeon.符文("Boss阴到没边但是我能回档"))
            try {
                Dungeon.saveAll();
                Dungeon.备份回溯存档();
            } catch (
                    IOException e) {
                ShatteredPixelDungeon.reportException(e);
            }
        spend( 45 );
        return true;
    }
}