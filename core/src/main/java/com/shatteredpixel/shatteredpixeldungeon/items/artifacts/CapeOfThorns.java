

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class CapeOfThorns extends Artifact {

	{
		image = 物品表.ARTIFACT_CAPE;

		levelCap = 10;

		charge = 0;
		chargeCap = 100;

		defaultAction = "NONE";
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new Thorns();
	}
	
	@Override
	public void charge(Hero target, float amount) {
			charge += Math.round(4*amount);
			updateQuickslot();
		if (charge >= chargeCap){
			charge=chargeCap;
		}
	}
	
	@Override
	public String desc() {
		String desc = Messages.get(this, "desc");
		if (isEquipped( Dungeon.hero )) {
			desc += "\n\n";
			if (charge == 0)
				desc += Messages.get(this, "desc_inactive");
			else
				desc += Messages.get(this, "desc_active");
		}

		return desc;
	}

	public class Thorns extends ArtifactBuff{

		@Override
		public boolean act(){
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public int proc(int damage, Char attacker, Char defender){
				charge += Math.round(damage*(5+ 等级()*0.25f));
				if (charge >= chargeCap){
					GLog.p( Messages.get(this, "radiating") );
					int deflected = Math.round(damage*(2f+等级()*0.1f));
					damage-=deflected;
					if (attacker != null) {
						attacker.受伤时(deflected, this);
						GLog.w( Messages.get(this, "inert") );
					}
					charge = 0;
					exp+= deflected/5;
				}
			


				if (exp >= (等级()+1)*5 && 等级() < levelCap){
					exp -= (等级()+1)*5;
					升级();
					Catalog.countUse(CapeOfThorns.class);
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
