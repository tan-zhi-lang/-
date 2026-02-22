

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.断骨法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.精神支柱;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;

public class 能量之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_ENERGY;
		buffClass = Energy.class;
	}

	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 0.1845f*soloBuffedBonus()));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						Messages.decimalFormat("#.2", 0.1845f*combinedBuffedBonus(Dungeon.hero)));
			}
			return info;
		} else {
			return Messages.get(this, "stats",
					Messages.decimalFormat("#.2", 0.1845f));
		}
	}

	public String upgradeStat1(int level){
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return Messages.decimalFormat("#.2", 0.1845f*(level+1)) + "倍";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Energy();
	}
	
	public static float wandChargeMultiplier( Char target ){
		float bonus =1+ 0.1845f*getBuffedBonus(target, Energy.class);

		if (target instanceof Hero hero){

			int 法杖数量=0;
			for(Item i:hero.belongings.backpack.items){
				if(i instanceof Wand a)
					法杖数量+=1+a.强化等级();
			}
			if(hero.符文("法杖收集家"))
				bonus+=法杖数量*0.1f;

			if(Dungeon.赛季(赛季设置.鬼怨地牢)) bonus+=0.35f;
			if (hero.heroClass != HeroClass.CLERIC && hero.天赋(Talent.轻量阅读)){
				bonus += hero.天赋点数(Talent.轻量阅读,0.07f);
			}
			if(hero.符文("尖端发明家"))bonus+=0.35f;
			if(hero.符文("由暴生急"))bonus+=0.5*hero.暴击率();
			if (hero.belongings.armor instanceof 法袍){
				bonus += 0.1f;
			}
			if(hero.heroClass(HeroClass.巫女)){
				float c=0;
				for(Item item:hero.belongings){
					if(item.cursed)c+=0.1f;
				}
				bonus+=c;
			}
			if(hero.visibleEnemies()>0){
				bonus *=精神支柱.增加();
			}else {
				bonus *=精神支柱.减少();
			}
			bonus *=断骨法杖.减少();
		}
		return bonus;
	}

	public static float artifactChargeMultiplier( Char target ){
		float bonus = 1+ 0.1845f*getBuffedBonus(target, Energy.class);

		if (target instanceof Hero hero){

			int 神器数量=0;
			for(Item i:hero.belongings.backpack.items){
				if(i instanceof Artifact a)
					神器数量+=1+a.等级();
			}
			if(hero.符文("神器收集家"))
				bonus+=神器数量*0.2f;

			if (hero.belongings.armor instanceof 能袍){
				bonus += 0.1f;
			}
			if(hero.符文("尖端发明家"))bonus+=-.35f;
			if(hero.符文("由暴生急"))bonus+=0.5*hero.暴击率();
			if(hero.heroClass(HeroClass.巫女)){
				float c=0;
				for(Item item:hero.belongings){
					if(item.cursed)c+=0.1f;
				}
				bonus+=c;
			}
			if(hero.visibleEnemies()>0){
				bonus *=精神支柱.增加();
			}else {
				bonus *=精神支柱.减少();
			}
		}

		return bonus;
	}
	public static float weaponChargeMultiplier( Char target ){
		float bonus = 1+ 0.1845f*getBuffedBonus(target, Energy.class);

		if (target instanceof Hero hero){
			if(hero.符文("尖端发明家"))bonus+=0.35f;
			if(hero.符文("由暴生急"))bonus+=0.5*hero.暴击率();
			if(hero.heroClass(HeroClass.DUELIST))bonus+=0.2f;
		}

		return bonus;
	}
	
	public class Energy extends RingBuff {
	}
}
