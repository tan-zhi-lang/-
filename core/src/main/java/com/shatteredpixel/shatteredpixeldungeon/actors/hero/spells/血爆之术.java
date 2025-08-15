

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.utils.Random;

public class 血爆之术 extends 巫术 {

	public static final 血爆之术 INSTANCE = new 血爆之术();

	@Override
	public int icon() {
		return HeroIcon.血爆之术;
	}

	@Override
	public float chargeUse(Hero hero) {
		return 1;
	}

	@Override
	public void onCast(灵月法杖 tome, Hero hero) {
		hero.生命 = 1;

		Buff.施加(hero, Barrier.class).设置(hero.最大生命(0.01f+hero.天赋点数(Talent.血历之术,0.33f)));
		hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(hero.最大生命(0.01f+hero.天赋点数(Talent.血历之术,0.33f))), FloatingText.SHIELDING );

		Item.updateQuickslot();

		hero.sprite.operate(hero.pos);
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.最大生命(0.01f+Dungeon.hero.天赋点数(Talent.血历之术,0.33f)));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
