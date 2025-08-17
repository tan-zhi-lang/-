

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.utils.Random;

public class Bat extends Mob {

	{
		spriteClass = BatSprite.class;
		
		生命 = 最大生命 = 30;
		defenseSkill = 15;
		baseSpeed = 2f;
		
		经验 = 7;
		最大等级 = 15;
		
		flying = true;
		
		loot = 治疗药剂.class;
		lootChance = 0.1667f; //by default, see lootChance()
	}
	
	@Override
	public int 攻击() {
		return Random.NormalIntRange( 5, 18 );
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 16;
	}
	
	@Override
	public int 防御() {
		return super.防御() + Random.NormalIntRange(0, 4);
	}

	@Override
	public void 死亡时(Object cause) {
		flying = false;
		super.死亡时(cause);
	}

	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		int reg = Math.min( damage - 4, 最大生命 - 生命);
		
		if (reg > 0) {
			生命 += reg;
			sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(reg), FloatingText.HEALING);
		}
		
		return damage;
	}
	
	@Override
	public float lootChance(){
		return super.lootChance() * ((7f - Dungeon.LimitedDrops.BAT_HP.count) / 7f);
	}
	
	@Override
	public Item createLoot(){
		Dungeon.LimitedDrops.BAT_HP.count++;
		return super.createLoot();
	}
	
}
