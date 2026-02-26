

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择海克斯;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

public class 海克斯宝典 extends 用品 {
	
	
	{
		image = 物品表.海克斯宝典;
		可以空间=false;
		嬗变=false;
		可堆叠=false;
		特别= true;
	}

	public int 用过=0;
	private static final String 用过x=        "用过";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过x,用过);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过= bundle.getInt(用过x);
	}
	public static int 使用上限(){
		return 2+(Dungeon.符文("骰子收集者")?2:0)+(Dungeon.符文("刷新海克斯")?5:0);
	}
	@Override
	public void 使用(Hero hero){
		if(算法.isDebug())
		new 海克斯宝典().放背包();

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择海克斯(this,hero));
			hero.更新数据();
		});
		super.使用(hero);
	}

	@Override
	public int 金币(){
		return 1000;
	}
	@Override
	public int 能量(){
		return 50;
	}
}
