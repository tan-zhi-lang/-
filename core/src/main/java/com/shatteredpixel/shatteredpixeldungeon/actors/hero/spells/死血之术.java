

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

public class 死血之术 extends 巫术 {

	public static final 死血之术 INSTANCE = new 死血之术();

	@Override
	public int icon() {
		return HeroIcon.死血之术;
	}

	

	@Override
	public void onCast(灵月法杖 tome, Hero hero) {
		hero.回血(Random.InvNormalIntRange(
				hero.天赋生命力(Talent.死血之术,0.2f)+
				hero.天赋生命力(Talent.高级死血,0.3f)
				,
				hero.天赋生命力(Talent.死血之术,0.4f)+
				hero.天赋生命力(Talent.高级死血,0.6f)));
				
		Buff.施加(hero, Barrier.class).设置(hero.最大生命(hero.天赋生命力(Talent.死血之术,0.2f)));
		hero.sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(hero.最大生命(hero.天赋点数(Talent.血爆之术,0.2f))),FloatingText.SHIELDING);
		
		Item.updateQuickslot();

		hero.sprite.operate();
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",
				Dungeon.hero.天赋生命力(Talent.死血之术,0.2f)+
						Dungeon.hero.天赋生命力(Talent.高级死血,0.3f),

				Dungeon.hero.天赋生命力(Talent.死血之术,0.4f)+
						Dungeon.hero.天赋生命力(Talent.高级死血,0.6f),
								   Dungeon.hero.天赋生命力(Talent.死血之术,0.5f)
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
