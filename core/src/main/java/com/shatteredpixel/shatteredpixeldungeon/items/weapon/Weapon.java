

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.GreaterHaste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RevealedArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Smite;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ParchmentScrap;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Annoying;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Dazzling;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Displacing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Explosive;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Friendly;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Polarized;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Sacrificial;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses.Wayward;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Chilling;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Corrupting;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Elastic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Lucky;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Projecting;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Unstable;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Vampiric;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.武技;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;

abstract public class Weapon extends KindOfWeapon {
	
	
	//region MeleeWeapon
	
	boolean 技能=true;
	public static String AC_ABILITY = "ABILITY";
	
	@Override
	public String defaultAction() {
		if (Dungeon.hero() &&Dungeon.hero.heroClass(HeroClass.DUELIST)&&技能){
			return AC_ABILITY;
		}else if(defaultAction!=null){
			return defaultAction;
		}else{
			return AC_THROW;
		}
	}
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		
		if (hero.heroClass(HeroClass.DUELIST)&&技能){
			actions.add(AC_ABILITY);
		}
		if(Dungeon.炼狱(炼狱设置.诅咒装备)&&(等级()>5||tier>3)) {
			actions.remove(AC_EQUIP);
		}
		return actions;
	}
	
	@Override
	public String actionName(String action, Hero hero) {
		if (action.equals(AC_ABILITY)){
			return Messages.upperCase(Messages.get(this,"ability_name"));
		} else {
			return super.actionName(action, hero);
		}
	}
	
	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		
		if (action.equals(AC_ABILITY)&&技能){
			usesTargeting = false;
			if (!isEquipped(hero)) {
				if (hero.天赋(Talent.SWIFT_EQUIP)){
					if (hero.buff(Talent.SwiftEquipCooldown.class) == null
						|| hero.buff(Talent.SwiftEquipCooldown.class).hasSecondUse()){
						execute(hero, AC_EQUIP);
					} else if (hero.heroClass == HeroClass.DUELIST) {
						GLog.w(Messages.get(this,"ability_need_equip"));
					}
				} else if (hero.heroClass == HeroClass.DUELIST) {
					GLog.w(Messages.get(this, "ability_need_equip"));
				}
			} else if (hero.heroClass != HeroClass.DUELIST){
				//do nothing
			} else if (力量() > hero.力量()){
				GLog.w(Messages.get(this, "ability_low_str"));
			} else if (charger.charges + charger.partialCharge < abilityChargeUse(hero, null)) {
				GLog.w(Messages.get(this, "ability_no_charge"));
			} else {
				
				if (targetingPrompt() == null){
					使用武技(hero,hero.pos);
					updateQuickslot();
				} else {
					usesTargeting = useTargeting();
					GameScene.selectCell(new CellSelector.Listener() {
						@Override
						public void onSelect(Integer cell) {
							if (cell != null) {
								使用武技(hero,cell);
								updateQuickslot();
							}
						}
						
						@Override
						public String prompt() {
							return targetingPrompt();
						}
					});
				}
			}
		}
	}
	
	//leave null for no targeting
	public String targetingPrompt(){
		return null;
	}
	
	public boolean useTargeting(){
		return targetingPrompt() != null;
	}
	
	@Override
	public int targetingPos(Hero user, int dst) {
		return dst; //weapon abilities do not use projectile logic, no autoaim
	}
	武技 武技;
	protected void 使用武技(Hero hero,Integer target){
		//do nothing by default
		武技.wep=this;
		武技.武技(hero,target);
	}
	
	public void beforeAbilityUsed(Hero hero,Char target){
		hero.belongings.abilityWeapon = this;
		
		charger.partialCharge -= abilityChargeUse(hero, target);
		while (charger.partialCharge < 0 && charger.charges > 0) {
			charger.charges--;
			charger.partialCharge++;
		}
		
		if (hero.heroClass == HeroClass.DUELIST
			&& hero.天赋(Talent.AGGRESSIVE_BARRIER)
			&& (hero.生命 / (float)hero.最大生命) <= 0.5f){
			int shieldAmt = hero.天赋生命力(Talent.AGGRESSIVE_BARRIER,0.8f);
			Buff.施加(hero, Barrier.class).设置(shieldAmt);
			hero.sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(shieldAmt),FloatingText.SHIELDING);
		}
		
		updateQuickslot();
	}
	
	public void afterAbilityUsed(Hero hero){
		hero.belongings.abilityWeapon = null;
		if (false){//使用武技命中
			Buff.延长(hero, Talent.PreciseAssaultTracker.class, hero.cooldown()+1f);
		}
		if (hero.天赋(Talent.VARIED_CHARGE)){
			Talent.VariedChargeTracker tracker = hero.buff(Talent.VariedChargeTracker.class);
			if (tracker == null || tracker.weapon == getClass() || tracker.weapon == null){
				Buff.施加(hero, Talent.VariedChargeTracker.class).weapon = getClass();
			} else {
				tracker.detach();
				charger.gainCharge(hero.天赋点数(Talent.VARIED_CHARGE,0.15f));
				ScrollOfRecharging.charge(hero);
			}
		}
		if (hero.天赋(Talent.COMBINED_LETHALITY)) {
			Talent.CombinedLethalityAbilityTracker tracker = hero.buff(Talent.CombinedLethalityAbilityTracker.class);
			if (tracker == null || tracker.weapon == this || tracker.weapon == null){
				Buff.施加(hero, Talent.CombinedLethalityAbilityTracker.class, hero.cooldown()).weapon = this;
			} else {
				//we triggered the talent, so remove the tracker
				tracker.detach();
			}
		}
		if (hero.天赋(Talent.COMBINED_ENERGY)){
			Talent.CombinedEnergyAbilityTracker tracker = hero.buff(Talent.CombinedEnergyAbilityTracker.class);
			if (tracker == null || !tracker.monkAbilused){
				Buff.延长(hero, Talent.CombinedEnergyAbilityTracker.class, 5f).wepAbilUsed = true;
			} else {
				tracker.wepAbilUsed = true;
				Buff.施加(hero, MonkEnergy.class).processCombinedEnergy(tracker);
			}
		}
		if (hero.buff(Talent.CounterAbilityTacker.class) != null){
			charger.gainCharge(hero.天赋点数(Talent.COUNTER_ABILITY)*0.375f);
			hero.buff(Talent.CounterAbilityTacker.class).detach();
		}
	}
	
	public static void onAbilityKill( Hero hero, Char killed ){
		if (killed.alignment == Char.Alignment.ENEMY && hero.天赋(Talent.LETHAL_HASTE)){
			//effectively 3/5 turns of greater haste
			Buff.施加(hero, GreaterHaste.class).set(hero.天赋点数(Talent.LETHAL_HASTE,1.3f));
		}
	}
	
	protected int baseChargeUse(Hero hero, Char target){
		return 1; //abilities use 1 charge by default
	}
	
	public final float abilityChargeUse(Hero hero, Char target){
		return baseChargeUse(hero, target);
	}
	
	public int tier;
	
	@Override
	public int 最小攻击(int lvl) {
		return augment.damageFactor(Math.round(最小+(tier+lvl)*伤害));
	}
	
	@Override
	public int 最大攻击(int lvl) {
		return augment.damageFactor(Math.round(最大+(5*(tier+1) +lvl*(tier+1))*伤害));
	}
	
	public int 力量(int lvl){
		int req = 力量(tier, lvl);
		if (masteryPotionBonus){
			req -= 2;
		}
		if (神力){
			req -= 2;
		}
		req*=1-Dungeon.hero.天赋点数(Talent.强力适应,0.15f);
		return req;
	}
	
	private static boolean evaluatingTwinUpgrades = false;
	@Override
	public int 强化等级() {
		if (!evaluatingTwinUpgrades && Dungeon.hero() && isEquipped(Dungeon.hero) && Dungeon.hero.天赋(Talent.TWIN_UPGRADES)){
			KindOfWeapon other = null;
			if (Dungeon.hero.belongings.weapon() != this) other = Dungeon.hero.belongings.weapon();
			if (Dungeon.hero.belongings.secondWep() != this) other = Dungeon.hero.belongings.secondWep();
			
			if (other instanceof Weapon) {
				evaluatingTwinUpgrades = true;
				int otherLevel = other.强化等级();
				evaluatingTwinUpgrades = false;
				
				//weaker weapon needs to be 2/1/0 tiers lower, based on talent level
				if ((tier + (4 - Dungeon.hero.天赋点数(Talent.TWIN_UPGRADES))) <= ((Weapon) other).tier
					&& otherLevel > super.强化等级()) {
					return otherLevel;
				}
				
			}
		}
		return super.强化等级();
	}
	
	
	@Override
	public String info() {
		
		String info = super.info();
		
		if (levelKnown) {
			info += "\n\n" + Messages.get(Weapon.class, "stats_known", 力量(), tier, 最小攻击(), 最大攻击(), 最小投掷攻击(), 最大投掷攻击());
			if (Dungeon.hero()) {
				if (力量() > Dungeon.hero.力量()) {
					info += " " + Messages.get(Weapon.class, "too_heavy");
				} else if (Dungeon.hero.力量() > 力量()) {
					info += " " + Messages.get(Weapon.class, "excess_str",Dungeon.hero.力量() - 力量());
				}
			}
		} else {
			info += "\n\n" + Messages.get(Weapon.class, "stats_known", 力量(0), tier, 最小攻击(0), 最大攻击(0), 最小投掷攻击(0), 最大投掷攻击(0));
			if (Dungeon.hero() && 力量(0) > Dungeon.hero.力量()) {
				info += " " + Messages.get(Weapon.class, "probably_too_heavy");
			} else if (Dungeon.hero.力量() > 力量()) {
				info += " " + Messages.get(Weapon.class, "excess_str",Dungeon.hero.力量() - 力量());
			}
		}
		
		String statsInfo = statsInfo();
		if (!statsInfo.equals("")) info += "\n\n" + statsInfo;
		
		switch (augment) {
			case DELAY:
				info += " " + Messages.get(Weapon.class, "delay");
				break;
			case ACCURACY:
				info += " " + Messages.get(Weapon.class, "accuracy");
				break;
			case DAMAGE:
				info += " " + Messages.get(Weapon.class, "damage");
				break;
			case NONE:
		}
		
		if (isEquipped(Dungeon.hero) && !hasCurseEnchant() &&Dungeon.hero.buff(HolyWeapon.HolyWepBuff.class)!=null
			&& (Dungeon.hero.subClass!=HeroSubClass.PALADIN||enchantment==null)){
			info += "\n\n" + Messages.capitalize(Messages.get(Weapon.class, "enchanted", Messages.get(HolyWeapon.class, "ench_name", Messages.get(Enchantment.class, "enchant"))));
			info += " " + Messages.get(HolyWeapon.class, "ench_desc");
		} else if (enchantment != null && (cursedKnown || !enchantment.curse())){
			info += "\n\n" + Messages.capitalize(Messages.get(Weapon.class, "enchanted", enchantment.name()));
			if (enchantHardened) info += " " + Messages.get(Weapon.class, "enchant_hardened");
			info += " " + enchantment.desc();
		} else if (enchantHardened){
			info += "\n\n" + Messages.get(Weapon.class, "hardened_no_enchant");
		}
		
		if (cursed && isEquipped( Dungeon.hero )) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Weapon.class, "cursed");
		} else if (!已鉴定() && cursedKnown){
			if (enchantment != null && enchantment.curse()) {
				info += "\n\n" + Messages.get(Weapon.class, "weak_cursed");
			} else {
				info += "\n\n" + Messages.get(Weapon.class, "not_cursed");
			}
		}
		
		//the mage's staff has no ability as it can only be gained by the mage
		if (Dungeon.hero() && Dungeon.hero.heroClass(HeroClass.DUELIST)&&技能){
//			info += "\n\n" + abilityInfo();
//			@Override
//			public String abilityInfo() {
//				if (levelKnown){
//					return Messages.get(this, "ability_desc", 2+ 强化等级());
//				} else {
//					return Messages.get(this, "typical_ability_desc", 2);
//				}
//			}
//
//			@Override
//			public String upgradeAbilityStat(int level) {
//				return Integer.toString(2+level);
//			}
		}
		
		return info;
	}
	@Override
	public int defenseFactor( Char owner ) {
		return 最大防御();
	}
	public int 最大防御(){
		return 最大防御(强化等级());
	}
	
	public int 最大防御(int lvl){
		return 0;
	}
	public int 最小防御(){
		return 最大防御(强化等级());
	}
	
	public int 最小防御(int lvl){
		return 0;
	}
	public String statsInfo(){
		if (已鉴定()){
			return Messages.get(this, "stats_desc",(最大防御(0)==0?"":"武器格挡_"+最小防御()+"~"+最大防御()+"_，"),
								命中,间隔,伤害,范围,
								(流血==0?"":"，流血_"+Math.round(流血*100)+"%_"),
								(吸血==0?"":"，吸血_"+Math.round(吸血*100)+"%_"),
								(伏击==0?"":"，伏击_"+Math.round(伏击*100)+"%_")
							   );
		} else {
			return Messages.get(this, "stats_desc",(最大防御(0)==0?"":"武器格挡_"+最小防御(0)+"~"+最大防御(0)+"_，"),
								命中,间隔,伤害,范围,
								(流血==0?"":"，伏击_"+Math.round(流血*100)+"%_"),
								(吸血==0?"":"，吸血_"+Math.round(吸血*100)+"%_"),
								(伏击==0?"":"，伏击_"+Math.round(伏击*100)+"%_")
							   );
		}
	}
	
	public String abilityInfo() {
		return Messages.get(this, "ability_desc");
	}
	
	public String upgradeAbilityStat(int level){
		return null;
	}
	
	@Override
	public int 金币() {
		int price = 20 * tier;
		if (hasGoodEnchant()) {
			price *= 1.5;
		}
		if (cursedKnown && (cursed || hasCurseEnchant())) {
			price /= 2;
		}
		if (levelKnown && 等级() > 0) {
			price *= (等级() + 1);
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}
	
	
	@Override
	public int 能量() {
		return Math.round(金币()*0.025f+1);
	}
	@Override
	public boolean 放背包(Bag container) {
		if (super.放背包( container )) {
			if (container.owner != null) {
				activate( container.owner);
			}
			if (Dungeon.hero() && Dungeon.hero.isAlive() && 已鉴定() && enchantment != null){
				Catalog.setSeen(enchantment.getClass());
				Statistics.itemTypesDiscovered.add(enchantment.getClass());
			}
			return true;
		} else {
			return false;
		}
	}
	
	public Weapon.Charger charger;
	public static class Charger extends Buff implements ActionIndicator.Action {
		{
			//so that duelist keeps weapon charge on ankh revive
			revivePersists = true;
		}
		public int charges = 3;
		public float partialCharge;
		
		@Override
		public boolean act() {
			if (charges < chargeCap()){
				if (再生.regenOn()){
					//60 to 45 turns per charge
					float chargeToGain = 1/(60f-1.5f*(chargeCap()-charges));
					
					//40 to 30 turns per charge for champion
					if (Dungeon.hero.subClass == HeroSubClass.CHAMPION){
						chargeToGain *= 1.5f;
					}
					chargeToGain*=能量之戒.weaponChargeMultiplier(target);
					//50% slower charge gain with brawler's stance enabled, even if buff is inactive
					if (Dungeon.hero.buff(武力之戒.BrawlersStance.class)!=null){
						chargeToGain *= 0.50f;
					}
					if(((Hero)target).天赋(Talent.WEAPON_RECHARGING)) {
						chargeToGain *= 1+((Hero)target).天赋点数(Talent.WEAPON_RECHARGING,0.2f);
					}
					partialCharge += chargeToGain;
				}
				
				int points = 0;
				if (points > 0 &&target.buff(Recharging.class)!=null||target.buff(ArtifactRecharge.class)!=null){
					//1 every 15 turns at +1, 10 turns at +2
					partialCharge += 1/(20f - 5f*points);
				}
				
				if (partialCharge >= 1){
					charges++;
					partialCharge--;
					updateQuickslot();
				}
			} else {
				partialCharge = 0;
			}
			
			if (ActionIndicator.action != this && Dungeon.hero.subClass == HeroSubClass.CHAMPION) {
				ActionIndicator.setAction(this);
			}
			
			spend(TICK);
			return true;
		}
		
		@Override
		public void fx(boolean on) {
			if (on && Dungeon.hero.subClass == HeroSubClass.CHAMPION) {
				ActionIndicator.setAction(this);
			}
		}
		
		@Override
		public void detach() {
			super.detach();
			ActionIndicator.clearAction(this);
		}
		
		public int chargeCap(){
			return Math.min(10, 3+ Dungeon.hero.等级(0.34f));
		}
		
		public void gainCharge( float charge ){
			if (charges < chargeCap()) {
				partialCharge += charge;
				while (partialCharge >= 1f) {
					charges++;
					partialCharge--;
				}
				if (charges >= chargeCap()){
					partialCharge = 0;
					charges = chargeCap();
				}
				updateQuickslot();
			}
		}
		
		public static final String CHARGES          = "charges";
		private static final String PARTIALCHARGE   = "partialCharge";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(CHARGES, charges);
			bundle.put(PARTIALCHARGE, partialCharge);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			charges = bundle.getInt(CHARGES);
			partialCharge = bundle.getFloat(PARTIALCHARGE);
		}
		
		@Override
		public String actionName() {
			return Messages.get(Weapon.class, "swap");
		}
		
		@Override
		public int actionIcon() {
			return HeroIcon.WEAPON_SWAP;
		}
		
		@Override
		public Visual primaryVisual() {
			Image ico;
			if (Dungeon.hero.belongings.weapon == null){
				ico = new HeroIcon(this);
			} else {
				ico = new ItemSprite(Dungeon.hero.belongings.weapon);
			}
			ico.width += 4; //shift slightly to the left to separate from smaller icon
			return ico;
		}
		
		@Override
		public Visual secondaryVisual() {
			Image ico;
			if (Dungeon.hero.belongings.secondWep == null){
				ico = new HeroIcon(this);
			} else {
				ico = new ItemSprite(Dungeon.hero.belongings.secondWep);
			}
			ico.scale.set(PixelScene.align(0.51f));
			ico.brightness(0.6f);
			return ico;
		}
		
		@Override
		public int indicatorColor() {
			return 0x5500BB;
		}
		
		@Override
		public void doAction() {
			if (Dungeon.hero.subClass != HeroSubClass.CHAMPION){
				return;
			}
			
			if (Dungeon.hero.belongings.secondWep == null && Dungeon.hero.belongings.backpack.items.size() >= Dungeon.hero.belongings.backpack.capacity()){
				GLog.w(Messages.get(Weapon.class, "swap_full"));
				return;
			}
			
			Weapon temp = Dungeon.hero.belongings.weapon;
			Dungeon.hero.belongings.weapon = Dungeon.hero.belongings.secondWep;
			Dungeon.hero.belongings.secondWep = temp;
			
			Dungeon.hero.sprite.operate();
			Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
			
			ActionIndicator.setAction(this);
			Item.updateQuickslot();
			AttackIndicator.updateState();
		}
	}
	
	//endregion
	
	//region MissileWeapon
	{
		usesTargeting = true;
	}
	public boolean spawnedForEffect = false;
	
	@Override
	public int 最小投掷攻击() {
		if (Dungeon.hero()){
			return Math.max(0, 最小投掷攻击(强化等级()+RingOfSharpshooting.levelDamageBonus(Dungeon.hero)));
		} else {
			return Math.max(0 , 最小投掷攻击( 强化等级() ));
		}
	}
	
	@Override
	public int 最小投掷攻击(int lvl) {
		return augment.damageFactor(Math.round(最小+(tier+lvl)*(伤害+0.5f)));
//		return Math.round(最小+(2*tier+lvl)*(伤害+));
	}
	
	@Override
	public int 最大投掷攻击() {
		if (Dungeon.hero()){
			return Math.max(0, 最大投掷攻击( 强化等级() + RingOfSharpshooting.levelDamageBonus(Dungeon.hero) ));
		} else {
			return Math.max(0 , 最大投掷攻击( 强化等级() ));
		}
	}
	
	@Override
	public int 最大投掷攻击(int lvl) {
		return augment.damageFactor(Math.round(最大+(5 * tier +tier*lvl )*(伤害+0.5f)));
//		return Math.round(最大+(5 * tier +tier*lvl )*(伤害));
	}
	
	
	@Override
	public int throwPos(Hero user, int dst) {
		
		int projecting = 0;
		if (hasEnchant(Projecting.class, user)){
			projecting += 4;
		}
		if ((!(this instanceof 灵能短弓.SpiritArrow) && Random.Int(3) < user.天赋点数(Talent.SHARED_ENCHANTMENT))){
			灵能短弓 bow = Dungeon.hero.belongings.getItem(灵能短弓.class);
			if (bow != null && bow.hasEnchant(Projecting.class, user)) {
				projecting += 4;
			}
		}
		
		if (projecting > 0
			&& (Dungeon.level.passable[dst] || Dungeon.level.avoid[dst] || Actor.findChar(dst) != null)
			&& Dungeon.level.distance(user.pos, dst) <= Math.round(projecting * Enchantment.genericProcChanceMultiplier(user))){
			return dst;
		} else {
			return super.throwPos(user, dst);
		}
	}
	
	
	protected float adjacentAccFactor(Char owner, Char target){
		if (target!=null&&Dungeon.level.distance(owner.pos,target.pos)<=范围) {
			//抵近射击
			if (owner instanceof Hero hero){
				return 1;
			} else {
				return 1;
			}
		} else {
			if (owner instanceof Hero hero){
				return 0.75f+hero.天赋点数(Talent.精准射击,0.25f);
			} else {
				return 0.75f;
			}
		}
	}
	@Override
	protected void onThrow( int cell ) {
		Char enemy = Actor.findChar(cell);
		if (enemy == null || enemy == curUser) {
			if (!spawnedForEffect) super.onThrow( cell );
		} else {
			if (!curUser.shoot( enemy, this )) {
				rangedMiss( cell );
			} else {
				rangedHit( enemy, cell );
			}
		}
	}
	boolean sticky = true;//默认吸在敌人身上
	protected void rangedHit( Char enemy, int cell ){
		if(投掷消失){
			if(!spawnedForEffect){
				if(sticky&&enemy!=null&&enemy.isActive()&&enemy.alignment!=Char.Alignment.ALLY){
					PinCushion p=Buff.施加(enemy,PinCushion.class);
					if(p.target==enemy){
						p.stick(this);
						return;
					}
				}
				Dungeon.level.drop(this,cell).sprite.drop();
			}
		}
		投掷消失=true;
	}
	
	protected void rangedMiss( int cell ) {
		if(!spawnedForEffect)
			super.onThrow(cell);
	}
	
	@Override
	public int 投掷攻击时(Char attacker, Char defender, int damage) {
		
		if (attacker instanceof Hero hero) {
			int exStr = hero.力量() - 力量();
			if (hero.heroClass(HeroClass.WARRIOR)) {
				if (exStr > 0) {
					damage += exStr;
				}
			}else{
				if (exStr > 0) {
					damage += Hero.heroDamageIntRange( 0, exStr );
				}
			}
			if (hero.天赋(Talent.FOLLOWUP_STRIKE)) {
				hero.必中=true;
			}
		}
		if(流血>0)
			Buff.施加( defender, 流血.class).set(Math.round(damage*流血));
		
		if(吸血>0&&attacker instanceof Hero hero){
			hero.生命流动+=damage * 吸血;
		}
		
		if (attacker == Dungeon.hero && Random.Int(3) < Dungeon.hero.天赋点数(Talent.SHARED_ENCHANTMENT)){
			灵能短弓 bow = Dungeon.hero.belongings.getItem(灵能短弓.class);
			if (bow != null && bow.enchantment != null && Dungeon.hero.buff(MagicImmune.class) == null) {
				damage = bow.enchantment.proc(this, attacker, defender, damage);
			}
		}
		
		if (attacker instanceof Hero hero) {
			
			damage+=hero.heroDamageIntRange(hero.天赋生命力(Talent.精准射击,0.05f),hero.天赋生命力(Talent.精准射击,0.12f));
			damage+=hero.heroDamageIntRange(hero.天赋生命力(Talent.持久忍战,0.04f),hero.天赋生命力(Talent.持久忍战,0.1f));
			int exStr = hero.力量() - 力量();
			if (hero.heroClass(HeroClass.WARRIOR)) {
				if (exStr > 0) {
					damage += exStr;
				}
			}else{
				if (exStr > 0) {
					damage += Hero.heroDamageIntRange( 0, exStr );
				}
			}
			if (hero.buff(Momentum.class)!=null&&hero.buff(Momentum.class).freerunning()) {
				damage = Math.round(damage * (1f + hero.天赋点数(Talent.PROJECTILE_MOMENTUM,0.1f)));
			}
		}
		if ((cursed || hasCurseEnchant()) && !cursedKnown){
			GLog.n(Messages.get(this, "curse_discover"));
		}
		cursedKnown = true;
		
		int result = super.投掷攻击时(attacker, defender, damage);
		
		//handle ID progress over parent/child
		if (usesLeftToID > usesLeftToID){
			float diff = usesLeftToID - usesLeftToID;
			diff = Math.min( diff, Talent.鉴定速度(Dungeon.hero,this));
			if (attacker instanceof Hero hero && hero.天赋(Talent.SURVIVALISTS_INTUITION)){
				diff*=hero.天赋点数(Talent.SURVIVALISTS_INTUITION,4);
			}
			usesLeftToID -= diff;
			availableUsesToID -= diff;
			if (usesLeftToID <= 0) {
				if (ShardOfOblivion.passiveIDDisabled()){
					setIDReady();
				} else {
					鉴定();
				}
			}
		}
		
		if (!已鉴定() && ShardOfOblivion.passiveIDDisabled()){
			Buff.延长(curUser, ShardOfOblivion.ThrownUseTracker.class, 50f);
		}
		
		return result;
	}
	
	
	@Override
	public float castDelay(Char user, int cell) {
		return delayFactor( user );
	}
	
	@Override
	public void cast(Hero user,int dst){
		if (user.天赋(Talent.SEER_SHOT)
			&& user.buff(Talent.SeerShotCooldown.class) == null){
			int shotPos = throwPos(user, dst);
			if (Actor.findChar(shotPos) == null) {
				RevealedArea a = Buff.施加(user, RevealedArea.class, 20);
				a.depth = Dungeon.depth;
				a.branch = Dungeon.branch;
				a.pos = shotPos;
				Buff.施加(user, Talent.SeerShotCooldown.class,  20);
			}
		}
		super.cast(user,dst);
	}
	//endregion
	
	
	
	
	public float 命中 = 1f;	// Accuracy modifier
	public float 间隔= 1f;	// Speed modifier
	public int 范围 = 1;    // Reach modifier (only applies to melee hits)

	public enum Augment {
		DELAY(1,0.8f,1),
		DAMAGE  (1.1f, 1,1),
		ACCURACY  (1, 1,1.3f),
		NONE	(1,1,1);

		private float damageFactor;
		private float delayFactor;
		private float accuracyfactor;

		Augment(float dmg, float dly, float acc){
			damageFactor = dmg;
			delayFactor = dly;
			accuracyfactor = acc;
		}

		public int damageFactor(int dmg){
			return Math.round(dmg * damageFactor);
		}

		public float delayFactor(float dly){
			return dly * delayFactor;
		}
		public float accuracyFactor(float acc){
			return acc * accuracyfactor;
		}
	}
	
	public Augment augment = Augment.NONE;

	protected int usesToID(){
		return 20;
	}
	public float usesLeftToID = usesToID();
	public float availableUsesToID = usesToID()/2f;
	
	public Enchantment enchantment;
	public boolean enchantHardened = false;
	public boolean curseInfusionBonus = false;
	public boolean masteryPotionBonus = false;
	public boolean 神力 = false;
	
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		
		if (attacker instanceof Hero hero) {
			int exStr = hero.力量() - 力量();
			if (hero.heroClass(HeroClass.WARRIOR)) {
				if (exStr > 0) {
					damage += exStr;
				}
			}else{
				if (exStr > 0) {
					damage += Hero.heroDamageIntRange( 0, exStr );
				}
			}
		}
		if(流血>0)
		Buff.施加( defender, 流血.class).set(damage*流血);
		
		if(吸血>0&&attacker instanceof Hero hero){
			hero.生命流动+=damage * 吸血;
		}
		boolean becameAlly = false;
		boolean wasAlly = defender.alignment == Char.Alignment.ALLY;
		if (attacker.buff(MagicImmune.class) == null) {
			Enchantment trinityEnchant = null;
			//only when it's the hero or a char that uses the hero's weapon
			if (Dungeon.hero.buff(BodyForm.BodyFormBuff.class) != null
					&& (attacker == Dungeon.hero || attacker instanceof MirrorImage || attacker instanceof ShadowClone.ShadowAlly)){
				trinityEnchant = Dungeon.hero.buff(BodyForm.BodyFormBuff.class).enchant();
				if (enchantment != null && trinityEnchant != null && trinityEnchant.getClass() == enchantment.getClass()){
					trinityEnchant = null;
				}
			}

			if (attacker instanceof Hero && isEquipped((Hero) attacker)
					&& attacker.buff(HolyWeapon.HolyWepBuff.class) != null){
				if (enchantment != null &&
						(((Hero) attacker).subClass == HeroSubClass.PALADIN || hasCurseEnchant())){
					damage = enchantment.proc(this, attacker, defender, damage);
					if (defender.alignment == Char.Alignment.ALLY && !wasAlly){
						becameAlly = true;
					}
				}
				if (defender.isAlive() && !becameAlly && trinityEnchant != null){
					damage = trinityEnchant.proc(this, attacker, defender, damage);
				}
				if (defender.isAlive() && !becameAlly) {
					int dmg = ((Hero) attacker).subClass == HeroSubClass.PALADIN ? 6 : 2;
					defender.受伤时(Math.round(dmg * Enchantment.genericProcChanceMultiplier(attacker)), HolyWeapon.INSTANCE);
				}

			} else {
				if (enchantment != null) {
					damage = enchantment.proc(this, attacker, defender, damage);
					if (defender.alignment == Char.Alignment.ALLY && !wasAlly) {
						becameAlly = true;
					}
				}

				if (defender.isAlive() && !becameAlly && trinityEnchant != null){
					damage = trinityEnchant.proc(this, attacker, defender, damage);
				}
			}

			if (attacker instanceof Hero && isEquipped((Hero) attacker) &&
					attacker.buff(Smite.SmiteTracker.class) != null && !becameAlly){
				defender.受伤时(Smite.bonusDmg((Hero) attacker, defender), Smite.INSTANCE);
			}
		}
		
		if (!levelKnown && attacker == Dungeon.hero) {
			float uses = Math.min( availableUsesToID, Talent.鉴定速度(Dungeon.hero,this));
			availableUsesToID -= uses;
			usesLeftToID -= uses;
			if (usesLeftToID <= 0) {
				if (ShardOfOblivion.passiveIDDisabled()){
					if (usesLeftToID > -1){
						GLog.p(Messages.get(ShardOfOblivion.class, "identify_ready"), name());
					}
					setIDReady();
				} else {
					鉴定();
					GLog.p(Messages.get(Weapon.class, "identify"));
					Badges.validateItemLevelAquired(this);
				}
			}
		}

		return damage;
	}
	
	public void onHeroGainExp( float levelPercent, Hero hero ){
		levelPercent *= Talent.鉴定速度(hero,this);
		if (!levelKnown && availableUsesToID <= usesToID()/2f) {
			//gains enough uses to ID over 0.5 levels
			availableUsesToID = Math.min(usesToID()/2f, availableUsesToID + levelPercent * usesToID());
		}
	}
	
	private static final String USES_LEFT_TO_ID = "uses_left_to_id";
	private static final String AVAILABLE_USES  = "available_uses";
	private static final String ENCHANTMENT	    = "enchantment";
	private static final String ENCHANT_HARDENED = "enchant_hardened";
	private static final String CURSE_INFUSION_BONUS = "curse_infusion_bonus";
	private static final String MASTERY_POTION_BONUS = "mastery_potion_bonus";
	private static final String 神力x = "神力";
	private static final String AUGMENT	        = "augment";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( AVAILABLE_USES, availableUsesToID );
		bundle.put( ENCHANTMENT, enchantment );
		bundle.put( ENCHANT_HARDENED, enchantHardened );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( MASTERY_POTION_BONUS, masteryPotionBonus );
		bundle.put( 神力x, 神力 );
		bundle.put( AUGMENT, augment );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		usesLeftToID = bundle.getFloat( USES_LEFT_TO_ID );
		availableUsesToID = bundle.getFloat( AVAILABLE_USES );
		enchantment = (Enchantment)bundle.get( ENCHANTMENT );
		enchantHardened = bundle.getBoolean( ENCHANT_HARDENED );
		curseInfusionBonus = bundle.getBoolean( CURSE_INFUSION_BONUS );
		masteryPotionBonus = bundle.getBoolean( MASTERY_POTION_BONUS );
		神力 = bundle.getBoolean( 神力x );

		augment = bundle.getEnum(AUGMENT, Augment.class);
	}
	
	@Override
	public void reset() {
		super.reset();
		usesLeftToID = usesToID();
		availableUsesToID = usesToID()/2f;
	}

	@Override
	public Item 鉴定(boolean byHero) {
		if (enchantment != null && byHero && Dungeon.hero() && Dungeon.hero.isAlive()){
			Catalog.setSeen(enchantment.getClass());
			Statistics.itemTypesDiscovered.add(enchantment.getClass());
		}
		return super.鉴定(byHero);
	}

	public void setIDReady(){
		usesLeftToID = -1;
	}

	public boolean readyToIdentify(){
		return !已鉴定() && usesLeftToID <= 0;
	}
	
	@Override
	public float accuracyFactor(Char owner, Char target) {
		
		int encumbrance = 0;
		
		if( owner instanceof Hero hero&&!hero.heroClass(HeroClass.DUELIST)){
			encumbrance = 力量() - hero.力量();
		}

		float ACC = this.命中;

		if (owner.buff(Wayward.WaywardBuff.class) != null && enchantment instanceof Wayward){
			ACC /= 5;
		}
		if(encumbrance > 0 )ACC/=Math.pow( 1.5, encumbrance );
		if(encumbrance < 0 )ACC*=1+Math.sqrt(-encumbrance)*owner.属性增幅;
		
		
		ACC *= adjacentAccFactor(owner, target);
		return augment.accuracyFactor(ACC);
	}
	
	@Override
	public float delayFactor( Char owner ) {
		return baseDelay(owner) * (1f/speedMultiplier(owner));
	}

	protected float baseDelay( Char owner ){
		float delay = augment.delayFactor(this.间隔);
		if (owner instanceof Hero) {
			int encumbrance = 力量() - ((Hero)owner).力量();
			if (encumbrance > 0&&owner instanceof Hero hero&&!hero.heroClass(HeroClass.DUELIST)){
				delay *= Math.pow( 1.2, encumbrance );
			}
			if (encumbrance < 0){
				delay/=1+Math.sqrt(-encumbrance)*owner.属性增幅;
			}
			
		}

		return delay;
	}

	protected float speedMultiplier(Char owner ){
		float multi = RingOfFuror.attackSpeedMultiplier(owner);

//		if (owner.buff(弯刀.SwordDance.class)!=null){
//			multi += 0.6f;
//		}

		return multi;
	}

	@Override
	public int reachFactor(Char owner) {
		int reach = 范围;
			reach += RingOfAccuracy.getBuffedBonus(owner, RingOfAccuracy.Accuracy.class)/2;
		if (owner instanceof Hero&&武力之戒.fightingUnarmed((Hero) owner)){
			reach = 1; //brawlers stance benefits from enchantments, but not innate reach
			if (!武力之戒.unarmedGetsWeaponEnchantment((Hero) owner)){
				return reach;
			}
		}
		if (owner instanceof Hero && owner.buff(AscendedForm.AscendBuff.class) != null){
			reach += 2;
		}

		if (hasEnchant(Projecting.class, owner)){
			return reach + Math.round(Enchantment.genericProcChanceMultiplier(owner));
		} else {
			return reach;
		}
	}

	public int 力量(){
		return 力量(等级());
	}

	protected static int 力量(int tier, int lvl){
		lvl = Math.max(0, lvl);

		//strength req decreases at +1,+3,+6,+10,etc.
		return (8 + tier * 2) - (int)(Math.sqrt(8 * lvl + 1) - 1)/2;
	}

	@Override
	public int 等级() {
		int level = super.等级();
		if (curseInfusionBonus) level += 1 + level/6;
		return level;
	}
	
	@Override
	public Item 升级() {
		return 升级(false);
	}
	
	public Item 升级(boolean enchant ) {

		if (enchant){
			if (enchantment == null){
				enchant(Enchantment.random());
			}
		} else if (enchantment != null) {
			//chance to lose harden buff is 10/20/40/80/100% when upgrading from +6/7/8/9/10
			if (enchantHardened){
				if (等级() >= 6 && Random.Float(10) < Math.pow(2, 等级()-6)){
					enchantHardened = false;
				}

			//chance to remove curse is a static 33%
			} else if (hasCurseEnchant()) {
				if (Random.Int(3) == 0) enchant(null);

			//otherwise chance to lose enchant is 10/20/40/80/100% when upgrading from +4/5/6/7/8
			} else if (等级() >= 4 && Random.Float(10) < Math.pow(2, 等级()-4)){
				enchant(null);
			}
		}
		
		cursed = false;

		return super.升级();
	}
	
	@Override
	public String name() {
		if (isEquipped(Dungeon.hero) && !hasCurseEnchant() && Dungeon.hero.buff(HolyWeapon.HolyWepBuff.class) != null
			&& (Dungeon.hero.subClass != HeroSubClass.PALADIN || enchantment == null)){
				return Messages.get(HolyWeapon.class, "ench_name", super.name());
			} else {
				return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.name(super.name()) : super.name();

		}
	}
	
	@Override
	public Item random() {
		//+0: 75% (3/4)
		//+1: 20% (4/20)
		//+2: 5%  (1/20)
		int n = 0;
		if(Dungeon.解压(解压设置.持之以恒)){
			if (Random.Int(1) == 0){
				n++;
				if (Random.Int(2) == 0){
					n++;
					if (Random.Int(3) == 0){
						n++;
						if (Random.Int(4) == 0){
							n++;
							if (Random.Int(5) == 0){
								n++;
							}
						}
					}
				}
			}
		}else{
			if(Random.Int(4)==0){
				n++;
				if(Random.Int(5)==0){
					n++;
				}
			}
		}
		等级(n);

		//we use a separate RNG here so that variance due to things like parchment scrap
		//does not affect levelgen
		Random.pushGenerator(Random.Long());

			//30% chance to be cursed
			//10% chance to be enchanted
			float effectRoll = Random.Float();
			if (effectRoll < 0.3f * ParchmentScrap.curseChanceMultiplier()) {
				enchant(Enchantment.randomCurse());
				cursed = true;
			} else if (effectRoll >= 1f - (0.1f * ParchmentScrap.enchantChanceMultiplier())){
				enchant();
			}

		Random.popGenerator();

		return this;
	}
	
	public Weapon enchant( Enchantment ench ) {
		if (ench == null || !ench.curse()) curseInfusionBonus = false;
		enchantment = ench;
		updateQuickslot();
		if (ench != null && 已鉴定() && Dungeon.hero()
				&& Dungeon.hero.isAlive() && Dungeon.hero.belongings.contains(this)){
			Catalog.setSeen(ench.getClass());
			Statistics.itemTypesDiscovered.add(ench.getClass());
		}
		return this;
	}

	public Weapon enchant() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.random( oldEnchantment );

		return enchant( ench );
	}

	public boolean hasEnchant(Class<?extends Enchantment> type, Char owner) {
		if (owner.buff(MagicImmune.class) != null) {
			return false;
		} else if (enchantment != null
				&& !enchantment.curse()
				&& owner instanceof Hero
				&& isEquipped((Hero) owner)
				&& owner.buff(HolyWeapon.HolyWepBuff.class) != null
				&& ((Hero) owner).subClass != HeroSubClass.PALADIN) {
			return false;
		} else if (owner.buff(BodyForm.BodyFormBuff.class) != null
				&& owner.buff(BodyForm.BodyFormBuff.class).enchant() != null
				&& owner.buff(BodyForm.BodyFormBuff.class).enchant().getClass().equals(type)){
			return true;
		} else if (enchantment != null) {
			return enchantment.getClass() == type;
		} else {
			return false;
		}
	}
	
	//these are not used to process specific enchant effects, so magic immune doesn't affect them
	public boolean hasGoodEnchant(){
		return enchantment != null && !enchantment.curse();
	}

	public boolean hasCurseEnchant(){
		return enchantment != null && enchantment.curse();
	}

	private static ItemSprite.Glowing HOLY = new ItemSprite.Glowing( 0xFFFF00 );

	@Override
	public ItemSprite.Glowing glowing() {
		if (isEquipped(Dungeon.hero) && !hasCurseEnchant() && Dungeon.hero.buff(HolyWeapon.HolyWepBuff.class) != null
				&& (Dungeon.hero.subClass != HeroSubClass.PALADIN || enchantment == null)){
			return HOLY;
		} else {
			return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.glowing() : null;
		}
	}

	public static abstract class Enchantment implements Bundlable {

		public static final Class<?>[] common = new Class<?>[]{
				Blazing.class, Chilling.class, Kinetic.class, Shocking.class};

		public static final Class<?>[] uncommon = new Class<?>[]{
				Blocking.class, Blooming.class, Elastic.class,
				Lucky.class, Projecting.class, Unstable.class};

		public static final Class<?>[] rare = new Class<?>[]{
				Corrupting.class, Grim.class, Vampiric.class};

		public static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};

		public static final Class<?>[] curses = new Class<?>[]{
				Annoying.class, Displacing.class, Dazzling.class, Explosive.class,
				Sacrificial.class, Wayward.class, Polarized.class, Friendly.class
		};
		
			
		public abstract int proc( Weapon weapon, Char attacker, Char defender, int damage );

		protected float procChanceMultiplier( Char attacker ){
			return genericProcChanceMultiplier( attacker );
		}

		public static float genericProcChanceMultiplier( Char attacker ){
			float multi = 奥术之戒.enchantPowerMultiplier(attacker);
			if(attacker instanceof Hero hero){
				multi+=hero.天赋点数(Talent.附魔打击,0.25f);
				multi+=hero.天赋点数(Talent.盈能附魔,0.12f);
				multi+=hero.天赋点数(Talent.SHARED_ENCHANTMENT,0.12f);
			}
//			if (attacker.buff(符文之刃.RunicSlashTracker.class)!=null){
//				multi += attacker.buff(符文之刃.RunicSlashTracker.class).boost;
//				attacker.buff(符文之刃.RunicSlashTracker.class).detach();
//			}

			if (attacker.buff(Smite.SmiteTracker.class) != null){
				multi += 3f;
			}

			if (attacker.buff(ElementalStrike.DirectedPowerTracker.class) != null){
				multi += attacker.buff(ElementalStrike.DirectedPowerTracker.class).enchBoost;
				attacker.buff(ElementalStrike.DirectedPowerTracker.class).detach();
			}

			if (attacker.buff(Talent.SpiritBladesTracker.class) != null
					&& ((Hero)attacker).天赋点数(Talent.SPIRIT_BLADES) == 4){
				multi += 0.1f;
			}
			if (attacker.buff(Talent.StrikingWaveTracker.class) != null
					&& ((Hero)attacker).天赋点数(Talent.STRIKING_WAVE) == 4){
				multi += 0.2f;
			}

			return multi;
		}

		public String name() {
			if (!curse())
				return name( Messages.get(this, "enchant"));
			else
				return name( Messages.get(Item.class, "curse"));
		}

		public String name( String weaponName ) {
			return Messages.get(this, "name", weaponName);
		}

		public String desc() {
			return Messages.get(this, "desc");
		}

		public boolean curse() {
			return false;
		}

		@Override
		public void restoreFromBundle( Bundle bundle ) {
		}

		@Override
		public void storeInBundle( Bundle bundle ) {
		}
		
		public abstract ItemSprite.Glowing glowing();
		
		@SuppressWarnings("unchecked")
		public static Enchantment random( Class<? extends Enchantment> ... toIgnore ) {
			switch(Random.chances(typeChances)){
				case 0: default:
					return randomCommon( toIgnore );
				case 1:
					return randomUncommon( toIgnore );
				case 2:
					return randomRare( toIgnore );
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomCommon( Class<? extends Enchantment> ... toIgnore ) {
			ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(common));
			enchants.removeAll(Arrays.asList(toIgnore));
			if (enchants.isEmpty()) {
				return random();
			} else {
				return (Enchantment) Reflection.newInstance(Random.element(enchants));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomUncommon( Class<? extends Enchantment> ... toIgnore ) {
			ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(uncommon));
			enchants.removeAll(Arrays.asList(toIgnore));
			if (enchants.isEmpty()) {
				return random();
			} else {
				return (Enchantment) Reflection.newInstance(Random.element(enchants));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomRare( Class<? extends Enchantment> ... toIgnore ) {
			ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(rare));
			enchants.removeAll(Arrays.asList(toIgnore));
			if (enchants.isEmpty()) {
				return random();
			} else {
				return (Enchantment) Reflection.newInstance(Random.element(enchants));
			}
		}

		@SuppressWarnings("unchecked")
		public static Enchantment randomCurse( Class<? extends Enchantment> ... toIgnore ){
			ArrayList<Class<?>> enchants = new ArrayList<>(Arrays.asList(curses));
			enchants.removeAll(Arrays.asList(toIgnore));
			if (enchants.isEmpty()) {
				return random();
			} else {
				return (Enchantment) Reflection.newInstance(Random.element(enchants));
			}
		}
		
	}
}
