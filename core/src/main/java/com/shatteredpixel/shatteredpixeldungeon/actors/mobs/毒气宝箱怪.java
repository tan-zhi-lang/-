

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class 毒气宝箱怪 extends Mimic {

	{
		spriteClass = MimicSprite.毒气.class;
		生命 = 最大生命 =Dungeon.层数(10);
		loot = Random.oneOf(Generator.randomArtifact(),
							Generator.randomWand(),Generator.randomRing());
	}

	@Override
	public int 最大闪避(Char enemy ) {
		return Char.INFINITE;
	}

	@Override
	public void 受伤时(int dmg, Object src ) {
		if(src instanceof ToxicGas){
			dmg=最大生命;
		}else{
			dmg=0;
		}
		super.受伤时(dmg,src);
	}
	@Override
	public boolean interact(Char c) {
		GLog.p("你打不开这个宝箱！");
		return false;
	}
}
