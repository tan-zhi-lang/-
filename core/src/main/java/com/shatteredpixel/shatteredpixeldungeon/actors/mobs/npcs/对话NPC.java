

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd对话;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class 对话NPC extends NPC {

	{
		spriteClass = ImpSprite.class;
		属性表.add(Property.IMMOVABLE);
	}

	@Override
	public int 最大闪避(Char enemy ) {
		return Char.INFINITE;
	}

	@Override
	public void 受伤时(float dmg, Object 来源) {
		//do nothing
	}

	@Override
	public boolean add( Buff buff ) {
		return false;
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public boolean interact(Char c) {
		
		sprite.turnTo( pos, Dungeon.hero.pos );

		if (c != Dungeon.hero){
			return true;
		}
		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				GameScene.show( new Wnd对话(对话NPC.this,"标题","选择1","选择2"));
			}
		});

		return true;
	}

	public void flee() {
		
		yell("恭喜你完成了任务！");
		
		destroy();
		sprite.die();
	}

	public static class Quest {
		
		private static boolean spawned;
		
		public static void reset() {
			spawned = false;
		}
		
		private static final String NODE		= "demon";
		
		private static final String SPAWNED	= "spawned";
		
		public static void storeInBundle( Bundle bundle ) {
			
			Bundle node = new Bundle();
			
			node.put( SPAWNED, spawned );


			bundle.put( NODE, node );
		}
		
		public static void restoreFromBundle( Bundle bundle ) {

			Bundle node = bundle.getBundle( NODE );
			
			if (!node.isNull() && (spawned = node.getBoolean( SPAWNED ))) {
				spawned	= node.getBoolean( SPAWNED );

			}
		}

	}
}
