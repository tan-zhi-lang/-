

package com.shatteredpixel.shatteredpixeldungeon;

public class 玩法设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 修罗血场				= 1;
	public static final int 从不过节= 2;
public static final int 鬼怨地牢				= 4;
	public static final int 赛季游戏				= 8;
	public static final int 地牢塔防= 16;
	public static final int 速通地牢			= 32;
	public static final int 刷子地牢		    = 64;
		public static final int 奇袭地牢	= 128;
		public static final int 重生怪物 	= 256;
	public static final int MAX_VALUE           = 511;
	public static final int MAX_CHALS           = 9;

	public static final String[] NAME_IDS = {
			"修罗血场",
			"从不过节",
			"鬼怨地牢",
			"赛季游戏",
			"地牢塔防",
			"速通地牢",
			"刷子地牢",
			"奇袭地牢",
			"重生怪物",
	};

	public static final int[] MASKS = {
			修罗血场,
			从不过节,
			鬼怨地牢,
			赛季游戏,
			地牢塔防,
			速通地牢,
			刷子地牢,
			奇袭地牢,
			重生怪物,
	};
	
	public static int 使用(){
		return 使用(Dungeon.玩法);
	}
	
	public static int 使用(int mask){
		int chCount = 0;
		for (int ch : 玩法设置.MASKS){
			if ((mask & ch) != 0) chCount++;
		}
		return chCount;
	}

}