

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.魔法飞刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.*;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.冰结短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.冲锋枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.圣银十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.暗裔短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.枪械;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.火炮;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.炼金动力十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.狙击枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.自然之力;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.霰弹枪;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

abstract public class KindOfWeapon extends EquipableItem {

	public String hitSound = Assets.Sounds.HIT;
	protected float hitSoundPitch = 1f;
	public float 伏击=0;
	public float 流血 =0;
	public float 魔法 =0;
	public float 吸血 =0;
	public float 首攻 =0;
	public float 麻痹 =0;
	public float 破甲 =0;
	public float 冻结 =0;
	public float 中毒=0;
	public int 最小= 0;
	public int 最大= 0;
	public float 伤害= 1f;
	public float 投掷= 1f;
	public float 防御= 1f;
	public boolean 具备防御= false;
	public float 伤害(){
		return 伤害;
	}
	public float 防御(){
		return 防御;
	}

	public float 投掷(){
		float 投掷=this.投掷;
		投掷*=1.45f;
		投掷*=魔法飞刀.投掷();

		if(Dungeon.hero()&&Dungeon.hero.belongings.weapon(投掷手套.class))
			投掷*=1.45f;

		if(Dungeon.hero()&&Dungeon.hero.heroClass(HeroClass.女忍)) 投掷+=.2f;
		return 投掷;
	}

	public float 流血(){
		float 流血=this.流血;
		return 流血;
	}

	public float 首攻(){
		float 首攻=this.首攻;
		return 首攻;
	}

	public boolean 迅速(){
		if(this instanceof 苦无||
		   this instanceof 金玫苦无||
		   this instanceof 小刀||
		   this instanceof 飞镖||
		   this instanceof 手里剑||
		   this instanceof 寒冰镖
		   )
		return true;

		return false;
	}
	public float 破甲(){
		float 破甲=this.破甲;
		if(this instanceof 小刺||
		   this instanceof 破甲锥||
		   this instanceof 双锥
		   )破甲+=0.75f;

		return 破甲;
	}

	public float 麻痹(){
		float 麻痹=this.麻痹;
		return 麻痹;
	}

	public float 冻结(){
		float 冻结=this.冻结;
		return 冻结;
	}
	public float 中毒(){
		float 毒素=this.中毒;
		return 毒素;
	}

	public float 吸血(){
		float 吸血=this.吸血;
		return 吸血;
	}
	public float 魔法(){
		float 魔法=this.魔法;
		if(Dungeon.符文("越女剑法"))魔法+=0.25f;
		return 魔法;
	}

	public float 伏击(){
		float 伏击=this.伏击;
		return 伏击;
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
	public boolean 延迟自动转= true;
	public boolean 拳套(){
		if(this instanceof 致胜拳炮)return true;
		if(this instanceof 镶钉手套)return true;
		if(this instanceof 爪)return true;
		if(this instanceof 血姬)return true;
		if(this instanceof 白带)return true;
		if(this instanceof 指虎)return true;
		if(this instanceof 臂铠)return true;
		if(this instanceof 星云拳套)return true;
		if(this instanceof 投掷手套)return true;
		if(this instanceof 魔岩拳套)return true;
		return false;
	}
	public boolean 镖(){
		if(this instanceof 飞镖)return true;
		if(this instanceof 手里剑)return true;
		if(this instanceof 苦无)return true;
		if(this instanceof 寒冰镖)return true;
		if(this instanceof 金玫苦无)return true;
		return false;
	}
	public boolean 匕首(){
		if(this instanceof 变态刀)return true;
		if(this instanceof 匕首)return true;
		if(this instanceof 双刃)return true;
		if(this instanceof 指虎)return true;
		if(this instanceof 小刺)return true;
		if(this instanceof 骨刀)return true;
		if(this instanceof 破甲锥)return true;
		if(this instanceof 长匕首)return true;
		if(this instanceof 臻冰刃)return true;
		return false;
	}
	public boolean 短剑(){
		if(this instanceof 短剑)return true;
		if(this instanceof 真铜短剑)return true;
		if(this instanceof 英雄断剑)return true;
		if(this instanceof 无影剑)return true;
		if(this instanceof 未知武器)return true;
		return false;
	}
	public boolean 剑(){
		if(Dungeon.hero()&&Dungeon.hero.subClass(HeroSubClass.冰魄剑神))return true;
		if(Dungeon.符文("越女剑法"))return true;
		if(短剑())return true;
		if(巨剑())return true;
		if(this instanceof 圣剑)return true;
		if(this instanceof 黑暗剑)return true;
		if(this instanceof 锯齿剑)return true;
		if(this instanceof 单手剑)return true;
		if(this instanceof 长剑)return true;
		if(this instanceof 无限之剑)return true;
		if(this instanceof 火焰剑)return true;
		if(this instanceof 流火)return true;
		if(this instanceof 蜜剑)return true;
		if(this instanceof 碎缘剑)return true;
		if(this instanceof 符文之刃)return true;
		if(this instanceof 暗杀之刃)return true;
		if(this instanceof 寒冰鱼剑)return true;
		if(this instanceof 配刺剑)return true;
		if(this instanceof 铜钱剑)return true;
		if(this instanceof 裂天剑)return true;
		if(this instanceof 玉龙)return true;
		if(this instanceof 虚哭神去)return true;
		return false;
	}
	public boolean 刀(){
		if(this instanceof 菱形刀)return true;
		if(this instanceof 尼泊尔弯刀)return true;
		if(this instanceof 弯刀)return true;
		if(this instanceof 骨刀)return true;
		if(this instanceof 变态刀)return true;
		if(this instanceof 血砍刀)return true;
		if(this instanceof 镜刃)return true;
		if(this instanceof 吸血刀)return true;
		if(this instanceof 妖刀村正)return true;
		if(长刀())return true;
		return false;
	}
	public boolean 盾(){
		if(this instanceof 圆盾)return true;
		if(this instanceof 联合盾)return true;
		if(this instanceof 巨型方盾)return true;
		if(this instanceof 冰门重盾)return true;
		return false;
	}

	public boolean 长矛(){
		if(this instanceof 长矛)return true;
		if(this instanceof 三叉戟)return true;
		if(this instanceof 海神三叉戟)return true;
		if(this instanceof 关刀)return true;
		return false;
	}
	public boolean 棍(){
		if(this instanceof 法师魔杖)return true;
		if(this instanceof 权杖)return true;
		if(this instanceof 木棍)return true;
		if(this instanceof 铁头棍)return true;
		if(this instanceof 金纹拐)return true;
		if(this instanceof 封印之杖)return true;
		if(this instanceof 闪电双截棍)return true;
		return false;
	}
	public boolean 钝器(){
		if(盾())return true;
		if(棍())return true;
		if(鞭())return true;
		if(this instanceof 石头)return true;
		if(this instanceof 雪球)return true;
		if(this instanceof 金铲铲)return true;
		if(this instanceof 修理扳手)return true;
		if(this instanceof 回旋镖)return true;
		if(this instanceof 枪械 x&&x.开火效果)return true;
		return false;
	}
	public boolean 鞭(){
		if(this instanceof 灵鞭)return true;
		if(this instanceof 长鞭)return true;
		return false;
	}
	public boolean 锤(){
		if(this instanceof 硬头锤)return true;
		if(this instanceof 链枷)return true;
		if(this instanceof 猪鲨链球)return true;
		if(this instanceof 锻造锤)return true;
		if(this instanceof 战锤)return true;
		if(this instanceof 雷神锤)return true;
		if(this instanceof 龙牙锤)return true;
		if(this instanceof 重锤)return true;
		return false;
	}
	public boolean 镰(){
		if(this instanceof 短柄镰)return true;
		if(this instanceof 战镰)return true;
		if(this instanceof 死神镰刀)return true;
		if(this instanceof 地裂镰)return true;
		return false;
	}
	public boolean 斧(){
		if(this instanceof 手斧)return true;
		if(this instanceof 疯狂斧)return true;
		if(this instanceof 战斧)return true;
		if(this instanceof 巨斧)return true;
		if(this instanceof 锈右斧)return true;
		return false;
	}
	public boolean 巨剑(){
		if(this instanceof 无尽之刃)return true;
		if(this instanceof 破败王剑)return true;
		if(this instanceof 饮血之剑)return true;
		if(this instanceof 碧蓝巨剑)return true;
		if(this instanceof 巨剑)return true;
		return false;
	}
	public boolean 长刀(){
		if(this instanceof 武士刀)return true;
		if(this instanceof 斩马刀)return true;
		return false;
	}
	public boolean 复仇者联盟(){
		if(this instanceof 雷神锤)return true;
		if(this instanceof 联合盾)return true;
		return false;
	}
	public boolean 我的世界(){
		if(this instanceof 下界合金剑)return true;
		if(this instanceof 回旋之刃)return true;
		if(this instanceof 钻石镐)return true;
		if(this instanceof 海神三叉戟)return true;
		if(this instanceof 重锤)return true;
		return false;
	}
	public boolean 幻影忍者(){
		if(this instanceof 菱形刀)return true;
		if(this instanceof 寒冰镖)return true;
		if(this instanceof 闪电双截棍)return true;
		if(this instanceof 地裂镰)return true;
		if(this instanceof 火焰剑)return true;
		return false;
	}
	public boolean 投掷粘(){
		//sticky = true;//默认吸在敌人身上
		if(钝器())return false;
		return true;
	}
	public boolean 回旋镖(){
		if(Dungeon.符文("投影魔术"))return true;
		if(this instanceof 修理扳手)return true;
		if(this instanceof 血砍刀)return true;
		if(this instanceof 回旋镖)return true;
		if(this instanceof 海神三叉戟)return true;
		if(this instanceof 联合盾)return true;
		if(穿透回旋镖())return true;
		return false;
	}
	public boolean 穿透回旋镖(){
		if(this instanceof 回旋之刃)return true;
		if(this instanceof 疯狂斧)return true;
		if(this instanceof 轮刃)return true;
		return false;
	}
	public boolean 双手(){
		if(this instanceof 镶钉手套)return true;
		if(this instanceof 血姬)return true;
		if(this instanceof 白带)return true;
		if(this instanceof 指虎)return true;
		if(this instanceof 臂铠)return true;
		if(this instanceof 星云拳套)return true;
		if(this instanceof 投掷手套)return true;
		if(this instanceof 魔岩拳套)return true;

		if(this instanceof 双刃)return true;
		if(this instanceof 狼筅)return true;

		if(this instanceof 鹿角刀)return true;
		if(this instanceof 誓刃)return true;
		if(this instanceof 木棍)return true;
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

		if(this instanceof 战镰)return true;
		if(this instanceof 关刀)return true;
		if(this instanceof 无尽之刃)return true;
		if(this instanceof 饮血之剑)return true;
		if(this instanceof 下界合金剑)return true;
		if(this instanceof 死神镰刀)return true;
		if(this instanceof 日炎链刃)return true;
		if(this instanceof 地裂镰)return true;
		if(this instanceof 寒冰镖)return true;
		if(this instanceof 闪电双截棍)return true;
		if(this instanceof 猩红散华)return true;
		if(this instanceof 重锤)return true;
		if(this instanceof 长矛)return true;

		if(this instanceof 短弓)return true;
		if(this instanceof 冰结短弓)return true;
		if(this instanceof 暗裔短弓)return true;
		if(this instanceof 灵能短弓)return true;
		if(this instanceof 自然之力)return true;
		if(this instanceof 十字弩)return true;
		if(this instanceof 炼金动力十字弩)return true;
		if(this instanceof 圣银十字弩)return true;

		if(this instanceof 狙击枪)return true;
		if(this instanceof 冲锋枪)return true;
		if(this instanceof 霰弹枪)return true;
		if(this instanceof 火炮)return true;
		if(this instanceof 致胜拳炮)return true;
		if(this instanceof 虚哭神去)return true;

		return false;
	}
	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_EQUIP)){//武器大师


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

							if(hero.belongings.weapon1()==null)doEquip(hero);
							else equipSecondary(hero);

//							if(双手()){
//								doEquip(hero);
//								GLog.橙("你装备了一把双手武器！");
//							}else{
//								if(hero.belongings.weapon1()==null)doEquip(hero);
//								else equipSecondary(hero);
//							}
						}
						if(hero.符文("跟着我左手右手一个慢动作"))hero.回百分比血(0.03f);
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

				@Override
				protected boolean enabled(int index) {
					if(index ==0){
						if(hero.belongings.weapon!=null)
							return !hero.belongings.weapon.cursed;
					}
					else
					{
						if(hero.belongings.secondWep!=null)
							return !hero.belongings.secondWep.cursed;
//						if(hero.belongings.weapon!=null&&hero.belongings.weapon.双手())return false;
//						if(hero.belongings.weapon==null)return false;
					}
					return true;
				}
			});
		} else {
			super.execute(hero, action);
		}
	}

	@Override
	public boolean isEquipped( Hero hero ) {
		return hero != null &&
			   (hero.belongings.weapon1() == this ||hero.belongings.weapon2()==this);
	}
	
	@Override
	public boolean doEquip( Hero hero ) {
		
		detachAll( hero.belongings.backpack );
		
		if (hero.belongings.weapon == null
			|| hero.belongings.weapon.doUnequip( hero, true )) {
			
			hero.belongings.weapon = (Weapon)this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.红(Messages.get(KindOfWeapon.class,"equip_cursed"));
				Dungeon.hero.sprite.哭泣();
			}
			
			hero.spendAndNext( timeToEquip(hero) );

			return true;
			
		} else {
			放背包( hero.belongings.backpack );
			return false;
		}
	}

	public boolean equipSecondary( Hero hero ){


		boolean wasInInv = hero.belongings.contains(this);
		detachAll( hero.belongings.backpack );

		if (hero.belongings.secondWep == null
			|| hero.belongings.secondWep.doUnequip( hero, true )) {

			hero.belongings.secondWep = (Weapon)this;
			activate( hero );
			Talent.装备时(hero, this);
			Badges.validateDuelistUnlock();
			updateQuickslot();

			cursedKnown = true;
			if (cursed) {
				equipCursed( hero );
				GLog.红(Messages.get(KindOfWeapon.class,"equip_cursed"));
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



	protected float timeToEquip( Hero hero ) {
		float x=super.timeToEquip(hero);
		if(hero.符文("跟着我左手右手一个慢动作"))
			x*=3;
		if (hero.subClass(HeroSubClass.武器大师)) {
			x=0;
		}
		return x;
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
		if (Dungeon.level.距离(owner.pos,target)>reach){
			return false;
		} else {
			boolean[] passable = BArray.not(Dungeon.level.solid,null);
			for (Char ch : Actor.chars()) {
				if (ch != owner) passable[ch.pos] = false;
			}

			PathFinder.buildDistanceMap(target,passable,reach);

			return PathFinder.distance[owner.pos] <= reach;
		}
	}

	public float 最大防御(Char owner) {
		return 0;
	}
	public float 最小防御( Char owner ) {
		return 0;
	}


	public boolean 攻击不消耗回合=false;
	public float 攻击时(Char attacker, Char defender, float damage ) {

		if(defender!=null&&defender.第x次防御==1&&首攻()>0){
			damage+=首攻();
		}
		if(defender!=null&&defender.第x次防御==1&&迅速()){
			攻击不消耗回合=true;
		}
		if(defender!=null&&麻痹()>0){
				算法.修复效果(()->{
					Buff.施加(defender,Paralysis.class,Paralysis.DURATION*麻痹());
				});
		}
		if(defender!=null&&冻结()>0){
				算法.修复效果(()->{
					Buff.施加(defender,Frost.class,Frost.DURATION*冻结());
				});
		}
		if(defender!=null&&中毒()>0){
			float finalDamage=damage;
			算法.修复效果(()->{
					Buff.施加(defender,Poison.class).set(finalDamage*中毒());
				});
		}
		if (attacker instanceof Hero hero){
			if(defender!=null&&伏击()>0&&defender instanceof Mob&&((Mob)defender).surprisedBy(hero)){
				damage+=damage*伏击()
						*(hero.符文("升级暗杀之刃")&&this instanceof 暗杀之刃
						  &&hero.暴击判定(defender,1)>1?hero.暴击伤害():1);

			}
		}
		return damage;
	}
	public float 投掷攻击时(Char attacker, Char defender, float damage ) {
		return damage;
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(hitSound, 1, pitch * hitSoundPitch);
	}
	
}
