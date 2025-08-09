

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.darts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Crossbow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Dart extends MissileWeapon {

	{
		levelKnown = true;

		image = 物品表.DART;
		hitSound = Assets.Sounds.HIT_ARROW;
		hitSoundPitch = 1.3f;
		
		tier = 1;
		
		//infinite, even with penalties
		baseUses = 1000;

		//all darts share a set ID
		setID = 0L;
	}
	
	protected static final String AC_TIP = "TIP";
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_TIP );
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		if (action.equals(AC_TIP)){
			GameScene.selectItem(itemSelector);
		}
	}
	
	@Override
	public int min(int lvl) {
		if (bow != null){
			if (!(this instanceof TippedDart) && Dungeon.hero.buff(Crossbow.ChargedShot.class) != null){
				//ability increases base dmg by 50%, scaling by 50%
				return  8 +                     //8 base
						2*bow.buffedLvl() + lvl;//+2 per bow level, +1 per level
			} else {
				return  4 +                     //4 base
						bow.buffedLvl() + lvl;  //+1 per level or bow level
			}
		} else {
			return  1 +     //1 base, down from 2
					lvl;    //scaling unchanged
		}
	}

	@Override
	public int max(int lvl) {
		if (bow != null){
			if (!(this instanceof TippedDart) && Dungeon.hero.buff(Crossbow.ChargedShot.class) != null){
				//ability increases base dmg by 50%, scaling by 50%
				return  16 +                       //16 base
						4*bow.buffedLvl() + 2*lvl; //+4 per bow level, +2 per level
			} else {
				return  12 +                       //12 base
						3*bow.buffedLvl() + 2*lvl; //+3 per bow level, +2 per level
			}
		} else {
			return  2 +     //2 base, down from 5
					2*lvl;  //scaling unchanged
		}
	}
	
	protected static Crossbow bow;
	
	private void updateCrossbow(){
		if (Dungeon.hero == null) {
			bow = null;
		} else if (Dungeon.hero.belongings.weapon() instanceof Crossbow){
			bow = (Crossbow) Dungeon.hero.belongings.weapon();
		} else if (Dungeon.hero.belongings.secondWep() instanceof Crossbow) {
			//player can instant swap anyway, so this is just QoL
			bow = (Crossbow) Dungeon.hero.belongings.secondWep();
		} else {
			bow = null;
		}
	}

	public boolean crossbowHasEnchant( Char owner ){
		return bow != null && bow.enchantment != null && owner.buff(MagicImmune.class) == null;
	}
	
	@Override
	public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
		if (bow != null && bow.hasEnchant(type, owner)){
			return true;
		} else {
			return super.hasEnchant(type, owner);
		}
	}

	@Override
	public float accuracyFactor(Char owner, Char target) {
		//don't update xbow here, as dart is the active weapon atm
		if (bow != null && owner.buff(Crossbow.ChargedShot.class) != null){
			return Char.INFINITE_ACCURACY;
		} else {
			return super.accuracyFactor(owner, target);
		}
	}

	@Override
	public int 攻击时(Char attacker, Char defender, int damage) {
		if (bow != null && !processingChargedShot){
			damage = bow.攻击时(attacker, defender, damage);
		}

		int dmg = super.攻击时(attacker, defender, damage);
		if (!processingChargedShot) {
			processChargedShot(defender, damage);
		}
		return dmg;
	}

	@Override
	public int throwPos(Hero user, int dst) {
		updateCrossbow();
		return super.throwPos(user, dst);
	}

	@Override
	protected void onThrow(int cell) {
		updateCrossbow();
		//we have to set this here, as on-hit effects can move the target we aim at
		chargedShotPos = cell;
		super.onThrow(cell);
	}

	protected boolean processingChargedShot = false;
	private int chargedShotPos;
	protected void processChargedShot( Char target, int dmg ){
		//don't update xbow here, as dart may be the active weapon atm
		processingChargedShot = true;
		if (chargedShotPos != -1 && bow != null && Dungeon.hero.buff(Crossbow.ChargedShot.class) != null) {
			PathFinder.buildDistanceMap(chargedShotPos, Dungeon.level.passable, 3);
			//necessary to clone as some on-hit effects use Pathfinder
			int[] distance = PathFinder.distance.clone();
			for (Char ch : Actor.chars()){
				if (ch == target){
					Actor.add(new Actor() {
						{ actPriority = VFX_PRIO; }
						@Override
						protected boolean act() {
							if (!ch.isAlive()){
								bow.onAbilityKill(Dungeon.hero, ch);
							}
							Actor.remove(this);
							return true;
						}
					});
				} else if (distance[ch.pos] != Integer.MAX_VALUE){
					this.攻击时(Dungeon.hero, ch, dmg);
				}
			}
		}
		chargedShotPos = -1;
		processingChargedShot = false;
	}

	@Override
	protected void decrementDurability() {
		super.decrementDurability();
		if (Dungeon.hero.buff(Crossbow.ChargedShot.class) != null) {
			Dungeon.hero.buff(Crossbow.ChargedShot.class).detach();
		}
	}

	@Override
	public void throwSound() {
		updateCrossbow();
		if (bow != null) {
			Sample.INSTANCE.play(Assets.Sounds.ATK_CROSSBOW, 1, Random.Float(0.87f, 1.15f));
		} else {
			super.throwSound();
		}
	}
	
	@Override
	public String info() {
		updateCrossbow();
		if (bow != null && !bow.isIdentified()){
			Crossbow realBow = bow;
			//create a temporary bow for IDing purposes
			bow = new Crossbow();
			String info = super.info();
			bow = realBow;
			return info;
		} else {
			return super.info();
		}
	}
	
	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int defaultQuantity() {
		return 2;
	}

	@Override
	public int value() {
		return Math.round(super.value()/2f); //half normal value
	}
	
	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(Dart.class, "prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return 绒布袋.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Plant.Seed;
		}

		@Override
		public void onSelect(final Item item) {
			
			if (item == null) return;
			
			final int maxToTip = Math.min(curItem.数量(), item.数量()*2);
			final int maxSeedsToUse = (maxToTip+1)/2;
			
			final int singleSeedDarts;
			
			final String[] options;
			
			if (curItem.数量() == 1){
				singleSeedDarts = 1;
				options = new String[]{
						Messages.get(Dart.class, "tip_one"),
						Messages.get(Dart.class, "tip_cancel")};
			} else {
				singleSeedDarts = 2;
				if (maxToTip <= 2){
					options = new String[]{
							Messages.get(Dart.class, "tip_two"),
							Messages.get(Dart.class, "tip_cancel")};
				} else {
					options = new String[]{
							Messages.get(Dart.class, "tip_all", maxToTip, maxSeedsToUse),
							Messages.get(Dart.class, "tip_two"),
							Messages.get(Dart.class, "tip_cancel")};
				}
			}
			
			TippedDart tipResult = TippedDart.getTipped((Plant.Seed) item, 1);
			
			GameScene.show(new WndOptions( new ItemSprite(item),
					Messages.titleCase(item.name()),
					Messages.get(Dart.class, "tip_desc", tipResult.name()) + "\n\n" + tipResult.desc(),
					options){
				
				@Override
				protected void onSelect(int index) {
					super.onSelect(index);
					
					if (index == 0 && options.length == 3){
						if (item.数量() <= maxSeedsToUse){
							item.detachAll( curUser.belongings.backpack );
						} else {
							item.数量(item.数量() - maxSeedsToUse);
						}
						
						if (maxToTip < curItem.数量()){
							curItem.数量(curItem.数量() - maxToTip);
						} else {
							curItem.detachAll(curUser.belongings.backpack);
						}
						
						TippedDart newDart = TippedDart.getTipped((Plant.Seed) item, maxToTip);
						if (!newDart.放背包()) Dungeon.level.drop(newDart, curUser.pos).sprite.drop();
						
						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate(curUser.pos);
						
					} else if ((index == 1 && options.length == 3) || (index == 0 && options.length == 2)){
						item.detach( curUser.belongings.backpack );
						
						if (curItem.数量() <= singleSeedDarts){
							curItem.detachAll( curUser.belongings.backpack );
						} else {
							curItem.数量(curItem.数量() - singleSeedDarts);
						}
						
						TippedDart newDart = TippedDart.getTipped((Plant.Seed) item, singleSeedDarts);
						if (!newDart.放背包()) Dungeon.level.drop(newDart, curUser.pos).sprite.drop();
						
						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate(curUser.pos);
					}
				}
			});
			
		}
		
	};
}
