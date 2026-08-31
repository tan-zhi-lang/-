

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.Rankings;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.上楼;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.下楼;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.地牢时间;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.开局属性更新;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.每10回合;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.每150回合;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.每300回合;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.每450回合;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.白猫保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.CrystalSpire;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DM0;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.RipperDemon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.RotLasher;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.毒气宝箱怪;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.粘咕;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.蟑螂;
import com.shatteredpixel.shatteredpixeldungeon.items.ArcaneResin;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Honeypot;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.TengusMask;
import com.shatteredpixel.shatteredpixeldungeon.items.Torch;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.勇装;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.巫服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.忍服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.战甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.披风;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.武服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.祭服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.背心;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.胸铠;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.训服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.连裙;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.道袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.铠甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.风衣;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.魔披;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.干涸绝露;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.鬼帝钟;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.宝物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.Pasty;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.液火药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.潜力药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.灵视药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.麻痹药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.六神之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.财富之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.来去秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.催眠卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.恐惧卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.盛怒卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.镜像卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.世界时表;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.优惠卡;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.心源芯片;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.断魂佛珠;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.皇室佩剑;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.书包;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.未知武器;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.冰结短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.冲锋枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.圣银十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.手枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.暗裔短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.火炮;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.炼金动力十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.狙击枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.自然之力;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.霰弹枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.白带;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.石头;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.臂铠;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血姬;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血砍刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.配刺剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金玫苦无;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长矛;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.雪球;
import com.shatteredpixel.shatteredpixeldungeon.items.传说之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.未来空间器;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.圣诞礼物;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.海克斯秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.海克斯移除器;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.物品生成;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.红包;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.结晶法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.自残绳;
import com.shatteredpixel.shatteredpixeldungeon.items.荣誉纹章;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.utils.Random;


public enum HeroClass{
	
	WARRIOR(HeroSubClass.不灭战士,HeroSubClass.战斗法师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	MAGE(HeroSubClass.冰魄剑神,HeroSubClass.大魔法师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	盗贼(HeroSubClass.冥法刺客,HeroSubClass.神偷无影,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	HUNTRESS(HeroSubClass.狙击手,HeroSubClass.守望者,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	
	DUELIST(HeroSubClass.武器大师,HeroSubClass.角斗士,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	CLERIC(HeroSubClass.圣骑士,HeroSubClass.祭司,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	巫女(HeroSubClass.金刚独狼,HeroSubClass.黑魔导师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	重武(HeroSubClass.盾之勇者,HeroSubClass.轻装步兵,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	镜魔(HeroSubClass.灵魂武者,HeroSubClass.内力武者,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	道士(HeroSubClass.死灵术士,HeroSubClass.真人,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	行僧(HeroSubClass.苦行者,HeroSubClass.符文法师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	近卫(HeroSubClass.征服者,HeroSubClass.皇室卫兵,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	兽灵(HeroSubClass.神兽之灵,HeroSubClass.养殖专家,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	机器(HeroSubClass.机械教主,HeroSubClass.魔法灵枢,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	女忍(HeroSubClass.灵月杀手,HeroSubClass.土影,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	戒老(HeroSubClass.阿修罗,HeroSubClass.指环王,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	逐姝(HeroSubClass.剑魔,HeroSubClass.圣女,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	罗兰(HeroSubClass.灾厄化身,HeroSubClass.灵剪刺客,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	学士(HeroSubClass.幸运之子,HeroSubClass.图书管理员,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	灵猫(HeroSubClass.黑白双子,HeroSubClass.猫头鹰,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	鼠弟(HeroSubClass.巫咒王鼠,HeroSubClass.实验狂鼠,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	凌云(HeroSubClass.解咒真人,HeroSubClass.吞噬云烟,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	血鬼(HeroSubClass.狂战士,HeroSubClass.血法师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	来世(HeroSubClass.时间刺客,HeroSubClass.戏命师,HeroSubClass.奇经八脉,HeroSubClass.多面手),
	NONE(HeroSubClass.奇经八脉,HeroSubClass.多面手);
	
	private HeroSubClass[] subClasses;
	
	HeroClass(HeroSubClass... subClasses){
		this.subClasses=subClasses;
	}
	
	public void initHero(Hero hero){
		hero.heroClass=this;
		hero.海克斯重置();

		Notes.物品类别备注(new 治疗药剂(),"使用技巧","能够站在献祭之火并扔向献祭之火即可直接完成献祭");
		Notes.物品类别备注(new 浮空药剂(),"使用技巧","浮空时可以无伤跳楼");
		Notes.物品类别备注(new 隐形药剂(),"使用技巧","隐形时巨型食人鱼就不会攻击你");
		Notes.物品类别备注(new 极速药剂(),"使用技巧","只要你跑得够快血色哨卫攻击不到你");

		Notes.物品类别备注(new 液火药剂(),"使用技巧","火焰可以燃烧障碍墙和融化魔法冰霜墙(必刷烈焰花)");
		Notes.物品类别备注(new 冰霜药剂(),"使用技巧","可以熄灭魔法火焰墙(必刷冰冠花)");

		Notes.物品类别备注(new 净化药剂(),"使用技巧","可以无视很多负面效果，如直接去毒气房一探究竟");

		Notes.物品类别备注(new Bomb(),"使用技巧","可以把很多物品扔地上堆起来，炸一下看看是不是稀有物品，不是稀有物品会被炸没");
		Notes.物品类别备注(new 水袋(),"使用技巧","装满水可以合成永生秘药");

		Notes.物品类别备注(new 毒气药剂(),"使用技巧","只有毒气能杀死毒气宝箱怪(必刷毒气药剂)");
		Notes.物品类别备注(new 麻痹药剂(),"使用技巧","只有麻痹能让DM0停止飞行(必刷麻痹药剂)");
		Notes.物品类别备注(new 灵视药剂(),"使用技巧","只有灵视能看到超级魔法绵羊(必刷灵视药剂)");
		
		Notes.物品类别备注(new PotionOfCleansing(),"使用技巧","饥饿值为0时饮用可以填充450饥饿");

		Notes.怪物备注(new Elemental.FireElemental(),"火焰怪物应对技巧","用冰霜伤害能更快击杀它！");
		Notes.怪物备注(new Elemental.FrostElemental(),"冰霜怪物应对技巧","用火焰伤害能更快击杀它！");
		Notes.怪物备注(new 粘咕(),"酸性怪物应对技巧","用火焰伤害能更快击杀它！");
		Notes.怪物备注(new Wraith(),"亡灵应对技巧","用雷和光伤害能更快击杀它！");
		Notes.怪物备注(new RipperDemon(),"恶魔应对技巧","用光伤害能更快击杀它！");
		Notes.怪物备注(new 蟑螂(),"昆虫应对技巧","用电和火伤害能更快击杀它！");
		Notes.怪物备注(new RotLasher(),"树妖应对技巧","用电和火伤害能更快击杀它！");
		Notes.怪物备注(new Piranha(),"海妖应对技巧","用电和毒伤害能更快击杀它！");
		Notes.怪物备注(new Rat(),"动物应对技巧","用冰火、电毒、毒、流血伤害能更快击杀它！");
		Notes.怪物备注(new CrystalSpire(),"应对技巧","用镐子伤害能更快击杀它！");

		Notes.怪物备注(new DM0(),"应对技巧","用麻痹、眩晕、魅惑、恐惧、魂飞魄散、冻结能直接秒杀他！");
		Notes.怪物备注(new 毒气宝箱怪(),"应对技巧","用毒气、中毒伤害能直接秒杀它！");



		if(hero.heroClass(HeroClass.鼠弟)||hero.heroClass(HeroClass.灵猫))
			if(Random.Int(2)==0)
			Dungeon.老鼠蝙蝠= true;

		if(Dungeon.派对(派对设置.英精英雄)){
			switch(Random.Int(6+3)){
				case 0:
				default:
					hero.英精英雄=0;
					break;
				case 1:
					hero.英精英雄=1;
					break;
				case 2:
					hero.英精英雄=2;
					break;
				case 3:
					hero.英精英雄=3;
					break;
				case 4:
					hero.英精英雄=4;
					break;
				case 5:
					hero.英精英雄=5;
					hero.英精英雄成长=0.2f;
					break;
				case 6:
					hero.英精英雄=6;
					break;
				case 7:
					hero.英精英雄=7;
					break;
				case 8:
					hero.英精英雄=8;
					break;
			}
		}
		
		Talent.initClassTalents(hero);
		
		//		Item i = new ClothArmor().鉴定();
		//		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;
		
		//		i = new Food();
		//		if (!Challenges.isItemBlocked(i)) i.放背包();


		if(Dungeon.派对(派对设置.种族天赋)){
			if(!(hero.heroClass(鼠弟)||hero.heroClass(机器)||hero.heroClass(灵猫)))
			if(hero.种族天赋.equals("")){
				hero.种族天赋=Random.oneOf("人类","兽人","鲛人","精灵",
										   "汪星人","龙人","矮人","猩人","恶魔",
										   "吸血鬼","不死族","机器","树妖","半人马","地精","巨灵"
										   );

				String s=switch(hero.种族天赋){
					case "人类"->"综合属性+5%。";
					case "兽人"->"暴击率+20%。";
					case "鲛人"->"在水中综合属性+15%。";
					case "精灵"->"在草丛中综合属性+15%。";
					case "汪星人"->"感知范围+7。";
					case "龙人"->"+30%元素抗性，敌人+30%法术穿透。";
					case "猩人"->"力量和最大生命+30%。";
					case "恶魔"->"治疗护盾-40%，击杀敌人+0.1攻击和0.2最大生命。";
					case "矮人"->"升级武器、防具、法杖、戒指能够50%概率额外升级一次。";
					case "吸血鬼"->"吸血+12%，白天吸血减半。";
					case "不死族"->"饥饿不再受伤。";
					case "机器"->"英雄机器的特性。";
					case "树妖"->"在草丛上，再生速度x5。";
					case "半人马"->"移速+50%。";
					case "地精"->"爆炸伤害x3。";
					case "巨灵"->"一直飞行，并且魔力+25%";
					default ->"";
				};
				Notes.备注("种族:"+hero.种族天赋,s);
			}
		}
		if(Dungeon.派对(派对设置.钢门联盟)){
			心之钢 钢门=new 心之钢();
			钢门.鉴定();
			钢门.activate(hero);
			钢门.放背包();
		}
		if(Dungeon.派对(派对设置.海克斯)){
			new 海克斯秘卷(true).放背包();
			new 海克斯移除器().放背包();
		}

		Buff.施加(hero,开局属性更新.class);

		if(Dungeon.赛季(赛季设置.地牢塔防)){
		}else {
			Buff.施加(hero,上楼.class);
			Buff.施加(hero,下楼.class);

			Buff.施加(hero,每10回合.class);
			Buff.施加(hero,每150回合.class);
			Buff.施加(hero,每300回合.class);
			Buff.施加(hero,每450回合.class);
			Buff.施加(hero,地牢时间.class);
		}
//			Buff.施加(hero,无法回头.class);


		if(算法.物品()!=null)
		算法.物品().放背包();

		if(Dungeon.赛季(赛季设置.地牢塔防)){

		}else{
			//初始物品！
				if(Holiday.getCurrentHoliday()==Holiday.春节){
					new 红包().放背包();
				}
				if(Holiday.getCurrentHoliday()==Holiday.国庆节){
					new 中国国旗().放背包();
				}
				if(Holiday.getCurrentHoliday()==Holiday._1111){
					new 优惠卡().放背包();
				}
				if(Holiday.getCurrentHoliday()==Holiday.圣诞节){
					new 圣诞礼物().放背包();
				}

				if(Holiday.getCurrentHoliday()==Holiday.感恩节){
					new HornOfPlenty().放背包();
				}

				new 绒布袋().放背包();
				Dungeon.LimitedDrops.VELVET_POUCH.drop();

				new 自残绳().放背包();
			}

		//region 初始
		if(Dungeon.赛季(赛季设置.地牢塔防)){

		}else
		switch(this){
			case WARRIOR:
				initWarrior(hero);
				break;
			case MAGE:
				initMage(hero);
				break;
			case 盗贼:
				initRogue(hero);
				break;
			case HUNTRESS:
				initHuntress(hero);
				break;
			
			case DUELIST:
				initDuelist(hero);
				break;
			
			case CLERIC:
				initCleric(hero);
				break;
			case 巫女:
				初始巫女(hero);
				break;
			case 重武:
				初始重武(hero);
				break;
			case 镜魔:
				初始镜魔(hero);
				break;
			case 道士:
				初始道士(hero);
				break;
			case 行僧:
				初始行僧(hero);
				break;
			case 近卫:
				初始近卫(hero);
				break;
			case 兽灵:
				初始兽灵(hero);
				break;
			case 机器:
				初始机器(hero);
				break;
			case 女忍:
				初始女忍(hero);
				break;
			case 戒老:
				初始戒老(hero);
				break;
			case 逐姝:
				初始逐姝(hero);
				break;
			case 罗兰:
				初始罗兰(hero);
				break;
			case 学士:
				初始学士(hero);
				break;
			case 灵猫:
				初始灵猫(hero);
				break;
			case 鼠弟:
				初始鼠弟(hero);
				break;
			case 凌云:
				初始凌云(hero);
				break;
			case 血鬼:
				初始血鬼(hero);
				break;
			case 来世:
				初始来世(hero);
				break;
		}

		if(hero.belongings.weapon!=null)
		hero.belongings.weapon.activate(hero);
		
		if(hero.belongings.armor!=null)
		hero.belongings.armor.activate(hero);
		
		if(hero.belongings.misc!=null)
		hero.belongings.misc.activate(hero);
		if(hero.belongings.misc2!=null)
		hero.belongings.misc2.activate(hero);
		if(hero.belongings.misc3!=null)
		hero.belongings.misc3.activate(hero);
		if(hero.belongings.misc4!=null)
		hero.belongings.misc4.activate(hero);
		if(hero.belongings.misc5!=null)
		hero.belongings.misc5.activate(hero);
		if(hero.belongings.misc6!=null)
			hero.belongings.misc6.activate(hero);
		if(hero.belongings.misc7!=null)
			hero.belongings.misc7.activate(hero);
		if(hero.belongings.misc8!=null)
			hero.belongings.misc8.activate(hero);
		if(hero.belongings.misc9!=null)
			hero.belongings.misc9.activate(hero);
		if(hero.belongings.misc10!=null)
			hero.belongings.misc10.activate(hero);

//		hero.belongings.misc10=new 幸运之戒();
//		hero.belongings.misc10.activate(hero);
//		hero.belongings.misc10.鉴定();
//		hero.belongings.misc10.升级(30);

		if(Dungeon.赛季(赛季设置.地牢塔防)){
			hero.belongings.weapon=null;
			hero.belongings.armor=null;
			hero.belongings.misc=null;
			hero.belongings.misc2=null;
			hero.belongings.misc3=null;
			hero.belongings.幸运=null;
			Buff.detach(hero,财富之戒.Wealth.class);
		}
		if(Dungeon.赛季(赛季设置.地牢塔防)){
			Item item=new 结晶法杖();
			item.放背包();
			Dungeon.quickslot.reset();
			Dungeon.quickslot.setSlot(0,item);
		}
		//endregion

		if(算法.isDebug()){
			new PotionBandolier().放背包();
			new MagicalHolster().放背包();
			new 宝物袋().放背包();
			new ScrollHolder().放背包();

			new 手枪().放背包();
			new 冲锋枪().放背包();
			new 霰弹枪().放背包();
			new 狙击枪().放背包();
			new 火炮().放背包();

			new 灵能短弓().放背包();
			new 自然之力().放背包();
			new 暗裔短弓().放背包();
			new 冰结短弓().放背包();

			new 炼金动力十字弩().放背包();
			new 圣银十字弩().放背包();
			int x=999;
			new 经验药剂().数量(x).放背包();
			new 治疗药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 潜力药剂().数量(x).放背包();
			new 液火药剂().数量(x).放背包();
			new 浮空药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 净化药剂().数量(x).放背包();
			new 隐形药剂().数量(x).放背包();
			new 灵视药剂().数量(x).放背包();
						new Icecap.Seed().数量(x).放背包();
			new 毒气药剂().数量(x).放背包();
			//			new DarkGold().数量(x).放背包();

			//			new 狂怒之戒().放背包();
			//			new 狂怒之戒().放背包();
			//			new 恢复之戒().放背包();
			//			new 闪避之戒().放背包();
			//			new 命中之戒().放背包();
			
			new 未知武器().放背包();
			new 物品生成().放背包();

			new Torch().数量(x).放背包();
			new Pasty().数量(x).放背包();


			new Honeypot().数量(x).放背包();

			new TengusMask().放背包();
			new AlchemistsToolkit().放背包();

			new ArcaneResin().数量(x).放背包();

			new 探地卷轴().数量(x).放背包();
			new 升级卷轴().数量(x).放背包();
			new 鉴定卷轴().数量(x).放背包();
			new 嬗变卷轴().数量(x).放背包();
			new 复仇卷轴().数量(x).放背包();
			new 祛邪卷轴().数量(x).放背包();
			new 镜像卷轴().数量(x).放背包();

			new 来去秘卷().数量(x).放背包();

			for (Item item : hero.belongings){
				item.鉴定();
			}
		}
		new 鉴定卷轴().鉴定();

		水袋 水袋=hero.belongings.getItem(水袋.class);
		if(水袋!=null)
		if(SPDSettings.quickslotWaterskin()){
			for(int s=0;s<QuickSlot.SIZE;s++){
				if(Dungeon.quickslot.getItem(s)==null){
					if(hero.heroClass(WARRIOR)){
						Dungeon.quickslot.setSlot(s,水袋);
						break;
					}
				}
			}
		}


	}
	
	public Badges.Badge masteryBadge(){
		switch(this){
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case 盗贼:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
			case DUELIST:
				return Badges.Badge.MASTERY_DUELIST;
			case CLERIC:
				return Badges.Badge.MASTERY_CLERIC;
			case 巫女:
				return Badges.Badge.巫女;
			case 重武:
				return Badges.Badge.重武;
		}
		return null;
	}
	
	private static void initWarrior(Hero hero){
		(hero.belongings.weapon=new 短剑()).鉴定();
		
		Item i=new 铠甲().鉴定();
			hero.belongings.armor=(铠甲)i;
		
		if(hero.belongings.armor!=null){
			hero.belongings.armor.affixSeal(new 荣誉纹章());
			Catalog.setSeen(荣誉纹章.class); //as it's not added to the inventory
		}

		石头 tome=new 石头();
		tome.鉴定().放背包();
		Dungeon.quickslot.setSlot(0,tome);

		new 水袋().放背包();

		new 治疗药剂().鉴定();
		new 盛怒卷轴().鉴定();
	}
	
	private static void initMage(Hero hero){
		Item i=new 法袍().鉴定();
			hero.belongings.armor=(法袍)i;
		
		法师魔杖 staff=new 法师魔杖(new WandOfMagicMissile());
		(hero.belongings.weapon=staff).鉴定();

		四叶草法典 tome=new 四叶草法典();
		(hero.belongings.misc=tome).鉴定();

		Dungeon.quickslot.setSlot(0,staff);
		Dungeon.quickslot.setSlot(1,tome);

		new 充能卷轴().鉴定();
		new 液火药剂().鉴定();
	}
	
	private static void initRogue(Hero hero){
		Item i=new 风衣().鉴定();
			hero.belongings.armor=(风衣)i;
		
		new 英雄断剑().鉴定().放背包();
		new Torch().放背包();
		
		(hero.belongings.weapon=new 匕首()).鉴定();

//		hero.belongings.weapon.enchant(new 诡秘());
//		hero.belongings.armor.inscribe(new 冰心());

		CloakOfShadows cloak=new CloakOfShadows();
		(hero.belongings.misc=cloak).鉴定();
		
		
		Dungeon.quickslot.setSlot(0,cloak);
		new 探地卷轴().鉴定();
		new 隐形药剂().鉴定();
	}
	
	private static void initHuntress(Hero hero){
		(hero.belongings.armor=new 披风()).鉴定();
		
		(hero.belongings.weapon=new 短弓()).鉴定();
		new 箭矢().数量(20).放背包();
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
		
		new 灵视药剂().鉴定();
		new 催眠卷轴().鉴定();
	}
	
	private static void initDuelist(Hero hero){
		Item i=new 胸铠().鉴定();
			hero.belongings.armor=(胸铠)i;
		(hero.belongings.weapon=new 配刺剑()).鉴定();
		
		
		new 潜力药剂().鉴定();
		new 复仇卷轴().鉴定();
	}
	
	private static void initCleric(Hero hero){
		Item i=new 祭服().鉴定();
			hero.belongings.armor=(祭服)i;
		
		(hero.belongings.weapon=new 权杖()).鉴定();
		
		神圣法典 tome=new 神圣法典();
		(hero.belongings.misc=tome).鉴定();
		
		Dungeon.quickslot.setSlot(0,tome);
		
		new 净化药剂().鉴定();
		new 祛邪卷轴().鉴定();
	}
	
	private static void 初始巫女(Hero hero){
		Item i=new 巫服().鉴定();
		hero.belongings.armor=(巫服)i;
		
		血砍刀 x=new 血砍刀();
		(hero.belongings.weapon=x).鉴定();
		
		灵月法杖 bow=new 灵月法杖();
		bow.鉴定().放背包();
		
		Dungeon.quickslot.setSlot(0,bow);
		
		new 恐惧卷轴().鉴定();
		new 经验药剂().鉴定();
	}
	
	private static void 初始重武(Hero hero){
		
		冰门重盾 x=new 冰门重盾();
		(hero.belongings.weapon=x).鉴定();

		雪球 雪球=new 雪球();
		雪球.鉴定().放背包();
		Dungeon.quickslot.setSlot(0,雪球);


		new 催眠卷轴().鉴定();
		new 冰霜药剂().鉴定();
	}
	
	private static void 初始镜魔(Hero hero){
		(hero.belongings.weapon=new 镜刃()).鉴定();
		(hero.belongings.armor=new 武服()).鉴定();
		
		new 镜像卷轴().鉴定();
		new 复仇卷轴().鉴定();
	}
	
	private static void 初始道士(Hero hero){
		(hero.belongings.weapon=new 铜钱剑()).鉴定();
		(hero.belongings.armor=new 道袍()).鉴定();

		鬼帝钟 tome=new 鬼帝钟();
		(hero.belongings.misc=tome).鉴定();
		new 本命玉佩().放背包();
		Dungeon.quickslot.setSlot(0,tome);
		new 祛邪卷轴().鉴定();
		new 净化药剂().鉴定();
	}
	
	private static void 初始行僧(Hero hero){
		(hero.belongings.weapon=new 白带()).鉴定();
		Buff.延长(hero,BlobImmunity.class,450*27);
		new 世界时表().放背包();
		new 极速药剂().鉴定();
		new 净化药剂().鉴定();
	}
	
	private static void 初始近卫(Hero hero){
		
		(hero.belongings.weapon=new 长矛()).鉴定();
		(hero.belongings.armor=new 战甲()).鉴定();
		new 皇室佩剑().放背包();
		new 潜力药剂().鉴定();
		new 盛怒卷轴().鉴定();
	}
	
	private static void 初始兽灵(Hero hero){
		
		(hero.belongings.weapon=new 臂铠()).鉴定();
		new 断魂佛珠().放背包();
		new 催眠卷轴().鉴定();
		new 灵视药剂().鉴定();
	}
	
	private static void 初始机器(Hero hero){
		
		(hero.belongings.weapon=new 修理扳手()).鉴定();
		
		new 心源芯片().放背包();
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
		new 探地卷轴().鉴定();
		new 灵视药剂().鉴定();
	}
	
	private static void 初始女忍(Hero hero){
		
		(hero.belongings.weapon=new 金玫苦无()).鉴定();
		(hero.belongings.armor=new 忍服()).鉴定();
		
		
		叛忍护额 tome=new 叛忍护额();
		(hero.belongings.misc=tome).鉴定();
		
		Dungeon.quickslot.setSlot(0,tome);
		new 液火药剂().鉴定();
		new 隐形药剂().鉴定();
	}
	
	private static void 初始戒老(Hero hero){
		(hero.belongings.weapon=new 碧蓝巨剑()).鉴定();
		new 传说之戒().放背包();
		(hero.belongings.armor=new 能袍()).鉴定();
		(hero.belongings.misc=new 六神之戒()).鉴定();
		new 升级卷轴().鉴定();
		new 充能卷轴().鉴定();
	}
	
	private static void 初始逐姝(Hero hero){
		(hero.belongings.weapon=new 灵鞭()).鉴定();
		
		(hero.belongings.armor=new 勇装()).鉴定();
		new 升级卷轴().鉴定();
		new 嬗变卷轴().鉴定();
	}
	
	private static void 初始罗兰(Hero hero){
		(hero.belongings.weapon=new 血姬()).鉴定();
		
		(hero.belongings.armor=new 连裙()).鉴定();
		干涸绝露 tome=new 干涸绝露();
		(hero.belongings.misc=tome).鉴定();

		Dungeon.quickslot.setSlot(0,tome);

		new 复仇卷轴().鉴定();
		new 恐惧卷轴().鉴定();
	}
	
	private static void 初始学士(Hero hero){
		(hero.belongings.weapon=new 书包()).鉴定();
		(hero.belongings.armor=new 训服()).鉴定();

		Generator.randomUsingDefaults(Generator.Category.POTION).放背包();
		Generator.randomUsingDefaults(Generator.Category.POTION).放背包();
		Generator.randomUsingDefaults(Generator.Category.POTION).放背包();

		Generator.randomUsingDefaults(Generator.Category.SCROLL).放背包();
		Generator.randomUsingDefaults(Generator.Category.SCROLL).放背包();
		Generator.randomUsingDefaults(Generator.Category.SCROLL).放背包();
		new 液火药剂().鉴定();
		new 毒气药剂().鉴定();
	}
	
	private static void 初始灵猫(Hero hero){
		
		(hero.belongings.armor=new 背心()).鉴定();
		new 祛邪卷轴().鉴定();
		new 灵视药剂().鉴定();

			Buff.施加(hero,白猫保护.class);
	}
	
	private static void 初始鼠弟(Hero hero){
		十字弩 x=new 十字弩();
		(hero.belongings.weapon=x).鉴定();

		new 箭矢().数量(10).放背包();

		Dungeon.quickslot.setSlot(0,x);
		(hero.belongings.armor=new 魔披()).鉴定();
		
		new 麻痹药剂().鉴定();
		new 毒气药剂().鉴定();
		
	}
	
	private static void 初始凌云(Hero hero){
		
		new 浮空药剂().鉴定();
		new 极速药剂().鉴定();
	}
	
	private static void 初始血鬼(Hero hero){
		
		(hero.belongings.weapon=new 吸血刀()).鉴定();
		
		new 治疗药剂().鉴定();
		new 复仇卷轴().鉴定();
	}
	
	private static void 初始来世(Hero hero){
		
//		手枪 x=new 手枪();
//		(hero.belongings.weapon=x).鉴定();
//		hero.belongings.weapon.activate(hero);
//		new 手枪子弹().数量(70).放背包();
//		Dungeon.quickslot.setSlot(0,x);
		Item i=new 未来空间器();
		i.放背包();
		Dungeon.quickslot.setSlot(0,i);
		Dungeon.gold=Rankings.INSTANCE.来世金币;
		Dungeon.energy=Rankings.INSTANCE.来世能量;
		
		new 传送卷轴().鉴定();
		new 极速药剂().鉴定();
	}
	
	public String title(){
		if(Dungeon.hero()&&!Dungeon.hero.名字.equals(""))
		return Dungeon.hero.名字;
		return Messages.get(HeroClass.class,name());

	}
	
	public String desc(){
		return Messages.get(HeroClass.class,name()+"_desc");
	}
	
	public String shortDesc(){
		return Messages.get(HeroClass.class,name()+"_desc_short");
	}
	
	public HeroSubClass[] subClasses(){
		return subClasses;
	}
	
	public ArmorAbility[] armorAbilities(){//护甲技能
		switch(this){
			case WARRIOR:
			default:
			case MAGE:
				return new ArmorAbility[]{new WildMagic()};
			case 盗贼:
				return new ArmorAbility[]{
										  new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{
										  new SpiritHawk()};
			case DUELIST:
				return new ArmorAbility[]{new Challenge(),
										  new ElementalStrike(),
										  new Feint()};
		}
	}
	
	public String spritesheet(){
		switch(this){
			case WARRIOR:
			default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case 盗贼:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case DUELIST:
				return Assets.Sprites.DUELIST;
			case CLERIC:
				return Assets.Sprites.CLERIC;
			case 巫女:
				return Assets.Sprites.巫女;
			case 重武:
				return Assets.Sprites.重武;
			case 镜魔:
				return Assets.Sprites.镜魔;
			case 道士:
				return Assets.Sprites.道士;
			case 行僧:
				return Assets.Sprites.行僧;
			case 近卫:
				return Assets.Sprites.近卫;
			case 兽灵:
				return Assets.Sprites.兽灵;
			case 机器:
				return Assets.Sprites.机器;
			case 女忍:
				return Assets.Sprites.女忍;
			case 戒老:
				return Assets.Sprites.戒老;
			case 逐姝:
				return Assets.Sprites.逐姝;
			case 罗兰:
				return Assets.Sprites.罗兰;
			case 学士:
				return Assets.Sprites.学士;
			case 灵猫:
				return Assets.Sprites.灵猫;
			case 鼠弟:
				return Assets.Sprites.鼠弟;
			case 凌云:
				if(Dungeon.hero()&&Dungeon.hero.subClass(HeroSubClass.解咒真人))
					return Assets.Sprites.凌云真人;
				return Assets.Sprites.凌云;
			case 血鬼:
				return Assets.Sprites.血鬼;
			case 来世:
				return Assets.Sprites.来世;
		}
	}
	public static String spritesheet(HeroClass heroClass){
		switch(heroClass){
			case WARRIOR:
			default:
				return Assets.Sprites.WARRIOR;
			case MAGE:
				return Assets.Sprites.MAGE;
			case 盗贼:
				return Assets.Sprites.ROGUE;
			case HUNTRESS:
				return Assets.Sprites.HUNTRESS;
			case DUELIST:
				return Assets.Sprites.DUELIST;
			case CLERIC:
				return Assets.Sprites.CLERIC;
			case 巫女:
				return Assets.Sprites.巫女;
			case 重武:
				return Assets.Sprites.重武;
			case 镜魔:
				return Assets.Sprites.镜魔;
			case 道士:
				return Assets.Sprites.道士;
			case 行僧:
				return Assets.Sprites.行僧;
			case 近卫:
				return Assets.Sprites.近卫;
			case 兽灵:
				return Assets.Sprites.兽灵;
			case 机器:
				return Assets.Sprites.机器;
			case 女忍:
				return Assets.Sprites.女忍;
			case 戒老:
				return Assets.Sprites.戒老;
			case 逐姝:
				return Assets.Sprites.逐姝;
			case 罗兰:
				return Assets.Sprites.罗兰;
			case 学士:
				return Assets.Sprites.学士;
			case 灵猫:
				return Assets.Sprites.灵猫;
			case 鼠弟:
				return Assets.Sprites.鼠弟;
			case 凌云:
				if(Dungeon.hero()&&Dungeon.hero.subClass(HeroSubClass.解咒真人))
					return Assets.Sprites.凌云真人;
				return Assets.Sprites.凌云;
			case 血鬼:
				return Assets.Sprites.血鬼;
			case 来世:
				return Assets.Sprites.来世;
		}
	}
	
	public String splashArt(){
		return Assets.Splashes.时空;
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if(算法.isDebug()){
			return true;
		}
		
		switch(this){
			case WARRIOR:
			default:
				return true;
			case MAGE:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_MAGE);
			case 盗贼:
				return Badges.isUnlocked(Badges.Badge.解锁盗贼);
			case HUNTRESS:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_HUNTRESS);
			case DUELIST:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_DUELIST);
			case CLERIC:
				return Badges.isUnlocked(Badges.Badge.UNLOCK_CLERIC);
			case 巫女:
				return Badges.isUnlocked(Badges.Badge.解锁巫女);
			case 重武:
				return Badges.isUnlocked(Badges.Badge.解锁重武);
			case 镜魔:
				return Badges.isUnlocked(Badges.Badge.解锁镜魔);
			case 道士:
				return Badges.isUnlocked(Badges.Badge.解锁道士);
			case 行僧:
				return Badges.isUnlocked(Badges.Badge.解锁行僧);
			case 近卫:
				return Badges.isUnlocked(Badges.Badge.解锁近卫);
			case 兽灵:
				return Badges.isUnlocked(Badges.Badge.解锁兽灵);
			case 机器:
				return Badges.isUnlocked(Badges.Badge.解锁机器);
			case 女忍:
				return Badges.isUnlocked(Badges.Badge.解锁女忍);
			case 戒老:
				return Badges.isUnlocked(Badges.Badge.解锁戒老);
			case 逐姝:
				return Badges.isUnlocked(Badges.Badge.解锁逐姝);
			case 罗兰:
				return Badges.isUnlocked(Badges.Badge.解锁罗兰);
			case 学士:
				return Badges.isUnlocked(Badges.Badge.解锁学士);
			case 灵猫:
				return Badges.isUnlocked(Badges.Badge.解锁灵猫);
			case 鼠弟:
				return Badges.isUnlocked(Badges.Badge.解锁鼠弟);
			case 凌云:
				return Badges.isUnlocked(Badges.Badge.解锁凌云);
			case 血鬼:
				return Badges.isUnlocked(Badges.Badge.解锁血鬼);
			case 来世:
				return Badges.isUnlocked(Badges.Badge.解锁来世);
		}
	}
	
	public String unlockMsg(){
		return shortDesc()+"\n\n"+Messages.get(HeroClass.class,name()+"_unlock");
	}
	
}
