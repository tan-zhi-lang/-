

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class 心之钢 extends Artifact {

	{
		image = 物品表.心之钢;
		
		levelCap = 3;
		
		charge = 0;
		chargeCap = 100;
	}
	
	@Override
	public void charge(Hero target, float amount) {
			charge = Math.min(charge+Math.round(5*amount),chargeCap);
			updateQuickslot();
	}
	
	@Override
	public String desc() {
		float 伤害=武力之戒.heromax()+Dungeon.hero.最大生命(0.04f+0.02f*等级());
		float 生命=伤害*(0.04f+等级()*0.02f);
		String desc = Messages.get(this, "desc",伤害,生命,
								   String.format("%.1f",Dungeon.hero.大小));

		return desc;
	}

	public class 心 extends ArtifactBuff{

		@Override
		public boolean act(){
			charge = Math.min(charge+Math.round(5*能量之戒.artifactChargeMultiplier(target)),chargeCap);
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public int proc(int damage, Char attacker, Char defender){
			if(charge>=chargeCap){
				if(attacker instanceof Hero hero){
					hero.大小=1.025f+等级()*0.025f;
					//1.6x游戏秒=回合
					float 伤害=武力之戒.heromax()+attacker.最大生命(0.04f+0.02f*等级());
					damage+=伤害;

					float 生命=伤害*(0.04f+等级()*0.02f);
					hero.生命成长+=生命;
					hero.更新属性();

					GLog.w("心之钢为这次物理攻击+"+伤害+"伤害，并+"+生命+"最大生命。");

					exp+=生命*3;
					charge=0;
					Sample.INSTANCE.play(Assets.Sounds.心之钢);
				}
			}
			
			if (exp >= (等级()+1)*33 && 等级() < levelCap){
				exp -= (等级()+1)*33;
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
