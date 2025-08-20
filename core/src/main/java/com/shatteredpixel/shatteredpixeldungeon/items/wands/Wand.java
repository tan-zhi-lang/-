

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ScrollEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SoulMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.DivineSense;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.GuidingLight;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.WondrousResin;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public abstract class Wand extends Item {

	public static final String AC_ZAP	= "ZAP";

	private static final float TIME_TO_ZAP	= 1f;
	
	public int maxCharges = initialCharges();
	public int curCharges = maxCharges;
	public float partialCharge = 0f;
	
	protected Charger charger;
	
	public boolean curChargeKnown = false;
	
	public boolean curseInfusionBonus = false;
	public int resinBonus = 0;

	private static final int USES_TO_ID = 10;
	public float usesLeftToID = USES_TO_ID;
	public float availableUsesToID = USES_TO_ID/2f;

	protected int collisionProperties = Ballistica.MAGIC_BOLT;
	
	{
		defaultAction = AC_ZAP;
		usesTargeting = true;
		bones = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (curCharges > 0 || !curChargeKnown) {
			actions.add( AC_ZAP );
		}

		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_ZAP )) {
			
			curUser = hero;
			curItem = this;
			GameScene.selectCell( zapper );
			
		}
	}

	@Override
	public int targetingPos(Hero user, int dst) {
		if (cursed && cursedKnown){
			return new Ballistica(user.pos, dst, Ballistica.MAGIC_BOLT).collisionPos;
		} else {
			return new Ballistica(user.pos, dst, collisionProperties).collisionPos;
		}
	}

	public abstract void onZap(Ballistica attack);

	public abstract void onHit(法师魔杖 staff, Char attacker, Char defender, int damage);

	//not affected by enchantment proc chance changers
	public static float procChanceMultiplier( Char attacker ){
		if (attacker.buff(Talent.EmpoweredStrikeTracker.class) != null){
			return 1f + ((Hero)attacker).天赋点数(Talent.EMPOWERED_STRIKE,0.5f);
		}
		return 1f;
	}

	public boolean tryToZap( Hero owner, int target ){

		if (owner.buff(WildMagic.WildMagicTracker.class) == null && owner.buff(MagicImmune.class) != null){
			GLog.w( Messages.get(this, "no_magic") );
			return false;
		}

		//if we're using wild magic, then assume we have charges
		if ( owner.buff(WildMagic.WildMagicTracker.class) != null || curCharges >= chargesPerCast()){
			return true;
		} else {
			GLog.w(Messages.get(this, "fizzles"));
			return false;
		}
	}

	@Override
	public boolean 放背包(Bag container ) {
		if (super.放背包( container )) {
			if (container.owner != null) {
				if (container instanceof MagicalHolster)
					charge( container.owner, ((MagicalHolster) container).HOLSTER_SCALE_FACTOR );
				else
					charge( container.owner );
			}
			return true;
		} else {
			return false;
		}
	}

	public void gainCharge( float amt ){
		gainCharge( amt, false );
	}

	public void gainCharge( float amt, boolean overcharge ){
		partialCharge += amt;
		while (partialCharge >= 1) {
			if (overcharge) curCharges = Math.min(maxCharges+(int)amt, curCharges+1);
			else curCharges = Math.min(maxCharges, curCharges+1);
			partialCharge--;
			updateQuickslot();
		}
	}
	
	public void charge( Char owner ) {
		if (charger == null) charger = new Charger();
		charger.attachTo( owner );
	}

	public void charge( Char owner, float chargeScaleFactor ){
		charge( owner );
		charger.setScaleFactor( chargeScaleFactor );
	}

	protected void wandProc(Char target, int chargesUsed){
		wandProc(target, 强化等级(), chargesUsed);
	}

	//TODO Consider externalizing char awareness buff
	protected static void wandProc(Char target, int wandLevel, int chargesUsed){
		if (Dungeon.hero.有天赋(Talent.ARCANE_VISION)) {
			int dur = Dungeon.hero.天赋点数(Talent.ARCANE_VISION,3);
			Buff.新增(Dungeon.hero, TalismanOfForesight.CharAwareness.class, dur).charID = target.id();
		}

		if (target != Dungeon.hero &&
				Dungeon.hero.subClass == HeroSubClass.WARLOCK &&
				//standard 1 - 0.92^x chance, plus 7%. Starts at 15%
				Random.Float() > (Math.pow(0.92f, (wandLevel*chargesUsed)+1) - 0.07f)){
			SoulMark.延长(target, SoulMark.class, SoulMark.DURATION + wandLevel);
		}

		if (Dungeon.hero.subClass == HeroSubClass.PRIEST && target.buff(GuidingLight.Illuminated.class) != null) {
			target.buff(GuidingLight.Illuminated.class).detach();
			target.受伤时(Dungeon.hero.等级 +5, GuidingLight.INSTANCE);
		}

		if (target.alignment != Char.Alignment.ALLY
				&& Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.SEARING_LIGHT)
				&& Dungeon.hero.buff(Talent.SearingLightCooldown.class) == null){
			Buff.施加(target, GuidingLight.Illuminated.class);
			Buff.施加(Dungeon.hero, Talent.SearingLightCooldown.class, 20f);
		}

		if (target.alignment != Char.Alignment.ALLY
				&& Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.SUNRAY)){
			// 15/25% chance
			if (Dungeon.hero.天赋概率(Talent.SUNRAY,15)){
				Buff.延长(target, Blindness.class, Dungeon.hero.天赋点数(Talent.SUNRAY,2));
			}
		}
	}

	@Override
	public void onDetach( ) {
		stopCharging();
	}

	public void stopCharging() {
		if (charger != null) {
			charger.detach();
			charger = null;
		}
	}
	
	public void 等级(int value) {
		super.等级( value );
		updateLevel();
	}
	
	@Override
	public Item 鉴定(boolean byHero ) {
		
		curChargeKnown = true;
		super.鉴定(byHero);
		
		updateQuickslot();
		
		return this;
	}

	public void setIDReady(){
		usesLeftToID = -1;
	}

	public boolean readyToIdentify(){
		return !已鉴定() && usesLeftToID <= 0;
	}
	
	public void onHeroGainExp( float levelPercent, Hero hero ){
		levelPercent *= Talent.itemIDSpeedFactor(hero, this);
		if (!已鉴定() && availableUsesToID <= USES_TO_ID/2f) {
			//gains enough uses to ID over 1 level
			availableUsesToID = Math.min(USES_TO_ID/2f, availableUsesToID + levelPercent * USES_TO_ID/2f);
		}
	}

	@Override
	public String info() {
		String desc = super.info();

		desc += "\n\n" + statsDesc();

		if (resinBonus == 1){
			desc += "\n\n" + Messages.get(Wand.class, "resin_one");
		} else if (resinBonus > 1){
			desc += "\n\n" + Messages.get(Wand.class, "resin_many", resinBonus);
		}

		if (cursed && cursedKnown) {
			desc += "\n\n" + Messages.get(Wand.class, "cursed");
		} else if (!已鉴定() && cursedKnown){
			desc += "\n\n" + Messages.get(Wand.class, "not_cursed");
		}

		if (Dungeon.hero != null && Dungeon.hero.subClass == HeroSubClass.BATTLEMAGE){
			desc += "\n\n" + Messages.get(this, "bmage_desc");
		}

		return desc;
	}

	public String statsDesc(){
		return Messages.get(this, "stats_desc");
	}

	public String upgradeStat1(int level){
		return null;
	}

	public String upgradeStat2(int level){
		return null;
	}

	public String upgradeStat3(int level){
		return null;
	}
	
	@Override
	public boolean 已鉴定() {
		return super.已鉴定() && curChargeKnown;
	}
	
	@Override
	public String status() {
		if (levelKnown) {
			return (curChargeKnown ? curCharges : "?") + "/" + maxCharges;
		} else {
			return null;
		}
	}
	
	@Override
	public int 等级() {
		if (!cursed && curseInfusionBonus){
			curseInfusionBonus = false;
			updateLevel();
		}
		int level = super.等级();
		if (curseInfusionBonus) level += 1 + level/6;
		level += resinBonus;
		return level;
	}
	
	@Override
	public Item 升级() {

		super.升级();

		if (Random.Int(3) == 0) {
			cursed = false;
		}

		if (resinBonus > 0){
			resinBonus--;
		}

		updateLevel();
		curCharges = Math.min( curCharges + 1, maxCharges );
		updateQuickslot();
		
		return this;
	}
	
	@Override
	public Item 降级() {
		super.降级();
		
		updateLevel();
		updateQuickslot();
		
		return this;
	}

	@Override
	public int 强化等级() {
		int lvl = super.强化等级();

		if (charger != null && charger.target != null) {

			//inside staff, still need to apply degradation
			if (charger.target == Dungeon.hero
					&& !Dungeon.hero.belongings.contains(this)
					&& Dungeon.hero.buff( Degrade.class ) != null){
				lvl = Degrade.reduceLevel(lvl);
			}

			if (charger.target.buff(ScrollEmpower.class) != null){
				lvl += 2;
			}

			if (charger.target.buff(WildMagic.WildMagicTracker.class) != null){
				int bonus = 4 + ((Hero)charger.target).天赋点数(Talent.WILD_POWER);
				if (Random.Int(2) == 0) bonus++;
				bonus /= 2; // +2/+2.5/+3/+3.5/+4 at 0/1/2/3/4 talent points

				int maxBonusLevel = 3 + ((Hero)charger.target).天赋点数(Talent.WILD_POWER);
				if (lvl < maxBonusLevel) {
					lvl = Math.min(lvl + bonus, maxBonusLevel);
				}
			}

			WandOfMagicMissile.MagicCharge buff = charger.target.buff(WandOfMagicMissile.MagicCharge.class);
			if (buff != null && buff.level() > lvl){
				return buff.level();
			}
		}
		return lvl;
	}

	public void updateLevel() {
		maxCharges = Math.min( initialCharges() + 等级(), 10+(curUser==null?0:curUser.天赋点数(Talent.DESPERATE_POWER)));
		curCharges = Math.min( curCharges+1, maxCharges );
	}
	
	public int initialCharges() {
		return 2;
	}

	protected int chargesPerCast() {
		return 1;
	}
	
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar( curUser.sprite.parent,
				MagicMissile.MAGIC_MISSILE,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}

	public void staffFx( 法师魔杖.StaffParticle particle ){
		particle.color(0xFFFFFF); particle.am = 0.3f;
		particle.setLifespan( 1f);
		particle.speed.polar( Random.Float(PointF.PI2), 2f );
		particle.setSize( 1f, 2f );
		particle.radiateXY(0.5f);
	}

	public void wandUsed() {
		if (!已鉴定()) {
			float uses = Math.min( availableUsesToID, Talent.itemIDSpeedFactor(Dungeon.hero, this) );
			availableUsesToID -= uses;
			usesLeftToID -= uses;
			if (usesLeftToID <= 0 || Dungeon.hero.天赋点数(Talent.SCHOLARS_INTUITION) == 1) {
				if (ShardOfOblivion.passiveIDDisabled()){
					if (usesLeftToID > -1){
						GLog.p(Messages.get(ShardOfOblivion.class, "identify_ready"), name());
					}
					setIDReady();
				} else {
					鉴定();
					GLog.p(Messages.get(Wand.class, "identify"));
					Badges.validateItemLevelAquired(this);
				}
			}
			if (ShardOfOblivion.passiveIDDisabled()){
				Buff.延长(curUser, ShardOfOblivion.WandUseTracker.class, 50f);
			}
		}

		//inside staff
		if (charger != null && charger.target == Dungeon.hero && !Dungeon.hero.belongings.contains(this)){
			if (Dungeon.hero.有天赋(Talent.EXCESS_CHARGE) && curCharges >= maxCharges){
				int shieldToGive = 强化等级()*Dungeon.hero.天赋生命力(Talent.EXCESS_CHARGE,0.6f);
				Buff.施加(Dungeon.hero, Barrier.class).设置(shieldToGive);
				Dungeon.hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldToGive), FloatingText.SHIELDING);
			}
		}
		if (Dungeon.hero.有天赋(Talent.保护屏障)) {
			int shieldToGive = Dungeon.hero.天赋生命力(Talent.保护屏障, 0.4f);
			if (Dungeon.hero.heroClass == HeroClass.MAGE ) {
				Buff.施加(Dungeon.hero, Barrier.class).设置(shieldToGive);
				Dungeon.hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldToGive), FloatingText.SHIELDING);

				//metamorphed. Triggers if wand is highest level hero has
			} else {
				final Wand curWand;
				curWand = (Wand) Wand.curItem;
				boolean highest = true;
				for (Item i : Dungeon.hero.belongings.getAllItems(Wand.class)) {
					if (i.等级() > curWand.等级()) {
						highest = false;
					}
				}
				if (highest) {
					Buff.施加(Dungeon.hero, Barrier.class).设置(shieldToGive);
					Dungeon.hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldToGive), FloatingText.SHIELDING);
				}
			}
		}
		
		curCharges -= cursed ? 1 : chargesPerCast();

		//remove magic charge at a higher priority, if we are benefiting from it are and not the
		//wand that just applied it
		WandOfMagicMissile.MagicCharge buff = curUser.buff(WandOfMagicMissile.MagicCharge.class);
		if (buff != null
				&& buff.wandJustApplied() != this
				&& buff.level() == 强化等级()
				&& 强化等级() > super.强化等级()){
			buff.detach();
		} else {
			ScrollEmpower empower = curUser.buff(ScrollEmpower.class);
			if (empower != null){
				empower.use();
			}
		}

		//If hero owns wand but it isn't in belongings it must be in the staff
		if (Dungeon.hero.有天赋(Talent.EMPOWERED_STRIKE)
				&& charger != null && charger.target == Dungeon.hero
				&& !Dungeon.hero.belongings.contains(this)){

			Buff.延长(Dungeon.hero, Talent.EmpoweredStrikeTracker.class, 10f);
		}

		if (Dungeon.hero.有天赋(Talent.LINGERING_MAGIC)
				&& charger != null && charger.target == Dungeon.hero){

			Buff.延长(Dungeon.hero, Talent.LingeringMagicTracker.class, 5f);
		}

		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.DIVINE_SENSE)){
			Buff.延长(Dungeon.hero, DivineSense.DivineSenseTracker.class, Dungeon.hero.cooldown()+1);
		}

		// 10/20/30%
		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.CLEANSE)
				&& Dungeon.hero.天赋概率(Talent.CLEANSE,10)){
			boolean removed = false;
			for (Buff b : Dungeon.hero.buffs()) {
				if (b.type == Buff.buffType.NEGATIVE
						&& !(b instanceof LostInventory)) {
					b.detach();
					removed = true;
				}
			}
			if (removed) new Flare( 6, 32 ).color(0xFF4CD2, true).show( Dungeon.hero.sprite, 2f );
		}

		Invisibility.dispel();
		updateQuickslot();

		curUser.spendAndNext( TIME_TO_ZAP );
	}
	
	@Override
	public Item random() {
		//+0: 66.67% (2/3)
		//+1: 26.67% (4/15)
		//+2: 6.67%  (1/15)
		int n = 0;
		if (Random.Int(3) == 0) {
			n++;
			if (Random.Int(5) == 0){
				n++;
			}
		}
		等级(n);
		curCharges += n;
		
		//30% chance to be cursed
		if (Random.Float() < 0.3f) {
			cursed = true;
		}

		return this;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		if (resinBonus == 0) return null;

		return new ItemSprite.Glowing(0xFFFFFF, 1f/(float)resinBonus);
	}

	@Override
	public int 金币() {
		int price = 75;
		if (cursed && cursedKnown) {
			price /= 2;
		}
		if (levelKnown) {
			if (等级() > 0) {
				price *= (等级() + 1);
			} else if (等级() < 0) {
				price /= (1 - 等级());
			}
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
	private static final String USES_LEFT_TO_ID     = "uses_left_to_id";
	private static final String AVAILABLE_USES      = "available_uses";
	private static final String CUR_CHARGES         = "curCharges";
	private static final String CUR_CHARGE_KNOWN    = "curChargeKnown";
	private static final String PARTIALCHARGE       = "partialCharge";
	private static final String CURSE_INFUSION_BONUS= "curse_infusion_bonus";
	private static final String RESIN_BONUS         = "resin_bonus";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( AVAILABLE_USES, availableUsesToID );
		bundle.put( CUR_CHARGES, curCharges );
		bundle.put( CUR_CHARGE_KNOWN, curChargeKnown );
		bundle.put( PARTIALCHARGE , partialCharge );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( RESIN_BONUS, resinBonus );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		availableUsesToID = bundle.getInt( AVAILABLE_USES );
		curseInfusionBonus = bundle.getBoolean(CURSE_INFUSION_BONUS);
		resinBonus = bundle.getInt(RESIN_BONUS);

		updateLevel();

		curCharges = bundle.getInt( CUR_CHARGES );
		curChargeKnown = bundle.getBoolean( CUR_CHARGE_KNOWN );
		partialCharge = bundle.getFloat( PARTIALCHARGE );
	}
	
	@Override
	public void reset() {
		super.reset();
		usesLeftToID = USES_TO_ID;
		availableUsesToID = USES_TO_ID/2f;
	}

	public int collisionProperties(int target){
		if (cursed)     return Ballistica.MAGIC_BOLT;
		else            return collisionProperties;
	}

	public static class PlaceHolder extends Wand {

		{
			image = 物品表.WAND_HOLDER;
		}

		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Wand;
		}

		@Override
		public void onZap(Ballistica attack) {}
		public void onHit(法师魔杖 staff, Char attacker, Char defender, int damage) {}

		@Override
		public String info() {
			return "";
		}
	}
	
	protected static CellSelector.Listener zapper = new  CellSelector.Listener() {
		
		@Override
		public void onSelect( Integer target ) {
			
			if (target != null) {
				
				//FIXME this safety check shouldn't be necessary
				//it would be better to eliminate the curItem static variable.
				final Wand curWand;
				if (curItem instanceof Wand) {
					curWand = (Wand) Wand.curItem;
				} else {
					return;
				}

				final Ballistica shot = new Ballistica( curUser.pos, target, curWand.collisionProperties(target));
				int cell = shot.collisionPos;
				
				if (target == curUser.pos || cell == curUser.pos) {
					if (target == curUser.pos&&(curUser.有天赋(Talent.SHIELD_BATTERY)||curUser.有天赋(Talent.饱腹法术))){

						if (curUser.buff(MagicImmune.class) != null){
							GLog.w( Messages.get(Wand.class, "no_magic") );
							return;
						}

						if (curWand.curCharges == 0){
							GLog.w( Messages.get(Wand.class, "fizzles") );
							return;
						}
						if(curUser.有天赋(Talent.SHIELD_BATTERY)) {
							int shield = curUser.最大生命(curUser.天赋点数(Talent.SHIELD_BATTERY, 0.015f)) * curWand.curCharges;
							Buff.施加(curUser, Barrier.class).设置(shield);
							curUser.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shield), FloatingText.SHIELDING);
							Sample.INSTANCE.play(Assets.Sounds.CHARGEUP);
						}
						if(curUser.有天赋(Talent.饱腹法术)) {
							float shield = curUser.天赋点数(Talent.饱腹法术, 3) * curWand.curCharges;
							Buff.施加(curUser, Hunger.class).吃饭(shield);

							curUser.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(Math.round(shield)), FloatingText.HUNGER);
							Sample.INSTANCE.play(Assets.Sounds.EAT);
						}

						curWand.curCharges = 0;
						curUser.sprite.operate(curUser.pos);
						ScrollOfRecharging.charge(curUser);
						updateQuickslot();
						curUser.spendAndNext(Actor.TICK);
						return;
					}
					GLog.i( Messages.get(Wand.class, "self_target") );
					return;
				}

				curUser.sprite.zap(cell);

				//attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
				if (Actor.findChar(target) != null)
					QuickSlotButton.target(Actor.findChar(target));
				else
					QuickSlotButton.target(Actor.findChar(cell));
				
				if (curWand.tryToZap(curUser, target)) {
					
					curUser.busy();

					//backup barrier logic
					//This triggers before the wand zap, mostly so the barrier helps vs skeletons
//					if (curWand.curCharges == curWand.chargesPerCast()
//							&& curWand.charger != null && curWand.charger.target == curUser){
//
//					}
					
					if (curWand.cursed){
						if (!curWand.cursedKnown){
							GLog.n(Messages.get(Wand.class, "curse_discover", curWand.name()));
						}
						CursedWand.cursedZap(curWand,
								curUser,
								new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT),
								new Callback() {
									@Override
									public void call() {
										curWand.wandUsed();
									}
								});
					} else {
						curWand.fx(shot, new Callback() {
							public void call() {
								curWand.onZap(shot);
								if (Random.Float() < WondrousResin.extraCurseEffectChance()){
									WondrousResin.forcePositive = true;
									CursedWand.cursedZap(curWand,
											curUser,
											new Ballistica(curUser.pos, target, Ballistica.MAGIC_BOLT), new Callback() {
												@Override
												public void call() {
													WondrousResin.forcePositive = false;
													curWand.wandUsed();
												}
											});
								} else {
									curWand.wandUsed();
								}
							}
						});

					}
					curWand.cursedKnown = true;
					
				}
				
			}
		}
		
		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};
	
	public class Charger extends Buff {
		
		private static final float BASE_CHARGE_DELAY = 10f;
		private static final float SCALING_CHARGE_ADDITION = 40f;
		private static final float NORMAL_SCALE_FACTOR = 0.875f;

		private static final float CHARGE_BUFF_BONUS = 0.25f;

		float scalingFactor = NORMAL_SCALE_FACTOR;

		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				//if we're loading in and the hero has partially spent a turn, delay for 1 turn
				if (target instanceof Hero && Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
					spend(TICK);
				}
				return true;
			}
			return false;
		}
		
		@Override
		public boolean act() {
			if (curCharges < maxCharges && target.buff(MagicImmune.class) == null)
				recharge();
			
			while (partialCharge >= 1 && curCharges < maxCharges) {
				partialCharge--;
				curCharges++;
				updateQuickslot();
			}
			
			if (curCharges == maxCharges){
				partialCharge = 0;
			}
			
			spend( TICK );
			
			return true;
		}

		private void recharge(){
			int missingCharges = maxCharges - curCharges;
			missingCharges = Math.max(0, missingCharges);

			float turnsToCharge = (float) (BASE_CHARGE_DELAY
					+ (SCALING_CHARGE_ADDITION * Math.pow(scalingFactor, missingCharges)));

			if (再生.regenOn())
				partialCharge += (1f/turnsToCharge) * 能量之戒.wandChargeMultiplier(target)*(1+Dungeon.hero.天赋点数(Talent.强能处消,0.25f));

			for (Recharging bonus : target.buffs(Recharging.class)){
				if (bonus != null && bonus.remainder() > 0f) {
					partialCharge += CHARGE_BUFF_BONUS * bonus.remainder();
				}
			}
		}
		
		public Wand wand(){
			return Wand.this;
		}

		public void gainCharge(float charge){
			if (curCharges < maxCharges) {
				partialCharge += charge;
				while (partialCharge >= 1f) {
					curCharges++;
					partialCharge--;
				}
				if (curCharges >= maxCharges){
					partialCharge = 0;
					curCharges = maxCharges;
				}
				updateQuickslot();
			}
		}

		private void setScaleFactor(float value){
			this.scalingFactor = value;
		}
	}
}
