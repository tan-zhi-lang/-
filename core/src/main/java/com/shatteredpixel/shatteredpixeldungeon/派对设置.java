

package com.shatteredpixel.shatteredpixeldungeon;

public class 派对设置{

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 英精英雄				= 1;
	public static final int 重生怪物= 2;
public static final int 钢门联盟				= 4;
public static final int 怪物猎场				= 8;
	public static final int 海克斯= 16;
	public static final int 小小可爱			= 32;
//	public static final int 刷子地牢		    = 64;
//		public static final int 升级概率	= 128;
//		public static final int  	= 256;
public static final int MAX_VALUE           = 256*2-1;
	public static final int MAX_CHALS           = 9;

	public static final String[] NAME_IDS = {
			"英精英雄",
			"重生怪物",
			"钢门联盟",
			"怪物猎场",
			"海克斯",
			"小小可爱",
	};

	public static final int[] MASKS = {
			英精英雄,
			重生怪物,
			钢门联盟,
			怪物猎场,
			海克斯,
			小小可爱,
	};
	
	public static int 使用(){
		return 使用(Dungeon.派对);
	}
	
	public static int 使用(int mask){
		int chCount = 0;
		for (int ch : 派对设置.MASKS){
			if ((mask & ch) != 0) chCount++;
		}
		return chCount;
	}

}