

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

abstract public class KindOfWeapon extends EquipableItem {

	protected String hitSound = Assets.Sounds.HIT;
	protected float hitSoundPitch = 1f;
	
	@Override
	public void execute(Hero hero, String action) {
		if (hero.subClass == HeroSubClass.CHAMPION && action.equals(AC_EQUIP)){
			usesTargeting = false;
			String primaryName = Messages.titleCase(hero.belongings.weapon != null ? hero.belongings.weapon.trueName() : Messages.get(KindOfWeapon.class, "empty"));
			String secondaryName = Messages.titleCase(hero.belongings.secondWep != null ? hero.belongings.secondWep.trueName() : Messages.get(KindOfWeapon.class, "empty"));
			if (primaryName.length() > 18) primaryName = primaryName.substring(0, 15) + "...";
			if (secondaryName.length() > 18) secondaryName = secondaryName.substring(0, 15) + "...";
			GameScene.show(new WndOptions(
					new ItemSprite(this),
					Messages.titleCase(name()),
					Messages.get(KindOfWeapon.class, "which_equip_msg"),
					Messages.get(KindOfWeapon.class, "which_equip_primary", primaryName),
					Messages.get(KindOfWeapon.class, "which_equip_secondary", secondaryName)
			){
				@Override
				protected void onSelect(int index) {
					super.onSelect(index);
					if (index == 0 || index == 1){
						//In addition to equipping itself, item reassigns itself to the quickslot
						//This is a special case as the item is being removed from inventory, but is staying with the hero.
						int slot = Dungeon.quickslot.getSlot( KindOfWeapon.this );
						slotOfUnequipped = -1;
						if (index == 0) {
							doEquip(hero);
						} else {
							equipSecondary(hero);
						}
						if (slot != -1) {
							Dungeon.quickslot.setSlot( slot, KindOfWeapon.this );
							updateQuickslot();
						//if this item wasn't quickslotted, but the item it is replacing as equipped was
						//then also have the item occupy the unequipped item's quickslot
						} else if (slotOfUnequipped != -1 && defaultAction() != null) {
							Dungeon.quickslot.setSlot( slotOfUnequipped, KindOfWeapon.this );
							updateQuickslot();
						}
					}
				}
			});
		} else {
			super.execute(hero, action);
		}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null && (hero.belongings.weapon() == this || hero.belongings.secondWep() == this);
	}

	private static boolean isSwiftEquipping = false;

	protected float timeToEquip( Hero hero ) {
		return isSwiftEquipping ? 0f : super.timeToEquip(hero);
	}
	
	@Override
	public boolean doEquip( Hero hero ) {

		isSwiftEquipping = false;
		if (hero.belongings.contains(this) && hero.有天赋(Talent.SWIFT_EQUIP)){
			if (hero.buff(Talent.SwiftEquipCooldown.class) == null
					|| hero.buff(Talent.SwiftEquipCooldown.class).hasSecondUse()){
				isSwiftEquipping = true;
			}
		}

		// 15/25% chance
		if (hero.heroClass != HeroClass.CLERIC && hero.有天赋(Talent.HOLY_INTUITION)
				&& cursed && !cursedKnown
				&& Random.Int(3) < hero.天赋点数(Talent.HOLY_INTUITION)){
			cursedKnown = true;
			if(hero.满天赋(Talent.HOLY_INTUITION)){
				鉴定();
				祛邪卷轴.净化(hero,this);
			}else{
				鉴定();
			}
			GLog.p(Messages.get(this, "curse_detected"));
			return false;
		}

		detachAll( hero.belongings.backpack );
		
		if (hero.belongings.weapon == null || hero.belongings.weapon.doUnequip( hero, true )) {
			
			hero.belongings.weapon = this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(KindOfWeapon.class, "equip_cursed") );
			}

			hero.spendAndNext( timeToEquip(hero) );
			if (isSwiftEquipping) {
				GLog.i(Messages.get(this, "swift_equip"));
				if (hero.buff(Talent.SwiftEquipCooldown.class) == null){
					Buff.施加(hero, Talent.SwiftEquipCooldown.class, 24-hero.天赋点数(Talent.SWIFT_EQUIP,5))
							.secondUse = hero.天赋点数(Talent.SWIFT_EQUIP) >= 2;
				} else if (hero.buff(Talent.SwiftEquipCooldown.class).hasSecondUse()) {
					hero.buff(Talent.SwiftEquipCooldown.class).secondUse = false;
				}
				isSwiftEquipping = false;
			}
			return true;
			
		} else {
			isSwiftEquipping = false;
			放背包( hero.belongings.backpack );
			return false;
		}
	}

	public boolean equipSecondary( Hero hero ){

		isSwiftEquipping = false;
		if (hero.belongings.contains(this) && hero.有天赋(Talent.SWIFT_EQUIP)){
			if (hero.buff(Talent.SwiftEquipCooldown.class) == null
					|| hero.buff(Talent.SwiftEquipCooldown.class).hasSecondUse()){
				isSwiftEquipping = true;
			}
		}

		boolean wasInInv = hero.belongings.contains(this);
		detachAll( hero.belongings.backpack );

		if (hero.belongings.secondWep == null || hero.belongings.secondWep.doUnequip( hero, true )) {

			hero.belongings.secondWep = this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(KindOfWeapon.class, "equip_cursed") );
			}

			hero.spendAndNext( timeToEquip(hero) );
			if (isSwiftEquipping) {
				GLog.i(Messages.get(this, "swift_equip"));
				if (hero.buff(Talent.SwiftEquipCooldown.class) == null){
					Buff.施加(hero, Talent.SwiftEquipCooldown.class, 24f-hero.天赋点数(Talent.SWIFT_EQUIP,5))
							.secondUse = hero.天赋点数(Talent.SWIFT_EQUIP) >= 2;
				} else if (hero.buff(Talent.SwiftEquipCooldown.class).hasSecondUse()) {
					hero.buff(Talent.SwiftEquipCooldown.class).secondUse = false;
				}
				isSwiftEquipping = false;
			}
			return true;

		} else {
			isSwiftEquipping = false;
			放背包( hero.belongings.backpack );
			return false;
		}
	}

	@Override
	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {
		boolean second = hero.belongings.secondWep == this;

		if (second){
			//do this first so that the item can go to a full inventory
			hero.belongings.secondWep = null;
		}

		if (super.doUnequip( hero, collect, single )) {

			if (!second){
				hero.belongings.weapon = null;
			}
			return true;

		} else {

			if (second){
				hero.belongings.secondWep = this;
			}
			return false;

		}
	}

	public int 最小攻击(){
		return 最小攻击(buffedLvl());
	}

	public int 最大攻击(){
		return 最大攻击(buffedLvl());
	}

	abstract public int 最小攻击(int lvl);
	abstract public int 最大攻击(int lvl);

	public int damageRoll( Char owner ) {
		if (owner instanceof Hero){
			return Hero.heroDamageIntRange(最小攻击(), 最大攻击());
		} else {
			return Random.NormalIntRange(最小攻击(), 最大攻击());
		}
	}
	
	public float accuracyFactor( Char owner, Char target ) {
		return 1f;
	}
	
	public float delayFactor( Char owner ) {
		return 1f;
	}

	public int reachFactor( Char owner ){
		return 1;
	}
	
	public boolean canReach( Char owner, int target){
		int reach = reachFactor(owner);
		if (Dungeon.level.distance( owner.pos, target ) > reach){
			return false;
		} else {
			boolean[] passable = BArray.not(Dungeon.level.solid, null);
			for (Char ch : Actor.chars()) {
				if (ch != owner) passable[ch.pos] = false;
			}
			
			PathFinder.buildDistanceMap(target, passable, reach);
			
			return PathFinder.distance[owner.pos] <= reach;
		}
	}

	public int defenseFactor( Char owner ) {
		return 0;
	}
	
	public int 攻击时(Char attacker, Char defender, int damage ) {
		return damage;
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(hitSound, 1, pitch * hitSoundPitch);
	}
	
}
