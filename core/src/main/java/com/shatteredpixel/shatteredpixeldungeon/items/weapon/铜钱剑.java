

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铜钱剑 extends Weapon{

	{
		image = 物品表.铜钱剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 1;
		延迟= 0.85f;
		伤害= 0.68f;
		伏击=0.75f;
		
		特别= true;
		遗产= false;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		
		if(defender.恶魔亡灵()&&attacker instanceof Hero hero&&hero.heroClass(HeroClass.道士)){
			damage=Math.round(damage*1.75f);
		}
		return super.攻击时(attacker,defender,damage);
	}

}
