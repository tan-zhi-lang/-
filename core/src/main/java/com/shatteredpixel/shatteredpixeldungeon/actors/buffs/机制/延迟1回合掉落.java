

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.watabou.utils.Callback;

public class 延迟1回合掉落 extends FlavourBuff{

	public void call(Callback c){
		c.call();
	}
}
