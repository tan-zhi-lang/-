

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺击;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 裂天剑 extends Weapon{
	{
		image = 物品表.裂天剑;
		hitSound = Assets.Sounds.攻击砍;
		特别=true;
		蓝色=true;
		伤害=0.8f;
		范围=3;
		技能=new 刺击();
		tier=5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {

		if(defender!=null){
			Ballistica b=new Ballistica(attacker.pos,defender.pos,Ballistica.IGNORE_SOFT_SOLID);

			for (int i : b.subPath(2, b.dist)){
				Char ch = Actor.findChar(i);
				if (ch != null&&ch.isAlive())
					ch.受伤时(damage*0.4f);
			}
		}
		return super.攻击时( attacker, defender, damage );
	}
}
