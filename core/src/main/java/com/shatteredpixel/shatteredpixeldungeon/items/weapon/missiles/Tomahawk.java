

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class Tomahawk extends MissileWeapon {

	{
		image = 物品表.TOMAHAWK;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 0.9f;

		tier = 4;
		baseUses = 5;
	}

	@Override
	public int min(int lvl) {
		return  Math.round(1.5f * tier) +   //6 base, down from 8
				lvl;                        //scaling unchanged
	}
	
	@Override
	public int max(int lvl) {
		return  Math.round(4f * tier) +     //16 base, down from 20
				(tier-1)*lvl;               //3 scaling, down from 4
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		//33% damage roll as bleed, but ignores armor and str bonus
		Buff.施加( defender, Bleeding.class ).set( Math.round(augment.damageFactor(Random.NormalIntRange(min(), max()))/3f) );
		return super.攻击时( attacker, defender, damage );
	}
}
