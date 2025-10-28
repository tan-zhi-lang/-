

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CrystalGuardianSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class CrystalGuardian extends Mob{

	{
		spriteClass = CrystalGuardianSprite.class;

		生命 = 最大生命 = 100;
		defenseSkill = 14;

		经验 = 10;
		最大等级 = -2;

		SLEEPING = new Sleeping();
		state = SLEEPING;

		properties.add(Property.INORGANIC);
		properties.add(Property.MINIBOSS);
	}

	private boolean recovering = false;

	public boolean recovering(){
		return recovering;
	}

	@Override
	protected boolean act() {
		if (recovering){
			if (buff(PinCushion.class) != null){
				buff(PinCushion.class).detach();
			}
			throwItems();
			生命 = Math.min(最大生命, 生命 +5);
			if (Dungeon.level.heroFOV[pos]) {
				sprite.showStatusWithIcon(CharSprite.增强, "5", FloatingText.HEALING);
			}
			if (生命 == 最大生命){
				recovering = false;
				if (sprite instanceof CrystalGuardianSprite) ((CrystalGuardianSprite) sprite).endCrumple();
			}
			spend(TICK);
			return true;
		}
		return super.act();
	}

	@Override
	public int 最小攻击() {
		return 10;
	}
	@Override
	public int 最大攻击() {
		return 16;
	}

	@Override
	public int 最大命中(Char target ) {
		return 20;
	}

	@Override
	public int 最大闪避(Char enemy) {
		if (recovering) return 0;
		else            return super.最大闪避(enemy);
	}

	@Override
	public boolean surprisedBy(Char enemy, boolean attacking) {
		if (recovering) return false;
		else            return super.surprisedBy(enemy, attacking);
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+10;
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	public boolean attack(Char enemy, float dmgMulti, float dmgBonus, float accMulti) {
		//if enemy is hero, and they aren't currently fighting the spire, -100 points
		if (enemy == Dungeon.hero){
			boolean spireNear = false;
			for (Mob m : Dungeon.level.mobs.toArray(new Mob[0])){
				if (m instanceof CrystalSpire && m.生命 != m.最大生命 && Dungeon.level.distance(pos, m.pos) <= 8){
					spireNear = true;
				}
			}
			if (!spireNear){
				Statistics.questScores[2] -= 100;
			}
		}
		return super.attack(enemy, dmgMulti, dmgBonus, accMulti);
	}

	@Override
	public int 防御时(Char enemy, int damage) {
		if (recovering){
			//this triggers before blocking, so the dmg as block-bypassing
			sprite.showStatusWithIcon(CharSprite.削弱, Integer.toString(damage), FloatingText.PHYS_DMG_NO_BLOCK);
			生命 = Math.max(1, 生命 -damage);
			damage = -1;
		}

		return super.防御时(enemy, damage);
	}

	@Override
	public boolean isAlive() {
		if (生命 <= 0){
			生命 = 1;

			for (Buff b : buffs()){
				if (!(b instanceof Doom || b instanceof Cripple)) {
					b.detach();
				}
			}

			if (!recovering) {
				recovering = true;
				Bestiary.setSeen(getClass());
				Bestiary.countEncounter(getClass());
				if (sprite != null) ((CrystalGuardianSprite) sprite).crumple();
			}
		}
		return super.isAlive();
	}

	@Override
	public boolean 是无敌(Class effect) {
		if (recovering){
			//while recovering, immune to chars that aren't the hero or spire
			// this is sort of a hack to prevent allies from attacking downed guardians
			return super.是无敌(effect) || (Char.class.isAssignableFrom(effect) && !Hero.class.isAssignableFrom(effect) && !CrystalSpire.class.isAssignableFrom(effect));
		}
		return super.是无敌(effect);
	}

	public CrystalGuardian(){
		super();
		switch (Random.Int(3)){
			case 0: default:
				spriteClass = CrystalGuardianSprite.Blue.class;
				break;
			case 1:
				spriteClass = CrystalGuardianSprite.Green.class;
				break;
			case 2:
				spriteClass = CrystalGuardianSprite.Red.class;
				break;
		}
	}

	@Override
	public float spawningWeight() {
		return 0;
	}

	@Override
	public float 移速() {
		//crystal guardians take up to 4 turns when moving through an enclosed space
		if (!Dungeon.level.openSpace[pos]) {
			return Math.max(0.25f, super.移速() / 4f);
		}
		return super.移速();
	}

	@Override
	public void move(int step, boolean travelling) {
		super.move(step, travelling);
		if (Dungeon.level.map[pos] == Terrain.MINE_CRYSTAL){
			Level.set(pos, Terrain.EMPTY);
			GameScene.updateMap(pos);
			if (Dungeon.level.heroFOV[pos]){
				Splash.at(pos, 0xFFFFFF, 5);
				Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			}
			//breaking a crystal costs an extra move, not affected by enclosed spaces though
			spend(1/super.移速());
		}
	}

	@Override
	public boolean[] modifyPassable(boolean[] passable) {
		//if we are hunting, we can stomp through crystals, but prefer not to
		if (state == HUNTING && target != -1){
			PathFinder.buildDistanceMap(target, passable);

			if (PathFinder.distance[pos] > 2*Dungeon.level.distance(pos, target)) {
				for (int i = 0; i < Dungeon.level.length(); i++) {
					passable[i] = passable[i] || Dungeon.level.map[i] == Terrain.MINE_CRYSTAL;
				}
			}
		}
		return passable;
	}

	@Override
	public void beckon(int cell) {
		if (state == SLEEPING){
			//do nothing
		} else {
			super.beckon(cell);
		}
	}

	protected class Sleeping extends Mob.Sleeping{

		@Override
		protected void awaken(boolean enemyInFOV) {
			if (enemyInFOV){
				//do not wake up if we see an enemy we can't actually reach
				PathFinder.buildDistanceMap(enemy.pos, Dungeon.level.passable);
				if (PathFinder.distance[pos] == Integer.MAX_VALUE){
					return;
				}
			}
			super.awaken(enemyInFOV);
		}
	}

	public static final String SPRITE = "sprite";
	public static final String RECOVERING = "recovering";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(SPRITE, spriteClass);
		bundle.put(RECOVERING, recovering);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		spriteClass = bundle.getClass(SPRITE);
		recovering = bundle.getBoolean(RECOVERING);
	}
}
