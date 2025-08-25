

package com.shatteredpixel.shatteredpixeldungeon;

public class 炼狱设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int x				= 1;
	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"x",
	};

	public static final int[] MASKS = {
			x,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 炼狱设置.MASKS){
			if ((Dungeon.炼狱 & ch) != 0) chCount++;
		}
		return chCount;
	}


}