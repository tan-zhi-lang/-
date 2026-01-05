

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ScorpioSprite;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

public class Scorpio extends Mob {
	
	{
		spriteClass = ScorpioSprite.class;
		
		生命 = 最大生命 = 110;
		defenseSkill = 24;
		viewDistance = 6;
		
		经验 = 14;
		最大等级 = 27;
		
		loot = Generator.Category.POTION;
		lootChance = 0.5f;

		properties.add(Property.DEMONIC);
	}
	
	@Override
	public float 移速() {
		if(enemy!=null&&Dungeon.level.distance( enemy.pos, pos )<=2)
			return super.移速()*5/6f;
		else
			return super.移速()*9/6f;
	}
	@Override
	public int 最小攻击() {
		return 30;
	}
	@Override
	public int 最大攻击() {
		return 40;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 36;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+16;
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return !Dungeon.level.adjacent( pos, enemy.pos )
				&& (super.canAttack(enemy) || new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == enemy.pos);
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.延长( enemy, Cripple.class, Cripple.DURATION );
		}
		
		return damage;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		if (state == HUNTING) {
			return enemySeen && getFurther( target );
		} else {
			return super.getCloser( target );
		}
	}
	
	@Override
	public void aggro(Char ch) {
		//cannot be aggroed to something it can't see
		//skip this check if FOV isn't initialized
		if (ch == null || fieldOfView == null
				|| fieldOfView.length != Dungeon.level.length() || fieldOfView[ch.pos]) {
			super.aggro(ch);
		}
	}

	@Override
	public Item createLoot() {
		Class<?extends Potion> loot;
		do{
			loot = (Class<? extends Potion>) Random.oneOf(Generator.Category.POTION.classes);
		} while (loot == 治疗药剂.class || loot == 力量药剂.class);

		return Reflection.newInstance(loot);
	}
	
}
