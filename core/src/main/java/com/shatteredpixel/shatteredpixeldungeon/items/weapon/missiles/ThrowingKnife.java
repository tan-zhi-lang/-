

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class ThrowingKnife extends MissileWeapon {
	
	{
		image = 物品表.THROWING_KNIFE;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.2f;
		
		bones = false;
		
		tier = 1;
		baseUses = 5;
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return  6 * tier +                      //6 base, up from 5
				(tier == 1 ? 2*lvl : tier*lvl); //scaling unchanged
	}
	
	@Override
	public int damageRoll(Char owner) {
		if (owner instanceof Hero) {
			Hero hero = (Hero)owner;
			Char enemy = hero.attackTarget();
			if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)) {
				//deals 75% toward max to max on surprise, instead of min to max.
				int diff = 最大攻击() - 最小攻击();
				int damage = augment.damageFactor(Hero.heroDamageIntRange(
						最小攻击() + Math.round(diff*0.75f),
						最大攻击()));
				int exStr = hero.力量() - 力量();
				if (exStr > 0) {
					damage += Hero.heroDamageIntRange(0, exStr);
				}
				return damage;
			}
		}
		return super.damageRoll(owner);
	}
}
