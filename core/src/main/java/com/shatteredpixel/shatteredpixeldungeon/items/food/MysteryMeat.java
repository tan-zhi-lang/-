

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class MysteryMeat extends Food {

	{
		image = 物品表.MEAT;
		energy = Hunger.HUNGRY/2f;
		炼金全放 = true;
	}
	
	@Override
	protected void satisfy(Hero hero) {
		super.satisfy(hero);
		effect(hero);
	}

	public int 金币() {
		return 5 * quantity;
	}

	public static void effect(Hero hero){
		switch (Random.Int( 5 )) {
			case 0:
				GLog.w( Messages.get(MysteryMeat.class, "hot") );
				Buff.施加( hero, 燃烧.class ).reignite( hero );
				break;
			case 1:
				GLog.w( Messages.get(MysteryMeat.class, "legs") );
				Buff.延长( hero, Roots.class, Roots.DURATION*2f );
				break;
			case 2:
				GLog.w( Messages.get(MysteryMeat.class, "not_well") );
				Buff.施加( hero, Poison.class ).set( hero.最大生命 / 5 );
				break;
			case 3:
				GLog.w( Messages.get(MysteryMeat.class, "stuffed") );
				Buff.延长( hero, Slow.class, Slow.DURATION );
				break;
		}
	}
	
	public static class PlaceHolder extends MysteryMeat {
		
		{
			image = 物品表.FOOD_HOLDER;
		}
		
		@Override
		public boolean isSimilar(Item item) {
			return item instanceof MysteryMeat || item instanceof StewedMeat
					|| item instanceof ChargrilledMeat || item instanceof FrozenCarpaccio;
		}
		
		@Override
		public String info() {
			return "";
		}
	}
	public static class PlaceHolderC extends MysteryMeat {

		{
			image = 物品表.MEAT;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof MysteryMeat;
		}

		@Override
		public String info() {
			return "";
		}
	}
}
