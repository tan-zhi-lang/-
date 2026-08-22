

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.法态穿透;
import com.shatteredpixel.shatteredpixeldungeon.items.TengusMask;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.巨大蟹钳;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.地裂镰;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.元素类型;
import com.watabou.utils.PathFinder;

public class 元素之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ELEMENTS;
		buffClass = Resistance.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   kw2(100f*(float)(1f-Math.pow(0.825f,soloBuffedBonus()))),
									   kw2(100f*(float)(1f/Math.pow(0.825f,soloBuffedBonus())))
									  );
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  kw2(100f*(float)(1f-Math.pow(0.825f,combinedBuffedBonus(Dungeon.hero)))),
												  kw2(100f*(float)(1f/Math.pow(0.825f,combinedBuffedBonus(Dungeon.hero))))
											 );
			}
			return info;
		} else {
			return Messages.get(this, "stats",  kw2(17.5f), kw2(100f/82.5f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 100f * (1f - Math.pow(0.825f, level)) + "%";
	}
	public String upgradeStat2(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return 100f * (1f/Math.pow(0.825f, level)) + "%";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}

	public static float 抗性(Char target,Class effect){
		for (Class c : 元素类型.RESISTS){
			if (c.isAssignableFrom(effect)){
				return 抗性(target);
			}else{
				return 1;
			}
		}
		return 1;
	}
	public static float 抗性(Char target){
		float x=1;
		if(target instanceof Hero hero){
			x*=1-hero.天赋点数(Talent.神圣净化,0.1f);
			x*=巨大蟹钳.受到();
			if(hero.符文("面壁者")){
				for(int i: PathFinder.相邻){
					if(Dungeon.level.在墙体(i+hero.pos))
						x*=0.92f;
				}
			}
			if(hero.符文("黄金忍者")&&(
					hero.belongings.hasItem(火焰剑.class)&&
					hero.belongings.hasItem(地裂镰.class)&&
					hero.belongings.hasItem(寒冰镖.class)&&
					hero.belongings.hasItem(闪电双截棍.class)
			))x*=0.5f;
			if(hero.符文("艾哲红石")&&hero.belongings.hasItem(TengusMask.class)) x*=0.1f;
			if(hero.符文("防御转魔抗"))x*=1-hero.最大防御()/(2.5+hero.最大防御());
			if(hero.subClass(HeroSubClass.大魔法师)) x*=0.7f;

			if(hero.种族天赋.equals("龙人"))x*=0.7f;

			if(hero.符文("法态:穿透")){
				if(hero.nobuff(法态穿透.class)){

					x*=0;
				}
			}
			if(hero.英精英雄==2)x*=0;

//			if (getBuffedBonus(target, Resistance.class) == 0) return 1;
					return (float)Math.pow(0.825f, getBuffedBonus(target, Resistance.class))*x;

		}else if(Dungeon.hero()){
			return x;
		}
		return x;
	}

	public static float 害怕( Char target, Class effect ){
		for (Class c : 元素类型.RESISTS){
			if (c.isAssignableFrom(effect)){
				return 害怕(target);
			}else{
				return 1;
			}
		}
		return 1;
	}
	public static float 害怕( Char target ){
		float x=1;
		if(target instanceof Hero hero){
			return x;
		}else if(Dungeon.hero()){
			//			if (getBuffedBonus(target, Resistance.class) == 0) return 1;
			return 1f/(float)Math.pow(0.825f, getBuffedBonus(target, Resistance.class))*x;

		}
		return x;
	}

	public class Resistance extends RingBuff {
	
	}
}
