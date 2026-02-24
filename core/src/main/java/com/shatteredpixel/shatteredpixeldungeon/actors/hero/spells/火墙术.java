

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class 火墙术 extends 目标法术 {

	public static final 火墙术
			INSTANCE = new 火墙术();

	@Override
	public int icon() {
		return HeroIcon.火墙术;
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

		hero.spend( 1f );
		hero.next();
		for (int offset : PathFinder.自相邻8){
			if (!Dungeon.level.solid[target+offset]) {
				GameScene.add(Blob.seed(target+offset,Math.round(hero.魔力(0.15f)),Fire.class));
			}
		}
	}

	

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Math.round(Dungeon.hero.魔力(0.15f)));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}
