

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.财富之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.watabou.noosa.Visual;
import com.watabou.utils.Random;

public class 幸运 extends Weapon.Enchantment {

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			float procChance=0.15f*procChanceMultiplier(attacker);
			if(Random.Float()<procChance){


				//default is -5: 80% common, 20% uncommon, 0% rare
				//ring level increases by 1 for each 20% above 100% proc rate
				Buff.施加(defender,LuckProc.class).ringLevel=-10+Math.round(5*procChance);
			}else{
				//in rare cases where we attack many times at once (e.g. gladiator fury)
				// make sure that failed luck procs override prior succeeded ones
				if(defender.buff(LuckProc.class)!=null){
					defender.buff(LuckProc.class).detach();
				}
			}
		}
		return damage;

	}
	
	public static Item genLoot(){
		//80% common, 20% uncommon, 0% rare
		return 财富之戒.genConsumableDrop(-5);
	}

	public static void showFlare( Visual vis ){
		财富之戒.showFlareForBonusDrop(vis);
	}

	@Override
	public Glowing glowing() {
		return 绿;
	}
	
	//used to keep track of whether a luck proc is incoming. see Mob.die()
	public static class LuckProc extends Buff {

		private int ringLevel = -5;
		
		@Override
		public boolean act() {
			detach();
			return true;
		}

		public Item genLoot(){
			detach();
			return 财富之戒.genConsumableDrop(ringLevel);
		}
	}
	
}
