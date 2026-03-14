package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.sprites.蠕虫动画;

public class 蠕虫 extends Mob {

		{
			spriteClass = 蠕虫动画.class;

			生命 = 最大生命 = 45;
			defenseSkill = 13;
			viewDistance = 6;
			baseSpeed=5/6f;

			经验 = 7;
			最大等级 = 15;
		}

		@Override
		public int 最大命中(Char target) {
			return 22;
		}

		@Override
		public float 最小攻击() {
			return 10;
		}

		@Override
		public float 最大攻击() {
			return 25;
		}

	@Override
	public void 受伤时(float dmg,Object src){
		super.受伤时(dmg,src);
		if(isAlive()&&src instanceof Char c){
			传送卷轴.周身瞬移(this,c.pos);
			Dungeon.level.occupyCell(this);
		}
	}
	@Override
	public float 最小防御(){
		return super.最小防御()+2;
	}
	@Override
	public float 最大防御(){
		return super.最大防御()+6;
	}
}