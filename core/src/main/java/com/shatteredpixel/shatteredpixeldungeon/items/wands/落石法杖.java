

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DelayedRockFall;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGeomancer;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.TargetedCell;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.石头;
import com.shatteredpixel.shatteredpixeldungeon.levels.CavesBossLevel;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.ColorMath;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 落石法杖 extends DamageWand {

	{
		image = 物品表.落石法杖;
	}


	public float min(int lvl){
		return 魔力(0.2f,0.5f);
	}

	public float max(int lvl){
		return 魔力(0.8f,0.25f);
	}

	@Override
	public void onZap(Ballistica bolt) {

		Char ch = Actor.findChar(bolt.collisionPos);
		wandProc(ch, chargesPerCast());

		for (int j : PathFinder.自相邻8){
			if(算法.概率学(5)){
				Dungeon.level.drop(new 石头(),bolt.collisionPos+j).sprite().drop();
			}
			ch=Actor.findChar(bolt.collisionPos+j);
			if(ch!=null){
				dropRocks(ch);
				ch.受伤时(damageRoll(),this);
			}else
				Dungeon.level.pressCell(bolt.collisionPos);
		}

		PixelScene.shake(3,1f);
		Sample.INSTANCE.play(Assets.Sounds.ROCKS);
	}

	public void dropRocks( Char target ) {

		Dungeon.hero.interrupt();
		final int rockCenter;

		//knock back 2 tiles if adjacent
		if (Dungeon.level.adjacent(Dungeon.hero.pos, target.pos)){
			int oppositeAdjacent = target.pos + (target.pos - Dungeon.hero.pos);
			Ballistica trajectory = new Ballistica(target.pos, oppositeAdjacent, Ballistica.MAGIC_BOLT);
			WandOfBlastWave.throwChar(target, trajectory, 2, false, false, this);
			if (target == Dungeon.hero){
				Dungeon.hero.interrupt();
			}
			rockCenter = trajectory.path.get(Math.min(trajectory.dist, 2));

			//knock back 1 tile if there's 1 tile of space
		} else if (Dungeon.hero.fieldOfView[target.pos] && Dungeon.level.distance(Dungeon.hero.pos, target.pos) == 2) {
			int oppositeAdjacent = target.pos + (target.pos - Dungeon.hero.pos);
			Ballistica trajectory = new Ballistica(target.pos, oppositeAdjacent, Ballistica.MAGIC_BOLT);
			WandOfBlastWave.throwChar(target, trajectory, 1, false, false, this);
			if (target == Dungeon.hero){
				Dungeon.hero.interrupt();
			}
			rockCenter = trajectory.path.get(Math.min(trajectory.dist, 1));

			//otherwise no knockback
		} else {
			rockCenter = target.pos;
		}

		int safeCell;
		do {
			safeCell = rockCenter + PathFinder.相邻8[Random.Int(8)];
		} while (safeCell == Dungeon.hero.pos
				 || (Dungeon.level.solid[safeCell] && Random.Int(2) == 0)
				 || (Blob.volumeAt(safeCell, CavesBossLevel.PylonEnergy.class) > 0 && Random.Int(2) == 0));

		ArrayList<Integer> rockCells = new ArrayList<>();

		int start = rockCenter - Dungeon.level.width() * 3 - 3;
		int pos;
		for (int y = 0; y < 7; y++) {
			pos = start + Dungeon.level.width() * y;
			for (int x = 0; x < 7; x++) {
				if (!Dungeon.level.insideMap(pos)) {
					pos++;
					continue;
				}
				//add rock cell to pos, if it is not solid, and isn't the safecell
				if (!Dungeon.level.solid[pos] && pos != safeCell && Random.Int(Dungeon.level.distance(rockCenter, pos)) == 0) {
					rockCells.add(pos);
				}
				pos++;
			}
		}
		for (int i : rockCells){
			Dungeon.hero.sprite.parent.add(new TargetedCell(i,0xFF0000));
		}
		//don't want to overly punish players with slow move or attack speed
		Buff.新增(target,落石.class,GameMath.之内(1,(int)Math.ceil(target.cooldown()),3)).setRockPositions(rockCells);
		target.buff(落石.class).set(damageRoll(),this);
	}

	public static class 落石 extends DelayedRockFall{
		float dmg;
		Wand w;
		public void set(float dmg,Wand w){
			this.w=w;
			this.dmg=dmg;
		}
		@Override
		public void affectChar(Char ch){
			ch.受伤时(dmg,w);

			if(ch.isAlive()){
				Buff.延长(ch,Paralysis.class,4);
			}else if(ch==Dungeon.hero){
				Dungeon.fail(target);
				GLog.n(Messages.get(GnollGeomancer.class,"rockfall_kill"));
			}
		}
	}
	@Override
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
								  MagicMissile.EARTH,
								  curUser.sprite,
								  bolt.collisionPos,
								  callback);
		Sample.INSTANCE.play(Assets.Sounds.ZAP);
	}

	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		if (Random.Int(10) == 0){
			particle.color(ColorMath.random(0xFFF568, 0x80791A));
		} else {
			particle.color(ColorMath.random(0x805500, 0x332500));
		}
		particle.am = 1f;
		particle.setLifespan(2f);
		particle.setSize( 1f, 2f);
		particle.shuffleXY(0.5f);
		float dst = Random.Float(11f);
		particle.x -= dst;
		particle.y += dst;
	}
}
