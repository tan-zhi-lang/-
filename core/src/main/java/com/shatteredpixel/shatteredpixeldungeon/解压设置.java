

package com.shatteredpixel.shatteredpixeldungeon;

public class 解压设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 超级背包				= 1;
	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"超级背包",
	};

	public static final int[] MASKS = {
			超级背包,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 解压设置.MASKS){
			if ((Dungeon.解压 & ch) != 0) chCount++;
		}
		return chCount;
	}


}