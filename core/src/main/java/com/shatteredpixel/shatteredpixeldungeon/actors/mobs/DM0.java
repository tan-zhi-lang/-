

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM0Sprite;

public class DM0 extends Mob {

	{
		spriteClass = DM0Sprite.class;
		
		生命 = 最大生命 = 1;
		
		经验 = 0;
		state = SLEEPING;
		flying = true;
		
		loot = Generator.random();
	}
	@Override
	public int 最大闪避(Char enemy ) {
		return INFINITE_EVASION;
	}
	@Override
	public float 移速() {
		if(hasbuff(Paralysis.class)){
			return 0;
		}
		return 6;
	}
	
	@Override
	public Char chooseEnemy() {
		return null;
	}
	
	@Override
	public void 受伤时(int dmg, Object src ) {
		
		if(src instanceof Paralysis){
			dmg=最大生命;
		}else{
			dmg=0;
		}
		super.受伤时(dmg,src);
	}
	@Override
	public void 死亡时(Object cause) {
		flying = false;
		super.死亡时(cause);
	}
	
}
