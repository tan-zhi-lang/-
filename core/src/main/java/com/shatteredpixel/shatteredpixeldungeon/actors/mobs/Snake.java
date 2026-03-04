

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SnakeSprite;
import com.watabou.noosa.audio.Sample;

public class Snake extends Mob {
	
	{
		spriteClass = SnakeSprite.class;

		生命 = 最大生命 = 10;
		defenseSkill = 5;

		经验 = 2;
		最大等级 = 8;
		loot = Generator.Category.SEED;
		lootChance = 0.25f;
		properties.add(Property.动物);
	}

	@Override
	public float 最大攻击() {
		return 6;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 10;
	}
	
	@Override
	public float 攻击时(Char enemy,float damage){
		Sample.INSTANCE.play(Assets.Sounds.蛇叫);
		return super.攻击时(enemy,damage);
	}
}
