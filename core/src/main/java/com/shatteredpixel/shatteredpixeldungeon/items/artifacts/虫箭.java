

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.替身保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.暗影替身;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 虫箭 extends Artifact {

	{
		image = 物品表.虫箭;
		defaultAction = AC_USE;
		usesTargeting=false;
	}
	protected static final String AC_USE = "USE";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		if(isEquipped(hero))
		actions.add( AC_USE );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)&&isEquipped(hero)) {
			if(召唤){
				召唤=false;
				Buff.施加(hero,替身保护.class);
			}
			else {
				召唤=true;

				for (Mob m : Dungeon.level.mobs){
					if (m instanceof 暗影替身 x){
						x.destroy();
						x.sprite.die();
						break;
					}
				}
				Buff.detach(hero,替身保护.class);
			}
		}
	}

	@Override
	public void charge(Hero target, float amount) {
		if (cursed ||target.buff(MagicImmune.class)!=null) return;

		//grants 5 turns of healing up-front, if hero isn't starving
		if (target.isStarving()) return;

		float healDelay = (10f - (1.33f + 等级()*0.667f))*1.5f;
		healDelay /= amount;
		float heal = 5f/healDelay;
		//effectively 0.5/1/1.5/2/2.5 HP per turn at +0/+6/+8/+9/+10
		if (Random.Float()<heal%1){
			heal++;
		}
		if (heal >= 1f && target.生命 < target.最大生命) {
			target.回血(Math.round(heal));
			if (target.生命 == target.最大生命) {
				target.resting = false;
			}
		}
	}

	public boolean 召唤=true;
	private static final String 召唤x=        "召唤";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(召唤x,召唤);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		召唤= bundle.getBoolean(召唤x);
	}
	@Override
	public String desc() {
		String desc = Messages.get(this, "desc");

		return desc;
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new 保护();
	}

	public class 保护 extends ArtifactBuff{

	}

}
