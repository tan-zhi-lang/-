

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 能量之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ENERGY;
		buffClass = Energy.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.##", Math.pow(1.175f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.##", Math.pow(1.175f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats",
					Messages.decimalFormat("#.##", 0.175f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", Math.pow(1.175f, level+1)-1f) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Energy();
	}
	
	public static float wandChargeMultiplier( Char target ){
		float bonus = (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));

		if (target instanceof Hero && ((Hero) target).heroClass != HeroClass.CLERIC && ((Hero) target).有天赋(Talent.LIGHT_READING)){
			bonus *= 1f + ((Hero) target).天赋点数(Talent.LIGHT_READING,0.07f);
		}

		if (target instanceof Hero && ((Hero) target).belongings.armor instanceof 法袍){
			bonus *= 1.25f;
		}
		if(target instanceof Hero hero&&hero.heroClass(HeroClass.巫女)){
			bonus/=2f;
		}
		return bonus;
	}

	public static float artifactChargeMultiplier( Char target ){
		float bonus = (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));

		if (target instanceof Hero && ((Hero) target).heroClass != HeroClass.盗贼 && ((Hero) target).有天赋(Talent.LIGHT_CLOAK)){
			bonus *= 1f + ((Hero) target).天赋点数(Talent.LIGHT_CLOAK,0.12f);
		}
		
		
		if (target instanceof Hero && ((Hero) target).belongings.armor instanceof 能袍){
			bonus *= 1.25f;
		}

		return bonus;
	}

	public static float armorChargeMultiplier( Char target ){
		return (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));
	}
	

	public static float weaponChargeMultiplier( Char target ){
		return (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));
	}
	
	public class Energy extends RingBuff {
	}
}
