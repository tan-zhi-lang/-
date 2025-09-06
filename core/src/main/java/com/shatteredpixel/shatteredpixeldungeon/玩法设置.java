

package com.shatteredpixel.shatteredpixeldungeon;

public class 玩法设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 修罗血场				= 1;
	public static final int 从不过节= 2;
public static final int 鬼怨地牢				= 4;
	public static final int 转生怪物				= 8;
	public static final int 规则怪谈				= 16;
	public static final int 时空逃离			= 32;
	public static final int 苏死坚挺		    = 64;
		public static final int 奇袭地牢	= 128;
		public static final int 简单战斗 	= 256;
	public static final int MAX_VALUE           = 511;

	public static final String[] NAME_IDS = {
			"修罗血场",
			"从不过节",
			"鬼怨地牢",
			"转生怪物",
			"规则怪谈",
			"时空逃离",
			"苏死坚挺",
			"奇袭地牢",
			"简单战斗",
	};

	public static final int[] MASKS = {
			修罗血场,
			从不过节,
			鬼怨地牢,
			转生怪物,
			规则怪谈,
			时空逃离,
			苏死坚挺,
			奇袭地牢,
			简单战斗,
	};

	public static int 使用(){
		int chCount = 0;
		for (int ch : 玩法设置.MASKS){
			if ((Dungeon.玩法 & ch) != 0) chCount++;
		}
		return chCount;
	}


}