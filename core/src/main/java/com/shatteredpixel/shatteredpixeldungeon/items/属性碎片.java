

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 属性碎片 extends 用品 {
	
	
	{
		image = 物品表.属性碎片;
		可以空间=false;
		特别= true;
		嬗变=false;
	}

	@Override
	public int 金币() {
		return 150;
	}
	@Override
	public void 使用(Hero hero){
		if(数量()==5){
			detachAll(hero.belongings.backpack);
			new 属性锻造器().放背包();
			super.使用(hero);
		}else if(数量()>5){
			split(5).detach(hero.belongings.backpack);
			new 属性锻造器().放背包();
			super.使用(hero);
		}else {
			new 属性碎片().放背包();
			GLog.w("数量不够，还差"+(6-数量())+"个。");
		}
	}

}
