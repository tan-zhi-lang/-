

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 金铲铲 extends Weapon {

	{
		image = 物品表.金铲铲;
		hitSound = Assets.Sounds.攻击砍;
		伤害=0.7f;
		特别=true;
		黄色=true;
		tier = 5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {

		if(defender!=null)
		if(attacker instanceof Hero hero&&hero.hasbuff(Hunger.class))
			hero.buff(Hunger.class).吃饭(15);

		return super.攻击时( attacker, defender, damage );
	}

}
