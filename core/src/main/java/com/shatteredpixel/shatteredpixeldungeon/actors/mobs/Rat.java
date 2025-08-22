

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Rat extends Mob {

	{
		spriteClass = RatSprite.class;
		
		生命 = 最大生命 = 8;
		defenseSkill = 2;

		最大等级 = 5;
	}

	@Override
	protected boolean act() {
		alignment = Dungeon.hero.heroClass(HeroClass.鼠弟)?Alignment.NEUTRAL:Alignment.ENEMY;
		if (alignment == Alignment.NEUTRAL&&Dungeon.hero.heroClass(HeroClass.鼠弟)){
			if(Dungeon.level.heroFOV[pos]){
				if (state == SLEEPING) state = WANDERING;
			}
		}
		if(Dungeon.hero.armorAbility instanceof Ratmogrify){
			if(alignment == Alignment.ENEMY) {
				alignment = Alignment.NEUTRAL;
			}
			if(Dungeon.level.heroFOV[pos]){
				if (state == SLEEPING) state = WANDERING;
			}
		}
		return super.act();
	}

	@Override
	public int 攻击() {
		return Random.NormalIntRange( 1, 4 );
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 8;
	}
	
	@Override
	public int 防御() {
		return super.防御() + Random.NormalIntRange(0, 1);
	}

	private static final String RAT_ALLY = "rat_ally";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		if (alignment == Alignment.ALLY) bundle.put(RAT_ALLY, true);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(RAT_ALLY)) alignment = Alignment.ALLY;
	}
}
