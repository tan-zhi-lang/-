

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Haste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RevealedArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ScrollEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.WandEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.DivineSense;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.RecallInscription;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HolyTome;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.感知符石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.镶钉手套;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public enum Talent {

	//Warrior T1
	HEARTY_MEAL(0), VETERANS_INTUITION(1), PROVOKED_ANGER(2), IRON_WILL(3),
	//Warrior T2
	IRON_STOMACH(4), LIQUID_WILLPOWER(5,3), RUNIC_TRANSFERENCE(6,3), LETHAL_MOMENTUM(7,3), IMPROVISED_PROJECTILES(8,3),
	//Warrior T3
	HOLD_FAST(9, 4), STRONGMAN(10, 4),
	//Berserker T3
	ENDLESS_RAGE(11, 4), DEATHLESS_FURY(12, 4), ENRAGED_CATALYST(13, 4),
	//Gladiator T3
	CLEAVE(14, 4), LETHAL_DEFENSE(15, 4), ENHANCED_COMBO(16, 4),
	//Heroic Leap T4
	BODY_SLAM(17, 4), IMPACT_WAVE(18, 4), DOUBLE_JUMP(19, 4),
	//Shockwave T4
	EXPANDING_WAVE(20, 4), STRIKING_WAVE(21, 4), SHOCK_FORCE(22, 4),
	//Endure T4
	SUSTAINED_RETRIBUTION(23, 4), SHRUG_IT_OFF(24, 4), EVEN_THE_ODDS(25, 4),

	//Mage T1
	EMPOWERING_MEAL(32), SCHOLARS_INTUITION(33), LINGERING_MAGIC(34), BACKUP_BARRIER(35),
	//Mage T2
	ENERGIZING_MEAL(36), INSCRIBED_POWER(37,3), WAND_PRESERVATION(38,3), ARCANE_VISION(39,3), SHIELD_BATTERY(40,3),
	//Mage T3
	DESPERATE_POWER(41, 4), ALLY_WARP(42, 4),
	//Battlemage T3
	EMPOWERED_STRIKE(43, 4), MYSTICAL_CHARGE(44, 4), EXCESS_CHARGE(45, 4),
	//Warlock T3
	SOUL_EATER(46, 4), SOUL_SIPHON(47, 4), NECROMANCERS_MINIONS(48, 4),
	//Elemental Blast T4
	BLAST_RADIUS(49, 4), ELEMENTAL_POWER(50, 4), REACTIVE_BARRIER(51, 4),
	//Wild Magic T4
	WILD_POWER(52, 4), FIRE_EVERYTHING(53, 4), CONSERVED_MAGIC(54, 4),
	//Warp Beacon T4
	TELEFRAG(55, 4), REMOTE_BEACON(56, 4), LONGRANGE_WARP(57, 4),

	//Rogue T1
	CACHED_RATIONS(64), THIEFS_INTUITION(65), SUCKER_PUNCH(66), PROTECTIVE_SHADOWS(67),
	//Rogue T2
	MYSTICAL_MEAL(68), INSCRIBED_STEALTH(69,3), WIDE_SEARCH(70,3), SILENT_STEPS(71,3), ROGUES_FORESIGHT(72,3),
	//Rogue T3
	ENHANCED_RINGS(73, 4), LIGHT_CLOAK(74, 4),
	//Assassin T3
	ENHANCED_LETHALITY(75, 4), ASSASSINS_REACH(76, 4), BOUNTY_HUNTER(77, 4),
	//Freerunner T3
	EVASIVE_ARMOR(78, 4), PROJECTILE_MOMENTUM(79, 4), SPEEDY_STEALTH(80, 4),
	//Smoke Bomb T4
	HASTY_RETREAT(81, 4), BODY_REPLACEMENT(82, 4), SHADOW_STEP(83, 4),
	//Death Mark T4
	FEAR_THE_REAPER(84, 4), DEATHLY_DURABILITY(85, 4), DOUBLE_MARK(86, 4),
	//Shadow Clone T4
	SHADOW_BLADE(87, 4), CLONED_ARMOR(88, 4), PERFECT_COPY(89, 4),

	//Huntress T1
	NATURES_BOUNTY(96), SURVIVALISTS_INTUITION(97), FOLLOWUP_STRIKE(98), NATURES_AID(99),
	//Huntress T2
	INVIGORATING_MEAL(100), LIQUID_NATURE(101,3), REJUVENATING_STEPS(102,3), HEIGHTENED_SENSES(103,3), DURABLE_PROJECTILES(104,3),
	//Huntress T3
	POINT_BLANK(105, 4), SEER_SHOT(106, 4),
	//Sniper T3
	FARSIGHT(107, 4), SHARED_ENCHANTMENT(108, 4), SHARED_UPGRADES(109, 4),
	//Warden T3
	DURABLE_TIPS(110, 4), BARKSKIN(111, 4), SHIELDING_DEW(112, 4),
	//Spectral Blades T4
	FAN_OF_BLADES(113, 4), PROJECTING_BLADES(114, 4), SPIRIT_BLADES(115, 4),
	//Natures Power T4
	GROWING_POWER(116, 4), NATURES_WRATH(117, 4), WILD_MOMENTUM(118, 4),
	//Spirit Hawk T4
	EAGLE_EYE(119, 4), GO_FOR_THE_EYES(120, 4), SWIFT_SPIRIT(121, 4),

	//Duelist T1
	STRENGTHENING_MEAL(128), ADVENTURERS_INTUITION(129), PATIENT_STRIKE(130), AGGRESSIVE_BARRIER(131),
	//Duelist T2
	FOCUSED_MEAL(132), LIQUID_AGILITY(133,3), WEAPON_RECHARGING(134,3), LETHAL_HASTE(135,3), SWIFT_EQUIP(136,3),
	//Duelist T3
	PRECISE_ASSAULT(137, 4), DEADLY_FOLLOWUP(138, 4),
	//Champion T3
	VARIED_CHARGE(139, 4), TWIN_UPGRADES(140, 4), COMBINED_LETHALITY(141, 4),
	//Monk T3
	UNENCUMBERED_SPIRIT(142, 4), MONASTIC_VIGOR(143, 4), COMBINED_ENERGY(144, 4),
	//Challenge T4
	CLOSE_THE_GAP(145, 4), INVIGORATING_VICTORY(146, 4), ELIMINATION_MATCH(147, 4),
	//Elemental Strike T4
	ELEMENTAL_REACH(148, 4), STRIKING_FORCE(149, 4), DIRECTED_POWER(150, 4),
	//Feint T4
	FEIGNED_RETREAT(151, 4), EXPOSE_WEAKNESS(152, 4), COUNTER_ABILITY(153, 4),

	//Cleric T1
	SATIATED_SPELLS(160), HOLY_INTUITION(161), SEARING_LIGHT(162), SHIELD_OF_LIGHT(163),
	//Cleric T2
	ENLIGHTENING_MEAL(164), RECALL_INSCRIPTION(165,3), SUNRAY(166,3), DIVINE_SENSE(167,3), BLESS(168,3),
	//Cleric T3
	CLEANSE(169, 4), LIGHT_READING(170, 4),
	//Priest T3
	HOLY_LANCE(171, 4), HALLOWED_GROUND(172, 4), MNEMONIC_PRAYER(173, 4),
	//Paladin T3
	LAY_ON_HANDS(174, 4), AURA_OF_PROTECTION(175, 4), WALL_OF_LIGHT(176, 4),
	//Ascended Form T4
	DIVINE_INTERVENTION(177, 4), JUDGEMENT(178, 4), FLASH(179, 4),
	//Trinity T4
	BODY_FORM(180, 4), MIND_FORM(181, 4), SPIRIT_FORM(182, 4),
	//Power of Many T4
	BEAMING_RAY(183, 4), LIFE_LINK(184, 4), STASIS(185, 4),

	//universal T4
	HEROIC_ENERGY(26, 4), //See icon() and title() for special logic for this one
	//Ratmogrify T4
	RATSISTANCE(215, 4), RATLOMACY(216, 4), RATFORCEMENTS(217, 4);

	public static class ImprovisedProjectileCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.15f, 0.2f, 0.5f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 50); }
	};
	public static class LethalMomentumTracker extends FlavourBuff{};
	public static class StrikingWaveTracker extends FlavourBuff{};
	public static class WandPreservationCounter extends CounterBuff{{revivePersists = true;}};
	public static class EmpoweredStrikeTracker extends FlavourBuff{
		//blast wave on-hit doesn't resolve instantly, so we delay detaching for it
		public boolean delayedDetach = false;
	};
	public static class ProtectiveShadowsTracker extends Buff {
		float barrierInc = 0.5f;

		@Override
		public boolean act() {
			//barrier every 2/1 turns, to a max of 3/5
			if (((Hero)target).有天赋(Talent.PROTECTIVE_SHADOWS) && target.invisible > 0){
				Barrier barrier = Buff.施加(target, Barrier.class);
				if (barrier.shielding() < ((Hero)target).天赋点数(Talent.PROTECTIVE_SHADOWS,10)) {
					barrierInc += 0.5f;
				}
				if (barrierInc >= 1){
					barrierInc = 0;
					barrier.incShield(1);
				} else {
					barrier.incShield(0); //resets barrier decay
				}
			} else {
				detach();
			}
			spend( TICK );
			return true;
		}

		private static final String BARRIER_INC = "barrier_inc";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put( BARRIER_INC, barrierInc);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			barrierInc = bundle.getFloat( BARRIER_INC );
		}
	}
	public static class BountyHunterTracker extends FlavourBuff{};
	public static class RejuvenatingStepsCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0f, 0.35f, 0.15f); }
		public float iconFadePercent() { return GameMath.gate(0, visualcooldown() / (15 - 5*Dungeon.hero.天赋点数(REJUVENATING_STEPS)), 1); }
	};
	public static class RejuvenatingStepsFurrow extends CounterBuff{{revivePersists = true;}};
	public static class SeerShotCooldown extends FlavourBuff{
		public int icon() { return target.buff(RevealedArea.class) != null ? BuffIndicator.NONE : BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.7f, 0.4f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 20); }
	};
	public static class SpiritBladesTracker extends FlavourBuff{};
	public static class PatientStrikeTracker extends Buff {
		public int pos;
		{ type = Buff.buffType.POSITIVE; }
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.5f, 0f, 1f); }
		@Override
		public boolean act() {
			if (pos != target.pos) {
				detach();
			} else {
				spend(TICK);
			}
			return true;
		}
		private static final String POS = "pos";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(POS, pos);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			pos = bundle.getInt(POS);
		}
	};
	public static class AggressiveBarrierCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.35f, 0f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 50); }
	};
	public static class LiquidAgilEVATracker extends FlavourBuff{};
	public static class LiquidAgilACCTracker extends FlavourBuff{
		public int uses;

		{ type = buffType.POSITIVE; }
		public int icon() { return BuffIndicator.INVERT_MARK; }
		public void tintIcon(Image icon) { icon.hardlight(0.5f, 0f, 1f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }

		private static final String USES = "uses";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(USES, uses);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			uses = bundle.getInt(USES);
		}
	};
	public static class LethalHasteCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.35f, 0f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 100); }
	};
	public static class SwiftEquipCooldown extends FlavourBuff{
		public boolean secondUse;
		public boolean hasSecondUse(){
			return secondUse;
		}

		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) {
			if (hasSecondUse()) icon.hardlight(0.85f, 0f, 1.0f);
			else                icon.hardlight(0.35f, 0f, 0.7f);
		}
		public float iconFadePercent() { return GameMath.gate(0, visualcooldown() / 20f, 1); }

		private static final String SECOND_USE = "second_use";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(SECOND_USE, secondUse);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			secondUse = bundle.getBoolean(SECOND_USE);
		}
	};
	public static class DeadlyFollowupTracker extends FlavourBuff{
		public int object;
		{ type = Buff.buffType.POSITIVE; }
		public int icon() { return BuffIndicator.INVERT_MARK; }
		public void tintIcon(Image icon) { icon.hardlight(0.5f, 0f, 1f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
		private static final String OBJECT    = "object";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(OBJECT, object);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			object = bundle.getInt(OBJECT);
		}
	}
	public static class PreciseAssaultTracker extends FlavourBuff{
		{ type = buffType.POSITIVE; }
		public int icon() { return BuffIndicator.INVERT_MARK; }
		public void tintIcon(Image icon) { icon.hardlight(1f, 1f, 0.0f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
	};
	public static class VariedChargeTracker extends Buff{
		public Class weapon;

		private static final String WEAPON    = "weapon";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(WEAPON, weapon);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			weapon = bundle.getClass(WEAPON);
		}
	}
	public static class CombinedLethalityAbilityTracker extends FlavourBuff{
		public MeleeWeapon weapon;
	};
	public static class CombinedEnergyAbilityTracker extends FlavourBuff{
		public boolean monkAbilused = false;
		public boolean wepAbilUsed = false;

		private static final String MONK_ABIL_USED  = "monk_abil_used";
		private static final String WEP_ABIL_USED   = "wep_abil_used";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(MONK_ABIL_USED, monkAbilused);
			bundle.put(WEP_ABIL_USED, wepAbilUsed);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			monkAbilused = bundle.getBoolean(MONK_ABIL_USED);
			wepAbilUsed = bundle.getBoolean(WEP_ABIL_USED);
		}
	}
	public static class CounterAbilityTacker extends FlavourBuff{}
	public static class SatiatedSpellsTracker extends Buff{
		@Override
		public int icon() {
			return BuffIndicator.SPELL_FOOD;
		}
	}
	//used for metamorphed searing light
	public static class SearingLightCooldown extends FlavourBuff{
		@Override
		public int icon() {
			return BuffIndicator.TIME;
		}
		public void tintIcon(Image icon) { icon.hardlight(0f, 0f, 1f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 20); }
	}

	int icon;
	int 最大点数;

	// tiers 1/2/3/4 start at levels 2/7/13/21 5 6 8 => 2/8/15/24/35 6-3 7-1 9
	public static int[] 天赋解锁 = new int[]{0, 1+1+0, 3+1+1, 8+1+2, 16+1+3, 24+1+4};
//	public static int[] 天赋解锁 = new int[]{0, 1+1+0, 6+1+1, 12+1+2, 20+1+3, 30+1+4};

	Talent( int icon ){
		this(icon, 2);
	}

	Talent( int icon, int 最大点数){
		this.icon = icon;
		this.最大点数 = 最大点数;
	}

	public int icon(){
		if (this == HEROIC_ENERGY){
			if (Ratmogrify.useRatroicEnergy){
				return 218;
			}
			HeroClass cls = Dungeon.hero != null ? Dungeon.hero.heroClass : GamesInProgress.selectedClass;
			switch (cls){
				case WARRIOR: default:
					return 26;
				case MAGE:
					return 58;
				case ROGUE:
					return 90;
				case HUNTRESS:
					return 122;
				case DUELIST:
					return 154;
				case CLERIC:
					return 186;
			}
		} else {
			return icon;
		}
	}

	public int 最大点数(){
		return 最大点数;
	}

	public String title(){
		if (this == HEROIC_ENERGY && Ratmogrify.useRatroicEnergy){
			return Messages.get(this, name() + ".rat_title");
		}
		return Messages.get(this, name() + ".title");
	}

	public final String desc(){
		return desc(false);
	}

	public String desc(boolean metamorphed){
		if (metamorphed){
			String metaDesc = Messages.get(this, name() + ".meta_desc");
			if (!metaDesc.equals(Messages.NO_TEXT_FOUND)){
				return Messages.get(this, name() + ".desc") + "\n\n" + metaDesc;
			}
		}
		return Messages.get(this, name() + ".desc");
	}

	public static void 获得天赋时(Hero hero, Talent talent ){
		//for metamorphosis
		if (talent == IRON_WILL && hero.heroClass != HeroClass.WARRIOR){
			Buff.施加(hero, BrokenSeal.WarriorShield.class);
		}

		if (talent == VETERANS_INTUITION && hero.天赋点数(VETERANS_INTUITION) == 1){
			if (hero.belongings.armor() != null && !ShardOfOblivion.passiveIDDisabled())  {
				hero.belongings.armor.鉴定();
			}
		}
		if (talent == THIEFS_INTUITION && hero.天赋点数(THIEFS_INTUITION) == 1){
			if (hero.belongings.ring instanceof Ring && !ShardOfOblivion.passiveIDDisabled()) {
				hero.belongings.ring.鉴定();
			}
			if (hero.belongings.misc instanceof Ring && !ShardOfOblivion.passiveIDDisabled()) {
				hero.belongings.misc.鉴定();
			}
		}
		if (talent == THIEFS_INTUITION && hero.天赋点数(THIEFS_INTUITION) == 1){
			if (hero.belongings.ring instanceof Ring) hero.belongings.ring.setKnown();
			if (hero.belongings.misc instanceof Ring) ((Ring) hero.belongings.misc).setKnown();
		}


		if (hero.天赋点数(THIEFS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof Ring){
					((Ring) item).setKnown();
					((Ring) item).鉴定();
				}
			}
		}
		if (hero.天赋点数(VETERANS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof Armor){
					((Armor) item).鉴定();
				}
			}
		}
		if (hero.天赋点数(SCHOLARS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof Wand){
					((Wand) item).鉴定();
				}
			}
		}
		if (hero.天赋点数(SURVIVALISTS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof MissileWeapon){
					((MissileWeapon) item).鉴定();
				}
			}
		}
		if (hero.天赋点数(ADVENTURERS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof MeleeWeapon){
					((MeleeWeapon) item).鉴定();
				}
			}
		}
		if (talent == ADVENTURERS_INTUITION && hero.天赋点数(ADVENTURERS_INTUITION) == 1){
			if (hero.belongings.weapon() != null && !ShardOfOblivion.passiveIDDisabled()){
				hero.belongings.weapon().鉴定();
			}
		}

		if (talent == PROTECTIVE_SHADOWS && hero.invisible > 0){
			Buff.施加(hero, Talent.ProtectiveShadowsTracker.class);
		}

		if (talent == LIGHT_CLOAK && hero.heroClass == HeroClass.ROGUE){
			for (Item item : Dungeon.hero.belongings.backpack){
				if (item instanceof CloakOfShadows){
					if (!hero.belongings.lostInventory() || item.keptThroughLostInventory()) {
						((CloakOfShadows) item).activate(Dungeon.hero);
					}
				}
			}
		}

		if (talent == HEIGHTENED_SENSES || talent == FARSIGHT || talent == DIVINE_SENSE){
			Dungeon.observe();
		}

		if (talent == TWIN_UPGRADES || talent == DESPERATE_POWER
				|| talent == STRONGMAN || talent == DURABLE_PROJECTILES){
			Item.updateQuickslot();
		}

		if (talent == UNENCUMBERED_SPIRIT && hero.满天赋(talent)){
			Item toGive = new ClothArmor().鉴定();
			if (!toGive.放背包()){
				Dungeon.level.drop(toGive, hero.pos).sprite.drop();
			}
			toGive = new 镶钉手套().鉴定();
			if (!toGive.放背包()){
				Dungeon.level.drop(toGive, hero.pos).sprite.drop();
			}
		}

		if (talent == LIGHT_READING && hero.heroClass == HeroClass.CLERIC){
			for (Item item : Dungeon.hero.belongings.backpack){
				if (item instanceof HolyTome){
					if (!hero.belongings.lostInventory() || item.keptThroughLostInventory()) {
						((HolyTome) item).activate(Dungeon.hero);
					}
				}
			}
		}

		//if we happen to have spirit form applied with a ring of might
		if (talent == SPIRIT_FORM){
			Dungeon.hero.更新生命(false);
		}
	}

	public static class CachedRationsDropped extends CounterBuff{{revivePersists = true;}};
	public static class NatureBerriesDropped extends CounterBuff{{revivePersists = true;}};

	public static void onFoodEaten( Hero hero, float foodVal, Item foodSource ){
		hero.生命 = Math.min(hero.生命 + 1, hero.最大生命);
		hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(1), FloatingText.HEALING);

		if (hero.有天赋(HEARTY_MEAL)){
			//4/6 HP healed, when hero is below 33% health (with a little rounding up)
			if (hero.生命 /(float)hero.最大生命 < 0.334f) {
				int healing = hero.天赋点数(HEARTY_MEAL,4);
				hero.生命 = Math.min(hero.生命 + healing, hero.最大生命);
				hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(healing), FloatingText.HEALING);

			}
		}
		if (hero.有天赋(IRON_STOMACH)){
			if (hero.cooldown() > 0) {
				Buff.施加(hero, WarriorFoodImmunity.class, hero.cooldown());
			}
		}
		if (hero.有天赋(EMPOWERING_MEAL)){
			//2/3 bonus wand damage for next 3 zaps
			Buff.施加( hero, WandEmpower.class).set(hero.天赋点数(EMPOWERING_MEAL,6), 1);
			ScrollOfRecharging.charge( hero );
		}
		int wandChargeTurns = 0;

		if (hero.有天赋(ENERGIZING_MEAL)){
			//5/8 turns of recharging
			wandChargeTurns += hero.天赋点数(ENERGIZING_MEAL,5);
		}
		int artifactChargeTurns = 0;
		if (hero.有天赋(MYSTICAL_MEAL)){
			//3/5 turns of recharging
			artifactChargeTurns += hero.天赋点数(MYSTICAL_MEAL,3);
		}
		if (hero.有天赋(INVIGORATING_MEAL)){
			//effectively 1/2 turns of haste
			Buff.延长( hero, Haste.class, hero.天赋点数(INVIGORATING_MEAL));
		}
		if (hero.有天赋(STRENGTHENING_MEAL)){
			//3 bonus physical damage for next 2/3 attacks
			Buff.施加( hero, PhysicalEmpower.class).set(hero.天赋点数(STRENGTHENING_MEAL,5), 1);
		}
		if (hero.有天赋(FOCUSED_MEAL)){
			if (hero.heroClass == HeroClass.DUELIST){
				//0.67/1 charge for the duelist
				Buff.施加( hero, MeleeWeapon.Charger.class ).gainCharge(hero.天赋点数(FOCUSED_MEAL,0.5f));
				ScrollOfRecharging.charge( hero );
			} else {
				// lvl/3 / lvl/2 bonus dmg on next hit for other classes
				Buff.施加( hero, PhysicalEmpower.class).set(Math.round(hero.等级 / (4f - hero.天赋点数(FOCUSED_MEAL))), 1);
			}
		}
		if (hero.有天赋(SATIATED_SPELLS)){
			if (hero.heroClass == HeroClass.CLERIC) {
				Buff.施加(hero, SatiatedSpellsTracker.class);
			} else {
				//3/5 shielding, delayed up to 10 turns
				int amount = hero.天赋点数(SATIATED_SPELLS,5);
				Barrier b = Buff.施加(hero, Barrier.class);
				if (b.shielding() <= amount){
					b.setShield(amount);
					b.delay(Math.max(10-b.cooldown(), 0));
				}
			}
		}
		if (hero.有天赋(ENLIGHTENING_MEAL)){
			if (hero.heroClass == HeroClass.CLERIC) {
				HolyTome tome = hero.belongings.getItem(HolyTome.class);
				if (tome != null) {
					// 2/3 of a charge at +1, 1 full charge at +2
					tome.directCharge( hero.天赋点数(ENLIGHTENING_MEAL,0.5f));
					ScrollOfRecharging.charge(hero);
				}
			} else {
				//2/3 turns of recharging, both kinds
				wandChargeTurns += hero.天赋点数(ENLIGHTENING_MEAL,2);
				artifactChargeTurns += hero.天赋点数(ENLIGHTENING_MEAL,2);
			}
		}

		//we process these at the end as they can stack together from some talents
		if (wandChargeTurns > 0){
			Buff.延长( hero, Recharging.class, wandChargeTurns );
			ScrollOfRecharging.charge( hero );
			SpellSprite.show(hero, SpellSprite.CHARGE);
		}
		if (artifactChargeTurns > 0){
			ArtifactRecharge buff = Buff.施加( hero, ArtifactRecharge.class);
			if (buff.left() < artifactChargeTurns){
				buff.set(artifactChargeTurns).ignoreHornOfPlenty = foodSource instanceof HornOfPlenty;
			}
			ScrollOfRecharging.charge( hero );
			SpellSprite.show(hero, SpellSprite.CHARGE, 0, 1, 1);
		}
	}

	public static class WarriorFoodImmunity extends FlavourBuff{
		{ actPriority = HERO_PRIO+1; }
	}

	public static float itemIDSpeedFactor( Hero hero, Item item ){
		float factor = 1f;

		// Affected by both Warrior(1.75x/2.5x) and Duelist(2.5x/inst.) talents
		if (item instanceof MeleeWeapon){
			factor *= hero.天赋点数(VETERANS_INTUITION,2);
		}
		// Affected by both Warrior(2.5x/inst.) and Duelist(1.75x/2.5x) talents
		if (item instanceof Armor){
			factor *= hero.天赋点数(ADVENTURERS_INTUITION,2);
		}
		return factor;
	}

	public static void onPotionUsed( Hero hero, int cell, float factor ){
		if (hero.有天赋(LIQUID_WILLPOWER)){
			// 6.5/10% of max HP
			int shieldToGive = Math.round( factor * hero.最大生命 * hero.天赋点数(LIQUID_WILLPOWER,0.06f));
			hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldToGive), FloatingText.SHIELDING);
			Buff.施加(hero, Barrier.class).setShield(shieldToGive);
		}
		if (hero.有天赋(LIQUID_NATURE)){
			ArrayList<Integer> grassCells = new ArrayList<>();
			for (int i : PathFinder.NEIGHBOURS9){
				grassCells.add(cell+i);
			}
			Random.shuffle(grassCells);
			for (int grassCell : grassCells){
				Char ch = Actor.findChar(grassCell);
				if (ch != null && ch.alignment == Char.Alignment.ENEMY){
					//1/2 turns of roots
					Buff.施加(ch, Roots.class, factor * hero.天赋点数(LIQUID_NATURE));
				}
				if (Dungeon.level.map[grassCell] == Terrain.EMPTY ||
						Dungeon.level.map[grassCell] == Terrain.EMBERS ||
						Dungeon.level.map[grassCell] == Terrain.EMPTY_DECO){
					Level.set(grassCell, Terrain.GRASS);
					GameScene.updateMap(grassCell);
				}
				CellEmitter.get(grassCell).burst(LeafParticle.LEVEL_SPECIFIC, 4);
			}
			// 4/6 cells total
			int totalGrassCells = (int) (factor * (hero.天赋点数(LIQUID_NATURE,2)));
			while (grassCells.size() > totalGrassCells){
				grassCells.remove(0);
			}
			for (int grassCell : grassCells){
				int t = Dungeon.level.map[grassCell];
				if ((t == Terrain.EMPTY || t == Terrain.EMPTY_DECO || t == Terrain.EMBERS
						|| t == Terrain.GRASS || t == Terrain.FURROWED_GRASS)
						&& Dungeon.level.plants.get(grassCell) == null){
					Level.set(grassCell, Terrain.HIGH_GRASS);
					GameScene.updateMap(grassCell);
				}
			}
			Dungeon.observe();
		}
		if (hero.有天赋(LIQUID_AGILITY)){
			Buff.延长(hero, LiquidAgilEVATracker.class, hero.cooldown() + Math.max(0, factor-1));
			if (factor >= 0.5f){
				Buff.延长(hero, LiquidAgilACCTracker.class, 5f).uses = Math.round(factor);
			}
		}
	}

	public static void onScrollUsed( Hero hero, int pos, float factor, Class<?extends Item> cls ){
		if (hero.有天赋(INSCRIBED_POWER)){
			// 2/3 empowered wand zaps
			Buff.施加(hero, ScrollEmpower.class).reset((int) (factor * (hero.天赋点数(INSCRIBED_POWER))));
		}
		if (hero.有天赋(INSCRIBED_STEALTH)){
			// 3/5 turns of stealth
			Buff.施加(hero, Invisibility.class, factor * hero.天赋点数(INSCRIBED_STEALTH,3));
			Sample.INSTANCE.play( Assets.Sounds.MELD );
		}
		if (hero.有天赋(RECALL_INSCRIPTION) && Scroll.class.isAssignableFrom(cls) && cls != 升级卷轴.class){
			if (hero.heroClass == HeroClass.CLERIC){
				Buff.延长(hero, RecallInscription.UsedItemTracker.class, hero.天赋点数(RECALL_INSCRIPTION) == 2 ? 300 : 10).item = cls;
			} else {
				// 10/15%
				if (Random.Int(99) < hero.天赋点数(RECALL_INSCRIPTION,10)){
					Reflection.newInstance(cls).放背包();
					GLog.p("refunded!");
				}
			}
		}
	}

	public static void onRunestoneUsed( Hero hero, int pos, Class<?extends Item> cls ){
		if (hero.有天赋(RECALL_INSCRIPTION) && Runestone.class.isAssignableFrom(cls)){
			if (hero.heroClass == HeroClass.CLERIC){
				Buff.延长(hero, RecallInscription.UsedItemTracker.class, hero.天赋点数(RECALL_INSCRIPTION) == 2 ? 300 : 10).item = cls;
			} else {

				//don't trigger on 1st intuition use
				if (cls.equals(感知符石.class) && hero.buff(感知符石.IntuitionUseTracker.class) != null){
					return;
				}
				// 10/15%
				if (Random.Int(99) < 1 + hero.天赋点数(RECALL_INSCRIPTION)){
					Reflection.newInstance(cls).放背包();
					GLog.p("refunded!");
				}
			}
		}
	}

	public static void onArtifactUsed( Hero hero ){
		if (hero.有天赋(ENHANCED_RINGS)){
			Buff.延长(hero, EnhancedRings.class, hero.天赋点数(ENHANCED_RINGS,2));
		}

		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.DIVINE_SENSE)){
			Buff.延长(Dungeon.hero, DivineSense.DivineSenseTracker.class, Dungeon.hero.cooldown()+1);
		}

		// 10/20/30%
		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.CLEANSE)
				&& Random.Int(10) < Dungeon.hero.天赋点数(Talent.CLEANSE)){
			boolean removed = false;
			for (Buff b : Dungeon.hero.buffs()) {
				if (b.type == Buff.buffType.NEGATIVE
						&& !(b instanceof LostInventory)) {
					b.detach();
					removed = true;
				}
			}
			if (removed && Dungeon.hero.sprite != null) {
				new Flare( 6, 32 ).color(0xFF4CD2, true).show( Dungeon.hero.sprite, 2f );
			}
		}
	}

	public static void 装备时(Hero hero, Item item ){
		boolean identify = false;
		if (hero.天赋点数(VETERANS_INTUITION) == 1 && item instanceof Armor){
			identify = true;
		}
		if (hero.有天赋(THIEFS_INTUITION) && item instanceof Ring){
			if (hero.天赋点数(THIEFS_INTUITION) == 1){
				identify = true;
			}
			((Ring) item).setKnown();
		}
		if (hero.天赋点数(ADVENTURERS_INTUITION) == 1 && item instanceof Weapon){
			identify = true;
		}

		if (identify && !ShardOfOblivion.passiveIDDisabled()){
			item.鉴定();
		}
	}

	public static void 拾取时(Hero hero, Item item ){
		if (hero.天赋点数(THIEFS_INTUITION) == 2){
			if (item instanceof Ring){
				((Ring) item).setKnown();
				((Ring) item).鉴定();
			}
		}
		if (hero.天赋点数(VETERANS_INTUITION) == 2){
			if (item instanceof Armor) ((Armor) item).鉴定();
		}
		if (hero.天赋点数(SCHOLARS_INTUITION) == 2){
			if (item instanceof Wand) ((Wand) item).鉴定();
		}
		if (hero.天赋点数(SURVIVALISTS_INTUITION) == 2){
			if (item instanceof MissileWeapon) ((MissileWeapon) item).鉴定();
		}
		if (hero.天赋点数(ADVENTURERS_INTUITION) == 2){
			if (item instanceof MeleeWeapon) ((MeleeWeapon) item).鉴定();
		}
	}

	public static int 伏击时(Hero hero, Char enemy, int dmg ){

		if(hero.有天赋(Talent.SUCKER_PUNCH) && enemy.buff(SuckerPunchTracker.class) == null) {
			dmg += hero.天赋点数(Talent.SUCKER_PUNCH, 2);
			Buff.施加(enemy, SuckerPunchTracker.class);
		}
		return dmg++;
	}
	public static int 攻击时(Hero hero, Char enemy, int dmg ){

		if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)){
			dmg=伏击时(hero,enemy,dmg);
		}
		if (hero.有天赋(Talent.PROVOKED_ANGER)
			&& hero.buff(ProvokedAngerTracker.class) != null){
			dmg += hero.天赋点数(Talent.PROVOKED_ANGER,3);
			hero.buff(ProvokedAngerTracker.class).detach();
		}

		if (hero.有天赋(Talent.LINGERING_MAGIC)
				&& hero.buff(LingeringMagicTracker.class) != null){
			dmg += hero.天赋点数(Talent.LINGERING_MAGIC,3);
			hero.buff(LingeringMagicTracker.class).detach();
		}

		if (hero.有天赋(Talent.FOLLOWUP_STRIKE) && enemy.isAlive() && enemy.alignment == Char.Alignment.ENEMY) {
			if (hero.belongings.attackingWeapon() instanceof MissileWeapon) {
				Buff.延长(hero, FollowupStrikeTracker.class, 5f).object = enemy.id();
			} else if (hero.buff(FollowupStrikeTracker.class) != null
					&& hero.buff(FollowupStrikeTracker.class).object == enemy.id()){
				dmg += hero.天赋点数(FOLLOWUP_STRIKE,4);
				hero.buff(FollowupStrikeTracker.class).detach();
			}
		}

		if (hero.buff(Talent.SpiritBladesTracker.class) != null
				&& Random.Int(10) < 3*hero.天赋点数(Talent.SPIRIT_BLADES)){
			SpiritBow bow = hero.belongings.getItem(SpiritBow.class);
			if (bow != null) dmg = bow.攻击时( hero, enemy, dmg );
			hero.buff(Talent.SpiritBladesTracker.class).detach();
		}

		if (hero.有天赋(PATIENT_STRIKE)){
			if (hero.buff(PatientStrikeTracker.class) != null
					&& !(hero.belongings.attackingWeapon() instanceof MissileWeapon)){
				hero.buff(PatientStrikeTracker.class).detach();
				dmg += hero.天赋点数(Talent.PATIENT_STRIKE,3);
			}
		}

		if (hero.有天赋(DEADLY_FOLLOWUP) && enemy.alignment == Char.Alignment.ENEMY) {
			if (hero.belongings.attackingWeapon() instanceof MissileWeapon) {
				if (!(hero.belongings.attackingWeapon() instanceof SpiritBow.SpiritArrow)) {
					Buff.延长(hero, DeadlyFollowupTracker.class, 5f).object = enemy.id();
				}
			} else if (hero.buff(DeadlyFollowupTracker.class) != null
					&& hero.buff(DeadlyFollowupTracker.class).object == enemy.id()){
				dmg = Math.round(dmg * (1.0f +hero.天赋点数(DEADLY_FOLLOWUP,0.1f)));
			}
		}

		return dmg;
	}

	public static class ProvokedAngerTracker extends FlavourBuff{
		{ type = Buff.buffType.POSITIVE; }
		public int icon() { return BuffIndicator.WEAPON; }
		public void tintIcon(Image icon) { icon.hardlight(1.43f, 1.43f, 1.43f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
	}
	public static class LingeringMagicTracker extends FlavourBuff{
		{ type = Buff.buffType.POSITIVE; }
		public int icon() { return BuffIndicator.WEAPON; }
		public void tintIcon(Image icon) { icon.hardlight(1.43f, 1.43f, 0f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
	}
	public static class SuckerPunchTracker extends Buff{};
	public static class FollowupStrikeTracker extends FlavourBuff{
		public int object;
		{ type = Buff.buffType.POSITIVE; }
		public int icon() { return BuffIndicator.INVERT_MARK; }
		public void tintIcon(Image icon) { icon.hardlight(0f, 0.75f, 1f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
		private static final String OBJECT    = "object";
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(OBJECT, object);
		}
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			object = bundle.getInt(OBJECT);
		}
	};

	public static final int MAX_TALENT_TIERS = 4;

	public static void initClassTalents( Hero hero ){
		initClassTalents( hero.heroClass, hero.talents, hero.metamorphedTalents );
	}

	public static void initClassTalents( HeroClass cls, ArrayList<LinkedHashMap<Talent, Integer>> talents){
		initClassTalents( cls, talents, new LinkedHashMap<>());
	}

	public static void initClassTalents( HeroClass cls, ArrayList<LinkedHashMap<Talent, Integer>> talents, LinkedHashMap<Talent, Talent> replacements ){
		while (talents.size() < MAX_TALENT_TIERS){
			talents.add(new LinkedHashMap<>());
		}

		ArrayList<Talent> tierTalents = new ArrayList<>();

		//tier 1
		switch (cls){
			case WARRIOR: default:
				Collections.addAll(tierTalents, /*HEARTY_MEAL,*/ VETERANS_INTUITION, PROVOKED_ANGER, IRON_WILL);
				break;
			case MAGE:
				Collections.addAll(tierTalents, /*EMPOWERING_MEAL,*/ SCHOLARS_INTUITION, LINGERING_MAGIC, BACKUP_BARRIER);
				break;
			case ROGUE:
				Collections.addAll(tierTalents, /*CACHED_RATIONS,*/ THIEFS_INTUITION, SUCKER_PUNCH, PROTECTIVE_SHADOWS);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, /*NATURES_BOUNTY,*/ SURVIVALISTS_INTUITION, FOLLOWUP_STRIKE, NATURES_AID);
				break;
			case DUELIST:
				Collections.addAll(tierTalents, /*STRENGTHENING_MEAL,*/ ADVENTURERS_INTUITION, PATIENT_STRIKE, AGGRESSIVE_BARRIER);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, /*SATIATED_SPELLS,*/ HOLY_INTUITION, SEARING_LIGHT, SHIELD_OF_LIGHT);
				break;
		}
		for (Talent talent : tierTalents){
			if (replacements.containsKey(talent)){
				talent = replacements.get(talent);
			}
			talents.get(0).put(talent, 0);
		}
		tierTalents.clear();

		//tier 2
		switch (cls){
			case WARRIOR: default:
				Collections.addAll(tierTalents, /*IRON_STOMACH,*/ LIQUID_WILLPOWER, RUNIC_TRANSFERENCE, LETHAL_MOMENTUM, IMPROVISED_PROJECTILES);
				break;
			case MAGE:
				Collections.addAll(tierTalents, /*ENERGIZING_MEAL,*/ INSCRIBED_POWER, WAND_PRESERVATION, ARCANE_VISION, SHIELD_BATTERY);
				break;
			case ROGUE:
				Collections.addAll(tierTalents, /*MYSTICAL_MEAL,*/ INSCRIBED_STEALTH, WIDE_SEARCH, SILENT_STEPS, ROGUES_FORESIGHT);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, /*INVIGORATING_MEAL,*/ LIQUID_NATURE, REJUVENATING_STEPS, HEIGHTENED_SENSES, DURABLE_PROJECTILES);
				break;
			case DUELIST:
				Collections.addAll(tierTalents,/* FOCUSED_MEAL,*/ LIQUID_AGILITY, WEAPON_RECHARGING, LETHAL_HASTE, SWIFT_EQUIP);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, /*ENLIGHTENING_MEAL,*/ RECALL_INSCRIPTION, SUNRAY, DIVINE_SENSE, BLESS);
				break;
		}
		for (Talent talent : tierTalents){
			if (replacements.containsKey(talent)){
				talent = replacements.get(talent);
			}
			talents.get(1).put(talent, 0);
		}
		tierTalents.clear();

		//tier 3
		switch (cls){
			case WARRIOR: default:
				Collections.addAll(tierTalents, HOLD_FAST, STRONGMAN);
				break;
			case MAGE:
				Collections.addAll(tierTalents, DESPERATE_POWER, ALLY_WARP);
				break;
			case ROGUE:
				Collections.addAll(tierTalents, ENHANCED_RINGS, LIGHT_CLOAK);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, POINT_BLANK, SEER_SHOT);
				break;
			case DUELIST:
				Collections.addAll(tierTalents, PRECISE_ASSAULT, DEADLY_FOLLOWUP);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, CLEANSE, LIGHT_READING);
				break;
		}
		for (Talent talent : tierTalents){
			if (replacements.containsKey(talent)){
				talent = replacements.get(talent);
			}
			talents.get(2).put(talent, 0);
		}
		tierTalents.clear();

		//tier4
		//TBD
	}

	public static void initSubclassTalents( Hero hero ){
		initSubclassTalents( hero.subClass, hero.talents );
	}

	public static void initSubclassTalents( HeroSubClass cls, ArrayList<LinkedHashMap<Talent, Integer>> talents ){
		if (cls == HeroSubClass.NONE) return;

		while (talents.size() < MAX_TALENT_TIERS){
			talents.add(new LinkedHashMap<>());
		}

		ArrayList<Talent> tierTalents = new ArrayList<>();

		//tier 3
		switch (cls){
			case BERSERKER: default:
				Collections.addAll(tierTalents, ENDLESS_RAGE, DEATHLESS_FURY, ENRAGED_CATALYST);
				break;
			case GLADIATOR:
				Collections.addAll(tierTalents, CLEAVE, LETHAL_DEFENSE, ENHANCED_COMBO);
				break;
			case BATTLEMAGE:
				Collections.addAll(tierTalents, EMPOWERED_STRIKE, MYSTICAL_CHARGE, EXCESS_CHARGE);
				break;
			case WARLOCK:
				Collections.addAll(tierTalents, SOUL_EATER, SOUL_SIPHON, NECROMANCERS_MINIONS);
				break;
			case ASSASSIN:
				Collections.addAll(tierTalents, ENHANCED_LETHALITY, ASSASSINS_REACH, BOUNTY_HUNTER);
				break;
			case FREERUNNER:
				Collections.addAll(tierTalents, EVASIVE_ARMOR, PROJECTILE_MOMENTUM, SPEEDY_STEALTH);
				break;
			case SNIPER:
				Collections.addAll(tierTalents, FARSIGHT, SHARED_ENCHANTMENT, SHARED_UPGRADES);
				break;
			case WARDEN:
				Collections.addAll(tierTalents, DURABLE_TIPS, BARKSKIN, SHIELDING_DEW);
				break;
			case CHAMPION:
				Collections.addAll(tierTalents, VARIED_CHARGE, TWIN_UPGRADES, COMBINED_LETHALITY);
				break;
			case MONK:
				Collections.addAll(tierTalents, UNENCUMBERED_SPIRIT, MONASTIC_VIGOR, COMBINED_ENERGY);
				break;
			case PRIEST:
				Collections.addAll(tierTalents, HOLY_LANCE, HALLOWED_GROUND, MNEMONIC_PRAYER);
				break;
			case PALADIN:
				Collections.addAll(tierTalents, LAY_ON_HANDS, AURA_OF_PROTECTION, WALL_OF_LIGHT);
				break;
		}
		for (Talent talent : tierTalents){
			talents.get(2).put(talent, 0);
		}
		tierTalents.clear();

	}

	public static void initArmorTalents( Hero hero ){
		initArmorTalents( hero.armorAbility, hero.talents);
	}

	public static void initArmorTalents(ArmorAbility abil, ArrayList<LinkedHashMap<Talent, Integer>> talents ){
		if (abil == null) return;

		while (talents.size() < MAX_TALENT_TIERS){
			talents.add(new LinkedHashMap<>());
		}

		for (Talent t : abil.talents()){
			talents.get(3).put(t, 0);
		}
	}

	private static final String TALENT_TIER = "talents_tier_";

	public static void storeTalentsInBundle( Bundle bundle, Hero hero ){
		for (int i = 0; i < MAX_TALENT_TIERS; i++){
			LinkedHashMap<Talent, Integer> tier = hero.talents.get(i);
			Bundle tierBundle = new Bundle();

			for (Talent talent : tier.keySet()){
				if (tier.get(talent) > 0){
					tierBundle.put(talent.name(), tier.get(talent));
				}
				if (tierBundle.contains(talent.name())){
					tier.put(talent, Math.min(tierBundle.getInt(talent.name()), talent.最大点数()));
				}
			}
			bundle.put(TALENT_TIER+(i+1), tierBundle);
		}

		Bundle replacementsBundle = new Bundle();
		for (Talent t : hero.metamorphedTalents.keySet()){
			replacementsBundle.put(t.name(), hero.metamorphedTalents.get(t));
		}
		bundle.put("replacements", replacementsBundle);
	}

	private static final HashSet<String> removedTalents = new HashSet<>();
	static{
		//nothing atm
	}

	private static final HashMap<String, String> renamedTalents = new HashMap<>();
	static{
		//nothing atm
	}

	public static void restoreTalentsFromBundle( Bundle bundle, Hero hero ){
		if (bundle.contains("replacements")){
			Bundle replacements = bundle.getBundle("replacements");
			for (String key : replacements.getKeys()){
				String value = replacements.getString(key);
				if (renamedTalents.containsKey(key)) key = renamedTalents.get(key);
				if (renamedTalents.containsKey(value)) value = renamedTalents.get(value);
				if (!removedTalents.contains(key) && !removedTalents.contains(value)){
					try {
						hero.metamorphedTalents.put(Talent.valueOf(key), Talent.valueOf(value));
					} catch (Exception e) {
						ShatteredPixelDungeon.reportException(e);
					}
				}
			}
		}

		if (hero.heroClass != null)     initClassTalents(hero);
		if (hero.subClass != null)      initSubclassTalents(hero);
		if (hero.armorAbility != null)  initArmorTalents(hero);

		for (int i = 0; i < MAX_TALENT_TIERS; i++){
			LinkedHashMap<Talent, Integer> tier = hero.talents.get(i);
			Bundle tierBundle = bundle.contains(TALENT_TIER+(i+1)) ? bundle.getBundle(TALENT_TIER+(i+1)) : null;

			if (tierBundle != null){
				for (String tName : tierBundle.getKeys()){
					int points = tierBundle.getInt(tName);
					if (renamedTalents.containsKey(tName)) tName = renamedTalents.get(tName);
					if (!removedTalents.contains(tName)) {
						try {
							Talent talent = Talent.valueOf(tName);
							if (tier.containsKey(talent)) {
								tier.put(talent, Math.min(points, talent.最大点数()));
							}
						} catch (Exception e) {
							ShatteredPixelDungeon.reportException(e);
						}
					}
				}
			}
		}
	}

}
