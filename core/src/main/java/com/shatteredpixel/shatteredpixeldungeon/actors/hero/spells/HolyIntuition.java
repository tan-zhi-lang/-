

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class HolyIntuition extends InventoryClericSpell {

	public static final HolyIntuition INSTANCE = new HolyIntuition();

	@Override
	public int icon() {
		return HeroIcon.HOLY_INTUITION;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return (item instanceof EquipableItem || item instanceof Wand) && !item.已鉴定() && !item.cursedKnown;
	}

	


	@Override
	protected void onItemSelected(神圣法典 tome, Hero hero, Item item) {
		if (item == null){
			return;
		}

		item.cursedKnown = true;

		if (item.cursed){
			GLog.w(Messages.get(this, "cursed"));
		} else {
			GLog.i(Messages.get(this, "uncursed"));
		}

		hero.spend( 1f );
		hero.busy();
		hero.sprite.operate();
		hero.sprite.parent.add( new Identification( hero.sprite.center().offset( 0, -16 ) ) );

		Sample.INSTANCE.play( Assets.Sounds.READ );
		onSpellCast(tome, hero);

	}

}
