

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 黑暗剑 extends Weapon{
	{
		image = 物品表.黑暗剑;
		hitSound = Assets.Sounds.攻击砍;
		特别=true;
		黑色=true;
		黑光=true;
		伤害=0.8f;
		tier=5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage){

		if(Dungeon.level!=null){
			damage*=Dungeon.区域()/2f;
		}else{
			damage*=1/2f;
		}
		return super.攻击时(attacker,defender,damage);
	}
}
