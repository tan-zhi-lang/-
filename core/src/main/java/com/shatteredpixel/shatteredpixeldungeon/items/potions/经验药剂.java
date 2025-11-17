

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 经验药剂 extends Potion {

	{
		icon = 物品表.Icons.POTION_EXP;
		
		遗产= true;

		talentFactor = 2f;
	}
	
	@Override
	public void apply( Hero hero ) {
		鉴定();
		hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(hero.升级所需()), FloatingText.EXPERIENCE);
		hero.经验( hero.升级所需(), getClass() );
		new Flare( 6, 32 ).color(0xFFFF00, true).show( curUser.sprite, 2f );
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 50 * quantity : super.金币();
	}

	@Override
	public int 能量() {
		return isKnown() ? 10 * quantity : super.能量();
	}
}
