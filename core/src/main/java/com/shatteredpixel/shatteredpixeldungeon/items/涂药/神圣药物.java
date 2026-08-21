

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 神圣药物 extends 涂药{
	@Override
	public float 触发(Char c,float damage) {

		if(Char.hasProp(c,Char.Property.UNDEAD)||Char.hasProp(c,Char.Property.DEMONIC)){
			c.sprite.emitter().start(ShadowParticle.UP,0.05f,10);
			Sample.INSTANCE.play(Assets.Sounds.BURNING);
			c.受伤时(Random.NormalFloat(10+Dungeon.scalingDepth()/3f,20+Dungeon.scalingDepth()/3f),this);
			//also do not bless enemies if processing charged shot
		}

		return 0;
	}
}
