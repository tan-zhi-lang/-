

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;

public class Corruption extends AllyBuff {

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	//corrupted enemies are usually fully healed and cleansed of most debuffs
	public static void corruptionHeal(Char target){
		target.回满血();
		
		for (Buff buff : target.buffs()) {
			if (buff.type == Buff.buffType.NEGATIVE
					&& !(buff instanceof 灵魂标记)) {
				buff.detach();
			}
		}
	}
	
	@Override
	public boolean act() {
		if(Dungeon.符文("破败之王"))
			target.回百分比血(0.05f);
		else
			target.受伤时(target.最大生命(0.01f), this);
		spend(TICK);

		return true;
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add( CharSprite.State.DARKENED );
		else if (target.invisible == 0) target.sprite.remove( CharSprite.State.DARKENED );
	}

	@Override
	public int icon() {
		return BuffIndicator.CORRUPT;
	}

}
