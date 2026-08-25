

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 传说之戒 extends Item {
	

	{
		image = 物品表.传说之戒;
		特别 = true;
		物品 = true;
		可以空间=false;
		嬗变= false;

		defaultAction=AC_转移;
	}

	public int 最大转移(){
		return 5;
	}

	@Override
	public String status() {
		if (levelKnown) {
			return 转移 + "/" + 最大转移();
		} else {
			return null;
		}
	}
	public int 转移;
	private static final String 转移x = "转移";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(转移x, 转移);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		转移 = bundle.getInt(转移x);
	}
	public static final String AC_转移        = "转移";
	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );

		actions.add(AC_转移);
		return actions;
	}

	@Override
	public int 强化等级(){
		return super.强化等级()+转移;
	}
	@Override
	public void execute(Hero hero,String action){
		super.execute(hero,action);

		if (action.equals(AC_转移)) {
			if(转移>=最大转移())
			GameScene.selectItem(new WndBag.ItemSelector() {

				@Override
				public String textPrompt() {
					return "选择一枚戒指来此戒等级转移至选择戒指！";
				}

				@Override
				public boolean itemSelectable(Item item) {
					return item instanceof Ring;
				}

				@Override
				public void onSelect(Item item) {
					item.升级(转移);
					转移=0;
					hero.sprite.operate(hero.pos);
					hero.spend(1f);
					hero.busy();
				}
			});
			else
				GameScene.selectItem(new WndBag.ItemSelector() {

					@Override
					public String textPrompt() {
						return "选择一枚戒指来将其等级转移至此戒！";
					}

					@Override
					public boolean itemSelectable(Item item) {
						return item instanceof Ring;
					}

					@Override
					public void onSelect(Item item) {
						转移等级(item);
						hero.sprite.operate(hero.pos);
						hero.spend(1f);
						hero.busy();
					}
				});
		}
	}

	public void 转移等级(Item w){
		转移=Item.转移等级(w,this,最大转移(),等级());
	}
}
