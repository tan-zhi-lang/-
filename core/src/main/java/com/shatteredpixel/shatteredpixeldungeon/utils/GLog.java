

package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.Signal;

public class GLog {

	public static final String TAG = "GAME";
	
	public static final String POSITIVE		= "++ ";
	public static final String NEGATIVE		= "-- ";
	public static final String WARNING		= "** ";
	public static final String HIGHLIGHT	= "@@ ";

	public static final String NEW_LINE	    = "\n";
	
	public static Signal<String> update = new Signal<>();

	public static void newLine(){
		update.dispatch( NEW_LINE );
	}
	
	/**
	 @return 白
	 */
	public static void i( String text, Object... args ) {
		if(!SPDSettings.游戏提示())return;
		if (args.length > 0) {
			text = Messages.format( text, args );
		}
		
		DeviceCompat.log( TAG, text );
		update.dispatch( text );
	}
	/**
	@return 绿
	 */
	public static void p( String text, Object... args ) {
		i( POSITIVE + text, args );
	}
	
	/**
	 @return 红
	 */
	public static void n( String text, Object... args ) {
		i( NEGATIVE + text, args );
	}
	
	/**
	 @return 橙
	 */
	public static void w( String text, Object... args ) {
		i( WARNING + text, args );
	}
	
	/**
	 @return 黄
	 */
	public static void h( String text, Object... args ) {
		i( HIGHLIGHT + text, args );
	}
}
