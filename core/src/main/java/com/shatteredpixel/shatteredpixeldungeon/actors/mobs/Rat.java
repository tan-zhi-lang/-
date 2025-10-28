

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Bundle;

public class Rat extends Mob {

	{
		spriteClass = RatSprite.class;
		
		生命 = 最大生命 = 8;
		defenseSkill = 2;

		最大等级 = 5;
	}
	public boolean 普通=true;
	@Override
	protected boolean act() {
		if(普通){
		
		if(Dungeon.hero.heroClass(HeroClass.鼠弟)){
				if(Dungeon.level.heroFOV[pos]){
					if(alignment != Alignment.ALLY) {
						alignment=Alignment.NEUTRAL;
					}
					if (enemy == Dungeon.hero) enemy = null;
					if(state==SLEEPING)
						state=WANDERING;
				}
			}
		}else{
			alignment=Alignment.ENEMY;
		}
		
		if(Dungeon.hero.armorAbility instanceof Ratmogrify){
			if(Dungeon.level.heroFOV[pos]){
				if(alignment != Alignment.ALLY) {
					alignment = Alignment.NEUTRAL;
				}
				if (enemy == Dungeon.hero) enemy = null;
				if (state == SLEEPING) state = WANDERING;
			}
		}
		return super.act();
	}

	@Override
	public int 最大攻击() {
		return 4;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 8;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+1;
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
