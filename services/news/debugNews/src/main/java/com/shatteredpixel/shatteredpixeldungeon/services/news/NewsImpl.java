

package com.shatteredpixel.shatteredpixeldungeon.services.news;

public class NewsImpl {

	private static NewsService newsChecker = new DebugNews();

	public static NewsService getNewsService(){
		return newsChecker;
	}

	public static boolean supportsNews(){
		return true;
	}

}
