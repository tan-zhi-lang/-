

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
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.PowerOfMany;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.Trinity;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Feint;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpectralBlades;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.SpiritHawk;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.ElementalBlast;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WarpBeacon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.DeathMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.ShadowClone;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.rogue.SmokeBomb;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.HeroicLeap;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Shockwave;
import com.shatteredpixel.shatteredpixeldungeon.items.Amulet;
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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍之额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.荆棘斗篷;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.杂物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.隐形药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.六神之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.来去秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.书包;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.白带;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.矛盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.臂铠;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血姬;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血砍刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.配刺剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金玫苦无;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.未来空间器;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.items.破损纹章;
import com.shatteredpixel.shatteredpixeldungeon.items.空间之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public enum HeroClass{
	
	WARRIOR(HeroSubClass.狂战士,HeroSubClass.角斗士),
	MAGE(HeroSubClass.战斗法师,HeroSubClass.术士),
	盗贼(HeroSubClass.刺客,HeroSubClass.疾行者),
	HUNTRESS(HeroSubClass.狙击手,HeroSubClass.守望者),
	
	DUELIST(HeroSubClass.勇士,HeroSubClass.武者),
	CLERIC(HeroSubClass.PRIEST,HeroSubClass.PALADIN),
	巫女(HeroSubClass.神秘学者,HeroSubClass.黑魔导师),
	重武(),镜魔(),
	道士(HeroSubClass.鬼师),行僧(),
	近卫(),兽灵(HeroSubClass.养殖专家),
	机器(),女忍(),
	戒老(),逐姝(),
	罗兰(),学士(),
	灵猫(),鼠弟(),
	凌云(),血鬼(),
	来世(),
	英雄();
	
	private HeroSubClass[] subClasses;
	
	HeroClass(HeroSubClass... subClasses){
		this.subClasses=subClasses;
	}
	
	public void initHero(Hero hero){
		hero.heroClass=this;
		Talent.initClassTalents(hero);
		
		//		Item i = new ClothArmor().鉴定();
		//		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;
		
		//		i = new Food();
		//		if (!Challenges.isItemBlocked(i)) i.放背包();
		
		if(算法.isDebug()){
			
			new PotionBandolier().放背包();
			new MagicalHolster().放背包();
			new 杂物袋().放背包();
			new ScrollHolder().放背包();
			int x=100;
			new 经验药剂().数量(x).鉴定(true).放背包();
			new 治疗药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new PotionOfLiquidFlame().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 净化药剂().数量(x).放背包();
			new 隐形药剂().数量(x).放背包();
			new Icecap.Seed().数量(x).放背包();
			new PotionOfToxicGas().数量(x).放背包();
			
//			new MysteryMeat().数量(x).放背包();
			
			new 升级卷轴().数量(x).放背包();
			new 鉴定卷轴().数量(x).放背包();
			new 嬗变卷轴().数量(x).放背包();
			new ScrollOfRetribution().数量(x).放背包();
			new 祛邪卷轴().数量(x).放背包();
			new 来去秘卷().数量(x).放背包();
			new ScrollOfMirrorImage().数量(x).放背包();
			
//			new WandOfCorrosion().放背包();
//			new MasterThievesArmband().放背包();
			new TengusMask().放背包();
			new 荆棘斗篷().放背包();
			new TalismanOfForesight().放背包();
			new ScrollOfMysticalEnergy().放背包();
			new AlchemistsToolkit().放背包();
//			new AlchemistsToolkit().放背包();
//			new 奥术之戒().放背包();
//			new 能量之戒().放背包();
//			new 手枪().放背包();
//			new TrinketCatalyst().放背包();
//			new CapeOfThorns().放背包();
			new Amulet().放背包();
//			new 商人信标().放背包();
//			new 召唤物品().放背包();
			new 能量之戒().放背包();
			new 六神之戒().放背包();
			new 六神之戒().放背包();
			
			for (Item item : Dungeon.hero.belongings){
				item.鉴定();
			}
		}
		
		算法.种子();
		if(Holiday.getCurrentHoliday()==Holiday.国庆节){
			new 中国国旗().放背包();
		}
		if(Holiday.getCurrentHoliday()==Holiday.感恩节){
			new HornOfPlenty().放背包();
		}
		new 绒布袋().放背包();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();
		
		水袋 水袋=new 水袋();
		if(hero.heroClass(WARRIOR)){
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
			hero.belongings.armor.affixSeal(new 破损纹章());
			Catalog.setSeen(破损纹章.class); //as it's not added to the inventory
		}
		
		new 治疗药剂().鉴定();
		new ScrollOfRage().鉴定();
	}
	
	private static void initMage(Hero hero){
		Item i=new 法袍().鉴定();
			hero.belongings.armor=(法袍)i;
		
		法师魔杖 staff;
		
		staff=new 法师魔杖(new 焰浪法杖());
		
		(hero.belongings.weapon=staff).鉴定();
		hero.belongings.weapon.activate(hero);	
		
		Dungeon.quickslot.setSlot(0,staff);
		
		new ScrollOfRecharging().鉴定();
		new PotionOfLiquidFlame().鉴定();
	}
	
	private static void initRogue(Hero hero){
		Item i=new 风衣().鉴定();
			hero.belongings.armor=(风衣)i;
		
		new 英雄断剑().放背包();
		new Torch().放背包();
		
		(hero.belongings.weapon=new 匕首()).鉴定();
		
		CloakOfShadows cloak=new CloakOfShadows();
		(hero.belongings.misc=cloak).鉴定();
		hero.belongings.misc.activate(hero);
		
		
		Dungeon.quickslot.setSlot(0,cloak);
		new 探地卷轴().鉴定();
		new 隐形药剂().鉴定();
	}
	
	private static void initHuntress(Hero hero){
		(hero.belongings.armor=new 披风()).鉴定();
		
		(hero.belongings.weapon=new 灵能短弓()).鉴定();
		
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
		
		new PotionOfMindVision().鉴定();
		new ScrollOfLullaby().鉴定();
	}
	
	private static void initDuelist(Hero hero){
		Item i=new 胸铠().鉴定();
			hero.belongings.armor=(胸铠)i;
		
		(hero.belongings.weapon=new 配刺剑()).鉴定();
		hero.belongings.weapon.activate(hero);
		
		
		new PotionOfStrength().鉴定();
		new ScrollOfRetribution().鉴定();
	}
	
	private static void initCleric(Hero hero){
		Item i=new 祭服().鉴定();
			hero.belongings.armor=(祭服)i;
		
		(hero.belongings.weapon=new 权杖()).鉴定();
		hero.belongings.weapon.activate(hero);
		
		神圣法典 tome=new 神圣法典();
		(hero.belongings.misc=tome).鉴定();
		hero.belongings.misc.activate(hero);
		
		Dungeon.quickslot.setSlot(0,tome);
		
		new 净化药剂().鉴定();
		new 祛邪卷轴().鉴定();
	}
	
	private static void 初始巫女(Hero hero){
		Item i=new 巫服().鉴定();
		hero.belongings.armor=(巫服)i;
		
		血砍刀 x=new 血砍刀();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		灵月法杖 bow=new 灵月法杖();
		bow.鉴定().放背包();
		
		Dungeon.quickslot.setSlot(0,bow);
		
		new ScrollOfTerror().鉴定();
		new 治疗药剂().鉴定();
	}
	
	private static void 初始重武(Hero hero){
		
		冰门重盾 x=new 冰门重盾();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		
		new ScrollOfLullaby().鉴定();
		new PotionOfFrost().鉴定();
	}
	
	private static void 初始镜魔(Hero hero){
		(hero.belongings.weapon=new 镜刃()).鉴定();
		(hero.belongings.armor=new 武服()).鉴定();
	}
	
	private static void 初始道士(Hero hero){
		(hero.belongings.weapon=new 铜钱剑()).鉴定();
		(hero.belongings.armor=new 道袍()).鉴定();
		
		本命玉佩 tome=new 本命玉佩();
		(hero.belongings.misc=tome).鉴定();
		hero.belongings.misc.activate(hero);
		
		Dungeon.quickslot.setSlot(0,tome);
	}
	
	private static void 初始行僧(Hero hero){
		(hero.belongings.weapon=new 白带()).鉴定();
		Buff.延长(hero,BlobImmunity.class,450*27);
	}
	
	private static void 初始近卫(Hero hero){
		
		(hero.belongings.weapon=new 矛盾()).鉴定();
		(hero.belongings.armor=new 战甲()).鉴定();
	}
	
	private static void 初始兽灵(Hero hero){
		
		(hero.belongings.weapon=new 臂铠()).鉴定();
		hero.belongings.weapon.activate(hero);
	}
	
	private static void 初始机器(Hero hero){
		
		(hero.belongings.weapon=new 修理扳手()).鉴定();
		
		
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
	}
	
	private static void 初始女忍(Hero hero){
		
		(hero.belongings.weapon=new 金玫苦无()).鉴定();
		(hero.belongings.armor=new 忍服()).鉴定();
		
		
		叛忍之额 tome=new 叛忍之额();
		(hero.belongings.misc=tome).鉴定();
		hero.belongings.misc.activate(hero);
		
		Dungeon.quickslot.setSlot(0,tome);
	}
	
	private static void 初始戒老(Hero hero){
		(hero.belongings.weapon=new 碧蓝巨剑()).鉴定();
		new 空间之戒().放背包();
		(hero.belongings.armor=new 能袍()).鉴定();
	}
	
	private static void 初始逐姝(Hero hero){
		(hero.belongings.weapon=new 灵鞭()).鉴定();
		
		(hero.belongings.armor=new 勇装()).鉴定();
	}
	
	private static void 初始罗兰(Hero hero){
		(hero.belongings.weapon=new 血姬()).鉴定();
		
		(hero.belongings.armor=new 连裙()).鉴定();
	}
	
	private static void 初始学士(Hero hero){
		(hero.belongings.weapon=new 书包()).鉴定();
		(hero.belongings.armor=new 训服()).鉴定();
	}
	
	private static void 初始灵猫(Hero hero){
		
		(hero.belongings.armor=new 背心()).鉴定();
	}
	
	private static void 初始鼠弟(Hero hero){
		十字弩 x=new 十字弩();
		(hero.belongings.weapon=x).鉴定();
		Item i=new 飞镖();
		i.放背包();
		Dungeon.quickslot.setSlot(0,i);
		(hero.belongings.armor=new 魔披()).鉴定();
		
	}
	
	private static void 初始凌云(Hero hero){}
	
	private static void 初始血鬼(Hero hero){
		
		(hero.belongings.weapon=new 吸血刀()).鉴定();
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
				return new ArmorAbility[]{new HeroicLeap(),
										  new Shockwave(),
										  new Endure()};
			case MAGE:
				return new ArmorAbility[]{new ElementalBlast(),
										  new WildMagic(),
										  new WarpBeacon()};
			case 盗贼:
				return new ArmorAbility[]{new SmokeBomb(),
										  new DeathMark(),
										  new ShadowClone()};
			case HUNTRESS:
				return new ArmorAbility[]{new SpectralBlades(),
										  new NaturesPower(),
										  new SpiritHawk()};
			case DUELIST:
				return new ArmorAbility[]{new Challenge(),
										  new ElementalStrike(),
										  new Feint()};
			case CLERIC:
				return new ArmorAbility[]{new AscendedForm(),
										  new Trinity(),
										  new PowerOfMany()};
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
