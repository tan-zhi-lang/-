

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 菱形刀 extends Weapon{
	{
		image = 物品表.菱形刀;
		hitSound = Assets.Sounds.攻击砍;
		伤害=0.8f;
		tier=5;
		特别=true;
		彩光=true;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null&&defender.第x次防御==1&&defender.亡灵()){
			if(attacker instanceof Hero hero)hero.攻击成长+=0.5f;
		}
		return super.攻击时( attacker, defender, damage );
	}
}
