

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class ExoticScroll extends Scroll {
	
	
	public static final LinkedHashMap<Class<?extends Scroll>, Class<?extends ExoticScroll>> regToExo = new LinkedHashMap<>();
	public static final LinkedHashMap<Class<?extends ExoticScroll>, Class<?extends Scroll>> exoToReg = new LinkedHashMap<>();
	static{
		regToExo.put(升级卷轴.class, ScrollOfEnchantment.class);
		exoToReg.put(ScrollOfEnchantment.class, 升级卷轴.class);

		regToExo.put(鉴定卷轴.class, ScrollOfDivination.class);
		exoToReg.put(ScrollOfDivination.class, 鉴定卷轴.class);
		
		regToExo.put(祛邪卷轴.class, ScrollOfAntiMagic.class);
		exoToReg.put(ScrollOfAntiMagic.class, 祛邪卷轴.class);

		regToExo.put(ScrollOfMirrorImage.class, ScrollOfPrismaticImage.class);
		exoToReg.put(ScrollOfPrismaticImage.class, ScrollOfMirrorImage.class);

		regToExo.put(ScrollOfRecharging.class, ScrollOfMysticalEnergy.class);
		exoToReg.put(ScrollOfMysticalEnergy.class, ScrollOfRecharging.class);

		regToExo.put(ScrollOfTeleportation.class, ScrollOfPassage.class);
		exoToReg.put(ScrollOfPassage.class, ScrollOfTeleportation.class);

		regToExo.put(ScrollOfLullaby.class, ScrollOfSirensSong.class);
		exoToReg.put(ScrollOfSirensSong.class, ScrollOfLullaby.class);

		regToExo.put(探地卷轴.class, ScrollOfForesight.class);
		exoToReg.put(ScrollOfForesight.class, 探地卷轴.class);

		regToExo.put(ScrollOfRage.class, ScrollOfChallenge.class);
		exoToReg.put(ScrollOfChallenge.class, ScrollOfRage.class);

		regToExo.put(ScrollOfRetribution.class, ScrollOfPsionicBlast.class);
		exoToReg.put(ScrollOfPsionicBlast.class, ScrollOfRetribution.class);
		
		regToExo.put(ScrollOfTerror.class, ScrollOfDread.class);
		exoToReg.put(ScrollOfDread.class, ScrollOfTerror.class);
		
		regToExo.put(嬗变卷轴.class, 蜕变秘卷.class);
		exoToReg.put(蜕变秘卷.class, 嬗变卷轴.class);
	}
	
	@Override
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( exoToReg.get(this.getClass()) ));
	}
	
	@Override
	public void setKnown() {
		if (!isKnown()) {
			handler.know(exoToReg.get(this.getClass()));
			updateQuickslot();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (handler != null && handler.contains(exoToReg.get(this.getClass()))) {
			image = handler.image(exoToReg.get(this.getClass())) + 32;
			rune = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	//20 gold more than its none-exotic equivalent
	public int 金币() {
		return (Reflection.newInstance(exoToReg.get(getClass())).金币() + 30) * quantity;
	}

	@Override
	//6 more energy than its none-exotic equivalent
	public int 能量() {
		return (Reflection.newInstance(exoToReg.get(getClass())).能量() + 6) * quantity;
	}
	
	public static class ScrollToExotic extends Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() == 1 && regToExo.containsKey(ingredients.get(0).getClass())){
				return true;
			}

			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 6;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				i.数量(i.数量()-1);
			}

			return Reflection.newInstance(regToExo.get(ingredients.get(0).getClass()));
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return Reflection.newInstance(regToExo.get(ingredients.get(0).getClass()));
		}
	}
}
