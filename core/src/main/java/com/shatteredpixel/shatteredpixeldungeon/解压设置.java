

package com.shatteredpixel.shatteredpixeldungeon;

public class 解压设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 超级背包				= 1;
	public static final int 幸运女神				= 2;
	public static final int 天赋武神				= 4;
	public static final int 纯正怪物				= 8;
	public static final int 宝物空投				= 16;
	public static final int 掉落几率			= 32;
	public static final int 血源迸发		    = 64;
	public static final int 持之以恒= 128;
	public static final int 探索口粮 	= 256;
	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"超级背包",
			"幸运女神",
			"天赋武神",
			"纯正怪物",
			"宝物空投",
			"掉落几率",
			"血源迸发",
			"持之以恒",
			"探索口粮",
	};

	public static final int[] MASKS = {
			超级背包,
			幸运女神,
			天赋武神,
			纯正怪物,
			宝物空投,
			掉落几率,
			血源迸发,
			持之以恒,
			探索口粮,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 解压设置.MASKS){
			if ((Dungeon.解压 & ch) != 0) chCount++;
		}
		return chCount;
	}


}