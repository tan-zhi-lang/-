

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class DimensionalSundial extends Trinket {

	{
		image = 物品表.SUNDIAL;
	}

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this,
					"stats_desc",
					(int)(100*(1f - enemySpawnMultiplierDaytime(等级()))),
					(int)(100*(enemySpawnMultiplierNighttime(等级())-1f)));
		} else {
			return Messages.get(this, "stats_desc",
					(int)(100*(1f - enemySpawnMultiplierDaytime(0))),
					(int)(100*(enemySpawnMultiplierNighttime(0)-1f)));
		}
	}

	public static boolean sundialWarned = false;

	public static float spawnMultiplierAtCurrentTime(){
		if (trinketLevel(DimensionalSundial.class) != -1) {
			if (Dungeon.地牢时间>2000||Dungeon.地牢时间<=800) {
				if (!sundialWarned){
					GLog.w(Messages.get(DimensionalSundial.class, "warning"));
					sundialWarned = true;
				}
				return enemySpawnMultiplierNighttime();
			} else {
				return enemySpawnMultiplierDaytime();
			}
		} else {
			return 1f;
		}
	}

	public static float enemySpawnMultiplierDaytime(){
		return enemySpawnMultiplierDaytime(trinketLevel(DimensionalSundial.class));
	}

	public static float enemySpawnMultiplierDaytime( int level ){
		if (level == -1){
			return 1f;
		} else {
			return 0.95f - 0.05f*level;
		}
	}

	public static float enemySpawnMultiplierNighttime(){
		return enemySpawnMultiplierNighttime(trinketLevel(DimensionalSundial.class));
	}

	public static float enemySpawnMultiplierNighttime( int level ){
		if (level == -1){
			return 1f;
		} else {
			return 1.25f + 0.25f*level;
		}
	}
}
