

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor.Glyph;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class 粘稠 extends Glyph {

	@Override
	public float proc( Armor armor, Char attacker, Char defender, float damage ) {
		if(defender!=null){
			//we use a tracker so that this glyph can apply after armor
			Buff.施加(defender,ViscosityTracker.class);
		}
		return damage;
		
	}

	@Override
	public Glowing glowing() {
		return 粉;
	}

	public static class ViscosityTracker extends Buff {

		{
			actPriority = Actor.VFX_PRIO;
		}


		public float deferDamage(float dmg){
			//account for icon stomach (just skip the glyph)
			if (target.buff(Talent.WarriorFoodImmunity.class) != null){
				return dmg;
			}

			float percent = 0.3f*genericProcChanceMultiplier(target);

			float amount= dmg * percent;


			if (amount > 0){
				DeferedDamage deferred = Buff.施加( target, DeferedDamage.class );
				deferred.extend( amount );

				target.sprite.showStatus(CharSprite.警告橙,Messages.get(粘稠.class,"deferred",amount));
			}

			return dmg - amount;
		}

		@Override
		public boolean act() {
			detach();
			return true;
		}
	}
	
	public static class DeferedDamage extends Buff {
		
		{
			type = buffType.NEGATIVE;
		}
		
		protected float damage = 0;
		
		private static final String DAMAGE	= "damage";
		
		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( DAMAGE, damage );
			
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			damage = bundle.getFloat( DAMAGE );
		}
		
		public void extend( float damage ) {
			if (this.damage == 0){
				//wait 1 turn before damaging if this is freshly applied
				postpone(TICK);
			}
			this.damage += damage;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.DEFERRED;
		}

		@Override
		public String iconTextDisplay() {
			return Float.toString(damage);
		}
		
		@Override
		public boolean act() {
			if (target.isAlive()) {

				float damageThisTick = Math.max(1, damage*0.1f);
				target.受伤时( damageThisTick, this );
				if (target == Dungeon.hero && !target.isAlive()) {

					Badges.validateDeathFromFriendlyMagic();

					Dungeon.fail( this );
					GLog.红(Messages.get(this,"ondeath"));
				}
				spend( TICK );

				damage -= damageThisTick;
				if (damage <= 0) {
					detach();
				}
				
			} else {
				
				detach();
				
			}
			
			return true;
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", damage);
		}
	}
}
