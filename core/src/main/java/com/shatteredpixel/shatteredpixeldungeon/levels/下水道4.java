

package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.watabou.noosa.audio.Music;

public class 下水道4 extends Level {

	private static final int SIZE = 21;
	
	{
		color1 = 0x534f3e;
		color2 = 0xb9d661;
	}
	
	@Override
	public String tilesTex() {
		return Assets.Environment.TILES_SEWERS;
	}
	
	@Override
	public String waterTex() {
		return Assets.Environment.WATER_SEWERS;
	}
	
	public static final String[] SEWER_TRACK_LIST
			= new String[]{Assets.Music.SEWERS_1, Assets.Music.SEWERS_2, Assets.Music.SEWERS_2,
						   Assets.Music.SEWERS_1, Assets.Music.SEWERS_3, Assets.Music.SEWERS_3};
	public static final float[] SEWER_TRACK_CHANCES = new float[]{1f, 1f, 0.5f, 0.25f, 1f, 0.5f};
	
	public void playLevelMusic(){
		if (Ghost.Quest.active()||Statistics.amuletObtained){
			if (Statistics.amuletObtained && Dungeon.depth == 1){
				Music.INSTANCE.play(Assets.Music.THEME_FINALE,true);
			} else {
				Music.INSTANCE.play(Assets.Music.SEWERS_TENSE, true);
			}
		} else {
			Music.INSTANCE.playTracks(SEWER_TRACK_LIST, SEWER_TRACK_CHANCES, false);
		}
	}
	private static short[] 下水道1 = {
			4,4,12,4,4,4,4,4,4,4,12,4,4,4,4,4,4,4,12,4,4,
			4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,
			4,0,0,0,0,0,0,14,14,14,0,0,0,2,2,2,2,2,0,0,4,
			4,0,0,0,0,0,0,0,0,0,0,14,0,2,0,0,0,2,0,0,4,
			4,0,0,0,0,14,0,2,2,2,0,14,0,2,0,14,0,2,0,0,4,
			4,0,0,0,0,0,0,2,0,2,0,0,0,2,0,14,0,2,0,0,4,
			4,0,0,0,14,0,2,2,0,2,0,14,0,2,0,14,0,2,0,0,4,
			4,0,0,0,0,0,2,0,0,2,0,14,0,2,0,0,0,2,0,0,4,
			4,0,0,14,0,2,2,0,0,2,0,0,0,2,0,0,25,2,25,0,4,
			4,0,0,0,0,2,0,0,0,2,2,2,2,2,0,0,2,8,2,0,4,
			4,0,14,0,2,2,0,0,0,0,0,0,0,0,0,0,25,2,25,0,4,
			4,0,0,0,2,0,0,14,14,14,0,0,14,14,14,0,0,0,0,0,4,
			4,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,
			4,0,0,2,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,0,4,
			4,0,2,2,0,14,0,2,0,0,0,0,0,0,0,0,0,0,2,0,4,
			4,0,2,0,0,14,0,2,0,0,0,0,0,14,14,14,14,0,2,0,4,
			4,0,2,0,0,0,0,2,0,25,2,25,0,0,0,0,0,0,2,0,4,
			4,0,2,2,2,2,2,2,0,2,7,2,2,2,2,2,2,2,2,0,4,
			4,0,0,0,0,0,0,0,0,25,2,25,0,0,0,0,0,0,0,0,4,
			4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4
		
		
	};
	@Override
	protected boolean build() {
		
		setSize(SIZE, SIZE);
		
		
		
		for (int i = 0; i < 下水道1.length; i++){
			 map[i] = 下水道1[i];
		}
		
		for (int i=2; i < SIZE; i++) {
			for (int j=2; j < SIZE; j++) {
				if(map[i * width() + j]==7){
					this.entrance=i * width() + j;
				}
				if(map[i * width() + j]==8){
					this.exit=i * width() + j;
				}
			}
		}
//		different exit behaviour depending on main branch or side one
		if (Dungeon.branch==0) {
			transitions.add(new LevelTransition(this,entrance,LevelTransition.Type.SURFACE));
			transitions.add(new LevelTransition(this,exit,LevelTransition.Type.REGULAR_EXIT));
		}
		
		return true;
	}
	private int mapToTerrain(int i) {
		if (!(i == 1 || i == 2)) {
			if (i != 3) {
				if (i != 4) {
					if (i == 16) {
						return 7;
					}
					if (i == 17) {
						return 8;
					}
					switch (i) {
						case -2147483644:
							break;
						case -2147483584:
						case 64:
						case 190:
							return 4;
						case -2147483550:
						case 98:
							return 25;
						case -2147483524:
						case 124:
						case 140:
							return 27;
						case 4:
							return 14;
						case 69:
							return 12;
						case 80:
							return SIZE;
						case 85:
							return 11;
						case 96:
							return 23;
						case 120:
							return 20;
						case 123:
							return 29;
						case 161:
							return 12;
						default:
							return 1;
					}
				}
				return 14;
			}
		}
		return 29;
	}
	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
	}

	public Actor addRespawner() {
		return null;
	}

	@Override
	protected void createItems() {
	}
	
	@Override
	public int randomRespawnCell( Char ch ) {
		return exit;
	}

}
