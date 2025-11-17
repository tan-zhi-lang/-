

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class 金纹拐 extends Weapon {

	{
		image = 物品表.金纹拐;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		
		间隔= 1.25f;
		tier = 5;
	}

	@Override
	public int 最大防御(int lvl){
		return 5 + lvl;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		
		int level = Math.max( 0, 强化等级() );
		
		// lvl 0 - 33%
		// lvl 1 - 50%
		// lvl 2 - 60%
		float procChance = (level+1f)/(level+3f)*奥术之戒.enchantPowerMultiplier(attacker);
		if (Random.Float() < procChance){
			Dungeon.gold(new Gold().random().数量());
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
			Dungeon.gold(new Gold().random().数量());
		}
		return super.投掷攻击时( attacker, defender, damage );
	}

}
