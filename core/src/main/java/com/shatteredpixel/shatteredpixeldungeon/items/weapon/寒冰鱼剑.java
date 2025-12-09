

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 寒冰鱼剑 extends Weapon {

	{
		image = 物品表.寒冰鱼剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.35f;
		伤害=1.15f;
		tier = 5;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender.第一次防御){
			算法.修复效果(
					()->{
						Buff.施加(defender,Frost.class,2f);
					}
						 );
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		if(defender.第一次防御){
			算法.修复效果(
					()->{
						Buff.施加(defender,Frost.class,2f);
					}
						 );
		}
		return super.投掷攻击时( attacker, defender, damage );
	}
}
