

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.金玫苦无;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

public class 风切忍术 extends 忍术 {

	public static final 风切忍术 INSTANCE = new 风切忍术();

	@Override
	public int icon() {
		return HeroIcon.风切忍术;
	}

	

	@Override
	public void onCast( Hero hero) {
		
		Item.updateQuickslot();

		hero.sprite.operate();
		Enchanting.show(hero, new 金玫苦无());

		onSpellCast(hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",2
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
