

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 海克斯移除器 extends 用品 {
	
	
	{
		image = 物品表.海克斯移除器;
	}

	@Override
	public void 使用(Hero hero){

		Sample.INSTANCE.play(Assets.Sounds.海克斯);

		String b=desc();

		GameScene.show(new WndTextInput("海克斯移除",
										b,
										移除,
										50,
										false,
										"确定",
										"取消"){
			@Override
			public void onSelect(boolean positive, String text) {
				移除=text;
				if (positive && !移除.isEmpty()){
					if(hero.是海克斯(移除)){
						Sample.INSTANCE.play(Assets.Sounds.海克斯);
						if(hero.符文(移除))hero.删除海克斯(移除);
						else hero.重置海克斯(移除);
						return;
					}
				}

				GLog.w("你输入的海克斯名字不存在！");
				new 海克斯移除器().放背包();
			}
		});
		super.使用(hero);
	}

	public String 移除="";
	private static final String 移除x=        "移除";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(移除x,移除);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		移除= bundle.getString(移除x);
	}
}
