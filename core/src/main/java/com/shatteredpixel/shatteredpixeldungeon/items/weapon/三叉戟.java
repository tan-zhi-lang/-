

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 三叉戟 extends Weapon{
	
	{
		image = 物品表.TRIDENT;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		投矛=true;
		延迟= 1.34f;
		伤害= 1.34f;
		范围 = 2;
		
		tier = 4;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender instanceof Piranha){
			damage+=defender.生命(0.5f);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		if(defender instanceof Piranha){
			damage+=defender.生命(0.5f);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}
}
