

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 死血之术 extends 巫术 {

	public static final 死血之术 INSTANCE = new 死血之术();

	@Override
	public int icon() {
		return HeroIcon.死血之术;
	}

	

	@Override
	public void onCast(灵月法杖 tome, Hero hero) {
		hero.回血(Random.InvNormalIntRange(
				hero.天赋生命力(Talent.死血之术,0.3f)+
				hero.天赋生命力(Talent.高级死血,0.5f)
				,
				hero.天赋生命力(Talent.死血之术,1)+
				hero.天赋生命力(Talent.高级死血,0.7f)
		));
		Item.updateQuickslot();

		hero.sprite.operate(hero.pos);
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",
				Dungeon.hero.天赋生命力(Talent.死血之术,0.3f)+
						Dungeon.hero.天赋生命力(Talent.高级死血,0.5f),

				Dungeon.hero.天赋生命力(Talent.死血之术,1f)+
						Dungeon.hero.天赋生命力(Talent.高级死血,0.7f)
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
