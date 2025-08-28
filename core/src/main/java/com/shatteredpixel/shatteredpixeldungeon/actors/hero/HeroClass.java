

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.QuickSlot;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
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
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CapeOfThorns;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.杂物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.净化药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.嬗变卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.TrinketCatalyst;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Cudgel;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.书包;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.冰门重盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.双刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.手枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.灵鞭;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.白带;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.矛盾;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.简易弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.臂铠;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.血姬;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.血砍刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.配刺剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.金玫苦无;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.镶钉手套;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingSpike;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.吸血飞刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.雪球;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.items.破损纹章;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.watabou.utils.Random;

public enum HeroClass{
	
	WARRIOR(HeroSubClass.BERSERKER,HeroSubClass.GLADIATOR),
	MAGE(HeroSubClass.BATTLEMAGE,HeroSubClass.WARLOCK),
	盗贼(HeroSubClass.ASSASSIN,HeroSubClass.FREERUNNER),
	HUNTRESS(HeroSubClass.SNIPER,HeroSubClass.WARDEN),
	
	DUELIST(HeroSubClass.CHAMPION,HeroSubClass.MONK),
	CLERIC(HeroSubClass.PRIEST,HeroSubClass.PALADIN),
	巫女(HeroSubClass.神秘学者,HeroSubClass.黑魔导师),
	重武(HeroSubClass.盾之勇者),镜魔(HeroSubClass.潜能觉醒),
	道士(HeroSubClass.潜能觉醒),行僧(HeroSubClass.潜能觉醒),
	近卫(HeroSubClass.潜能觉醒),兽灵(HeroSubClass.养殖专家),
	机器(HeroSubClass.潜能觉醒),女忍(HeroSubClass.潜能觉醒),
	戒老(HeroSubClass.潜能觉醒),逐姝(HeroSubClass.潜能觉醒),
	罗兰(HeroSubClass.潜能觉醒),学士(HeroSubClass.潜能觉醒),
	灵猫(HeroSubClass.潜能觉醒),鼠弟(HeroSubClass.潜能觉醒),
	凌云(HeroSubClass.潜能觉醒),血鬼(HeroSubClass.潜能觉醒),
	枪手(HeroSubClass.潜能觉醒);
	
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
		
		if(Dungeon.解压(解压设置.宝物空投)){
			Random.oneOf(Generator.randomWeapon(),Generator.randomArmor(),Generator.randomRing(),Generator.randomArtifact(),Generator.randomWand()).放背包();
		}
		if(算法.isDebug()){
			
			new PotionBandolier().放背包();
			new MagicalHolster().放背包();
			new 杂物袋().放背包();
			new ScrollHolder().放背包();
			int x=100;
			new 经验药剂().数量(x).鉴定(true).放背包();
			new 治疗药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 极速药剂().数量(x).放背包();
			new 净化药剂().数量(x).放背包();
			new PotionOfInvisibility().数量(x).放背包();
			new PotionOfToxicGas().数量(x).放背包();
			
			new MysteryMeat().数量(x).放背包();
			
			new 升级卷轴().数量(x).放背包();
			new 鉴定卷轴().数量(x).放背包();
			new 嬗变卷轴().数量(x).放背包();
			new 祛邪卷轴().数量(x).放背包();
			
			new TengusMask().放背包();
			new AlchemistsToolkit().放背包();
			new 奥术之戒().放背包();
			new 能量之戒().放背包();
			new 手枪().放背包();
			new TrinketCatalyst().放背包();
			new CapeOfThorns().放背包();
			
			for (Item item : Dungeon.hero.belongings){
				item.鉴定();
			}
		}
		
		算法.种子();
		
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
			case 枪手:
				初始枪手(hero);
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
		(hero.belongings.weapon=new WornShortsword()).鉴定();
		
		Item i=new 铠甲().鉴定();
			hero.belongings.armor=(铠甲)i;
		
		ThrowingStone stones=new ThrowingStone();
		stones.鉴定().放背包();
		
		Dungeon.quickslot.setSlot(0,stones);
		
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
		
		new Torch().放背包();
		
		(hero.belongings.weapon=new 双刃()).鉴定();
		
		CloakOfShadows cloak=new CloakOfShadows();
		(hero.belongings.misc=cloak).鉴定();
		hero.belongings.misc.activate(hero);
		
		ThrowingKnife knives=new ThrowingKnife();
		knives.鉴定().放背包();
		
		Dungeon.quickslot.setSlot(0,cloak);
		Dungeon.quickslot.setSlot(1,knives);
		
		new 探地卷轴().鉴定();
		new PotionOfInvisibility().鉴定();
	}
	
	private static void initHuntress(Hero hero){
		Item i=new 披风().鉴定();
			hero.belongings.armor=(披风)i;
		
		(hero.belongings.weapon=new 镶钉手套()).鉴定();
		
		灵能短弓 bow=new 灵能短弓();
		bow.鉴定().放背包();
		
		Dungeon.quickslot.setSlot(0,bow);
		
		new PotionOfMindVision().鉴定();
		new ScrollOfLullaby().鉴定();
	}
	
	private static void initDuelist(Hero hero){
		Item i=new 胸铠().鉴定();
			hero.belongings.armor=(胸铠)i;
		
		(hero.belongings.weapon=new 配刺剑()).鉴定();
		hero.belongings.weapon.activate(hero);
		
		ThrowingSpike spikes=new ThrowingSpike();
		spikes.数量(2).鉴定().放背包(); //set quantity is 3, but Duelist starts with 2
		
		Dungeon.quickslot.setSlot(0,hero.belongings.weapon);
		Dungeon.quickslot.setSlot(1,spikes);
		
		new PotionOfStrength().鉴定();
		new ScrollOfRetribution().鉴定();
	}
	
	private static void initCleric(Hero hero){
		Item i=new 祭服().鉴定();
			hero.belongings.armor=(祭服)i;
		
		(hero.belongings.weapon=new Cudgel()).鉴定();
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
		
		
		雪球 y=new 雪球();
		y.数量(3).鉴定().放背包();
		Dungeon.quickslot.setSlot(0,y);
		
		new ScrollOfLullaby().鉴定();
		new PotionOfFrost().鉴定();
	}
	
	private static void 初始镜魔(Hero hero){
		(hero.belongings.weapon=new 镜刃()).鉴定();
		(hero.belongings.armor=new 武服()).鉴定();
	}
	
	private static void 初始道士(Hero hero){
		(hero.belongings.weapon=new 铜钱剑()).鉴定();
		hero.belongings.weapon.activate(hero);
		(hero.belongings.armor=new 道袍()).鉴定();
	}
	
	private static void 初始行僧(Hero hero){
		(hero.belongings.weapon=new 白带()).鉴定();
		hero.belongings.weapon.activate(hero);
		Buff.延长(hero,BlobImmunity.class,450*25);
	}
	
	private static void 初始近卫(Hero hero){
		
		(hero.belongings.weapon=new 矛盾()).鉴定();
		hero.belongings.weapon.activate(hero);
		(hero.belongings.armor=new 战甲()).鉴定();
	}
	
	private static void 初始兽灵(Hero hero){
		
		(hero.belongings.weapon=new 臂铠()).鉴定();
		hero.belongings.weapon.activate(hero);
	}
	
	private static void 初始机器(Hero hero){
		
		修理扳手 x=new 修理扳手();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		Dungeon.quickslot.setSlot(0,x);
	}
	
	private static void 初始女忍(Hero hero){
		
		金玫苦无 x=new 金玫苦无();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		Dungeon.quickslot.setSlot(0,x);
		(hero.belongings.armor=new 忍服()).鉴定();
	}
	
	private static void 初始戒老(Hero hero){
		(hero.belongings.weapon=new 碧蓝巨剑()).鉴定();
		
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
		简易弩 x=new 简易弩();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		Dungeon.quickslot.setSlot(0,x);
		(hero.belongings.armor=new 魔披()).鉴定();
		
	}
	
	private static void 初始凌云(Hero hero){}
	
	private static void 初始血鬼(Hero hero){
		
		吸血飞刀 y=new 吸血飞刀();
		y.鉴定().放背包();
		Dungeon.quickslot.setSlot(0,y);
	}
	
	private static void 初始枪手(Hero hero){
		
		手枪 x=new 手枪();
		(hero.belongings.weapon=x).鉴定();
		hero.belongings.weapon.activate(hero);
		
		Dungeon.quickslot.setSlot(0,x);
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
			case 枪手:
				return Assets.Sprites.枪手;
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
		}
	}
	
	public String unlockMsg(){
		return shortDesc()+"\n\n"+Messages.get(HeroClass.class,name()+"_unlock");
	}
	
}
