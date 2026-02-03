

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
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
		if(Dungeon.派对(派对设置.钢门联盟)){
			charge = Math.min(charge+Math.round(15*amount),chargeCap);
		}else{
			charge = Math.min(charge+Math.round(5*amount),chargeCap);
		}
			updateQuickslot();
	}
	
	@Override
	public String desc() {
		float 伤害=武力之戒.heromax()+Dungeon.hero.最大生命(0.04f+0.02f*等级());
		float 生命=伤害*(0.04f+等级()*0.02f);
		String desc = Messages.get(this, "desc",String.format("%.2f",伤害),String.format("%.2f",生命),
								   String.format("%.1f",Dungeon.hero.大小));

		return desc;
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			if (!collect || !Dungeon.派对(派对设置.钢门联盟)){
				if (activeBuff != null){
					activeBuff.detach();
					activeBuff = null;
				}
			} else {
				activate(hero);
			}

			return true;
		} else
			return false;
	}
	@Override
	public boolean 放背包(Bag container) {
		if (super.放背包(container)){
			if (container.owner instanceof Hero
				&& passiveBuff == null
				&& Dungeon.派对(派对设置.钢门联盟)){
				activate((Hero) container.owner);
			}
			return true;
		} else{
			return false;
		}
	}
	public class 心 extends ArtifactBuff{

		@Override
		public boolean act(){
			if(Dungeon.派对(派对设置.钢门联盟)){
				charge=Math.min(charge+Math.round(15*能量之戒.artifactChargeMultiplier(target)),chargeCap);
			}else{
				Math.min(charge+Math.round(5*能量之戒.artifactChargeMultiplier(target)),chargeCap);
			}
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public float proc(float damage, Char attacker, Char defender){
			if(charge>=chargeCap){
				if(attacker instanceof Hero hero){
					hero.大小=1.025f+等级()*0.025f;
					//1.6x游戏秒=回合
					float 伤害=武力之戒.heromax()+attacker.最大生命(0.04f+0.02f*等级());
					damage+=伤害;

					float 生命=伤害*(0.04f+等级()*0.02f);
					hero.生命成长+=生命;
					hero.更新属性();

					GLog.w("心之钢为这次物理攻击+"+String.format("%.2f",伤害)+"伤害，并+"+String.format("%.2f",生命)+"最大生命。");

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
