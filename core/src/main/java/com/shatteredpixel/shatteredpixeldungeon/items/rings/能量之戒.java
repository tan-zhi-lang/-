

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.断骨法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.精神支柱;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;

public class 能量之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ENERGY;
		buffClass = Energy.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.2", Math.pow(1.175f, soloBuffedBonus()) - 1f));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.2", Math.pow(1.175f, combinedBuffedBonus(Dungeon.hero)) - 1f));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 0.175f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.2", Math.pow(1.175f, level+1)-1f) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Energy();
	}
	
	public static float wandChargeMultiplier( Char target ){
		float bonus = (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));

		if (target instanceof Hero hero){
			
			if(Dungeon.玩法(玩法设置.鬼怨地牢))bonus*=1.34f;
			if (hero.heroClass != HeroClass.CLERIC && hero.天赋(Talent.轻量阅读)){
				bonus *= 1f + hero.天赋点数(Talent.轻量阅读,0.07f);
			}
			if(hero.visibleEnemies()>0){
				bonus *=精神支柱.增加();
			}else {
				bonus *=精神支柱.减少();
			}
			bonus *=断骨法杖.减少();
			if (hero.belongings.armor instanceof 法袍){
				bonus *= 1.1f;
			}
			if(hero.heroClass(HeroClass.巫女)){
				float c=1;
				for(Item item:hero.belongings){
					if(item.cursed)c+=0.1f;
				}
				bonus*=c;
			}
		}
		return bonus;
	}

	public static float artifactChargeMultiplier( Char target ){
		float bonus = (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));

		if (target instanceof Hero hero){
			if (hero.belongings.armor instanceof 能袍){
				bonus *= 1.1f;
			}
			if(hero.visibleEnemies()>0){
				bonus *=精神支柱.增加();
			}else {
				bonus *=精神支柱.减少();
			}
			if(hero.heroClass(HeroClass.巫女)){
				float c=1;
				for(Item item:hero.belongings){
					if(item.cursed)c+=0.1f;
				}
				bonus*=c;
			}
		}
		if(bonus*0.85f>=1)bonus*=0.85f;

		return bonus;
	}
	public static float weaponChargeMultiplier( Char target ){
		float bonus = (float)Math.pow(1.175, getBuffedBonus(target, Energy.class));

		if (target instanceof Hero hero){
			if(hero.heroClass(HeroClass.DUELIST))bonus+=0.2f;
		}
		
		if(bonus*0.7f>=1)bonus*=0.7f;
		return bonus;
	}
	
	public class Energy extends RingBuff {
	}
}
