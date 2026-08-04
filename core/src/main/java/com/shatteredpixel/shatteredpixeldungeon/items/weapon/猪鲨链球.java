

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfAquaticRejuvenation;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.甩击;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 猪鲨链球 extends Weapon {

	{
		image = 物品表.猪鲨链球;
		hitSound = Assets.Sounds.锤打;
		
		技能=new 甩击();
		tier = 5;
//		连招范围=2;
		范围 = 2;
		延迟= 1.3f;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			Dungeon.level.dropRandomCell(new Dewdrop(), defender.pos);
			Dungeon.level.dropRandomCell(new Dewdrop(), defender.pos);
		}
		return super.攻击时( attacker, defender, damage );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{链枷.class,
					ElixirOfAquaticRejuvenation.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 猪鲨链球.class;
			outQuantity = 1;
		}

	}
	

}
