

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.大杀四方;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;

public class 半月刃 extends Weapon{

	{
		image = 物品表.半月刃;
		hitSound = Assets.Sounds.巨剑;
		技能=new 大杀四方();

		延迟= 2;
		tier = 3;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null)
		for (int n : PathFinder.相邻){
			Char c= Actor.findChar(attacker.pos+n);
			if(c!=null){
				c.受伤时(damage,this);
			}
		}
		return super.攻击时( attacker, defender, damage );
	}
}
