

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 勇装 extends Armor {

	{
		image = 物品表.勇装;
		换甲=Assets.Sounds.皮甲;
		嬗变= false;
		专属=true;
	}

	public 勇装(){
		super(1);
	}
	
	@Override
	public float 力量(int lvl) {
		float req = 力量(tier, lvl)-2;
		if(isEquipped(Dungeon.hero)&&Dungeon.hero()){
            req-=Dungeon.hero.护甲力量;
        }
		if (神力){
			req -= 3;
		}
		
		return req;
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*1.34f);
	}
	@Override
	public int 能量() {
		return Math.round(super.能量()*1.34f);
	}
}