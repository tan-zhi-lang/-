

package com.shatteredpixel.shatteredpixeldungeon;

public class 系统设置{
	
	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 生命成长=1;
	public static final int 防御成长=2;
	public static final int 资产破亿=4;
	public static final int 经验成长=8;
	public static final int 时间能力=16;
	public static final int 金币能力=32;
	public static final int 虔诚物到=64;
	public static final int 波罗神盾=128;
	public static final int 独自变强=256;
	public static final int MAX_VALUE=511;
	
	public static final String[] NAME_IDS={"生命成长","防御成长","资产破亿",
										   "经验成长",
										   
										   "时间能力","金币能力","虔诚物到",
										   "波罗神盾","独自变强",};
	
	public static final int[] MASKS={生命成长,防御成长,资产破亿,经验成长,
									 
									 时间能力,金币能力,虔诚物到,波罗神盾,
									 独自变强,};
	
	public static int 使用(){
		int chCount=0;
		for(int ch: 系统设置.MASKS){
			if((Dungeon.系统&ch)!=0){
				chCount++;
			}
		}
		return chCount;
	}
	
	
}