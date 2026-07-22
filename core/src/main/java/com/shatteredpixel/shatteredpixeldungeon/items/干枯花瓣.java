package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 干枯花瓣 extends 用品 {

	{
		image = 物品表.PETAL;
	}

	@Override
	public void 使用(Hero hero){
		spawnImages(hero, 2);
		super.使用(hero);
	}

	public static int spawnImages( Hero hero, int nImages ){
		return spawnImages( hero, hero.pos, nImages);
	}

	//returns the number of images spawned
	public static int spawnImages( Hero hero, int pos, int nImages ){

		ArrayList<Integer> respawnPoints = new ArrayList<>();

		for (int i=0;i<PathFinder.自相邻.length;i++) {
			int p = pos + PathFinder.自相邻[i];
			if (Actor.findChar(p)==null&&Dungeon.level.passable[p]) {
				respawnPoints.add( p );
			}
		}

		int spawned = 0;
		while (nImages > 0 && respawnPoints.size() > 0) {
			int index = Random.index(respawnPoints);

			Wraith mob = new Wraith();
			mob.adjustStats( Dungeon.scalingDepth() );
			mob.alignment=Char.Alignment.ALLY;
			mob.state=mob.HUNTING;
			hero.sprite.emitter().burst(ShadowParticle.CURSE,5);
			GameScene.add(mob);
			传送卷轴.appear(mob,respawnPoints.get(index));

			respawnPoints.remove( index );
			nImages--;
			spawned++;
		}

		return spawned;
	}

	}