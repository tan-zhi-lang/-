

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class HolyDart extends TippedDart {

	{
		image = 物品表.HOLY_DART;
	}
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {

		//do nothing to the hero when processing charged shot
		if (processingChargedShot && defender == attacker){
			return super.攻击时(attacker, defender, damage);
		}

		if (attacker.alignment == defender.alignment){
			Buff.施加(defender, Bless.class, Math.round(Bless.DURATION));
			return 0;
		}

		if (Char.hasProp(defender, Char.Property.UNDEAD) || Char.hasProp(defender, Char.Property.DEMONIC)){
			defender.sprite.emitter().start( ShadowParticle.UP, 0.05f, 10+ 强化等级() );
			Sample.INSTANCE.play(Assets.Sounds.BURNING);
			defender.受伤时(Random.NormalIntRange(10 + Dungeon.scalingDepth()/3, 20 + Dungeon.scalingDepth()/3), this);
		//also do not bless enemies if processing charged shot
		} else if (!processingChargedShot){
			Buff.施加(defender, Bless.class, Math.round(Bless.DURATION));
		}
		
		return super.攻击时(attacker, defender, damage);
	}
}
