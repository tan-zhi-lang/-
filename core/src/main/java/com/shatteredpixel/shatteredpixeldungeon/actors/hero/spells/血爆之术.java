

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
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
	public void onCast(灵月法杖 tome, Hero hero) {
		if(hero.有天赋(Talent.高级血爆)){
			float shield = hero.天赋点数(Talent.高级血爆, 100);
			Buff.施加(hero, Hunger.class).吃饭(shield);
		}
		hero.受伤(20-hero.天赋点数(Talent.血爆之术,5)+hero.最大生命(0.6f-hero.天赋点数(Talent.血爆之术,0.15f)));
		Buff.施加(hero, Barrier.class).设置(Dungeon.hero.天赋点数(Talent.血爆之术,10)+hero.最大生命(hero.天赋点数(Talent.血爆之术,0.15f)));
		hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(Dungeon.hero.天赋点数(Talent.血爆之术,10)+hero.最大生命(hero.天赋点数(Talent.血爆之术,0.15f))), FloatingText.SHIELDING );

		Item.updateQuickslot();

		hero.sprite.operate(hero.pos);
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",
				20-Dungeon.hero.天赋点数(Talent.血爆之术,5)+Dungeon.hero.最大生命(0.6f-Dungeon.hero.天赋点数(Talent.血爆之术,0.15f)),
				Dungeon.hero.天赋点数(Talent.血爆之术,10)+Dungeon.hero.最大生命(Dungeon.hero.天赋点数(Talent.血爆之术,0.15f)),
				Dungeon.hero.有天赋(Talent.高级血爆)?Dungeon.hero.天赋点数(Talent.高级血爆,100):""
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
