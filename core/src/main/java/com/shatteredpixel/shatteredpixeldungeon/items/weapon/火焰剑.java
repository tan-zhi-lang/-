

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 火焰剑 extends Weapon{
	{
		image = 物品表.火焰剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=0.8f;
		tier=5;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender.nobuff(火毒.class))
			Buff.施加(defender,火毒.class).reignite(defender);
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		if(defender.nobuff(火毒.class))
			Buff.施加(defender,火毒.class).reignite(defender);
		return super.投掷攻击时( attacker, defender, damage );
	}
}
