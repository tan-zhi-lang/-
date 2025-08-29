

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class 血姬 extends MeleeWeapon {
	
	{
		image = 物品表.血姬;

		tier = 1;
		间隔= 0.5f; //2x speed
		伏击=true;
		伏击率=1;
		unique = true;
		bones = false;
	}
	
	@Override
	public int 最小攻击(int lvl) {
		return  1+tier +  //base
				lvl;    //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(1.5f*(tier+1)) +     //5 base, down from 10
				lvl*Math.round(0.3f*(tier+1));  //+1 per level, down from +2
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		damage = super.攻击时(attacker,defender,damage);
		Buff.施加( defender, 流血.class).set(Math.round(augment.damageFactor(Random.NormalIntRange(最小攻击(),最大攻击()))/3f));
		return damage;
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
		sneakAbility(hero, target, 5, 5, this);
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
