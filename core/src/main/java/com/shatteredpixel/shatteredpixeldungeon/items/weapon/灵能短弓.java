

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.神射之戒;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Blindweed;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredpixel.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 灵能短弓 extends Weapon {
	
	public static final String AC_SHOOT		= "SHOOT";
	
	{
		image = 物品表.灵能短弓;
		defaultAction = AC_SHOOT;
		usesTargeting = true;
		tier=1;

		绿色 = true;
		伤害=0.75f;
		延迟=1.5f;
		特别= true;
		遗产= false;
	}
	@Override
	public String defaultAction() {
		return defaultAction;
	}
	
	public boolean sniperSpecial = false;
	public float sniperSpecialBonusDamage = 0f;
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHOOT);
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action) {
		
		super.execute(hero, action);
		
		if (action.equals(AC_SHOOT)) {
			curUser = hero;
			curItem = this;
			GameScene.selectCell( shooter );
		}
	}

	private static Class[] harmfulPlants = new Class[]{
			Blindweed.class, Firebloom.class, Icecap.class, Sorrowmoss.class,  Stormvine.class
	};

	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {

		return super.攻击时(attacker, defender, damage);
	}

	public float 最小弓箭攻击() {
		return 最小弓箭攻击(强化等级());
	}
	public float 最小弓箭攻击(int lvl) {
		float dmg =1+ lvl/5f
				   +神射之戒.levelDamageBonus(Dungeon.hero)
				   +(curseInfusionBonus ? 1 + lvl/5f : 0);
		if(Dungeon.hero.符文("升级灵能短弓"))dmg*=3.5;
		dmg*=1.5f;
		dmg*=1+Dungeon.hero.天赋点数(Talent.弓箭强化,0.2f);
		return Math.max(0, dmg);
	}
	
	public float 最大弓箭攻击() {
		return 最大弓箭攻击(强化等级());
	}
	public float 最大弓箭攻击(int lvl) {
		float dmg =6f+ lvl/2.5f
				   +神射之戒.levelDamageBonus(Dungeon.hero)*2
				   +(curseInfusionBonus ? 2 + lvl/2.5f : 0);
		if(Dungeon.hero.符文("升级灵能短弓"))dmg*=3.5;
		dmg*=1.5f;
		dmg*=1+Dungeon.hero.天赋点数(Talent.弓箭强化,0.2f);
		return Math.max(0, dmg);
	}
	@Override
	public String desc() {
		return Messages.get(this, "desc",String.format("%.2f",最小弓箭攻击()),String.format("%.2f",最大弓箭攻击()));
	}

	@Override
	public int targetingPos(Hero user, int dst) {
		return knockArrow().targetingPos(user, dst);
	}
	
	private int targetPos;
	
	@Override
	protected float baseDelay(Char owner) {
		float d=1;
		if (sniperSpecial){
			switch (augment){
				case NONE: default:
					return 0f;
				case ACCURACY:
					return 1f;
				case DELAY:
					return 2;
				case DAMAGE:
					return 3;
			}
		} else{
			return super.baseDelay(owner)/d;
		}
	}

	@Override
	public int 强化等级(){

		int level = Dungeon.hero == null ? 0 : Dungeon.hero.等级 /5;
		if (curseInfusionBonus) level += 1 + level/5;
		return level+super.强化等级();
	}

	public SpiritArrow knockArrow(){
		return new SpiritArrow();
	}
	
	public class SpiritArrow extends Weapon {
		
		{
			image = 物品表.SPIRIT_ARROW;

			hitSound = Assets.Sounds.HIT_ARROW;

		}
		@Override
		public Emitter emitter() {
			if (!sniperSpecial){
				Emitter e = new Emitter();
				e.pos(5, 5);
				e.fillTarget = false;
				e.pour(LeafParticle.GENERAL, 0.01f);
				return e;
			} else {
				return super.emitter();
			}
		}
		@Override
		public float 最小投掷攻击(int lvl) {
			return 灵能短弓.this.最小弓箭攻击(lvl);
		}
		
		@Override
		public float 最大投掷攻击(int lvl) {
			return 灵能短弓.this.最大弓箭攻击(lvl);
		
		}
		
		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 灵能短弓.this.hasEnchant(type, owner);
		}
		
		@Override
		public float 投掷攻击时(Char attacker, Char defender, float damage) {
			damage+=attacker.攻击范围();
			damage*=attacker.攻击延迟();

			if(defender!=null)
			if (sniperSpecial){
				damage = Math.round(damage * (1f + sniperSpecialBonusDamage));
				
				switch (augment){
					case NONE:
						break;
					case DELAY:
						damage = Math.round(damage * 0.5f);
						break;
					case ACCURACY:
						damage = Math.round(damage * 1.2f);
						break;
					case DAMAGE:
						//as distance increases so does damage, capping at 3x:
						//1.20x|1.35x|1.52x|1.71x|1.92x|2.16x|2.43x|2.74x|3.00x
						int distance = Dungeon.level.distance(attacker.pos, targetPos) - 1;
						float multiplier = Math.min(3f, 1.2f * (float)Math.pow(1.125f, distance));
						damage = Math.round(damage * multiplier);
						break;
				}
			}

			return 灵能短弓.this.投掷攻击时(attacker, defender, damage);
		}
		
		@Override
		public float delayFactor(Char user) {
			return 灵能短弓.this.delayFactor(user);
		}
		
		@Override
		public float accuracyFactor(Char owner, Char target) {
			if (sniperSpecial && 灵能短弓.this.augment == Augment.ACCURACY){
				return Float.POSITIVE_INFINITY;
			} else {
				return super.accuracyFactor(owner, target);
			}
		}
		
		@Override
		public float 力量(int lvl) {
			return 灵能短弓.this.力量();
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {
				Dungeon.level.pressCellmin( cell );
			}
			Char enemy = Actor.findChar( cell );
			if (enemy == null || enemy == curUser) {
				Splash.at( cell, 0xCC99FFFF, 1 );
			} else {
				if (!curUser.shoot( enemy, this )) {
					Splash.at(cell, 0xCC99FFFF, 1);
				}
				if (sniperSpecial && 灵能短弓.this.augment != Augment.DELAY) sniperSpecial = false;
			}
		}

		@Override
		public void throwSound() {
			Sample.INSTANCE.play( Assets.Sounds.ATK_SPIRITBOW, 1, Random.Float(0.87f, 1.15f) );
		}

		int flurryCount = -1;
		Actor flurryActor = null;

		@Override
		public void cast(final Hero user, final int dst) {
			final int cell = throwPos( user, dst );
			灵能短弓.this.targetPos = cell;
			if (sniperSpecial && 灵能短弓.this.augment == Augment.DELAY){
				if (flurryCount == -1) flurryCount = 3;
				
				final Char enemy = Actor.findChar( cell );
				
				if (enemy == null){
					if (user.buff(Talent.LethalMomentumTracker.class) != null){
						user.buff(Talent.LethalMomentumTracker.class).detach();
						user.next();
					} else {
						user.spendAndNext(castDelay(user, cell));
					}
					sniperSpecial = false;
					flurryCount = -1;

					if (flurryActor != null){
						flurryActor.next();
						flurryActor = null;
					}
					return;
				}

				QuickSlotButton.target(enemy);
				
				user.busy();
				
				throwSound();

				user.sprite.zap(cell);
				((MissileSprite) user.sprite.parent.recycle(MissileSprite.class)).
						reset(user.sprite,
								cell,
								this,
								new Callback() {
									@Override
									public void call() {
										if (enemy.isAlive()) {
											curUser = user;
											onThrow(cell);
										}

										flurryCount--;
										if (flurryCount > 0){
											Actor.add(new Actor() {

												{
													actPriority = VFX_PRIO-1;
												}

												@Override
												protected boolean act() {
													flurryActor = this;
													int target = QuickSlotButton.autoAim(enemy, SpiritArrow.this);
													if (target == -1) target = cell;
													cast(user, target);
													Actor.remove(this);
													return false;
												}
											});
											curUser.next();
										} else {
											if (user.buff(Talent.LethalMomentumTracker.class) != null){
												user.buff(Talent.LethalMomentumTracker.class).detach();
												user.next();
											} else {
												user.spendAndNext(castDelay(user, cell));
											}
											sniperSpecial = false;
											flurryCount = -1;
										}

										if (flurryActor != null){
											flurryActor.next();
											flurryActor = null;
										}
									}
								});
				
			} else {
				super.cast(user, dst);
			}
		}
	}
	
	private CellSelector.Listener shooter = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				knockArrow().cast(curUser, target);
			}
		}
		@Override
		public String prompt() {
			return Messages.get(灵能短弓.class, "prompt");
		}
	};
}
