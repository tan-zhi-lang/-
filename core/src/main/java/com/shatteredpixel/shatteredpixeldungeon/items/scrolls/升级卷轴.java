

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUpgrade;

import java.util.ArrayList;

public class 升级卷轴 extends InventoryScroll {
	
	{
		icon = 物品表.Icons.SCROLL_UPGRADE;
		preferredBag = Belongings.Backpack.class;
		
		特别= true;
		绿色=已鉴定();
		talentFactor = 2f;
	}

	protected static final String AC_强化 = "强化";
	protected static final String AC_自升 = "自升";
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		if(已鉴定()&&hero.subClass(HeroSubClass.机械教主))
		actions.add( AC_强化 );
		if(hero.符文("升级升级卷轴:自升"))
		actions.add( AC_自升 );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_强化)) {
			detach( curUser.belongings.backpack );
			hero.属性成长+=0.045f;
		}
		if (action.equals(AC_自升)) {
			detach( curUser.belongings.backpack );
			hero.自升++;
		}
	}
	@Override
	protected boolean usableOnItem(Item item) {
		if(Dungeon.符文("升级升级卷轴:武器专精")&&item instanceof Wand)return false;
		if(Dungeon.符文("升级升级卷轴:法杖专精")&&item instanceof Weapon)return false;
		return item.可升级();
	}

	@Override
	protected void onItemSelected( Item item ) {
		
		GameScene.show(new WndUpgrade(this, item, identifiedByUse));

	}

	public void reShowSelector(boolean force){
		identifiedByUse = force;
		curItem = this;
		GameScene.selectItem(itemSelector);
	}

	public WndBag.ItemSelector getSelector(boolean force){
		identifiedByUse = force;
		curItem = this;
		return itemSelector;
	}

	public Item upgradeItem( Item item ){
		upgrade( curUser );
		Badges.解锁学士();
		Degrade.detach( curUser, Degrade.class );
		//logic for telling the user when item properties change from upgrades
		//...yes this is rather messy
		
		if (item instanceof Weapon){
			Weapon w = (Weapon) item;
			item = w.升级();

			if(Dungeon.符文("升级升级卷轴:武器专精"))item.升级();


		} else if (item instanceof Armor){
			Armor a = (Armor) item;
			item = a.升级();

		} else if (item instanceof Wand || item instanceof Ring) {

			item = item.升级();
			if(item instanceof Wand&&Dungeon.符文("升级升级卷轴:武器专精")){
				item.升级(3);
			}

		} else {
			item = item.升级();
		}

		Badges.validateItemLevelAquired( item );
		Statistics.upgradesUsed++;

		Catalog.countUse(item.getClass());

		return item;
	}
	
	public static void upgrade( Hero hero ) {
		hero.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );
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
