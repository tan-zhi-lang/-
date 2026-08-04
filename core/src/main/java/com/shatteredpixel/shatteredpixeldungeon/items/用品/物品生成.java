

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd物品代码名;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Bundle;

public class 物品生成 extends 用品{
	
	
	{
		重复使用=true;

		黑色=true;
		白光=true;

		不能丢扔=true;
		金币价值=0;
		能量价值=0;
	}

	public String 代码="";
	private static final String 代码x=        "代码";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(代码x,代码);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		代码= bundle.getString(代码x);
	}
	@Override
	public void 使用(Hero hero){

		GameScene.show(new Wnd物品代码名("物品生成",
										 desc(),
										 代码,
										 50,
										 false,
										 "确定",
										 "取消"){
			@Override
			public void onSelect(boolean positive, String text) {
				if (positive && !text.isEmpty()){
					if(算法.物品(text)!=null){
						代码=text;
						Item item=算法.物品(text);
						item.鉴定(鉴定);
						if(item instanceof EquipableItem&&诅咒){
							item.cursed=诅咒;
							item.cursedKnown=诅咒;
						}
						GLog.黄("你生成了"+item.数量()+"个"+item.name());
						item.放背包();
//						Dungeon.level.drop(item,hero.pos);
					}else
						GLog.橙("你输入的代码名不存在！");
				}
			}
		});
		super.使用(hero);
	}

}
