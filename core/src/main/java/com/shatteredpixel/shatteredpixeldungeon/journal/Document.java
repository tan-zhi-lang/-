

package com.shatteredpixel.shatteredpixeldungeon.journal;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.DeviceCompat;

import java.util.Collection;
import java.util.LinkedHashMap;

public enum Document {
	
	ADVENTURERS_GUIDE(物品表.GUIDE_PAGE, false),
	ALCHEMY_GUIDE(物品表.ALCH_PAGE, false),

	INTROS(Icons.上楼, true),
	SEWERS_GUARD(物品表.SEWER_PAGE, true),
	PRISON_WARDEN(物品表.PRISON_PAGE, true),
	CAVES_EXPLORER(物品表.CAVES_PAGE, true),
	CITY_WARLOCK(物品表.CITY_PAGE, true),
	HALLS_KING(物品表.HALLS_PAGE, true),
	JANE_PAGE(物品表.JANE_PAGE, true);

	Document( int sprite, boolean lore ){
		pageIcon = null;
		pageSprite = sprite;
		loreDocument = lore;
	}

	Document( Icons icon, boolean lore ){
		pageIcon = icon;
		pageSprite = 0;
		loreDocument = lore;
	}

	public static final int NOT_FOUND = 0;
	public static final int FOUND = 1;
	public static final int READ = 2;
	private LinkedHashMap<String, Integer> pagesStates = new LinkedHashMap<>();
	
	public boolean findPage( String page ) {
		if (pagesStates.containsKey(page) && pagesStates.get(page) == NOT_FOUND){
			pagesStates.put(page, FOUND);
			Journal.saveNeeded = true;
			Badges.validateCatalogBadges();
			return true;
		}
		return false;
	}

	public boolean findPage( int pageIdx ) {
		return findPage( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean deletePage( String page ){
		if (pagesStates.containsKey(page) && pagesStates.get(page) != NOT_FOUND){
			pagesStates.put(page, NOT_FOUND);
			Journal.saveNeeded = true;
			return true;
		}
		return false;
	}

	public boolean deletePage( int pageIdx ) {
		return deletePage( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean unreadPage( String page ){
		if (pagesStates.containsKey(page) && pagesStates.get(page) == READ){
			pagesStates.put(page, FOUND);
			Journal.saveNeeded = true;
			return true;
		}
		return false;
	}

	public boolean unreadPage( int pageIdx ) {
		return deletePage( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean isPageFound( String page ){
		return pagesStates.containsKey(page) && pagesStates.get(page) > NOT_FOUND;
	}

	public boolean isPageFound( int pageIdx ){
		return isPageFound( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean anyPagesFound(){
		for( Integer val : pagesStates.values()){
			if (val != NOT_FOUND){
				return true;
			}
		}
		return false;
	}

	public boolean allPagesFound(){
		for( Integer val : pagesStates.values()){
			if (val == NOT_FOUND){
				return false;
			}
		}
		return true;
	}

	public boolean readPage( String page ) {
		if (pagesStates.containsKey(page)){
			pagesStates.put(page, READ);
			Journal.saveNeeded = true;
			Badges.validateCatalogBadges();
			return true;
		}
		return false;
	}

	public boolean readPage( int pageIdx ) {
		return readPage( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public boolean isPageRead( String page ){
		return pagesStates.containsKey(page) && pagesStates.get(page) == READ;
	}

	public boolean isPageRead( int pageIdx ){
		return isPageRead( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public Collection<String> pageNames(){
		return pagesStates.keySet();
	}

	public int pageIdx(String name){
		int i = 0;
		for( String page : pagesStates.keySet()){
			if (page.equals(name)){
				return i;
			}
			i++;
		}
		return -1;
	}

	private int pageSprite;
	private Icons pageIcon;
	public Image pageSprite(){
		return pageSprite("");
	}

	public Image pageSprite(String page){
		if (page.isEmpty() || !isPageFound(page) || this != ADVENTURERS_GUIDE){
			if (pageIcon != null){
				return Icons.get(pageIcon);
			} else {
				return new ItemSprite(pageSprite);
			}
		} else {
			//special per-page visuals for guidebook
			switch (page){
				case GUIDE_INTRO: default:
					return new ItemSprite(物品表.MASTERY);
				case GUIDE_EXAMINING:
					return Icons.get(Icons.MAGNIFY);
				case GUIDE_SURPRISE_ATKS:
					return Icons.get(Icons.SNAKE);
				case GUIDE_IDING:
					return new ItemSprite( new 鉴定卷轴() );
				case GUIDE_FOOD:
					return new ItemSprite( 物品表.PASTY );
				case GUIDE_ALCHEMY:
					return new ItemSprite( 物品表.TRINKET_CATA );
				case GUIDE_DIEING:
					return new ItemSprite( 物品表.TOMB );
				case GUIDE_SEARCHING:
					return Icons.get(Icons.MAGNIFY);
				case 力量:
					return new ItemSprite( 物品表.巨斧);
				case 装备:
					return new ItemSprite( 物品表.RING_EMERALD );
				case 搜索:
					return new ItemSprite( 物品表.CRYSTAL_KEY );
				case 升级:
					return Icons.get(Icons.TALENT);
				case 地势:
					return new ItemSprite( 物品表.灵能短弓);
				case 法伤:
					return new ItemSprite( 物品表.焰浪法杖);
				case 护甲:
					return new ItemSprite( 物品表.护甲修理工具包);
				case 暴击:
					return new ItemSprite( 物品表.无尽之刃);
				case GUIDE_CALENDAR:
					return Icons.get(Icons.CALENDAR);
			}
		}
	}

	private boolean loreDocument;
	public boolean isLoreDoc(){
		return loreDocument;
	}
	
	public String title(){
		return Messages.get( this, name() + ".title");
	}

	public String discoverHint(){
		return Messages.get( this, name() + ".discover_hint");
	}
	
	public String pageTitle( String page ){
		return Messages.get( this, name() + "." + page + ".title");
	}
	
	public String pageTitle( int pageIdx ){
		return pageTitle( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}
	
	public String pageBody( String page ){
		return Messages.get( this, name() + "." + page + ".body");
	}
	
	public String pageBody( int pageIdx ){
		return pageBody( pagesStates.keySet().toArray(new String[0])[pageIdx] );
	}

	public static final String GUIDE_INTRO          = "Intro";
	public static final String GUIDE_EXAMINING      = "Examining";
	public static final String GUIDE_SURPRISE_ATKS  = "Surprise_Attacks";
	public static final String GUIDE_IDING          = "Identifying";
	public static final String GUIDE_FOOD           = "Food";
	public static final String GUIDE_ALCHEMY        = "Alchemy";
	public static final String GUIDE_DIEING         = "Dieing";

	public static final String GUIDE_SEARCHING      = "Searching";
	public static final String 力量= "力量";
	public static final String 装备= "装备";
	public static final String 搜索= "搜索";
	public static final String 升级= "升级";
	public static final String 地势= "地势";
	public static final String 法伤= "法伤";
	public static final String 护甲= "护甲";
	public static final String 暴击= "暴击";
	public static final String GUIDE_CALENDAR= "calendar";

	public static final String KING_ATTRITION       = "attrition";

	//pages and default states
	static {
		boolean debug = DeviceCompat.isDebug();
		//hero gets these when guidebook is collected
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_INTRO,          debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_EXAMINING,      debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_SURPRISE_ATKS,  debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_IDING,          debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_FOOD,           debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_ALCHEMY,        debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_DIEING,         debug ? READ : NOT_FOUND);
		//given in sewers
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_SEARCHING,      debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(力量,           debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(装备,           debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(搜索,            debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(升级,          debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(地势,        debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(法伤,              debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(护甲,debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(暴击,debug ? READ : NOT_FOUND);
		ADVENTURERS_GUIDE.pagesStates.put(GUIDE_CALENDAR,debug ? READ : NOT_FOUND);
		
		//given in sewers
		ALCHEMY_GUIDE.pagesStates.put("Potions",                debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Stones",                 debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Energy_Food",            debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Exotic_Potions",         debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Exotic_Scrolls",         debug ? READ : NOT_FOUND);
		//given in prison
		ALCHEMY_GUIDE.pagesStates.put("Bombs",                  debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Weapons",                debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Brews_Elixirs",          debug ? READ : NOT_FOUND);
		ALCHEMY_GUIDE.pagesStates.put("Spells",                 debug ? READ : NOT_FOUND);

		INTROS.pagesStates.put("Dungeon",                       READ);
		INTROS.pagesStates.put("Sewers",                        debug ? READ : NOT_FOUND);
		INTROS.pagesStates.put("Prison",                        debug ? READ : NOT_FOUND);
		INTROS.pagesStates.put("Caves",                         debug ? READ : NOT_FOUND);
		INTROS.pagesStates.put("City",                          debug ? READ : NOT_FOUND);
		INTROS.pagesStates.put("Halls",                         debug ? READ : NOT_FOUND);

		SEWERS_GUARD.pagesStates.put("new_position",            debug ? READ : NOT_FOUND);
		SEWERS_GUARD.pagesStates.put("dangerous",               debug ? READ : NOT_FOUND);
		SEWERS_GUARD.pagesStates.put("crabs",                   debug ? READ : NOT_FOUND);
		SEWERS_GUARD.pagesStates.put("guild",                   debug ? READ : NOT_FOUND);
		SEWERS_GUARD.pagesStates.put("lost",                    debug ? READ : NOT_FOUND);
		SEWERS_GUARD.pagesStates.put("not_worth",               debug ? READ : NOT_FOUND);

		PRISON_WARDEN.pagesStates.put("journal",                debug ? READ : NOT_FOUND);
		PRISON_WARDEN.pagesStates.put("recruits",               debug ? READ : NOT_FOUND);
		PRISON_WARDEN.pagesStates.put("mines",                  debug ? READ : NOT_FOUND);
		PRISON_WARDEN.pagesStates.put("rotberry",               debug ? READ : NOT_FOUND);
		PRISON_WARDEN.pagesStates.put("no_support",             debug ? READ : NOT_FOUND);
		PRISON_WARDEN.pagesStates.put("letter",                 debug ? READ : NOT_FOUND);

		CAVES_EXPLORER.pagesStates.put("expedition",            debug ? READ : NOT_FOUND);
		CAVES_EXPLORER.pagesStates.put("gold",                  debug ? READ : NOT_FOUND);
		CAVES_EXPLORER.pagesStates.put("troll",                 debug ? READ : NOT_FOUND);
		CAVES_EXPLORER.pagesStates.put("city",                  debug ? READ : NOT_FOUND);
		CAVES_EXPLORER.pagesStates.put("alive",                 debug ? READ : NOT_FOUND);
		CAVES_EXPLORER.pagesStates.put("report",                debug ? READ : NOT_FOUND);

		CITY_WARLOCK.pagesStates.put("old_king",                debug ? READ : NOT_FOUND);
		CITY_WARLOCK.pagesStates.put("resistance",              debug ? READ : NOT_FOUND);
		CITY_WARLOCK.pagesStates.put("failure",                 debug ? READ : NOT_FOUND);
		CITY_WARLOCK.pagesStates.put("more_powerful",           debug ? READ : NOT_FOUND);
		CITY_WARLOCK.pagesStates.put("new_power",               debug ? READ : NOT_FOUND);
		CITY_WARLOCK.pagesStates.put("seen_it",                 debug ? READ : NOT_FOUND);

		HALLS_KING.pagesStates.put("Rejection",                 debug ? READ : NOT_FOUND);
		HALLS_KING.pagesStates.put("amulet",                    debug ? READ : NOT_FOUND);
		HALLS_KING.pagesStates.put("ritual",                    debug ? READ : NOT_FOUND);
		HALLS_KING.pagesStates.put("new_king",                  debug ? READ : NOT_FOUND);
		HALLS_KING.pagesStates.put("thing",                     debug ? READ : NOT_FOUND);
		HALLS_KING.pagesStates.put(KING_ATTRITION,              debug ? NOT_FOUND : NOT_FOUND);

		JANE_PAGE.pagesStates.put("a",              debug ? NOT_FOUND : NOT_FOUND);
		JANE_PAGE.pagesStates.put("b",              debug ? NOT_FOUND : NOT_FOUND);
		JANE_PAGE.pagesStates.put("c",              debug ? NOT_FOUND : NOT_FOUND);
		JANE_PAGE.pagesStates.put("d",              debug ? NOT_FOUND : NOT_FOUND);
		JANE_PAGE.pagesStates.put("e",              debug ? NOT_FOUND : NOT_FOUND);
		JANE_PAGE.pagesStates.put("f",              debug ? NOT_FOUND : NOT_FOUND);

	}
	
	private static final String DOCUMENTS = "documents";
	
	public static void store( Bundle bundle ){
		
		Bundle docsBundle = new Bundle();
		
		for ( Document doc : values()){
			Bundle pagesBundle = new Bundle();
			boolean empty = true;
			for (String page : doc.pageNames()){
				if (doc.pagesStates.get(page) != NOT_FOUND){
					pagesBundle.put(page, doc.pagesStates.get(page));
					empty = false;
				}
			}
			if (!empty){
				docsBundle.put(doc.name(), pagesBundle);
			}
		}
		
		bundle.put( DOCUMENTS, docsBundle );
		
	}
	
	public static void restore( Bundle bundle ){
		
		if (!bundle.contains( DOCUMENTS )){
			return;
		}
		
		Bundle docsBundle = bundle.getBundle( DOCUMENTS );
		
		for ( Document doc : values()){
			if (docsBundle.contains(doc.name())){
				Bundle pagesBundle = docsBundle.getBundle(doc.name());

				for (String page : doc.pageNames()) {
					if (pagesBundle.contains(page)) {
						doc.pagesStates.put(page, pagesBundle.getInt(page));
					}
				}
			}
		}
	}
	
}
