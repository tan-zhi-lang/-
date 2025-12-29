

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ElixirOfAquaticRejuvenation extends Elixir {
	
	{
		image = 物品表.ELIXIR_AQUA;
		icon = 物品表.Icons.水灵;
	}
	
	@Override
	public void apply(Hero hero) {
		if (Dungeon.isChallenged(Challenges.NO_HEALING)){
			治疗药剂.pharmacophobiaProc(hero);
		} else {
			Buff.施加(hero, AquaHealing.class).set(Math.round(hero.最大生命 * 1.5f));
		}
	}
	
	public static class AquaHealing extends Buff {
		
		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		private int left;
		
		public void set( int amount ){
			if (amount > left) left = amount;
		}

		public void extend( float duration ) {
			left += duration;
		}
		
		@Override
		public boolean act() {
			
			if (!target.flying && Dungeon.level.water[target.pos] && target.生命 < target.最大生命){
				float healAmt = GameMath.gate( 1, target.最大生命 /50f, left );
				healAmt = Math.min(healAmt, target.最大生命 - target.生命);
				if (Random.Float() < (healAmt % 1)){
					healAmt = (float)Math.ceil(healAmt);
				} else {
					healAmt = (float)Math.floor(healAmt);
				}
				target.回血(healAmt);
				left -= (int)healAmt;
				
				if (target.生命 >= target.最大生命) {
					target.生命 = target.最大生命;
					if (target instanceof Hero) {
						((Hero) target).resting = false;
					}
				}
			}
			
			if (left <= 0){
				detach();
				if (target instanceof Hero) {
					((Hero) target).resting = false;
				}
			} else {
				spend(TICK);
			}
			return true;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.HEALING;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0, 0.75f, 0.75f);
		}

		@Override
		public float iconFadePercent() {
			float max = Math.round(target.最大生命 * 1.5f);
			return Math.max(0, (max - left) / max);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString(left);
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", left);
		}
		
		private static final String LEFT = "left";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( LEFT, left );
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			left = bundle.getInt( LEFT );
			
		}
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{治疗药剂.class, GooBlob.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = ElixirOfAquaticRejuvenation.class;
			outQuantity = 1;
		}

		@Override
		public Item brew(ArrayList<Item> ingredients) {
			Catalog.countUse(GooBlob.class);
			return super.brew(ingredients);
		}
	}
	
}
