

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 属性碎片 extends 用品{
	
	
	{
		image = 物品表.属性碎片;
		可以空间=false;
		特别= true;
		重复使用= true;
		嬗变=false;
	}

	@Override
	public int 金币() {
		return 25*quantity;
	}
	@Override
	public void 使用(Hero hero){
		if(数量()==5){
			detachAll(hero.belongings.backpack);
			new 属性锻造器().放背包();
		}else if(数量()>5){
			split(5).detach(hero.belongings.backpack);
			new 属性锻造器().放背包();
		}else {
			GLog.橙("数量不够，还差"+(6-数量())+"个。");
		}
		super.使用(hero);
	}

}
