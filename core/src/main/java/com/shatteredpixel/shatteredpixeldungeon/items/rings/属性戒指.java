

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Bundle;

public class 属性戒指 extends Ring {
	{
		icon=物品表.Icons.强能;
		buffClass = 属性.class;
	}
	public float 攻击=0;
	public float 防御=0;
	public float 穿甲=0;
	public float 吸血=0;
	public float 暴击=0;
	public float 反击=0;
	public float 命中=0;
	public float 闪避=0;
	public float 生命=0;

	private static final String 攻击x = "攻击";
	private static final String 防御x = "防御";
	private static final String 穿甲x = "穿甲";
	private static final String 吸血x = "吸血";
	private static final String 暴击x = "暴击";
	private static final String 反击x = "反击";
	private static final String 命中x = "命中";
	private static final String 闪避x = "闪避";
	private static final String 生命x = "生命";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(攻击x, 攻击);
		bundle.put(防御x, 防御);
		bundle.put(穿甲x, 穿甲);
		bundle.put(吸血x, 吸血);
		bundle.put(暴击x, 暴击);
		bundle.put(反击x, 反击);
		bundle.put(命中x, 命中);
		bundle.put(闪避x, 闪避);
		bundle.put(生命x, 生命);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		攻击 = bundle.getFloat(攻击x);
		防御 = bundle.getFloat(防御x);
		穿甲 = bundle.getFloat(穿甲x);
		吸血 = bundle.getFloat(吸血x);
		暴击 = bundle.getFloat(暴击x);
		反击 = bundle.getFloat(反击x);
		命中 = bundle.getFloat(命中x);
		闪避 = bundle.getFloat(闪避x);
		生命 = bundle.getFloat(生命x);
	}
	public String statsInfo() {
		return Messages.get(this,"stats",
							(攻击>0?"攻击 + "+攻击:"")+
							(防御>0?"防御 + "+防御:"")+
							(穿甲>0?"穿甲 + "+穿甲:"")+
							(吸血>0?"吸血 + "+吸血:"")+
							(暴击>0?"暴击 + "+暴击:"")+
							(反击>0?"反击 + "+反击:"")+
							(命中>0?"命中 + "+命中:"")+
							(闪避>0?"闪避 + "+闪避:"")+
							(生命>0?"生命 + "+生命:"")
							);
	}
	@Override
	protected RingBuff buff( ) {
		return new 属性();
	}

	public class 属性 extends RingBuff {
	}
}
