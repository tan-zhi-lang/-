

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.TargetedCell;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Embers;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ElementalSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class Elemental extends Mob {

	{
		生命 = 最大生命 = 60;
		defenseSkill = 20;
		
		经验 = 10;
		最大等级 = 20;
		
		flying = true;
	}

	protected boolean summonedALly;
	
	@Override
	public int 攻击() {
		if (!summonedALly) {
			return Random.NormalIntRange(20, 25);
		} else {
			int regionScale = Math.max(2, (1 + Dungeon.scalingDepth()/5));
			return Random.NormalIntRange(5*regionScale, 5 + 5*regionScale);
		}
	}
	
	@Override
	public int 最大命中(Char target ) {
		if (!summonedALly) {
			return 25;
		} else {
			int regionScale = Math.max(2, (1 + Dungeon.scalingDepth()/5));
			return 5 + 5*regionScale;
		}
	}

	public void setSummonedALly(){
		summonedALly = true;
		//sewers are prison are equivalent, otherwise scales as normal (2/2/3/4/5)
		int regionScale = Math.max(2, (1 + Dungeon.scalingDepth()/5));
		defenseSkill = 5*regionScale;
		最大生命 = 15*regionScale;
	}
	
	@Override
	public int 防御() {
		return super.防御() + Random.NormalIntRange(0, 5);
	}
	
	protected int rangedCooldown = Random.NormalIntRange( 3, 5 );
	
	@Override
	protected boolean act() {
		if (state == HUNTING){
			rangedCooldown--;
		}
		
		return super.act();
	}

	@Override
	public void 死亡时(Object cause) {
		flying = false;
		super.死亡时(cause);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		if (super.canAttack(enemy)){
			return true;
		} else {
			return rangedCooldown < 0 && new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT ).collisionPos == enemy.pos;
		}
	}
	
	protected boolean doAttack( Char enemy ) {
		
		if (Dungeon.level.adjacent( pos, enemy.pos )
				|| rangedCooldown > 0
				|| new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT ).collisionPos != enemy.pos) {
			
			return super.doAttack( enemy );
			
		} else {
			
			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap( enemy.pos );
				return false;
			} else {
				zap();
				return true;
			}
		}
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		meleeProc( enemy, damage );
		
		return damage;
	}
	
	protected void zap() {
		spend( 1f );

		Invisibility.dispel(this);
		Char enemy = this.enemy;
		if (hit( this, enemy, true )) {
			
			rangedProc( enemy );
			
		} else {
			enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
		}

		rangedCooldown = Random.NormalIntRange( 3, 5 );
	}
	
	public void onZapComplete() {
		zap();
		next();
	}
	
	@Override
	public boolean add( Buff buff ) {
		if (harmfulBuffs.contains( buff.getClass() )) {
			受伤时( Random.NormalIntRange( 最大生命 /2, 最大生命 * 3/5 ), buff );
			return false;
		} else {
			return super.add( buff );
		}
	}
	
	protected abstract void meleeProc( Char enemy, int damage );
	protected abstract void rangedProc( Char enemy );
	
	protected ArrayList<Class<? extends Buff>> harmfulBuffs = new ArrayList<>();
	
	private static final String COOLDOWN = "cooldown";
	private static final String SUMMONED_ALLY = "summoned_ally";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( COOLDOWN, rangedCooldown );
		bundle.put( SUMMONED_ALLY, summonedALly);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		if (bundle.contains( COOLDOWN )){
			rangedCooldown = bundle.getInt( COOLDOWN );
		}
		summonedALly = bundle.getBoolean( SUMMONED_ALLY );
		if (summonedALly){
			setSummonedALly();
		}
	}
	
	public static class FireElemental extends Elemental {
		
		{
			spriteClass = ElementalSprite.Fire.class;
			
			loot = PotionOfLiquidFlame.class;
			lootChance = 1/8f;
			
			properties.add( Property.FIERY );
			
			harmfulBuffs.add( com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost.class );
			harmfulBuffs.add( Chill.class );
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			if (Random.Int( 2 ) == 0 && !Dungeon.level.water[enemy.pos]) {
				Buff.施加( enemy, 燃烧.class ).reignite( enemy );
				if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
			}
		}
		
		@Override
		protected void rangedProc( Char enemy ) {
			if (!Dungeon.level.water[enemy.pos]) {
				Buff.施加( enemy, 燃烧.class ).reignite( enemy, 4f );
			}
			if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
		}
	}
	
	//used in wandmaker quest, a fire elemental with lower ACC/EVA/DMG, no on-hit fire
	// and a unique 'fireball' style ranged attack, which can be dodged
	public static class NewbornFireElemental extends FireElemental {
		
		{
			spriteClass = ElementalSprite.NewbornFire.class;

			defenseSkill = 12;
			
			properties.add(Property.MINIBOSS);
		}

		private int targetingPos = -1;

		@Override
		protected boolean act() {
			//fire a charged attack instead of any other action, as long as it is possible to do so
			if (targetingPos != -1 && state == HUNTING){
				//account for bolt hitting walls, in case position suddenly changed
				targetingPos = new Ballistica( pos, targetingPos, Ballistica.STOP_SOLID | Ballistica.STOP_TARGET ).collisionPos;
				if (sprite != null && (sprite.visible || Dungeon.level.heroFOV[targetingPos])) {
					sprite.zap( targetingPos );
					return false;
				} else {
					zap();
					return true;
				}
			} else {

				if (state != HUNTING){
					targetingPos = -1;
				}

				return super.act();
			}
		}

		@Override
		protected boolean canAttack( Char enemy ) {
			if (super.canAttack(enemy)){
				return true;
			} else {
				return rangedCooldown < 0 && new Ballistica( pos, enemy.pos, Ballistica.STOP_SOLID | Ballistica.STOP_TARGET ).collisionPos == enemy.pos;
			}
		}

		protected boolean doAttack( Char enemy ) {

			if (rangedCooldown > 0) {

				return super.doAttack( enemy );

			} else if (new Ballistica( pos, enemy.pos, Ballistica.STOP_SOLID | Ballistica.STOP_TARGET ).collisionPos == enemy.pos) {

				//set up an attack for next turn
				ArrayList<Integer> candidates = new ArrayList<>();
				for (int i : PathFinder.NEIGHBOURS8){
					int target = enemy.pos + i;
					if (target != pos && new Ballistica(pos, target, Ballistica.STOP_SOLID | Ballistica.STOP_TARGET).collisionPos == target){
						candidates.add(target);
					}
				}

				if (!candidates.isEmpty()){
					targetingPos = Random.element(candidates);

					for (int i : PathFinder.NEIGHBOURS9){
						if (!Dungeon.level.solid[targetingPos + i]) {
							sprite.parent.addToBack(new TargetedCell(targetingPos + i, 0xFF0000));
						}
					}

					GLog.n(Messages.get(this, "charging"));
					spend(GameMath.gate(attackDelay(), (int)Math.ceil(Dungeon.hero.cooldown()), 3*attackDelay()));
					Dungeon.hero.interrupt();
					return true;
				} else {
					rangedCooldown = 1;
					return super.doAttack(enemy);
				}


			} else {

				if (sprite != null && (sprite.visible || Dungeon.level.heroFOV[targetingPos])) {
					sprite.zap( targetingPos );
					return false;
				} else {
					zap();
					return true;
				}

			}
		}

		@Override
		protected void zap() {
			if (targetingPos != -1) {
				spend(1f);

				Invisibility.dispel(this);

				for (int i : PathFinder.NEIGHBOURS9) {
					if (!Dungeon.level.solid[targetingPos + i]) {
						CellEmitter.get(targetingPos + i).burst(ElmoParticle.FACTORY, 5);
						if (Dungeon.level.water[targetingPos + i]) {
							GameScene.add(Blob.seed(targetingPos + i, 2, Fire.class));
						} else {
							GameScene.add(Blob.seed(targetingPos + i, 8, Fire.class));
						}

						Char target = Actor.findChar(targetingPos + i);
						if (target != null && target != this) {
							Buff.施加(target, 燃烧.class).reignite(target);
							if (target == Dungeon.hero){
								Statistics.questScores[1] -= 200;
							}
						}
					}
				}
				Sample.INSTANCE.play(Assets.Sounds.BURNING);
			}

			targetingPos = -1;
			rangedCooldown = Random.NormalIntRange( 3, 5 );
		}

		@Override
		public int 最大命中(Char target) {
			if (!summonedALly) {
				return 15;
			} else {
				return super.最大命中(target);
			}
		}

		@Override
		public int 攻击() {
			if (!summonedALly) {
				return Random.NormalIntRange(10, 12);
			} else {
				return super.攻击();
			}
		}

		@Override
		protected void meleeProc(Char enemy, int damage) {
			//no fiery on-hit unless it is an ally summon
			if (summonedALly) {
				super.meleeProc(enemy, damage);
			}
		}

		@Override
		public void 死亡时(Object cause) {
			super.死亡时(cause);
			if (alignment == Alignment.ENEMY) {
				Dungeon.level.drop( new Embers(), pos ).sprite.drop();
				//assign score here as player may choose to keep the embers
				Statistics.questScores[1] += 2000;
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						Music.INSTANCE.fadeOut(1f, new Callback() {
							@Override
							public void call() {
								if (Dungeon.level != null) {
									Dungeon.level.playLevelMusic();
								}
							}
						});
					}
				});
			}
		}

		@Override
		public boolean reset() {
			return !summonedALly;
		}

		@Override
		public String description() {
			String desc = super.description();

			if (summonedALly){
				desc += " " + Messages.get(this, "desc_ally");
			} else {
				desc += " " + Messages.get(this, "desc_boss");
			}

			return desc;
		}

		private static final String TARGETING_POS = "targeting_pos";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(TARGETING_POS, targetingPos);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			targetingPos = bundle.getInt(TARGETING_POS);
		}
	}

	//not a miniboss, no ranged attack, otherwise a newborn elemental
	public static class AllyNewBornElemental extends NewbornFireElemental {

		{
			rangedCooldown = Integer.MAX_VALUE;

			properties.remove(Property.MINIBOSS);
		}

	}
	
	public static class FrostElemental extends Elemental {
		
		{
			spriteClass = ElementalSprite.Frost.class;
			
			loot = PotionOfFrost.class;
			lootChance = 1/8f;
			
			properties.add( Property.ICY );
			
			harmfulBuffs.add( 燃烧.class );
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			if (Random.Int( 3 ) == 0 || Dungeon.level.water[enemy.pos]) {
				Freezing.freeze( enemy.pos );
				if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
			}
		}
		
		@Override
		protected void rangedProc( Char enemy ) {
			Freezing.freeze( enemy.pos );
			if (enemy.sprite.visible) Splash.at( enemy.sprite.center(), sprite.blood(), 5);
		}
	}
	
	public static class ShockElemental extends Elemental {
		
		{
			spriteClass = ElementalSprite.Shock.class;
			
			loot = ScrollOfRecharging.class;
			lootChance = 1/4f;
			
			properties.add( Property.ELECTRIC );
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			ArrayList<Char> affected = new ArrayList<>();
			ArrayList<Lightning.Arc> arcs = new ArrayList<>();
			Shocking.arc( this, enemy, 2, affected, arcs );
			
			if (!Dungeon.level.water[enemy.pos]) {
				affected.remove( enemy );
			}
			
			for (Char ch : affected) {
				ch.受伤时( Math.round( damage * 0.4f ), new Shocking() );
				if (ch == Dungeon.hero && !ch.isAlive()){
					Dungeon.fail(this);
					GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
				}
			}

			boolean visible = sprite.visible || enemy.sprite.visible;
			for (Char ch : affected){
				if (ch.sprite.visible) visible = true;
			}

			if (visible) {
				sprite.parent.addToFront(new Lightning(arcs, null));
				Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
			}
		}
		
		@Override
		protected void rangedProc( Char enemy ) {
			Buff.施加( enemy, Blindness.class, Blindness.DURATION/2f );
			if (enemy == Dungeon.hero) {
				GameScene.flash(0x80FFFFFF);
			}
		}
	}
	
	public static class ChaosElemental extends Elemental {
		
		{
			spriteClass = ElementalSprite.Chaos.class;
			
			loot = ScrollOfTransmutation.class;
			lootChance = 1f;
		}
		
		@Override
		protected void meleeProc( Char enemy, int damage ) {
			Ballistica aim = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
			//TODO shortcutting the fx seems fine for now but may cause problems with new cursed effects
			//of course, not shortcutting it means actor ordering issues =S
			CursedWand.randomValidEffect(null, this, aim, false).effect(null, this, aim, false);
		}

		@Override
		protected void zap() {
			spend( 1f );

			Invisibility.dispel(this);
			Char enemy = this.enemy;
			//skips accuracy check, always hits
			rangedProc( enemy );

			rangedCooldown = Random.NormalIntRange( 3, 5 );
		}

		@Override
		public void onZapComplete() {
			zap();
			//next(); triggers after wand effect
		}

		@Override
		protected void rangedProc( Char enemy ) {
			CursedWand.cursedZap(null, this, new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET), new Callback() {
				@Override
				public void call() {
					next();
				}
			});
		}
	}
	
	public static Class<? extends Elemental> random(){
		float altChance = 1/50f * RatSkull.exoticChanceMultiplier();
		if (Random.Float() < altChance){
			return ChaosElemental.class;
		}
		
		float roll = Random.Float();
		if (roll < 0.4f){
			return FireElemental.class;
		} else if (roll < 0.8f){
			return FrostElemental.class;
		} else {
			return ShockElemental.class;
		}
	}
}
