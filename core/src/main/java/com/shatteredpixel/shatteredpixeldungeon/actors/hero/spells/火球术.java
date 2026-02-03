

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.魔法冰霜房间;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 火球术 extends 目标法术 {

	public static final 火球术
			INSTANCE = new 火球术();

	@Override
	public int icon() {
		return HeroIcon.火球术;
	}

	@Override
	protected void onTargetSelected(四叶草法典 tome,Hero hero,Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target, targetingFlags());
		
		if (Actor.findChar( aim.collisionPos ) == hero){
			GLog.i(Messages.get(Wand.class,"self_target"));
			return;
		}
		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}
		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f));
		
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.FIRE, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Heap
						heap = Dungeon.level.heaps.get(aim.collisionPos);
				if (heap != null) {
					heap.freeze();
				}

				Freezing
						free = (Freezing) Dungeon.level.blobs.get(Freezing.class);
				if (free != null && free.volume > 0) {
					free.clear( aim.collisionPos );
				}

				魔法冰霜房间.魔法冰霜  魔法冰霜= (魔法冰霜房间.魔法冰霜)Dungeon.level.blobs.get(魔法冰霜房间.魔法冰霜.class);
				if (魔法冰霜!=null&&魔法冰霜.volume>0) {
					魔法冰霜.clear(aim.collisionPos);
					//bolt ends 1 tile short of fire, so check next tile too
					if (aim.path.size() > aim.dist+1){
						魔法冰霜.clear(aim.path.get(aim.dist+1));
					}

				}

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null) {
					ch.受伤时(
							Random.NormalFloat(
									1+
									hero.术提升(0.5f)
									,
									4+
									hero.术提升(2.5f)
											  ), 火球术.this);

					ch.sprite.burst( 0xFF99CCFF, hero.术提升() / 2 + 2 );
					Buff.施加(ch,燃烧.class).reignite(ch,2+hero.术提升());
				} else {
					Dungeon.level.pressCell(aim.collisionPos);
				}

				hero.spend( 1f );
				hero.next();
				
				onSpellCast(tome, hero);
			}
		});
	}

	

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",1+Dungeon.hero.术提升(0.5f),4+Dungeon.hero.术提升(2.5f));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}
