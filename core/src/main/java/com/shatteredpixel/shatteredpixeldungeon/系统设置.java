

package com.shatteredpixel.shatteredpixeldungeon;

public class 系统设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 生命成长				= 1;

	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"生命成长",
	};

	public static final int[] MASKS = {
			生命成长,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 系统设置.MASKS){
			if ((Dungeon.系统 & ch) != 0) chCount++;
		}
		return chCount;
	}


}