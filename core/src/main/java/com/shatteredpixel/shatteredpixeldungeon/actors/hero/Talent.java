

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import static com.shatteredpixel.shatteredpixeldungeon.算法.x10;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x15;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x23;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x25;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x6;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x7;
import static com.shatteredpixel.shatteredpixeldungeon.算法.x8;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.CounterBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ScrollEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.感知符石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.传奇肛塞;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
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

	纹章荣耀(9, 4), 力大无穷(10,4),
	//Berserker T3
	洪荒之怒(11,4), 血气旺盛(12,4), 嗜血成性(13,4),
	//Gladiator T3
	连战热忱(14,4), 以战养战(15,4), 连击强化(16,4),
	//Heroic Leap T4
	BODY_SLAM(17, 4), IMPACT_WAVE(18, 4), DOUBLE_JUMP(19, 4),
	//Shockwave T4
	EXPANDING_WAVE(20, 4), STRIKING_WAVE(21, 4), SHOCK_FORCE(22, 4),
	//Endure T4
	SUSTAINED_RETRIBUTION(23, 4), SHRUG_IT_OFF(24, 4), EVEN_THE_ODDS(25, 4),

	高级魔杖(41,4), SHIELD_BATTERY(42, 4),
	//Battlemage T3
	EMPOWERED_STRIKE(43, 4), MYSTICAL_CHARGE(44, 4), 盈能附魔(45,4),
	//Warlock T3
	SOUL_EATER(46, 4), SOUL_SIPHON(47, 4), NECROMANCERS_MINIONS(48, 4),
	//Elemental Blast T4
	BLAST_RADIUS(49, 4), ELEMENTAL_POWER(50, 4), REACTIVE_BARRIER(51, 4),
	//Wild Magic T4
	WILD_POWER(52, 4), FIRE_EVERYTHING(53, 4), CONSERVED_MAGIC(54, 4),
	//Warp Beacon T4
	TELEFRAG(55, 4), REMOTE_BEACON(56, 4), LONGRANGE_WARP(57, 4),

	
	体生匿影(73, 4), 轻便斗篷(74,4),
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

	
	弓箭强化(105,4), SEER_SHOT(106,4),
	//Sniper T3
	鹰眼远视(107,4), SHARED_ENCHANTMENT(108,4), 狙击弱点(109,4),
	//Warden T3
	消受投掷(110,4), 丛林法则(111,4), SHIELDING_DEW(112,4),
	//Spectral Blades T4
	FAN_OF_BLADES(113, 4), PROJECTING_BLADES(114, 4), SPIRIT_BLADES(115, 4),
	//Natures Power T4
	GROWING_POWER(116, 4), NATURES_WRATH(117, 4), WILD_MOMENTUM(118, 4),
	//Spirit Hawk T4
	EAGLE_EYE(119, 4), GO_FOR_THE_EYES(120, 4), SWIFT_SPIRIT(121, 4),

	附魔打击(137, 4), 高阶配装(138,4),
	//Champion T3
	双武格挡(139,4), TWIN_UPGRADES(140,4), 复合损伤(141,4),
	//Monk T3
	UNENCUMBERED_SPIRIT(142, 4), MONASTIC_VIGOR(143, 4), COMBINED_ENERGY(144, 4),
	//Challenge T4
	CLOSE_THE_GAP(145, 4), INVIGORATING_VICTORY(146, 4), ELIMINATION_MATCH(147, 4),
	//Elemental Strike T4
	ELEMENTAL_REACH(148, 4), STRIKING_FORCE(149, 4), DIRECTED_POWER(150, 4),
	//Feint T4
	FEIGNED_RETREAT(151, 4), EXPOSE_WEAKNESS(152, 4), COUNTER_ABILITY(153, 4),

	//Cleric T2
	符文复制(x6+4,3), 日耀射线(x6+5,3), 神圣感知(x6+6,3), BLESS(x6+7,3),
	//Cleric T3
	CLEANSE(169, 4),
	神圣净化(169,4), 轻量阅读(170,4),
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
	
	任督二脉(x8+26, 3+6+9+3+3),
	
	血爆巫术(x7+8,3),
	顶福精华(x7+9,4),强能处消(x7+10,4),
	物到巫术(x7+11,4),星火符刃(x7+12,4),高级血爆(x7+13,4),
	高级痛命(x7+14,4),高级死血(x7+15,4),高级吸血(x7+16,4),
	
	冰门高攻(x8+9,4),最佳防御(x8+10,4),
	
	轻便玉佩(x10+10,4),
	
	轻便护额(x15+10,4),
	
	死亡抗拒(x23+5,4),
	
	鉴定速度(x25),攻击强化(x25+1),防御强化(x25+2),
	杀伐果决(x25+4,3),技巧敏捷(x25+5,3),杀意感知(x25+6,3),随风而动(x25+7,3),
	;
	
	//region Buff
	
	public static class LethalMomentumTracker extends FlavourBuff{}
	public static class StrikingWaveTracker extends FlavourBuff{}
	public static class WandPreservationCounter extends CounterBuff{{revivePersists = true;}}
	
	public static class ProtectiveShadowsTracker extends Buff {
		float barrierInc = 0.5f;

		@Override
		public boolean act() {
			if(target instanceof Hero hero)
			//barrier every 2/1 turns, to a max of 3/5
			if (hero.天赋(Talent.体生匿影)&&target.invisible>0){
				barrierInc += 1;
				if (barrierInc >= 4){
					barrierInc = 0;
					if (hero.天赋(Talent.体生匿影)) {
						hero.回血(hero.最大生命(hero.天赋点数(Talent.体生匿影,0.04f)));
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
	
	public static class RejuvenatingStepsCooldown extends FlavourBuff{//复春步伐
		public int icon() { return BuffIndicator.TIME; }
		public void tintIcon(Image icon) { icon.hardlight(0f, 0.35f, 0.15f); }
		public float iconFadePercent() { return GameMath.gate(0, visualcooldown() / (20 ), 1); }
	}
	
	public static class RejuvenatingStepsFurrow extends CounterBuff{{revivePersists = true;}}
	
	
	public static class SpiritBladesTracker extends FlavourBuff{}
	
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
	
	public static class PreciseAssaultTracker extends FlavourBuff{
		{ type = buffType.POSITIVE; }
		public int icon() { return BuffIndicator.INVERT_MARK; }
		public void tintIcon(Image icon) { icon.hardlight(1f, 1f, 0.0f); }
		public float iconFadePercent() { return Math.max(0, 1f - (visualcooldown() / 5)); }
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

		if (talent==轻便斗篷&&hero.heroClass==HeroClass.盗贼){
			for (Item item : Dungeon.hero.belongings.backpack.items){
				if (item instanceof CloakOfShadows){
					if (!hero.belongings.lostInventory() || item.keptThroughLostInventory()) {
						((CloakOfShadows) item).activate(Dungeon.hero);
					}
				}
			}
		}

		if (talent==轻量阅读&&hero.heroClass==HeroClass.CLERIC){
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
	
	public static void 休息时(Hero hero, int pos){
		if (false){//不动如山
			Buff.施加(Dungeon.hero, HoldFast.class).pos = Dungeon.hero.pos;
		}
	}
	public static void 吃饭时(Hero hero, float foodVal ){
		hero.回血(Math.round(foodVal));
	
		if (hero.heroClass(HeroClass.学士)){
			if (hero.cooldown() > 0) {
				Buff.施加(hero, WarriorFoodImmunity.class, hero.cooldown());
			}
		}
		//法杖伤害
//			Buff.施加( hero, WandEmpower.class).set(5, 1);
//			ScrollOfRecharging.charge( hero );
		//加伤
//			//3 bonus physical damage for next 2/3 attacks
//			Buff.施加( hero, PhysicalEmpower.class).set(1, 1);
		//武技充能
//		if (hero.有天赋()){
//			if (hero.heroClass == HeroClass.DUELIST){
//				//0.67/1 charge for the duelist
//				Buff.施加( hero, Weapon.Charger.class ).gainCharge(hero.天赋点数(,0.5f));
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
//		if (wandChargeTurns > 0){
//			Buff.延长( hero, Recharging.class, wandChargeTurns );
//			ScrollOfRecharging.charge( hero );
//			SpellSprite.show(hero, SpellSprite.CHARGE);
//		}
//		if (artifactChargeTurns > 0){
//			ArtifactRecharge buff = Buff.施加( hero, ArtifactRecharge.class);
//			if (buff.left() < artifactChargeTurns){
//				buff.set(artifactChargeTurns).ignoreHornOfPlenty = foodSource instanceof HornOfPlenty;
//			}
//			ScrollOfRecharging.charge( hero );
//			SpellSprite.show(hero, SpellSprite.CHARGE, 0, 1, 1);
//		}
	}

	public static class WarriorFoodImmunity extends FlavourBuff{
		{ actPriority = HERO_PRIO+1; }
	}

	public static float 鉴定速度(Hero hero,Item item){
		float factor = 1f;
		factor+= hero.天赋点数(鉴定速度,2.5f);

		return factor;
	}

	public static void 喝药时(Hero hero, int cell, float factor ){

		if (false){//喝药加纹章盾
			// 6.5/10% of max HP
			int shieldToGive = Math.round( factor * hero.最大生命);
			Buff.施加(hero, Barrier.class).设置(shieldToGive);
		}
		if (false){//喝药加闪
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
		if (false){//阅读隐形
			// 3/5 turns of stealth
			Buff.施加(hero, Invisibility.class, factor);
			Sample.INSTANCE.play( Assets.Sounds.MELD );
		}
		if (hero.天赋(符文复制)&&Scroll.class.isAssignableFrom(cls)&&cls!=升级卷轴.class){
				
				if (Random.Int(99) < hero.天赋点数(符文复制,10)){
					Reflection.newInstance(cls).放背包();
					GLog.p(Messages.get(Talent.class,符文复制.name()+".refunded"));
				}
			}
	}

	public static void onRunestoneUsed( Hero hero, int pos, Class<?extends Item> cls ){
		if (hero.天赋(符文复制)&&Runestone.class.isAssignableFrom(cls)){
				//don't trigger on 1st intuition use
				if (cls.equals(感知符石.class) && hero.buff(感知符石.IntuitionUseTracker.class) != null){
					return;
				}
				
				if (Random.Int(99) < 1 + hero.天赋点数(符文复制)){
					Reflection.newInstance(cls).放背包();
					GLog.p(Messages.get(Talent.class,符文复制.name()+".refunded"));
				}
			}
	}

	public static void onArtifactUsed( Hero hero ){

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
		
		if (identify){
			if(ShardOfOblivion.passiveIDDisabled()){
				if(item instanceof Weapon){
					((Weapon)item).setIDReady();
				}else if(item instanceof Armor){
					((Armor)item).setIDReady();
				}else if(item instanceof Ring){
					((Ring)item).setIDReady();
				}
			}else{
				item.鉴定();
			}
		}
	}

	public static void 拾取时(Hero hero, Item item ){
	
	}

	public static int 伏击时(Hero hero, Char enemy, int dmg ){
		dmg++;
		if(hero.hasbuff(Invisibility.class)){
			dmg++;
		}
		if(enemy.第一次背袭) {
			dmg++;
		}
		if(enemy.第一次背袭){
			enemy.第一次背袭=false;
		}
		dmg*=传奇肛塞.伏击();
		return dmg;
	}
	public static int 攻击时(Hero hero, Char enemy, int dmg ){

		if (enemy instanceof Mob && ((Mob) enemy).surprisedBy(hero)){
			dmg=伏击时(hero,enemy,dmg);
		}
		
		//region 附带效果

		if (hero.buff(Talent.SpiritBladesTracker.class) != null
				&& Random.Int(10) < 3*hero.天赋点数(Talent.SPIRIT_BLADES)){
			灵能短弓 bow = hero.belongings.getItem(灵能短弓.class);
			if (bow != null) dmg = bow.攻击时( hero, enemy, dmg );
			hero.buff(Talent.SpiritBladesTracker.class).detach();
		}
		
		//endregion
		
		
		
		return dmg;
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
			switch(cls){
				default:
					Collections.addAll(tierTalents, 鉴定速度, 攻击强化, 防御强化);
					break;
				case 英雄:
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
		Collections.addAll(tierTalents,杀伐果决,技巧敏捷,杀意感知,随风而动);
		for (Talent talent : tierTalents){
			if (replacements.containsKey(talent)){
				talent = replacements.get(talent);
			}
			talents.get(1).put(talent, 0);
		}
		tierTalents.clear();
		
		//tier 3
		switch(cls){
			default:
			case 英雄:
				Collections.addAll(tierTalents,任督二脉);
				break;
			case WARRIOR:
				Collections.addAll(tierTalents,纹章荣耀,力大无穷);
				break;
			case MAGE:
				Collections.addAll(tierTalents,高级魔杖,SHIELD_BATTERY);
				break;
			case 盗贼:
				Collections.addAll(tierTalents,体生匿影,轻便斗篷);
				break;
			case HUNTRESS:
				Collections.addAll(tierTalents,弓箭强化,SEER_SHOT);
				break;
			case DUELIST:
				Collections.addAll(tierTalents,附魔打击,高阶配装);
				break;
			case CLERIC:
				Collections.addAll(tierTalents,神圣净化,轻量阅读);
				break;
			case 巫女:
				Collections.addAll(tierTalents,顶福精华,强能处消);
				break;
			case 重武:
				Collections.addAll(tierTalents,冰门高攻,最佳防御);
				break;
			case 道士:
				Collections.addAll(tierTalents,轻便玉佩);
				break;
			case 女忍:
				Collections.addAll(tierTalents,轻便护额);
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
			case 狂战士:
				Collections.addAll(tierTalents,洪荒之怒,血气旺盛,嗜血成性);
				break;
			case 角斗士:
				Collections.addAll(tierTalents,连战热忱,以战养战,连击强化);
				break;
			case 战斗法师:
				Collections.addAll(tierTalents,EMPOWERED_STRIKE,MYSTICAL_CHARGE,盈能附魔);
				break;
			case 术士:
				Collections.addAll(tierTalents, SOUL_EATER, SOUL_SIPHON, NECROMANCERS_MINIONS);
				break;
			case 刺客:
				Collections.addAll(tierTalents, ENHANCED_LETHALITY, ASSASSINS_REACH, BOUNTY_HUNTER);
				break;
			case 疾行者:
				Collections.addAll(tierTalents, EVASIVE_ARMOR, PROJECTILE_MOMENTUM, SPEEDY_STEALTH);
				break;
			case 狙击手:
				Collections.addAll(tierTalents,鹰眼远视,SHARED_ENCHANTMENT,狙击弱点);
				break;
			case 守望者:
				Collections.addAll(tierTalents,消受投掷,丛林法则,SHIELDING_DEW);
				break;
			case 勇士:
				Collections.addAll(tierTalents,双武格挡,TWIN_UPGRADES,复合损伤);
				break;
			case 武者:
				Collections.addAll(tierTalents, UNENCUMBERED_SPIRIT, MONASTIC_VIGOR, COMBINED_ENERGY);
				break;
			case PRIEST:
				Collections.addAll(tierTalents, HOLY_LANCE, HALLOWED_GROUND, MNEMONIC_PRAYER);
				break;
			case PALADIN:
				Collections.addAll(tierTalents, LAY_ON_HANDS, AURA_OF_PROTECTION, WALL_OF_LIGHT);
				break;
			case 神秘学者:
				Collections.addAll(tierTalents,物到巫术,星火符刃,高级血爆);
				break;
			case 黑魔导师:
				Collections.addAll(tierTalents, 高级痛命, 高级死血, 高级吸血);
				break;
			case 盾之勇者:
				Collections.addAll(tierTalents);
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
