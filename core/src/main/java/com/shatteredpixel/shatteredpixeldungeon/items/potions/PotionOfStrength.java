

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;

public class PotionOfStrength extends Potion {

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
}
