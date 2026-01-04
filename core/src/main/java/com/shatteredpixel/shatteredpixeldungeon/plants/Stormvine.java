

package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Levitation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class Stormvine extends Plant {

	{
		image = 5;
		seedClass = Seed.class;
	}

	@Override
	public void activate( Char ch ) {

		if (ch != null) {
			if (ch instanceof Hero hero&&hero.职业精通()&&hero.subClass==HeroSubClass.守望者){
				Buff.施加(ch, Levitation.class, Levitation.DURATION/2f);
			} else {
				if (ch instanceof Mob){
					Buff.延长(ch, Trap.HazardAssistTracker.class, Trap.HazardAssistTracker.DURATION);
				}
				Buff.施加(ch, Vertigo.class, Vertigo.DURATION);
			}
		}
	}

	public static class Seed extends Plant.Seed {
		{
			image = 物品表.SEED_STORMVINE;

			plantClass = Stormvine.class;
		}
	}
}
