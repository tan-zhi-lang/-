

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.火毒粒子;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.魔法冰霜房间;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;

public class 火毒元素 extends Fire {

	@Override
	protected void evolve() {

		boolean[] flamable = Dungeon.level.flamable;
		int cell;
		int fire;

		boolean observe = false;

		for (int i = area.left-1; i <= area.right; i++) {
			for (int j = area.top-1; j <= area.bottom; j++) {
				cell = i + j*Dungeon.level.width();
				if (cur[cell] > 0) {


					burn( cell );

					fire = cur[cell] - 1;
					if (fire <= 0 && flamable[cell]) {

						Dungeon.level.destroy( cell );

						observe = true;
						GameScene.updateMap( cell );

					}

				} else {
					fire = 0;
				}

				volume += (off[cell] = fire);
			}
		}

		if (observe) {
			Dungeon.observe();
		}
	}
	
	public static void burn( int pos ) {
		Char ch = Actor.findChar( pos );
		if (ch != null) {
			if(Dungeon.符文("祖母辣椒油")){
				if(ch instanceof Hero hero)
					Dungeon.hero.回百分比血(0.075f);
				else
					ch.受伤时(Dungeon.hero.最大生命(0.075f),燃烧.class);
			}
		}
		if (ch != null && !ch.免疫(火毒元素.class)) {
			Buff.施加( ch, 火毒.class).reignite(ch);
		}

		魔法冰霜房间.魔法冰霜 魔法冰霜 = (魔法冰霜房间.魔法冰霜)Dungeon.level.blobs.get(魔法冰霜房间.魔法冰霜.class);
		if (魔法冰霜 != null && 魔法冰霜.volume > 0) {
			魔法冰霜.clear( pos );
		}
		Plant plant = Dungeon.level.plants.get( pos );
		if (plant != null){
			if(plant instanceof Firebloom)return;
			if(plant instanceof Icecap)return;
			plant.wither();
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.pour(火毒粒子.FACTORY,0.03f);
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
