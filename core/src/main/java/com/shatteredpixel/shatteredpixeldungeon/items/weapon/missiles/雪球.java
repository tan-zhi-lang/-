

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SnowParticle;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 雪球 extends MissileWeapon {
	
	{
		image = 物品表.雪球;
		hitSoundPitch = 1.1f;
		
		bones = false;
		蓝色=true;
		
		伤害=1.1f;
		tier = 1;
		baseUses = 1;
		sticky = false;
	}
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		damage = super.攻击时(attacker,defender,damage);
		
		Buff.施加(defender,Chill.class,3f);
		defender.sprite.emitter().burst(SnowParticle.FACTORY,3);
		return damage;
	}

}
