

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.utils.Random;


public abstract class KindofMisc extends EquipableItem {

	@Override
	public boolean doEquip(final Hero hero) {

		boolean equipFull = false;

		if(hero.belongings.misc3 != null){
			equipFull=true;
		}
		if (equipFull) {

			final KindofMisc[] miscs = new KindofMisc[3];
			miscs[0] = hero.belongings.misc;
			miscs[1] = hero.belongings.misc2;
			miscs[2] = hero.belongings.misc3;

			final boolean[] enabled = new boolean[3];
			enabled[0] = miscs[0] != null;
			enabled[1] = miscs[1] != null;
			enabled[2] = miscs[2] != null;

			GameScene.show(
					new WndOptions(new ItemSprite(this),
							Messages.get(KindofMisc.class, "unequip_title"),
							Messages.get(KindofMisc.class, "unequip_message"),
							miscs[0] == null ? "---" : Messages.titleCase(miscs[0].title()),
							miscs[1] == null ? "---" : Messages.titleCase(miscs[1].title()),
							miscs[2] == null ? "---" : Messages.titleCase(miscs[2].title())) {

						@Override
						protected void onSelect(int index) {

							KindofMisc equipped = miscs[index];
							//we directly remove the item because we want to have inventory capacity
							// to unequip the equipped one, but don't want to trigger any other
							// item detaching logic
							int slot = Dungeon.quickslot.getSlot(KindofMisc.this);
							slotOfUnequipped = -1;
							Dungeon.hero.belongings.backpack.items.remove(KindofMisc.this);
							if (equipped.doUnequip(hero, true, false)) {
								//swap out equip in misc slot if needed
								if (index == 0){
									hero.belongings.misc = KindofMisc.this;
								} else if (index == 1){
									hero.belongings.misc2 = KindofMisc.this;
								} else if (index == 2 ){
									hero.belongings.misc3 = KindofMisc.this;
								}
							} else {
								Dungeon.hero.belongings.backpack.items.add(KindofMisc.this);
							}
							if (slot != -1) {
								Dungeon.quickslot.setSlot(slot, KindofMisc.this);
							} else if (slotOfUnequipped != -1 && defaultAction() != null){
								Dungeon.quickslot.setSlot(slotOfUnequipped, KindofMisc.this);
							}
							updateQuickslot();
						}

						@Override
						protected boolean enabled(int index) {
							return enabled[index];
						}
					});

			return false;

		} else {

			// 15/25% chance
			if (hero.heroClass != HeroClass.CLERIC && hero.有天赋(Talent.HOLY_INTUITION)
					&& cursed && !cursedKnown
					&& Random.Int(3) < hero.天赋点数(Talent.HOLY_INTUITION)){
				cursedKnown = true;
				if(hero.满天赋(Talent.HOLY_INTUITION)){
					鉴定();
					祛邪卷轴.净化(hero,this);
				}else{
					鉴定();
				}
				GLog.p(Messages.get(this, "curse_detected"));
				return false;
			}
			if(hero.belongings.misc == null){
				hero.belongings.misc = this;
			}else if(hero.belongings.misc2 == null){
				hero.belongings.misc2 = this;
			}else if(hero.belongings.misc3 == null){
				hero.belongings.misc3 = this;
			}


			detach( hero.belongings.backpack );

			Talent.装备时(hero, this);
			activate( hero );

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(this, "equip_cursed", this) );
			}

			hero.spendAndNext( timeToEquip(hero) );
			return true;

		}

	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			if (hero.belongings.misc == this) {
				hero.belongings.misc = null;
			}else if (hero.belongings.misc2 == this) {
				hero.belongings.misc2 = null;
			}else if (hero.belongings.misc3 == this) {
				hero.belongings.misc3 = null;
			}

			return true;

		} else {

			return false;

		}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null && (hero.belongings.misc() == this
				|| hero.belongings.misc2() == this
				|| hero.belongings.misc3() == this);
	}

}
