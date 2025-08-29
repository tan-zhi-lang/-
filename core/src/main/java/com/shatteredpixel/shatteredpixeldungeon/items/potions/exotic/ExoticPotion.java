

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
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
		regToExo.put(PotionOfStrength.class, PotionOfMastery.class);
		exoToReg.put(PotionOfMastery.class, PotionOfStrength.class);

		regToExo.put(治疗药剂.class, PotionOfShielding.class);
		exoToReg.put(PotionOfShielding.class, 治疗药剂.class);

		regToExo.put(PotionOfMindVision.class, PotionOfMagicalSight.class);
		exoToReg.put(PotionOfMagicalSight.class, PotionOfMindVision.class);

		regToExo.put(PotionOfFrost.class, PotionOfSnapFreeze.class);
		exoToReg.put(PotionOfSnapFreeze.class, PotionOfFrost.class);

		regToExo.put(PotionOfLiquidFlame.class, PotionOfDragonsBreath.class);
		exoToReg.put(PotionOfDragonsBreath.class, PotionOfLiquidFlame.class);

		regToExo.put(PotionOfToxicGas.class, PotionOfCorrosiveGas.class);
		exoToReg.put(PotionOfCorrosiveGas.class, PotionOfToxicGas.class);

		regToExo.put(极速药剂.class, PotionOfStamina.class);
		exoToReg.put(PotionOfStamina.class, 极速药剂.class);

		regToExo.put(PotionOfInvisibility.class, PotionOfShroudingFog.class);
		exoToReg.put(PotionOfShroudingFog.class, PotionOfInvisibility.class);
		
		regToExo.put(PotionOfLevitation.class, PotionOfStormClouds.class);
		exoToReg.put(PotionOfStormClouds.class, PotionOfLevitation.class);

		regToExo.put(PotionOfParalyticGas.class, PotionOfEarthenArmor.class);
		exoToReg.put(PotionOfEarthenArmor.class, PotionOfParalyticGas.class);

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
