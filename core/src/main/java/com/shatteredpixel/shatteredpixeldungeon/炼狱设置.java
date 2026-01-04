

package com.shatteredpixel.shatteredpixeldungeon;

public class 炼狱设置 {

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 遗失钥匙				= 1;
	public static final int 诅咒面具				= 2;
	public static final int 诅咒神器				= 4;
	public static final int 诅咒之戒				= 8;
	public static final int 诅咒法杖				= 16;
	public static final int 无力投掷			= 32;
	public static final int 治疗禁令= 64;
	public static final int 体弱多病	= 128;
	public static final int STRONGER_BOSSES 	= 256;
	public static final int MAX_VALUE           = 511;
	public static final int MAX_CHALS           = 9;

	public static final String[] NAME_IDS = {
			"遗失钥匙",
			"诅咒面具",
			"诅咒神器",
			"诅咒之戒",
			"诅咒法杖",
			"无力投掷",
			"治疗禁令",
			"体弱多病",
	};

	public static final int[] MASKS = {
			遗失钥匙,
			诅咒面具,
			诅咒神器,
			诅咒之戒,
			诅咒法杖,
			无力投掷,
			治疗禁令,
			体弱多病,
	};
	
	public static int 使用(){
		return 使用(Dungeon.炼狱);
	}
	
	public static int 使用(int mask){
		int chCount = 0;
		for (int ch : 炼狱设置.MASKS){
			if ((mask & ch) != 0) chCount++;
		}
		return chCount;
	}

}