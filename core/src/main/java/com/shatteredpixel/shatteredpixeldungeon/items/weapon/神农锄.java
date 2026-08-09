

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.割草;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 神农锄 extends Weapon{

	{
		image = 物品表.神农锄;
		hitSound = Assets.Sounds.攻击砍;
		
		技能=new 割草();
		延迟=1.2f;
		tier = 5;

		特别=true;
		绿色=true;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			if(算法.概率学(15)){
				Dungeon.level.drop(Generator.randomUsingDefaults(Generator.Category.SEED),defender.pos).sprite().drop();
			}
		}
		return super.攻击时( attacker, defender, damage );
	}


}
