

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.落石法杖;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM0Sprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class DM0 extends Mob {

	{
		spriteClass = DM0Sprite.class;
		
		生命 = 最大生命 = Dungeon.层数(1);
		
		state = WANDERING;
		flying = true;
		
		properties.add(Property.机械);
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
		if(enemy!=null&&Dungeon.level.距离(enemy.pos,pos)<=2)
			return super.移速()*5/6f;
		else
			return super.移速()*9/6f;
	}
	@Override
	public float 防御时(Char enemy,float damage){
		Sample.INSTANCE.play(Assets.Sounds.金属受伤);
		return super.防御时(enemy,damage);
	}
	@Override
	public Char chooseEnemy() {
		return null;
	}
	
	@Override
	public boolean add( Buff buff) {
		if(buff instanceof Paralysis||buff instanceof 落石法杖.落石){
			受伤时(最大生命,buff);
			return true;
		}
		return false;
	}
	@Override
	public void 死亡时(Object 来源) {
		flying = false;
		super.死亡时(来源);
	}
	
}
