

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
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
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2", 100f * (1f - Math.pow(0.825f, level+1))) + "%";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}

	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
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
		for (Class c : RESISTS){
			if (c.isAssignableFrom(effect)){
				return resist(target,effect);
			}else{
				return 1;
			}
		}
		return 1;
	}
	public static float resist( Char target ){
		float x=1;
		if(target instanceof Hero hero){
			x*=1-hero.天赋点数(Talent.神圣净化,0.1f);
			x*=巨大蟹钳.受到();
			if(hero.符文("防御转魔抗"))x*=1-hero.最大防御()/(2.5+hero.最大防御());
			if(hero.subClass(HeroSubClass.元素法师))x*=0.7f;

			if(hero.种族天赋.equals("龙人"))x*=0.7f;
			if(hero.英精英雄==2)x*=0;
//			if (getBuffedBonus(target, Resistance.class) == 0) return 1;
					return (float)Math.pow(0.825f, getBuffedBonus(target, Resistance.class))*x;

		}else if(Dungeon.hero()){
			Hero hero=Dungeon.hero;
			if(hero.种族天赋.equals("龙人"))x*=0.7f;
			x*=1+hero.天赋点数(Talent.元素之力,0.075f);
//			if (getBuffedBonus(hero, Resistance.class) == 0) return 1;

			if(hero.subClass(HeroSubClass.元素法师)&&hero.职业精通())x*=1+0.3f;
				return (float)Math.pow(0.825, getBuffedBonus(hero, Resistance.class))*x;
		}
		return x;
	}
	
	public class Resistance extends RingBuff {
	
	}
}
