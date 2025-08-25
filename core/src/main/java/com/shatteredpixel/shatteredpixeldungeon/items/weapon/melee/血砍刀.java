

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RevealedArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 血砍刀 extends MeleeWeapon {
	public static final String AC_SHOOT		= "SHOOT";
	
	{
		image = 物品表.血砍刀;
		hitSound = Assets.Sounds.HIT_STAB;
		hitSoundPitch = 1.2f;

		tier = 1;

		bones = false;

		defaultAction = AC_SHOOT;
		usesTargeting = true;
		红色 = true;
	}
	@Override
	public int 最小攻击(int lvl) {
		return  tier +  //base
				lvl;    //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  2+3*(tier+1) +    //8 base, down from 10
				lvl*(tier+1)/2*3;   //scaling unchanged
	}
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		damage = super.攻击时(attacker,defender,damage);
		Buff.施加( defender, 流血.class ).set( Math.round(augment.damageFactor(Random.NormalIntRange(最小攻击(), 最大攻击()))/3f) );
		return damage;
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*1.34f);
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHOOT);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_SHOOT)&&isEquipped(hero)) {

			curUser = hero;
			curItem = this;
			GameScene.selectCell( shooter );

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
			return Messages.get(血砍刀.class, "prompt");
		}
	};
	public 血砍刀.Spirit knockArrow(){
		return new 血砍刀.Spirit();
	}
	private int targetPos;
	public class Spirit extends MissileWeapon {

		{
			image = 物品表.血砍刀;

			hitSound = Assets.Sounds.HIT_SLASH;

			setID = 0;
		}

		@Override
		public int defaultQuantity() {
			return 1;
		}

		@Override
		public int damageRoll(Char owner) {
			if(Dungeon.炼狱(炼狱设置.诅咒投掷)){
				return 0;
			}
			return 血砍刀.this.damageRoll(owner);
		}

		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 血砍刀.this.hasEnchant(type, owner);
		}

		@Override
		public int 攻击时(Char attacker, Char defender, int damage) {
			return 血砍刀.this.攻击时(attacker, defender, damage);
		}

		@Override
		public float delayFactor(Char user) {
			return 血砍刀.this.delayFactor(user);
		}

		@Override
		public int 力量(int lvl) {
			return 血砍刀.this.力量();
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {
				Dungeon.level.pressCell( cell );
			}
			Char enemy = Actor.findChar( cell );
			if (enemy == null || enemy == curUser) {
				parent = null;
				//metamorphed seer shot logic
				if (curUser.有天赋(Talent.SEER_SHOT)
						&& curUser.heroClass != HeroClass.HUNTRESS
						&& curUser.buff(Talent.SeerShotCooldown.class) == null){
					if (Actor.findChar(cell) == null) {
						RevealedArea a = Buff.施加(curUser, RevealedArea.class, 25-curUser.天赋点数(Talent.SEER_SHOT,5));
						a.depth = Dungeon.depth;
						a.pos = cell;
						Buff.施加(curUser, Talent.SeerShotCooldown.class,  curUser.天赋点数(Talent.SEER_SHOT,10));
					}
				}
				if (!spawnedForEffect) {
					Dungeon.hero.belongings.weapon=null;
					Dungeon.hero.belongings.backpack.items.remove(血砍刀.this);
					updateQuickslot();
					Heap heap = Dungeon.level.drop( 血砍刀.this, cell );
					if (!heap.isEmpty()) {
						heap.sprite.drop( cell );
					}
				}
			} else {
				if (!curUser.shoot( enemy, this )) {

				} else {

				}
			}
		}

		protected void rangedHit( Char enemy, int cell ){
			Dungeon.level.drop( this, cell ).sprite.drop();
		}

		protected void rangedMiss( int cell ) {
			parent = null;
			if (!spawnedForEffect) super.onThrow(cell);
		}
		@Override
		public void cast(final Hero user, final int dst) {
			final int cell = throwPos( user, dst );
			血砍刀.this.targetPos = cell;
				super.cast(user, dst);
		}
	}
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	public boolean useTargeting(){
		return false;
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		sneakAbility(hero, target, 5, 2+ 强化等级(), this);
	}

	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", 2+ 强化等级());
		} else {
			return Messages.get(this, "typical_ability_desc", 2);
		}
	}

	@Override
	public String upgradeAbilityStat(int level) {
		return Integer.toString(2+level);
	}

	public static void sneakAbility(Hero hero, Integer target, int maxDist, int invisTurns, MeleeWeapon wep){
		if (target == null) {
			return;
		}

		PathFinder.buildDistanceMap(Dungeon.hero.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null), maxDist);
		if (PathFinder.distance[target] == Integer.MAX_VALUE || !Dungeon.level.heroFOV[target] || hero.rooted) {
			GLog.w(Messages.get(wep, "ability_target_range"));
			if (Dungeon.hero.rooted) PixelScene.shake( 1, 1f );
			return;
		}

		if (Actor.findChar(target) != null) {
			GLog.w(Messages.get(wep, "ability_occupied"));
			return;
		}

		wep.beforeAbilityUsed(hero, null);
		Buff.延长(hero, Invisibility.class, invisTurns-1); //1 fewer turns as ability is instant

		Dungeon.hero.sprite.turnTo( Dungeon.hero.pos, target);
		Dungeon.hero.pos = target;
		Dungeon.level.occupyCell(Dungeon.hero);
		Dungeon.observe();
		GameScene.updateFog();
		Dungeon.hero.checkVisibleMobs();

		Dungeon.hero.sprite.place( Dungeon.hero.pos );
		CellEmitter.get( Dungeon.hero.pos ).burst( Speck.factory( Speck.WOOL ), 6 );
		Sample.INSTANCE.play( Assets.Sounds.PUFF );

		hero.next();
		wep.afterAbilityUsed(hero);
	}
}
