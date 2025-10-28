

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

//these aren't considered potions internally as most potion effects shouldn't apply to them
//mainly due to their high quantity
public class LiquidMetal extends Item {

	{
		image = 物品表.LIQUID_METAL;
		
		可堆叠= true;
		白色 = true;

		defaultAction = AC_APPLY;
		
		遗产= true;
		炼金全放 = true;
	}

	private static final String AC_APPLY = "APPLY";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_APPLY );
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_APPLY)) {

			curUser = hero;
			GameScene.selectItem( itemSelector );

		}
	}

	@Override
	protected void onThrow( int cell ) {
		if (Dungeon.level.map[cell] == Terrain.WELL || Dungeon.level.pit[cell]) {

			super.onThrow( cell );

		} else  {

			Dungeon.level.pressCell( cell );
			if (Dungeon.level.heroFOV[cell]) {
				GLog.i( Messages.get(Potion.class, "shatter") );
				Sample.INSTANCE.play( Assets.Sounds.SHATTER );
				Splash.at( cell, 0xBFBFBF, 5 );
			}

		}
	}

	@Override
	public boolean 可升级() {
		return false;
	}

	@Override
	public boolean 已鉴定() {
		return true;
	}

	@Override
	public int 金币() {
		return quantity;
	}

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector(){
		
		@Override
		public String textPrompt(){
			return Messages.get(LiquidMetal.class,"prompt");
		}
		
		@Override
		public Class<? extends Bag> preferredBag(){
			return MagicalHolster.class;
		}
		
		@Override
		public boolean itemSelectable(Item item){
			return item instanceof Weapon&&!(item instanceof 飞镖);
		}
		
		@Override
		public void onSelect(Item item){
			if(item!=null&&item instanceof Weapon){
				Weapon m=(Weapon)item;
				
				int maxToUse=10*m.tier;
				maxToUse*=m.等级()+1;
				
				float percentDurabilityLost=1;
				if(true){
					if(m.数量()<2){
						GLog.w(Messages.get(LiquidMetal.class,"already_fixed"));
						return;
					}
				}else if(2<数量()){
					Catalog.countUses(LiquidMetal.class,maxToUse);
					数量(数量()-maxToUse);
					GLog.i(Messages.get(LiquidMetal.class,"apply",maxToUse));
					
				}else{
					Catalog.countUses(LiquidMetal.class,数量());
					GLog.i(Messages.get(LiquidMetal.class,"apply",数量()));
					detachAll(Dungeon.hero.belongings.backpack);
				}
				
				curUser.sprite.operate();
				Sample.INSTANCE.play(Assets.Sounds.DRINK);
				updateQuickslot();
				curUser.sprite.emitter().start(Speck.factory(Speck.LIGHT),0.1f,10);
			}
		}
	};
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe{
			
			@Override
			public boolean testIngredients(ArrayList<Item> ingredients){
				return ingredients.size()==1&&ingredients.get(0) instanceof Weapon&&ingredients.get(0).cursedKnown&&!ingredients.get(0).cursed;
			}
			
			@Override
			public int cost(ArrayList<Item> ingredients){
				Weapon m=(Weapon)ingredients.get(0);
				if(m!=null){
					return m.quantity;
				}
				return 1;
			}
			
			@Override
			public Item brew(ArrayList<Item> ingredients){
				Item result=sampleOutput(ingredients);
				Weapon m=(Weapon)ingredients.get(0);
				result.数量(metalQuantity(m));
				
				return result;
			}
			
			@Override
			public Item sampleOutput(ArrayList<Item> ingredients){
				Weapon m=(Weapon)ingredients.get(0);
				
				if(m.levelKnown){
					return new LiquidMetal().数量(metalQuantity(m));
				}else{
					return new LiquidMetal().数量(metalQuantity(m));
				}
			}
			
			private int metalQuantity(Weapon m){
				return 10*m.tier+10*m.等级();
			}
		}
	
}
