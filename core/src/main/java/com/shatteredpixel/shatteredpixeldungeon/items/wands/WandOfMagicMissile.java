

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class WandOfMagicMissile extends DamageWand {

	{
		image = 物品表.WAND_MAGIC_MISSILE;
	}

	public int min(int lvl){
		return 2+lvl;
	}

	public int max(int lvl){
		return 8+2*lvl;
	}
	
	@Override
	public void onZap(Ballistica bolt) {
				
		Char ch = Actor.findChar( bolt.collisionPos );
		if (ch != null) {

			wandProc(ch, chargesPerCast());
			ch.受伤时(damageRoll(), this);
			Sample.INSTANCE.play( Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f) );

			ch.sprite.burst(0xFFFFFFFF, buffedLvl() / 2 + 2);

			//apply the magic charge buff if we have another wand in inventory of a lower level, or already have the buff
			for (Wand.Charger wandCharger : curUser.buffs(Wand.Charger.class)){
				if (wandCharger.wand().buffedLvl() < buffedLvl() || curUser.buff(MagicCharge.class) != null){
					Buff.延长(curUser, MagicCharge.class, MagicCharge.DURATION).setup(this);
					break;
				}
			}

		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}

	@Override
	public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
		SpellSprite.show(attacker, SpellSprite.CHARGE);
		for (Wand.Charger c : attacker.buffs(Wand.Charger.class)){
			if (c.wand() != this){
				c.gainCharge(0.5f * procChanceMultiplier(attacker));
			}
		}

	}

	public int initialCharges() {
		return 3;
	}

	public static class MagicCharge extends FlavourBuff {

		{
			type = buffType.POSITIVE;
			announced = true;
		}

		public static float DURATION = 4f;

		private int level = 0;
		private Wand wandJustApplied; //we don't bundle this as it's only used right as the buff is applied

		public void setup(Wand wand){
			if (level < wand.buffedLvl()){
				this.level = wand.buffedLvl();
				this.wandJustApplied = wand;
			}
		}

		@Override
		public void detach() {
			super.detach();
			updateQuickslot();
		}

		public int level(){
			return this.level;
		}

		//this is used briefly so that a wand of magic missile can't clear the buff it just applied
		public Wand wandJustApplied(){
			Wand result = this.wandJustApplied;
			this.wandJustApplied = null;
			return result;
		}

		@Override
		public int icon() {
			return BuffIndicator.UPGRADE;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0.2f, 0.6f, 1f);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", level(), dispTurns());
		}

		private static final String LEVEL = "level";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(LEVEL, level);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			level = bundle.getInt(LEVEL);
		}
	}

}
