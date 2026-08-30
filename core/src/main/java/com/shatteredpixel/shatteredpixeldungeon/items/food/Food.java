

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Food extends Item {

	public static final float TIME_TO_EAT	= 3f;
	
	public static final String AC_EAT	= "EAT";
	public static final String AC_食物栏	= "食物栏";

	public float energy = Hunger.HUNGRY;
	
	{
		可堆叠= true;
		image = 物品表.RATION;

		defaultAction = AC_EAT;
		
		遗产= true;
		物品 = true;
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
					inputs =  new Class[]{SmallRation.class,};
			inQuantity = new int[]{1,};

			cost = 3;

			output = Food.class;
			outQuantity = 1;
		}

	}
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if(!(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云)))
		actions.add( AC_EAT );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );
		if(hero.符文("我是瘦子")&&!hero.buff(Hunger.class).空腹())return;
		if (action.equals( AC_EAT )||action.equals( AC_食物栏 )) {

			if(action.equals( AC_EAT ))
			detach( hero.belongings.backpack );

			if(hero.符文("备用口粮")&&
			  Random.Int(1)==0)new Food().放背包();

			Catalog.countUse(getClass());
			
			satisfy(hero);
			GLog.白(Messages.get(this,"eat_msg"));
			
			hero.sprite.operate( hero.pos );
			hero.busy();
			SpellSprite.show( hero, SpellSprite.FOOD );
			eatSFX();
			
			hero.spend( eatingTime() );
			
			Statistics.foodEaten++;
			Badges.validateFoodEaten();
			
		}
	}

	protected void eatSFX(){
		Sample.INSTANCE.play( Assets.Sounds.EAT );
	}

	public float eatingTime(){
		if(Dungeon.符文("细嚼慢咽"))return TIME_TO_EAT*1.5f;
		return TIME_TO_EAT;

	}
	
	protected void satisfy( Hero hero ){
		float foodVal = energy;

		Buff.施加(hero, Hunger.class).吃饭(foodVal);
	}
	
	@Override
	public int 金币() {
		return 10 * quantity;
	}
}
