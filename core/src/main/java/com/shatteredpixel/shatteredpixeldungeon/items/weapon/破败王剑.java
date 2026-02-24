

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.嗜血;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 破败王剑 extends Weapon{
	{
		image = 物品表.破败王剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier=5;
		延迟=0.75f;
		吸血=0.075f;
		伤害=0.75f;
		技能=new 嗜血();
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null&&attacker instanceof Hero hero&&hero.符文("升级破败王剑"))
			damage+=defender.最大生命(0.035f);
		if(defender!=null)
		damage+=defender.生命(0.15f);
		return super.攻击时( attacker, defender, damage );
	}

}
