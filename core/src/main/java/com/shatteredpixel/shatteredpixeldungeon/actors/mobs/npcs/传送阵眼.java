

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.矮人徽章;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.传送阵眼动画;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Game;

public class 传送阵眼 extends NPC {


	{
		spriteClass = 传送阵眼动画.class;
	}


	@Override
	public int 最大闪避(Char enemy) {
		return Char.INFINITE;
	}

	@Override
	public void 受伤时(float dmg, Object src ) {
		//do nothing
	}

	@Override
	public boolean add( Buff buff ) {
		return false;
	}

	@Override
	public boolean interact(Char c) {
		boolean k=false;
		if(算法.isDebug()){
			k=true;
		}
		if(c instanceof Hero hero){

			if(!Imp.Quest.isCompleted()){
				GLog.w("你没有使用传送阵的权限！");
			}

			Item i=hero.belongings.getItem(矮人徽章.class);
			if(i==null){
				GLog.w("你没有使用传送阵的物品！");
			}else {
				if(i.数量()==1){
					i.detachAll(hero.belongings.backpack);
					k=true;
				}else if(i.数量()>1){
					i.split(1).detach(hero.belongings.backpack);
					k=true;
				}
			}

		if(k){
			GLog.w("你使用了矮人魔法传送阵！");

			Level.beforeTransition();
			if(Dungeon.相对层数()==11){
				InterlevelScene.mode=InterlevelScene.Mode.GOTO;
				InterlevelScene.returnDepth=Dungeon.depth+7;
				InterlevelScene.returnBranch=0;
				InterlevelScene.returnPos=0;
			}else if(Dungeon.相对层数()==18){
				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = Dungeon.depth - 7;
				InterlevelScene.returnBranch = 0;
				InterlevelScene.returnPos = 0;
			}
			Game.switchScene(InterlevelScene.class);
		}else
			return false;

		}
		return true;
	}
}