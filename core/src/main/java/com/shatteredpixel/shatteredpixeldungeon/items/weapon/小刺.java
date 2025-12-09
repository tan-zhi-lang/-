

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.破击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 小刺 extends Weapon{

	{
		image = 物品表.THROWING_SPIKE;
		hitSound = Assets.Sounds.HIT_STAB;
		伤害=0.85f;
		技能=new 破击();
		tier = 1;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		damage+=defender.最大防御();
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		damage+=defender.最大防御();
		return super.投掷攻击时( attacker, defender, damage );
	}

}
