

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class 净化药物 extends 涂药{

	@Override
	public float 触发(Char c,float damage) {
				//need to delay this so damage from the dart doesn't break wandering
		new FlavourBuff(){
			{
				actPriority=VFX_PRIO;
			}

			public boolean act(){
				if(((Mob)c).state==((Mob)c).HUNTING||((Mob)c).state==((Mob)c).FLEEING){
					((Mob)c).state=((Mob)c).WANDERING;
				}
				((Mob)c).beckon(Dungeon.level.randomDestination(c));


				c.sprite.showLost();
				return super.act();
			}
		}.attachTo(c);
			
		


		return super.触发( c, damage);
	}
}
