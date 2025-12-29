

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;


import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Blacksmith;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 锻造锤 extends Weapon {
	
	{
		image = 物品表.锻造锤;
		defaultAction=AC_升级;
		物品 = true;
		
		特别= true;
		遗产= false;

		tier = 3;
	}
	public static String AC_升级 = "升级";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add(AC_升级);
		return actions;
	}
	
	@Override
	public void execute(Hero hero,String action){
		if(action.equals(AC_升级)){
			GameScene.selectItem(new UpgradeSelector());
		}
		super.execute(hero,action);
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc",1+(Dungeon.解压(解压设置.独自变强)?Dungeon.hero.等级/2:0));
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
				   && item.已鉴定()
				   && !item.cursed
				   && item.等级() < 1+(Dungeon.解压(解压设置.独自变强)?Dungeon.hero.等级/2:0);
		}
		
		@Override
		public void onSelect(Item item) {
			if (item != null) {
				item.升级();
				int upgradeCost = 1000 +1000*Blacksmith.Quest.upgrades;
				Blacksmith.Quest.favor -= upgradeCost;
				Blacksmith.Quest.upgrades++;
				
				
				Sample.INSTANCE.play(Assets.Sounds.EVOKE);
				升级卷轴.upgrade(Dungeon.hero);
				Item.evoke( Dungeon.hero );
				
				
				Dungeon.hero.sprite.operate(Dungeon.hero.pos);
				Dungeon.hero.spend( 1f );
				Dungeon.hero.busy();
				
				Badges.validateItemLevelAquired(item);
				
				if (!Blacksmith.Quest.rewardsAvailable()){
					Notes.remove(Notes.Landmark.TROLL);
				}
				
				Catalog.countUse(item.getClass());
			}
		}
	}
}
