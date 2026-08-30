

package com.shatteredpixel.shatteredpixeldungeon.levels.features;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;

public class Door {

	public static void enter( int pos ) {
		Level.set( pos, Terrain.OPEN_DOOR );
		GameScene.updateMap( pos );

		if (Dungeon.level.heroFOV[pos]) {
			Dungeon.observe();


			if(Dungeon.区域()==5){
				Sample.INSTANCE.play( Assets.Sounds.地狱门 );
			}else if(Dungeon.区域()==4){
				Sample.INSTANCE.play( Assets.Sounds.石门 );
			}else if(Dungeon.区域()==3){
				Sample.INSTANCE.play( Assets.Sounds.洞穴门 );
			}else if(Dungeon.区域()==2){
				Sample.INSTANCE.play( Assets.Sounds.监狱门 );
			}else
			Sample.INSTANCE.play( Assets.Sounds.木门 );
		}
	}

	public static void leave( int pos ) {
		int chars = 0;
		
		for (Char ch : Actor.chars()){
			if (ch.pos == pos) chars++;
		}
		
		//door does not shut if anything else is also on it
		if (Dungeon.level.heaps.get( pos ) == null && chars <= 1) {
			Level.set( pos, Terrain.DOOR );
			GameScene.updateMap( pos );
			if (Dungeon.level.heroFOV[pos])
				Dungeon.observe();

//			if(Dungeon.区域()==5){
//				Sample.INSTANCE.play( Assets.Sounds.地狱门,0.8f );
//			}else if(Dungeon.区域()==4){
//				Sample.INSTANCE.play( Assets.Sounds.石门 ,0.8f );
//			}else if(Dungeon.区域()==3){
//				Sample.INSTANCE.play( Assets.Sounds.洞穴门,0.8f  );
//			}else if(Dungeon.区域()==2){
//				Sample.INSTANCE.play( Assets.Sounds.监狱门,0.8f  );
//			}else
//				Sample.INSTANCE.play( Assets.Sounds.木门 ,0.8f );
		}
	}
}
