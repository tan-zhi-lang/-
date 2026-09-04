

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.丛生;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.代谢;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.元法;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.冰心;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.同位素;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.守护;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.晦暗;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.涌流;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.磐石;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.粘稠;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.臃肿;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.荆棘;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.虐待;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.轻便;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.迅捷;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.迷彩;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.魅惑;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ParchmentScrap;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.荣誉纹章;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Armor extends EquipableItem {

	protected static final String AC_DETACH       = "DETACH";
	protected String 换甲 = Assets.Sounds.布甲;
	public float 防御= 1f;

	public float DR(){
		Char c=new Rat();
		return augment.defenseFactor(
				(
					((最小防御()+最大防御())>0?(最小防御()+最大防御())/2f:0)
				)
								   )
				;
	}
	public enum Augment {
		DEFENSE (1, 1.15f,1),
		SPEED (1, 1,1.08f),
		EVASION (1.22f , 1,1),
		NONE	(1,1,1);
		
		private float evasionFactor;
		private float defenceFactor;
		private float speedFactor;
		
		Augment(float eva, float df, float sp){
			evasionFactor = eva;
			defenceFactor = df;
			speedFactor = sp;
		}
		
		public float evasionFactor(float evasion){
			return evasion*evasionFactor;
		}
		
		public float defenseFactor(float defense){
			return defense*defenceFactor;
		}
		public float speedFactor(float speed){
			return speed*speedFactor;
		}
	}
	
	public Augment augment = Augment.NONE;
	
	public Glyph glyph;
	public boolean curseInfusionBonus = false;
	public boolean 神力 = false;
	
	public 荣誉纹章 荣誉纹章;
	
	public int tier;

	public int tier(){
		return Math.min(5,tier+(专属&&Dungeon.符文("随行圣衣")?4:0));
	}
	private static final int USES_TO_ID = 10;
	public float usesLeftToID = USES_TO_ID;
	
	public Armor( int tier ) {
		this.tier = tier;
	}
	
	private static final String USES_LEFT_TO_ID = "uses_left_to_id";
	private static final String GLYPH			= "glyph";
	private static final String CURSE_INFUSION_BONUS = "curse_infusion_bonus";
	private static final String 神力x = "神力";
	private static final String 荣誉纹章x = "荣誉纹章";
	private static final String AUGMENT			= "augment";
	private static final String TIER			= "tier";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( GLYPH, glyph );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( 神力x, 神力 );
		bundle.put(荣誉纹章x,荣誉纹章);
		bundle.put( AUGMENT, augment);
		bundle.put( TIER, tier);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		inscribe((Glyph) bundle.get(GLYPH));
		curseInfusionBonus = bundle.getBoolean( CURSE_INFUSION_BONUS );
		神力 = bundle.getBoolean( 神力x );
		荣誉纹章= (荣誉纹章)bundle.get(荣誉纹章x);
		tier= bundle.getInt(TIER);

		augment = bundle.getEnum(AUGMENT, Augment.class);
	}

	@Override
	public void reset() {
		super.reset();
		usesLeftToID = USES_TO_ID;
		//armor can be kept in bones between runs, the seal cannot.
		荣誉纹章= null;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if (荣誉纹章!=null) actions.add(AC_DETACH);
		boolean b=false;
		if(this instanceof 背心&&hero.heroClass(HeroClass.灵猫)){
			b=true;
		}else if(this instanceof 披风&&hero.heroClass(HeroClass.鼠弟)){
			b=true;
		}else if(hero.heroClass(HeroClass.凌云)){
			b=true;
		}
		if(b)
		actions.remove(AC_EQUIP);
		return actions;
	}
	// 在类中定义静态映射（只需定义一次）
	private static final Map<Class<?>, HeroClass> ARMOR_RESTRICTIONS = new HashMap<>();
	static {
		ARMOR_RESTRICTIONS.put(铠甲.class, HeroClass.WARRIOR);
		ARMOR_RESTRICTIONS.put(法袍.class, HeroClass.MAGE);
		ARMOR_RESTRICTIONS.put(风衣.class, HeroClass.盗贼);
		ARMOR_RESTRICTIONS.put(披风.class, HeroClass.HUNTRESS);
		ARMOR_RESTRICTIONS.put(胸铠.class, HeroClass.DUELIST);
		ARMOR_RESTRICTIONS.put(祭服.class, HeroClass.CLERIC);
		ARMOR_RESTRICTIONS.put(巫服.class, HeroClass.巫女);
		ARMOR_RESTRICTIONS.put(武服.class, HeroClass.镜魔);
		ARMOR_RESTRICTIONS.put(道袍.class, HeroClass.道士);
		ARMOR_RESTRICTIONS.put(战甲.class, HeroClass.近卫);
		ARMOR_RESTRICTIONS.put(忍服.class, HeroClass.女忍);
		ARMOR_RESTRICTIONS.put(能袍.class, HeroClass.戒老);
		ARMOR_RESTRICTIONS.put(勇装.class, HeroClass.逐姝);
		ARMOR_RESTRICTIONS.put(连裙.class, HeroClass.罗兰);
		ARMOR_RESTRICTIONS.put(训服.class, HeroClass.学士);
		ARMOR_RESTRICTIONS.put(背心.class, HeroClass.灵猫);
		ARMOR_RESTRICTIONS.put(魔披.class, HeroClass.鼠弟);
	}

	@Override
	public void execute(Hero hero, String action) {


		if (action.equals(AC_EQUIP)){

			// 在装备方法中：
			for (
					Map.Entry<Class<?>, HeroClass> entry : ARMOR_RESTRICTIONS.entrySet()) {
				if (entry.getKey().isInstance(this)) {
					if (!hero.真heroClass(entry.getValue())) {
						GLog.橙("你无法装备他人英雄的防具");
						return;
					}
					break;  // 匹配到对应防具，检查结束
				}
			}

			String primaryName = Messages.titleCase(hero.belongings.armor != null ? hero.belongings.armor.trueName() : Messages.get(Armor.class,"empty"));
			String secondaryName = Messages.titleCase(hero.belongings.armor2 != null ? hero.belongings.armor2.trueName() : Messages.get(Armor.class, "empty"));
			if (primaryName.length() > 18) primaryName = primaryName.substring(0, 15) + "...";
			if (secondaryName.length() > 18) secondaryName = secondaryName.substring(0, 15) + "...";
			GameScene.show(new WndOptions(
					new ItemSprite(this),
					Messages.titleCase(name()),
					Messages.get(Armor.class, "which_equip_msg"),
					Messages.get(Armor.class, "which_equip_primary", primaryName),
					Messages.get(Armor.class, "which_equip_secondary", secondaryName)
			){
				@Override
				protected void onSelect(int index) {
					super.onSelect(index);
					if (index == 0 || index == 1){

						slotOfUnequipped = -1;
						if (index == 0) {
								doEquip(hero);
						} else {

							if(hero.belongings.armor!=null){
								equipSecondary(hero);
							}else
								doEquip(hero);
						}
						updateQuickslot();
					}
				}

				@Override
				protected boolean enabled(int index) {
					if(index ==0){

					}
					else
					{
						if(hero.belongings.armor==null)return false;
					}
					return true;
				}
			});
		}
		else if (action.equals(AC_DETACH)&&荣誉纹章!=null){
			荣誉纹章 detaching = detachSeal();
			GLog.白(Messages.get(Armor.class,"detach_seal"));
			hero.sprite.operate();
			detaching.放背包();
			updateQuickslot();
		}else{

			super.execute(hero, action);
		}
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {

		boolean second = hero.belongings.armor2 == this;
		if (second){
			//do this first so that the item can go to a full inventory
			hero.belongings.armor2 = null;
		}
			if (super.doUnequip( hero, collect, single )) {

				if(首次装备){
					usesLeftToID-=Talent.鉴定速度(hero,this);
				}
//				hero.belongings.armor = null;

				//必须在armor置空后再刷新贴图，否则tier()仍读到旧护甲导致外观不更新
				if (!second){
					hero.belongings.armor= null;
				}
				((HeroSprite)hero.sprite).updateArmor();
				return true;

			} else {

				if (second){
					hero.belongings.armor2 = (Armor)this;
				}
				return false;

			}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null &&
			   (hero.belongings.armor1() == this ||hero.belongings.armor2()==this);
	}

	public boolean equipSecondary( Hero hero ){


		boolean wasInInv = hero.belongings.contains(this);
		detachAll( hero.belongings.backpack );

		if (hero.belongings.armor2 == null
			|| hero.belongings.armor2.doUnequip( hero, true )) {

			hero.belongings.armor2 = (Armor)this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.红(Messages.get(Armor.class,"equip_cursed"));
				Dungeon.hero.sprite.哭泣();
			}

			hero.spendAndNext( timeToEquip(hero) );
			return true;

		} else {

			放背包( hero.belongings.backpack );
			return false;
		}
	}

	@Override
	public boolean doEquip( Hero hero ) {



		detachAll(hero.belongings.backpack);
//		Armor oldArmor = hero.belongings.armor;
		if (hero.belongings.armor == null
			|| hero.belongings.armor.doUnequip( hero, true )) {

			hero.belongings.armor = this;

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.红(Messages.get(Armor.class,"equip_cursed"));
				Dungeon.hero.sprite.哭泣();
			}

			((HeroSprite)hero.sprite).updateArmor();
			activate(hero);
			Talent.装备时(hero, this);
//			hero.spend( timeToEquip( hero ) );
			hero.spendAndNext( timeToEquip(hero) );

//			if (Dungeon.hero.heroClass(HeroClass.WARRIOR) && checkSeal() == null){
//				荣誉纹章 seal =oldArmor!=null ? oldArmor.checkSeal() : null;
//				if (seal != null && (!cursed || (seal.getGlyph() != null && seal.getGlyph().curse()))){
//
//					GameScene.show(new WndOptions(new ItemSprite(物品表.荣誉纹章),
//												  Messages.titleCase(seal.title()),
//												  Messages.get(Armor.class, "seal_transfer"),
//												  Messages.get(Armor.class, "seal_transfer_yes"),
//												  Messages.get(Armor.class, "seal_transfer_no")){
//						@Override
//						protected void onSelect(int index) {
//							super.onSelect(index);
//							if (index == 0){
//								seal.affixToArmor(Armor.this, oldArmor);
//								updateQuickslot();
//							}
//							super.hide();
//						}
//
//						@Override
//						public void hide() {
//							//do nothing, must press button
//						}
//					});
//				} else {
//					hero.next();
//				}
//			} else {
//				hero.next();
//			}
			return true;

		} else {

			放背包( hero.belongings.backpack );
			return false;

		}
	}

	@Override
	public boolean 放背包(Bag container) {
		if(super.放背包(container)){
			if(首次拾取){
				usesLeftToID -= Talent.鉴定速度(Dungeon.hero,this);
			}
			if (container.owner != null) {
				activate( container.owner);
			}
			if (Dungeon.hero() && Dungeon.hero.isAlive() && 已鉴定() && glyph != null){
				Catalog.setSeen(glyph.getClass());
				Statistics.itemTypesDiscovered.add(glyph.getClass());
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Item 鉴定(boolean byHero) {
		if (glyph != null && byHero && Dungeon.hero() && Dungeon.hero.isAlive()){
			Catalog.setSeen(glyph.getClass());
			Statistics.itemTypesDiscovered.add(glyph.getClass());
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
	protected float timeToEquip( Hero hero ) {
		换甲();
		return hero.攻击延迟()*2f;
	}
	public void 换甲(){
		Sample.INSTANCE.play(换甲);
	}

	@Override
	public void activate(Char ch) {
//		if (破损纹章 != null) Buff.施加(ch, 破损纹章.WarriorShield.class).setArmor(this);
	}
	@Override
	public int 强化等级(){
		//only the hero can be affected by Degradation
		if (Dungeon.hero() && Dungeon.hero.buff( Degrade.class ) != null
				&& (isEquipped( Dungeon.hero ) || Dungeon.hero.belongings.contains( this ))) {
			return Degrade.reduceLevel(等级()+(荣誉纹章!=null?
													   荣誉纹章.等级():0));
		} else {
			return 等级()+(荣誉纹章!=null?
								   荣誉纹章.等级():0);
		}
	}
	public void affixSeal(荣誉纹章 seal){
		this.荣誉纹章= seal;
		if (seal.getGlyph() != null){
			inscribe(seal.getGlyph());
		}
//		if (isEquipped(Dungeon.hero)){
//			Buff.施加(Dungeon.hero, 破损纹章.WarriorShield.class).setArmor(this);
//		}
	}

	public 荣誉纹章 detachSeal(){
		if (荣誉纹章!=null){

//			if (isEquipped(Dungeon.hero)) {
//				破损纹章.WarriorShield sealBuff = Dungeon.hero.buff(破损纹章.WarriorShield.class);
//				if (sealBuff != null) sealBuff.setArmor(null);
//			}

			荣誉纹章 detaching =荣誉纹章;

			if(真等级()>0)
			荣誉纹章.等级(Item.转移等级(this,荣誉纹章.最大等级(),荣誉纹章.等级()));

			荣誉纹章= null;
			if (detaching.canTransferGlyph()){
				inscribe(null);
			} else {
				detaching.setGlyph(null);
			}
			return detaching;
		} else {
			return null;
		}
	}

	public 荣誉纹章 checkSeal(){
		return 荣誉纹章;
	}

	public final float 最大防御(){
		return 最大防御(强化等级());
	}

	public float 最大防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return augment.defenseFactor(tier() + lvl)*防御;
		}

		return augment.defenseFactor(tier()*(1+1+lvl))*防御;
	}

	public final float 最小防御(){
		return 最小防御(强化等级());
	}

	public float 最小防御(int lvl){
//		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
//			return 0;
//		}
		return augment.defenseFactor(tier()+lvl)*防御;
	}

	//This exists so we can test what a char's base evasion would be without armor affecting it
	//more ugly static vars yaaay~
	public static boolean testingNoArmDefSkill = false;
	
	public float evasionFactor( Char owner, float evasion ){
		if (testingNoArmDefSkill) return evasion;
		
		if (hasGlyph(磐石.class,owner)){
			return 0;
		}
		
		if (owner instanceof Hero hero){
			float aEnc = 力量() - hero.力量();
			if (aEnc > 0&&!hero.heroClass(HeroClass.重武)) evasion /= Math.pow(1.5, aEnc);
			if (aEnc < 0) evasion *= 1-aEnc*owner.属性增幅()/2f;
			
		}

		if(cursed)evasion*=0.7f;
		if(hasGlyph(轻便.class))
		evasion*=1.2f*Glyph.genericProcChanceMultiplier(owner)*owner.glyphLevel(轻便.class);
		return augment.evasionFactor(evasion);
	}
	
	public float speedFactor( Char owner, float speed ){

		if (owner instanceof Hero hero&&!hero.heroClass(HeroClass.重武)) {
			float aEnc = 力量() - hero.力量();
			if (aEnc > 0) speed /= Math.pow(1.2, aEnc);
			if (aEnc < 0) speed *= 1-aEnc*owner.属性增幅()/2f;
		}
		if(cursed)speed*=0.7f;
		return augment.speedFactor(speed);
		
	}
	
	@Override
	public int 等级() {
		int level = super.等级();
		//TODO warrior's seal upgrade should probably be considered here too
		// instead of being part of true level
		if (curseInfusionBonus) level += 1 + level/6;
		return level;
	}
	
	@Override
	public Item 升级() {
		return 升级( false );
	}
	
	public Item 升级(boolean inscribe ) {

		if (inscribe){
			if (glyph == null){
				inscribe( Glyph.random() );
			}
		}

			super.升级();
		return this;
	}

	public float 防御时(Char attacker, Char defender, float damage ) {
		
		if(defender instanceof Hero hero){
			if(attacker!=null&&首次使用){
				usesLeftToID-=Talent.鉴定速度(hero,this);
			}
		}
		if (attacker!=null&&defender.buff(MagicImmune.class) == null) {
				if (glyph != null) {
					damage = glyph.proc(this, attacker, defender, damage);
				}
			damage = Math.max(damage, 0);
		}
		
		if (attacker!=null&&!已鉴定() && defender == Dungeon.hero) {
			usesLeftToID -= Talent.鉴定速度(Dungeon.hero,this);
			if (usesLeftToID <= 0) {
				if (ShardOfOblivion.passiveIDDisabled()){
					if (usesLeftToID > -1){
						GLog.绿(Messages.get(ShardOfOblivion.class,"identify_ready"),name());
					}
					setIDReady();
				} else {
					鉴定();
					GLog.绿(Messages.get(Armor.class,"identify"));
					Badges.validateItemLevelAquired(this);
				}
			}
		}

		if(attacker!=null&&涂药种类!=null){
			damage=涂药种类.触发(defender,damage);
			涂药种类.消耗(this);

		}
		return damage;
	}
	
	@Override
	public void onHeroGainExp(float levelPercent, Hero hero) {
	
	}

	@Override
	public String name() {
				return glyph != null && (cursedKnown || !glyph.curse()) ? glyph.name( super.name() ) : super.name();
	}

	@Override
	public String status() {
		if (荣誉纹章!=null) {
			return 荣誉纹章.等级() + "/" + 荣誉纹章.最大等级();
		} else {
			return null;
		}
	}
	@Override
	public String info() {
		String info = super.info();

		if (levelKnown) {

			info += "\n\n" + Messages.get(Armor.class, "curr_absorb", kw2(力量()), tier(),
										  kw2(最小防御()), kw2(最大防御()));
			
			if (Dungeon.hero() && 力量() > Dungeon.hero.力量()) {
				info += " " + Messages.get(Armor.class, "too_heavy");
				if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.力量)){
					GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.力量);
				}
			}
		} else {
			info += "\n\n" + Messages.get(Armor.class, "curr_absorb", kw2(力量(0)), tier(),
									  kw2(最小防御(0)),
										  kw2(最大防御(0)));

			if (Dungeon.hero() && 力量(0) > Dungeon.hero.力量()) {
				info += " " + Messages.get(Armor.class, "too_heavy");
				if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.力量)){
					GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.力量);
				}
			}
		}

		switch (augment) {
			case DEFENSE:
				info += "\n\n" + Messages.get(Armor.class, "defense");
				break;
			case SPEED:
				info += "\n\n" + Messages.get(Armor.class, "speed");
				break;
			case EVASION:
				info += "\n\n" + Messages.get(Armor.class, "evasion");
				break;
			case NONE:
		}
		if (glyph != null) {
			info += "\n\n" + glyph.desc();
		}

		if (cursed && isEquipped( Dungeon.hero )) {
			info += "\n\n" + Messages.get(Armor.class, "cursed_worn");
		} else if (cursedKnown && cursed) {
			info += "\n\n" + Messages.get(Armor.class, "cursed");
		} else if (!已鉴定() && cursedKnown){
			if (glyph != null && glyph.curse()) {
				info += "\n\n" + Messages.get(Armor.class, "weak_cursed");
			} else {
				info += "\n\n" + Messages.get(Armor.class, "not_cursed");
			}
		}
		if (涂药种类 != null){
			info += "\n\n" +"这件防具涂抹了_"+ 涂药种类.name()+"_，效果为"+ 涂药种类.desc();
			if(涂药种类.涂药次数>0)
				info += "\n" + Messages.get(Armor.class, "uses_left",涂药种类.涂药次数);
			//
		}
//		if (破损纹章 != null) {
//			info += "\n\n" + Messages.get(Armor.class, "seal_attached", 破损纹章.maxShield(tier(), 强化等级()));
//		}
//
		return info;
	}

	@Override
	public Emitter emitter() {
		if (荣誉纹章==null) return super.emitter();
		Emitter emitter = new Emitter();
		emitter.pos(物品表.film.width(image)/2f + 2f, 物品表.film.height(image)/3f);
		emitter.fillTarget = false;
		emitter.pour(Speck.factory( Speck.RED_LIGHT ), 0.6f);
		return emitter;
	}

	@Override
	public Item random() {
		//+0: 75% (3/4)
		//+1: 20% (4/20)
		//+2: 5%  (1/20)
		int n = 0;
		float 概率=1;
		if(Dungeon.hero()) 概率*=Dungeon.hero.幸运机制();
		if(Dungeon.解压(解压设置.持之以恒)){
			if (算法.概率学(概率*1/2)){
				n++;
				if (算法.概率学(概率*1/3)){
					n++;
					if (算法.概率学(概率*1/4)){
						n++;
						if (算法.概率学(概率*1/5)){
							n++;
							if (算法.概率学(概率*1/6)){
								n++;
							}
						}
					}
				}
			}
		
		}else {
			if (算法.概率学(概率*1/5)){
				n++;
				if (算法.概率学(概率*1/6)){
					n++;
				}
			}
		}
		等级(n);

		//we use a separate RNG here so that variance due to things like parchment scrap
		//does not affect levelgen
		Random.pushGenerator(Random.Long());

			//30% chance to be cursed
			//15% chance to be inscribed
			float effectRoll = Random.Float();
			if(Dungeon.hero()) effectRoll*=Dungeon.hero.幸运机制();

				if(effectRoll<0.3f/ParchmentScrap.curseChanceMultiplier()){
					cursed=true;
				}else
					if(effectRoll>=1f-(0.15f*ParchmentScrap.enchantChanceMultiplier())){
						inscribe();
					}

		Random.popGenerator();

		return this;
	}

	public float 力量(){
		return 力量(等级());
	}

	public float 力量(int lvl){
		float req = 力量(tier(), lvl);
		if(Dungeon.hero()){
            req-=Dungeon.hero.护甲力量;
        }
		if (神力){
			req -= 3;
		}
		
		return req;
	}

	protected static float 力量(int tier, int lvl){
		lvl = Math.max(0, lvl);
		float str=0;
		if(Dungeon.符文("控鹤擒龙")){
			str-=8;
		}

		//strength req decreases at +1,+3,+6,+10,etc.
		return (float)(str+(8+tier*2)-(Math.sqrt(8*lvl+1)-1)/2);
	}
	
	@Override
	public int 金币() {
		if (荣誉纹章!=null) return 0;

		int price = 20 * tier();
		if (hasGoodGlyph()) {
			price *= 1.5;
		}
		if (cursedKnown && (cursed || hasCurseGlyph())) {
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
		return Math.round(金币提升()*装备能量);
	}
	public Armor inscribe( Glyph glyph ) {
		if (glyph == null || !glyph.curse()) curseInfusionBonus = false;
		this.glyph = glyph;
		updateQuickslot();
		//the hero needs runic transference to actually transfer, but we still attach the glyph here
		// in case they take that talent in the future
		if (荣誉纹章!=null){
			荣誉纹章.setGlyph(glyph);
		}
		if (glyph != null && 已鉴定() && Dungeon.hero()
				&& Dungeon.hero.isAlive() && Dungeon.hero.belongings.contains(this)){
			Catalog.setSeen(glyph.getClass());
			Statistics.itemTypesDiscovered.add(glyph.getClass());
		}
		return this;
	}

	public Armor inscribe() {

		Class<? extends Glyph> oldGlyphClass = glyph != null ? glyph.getClass() : null;
		Glyph gl = Glyph.random( oldGlyphClass );

		return inscribe( gl );
	}

	public boolean hasGlyph(Class<?extends Glyph> type) {
		if (glyph != null) {
			return glyph.getClass() == type;
		} else {
			return false;
		}
	}
	public boolean hasGlyph(Class<?extends Glyph> type, Char owner) {
		if (owner.buff(MagicImmune.class) != null) {
			return false;
		}else if (glyph != null) {
			return glyph.getClass() == type;
		} else {
			return false;
		}
	}

	//these are not used to process specific glyph effects, so magic immune doesn't affect them
	public boolean hasGlyph(){
		return glyph != null;
	}
	public boolean hasGoodGlyph(){
		return glyph != null && !glyph.curse();
	}

	public boolean hasCurseGlyph(){
		return glyph != null && glyph.curse();
	}

	private static ItemSprite.Glowing HOLY = new ItemSprite.Glowing( 0xFFFF00 );

	@Override
	public ItemSprite.Glowing glowing() {
			return glyph != null && (cursedKnown || !glyph.curse()) ? glyph.glowing() : null;
	}
	
	public static abstract class Glyph implements Bundlable {

		public ItemSprite.Glowing 深红= new ItemSprite.Glowing(0xa6001c );
		public ItemSprite.Glowing 黄= new ItemSprite.Glowing(0xFFFF00 );
		public ItemSprite.Glowing 棕= new ItemSprite.Glowing(0x4d3d2e );
		public ItemSprite.Glowing 青= new ItemSprite.Glowing(0x00feff );
		public ItemSprite.Glowing 蓝= new ItemSprite.Glowing(0x0000FF );
		public ItemSprite.Glowing 紫= new ItemSprite.Glowing(0x7828f );
		public ItemSprite.Glowing 绿= new ItemSprite.Glowing(0x50ff60 );
		public ItemSprite.Glowing 粉= new ItemSprite.Glowing(0xff4cd2 );
		public ItemSprite.Glowing 白= new ItemSprite.Glowing(0xFFFFFF );
		public ItemSprite.Glowing 深灰= new ItemSprite.Glowing(0x222222 );
		public static final Class<?>[] all = new Class<?>[]{
				晦暗.class,//
				轻便.class,//
				丛生.class,//
				臃肿.class,//

				冰心.class,//
				同位素.class,//
				荆棘.class,//
				磐石.class,//

				迅捷.class,//
				元法.class,//
				虐待.class,//
				代谢.class,//

				粘稠.class,//
				守护.class,//
				迷彩.class,//
				涌流.class,//

				魅惑.class//
		};

		public abstract float proc( Armor armor, Char attacker, Char defender, float damage );

		protected float procChanceMultiplier( Char defender ){
			return genericProcChanceMultiplier( defender );
		}

		public static float genericProcChanceMultiplier( Char defender ){
			float multi = 1;
			if(defender!=null)
			multi*=奥术之戒.enchantPowerMultiplier(defender);
			return multi;
		}
		
		public String name() {
			if (!curse())
				return name( Messages.get(this, "glyph") );
			else
				return name( Messages.get(Item.class, "curse"));
		}
		
		public String name( String armorName ) {
			return Messages.get(this, "name", armorName);
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
		
		public ItemSprite.Glowing glowing(){
			return 白;
		};

		@SuppressWarnings("unchecked")
		public static Glyph random( Class<? extends Glyph> ... toIgnore ) {
				return randomAll( toIgnore );
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomAll( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(all));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}


		
	}
	
	
	
	public static class PlaceHolder extends Armor{
		
		{
			image = 物品表.ARMOR_HOLDER;
		}
		
		public PlaceHolder(){
			super(0);
		}
		
		@Override
		public boolean isSimilar(Item item) {
			return item instanceof Armor;
		}
		
		@Override
		public String info() {
			return "";
		}
	}
}
