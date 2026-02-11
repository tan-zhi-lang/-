

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择海克斯;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

public class 海克斯宝典 extends 用品 {
	
	
	{
		image = 物品表.海克斯宝典;
		可以空间=false;
		嬗变=false;
		特别= true;
	}

	public boolean 用过=false;
	private static final String 用过x=        "用过";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过x,用过);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过= bundle.getBoolean(用过x);
	}
	@Override
	public void 使用(Hero hero){

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择海克斯(this,hero));
			hero.更新数据();
		});
		super.使用(hero);
	}

}
