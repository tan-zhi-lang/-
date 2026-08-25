

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.幸运之泉;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Transmuting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 转级菱晶 extends InventorySpell {
	
	{
		image = 物品表.RECYCLE;
		icon = 物品表.Icons.转换;

		talentFactor = 2;
		talentChance = 1/(float)Recipe.OUT_QUANTITY;
	}

	@Override
	protected boolean usableOnItem(Item item) {

		if(item instanceof Bag){
			return false;
		}
		if(!item.嬗变){
			return false;
		}
		if (item instanceof Weapon){
			//all melee weapons, except pickaxe when in a mining level
			return !(item instanceof 镐子&&Dungeon.level instanceof MiningLevel);

			//all missile weapons except untipped darts
		} else {
			return item instanceof Ring||item instanceof Armor||item instanceof Wand;
		}
	}

	@Override
	protected void onItemSelected(Item item) {
		Item result=幸运之泉.changeItem(item);

		item.detach(curUser.belongings.backpack);
		GLog.绿(Messages.get(this,"recycled",result.name()));
		result.放背包();

		Transmuting.show(curUser, item, result);
		curUser.sprite.emitter().start(Speck.factory(Speck.CHANGE), 0.2f, 10);
	}
	
	@Override
	public int 金币() {
		return (int)(60 * (quantity/(float)Recipe.OUT_QUANTITY));
	}

	@Override
	public int 能量() {
		return (int)(12 * (quantity/(float)Recipe.OUT_QUANTITY));
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		private static final int OUT_QUANTITY = 2;
		
		{
			inputs =  new Class[]{嬗变卷轴.class,
					升级卷轴.class};
			inQuantity = new int[]{1,1};
			
			cost = 8;
			
			output = 转级菱晶.class;
			outQuantity = OUT_QUANTITY;
		}
		
	}
}
