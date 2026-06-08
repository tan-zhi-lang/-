

package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Ghost;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.真正护符;
import com.shatteredpixel.shatteredpixeldungeon.items.技能书;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.SurfaceScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndMessage;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.utils.Callback;

public class 回廊1 extends Level {

	private static final int WSIZE = 20;
	private static final int HSIZE = 11;

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
	private static short[] 回廊1= {
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,
			4,7,1,2,1,2,1,2,1,14,1,2,1,2,1,2,1,14,4,4,
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,4,
			4,4,1,14,1,2,1,2,1,2,1,14,1,2,1,2,1,2,4,4,
			4,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,
			4,4,1,2,1,2,1,2,1,14,1,2,1,2,1,2,1,14,4,4,
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,4,
			4,4,1,14,1,2,1,2,1,2,1,14,1,2,1,2,1,2,4,4,
			4,1,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,
			4,4,1,2,1,2,1,2,1,14,1,2,1,2,1,2,1,14,8,4,
			4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,

	};
	@Override
	protected boolean build() {
		
		setSize(WSIZE, HSIZE);
		
		for (int i=0;i<回廊1.length;i++){
			 map[i] = 回廊1[i];
		}
		
		for (int i=0; i < HSIZE; i++) {
			for (int j=0; j < WSIZE; j++) {
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
			return super.activateTransition(hero, transition);
		}
		if (transition.type == LevelTransition.Type.SURFACE){
			if (hero.belongings.getItem( 真正护符.class)==null) {
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
						Dungeon.win( 真正护符.class);
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

	@Override
	public Mob createMob() {
		return null;
	}
	
	@Override
	protected void createMobs() {
		float x=1;
		for (int i=0; i < HSIZE; i++) {
			for (int j=0; j < WSIZE; j++) {
				int pos=i * width() + j;
				if(在草丛(pos)){
					x+=0.25f;
					Mob mob=new Rat();
					mob.生命=mob.最大生命=mob.最大生命*x*Dungeon.难度生命(mob);
					mob.pos=pos;

					Buff.施加(mob,MagicalSleep.class);

					mobs.add(mob);
				}
			}
		}
	}

	public Actor addRespawner() {
		return null;
	}

	@Override
	protected void createItems() {
		float x=0;
		for (int i=0; i < HSIZE; i++) {
			for (int j=0; j < WSIZE; j++){
				int pos=i*width()+j;
				if(在地板(pos)){
					drop(new 技能书(),pos).setHauntedIfCursed().type = Heap.Type.CHEST;
				}
			}
			}
	}
	
	@Override
	public int randomRespawnCell( Char ch ) {
		return exit;
	}

}
