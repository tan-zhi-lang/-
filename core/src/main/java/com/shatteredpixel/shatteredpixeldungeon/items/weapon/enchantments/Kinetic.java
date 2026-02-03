

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class Kinetic extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing YELLOW = new ItemSprite.Glowing( 0xFFFF00 );

	@Override
	public float proc(Weapon weapon, Char attacker, Char defender, float damage) {

		return damage;
	}
	
	@Override
	public ItemSprite.Glowing glowing() {
		return YELLOW;
	}

	public static class KineticTracker extends Buff {

		{
			actPriority = Actor.VFX_PRIO;
		}

		public float conservedDamage;

		@Override
		public boolean act() {
			detach();
			return true;
		}
	}
	
	public static class ConservedDamage extends Buff {

		{
			type = buffType.POSITIVE;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.WEAPON;
		}
		
		@Override
		public void tintIcon(Image icon) {
			if (preservedDamage >= 10){
				icon.hardlight(1f, 0f, 0f);
			} else if (preservedDamage >= 5) {
				icon.hardlight(1f, 1f - (preservedDamage - 5f)*.2f, 0f);
			} else {
				icon.hardlight(1f, 1f, 1f - preservedDamage*.2f);
			}
		}

		@Override
		public String iconTextDisplay() {
			return Float.toString(damageBonus());
		}
		
		private float preservedDamage;
		
		public void setBonus(float bonus){
			preservedDamage = bonus;
		}
		
		public float damageBonus(){
			return preservedDamage;
		}
		
		@Override
		public boolean act() {
			preservedDamage -= Math.max(preservedDamage*.025f, 0.1f);
			if (preservedDamage <= 0) detach();
			
			spend(TICK);
			return true;
		}

		public void delay( float value ){
			spend(value);
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", damageBonus());
		}
		
		private static final String PRESERVED_DAMAGE = "preserve_damage";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(PRESERVED_DAMAGE, preservedDamage);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if (bundle.contains(PRESERVED_DAMAGE)){
				preservedDamage = bundle.getFloat(PRESERVED_DAMAGE);
			} else {
				preservedDamage = cooldown()/10;
				spend(cooldown());
			}
		}
	}
}
