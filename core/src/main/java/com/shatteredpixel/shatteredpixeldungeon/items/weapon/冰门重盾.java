

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 冰门重盾 extends Weapon{

	{
		image = 物品表.冰门重盾;
		hitSound = Assets.Sounds.盾牌;
		特别= true;
		defaultAction=AC_转移;
		缴械= false;
		嬗变= false;
		专属=true;
		具备防御=true;
		魔法=0.15f;
		伤害= 0.7f;
		tier = 1;
	}

	public int 最大转移(){
		return 3+Dungeon.hero.天赋点数(Talent.高阶盾武,2);
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
	public static final String AC_转移		= "转移";
	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if(!hero.subClass(HeroSubClass.盾之勇者)) {
			actions.remove(AC_UNEQUIP);
			actions.remove(AC_THROW);
		}
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

			GameScene.selectItem(new WndBag.ItemSelector() {

				@Override
				public String textPrompt() {
					return "选择一件武器来将其等级转移至重盾！";
				}

				@Override
				public boolean itemSelectable(Item item) {
					return item instanceof Weapon;
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

		if(w.真等级()>0)
		转移=Item.转移等级(w,最大转移(),等级());
	}


	@Override
	public int tier(){
		return Math.min(5,super.tier()+Dungeon.hero.天赋点数(Talent.高阶盾武));
	}
	@Override
	public int 能量() {
		return 0;
	}
	@Override
	public int 金币() {
		return 0;
	}

	@Override
	public float 命中(){
		return super.命中()+Dungeon.hero.天赋点数(Talent.用盾诀窍,0.075f);
	}

	@Override
	public float 吸血(){
		return super.吸血()+Dungeon.hero.天赋点数(Talent.用盾诀窍,0.03f);
	}

	@Override
	public float 防御(){
		return super.防御()+Dungeon.hero.天赋点数(Talent.冰门高防,0.5f);
	}


}