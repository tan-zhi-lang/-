

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.破损纹章.WarriorShield;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

public class Berserk extends ShieldBuff implements ActionIndicator.Action {

	{
		type = buffType.POSITIVE;

		detachesAtZero = false;
		shieldUsePriority = -1; //other shielding buffs are always consumed first
	}

	private enum State{
		NORMAL, BERSERK, RECOVERING
	}
	private State state = State.NORMAL;

	private static final float LEVEL_RECOVER_START = 4f+1f;
	private float levelRecovery;

	private static final int TURN_RECOVERY_START = 100;
	private int turnRecovery;

	public int powerLossBuffer = 0;
	private float power = 0;

	private static final String STATE = "state";
	private static final String LEVEL_RECOVERY = "levelrecovery";
	private static final String TURN_RECOVERY = "turn_recovery";
	private static final String POWER = "power";
	private static final String POWER_BUFFER = "power_buffer";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STATE, state);
		bundle.put(POWER, power);
		bundle.put(POWER_BUFFER, powerLossBuffer);
		bundle.put(LEVEL_RECOVERY, levelRecovery);
		bundle.put(TURN_RECOVERY, turnRecovery);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		state = bundle.getEnum(STATE, State.class);
		power = bundle.getFloat(POWER);
		powerLossBuffer = bundle.getInt(POWER_BUFFER);
		levelRecovery = bundle.getFloat(LEVEL_RECOVERY);
		turnRecovery = bundle.getInt(TURN_RECOVERY);

		if (power >= 1f && state == State.NORMAL){
			ActionIndicator.setAction(this);
		}
	}

	@Override
	public boolean act() {
		if (state == State.BERSERK){
			if (target.shielding() > 0) {
				//lose 2.5% of shielding per turn, but no less than 1
				float dmg = (float)Math.ceil(target.shielding() * 0.025f) * HoldFast.buffDecayFactor(target);
				if (Random.Float() < dmg % 1){
					dmg++;
				}

				ShieldBuff.processDamage(target, (int)dmg, this);

				if (target.shielding() <= 0){
					state = State.RECOVERING;
					power = 0f;
					BuffIndicator.refreshHero();
					if (!target.isAlive()){
						target.死亡时(this);
						if (!target.isAlive()) Dungeon.fail(this);
					}
				}

			} else {
				state = State.RECOVERING;
				power = 0f;
				if (!target.isAlive()){
					target.死亡时(this);
					if (!target.isAlive()) Dungeon.fail(this);
				}

			}
		} else if (state == State.NORMAL) {
			if (powerLossBuffer > 0){
				powerLossBuffer--;
			} else {
				power -= GameMath.gate(0.1f, power, 1f) * 0.05f * Math.pow((target.生命 / (float) target.最大生命), 2);

				if (power < 1f){
					ActionIndicator.clearAction(this);
				} else {
					ActionIndicator.refresh();
				}

				if (power <= 0) {
					detach();
				}
			}
		} else if (state == State.RECOVERING && levelRecovery == 0 && 再生.regenOn()){
			turnRecovery--;
			if (turnRecovery <= 0){
				turnRecovery = 0;
				state = State.NORMAL;
			}
		}
		spend(TICK);
		return true;
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	public float enchantFactor(float chance){
		return chance + ((Math.min(1f, power) * 0.15f) * ((Hero) target).天赋点数(Talent.ENRAGED_CATALYST));
	}

	public float damageFactor(float dmg){
		return dmg * Math.min(1.5f, 1f + (power / 2f));
	}

	public boolean berserking(){
		if (target.生命 == 0
				&& state == State.NORMAL
				&& power >= 1f){
			startBerserking();
			ActionIndicator.clearAction(this);
		}

		return state == State.BERSERK && target.shielding() > 0;
	}

	private void startBerserking(){
		state = State.BERSERK;
		SpellSprite.show(target, SpellSprite.BERSERK);
		Sample.INSTANCE.play( Assets.Sounds.CHALLENGE );
		GameScene.flash(0xFF0000);

		if (target.生命 > 0) {
			turnRecovery = TURN_RECOVERY_START;
			levelRecovery = 0;
		} else {
			levelRecovery = LEVEL_RECOVER_START;
			turnRecovery = 0;
		}

		int shieldAmount = currentShieldBoost();
		设置(shieldAmount);
		target.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(shieldAmount), FloatingText.SHIELDING );

		BuffIndicator.refreshHero();
	}

	public int currentShieldBoost(){
		//base multiplier scales at 1/1.5/2/2.5/3x at 100/37/20/9/0% HP
		float shieldMultiplier = 1f + 2*(float)Math.pow((1f-(target.生命 /(float)target.最大生命)), 3);

		//Endless rage effect on shield and cooldown
		if (power > 1f){
			shieldMultiplier *= power;
			levelRecovery *= 2f - power;
			turnRecovery *= 2f - power;
		}

		int baseShield = 8;
		if (target instanceof Hero && ((Hero) target).belongings.armor() != null){
			baseShield += 2*((Hero) target).belongings.armor().强化等级();
		}
		return Math.round(baseShield * shieldMultiplier);
	}

	//not accounting for talents
	public int maxShieldBoost(){
		int baseShield = 8;
		if (target instanceof Hero && ((Hero) target).belongings.armor() != null){
			baseShield += 2*((Hero) target).belongings.armor().强化等级();
		}
		return baseShield*3;
	}
	public float end(){
		return 1f + ((Hero)target).天赋点数(Talent.ENDLESS_RAGE,0.15f);
	}
	public void damage(int damage){
		if (state != State.NORMAL) return;
		float maxPower = end();
		power = Math.min(maxPower, power + (damage/(float)target.最大生命)/4f );
		BuffIndicator.refreshHero(); //show new power immediately
		powerLossBuffer = 3; //2 turns until rage starts dropping
		if (power >= 1f){
			ActionIndicator.setAction(this);
		}
	}

	public void recover(float percent){
		if (state == State.RECOVERING && levelRecovery > 0){
			levelRecovery -= percent;
			if (levelRecovery <= 0) {
				levelRecovery = 0;
				if (turnRecovery == 0){
					state = State.NORMAL;
				}
			}
		}
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.BERSERK;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text((int) (power * 100) + "%");
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {
		return 0x660000;
	}

	@Override
	public void doAction() {
		WarriorShield shield = target.buff(WarriorShield.class);
		if (shield != null && shield.maxShield() > 0) {
			startBerserking();
			ActionIndicator.clearAction(this);
		} else {
			GLog.w(Messages.get(this, "no_seal"));
		}
	}

	@Override
	public int icon() {
		return BuffIndicator.BERSERK;
	}
	
	@Override
	public void tintIcon(Image icon) {
		switch (state){
			case NORMAL: default:
				if (power < 1f) icon.hardlight(1f, 0.5f, 0f);
				else            icon.hardlight(1f, 0f, 0f);
				break;
			case BERSERK:
				icon.hardlight(1f, 0f, 0f);
				break;
			case RECOVERING:
				icon.hardlight(0, 0, 1f);
				break;
		}
	}
	
	@Override
	public float iconFadePercent() {
		switch (state){
			case NORMAL: default:
				float maxPower = end();
				return (maxPower - power)/maxPower;
			case BERSERK:
				return 1f - 护盾量() / (float)maxShieldBoost();
			case RECOVERING:
				if (levelRecovery > 0) {
					return levelRecovery/LEVEL_RECOVER_START;
				} else {
					return turnRecovery/(float)TURN_RECOVERY_START;
				}
		}
	}

	public String iconTextDisplay(){
		switch (state){
			case NORMAL: default:
				return (int)(power*100) + "%";
			case BERSERK:
				return Integer.toString(护盾量());
			case RECOVERING:
				if (levelRecovery > 0) {
					return Messages.decimalFormat("#.##", levelRecovery);
				} else {
					return Integer.toString(turnRecovery);
				}
		}
	}

	@Override
	public String name() {
		switch (state){
			case NORMAL: default:
				return Messages.get(this, "angered");
			case BERSERK:
				return Messages.get(this, "berserk");
			case RECOVERING:
				return Messages.get(this, "recovering");
		}
	}

	@Override
	public String desc() {
		float dispDamage = ((int)damageFactor(10000) / 100f) - 100f;
		switch (state){
			case NORMAL: default:
				return Messages.get(this, "angered_desc", Math.floor(power * 100f), dispDamage, currentShieldBoost());
			case BERSERK:
				return Messages.get(this, "berserk_desc", 护盾量());
			case RECOVERING:
				if (levelRecovery > 0){
					return Messages.get(this, "recovering_desc") + "\n\n" + Messages.get(this, "recovering_desc_levels", levelRecovery);
				} else {
					return Messages.get(this, "recovering_desc") + "\n\n" + Messages.get(this, "recovering_desc_turns", turnRecovery);
				}
		}
		
	}
}
