

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Tomahawk extends MissileWeapon {

	{
		image = 物品表.TOMAHAWK;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;
		
		间隔=1.1f;
		伤害=1.2f;
		tier = 4;
		baseUses = 5;
	}

	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		//33% damage roll as bleed, but ignores armor and str bonus
		Buff.施加( defender, 流血.class ).set( Math.round(damageRoll(attacker)/3f) );
		return super.攻击时( attacker, defender, damage );
	}
}
