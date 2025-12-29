

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;

public class ElixirOfFeatherFall extends Elixir {

	{
		image = 物品表.ELIXIR_FEATHER;
		icon = 物品表.Icons.羽落;

		talentChance = 1;
	}

	@Override
	public void apply(Hero hero) {
		Buff.新增(hero, FeatherBuff.class, FeatherBuff.DURATION);

		hero.sprite.emitter().burst(Speck.factory(Speck.JET), 20);
		GLog.p(Messages.get(this, "light"));
	}

	public static class FeatherBuff extends FlavourBuff{
		//does nothing, just waits to be triggered by chasm falling
		{
			type=buffType.POSITIVE;
		}
		
		public void processFall(){
			spend(-20f);
			if(cooldown()<=0){
				detach();
			}
		}
		
		public static final float DURATION=400f;
		
		@Override
		public int icon(){
			return BuffIndicator.LEVITATION;
		}
		
		@Override
		public void tintIcon(Image icon){
			icon.hardlight(1f,2f,1.25f);
		}
		
		@Override
		public float iconFadePercent(){
			return Math.max(0,(DURATION-visualcooldown())/DURATION);
		}
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {


		{
			inputs =  new Class[]{浮空药剂.class};
			inQuantity = new int[]{1};

			cost = 10;

			output = ElixirOfFeatherFall.class;
		}

	}

}
