

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatKingSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SheepSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShopkeeperSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SnakeSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ThiefSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class 重制 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v1_0_0_Changes(changeInfos);
	}
	/*

	 */
	public static void add_v1_0_0_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.0.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(物品表.爪), "爪",
				"新增2阶近战武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.半月刃), "半月刃",
				"新增3阶近战武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.PHANTOM_MEAT), "幻影鱼肉",
				"生肉+隐形药剂消耗5能量合成。"));
		changes.addButton( new ChangeButton(Icons.ALCHEMY.get(), "炼金",
				"配方3=>6。\n" +
						"现在正装备的装备不能放入炼金和提炼。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.枪手, tier(HeroClass.枪手))), "枪手",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.血鬼, tier(HeroClass.血鬼))), "血鬼",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.凌云, tier(HeroClass.凌云))), "凌云",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.鼠弟, tier(HeroClass.鼠弟))), "鼠弟",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.灵猫, tier(HeroClass.灵猫))), "灵猫",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.学士, tier(HeroClass.学士))), "学士",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.罗兰, tier(HeroClass.罗兰))), "罗兰",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.逐姝, tier(HeroClass.逐姝))), "逐姝",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.戒老, tier(HeroClass.戒老))), "戒老",
				"新增i英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.女忍, tier(HeroClass.女忍))), "女忍",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.机器, tier(HeroClass.机器))), "机器",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.兽灵, tier(HeroClass.兽灵))), "兽灵",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.近卫, tier(HeroClass.近卫))), "近卫",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.行僧, tier(HeroClass.行僧))), "行僧",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.道士, tier(HeroClass.道士))), "道士",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.镜魔, tier(HeroClass.镜魔))), "镜魔",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.重武, tier(HeroClass.重武))), "重武",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.巫女, tier(HeroClass.巫女))), "巫女",
				"新增英雄。"));
		changes.addButton( new ChangeButton(new MimicSprite.毒气(), "毒气宝箱怪房",
				"新增房间。"));
		changes.addButton( new ChangeButton(Icons.神力之泉.get(), "神力之泉",
				"新增神力之泉，英雄战上面可以获得永久力量和生命，物品在上面可以永久减少2力量需求和升级一次。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.单身狗粮), "单身狗粮",
				"7-8月掉落单身狗粮。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "食物条",
				"状态栏添加食物条。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.杂物袋), "杂物袋",
				"商店出售杂物袋。"));
		changes.addButton( new ChangeButton(Icons.SEED.get(), "种子",
				"现在输入代码名可以直接生成在背包。"));
		changes.addButton( new ChangeButton(Icons.DISPLAY.get(), "动画加快",
				"添加可调整动画加快按钮。"));
		changes.addButton( new ChangeButton(Icons.CONTROLLER.get(), "固定移速",
				"可调整固定移速，例如在1.05移速时固定1。"));
		changes.addButton( new ChangeButton(Icons.AUDIO.get(), "主题曲",
				"我的三体《夜航星》。"));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_CAPE), "荆棘斗篷",
				"回归。"));

		changes.addButton( new ChangeButton(new ImpSprite(), "野心勃勃的小恶魔",
				"不再半透明。"));
		changes.addButton( new ChangeButton(new GhostSprite(), "悲伤幽灵",
				"移速50% => 43%。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "升级",
				"升级提升的生命+0.6，命中和闪避+0.21。最大等级-5，升级所需经验初始+10，每级+20。\n" ));
		changes.addButton( new ChangeButton(new ThiefSprite(), "疯狂小偷",
				"偷后移速83% => 75%。\n" +
						"物理防御时掉落金币1 => 10"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ENERGY), "能量晶体",
				"武器和护甲、神器、法杖和戒指可以提炼，提炼量是金币的2.5%+1，投掷武器是金币的(2.5%/数量+1)x数量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.AMULET), "Yendor护符",
				"结算时的英雄贴图采用另一种。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RAPIER), "配刺剑",
				"最小伤害+1，最大伤害+1，最大伤害4x(阶+1)=5x(阶+1)，攻击延迟1=>0.75。\n" +
						"每级可以提升1格挡量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.KATANA), "武士刀",
				"每级可以提升1格挡量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.QUARTERSTAFF), "铁头棍",
				"每级可以提升1格挡量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.DAGGER), "双钗",
				"最小伤害+1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SAI), "双刃",
				"最小伤害+1，最大伤害4x(阶+1)=3x(阶+1)，攻击延迟1=>0.75。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.GLOVES), "镶钉手套",
				"最小伤害+1，最大伤害2.5x(阶+1)+0.5x(阶=1)=2x(阶+1)+0.4x(阶=1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.GLOVES), "GAUNTLETS",
				"最小伤害+1，最大伤害2.5x(阶+1)+0.5x(阶=1=2x(阶+1)+0.4x(阶=1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.水袋), "水袋",
				"灌满的水袋可以合成一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.LIQUID_METAL), "液金",
				"不管合成多少，都只消耗3能量=>多少数量消耗多少能量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"改成类似合成液金配方。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.尸尘), "尸尘",
				"金币数量1 => 自然生成金币。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.BATTLE_AXE), "战斧",
				"移除精度加成。\n" +
						"最大伤害4x(阶+1)+等级x(阶+1)+最大伤害4x(阶+2)+等级x(阶+2)。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAR_HAMMER), "武技",
				"武器充能最大充能8 => 10。\n" +
						"勇士职业不改变武器最大充能。" ,

						"顺劈\n" +
						"统一+34%伤害。\n" ,

						"连击统\n" +
						"一攻速回合内攻击两次。"));
		changes.addButton( new ChangeButton(new SheepSprite(), "魔法绵羊",
				"彩蛋。"));
		changes.addButton( new ChangeButton(Icons.INFO.get(), "信息面板",
				"优化。"));
		changes.addButton( new ChangeButton(Icons.LANGS.get(), "语言设置",
				"移除。"));

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"英雄\n\n" +
						"浮空状态在深渊点击自身可以无伤掉下去。\n"+
						"神器和戒指栏位=>两个杂项栏位。\n"+
						"最大生命-4。\n"+
						"各英雄都拥有特定的效果，武器装备更符合设定。\n"+
						"除了战士都移除水袋，护甲都变成专属护甲。\n"+
						"再生恢复生命1=>25%生命力。"+
						"新增生命力=根号(最大生命+是英雄则额外+力量)，大部分天赋等都被此数值影响。"
				,
				"杂项\n\n" +
						"投掷和弓在近距离最大命中不变，非近距离-33%最大命中。\n" +
						"优化感知、视野范围、光照范围计算。"
		));
		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ShopkeeperSprite(), "商人房间",
						"1层有商店。\n" +
						"非战士在6层会卖水袋。\n" +
						"商店的小包口粮x2 => 口粮+小包口粮。\n" +
						"随机一个药剂或卷轴 => 两张随机卷轴+一瓶随机药剂。"));
		changes.addButton( new ChangeButton(new RatKingSprite(), "鼠王房间",
				"移速2=>1.5\n" +
						"金币数量x2。"));
		changes.addButton( new ChangeButton(new SnakeSprite(), "下水道巨蛇",
				"最大攻击+2。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "天赋系统",
				"移除一层和二层的一个天赋，增强大部分。\n" +
						"天赋解锁从2/7/13 => 2/5/11\n" +
						"天赋点数5/6/8 => 3/6/9\n" +
						"二层和三层最大天赋点+1"));
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢",
				"献祭房\n" +
						"所需经验减少90%，并且可以扔治疗药剂直接献祭完成。\n" ,

						"毒气房\n" +
						"金币数量x2。\n" ,

						"第一层必定生成一瓶经验药剂。\n" +
						"每个区域生成的升级卷轴数量+1。\n" +
						"每个区域第3层必定一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SOMETHING), "物品",
					"一些物品图片更新。\n" +
					"更好的物品图标和底色数值显示。\n" +
							"走到金币、能量晶体、水珠、种子、钥匙和背包有的且可堆叠物品，自动拾取。\n" +
							"饰品提炼能量5=>4+等级x3"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ORE), "暗金矿",
					"可以出售和提炼。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
			"根骨之戒\n" +
					"力量+1 => +2。提升生命系数1.035 => 1.1。",

					"精准之戒\n" +
					"+50%等级的攻击范围。",

					"武力之戒\n" +
					"空手伤害1~力量-8=>25%生命力~75%生命力+100%的武力之戒的最小~最大伤害。\n" +
					"拳套武器+25%的武力之戒的最小~最大伤害和75%的空手最小~最大伤害。\n" +
					"最小和最大伤害不会因为等级为0不取最大阶伤。\n" +
					"只对近战武器生效=>投掷武器也生效，且额外增加最小伤害。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_HOLDER), "种子",
					"速行蓟\n" +
							"的时间气泡攻击不会打断时间冻结。\n" ,

							"冰冠花\n" +
							"不会被燃烧，且在踩踏能消灭火墙。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.FISHING_SPEAR), "投掷武器",
					"捕鱼矛、投矛、标枪、三叉戟\n" +
							"都拥有对第一次防御该武器的第一必定最大攻击，但攻击需要花费1.5回合。\n" ,

							"手里剑和苦无\n" +
							"都拥有回合冷却后不消耗回合的投掷和伏击伤害加成，且攻击需要花费0.67回合。\n" ,

							"重型回旋镖\n" +
							"回来的时间3回合=>1回合。\n" ,

							"飞槌和投掷锤\n" +
							"在命中的敌人还会眩晕2回合。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_HOLDER), "法杖",
					"升级获得1充能。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SCROLL_HOLDER), "卷轴",
//					"选择物品使用的卷轴在取消时获得对应符石：\n" +
//							"升级卷轴=>附魔符石。\n"+
//							"鉴定卷轴=>感知符石。\n"+
//							"嬗变卷轴=>强化符石。\n"+
//							"祛邪卷轴=>探魔符石。",
							"注魔秘卷\n" +
							"还附带一次升级。"
				));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.POTION_HOLDER), "药剂",
					"治疗药剂\n" +
							"最大生命80% => 90%。\n" +
							"恢复速度25% => 34%。",

							"奥术护盾合剂\n"+
							"最大生命护盾60% => 75%。",

							"净化药剂\n"+
							"持续回合+5。",

							"极速药剂\n"+
							"移速3 => 4。\n" +
							"持续回合20 => 15",

							"精力回复合剂\n" +
							"移速1.5 => 2.5。\n" +
							"持续回合100 => 60"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.OVERPRICED), "小包口粮",
				"每区域第4层必定生成一个。\n" +
						"金币价值减半"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_FIREBLOOM), "烈焰花",
				"4层必定生成。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RATION), "吃饭机制",
				"吃饭恢复25%生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
				"最大充能+1 => +2。\n" +
				"升级获得1充能。\n" +
				"自带+1强化等级。\n" +
				"自带法杖回收，且能吸收灌注法杖一半的等级。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.WIDE_SEARCH), "搜索机制",
				"搜索会在搜索范围内拾取物品和打开箱子。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.SUCKER_PUNCH), "伏击机制",
				"伏击+0~25%敌人生命力，隐形伏击+0~25%敌人生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_MAGIC_MISSILE), "魔弹法杖",
				"升级则会暂时强化其他法杖=>从升级获得额外一次等级"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_FROST), "冰霜法杖",
				"对冻伤和冻结的敌人建设伤害=>冻伤增加伤害且冻结敌人，如果冻结就减少伤害。\n" +
						"在水中额外2回合冻伤=>直接冻结。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ANKH), "十字架",
				"未祝福效果 => 残血无敌复活。\n" +
						"祝福效果 => 满血无敌复活"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_MIGHT), "根骨秘药",
				"+1力量+暂时最大生命 => +15永久最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.破损纹章), "破损纹章",
				"初始护盾3，并计算护甲阶级 => 1，并计算护甲阶级。\n" +
				"半血受伤获得 => 受伤获得。\n" +
				"提升护甲的等级改成强化等级。\n" +
				"可额外携带1级，冷却150 => 50。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
				"最小攻击成长20%=>4%，最大攻击-1。\n" +
						"箭矢能触发部分东西。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_CHALICE1), "蓄血圣杯",
				"血祭消耗生命-5。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.BACKPACK), "背包",
				"背包格子+10。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ALCHEMIZE), "炼金菱晶",
				"商店卖数量x3。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.时光沙漏), "时光沙漏",
				"攻击不会打断时间冻结。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARCANE_RESIN), "奥术树脂",
				"取消等级升级上限。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
				"对武器的增强\n" +
						"增加伤害50%减少67%攻速 => 增加25%伤害\n" +
						"增加33%攻速减少30%伤害 => 增加34%攻速",

						"对护甲的增强\n" +
						"增加100%防御减少200%闪避 => 增加25%防御\n" +
						"增加200%闪避减少100%防御 => 增加50%闪避"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.FIRE, true), "燃烧",
				"燃烧获得火把效果。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.ANKH, true), "无敌",
				"初始添加3回合 => 10回合。\n" +
						"重生十字架 祝福3回合=> 未祝福5回合，祝福10回合。"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.削弱);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "口粮",
				"英雄初始不携带口粮。"));
		changes.addButton( new ChangeButton(new GooSprite(), "粘咕",
				"受到燃烧伤害+20%生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"3个合成时消耗能量2 => 3。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.无声步伐), "无声步伐",
				"所有英雄的惊扰距离+1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.CROWN), "护甲技能",
				"矮人国王不掉落，护甲技能移除。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_FEATHER), "羽落秘药",
				"移除。"));

	}

}
