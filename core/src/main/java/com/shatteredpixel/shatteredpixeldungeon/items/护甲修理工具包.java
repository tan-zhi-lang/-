

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 护甲修理工具包 extends 用品 {
	
	
	{
		image = 物品表.护甲修理工具包;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)) {
			detach(hero.belongings.backpack);
			hero.回满护甲();
		}
	}
	
	@Override
	public int 金币() {
		return 10 * quantity;
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
				   && ingredients.get(0) instanceof Armor a
				   && a.破损纹章==null
				   && a.等级+1>0
				   && !a.cursed;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 6;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			Armor w = (Armor)ingredients.get(0);
			
			if (!w.levelKnown){
				result.数量(resinQuantity(w));
			}
			w.数量(0);
			
			return result;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Armor w = (Armor)ingredients.get(0);
			
			if (w.levelKnown){
				return new 护甲修理工具包().数量(resinQuantity(w));
			} else {
				return new 护甲修理工具包();
			}
		}
		
		private int resinQuantity(Armor w){
			int level = w.等级();
			int quantity = level+1;
			return quantity;
		}
	}
}
