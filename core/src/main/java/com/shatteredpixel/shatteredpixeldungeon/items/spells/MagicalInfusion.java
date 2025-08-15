

package com.shatteredpixel.shatteredpixeldungeon.items.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUpgrade;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class MagicalInfusion extends InventorySpell {
	
	{
		image = 物品表.MAGIC_INFUSE;

		unique = true;

		talentFactor = 2;
	}

	@Override
	protected boolean usableOnItem(Item item) {
		return item.可升级();
	}

	@Override
	protected void onItemSelected( Item item ) {

		GameScene.show(new WndUpgrade(this, item, false));

	}

	public void reShowSelector(){
		curItem = this;
		GameScene.selectItem(itemSelector);
	}

	public void useAnimation(){
		curUser.spend(1f);
		curUser.busy();
		(curUser.sprite).operate(curUser.pos);

		Sample.INSTANCE.play(Assets.Sounds.READ);
		Invisibility.dispel();

		Catalog.countUse(curItem.getClass());
		if (Random.Float() < ((Spell) curItem).talentChance) {
			Talent.onScrollUsed(curUser, curUser.pos, ((Spell) curItem).talentFactor, getClass());
		}
	}

	public Item upgradeItem( Item item ){
		升级卷轴.upgrade(curUser);

		Degrade.detach( curUser, Degrade.class );

		if (item instanceof Weapon && ((Weapon) item).enchantment != null) {
			item = ((Weapon) item).升级(true);
		} else if (item instanceof Armor && ((Armor) item).glyph != null) {
			item = ((Armor) item).升级(true);
		} else {
			boolean wasCursed = item.cursed;
			boolean wasCurseInfused = item instanceof Wand && ((Wand) item).curseInfusionBonus;
			item = item.升级();
			if (wasCursed) item.cursed = true;
			if (wasCurseInfused) ((Wand) item).curseInfusionBonus = true;
		}

		GLog.p( Messages.get(this, "infuse") );
		Badges.validateItemLevelAquired(item);

		Catalog.countUse(item.getClass());

		Statistics.upgradesUsed++;

		return item;
	}
	
	@Override
	public int 金币() {
		return 60 * quantity;
	}

	@Override
	public int energyVal() {
		return 12 * quantity;
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{升级卷轴.class};
			inQuantity = new int[]{1};
			
			cost = 12;
			
			output = MagicalInfusion.class;
			outQuantity = 1;
		}
		
	}
}
