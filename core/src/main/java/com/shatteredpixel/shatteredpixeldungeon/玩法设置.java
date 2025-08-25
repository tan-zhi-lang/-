

package com.shatteredpixel.shatteredpixeldungeon;

public class 玩法设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 修罗血场				= 1;
//	public static final int 摇曳落石				= 2;
	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"修罗血场",
//			"摇曳落石",
	};

	public static final int[] MASKS = {
			修罗血场,
//			摇曳落石,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 玩法设置.MASKS){
			if ((Dungeon.玩法 & ch) != 0) chCount++;
		}
		return chCount;
	}


}