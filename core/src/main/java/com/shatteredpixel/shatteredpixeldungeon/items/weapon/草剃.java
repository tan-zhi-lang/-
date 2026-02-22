

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.PathFinder;

public class 草剃 extends Weapon {

	{
		image = 物品表.草剃;
		hitSound = Assets.Sounds.叶子;
		
		延迟= 0.8f;
		伤害= 0.8f;
		
		伏击=0.5f;
		tier = 5;
	}
	
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		for (int n : PathFinder.相邻8){
			int cell=attacker.pos+n;
			Dungeon.level.pressCellgrass(cell);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		
		for (int n : PathFinder.相邻8){
			int cell=attacker.pos+n;
			Dungeon.level.pressCellgrass(cell);
		}
		return super.投掷攻击时( attacker, defender, damage );
	}

}