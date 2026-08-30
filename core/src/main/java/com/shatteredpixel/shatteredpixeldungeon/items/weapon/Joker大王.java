

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Joker大王 extends Weapon{
	
	{
		image = 物品表.大王;
		hitSound = Assets.Sounds.卡牌;
		tier = 5;
		延迟=0.7f;
		白色=true;
		特别=true;
	}
	@Override
	public float pickupDelay() {
		return 0;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null)
		if(defender.老鬼()||defender.小老鬼()){
			damage*=1+Dungeon.区域();
		}
		return super.攻击时( attacker, defender, damage );
	}
}
