

package com.shatteredpixel.shatteredpixeldungeon.services.updates;

import com.watabou.noosa.Game;

public class UpdateImpl {

	private static UpdateService updateChecker = new DebugUpdates();

	public static UpdateService getUpdateService(){
		return updateChecker;
	}

	public static boolean supportsUpdates(){
		return Game.version.contains("INDEV");
	}

}
