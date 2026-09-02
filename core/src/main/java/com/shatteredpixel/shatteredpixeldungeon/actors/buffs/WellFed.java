

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.仓鼠之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;

public class WellFed extends Buff {

	{
		type = buffType.POSITIVE;
		announced = true;
	}
	
	public int left;
	
	@Override
	public boolean act() {
		if(healingLeft>0){
			真extend(healingThisTick());
			healingLeft-=healingThisTick();
		}
		left --;
		if (left < 0){
			detach();
			if (target instanceof Hero) {
				((Hero) target).resting = false;
			}
			return true;
		} else if (left % 18 == 0 && target.生命 < target.最大生命){
			target.回血(1);
			if (target.生命 == target.最大生命 && target instanceof Hero) {
				((Hero) target).resting = false;
			}
		}

		//salt cube does slow this buff down, but doesn't lessen the bonus health
		spend(TICK / Hunger.饥饿速度());

		if(Dungeon.赛季(赛季设置.规则怪谈))target.受伤时(target.最大生命(上限()/100f));
		return true;
	}
	
	public void reset(){
		//heals one HP every 18 turns for 450 turns
		//25 HP healed in total
		left = (int)Hunger.STARVING;
		left=Math.min(上限(),left);
	}
	public void extend( float duration ) {
		setHeal(duration, 0.5f, 0);
	}
	public void 真extend( float duration ) {
		left += duration;
		left=Math.min(上限(),left);
	}

	public static int 上限() {
		int x=450;
		
		if(Dungeon.符文("大胃王"))x+=900;
		if(Dungeon.hero())
		x+=仓鼠之戒.饱腹(Dungeon.hero);
		return x;
	}
	@Override
	public int icon() {
		return BuffIndicator.WELL_FED;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (Hunger.STARVING - left) / Hunger.STARVING);
	}

	@Override
	public String iconTextDisplay() {
		float visualLeft = left / SaltCube.hungerGainMultiplier();
		return Math.round(visualLeft+1)+"";
	}
	
	@Override
	public String desc() {
		int visualLeft = (int)(left / SaltCube.hungerGainMultiplier());
		return Messages.get(this, "desc", visualLeft + 1);
	}
	
	private static final String LEFT = "left";

	private float healingLeft;

	private float percentHealPerTick;
	private float flatHealPerTick;
	private static final String HEALINGLEFT = "healingleft";
	private static final String PERCENT = "percent";
	private static final String FLAT = "flat";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEFT, left);

		bundle.put(HEALINGLEFT, healingLeft);
		bundle.put(PERCENT, percentHealPerTick);
		bundle.put(FLAT, flatHealPerTick);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		left = bundle.getInt(LEFT);

		healingLeft = bundle.getFloat(HEALINGLEFT);
		percentHealPerTick = bundle.getFloat(PERCENT);
		flatHealPerTick = bundle.getFloat(FLAT);
	}

	private float healingThisTick(){
		float heal = GameMath.之内(1,
								   Math.round(healingLeft * percentHealPerTick) + flatHealPerTick,
								   healingLeft);
		return heal;
	}

	public void setHeal(float amount, float percentPerTick, float flatPerTick){
		//multiple sources of healing do not overlap, but do combine the best of their properties
		healingLeft = Math.max(healingLeft, amount);
		percentHealPerTick = Math.max(percentHealPerTick, percentPerTick);
		flatHealPerTick = Math.max(flatHealPerTick, flatPerTick);
	}
	public void increaseHeal( int amount ){
		healingLeft += amount;
	}
}
