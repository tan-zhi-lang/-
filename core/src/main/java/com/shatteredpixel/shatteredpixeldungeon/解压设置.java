

package com.shatteredpixel.shatteredpixeldungeon;

public class 解压设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 超级背包				= 1;
	public static final int 幸运女神				= 2;
	public static final int 天赋武神				= 4;
	public static final int 点石成金				= 8;
	public static final int 宝物空投				= 16;
	public static final int 掉落几率			= 32;
	public static final int 独自变强= 64;
	public static final int 持之以恒= 128;
	public static final int 探索口粮 	= 256;
	public static final int MAX_VALUE           = 511;
	public static final int MAX_CHALS           = 9;

	public static final String[] NAME_IDS = {
			"超级背包",
			"幸运女神",
			"天赋武神",
			"点石成金",
			"宝物空投",
			"掉落几率",
			"独自变强",
			"持之以恒",
			"探索口粮",
	};

	public static final int[] MASKS = {
			超级背包,
			幸运女神,
			天赋武神,
			点石成金,
			宝物空投,
			掉落几率,
			独自变强,
			持之以恒,
			探索口粮,
	};
	
	public static int 使用(){
		return 使用(Dungeon.解压);
	}
	
	public static int 使用(int mask){
		int chCount = 0;
		for (int ch : 解压设置.MASKS){
			if ((mask & ch) != 0) chCount++;
		}
		return chCount;
	}


}