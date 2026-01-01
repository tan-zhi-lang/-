

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.巨大蟹钳;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.HashSet;

public class RingOfElements extends Ring {

	{
		icon = 物品表.Icons.RING_ELEMENTS;
		buffClass = Resistance.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, soloBuffedBonus()))),
					Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, soloBuffedBonus())))
									  );
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, combinedBuffedBonus(Dungeon.hero)))),
						Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, combinedBuffedBonus(Dungeon.hero))))
											 );
			}
			return info;
		} else {
			return Messages.get(this, "stats", Messages.decimalFormat("#.2", 17.5f), Messages.decimalFormat("#.2", 17.5f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, level+1))) + "%";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}

	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( 燃烧.class );
		RESISTS.add( Chill.class );
		RESISTS.add( Frost.class );
		RESISTS.add( Ooze.class );
		RESISTS.add( Paralysis.class );
		RESISTS.add( Poison.class );
		RESISTS.add( Corrosion.class );

		RESISTS.add( ToxicGas.class );
		RESISTS.add( Electricity.class );

		RESISTS.addAll( AntiMagic.RESISTS );
	}
	
	public static float resist( Char target, Class effect ){
		float x=1;
		if(target instanceof Hero hero){
			x*=1-hero.天赋点数(Talent.神圣净化,0.15f);
			x*=巨大蟹钳.受到();
			if (getBuffedBonus(target, Resistance.class) == 0) return x;
			
			for (Class c : RESISTS){
				if (c.isAssignableFrom(effect)){
					return (float)Math.pow(0.825, getBuffedBonus(target, Resistance.class))*x;
				}
			}
			
		}else if(Dungeon.hero()){
			x*=1+Dungeon.hero.天赋点数(Talent.元素之力,0.1f);
			if (getBuffedBonus(Dungeon.hero, Resistance.class) == 0) return x;
			
			for (Class c : RESISTS){
				if (c.isAssignableFrom(effect)){
					return 1/(float)Math.pow(0.825, getBuffedBonus(Dungeon.hero, Resistance.class))*x;
				}
			}
		}
		return x;
	}
	
	public class Resistance extends RingBuff {
	
	}
}
