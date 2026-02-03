

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
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
		return 1;
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
	public void 受伤时(float dmg, Object src ) {
		
		if(src instanceof Paralysis){
			dmg=最大生命;
		}else{
			dmg=0;
		}
		super.受伤时(dmg,src);
	}
	@Override
	public boolean add( Buff buff) {
		if(buff instanceof Paralysis){
			受伤时(最大生命,buff);
			return true;
		}
		return false;
	}
	@Override
	public void 死亡时(Object cause) {
		flying = false;
		super.死亡时(cause);
	}
	
}
