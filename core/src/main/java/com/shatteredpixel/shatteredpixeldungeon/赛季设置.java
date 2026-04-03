

package com.shatteredpixel.shatteredpixeldungeon;

public class 赛季设置{

	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 修罗血场				= 1;
	public static final int 升级概率= 2;
public static final int 鬼怨地牢				= 4;
	public static final int 从零英雄				= 8;
	public static final int 地牢塔防= 16;
	public static final int 刷子地牢			= 32;
	public static final int 危险重重		    = 64;
		public static final int 生化模式 	= 128;
		public static final int 即时策略 	= 256;
	public static final int MAX_VALUE           = 256*2-1;
	public static final int MAX_CHALS           = 9;

	public static final String[] NAME_IDS = {
			"修罗血场",
			"升级概率",
			"鬼怨地牢",
			"从零英雄",
			"地牢塔防",
			"刷子地牢",
			"危险重重",
			"生化模式",
			"即时策略",
	};

	public static final int[] MASKS = {
			修罗血场,
			升级概率,
			鬼怨地牢,
			从零英雄,
			地牢塔防,
			刷子地牢,
			危险重重,
			生化模式,
			即时策略,
	};
	
	public static int 使用(){
		return 使用(Dungeon.赛季);
	}
	
	public static int 使用(int mask){
		int chCount = 0;
		for (int ch : 赛季设置.MASKS){
			if ((mask & ch) != 0) chCount++;
		}
		return chCount;
	}

}