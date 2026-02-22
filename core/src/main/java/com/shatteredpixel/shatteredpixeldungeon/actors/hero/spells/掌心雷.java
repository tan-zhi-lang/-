

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 掌心雷 extends 目标道术 {

	public static final 掌心雷 INSTANCE = new 掌心雷();

	@Override
	public int icon() {
		return HeroIcon.掌心雷;
	}
	
	@Override
	protected void onTargetSelected(本命玉佩 tome,Hero hero,Integer target){

		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null||enemy instanceof NPC||enemy==hero||hero.isCharmedBy(enemy)||!Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(Weapon.class,"ability_no_target"));
			return;
		}

		if (!hero.canAttack(enemy)){
			GLog.w(Messages.get(Weapon.class, "ability_target_range"));
			return;
		}

		hero.sprite.attack(enemy.pos, new Callback() {
			@Override
			public void call() {
				AttackIndicator.target(enemy);
				enemy.受伤(enemy.最大生命(0.2f)+enemy.最大防御());
				Invisibility.notimedispel();
				hero.sprite.centerEmitter().burst(SparkParticle.FACTORY,10);
				hero.sprite.flash();
				Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
				hero.spendAndNext(hero.攻击延迟());
			}
		});
	}
	
	@Override
	public String desc(){
		String desc = Messages.get(this, "desc");
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
