

package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;
import com.watabou.utils.Random;

import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Holiday {

	NONE,元旦节,
	春节,
	元宵节,
	妇女节,
	植树节,
	复活节,
	清明节,愚人节,
	端午节,
	游戏周年,
	_520,
	_618,
	彩虹节,
	七夕节,
	中元节,
	SHATTEREDPD_BIRTHDAY,
	中秋节,国庆节,_1024,
	万圣节,
	_1111,
	感恩节,
	PD_BIRTHDAY,
	圣诞节
	;

	//total of 61-62 festive days each year, mainly concentrated in Late Oct to Early Feb

	//we cache the holiday here so that holiday logic doesn't suddenly shut off mid-game
	//this gets cleared on game launch (of course), and whenever leaving a game scene
	private static Holiday cached;
	public static void clearCachedHoliday(){
		cached = null;
	}

	public static Holiday getCurrentHoliday(){
		if (cached == null){
			cached = getHolidayForDate((GregorianCalendar) GregorianCalendar.getInstance());
		}
		return cached;
	}
	//requires a gregorian calendar
	public static boolean 星期(GregorianCalendar cal,int x){
		int monthday=7;
		switch(x){
			case 1:return cal.get(Calendar.DAY_OF_MONTH)<=monthday;
			case 2:return cal.get(Calendar.DAY_OF_MONTH)>=monthday&&cal.get(Calendar.DAY_OF_MONTH)<=2*monthday;
			case 3:return cal.get(Calendar.DAY_OF_MONTH)>=2*monthday&&cal.get(Calendar.DAY_OF_MONTH)<=3*monthday;
			case 4:return cal.get(Calendar.DAY_OF_MONTH)>=3*monthday;
			case 23:return cal.get(Calendar.DAY_OF_MONTH)>=monthday&&cal.get(Calendar.DAY_OF_MONTH)<=3*monthday;
			case 12:return cal.get(Calendar.DAY_OF_MONTH)<=2*monthday;
			case 123:return cal.get(Calendar.DAY_OF_MONTH)<=3*monthday;
			case 234:return cal.get(Calendar.DAY_OF_MONTH)>=monthday;
			case 34:return cal.get(Calendar.DAY_OF_MONTH)>=2*monthday;
		}
		return false;
	}
	public static Holiday getHolidayForDate(GregorianCalendar cal){
		
		if(SPDSettings.从不过节())return NONE;
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY){
			if(星期(cal,1))
			return 元旦节;
			if(星期(cal,23))
			return Random.oneOf(元旦节,春节);//1月第2、3个星期
			if(星期(cal,4))
			return 春节;
		}
		if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY){
			if(星期(cal,234))
				return Random.oneOf(春节);
		}
		if (cal.get(Calendar.MONTH) == Calendar.MARCH){
			if(星期(cal,1))
				return 妇女节;
			if(星期(cal,23))
				return Random.oneOf(妇女节,植树节,复活节);
			if(星期(cal,4))
				return 复活节;//3月第4个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.APRIL){
			if(星期(cal,12))
				return Random.oneOf(清明节,愚人节);//4月第1、2个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.MAY){
			if(星期(cal,23))
				return Random.oneOf(游戏周年,_520);//5月第2、3个星期
			if(星期(cal,4))
				return Random.oneOf(游戏周年,_520,端午节);
		}
		if (cal.get(Calendar.MONTH) == Calendar.JUNE){
			if(星期(cal,1))
				return 端午节;
			if(星期(cal,23))
				return Random.oneOf(_618,端午节);//6月第2、3个星期
			if(星期(cal,4))
				return 端午节;
		}
		if (cal.get(Calendar.MONTH) == Calendar.AUGUST){
			if(星期(cal,12))
				return Random.oneOf(SHATTEREDPD_BIRTHDAY,七夕节);//8月第1、2个星期
			if(星期(cal,34))
				return Random.oneOf(中元节,七夕节);//8月第3、4个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER){
			if(星期(cal,1))
				return 中元节;//9月第1个星期
			if(星期(cal,23))
				return Random.oneOf(中元节,中秋节);
			if(星期(cal,4))
				return 中秋节;//9月第4个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.OCTOBER){
			if(星期(cal,1))
			return Random.oneOf(中秋节,国庆节);//10月第1个星期
			if(星期(cal,2))
			return Random.oneOf(中秋节,国庆节,_1024);
			if(星期(cal,34))
			return _1024;//10月第3、4个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER){
				if(星期(cal,1))
			return 万圣节;//11月第1个星期
			if(星期(cal,23))
			return Random.oneOf(万圣节,_1111,感恩节);
				if(星期(cal,4))
			return 感恩节;//11月第4个星期
		}
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER){
			if(星期(cal,12))
			return PD_BIRTHDAY;//12月第1、2个星期
			if(星期(cal,34))
			return 圣诞节;//12月第3、4个星期
		}
		//破碎和像素地牢游戏周年原先1个星期

		return NONE;
	}

	//has to be hard-coded on a per-year basis =S
	public static boolean isLunarNewYear(int year, int dayOfYear){
		int lunarNewYearDayOfYear;
		switch (year){
			//yes, I really did hardcode this all the way from 2020 to 2100
			default:   lunarNewYearDayOfYear = 31+5; break;     //defaults to February 5th
			case 2020: lunarNewYearDayOfYear = 25; break;       //January 25th
			case 2021: lunarNewYearDayOfYear = 31+12; break;    //February 12th
			case 2022: lunarNewYearDayOfYear = 31+1; break;     //February 1st
			case 2023: lunarNewYearDayOfYear = 22; break;       //January 22nd
			case 2024: lunarNewYearDayOfYear = 31+10; break;    //February 10th
			case 2025: lunarNewYearDayOfYear = 29; break;       //January 29th
			case 2026: lunarNewYearDayOfYear = 31+17; break;    //February 17th
			case 2027: lunarNewYearDayOfYear = 31+6; break;     //February 6th
			case 2028: lunarNewYearDayOfYear = 26; break;       //January 26th
			case 2029: lunarNewYearDayOfYear = 31+13; break;    //February 13th
			case 2030: lunarNewYearDayOfYear = 31+3; break;     //February 3rd
			case 2031: lunarNewYearDayOfYear = 23; break;       //January 23rd
			case 2032: lunarNewYearDayOfYear = 31+11; break;    //February 11th
			case 2033: lunarNewYearDayOfYear = 31; break;       //January 31st
			case 2034: lunarNewYearDayOfYear = 31+19; break;    //February 19th
			case 2035: lunarNewYearDayOfYear = 31+8; break;     //February 8th
			case 2036: lunarNewYearDayOfYear = 28; break;       //January 28th
			case 2037: lunarNewYearDayOfYear = 31+15; break;    //February 15th
			case 2038: lunarNewYearDayOfYear = 31+4; break;     //February 4th
			case 2039: lunarNewYearDayOfYear = 24; break;       //January 24th
			case 2040: lunarNewYearDayOfYear = 31+12; break;    //February 12th
			case 2041: lunarNewYearDayOfYear = 31+1; break;     //February 1st
			case 2042: lunarNewYearDayOfYear = 22; break;       //January 22nd
			case 2043: lunarNewYearDayOfYear = 31+10; break;    //February 10th
			case 2044: lunarNewYearDayOfYear = 30; break;       //January 30th
			case 2045: lunarNewYearDayOfYear = 31+17; break;    //February 17th
			case 2046: lunarNewYearDayOfYear = 31+6; break;     //February 6th
			case 2047: lunarNewYearDayOfYear = 26; break;       //January 26th
			case 2048: lunarNewYearDayOfYear = 31+14; break;    //February 14th
			case 2049: lunarNewYearDayOfYear = 31+2; break;     //February 2nd
			case 2050: lunarNewYearDayOfYear = 23; break;       //January 23rd
			case 2051: lunarNewYearDayOfYear = 31+11; break;    //February 11th
			case 2052: lunarNewYearDayOfYear = 31+1; break;     //February 1st
			case 2053: lunarNewYearDayOfYear = 31+19; break;    //February 19th
			case 2054: lunarNewYearDayOfYear = 31+8; break;     //February 8th
			case 2055: lunarNewYearDayOfYear = 28; break;       //January 28th
			case 2056: lunarNewYearDayOfYear = 31+15; break;    //February 15th
			case 2057: lunarNewYearDayOfYear = 31+4; break;     //February 4th
			case 2058: lunarNewYearDayOfYear = 24; break;       //January 24th
			case 2059: lunarNewYearDayOfYear = 31+12; break;    //February 12th
			case 2060: lunarNewYearDayOfYear = 31+2; break;     //February 2nd
			case 2061: lunarNewYearDayOfYear = 21; break;       //January 21st
			case 2062: lunarNewYearDayOfYear = 31+9; break;     //February 9th
			case 2063: lunarNewYearDayOfYear = 29; break;       //January 29th
			case 2064: lunarNewYearDayOfYear = 31+17; break;    //February 17th
			case 2065: lunarNewYearDayOfYear = 31+5; break;     //February 5th
			case 2066: lunarNewYearDayOfYear = 26; break;       //January 26th
			case 2067: lunarNewYearDayOfYear = 31+14; break;    //February 14th
			case 2068: lunarNewYearDayOfYear = 31+3; break;     //February 3rd
			case 2069: lunarNewYearDayOfYear = 23; break;       //January 23rd
			case 2070: lunarNewYearDayOfYear = 31+11; break;    //February 11th
			case 2071: lunarNewYearDayOfYear = 31; break;       //January 31st
			case 2072: lunarNewYearDayOfYear = 31+19; break;    //February 19th
			case 2073: lunarNewYearDayOfYear = 31+7; break;     //February 7th
			case 2074: lunarNewYearDayOfYear = 27; break;       //January 27th
			case 2075: lunarNewYearDayOfYear = 31+15; break;    //February 15th
			case 2076: lunarNewYearDayOfYear = 31+5; break;     //February 5th
			case 2077: lunarNewYearDayOfYear = 24; break;       //January 24th
			case 2078: lunarNewYearDayOfYear = 31+12; break;    //February 12th
			case 2079: lunarNewYearDayOfYear = 31+2; break;     //February 2nd
			case 2080: lunarNewYearDayOfYear = 22; break;       //January 22nd
			case 2081: lunarNewYearDayOfYear = 31+9; break;     //February 9th
			case 2082: lunarNewYearDayOfYear = 29; break;       //January 29th
			case 2083: lunarNewYearDayOfYear = 31+17; break;    //February 17th
			case 2084: lunarNewYearDayOfYear = 31+6; break;     //February 6th
			case 2085: lunarNewYearDayOfYear = 26; break;       //January 26th
			case 2086: lunarNewYearDayOfYear = 31+14; break;    //February 14th
			case 2087: lunarNewYearDayOfYear = 31+3; break;     //February 3rd
			case 2088: lunarNewYearDayOfYear = 24; break;       //January 24th
			case 2089: lunarNewYearDayOfYear = 31+10; break;    //February 10th
			case 2090: lunarNewYearDayOfYear = 30; break;       //January 30th
			case 2091: lunarNewYearDayOfYear = 31+18; break;    //February 18th
			case 2092: lunarNewYearDayOfYear = 31+7; break;     //February 7th
			case 2093: lunarNewYearDayOfYear = 27; break;       //January 27th
			case 2094: lunarNewYearDayOfYear = 31+15; break;    //February 15th
			case 2095: lunarNewYearDayOfYear = 31+5; break;     //February 5th
			case 2096: lunarNewYearDayOfYear = 25; break;       //January 25th
			case 2097: lunarNewYearDayOfYear = 31+12; break;    //February 12th
			case 2098: lunarNewYearDayOfYear = 31+1; break;     //February 1st
			case 2099: lunarNewYearDayOfYear = 21; break;       //January 21st
			case 2100: lunarNewYearDayOfYear = 31+9; break;     //February 9th
		}

		//celebrate for 7 days total, with Lunar New Year on the 5th day
		return dayOfYear >= lunarNewYearDayOfYear-4 && dayOfYear <= lunarNewYearDayOfYear+2;
	}

	//has to be algorithmically computed =S
	public static boolean isEaster(int year, int dayOfYear, boolean isLeapYear){
		//if we're not in March or April, just skip out of all these calculations
		if (dayOfYear < 59 || dayOfYear > 121) {
			return false;
		}

		//Uses the Anonymous Gregorian Algorithm
		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (a*19 + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2*e + 2*i - h - k) % 7;
		int m = (a + h*11 + l*22)/451;
		int n = (h + l - m*7 + 114) / 31;
		int o = (h + l - m*7 + 114) % 31;

		int easterDayOfYear = 0;

		if (n == 3){
			easterDayOfYear += 59; //march
		} else {
			easterDayOfYear += 90; //april
		}

		if (isLeapYear) {
			easterDayOfYear += 1; //add an extra day to account for February 29th
		}

		easterDayOfYear += (o+1); //add day of month

		//celebrate for 7 days total, with Easter Sunday on the 5th day
		return dayOfYear >= easterDayOfYear-4 && dayOfYear <= easterDayOfYear+2;
	}

}
