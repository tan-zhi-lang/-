

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 巨斧 extends Weapon{

	{
		image = 物品表.巨斧;
		hitSound = Assets.Sounds.HIT_SLASH;
		间隔=1.5f;
		伤害=1.75f;
		双手=true;

		tier = 5;
	}

	@Override
	public int 力量(int lvl) {
		int req = 力量(tier+1, lvl); //20 base strength req, up from 18
		if (masteryPotionBonus){
			req -= 2;
		}
		if (神力){
			req -= 2;
		}
		return req;
	}

}
