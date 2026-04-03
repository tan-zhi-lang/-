

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Transmuting;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.brews.Brew;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.Elixir;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.Trinket;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;

public class 贤者之石 extends 用品 {
	
	
	{
		image = 物品表.贤者之石;
		重复使用=true;
	}

	@Override
	public void 使用(Hero hero){
		GameScene.selectItem(itemSelector);
		super.使用(hero);
	}
	protected Class<?extends Bag> preferredBag = Belongings.Backpack.class;

	protected boolean usableOnItem(Item item) {

		if(item instanceof Bag){
			return false;
		}
		if(!item.嬗变){
			return false;
		}
		if (item instanceof Weapon){
			//all melee weapons, except pickaxe when in a mining level
			return !(item instanceof 镐子&&Dungeon.level instanceof MiningLevel);

			//all missile weapons except untipped darts
		} else if (item instanceof Potion){
			return !(item instanceof Elixir||item instanceof Brew);

			//all regular or exotic scrolls, except itself (unless un-ided, in which case it was already consumed)
		} else if (item instanceof Scroll) {
			return item != this||item.数量()>1;

			//all non-unique artifacts (no holy tome or cloak of shadows, basically)
		} else if (item instanceof Artifact) {
			return !item.特别;

			//all rings, wands, trinkets, seeds, and runestones
		} else {
			return item instanceof Ring||item instanceof Armor||item instanceof Wand||item instanceof Trinket
				   ||item instanceof Plant.Seed||item instanceof Runestone;
		}
	}

	protected void onItemSelected(Item item) {

		Item result = 嬗变卷轴.changeItem(item);

		if (result == null){
			//This shouldn't ever trigger
			GLog.n(Messages.get(嬗变卷轴.class,"nothing"));
			curItem.放背包( curUser.belongings.backpack );
		} else {
			if (result != item) {
				int slot = Dungeon.quickslot.getSlot(item);
				if (item.isEquipped(Dungeon.hero)) {
					item.cursed = false; //to allow it to be unequipped
					if (item instanceof Artifact && result instanceof Ring){
						//if we turned an equipped artifact into a ring, ring goes into inventory
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						result.放背包();
					} else if (item instanceof KindOfWeapon && Dungeon.hero.belongings.secondWep() == item){
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						((KindOfWeapon) result).equipSecondary(Dungeon.hero);
					} else {
						((EquipableItem) item).doUnequip(Dungeon.hero, false);
						((EquipableItem) result).doEquip(Dungeon.hero);
					}
					Dungeon.hero.spend(-Dungeon.hero.cooldown()); //cancel equip/unequip time
				} else {
					item.detach(Dungeon.hero.belongings.backpack);
					if (!result.放背包()) {
						Dungeon.level.drop(result, curUser.pos).sprite().drop();
					} else if (result.可堆叠&&Dungeon.hero.belongings.getSimilar(result)!=null){
						result = Dungeon.hero.belongings.getSimilar(result);
					}
				}
				if (slot != -1
					&& result.defaultAction() != null
					&& !Dungeon.quickslot.isNonePlaceholder(slot)
					&& Dungeon.hero.belongings.contains(result)){
					Dungeon.quickslot.setSlot(slot, result);
				}
			}
			if (result.已鉴定()){
				Catalog.setSeen(result.getClass());
				Statistics.itemTypesDiscovered.add(result.getClass());
			}
			Transmuting.show(curUser,item,result);
			curUser.sprite.emitter().start(Speck.factory(Speck.CHANGE),0.2f,10);
			GLog.p( Messages.get(嬗变卷轴.class, "morph") );
			curUser.sprite.礼物();
		}

	}
	public WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(嬗变卷轴.class,"inv_title");
		}

		@Override
		public Class<? extends Bag> preferredBag() {
			return preferredBag;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return usableOnItem(item);
		}

		@Override
		public void onSelect( Item item ) {

			if (item != null){
				onItemSelected(item);
			}
		}
	};

}
