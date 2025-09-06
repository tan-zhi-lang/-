

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import static com.shatteredpixel.shatteredpixeldungeon.算法.x10;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x11;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x12;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x13;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x14;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x15;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x16;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x17;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x18;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x19;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x2;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x20;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x21;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x22;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x23;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x24;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x3;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x4;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x5;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x6;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x7;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x8;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x9;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.RevealedArea;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ScrollEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.DivineSense;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.RecallInscription;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.感知符石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.破损纹章;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public enum Talent {

	//Warrior T1
	VETERANS_INTUITION(0), 受衅怒火(1), 钢铁意志(2),
	//Warrior T2
	致命打击(4,3), 纹章升级(5,3), 越战越勇(6,3), IMPROVISED_PROJECTILES(7,3),
	//Warrior T3
	纹章荣耀(9, 4), STRONGMAN(10, 4),
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
	SCHOLARS_INTUITION(x2), LINGERING_MAGIC(x2+1), 保护屏障(x2+2),
	//Mage T2
	饱腹法术(x2+4,3), 高级魔杖(x2+5,3), ARCANE_VISION(x2+6,3), SHIELD_BATTERY(x2+7,3),
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
	THIEFS_INTUITION(x3), SUCKER_PUNCH(x3+1), PROTECTIVE_SHADOWS(x3+2),
	//Rogue T2
	体生匿影(x3+4,3), WIDE_SEARCH(x3+5,3), 无声步伐(x3+6,3), 寻宝猎人(x3+7,3),
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
	SURVIVALISTS_INTUITION(x4), FOLLOWUP_STRIKE(x4+1), NATURES_AID(x4+2),
	//Huntress T2
	自然猎手(x4+4,3), REJUVENATING_STEPS(x4+5,3), HEIGHTENED_SENSES(x4+6,3), DURABLE_PROJECTILES(x4+7,3),
	//Huntress T3
	自然丰收(105, 4), SEER_SHOT(106, 4),
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
	ADVENTURERS_INTUITION(x5), PATIENT_STRIKE(x5+1), AGGRESSIVE_BARRIER(x5+2),
	//Duelist T2
	灵敏机动(x5+4,3), WEAPON_RECHARGING(x5+5,3), LETHAL_HASTE(x5+6,3), SWIFT_EQUIP(x5+7,3),
	//Duelist T3
	附魔打击(137, 4), DEADLY_FOLLOWUP(138, 4),
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
	HOLY_INTUITION(x6), SEARING_LIGHT(x6+1), SHIELD_OF_LIGHT(x6+2),
	//Cleric T2
	RECALL_INSCRIPTION(x6+4,3), SUNRAY(x6+5,3), DIVINE_SENSE(x6+6,3), BLESS(x6+7,3),
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
	RATSISTANCE(215, 4), RATLOMACY(216, 4), RATFORCEMENTS(217, 4),
	
	任督二脉(x8+26, 3+6+9+2+2+2+2),
	
	祭鉴之术(x7),痛命之术(x7+1),死血之术(x7+2),
	血历之术(x7+4,3),血爆之术(x7+5,3),饮血之术(x7+6,3),换血之术(x7+7,3),
	顶福精华(x7+9,4),强能处消(x7+10,4),
	物到之术(x7+11,4),星火符刃(x7+12,4),高级血爆(x7+13,4),
	高级痛命(x7+14,4),高级死血(x7+15,4),高级吸血(x7+16,4),


	坚守鉴定(x8),盾举冲击(x8+1),钢铁之盾(x8+2),
	强力适应(x8+4,3),捍守可拘(x8+5,3),孤立无援(x8+6,3),严傲之意(x8+7,3),
	冰门高攻(x8+9,4),最佳防御(x8+10,4),
	强壮体魄(x8+11,4), 勇士之证(x8+12,4), 用盾诀窍(x8+13,4),
	急中生镜(x9),
	净除道法(x10),
	行路知里(x11),
	孤注一掷(x12),
	赌博高手(x13),
	探测仪器(x14),
	忍者直觉(x15),
	戒指察觉(x16),
	万变魁斗(x17),
	污蔑狂宴(x18),
	熟能生巧(x19),
	猫咪知觉(x20),
	敏锐嗅觉(x21),
	外裹透析(x22),
	血液侵透(x23),
	未来知识(x24),
	;
	public static class ImprovisedProjectileCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.15f, 0.2f, 0.5f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 50); }
	}
	
	public static class LethalMomentumTracker extends FlavourBuff{}
	public static class StrikingWaveTracker extends FlavourBuff{}
	public static class WandPreservationCounter extends CounterBuff{{revivePersists = true;}}
	public static class EmpoweredStrikeTracker extends FlavourBuff{
		//blast wave on-hit doesn't resolve instantly, so we delay detaching for it
		public boolean delayedDetach = false;
	}
	
	public static class ProtectiveShadowsTracker extends Buff {
		float barrierInc = 0.5f;

		@Override
		public boolean act() {
			if(target instanceof Hero hero)
			//barrier every 2/1 turns, to a max of 3/5
			if (hero.天赋(Talent.PROTECTIVE_SHADOWS)&&target.invisible>0){
				Barrier barrier = Buff.施加(target, Barrier.class);
				barrierInc += 1;
				int s = hero.天赋生命力(Talent.PROTECTIVE_SHADOWS,0.4f);
				if (barrier.护盾量() < s) {
					barrier.设置(s);
				}
				if (barrierInc >= 2){
					barrierInc = 0;
					if (hero.天赋(Talent.体生匿影)) {
						hero.回血(hero.天赋生命力(Talent.体生匿影,0.2f));
					}
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
	public static class BountyHunterTracker extends FlavourBuff{}
	
	public static class RejuvenatingStepsCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0f, 0.35f, 0.15f); }
		public float iconFadePercent() { return GameMath.gate(0, visualcooldown() / (20 - Dungeon.hero.天赋点数(REJUVENATING_STEPS,5)), 1); }
	}
	
	public static class RejuvenatingStepsFurrow extends CounterBuff{{revivePersists = true;}}
	
	public static class SeerShotCooldown extends FlavourBuff{
		public int icon() { return target.buff(RevealedArea.class) != null ? BuffIndicator.NONE : BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.7f, 0.4f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 20); }
	}
	
	public static class SpiritBladesTracker extends FlavourBuff{}
	
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
	}
	
	public static class AggressiveBarrierCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.35f, 0f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 50); }
	}
	
	public static class LiquidAgilEVATracker extends FlavourBuff{}
	
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
	}
	
	public static class LethalHasteCooldown extends FlavourBuff{
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0.35f, 0f, 0.7f); }
		public float iconFadePercent() { return Math.max(0, visualcooldown() / 100); }
	}
	
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
	}
	
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
	}
	
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
	}
	
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
	public static int[] 天赋解锁 = new int[]{0, 2, 5, 11, 20, 20};//25
//	public static int[] 天赋解锁 = new int[]{0, 2, 7, 13, 21, 30};

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
				return 26*7;
			}
			HeroClass cls = Dungeon.hero() ? Dungeon.hero.heroClass : GamesInProgress.selectedClass;
			switch (cls){
				case WARRIOR: default:
					return 26;
				case MAGE:
					return 26*2;
				case 盗贼:
					return 26*3;
				case HUNTRESS:
					return 26*4;
				case DUELIST:
					return 26*5;
				case CLERIC:
					return 26*6;
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
			if (!metaDesc.equals("没找到")){
//			if (!metaDesc.equals(Messages.NO_TEXT_FOUND)){
				return Messages.get(this, name() + ".desc") + "\n\n" + metaDesc;
			}
		}
		return Messages.get(this, name() + ".desc");
	}

	public static void 获得天赋时(Hero hero, Talent talent ){
		//for metamorphosis
		if (talent == 钢铁意志 && hero.heroClass != HeroClass.WARRIOR){
			Buff.施加(hero, 破损纹章.WarriorShield.class);
		}

		if (talent == VETERANS_INTUITION && hero.天赋点数(VETERANS_INTUITION) == 1){
			if (hero.belongings.armor() != null && !ShardOfOblivion.passiveIDDisabled())  {
				hero.belongings.armor.鉴定();
			}
		}
		if (talent == THIEFS_INTUITION && hero.天赋点数(THIEFS_INTUITION) == 1){
			if (hero.belongings.misc instanceof Ring) ((Ring) hero.belongings.misc).setKnown();
			if (hero.belongings.misc2 instanceof Ring) ((Ring) hero.belongings.misc2).setKnown();
			if (hero.belongings.misc3 instanceof Ring) ((Ring) hero.belongings.misc3).setKnown();
			if(!ShardOfOblivion.passiveIDDisabled()) {
				if (hero.belongings.misc instanceof Ring) {
					hero.belongings.misc.鉴定();
				}
				if (hero.belongings.misc2 instanceof Ring) {
					hero.belongings.misc2.鉴定();
				}
				if (hero.belongings.misc3 instanceof Ring) {
					hero.belongings.misc3.鉴定();
				}
			}
		}
		
		
		if(talent==THIEFS_INTUITION)
		if (hero.天赋点数(THIEFS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof Ring){
					((Ring) item).setKnown();
					((Ring) item).鉴定();
				}
			}
		}
		if(talent==VETERANS_INTUITION)
		if (hero.天赋点数(VETERANS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof Armor){
					((Armor) item).鉴定();
				}
			}
		}
		
		if(talent==急中生镜)
		if(hero.满天赋(急中生镜)){
			for(Item item:hero.belongings){
				if (item.等级()<=hero.天赋点数(急中生镜,2)){
					祛邪卷轴.净化(hero,item.鉴定());
				}
			}
		}else if (hero.天赋点数(急中生镜)==1){
			for(Item item:hero.belongings.装备()){
				if (item.等级()<=hero.天赋点数(急中生镜,2)){
					item.鉴定();
				}
			}
		}
		
		if(talent==净除道法)
		if(hero.满天赋(净除道法)){
			for(Item item:hero.belongings){
				if (item.cursed){
					祛邪卷轴.净化(hero,item.鉴定().特殊升级());
				}
			}
		}else if (hero.天赋点数(净除道法) == 1){
			for(Item item:hero.belongings.装备()){
				if (item!=null&&item.cursed){
					祛邪卷轴.净化(hero,item.鉴定());
				}
			}
		}
		if(talent==外裹透析)
		if(hero.满天赋(外裹透析)){
			for(Item item:hero.belongings){
				祛邪卷轴.净化(hero,item.鉴定().特殊升级());
				hero.受伤(3-hero.天赋点数(外裹透析));
			}
		}else if (hero.天赋点数(外裹透析) == 1){
			for(Item item:hero.belongings.装备()){
				item.鉴定();
				hero.受伤(3-hero.天赋点数(外裹透析));
			}
		}
		if(talent==赌博高手)
		if(hero.满天赋(赌博高手)){
			for(Item item:hero.belongings){
				祛邪卷轴.净化(hero,item.鉴定().特殊升级());
			}
		}else if (hero.天赋点数(赌博高手) == 1){
			for(Item item:hero.belongings.装备()){
				祛邪卷轴.净化(hero,item.鉴定().特殊升级());
			}
		}
		if(talent==猫咪知觉)
		if(hero.满天赋(猫咪知觉)){
			for(Item item:hero.belongings){
				item.鉴定();
			}
		}else if (hero.天赋点数(猫咪知觉) == 1){
			for(Item item:hero.belongings.装备()){
				item.鉴定();
			}
		}
		
		if(talent==戒指察觉)
		if(hero.天赋(戒指察觉)){
			for(Item item:hero.belongings){
				if (item instanceof Ring){
					祛邪卷轴.净化(hero,item.鉴定());
					if(hero.满天赋(戒指察觉)){
						item.特殊升级();
					}
				}
			}
		}
		
		if(talent==SCHOLARS_INTUITION)
		if (hero.天赋点数(SCHOLARS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings.backpack){
				if (item instanceof Wand){
					((Wand) item).鉴定().特殊升级();
				}
			}
		}
		if(talent==SURVIVALISTS_INTUITION)
		if (hero.天赋点数(SURVIVALISTS_INTUITION) == 2){
			for (Item item : Dungeon.hero.belongings){
				if (item instanceof MissileWeapon){
					((MissileWeapon) item).鉴定().特殊升级();
				}
			}
		}
		if(talent==ADVENTURERS_INTUITION)
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

		if (talent == LIGHT_CLOAK && hero.heroClass == HeroClass.盗贼){
			for (Item item : Dungeon.hero.belongings.backpack.items){
				if (item instanceof CloakOfShadows){
					if (!hero.belongings.lostInventory() || item.keptThroughLostInventory()) {
						((CloakOfShadows) item).activate(Dungeon.hero);
					}
				}
			}
		}

		if (talent == LIGHT_READING && hero.heroClass == HeroClass.CLERIC){
			for (Item item : Dungeon.hero.belongings.backpack.items){
				if (item instanceof 神圣法典){
					if (!hero.belongings.lostInventory() || item.keptThroughLostInventory()) {
						((神圣法典) item).activate(Dungeon.hero);
					}
				}
			}
		}
		hero.更新数据();
	}

	public static class CachedRationsDropped extends CounterBuff{{revivePersists = true;}}
	
	public static class 寻宝猎人 extends CounterBuff{{revivePersists = true;}}
	
	public static class NatureBerriesDropped extends CounterBuff{{revivePersists = true;}}
	
	public static void 吃饭时(Hero hero, float foodVal, Item foodSource ){
		hero.回血(hero.生命力(0.26f));

		if (hero.heroClass(HeroClass.WARRIOR)){
			if (hero.cooldown() > 0) {
				Buff.施加(hero, WarriorFoodImmunity.class, hero.cooldown());
			}
		}
		//法杖伤害
//			Buff.施加( hero, WandEmpower.class).set(5, 1);
//			ScrollOfRecharging.charge( hero );
		int wandChargeTurns = 0;

		int artifactChargeTurns = 0;
		//加伤
//			//3 bonus physical damage for next 2/3 attacks
//			Buff.施加( hero, PhysicalEmpower.class).set(1, 1);
		//武技充能
//		if (hero.有天赋()){
//			if (hero.heroClass == HeroClass.DUELIST){
//				//0.67/1 charge for the duelist
//				Buff.施加( hero, MeleeWeapon.Charger.class ).gainCharge(hero.天赋点数(,0.5f));
//				ScrollOfRecharging.charge( hero );
//			} else {
//				// lvl/3 / lvl/2 bonus dmg on next hit for other classes
//				Buff.施加( hero, PhysicalEmpower.class).set(Math.round(hero.等级 / (4f - hero.天赋点数())), 1);
//			}
//		}
		//施法获得护盾
//			if (hero.heroClass == HeroClass.CLERIC) {
//				Buff.施加(hero, SatiatedSpellsTracker.class);
//			} else {
//				//3/5 shielding, delayed up to 10 turns
//				int amount = hero.天赋点数(,5);
//				Barrier b = Buff.施加(hero, Barrier.class);
//				if (b.护盾量() <= amount){
//					b.设置(amount);
//					b.delay(Math.max(10-b.cooldown(), 0));
//				}
//			}
		//法典充能
//		if (hero.有天赋(ENLIGHTENING_MEAL)){
//			if (hero.heroClass == HeroClass.CLERIC) {
//				神圣法典 tome = hero.belongings.getItem(神圣法典.class);
//				if (tome != null) {
//					// 2/3 of a charge at +1, 1 full charge at +2
//					tome.directCharge( hero.天赋点数(ENLIGHTENING_MEAL,0.5f));
//					ScrollOfRecharging.charge(hero);
//				}
//			} else {
//				//2/3 turns of recharging, both kinds
//				wandChargeTurns += hero.天赋点数(ENLIGHTENING_MEAL,2);
//				artifactChargeTurns += hero.天赋点数(ENLIGHTENING_MEAL,2);
//			}
//		}

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

	public static float 鉴定速度(Hero hero,Item item){
		float factor = 1f
					   +hero.天赋点数(探测仪器,2)
					   +hero.天赋点数(熟能生巧,1)
				;
		if(hero.天赋(污蔑狂宴)){
			factor+=hero.污蔑狂宴/1500f;
		}
		if(hero.天赋(敏锐嗅觉)&&hero.hasbuff(Hunger.class)){
			factor+=(450f-hero.buff(Hunger.class).hunger())/hero.天赋点数(敏锐嗅觉,1f);
		}
		// Affected by both Warrior(1.75x/2.5x) and Duelist(2.5x/inst.) talents
		if (item instanceof MeleeWeapon){
			factor += hero.天赋点数(VETERANS_INTUITION,2);
		}
		// Affected by both Warrior(2.5x/inst.) and Duelist(1.75x/2.5x) talents
		if (item instanceof Armor){
			factor += hero.天赋点数(ADVENTURERS_INTUITION,2);
		}
		if (hero.天赋(急中生镜)&&item.等级()<=hero.天赋点数(急中生镜,2)){
			factor += hero.天赋点数(急中生镜);
		}
		if(hero.天赋(净除道法)&&item.cursed){
		factor += hero.天赋点数(净除道法);
		}
		if(hero.天赋(行路知里)){
		factor += hero.天赋点数(行路知里,3)*hero.移速();
		}
		if(hero.天赋(忍者直觉)){
			factor*=hero.天赋点数(忍者直觉,0.2f);
		}
		return factor;
	}

	public static void 喝药时(Hero hero, int cell, float factor ){

		if (false){//喝药加纹章盾
			// 6.5/10% of max HP
			int shieldToGive = Math.round( factor * hero.最大生命);
			hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldToGive), FloatingText.SHIELDING);
			Buff.施加(hero, Barrier.class).设置(shieldToGive);
		}
		if (false){//喝药加闪
			Buff.延长(hero, LiquidAgilEVATracker.class, hero.cooldown() + Math.max(0, factor-1));
			if (factor >= 0.5f){
				Buff.延长(hero, LiquidAgilACCTracker.class, 5f).uses = Math.round(factor);
			}
		}
	}

	public static void onScrollUsed( Hero hero, int pos, float factor, Class<?extends Item> cls ){
		if (false){//阅读法杖升级
			// 2/3 empowered wand zaps
			Buff.施加(hero, ScrollEmpower.class).reset((int) (factor));
		}
		if (false){//阅读隐身
			// 3/5 turns of stealth
			Buff.施加(hero, Invisibility.class, factor);
			Sample.INSTANCE.play( Assets.Sounds.MELD );
		}
		if (hero.天赋(RECALL_INSCRIPTION)&&Scroll.class.isAssignableFrom(cls)&&cls!=升级卷轴.class){
			if (hero.heroClass == HeroClass.CLERIC){
				Buff.延长(hero, RecallInscription.UsedItemTracker.class, hero.天赋点数(RECALL_INSCRIPTION,10)).item = cls;
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
		if (hero.天赋(RECALL_INSCRIPTION)&&Runestone.class.isAssignableFrom(cls)){
			if (hero.heroClass == HeroClass.CLERIC){
				Buff.延长(hero, RecallInscription.UsedItemTracker.class, hero.天赋点数(RECALL_INSCRIPTION,10)).item = cls;
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
		if (hero.天赋(ENHANCED_RINGS)){
			Buff.延长(hero, EnhancedRings.class, hero.天赋点数(ENHANCED_RINGS,2));
		}

		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.天赋(Talent.DIVINE_SENSE)){
			Buff.延长(Dungeon.hero, DivineSense.DivineSenseTracker.class, Dungeon.hero.cooldown()+1);
		}

		// 10/20/30%
		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.天赋(Talent.CLEANSE)
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
		if (hero.天赋点数(急中生镜) == 1 && item.等级()<=hero.天赋点数(急中生镜,2)){
			identify = true;
			if(hero.满天赋(急中生镜)){
				祛邪卷轴.净化(hero,item);
			}
		}
		if (hero.天赋(净除道法)&&item.cursed){
			identify = true;
			祛邪卷轴.净化(hero,item);
			if(hero.满天赋(净除道法)){
				item.特殊升级();
			}
		}
		if (hero.天赋(外裹透析)){
			identify = true;
			if(hero.满天赋(外裹透析)){
				祛邪卷轴.净化(hero,item);
			}
			hero.受伤(3-hero.天赋点数(外裹透析));
		}
		if (hero.天赋(赌博高手)&&!item.cursed){
			identify = true;
			if(hero.满天赋(赌博高手)){
				item.特殊升级();
			}
		}
		if (hero.天赋(THIEFS_INTUITION)&&item instanceof Ring){
			if (hero.天赋点数(THIEFS_INTUITION) == 1){
				identify = true;
			}
			((Ring) item).setKnown();
		}
		if (hero.天赋点数(ADVENTURERS_INTUITION) == 1 && item instanceof Weapon){
			identify = true;
		}
		
		if (hero.天赋(猫咪知觉)){
			item.cursedKnown=true;
			if (hero.满天赋(猫咪知觉)){
				item.鉴定();
			}
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
		if (hero.满天赋(猫咪知觉)){
			item.鉴定();
		}else if (hero.天赋(猫咪知觉)){
			item.cursedKnown=true;
		}
		
		if (hero.天赋(熟能生巧)){
			if (item instanceof Scroll) item.鉴定();
			if (hero.满天赋(熟能生巧)){
				if(item instanceof Potion)
					item.鉴定();
			}
		}
		if (hero.满天赋(熟能生巧)&&!item.cursed){
				item.鉴定().特殊升级();
		}
		if (hero.天赋点数(VETERANS_INTUITION) == 2){
			if (item instanceof Armor) ((Armor) item).鉴定();
		}
		if (hero.天赋点数(SCHOLARS_INTUITION) == 2){
			if (item instanceof Wand) ((Wand) item).鉴定().特殊升级();
		}
		if (hero.天赋点数(SURVIVALISTS_INTUITION) == 2){
			if (item instanceof MissileWeapon) ((MissileWeapon) item).鉴定().特殊升级();
		}
		if (hero.天赋点数(ADVENTURERS_INTUITION) == 2){
			if (item instanceof MeleeWeapon) ((MeleeWeapon) item).鉴定();
		}
		if (hero.满天赋(急中生镜) && item.等级()<=hero.天赋点数(急中生镜,2)){
			祛邪卷轴.净化(hero,item.鉴定());
		}
		if(hero.满天赋(净除道法)&&item.cursed){
			祛邪卷轴.净化(hero,item.鉴定().特殊升级());
		}
		if(hero.满天赋(外裹透析)){
			祛邪卷轴.净化(hero,item.鉴定());
			hero.受伤(3-hero.天赋点数(外裹透析));
		}
		if(hero.天赋(戒指察觉)&&item instanceof Ring){
			祛邪卷轴.净化(hero,item.鉴定());
			if(hero.满天赋(戒指察觉)){
				item.特殊升级();
			}
		}
	}

	public static int 伏击时(Hero hero, Char enemy, int dmg ){
		dmg+=Random.NormalIntRange(0,enemy.生命力(0.25f));
		if(hero.hasbuff(Invisibility.class)){
			dmg+=Random.NormalIntRange(0,enemy.生命力(0.25f));
		}
		if(hero.天赋(Talent.SUCKER_PUNCH) ) {
			dmg += hero.天赋生命力(Talent.SUCKER_PUNCH,0.2f);
		}
		return dmg;
	}
	public static int 攻击时(Hero hero, Char enemy, int dmg ){

		if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)){
			dmg=伏击时(hero,enemy,dmg);
		}
		if (hero.天赋(Talent.受衅怒火)
			&& hero.buff(ProvokedAngerTracker.class) != null){
			dmg +=Random.NormalIntRange(hero.天赋生命力(Talent.受衅怒火,0.2f),hero.天赋生命力(Talent.受衅怒火,0.5f));
			hero.buff(ProvokedAngerTracker.class).detach();
		}

		if (hero.天赋(Talent.LINGERING_MAGIC)
				&& hero.buff(LingeringMagicTracker.class) != null){
			dmg += hero.天赋生命力(Talent.LINGERING_MAGIC,0.2f);
			hero.buff(LingeringMagicTracker.class).detach();
		}

		if (hero.天赋(Talent.FOLLOWUP_STRIKE)&&enemy.isAlive()&&enemy.alignment==Char.Alignment.ENEMY) {
			if (hero.belongings.attackingWeapon() instanceof MissileWeapon) {
				Buff.延长(hero, FollowupStrikeTracker.class, 5f).object = enemy.id();
				hero.必中=true;
			} else if (hero.buff(FollowupStrikeTracker.class) != null
					&& hero.buff(FollowupStrikeTracker.class).object == enemy.id()){
				dmg += hero.天赋生命力(FOLLOWUP_STRIKE,0.14f);
				hero.buff(FollowupStrikeTracker.class).detach();
			}
		}

		if (hero.buff(Talent.SpiritBladesTracker.class) != null
				&& Random.Int(10) < 3*hero.天赋点数(Talent.SPIRIT_BLADES)){
			灵能短弓 bow = hero.belongings.getItem(灵能短弓.class);
			if (bow != null) dmg = bow.攻击时( hero, enemy, dmg );
			hero.buff(Talent.SpiritBladesTracker.class).detach();
		}

		if (hero.天赋(PATIENT_STRIKE)){
			if (hero.buff(PatientStrikeTracker.class) != null
					&& !(hero.belongings.attackingWeapon() instanceof MissileWeapon)){
				hero.buff(PatientStrikeTracker.class).detach();
				dmg += hero.天赋生命力(Talent.PATIENT_STRIKE,0.5f);
			}
		}

		if (hero.天赋(DEADLY_FOLLOWUP)&&enemy.alignment==Char.Alignment.ENEMY) {
			if (hero.belongings.attackingWeapon() instanceof MissileWeapon) {
				if (!(hero.belongings.attackingWeapon() instanceof 灵能短弓.SpiritArrow)) {
					Buff.延长(hero, DeadlyFollowupTracker.class, 5f).object = enemy.id();
				}
			} else if (hero.buff(DeadlyFollowupTracker.class) != null
					&& hero.buff(DeadlyFollowupTracker.class).object == enemy.id()){
				dmg = Math.round(dmg * (1.0f +hero.天赋点数(DEADLY_FOLLOWUP,0.12f)));
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
	}
	
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
			default:
				Collections.addAll(tierTalents, 任督二脉);
				break;
			case WARRIOR:
				Collections.addAll(tierTalents, VETERANS_INTUITION, 受衅怒火, 钢铁意志);
				break;
			case MAGE:
				Collections.addAll(tierTalents, SCHOLARS_INTUITION, LINGERING_MAGIC, 保护屏障);
				break;
			case 盗贼:
				Collections.addAll(tierTalents, THIEFS_INTUITION, SUCKER_PUNCH, PROTECTIVE_SHADOWS);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, SURVIVALISTS_INTUITION, FOLLOWUP_STRIKE, NATURES_AID);
				break;
			case DUELIST:
				Collections.addAll(tierTalents, ADVENTURERS_INTUITION, PATIENT_STRIKE, AGGRESSIVE_BARRIER);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, HOLY_INTUITION, SEARING_LIGHT, SHIELD_OF_LIGHT);
				break;
			case 巫女:
				Collections.addAll(tierTalents, 祭鉴之术, 痛命之术, 死血之术);
				break;
			case 重武:
				Collections.addAll(tierTalents, 坚守鉴定, 盾举冲击, 钢铁之盾);
				break;
			case 镜魔:
				Collections.addAll(tierTalents, 急中生镜);
				break;
			case 道士:
				Collections.addAll(tierTalents, 净除道法);
				break;
			case 行僧:
				Collections.addAll(tierTalents, 行路知里);
				break;
			case 近卫:
				Collections.addAll(tierTalents, 孤注一掷);
				break;
			case 兽灵:
				Collections.addAll(tierTalents, 赌博高手);
				break;
			case 机器:
				Collections.addAll(tierTalents, 探测仪器);
				break;
			case 女忍:
				Collections.addAll(tierTalents, 忍者直觉);
				break;
			case 戒老:
				Collections.addAll(tierTalents, 戒指察觉);
				break;
			case 逐姝:
				Collections.addAll(tierTalents, 万变魁斗);
				break;
			case 罗兰:
				Collections.addAll(tierTalents, 污蔑狂宴);
				break;
			case 学士:
				Collections.addAll(tierTalents, 熟能生巧);
				break;
			case 灵猫:
				Collections.addAll(tierTalents, 猫咪知觉);
				break;
			case 鼠弟:
				Collections.addAll(tierTalents, 敏锐嗅觉);
				break;
			case 凌云:
				Collections.addAll(tierTalents, 外裹透析);
				break;
			case 血鬼:
				Collections.addAll(tierTalents, 血液侵透);
				break;
			case 枪手:
				Collections.addAll(tierTalents, 未来知识);
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
			default:
				Collections.addAll(tierTalents, 任督二脉);
				break;
			case WARRIOR:
				Collections.addAll(tierTalents, 致命打击, 纹章升级, 越战越勇, IMPROVISED_PROJECTILES);
				break;
			case MAGE:
				Collections.addAll(tierTalents,饱腹法术,高级魔杖,ARCANE_VISION,SHIELD_BATTERY);
				break;
			case 盗贼:
				Collections.addAll(tierTalents, 体生匿影, WIDE_SEARCH, 无声步伐, 寻宝猎人);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, 自然猎手, REJUVENATING_STEPS, HEIGHTENED_SENSES, DURABLE_PROJECTILES);
				break;
			case DUELIST:
				Collections.addAll(tierTalents, 灵敏机动, WEAPON_RECHARGING, LETHAL_HASTE, SWIFT_EQUIP);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, RECALL_INSCRIPTION, SUNRAY, DIVINE_SENSE, BLESS);
				break;
			case 巫女:
				Collections.addAll(tierTalents, 血历之术, 血爆之术, 饮血之术, 换血之术);
				break;
			case 重武:
				Collections.addAll(tierTalents, 强力适应,捍守可拘,孤立无援,严傲之意);
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
			default:
				Collections.addAll(tierTalents, 任督二脉);
				break;
			case WARRIOR:
				Collections.addAll(tierTalents, 纹章荣耀, STRONGMAN);
				break;
			case MAGE:
				Collections.addAll(tierTalents, DESPERATE_POWER, ALLY_WARP);
				break;
			case 盗贼:
				Collections.addAll(tierTalents, ENHANCED_RINGS, LIGHT_CLOAK);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents, 自然丰收, SEER_SHOT);
				break;
			case DUELIST:
				Collections.addAll(tierTalents, 附魔打击, DEADLY_FOLLOWUP);
				break;
			case CLERIC:
				Collections.addAll(tierTalents, CLEANSE, LIGHT_READING);
				break;
			case 巫女:
				Collections.addAll(tierTalents, 顶福精华, 强能处消);
				break;
			case 重武:
				Collections.addAll(tierTalents, 冰门高攻, 最佳防御);
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
			case 潜能觉醒: default:
				Collections.addAll(tierTalents, 任督二脉);
				break;
			case BERSERKER:
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
			case 神秘学者:
				Collections.addAll(tierTalents, 物到之术, 星火符刃, 高级血爆);
				break;
			case 黑魔导师:
				Collections.addAll(tierTalents, 高级痛命, 高级死血, 高级吸血);
				break;
//			case 盾之勇者:
//				Collections.addAll(tierTalents, 强壮体魄, 勇士之证, 用盾诀窍);
//				break;
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
