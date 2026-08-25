package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class 涂药 implements Bundlable{

	public void 触发(Char c) {
		触发(c,0);
	}
	public float 触发(Char c,float damage) {

		return damage;
	}

	public final String name() {
		return Messages.get(this,"name");
	}
	public String desc() {
		return Messages.get(this, "desc");
	}
	public void 消耗(EquipableItem i){
		涂药次数=Math.max(0,--涂药次数);

		if(this instanceof 腐莓药物){
			if(涂药次数==15/2)
				GLog.橙("你装备上的涂药快要失效了。");
		}else{
			if(涂药次数==5)
				GLog.橙("你装备上的涂药快要失效了。");
		}
		if(涂药次数==0){
			GLog.橙("你装备上的涂药已经失效了。");
			i.涂药种类=null;
		}
	}
	public int 涂药次数=0;
	public static final String 涂药次数x	= "涂药次数";
	@Override
	public void restoreFromBundle(Bundle bundle){

		bundle.put( 涂药次数x, 涂药次数 );
	}

	@Override
	public void storeInBundle(Bundle bundle){
		涂药次数 = bundle.getInt( 涂药次数x );
	}
}
