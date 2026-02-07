

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择属性;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;

public class 属性锻造器 extends 用品 {
	
	
	{
		image = 物品表.属性锻造器;
	}
	
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.海克斯);

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择属性(hero));
		});
		super.使用(hero);
	}

}
