

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM0Sprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class DM0 extends Mob {

	{
		spriteClass = DM0Sprite.class;
		
		生命 = 最大生命 = 1;
		
		经验 = 0;
		state = WANDERING;
		flying = true;
		
		loot = Random.oneOf(Generator.randomUsingDefaults( Generator.Category.POTION ),
							Generator.randomUsingDefaults( Generator.Category.SCROLL ));
	}
	@Override
	public int 最大闪避(Char enemy ) {
		return Char.INFINITE;
	}
	@Override
	public float 移速() {
		if(hasbuff(Paralysis.class)){
			return 0;
		}
		return 2;
	}
	@Override
	public int 防御时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.金属受伤);
		return super.防御时(enemy,damage);
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
