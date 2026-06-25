package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Bundle;

public class 无法回头 extends FlavourBuff{

	public int pos = -1;

	@Override
	public boolean act(){
		spend( TICK );
		if(pos==-1)
			pos=target.pos;

		if(target.pos!=pos)
			if((Dungeon.level.map[pos]==Terrain.EMPTY||
				Dungeon.level.map[pos]==Terrain.EMPTY_DECO||
				Dungeon.level.map[pos]==Terrain.EMPTY_SP
			   )){
				Level.set(pos,Terrain.CHASM);
				GameScene.updateMap(pos);
				CellEmitter.get(pos).burst(Speck.factory(Speck.STEAM),10);
				pos=target.pos;
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