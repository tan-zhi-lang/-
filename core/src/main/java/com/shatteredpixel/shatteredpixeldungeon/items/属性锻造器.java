

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择属性;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 属性锻造器 extends 用品 {
	
	
	{
		image = 物品表.属性锻造器;
		嬗变=false;
		可以空间=false;
		可堆叠=false;
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
		Sample.INSTANCE.play(Assets.Sounds.海克斯,1.75f);

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择属性(this,hero));
		});
		super.使用(hero);
	}

	@Override
	public int 金币() {
		return 750;
	}
}
