

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class 力量药剂 extends Potion {

	{
		icon = 物品表.Icons.POTION_STRENGTH;
		
		特别= true;

		talentFactor = 2f;
	}
	
	@Override
	public void apply( Hero hero ) {
		鉴定();
		
		if(!Dungeon.炼狱(炼狱设置.体弱多病)){
			hero.力量++;
		}
		if(hero.符文("力量的爆发"))
			Buff.施加(hero,HTBoost.class).add();

		hero.sprite.showStatusWithIcon(CharSprite.增强, "1", FloatingText.STRENGTH);

		GLog.p( Messages.get(this, "msg", hero.力量()) );
		
		Badges.validateStrengthAttained();
		Badges.validateDuelistUnlock();
	}

	@Override
	public int 金币() {
		return isKnown() ? 50 * quantity : super.金币();
	}

	@Override
	public int 能量() {
		return isKnown() ? 10 * quantity : super.能量();
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
				if(hero.符文("力量的爆发"))
					hero.力量+=hero.力量()/10f;
			super.detach();
		}

		public void add(){
			left += 900;
		}

		@Override
		public int icon() {
			return BuffIndicator.UPGRADE;
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
