

package com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Drowsy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 永生秘药 extends Elixir {

	{
		image = 物品表.永生秘药;
		
		特别= true;

		talentFactor = 2f;
	}
	public static final String AC_ITEM = "ITEM";
	
	@Override
	public void apply( Hero hero ) {
		cure( hero );
		heal( hero );
		hero.belongings.uncurseEquipped();
		hero.buff( Hunger.class ).吃饭( Hunger.STARVING );
	}
	
	public static void heal( Char ch){
		if (ch == Dungeon.hero && Dungeon.isChallenged(Challenges.NO_HEALING)){
			pharmacophobiaProc(Dungeon.hero);
		} else {
			//starts out healing 30 hp, equalizes with hero health total at level 11
			Healing healing = Buff.施加(ch,Healing.class);
			healing.setHeal(ch.最大生命, 1, 0);
			healing.applyVialEffect();
			if (ch == Dungeon.hero){
				GLog.p(Messages.get(治疗药剂.class,"heal"));
			}
		}
	}
	
	public static void pharmacophobiaProc( Hero hero ){
		// harms the hero for ~40% of their max HP in poison
		Buff.施加( hero, Poison.class).set(8+hero.等级);
	}
	
	public static void cure( Char ch ) {
		Buff.detach( ch, Poison.class );
		Buff.detach( ch, Cripple.class);
		Buff.detach( ch, Weakness.class);
		Buff.detach( ch, Vulnerable.class);
		Buff.detach( ch, 流血.class);
		Buff.detach( ch, Blindness.class);
		Buff.detach( ch, Drowsy.class);
		Buff.detach( ch, Slow.class);
		Buff.detach( ch, Vertigo.class);
	}
	
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{治疗药剂.class,};
			inQuantity = new int[]{1,};
			
			cost = 16;
			
			output = 永生秘药.class;
			outQuantity = 1;
		}
		
	}
	
}
