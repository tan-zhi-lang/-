

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

import java.util.HashSet;

public class 木遁 extends 目标忍术 {

	public static final 木遁 INSTANCE = new 木遁();

	@Override
	public int icon() {
		return HeroIcon.木遁;
	}
	
	@Override
	protected void onTargetSelected(叛忍护额 tome,Hero hero,Integer target){

		if (target != null) {

			if (target != hero.pos && hero.rooted){
				PixelScene.shake(1,1f);
				return;
			}

			PathFinder.buildDistanceMap(hero.pos,BArray.or(Dungeon.level.passable,Dungeon.level.avoid,null),6);

			if ( PathFinder.distance[target] == Integer.MAX_VALUE ||
				 !Dungeon.level.heroFOV[target] ||
				 (target != hero.pos && Actor.findChar( target ) != null)) {

				GLog.w( Messages.get(this, "fov") );
				return;
			}

			boolean shadowStepping = hero.invisible > 0;

			if (!shadowStepping) {
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (Dungeon.level.adjacent(mob.pos, hero.pos) && mob.alignment != Char.Alignment.ALLY) {
						Buff.延长(mob,Blindness.class,Blindness.DURATION/2f);
						if (mob.state == mob.HUNTING) mob.state = mob.WANDERING;
						mob.sprite.emitter().burst(Speck.factory(Speck.LIGHT),4);
					}
				}

				for (Char ch : Actor.chars()){
					if (ch instanceof NinjaLog){
						ch.死亡时(null);
					}
				}

				NinjaLog n = new NinjaLog();
				n.pos = hero.pos;
				GameScene.add(n);
				Dungeon.level.occupyCell(n);

				Buff.施加(hero, Invisibility.class, 4f);
			}

			CellEmitter.get(hero.pos).burst(Speck.factory(Speck.WOOL),10);
			传送卷轴.appear(hero,target);
			Sample.INSTANCE.play(Assets.Sounds.PUFF);
			Dungeon.level.occupyCell( hero );
			Dungeon.observe();
			GameScene.updateFog();

			if (!shadowStepping) {
				hero.spendAndNext(Actor.TICK);
			} else {
				hero.next();
			}
		}
		onSpellCast(tome, hero);
	}



	public static class NinjaLog extends NPC{

		{
			spriteClass = NinjaLogSprite.class;
			defenseSkill = 0;

			properties.add(Property.INORGANIC); //wood is organic, but this is accurate for game logic

			alignment = Alignment.ALLY;

			最大生命 = 20;

			生命 = 最大生命;
			properties.add(Property.树妖);
		}

		@Override
		public float 最大防御() {
			return 0;
		}

		{
			immunities.add( Dread.class);
			immunities.add( Terror.class);
			immunities.add( Amok.class);
			immunities.add( Charm.class);
			immunities.add( Sleep.class);
			immunities.add( AllyBuff.class);
		}

	}

	public static class NinjaLogSprite extends MobSprite{

		public NinjaLogSprite(){
			super();

			texture( Assets.Sprites.NINJA_LOG );

			TextureFilm frames = new TextureFilm(texture,11,12 );

			idle = new Animation( 0, true );
			idle.frames( frames, 0 );

			run = idle.clone();
			attack = idle.clone();
			zap = attack.clone();

			die = new Animation( 12, false );
			die.frames( frames, 1, 2, 3, 4 );

			play( idle );

		}

		@Override
		public void showAlert() {
			//do nothing
		}

		@Override
		public int blood() {
			return 0xFF966400;
		}

	}
	public static Char findChar(Ballistica path, Hero hero, int wallPenetration, HashSet<Char> existingTargets){
		for (int cell : path.path){
			Char ch = Actor.findChar(cell);
			if (ch != null){
				if (ch == hero || existingTargets.contains(ch) || ch.alignment == Char.Alignment.ALLY){
					continue;
				} else {
					return ch;
				}
			}
			if (Dungeon.level.solid[cell]){
				wallPenetration--;
				if (wallPenetration < 0){
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",60,
								   1,2
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
