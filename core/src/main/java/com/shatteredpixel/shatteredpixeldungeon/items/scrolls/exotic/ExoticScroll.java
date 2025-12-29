

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.催眠卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.镜像卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.盛怒卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.恐惧卷轴;
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

		regToExo.put(镜像卷轴.class,ScrollOfPrismaticImage.class);
		exoToReg.put(ScrollOfPrismaticImage.class, 镜像卷轴.class);

		regToExo.put(充能卷轴.class,ScrollOfMysticalEnergy.class);
		exoToReg.put(ScrollOfMysticalEnergy.class, 充能卷轴.class);

		regToExo.put(传送卷轴.class,来去秘卷.class);
		exoToReg.put(来去秘卷.class,传送卷轴.class);

		regToExo.put(催眠卷轴.class,ScrollOfSirensSong.class);
		exoToReg.put(ScrollOfSirensSong.class, 催眠卷轴.class);

		regToExo.put(探地卷轴.class, ScrollOfForesight.class);
		exoToReg.put(ScrollOfForesight.class, 探地卷轴.class);

		regToExo.put(盛怒卷轴.class,ScrollOfChallenge.class);
		exoToReg.put(ScrollOfChallenge.class, 盛怒卷轴.class);

		regToExo.put(复仇卷轴.class,ScrollOfPsionicBlast.class);
		exoToReg.put(ScrollOfPsionicBlast.class, 复仇卷轴.class);
		
		regToExo.put(恐惧卷轴.class,ScrollOfDread.class);
		exoToReg.put(ScrollOfDread.class, 恐惧卷轴.class);
		
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
