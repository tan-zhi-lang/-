

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Levitation;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.保鲜;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Bundle;

public class 重锤 extends Weapon{

	{
		image = 物品表.重锤;
		hitSound = Assets.Sounds.HIT_CRUSH;
		
		技能=new 保鲜();
		延迟=0.6f;

		
		tier = 5;
	}

	public int 猛击=0;
	private static final String 猛击x=        "猛击";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(猛击x,猛击);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		猛击= bundle.getInt(猛击x);
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(attacker instanceof Hero&&猛击>0){
			damage*=Dungeon.区域();
			猛击--;
		}
		if(attacker.hasbuff(Levitation.class))damage*=attacker.暴击伤害();
		return super.攻击时( attacker, defender, damage );
	}

}
