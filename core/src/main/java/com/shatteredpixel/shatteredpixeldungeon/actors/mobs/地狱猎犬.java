

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.地狱猎犬动画;
import com.watabou.noosa.audio.Sample;

public class 地狱猎犬 extends Mob {
	
	{
		spriteClass = 地狱猎犬动画.class;
		
		生命 = 最大生命 = 70;
		defenseSkill = 25;
		baseSpeed=2f;
		经验 = 12;
		最大等级 = 25;

		properties.add(Property.动物);
	}
	
	@Override
	public float 最小攻击() {
		return 5;
	}

	@Override
	public float 最大攻击() {
		return 25;
	}

	@Override
	public float 攻击时(Char enemy,float damage){
		Sample.INSTANCE.play(Assets.Sounds.狗叫);
		return super.攻击时(enemy,damage);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 30;
	}
	
	@Override
	public float 最大防御() {
		return super.最大防御()+8;
	}
}
