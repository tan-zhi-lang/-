

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 黑暗剑 extends Weapon{
	{
		image = 物品表.黑暗剑;
		hitSound = Assets.Sounds.CURSED;
		特别=true;
		黑色=true;
		黑光=true;
		伤害=0.8f;
		tier=5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		if(attacker!=null){
			damage+=35f/attacker.视野范围();
		}
		if(Dungeon.level!=null){
			damage*=1+Dungeon.区域()/3f;
		}else{
			damage*=1+1/3f;
		}
		return super.攻击时(attacker,defender,damage);
	}
}
