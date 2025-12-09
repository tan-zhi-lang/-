

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class 神农锄 extends Weapon{

	{
		image = 物品表.神农锄;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		
		双手=true;
		tier = 5;
		延迟= 1.25f;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		
		int level = Math.max( 0, 强化等级() );
		
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		float procChance = (level+1f)/(level+3f)*奥术之戒.enchantPowerMultiplier(attacker);
		if (Random.Float()<procChance){
			Dungeon.level.drop(Generator.randomUsingDefaults(Generator.Category.SEED),attacker.pos).sprite.drop();
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		int level = Math.max( 0, 强化等级() );
		
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		float procChance = (level+1f)/(level+3f)*奥术之戒.enchantPowerMultiplier(attacker);
		if (Random.Float() < procChance){
			Dungeon.level.drop(Generator.randomUsingDefaults(Generator.Category.SEED),attacker.pos).sprite.drop();
		}
		return super.投掷攻击时( attacker, defender, damage );
	}


}
