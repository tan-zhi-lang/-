

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;


public abstract class KindofMisc extends EquipableItem {

	@Override
	public boolean doEquip(final Hero hero) {

		boolean equipFull = false;
		if(hero.女人()){
			if(hero.belongings.misc != null
			   &&hero.belongings.misc2 != null
			   &&hero.belongings.misc3 != null
			   &&hero.belongings.misc4 != null
			   &&hero.belongings.misc5 != null
			   &&hero.belongings.misc6 != null
			   &&hero.belongings.misc7 != null
			   &&hero.belongings.misc8 != null
			   &&hero.belongings.misc9 != null
			){
				equipFull=true;//9个时才选择一件取下
			}
		}else{

			if(hero.belongings.misc != null
			   &&hero.belongings.misc2 != null
			   &&hero.belongings.misc3 != null
			   &&hero.belongings.misc4 != null
			   &&hero.belongings.misc5 != null
			   &&hero.belongings.misc6 != null
			   &&hero.belongings.misc7 != null
			   &&hero.belongings.misc8 != null
			   &&hero.belongings.misc9 != null
			   &&hero.belongings.misc10 != null
			){
				equipFull=true;//10个时才选择一件取下
			}
		}
		if (equipFull) {
			final KindofMisc[] miscs = new KindofMisc[10];
			miscs[0] = hero.belongings.misc;
			miscs[1] = hero.belongings.misc2;
			miscs[2] = hero.belongings.misc3;
			miscs[3] = hero.belongings.misc4;
			miscs[4] = hero.belongings.misc5;
			miscs[5] = hero.belongings.misc6;
			miscs[6] = hero.belongings.misc7;
			miscs[7] = hero.belongings.misc8;
			miscs[8] = hero.belongings.misc9;
			miscs[9] = hero.belongings.misc10;

			final boolean[] enabled = new boolean[10];
			enabled[0] = miscs[0] != null;
			enabled[1] = miscs[1] != null;
			enabled[2] = miscs[2] != null;
			enabled[3] = miscs[3] != null;
			enabled[4] = miscs[4] != null;
			enabled[5] = miscs[5] != null;
			enabled[6] = miscs[6] != null;
			enabled[7] = miscs[7] != null;
			enabled[8] = miscs[8] != null;
			enabled[9] = miscs[9] != null;

			GameScene.show(
					new WndOptions(new ItemSprite(this),
							Messages.get(KindofMisc.class, "unequip_title"),
							Messages.get(KindofMisc.class, "unequip_message"),
							miscs[0] == null ? "- - -" : Messages.titleCase(miscs[0].title()),
							miscs[1] == null ? "- - -" : Messages.titleCase(miscs[1].title()),
							miscs[2] == null ? "- - -" : Messages.titleCase(miscs[2].title()),
							miscs[3] == null ? "- - -" : Messages.titleCase(miscs[3].title()),
						   miscs[4] == null ? "- - -" : Messages.titleCase(miscs[4].title()),
						   miscs[5] == null ? "- - -" : Messages.titleCase(miscs[5].title()),
						   miscs[6] == null ? "- - -" : Messages.titleCase(miscs[6].title()),
						   miscs[7] == null ? "- - -" : Messages.titleCase(miscs[7].title()),
						   miscs[8] == null ? "- - -" : Messages.titleCase(miscs[8].title()),
						   miscs[9] == null ? "- - -" : Messages.titleCase(miscs[9].title())
					) {

						@Override
						protected void onSelect(int index) {

							KindofMisc equipped = miscs[index];
							//we directly remove the item because we want to have inventory capacity
							// to unequip the equipped one, but don't want to trigger any other
							// item detaching logic
							int slot = Dungeon.quickslot.getSlot(KindofMisc.this);
							slotOfUnequipped = -1;
//							Dungeon.hero.belongings.backpack.items.remove(KindofMisc.this);
							if (equipped.doUnequip(hero, true, false)) {
								//swap out equip in misc slot if needed
								//
								detach( hero.belongings.backpack );

								Talent.装备时(hero, KindofMisc.this);
								activate( hero );
								//
								if (index == 0){
									hero.belongings.misc = KindofMisc.this;
								} else if (index == 1){
									hero.belongings.misc2 = KindofMisc.this;
								} else if (index == 2 ){
									hero.belongings.misc3 = KindofMisc.this;
								} else if (index == 3 ){
									hero.belongings.misc4 = KindofMisc.this;
								} else if (index == 4 ){
									hero.belongings.misc5 = KindofMisc.this;
								}else if (index == 5) {
									hero.belongings.misc6 = KindofMisc.this;
								} else if (index == 6) {
									hero.belongings.misc7 = KindofMisc.this;
								} else if (index == 7) {
									hero.belongings.misc8 = KindofMisc.this;
								} else if (index == 8) {
									hero.belongings.misc9 = KindofMisc.this;
								} else if (index == 9) {
									hero.belongings.misc10 = KindofMisc.this;
								}
							}
//							else {
//								Dungeon.hero.belongings.backpack.items.add(KindofMisc.this);
//							}
							if (slot != -1) {
								Dungeon.quickslot.setSlot(slot, KindofMisc.this);
							} else if (slotOfUnequipped != -1 && defaultAction() != null){
								Dungeon.quickslot.setSlot(slotOfUnequipped, KindofMisc.this);
							}
							updateQuickslot();
						}

						@Override
						protected boolean enabled(int index) {
							if(hero.女人()&&index==9){

								return false;
							}
							return enabled[index];
						}
					});

			return false;

		} else {

			if(hero.belongings.misc == null){
				hero.belongings.misc = this;
			}else if(hero.belongings.misc2 == null){
				hero.belongings.misc2 = this;
			}else if(hero.belongings.misc3 == null){
				hero.belongings.misc3 = this;
			}else if(hero.belongings.misc4 == null){
				hero.belongings.misc4 = this;
			}else if(hero.belongings.misc5 == null){
				hero.belongings.misc5 = this;
			}else if (hero.belongings.misc6 == null) {
				hero.belongings.misc6 = this;
			} else if (hero.belongings.misc7 == null) {
				hero.belongings.misc7 = this;
			} else if (hero.belongings.misc8 == null) {
				hero.belongings.misc8 = this;
			} else if (hero.belongings.misc9 == null) {
				hero.belongings.misc9 = this;
			} else if (hero.belongings.misc10 == null) {
				hero.belongings.misc10 = this;
			}


			detach( hero.belongings.backpack );

			Talent.装备时(hero, this);
			activate( hero );

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.红(Messages.get(this,"equip_cursed",this));
				Dungeon.hero.sprite.哭泣();
			}

			hero.spendAndNext( timeToEquip(hero) );
			return true;

		}

	}

	@Override
	public boolean doEquip2(final Hero hero) {
		幸运装备=true;
		if(hero.belongings.幸运 == null){
			hero.belongings.幸运 = this;
		}

		detach( hero.belongings.backpack );

		Talent.装备时(hero, this);
		activate( hero );

		cursedKnown = true;
		if (cursed) {
			equipCursed( hero );
			GLog.红(Messages.get(this,"equip_cursed",this));
			Dungeon.hero.sprite.哭泣();
		}

		hero.spendAndNext( timeToEquip(hero) );
		return true;

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
			}else if (hero.belongings.misc4 == this) {
				hero.belongings.misc4 = null;
			}else if (hero.belongings.misc5 == this) {
				hero.belongings.misc5 = null;
			}else if (hero.belongings.misc6 == this) {
				hero.belongings.misc6 = null;
			} else if (hero.belongings.misc7 == this) {
				hero.belongings.misc7 = null;
			} else if (hero.belongings.misc8 == this) {
				hero.belongings.misc8 = null;
			} else if (hero.belongings.misc9 == this) {
				hero.belongings.misc9 = null;
			} else if (hero.belongings.misc10 == this) {
				hero.belongings.misc10 = null;
			}

			return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean doUnequip2(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			幸运装备=false;
			if (hero.belongings.幸运 == this) {
				hero.belongings.幸运 = null;
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
				|| hero.belongings.misc3() == this
				|| hero.belongings.misc4() == this
				|| hero.belongings.misc5() == this
				|| hero.belongings.misc6() == this
				|| hero.belongings.misc7() == this
				|| hero.belongings.misc8() == this
				|| hero.belongings.misc9() == this
				|| hero.belongings.misc10() == this
				|| hero.belongings.幸运() == this);
	}

}
