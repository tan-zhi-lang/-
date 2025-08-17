

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class HallowedGround extends TargetedClericSpell {

	public static final HallowedGround INSTANCE = new HallowedGround();

	@Override
	public int icon() {
		return HeroIcon.HALLOWED_GROUND;
	}

	@Override
	public float chargeUse(Hero hero) {
		return 2;
	}

	@Override
	public int targetingFlags() {
		return Ballistica.STOP_TARGET;
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {

		if (target == null){
			return;
		}

		if (Dungeon.level.solid[target] || !Dungeon.level.heroFOV[target]){
			GLog.w(Messages.get(this, "invalid_target"));
			return;
		}

		ArrayList<Char> affected = new ArrayList<>();

		PathFinder.buildDistanceMap(target, BArray.not(Dungeon.level.solid, null), hero.天赋点数(Talent.HALLOWED_GROUND));
		for (int i = 0; i < Dungeon.level.length(); i++){
			if (PathFinder.distance[i] != Integer.MAX_VALUE){
				int c = Dungeon.level.map[i];
				if (c == Terrain.EMPTY || c == Terrain.EMBERS || c == Terrain.EMPTY_DECO) {
					Level.set( i, Terrain.GRASS);
					GameScene.updateMap( i );
					CellEmitter.get(i).burst(LeafParticle.LEVEL_SPECIFIC, 2);
				}
				GameScene.add(Blob.seed(i, 20, HallowedTerrain.class));
				CellEmitter.get(i).burst(ShaftParticle.FACTORY, 2);

				Char ch = Actor.findChar(i);
				if (ch != null){
					affected.add(ch);
				}
			}
		}

		Char ally = PowerOfMany.getPoweredAlly();
		if (ally != null && ally.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
			if (affected.contains(hero) && !affected.contains(ally)){
				affected.add(ally);
			} else if (!affected.contains(hero) && affected.contains(ally)){
				affected.add(hero);
			}
		}

		for (Char ch : affected){
			affectChar(ch);
		}

		Sample.INSTANCE.play(Assets.Sounds.MELD);
		hero.sprite.zap(target);
		hero.spendAndNext( 1f );

		onSpellCast(tome, hero);

	}

	private void affectChar( Char ch ){
		if (ch.alignment == Char.Alignment.ALLY){
			int x=ch.最大生命(Dungeon.hero.天赋点数(Talent.HALLOWED_GROUND,0.03f))+Dungeon.hero.天赋点数(Talent.HALLOWED_GROUND,3);

			if (ch == Dungeon.hero || ch.生命 == ch.最大生命){
				int barrierToGive = Math.min(x, x*x - ch.shielding());
				Buff.施加(ch, Barrier.class).增加(barrierToGive);
				ch.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(barrierToGive), FloatingText.SHIELDING );
			} else {
				int barrier = x - (ch.最大生命 - ch.生命);
				barrier = Math.max(barrier, 0);
				ch.生命 += x - barrier;
				ch.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(x-barrier), FloatingText.HEALING );
				if (barrier > 0){
					Buff.施加(ch, Barrier.class).增加(barrier);
					ch.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(barrier), FloatingText.SHIELDING );
				}
			}
		} else if (!ch.flying) {
			Buff.施加(ch, GuidingLight.Illuminated.class);
			Buff.施加(ch, Roots.class, Dungeon.hero.天赋点数(Talent.HALLOWED_GROUND,1));
		}
	}

	public String desc(){
		int area = 1 + Dungeon.hero.天赋点数(Talent.HALLOWED_GROUND,2);
		return Messages.get(this, "desc", area) + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public static class HallowedTerrain extends Blob {

		@Override
		protected void evolve() {

			int cell;

			Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );

			ArrayList<Char> affected = new ArrayList<>();

			// on avg, hallowed ground produces 9/17/25 tiles of grass, 100/67/50% of total tiles
			int chance = 10 + 10*Dungeon.hero.天赋点数(Talent.HALLOWED_GROUND);

			for (int i = area.left-1; i <= area.right; i++) {
				for (int j = area.top-1; j <= area.bottom; j++) {
					cell = i + j*Dungeon.level.width();
					if (cur[cell] > 0) {

						//fire destroys hallowed terrain
						if (fire != null && fire.volume > 0 && fire.cur[cell] > 0){
							off[cell] = cur[cell] = 0;
							continue;
						}

						int c = Dungeon.level.map[cell];
						if (c == Terrain.GRASS && Dungeon.level.plants.get(c) == null) {
							if (Random.Int(chance) == 0) {
								if (!再生.regenOn()
										|| (Dungeon.hero.buff(HallowedFurrowTracker.class) != null && Dungeon.hero.buff(HallowedFurrowTracker.class).count() > 100)){
									Level.set(cell, Terrain.FURROWED_GRASS);
								} else {
									Level.set(cell, Terrain.HIGH_GRASS);
								}
								GameScene.updateMap(cell);
								CellEmitter.get(cell).burst(LeafParticle.LEVEL_SPECIFIC, 5);
							}
						} else if (c == Terrain.EMPTY || c == Terrain.EMBERS || c == Terrain.EMPTY_DECO) {
							Level.set(cell, Terrain.GRASS);
							GameScene.updateMap(cell);
							CellEmitter.get(cell).burst(LeafParticle.LEVEL_SPECIFIC, 2);
						}

						Char ch = Actor.findChar(cell);
						if (ch != null){
							affected.add(ch);
						}

						off[cell] = cur[cell] - 1;
						volume += off[cell];
					} else {
						off[cell] = 0;
					}
				}
			}

			//max of 100 turns of grass per hero level before it starts to furrow
			if (volume > 0){
				Buff.count(Dungeon.hero, HallowedFurrowTracker.class, 1);
			}

			Char ally = PowerOfMany.getPoweredAlly();
			if (ally != null && ally.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null){
				if (affected.contains(Dungeon.hero) && !affected.contains(ally)){
					affected.add(ally);
				} else if (!affected.contains(Dungeon.hero) && affected.contains(ally)){
					affected.add(Dungeon.hero);
				}
			}

			for (Char ch :affected){
				affectChar(ch);
			}

		}

		private void affectChar( Char ch ){
			if (ch.alignment == Char.Alignment.ALLY){
				if (ch == Dungeon.hero || ch.生命 == ch.最大生命){
					Buff.施加(ch, Barrier.class).增加(1);
					ch.sprite.showStatusWithIcon( CharSprite.增强, "1", FloatingText.SHIELDING );
				} else {
					ch.生命++;
					ch.sprite.showStatusWithIcon( CharSprite.增强, "1", FloatingText.HEALING );
				}
			} else if (!ch.flying && ch.buff(Roots.class) == null){
				Buff.延长(ch, Cripple.class, 1f);
			}
		}

		@Override
		public void use(BlobEmitter emitter) {
			super.use( emitter );
			emitter.pour( ShaftParticle.FACTORY, 1f );
		}

		@Override
		public String tileDesc() {
			return Messages.get(this, "desc");
		}
	}

	public static class HallowedFurrowTracker extends CounterBuff{{revivePersists = true;}}

}
