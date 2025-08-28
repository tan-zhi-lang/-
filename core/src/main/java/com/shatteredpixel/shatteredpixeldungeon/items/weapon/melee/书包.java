

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 书包 extends MeleeWeapon {

	{
		image = 物品表.书包;
		hitSoundPitch = 1.3f;

		tier = 1;
		间隔= 1.25f; //2x speed
		
		bones = false;
	}

	@Override
	public int 最大攻击(int lvl) {
		int d=0;
		if(curUser!=null){
			for(Item i:curUser.belongings.backpack.items){
				if(i instanceof Scroll s)d+=s.数量(0.5f);
			}
		}
		return  2*(tier+1) +     //5 base, down from 10
				lvl*(tier+1)+d;  //+1 per level, down from +2
	}
}
