

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;

public class 来去秘卷 extends ExoticScroll {
	
	{
		icon = 物品表.Icons.SCROLL_PASSAGE;
	}
	
	@Override
	public void doRead() {

		detach(curUser.belongings.backpack);
		鉴定();
		readAnimation();
		
		if (!Dungeon.interfloorTeleportAllowed()) {
			
			GLog.w( Messages.get(ScrollOfTeleportation.class, "no_tele") );
			return;
			
		}
		if(Dungeon.level.map[Dungeon.hero.pos]==Terrain.ENTRANCE){
			
			Level.beforeTransition();
			InterlevelScene.mode = InterlevelScene.Mode.FALL;
			InterlevelScene.returnDepth = Dungeon.depth +1;
			InterlevelScene.returnBranch = 0;
			InterlevelScene.returnPos = 2;
			
			Buff.施加(Dungeon.hero,Invulnerability.class,3);
			Buff.施加(Dungeon.hero,BlobImmunity.class,3);
			Game.switchScene( InterlevelScene.class );
		}else if(Dungeon.level.map[Dungeon.hero.pos]==Terrain.EXIT){
			Level.beforeTransition();
			InterlevelScene.mode = InterlevelScene.Mode.RETURN;
			InterlevelScene.returnDepth = Dungeon.depth - 1;
			InterlevelScene.returnBranch = 0;
			InterlevelScene.returnPos = -2;
			Game.switchScene( InterlevelScene.class );
		}else{
			ScrollOfTeleportation.teleportPreferringUnseen(Dungeon.hero);
		}
	}
	
}
