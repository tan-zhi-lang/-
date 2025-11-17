

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
		间隔= 0.8f;
		伤害= 0.8f;
		伏击=0.75f;
		
		特别= true;
		遗产= false;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage){
		
		if(defender.properties().contains(Char.Property.UNDEAD)&&attacker instanceof Hero hero&&hero.heroClass(HeroClass.道士)){
			damage=Math.round(damage*1.1f);
		}
		return super.攻击时(attacker,defender,damage);
	}

}
