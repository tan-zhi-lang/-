

package com.shatteredpixel.shatteredpixeldungeon.items.rings;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.EnhancedRings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.SpiritForm;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.ItemStatusHandler;
import com.shatteredpixel.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Ring extends KindofMisc {
	
	protected Buff buff;
	protected Class<? extends RingBuff> buffClass;

	private static final LinkedHashMap<String, Integer> gems = new LinkedHashMap<String, Integer>() {
		{
			put("garnet", 物品表.RING_GARNET);
			put("ruby", 物品表.RING_RUBY);
			put("topaz", 物品表.RING_TOPAZ);
			put("emerald", 物品表.RING_EMERALD);
			put("onyx", 物品表.RING_ONYX);
			put("opal", 物品表.RING_OPAL);
			put("tourmaline", 物品表.RING_TOURMALINE);
			put("sapphire", 物品表.RING_SAPPHIRE);
			put("amethyst", 物品表.RING_AMETHYST);
			put("quartz", 物品表.RING_QUARTZ);
			put("agate", 物品表.RING_AGATE);
			put("diamond", 物品表.RING_DIAMOND);
		}
	};
	
	private static ItemStatusHandler<Ring> handler;
	
	private String gem;
	
	//rings cannot be 'used' like other equipment, so they ID purely based on exp
	private float levelsToID = 1;
	
	@SuppressWarnings("unchecked")
	public static void initGems() {
		handler = new ItemStatusHandler<>( (Class<? extends Ring>[])Generator.Category.RING.classes, gems );
	}

	public static void clearGems(){
		handler = null;
	}
	
	public static void save( Bundle bundle ) {
		handler.save( bundle );
	}

	public static void saveSelectively( Bundle bundle, ArrayList<Item> items ) {
		handler.saveSelectively( bundle, items );
	}
	
	@SuppressWarnings("unchecked")
	public static void restore( Bundle bundle ) {
		handler = new ItemStatusHandler<>( (Class<? extends Ring>[])Generator.Category.RING.classes, gems, bundle );
	}
	
	public Ring() {
		super();
		reset();
	}

	//anonymous rings are always IDed, do not affect ID status,
	//and their sprite is replaced by a placeholder if they are not known,
	//useful for items that appear in UIs, or which are only spawned for their effects
	protected boolean anonymous = false;
	public void anonymize(){
		if (!isKnown()) image = 物品表.RING_HOLDER;
		anonymous = true;
	}
	
	public void reset() {
		super.reset();
		levelsToID = 1;
		if (handler != null && handler.contains(this)){
			image = handler.image(this);
			gem = handler.label(this);
		} else {
			image = 物品表.RING_GARNET;
			gem = "garnet";
		}
	}
	
	public void activate( Char ch ) {
		if (buff != null){
			buff.detach();
			buff = null;
		}
		buff = buff();
		buff.attachTo( ch );
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {

			if (buff != null) {
				buff.detach();
				buff = null;
			}

			return true;

		} else {

			return false;

		}
	}
	
	public boolean isKnown() {
		return anonymous || (handler != null && handler.isKnown( this ));
	}
	
	public void setKnown() {
		if (!anonymous) {
			if (!isKnown()) {
				handler.know(this);
			}

			if (Dungeon.hero.isAlive()) {
				Catalog.setSeen(getClass());
				Statistics.itemTypesDiscovered.add(getClass());
			}
		}
	}
	
	@Override
	public String name() {
		return isKnown() ? super.name() : Messages.get(Ring.class, gem);
	}

	@Override
	public String desc() {
		return isKnown() ? super.desc() : Messages.get(this, "unknown_desc");
	}
	
	@Override
	public String info(){

		//skip custom notes if anonymized and un-Ided
		String desc;
		if (anonymous && (handler == null || !handler.isKnown( this ))){
			desc = desc();

		} else {
			desc = super.info();
		}

		if (cursed && isEquipped( Dungeon.hero )) {
			desc += "\n\n" + Messages.get(Ring.class, "cursed_worn");
			
		} else if (cursed && cursedKnown) {
			desc += "\n\n" + Messages.get(Ring.class, "curse_known");
			
		} else if (!已鉴定() && cursedKnown){
			desc += "\n\n" + Messages.get(Ring.class, "not_cursed");
			
		}
		
		if (isKnown()) {
			desc += "\n\n" + statsInfo();
		}
		
		return desc;
	}
	
	protected String statsInfo(){
		return "";
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
	public Item 升级() {
		super.升级();
		
		if (Random.Int(3) == 0) {
			cursed = false;
		}
		
		return this;
	}
	
	@Override
	public boolean 已鉴定() {
		return super.已鉴定() && isKnown();
	}
	
	@Override
	public Item 鉴定(boolean byHero ) {
		setKnown();
		levelsToID = 0;
		return super.鉴定(byHero);
	}

	public void setIDReady(){
		levelsToID = -1;
	}

	public boolean readyToIdentify(){
		return !已鉴定() && levelsToID <= 0;
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
		
		//30% chance to be cursed
		if (Random.Float() < 0.3f) {
			cursed = true;
		}
		
		return this;
	}
	
	public static HashSet<Class<? extends Ring>> getKnown() {
		return handler.known();
	}
	
	public static HashSet<Class<? extends Ring>> getUnknown() {
		return handler.unknown();
	}
	
	public static boolean allKnown() {
		return handler != null && handler.known().size() == Generator.Category.RING.classes.length;
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
		return Math.round(金币()*0.15f);
	}
	protected RingBuff buff() {
		return null;
	}

	private static final String LEVELS_TO_ID    = "levels_to_ID";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEVELS_TO_ID, levelsToID );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		levelsToID = bundle.getFloat( LEVELS_TO_ID );
	}
	
	public void onHeroGainExp( float levelPercent, Hero hero ){
		if (已鉴定() || !isEquipped(hero)) return;
		levelPercent *= Talent.itemIDSpeedFactor(hero, this);
		//becomes IDed after 1 level
		levelsToID -= levelPercent;
		if (levelsToID <= 0){
			if (ShardOfOblivion.passiveIDDisabled()){
				if (levelsToID > -1){
					GLog.p(Messages.get(ShardOfOblivion.class, "identify_ready"), name());
				}
				setIDReady();
			} else {
				鉴定();
				GLog.p(Messages.get(Ring.class, "identify"));
				Badges.validateItemLevelAquired(this);
			}
		}
	}

	@Override
	public int 强化等级() {
		int lvl = super.强化等级();
		if (Dungeon.hero.buff(EnhancedRings.class) != null){
			lvl+=Dungeon.hero.天赋点数(Talent.ENHANCED_RINGS);
		}
		return lvl;
	}

	public static int getBonus(Char target, Class<?extends RingBuff> type){
		if (target.buff(MagicImmune.class) != null) return 0;
		int bonus = 0;
		for (RingBuff buff : target.buffs(type)) {
			bonus += buff.level();
		}
		SpiritForm.SpiritFormBuff spiritForm = target.buff(SpiritForm.SpiritFormBuff.class);
		if (bonus == 0
				&& spiritForm != null
				&& spiritForm.ring() != null
				&& spiritForm.ring().buffClass == type){
			bonus += spiritForm.ring().soloBonus();
		}
		return bonus;
	}

	public static int getBuffedBonus(Char target, Class<?extends RingBuff> type){
		if (target.buff(MagicImmune.class) != null) return 0;
		int bonus = 0;
		for (RingBuff buff : target.buffs(type)) {
			bonus += buff.buffedLvl();
		}
		if (bonus == 0
				&& target.buff(SpiritForm.SpiritFormBuff.class) != null
				&& target.buff(SpiritForm.SpiritFormBuff.class).ring() != null
				&& target.buff(SpiritForm.SpiritFormBuff.class).ring().buffClass == type){
			bonus += target.buff(SpiritForm.SpiritFormBuff.class).ring().soloBuffedBonus();
		}
		return bonus;
	}

	//just used for ring descriptions
	public int soloBonus(){
		if (cursed){
			return Math.min( 0, Ring.this.等级()-2 );
		} else {
			return Ring.this.等级()+1;
		}
	}

	//just used for ring descriptions
	public int soloBuffedBonus(){
		if (cursed){
			return Math.min( 0, Ring.this.强化等级()-2 );
		} else {
			return Ring.this.强化等级()+1;
		}
	}

	//just used for ring descriptions
	public int combinedBonus(Hero hero){
		int bonus = 0;
		if (hero.belongings.ring() != null && hero.belongings.ring().getClass() == getClass()){
			bonus += hero.belongings.ring().soloBonus();
		}
		if (hero.belongings.misc() != null && hero.belongings.misc().getClass() == getClass()){
			bonus += ((Ring)hero.belongings.misc()).soloBonus();
		}
		return bonus;
	}

	//just used for ring descriptions
	public int combinedBuffedBonus(Hero hero){
		int bonus = 0;
		if (hero.belongings.ring() != null && hero.belongings.ring().getClass() == getClass()){
			bonus += hero.belongings.ring().soloBuffedBonus();
		}
		if (hero.belongings.misc() != null && hero.belongings.misc().getClass() == getClass()){
			bonus += ((Ring)hero.belongings.misc()).soloBuffedBonus();
		}
		return bonus;
	}

	public class RingBuff extends Buff {

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
			spend( TICK );
			return true;
		}

		public int level(){
			return Ring.this.soloBonus();
		}

		public int buffedLvl(){
			return Ring.this.soloBuffedBonus();
		}

	}
}
