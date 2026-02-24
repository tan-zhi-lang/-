

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 书包 extends Weapon{

	{
		image = 物品表.书包;
		

		tier = 1;
		伤害=0.5f;
		

	}
	
	@Override
	public String desc(){
		int d=0;
		if(curUser!=null){
			for(Item i:curUser.belongings){
				if(i instanceof Scroll s)d+=s.数量()/2;
			}
		}
		return Messages.get(this,"desc",d);
	}
	@Override
	public float 最大攻击(int lvl) {
		int d=0;
		if(curUser!=null){
			for(Item i:curUser.belongings){
				if(i instanceof Scroll s)d+=s.数量()/2;
			}
		}
		return  super.最大攻击(lvl)+d;
	}
}
