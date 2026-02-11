

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.极速;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class 极速药剂 extends Potion {
	
	{
		icon = 物品表.Icons.POTION_HASTE;
	}
	
	@Override
	public void apply(Hero hero) {
		鉴定();

		if(hero.符文("速度的承载"))
			Buff.施加(hero,HTBoost.class).add();

		GLog.w( Messages.get(this, "energetic") );
		Buff.延长( hero, 极速.class, 极速.DURATION);
		SpellSprite.show(hero, SpellSprite.HASTE, 1, 1, 0);
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 40 * quantity : super.金币();
	}

	public static class HTBoost extends Buff{

		{
			type = buffType.POSITIVE;
		}

		private int left;

		@Override
		public boolean act() {
			if (target.isAlive()) {
				if (left > 0) {
					left--;
					spend( TICK );
				} else {
					detach();
				}

			} else {
				detach();
			}
			return true;
		}

		@Override
		public void detach(){
			if(target instanceof Hero hero)
				if(hero.符文("速度的承载")){
					hero.攻速成长+=0.15f;
					hero.移速成长+=0.15f;
				}
			super.detach();
		}

		public void add(){
			left += 900;
		}

		@Override
		public int icon() {
			return BuffIndicator.HASTE;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1f, 0.5f, 0f);
		}

		@Override
		public float iconFadePercent() {
			return (900f - left) / 900f;
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString(left);
		}

		private static String LEFT = "left";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put( LEFT, left );
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			left = bundle.getInt(LEFT);
		}
	}
}
