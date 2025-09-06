

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 铜钱剑 extends MeleeWeapon {

	{
		image = 物品表.铜钱剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;

		tier = 1;
		命中= 1.2f;
		间隔= 0.7f;
		伤害= 0.5f;
		
		bones = false;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage){
		
		if(defender.properties.contains(Char.Property.UNDEAD)&&attacker instanceof Hero hero&&hero.heroClass(HeroClass.道士)){
			damage=Math.round(damage*1.1f);
		}
		return super.攻击时(attacker,defender,damage);
	}
	
	@Override
	protected int baseChargeUse(Hero hero, Char target){
		if (hero.buff(Sword.CleaveTracker.class) != null){
			return 0;
		} else {
			return 1;
		}
	}
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}
	
	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		Sword.cleaveAbility(hero, target, 1.34f, 0, this);
	}
	
	@Override
	public String abilityInfo() {
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()), augment.damageFactor(最大攻击()));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0), 最大攻击(0));
		}
	}
	
	public String upgradeAbilityStat(int level){
		return augment.damageFactor(最小攻击(level)) + "-" + augment.damageFactor(最大攻击(level));
	}

}
