

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.血腥生肉;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.levels.VaultLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.utils.Bundle;

public class Hunger extends Buff implements Hero.Doom {

	public static final float HUNGRY	= 300f;
	public static final float STARVING	= 450f;
	
	public float level;
	public float partial=0;

	private static final String LEVEL			= "level";
	private static final String PARTIAL 	= "partial";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);
		bundle.put( LEVEL, level );
		bundle.put( PARTIAL, partial );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		level = bundle.getFloat( LEVEL );
		partial = bundle.getFloat(PARTIAL);
	}
	protected int color;
	protected int rays;
	@Override
	public void fx(boolean on) {
		if(target instanceof Hero hero&&hero.英精英雄!=-1){
			rays = 5;
			switch(hero.英精英雄){
				case 0:
					color = 0xFF8800;
					break;
				case 1:
					color = 0x8800FF;
					break;
				case 2:
					color = 0x00FF00;
					break;
				case 3:
					color = 0x0088FF;
					break;
				case 4:
					color = 0xFFFF00;
					break;
				case 5:
					color = 0x111111;
					break;
				case 6:
					color = 0x3399FF;
					break;
				case 7:
					color = 0xFFFFFF;
					break;
				case 8:
					color = 0xFF2222; //a little white helps it stick out from background
					break;
			}
		}
		if (on) target.sprite.aura( color, rays );
		else target.sprite.clearAura();
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
		boolean 不饥饿=false;
		if (Dungeon.level.locked
				|| target.buff(WellFed.class) != null
				|| SPDSettings.intro()
				|| target.buff(ScrollOfChallenge.ChallengeArena.class) != null
			|| Dungeon.level instanceof VaultLevel){
			不饥饿=true;
		}
		if(Dungeon.赛季(赛季设置.地牢塔防)){
			不饥饿=true;
		}
		if(target instanceof Hero hero&&(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云))){
			不饥饿=true;
		}
		if(不饥饿){
			spend(TICK);
			return true;
		}

		if (target.isAlive() && target instanceof Hero hero) {
			
			if (isStarving()) {//饥饿时

				partial=饥饿速度()/20f+(float)Math.sqrt(hero.已损失生命())/89f;

				if (partial > 0){
//					partial=0;
					hero.受伤时(partial, this);
				}
				
			} else {

				float hungerDelay = 饥饿速度();
				float newLevel = level + hungerDelay;
				if (newLevel >= STARVING) {//450时

					GLog.红(Messages.get(this,"onstarving"));
					hero.受伤时( hungerDelay/20f+(float)Math.sqrt(hero.已损失生命())/89f, this);

					hero.interrupt();
					newLevel = STARVING;

				} else if (newLevel >= HUNGRY && level < HUNGRY) {

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

	public static float 吃饭效率() {
		float energy=1;

		if(Dungeon.hero.heroClass(HeroClass.来世))energy*=4/3f;

		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			energy /= 2f;
		}
		if(Dungeon.符文("细嚼慢咽"))energy*=2;

		return energy;
	}
	public void 吃饭(float energy ) {
		if(Dungeon.hero()){
			energy*=吃饭效率();
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
				target.受伤时( 饥饿速度()/20f+(float)Math.sqrt(hero.已损失生命())/89f*2, this );
			else
			target.受伤时( 饥饿速度()/20f+(float)Math.sqrt(hero.已损失生命())/89f, this );
			partial=0;
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

//	@Override
//	public int icon() {
//		if (level < HUNGRY) {
//			return BuffIndicator.NONE;
//		} else if (level < STARVING) {
//			return BuffIndicator.HUNGER;
//		} else {
//			return BuffIndicator.STARVATION;
//		}
//	}

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
