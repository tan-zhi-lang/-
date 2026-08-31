

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.sprites.地狱猎犬动画;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 地狱猎犬 extends Mob {
	
	{
		spriteClass = 地狱猎犬动画.class;
		
		生命 = 最大生命 = 100;
		defenseSkill = 25;
		baseSpeed=2f;
		经验 = 12;
		最大等级 = 25;

		属性表.add(Property.动物);
	}

	@Override
	public float 攻击延迟(){
		return super.攻击延迟()/2f;
	}

	@Override
	public float 最小攻击() {
		return 17;
	}

	@Override
	public float 最大攻击() {
		return 27;
	}

	@Override
	protected boolean act(){
		Buff.施加(this,灵焰.class).extend(2);
		return super.act();
	}

	@Override
	public void 受伤时(float dmg,Object 来源){
		if(来源 instanceof 灵焰)dmg=0;
		super.受伤时(dmg,来源);
	}

	@Override
	public float 攻击时(Char enemy,float damage){
		if (damage > 0&&Random.Int(2)==0) {
			Buff.施加(this,灵焰.class).reignite(this);
		}
		Sample.INSTANCE.play(Assets.Sounds.狗叫);
		return super.攻击时(enemy,damage);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 34;
	}
	
	@Override
	public float 最大防御() {
		return super.最大防御()+10;
	}
}
