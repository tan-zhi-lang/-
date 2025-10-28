

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 槌 extends Weapon{
	
	{
		image = 物品表.THROWING_CLUB;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		tier = 2;
	}
	public float pickupDelay() {
		return 0;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(defender.第一次防御){
			Buff.延长(defender,Vertigo.class,1);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		if(defender.第一次防御){
			Buff.延长(defender,Vertigo.class,1);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}
}
