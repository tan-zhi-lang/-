

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.BlobImmunity;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.宝物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.毒气药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.浮空药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.液火药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.灵视药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.麻痹药剂;
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
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.优惠卡;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.书包;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.冲锋枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火炮;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.狙击枪;
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
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.霰弹枪;
import com.shatteredpixel.shatteredpixeldungeon.items.圣诞礼物;
import com.shatteredpixel.shatteredpixeldungeon.items.未来空间器;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.shatteredpixel.shatteredpixeldungeon.items.空间之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.红包;
import com.shatteredpixel.shatteredpixeldungeon.items.结晶法杖;
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
	
	WARRIOR(HeroSubClass.狂战士,HeroSubClass.角斗士),
	MAGE(HeroSubClass.冰结师,HeroSubClass.元素法师),
	盗贼(HeroSubClass.刺客,HeroSubClass.神偷无影),
	HUNTRESS(HeroSubClass.狙击手,HeroSubClass.守望者),
	
	DUELIST(HeroSubClass.武器大师,HeroSubClass.武者),
	CLERIC(HeroSubClass.圣骑士,HeroSubClass.祭司),
	巫女(HeroSubClass.战斗法师,HeroSubClass.黑魔导师),//1
	重武(HeroSubClass.盾之勇者,HeroSubClass.轻装步兵),
	镜魔(HeroSubClass.灵月杀手,HeroSubClass.不灭战士),
	道士(HeroSubClass.死灵术士,HeroSubClass.真人),//1
	行僧(HeroSubClass.疾行者),//1
	近卫(HeroSubClass.征服者,HeroSubClass.皇室卫兵),
	兽灵(HeroSubClass.神兽之灵),//1
	机器(HeroSubClass.潜能觉醒),///2
	女忍(HeroSubClass.灵魂武者,HeroSubClass.土影),//1
	戒老(HeroSubClass.阿修罗,HeroSubClass.指环王),
	逐姝(HeroSubClass.潜能觉醒),///2
	罗兰(HeroSubClass.潜能觉醒),///2
	学士(HeroSubClass.潜能觉醒),///2
	灵猫(HeroSubClass.黑白双子),//1
	鼠弟(HeroSubClass.巫咒王鼠,HeroSubClass.实验狂鼠),
	凌云(HeroSubClass.潜能觉醒),///2
	血鬼(HeroSubClass.金刚独狼,HeroSubClass.血法师),
	来世(HeroSubClass.时间刺客),//1
	NONE(HeroSubClass.潜能觉醒);
	
	private HeroSubClass[] subClasses;
	
	HeroClass(HeroSubClass... subClasses){
		this.subClasses=subClasses;
	}
	
	public void initHero(Hero hero){
		hero.heroClass=this;

		Notes.物品类别备注(new 治疗药剂(),"治疗药剂"+"使用技巧","能够站在献祭之火扔向献祭之火即可直接完成献祭");
		Notes.物品类别备注(new 浮空药剂(),"浮空药剂"+"使用技巧","浮空时可以无伤跳楼");
		Notes.物品类别备注(new 隐形药剂(),"隐形药剂"+"使用技巧","隐形时巨型食人鱼就不会攻击你");
		Notes.物品类别备注(new 极速药剂(),"极速药剂"+"使用技巧","只要你跑得够快血色哨卫攻击不到你");

		Notes.物品类别备注(new 液火药剂(),"液火药剂"+"使用技巧","火焰可以燃烧(必刷烈焰花)");
		Notes.物品类别备注(new 冰霜药剂(),"冰霜药剂"+"使用技巧","可以熄灭魔法火焰(必刷冰冠花)");

		Notes.物品类别备注(new 净化药剂(),"净化药剂"+"使用技巧","可以无视很多负面效果，直接去毒气房一探究竟");

		Notes.物品类别备注(new Bomb(),"炸弹"+"使用技巧","可以把很多物品扔地上堆起来，炸一下看看是不是稀有物品，不是稀有物品会被炸没");
		Notes.物品类别备注(new 水袋(),"水袋"+"使用技巧","装满水可以合成永生秘药");
		Notes.物品类别备注(new 毒气药剂(),"毒气药剂"+"使用技巧","只有毒气能杀死毒气宝箱怪(必刷毒气药剂)");
		Notes.物品类别备注(new 麻痹药剂(),"麻痹药剂"+"使用技巧","只有麻痹能让DM0停止飞行(必刷麻痹药剂)");
		Notes.物品类别备注(new 灵视药剂(),"灵视药剂"+"使用技巧","只有灵视能看到超级魔法绵羊(必刷灵视药剂)");



		if(hero.heroClass(HeroClass.鼠弟))
			Dungeon.老鼠蝙蝠= true;

		if(Dungeon.派对(派对设置.英精英雄)){
			switch(Random.Int(6)){
				case 0:
				default:
					hero.英精英雄=0;
//					ChampionEnemy.Blazing.class;
					break;
				case 1:
					hero.英精英雄=1;
//					ChampionEnemy.Projecting.class;
					break;
				case 2:
					hero.英精英雄=2;
//					ChampionEnemy.AntiMagic.class;
					break;
				case 3:
					hero.英精英雄=3;
//					ChampionEnemy.Giant.class;
					break;
				case 4:
					hero.英精英雄=4;
//					ChampionEnemy.Blessed.class;
					break;
				case 5:
					hero.英精英雄=5;
					hero.英精英雄成长=0.2f;
//					ChampionEnemy.Growing.class;
					break;
			}
		}
		Talent.initClassTalents(hero);
		
		//		Item i = new ClothArmor().鉴定();
		//		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;
		
		//		i = new Food();
		//		if (!Challenges.isItemBlocked(i)) i.放背包();

		if(Dungeon.赛季(赛季设置.幸运转世)){
			hero.幸运转世=1;
//			hero.幸运转世=Random.oneOf(1,2);
		}

		if(Dungeon.派对(派对设置.钢门联盟)){
			心之钢 钢门=new 心之钢();
			钢门.鉴定();
			钢门.activate(hero);
			钢门.放背包();
		}
		if(Dungeon.派对(派对设置.海克斯)){
			new 海克斯宝典().放背包();
		}
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

			int x=999;
			new 经验药剂().数量(x).放背包();
			new 治疗药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 力量药剂().数量(x).放背包();
			new 液火药剂().数量(x).放背包();
			new 浮空药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 净化药剂().数量(x).放背包();
			new 隐形药剂().数量(x).放背包();
			new 灵视药剂().数量(x).放背包();
			new Icecap.Seed().数量(x).放背包();
			new 毒气药剂().数量(x).放背包();

//			new 坠牢之星().数量(x).放背包();
			new Torch().数量(x).放背包();

//			new MysteryMeat().数量(x).放背包();
//			new 属性锻造器().数量(10).放背包();
			
			new 探地卷轴().数量(x).放背包();
			new 升级卷轴().数量(x).放背包();
			new 鉴定卷轴().数量(x).放背包();
			new 嬗变卷轴().数量(x).放背包();
			new 复仇卷轴().数量(x).放背包();
			new 祛邪卷轴().数量(x).放背包();
			new 来去秘卷().数量(x).放背包();
			new 镜像卷轴().数量(x).放背包();


			
			for (Item item : hero.belongings){
				item.鉴定();
			}
		}

		if(算法.种子()!=null)
		算法.种子().放背包();
		
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
		
		水袋 水袋=new 水袋();
		if(hero.heroClass(WARRIOR)){
			if(!Dungeon.赛季(赛季设置.地牢塔防))
			水袋.放背包();
		}
		
		//region 初始
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
		
		if(hero.belongings.幸运!=null)
		hero.belongings.幸运.activate(hero);
		hero.belongings.幸运.鉴定();
		
		if(Dungeon.赛季(赛季设置.地牢塔防)){
			hero.belongings.weapon=null;
			hero.belongings.armor=null;
			hero.belongings.misc=null;
			hero.belongings.misc2=null;
			hero.belongings.misc3=null;
		}
		if(Dungeon.赛季(赛季设置.地牢塔防)){
			Item item=new 结晶法杖();
			item.放背包();
			Dungeon.quickslot.reset();
			Dungeon.quickslot.setSlot(0,item);
		}
		//endregion
		
		new 鉴定卷轴().鉴定();
		
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
		
		new 英雄断剑().放背包();
		new Torch().放背包();
		
		(hero.belongings.weapon=new 匕首()).鉴定();
		
		CloakOfShadows cloak=new CloakOfShadows();
		(hero.belongings.misc=cloak).鉴定();
		
		
		Dungeon.quickslot.setSlot(0,cloak);
		new 探地卷轴().鉴定();
		new 隐形药剂().鉴定();
	}
	
	private static void initHuntress(Hero hero){
		(hero.belongings.armor=new 披风()).鉴定();
		
		(hero.belongings.weapon=new 灵能短弓()).鉴定();
		
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
		
		new 灵视药剂().鉴定();
		new 催眠卷轴().鉴定();
	}
	
	private static void initDuelist(Hero hero){
		Item i=new 胸铠().鉴定();
			hero.belongings.armor=(胸铠)i;
		(hero.belongings.weapon=new 配刺剑()).鉴定();
		
		
		new 力量药剂().鉴定();
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
		
		本命玉佩 tome=new 本命玉佩();
		(hero.belongings.misc=tome).鉴定();
		
		Dungeon.quickslot.setSlot(0,tome);
		new 祛邪卷轴().鉴定();
		new 净化药剂().鉴定();
	}
	
	private static void 初始行僧(Hero hero){
		(hero.belongings.weapon=new 白带()).鉴定();
		Buff.延长(hero,BlobImmunity.class,450*27);
		new 极速药剂().鉴定();
		new 净化药剂().鉴定();
	}
	
	private static void 初始近卫(Hero hero){
		
		(hero.belongings.weapon=new 长矛()).鉴定();
		(hero.belongings.armor=new 战甲()).鉴定();
		new 力量药剂().鉴定();
		new 盛怒卷轴().鉴定();
	}
	
	private static void 初始兽灵(Hero hero){
		
		(hero.belongings.weapon=new 臂铠()).鉴定();
		new 催眠卷轴().鉴定();
		new 灵视药剂().鉴定();
	}
	
	private static void 初始机器(Hero hero){
		
		(hero.belongings.weapon=new 修理扳手()).鉴定();
		
		
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
		new 空间之戒().放背包();
		(hero.belongings.armor=new 能袍()).鉴定();
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
		new 复仇卷轴().鉴定();
		new 恐惧卷轴().鉴定();
	}
	
	private static void 初始学士(Hero hero){
		(hero.belongings.weapon=new 书包()).鉴定();
		(hero.belongings.armor=new 训服()).鉴定();
		new 液火药剂().鉴定();
		new 毒气药剂().鉴定();
	}
	
	private static void 初始灵猫(Hero hero){
		
		(hero.belongings.armor=new 背心()).鉴定();
		new 祛邪卷轴().鉴定();
		new 灵视药剂().鉴定();
	}
	
	private static void 初始鼠弟(Hero hero){
		十字弩 x=new 十字弩();
		(hero.belongings.weapon=x).鉴定();
		Item i=new 飞镖();
		i.放背包();
		Dungeon.quickslot.setSlot(0,i);
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
		Dungeon.gold=Statistics.金币;
		Dungeon.energy=Statistics.能量;
		
		new 传送卷轴().鉴定();
		new 极速药剂().鉴定();
	}
	
	public String title(){
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
				return new ArmorAbility[]{new ElementalBlast(),
										  new WildMagic(),
										  new WarpBeacon()};
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
