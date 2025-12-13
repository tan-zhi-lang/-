

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.RatKing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Rat extends Mob {

	{
		spriteClass = RatSprite.class;
		
		生命 = 最大生命 = Dungeon.老鼠蝙蝠?50:8;
		defenseSkill = Dungeon.老鼠蝙蝠?16:2;
		经验=Dungeon.老鼠蝙蝠?7:1;
		baseSpeed = Dungeon.老鼠蝙蝠?2:1;
		最大等级 = Dungeon.老鼠蝙蝠?15:5;
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
		
		if(!RatKing.库存||Dungeon.hero.armorAbility instanceof Ratmogrify){
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
	public int 最小攻击() {
		return Dungeon.老鼠蝙蝠?5:1;
	}
	@Override
	public int 最大攻击() {
		return Dungeon.老鼠蝙蝠?18:4;
	}
	
	
	@Override
	public void 死亡时(Object cause){
		Sample.INSTANCE.play(Assets.Sounds.RAT);
		super.死亡时(cause);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return Dungeon.老鼠蝙蝠?16:8;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+(Dungeon.老鼠蝙蝠?4:1);
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
