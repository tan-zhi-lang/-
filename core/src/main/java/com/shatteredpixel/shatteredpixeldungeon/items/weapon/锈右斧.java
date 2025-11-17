

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 锈右斧 extends Weapon{

	{
		image = 物品表.锈右斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		间隔=2;
		伤害=2;
		双手=true;

		tier = 5;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender,Poison.class).set(Math.round(damage*0.25));
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender, Poison.class).set(Math.round(damage*0.25));
		return super.投掷攻击时( attacker, defender, damage );
	}
	
	
}
