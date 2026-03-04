package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.石虱动画;

public class 石虱 extends Mob {

		{
			spriteClass = 石虱动画.class;

			生命 = 最大生命 = 25;
			defenseSkill = 45;
			baseSpeed=2;

			经验 = 8;
			最大等级 = 16;
		}

	@Override
	public float 攻击延迟(){
		return super.攻击延迟()*0.5f;
	}

	@Override
		public int 最大命中(Char target) {
			return 16;
		}

		@Override
		public float 最小攻击() {
			return 4;
		}

		@Override
		public float 最大攻击() {
			return 10;
		}

	@Override
	public float 最大防御(){
		return super.最大防御()+2;
	}
}