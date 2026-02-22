

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.关刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.单手剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.双刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.双剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.变态刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.回旋镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.圆盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.地裂镰;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰鱼剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨型方盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.弯刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战镰;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.斩马刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无影剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.日炎链刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.暗杀之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武士刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.死神镰刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流火;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.爪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.白带;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.真铜短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.破败王剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.硬头锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碎缘剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.符文之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.腥红散华;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.臂铠;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蜜剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血姬;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血砍刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.轮刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.配刺剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.重锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金纹拐;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铁头棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.链枷;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.锈右斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.锻造锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镶钉手套;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长矛;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.魔岩拳套;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

abstract public class KindOfWeapon extends EquipableItem {

	public String hitSound = Assets.Sounds.HIT;
	protected float hitSoundPitch = 1f;
	public float 伏击=0;
	public float 流血 =0;
	public float 吸血 =0;
	public int 最小= 0;
	public int 最大= 0;
	public float 伤害= 1f;
	public boolean 拳套(){
		if(this instanceof 镶钉手套)return true;
		if(this instanceof 爪)return true;
		if(this instanceof 血姬)return true;
		if(this instanceof 白带)return true;
		if(this instanceof 臂铠)return true;
		if(this instanceof 魔岩拳套)return true;
		return false;
	}
	public boolean 剑(){
		if(this instanceof 短剑)return true;
		if(this instanceof 真铜短剑)return true;
		if(this instanceof 单手剑)return true;
		if(this instanceof 长剑)return true;
		if(this instanceof 巨剑)return true;
		if(this instanceof 英雄断剑)return true;
		if(this instanceof 无尽之刃)return true;
		if(this instanceof 饮血之剑)return true;
		if(this instanceof 破败王剑)return true;
		if(this instanceof 火焰剑)return true;
		if(this instanceof 流火)return true;
		if(this instanceof 蜜剑)return true;
		if(this instanceof 碎缘剑)return true;
		if(this instanceof 符文之刃)return true;
		if(this instanceof 暗杀之刃)return true;
		if(this instanceof 寒冰鱼剑)return true;
		if(this instanceof 无影剑)return true;
		if(this instanceof 配刺剑)return true;
		if(this instanceof 碧蓝巨剑)return true;
		if(this instanceof 铜钱剑)return true;
		return false;
	}
	public boolean 刀(){
		if(this instanceof 双刃)return true;
		if(this instanceof 弯刀)return true;
		if(this instanceof 变态刀)return true;
		if(this instanceof 血砍刀)return true;
		if(this instanceof 镜刃)return true;
		if(this instanceof 吸血刀)return true;
		return false;
	}
	public boolean 斧(){
		if(this instanceof 手斧)return true;
		if(this instanceof 战斧)return true;
		if(this instanceof 巨斧)return true;
		if(this instanceof 锈右斧)return true;
		return false;
	}
	public boolean 锤(){
		if(this instanceof 重锤)return true;
		if(this instanceof 硬头锤)return true;
		if(this instanceof 战锤)return true;
		if(this instanceof 链枷)return true;
		if(this instanceof 权杖)return true;
		if(this instanceof 锻造锤)return true;
		return false;
	}
	public boolean 鞭(){
		if(this instanceof 灵鞭)return true;
		if(this instanceof 长鞭)return true;
		if(this instanceof 日炎链刃)return true;
		return false;
	}
	public boolean 盾(){
		if(this instanceof 冰门重盾)return true;
		if(this instanceof 圆盾)return true;
		if(this instanceof 巨型方盾)return true;
		return false;
	}
	public boolean 棍(){
		if(this instanceof 铁头棍)return true;
		if(this instanceof 金纹拐)return true;
		if(this instanceof 闪电双截棍)return true;
		return false;
	}
	public boolean 长矛(){
		if(this instanceof 长矛)return true;
		if(this instanceof 三叉戟)return true;
		if(this instanceof 关刀)return true;
		if(this instanceof 白带)return true;
		if(this instanceof 臂铠)return true;
		if(this instanceof 魔岩拳套)return true;
		return false;
	}
	public boolean 回旋镖(){
		if(this instanceof 修理扳手)return true;
		if(this instanceof 血砍刀)return true;
		if(this instanceof 回旋镖)return true;
		if(this instanceof 轮刃)return true;
		return false;
	}
	public boolean 双手(){
		if(this instanceof 轮刃)return true;
		if(this instanceof 镶钉手套)return true;
		if(this instanceof 铁头棍)return true;
		if(this instanceof 爪)return true;
		if(this instanceof 武士刀)return true;
		if(this instanceof 斩马刀)return true;
		if(this instanceof 长剑)return true;
		if(this instanceof 战斧)return true;
		if(this instanceof 巨型方盾)return true;
		if(this instanceof 双剑)return true;
		if(this instanceof 巨剑)return true;
		if(this instanceof 战锤)return true;
		if(this instanceof 巨斧)return true;
		if(this instanceof 魔岩拳套)return true;
		if(this instanceof 战镰)return true;
		if(this instanceof 关刀)return true;
		if(this instanceof 英雄断剑)return true;
		if(this instanceof 无尽之刃)return true;
		if(this instanceof 饮血之剑)return true;
		if(this instanceof 死神镰刀)return true;
		if(this instanceof 日炎链刃)return true;
		if(this instanceof 地裂镰)return true;
		if(this instanceof 寒冰镖)return true;
		if(this instanceof 闪电双截棍)return true;
		if(this instanceof 腥红散华)return true;
		if(this instanceof 重锤)return true;
		if(this instanceof 灵能短弓)return true;
		if(this instanceof 白带)return true;
		if(this instanceof 臂铠)return true;
		if(this instanceof 长矛)return true;
		if(this instanceof 血姬)return true;
		if(this instanceof 十字弩)return true;

		return false;
	}
	@Override
	public void execute(Hero hero, String action) {
		if (hero.subClass == HeroSubClass.武器大师&&action.equals(AC_EQUIP)){
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
		
		if (hero.subClass(HeroSubClass.武器大师)) {
			return 0;
		}
		return isSwiftEquipping ? 0f : super.timeToEquip(hero);
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		
		detachAll( hero.belongings.backpack );
		
		if (hero.belongings.weapon == null || hero.belongings.weapon.doUnequip( hero, true )) {
			
			hero.belongings.weapon = (Weapon)this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(KindOfWeapon.class, "equip_cursed") );
				Dungeon.hero.sprite.哭泣();
			}
			
			hero.spendAndNext( timeToEquip(hero) );
			if (hero.subClass(HeroSubClass.武器大师)) {
				GLog.i(Messages.get(this, "swift_equip"));
			}
			return true;
			
		} else {
			放背包( hero.belongings.backpack );
			return false;
		}
	}

	public boolean equipSecondary( Hero hero ){


		boolean wasInInv = hero.belongings.contains(this);
		detachAll( hero.belongings.backpack );

		if (hero.belongings.secondWep == null || hero.belongings.secondWep.doUnequip( hero, true )) {

			hero.belongings.secondWep = (Weapon)this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.n( Messages.get(KindOfWeapon.class, "equip_cursed") );
				Dungeon.hero.sprite.哭泣();
			}

			hero.spendAndNext( timeToEquip(hero) );
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
				hero.belongings.secondWep = (Weapon)this;
			}
			return false;

		}
	}

	public float 最小攻击(){
		return 最小攻击(强化等级());
	}

	public float 最大攻击(){
		return 最大攻击(强化等级());
	}
	public float 最小投掷攻击(){
		return 最小投掷攻击(强化等级());
	}

	public float 最大投掷攻击(){
		return 最大投掷攻击(强化等级());
	}

	abstract public float 最小攻击(int lvl);
	abstract public float 最大攻击(int lvl);
	abstract public float 最小投掷攻击(int lvl);
	abstract public float 最大投掷攻击(int lvl);
	
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
		int reach = owner.攻击范围();
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

	public float defenseFactor( Char owner ) {
		return 0;
	}
	
	public float 攻击时(Char attacker, Char defender, float damage ) {
		
		if (attacker instanceof Hero hero){
			if(defender!=null&&defender.第x次防御==1&&长矛()){
				return 最大投掷攻击();
			}
			if(defender!=null&&伏击>0&&defender instanceof Mob&&((Mob)defender).surprisedBy(hero)){
				damage+=(最大攻击()-最小攻击())*伏击*(hero.符文("升级暗杀之刃")&&this instanceof 暗杀之刃&&hero.暴击(defender,1)>1?hero.暴击伤害():1);

			}
		}
		return damage;
	}
	public float 投掷攻击时(Char attacker, Char defender, float damage ) {
		if (attacker instanceof Hero hero){
			if(defender!=null&&defender.第x次防御==1&&长矛()){
				return 最大投掷攻击();
			}

			if(defender!=null&&伏击>0&&defender instanceof Mob&&((Mob)defender).surprisedBy(hero)){
				damage+=(最大投掷攻击()-最小投掷攻击())*伏击*(hero.符文("升级暗杀之刃")&&this instanceof 暗杀之刃&&hero.暴击(defender,1)>1?hero.暴击伤害():1);
			}
		}
		return damage;
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(hitSound, 1, pitch * hitSoundPitch);
	}
	
}
