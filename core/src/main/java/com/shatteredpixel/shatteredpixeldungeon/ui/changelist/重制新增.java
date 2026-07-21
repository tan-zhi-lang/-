

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.九龙拉管;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.灵魂焰灯;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.生命蜡烛;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.虫箭;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.宝物袋;
import com.shatteredpixel.shatteredpixeldungeon.items.food.红蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.food.绿蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.food.蓝蘑菇;
import com.shatteredpixel.shatteredpixeldungeon.items.food.蜂蜜;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.恢复之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.极肚之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.魔攻之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.结能菱晶;
import com.shatteredpixel.shatteredpixeldungeon.items.spells.结金菱晶;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.世界标尺;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.丛林玫瑰;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.优惠卡;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.传奇肛塞;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.动力之靴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.圣金之沙;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.妖精粉尘;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.巨大蟹钳;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.幸运硬币;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.投机之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.断骨法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.桃木剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.水晶碎块;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.火毒箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.狂妄皇冠;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.男人国徽章;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.真正护符;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.破损短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.磨刀石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.神圣之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.精神支柱;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.虚无透纱;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.血腥生肉;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.角斗链枷;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.遗失符石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.重力场球;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.骸骨左轮;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.冰海法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.影织法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.棱镜法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.浓毒法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.潮霆法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.烈焰法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.落石法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.半月刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.变态刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.地裂镰;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.妖刀村正;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰鱼剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.封印之杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.斩马刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无影剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无限之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.日炎链刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.死神镰刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流火;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.爪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.猩红散华;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.真铜短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.破甲锥;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.破败王剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碎缘剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.神农锄;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.草剃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蜜剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蝙蝠棒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.重锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金纹拐;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.锈右斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.锻造锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.商人信标;
import com.shatteredpixel.shatteredpixeldungeon.items.圣诞礼物;
import com.shatteredpixel.shatteredpixeldungeon.items.坠牢之星;
import com.shatteredpixel.shatteredpixeldungeon.items.封禁卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.护甲修理工具包;
import com.shatteredpixel.shatteredpixeldungeon.items.活力水晶;
import com.shatteredpixel.shatteredpixeldungeon.items.激泥酞酶;
import com.shatteredpixel.shatteredpixeldungeon.items.火把神的恩宠;
import com.shatteredpixel.shatteredpixeldungeon.items.生命果;
import com.shatteredpixel.shatteredpixeldungeon.items.生命水晶;
import com.shatteredpixel.shatteredpixeldungeon.items.神盾果;
import com.shatteredpixel.shatteredpixeldungeon.items.秘银;
import com.shatteredpixel.shatteredpixeldungeon.items.精纯药液;
import com.shatteredpixel.shatteredpixeldungeon.items.红包;
import com.shatteredpixel.shatteredpixeldungeon.items.自残绳;
import com.shatteredpixel.shatteredpixeldungeon.items.血药;
import com.shatteredpixel.shatteredpixeldungeon.items.进力药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.进级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.进阶宝典;
import com.shatteredpixel.shatteredpixeldungeon.items.魔力水晶;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM0Sprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SheepSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.传送阵眼动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.地狱猎犬动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.水蛭动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.sprites.石虱动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.蟑螂动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.蠕虫动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.骷髅战士动画;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class 重制新增{

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		新机制(changeInfos);
		新英雄(changeInfos);
		新武器(changeInfos);
		新禁忌物(changeInfos);
		新物品(changeInfos);
		新装备(changeInfos);
		新房间(changeInfos);
		新法杖(changeInfos);
		新选项(changeInfos);
		新怪物(changeInfos);
		新表情(changeInfos);
		新杂项(changeInfos);
	}
	/**
	轻便刻印+闪避-防御
	 头骨棒5，击杀敌人+1最大攻击
	*/

	private static void 新机制(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("新机制", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);


		changes.addButton( new ChangeButton("","主属性",

		"每个英雄有自己的主属性，最大攻击+主属性-10。\n"+
			"力量\n" +
			"1%治疗护盾，0.5%暴击率，1最大生命，因力量的空手伤害和满适配武器的额外伤害，满适配条件。",

				"敏捷\n"+
				"初始是10+0.4x升级等级(满级额外+0.4)，0.5最大护甲，影响命中、攻速、闪避、移速\n"+
				"武器满适配：命中√(力量-武器力量)×0.1%x敏捷，攻速√(力量-武器力量)×0.1%x敏捷\n"+
				"防具满适配：闪避√(力量-防具力量)×0.033%x敏捷，移速√(力量-防具力量)×0.033%x敏捷\n"+
				"裸身状态：闪避√(力量-10)×0.1%x敏捷，移速√(力量-10)×0.1%x敏捷\n"+
				"空手加成：命中√(力量-10)×0.1%x敏捷，攻速√(力量-10)×0.033%x敏捷",

				"魔力\n" +
				"初始是10，法杖、法、巫、道、忍术的收益，武器、法杖、神器充能速度5%，0.2最大魔抗。\n"+
				"生命、护甲、力量、敏捷、魔力属性最低为0.01，生命和护甲最大10_0000_000。\n"+
				"最大生命值20+5x升级等级=>10+1.625x等级(满级额外+1)。\n"+
				"升级+最大命中和最大闪避=>最大命中和最大闪避+等级-1(满级额外+1)，最大等级-5。\n"
											));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.WARRIOR,tier(HeroClass.WARRIOR))),"杂项机制",

											"新增吸血作用于攻击的伤害百分比恢复生命值(不过对非Boss仅1/2效果)，全能吸血作用于大部分的伤害百分比恢复生命值(不过对非Boss仅1/6效果)。\n"+
											"并且吸血和全能吸血会根据已损失生命，提供最多1.25倍率，最低0.6倍。\n"+
											"新增穿甲(固定值无视防御)，护甲穿透(百分比无视防御)，先x/后+-。",


											"敌人第一次被攻击+0.5伤害，伏击+0.5，隐形伏击再+0.5，敌人第一次被伏击再+0.5。\n"+
											"蛇皮走位，正常行动会叠加4层，攻击叠加6层，最多100层。满层时的攻击+移速的伤害，获得1回合时间气泡，并恢复移速的生命，并清空。\n"+


											//											"游戏第1、2局幸运值+1，每日挑战非重玩+1幸运值。\n"+
											"新增护甲，初始是5(5+等级(满级额外+1))，相当于不会随时间损失的物理护盾，根据护甲，最多30%伤害减免，最少10%伤害减免，包含爆炸、骷髅死亡爆炸和跳楼受伤(不含流血)。非战斗状态的每150回合修理1护甲。\n" +
											"击杀敌人施加连杀状态10回合，敌人被发现会施加被发现回合。\n"+
											//											"点击目的地会提示框，有敌人和陷阱则是红色，普遍绿色。\n"+
											"吃饭回血食物填充/150的生命。"

											));


		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), "新增杂项",
											"设置可调整物品命名、打断英雄、游戏提示、游戏提示、装备武器、装备武器、从不过节。\n"+
											"设置可调整固定攻速、固定移速、休息速度。\n"+
											"设置可调整帧率、字体大小、画面同步、动画加快、透明界面、更多按钮、颜色区块、文字寿命、提示行数。"));
	}

	private static void 新英雄(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("新英雄", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(HeroClass.来世));
		changes.addButton( new ChangeButton(HeroClass.血鬼));
		changes.addButton( new ChangeButton(HeroClass.凌云));
		changes.addButton( new ChangeButton(HeroClass.鼠弟));
		changes.addButton( new ChangeButton(HeroClass.灵猫));
		changes.addButton( new ChangeButton(HeroClass.学士));
		changes.addButton( new ChangeButton(HeroClass.罗兰));
		changes.addButton( new ChangeButton(HeroClass.逐姝));
		changes.addButton( new ChangeButton(HeroClass.戒老));
		changes.addButton( new ChangeButton(HeroClass.女忍));
		changes.addButton( new ChangeButton(HeroClass.机器));
		changes.addButton( new ChangeButton(HeroClass.兽灵));
		changes.addButton( new ChangeButton(HeroClass.近卫));
		changes.addButton( new ChangeButton(HeroClass.行僧));
		changes.addButton( new ChangeButton(HeroClass.道士));
		changes.addButton( new ChangeButton(HeroClass.镜魔));
		changes.addButton( new ChangeButton(HeroClass.重武));
		changes.addButton( new ChangeButton(HeroClass.巫女));
	}

	private static void 新武器(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("新武器", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new 蝙蝠棒()));
		changes.addButton(new ChangeButton(new 变态刀()));
		changes.addButton(new ChangeButton(new 无影剑()));
		changes.addButton(new ChangeButton(new 重锤()));
		changes.addButton(new ChangeButton(new 蝙蝠棒()));
		changes.addButton(new ChangeButton(new 猩红散华()));
		changes.addButton(new ChangeButton(new 真铜短剑()));
		changes.addButton(new ChangeButton(new 流火()));

		changes.addButton(new ChangeButton(new 火焰剑()));
		changes.addButton(new ChangeButton(new 地裂镰()));
		changes.addButton(new ChangeButton(new 寒冰镖()));
		changes.addButton(new ChangeButton(new 闪电双截棍()));

		changes.addButton(new ChangeButton(new 蜜剑()));
		changes.addButton(new ChangeButton(new 日炎链刃()));
		changes.addButton(new ChangeButton(new 神农锄()));
		changes.addButton(new ChangeButton(new 死神镰刀()));
		changes.addButton(new ChangeButton(new 无限之剑()));
		changes.addButton(new ChangeButton(new 封印之杖()));
		changes.addButton(new ChangeButton(new 妖刀村正()));
		changes.addButton(new ChangeButton(new 破败王剑()));
		changes.addButton(new ChangeButton(new 妖刀村正()));
		changes.addButton(new ChangeButton(new 饮血之剑()));
		changes.addButton(new ChangeButton(new 无尽之刃()));
		changes.addButton(new ChangeButton(new 锈右斧()));
		changes.addButton(new ChangeButton(new 无尽之刃()));
		changes.addButton(new ChangeButton(new 碎缘剑()));
		changes.addButton(new ChangeButton(new 草剃()));
		changes.addButton(new ChangeButton(new 金纹拐()));
		changes.addButton(new ChangeButton(new 英雄断剑()));
		changes.addButton(new ChangeButton(new 草剃()));

		//4
		changes.addButton(new ChangeButton(new 寒冰鱼剑()));

		//3
		changes.addButton(new ChangeButton(new 斩马刀()));
		changes.addButton(new ChangeButton(new 破甲锥()));
		changes.addButton(new ChangeButton(new 半月刃()));

		//2
		changes.addButton(new ChangeButton(new 爪()));

	}

	private static void 新禁忌物(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("新禁忌物", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new 真正护符(),"26层获得"));
		changes.addButton(new ChangeButton(new 丛林玫瑰(),"踩踏草丛1/900概率掉落"));
		changes.addButton(new ChangeButton(new 优惠卡(),"双十一开局获得"));
		changes.addButton(new ChangeButton(new 中国国旗(),"国庆节开局获得"));

		changes.addButton(new ChangeButton(new 桃木剑()));
		changes.addButton(new ChangeButton(new 男人国徽章()));
		changes.addButton(new ChangeButton(new 神圣之剑()));
		changes.addButton(new ChangeButton(new 投机之剑()));
		changes.addButton(new ChangeButton(new 血腥生肉()));
		changes.addButton(new ChangeButton(new 狂妄皇冠()));

		changes.addButton(new ChangeButton(new 火毒箭矢()));
		changes.addButton(new ChangeButton(new RatSkull()));
		changes.addButton(new ChangeButton(new 巨大蟹钳()));

		changes.addButton(new ChangeButton(new 骸骨左轮()));
		changes.addButton(new ChangeButton(new 断骨法杖()));
		changes.addButton(new ChangeButton(new 遗失符石()));
		changes.addButton(new ChangeButton(new 破损短剑()));
		changes.addButton(new ChangeButton(new 角斗链枷()));
		changes.addButton(new ChangeButton(new 磨刀石()));
		changes.addButton(new ChangeButton(new 幸运硬币()));
		changes.addButton(new ChangeButton(new 虚无透纱()));
		changes.addButton(new ChangeButton(new 精神支柱()));
		changes.addButton(new ChangeButton(new 圣金之沙()));
		changes.addButton(new ChangeButton(new 妖精粉尘()));
		changes.addButton(new ChangeButton(new 动力之靴()));
		changes.addButton(new ChangeButton(new 重力场球()));
		changes.addButton(new ChangeButton(new 水晶碎块()));
		changes.addButton(new ChangeButton(new 世界标尺()));
		changes.addButton(new ChangeButton(new 传奇肛塞()));

	}

	public static void 新物品( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新物品", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new 封禁卷轴(),"升级卷轴消耗5能量合成"));
		changes.addButton(new ChangeButton(new 进级卷轴(),"升级卷轴+激泥酞酶消耗5能量合成"));
		changes.addButton(new ChangeButton(new 进力药剂(),"潜力药剂+精纯药液消耗5能量合成"));
		changes.addButton(new ChangeButton(new 结金菱晶(),"念力结晶+激泥酞酶消耗5能量合成"));
		changes.addButton(new ChangeButton(new 结能菱晶(),"念力结晶+激泥酞酶消耗5能量合成"));
		changes.addButton(new ChangeButton(new 精纯药液(),"药剂+种子消耗5能量合成"));
		changes.addButton(new ChangeButton(new 激泥酞酶(),"卷轴+符石消耗5能量合成"));
		changes.addButton(new ChangeButton(new 秘银(),"秘银x3消耗5能量合成"));
		changes.addButton(new ChangeButton(new 护甲修理工具包(),
				//									   "护盾机制，改成免疫一次伤害，并且可以叠加，也不会随时间流逝。\n\n" +
										   "巨魔铁匠可以花费人情修复你的护甲。\n" +
										   "巨魔铁匠可以花费人情升级装备的阶，不超过5。\n" +
										   "商店会出售2+区域层数量。\n" +
										   "护甲改成防具，防具可以放入炼金锅转换护甲修理工具包，同酶优树酯。"));
		changes.addButton(new ChangeButton(new 进阶宝典(),
										   "3区域第3层必定生成一个。"));
		changes.addButton(new ChangeButton(new 血药(),
										   "商店必定出售1个。"
		));
		changes.addButton(new ChangeButton(new 自残绳(),"开局获得"
		));
		changes.addButton(new ChangeButton(new 火把神的恩宠(),
										   "3个火把合成。"
		));
		changes.addButton(new ChangeButton(new 圣诞礼物(),
										   "圣诞节开局获得一个。"));
		changes.addButton(new ChangeButton(new 商人信标(),
										   "可以从商人偷取或出售超过6个物品获得。\n" +
										   "可以无限使用炼金菱晶的商店系统。"));
		changes.addButton(new ChangeButton(new 锻造锤(),
										   "恶魔铁匠不要人情获得。"));
		changes.addButton(new ChangeButton(new 蜂蜜(),
										   "金色蜜蜂死亡必定掉落一个蜂蜜。\n" +
										   "蜂蜜罐击碎必定掉落一个蜂蜜。"));
		changes.addButton(new ChangeButton(new 红蘑菇(),
										   "女猎特性可以在高草找到"));
		changes.addButton(new ChangeButton(new 蓝蘑菇(),
										   "女猎特性可以在高草找到"));
		changes.addButton(new ChangeButton(new 绿蘑菇(),
										   "女猎特性可以在高草找到"));
		changes.addButton(new ChangeButton(new 红包(),
										   "春节开局获得"));
		changes.addButton(new ChangeButton(new 生命果(),
										   "踩踏草丛1/300概率获得，上限1个。"));
		changes.addButton(new ChangeButton(new 生命水晶(),
										   "洞穴层第三层生成一个。"));
		changes.addButton(new ChangeButton(new 坠牢之星(),
										   "19:30~4:30，1/450概率掉落，最多掉落45个。"
		));
		changes.addButton(new ChangeButton(new 魔力水晶(),
										   "5x坠牢之星消耗10能量合成。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.奥术水晶),"奥术水晶",
										   "五区第三层生成一个。"));
		changes.addButton(new ChangeButton(new 活力水晶(),
						  "五区第三层生成一个。"));
		changes.addButton(new ChangeButton(new 神盾果(),"矮人城第三层生成一个。"));
		changes.addButton(new ChangeButton(new 宝物袋(),"商店第一区域必定出售。"));
	
	}

	public static void 新装备( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新装备", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		changes.addButton(new ChangeButton(new 装甲之戒(),"裸衣+(根据力量自适应+3/2+等级/2)~根据力量自适应x(2+3/2+等级/2)的防御，防具+根据力量自适应+3/2+等级/2的最大防御。。"));
		changes.addButton(new ChangeButton(new 恢复之戒(),"生命再生和护甲再生+0.236x等级。"));
		changes.addButton(new ChangeButton(new 极肚之戒(),"最大饱腹+100x等级，但是力量+0.5%x等级x(最大饥饿+最大饱腹)。"));
		changes.addButton(new ChangeButton(new 魔攻之戒(),"施法对目标造成50%魔力+20%等级收益~90%魔力+67%等级收益。"));

		changes.addButton(new ChangeButton(new 灵魂焰灯()));
		changes.addButton(new ChangeButton(new 生命蜡烛()));
		changes.addButton(new ChangeButton(new 九龙拉管()));
		changes.addButton(new ChangeButton(new 虫箭()));
		changes.addButton(new ChangeButton(new 心之钢()));

	}
	
	public static void 新房间( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新房间", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		
		changes.addButton( new ChangeButton(new ItemSprite(物品表.SEED_ICECAP),"魔法冰霜房间",
											"必定生成液火药剂和烈焰花种子。\n" +
											"奖励同魔法火焰房间。"));
		changes.addButton( new ChangeButton(new DM0Sprite(),"DM0房",
											"必定生成麻痹药剂。\n" +
											"DM0遇到麻痹会死亡，死亡随机掉率卷轴药剂。"));
		changes.addButton( new ChangeButton(new SheepSprite(),"超级魔法绵羊房",
											"必定生成灵视药剂。\n" +
											"超级魔法绵羊是隐形的，只能被灵视药剂发现，可以被击杀，但是每次受伤会随机传送。死亡随机掉率神器、法杖、戒指"));
		changes.addButton( new ChangeButton(new MimicSprite.毒气(),"毒气宝箱怪房",
											"必定生成毒气药剂。\n" +
											"宝箱毒气怪遇到毒气会死亡，死亡随机掉率武器护甲。"));
		changes.addButton( new ChangeButton(Icons.神力之泉.get(),"神力之泉",
											"英雄站上面永久+1力量，武器或护甲在上面永久减少3力量需求。"));
		changes.addButton( new ChangeButton(Icons.天赋之泉.get(), "天赋之泉",
											"英雄站上面永久+1天赋点，物品在上面升级一次。"));
changes.addButton( new ChangeButton(Icons.幸运之泉.get(), "幸运之泉",
											"英雄站上面永久+1幸运值，物品在上面随机变成更好的物品。"));

		changes.addButton( new ChangeButton(Icons.DISTANT_WELL.get(),"干涸大井",
											"13层生成干涸大井。\n" +
											"大井外围每格20%概率生成随机药剂，中间必定一瓶永生秘药。"));
		changes.addButton( new ChangeButton(new 传送阵眼动画(),"传送阵眼",
											"11、18层生成矮人传送魔法阵房。\n" +
											"完成野心勃勃的小恶魔的任务，他不会没收你的矮人徽章，你可以用此来使用矮人传送魔法阵。\n" +
											"第五区域商店回出售4-5个矮人徽章。"));

	}
	
	public static void 新法杖( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新法杖", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(物品表.浓毒法杖), "浓毒法杖",
										   "毒气版酸蚀法杖，并且重制毒气代码，伤害1+地牢层数/25=>15%√毒气元素。\n"+new 浓毒法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.潮霆法杖), "潮霆法杖",
										   "连锁距离更远，伤害更高也更危险的加强版雷霆法杖。\n"+new 潮霆法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.落石法杖), "落石法杖",
										   "新增法杖。\n"+new 落石法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.棱镜法杖), "棱镜法杖",
										   "加强版散射棱光法杖。\n"+new 棱镜法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.影织法杖), "影织法杖",
										   "加强版散射解离法杖。\n"+new 影织法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.冰海法杖), "冰海法杖",
										   "大范围施法版冰霜法杖。\n"+new 冰海法杖().statsDesc()
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.烈焰法杖), "烈焰法杖",
										   "单体迷你版焰浪法杖。"+new 烈焰法杖().statsDesc()
		));
	}

	public static void 新选项( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新选项", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.解压开.get(), "解压",
											"小幅度幅度减少游戏难度。"));
		changes.addButton( new ChangeButton(Icons.系统开.get(), "系统",
											"大幅度减少游戏难度。"));
		changes.addButton( new ChangeButton(Icons.炼狱开.get(), "炼狱",
											"大幅度增加游戏难度。"));
		changes.addButton( new ChangeButton(Icons.派对开.get(),"派对",
											"改变游戏正面大于负面的玩法。"));
		changes.addButton( new ChangeButton(Icons.赛季开.get(),"赛季",
											"改变游戏的玩法。"));
		changes.addButton( new ChangeButton(Icons.STATS.get(), "杂项设置",
											"调整游戏一些设定。"));
		
	}
	
	public static void 新怪物( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新怪物", true, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new 地狱猎犬动画(),"地狱猎犬",
											"五区怪物，替换三四五层的恶魔撕裂者。"));
		changes.addButton( new ChangeButton(new 石虱动画(),"石虱",
											"三区怪物，替换二层的吸血蝙蝠。"));
		changes.addButton( new ChangeButton(new 蠕虫动画(),"蠕虫",
											"三区怪物，替换一和二层的吸血蝙蝠。\n" +
											"受到伤害会在敌人相邻传送"));
		changes.addButton( new ChangeButton(new 水蛭动画(),"水蛭",
											"一区怪物，替换二和三层的啮齿老鼠。\n" +
											"在水中每回合恢复15%最大生命。\n" +
											"100%吸血"));
		changes.addButton( new ChangeButton(new 蟑螂动画(),"蟑螂",
											"一区怪物，替换一层的下水道巨蛇。\n" +
											"根据已损失生命最多获得80%物理伤害减免，不过最少受到45%最大生命的伤害，与敌人相邻时，闪避为0。"));
		changes.addButton( new ChangeButton(new 骷髅战士动画(),"骷髅战士",
											"新增骷髅的变异体。\n" +
											"死亡掉落单手剑、长剑、巨剑任意一个。"));
		
	}

	public static void 新表情( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("新表情", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.睡眠), "睡眠",
											"将原版改成此"));
		changes.addButton( new ChangeButton(Icons.get(Icons.感叹), "感叹",
											"将原版改成此"));
		changes.addButton( new ChangeButton(Icons.get(Icons.问号), "问号",
											"将原版改成此"));
		changes.addButton( new ChangeButton(Icons.get(Icons.调查), "问号",
											"将原版改成此"));

		changes.addButton( new ChangeButton(Icons.get(Icons.无语), "无语",
											"2次未命中"));
		changes.addButton( new ChangeButton(Icons.get(Icons.扣6), "扣6",
											"受到大于1/2生命的伤害"));
		changes.addButton( new ChangeButton(Icons.get(Icons.愤怒), "愤怒",
											"逃跑失败、被伏击、50%视野外受伤"));
		changes.addButton( new ChangeButton(Icons.get(Icons.爱心), "爱心",
											"魅惑"));
		changes.addButton( new ChangeButton(Icons.get(Icons.礼物), "礼物",
											"嬗变物品、拾取可升级物品"));
		changes.addButton( new ChangeButton(Icons.get(Icons.微笑), "微笑",
											"鉴定没诅咒的物品，且带等级"));
		changes.addButton( new ChangeButton(Icons.get(Icons.滑稽), "滑稽",
											"施法"));
		changes.addButton( new ChangeButton(Icons.get(Icons.歪嘴), "歪嘴",
											"暴击、蛇皮走位"));
		changes.addButton( new ChangeButton(Icons.get(Icons.吃瓜), "吃瓜",
											"找敌人过不去"));
		changes.addButton( new ChangeButton(Icons.get(Icons.哭泣), "哭泣",
											"受到大于1/3生命的伤害、诅咒物品、被偷东西、打不死蟑螂、被监狱守卫拉"));

	}
	public static void 新杂项(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("新杂项", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER,new ItemSprite.Glowing( 0xFFFFFF )), "附魔",
		"罕见传说\n" +
		"获得30%强化等级。"));
//		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER,new ItemSprite.Glowing( 0x666666 )), "诅咒附魔",
//		"血祭\n" +
//		"攻击损失2%生命，并根据已损失生命造成额外伤害。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER,new ItemSprite.Glowing( 0x666666 )), "刻印",
		"诅咒虐待\n" +
		"受到的物理伤害x4.5，如果没有死亡，则恢复受到的伤害。\n\n"+
		"诅咒焦灼\n" +
		"防御过程1/8的概率产生火团。"));

		changes.addButton( new ChangeButton(Icons.ALCHEMY.get(),"炼金",
											"配方\n" +
											"口粮+护甲修理工具包消耗2能量合成布甲。\n" +
											"布甲+护甲修理工具包消耗4能量合成皮甲。\n" +
											"皮甲+护甲修理工具包消耗6能量合成链甲。\n" +
											"链甲+护甲修理工具包消耗8能量合成鳞甲。\n" +
											"鳞甲+护甲修理工具包消耗10能量合成板甲。" ,

											"双刃+金液消耗9能量合成双剑。\n" +
											"匕首+金液消耗6能量合成长匕首。\n" +
											"短剑+金液消耗6能量合成单手剑。\n" +
											"单手+金液剑消耗9能量合成长剑。\n" +
											"短柄镰+金液消耗12能量合成战镰。\n" +
											"长剑+金液消耗12能量合成巨剑。",
											"镶钉手套+金液消耗12能量魔岩拳。",

											"长匕首+隐形药剂+器灵消耗15能量合成暗杀之刃。\n" +
											"巨剑+治疗药剂+器灵消耗15能量合成饮血之剑。\n" +
											"战镰+复仇卷轴+器灵消耗15能量合成死神镰刀。\n" +
											"弯刀+液火药剂+器灵消耗15能量合成火焰剑。\n" +
											"手里剑+冰霜药剂+器灵消耗15能量合成寒冰镖。\n" +
											"战镰+大地护甲合剂+器灵消耗15能量合成地裂镰。\n" +
											"长匕首+冰霜药剂+器灵消耗15能量合成臻冰刃。",

											"治疗药剂消耗5能量合成血药x5。\n" +
											"治疗药剂+血药消耗10能量合成永生秘药。\n" +
											"紊乱魔药消耗5能量合成净化药剂，无序结晶消耗5能量合成祛邪卷轴。\n" +
											"生肉+隐形药剂消耗5能量合成幻影鱼肉。"
		));
		changes.addButton( new ChangeButton(Icons.CALENDAR.get(), "日历",
				"地牢探索指南新增日历节日。\n" +
				"新增妇女节、植树节、清明节、游戏周年、520、端午节618、七夕(奇袭)节、中元节、中秋节、国庆节、程序员(1024)节，双十一。"));

	}
}
