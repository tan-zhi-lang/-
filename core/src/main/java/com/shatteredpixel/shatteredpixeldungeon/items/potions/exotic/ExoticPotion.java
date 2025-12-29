

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.液火药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.灵视药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.麻痹药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExoticPotion extends Potion {
	
	{
		//sprite = equivalent potion sprite but one row down
	}
	
	public static final LinkedHashMap<Class<?extends Potion>, Class<?extends ExoticPotion>> regToExo = new LinkedHashMap<>();
	public static final LinkedHashMap<Class<?extends ExoticPotion>, Class<?extends Potion>> exoToReg = new LinkedHashMap<>();
	static{
		regToExo.put(力量药剂.class,PotionOfMastery.class);
		exoToReg.put(PotionOfMastery.class, 力量药剂.class);

		regToExo.put(治疗药剂.class, PotionOfShielding.class);
		exoToReg.put(PotionOfShielding.class, 治疗药剂.class);

		regToExo.put(灵视药剂.class,PotionOfMagicalSight.class);
		exoToReg.put(PotionOfMagicalSight.class, 灵视药剂.class);

		regToExo.put(冰霜药剂.class,PotionOfSnapFreeze.class);
		exoToReg.put(PotionOfSnapFreeze.class, 冰霜药剂.class);

		regToExo.put(液火药剂.class,PotionOfDragonsBreath.class);
		exoToReg.put(PotionOfDragonsBreath.class, 液火药剂.class);

		regToExo.put(毒气药剂.class,PotionOfCorrosiveGas.class);
		exoToReg.put(PotionOfCorrosiveGas.class, 毒气药剂.class);

		regToExo.put(极速药剂.class, PotionOfStamina.class);
		exoToReg.put(PotionOfStamina.class, 极速药剂.class);

		regToExo.put(隐形药剂.class,PotionOfShroudingFog.class);
		exoToReg.put(PotionOfShroudingFog.class, 隐形药剂.class);
		
		regToExo.put(浮空药剂.class,PotionOfStormClouds.class);
		exoToReg.put(PotionOfStormClouds.class, 浮空药剂.class);

		regToExo.put(麻痹药剂.class,PotionOfEarthenArmor.class);
		exoToReg.put(PotionOfEarthenArmor.class, 麻痹药剂.class);

		regToExo.put(净化药剂.class, PotionOfCleansing.class);
		exoToReg.put(PotionOfCleansing.class, 净化药剂.class);

		regToExo.put(经验药剂.class, PotionOfDivineInspiration.class);
		exoToReg.put(PotionOfDivineInspiration.class, 经验药剂.class);

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
			color = handler.label(exoToReg.get(this.getClass()));
		}
	}
	
	@Override
	//20 gold more than its none-exotic equivalent
	public int 金币() {
		return (Reflection.newInstance(exoToReg.get(getClass())).金币() + 20) * quantity;
	}

	@Override
	//4 more energy than its none-exotic equivalent
	public int 能量() {
		return (Reflection.newInstance(exoToReg.get(getClass())).能量() + 4) * quantity;
	}

	public static class PotionToExotic extends Recipe{

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() == 1 && regToExo.containsKey(ingredients.get(0).getClass())){
				return true;
			}

			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 4;
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
