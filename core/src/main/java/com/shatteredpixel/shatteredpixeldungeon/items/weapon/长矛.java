

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.刺击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 长矛 extends Weapon{

	{
		image = 物品表.SPEAR;
		hitSound = Assets.Sounds.HIT_STAB;
		
		技能=new 刺击();
		投矛=true;
		tier = 1;
		延迟= 1.35f;
		伤害= 1.35f;
		命中=0.85f;
		连招范围=2;
		范围 = 2;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender instanceof Piranha){
			damage+=defender.生命(0.5f);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		if(defender instanceof Piranha){
			damage+=defender.生命(0.5f);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}

}
