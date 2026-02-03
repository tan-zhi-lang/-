

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.影织法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUseItem;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 法师魔杖 extends Weapon{

	private Wand wand;

	public static final String AC_IMBUE = "IMBUE";
	public static final String AC_ZAP	= "ZAP";

	private static final float STAFF_SCALE_FACTOR = 0.75f;

	{
		image = 物品表.法师魔杖;
		
		tier = 1;
		伤害=0.68f;

		defaultAction = AC_ZAP;
		usesTargeting = true;
		
		特别= true;
		遗产= false;
		嬗变= false;
		专属=true;
	}
	@Override
	public String defaultAction() {
		return defaultAction;
	}
	public 法师魔杖() {
		wand = null;
	}
	public int 转移=0;
	public int 最大转移(){
		int x=1;
		if(Dungeon.解压(解压设置.独自变强)){
			x+=Dungeon.hero.等级/2;
		}
		return x+(Dungeon.hero()?Dungeon.hero.天赋点数(Talent.高级魔杖):0);
	}
	@Override
	public int 强化等级(){
		int l=转移;
		if(Dungeon.hero()){
			l+=Dungeon.hero.智力;
			l+=(Dungeon.hero.heroClass(HeroClass.MAGE)?1:0);
		}
		//only the hero can be affected by Degradation
		if (Dungeon.hero() && Dungeon.hero.buff( Degrade.class ) != null
				&& (isEquipped( Dungeon.hero ) || Dungeon.hero.belongings.contains( this ))) {
			return Degrade.reduceLevel(等级()+l);
		} else {
			return 等级()+l;
		}
	}

	public 法师魔杖(Wand wand){
		this();
		wand.鉴定();
		wand.cursed = false;
		this.wand = wand;
		updateWand();
		wand.curCharges = wand.maxCharges;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add(AC_IMBUE);
		if (wand!= null && wand.curCharges > 0) {
			actions.add( AC_ZAP );
		}
		return actions;
	}

	@Override
	public void activate( Char ch ) {
		super.activate(ch);
		applyWandChargeBuff(ch);
	}

	@Override
	public int targetingPos(Hero user, int dst) {
		if (wand != null) {
			return wand.targetingPos(user, dst);
		} else {
			return super.targetingPos(user, dst);
		}
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_IMBUE)) {
			curUser = hero;
			GameScene.selectItem(itemSelector);

		} else if (action.equals(AC_ZAP)){

			if (wand == null) {
				GameScene.show(new WndUseItem(null, this));
				return;
			}

			if (cursed || hasCurseEnchant()) wand.cursed = true;
			else                             wand.cursed = false;
			wand.execute(hero, AC_ZAP);
		}
	}

	@Override
	public int buffedVisiblyUpgraded() {
		if (wand != null){
			return Math.max(super.buffedVisiblyUpgraded(), wand.buffedVisiblyUpgraded());
		} else {
			return super.buffedVisiblyUpgraded();
		}
	}

	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {
		if (attacker instanceof Hero && ((Hero) attacker).天赋(Talent.MYSTICAL_CHARGE)){
			Hero hero = (Hero) attacker;
			ArtifactRecharge.chargeArtifacts(hero, hero.天赋点数(Talent.MYSTICAL_CHARGE,0.5f));
		}
			damage += Math.round(damage*wand.curCharges*Dungeon.hero.天赋点数(Talent.EMPOWERED_STRIKE,0.075f));
		
		if (wand != null &&
				attacker instanceof Hero hero && hero.subClass(HeroSubClass.战斗法师)) {
			if (wand.curCharges < wand.maxCharges&&hero.职业精通()){
				wand.partialCharge+=0.5f;
				充能卷轴.charge(hero);
			}
			wand.onHit(this, attacker, defender, damage);
		}

		return super.攻击时(attacker, defender, damage);
	}

	@Override
	public int reachFactor(Char owner) {
		int reach = super.reachFactor(owner);
		if (owner instanceof Hero
				&& ((Hero)owner).subClass == HeroSubClass.战斗法师){

			if(wand instanceof WandOfDisintegration)
			reach += Math.round(Wand.procChanceMultiplier(owner));

			if(wand instanceof 影织法杖)
			reach += Math.round(Wand.procChanceMultiplier(owner)*2);
		}
		return reach;
	}

	@Override
	public boolean 放背包(Bag container ) {
		if (super.放背包(container)) {
			if (container.owner != null) {
				applyWandChargeBuff(container.owner);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onDetach( ) {
		if (wand != null) wand.stopCharging();
	}

	public Item imbueWand(Wand wand, Char owner){

		int oldStaffcharges = this.wand != null ? this.wand.curCharges : 0;

		if (owner == Dungeon.hero){
			Talent.WandPreservationCount
					counter = Buff.施加(Dungeon.hero,Talent.WandPreservationCount.class);
//			if (counter.count == 0){
//				counter.set(1);
				
				int 转移量 = 最大转移()- 转移;
				if(转移量>0&&this.wand.等级()>0&&this.wand.等级()-转移量>=0){
					this.wand.等级(this.wand.等级()-转移量);
					转移+=转移量;
				}
				if (!this.wand.放背包()) {
					Dungeon.level.drop(this.wand, owner.pos);
				}
				GLog.newLine();
				GLog.p(Messages.get(this, "preserved"));
//			}
		}

		this.wand = null;

		wand.resinBonus = 0;
		wand.updateLevel();
//
//		等级(targetLevel);
		this.wand = wand;
		wand.levelKnown = wand.curChargeKnown = true;
		updateWand();
		wand.curCharges = Math.min(wand.maxCharges, wand.curCharges+oldStaffcharges);
		if (owner != null){
			applyWandChargeBuff(owner);
 		} else if (Dungeon.hero.belongings.contains(this)){
			applyWandChargeBuff(Dungeon.hero);
		}

		if (wand.cursed && (!this.cursed || !this.hasCurseEnchant())){
			equipCursed(Dungeon.hero);
			this.cursed = this.cursedKnown = true;
			enchant(Enchantment.randomCurse());
		}

		//This is necessary to reset any particles.
		//FIXME this is gross, should implement a better way to fully reset quickslot visuals
		int slot = Dungeon.quickslot.getSlot(this);
		if (slot != -1){
			Dungeon.quickslot.clearSlot(slot);
			updateQuickslot();
			Dungeon.quickslot.setSlot( slot, this );
			updateQuickslot();
		}
		
		Badges.validateItemLevelAquired(this);

		return this;
	}

	public void gainCharge( float amt ){
		gainCharge(amt, false);
	}

	public void gainCharge( float amt, boolean overcharge ){
		if (wand != null){
			wand.gainCharge(amt, overcharge);
		}
	}

	public void applyWandChargeBuff(Char owner){
		if (wand != null){
			wand.charge(owner, STAFF_SCALE_FACTOR);
		}
	}

	public Class<?extends Wand> wandClass(){
		return wand != null ? wand.getClass() : null;
	}

	@Override
	public Item 升级(boolean enchant) {
		super.升级( enchant );

		updateWand();

		return this;
	}

	@Override
	public Item 降级() {
		super.降级();

		updateWand();

		return this;
	}
	
	public void updateWand(){
		if (wand != null) {
			int curCharges = wand.curCharges;
			wand.等级(等级());
			int max=0;
			//gives the wand one additional max charge
			wand.maxCharges = Math.min(wand.maxCharges + 1, 10+max);
			wand.curCharges = Math.min(curCharges + (curUser!=null&&curUser.heroClass(HeroClass.MAGE)?1:0), wand.maxCharges);
			updateQuickslot();
		}
	}

	@Override
	public String status() {
		if (wand == null) return super.status();
		else return wand.status();
	}

	@Override
	public String name() {
		if (wand == null) {
			return super.name();
		} else {
			String name = Messages.get(wand, "staff_name");
			return enchantment != null && (cursedKnown || !enchantment.curse()) ? enchantment.name( name ) : name;
		}
	}
	@Override
	public String desc(){
		return Messages.get(this, "desc",转移,最大转移());
	}
	@Override
	public String info() {
		String info = super.info();

		if (wand != null){
			info += "\n\n" + Messages.get(this, "has_wand", Messages.get(wand, "name"));
			if ((!cursed && !hasCurseEnchant()) || !cursedKnown)    info += " " + wand.statsDesc();
			else                                                    info += " " + Messages.get(this, "cursed_wand");

			if (Dungeon.hero.subClass == HeroSubClass.战斗法师){
				info += "\n\n" + Messages.get(wand, "bmage_desc");
			}
		}

		return info;
	}

	@Override
	public Emitter emitter() {
		if (wand == null) return null;
		Emitter emitter = new Emitter();
		emitter.pos(12.5f, 3);
		emitter.fillTarget = false;
		emitter.pour(StaffParticleFactory, 0.1f);
		return emitter;
	}

	private static final String WAND = "wand";
	private static final String MAXCHARGES = "maxCharges";
	private static final String 转移x = "转移";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(WAND, wand);
		bundle.put(转移x, 转移);
		if (wand != null) {
			bundle.put(MAXCHARGES, wand.maxCharges);
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		wand = (Wand) bundle.get(WAND);
		转移 = bundle.getInt(转移x);
		if (wand != null) {
			wand.maxCharges = bundle.getInt(MAXCHARGES);
		}
	}

	@Override
	public int 金币() {
		return 0;
	}
	
	@Override
	public Weapon enchant(Enchantment ench) {
		if (curseInfusionBonus && (ench == null || !ench.curse())){
			curseInfusionBonus = false;
			updateWand();
		}
		return super.enchant(ench);
	}
	
	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(法师魔杖.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return MagicalHolster.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Wand;
		}

		@Override
		public void onSelect( final Item item ) {
			if (item != null) {

				if (wand == null){
					applyWand((Wand)item);
				} else if(!(item instanceof 灵月法杖)) {
					int newLevel=0;
					int 转移量 = 最大转移()- 转移;
					if(转移量>0&&item.等级()>0){
						newLevel=item.等级()-转移量;
						转移+=转移量;
					}

					String bodyText = Messages.get(法师魔杖.class, "imbue_desc");
					if (item.已鉴定()){
						bodyText += "\n\n" + Messages.get(法师魔杖.class, "imbue_level", newLevel);
					} else {
						bodyText += "\n\n" + Messages.get(法师魔杖.class, "imbue_unknown", 转移量);
					}

					if (!item.cursedKnown || item.cursed){
						bodyText += "\n\n" + Messages.get(法师魔杖.class, "imbue_cursed");
					}

					if (Dungeon.hero.天赋(Talent.高级魔杖)
						&&Dungeon.hero.buff(Talent.WandPreservationCount.class)==null){
						bodyText += "\n\n" + Messages.get(法师魔杖.class, "imbue_talent");
					} else {
						bodyText += "\n\n" + Messages.get(法师魔杖.class, "imbue_lost");
					}

					GameScene.show(
							new WndOptions(new ItemSprite(item),
									Messages.titleCase(item.name()),
									bodyText,
									Messages.get(法师魔杖.class, "yes"),
									Messages.get(法师魔杖.class, "no")) {
								@Override
								protected void onSelect(int index) {
									if (index == 0) {
										applyWand((Wand)item);
									}
								}
							}
					);
				}
			}
		}

		private void applyWand(Wand wand){
			Sample.INSTANCE.play(Assets.Sounds.BURNING);
			curUser.sprite.emitter().burst( ElmoParticle.FACTORY, 12 );
			evoke(curUser);

			Dungeon.quickslot.clearItem(wand);

			wand.detach(curUser.belongings.backpack);

			GLog.p( Messages.get(法师魔杖.class, "imbue", wand.name()));
			imbueWand( wand, curUser );

			updateQuickslot();
		}
	};

	private final Emitter.Factory StaffParticleFactory = new Emitter.Factory() {
		@Override
		//reimplementing this is needed as instance creation of new staff particles must be within this class.
		public void emit( Emitter emitter, int index, float x, float y ) {
			StaffParticle c = (StaffParticle)emitter.getFirstAvailable(StaffParticle.class);
			if (c == null) {
				c = new StaffParticle();
				emitter.add(c);
			}
			c.reset(x, y);
		}

		@Override
		//some particles need light mode, others don't
		public boolean lightMode() {
			return !((wand instanceof WandOfDisintegration)
					|| (wand instanceof WandOfCorruption)
					|| (wand instanceof WandOfCorrosion)
					|| (wand instanceof WandOfRegrowth)
					|| (wand instanceof WandOfLivingEarth));
		}
	};

	//determines particle effects to use based on wand the staff owns.
	public class StaffParticle extends PixelParticle{

		private float minSize;
		private float maxSize;
		public float sizeJitter = 0;

		public StaffParticle(){
			super();
		}

		public void reset( float x, float y ) {
			revive();

			speed.set(0);

			this.x = x;
			this.y = y;

			if (wand != null)
				wand.staffFx( this );

		}

		public void setSize( float minSize, float maxSize ){
			this.minSize = minSize;
			this.maxSize = maxSize;
		}

		public void setLifespan( float life ){
			lifespan = left = life;
		}

		public void shuffleXY(float amt){
			x += Random.Float(-amt, amt);
			y += Random.Float(-amt, amt);
		}

		public void radiateXY(float amt){
			float hypot = (float)Math.hypot(speed.x, speed.y);
			this.x += speed.x/hypot*amt;
			this.y += speed.y/hypot*amt;
		}

		@Override
		public void update() {
			super.update();
			size(minSize + (left / lifespan)*(maxSize-minSize) + Random.Float(sizeJitter));
		}
	}
}
