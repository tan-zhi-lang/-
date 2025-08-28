

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

public class 血历之术 extends 巫术 {

	public static final 血历之术 INSTANCE = new 血历之术();

	@Override
	public int icon() {
		return HeroIcon.血历之术;
	}

	

	@Override
	public void onCast(灵月法杖 tome, Hero hero) {
		hero.受伤(hero.生命力(4)-hero.天赋生命力(Talent.物到之术,1));
		hero.经验(hero.最大生命(hero.天赋点数(Talent.血历之术,0.2f)));
		Item.updateQuickslot();

		hero.sprite.operate();
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",
				Dungeon.hero.生命力(4)-Dungeon.hero.天赋生命力(Talent.物到之术,1),
				Dungeon.hero.最大生命(Dungeon.hero.天赋点数(Talent.血历之术,0.2f))
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
