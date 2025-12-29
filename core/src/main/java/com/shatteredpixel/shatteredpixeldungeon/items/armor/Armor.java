

package com.shatteredpixel.shatteredpixeldungeon.items.armor;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.组间休息;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.隔天休息;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.AuraOfProtection;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.LifeLinkSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.白猫;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.AntiEntropy;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Bulk;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Displacement;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Metabolism;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Multiplicity;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Overgrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Stench;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.焦灼;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.虐待;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Affection;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Entanglement;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Flow;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Obfuscation;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Repulsion;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Stone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Thorns;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ParchmentScrap;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ShardOfOblivion;
import com.shatteredpixel.shatteredpixeldungeon.items.荣誉纹章;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;

public class Armor extends EquipableItem {

	protected static final String AC_DETACH       = "DETACH";
	protected String 换甲 = Assets.Sounds.布甲;
	
	public enum Augment {
		DEFENSE (1, 1.15f,1),
		SPEED (1, 1,1.3f),
		EVASION (1.45f , 1,1),
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
		
		public int defenseFactor(float defense){
			return Math.round(defense*defenceFactor);
		}
		public float speedFactor(float speed){
			return speed*speedFactor;
		}
	}
	
	public Augment augment = Augment.NONE;
	
	public Glyph glyph;
	public boolean glyphHardened = false;
	public boolean curseInfusionBonus = false;
	public boolean 神力 = false;
	
	public 荣誉纹章 荣誉纹章;
	
	public int tier;
	
	private static final int USES_TO_ID = 10;
	public float usesLeftToID = USES_TO_ID;
	
	public Armor( int tier ) {
		this.tier = tier;
	}
	
	private static final String USES_LEFT_TO_ID = "uses_left_to_id";
	private static final String GLYPH			= "glyph";
	private static final String GLYPH_HARDENED	= "glyph_hardened";
	private static final String CURSE_INFUSION_BONUS = "curse_infusion_bonus";
	private static final String 神力x = "神力";
	private static final String 荣誉纹章x = "荣誉纹章";
	private static final String AUGMENT			= "augment";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( USES_LEFT_TO_ID, usesLeftToID );
		bundle.put( GLYPH, glyph );
		bundle.put( GLYPH_HARDENED, glyphHardened );
		bundle.put( CURSE_INFUSION_BONUS, curseInfusionBonus );
		bundle.put( 神力x, 神力 );
		bundle.put(荣誉纹章x,荣誉纹章);
		bundle.put( AUGMENT, augment);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		usesLeftToID = bundle.getInt( USES_LEFT_TO_ID );
		inscribe((Glyph) bundle.get(GLYPH));
		glyphHardened = bundle.getBoolean(GLYPH_HARDENED);
		curseInfusionBonus = bundle.getBoolean( CURSE_INFUSION_BONUS );
		神力 = bundle.getBoolean( 神力x );
		荣誉纹章= (荣誉纹章)bundle.get(荣誉纹章x);
		
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
		if(!(this instanceof 背心)&&hero.heroClass(HeroClass.灵猫)){
			actions.remove(AC_EQUIP);
		}
		if(!(this instanceof 披风)&&hero.heroClass(HeroClass.鼠弟)){
			actions.remove(AC_EQUIP);
		}
		if(hero.heroClass(HeroClass.凌云)){
			actions.remove(AC_EQUIP);
		}
		if(Dungeon.炼狱(炼狱设置.诅咒装备)&&(等级()>5||tier>3)) {
			actions.remove(AC_EQUIP);
		}
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_DETACH)&&荣誉纹章!=null){
			荣誉纹章 detaching = detachSeal();
			GLog.i( Messages.get(Armor.class, "detach_seal") );
			hero.sprite.operate();
			detaching.放背包();
			updateQuickslot();
		}
	}

	@Override
	public boolean 放背包(Bag container) {
		if(super.放背包(container)){
			if(首次拾取){
				
				usesLeftToID -= Talent.鉴定速度(Dungeon.hero,this);
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
		return hero.攻击延迟()*1/hero.移速()*6f;
	}
	public void 换甲(){
		Sample.INSTANCE.play(换甲);
	}
	@Override
	public boolean doEquip( Hero hero ) {
		
		
		
		detach(hero.belongings.backpack);
		Armor oldArmor = hero.belongings.armor;
		if (hero.belongings.armor == null || hero.belongings.armor.doUnequip( hero, true, false )) {
			
			hero.belongings.armor = this;
			
			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(Armor.class, "equip_cursed") );
				Dungeon.hero.sprite.哭泣();
			}
			
			((HeroSprite)hero.sprite).updateArmor();
			activate(hero);
			Talent.装备时(hero, this);
			hero.spend( timeToEquip( hero ) );

			if (Dungeon.hero.heroClass(HeroClass.WARRIOR) && checkSeal() == null){
				荣誉纹章 seal =oldArmor!=null ? oldArmor.checkSeal() : null;
				if (seal != null && (!cursed || (seal.getGlyph() != null && seal.getGlyph().curse()))){

					GameScene.show(new WndOptions(new ItemSprite(物品表.荣誉纹章),
							Messages.titleCase(seal.title()),
							Messages.get(Armor.class, "seal_transfer"),
							Messages.get(Armor.class, "seal_transfer_yes"),
							Messages.get(Armor.class, "seal_transfer_no")){
						@Override
						protected void onSelect(int index) {
							super.onSelect(index);
							if (index == 0){
								seal.affixToArmor(Armor.this, oldArmor);
								updateQuickslot();
							}
							super.hide();
						}

						@Override
						public void hide() {
							//do nothing, must press button
						}
					});
				} else {
					hero.next();
				}
			} else {
				hero.next();
			}
			return true;
			
		} else {
			
			放背包( hero.belongings.backpack );
			return false;
			
		}
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
			int 转移量 =荣誉纹章.最大等级()-荣誉纹章.等级();
			if(转移量>0&&真等级()>0&&真等级()-转移量>=0){
				等级(真等级()-转移量);
				荣誉纹章.升级(转移量);
			}
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

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		if (super.doUnequip( hero, collect, single )) {
			if(首次装备){
				usesLeftToID-=Talent.鉴定速度(hero,this);
			}
			hero.belongings.armor = null;
			((HeroSprite)hero.sprite).updateArmor();

//			破损纹章.WarriorShield sealBuff = hero.buff(破损纹章.WarriorShield.class);
//			if (sealBuff != null) sealBuff.setArmor(null);

			return true;

		} else {

			return false;

		}
	}
	
	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null && hero.belongings.armor() == this;
	}

	public final int 最大防御(){
		return 最大防御(强化等级());
	}

	public int 最大防御(int lvl){
		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
			return augment.defenseFactor(1 + tier + lvl);
		}

		return augment.defenseFactor(Math.round(2*tier * (1 + lvl/1.5f)));
	}

	public final int 最小防御(){
		return 最小防御(强化等级());
	}

	public int 最小防御(int lvl){
		return augment.defenseFactor(tier);
//		if (Dungeon.isChallenged(Challenges.NO_ARMOR)){
//			return 0;
//		}
//		return augment.defenseFactor(lvl);
	}

	//This exists so we can test what a char's base evasion would be without armor affecting it
	//more ugly static vars yaaay~
	public static boolean testingNoArmDefSkill = false;
	
	public float evasionFactor( Char owner, float evasion ){
		if (testingNoArmDefSkill) return evasion;
		
		if (hasGlyph(Stone.class, owner) && !Stone.testingEvasion()){
			return 0;
		}
		
		if (owner instanceof Hero hero){
			int aEnc = 力量() - hero.力量();
			if (aEnc > 0&&!hero.heroClass(HeroClass.重武)) evasion /= Math.pow(1.5, aEnc);
			if (aEnc < 0) evasion *= 1-aEnc*owner.属性增幅;
			
		}
		
		return augment.evasionFactor(evasion);
	}
	
	public float speedFactor( Char owner, float speed ){
		
		if (owner instanceof Hero hero&&!hero.heroClass(HeroClass.重武)) {
			int aEnc = 力量() - hero.力量();
			if (aEnc > 0) speed /= Math.pow(1.2, aEnc);
			if (aEnc < 0) speed *= 1-aEnc*owner.属性增幅/2f;
		}
		
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
		} else if (glyph != null) {
			//chance to lose harden buff is 10/20/40/80/100% when upgrading from +6/7/8/9/10
			if (glyphHardened) {
				if (等级() >= 6 && Random.Float(10) < Math.pow(2, 等级()-6)){
					glyphHardened = false;
				}

			//chance to remove curse is a static 33%
			} else if (hasCurseGlyph()){
				if (Random.Int(3) == 0) inscribe(null);

			//otherwise chance to lose glyph is 10/20/40/80/100% when upgrading from +4/5/6/7/8
			} else {

				//the chance from +4/5, and then +6 can be set to 0% with metamorphed runic transference
				int lossChanceStart = 4;

				if (等级() >= lossChanceStart && Random.Float(10) < Math.pow(2, 等级()-4)) {
					inscribe(null);
				}
			}
		}
		
		cursed = false;

		if (荣誉纹章!=null&&荣誉纹章.等级()<荣誉纹章.最大等级()) {
			荣誉纹章.升级();//优先纹章
		}else{
			super.升级();
		}

		return this;
	}
	
	public int 防御时(Char attacker, Char defender, int damage ) {
		
		if(defender instanceof Hero hero){
			if(首次使用){
				usesLeftToID-=Talent.鉴定速度(hero,this);
			}
			if(hero.subClass(HeroSubClass.健身猛男)&&力量() > hero.力量()&&hero.nobuff(隔天休息.class)){
				if(hero.hasbuff(组间休息.class)&&hero.现在健身>0){
					hero.现在健身-=0.01f;
				}else{
					if(hero.现在健身>=3){
						Buff.施加(hero,隔天休息.class,900);
					}else{
						hero.现在健身+=0.01f;
						Buff.施加(hero,组间休息.class,1);
					}
				}
			}
		}
		if (defender.buff(MagicImmune.class) == null) {
			Glyph trinityGlyph = null;
			//only when it's the hero or a char that uses the hero's armor
			if (Dungeon.hero.buff(BodyForm.BodyFormBuff.class) != null
					&& (defender == Dungeon.hero||defender instanceof PrismaticImage||defender instanceof 白猫||defender instanceof ShadowClone.ShadowAlly)){
				trinityGlyph = Dungeon.hero.buff(BodyForm.BodyFormBuff.class).glyph();
				if (glyph != null && trinityGlyph != null && trinityGlyph.getClass() == glyph.getClass()){
					trinityGlyph = null;
				}
			}
				if (glyph != null) {
					damage = glyph.proc(this, attacker, defender, damage);
				}
				if (trinityGlyph != null){
					damage = trinityGlyph.proc( this, attacker, defender, damage );
				}
				//so that this effect procs for allies using this armor via aura of protection
				if (defender.alignment == Dungeon.hero.alignment
						&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
						&& (Dungeon.level.distance(defender.pos, Dungeon.hero.pos) <= 2 || defender.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)
						) {
					int blocking = Dungeon.hero.subClass == HeroSubClass.PALADIN ? 3 : 1;
					damage -= Math.round(blocking * Glyph.genericProcChanceMultiplier(defender));
				}
			damage = Math.max(damage, 0);
		}
		
		if (!已鉴定() && defender == Dungeon.hero) {
			usesLeftToID -= Talent.鉴定速度(Dungeon.hero,this);
			if (usesLeftToID <= 0) {
				if (ShardOfOblivion.passiveIDDisabled()){
					if (usesLeftToID > -1){
						GLog.p(Messages.get(ShardOfOblivion.class, "identify_ready"), name());
					}
					setIDReady();
				} else {
					鉴定();
					GLog.p(Messages.get(Armor.class, "identify"));
					Badges.validateItemLevelAquired(this);
				}
			}
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
	public String info() {
		String info = super.info();
		
		if (levelKnown) {

			info += "\n\n" + Messages.get(Armor.class, "curr_absorb", 力量(), tier, 最小防御(), 最大防御());
			
			if (Dungeon.hero() && 力量() > Dungeon.hero.力量()) {
				info += " " + Messages.get(Armor.class, "too_heavy");
			}
		} else {
			info += "\n\n" + Messages.get(Armor.class, "curr_absorb", 力量(0), tier, 最小防御(0), 最大防御(0));

			if (Dungeon.hero() && 力量(0) > Dungeon.hero.力量()) {
				info += " " + Messages.get(Armor.class, "probably_too_heavy");
			}
		}

		switch (augment) {
			case DEFENSE:
				info += " " + Messages.get(Armor.class, "defense");
				break;
			case SPEED:
				info += " " + Messages.get(Armor.class, "speed");
				break;
			case EVASION:
				info += " " + Messages.get(Armor.class, "evasion");
				break;
			case NONE:
		}
		if (glyph != null  && (cursedKnown || !glyph.curse())) {
			info += "\n\n" +  Messages.capitalize(Messages.get(Armor.class, "inscribed", glyph.name()));
			if (glyphHardened) info += " " + Messages.get(Armor.class, "glyph_hardened");
			info += " " + glyph.desc();
		} else if (glyphHardened){
			info += "\n\n" + Messages.get(Armor.class, "hardened_no_glyph");
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
//
//		if (破损纹章 != null) {
//			info += "\n\n" + Messages.get(Armor.class, "seal_attached", 破损纹章.maxShield(tier, 强化等级()));
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
		
		if(Dungeon.解压(解压设置.持之以恒)){
			if (Random.Int(1) == 0){
				n++;
				if(Random.Int(2)==0){
					n++;
					if(Random.Int(3)==0){
						n++;
						if(Random.Int(4)==0){
							n++;
							if(Random.Int(5)==0){
								n++;
							}
						}
					}
				}
			}
		
		}else {
			if (Random.Int(4) == 0) {
				n++;
				if (Random.Int(5) == 0) {
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
			if (effectRoll < 0.3f * ParchmentScrap.curseChanceMultiplier()) {
				inscribe(Glyph.randomCurse());
				cursed = true;
			} else if (effectRoll >= 1f - (0.15f * ParchmentScrap.enchantChanceMultiplier())){
				inscribe();
			}

		Random.popGenerator();

		return this;
	}

	public int 力量(){
		return 力量(等级());
	}

	public int 力量(int lvl){
		int req = 力量(tier, lvl);
		if(isEquipped(Dungeon.hero)&&Dungeon.hero()){
            req-=Dungeon.hero.护甲力量;
        }
		if (神力){
			req -= 3;
		}
		
		return req;
	}

	protected static int 力量(int tier, int lvl){
		lvl = Math.max(0, lvl);
		//strength req decreases at +1,+3,+6,+10,etc.
		return (8 + Math.round(tier * 2)) - (int)(Math.sqrt(8 * lvl + 1) - 1)/2;
	}
	
	@Override
	public int 金币() {
		if (荣誉纹章!=null) return 0;

		int price = 20 * tier;
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
		return Math.round(金币()*0.025f+1);
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

	public boolean hasGlyph(Class<?extends Glyph> type, Char owner) {
		if (owner.buff(MagicImmune.class) != null) {
			return false;
		} else if (owner.buff(BodyForm.BodyFormBuff.class) != null
				&& owner.buff(BodyForm.BodyFormBuff.class).glyph() != null
				&& owner.buff(BodyForm.BodyFormBuff.class).glyph().getClass().equals(type)){
			return true;
		} else if (glyph != null) {
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
		
		public static final Class<?>[] common = new Class<?>[]{
				Obfuscation.class, Swiftness.class, Viscosity.class, Potential.class };

		public static final Class<?>[] uncommon = new Class<?>[]{
				Brimstone.class, Stone.class, Entanglement.class,
				Repulsion.class, Camouflage.class, Flow.class };

		public static final Class<?>[] rare = new Class<?>[]{
				Affection.class, AntiMagic.class, Thorns.class };

		public static final float[] typeChances = new float[]{
				50, //12.5% each
				40, //6.67% each
				10  //3.33% each
		};

		public static final Class<?>[] curses = new Class<?>[]{
				AntiEntropy.class, Corrosion.class, Displacement.class, Metabolism.class,
				Multiplicity.class, Stench.class, Overgrowth.class, Bulk.class, 虐待.class, 焦灼.class
		};
		
		public abstract int proc( Armor armor, Char attacker, Char defender, int damage );

		protected float procChanceMultiplier( Char defender ){
			return genericProcChanceMultiplier( defender );
		}

		public static float genericProcChanceMultiplier( Char defender ){
			float multi = 奥术之戒.enchantPowerMultiplier(defender);

			if (Dungeon.hero.alignment == defender.alignment
					&& Dungeon.hero.buff(AuraOfProtection.AuraBuff.class) != null
					&& (Dungeon.level.distance(defender.pos, Dungeon.hero.pos) <= 2 || defender.buff(LifeLinkSpell.LifeLinkSpellBuff.class) != null)){
				multi +=Dungeon.hero.天赋点数(Talent.AURA_OF_PROTECTION,0.3f);
			}

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
		
		public abstract ItemSprite.Glowing glowing();

		@SuppressWarnings("unchecked")
		public static Glyph random( Class<? extends Glyph> ... toIgnore ) {
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
		public static Glyph randomCommon( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(common));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomUncommon( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(uncommon));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomRare( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(rare));
			glyphs.removeAll(Arrays.asList(toIgnore));
			if (glyphs.isEmpty()) {
				return random();
			} else {
				return (Glyph) Reflection.newInstance(Random.element(glyphs));
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Glyph randomCurse( Class<? extends Glyph> ... toIgnore ){
			ArrayList<Class<?>> glyphs = new ArrayList<>(Arrays.asList(curses));
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
