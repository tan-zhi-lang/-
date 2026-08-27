

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.极肚之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.血腥生肉;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.地裂镰;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.levels.VaultLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;

public class Hunger extends Buff implements Hero.Doom {

	public static final float HUNGRY	= 300f;
	public static final float STARVING	= 450f;
	
	public float level;

	private static final String LEVEL			= "level";

	private float healingLeft;

	private float percentHealPerTick;
	private float flatHealPerTick;
	private static final String LEFT = "left";
	private static final String PERCENT = "percent";
	private static final String FLAT = "flat";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( LEVEL, level );

		bundle.put(LEFT, healingLeft);
		bundle.put(PERCENT, percentHealPerTick);
		bundle.put(FLAT, flatHealPerTick);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );

		healingLeft = bundle.getFloat(LEFT);
		percentHealPerTick = bundle.getFloat(PERCENT);
		flatHealPerTick = bundle.getFloat(FLAT);
	}
	protected int color;
	protected int rays;
	@Override
	public void fx(boolean on) {
		if(target instanceof Hero hero){
			if(hero.符文("黄金忍者")&&(
					hero.belongings.hasItem(火焰剑.class)&&
					hero.belongings.hasItem(地裂镰.class)&&
					hero.belongings.hasItem(寒冰镖.class)&&
					hero.belongings.hasItem(闪电双截棍.class)
			)){
				rays=5;
				color=0xFFFF00;
			}
			if(hero.英精英雄!=-1){
				rays=5;
				switch(hero.英精英雄){
					case 0:
						color=0xFF8800;
						break;
					case 1:
						color=0x8800FF;
						break;
					case 2:
						color=0x00FF00;
						break;
					case 3:
						color=0x0088FF;
						break;
					case 4:
						color=0xFFFF00;
						break;
					case 5:
						color=0x111111;
						break;
					case 6:
						color=0x3399FF;
						break;
					case 7:
						color=0xFFFFFF;
						break;
					case 8:
						color=0xFF2222; //a little white helps it stick out from background
						break;
				}
			}
		}

		if (on) target.sprite.aura( color, rays );
		else target.sprite.clearAura();
	}
	public float 饥饿伤害(){
		float x=饥饿速度()*0.5f*
				((float)Math.sqrt(target.最大生命(0.0005f))+
				 (float)Math.sqrt(target.已损失生命(0.0005f)));
		if(Dungeon.hero.种族天赋.equals("不死族"))x=0;
		return x;
	}
	public static float 饥饿速度(){
		float x=1;

		if (Dungeon.hero.buff(Shadows.class) != null){
			x /= 2;
		}
		if(Dungeon.符文("短跑壮如牛长跑瘦如猴"))
			x *= Dungeon.hero.移速();
		if(Dungeon.符文("大胃王"))
			x /= 3;
		if(Dungeon.符文("树懒转世"))
			x /= 2;
		if(Dungeon.解压(解压设置.抗饿能手))
			x /= 2;
		if(Dungeon.符文("我是瘦子"))x/=4f;
		x*=SaltCube.hungerGainMultiplier();
		x*=血腥生肉.饥饿();
		if(Dungeon.hero.heroClass(HeroClass.WARRIOR))x*=0.67f;
		return x;
	}
	@Override
	public boolean act() {

		if (target.isAlive() && target instanceof Hero hero) {
			if(healingLeft>0){
				真吃饭(healingThisTick());
				healingLeft-=healingThisTick();
			}

			boolean 不饥饿=false;
			if (Dungeon.level.locked
				|| target.buff(WellFed.class) != null
				||SPDSettings.intro()
				||target.buff(ScrollOfChallenge.ChallengeArena.class)!=null
				||Dungeon.level instanceof VaultLevel){
				不饥饿=true;
			}
			if(Dungeon.赛季(赛季设置.地牢塔防)){
				不饥饿=true;
			}
			if(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云)){
				不饥饿=true;
			}
			if(不饥饿){
				spend(TICK);
				return true;
			}

			if (isStarving()) {//饥饿时


				if (饥饿伤害() > 0){
					hero.受伤时(饥饿伤害(), this);
				}
				
			} else {

				float hungerDelay = 饥饿速度();
				float newLevel = level + hungerDelay;
				if (newLevel >= STARVING) {//450时

					GLog.红(Messages.get(this,"onstarving"));
					hero.受伤时( (hungerDelay/20f+(float)Math.sqrt(hero.已损失生命())/89f)*饥饿伤害(), this);

					hero.interrupt();
					newLevel = STARVING;

				} else if (newLevel >= HUNGRY && level < HUNGRY) {

					hero.interrupt();
					GLog.橙(Messages.get(this,"onhungry"));

					if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_FOOD)){
						GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_FOOD);
					}

				}
				level = Math.min(STARVING+1,newLevel);

			}
			spend( TICK );

		} else {

			diactivate();

		}

		return true;
	}

	public static float 吃饭效果() {
		float energy=1;

		if(Dungeon.hero.heroClass(HeroClass.来世))energy*=4/3f;

		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			energy /= 2f;
		}
		if(Dungeon.符文("细嚼慢咽"))energy*=2;

		energy*=极肚之戒.饥饿速度(Dungeon.hero);
		return energy;
	}
	public void 吃饭(float energy ) {
		setHeal(energy, 0.5f, 0);
	}
	public void 真吃饭(float energy) {
		if(Dungeon.hero()){
			energy*=吃饭效果();
		}
		affectHunger( energy, false );
	}
	public void affectHunger(float energy, boolean overrideLimits ) {
		if(target instanceof Hero hero){
			float x=level-energy;
			if(x<0){
				if(hero.符文("吃胀到了")){
						hero.回血(-x);
				}
				Buff.施加(hero, WellFed.class).extend(-x);
			}

			Talent.吃饭时(hero,energy/150f);
		if (energy < 0 && target.buff(WellFed.class) != null){
			target.buff(WellFed.class).left += energy;
			BuffIndicator.refreshHero();
			return;
		}

		float oldLevel = level;

		level = Math.max(0,level-energy);
//		if (level < 0 && !overrideLimits) {
//			level = 0;
//		}

		if (oldLevel < HUNGRY && level >= HUNGRY){
			GLog.橙(Messages.get(this,"onhungry"));
		} else if (oldLevel < STARVING && level >= STARVING){
			GLog.红(Messages.get(this,"onstarving"));
			if(Dungeon.符文("我是瘦子"))
				target.受伤时( 饥饿伤害(), this );
			else
			target.受伤时( 饥饿伤害(), this );
		}

		BuffIndicator.refreshHero();
		}
	}
	public boolean isStarving() {
		return level >= STARVING;
	}

	public float hunger() {
		return level;
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


	@Override
	public int icon() {
		if (level < HUNGRY) {
			return BuffIndicator.NONE;
		} else if (level < STARVING) {
			return BuffIndicator.HUNGER;
		} else {
			return BuffIndicator.STARVATION;
		}
	}

	@Override
	public String name() {
		if (level < STARVING) {
			return Messages.get(this, "hungry");
		} else {
			return Messages.get(this, "starving");
		}
	}

	@Override
	public String desc() {
		String result;
		if (level < STARVING) {
			result = Messages.get(this, "desc_intro_hungry");
		} else {
			result = Messages.get(this, "desc_intro_starving");
		}

		result += Messages.get(this, "desc");

		return result;
	}

	@Override
	public void onDeath() {

		Badges.validateDeathFromHunger();

		Dungeon.fail( this );
		GLog.红(Messages.get(this,"ondeath"));
	}
	public boolean 饥饿(){
		return level > HUNGRY;
	}
	public boolean 空腹(){
		return level > STARVING;
	}
	public boolean 饱腹(){
		return level < STARVING-HUNGRY;
	}
}
