

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 黄金之心 extends Artifact {

	{
		image = 物品表.ARTIFACT_CAPE;

		levelCap = 3;
		defaultAction = "NONE";
	}

	@Override
	public void charge(Hero target, float amount) {
		if (cursed || target.buff(MagicImmune.class) != null) return;

	}
	
	@Override
	public String desc() {
		String desc = Messages.get(this, "desc");

		return desc;
	}
	
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new 神器();
	}
	
	public class 神器 extends ArtifactBuff{

		@Override
		public boolean act(){

			exp+= 1;
			if (exp >= (等级()+1)*10 && 等级() < levelCap){
				exp -= (等级()+1)*10;
				升级();
				GLog.p( Messages.get(this, "levelup") );
			}
			updateQuickslot();
			spend(TICK);
			return true;
		}

	}


}
