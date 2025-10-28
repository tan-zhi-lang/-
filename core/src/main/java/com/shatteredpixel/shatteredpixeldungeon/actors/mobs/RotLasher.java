

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RotLasherSprite;

public class RotLasher extends Mob {

	{
		spriteClass = RotLasherSprite.class;

		生命 = 最大生命 = 80;
		defenseSkill = 0;

		经验 = 1;

		loot = Generator.Category.SEED;
		lootChance = 0.75f;

		state = WANDERING = new Waiting();
		viewDistance = 1;

		properties.add(Property.IMMOVABLE);
		properties.add(Property.MINIBOSS);
	}

	@Override
	protected boolean act() {
		if (生命 < 最大生命 && (enemy == null || !Dungeon.level.adjacent(pos, enemy.pos))) {
			sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(Math.min(5, 最大生命 - 生命)), FloatingText.HEALING);
			生命 = Math.min(最大生命, 生命 + 5);
		}
		return super.act();
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		if (src instanceof 燃烧) {
			destroy();
			sprite.die();
		} else {
			super.受伤时(dmg, src);
		}
	}

	@Override
	public boolean attack(Char enemy, float dmgMulti, float dmgBonus, float accMulti) {
		if (enemy == Dungeon.hero){
			Statistics.questScores[1] -= 100;
		}
		return super.attack(enemy, dmgMulti, dmgBonus, accMulti);
	}

	@Override
	public int 攻击时(Char enemy, int damage) {
		damage = super.攻击时( enemy, damage );
		Buff.施加( enemy, Cripple.class, 2f );
		return super.攻击时(enemy, damage);
	}

	@Override
	public boolean reset() {
		return true;
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
	public int 最小攻击() {
		return 10;
	}
	@Override
	public int 最大攻击() {
		return 20;
	}

	@Override
	public int 最大命中(Char target ) {
		return 25;
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+8;
	}
	
	{
		immunities.add( ToxicGas.class );
	}

	private class Waiting extends Mob.Wandering{

		@Override
		protected boolean noticeEnemy() {
			spend(TICK);
			return super.noticeEnemy();
		}
	}
}
