

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
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
		橙色=true;
		levelCap = 3;
		
		charge = 0;
		chargeCap = 100;
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if(Dungeon.派对(派对设置.钢门联盟)){
			charge = Math.min(charge+100*amount,chargeCap);
		}else{
			charge = Math.min(charge+34*amount,chargeCap);
		}
			updateQuickslot();
	}
	
	@Override
	public String desc() {
		float 伤害=20*(0.06f+0.02f*等级());
		if(Dungeon.hero())伤害=Dungeon.hero.最大生命(0.06f+0.02f*等级());
		float 大小 =1;
		if(Dungeon.hero())大小=Dungeon.hero.大小();

		float 生命=伤害*(0.03f+等级()*0.01f);
		if(Dungeon.派对(派对设置.钢门联盟))生命*=2;
		if(Dungeon.符文("钢化你心"))生命*=3;
		String desc = Messages.get(this, "desc",伤害,生命,大小);

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
				activate(container.owner);
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
				charge=Math.min(charge+100*能量之戒.artifactChargeMultiplier(target),chargeCap);
			}else{
				charge=Math.min(charge+34*能量之戒.artifactChargeMultiplier(target),chargeCap);
			}
			updateQuickslot();
			spend(TICK);
			return true;
		}

		public float proc(float damage, Char attacker, Char defender){
			if(charge>=chargeCap){
				if(attacker instanceof Hero hero){
					float 伤害=attacker.最大生命(0.06f+0.02f*等级());
					damage+=伤害;

					float 生命=伤害*(0.03f+等级()*0.01f);
					if(Dungeon.符文("钢化你心"))生命*=3;
					if(Dungeon.派对(派对设置.钢门联盟))生命*=2;
					if(cursed)
					hero.生命成长-=生命;
					else
					hero.生命成长+=生命;

					

					if(cursed)
						GLog.橙("心之钢为这次攻击-"+伤害+"伤害，并-"+生命+"最大生命。");
					else
						GLog.橙("心之钢为这次攻击+"+伤害+"伤害，并+"+生命+"最大生命。");

					exp+=Math.round(伤害+生命);
					charge=0;
					Sample.INSTANCE.play(Assets.Sounds.心之钢);
				}
			}
			
			if (exp >= (等级()+1)*50 && 等级() < levelCap){
				exp -= (等级()+1)*50;
				升级();
				Catalog.countUse(心之钢.class);
				GLog.绿(Messages.get(this,"levelup"));
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
