

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 重力场球 extends Trinket {

	{
		image = 物品表.重力场球;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this,"stats_desc",
								100*英雄(),
								100*敌人()
							   );
		} else {
			return Messages.get(this,"stats_desc",
								100*英雄(0),
								100*敌人(0)
							   );
		}
	}


	public static float 攻速(Char c){
		float x=1;
		if(trinketLevel(重力场球.class)>=0)
		if(c!=null){
			x=1/0.75f;
			if(c instanceof Hero)x*=英雄();
			if(c instanceof Mob)x*=敌人();
		}
		return x;
	}

	public static float 移速(Char c){
		float x=1;
		if(trinketLevel(重力场球.class)>=0)
		if(c!=null){
			x=0.75f;
			if(c instanceof Hero)x/=英雄();
			if(c instanceof Mob)x/=敌人();
		}
		return x;
	}

	public static float 英雄(){
		return 英雄(trinketLevel(重力场球.class));
	}

	public static float 英雄(int level){
		if (level < 0){
			return 1;
		} else {
			return 0.95f-0.1f*level;
		}
	}

	public static float 敌人(){
		return 敌人(trinketLevel(重力场球.class));
	}

	public static float 敌人(int level){
		if (level < 0){
			return 1;
		} else {
			return 1f-0.05f*level;
		}
	}

}
