

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Bolas extends MissileWeapon {
	
	{
		image = 物品表.BOLAS;
		hitSound = Assets.Sounds.HIT;
		hitSoundPitch = 1f;
		
		tier = 3;
		baseUses = 5;
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return  3 * tier +                      //9 base, down from 15
				(tier-1)*lvl;                   //2 scaling, down from 3
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		Buff.延长( defender, Cripple.class, Cripple.DURATION );
		return super.攻击时( attacker, defender, damage );
	}
}
