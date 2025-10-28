

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM201Sprite;
import com.watabou.utils.PathFinder;

public class DM201 extends DM200 {

	{
		spriteClass = DM201Sprite.class;

		生命 = 最大生命 = 120;

		properties.add(Property.IMMOVABLE);

		HUNTING = new Hunting();
	}

	@Override
	public int 最小攻击() {
		return 15;
	}

	private boolean threatened = false;

	@Override
	public void 受伤时(int dmg, Object src) {
		if (!(src instanceof Corruption)) {
			if ((src instanceof Char && !Dungeon.level.adjacent(pos, ((Char) src).pos))
				|| enemy == null || !Dungeon.level.adjacent(pos, enemy.pos)) {
				threatened = true;
			}
		}
		super.受伤时(dmg, src);
	}

	public void onZapComplete(){
		zap();
		next();
	}

	private void zap( ){
		threatened = false;
		spend(TICK);

		GameScene.add(Blob.seed(enemy.pos, 15, CorrosiveGas.class).setStrength(8));
		for (int i : PathFinder.NEIGHBOURS8){
			if (!Dungeon.level.solid[enemy.pos+i]) {
				GameScene.add(Blob.seed(enemy.pos + i, 5, CorrosiveGas.class).setStrength(8));
			}
		}

	}

	@Override
	protected boolean canVent(int target) {
		return false;
	}

	@Override
	protected boolean getCloser(int target) {
		return false;
	}

	@Override
	protected boolean getFurther(int target) {
		return false;
	}

	@Override
	public void rollToDropLoot() {
		if (Dungeon.hero.等级 > 最大等级 + 2) return;

		super.rollToDropLoot();

		Dungeon.level.dropRandomCell( new MetalShard(), pos );
	}

	private class Hunting extends Mob.Hunting {

		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {

			if (threatened && enemyInFOV){
				if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
					sprite.zap( enemy.pos );
					return false;
				} else {
					zap();
					return true;
				}
			} else {
				return super.act( enemyInFOV, justAlerted );
			}

		}

	}

}
