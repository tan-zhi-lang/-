

package com.shatteredpixel.shatteredpixeldungeon;

public class 系统设置{
	
	//Some of these internal IDs are outdated and don't represent what these challenges do
	public static final int 生命成长=1;
	public static final int 防御成长=2;
	public static final int 资产破亿=4;
	public static final int 透视系统=8;
	public static final int 时间能力=16;
	public static final int 金币能力=32;
	public static final int 无限资源=64;
	public static final int 波罗神盾=128;
	public static final int 攻击成长=256;
	public static final int MAX_VALUE=511;
	
	public static final String[] NAME_IDS={"生命成长","防御成长","资产破亿",
										   "透视系统",
										   
										   "时间能力","金币能力","无限资源",
										   "波罗神盾","攻击成长",};
	
	public static final int[] MASKS={生命成长,防御成长,资产破亿,透视系统,
									 
									 时间能力,金币能力,无限资源,波罗神盾,
									 攻击成长,};
	
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