

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 海克斯宝典 extends 用品 {
	
	
	{
		image = 物品表.海克斯宝典;
		可以空间=false;
		嬗变=false;
		可堆叠=false;
		特别= true;
	}

	public String 祈愿="";
	private static final String 祈愿x=        "祈愿";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(祈愿x,祈愿);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		祈愿= bundle.getString(祈愿x);
	}
	@Override
	public void 使用(Hero hero){

		Dungeon.保存游戏();//每次之前保存
			String b="输入海克斯名字并获得";
			GameScene.show(new WndTextInput("海克斯获得",
											b,
											祈愿,
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					祈愿=text;
					if (positive && !祈愿.isEmpty()){
						if(hero.是海克斯(祈愿)&&!hero.符文(祈愿)){
							Sample.INSTANCE.play(Assets.Sounds.海克斯);
								hero.选择海克斯(祈愿);

							detach();
						}else
							GLog.橙("你输入的海克斯名字不存在！");
					}
				}
			});

		super.使用(hero);
	}

	@Override
	public int 金币(){
		return 75*8*6;
	}
	@Override
	public int 能量(){
		return 15*2*6;
	}
}
