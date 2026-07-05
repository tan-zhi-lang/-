

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铜钱剑 extends Weapon{

	{
		image = 物品表.铜钱剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		tier = 1;
		伤害= 0.6f;
		
		特别= true;

	}

	@Override
	public int 强化等级(){
		int level = super.强化等级();
		if(Dungeon.hero()) level+=Dungeon.hero.等级(0.2f)+Dungeon.hero.魔力(0.1f);
		return level;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		
		if(defender!=null&&defender.恶魔亡灵()&&attacker instanceof Hero hero&&hero.heroClass(HeroClass.道士)){

			if(defender.isAlive())defender.受伤时(damage*0.75f);
		}
		return super.攻击时(attacker,defender,damage);
	}

}
