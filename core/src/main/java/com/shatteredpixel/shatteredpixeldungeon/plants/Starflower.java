

package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Starflower extends Plant {

	{
		image = 9;
		seedClass = Seed.class;
	}

	@Override
	public void activate( Char ch ) {

		if (ch != null) {
			Buff.延长(ch, Bless.class, Bless.DURATION);
			if (Dungeon.level.heroFOV[ch.pos]){
				new Flare(6, 32).color(0xFFFF00, true).show(ch.sprite, 2f);
			}
			if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
				Buff.延长(ch, Recharging.class, Recharging.DURATION);
				SpellSprite.show( ch, SpellSprite.CHARGE );
			}
		}

	}

	public static class Seed extends Plant.Seed{

		{
			image = 物品表.SEED_STARFLOWER;

			plantClass = Starflower.class;
		}
		
		@Override
		public int 金币() {
			return 30 * quantity;
		}

		@Override
		public int energyVal() {
			return 3 * quantity;
		}
	}
}
