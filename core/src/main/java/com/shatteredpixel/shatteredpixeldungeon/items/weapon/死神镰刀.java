

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 死神镰刀 extends Weapon{

	{
		image = 物品表.死神镰刀;
		hitSound = Assets.Sounds.死神镰刀;
		
		
		双手=true;
		tier = 5;
		间隔= 1.5f;
		伤害= 1.75f;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		
		if(defender.残血()){
			damage*=2;
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		
		if(defender.半血以下()){
			damage*=2;
		}
		return super.投掷攻击时( attacker, defender, damage );
	}


}
