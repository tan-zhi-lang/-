

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Regrowth;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FungalSpinnerSprite;
import com.watabou.utils.PathFinder;

public class FungalSpinner extends Spinner {

	{
		spriteClass = FungalSpinnerSprite.class;

		生命 = 最大生命 = 40;
		defenseSkill = 16;

		经验 = 7;
		最大等级 = -2;
	}

	@Override
	protected void applyWebToCell(int cell) {
		GameScene.add(Blob.seed(cell, 40, Regrowth.class));
	}

	@Override
	public void damage(int dmg, Object src) {
		int grassCells = 0;
		for (int i : PathFinder.NEIGHBOURS9) {
			if (Dungeon.level.map[pos+i] == Terrain.FURROWED_GRASS
					|| Dungeon.level.map[pos+i] == Terrain.HIGH_GRASS){
				grassCells++;
			}
		}
		//first adjacent grass cell reduces damage taken by 30%, each one after reduces by another 10%
		if (grassCells > 0) dmg = Math.round(dmg * (8-grassCells)/10f);

		super.damage(dmg, src);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		return damage; //does not apply poison
	}

	{
		immunities.add(Regrowth.class);
	}

}
