

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 干涸绝露 extends Artifact {


	{
		image = 物品表.干涸绝露;

		levelCap = 10;

		charge = 3 + 等级()/2;
		partialCharge = 0;
		chargeCap = 3 + 等级()/2;
	}
	@Override
	public String defaultAction(){
		if (charge==0) {
			return AC_STORE;
		}
		return AC_SNACK;
	}

	private int 种子能量= 0;

	public static final String AC_SNACK = "SNACK";
	public static final String AC_EAT = "EAT";
	public static final String AC_STORE = "STORE";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (hero.buff(MagicImmune.class) != null) return actions;
		if(!(hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云)))
		if ((isEquipped( hero ) || hero.天赋(Talent.轻便斗篷) )&& charge > 0) {
			actions.add(AC_SNACK);
			actions.add(AC_EAT);
		}
		if ((isEquipped( hero ) || hero.天赋(Talent.轻便斗篷) ) && 等级() < levelCap && !cursed) {
			actions.add(AC_STORE);
		}
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute(hero, action);

		if (hero.buff(MagicImmune.class) != null) return;

		if (action.equals(AC_EAT) || action.equals(AC_SNACK)){

			if (!isEquipped(hero) && !hero.天赋(Talent.祸乱绝露)) GLog.i(Messages.get(Artifact.class,"need_to_equip"));
			else if (charge == 0)  GLog.i( Messages.get(this, "no_food") );
			else {
				//consume as much food as it takes to be full, to a minimum of 1
				float chargesToUse = 2;

				if (chargesToUse > charge) chargesToUse = charge;

				//always use 1 charge if snacking
				if (action.equals(AC_SNACK)){
					chargesToUse = 1;
				}

				doEatEffect(hero, chargesToUse);
			}

		} else if (action.equals(AC_STORE)){

			GameScene.selectItem(itemSelector);

		}
	}

	public void doEatEffect(Hero hero, float chargesToUse){

		charge -= chargesToUse;
		Talent.onArtifactUsed(hero);

		hero.sprite.operate();
		hero.busy();

		hero.回百分比血(chargesToUse*0.035f);
		for(Mob m:hero.getVisibleEnemies()){
			if(m instanceof Mob&&m.alignment==Char.Alignment.ENEMY){
				m.受伤时(hero.魔力(0.45f)*chargesToUse,this);
			}
		}

		Sample.INSTANCE.play(Assets.Sounds.CURSED);
		GLog.i( Messages.get(this, "eat") );


		hero.spend(1);


		if (charge >= 6)   image = 物品表.干涸绝露3;
		else if (charge >= 3)   image = 物品表.干涸绝露2;
		else                    image = 物品表.干涸绝露;

		updateQuickslot();
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new hornRecharge();
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			if (!collect || !hero.天赋(Talent.祸乱绝露)){
				if (activeBuff != null){
					activeBuff.detach();
					activeBuff = null;
				}
			} else {
				activate(hero);
			}

			return true;
		} else
			return false;
	}
	@Override
	public boolean 放背包(Bag container ) {
		if (super.放背包(container)){
			if (container.owner instanceof Hero
				&& passiveBuff == null
				&& ((Hero) container.owner).天赋(Talent.祸乱绝露)){
				activate((Hero) container.owner);
			}
			return true;
		} else{
			return false;
		}
	}
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null){
			if (!isEquipped(target)) amount *= target.天赋点数(Talent.祸乱绝露,0.25f);
			partialCharge += 0.25f*amount;
			while (partialCharge >= 1){
				partialCharge--;
				charge++;
				
				if (charge == chargeCap){
					GLog.p( Messages.get(干涸绝露.class,"full"));
					partialCharge = 0;
				}

				if (charge >= 6)   image = 物品表.干涸绝露3;
				else if (charge >= 3)   image = 物品表.干涸绝露2;
				else                    image = 物品表.干涸绝露;

				updateQuickslot();
			}
		}
	}
	
	@Override
	public String desc() {
		String desc = super.desc();

		if ( isEquipped( Dungeon.hero ) ){
			if (!cursed) {
				if (等级() < levelCap)
					desc += "\n\n" +Messages.get(this, "desc_hint");
			} else {
				desc += "\n\n" +Messages.get(this, "desc_cursed");
			}
		}

		return desc;
	}

	@Override
	public void 等级(int value) {
		super.等级(value);
		chargeCap = 5 + 等级()/2;
	}

	@Override
	public Item 升级() {
		super.升级();
		chargeCap = 5 + 等级()/2;
		return this;
	}
	
	public void gainValue(Plant.Seed seed){
		if (等级() >= 10) return;

		种子能量+= 1;
		//Pasties and phantom meat are worth two upgrades instead of 1.5, meat pies are worth 4 instead of 3!
		if (seed instanceof Rotberry.Seed){
			种子能量+=1;
		}
		if (种子能量>=3){
			int upgrades =种子能量/3;
			upgrades = Math.min(upgrades, 10 - 等级());
			升级(upgrades);
			Catalog.countUse(干涸绝露.class);
			种子能量-=upgrades;
			if (等级() == 10){
				种子能量= 0;
				GLog.p( Messages.get(this, "maxlevel") );
			} else {
				GLog.p( Messages.get(this, "levelup") );
			}
		} else {
			GLog.i( Messages.get(this, "feed") );
		}
	}
	
	private static final String STORED = "stored";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STORED,种子能量);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		种子能量= bundle.getInt(STORED);

		if (charge >= 6)  image = 物品表.干涸绝露3;
		else if (charge >= 3)   image = 物品表.干涸绝露2;
	}

	public class hornRecharge extends ArtifactBuff{

		@Override
		public boolean act() {
			if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null) {
				if (activeBuff == null&&再生.regenOn()) {
					float missing = (chargeCap - charge);
					if (等级() > 7) missing += 5*(等级() - 7)/3f;
					float turnsToCharge = (45 - missing);
					turnsToCharge /= 能量之戒.artifactChargeMultiplier(target);
					float chargeToGain = (1f / turnsToCharge);
					if (!isEquipped(Dungeon.hero)){
						chargeToGain *= Dungeon.hero.天赋点数(Talent.祸乱绝露,0.25f);
					}

					partialCharge += chargeToGain;
				}

				while (partialCharge >= 1) {
					charge++;
					partialCharge -= 1;
					if (charge == chargeCap){
						partialCharge = 0;
					}

				}
			} else {
				partialCharge = 0;
			}

			if (cooldown > 0)
				cooldown --;

			updateQuickslot();

			spend( TICK );

			return true;
		}

	}

	protected static WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(干涸绝露.class,"prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return Belongings.Backpack.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Plant.Seed;
		}

		@Override
		public void onSelect( Item item ) {
			if (item != null && item instanceof Plant.Seed) {
					Hero hero = Dungeon.hero;
					hero.sprite.operate( hero.pos );
					hero.busy();
					hero.spend( 1 );

					((干涸绝露)curItem).gainValue(((Plant.Seed)item));
					item.detach(hero.belongings.backpack);

			}
		}
	};
}
