

package com.shatteredpixel.shatteredpixeldungeon.items;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;

public class 超级升级符石 extends 用品{
	
	{
		image = 物品表.STONE_AUGMENTATION;
	}
	@Override
	public void 使用(Hero hero){
		GameScene.selectItem(new UpgradeSelector());
		super.使用(hero);
	}

	private class UpgradeSelector extends WndBag.ItemSelector {
		
		@Override
		public String textPrompt() {
			return Messages.get(this,"prompt");
		}
		
		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}
		
		@Override
		public boolean itemSelectable(Item item) {
			return item.可升级()
				   && (item instanceof Weapon||item instanceof Wand||item instanceof Armor||item instanceof Ring);
		}
		
		@Override
		public void onSelect(Item item) {
			if (item != null) {
				item.超级等级=true;
				Sample.INSTANCE.play(Assets.Sounds.EVOKE);
				升级卷轴.upgrade(Dungeon.hero);
				Item.evoke( Dungeon.hero );
				
				
				Dungeon.hero.sprite.operate(Dungeon.hero.pos);
				Dungeon.hero.spend( 1f );
				Dungeon.hero.busy();

			}
		}
	}
}
