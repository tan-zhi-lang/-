

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

public class 粘咕 extends Mob {

	{
		生命 = 最大生命 = Dungeon.isChallenged(Challenges.STRONGER_BOSSES) ? 120 : 100;
		经验 = 10;
		defenseSkill = 8;
		spriteClass = GooSprite.class;

		properties.add(Property.BOSS);
		properties.add(Property.DEMONIC);
		properties.add(Property.ACIDIC);
	}

	private int pumpedUp = 0;
	private int healInc = 1;

	@Override
	public int 最小攻击() {
		int min = 1;
		int max = (生命 *2 <= 最大生命) ? 12 : 8;
		if (pumpedUp > 0) {
			pumpedUp = 0;
			if (enemy == Dungeon.hero) {
				Statistics.qualifiedForBossChallengeBadge = false;
				Statistics.bossScores[0] -= 100;
			}
			return min*3;
		} else {
			return min;
		}
	}
	@Override
	public int 最大攻击() {
		int min = 1;
		int max = (生命 *2 <= 最大生命) ? 12 : 8;
		if (pumpedUp > 0) {
			pumpedUp = 0;
			if (enemy == Dungeon.hero) {
				Statistics.qualifiedForBossChallengeBadge = false;
				Statistics.bossScores[0] -= 100;
			}
			return max*3;
		} else {
			return max;
		}
	}

	@Override
	public int 最大命中(Char target ) {
		int attack = 10;
		if (生命 *2 <= 最大生命) attack = 15;
		if (pumpedUp > 0) attack *= 2;
		return attack;
	}

	@Override
	public int 最大闪避(Char enemy) {
		return (int)(super.最大闪避(enemy) * ((生命 *2 <= 最大生命)? 1.5 : 1));
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+2;
	}

	@Override
	public boolean act() {

		if (state != HUNTING && pumpedUp > 0){
			pumpedUp = 0;
			sprite.idle();
		}

		if (!flying && Dungeon.level.water[pos] && 生命 < 最大生命) {
			回血(healInc);
			Statistics.qualifiedForBossChallengeBadge = false;

			LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
			if (lock != null){
				if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES))   lock.removeTime(healInc);
				else                                                    lock.removeTime(healInc*1.5f);
			}

			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES) && healInc < 3) {
				healInc++;
			}
			if (生命 *2 > 最大生命) {
				BossHealthBar.bleed(false);
				((GooSprite)sprite).spray(false);
				生命 = Math.min(生命, 最大生命);
			}
		} else {
			healInc = 1;
		}
		
		if (state != SLEEPING){
			Dungeon.level.seal();
		}

		return super.act();
	}

	@Override
	protected boolean canAttack( Char enemy ) {
		if (pumpedUp > 0){
			//we check both from and to in this case as projectile logic isn't always symmetrical.
			//this helps trim out BS edge-cases
			return Dungeon.level.distance(enemy.pos, pos) <= 2
						&& new Ballistica( pos, enemy.pos, Ballistica.STOP_TARGET | Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID).collisionPos == enemy.pos
						&& new Ballistica( enemy.pos, pos, Ballistica.STOP_TARGET | Ballistica.STOP_SOLID | Ballistica.IGNORE_SOFT_SOLID).collisionPos == pos;
		} else {
			return super.canAttack(enemy);
		}
	}

	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		if (Random.Int( 3 ) == 0) {
			Buff.施加( enemy, Ooze.class ).set( Ooze.DURATION );
			enemy.sprite.burst( 0x000000, 5 );
		}

		if (pumpedUp > 0) {
			PixelScene.shake( 3, 0.2f );
		}

		return damage;
	}

	@Override
	public void updateSpriteState() {
		super.updateSpriteState();

		if (pumpedUp > 0){
			((GooSprite)sprite).pumpUp( pumpedUp );
		}
	}

	@Override
	protected boolean doAttack( Char enemy ) {
		if (pumpedUp == 1) {
			pumpedUp++;
			((GooSprite)sprite).pumpUp( pumpedUp );

			spend(攻击延迟());

			return true;
		} else if (pumpedUp >= 2 || Random.Int( (生命 *2 <= 最大生命) ? 2 : 5 ) > 0) {

			boolean visible = Dungeon.level.heroFOV[pos];

			if (visible) {
				if (pumpedUp >= 2) {
					((GooSprite) sprite).pumpAttack();
				} else {
					sprite.attack(enemy.pos);
				}
			} else {
				if (pumpedUp >= 2){
					((GooSprite)sprite).triggerEmitters();
				}
				attack( enemy );
				Invisibility.dispel(this);
				spend(攻击延迟());
			}

			return !visible;

		} else {

			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES)){
				pumpedUp += 2;
				//don't want to overly punish players with slow move or attack speed
				spend(GameMath.gate(攻击延迟(),(int)Math.ceil(enemy.cooldown()),3*攻击延迟()));
			} else {
				pumpedUp++;
				spend(攻击延迟());
			}

			((GooSprite)sprite).pumpUp( pumpedUp );

			if (Dungeon.level.heroFOV[pos]) {
				sprite.showStatus( CharSprite.WARNING, Messages.get(this, "!!!") );
				GLog.n( Messages.get(this, "pumpup") );
			}

			return true;
		}
	}

	@Override
	public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {
		boolean result = super.attack( enemy, dmgMulti, dmgBonus, accMulti );
		if (pumpedUp > 0) {
			pumpedUp = 0;
			if (enemy == Dungeon.hero) {
				Statistics.qualifiedForBossChallengeBadge = false;
				Statistics.bossScores[0] -= 100;
			}
		}
		return result;
	}

	@Override
	protected boolean getCloser( int target ) {
		if (pumpedUp != 0) {
			pumpedUp = 0;
			sprite.idle();
		}
		return super.getCloser( target );
	}

	@Override
	protected boolean getFurther(int target) {
		if (pumpedUp != 0) {
			pumpedUp = 0;
			sprite.idle();
		}
		return super.getFurther( target );
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		if (!BossHealthBar.isAssigned()){
			BossHealthBar.assignBoss( this );
			Dungeon.level.seal();
		}
		if(src instanceof 燃烧){
			dmg+=2;
		}

		boolean bleeding = (生命 *2 <= 最大生命);
		super.受伤时(dmg, src);
		if ((生命 *2 <= 最大生命) && !bleeding){
			BossHealthBar.bleed(true);
			sprite.showStatus(CharSprite.WARNING, Messages.get(this, "enraged"));
			((GooSprite)sprite).spray(true);
			yell(Messages.get(this, "gluuurp"));
		}
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null && !免疫(src.getClass()) && !是无敌(src.getClass())){
			if (Dungeon.isChallenged(Challenges.STRONGER_BOSSES))   lock.addTime(dmg);
			else                                                    lock.addTime(dmg*1.5f);
		}
	}

	@Override
	public void 死亡时(Object cause ) {
		
		Sample.INSTANCE.play(Assets.Sounds.史莱姆);
		super.死亡时( cause );
		
		Dungeon.level.unseal();
		
		GameScene.bossSlain();
		new 骷髅钥匙( Dungeon.depth ).doPickUp();
		
		//60% chance of 2 blobs, 30% chance of 3, 10% chance for 4. Average of 2.5
		int blobs = Random.chances(new float[]{0, 0, 6, 3, 1});
		for (int i = 0; i < blobs; i++){
			Dungeon.level.dropRandomCell( new GooBlob(), pos);
		}
		
		Badges.validateBossSlain();
		if (Statistics.qualifiedForBossChallengeBadge){
			Badges.validateBossChallengeCompleted();
		}
		Statistics.bossScores[0] += 1000;
		
		yell( Messages.get(this, "defeated") );
	}
	
	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
			Dungeon.level.seal();
			yell(Messages.get(this, "notice"));
			for (Char ch : Actor.chars()){
				if (ch instanceof DriedRose.GhostHero){
					((DriedRose.GhostHero) ch).sayBoss();
				}
			}
		}
	}

	private final String PUMPEDUP = "pumpedup";
	private final String HEALINC = "healinc";

	@Override
	public void storeInBundle( Bundle bundle ) {

		super.storeInBundle( bundle );

		bundle.put( PUMPEDUP , pumpedUp );
		bundle.put( HEALINC, healInc );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {

		super.restoreFromBundle( bundle );

		pumpedUp = bundle.getInt( PUMPEDUP );
		if (state != SLEEPING) BossHealthBar.assignBoss(this);
		if ((生命 *2 <= 最大生命)) BossHealthBar.bleed(true);

		healInc = bundle.getInt(HEALINC);
	}
	
}
