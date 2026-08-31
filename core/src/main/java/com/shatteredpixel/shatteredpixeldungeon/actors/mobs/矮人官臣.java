package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.UndeadSprite;

public class 矮人官臣 extends Skeleton {
		{
			spriteClass = UndeadSprite.class;
			生命 = 最大生命 = 100;
			defenseSkill = 25;
			经验 = 11;
			最大等级 = 21;
			爆炸min=10;
			爆炸max=20;
		}
	@Override
	public float 最小攻击() {
		return 22;
	}
	@Override
	public float 最大攻击() {
		return 28;
	}

	@Override
	public int 最大命中(Char target) {
		return 28;
	}

	@Override
	public float 最大防御() {
		return super.最大防御()+10;
	}

}