

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.魔法冰霜房间;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class 烈焰法杖 extends DamageWand {

	{
		image = 物品表.烈焰法杖;
	}

	public float min(int lvl){
		return (2+lvl)/2;
	}

	public float max(int lvl){
		return (8+5*lvl)/2;
	}

	@Override
	public void onZap(Ballistica bolt) {

		Heap heap = Dungeon.level.heaps.get(bolt.collisionPos);
		if (heap != null) {
			heap.freeze();
		}

		Freezing free = (Freezing) Dungeon.level.blobs.get(Freezing.class);
		if (free != null && free.volume > 0) {
			free.clear( bolt.collisionPos );
		}

		魔法冰霜房间.魔法冰霜  魔法冰霜= (魔法冰霜房间.魔法冰霜)Dungeon.level.blobs.get(魔法冰霜房间.魔法冰霜.class);
		if (魔法冰霜!=null&&魔法冰霜.volume>0) {
			魔法冰霜.clear(bolt.collisionPos);
			//bolt ends 1 tile short of fire, so check next tile too
			if (bolt.path.size() > bolt.dist+1){
				魔法冰霜.clear(bolt.path.get(bolt.dist+1));
			}

		}

		Char ch = Actor.findChar(bolt.collisionPos);
		if (ch != null){

			float damage = damageRoll();

			if (ch.buff(燃烧.class) != null){
				//6.67% less damage per turn of chill remaining, to a max of 10 turns (50% dmg)
				float chillturns = Math.min(10, ch.buff(燃烧.class).cooldown());
				damage = (int)Math.round(damage * Math.pow(0.9333f, chillturns));
			}

			wandProc(ch, chargesPerCast());
			ch.受伤时(damage, this);
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, 1.1f * Random.Float(0.87f, 1.15f) );

			if (ch.isAlive()){
				if (ch.buff(Chill.class) != null){
					Buff.施加(ch,Paralysis.class,4f);
				}
				if (ch.在草丛()||ch.在门上()) {
					Buff.施加(ch,Paralysis.class,4f);
				} else {
					ch.sprite.burst( 0xFF99CCFF, 强化等级() / 2 + 2 );
					Buff.施加(ch, 燃烧.class).reignite(ch,2 + 强化等级());
				}
			}
		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}

	@Override
	public String upgradeStat2(int level) {
		return Integer.toString(2 + level);
	}

	@Override
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
				MagicMissile.FIRE,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play(Assets.Sounds.ZAP);
	}


	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		particle.color( 0xEE7722 );
		particle.am = 0.5f;
		particle.setLifespan(2f);
		float angle = Random.Float(PointF.PI2);
		particle.speed.polar( angle, 2f);
		particle.acc.set( 0f, 1f);
		particle.setSize( 0f, 1.5f);
		particle.radiateXY(Random.Float(1f));
	}

}
