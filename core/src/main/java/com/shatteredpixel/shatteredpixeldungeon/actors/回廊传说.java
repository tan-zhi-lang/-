package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Bundle;

public class 回廊传说 extends FlavourBuff{

    public int pos = 0;

    @Override
    public boolean act(){
        spend( TICK );
        if(target.pos!=pos)
        if(Dungeon.level.passable[pos]){
            Level.set(pos,Terrain.WALL);
            GameScene.updateMap(pos);
            CellEmitter.get(pos).burst(Speck.factory(Speck.STEAM),10);
        }
        return true;
    }

    private static final String POS = "pos";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(POS, pos);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        pos = bundle.getInt(POS);
    }
    }