

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.小吞噬怪动画;
import com.watabou.utils.Bundle;

public class 小吞噬怪 extends Mob {
	
	{
		spriteClass = 小吞噬怪动画.class;
		
		生命 = 最大生命 = 1;
		baseSpeed=3;
	}
	@Override
	public int 最大命中(Char target ) {
		return Char.INFINITE;
	}

	@Override
	public int 最大闪避(Char enemy ) {
		if(攻击>0)
		return Char.INFINITE;
		return 0;
	}

	@Override
	public float 攻击时(Char enemy,float damage){
		damage+=攻击;
		攻击=0;
		return super.攻击时(enemy,damage);
	}

	public float 攻击=0;
	private static final String 攻击x = "攻击";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( 攻击x, 攻击 );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		攻击 = bundle.getFloat(攻击x);
	}
	@Override
	public void 受伤时(float dmg,Object 来源){
		if(攻击>0)dmg=0;
		super.受伤时(dmg,来源);
	}
}
