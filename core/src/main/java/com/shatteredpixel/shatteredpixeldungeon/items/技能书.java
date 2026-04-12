

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择技能;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

public class 技能书 extends 用品 {
	
	
	{
		image = 物品表.技能书;
		可以空间=false;
		嬗变=false;
		可堆叠=false;
		特别= true;
	}

	public int 用过=0;
	public int 都要=0;
	private static final String 用过x=        "用过";
	private static final String 都要x=        "都要";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过x,用过);
		bundle.put(都要x,都要);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过= bundle.getInt(用过x);
		都要= bundle.getInt(都要x);
	}
	public int 使用上限(){
		return 3-用过;
	}
	public int 都要上限(){
		return 2-都要;
	}
	@Override
	public void 使用(Hero hero){

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择技能(this,hero));
			hero.更新数据();
		});


//		Dungeon.保存游戏();
		super.使用(hero);
	}
}
