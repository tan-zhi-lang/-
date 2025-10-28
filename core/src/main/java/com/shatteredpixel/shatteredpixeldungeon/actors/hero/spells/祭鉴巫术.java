

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;

public class 祭鉴巫术 extends 背包巫术 {

	public static final 祭鉴巫术 INSTANCE = new 祭鉴巫术();

	@Override
	public int icon() {
		return HeroIcon.祭鉴巫术;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return !item.已鉴定();
	}

	

	@Override
	protected void onItemSelected(灵月法杖 tome, Hero hero, Item item) {
		if (item == null){
			return;
		}
		if(hero.满天赋(Talent.祭鉴巫术)){
			祛邪卷轴.祛邪(hero,item.鉴定().特殊升级());
		}else{
			item.鉴定();
		}
		hero.受伤(hero.天赋生命力(Talent.祭鉴巫术,1f));

		hero.spend( 1f );
		hero.busy();

		hero.sprite.operate();
		hero.sprite.parent.add( new Identification( hero.sprite.center().offset( 0, -16 ) ) );

		Sample.INSTANCE.play( Assets.Sounds.READ );
		onSpellCast(tome, hero);

	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.天赋生命力(Talent.祭鉴巫术,1f),Dungeon.hero.天赋点数(Talent.祭鉴巫术)==2?"鉴定、祛邪、升级":"鉴定");
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
