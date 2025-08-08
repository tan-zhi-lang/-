

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class WellFed extends Buff {

	{
		type = buffType.POSITIVE;
		announced = true;
	}
	
	int left;
	
	@Override
	public boolean act() {
		left --;
		if (left < 0){
			detach();
			if (target instanceof Hero) {
				((Hero) target).resting = false;
			}
			return true;
		} else if (left % 18 == 0 && target.生命 < target.最大生命){
			target.生命 += 1;
			target.sprite.showStatusWithIcon(CharSprite.POSITIVE, "1", FloatingText.HEALING);

			if (target.生命 == target.最大生命 && target instanceof Hero) {
				((Hero) target).resting = false;
			}
		}

		//salt cube does slow this buff down, but doesn't lessen the bonus health
		spend(TICK / SaltCube.hungerGainMultiplier());
		return true;
	}
	
	public void reset(){
		//heals one HP every 18 turns for 450 turns
		//25 HP healed in total
		left = (int)Hunger.STARVING;
		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			//150 turns if on diet is enabled
			left /= 3;
		}
	}

	public void extend( float duration ) {
		left += duration;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.WELL_FED;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (Hunger.STARVING - left) / Hunger.STARVING);
	}

	@Override
	public String iconTextDisplay() {
		int visualLeft = (int)(left / SaltCube.hungerGainMultiplier());
		return Integer.toString(visualLeft+1);
	}
	
	@Override
	public String desc() {
		int visualLeft = (int)(left / SaltCube.hungerGainMultiplier());
		return Messages.get(this, "desc", visualLeft + 1);
	}
	
	private static final String LEFT = "left";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEFT, left);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		left = bundle.getInt(LEFT);
	}
}
