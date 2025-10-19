

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class ShieldBuff extends Buff {
	
	public int shielding;

	//higher priority shielding buffs are consumed first if multiple exist
	//currently we have the following:
	// 2: relatively weak and short term shields like blocking buff
	// 1: larger but still short-term shields from Cleric's ascended form
	// 0: everything else, mostly the various sources of generic barrier
	protected int shieldUsePriority = 0;
	protected boolean detachesAtZero = true;
	
	@Override
	public boolean attachTo(Char target) {
		if (super.attachTo(target)) {
			target.needsShieldUpdate = true;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void detach() {
		target.needsShieldUpdate = true;
		super.detach();
	}
	
	public int 护盾量(){
		return shielding;
	}
	
	public void 设置(int shield ) {
		if(target instanceof Hero hero&&hero.heroClass(HeroClass.逐姝)){
			shield=Math.round(1.15f*shield);
		}
		if (this.shielding <= shield) this.shielding = shield;
		if (target != null) target.needsShieldUpdate = true;
	}
	
	public void 增加(){
		增加(1);
	}

	public void 增加(int amt ){
		if(target instanceof Hero hero&&hero.heroClass(HeroClass.逐姝)){
			amt=Math.round(1.15f*amt);
		}
		shielding += amt;
		if (target != null) target.needsShieldUpdate = true;
	}

	//doesn't add shield, but postpones it detereorating
	public void delay( float value ){
		spend(value);
	}
	
	public void 减少(){
		减少(1);
	}

	public void 减少(int amt ){
		shielding -= amt;
		if (target != null) target.needsShieldUpdate = true;
	}
	
	//returns the amount of damage leftover
	public int absorbDamage( int dmg ){
		if (shielding >= dmg){
			shielding -= dmg;
			dmg = 0;
		} else {
			dmg -= shielding;
			shielding = 0;
		}
		if (shielding <= 0 && detachesAtZero){
			detach();
		}
		if (target != null) target.needsShieldUpdate = true;
		return dmg;
	}

	public static int processDamage( Char target, int damage, Object src ){
		//hunger damage is not affected by shielding
		if (src instanceof Hunger){
			return damage;
		}

		ArrayList<ShieldBuff> buffs = new ArrayList<>(target.buffs(ShieldBuff.class));
		if (!buffs.isEmpty()){
			//sort in descending order based on shield use priority
			Collections.sort(buffs, new Comparator<ShieldBuff>() {
				@Override
				public int compare(ShieldBuff a, ShieldBuff b) {
					return b.shieldUsePriority - a.shieldUsePriority;
				}
			});
			for (ShieldBuff buff : buffs){
				if (buff.护盾量() <= 0) continue;
				damage = buff.absorbDamage(damage);
				if (damage == 0) break;
			}
		}

		return damage;
	}
	
	private static final String SHIELDING = "shielding";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( SHIELDING, shielding);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		shielding = bundle.getInt( SHIELDING );
	}
	
}
