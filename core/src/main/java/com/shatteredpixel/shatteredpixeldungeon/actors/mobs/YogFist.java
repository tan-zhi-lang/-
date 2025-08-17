

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sickle;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.FistSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public abstract class YogFist extends Mob {

	{
		生命 = 最大生命 = 300;
		defenseSkill = 20;

		viewDistance = Light.DISTANCE;

		//for doomed resistance
		经验 = 25;
		最大等级 = -2;

		state = HUNTING;

		properties.add(Property.BOSS);
		properties.add(Property.DEMONIC);
	}

	private float rangedCooldown;
	protected boolean canRangedInMelee = true;

	protected void incrementRangedCooldown(){
		rangedCooldown += Random.NormalFloat(8, 12);
	}

	@Override
	protected boolean act() {
		if (paralysed <= 0 && rangedCooldown > 0) rangedCooldown--;

		if (Dungeon.hero.invisible <= 0 && state == WANDERING){
			beckon(Dungeon.hero.pos);
			state = HUNTING;
			enemy = Dungeon.hero;
		}

		return super.act();
	}

	@Override
	protected boolean canAttack(Char enemy) {
		if (rangedCooldown <= 0){
			return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
		} else {
			return super.canAttack(enemy);
		}
	}

	private boolean invulnWarned = false;

	protected boolean isNearYog(){
		int yogPos = Dungeon.level.exit() + 3*Dungeon.level.width();
		return Dungeon.level.distance(pos, yogPos) <= 4;
	}

	@Override
	public boolean 是无敌(Class effect) {
		if (isNearYog() && !invulnWarned){
			invulnWarned = true;
			GLog.w(Messages.get(this, "invuln_warn"));
		}
		return isNearYog() || super.是无敌(effect);
	}

	@Override
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.adjacent( pos, enemy.pos ) && (!canRangedInMelee || rangedCooldown > 0)) {

			return super.doAttack( enemy );

		} else {

			incrementRangedCooldown();
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
	public void 受伤时(int dmg, Object src) {
		int preHP = 生命;
		super.受伤时(dmg, src);
		int dmgTaken = preHP - 生命;

		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (dmgTaken > 0 && lock != null && !免疫(src.getClass()) && !是无敌(src.getClass())){
			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES))   lock.addTime(dmgTaken/4f);
			else                                                    lock.addTime(dmgTaken/2f);
		}
	}

	@Override
	public void 死亡时(Object cause) {
		super.死亡时(cause);
		for ( Char c : Actor.chars() ){
			if (c instanceof YogDzewa){
				((YogDzewa) c).processFistDeath();
			}
		}
	}

	protected abstract void zap();

	public void onZapComplete(){
		zap();
		next();
	}

	@Override
	public int 最大命中(Char target ) {
		return 36;
	}

	@Override
	public int 攻击() {
		return Random.NormalIntRange( 18, 36 );
	}

	@Override
	public int 防御() {
		return super.防御() + Random.NormalIntRange(0, 15);
	}

	{
		immunities.add( Sleep.class );
	}

	@Override
	public String description() {
		return Messages.get(YogFist.class, "desc") + "\n\n" + Messages.get(this, "desc");
	}

	public static final String RANGED_COOLDOWN = "ranged_cooldown";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(RANGED_COOLDOWN, rangedCooldown);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		rangedCooldown = bundle.getFloat(RANGED_COOLDOWN);
	}

	public static class BurningFist extends YogFist {

		{
			spriteClass = FistSprite.Burning.class;

			properties.add(Property.FIERY);
		}

		@Override
		public boolean act() {

			boolean result = super.act();

			if (Dungeon.level.map[pos] == Terrain.WATER){
				Level.set( pos, Terrain.EMPTY);
				GameScene.updateMap( pos );
				CellEmitter.get( pos ).burst( Speck.factory( Speck.STEAM ), 10 );
			}

			//1.67 evaporated tiles on average
			int evaporatedTiles = Random.chances(new float[]{0, 1, 2});

			for (int i = 0; i < evaporatedTiles; i++) {
				int cell = pos + PathFinder.NEIGHBOURS8[Random.Int(8)];
				if (Dungeon.level.map[cell] == Terrain.WATER){
					Level.set( cell, Terrain.EMPTY);
					GameScene.updateMap( cell );
					CellEmitter.get( cell ).burst( Speck.factory( Speck.STEAM ), 10 );
				}
			}

			for (int i : PathFinder.NEIGHBOURS9) {
				int vol = Fire.volumeAt(pos+i, Fire.class);
				if (vol < 4 && !Dungeon.level.water[pos + i] && !Dungeon.level.solid[pos + i]){
					GameScene.add( Blob.seed( pos + i, 4 - vol, Fire.class ) );
				}
			}

			return result;
		}

		@Override
		protected void zap() {
			spend( 1f );

			if (Dungeon.level.map[enemy.pos] == Terrain.WATER){
				Level.set( enemy.pos, Terrain.EMPTY);
				GameScene.updateMap( enemy.pos );
				CellEmitter.get( enemy.pos ).burst( Speck.factory( Speck.STEAM ), 10 );
			} else {
				Buff.施加( enemy, 燃烧.class ).reignite( enemy );
			}

			for (int i : PathFinder.NEIGHBOURS9){
				if (!Dungeon.level.water[enemy.pos+i] && !Dungeon.level.solid[enemy.pos+i]){
					int vol = Fire.volumeAt(enemy.pos+i, Fire.class);
					if (vol < 4){
						GameScene.add( Blob.seed( enemy.pos + i, 4 - vol, Fire.class ) );
					}
				}
			}

		}

		{
			immunities.add(Frost.class);

			resistances.add(StormCloud.class);
			resistances.add(GeyserTrap.class);
		}

	}

	public static class SoiledFist extends YogFist {

		{
			spriteClass = FistSprite.Soiled.class;
		}

		@Override
		public boolean act() {

			boolean result = super.act();

			//1.33 grass tiles on average
			int furrowedTiles = Random.chances(new float[]{0, 2, 1});

			for (int i = 0; i < furrowedTiles; i++) {
				int cell = pos + PathFinder.NEIGHBOURS9[Random.Int(9)];
				if (Dungeon.level.map[cell] == Terrain.GRASS) {
					Level.set(cell, Terrain.FURROWED_GRASS);
					GameScene.updateMap(cell);
					CellEmitter.get(cell).burst(LeafParticle.GENERAL, 10);
				}
			}

			Dungeon.observe();

			for (int i : PathFinder.NEIGHBOURS9) {
				int cell = pos + i;
				if (canSpreadGrass(cell)){
					Level.set(pos+i, Terrain.GRASS);
					GameScene.updateMap( pos + i );
				}
			}

			return result;
		}

		@Override
		public void 受伤时(int dmg, Object src) {
			int grassCells = 0;
			for (int i : PathFinder.NEIGHBOURS9) {
				if (Dungeon.level.map[pos+i] == Terrain.FURROWED_GRASS
				|| Dungeon.level.map[pos+i] == Terrain.HIGH_GRASS){
					grassCells++;
				}
			}
			if (grassCells > 0) dmg = Math.round(dmg * (6-grassCells)/6f);

			//can be ignited, but takes no damage from burning
			if (src.getClass() == 燃烧.class){
				return;
			}

			super.受伤时(dmg, src);
		}

		@Override
		protected void zap() {
			spend( 1f );

			Invisibility.dispel(this);
			Char enemy = this.enemy;
			if (hit( this, enemy, true )) {

				Buff.施加( enemy, Roots.class, 3f );

			} else {

				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}

			for (int i : PathFinder.NEIGHBOURS9){
				int cell = enemy.pos + i;
				if (canSpreadGrass(cell)){
					if (Random.Int(5) == 0){
						Level.set(cell, Terrain.FURROWED_GRASS);
						GameScene.updateMap( cell );
					} else {
						Level.set(cell, Terrain.GRASS);
						GameScene.updateMap( cell );
					}
					CellEmitter.get( cell ).burst( LeafParticle.GENERAL, 10 );
				}
			}
			Dungeon.observe();

		}

		private boolean canSpreadGrass(int cell){
			int yogPos = Dungeon.level.exit() + Dungeon.level.width()*3;
			return Dungeon.level.distance(cell, yogPos) > 4 && !Dungeon.level.solid[cell]
					&& !(Dungeon.level.map[cell] == Terrain.FURROWED_GRASS || Dungeon.level.map[cell] == Terrain.HIGH_GRASS);
		}

	}

	public static class RottingFist extends YogFist {

		{
			spriteClass = FistSprite.Rotting.class;

			properties.add(Property.ACIDIC);
		}

		@Override
		protected boolean act() {
			//ensures toxic gas acts at the appropriate time when added
			GameScene.add(Blob.seed(pos, 0, ToxicGas.class));

			if (Dungeon.level.water[pos] && 生命 < 最大生命) {
				sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(最大生命 /50), FloatingText.HEALING);
				生命 = Math.min(最大生命, 生命 + 最大生命 /50);
			}

			return super.act();
		}

		@Override
		public void 受伤时(int dmg, Object src) {
			if (!是无敌(src.getClass())
					&& !(src instanceof 流血)
					&& buff(Sickle.HarvestBleedTracker.class) == null){
				dmg = Math.round( dmg * resist( src.getClass() ));
				if (dmg < 0){
					return;
				}
				流血 b = buff(流血.class);
				if (b == null){
					b = new 流血();
				}
				b.announced = false;
				b.set(dmg*.6f);
				b.attachTo(this);
				sprite.showStatus(CharSprite.WARNING, Messages.titleCase(b.name()) + " " + (int)b.level());
			} else{
				super.受伤时(dmg, src);
			}
		}

		@Override
		protected void zap() {
			spend( 1f );
			GameScene.add(Blob.seed(enemy.pos, 100, ToxicGas.class));
		}

		@Override
		public int 攻击时(Char enemy, int damage ) {
			damage = super.攻击时( enemy, damage );

			if (Random.Int( 2 ) == 0) {
				Buff.施加( enemy, Ooze.class ).set( Ooze.DURATION );
				enemy.sprite.burst( 0xFF000000, 5 );
			}

			return damage;
		}

		{
			immunities.add(ToxicGas.class);
		}

	}

	public static class RustedFist extends YogFist {

		{
			spriteClass = FistSprite.Rusted.class;

			properties.add(Property.LARGE);
			properties.add(Property.INORGANIC);
		}

		@Override
		public int 攻击() {
			return Random.NormalIntRange( 22, 44 );
		}

		@Override
		public void 受伤时(int dmg, Object src) {
			if (!是无敌(src.getClass()) && !(src instanceof Viscosity.DeferedDamage)){
				dmg = Math.round( dmg * resist( src.getClass() ));
				if (dmg >= 0) {
					Buff.施加(this, Viscosity.DeferedDamage.class).extend(dmg);
					sprite.showStatus(CharSprite.WARNING, Messages.get(Viscosity.class, "deferred", dmg));
				}
			} else{
				super.受伤时(dmg, src);
			}
		}

		@Override
		protected void zap() {
			spend( 1f );
			Buff.施加(enemy, Cripple.class, 4f);
		}

	}

	public static class BrightFist extends YogFist {

		{
			spriteClass = FistSprite.Bright.class;

			properties.add(Property.ELECTRIC);

			canRangedInMelee = false;
		}

		@Override
		protected void incrementRangedCooldown() {
			//ranged attack has no cooldown
		}

		//used so resistances can differentiate between melee and magical attacks
		public static class LightBeam{}

		@Override
		protected void zap() {
			spend( 1f );

			Invisibility.dispel(this);
			Char enemy = this.enemy;
			if (hit( this, enemy, true )) {

				enemy.受伤时( Random.NormalIntRange(10, 20), new LightBeam() );
				Buff.延长( enemy, Blindness.class, Blindness.DURATION/2f );

				if (!enemy.isAlive() && enemy == Dungeon.hero) {
					Badges.validateDeathFromEnemyMagic();
					Dungeon.fail( this );
					GLog.n( Messages.get(Char.class, "kill", name()) );
				}

			} else {

				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}

		}

		@Override
		public void 受伤时(int dmg, Object src) {
			int beforeHP = 生命;
			super.受伤时(dmg, src);
			if (isAlive() && beforeHP > 最大生命 /2 && 生命 < 最大生命 /2){
				生命 = 最大生命 /2;
				Buff.延长( Dungeon.hero, Blindness.class, Blindness.DURATION*1.5f );
				int i;
				do {
					i = Random.Int(Dungeon.level.length());
				} while (Dungeon.level.heroFOV[i]
						|| Dungeon.level.solid[i]
						|| Actor.findChar(i) != null
						|| PathFinder.getStep(i, Dungeon.level.exit(), Dungeon.level.passable) == -1);
				ScrollOfTeleportation.appear(this, i);
				state = WANDERING;
				GameScene.flash(0x80FFFFFF);
				GLog.w( Messages.get( this, "teleport" ));
			} else if (!isAlive()){
				Buff.延长( Dungeon.hero, Blindness.class, Blindness.DURATION*3f );
				GameScene.flash(0x80FFFFFF);
			}
		}

	}

	public static class DarkFist extends YogFist {

		{
			spriteClass = FistSprite.Dark.class;

			canRangedInMelee = false;
		}

		@Override
		protected void incrementRangedCooldown() {
			//ranged attack has no cooldown
		}

		//used so resistances can differentiate between melee and magical attacks
		public static class DarkBolt{}

		@Override
		protected void zap() {
			spend( 1f );

			Invisibility.dispel(this);
			Char enemy = this.enemy;
			if (hit( this, enemy, true )) {

				enemy.受伤时( Random.NormalIntRange(10, 20), new DarkBolt() );

				Light l = enemy.buff(Light.class);
				if (l != null){
					l.weaken(50);
				}

				if (!enemy.isAlive() && enemy == Dungeon.hero) {
					Badges.validateDeathFromEnemyMagic();
					Dungeon.fail( this );
					GLog.n( Messages.get(Char.class, "kill", name()) );
				}

			} else {

				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}

		}

		@Override
		public void 受伤时(int dmg, Object src) {
			int beforeHP = 生命;
			super.受伤时(dmg, src);
			if (isAlive() && beforeHP > 最大生命 /2 && 生命 < 最大生命 /2){
				生命 = 最大生命 /2;
				Light l = Dungeon.hero.buff(Light.class);
				if (l != null){
					l.detach();
				}
				int i;
				do {
					i = Random.Int(Dungeon.level.length());
				} while (Dungeon.level.heroFOV[i]
						|| Dungeon.level.solid[i]
						|| Actor.findChar(i) != null
						|| PathFinder.getStep(i, Dungeon.level.exit(), Dungeon.level.passable) == -1);
				ScrollOfTeleportation.appear(this, i);
				state = WANDERING;
				GameScene.flash(0, false);
				GLog.w( Messages.get( this, "teleport" ));
			} else if (!isAlive()){
				Light l = Dungeon.hero.buff(Light.class);
				if (l != null){
					l.detach();
				}
				GameScene.flash(0, false);
			}
		}

	}

}
