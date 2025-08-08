

package com.shatteredpixel.shatteredpixeldungeon.services.updates;

public class UpdateImpl {

	private static UpdateService updateChecker = new GitHubUpdates();

	public static UpdateService getUpdateService(){
		return updateChecker;
	}

	public static boolean supportsUpdates(){
		return true;
	}

}
