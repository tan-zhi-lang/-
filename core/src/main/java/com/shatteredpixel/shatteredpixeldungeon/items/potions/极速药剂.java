

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 极速药剂 extends Potion {
	
	{
		icon = 物品表.Icons.POTION_HASTE;
	}
	
	@Override
	public void apply(Hero hero) {
		鉴定();
		
		GLog.w( Messages.get(this, "energetic") );
		Buff.延长( hero, Haste.class, Haste.DURATION);
		SpellSprite.show(hero, SpellSprite.HASTE, 1, 1, 0);
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 40 * quantity : super.金币();
	}
}
