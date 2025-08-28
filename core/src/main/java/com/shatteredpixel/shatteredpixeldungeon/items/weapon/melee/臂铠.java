

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 臂铠 extends MeleeWeapon {

	{
		image = 物品表.臂铠;
		hitSoundPitch = 1.3f;

		tier = 1;
		间隔= 0.67f; //2x speed
		
		bones = false;
	}

	@Override
	public int 最小攻击(int lvl) {
		return  1+tier +  //base
				lvl;    //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  Math.round(3f*(tier+1)) +     //5 base, down from 10
				lvl*Math.round(0.6f*(tier+1));  //+1 per level, down from +2
	}
	
	@Override
	public int defenseFactor( Char owner) {
		return 最大防御();
	}
	
	public int 最大防御(){
		return 最大防御(强化等级());
	}
	
	//4 extra defence, plus 1 per level
	public int 最大防御(int lvl){
		return 2 + lvl;
	}
	
	public String statsInfo(){
		if (已鉴定()){
			return Messages.get(this, "stats_desc", 2+ 强化等级());
		} else {
			return Messages.get(this, "typical_stats_desc", 2);
		}
	}
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		Sai.comboStrikeAbility(hero, target, 1, 0, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 2 + 强化等级()/3 : 2;
		if (levelKnown){
			return Messages.get(this, "ability_desc", dmgBoost);
		} else {
			return Messages.get(this, "typical_ability_desc", dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		return "+" + augment.damageFactor(2 + level);
	}

}
