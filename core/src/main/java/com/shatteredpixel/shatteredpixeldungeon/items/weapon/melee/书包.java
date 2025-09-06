

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 书包 extends MeleeWeapon {

	{
		image = 物品表.书包;
		hitSoundPitch = 1.3f;

		tier = 1;
		命中=1.1f;
		间隔=0.9f;
		伤害=1.1f;
		
		bones = false;
	}

	@Override
	public int 最大攻击(int lvl) {
		int d=0;
		if(curUser!=null){
			for(Item i:curUser.belongings){
				if(i instanceof Scroll s)d+=s.数量(0.5f);
			}
		}
		return  super.最大攻击(lvl)+d;
	}
}
