

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.圣银箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 圣银十字弩 extends 十字弩{
	
	{
		image = 物品表.圣银十字弩;
		
		tier = 5;
		子弹 = new 圣银箭矢();
		image2 = 物品表.圣银箭矢;

		无限子弹=true;
		掉落子弹=false;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage){
		if(defender!=null)defender.受伤时(defender.最大生命(0.045f));
		return super.攻击时(attacker,defender,damage);
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{炼金动力十字弩.class,
					LiquidMetal.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 圣银十字弩.class;
			outQuantity = 1;
		}

	}
}
