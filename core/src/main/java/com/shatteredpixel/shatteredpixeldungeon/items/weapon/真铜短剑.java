

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.潜行;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 真铜短剑 extends Weapon{

	{
		image = 物品表.真铜短剑;
		hitSound = Assets.Sounds.HIT_SLASH;


		伤害=0.75f;
		技能=new 潜行();

		伏击=0.67f;

		tier = 5;
		遗产= false;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender,Poison.class).set(Math.round(damage*0.35f));
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender, Poison.class).set(Math.round(damage*0.35f));
		return super.投掷攻击时( attacker, defender, damage );
	}

}
