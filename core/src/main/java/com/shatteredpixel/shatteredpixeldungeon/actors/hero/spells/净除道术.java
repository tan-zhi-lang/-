

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class 净除道术 extends 背包道术 {

	public static final 净除道术 INSTANCE = new 净除道术();

	@Override
	public int icon() {
		return HeroIcon.净除道术;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return item.cursedKnown;
	}

	@Override
	protected void onItemSelected(铜钱剑 tome,Hero hero,Item item) {
		if (item == null){
			return;
		}
		if(hero.满天赋(Talent.净除道术)){
			祛邪卷轴.净化(hero,item.鉴定().特殊升级());
		}else{
			祛邪卷轴.净化(hero,item.鉴定());
		}

		hero.spend( 1f );
		hero.busy();

		hero.sprite.operate();
		hero.sprite.parent.add( new Identification( hero.sprite.center().offset( 0, -16 ) ) );

		Sample.INSTANCE.play( Assets.Sounds.READ );
		onSpellCast(tome, hero);

	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.天赋点数(Talent.净除道术)==2?"祛邪、鉴定、升级":"祛邪");
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
