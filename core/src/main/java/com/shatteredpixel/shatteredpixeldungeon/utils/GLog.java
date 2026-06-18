

package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.Signal;

public class GLog {

	public static final String TAG = "GAME";
	
	public static final String 绿色 = "++ ";
	public static final String 红色 = "** ";
	public static final String 橙色 = "== ";
	public static final String 黄色 = "__ ";

	public static final String 蓝色 = "@@ ";
	public static final String 粉色 = "^^ ";
	public static final String 紫色 = "## ";
	public static final String 灰色 = "-- ";
	public static final String 黑色 = ",, ";
	public static final String 棕色 = ";; ";
	public static final String 青色 = "!! ";
	public static final String 靛色 = "?? ";

	public static final String NEW_LINE	    = "\n";
	
	public static Signal<String> update = new Signal<>();

	public static void newLine(){
//		update.dispatch( NEW_LINE );//修复
	}
	
	/**
	 @return 白
	 */
	public static void p(String text,Object... args) {
		if(!SPDSettings.游戏提示())return;
		if (args.length > 0) {
			text = Messages.format( text, args );
		}
//		text+="\n";
		DeviceCompat.log( TAG, text );
		update.dispatch( text );

	}
	public static void 白(String text,Object... args) {
		p(text,args);
	}
	/**
	@return 绿
	 */
	public static void 绿(String text,Object... args) {
		p(绿色+text,args);
	}
	
	/**
	 @return 红
	 */
	public static void 红(String text,Object... args) {
		p(红色+text,args);
	}
	
	/**
	 @return 橙
	 */
	public static void 橙(String text,Object... args) {
		p(橙色+text,args);
	}
	
	/**
	 @return 黄
	 */
	public static void 黄(String text,Object... args) {
		p(黄色+text,args);
	}
	public static void 蓝(String text,Object... args) {
		p(蓝色+text,args);
	}
	public static void 粉(String text,Object... args) {
		p(粉色+text,args);
	}
	public static void 紫(String text,Object... args) {
		p(紫色+text,args);
	}
	public static void 灰(String text,Object... args) {
		p(灰色+text,args);
	}
	public static void 黑(String text,Object... args) {
		p(黑色+text,args);
	}
	public static void 棕(String text,Object... args) {
		p(棕色+text,args);
	}
	public static void 青(String text,Object... args) {
		p(青色+text,args);
	}
	public static void 靛(String text,Object... args) {
		p(靛色+text,args);
	}
}
