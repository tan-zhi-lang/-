

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
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
	private static final String AC_吃掉 = "吃掉";
	private static final String AC_圣水 = "圣水";
	private static final String AC_金验 = "金验";
	private static final String AC_隐晦 = "隐晦";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_APPLY );

		if(hero.符文("吃金液"))
		actions.add( AC_吃掉 );

		if(hero.符文("圣水"))
		actions.add( AC_圣水 );

		if(hero.符文("金验"))
		actions.add( AC_金验 );

		if(hero.符文("隐晦"))
		actions.add( AC_隐晦 );

		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_APPLY)) {

			curUser = hero;
			GameScene.selectItem( itemSelector );

		}
		if (action.equals(AC_吃掉)) {

			hero.buff( Hunger.class).吃饭(Hunger.STARVING);
			detach(hero.belongings.backpack);
			hero.sprite.operate( hero.pos );
			hero.busy();
			hero.spend(3 );
			SpellSprite.show(hero,SpellSprite.FOOD);
			Sample.INSTANCE.play( Assets.Sounds.EAT );
		}
		if (action.equals(AC_圣水)) {
			if(hero.belongings.hasItem(水袋.class)){
				for(int x=0;x<=4;x++){
					水袋 flask=hero.belongings.getItem(水袋.class);
					if(flask!=null&&!flask.isFull()){
						flask.collectDew(new Dewdrop());
					}else hero.回血(0.05f);
				}
			}else {
				hero.回血(0.2f);
			}

			detach(hero.belongings.backpack);
			hero.sprite.operate( hero.pos );
			hero.busy();
			hero.spend(1 );
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );

		}
		if (action.equals(AC_金验)) {
			new 经验药剂().放背包();
			detach(hero.belongings.backpack);
			hero.sprite.operate( hero.pos );
			hero.busy();
			hero.spend(1 );
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );

		}
		if (action.equals(AC_隐晦)) {
			new 隐形药剂().数量(2).放背包();
			detach(hero.belongings.backpack);
			hero.sprite.operate( hero.pos );
			hero.busy();
			hero.spend(1 );
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );

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
			return item instanceof Weapon;
		}
		
		@Override
		public void onSelect(Item item){
			if(item!=null&&item instanceof Weapon){
				Weapon m=(Weapon)item;
				
				int maxToUse=(1+m.tier+m.等级());
				if(!Dungeon.符文("升级金液"))
				maxToUse*=3;
				if(数量()<maxToUse){
					GLog.w(Messages.get(LiquidMetal.class,"already_fixed"));
					return;
				}else if(数量()>maxToUse){
					Catalog.countUses(LiquidMetal.class,maxToUse);
					数量(数量()-maxToUse);
					GLog.i(Messages.get(LiquidMetal.class,"apply",maxToUse));

					m.升级();
				}else{
					Catalog.countUses(LiquidMetal.class,数量());
					GLog.i(Messages.get(LiquidMetal.class,"apply",数量()));
					detachAll(Dungeon.hero.belongings.backpack);
					m.升级();
				}
				
				curUser.sprite.operate();
				Sample.INSTANCE.play(Assets.Sounds.DRINK);
				updateQuickslot();
				curUser.sprite.emitter().start(Speck.factory(Speck.LIGHT),0.1f,10);
			}
		}
	};

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
				   && ingredients.get(0) instanceof Weapon w
				   && w.等级+w.tier()>0
//				   && !w.cursed
					;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 5;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			Weapon w = (Weapon)ingredients.get(0);

			if (!w.levelKnown){
				result.数量(resinQuantity(w));
			}
			w.数量0();

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Weapon w = (Weapon)ingredients.get(0);

			if (w.levelKnown){
				return new LiquidMetal().数量(resinQuantity(w));
			} else {
				return new LiquidMetal();
			}
		}

		private int resinQuantity(Weapon w){
			int level = w.等级();
			int quantity = level+w.tier();
			return quantity;
		}
	}
}
