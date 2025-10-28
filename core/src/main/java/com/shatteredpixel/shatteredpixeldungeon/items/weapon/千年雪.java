

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SnowParticle;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 千年雪 extends Weapon{
	
	{
		image = 物品表.千年雪;
		
		遗产= false;
		蓝色=true;
		
		tier = 1;
	}
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		damage = super.攻击时(attacker,defender,damage);
		
		Buff.施加(defender,Chill.class,3f);
		defender.sprite.emitter().burst(SnowParticle.FACTORY,3);
		return damage;
	}

	@Override
	public int 投掷攻击时(Char attacker, Char defender, int damage ) {
		damage = super.投掷攻击时(attacker,defender,damage);
		
		Buff.施加(defender,Chill.class,3f);
		defender.sprite.emitter().burst(SnowParticle.FACTORY,3);
		return damage;
	}

}
