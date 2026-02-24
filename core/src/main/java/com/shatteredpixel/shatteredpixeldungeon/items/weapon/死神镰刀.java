

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.无情铁手;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 死神镰刀 extends Weapon{

	{
		image = 物品表.死神镰刀;
		hitSound = Assets.Sounds.死神镰刀;
		
		技能=new 无情铁手();
		
		
		tier = 5;
		延迟= 1.25f;
		伤害= 1.35f;
		命中=0.75f;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(attacker instanceof Hero hero&&hero.符文("升级死神镰刀"))damage*=attacker.暴击伤害();
		if(defender!=null&&defender.残血()){
			damage*=attacker.暴击伤害();
		}
		return super.攻击时( attacker, defender, damage );
	}



}
