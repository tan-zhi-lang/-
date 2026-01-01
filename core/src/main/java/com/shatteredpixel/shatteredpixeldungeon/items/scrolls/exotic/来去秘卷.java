

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUseItem;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Game;

import java.util.ArrayList;

public class 来去秘卷 extends ExoticScroll {
	
	{
		icon = 物品表.Icons.SCROLL_PASSAGE;
		defaultAction=AC_下楼;
	}
	
	protected static final String AC_上楼 = "上楼";
	protected static final String AC_下楼 = "下楼";
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_上楼 );
		actions.add( AC_下楼 );
		return actions;
	}
	
	@Override
	public void execute(Hero hero,String action){
		super.execute(hero,action);
		if (action.equals( AC_上楼 )) {
			
			
			if ((!Dungeon.interfloorTeleportAllowed()
				|| Dungeon.depth==1)&&!算法.isDebug()) {
				GLog.w( Messages.get(传送卷轴.class,"no_tele"));
				return;
			}
			
			Level.beforeTransition();
			InterlevelScene.mode = InterlevelScene.Mode.RETURN;
			InterlevelScene.returnDepth = Dungeon.depth - 1;
			InterlevelScene.returnBranch = 0;
			InterlevelScene.returnPos = -2;
			Game.switchScene( InterlevelScene.class );
		}
		if (action.equals( AC_下楼 )) {
			
			
			if ( (!Dungeon.interfloorTeleportAllowed()
				 || Dungeon.depth==26)&&!算法.isDebug()) {
				GLog.w( Messages.get(传送卷轴.class,"no_tele"));
				return;
			}
			
			Level.beforeTransition();
			InterlevelScene.mode = InterlevelScene.Mode.FALL;
			InterlevelScene.returnDepth = Dungeon.depth +1;
			InterlevelScene.returnBranch = 0;
			InterlevelScene.returnPos = 2;
			
			Buff.施加(Dungeon.hero,Invulnerability.class,3);
			Buff.施加(Dungeon.hero,BlobImmunity.class,3);
			Game.switchScene( InterlevelScene.class );
		}
	}
	
	@Override
	public void doRead() {

		detach(curUser.belongings.backpack);
		鉴定();
		readAnimation();
		
		GameScene.show(new WndUseItem(null,this));
	}
	
}
