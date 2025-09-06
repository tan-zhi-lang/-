

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class Cudgel extends MeleeWeapon {

	{
		image = 物品表.CUDGEL;
		hitSound = Assets.Sounds.HIT_CRUSH;
		hitSoundPitch = 1.2f;

		tier = 1;
		间隔=1.2f;
		伤害=0.6f;
		bones = false;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		if(Random.Int(2)==0){
			Buff.延长(defender,Vertigo.class,1);
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	@Override
	protected void duelistAbility(Hero hero, Integer target) {
		//+(3+1.5*lvl) damage, roughly +67% base dmg, +100% scaling
		int dmgBoost = augment.damageFactor(3 + Math.round(1.5f* 强化等级()));
		Mace.heavyBlowAbility(hero, target, 1, dmgBoost, this);
	}

	@Override
	public String abilityInfo() {
		int dmgBoost = levelKnown ? 3 + Math.round(1.5f* 强化等级()) : 3;
		if (levelKnown){
			return Messages.get(this, "ability_desc", augment.damageFactor(最小攻击()+dmgBoost), augment.damageFactor(最大攻击()+dmgBoost));
		} else {
			return Messages.get(this, "typical_ability_desc", 最小攻击(0)+dmgBoost, this.最大攻击(0)+dmgBoost);
		}
	}

	public String upgradeAbilityStat(int level){
		int dmgBoost = 3 + Math.round(1.5f*level);
		return augment.damageFactor(最小攻击(level)+dmgBoost) + "-" + augment.damageFactor(this.最大攻击(level)+dmgBoost);
	}

}
