

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.RainbowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.watabou.noosa.audio.Sample;

public class Pasty extends Food {

	{
		reset();

		energy = Hunger.STARVING;

		bones = true;
	}
	
	@Override
	public void reset() {
		super.reset();
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				image = 物品表.PASTY;
				break;
			case LUNAR_NEW_YEAR:
				image = 物品表.STEAMED_FISH;
				break;
			case APRIL_FOOLS:
				image = 物品表.CHOC_AMULET;
				break;
			case EASTER:
				image = 物品表.EASTER_EGG;
				break;
			case PRIDE:
				image = 物品表.RAINBOW_POTION;
				break;
			case SHATTEREDPD_BIRTHDAY:
				image = 物品表.SHATTERED_CAKE;
				break;
			case HALLOWEEN:
				image = 物品表.PUMPKIN_PIE;
				break;
			case PD_BIRTHDAY:
				image = 物品表.VANILLA_CAKE;
				break;
			case WINTER_HOLIDAYS:
				image = 物品表.CANDY_CANE;
				break;
			case NEW_YEARS:
				image = 物品表.SPARKLING_POTION;
				break;
			case 奇袭节:
				image = 物品表.单身狗粮;
				break;
		}
	}

	@Override
	protected void eatSFX() {
		switch(Holiday.getCurrentHoliday()){
			case PRIDE:
			case NEW_YEARS:
				Sample.INSTANCE.play( Assets.Sounds.DRINK );
				return;
		}
		super.eatSFX();
	}

	@Override
	protected void satisfy(Hero hero) {
		if (Holiday.getCurrentHoliday() == Holiday.LUNAR_NEW_YEAR){
			//main item only clears 300 hunger on lunar new year...
			energy = Hunger.HUNGRY;
		}

		super.satisfy(hero);
		
		switch(Holiday.getCurrentHoliday()){
			default:
				break; //do nothing extra
			case LUNAR_NEW_YEAR:
				//...but it also awards an extra item that restores 150 hunger
				FishLeftover left = new FishLeftover();
				left.放背包();
				break;
			case APRIL_FOOLS:
				Sample.INSTANCE.play(Assets.Sounds.MIMIC);
			case EASTER:
				ArtifactRecharge.chargeArtifacts(hero, 2f);
				ScrollOfRecharging.charge( hero );
				break;
			case PRIDE:
				Char target = null;

				//charms an adjacent non-boss enemy, prioritizing the one the hero is focusing on
				for (Char ch : Actor.chars()){
					if (!Char.hasProp(ch, Char.Property.BOSS)
							&& !Char.hasProp(ch, Char.Property.MINIBOSS)
							&& ch.alignment == Char.Alignment.ENEMY
							&& Dungeon.level.adjacent(hero.pos, ch.pos)){
						if (target == null || ch == TargetHealthIndicator.instance.target()){
							target = ch;
						}
					}
				}

				if (target != null){
					Buff.施加(target, Charm.class, 5f).object = hero.id();
				}
				hero.sprite.emitter().burst(RainbowParticle.BURST, 15);
				break;
			case SHATTEREDPD_BIRTHDAY:
			case PD_BIRTHDAY:
				//gives 10% of level in exp, min of 2
				int expToGive = Math.max(2, hero.升级所需()/10);
				hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(expToGive), FloatingText.EXPERIENCE);
				hero.经验(expToGive, 经验药剂.class);
				break;
			case HALLOWEEN:
				//heals for 5% max hp, min of 3
				int toHeal = Math.max(3, hero.最大生命 /20);
				hero.生命 = Math.min(hero.生命 + toHeal, hero.最大生命);
				hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(toHeal), FloatingText.HEALING );
				break;
			case WINTER_HOLIDAYS:
				hero.belongings.charge(0.5f); //2 turns worth
				ScrollOfRecharging.charge( hero );
				break;
			case NEW_YEARS:
				//shields for 10% of max hp, min of 5
				int toShield = Math.max(5, hero.最大生命 /10);
				Buff.施加(hero, Barrier.class).设置(toShield);
				hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(toShield), FloatingText.SHIELDING );
				break;
			case 奇袭节:
				if(hero.神力==0) hero.神力++;
				hero.sprite.showStatusWithIcon(CharSprite.增强, "1", FloatingText.STRENGTH);
				GLog.p( Messages.get(PotionOfStrength.class, "msg", hero.力量()) );
				break;
		}
	}

	@Override
	public String name() {
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				return super.name();
			case LUNAR_NEW_YEAR:
				return Messages.get(this, "fish_name");
			case APRIL_FOOLS:
				return Messages.get(this, "amulet_name");
			case EASTER:
				return Messages.get(this, "egg_name");
			case PRIDE:
				return Messages.get(this, "rainbow_name");
			case SHATTEREDPD_BIRTHDAY:
				return Messages.get(this, "shattered_name");
			case HALLOWEEN:
				return Messages.get(this, "pie_name");
			case PD_BIRTHDAY:
				return Messages.get(this, "vanilla_name");
			case WINTER_HOLIDAYS:
				return Messages.get(this, "cane_name");
			case NEW_YEARS:
				return Messages.get(this, "sparkling_name");
			case 奇袭节:
				return Messages.get(this, "单身狗粮");
		}
	}

	@Override
	public String desc() {
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				return super.desc();
			case LUNAR_NEW_YEAR:
				return Messages.get(this, "fish_desc");
			case APRIL_FOOLS:
				return Messages.get(this, "amulet_desc");
			case EASTER:
				return Messages.get(this, "egg_desc");
			case PRIDE:
				return Messages.get(this, "rainbow_desc");
			case SHATTEREDPD_BIRTHDAY:
				return Messages.get(this, "shattered_desc");
			case HALLOWEEN:
				return Messages.get(this, "pie_desc");
			case PD_BIRTHDAY:
				return Messages.get(this, "vanilla_desc");
			case WINTER_HOLIDAYS:
				return Messages.get(this, "cane_desc");
			case NEW_YEARS:
				return Messages.get(this, "sparkling_desc");
			case 奇袭节:
				return Messages.get(this, "单身狗粮_desc");
		}
	}
	
	@Override
	public int 金币() {
		return 20 * quantity;
	}

	public static class FishLeftover extends Food {

		{
			image = 物品表.FISH_LEFTOVER;
			energy = Hunger.HUNGRY/2;
		}

		@Override
		public int 金币() {
			return 10 * quantity;
		}
	}
}
