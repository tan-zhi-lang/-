

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 战锤 extends Weapon{

	{
		image = 物品表.WAR_HAMMER;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		技能=new 重击();
		延迟=1.25f;
		伤害=1.15f;
		
		tier = 4;
	}
	
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null&&defender.第x次防御==1){
			Buff.延长(defender,Paralysis.class,1);
		}
		return super.攻击时( attacker, defender, damage );
	}
}
