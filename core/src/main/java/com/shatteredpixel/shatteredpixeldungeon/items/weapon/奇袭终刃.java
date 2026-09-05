

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 奇袭终刃 extends Weapon{
	{
		image = 物品表.奇袭终刃;
		hitSound = Assets.Sounds.镜刃;

		范围=7;

		特别=true;
		靛色=true;
		tier=5;
	}

	@Override
	public float 伤害(){
		return super.伤害()*2/5f;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		if(defender!=null){
			defender.受伤时(defender.最大生命((Badges.isUnlocked(Badges.Badge.VICTORY)?0.2f:0.15f)));
		}
		return super.攻击时(attacker,defender,damage);
	}
}
