

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;

public class 燧石 extends Weapon{

	{
		image = 物品表.燧石;
		hitSound = Assets.Sounds.锤打;
		延迟=1.5f;
		技能=new 重击();
		特别=true;
		黑色=true;
		tier = 5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		if(defender!=null){

			for (int i : PathFinder.范围3){
				Char c=Actor.findChar(defender.pos+i);
				if(c!=null&c.alignment==Char.Alignment.ENEMY){
					c.受伤时(damage);
				}
			}
		}
		return super.攻击时(attacker,defender,damage);
	}
}
