

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 腥红散华 extends Weapon{
	
	{
		image = 物品表.腥红散华;
		hitSound = Assets.Sounds.HIT_ARROW;
		范围=5;
		红色 = true;
		特别= true;

		伤害=0.2f;
		吸血=0.07f;
		命中=0.65f;
		tier = 5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null&&attacker instanceof Hero hero){
			hero.风刃(hero,defender.pos,45,4,new 吸血刀());
		}
		return super.攻击时( attacker, defender, damage );
	}
}
