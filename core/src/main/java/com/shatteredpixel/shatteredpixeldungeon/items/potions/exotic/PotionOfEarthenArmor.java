

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PotionOfEarthenArmor extends ExoticPotion {
	
	{
		icon = 物品表.Icons.POTION_EARTHARMR;
	}
	
	@Override
	public void apply( Hero hero ) {
		鉴定();
		
		Barkskin.conditionallyAppend( hero, 2 + hero.等级 /3, 50 );
	}
	
}
