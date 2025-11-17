

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 投石 extends Weapon{
	
	{
		image = 物品表.THROWING_STONE;
		tier = 1;
		间隔=0.5f;
		伤害=0.5f;
	}
	@Override
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
	@Override
	public int 能量() {
		return 0;
	}
	@Override
	public int 金币() {
		return 0;
	}
}
