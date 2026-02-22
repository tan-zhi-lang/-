package com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.particles.Emitter;

public class 神圣长枪 extends Weapon{

		{
			image = 物品表.SPEAR;
			hitSound = Assets.Sounds.HIT_STAB;
		}

		@Override
		public ItemSprite.Glowing glowing() {
			return new ItemSprite.Glowing(0xFFFFFF,0.1f);
		}

		@Override
		public Emitter emitter() {
			Emitter emitter = new Emitter();
			emitter.pos( 5, 5, 0, 0);
			emitter.fillTarget = false;
			emitter.pour(SparkParticle.FACTORY,0.025f);
			return emitter;
		}
	}