

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM0Sprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SheepSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.sprites.骷髅战士动画;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class 重制 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v1_0_0_Changes(changeInfos);
	}

	
	static Image bug= new Image(Assets.Sprites.修复, 144, 0, 16, 16);
	public static void add_v1_0_0_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.0.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo("新增", false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton( new ChangeButton(new 骷髅战士动画(),"骷髅战士",
											"_-_ 新增骷髅的变异体。\n" +
											"_-_ 死亡掉落单手剑、长剑、巨剑任意一个。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.护甲修理工具包), "护甲修理工具包",
										   "_-_ 护甲机制，和破碎护盾差不多，但是不会随时间流逝，也只能防御物理攻击伤害，包含骷髅死亡爆炸和跳楼受伤(不含流血)。\n" +
										   "_-_ 护盾机制，改成免疫一次伤害，并且可以叠加，也不会随时间流逝。\n\n" +
										   "_-_ 最大护甲为25%最大生命。\n" +
										   "_-_ 每30回合修理1护甲。\n" +
										   "_-_ 巨魔铁匠可以花费人情修复你的护甲。\n" +
										   "_-_ 商店会出售此物。\n" +
										   "_-_ 护甲改成防具，防具可以放入炼金锅转换护甲修理工具包，同奥术树脂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MASTERY), "进阶宝典",
										   "_-_ 5区域第一层必定生成一个。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.角斗链枷), "角斗链枷",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.磨刀石), "磨刀石",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.幸运硬币), "幸运硬币",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.虚无透纱), "虚无透纱",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.精神支柱), "精神支柱",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.圣金之沙), "圣金之沙",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.优惠卡), "优惠卡",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.传奇肛塞), "传奇肛塞",
										   "新增饰品。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.斩马刀), "斩马刀",
										   "新增3阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.流火), "流火",
										   "新增星辰像素地牢中联动武器为5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.蜜剑), "蜜剑",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.日炎链刃), "日炎链刃",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.神农锄), "神农锄",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.死神镰刀), "死神镰刀",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.饮血之剑), "饮血之剑",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.无尽之刃), "无尽之刃",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.锈右斧), "锈右斧",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.臻冰刃), "臻冰刃",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.碎缘剑), "碎缘剑",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.草剃), "草剃",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.金纹拐), "金纹拐",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.英雄断剑), "英雄断剑",
										   "新增5阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
										   "装甲之戒\n" +
										   "_-_ 裸衣+0~2x(1+等级)的防御，防具+0~根据力量自适应x(1+等级)的防御\n\n"+
										   
										   "恢复之戒\n" +
										   "_-_ 恢复速度+1.2x等级的次方\n\n"+
										   
										   "魔攻之戒\n" +
										   "_-_ 施法对目标造成5+等级~9+6x等级的伤害。"
										   ));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER,new ItemSprite.Glowing( 0x111111 )), "诅咒刻印",
		"虐待\n" +
		"_-_ 受到的物理伤害x3.5，如果没有死亡，则恢复受到的伤害。\n\n"+
		"焦灼\n" +
		"_-_ 防御过程1/8的概率产生火团。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.蜂蜜), "蜂蜜",
		"_-_ 金色蜜蜂死亡必定掉落一个蜂蜜。\n" +
		"_-_ 蜂蜜罐击碎必定掉落一个蜂蜜"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.生命水晶), "生命水晶",
		"_-_ +20最大生命。\n" +
		"_-_ 洞穴层第三层生成一个。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.生命果), "生命果",
		"_-_ +5最大生命。\n" +
		"_-_ 踩踏草丛1/500概率获得，上限1个。"));
		//region 新增游戏选项
		changes.addButton( new ChangeButton(Icons.玩法开.get(), "玩法",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.系统开.get(), "系统",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.解压开.get(), "解压",
				"新增游戏选项。"));
		changes.addButton( new ChangeButton(Icons.炼狱开.get(), "炼狱",
				"新增游戏选项。"));
		//region 武器
		changes.addButton(new ChangeButton(new ItemSprite(物品表.爪), "爪",
				"新增2阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.破甲锥), "破甲锥",
				"新增3阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.半月刃), "半月刃",
				"新增3阶武器。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.寒冰鱼剑), "寒冰鱼剑",
				"新增4阶武器。"));
		//endregion
		//endregion
		
		//region 新增英雄
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.来世,tier(HeroClass.来世))),"来世",
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
		//endregion
		
		//region 新增房间
		changes.addButton( new ChangeButton(new DM0Sprite(),"DM0房",
				"_-_ 必定生成麻痹药剂。\n" +
				"_-_ DM0遇到麻痹会移速为1，并死亡随机掉率卷轴药剂。"));
		changes.addButton( new ChangeButton(new SheepSprite(),"超级魔法绵羊房",
				"_-_ 必定生成灵视药剂。\n" +
				"_-_ 超级魔法绵羊是隐形的，只能被灵视药剂发现，可以被击杀，但是每次受伤会随机传送。死亡随机掉率神器、法杖、戒指"));
		changes.addButton( new ChangeButton(new MimicSprite.毒气(), "毒气宝箱怪房",
				"_-_ 必定生成毒气药剂。\n" +
				"_-_ 宝箱毒气怪遇到毒气会死亡，死亡随机掉率武器护甲。"));
		changes.addButton( new ChangeButton(Icons.神力之泉.get(), "神力之泉",
				"_-_ 英雄站上面永久+1力量，武器或护甲在上面永久减少3力量需求。"));
		changes.addButton( new ChangeButton(Icons.天赋之泉.get(), "天赋之泉",
				"_-_ 英雄站上面永久+1天赋点，物品在上面升级一次。"));
		//endregion
		
		changes.addButton( new ChangeButton(Icons.CALENDAR.get(), "日历",
				"_-_ 地牢探索指南新增日历节日。\n" +
				"_-_ 新增妇女节、植树节、清明节、游戏周年、520、端午节618、七夕(奇袭)节、中元节、中秋节、国庆节、程序员(1024)节，双十一。"));
		
		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), "杂项",
											"_-_ 新增各种情况的新表情"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.杂物袋), "杂物袋",
				"_-_商店出售杂物袋，必定优先卖在第一区域。"));
		changes = new ChangeInfo("改动", false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MISSILE_HOLDER), "投掷武器",
										   "_-_ 移除投掷武器，投掷武器和近战武器归类为武器，都可以进行装备、投掷，但不可堆叠。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.轮刃), "轮刃",
										   "_-_ 十字弩改成了轮刃。"));
		changes.addButton( new ChangeButton(Icons.ALCHEMY.get(), "炼金",
				
				"界面\n\n"+
						"_-_ 最大配方3=>6。\n" +
						"_-_ 现在正装备的装备以及背包不能放入炼金和提炼。",
						
						"配方\n\n" +
						"_-_ 合成炖肉所需能量为固定数量。\n" +
						"_-_ 生肉+隐形药剂消耗5能量合成幻影鱼肉。"
		));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_CAPE), "荆棘斗篷",
				"回归。\n\n" +
				"_-_ 所有受伤时判定=>物理防御时判定。\n" +
				"_-_ 吸收伤害充能倍率50%+5%x神器等级=>500%+25%x神器等级。\n" +
				"_-_ 减伤和反伤为0~受伤=>减伤为0，并且反伤为(90%+40%x神器等级)。\n" +
				"_-_ 移除反伤距离限制和冷却机制。"));
		
		
		//region 地牢神器戒指法杖种子药剂卷轴
		
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢",
										   "_-_ 火房必定生成额外冰冠花物品\n"+
										   "_-_ 木房必定生成额外烈焰花物品\n"+
										   
										   "献祭房\n\n" +
										   "_-_ 所需经验减少90%，并且可以站在祭坛上扔治疗药剂直接献祭完成。\n"+
										   
										   "商人房\n\n" +
										   "_-_ 物价只计算地牢1层。\n" +
										   "_-_ 神器法杖戒指中随机卖一样，如果斗不卖默认奥术刻印笔=>神器法杖戒指中随机卖一样，并且必卖奥术刻印笔。\n" +
										   "_-_ 炼金菱晶商店不卖。\n" +
										   "_-_ 1区域1层也有商店，卖1阶武器和防具。\n" +
										   "_-_ 卖给商人20件物品以上会免费赠送一个商人信标。\n" +
										   "_-_ 不再随机药剂和卷轴必卖净化药剂、极速药剂、隐形药剂和嬗变卷轴，两张鉴定卷轴和治疗药剂。\n" +
										   "_-_ 非战士在第一个商店会卖水袋。\n" +
										   "_-_ 出售的戒指、法杖和神器现在是鉴定的。\n" +
										   "_-_ 只卖一个武器。\n" +
										   "_-_ 商店的武器和防具，戒指和法杖有概率拥有等级。\n" +
										   "_-_ 商店的小包口粮x2=>口粮+小包口粮。\n"+
										   
										   "金币宝箱房\n\n" +
										   "_-_ 金币数量5~15=>30+地牢层数x10, 60+地牢层数x20。\n" ,
										   
										   "尸尘房\n\n" +
										   "_-_ 金币数量1=>自然生成金币。\n" ,
										   
										   "毒气房和鼠王房\n\n" +
										   "_-_ 金币数量10~15=>30+地牢层数x10, 60+地牢层数x20。\n" ,
										   
										   "地牢生成。\n\n" +
										   "_-_ 生成金币只计算1层。\n" +
										   "_-_ 每区域偶数层必定生成一个小包口粮，且金币价值减半。\n" +
//										   "_-_ 每区域的升级卷轴数量-1。\n" +
										   "_-_ 每区域第3层必定生成治疗药剂。\n" +
										   "_-_ 第一层怪物数量+1。\n" +
										   "_-_ 第四层必定生成一个烈焰花种子和液火药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_HOLDER), "神器",
										   "_-_ 装备和卸下花费1=>攻速x2。\n\n"+
										   
										   "虚空锁链\n\n" +
										   "_-_ 拉扯敌人会残废4回合。\n"+
										   
										   "自然之履\n\n" +
										   "_-_ 充能速度翻倍。\n"+
										   
										   "蓄血圣杯\n\n" +
										   "_-_ 血祭消耗生命-5。\n"+
										   
										   "神偷袖章\n\n" +
										   "_-_ 偷取1格内的敌人物品=>攻击范围内。\n" +
										   "_-_ 可以窃取商人的商人信标。\n" +
										   "_-_ 可以窃取魔法绵羊的随机种子。\n" +
										   "_-_ 移除等级大于目标时不能窃取。\n" +
										   "_-_ 不是敌人也能偷取。\n"+
										   
										   "时光沙漏\n\n" +
										   "_-_ 攻击不会打断时间冻结。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
										   "_-_ 装备和卸下花费时间1=>攻速\n" +
										   "_-_ 取消物品还需要经验的鉴定，戒指是每装备1回合鉴定1/30次。\n\n"+
										   
										   "根骨之戒\n\n" +
										   "_-_ 1力量=>2力量。\n"+
										   "_-_ +生命系数1.035=>+(等级+1)x25生命。\n"+
										   
										   "狂怒之戒\n\n" +
										   "_-_ 1.09051=>1.15。且所有情况都能使用，还能在固定攻速时也能进行攻击提升。\n"+
										   
										   "疾速之戒=>时间之戒\n\n" +
										   "_-_ 1.175=>0.85不仅仅加速移动速度，而是所有时间花费。\n"+
										   
										   "财富之戒\n\n" +
										   "_-_ 英雄自带1级。\n"+
										   "_-_ 增加暴击率6%+6%x等级。\n"+
										   
										   "能量之戒\n\n" +
										   "_-_ 新增对武器的充能不过职业70%效果，神器充能也削弱至85%。\n"+
										   
										   "精准之戒=>命中之戒\n\n" +
										   "_-_ +等级x攻击范围。并且最大命中提升1.3系数=>等级x2最小命中。\n"+
										   
										   "闪避之戒\n\n" +
										   "_-_ 最大闪避提升1.125系数=>等级x2最小和最大闪避。\n"+
										   
										   "韧性之戒\n\n" +
										   "_-_ 减少所有受到伤害，上取整=>减少所受物理攻击伤害，四舍五入。\n"+
										   
										   "武力之戒\n\n" +
										   "_-_ 能和空手拳套叠加。\n" +
										   "_-_ 最小和最大攻击/2。\n" +
										   "_-_ 空手攻击1~力量-8=>10%力量~力量-8。\n" +
										   "_-_ 最小和最大伤害不会因为等级为0不取最大阶伤。\n" +
										   "_-_ 武器+等级增伤害=>武器获得1~1+等级x2攻击"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_HOLDER), "法杖",
										   "_-_ 法杖升级不再恢复1充能\n\n"+
										   
										   "灵壤法杖\n\n" +
										   "_-_ 对深渊目标地施法如果是深渊那么就接壤。\n"+
										   
										   "酸蚀法杖\n\n" +
										   "_-_ 气体量50+10x级=>30+15x级。\n"+
										   
										   "魔弹法杖\n\n" +
										   "_-_ 升级则会暂时强化其他法杖=>从升级获得额外一次等级。\n"+
										   
										   "冰霜法杖\n\n"+
										   "_-_ 对冻伤和冻结的敌人建设伤害=>冻伤增加伤害且冻结敌人，如果冻结就减少伤害。\n" +
										   "_-_ 在水中额外2回合冻伤=>直接冻结。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_HOLDER), "种子",
										   "速行蓟\n" +
										   "_-_ 时间气泡攻击不会打断。\n\n" +
										   
										   "烈焰花\n" +
										   "_-_ 不会被烧毁。\n\n" +
										   
										   "冰冠花\n" +
										   "_-_ 不会被烧毁。\n" +
										   "_-_ 如果有火，则扔出就能直接触发。"
		));
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.POTION_HOLDER), "药剂",
										   "治疗药剂\n\n" +
										   "_-_ 最大生命80%+14=>90%。\n" +
										   "_-_ 恢复速度25%=>45%。\n"+
										   
										   "奥术护盾合剂\n\n"+
										   "_-_ 最大生命护盾60%+10=>最大生命5%的护盾层数。\n"+
										   
										   "根骨秘药\n\n"+
										   "_-_ +暂时最大生命=>25永久最大生命。\n"+
										   
										   "麻痹药剂\n\n"+
										   "_-_ 范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。\n"+
										   
										   "毒气药剂\n\n"+
										   "_-_ 范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。\n"+
										   
										   "肌肉记忆合剂\n\n"+
										   "_-_ 可以叠加使用，并且不再单单减少一个物品，是所有武器或所有护甲，供玩家自己选择。"+
										   
										   "神意启发合剂\n\n"+
										   "_-_ 天赋点+2=>1。"+
										   
										   "羽落秘药\n\n"+
										   "_-_ 持续时间50=>400。跳楼减少10回合=>减少20回合。并且还能自动识别环境获得5回合浮空。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SCROLL_HOLDER), "卷轴",
										   "蜕变秘卷\n" +
										   "_-_ 移除蜕变天赋，而是改变英雄的特性。\n\n" +
										   
										   "嬗变卷轴\n" +
										   "_-_ 护甲也能嬗变其他阶级护甲。\n\n" +
										   
										   "注魔秘卷\n" +
										   "_-_ 还附带一次升级。"
		));
		//endregion
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "武器",
										   "_-_ 装备和卸下时间1=>攻速\n" +
										   "_-_ 扔出装备的武器时，只花费扔出的时间，不花费卸下的时间。\n"+
										   "_-_ 所有武器伤害倍率大于和小于1的最小和最大伤害，能从升级正确分配伤害。\n"+
										   "_-_ 近战武器最小伤害=阶+级、最大伤害=5x(阶+1)+级x(阶+1)不同武器调整5的值=>不同武器x伤害倍率来改动。\n" +
										   "_-_ 投掷武器最小伤害=2*阶+级、最大伤害=5x阶+级x阶不同武器调整5的值=>不同武器x伤害倍率来改动，最小伤害倍率0.5，最大伤害倍率1.2。\n" +
										   "_-_ 武器投掷最小攻击2x阶+等级=>(阶+等级)x0.85。\n" +
										   "_-_ 武器完全适配增加命中，转换效率为根号(力量-武器力量)的1%加成。\n" +
										   "_-_ 武器完全适配增加攻速，转换效率为根号(力量-武器力量)的2%加成。\n" +
										   "_-_ 大部分武器属性所有调整，阶级有所下降，一部分重叠类型武器删除。\n" +
										   "_-_ 武器部分格挡量能根据等级提升。\n" +
										   "_-_ 空手攻速1=>2。\n" +
										   "_-_ 根据力量增加命中，转换效率为根号(力量-10)x2%。\n" +
										   "_-_ 根据力量增加攻速，转换效率为根号(力量-10)x1%。"
										   ));
		
		
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER), "防具",
										   "_-_ 防具装备和卸下时间1=>攻速x移速x6。\n"+
										   "_-_ 最小防御等级=>阶级。\n"+
										   "_-_ 最大防御阶级x(2+等级)=>2x阶级x(1+等级/1.5)。\n"+
										   "_-_ 防具完全适配增加闪避，转换效率为根号(力量-防具力量)的1%加成。\n"+
										   "_-_ 防具完全适配增加移速，转换效率为根号(力量-防具力量)的2%加成。\n"+
										   "_-_ 裸衣增加闪避，转换效率为根号(力量-10)的2%加成。\n"+
										   "_-_ 裸衣增加移速，转换效率为根号(力量-10)的1%加成。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
										   "强化武器\n" +
										   "_-_ 新增+45%最大命中的选项。\n" +
										   "_-_ +伤害50%-67%攻速x(等级+2)=>+15%伤害。\n" +
										   "_-_ +33%攻速-30%伤害x(等级+2)=>+30%攻速。\n\n"+
										   
										   "强化防具\n" +
										   "_-_ 新增+30%移速的选项。\n" +
										   "_-_ +100%防御-200%闪避x(等级+2)=>+15%防御。\n" +
										   "_-_ +200%闪避-100%防御x(等级+2)=>+45%闪避。"));
		
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.WARRIOR,tier(HeroClass.WARRIOR))),"英雄",
											"英雄初始\n\n" +
											"_-_ 英雄初始不携带口粮。\n"+
											"_-_ 各英雄都拥有特定的效果，武器装备更符合设定。\n"+
											"_-_ 除了战士都移除水袋，防具都变成专属防具。\n",
											
											"英雄职业\n\n" +
											"_-_ 几乎所有职业都重做了。\n",
											
											"英雄天赋\n\n" +
											"_-_ 移除一层和二层的一个天赋，几乎所有天赋都重做了。\n" +
											"_-_ 两点天赋的效果点满不再是1.5倍，是2倍。\n" +
											"_-_ 天赋解锁从2/7/13/21/30=>2/6/11/21/25\n" +
											"_-_ 天赋点数5/6/8=>3/6/9\n" +
											"_-_ 二层和三层最大天赋点+1",
											
											"英雄机制\n\n" +
											"_-_ 新增吸血作用于物理攻击的伤害百分比恢复生命值，全能吸血作用于大部分的伤害百分比恢复生命值。\n"+
											"_-_ 不进行攻击的回合，可叠加隐藏蛇皮走位效果，超过1层后+1最小命中和闪避，物理攻击后重置为0。\n"+
											"_-_ 任何饱腹填充都能触发吃饭天赋，不过效果倍率是食物填充值/150。\n"+
											"_-_ 移除搜索、开锁等动作扣除饥饿值，同时搜索花费时间2=>搜索范围x2。\n"+
											"_-_ 搜索会在搜索范围内拾取非陷阱上的物品和打开箱子，以及踩踏草。\n"+
											"_-_ 交互距离相邻=>攻击范围内。\n"+
											"_-_ 视野算法优化。\n"+
											"_-_ 防具技能移除，矮人国王的皇冠使用为解锁4层天赋6个天赋。\n"+
											"_-_ 状态栏添加食物条。并且不显示Buff图标。\n"+
											"_-_ 状态栏添加护盾条。\n"+
											"_-_ 先处理物理防御时然后处理物理攻击时=>先处理物理攻击时然后处理物理防御时。\n"+
											"_-_ 吃饭回血食物填充/150的生命。\n"+
											"_-_ 敌人第一次被攻击+1伤害，伏击+1，隐形伏击再+1，敌人第一次被伏击再+1。\n"+
											"_-_ 投掷和弓在近距离最大命中不变，非近距离-25%最大命中。\n"+
											"_-_ 燃烧获得火把效果。\n"+
											"_-_ 升级不再会同时恢复提升的生命。\n"+
											"_-_ 神器和戒指栏位=>两个杂项栏位。\n"+
											"_-_ 再生恢复的生命值为延迟值=>1。\n"+
											"_-_ 饥饿首次受伤450*最大生命/1000，后续受伤最大生命/1000=>首次和后续伤害皆为1值，同时优化饥饿算法。\n"+
											"_-_ 浮空状态在深渊点击自身可以无伤掉下去。\n",
											
											"英雄属性\n\n" +
											"_-_ 最大生命值20+5x等级=>20+5x等级。\n"+
											"_-_ 升级不在恢复升级所提升的生命。\n"+
											"_-_ +0.2命中和闪避。最大等级-5。\n"+
											"_-_ 升级所需经验初始10+5/级=>10+6/级。"));
		
		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), "杂项",
				"物品\n\n" +
				"_-_ 现在首次拾取和装备、使用进行一次鉴定效果。\n" +
				"_-_ 武器战技重做。\n" +
				"_-_ 震爆符石不会对自己也造成伤害。\n" +
				"_-_ 部分物品有音效。\n" +
				"_-_ 冰冠花、冰霜药剂和冰暴魔药不会被燃烧。\n" +
				"_-_ 炸弹伤害翻倍，且无视敌人防御。\n" +
				"_-_ 炸弹不可摧毁可升级的物品，装备不可摧毁=>炸弹不摧毁带等级的物品，神器不可摧毁。\n" +
				"_-_ 暗金矿可以提炼获得25炼金能量。\n" +
				"_-_ 武器和防具、神器、法杖和戒指可以提炼，提炼量是金币的2.5%+1，投掷武器是金币的(2.5%/数量+1)x数量。\n" +
				"_-_ 物品可以重命名。\n" +
				"_-_ 20/20水袋可以合成一瓶永生秘药。\n" +
				"_-_ 扔出、拾取、丢下花费时间1=>攻速。\n" +
				"_-_ 移除大部分没用描述。\n" +
				"_-_ 一些物品图片更好。\n" +
				"_-_ 饰品提炼能量5=>4+等级x3。"+
				"_-_ 灵能短弓箭矢能触发部分东西。"+
				"_-_ 物品掉落位置更好。\n" ,
				
				"生物\n\n" +
				"_-_ 食人鱼现在会优先去吃生肉，并产生一个新的食人鱼，不过每个食人鱼只能产生一次。\n"+
				"_-_ 新增植物、酸性和寒冰属性的生物受到火焰伤害x2。\n"+
				"_-_ 新增火焰属性的生物受到寒冰伤害x2。\n"+
				"_-_ 新增暴击率和暴击伤害，默认6%暴击率，和145%暴击伤害。如果不是百分比暴击，首次攻击必定不暴击，且400/暴击率次物理攻击未暴击则下次必定暴击，暴击后重置。\n"+
				"_-_ 新增机械属性的生物受到电伤害x2。\n"+
				"_-_ 新增动物属性的生物受到流血、中毒、毒气、酸性、火焰和寒冰伤害x2。\n"+
				"_-_ 另一种地牢情况是蝙蝠在1区，老鼠在3区，强度变化。\n"+
				"_-_ 怨灵最大生命1=>地牢层数x2。\n"+
				"_-_ 部分生物有音效。\n"+
				"_-_ 矮人国王在召唤2个矮人尸群时，有一个将是骷髅。\n"+
				"_-_ 吸血蝙蝠物理攻击恢复血量1~14(攻击力-4)=>3~9(吸血50%)。\n"+
				"_-_ 宝箱怪动画优化。\n"+
				"_-_ 给鼠王矮人国王的皇冠获得大量物品和五千金币，普通老鼠变成中立。\n"+
				"_-_ 老杖匠的奖励额外一个选择，获得3个奥术树脂。\n"+
				"_-_ 巨魔铁匠任务要求数量40=>20，携带的数量越多，增加的好感度越多。新增额外选项，不需要人情，如果还有2500以上金币和25能量会赠送锻造锤。\n"+
				"_-_ 天狗陷阱多显示1.5秒。\n"+
				"_-_ 矮人国王延缓伤害改成和史莱姆一样的固定衰减。\n"+
				"_-_ 骷髅死亡爆炸对于对于目标的伤害会经过防御时，并且更好的处理。\n"+
				"_-_ 大部分怪物正确获得属性。\n"+
				"_-_ 下水道巨蛇最小和最大攻击+1。\n"+
				"_-_ 疯狂小偷和疯狂强盗，没偷东西移速7/6，防御时掉落1金币=>30+地牢层数x10, 60+地牢层数x20。\n"+
				"_-_ 野心勃勃的小恶魔不再半透明。\n"+
				"_-_ 豺狼诡术师、酸液巨蝎和巨蝎接近它后，移速会只有5/6。其他情况的移速100%=>7/6。。\n"+
				"_-_ 魔法绵羊交互有彩蛋，还能获得经验。",
				
				"UI\n\n" +
				"_-_ 设置界面和英雄属性界面更好。\n" +
				"_-_ 更好的物品图标和底色，数值显示。\n" +
				"_-_ 优化显示面板以及显示更多内容。\n" +
				"_-_ 设置UI更新，且添加和移除部分按钮。\n" +
				"_-_ Yendor护符结算时的英雄贴图采用另一种。\n" +
				"_-_ 属性条动画更好。" ,
				
				"杂项\n\n"+
				"_-_ 弥补各种情况的新表情。\n" +
				"_-_ 火焰和冰霜相关的物品不会被烧毁。\n" +
				"_-_ 毒气陷阱范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。\n" +
				"_-_ 优化武器的快捷默认使用，如果有战技会弹出使用按钮。\n" +
				"_-_ 一些勋章降低获取难度。\n" +
				"_-_ 护盾特效偏粉=>偏蓝。\n" +
				"_-_ 种子存档也能解锁勋章，不需要通关也能使用一些开关。\n" +
				"_-_ 身上带有护符能使用传送。\n" +
				"_-_ 大部分代码先+后x计算，除了特殊机制。\n" +
				"_-_ 主题曲我的三体《夜航星》。\n" +
				"_-_ 现在种子输入代码名可以直接生成鉴定的物品在背包，并且不会固定种子，但其他种子依旧会固定。\n" +
				"_-_ 移除语言设置。\n" +
				"_-_ 设置可调整自动拾取、游戏提示。\n"+
				"_-_ 设置可调整固定攻速和移速，例如在1.05移速时固定1。攻速固定1时，会根据攻速调整攻击力，不影响总体DPS。\n"+
				"_-_ 设置可调整休息时间倍率。\n"+
				"_-_ 设置可调整画面同步按钮，关了才不会锁帧。\n"+
				"_-_ 设置可调整帧率和字体大小。\n"+
				"_-_ 设置可开启透明界面。\n"+
				"_-_ 设置可调整动画加快按钮。"
		));
		changes.addButton(new ChangeButton(bug, "修复",
				"_-_ 修复酸蚀法杖高等级的施法会导致低等级的施法伤害没有正确分配级。\n" +
				"_-_ 修复魅魔站立动画异常有攻击的一帧。\n" +
				"_-_ 修复楼层入口和楼层出口文本反了。\n" +
				"_-_ 修复虹光幻像不在英雄视野还在攻击。\n" +
				"_-_ 修复0受伤时还显示受伤图标。\n" +
				"_-_ 修复背包格子不是正方形。\n" +
				"_-_ 修复防具使用强化符石最小防御和最大防御没有正确分配。\n" +
				"_-_ 修复神偷袖章偷取没有掉落物的敌人崩溃。\n" +
				"_-_ 修复恶魔撕裂者有概率自己跳入深渊。"));

		changes = new ChangeInfo("增强", false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);
		
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ANKH), "十字架",
										   "未祝福效果\n" +
										   "_-_ 满血复活并丢弃背包=>残血复活。\n\n" +
										   
										   "祝福效果\n" +
										   "_-_ 残血净化无敌复活=>满血净化无敌复活。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
				"_-_ 自带法杖回收，且能转移灌注法杖等级。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.荣誉纹章),"荣誉纹章",
				"_-_ 破损纹章=>荣誉纹章。\n" +
				"_-_ 移除受伤获得爆发屏障。\n" +
				"_-_ 提升防具的等级≡>防具获得强化等级。。\n" +
				"_-_ 可额外携带1级。。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARCANE_RESIN), "奥术树脂",
				"_-_ 法杖转换数量2*(级-树脂级)=>级-树脂级+1，并且级-树脂级+1需要大于0才能转换。\n" +
				"_-_ 取消等级升级上限。"));
		
		changes = new ChangeInfo("削弱", false, null);
		changes.hardlight(CharSprite.削弱);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
										   "_-_ 升级所带来的伤害提升减半，包括诅咒升级。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.AMULET, true), "护符诡咒",
										   "移除后续对英雄的减速和伤害。"));

	}
}
