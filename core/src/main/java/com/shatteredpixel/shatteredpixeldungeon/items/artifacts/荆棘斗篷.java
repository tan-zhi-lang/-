

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
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

		增强方式="物理防御";
		charge = 0;
		chargeCap = 100;

	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (target.buff(MagicImmune.class)!=null) return;
		charge = Math.min(charge+amount*10,chargeCap);
		updateQuickslot();
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc",Math.round((0.5f+等级()*0.05f)*100)
				,kw2(Dungeon.hero.最大护甲((1+等级())/3f)))+stas();
	}
	
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new Thorns();
	}
	
	public class Thorns extends ArtifactBuff{

		@Override
		public boolean act(){
			charge = Math.min(charge+10*能量之戒.artifactChargeMultiplier(target)*(1+(Dungeon.符文("升级荆棘斗篷")?0.45f:0)),chargeCap);
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public float proc(float damage, Char defender){
				if(charge>=chargeCap){
					if(defender!=null)
					GLog.绿(Messages.get(this,"radiating",0,(1+等级())/3f*Dungeon.hero.最大护甲));

					float deflected=damage*(1-0.5f+等级()*0.05f);

					if(defender!=null){
						Talent.onArtifactUsed(Dungeon.hero);
					}
					damage+=deflected;

					if(defender!=null){
						charge=0;
						exp+=deflected;
					}
				}

				if(defender!=null&&exp>=(等级()+1)*20&&等级()<levelCap){
					exp-=(等级()+1)*20;
					升级();
					Catalog.countUse(荆棘斗篷.class);
					GLog.绿(Messages.get(this,"levelup"));
				}
				if(defender!=null)
				updateQuickslot();

			return damage;
		}

		public float proc(float damage, Char attacker, Char defender){
				if (charge >= chargeCap){
					GLog.绿(Messages.get(this,"radiating",Math.round((0.5f+等级()*0.05f)*100)
							,(1+等级())/3f*Dungeon.hero.最大护甲));
					float deflected = damage*(0.5f+等级()*0.05f);
					damage=0.5f-0.05f*等级();
					if (attacker != null) {
						Talent.onArtifactUsed(Dungeon.hero);
						attacker.受伤时(deflected, this);
					}
					charge = 0;
					exp+= deflected;
				}

				if (exp >= (等级()+1)*20 && 等级() < levelCap){
					exp -= (等级()+1)*20;
					升级();
					Catalog.countUse(荆棘斗篷.class);
					GLog.绿(Messages.get(this,"levelup"));
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
