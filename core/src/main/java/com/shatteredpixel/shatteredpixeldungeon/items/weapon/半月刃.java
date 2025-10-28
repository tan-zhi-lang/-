

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;

public class 半月刃 extends Weapon{

	{
		image = 物品表.半月刃;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=0.8f;
		tier = 3;
	}


	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {
		for (int n : PathFinder.NEIGHBOURS8){
			Char c= Actor.findChar(attacker.pos+n);
			if(c!=null&&c.alignment == Char.Alignment.ENEMY&& Dungeon.level.heroFOV[c.pos]){
				c.受伤(damage);
			}
		}
		return super.攻击时(attacker, defender, damage);
	}


}
