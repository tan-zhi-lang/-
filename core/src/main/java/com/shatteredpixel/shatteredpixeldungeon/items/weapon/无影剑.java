

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 无影剑 extends Weapon{
	{
		image = 物品表.无影剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		延迟=1.25f;
		伤害=0.85f;
		tier=5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {

		if(attacker instanceof Hero hero){
			if(hero.海克斯.get("升级无影剑"))
				damage*=1.2f;
		}
		damage*=1.3f;
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		if(attacker instanceof Hero hero){
			if(hero.海克斯.get("升级无影剑"))
				damage*=1.2f;
		}
		damage*=1.3f;
		return super.投掷攻击时( attacker, defender, damage );
	}
}
