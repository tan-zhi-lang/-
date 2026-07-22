

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 虚哭神去 extends Weapon{
	{
		image = 物品表.虚哭神去;
		hitSound = Assets.Sounds.攻击砍;
		吸血=0.15f;
		伤害=0.8f;
		特别=true;
		红色=true;
		tier=5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			damage+=attacker.生命(0.06f);
			attacker.受伤时(attacker.生命(0.02f));
		}
		return super.攻击时( attacker, defender, damage );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{狼筅.class,
					武士刀.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 日炎链刃.class;
			outQuantity = 1;
		}

	}
}
