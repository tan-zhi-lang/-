

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 心之钢 extends Artifact {

	{
		image = 物品表.心之钢;
		
		levelCap = 10;
		
		charge = 0;
		chargeCap = 100;
	}
	public int 心之钢生命=0;
	private static final String 心之钢生命x=        "心之钢生命";
	
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(心之钢生命x,心之钢生命);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		心之钢生命= bundle.getInt(心之钢生命x);
	}
	
	@Override
	public void charge(Hero target, float amount) {
			charge = Math.min(charge+Math.round(3*amount),chargeCap);
			updateQuickslot();
	}
	
	@Override
	public String desc() {
		String desc = Messages.get(this, "desc",Dungeon.hero.最大生命(0.1f)+等级()+1,Dungeon.hero.大小,心之钢生命);

		return desc;
	}

	public class 心 extends ArtifactBuff{

		@Override
		public boolean act(){
			charge = Math.min(charge+Math.round(3*能量之戒.artifactChargeMultiplier(target)),chargeCap);
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public int proc(int damage, Char attacker, Char defender){
			if(charge>=chargeCap){
				if(attacker instanceof Hero hero){
					GLog.p("你的心之钢为你增加了"+attacker.最大生命(0.1f)+等级()+1+"这次物理攻击伤害，并增加了1最大生命。");
					hero.大小=1+0.05f+等级()*0.025f;
					damage+=attacker.最大生命(0.1f)+等级()+1;
					心之钢生命++;
					exp+=Math.round(damage*(2f+等级()*0.1f));
					charge=0;
					Sample.INSTANCE.play(Assets.Sounds.心之钢);
				}
			}
			
			if (exp >= (等级()+1)*10 && 等级() < levelCap){
				exp -= (等级()+1)*10;
				升级();
				Catalog.countUse(心之钢.class);
				GLog.p( Messages.get(this, "levelup") );
			}
			updateQuickslot();
			return damage;
		}
	}
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new 心();
	}
	

}
