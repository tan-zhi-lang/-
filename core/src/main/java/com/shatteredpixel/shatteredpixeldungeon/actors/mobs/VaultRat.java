package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class VaultRat extends VaultMob {

	{
		spriteClass = RatSprite.class;

		生命 = 最大生命 = 8;
		defenseSkill = 2;

		最大等级 = -2;
	}

	@Override
	public float 最小攻击() {
		return 0;
	}
	@Override
	public float 最大攻击() {
		return 0;
	}

	@Override
	public int 最大命中(Char target) {
		return 8;
	}

	@Override
	public float 最大防御() {
		return super.最大防御() + Random.NormalIntRange(0, 1);
	}

	@Override
	public String name() {
		return Messages.get(Rat.class, "name");
	}

	@Override
	public String description() {
		return Messages.get(Rat.class, "desc") + "\n\n" + super.description();
	}
}
