

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.劈斩;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 锈右斧 extends Weapon{

	{
		image = 物品表.锈右斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟= 1.5f;
		伤害=1.2f;
		双手=true;
		技能=new 劈斩();

		tier = 5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender,Poison.class).set(Math.round(damage*0.2f));
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		if(defender.nobuff(Poison.class))
			Buff.施加(defender, Poison.class).set(Math.round(damage*0.2f));
		return super.投掷攻击时( attacker, defender, damage );
	}
	
	
}
