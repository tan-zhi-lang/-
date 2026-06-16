package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.水蛭动画;

public class 水蛭 extends Mob {

		{
			spriteClass = 水蛭动画.class;

			生命 = 最大生命 = 6;
			defenseSkill = 2;
			viewDistance = 6;
			baseSpeed=5/6f;
			经验 = 2;
			最大等级 = 7;
		}

		@Override
		public int 最大命中(Char target) {
			return 16;
		}

		@Override
		public float 最小攻击() {
			return 1;
		}

		@Override
		public float 最大攻击() {
			return 2;
		}

	@Override
	public float 吸血(){
		return super.吸血()+1;
	}

	@Override
	protected boolean act(){
			if(在水中())回百分比血(0.15f);
		return super.act();
	}
}