

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class PotionOfShielding extends ExoticPotion {
	
	{
		icon = 物品表.Icons.POTION_SHIELDING;
	}
	
	@Override
	public void apply(Hero hero) {
		鉴定();

		if (Dungeon.isChallenged(Challenges.NO_HEALING)){
			治疗药剂.pharmacophobiaProc(hero);
		} else {
			//~75% of a potion of healing
			Buff.施加(hero, Barrier.class).设置(hero.最大生命(0.75f));
			hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(hero.最大生命(0.75f)), FloatingText.SHIELDING );
		}
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*1.25f);
	}
}
