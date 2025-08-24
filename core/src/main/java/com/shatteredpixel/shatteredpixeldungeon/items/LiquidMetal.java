

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts.Dart;
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

		stackable = true;
		白色 = true;

		defaultAction = AC_APPLY;

		bones = true;
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

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(LiquidMetal.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return MagicalHolster.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof MissileWeapon && !(item instanceof Dart);
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof MissileWeapon) {
				MissileWeapon m = (MissileWeapon)item;

				float maxToUse = 5*(m.tier+1);
				maxToUse *= Math.pow(1.35f, m.等级());

				float durabilityPerMetal = 100 / maxToUse;

				//we remove a tiny amount here to account for rounding errors
				float percentDurabilityLost = 0.999f - (m.durabilityLeft()/100f);
				int toUse = (int)Math.ceil(maxToUse*percentDurabilityLost);
				if (toUse == 0 ||
						Math.ceil(m.durabilityLeft()/ m.durabilityPerUse()) >= Math.ceil(m.MAX_DURABILITY/ m.durabilityPerUse()) ){

					if (m.get数量() < m.defaultQuantity()){
						if (get数量()*durabilityPerMetal >= m.durabilityPerUse()){
							m.get数量(m.get数量()+1);
							if (maxToUse < get数量()){
								Catalog.countUses(LiquidMetal.class, (int)Math.ceil(maxToUse));
								GLog.i(Messages.get(LiquidMetal.class, "apply", (int)Math.ceil(maxToUse)));
								quantity -= (int)Math.ceil(maxToUse);
							} else {
								Catalog.countUses(LiquidMetal.class, get数量());
								m.damage(100f);
								m.repair(get数量()*durabilityPerMetal-1);
								GLog.i(Messages.get(LiquidMetal.class, "apply", get数量()));
								detachAll(Dungeon.hero.belongings.backpack);
							}
						} else {
							GLog.w(Messages.get(LiquidMetal.class, "already_fixed"));
							return;
						}
					} else {
						GLog.w(Messages.get(LiquidMetal.class, "already_fixed"));
						return;
					}
				} else if (toUse < get数量()) {
					Catalog.countUses(LiquidMetal.class, toUse);
					m.repair(maxToUse*durabilityPerMetal);
					get数量(get数量()-toUse);
					GLog.i(Messages.get(LiquidMetal.class, "apply", toUse));

				} else {
					Catalog.countUses(LiquidMetal.class, get数量());
					m.repair(get数量()*durabilityPerMetal);
					GLog.i(Messages.get(LiquidMetal.class, "apply", get数量()));
					detachAll(Dungeon.hero.belongings.backpack);
				}

				curUser.sprite.operate();
				Sample.INSTANCE.play(Assets.Sounds.DRINK);
				updateQuickslot();
				curUser.sprite.emitter().start(Speck.factory(Speck.LIGHT), 0.1f, 10);
			}
		}
	};

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe {

		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.size() == 1
					&& ingredients.get(0) instanceof MissileWeapon
					&& ingredients.get(0).cursedKnown
					&& !ingredients.get(0).cursed;
		}

		@Override
		public int cost(ArrayList<Item> ingredients) {
			MissileWeapon m = (MissileWeapon) ingredients.get(0);
			if(m!=null){
				return metalQuantity(m);
			}
			return 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Item result = sampleOutput(ingredients);
			MissileWeapon m = (MissileWeapon) ingredients.get(0);
			if (!m.levelKnown){
				result.get数量(metalQuantity(m));
			}

			m.get数量(0);
			Buff.施加(Dungeon.hero, MissileWeapon.UpgradedSetTracker.class).levelThresholds.put(m.setID, Integer.MAX_VALUE);

			return result;
		}

		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			MissileWeapon m = (MissileWeapon) ingredients.get(0);

			if (m.levelKnown){
				return new LiquidMetal().get数量(metalQuantity(m));
			} else {
				return new LiquidMetal();
			}
		}

		private int metalQuantity(MissileWeapon m){
			float quantityPerWeapon = 5*(m.tier+1);
			if (m.defaultQuantity() != 3){
				quantityPerWeapon = 3f / m.defaultQuantity();
			}
			quantityPerWeapon *= Math.pow(1.33f, Math.min(5, m.等级()));

			float quantity = m.get数量()-1;
			quantity += 0.25f + 0.0075f*m.durabilityLeft();

			return Math.round(quantity * quantityPerWeapon);
		}
	}

}
