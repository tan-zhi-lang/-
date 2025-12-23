

package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.SurfaceScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndMessage;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.utils.Callback;

public class 下水道1 extends Level {

	private static final int SIZE = 15;
	
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
			4,4,12,4,4,4,4,12,4,4,4,4,12,4,4,
			4,0,0,0,0,0,0,0,0,0,0,0,0,0,4,
			4,0,0,0,0,0,25,2,25,0,0,0,0,0,4,
			4,0,0,0,0,0,2,7,2,0,0,0,0,0,4,
			4,0,0,0,14,0,25,2,25,0,14,0,0,0,4,
			4,0,0,0,0,0,0,2,0,0,0,0,0,0,4,
			4,0,0,0,0,14,0,2,0,14,0,0,0,0,4,
			4,0,0,0,0,14,0,2,0,14,0,0,0,0,4,
			4,0,0,0,0,14,0,2,0,14,0,0,0,0,4,
			4,0,0,0,0,0,0,2,0,0,0,0,0,0,4,
			4,0,0,0,14,0,25,2,25,0,14,0,0,0,4,
			4,0,0,0,0,0,2,8,2,0,0,0,0,0,4,
			4,0,0,0,0,0,25,2,25,0,0,0,0,0,4,
			4,0,0,0,0,0,0,0,0,0,0,0,0,0,4,
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4
		
		
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
	
	@Override
	public boolean activateTransition(Hero hero,LevelTransition transition) {
		if (transition.type == LevelTransition.Type.REGULAR_EXIT){
			if(hero.地牢塔防去下一层){
				hero.地牢塔防去下一层=false;
				return super.activateTransition(hero, transition);
			}
			return false;
		}
		if (transition.type == LevelTransition.Type.SURFACE){
			if (hero.belongings.getItem( Amulet.class)==null) {
				Game.runOnRenderThread(new Callback() {
					@Override
					public void call() {
						GameScene.show(new WndMessage(Messages.get(hero,"leave") ));
					}
				});
				return false;
			} else {
				Statistics.ascended = true;
				Game.switchScene(SurfaceScene.class,new Game.SceneChangeCallback() {
					@Override
					public void beforeCreate() {
					
					}
					
					@Override
					public void afterCreate() {
						Badges.validateHappyEnd();
						Dungeon.win( Amulet.class );
						Dungeon.deleteGame(GamesInProgress.curSlot,true);
						Badges.saveGlobal();
					}
				});
				return true;
			}
		} else {
			return super.activateTransition(hero, transition);
		}
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
