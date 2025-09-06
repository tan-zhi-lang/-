

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingStone extends MissileWeapon {
	
	{
		image = 物品表.THROWING_STONE;
		hitSoundPitch = 1.1f;
		
		bones = false;
		
		命中=0.9f;
		间隔=1.1f;
		伤害=1.2f;
		tier = 1;
		baseUses = 5;
		sticky = false;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		Buff.延长(defender,Vertigo.class,1);
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()/2f); //half normal value
	}
}
