

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WandEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.watabou.noosa.audio.Sample;

//for wands that directly damage a target
//wands with AOE or circumstantial direct damage count here (e.g. fireblast, transfusion), but wands with indirect damage do not (e.g. corrosion)
public abstract class DamageWand extends Wand{

	public float min(){
		return min(强化等级());
	}

	public abstract float min(int lvl);

	public float max(){
		return max(强化等级());
	}

	public abstract float max(int lvl);

	public float damageRoll(){
		return damageRoll(强化等级());
	}

	public float damageRoll(int lvl){
		float dmg = Hero.heroDamage(min(lvl),max(lvl));

		WandEmpower emp = Dungeon.hero.buff(WandEmpower.class);
		if (emp != null){
			dmg += emp.dmgBoost;
			emp.left--;
			if (emp.left <= 0) {
				emp.detach();
			}
			Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG, 0.75f, 1.2f);
		}
		return dmg;
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", String.format("%.2f",min()), String.format("%.2f",max()));
		else
			return Messages.get(this, "stats_desc", String.format("%.2f",min(0)), String.format("%.2f",max(0)));
	}

	@Override
	public String upgradeStat1(int level) {
		return String.format("%.2f",min(level)) + "-" + String.format("%.2f",max(level));
	}
}
