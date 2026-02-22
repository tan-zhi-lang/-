

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.守御灵光;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.护盾;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Enchanting;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class 赐福 extends ClericSpell {

	public static final 赐福 INSTANCE = new 赐福();

	@Override
	public int icon() {
		return HeroIcon.赐福;
	}

	

	@Override
	public void onCast(神圣法典 tome, Hero hero) {

		Buff.施加(hero,效果.class,50f);

		if(hero.天赋(Talent.守御灵光))
			Buff.施加(hero,守御灵光.class,50f);

		if(hero.天赋(Talent.神圣屏障))
		Buff.施加(hero,护盾.class).增加(hero.天赋点数(Talent.神圣屏障,2));

		Item.updateQuickslot();

		Sample.INSTANCE.play(Assets.Sounds.READ);

		hero.sprite.operate();
		if (hero.belongings.weapon() != null) Enchanting.show(hero, hero.belongings.weapon());

		onSpellCast(tome, hero);
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.光照范围(),Dungeon.hero.光照范围()*2);
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

	public static class 效果 extends FlavourBuff {

		public static final float DURATION	= 50f;

		{
			type = buffType.POSITIVE;
		}

		@Override
		public int icon() {
			return BuffIndicator.HOLY_WEAPON;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}
	}

}
