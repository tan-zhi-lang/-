package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 报复 extends 武技{
	@Override
	public void 武技(Hero hero,Integer target){
		
		if (hero.生命 / (float)hero.最大生命 >= 0.5f){
			GLog.w(Messages.get(this, "ability_cant_use"));
			return;
		}
		
		if (target == null) {
			return;
		}
		
		Char enemy = Actor.findChar(target);
		if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(this, "ability_no_target"));
			return;
		}
		
		hero.belongings.abilityWeapon = wep;
		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(this, "ability_target_range"));
			hero.belongings.abilityWeapon = null;
			return;
		}
		hero.belongings.abilityWeapon = null;
		
		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
//				beforeAbilityUsed(hero, enemy);
				AttackIndicator.target(enemy);
				
				//+(15+(2*lvl)) damage, roughly +60% base damage, +55% scaling
				int dmgBoost = 2;//augment.damageFactor(15 + 2* 强化等级())
				
				if (hero.attack(enemy, 1, dmgBoost, Char.INFINITE_ACCURACY)){
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
				}
				
				Invisibility.notimedispel();
				if (!enemy.isAlive()){
					hero.next();
//					onAbilityKill(hero, enemy);
				} else {
					hero.spendAndNext(hero.攻击延迟());
				}
//				afterAbilityUsed(hero);
			}
		});
		super.武技(hero,target);
	}
	
}
