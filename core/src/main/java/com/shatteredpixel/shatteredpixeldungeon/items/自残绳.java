

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class 自残绳 extends 用品 {
	
	
	{
		image = 物品表.自残绳;
		遗产=false;
		可堆叠=false;
		重复使用=true;
		红色=true;
	}
	
	@Override
	public void 使用(Hero hero){

		float x=0.45f;
		if(hero.符文("命运绳子"))x/=2f;
		if(hero.符文("炼狱绳")){
			x=0.15f;
			hero.力量+=0.5f;
		}
		if(hero.符文("无法上吊之物")){
			x=0;
			hero.回百分比血(0.02f);
		}
		if(x>0){
			if(hero.符文("伤敌100自损100")){
				for(Mob m: Dungeon.level.mobs){
					m.受伤时(m.最大生命(x));
				}
			}

			if(hero.符文("命运绳子")){
				if(Random.Int(1)==0){

					hero.回百分比血(x);
				}else {
					hero.受伤时(hero.最大生命(x));
				}
			}else
			hero.受伤时(hero.最大生命(x));
		}
		
//		hero.死亡时(null);
		super.使用(hero);
	}
}
