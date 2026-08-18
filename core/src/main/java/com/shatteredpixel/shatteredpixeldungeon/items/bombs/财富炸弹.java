

package com.shatteredpixel.shatteredpixeldungeon.items.bombs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class 财富炸弹 extends Bomb {
	
	{
		image = 物品表.财富炸弹;
	}

	@Override
	protected int explosionRange() {
		return 2;
	}

	@Override
	public void explode(int cell) {
		super.explode(cell);

		if (Dungeon.level.heroFOV[cell]) {
			new Flare(10,64).show(Dungeon.hero.sprite.parent,DungeonTilemap.tileCenterToWorld(cell),2f);
		}

		ArrayList<Char> affected = new ArrayList<>();

		PathFinder.buildDistanceMap(cell,BArray.not(Dungeon.level.solid,null),explosionRange());
		for (int i = 0; i < PathFinder.distance.length; i++) {
			if (PathFinder.distance[i] < Integer.MAX_VALUE) {
				Char ch = Actor.findChar(i);
				if (ch != null) {
					affected.add(ch);

				}
			}
		}

		for (Char ch : affected){
			if(ch instanceof Mob m){
				if(m.loot!=null){
					Dungeon.level.drop(m.createLoot(),m.pos);
					Dungeon.level.drop(m.createLoot(),m.pos);
				}
			}
		}

		Sample.INSTANCE.play(Assets.Sounds.GOLD);
	}

	
	@Override
	public int 金币() {
		//prices of ingredients
		return quantity * (20 + 30);
	}
}
