

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.连击;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndUseItem;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;

import java.util.ArrayList;

public class 破损纹章 extends Item {

	public static final String AC_AFFIX = "AFFIX";

	//only to be used from the quickslot, for tutorial purposes mostly.
	public static final String AC_INFO = "INFO_WINDOW";

	{
		image = 物品表.破损纹章;

		cursedKnown = levelKnown = true;
		特别= true;
		遗产= false;

		defaultAction = AC_INFO;
	}

	private Armor.Glyph glyph;
	private int 转移等级 = 0;

	public boolean canTransferGlyph(){
		if (glyph == null){
			return false;
		}
//		if (Dungeon.hero.天赋点数(Talent.纹章刻印)==3){
			return true;
//		} else if (Dungeon.hero.天赋点数(Talent.纹章刻印)==2
//			&& (Arrays.asList(Armor.Glyph.common).contains(glyph.getClass())
//				|| Arrays.asList(Armor.Glyph.uncommon).contains(glyph.getClass()))){
//			return true;
//		}else if (Dungeon.hero.天赋点数(Talent.纹章刻印)==1
//			&& Arrays.asList(Armor.Glyph.common).contains(glyph.getClass())){
//			return true;
//		} else {
//			return false;
//		}
	}

	public Armor.Glyph getGlyph(){
		return glyph;
	}

	public void setGlyph( Armor.Glyph glyph ){
		this.glyph = glyph;
	}

	public int maxShield( int armTier, int armLvl ){
		float x=0.5f;
		if(Dungeon.hero()&&Dungeon.hero.天赋(Talent.纹章荣耀)){
			x+=Dungeon.hero.天赋点数(Talent.纹章荣耀,0.5f);
		}
		return Math.round((2*armTier*(1+armLvl))*x);
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return glyph != null ? glyph.glowing() : null;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions =  super.actions(hero);
		actions.add(AC_AFFIX);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_AFFIX)){
			curItem = this;
			GameScene.selectItem(armorSelector);
		} else if (action.equals(AC_INFO)) {
			GameScene.show(new WndUseItem(null, this));
		}
	}

	//outgoing is either the seal itself as an item, or an armor the seal is affixed to
	public void affixToArmor(Armor armor, Item outgoing){
		if (armor != null) {
			if (!armor.cursedKnown){
				GLog.w(Messages.get(破损纹章.class, "unknown_armor"));

			} else if (armor.cursed && (getGlyph() == null || !getGlyph().curse())){
				GLog.w(Messages.get(破损纹章.class, "cursed_armor"));

			}else if (armor.glyph != null && getGlyph() != null
					&& canTransferGlyph()
					&& armor.glyph.getClass() != getGlyph().getClass()) {

				GameScene.show(new WndOptions(new ItemSprite(物品表.破损纹章),
						Messages.get(破损纹章.class, "choose_title"),
						Messages.get(破损纹章.class, "choose_desc", armor.glyph.name(), getGlyph().name()),
						armor.glyph.name(),
						getGlyph().name()){
					@Override
					protected void onSelect(int index) {
						if (index == -1) return;

						if (outgoing == 破损纹章.this) {
							detach(Dungeon.hero.belongings.backpack);
						} else if (outgoing instanceof Armor){
							((Armor) outgoing).detachSeal();
						}

						if (index == 0) setGlyph(null);
						//if index is 1, then the glyph transfer happens in affixSeal

						GLog.p(Messages.get(破损纹章.class, "affix"));
						Dungeon.hero.sprite.operate();
						Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
						armor.affixSeal(破损纹章.this);
					}

					@Override
					public void hide() {
						super.hide();
						Dungeon.hero.next();
					}
				});

			} else {
				if (outgoing == this) {
					detach(Dungeon.hero.belongings.backpack);
				} else if (outgoing instanceof Armor){
					((Armor) outgoing).detachSeal();
				}

				GLog.p(Messages.get(破损纹章.class, "affix"));
				Dungeon.hero.sprite.operate();
				Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
				armor.affixSeal(this);
				Dungeon.hero.next();
			}
		}
	}

	@Override
	public String name() {
		return glyph != null ? glyph.name( super.name() ) : super.name();
	}

	@Override
	public String info() {
		String info = super.info();
		if (glyph != null){
			info += "\n\n" + Messages.get(this, "inscribed", glyph.name());
			info += " " + glyph.desc();
		}
		return info;
	}

	@Override
	//scroll of upgrade can be used directly once, same as upgrading armor the seal is affixed to then removing it.
	public boolean 可升级() {
		return 等级() < 最大等级();
	}
	public int 最大等级(){
		return 1+Dungeon.hero.天赋点数(Talent.纹章荣耀);
	}
	protected static WndBag.ItemSelector armorSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return  Messages.get(破损纹章.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Armor;
		}

		@Override
		public void onSelect( Item item ) {
			if (item instanceof Armor) {
				破损纹章 seal = (破损纹章) curItem;
				seal.affixToArmor((Armor)item, seal);
			}
		}
	};

	private static final String GLYPH = "glyph";
	private static final String 转移等级x  = "转移等级";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(GLYPH, glyph);
		bundle.put(转移等级x, 转移等级);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		glyph = (Armor.Glyph)bundle.get(GLYPH);
		转移等级 = bundle.getInt(转移等级x);
	}

	public static class WarriorShield extends ShieldBuff {

		{
			type = buffType.POSITIVE;

			detachesAtZero = false;
			shieldUsePriority = 2;
		}

		private Armor armor;

		private int cooldown = 0;
		private float turnsSinceEnemies = 0;

		private static int COOLDOWN_START = 100;
		public int cool(){
			return COOLDOWN_START;
		}

		@Override
		public int icon() {
			if (coolingDown() || 护盾量() > 0){
				return BuffIndicator.SEAL_SHIELD;
			} else {
				return BuffIndicator.NONE;
			}
		}

		@Override
		public void tintIcon(Image icon) {
			if (coolingDown() && 护盾量() == 0){
				icon.brightness(0.3f);
			} else {
				icon.resetColor();
			}
		}

		@Override
		public float iconFadePercent() {
			if (护盾量() > 0){
				return GameMath.gate(0, 1f - 护盾量()/(float)maxShield(), 1);
			} else if (coolingDown()){
				return GameMath.gate(0, cooldown / (float)cool(), 1);
			} else {
				return 0;
			}
		}

		@Override
		public String iconTextDisplay() {
			if (护盾量() > 0){
				return Integer.toString(护盾量());
			} else if (coolingDown()){
				return Integer.toString(cooldown);
			} else {
				return "";
			}
		}

		@Override
		public String desc() {
			if (护盾量() > 0){
				return Messages.get(this, "desc_active", 护盾量(), cooldown);
			} else {
				return Messages.get(this, "desc_cooldown", cooldown);
			}
		}
		
		@Override
		public boolean attachTo(Char target){
			
			return super.attachTo(target);
		}
		
		@Override
		public synchronized boolean act() {
			if (cooldown > 0 && 再生.regenOn()){
				cooldown--;
			}

			if (护盾量() > 0){
				if (Dungeon.hero.visibleEnemies() == 0 &&Dungeon.hero.buff(连击.class)==null){
					turnsSinceEnemies += HoldFast.buffDecayFactor(target);
					if (turnsSinceEnemies >= 5){
						if (cooldown > 0) {
							float percentLeft = 护盾量() / (float)maxShield();
							//max of 50% cooldown refund
							cooldown = Math.max(0, (int)(cooldown - cool() * (percentLeft / 2f)));
						}
						减少(护盾量());
					}
				} else {
					turnsSinceEnemies = 0;
				}
			}
			
			if (护盾量() <= 0 && maxShield() <= 0 && cooldown == 0){
				detach();
			}
			
			spend(TICK);
			return true;
		}

		public synchronized void activate() {
			增加(maxShield());
			cooldown = Math.max(0, cooldown+cool());
			turnsSinceEnemies = 0;
		}

		public boolean coolingDown(){
			return cooldown > 0;
		}

		public void reduceCooldown(float percentage){
			cooldown -= Math.round(cool()*percentage);
			cooldown = Math.max(cooldown, -cool());
		}

		public synchronized void setArmor(Armor arm){
			armor = arm;
		}

		public synchronized int maxShield() {

			if (armor != null && armor.isEquipped((Hero)target) && armor.checkSeal() != null) {
				return armor.checkSeal().maxShield(armor.tier, armor.等级());
			} else {
				return 0;
			}
		}

		public static final String COOLDOWN = "cooldown";
		public static final String TURNS_SINCE_ENEMIES = "turns_since_enemies";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(COOLDOWN, cooldown);
			bundle.put(TURNS_SINCE_ENEMIES, turnsSinceEnemies);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			if (bundle.contains(COOLDOWN)) {
				cooldown = bundle.getInt(COOLDOWN);
				turnsSinceEnemies = bundle.getFloat(TURNS_SINCE_ENEMIES);

			//if we have shield from pre-3.1, have it last a bit
			} else if (护盾量() > 0) {
				turnsSinceEnemies = -100;
			}
		}
	}
}
