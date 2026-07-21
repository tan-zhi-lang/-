

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.背刺;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臻冰刃 extends Weapon {

	{
		image = 物品表.臻冰刃;
		hitSound = Assets.Sounds.HIT_STAB;

		延迟=0.8f;
		伤害=0.8f;
		
		技能=new 背刺();
		特别=true;
		青色=true;
		tier = 5;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			if(defender.hasbuff(Chill.class)||defender.hasbuff(Frost.class)){
				damage+=damage*0.45f;
			}
		}
			return super.攻击时( attacker, defender, damage );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{长匕首.class,
					冰霜药剂.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 臻冰刃.class;
			outQuantity = 1;
		}

	}
}