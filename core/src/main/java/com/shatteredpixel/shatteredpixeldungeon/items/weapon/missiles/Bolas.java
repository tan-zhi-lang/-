

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Bolas extends MissileWeapon {
	
	{
		image = 物品表.BOLAS;
		
		命中=0.9f;
		间隔=1.1f;
		伤害=1.2f;
		tier = 3;
		baseUses = 5;
	}
	
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		Buff.延长( defender, Cripple.class, Cripple.DURATION/2 );
		return super.攻击时( attacker, defender, damage );
	}
}
