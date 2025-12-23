

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 荆棘斗篷 extends Artifact {

	{
		image = 物品表.ARTIFACT_CAPE;

		levelCap = 10;

		charge = 0;
		chargeCap = 100;

		defaultAction = "NONE";
	}
	
	@Override
	public void charge(Hero target, float amount) {
		charge = Math.min(charge+10,chargeCap);
		updateQuickslot();
	}
	
	@Override
	public String desc() {
		String desc = Messages.get(this, "desc",0.9f+等级()*0.4f);

		return desc;
	}
	
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new Thorns();
	}
	
	public class Thorns extends ArtifactBuff{

		@Override
		public boolean act(){
			charge = Math.min(charge+Math.round(10*能量之戒.artifactChargeMultiplier(target)),chargeCap);
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public int proc(int damage, Char attacker, Char defender){
				if (charge >= chargeCap){
					GLog.p( Messages.get(this, "radiating",Math.round(damage*(0.5f+等级()*0.2f))));
					int deflected = Math.round(damage*(0.5f+等级()*0.2f));
					damage=0;
					if (attacker != null) {
						Talent.onArtifactUsed(Dungeon.hero);
						attacker.受伤时(deflected, this);
					}
					charge = 0;
					exp+= deflected/5;
				}
				
				if (exp >= (等级()+1)*10 && 等级() < levelCap){
					exp -= (等级()+1)*10;
					升级();
					Catalog.countUse(荆棘斗篷.class);
					GLog.p( Messages.get(this, "levelup") );
				}
			updateQuickslot();
			return damage;
		}

		@Override
		public int icon() {
				return BuffIndicator.THORNS;
		}

	}


}
