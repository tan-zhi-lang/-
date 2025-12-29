

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
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
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
		
		遗产= true;
	}
	
	@Override
	public void reset() {
		super.reset();
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				image = 物品表.PASTY;
				break;
			case 元旦节:
				image = 物品表.SPARKLING_POTION;
				break;
			case 春节:
				image = 物品表.STEAMED_FISH;
				break;
			case 复活节:
				image = 物品表.EASTER_EGG;
				break;
			case 愚人节:
				image = 物品表.CHOC_AMULET;
				break;
			case 彩虹节:
				image = 物品表.RAINBOW_POTION;
				break;
//			case 端午节:
//				image = 物品表.粽子;
//				break;
			case 七夕节:
				image = 物品表.单身狗粮;
				break;
			case 中秋节:
				image = 物品表.月饼;
				break;
			case SHATTEREDPD_BIRTHDAY:
				image = 物品表.SHATTERED_CAKE;
				break;
			case 万圣节:
				image = 物品表.PUMPKIN_PIE;
				break;
			case PD_BIRTHDAY:
				image = 物品表.VANILLA_CAKE;
				break;
			case 圣诞节:
				image = 物品表.CANDY_CANE;
				break;
		}
	}

	@Override
	protected void eatSFX() {
		switch(Holiday.getCurrentHoliday()){
			case 彩虹节:
			case 元旦节:
				Sample.INSTANCE.play( Assets.Sounds.DRINK );
				return;
		}
		super.eatSFX();
	}

	@Override
	protected void satisfy(Hero hero) {
		if (Holiday.getCurrentHoliday() == Holiday.春节){
			//main item only clears 300 hunger on lunar new year...
			energy = Hunger.HUNGRY;
		}

		super.satisfy(hero);
		if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_CALENDAR)){
			GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.GUIDE_CALENDAR);
		}
		
		switch(Holiday.getCurrentHoliday()){
			default:
				break; //do nothing extra
			case 元旦节:
				//shields for 10% of max hp, min of 5
				int toShield = Math.max(5, hero.最大生命 /10);
				Buff.施加(hero, Barrier.class).设置(toShield);
				break;
			case 春节:
				//...but it also awards an extra item that restores 150 hunger
				FishLeftover left = new FishLeftover();
				left.放背包();
				break;
			case 复活节:
				ArtifactRecharge.chargeArtifacts(hero, 2f);
				充能卷轴.charge(hero);
				break;
			case 愚人节:
				Sample.INSTANCE.play(Assets.Sounds.MIMIC);
			case 彩虹节:
				Char target = null;
				
				//charms an adjacent non-boss enemy, prioritizing the one the hero is focusing on
				for (Char ch : Actor.chars()){
					if (!Char.hasProp(ch, Char.Property.BOSS)
						&&!Char.hasProp(ch, Char.Property.MINIBOSS)
						&& ch.alignment == Char.Alignment.ENEMY
						&&Dungeon.level.adjacent(hero.pos,ch.pos)){
						if (target == null ||ch==TargetHealthIndicator.instance.target()){
							target = ch;
						}
					}
				}
				
				if (target != null){
					Buff.施加(target,Charm.class,5f).object = hero.id();
				}
				hero.sprite.emitter().burst(RainbowParticle.BURST,15);
				break;
			
			case 端午节:
				break;
			case 七夕节:
				if(!hero.单身) {
					hero.单身=true;
					hero.sprite.showStatusWithIcon(CharSprite.增强, "10%", FloatingText.STRENGTH);
					GLog.p( Messages.get(力量药剂.class,"msg",hero.力量()));
				}
				break;
			case 中秋节:
				Buff.施加(hero, Swiftthistle.TimeBubble.class).reset();
				break;
			case SHATTEREDPD_BIRTHDAY:
				//gives 10% of level in exp, min of 2
				int expToGive = Math.max(2, hero.升级所需()/10);
				hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(expToGive), FloatingText.EXPERIENCE);
				hero.经验(expToGive, 经验药剂.class);
				break;
			case 万圣节:
				//heals for 5% max hp, min of 3
				int toHeal = Math.max(3, hero.最大生命 /20);
				hero.回血(toHeal);
				break;
			case PD_BIRTHDAY:
				//gives 10% of level in exp, min of 2
				hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(Math.max(2, hero.升级所需()/10)), FloatingText.EXPERIENCE);
				hero.经验(Math.max(2, hero.升级所需()/10), 经验药剂.class);
				break;
			case 圣诞节:
				hero.belongings.charge(0.5f); //2 turns worth
				充能卷轴.charge(hero);
				break;
		}
	}

	@Override
	public String name() {
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				return super.name();
			case 元旦节:
				return Messages.get(this, "sparkling_name");
			case 春节:
				return Messages.get(this, "fish_name");
			case 复活节:
				return Messages.get(this, "egg_name");
			case 愚人节:
				return Messages.get(this, "amulet_name");
			case 彩虹节:
				return Messages.get(this, "rainbow_name");
//			case 端午节:
//				return Messages.get(this, "粽子");
			case 七夕节:
				return Messages.get(this, "单身狗粮");
			case 中秋节:
				return Messages.get(this, "月饼");
			case SHATTEREDPD_BIRTHDAY:
				return Messages.get(this, "shattered_name");
			case 万圣节:
				return Messages.get(this, "pie_name");
			case PD_BIRTHDAY:
				return Messages.get(this, "vanilla_name");
			case 圣诞节:
				return Messages.get(this, "cane_name");
		}
	}

	@Override
	public String desc() {
		switch(Holiday.getCurrentHoliday()){
			case NONE: default:
				return super.desc();
			case 元旦节:
				return Messages.get(this, "sparkling_desc");
			case 春节:
				return Messages.get(this, "fish_desc");
			case 复活节:
				return Messages.get(this, "egg_desc");
			case 愚人节:
				return Messages.get(this, "amulet_desc");
			case 彩虹节:
				return Messages.get(this, "rainbow_desc");
//			case 端午节:
//				return Messages.get(this, "粽子_desc");
			case 七夕节:
				return Messages.get(this, "单身狗粮_desc");
			case 中秋节:
				return Messages.get(this, "月饼_desc");
			case SHATTEREDPD_BIRTHDAY:
				return Messages.get(this, "shattered_desc");
			case 万圣节:
				return Messages.get(this, "pie_desc");
			case PD_BIRTHDAY:
				return Messages.get(this, "vanilla_desc");
			case 圣诞节:
				return Messages.get(this, "cane_desc");
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
