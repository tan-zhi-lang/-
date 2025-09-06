

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BlacksmithSprite;
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
		changes.addButton(new ChangeButton(new ItemSprite(物品表.中国国旗), "中国国旗",
		"新增中国国旗饰物，在国庆开局自带。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
		"新增心力之戒、全知之戒、时间之戒。"));
		changes.addButton( new ChangeButton(Icons.玩法开.get(), "玩法",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.系统开.get(), "系统",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.解压开.get(), "解压",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.炼狱开.get(), "炼狱",
				"新增游戏选项。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.爪), "爪",
				"新增2阶近战武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.半月刃), "半月刃",
				"新增3阶近战武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.PHANTOM_MEAT), "幻影鱼肉",
				"生肉+隐形药剂消耗5能量合成。"));
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
				"新增英雄。"));
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
				"新增房间，必定生成毒气药剂。"));
		changes.addButton( new ChangeButton(Icons.神力之泉.get(), "神力之泉",
				"新增神力之泉，英雄站上面永久+10%力量，物品在上面永久减少2力量需求。"));
		changes.addButton( new ChangeButton(Icons.天赋之泉.get(), "天赋之泉",
				"新增天赋之泉，英雄站上面永久+1天赋点，物品在上面升级一次。"));
		changes.addButton( new ChangeButton(Icons.CHEVRON.get(), "日历",
				"地牢探索指南新增日历节日。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.单身狗粮), "单身狗粮",
				"7-8月掉落单身狗粮。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.杂物袋), "杂物袋",
				"商店出售杂物袋。"));
		changes.addButton( new ChangeButton(Icons.DISPLAY.get(), "显示设置",
				"界面重构。\n" +
				"添加可调整动画加快按钮。\n" +
				"新增可调整画面同步按钮，关了才不会锁帧。"));
		changes.addButton( new ChangeButton(Icons.CONTROLLER.get(), "固定移速",
				"可调整固定移速，例如在1.05移速时固定1。"));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new TalentIcon(Talent.HEIGHTENED_SENSES), "视野和感知",
				"视野算法优化，视野范围+光照范围不得超过视野范围，除非增加视野范围。"));
		changes.addButton( new ChangeButton(Icons.ALCHEMY.get(), "炼金",
				"配方3=>6。\n" +
						"现在正装备的装备不能放入炼金和提炼。"));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_CAPE), "荆棘斗篷",
				"回归。"));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "食物条",
				"状态栏添加食物条。"));
		changes.addButton( new ChangeButton(Icons.SEED.get(), "种子",
				"现在输入代码名可以直接生成在背包。"));
		changes.addButton( new ChangeButton(Icons.AUDIO.get(), "主题曲",
				"我的三体《夜航星》。"));
		changes.addButton( new ChangeButton(new ImpSprite(), "野心勃勃的小恶魔",
				"不再半透明。"));
		changes.addButton( new ChangeButton(new GhostSprite(), "悲伤幽灵",
				"移速50%=>43%。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "升级",
				"升级提升的生命+0.625，命中和闪避+0.21。最大等级-5。\n" +
				"升级所需经验初始10+5/(级-1)=>10+10/级。\n" ));
		changes.addButton( new ChangeButton(new ThiefSprite(), "疯狂小偷",
				"偷后移速83%=>75%。\n" +
						"物理防御时掉落金币1=>10"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ENERGY), "能量晶体",
				"武器和护甲、神器、法杖和戒指可以提炼，提炼量是金币的2.5%+1，投掷武器是金币的(2.5%/数量+1)x数量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.AMULET), "Yendor护符",
				"结算时的英雄贴图采用另一种。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.水袋), "水袋",
				"灌满的水袋可以合成一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.LIQUID_METAL), "液金",
				"不管合成多少，都只消耗3能量=>多少数量消耗多少能量。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"改成类似合成液金配方。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.尸尘), "尸尘",
				"金币数量1=>自然生成金币。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAR_HAMMER), "武技",
				"武器充能最大充能8=>10。\n" +
						"勇士职业不改变武器最大充能。" ,

						"顺劈\n" +
						"统一+34%伤害。\n" ,

						"连击统\n" +
						"一攻速回合内攻击两次。"));
		changes.addButton( new ChangeButton(new SheepSprite(), "魔法绵羊",
				"交互有彩蛋，还能获得经验。"));
		changes.addButton( new ChangeButton(Icons.INFO.get(), "信息面板",
				"优化。"));
		changes.addButton( new ChangeButton(Icons.LANGS.get(), "语言设置",
				"移除。"));

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"英雄\n\n" +
						"浮空状态在深渊点击自身可以无伤掉下去。\n"+
						"神器和戒指栏位=>两个杂项栏位。\n"+
						"最大生命-5。\n"+
						"各英雄都拥有特定的效果，武器装备更符合设定。\n"+
						"除了战士都移除水袋，护甲都变成专属护甲。\n"+
						"饥饿首次受伤450*最大生命/1000，后续受伤最大生命/1000=>首次受伤18%生命力，后续受伤13%。"+
						"再生恢复生命1=>13%生命力。"+
						"新增生命力=根号(最大生命)，大部分天赋等都被此数值影响。"
				,
				"杂项\n\n" +
				"投掷和弓在近距离最大命中不变，非近距离-25%最大命中。\n" +
				"设置UI更新，且添加和移除部分按钮。"
		));
		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				
				"武器伤害调整\n" +
				"所有武器伤害倍率大于和小于1的最小和最大伤害，能从升级正确分配伤害。" +
				"即近战武器最小伤害=阶+级、最大伤害=5x(阶+1)+级x(阶+1)不同武器调整5的值=>不同武器x伤害倍率来改动。" +
				"即投掷武器最小伤害=2*阶+级、最大伤害=5x阶+级x阶不同武器调整5的值=>不同武器x伤害倍率来改动。\n" ,
				
				"修复酸蚀法杖高等级的施法会导致低等级的施法伤害没有正确分配，同时气体量50+10x级=>30+15x级。\n" +
				"修复神偷袖章偷取没有掉落物的敌人崩溃。\n" +
				"修复恶魔撕裂者有概率自己跳入深渊。\n" +
				"修复潜在存在的背包物品寻找问题。\n" +
				"武器部分格挡量不能根据等级提升。\n" +
				"修复护甲使用强化符石最小防御和最大防御没有正确分配。"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new ShopkeeperSprite(), "商人房间",
						"1区域也有商店，卖1阶武器和护甲。\n" +
						"非战士在第一个商店会卖水袋。\n" +
						"商店的武器和护甲，戒指和法杖有概率拥有等级，且必定拥有1级。\n" +
						"商店的小包口粮x2=>口粮+小包口粮。\n" +
						"随机一个药剂或卷轴=>两张随机卷轴+一瓶随机药剂。"));
		changes.addButton( new ChangeButton(new BlacksmithSprite(),"巨魔铁匠",
				"任务要求数量40=>20。"));
		changes.addButton( new ChangeButton(new RatKingSprite(), "鼠王房间",
				"移速2=>1.5\n" +
						"金币数量x2。"));
		changes.addButton( new ChangeButton(new SnakeSprite(), "下水道巨蛇",
				"最大攻击+2。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "天赋系统",
				"移除一层和二层的一个天赋，增强大部分。\n" +
						"天赋解锁从2/7/13=>2/5/11\n" +
						"天赋点数5/6/8=>3/6/9\n" +
						"二层和三层最大天赋点+1"));
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢",
				"献祭房\n" +
						"所需经验减少90%，并且可以站在祭坛上扔治疗药剂直接献祭完成。\n" ,

						"毒气房\n" +
						"金币数量x2。\n" ,

						"第一层怪物数量+2。\n" +
						"第一层必定生成一瓶经验药剂。\n" +
						"每个区域生成的升级卷轴数量-1。\n" +
						"每个区域第3层必定一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ORE), "暗金矿",
										   "可以出售和提炼。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SOMETHING), "物品",
					"物品可以重命名。\n" +
					"一些物品图片更好。\n" +
					"更好的物品图标和底色，数值显示。\n" +
							"饰品提炼能量5=>4+等级x3"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
			"部分描述更好。",
				   "根骨之戒\n" +
					"力量+1=>+2。提升生命系数1.035=>1.1。",

					"狂怒之戒\n" +
					"1.09051=>1.091。",

					"精准之戒\n" +
					"+50%等级的攻击范围。",

					"能量之戒\n" +
					"护甲充能=>武器充能。",

					"武力之戒\n" +
					"空手伤害1~力量-8=>10%生命力~20%生命力+40%的武力之戒的最小~最大伤害。\n" +
					"拳套武器+25%效果的武力之戒的最小~最大伤害和50%效果的空手最小~最大伤害。\n" +
					"最小和最大伤害不会因为等级为0不取最大阶伤。\n" +
					"只对近战武器生效=>投掷武器也生效，且额外增加50%戒指的最小伤害。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_HOLDER), "种子",
					"速行蓟\n" +
							"时间气泡攻击不会打断。\n" ,

							"冰冠花\n" +
							"不会被燃烧，且在触发能消灭火墙。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器1",
										   "权杖\n"+
										   "在命中的敌人34%概率还会眩晕1回合。\n" +
										   "命中率140%=>100%。\n" +
										   "攻击间隔1=>1.2。\n" +
										   "伤害倍率0.8=>1.3",
										   
										   "魔岩拳套\n" +
										   "最小伤害+1，命中率1=>1.1。",
										   
										   "长矛和关刀\n" +
										   "命中率1=>0.8，攻击间隔1.5=>1.3。",
										   
										   "链枷可以伏击。",
										   
										   "武器反超力增加命中，转换效率为根号武器力量-力量的3%加成。\n" +
										   "武器反超力增加攻速，转换效率为根号武器力量-力量的3%加成。\n" +
										   "空手增加命中，转换效率为根号力量的3%加成。\n" +
										   "空手增加攻速，转换效率为根号力量的25%加成。" ,
										   
										   "描述命中率、攻击间隔、伤害倍率、攻击范围、伏击率和格挡量。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器2",
										   "硬头锤\n" +
										   "命中率1.28=>0.9，攻击间隔1=>1.1，伤害倍率0.8=>1.2。在命中的敌人34%概率还会眩晕1回合。",
										   
										   "短柄镰\n" +
										   "命中率0.68=>0.9，攻击间隔1=>0.9，伤害倍率1.3=>1。",
										   
										   "镶钉手套\n" +
										   "最小伤害+1，伤害倍率0.5=>0.4，命中率1=>1.2。",
										   
										   "圆盾和巨型方盾\n" +
										   "命中率1=>0.7，攻击间隔1=>1.2，伤害倍率0.6=>1.4。",
										   
										   "战锤\n" +
										   "命中率1.2=>0.7，攻击间隔1=>1.5，伤害倍率0.8=>1.6。在命中的敌人34%概率还会眩晕1回合。"
										   ));
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器3",
										   
										   "配刺剑\n" +
										   "最小伤害+1，命中率1=>1.1，攻击间隔1=>0.8。",
										   
										   "巨剑\n" +
										   "命中率1=>0.7，攻击间隔1=>1.5，伤害倍率1=>1.8。",
										   
										   "巨斧\n" +
										   "命中率1=>0.6，攻击间隔1=>1.6，伤害倍率1.8=>2。",
										   
										   "十字弩\n" +
										   "命中率1=>1.1，攻击间隔1=>0.9，伤害倍率1=>0.8。",
										   
										   "战镰\n" +
										   "命中率0.8=>0.6，攻击间隔1=>1.6，伤害倍率1.3=>1.6。"));
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器4",
										   
										   "战斧\n" +
										   "命中率1=>0.7，攻击间隔1=>1.5，伤害倍率0.8=>1.8。",
										   
										   "长鞭\n" +
										   "命中率1=>0.7，攻击间隔1=>1.2，伤害倍率1=>1.4。",
										   
										   "双钗\n" +
										   "最小伤害+1，命中率1=>1.2，伤害倍率0.5=>0.4。",
										   
										   "手斧\n" +
										   "命中率1.32=>1.1，攻击间隔1=>0.9，伤害倍率1=>0.8。",
										   
										   "链枷\n" +
										   "攻击间隔1=>1.3，伤害倍率1.4>1.3。"));
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器5",
										   
										   "暗杀之刃\n" +
										   "命中率1=>1.1，攻击间隔1=>0.9。",
										   
										   "符文之刃\n" +
										   "等级获得额外伤害=>伤害倍率1.1和额外获得10%的等级。",
										   
										   "匕首\n" +
										   "命中率1=>1.3，攻击间隔1=>0.7，伤害倍率0.8=>0.6。",
										   
										   "双刃\n" +
										   "最小伤害+1，命中率1=>1.3，攻击间隔1=>0.7，伤害倍率0.8=>0.6。",
										   
										   "弯刀\n" +
										   "命中率1=>1.1，攻击间隔0.8=>0.9，伤害倍率1=>0.8。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "近战武器6",
										   "武士刀\n" +
										   "命中率1=>1.1，攻击间隔1=>0.9，伤害倍率0.8=>1.1。",
										   
										   "破旧短剑和短剑\n" +
										   "命中率1=>1.1，攻击间隔1=>0.9，伤害倍率1=>0.8。",
										   
										   "铁头棍\n" +
										   "命中率1=>0.9，攻击间隔1=>1.1，伤害倍率0.8=>1.2。"));
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MISSILE_HOLDER), "投掷武器1",
					"捕鱼矛、投矛、标枪、三叉戟\n" +
							"命中率1=>0.8，攻击间隔1=>1.3，伤害倍率1=>1.3。" +
							"都拥有对第一次防御该武器的第一必定最大攻击。\n" ,

							"苦无\n" +
							"伤害倍率0.8=>1。\n" ,
							
							"飞刺\n" +
							"命中率1=>0.9，攻击间隔1=>1.1，伤害倍率0.6=>1.2。\n" ,
							
							"飞斧头\n" +
							"攻击间隔1=>1.1，最小伤害倍率1.5最大伤害倍率0.8=>1.2。\n" ,

							"投石、飞槌和投掷锤\n" +
							"手里剑命中率1=>0.9，攻击间隔1=>1.1，伤害0.8=>1.2(投石伤害倍率1=>1.2)。在命中的敌人还会眩晕1回合。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MISSILE_HOLDER), "投掷武器2",
										   
										   "飞镖\n" +
										   "攻击间隔1=>0.9，攻击间隔1=>0.8。\n" ,
										   
										   "重型回旋镖\n" +
										   "攻击间隔1=>0.7，攻击间隔1=>1.5，伤害倍率0.8=>1.6。\n" ,
										   
										   "流星索和震爆方石\n" +
										   "命中率1=>0.9，攻击间隔1=>1.1，伤害倍率1=>1.2。流星索施加残废时间10=>5。\n" ,
										   
										   "飞刀\n" +
										   "命中率1=>1.1，攻击间隔1=>0.9，伤害倍率1.2=>0.8。\n" ,
										   
										   "手里剑和苦无\n" +
										   "手里剑命中率1=>0.8，攻击间隔1=>0.6，伤害倍率1=>0.6。都拥有回合冷却后不消耗回合的投掷和伏击伤害加成。\n" ,
										   
										   "重型回旋镖\n" +
										   "回来的时间3回合=>1回合。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER), "护甲",
					"护甲反超力增加闪避，转换效率为根号武器力量-力量的3%加成。\n"+
					"护甲反超力增加移速，转换效率为根号武器力量-力量的3%加成。\n"+
										   "裸衣增加闪避，转换效率为根号力量的3%加成。\n"+
										   "裸衣增加移速，转换效率为根号力量的25%加成。"
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
							"最大生命80%+14=>90%。\n" +
							"恢复速度25%=>30%。",

							"奥术护盾合剂\n"+
							"最大生命护盾60%+10=>70%。",

							"神意启发合剂\n"+
							"天赋点+2=>1。",

							"净化药剂\n"+
							"持续回合+5。",

							"极速药剂\n"+
							"移速3=>4。\n" +
							"持续回合20=>15",

							"精力回复合剂\n" +
							"移速1.5=>2.5。\n" +
							"持续回合100=>60"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_HOLDER), "法杖",
					"魔弹法杖\n" +
							"升级则会暂时强化其他法杖=>从升级获得额外一次等级。",

							"冰霜法杖\n"+
							"对冻伤和冻结的敌人建设伤害=>冻伤增加伤害且冻结敌人，如果冻结就减少伤害。\n" +
							"在水中额外2回合冻伤=>直接冻结。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.OVERPRICED), "小包口粮",
				"每区域第4层必定生成一个。\n" +
						"金币价值减半"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_FIREBLOOM), "烈焰花",
				"4层必定生成。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RATION), "吃饭机制",
				"吃饭恢复26%生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
				"攻击间隔1=>1.2。\n" +
				"升级获得1充能。\n" +
				"自带+1强化等级。\n" +
				"自带法杖回收，且能吸收灌注法杖一半的等级。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.WIDE_SEARCH), "搜索机制",
				"搜索会在搜索范围内拾取非陷阱上的物品和打开箱子。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.SUCKER_PUNCH), "伏击机制",
				"伏击+0~25%敌人生命力，隐形伏击+0~25%敌人生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ANKH), "十字架",
				"未祝福效果=>残血无敌复活。\n" +
						"祝福效果=>满血无敌复活"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_MIGHT), "根骨秘药",
				"+1力量+暂时最大生命=>+15永久最大生命和1生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.破损纹章), "破损纹章",
				"初始护盾3，并计算护甲阶级=>1，并计算护甲阶级。\n" +
				"半血受伤获得=>受伤获得。\n" +
				"提升护甲的等级改成强化等级。\n" +
				"可额外携带1级，冷却150=>50。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
				"最小攻击成长20%=>4%，最大攻击-1。\n" +
						"箭矢能触发部分东西。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_HOLDER), "神器",
				"蓄血圣杯\n" +
							"血祭消耗生命-5。",
									
						   "时光沙漏\n" +
						   "攻击不会打断时间冻结。"
										   ));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.BACKPACK), "背包",
				"背包格子+10。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ALCHEMIZE), "炼金菱晶",
				"商店卖数量x2。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARCANE_RESIN), "奥术树脂",
				"法杖转换数量2*(级-树脂级)=>级-树脂级+1，并且级-树脂级+1需要大于0才能转换。\n" +
				"取消等级升级上限。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
				"对投掷不会降低耐久。" ,
				"对武器的增强\n" +
						"新增+30%命中\n" +
						"+伤害50%-67%攻速x(等级+2)=>+10%伤害。\n" +
						"+33%攻速-30%伤害x(等级+2)=>+20%攻速。",

						"对护甲的增强\n" +
						"新增+20%移速\n" +
						"+100%防御-200%闪避x(等级+2)=>+10%防御。\n" +
						"+200%闪避-100%防御x(等级+2)=>+30%闪避。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.FIRE, true), "燃烧",
				"燃烧获得火把效果。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "口粮",
										   "任何填充饱腹都能触发吃饭效果，不过是叠加到150才会，并且能够堆叠触发。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.ANKH, true), "无敌",
				"初始添加3回合=>10回合。\n" +
						"重生十字架 祝福3回合=> 未祝福5回合，祝福10回合。"));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.削弱);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "口粮",
				"英雄初始不携带口粮。"));
		changes.addButton( new ChangeButton(new GooSprite(), "粘咕",
				"受到燃烧伤害+20%生命力。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"3个合成时消耗能量2=>3。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.无声步伐), "无声步伐",
				"所有英雄的惊扰距离+1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.CROWN), "护甲技能",
				"矮人国王不掉落，护甲技能移除。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_FEATHER), "羽落秘药",
				"移除。"));

	}

}
