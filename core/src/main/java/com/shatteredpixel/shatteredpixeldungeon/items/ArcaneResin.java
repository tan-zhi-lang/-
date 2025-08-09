

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class ArcaneResin extends Item {

	{
		image = 物品表.ARCANE_RESIN;

		stackable = true;

		defaultAction = AC_APPLY;

		bones = true;
	}

	private static final String AC_APPLY = "APPLY";

	@Override
	public ArrayList<String> actions(Hero hero ) {
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
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int value() {
		return 30* 数量();
	}

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(ArcaneResin.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return MagicalHolster.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Wand && item.isIdentified();
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof Wand) {
				Wand w = (Wand)item;

				if (w.等级() >= 3){
					GLog.w(Messages.get(ArcaneResin.class, "level_too_high"));
					return;
				}

				int resinToUse = w.等级()+1;

				if (数量() < resinToUse){
					GLog.w(Messages.get(ArcaneResin.class, "not_enough"));

				} else {

					Catalog.countUses(ArcaneResin.class, resinToUse);
					if (resinToUse < 数量()){
						数量(数量()-resinToUse);
					} else {
						detachAll(Dungeon.hero.belongings.backpack);
					}

					w.resinBonus++;
					w.curCharges++;
					w.updateLevel();
					Item.updateQuickslot();

					curUser.sprite.operate(curUser.pos);
					Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
					curUser.sprite.emitter().start( Speck.factory( Speck.UP ), 0.2f, 3 );

					curUser.spendAndNext(Actor.TICK);
					GLog.p(Messages.get(ArcaneResin.class, "apply"));
				}
			}
		}
	};

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
					&& ingredients.get(0) instanceof Wand
					&& ingredients.get(0).cursedKnown
					&& !ingredients.get(0).cursed;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 5;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			Wand w = (Wand)ingredients.get(0);

			if (!w.levelKnown){
				result.数量(resinQuantity(w));
			}
			w.数量(0);

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			Wand w = (Wand)ingredients.get(0);

			if (w.levelKnown){
				return new ArcaneResin().数量(resinQuantity(w));
			} else {
				return new ArcaneResin();
			}
		}

		private int resinQuantity(Wand w){
			int level = w.等级() - w.resinBonus;
			int quantity = 2*(level+1);

			if (Dungeon.hero.heroClass != HeroClass.MAGE && Dungeon.hero.有天赋(Talent.WAND_PRESERVATION)){
				quantity += Dungeon.hero.天赋点数(Talent.WAND_PRESERVATION);
			}
			return quantity;
		}
	}

}
