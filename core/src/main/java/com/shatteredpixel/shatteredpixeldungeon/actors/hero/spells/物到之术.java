

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;

public class 物到之术 extends 巫术 {

	public static final 物到之术 INSTANCE = new 物到之术();

	@Override
	public int icon() {
		return HeroIcon.物到之术;
	}

	@Override
	public void onCast(灵月法杖 tome, Hero hero) {
		hero.受伤(hero.生命力(10)-hero.天赋生命力(Talent.物到之术,2));
		Item item = Generator.random();
		item.放背包();
		Item.updateQuickslot();

		hero.sprite.operate();
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.生命力(10)-Dungeon.hero.天赋生命力(Talent.物到之术,2));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
