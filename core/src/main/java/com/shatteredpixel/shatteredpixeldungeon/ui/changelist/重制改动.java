

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Crab;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollTrickster;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Guard;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Skeleton;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Slime;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Wraith;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.NecromancerSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ShopkeeperSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ThiefSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class 重制改动{

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		重要(changeInfos);
		怪物(changeInfos);
		物品(changeInfos);
		杂项(changeInfos);
	}


	public static void 怪物(ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("怪物改动", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS),"怪物",
											"大部分怪物正确获得属性。\n"+
											"1楼刷怪不会刷怪=>25x25回合后才会刷怪(幸福结局回来除外)。\n"+
											"1楼刷怪数量8=>10。\n"+
											"刷怪速度最短1回合，最长450回合。\n"+
											"大部分单位的最小防御+1。\n"+

											"怪物生成不会在出口上。\n"+
											"怪物生成可以在物品上。\n"+

											"新增树妖、海妖、昆虫、酸性和冰霜属性的生物受到火焰伤害x2倍。\n"+
											"宝箱怪动画优化。",

											"新增怪物都拥有一套新的判定，害怕，受到害怕元素的伤害x2倍。\n"+
											"新增火焰属性的生物受到寒冰伤害x2倍。\n"+
											"新增动物属性的生物受到流血、中毒、毒气、酸性、火焰和寒冰伤害x2倍。\n"+
											"新增7%暴击率和145%暴击伤害，超过的暴击率33%转暴击伤害。如果不是百分比暴击，首次攻击必定不暴击，且600/暴击率次攻击未暴击则下次必定暴击，暴击后重置。\n"+

											"魔法绵羊交互有彩蛋，还能获得经验。"));

		changes.addButton( new ChangeButton(new GooSprite(),"老鬼",
											"小老鬼\n"+
											"巨钳螃蟹击杀掉落生肉x2=>巨大蟹钳。\n"+
											"腐臭老鼠击杀掉落巨鼠头骨。\n"+
											"豺狼诡术师(击杀掉落随机武器=>火毒箭矢)接近它后，移速会只有5/6。其他情况的移速100%=>9/6。",

											"老鬼\n"+
											"古神会播放古神低语。\n"+
											"粘咕获得史莱姆物理减伤，但是回血只有1/10。\n"+
											"天狗陷阱多显示2秒。\n"+
											"DM300最大攻击+5。\n"+
											"矮人国王死亡掉落矮人国王的皇冠=>狂妄皇冠，且金币价值160。\n"+
											"矮人国王延缓伤害改成和史莱姆一样的固定衰减。\n"+
											"矮人国王在召唤2个矮人尸群时，有一个将是骷髅。\n"+
											"矮人国王最小和最大攻击+10。"));

		changes.addButton( new ChangeButton(new Slime(),"所有史莱姆","史莱姆受到伤害减伤=>物理减伤。"));
		changes.addButton( new ChangeButton(new Crab(),"所有螃蟹","下水道螃蟹移速初始1.5倍，且所有螃蟹敌人水平移动速度x2.5倍。"));
		changes.addButton( new ChangeButton(new ThiefSprite(),"所有小偷","没偷东西移速9/6。"));
		changes.addButton( new ChangeButton(new NecromancerSprite(),"所有法师","最大闪避-4。 "));


		changes.addButton( new ChangeButton(new Wraith(),"最大生命1=>地牢层数/2。 "));
		changes.addButton( new ChangeButton(new Bat(),"攻击恢复血量1~14(攻击-4)=>2~8(吸血40%)。\n"+
													  "最大生命+5，最大攻击+3，最大命中+2，最大闪避+1，移速2=>1.5，最大经验和最大等级+1。"));
		changes.addButton( new ChangeButton(new Guard(),"最大闪避+4，最大命中+2。"));
		changes.addButton( new ChangeButton(new Piranha(),"现在会优先去吃生肉，并产生一个新的巨型食人鱼，不过每个巨型食人鱼只能产生一次。"));
		changes.addButton( new ChangeButton(new Snake(),"最小和最大攻击+2，最大生命+6，最大防御+1，最大闪避25=>5，最大等级7=>8，并且替换二层豺狼巡查。"));
		changes.addButton( new ChangeButton(new GnollTrickster(),"(击杀掉落随机武器=>火毒箭矢)、酸液巨蝎和巨蝎接近它后，移速会只有5/6。其他情况的移速100%=>9/6。"));
		changes.addButton( new ChangeButton(new Skeleton(),"死亡爆炸对于对于目标的伤害会经过防御，并且更好的处理。"));

	}

	public static void 物品(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("物品改动", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(Icons.get(Icons.PREFS), "物品",
										   "大部分物品的描述更具现。\n" +
										   "1阶的武器和防具不会遗产。\n" +
										   "以及很多杂物品的遗产和是否可嬗变优化。\n" +
										   "种子也可以遗产。" ,

										   "特别的物品可以出售。\n" +
										   "神秘的肉=>生肉。\n" +
										   "现在首次拾取和装备、使用进行一次鉴定效果。\n" +
										   "震爆符石不会对自己也造成伤害。\n" +
										   "冰冠花、冰霜药剂和冰暴魔药不会被燃烧。\n" +
										   "炸弹伤害翻倍，经过防御的代码，且减少最大防御的伤害=>最大防御x2。" ,
										   "炸弹不可摧毁可升级的物品，装备不可摧毁=>炸弹不摧毁带等级的物品，神器不可摧毁。\n" +
										   "物品可以重命名。\n" +
										   "扔出、拾取、丢下花费时间1=>攻速。\n" +
										   "移除大部分没用描述。\n" +
										   "一些物品图片更好。\n" +
										   "物品掉落位置更好。" ));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MASK), "职业",
										   "几乎所有职业都重做了，转职不会立刻判定刺客的潜伏。\n" +
										   "战斗法师职业给到巫女。\n" +
										   "疾行者职业给到行僧。\n" +
										   "术士职业给到道士。\n" +
										   "勇士=>武器大师(所有人拥有副武器功能，但仅45%攻击、命中、攻速效果)。\n" +
										   "术士=>死灵术士。"
		));changes.addButton(new ChangeButton(new ItemSprite(物品表.GOLD), "金币",
											  "商店物价只计算1区且/1.5，地牢生成金币不计算层且/5。\n" +
											  "所有物品金币价值/5，并且只显示大于1的金币获取。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ENERGY), "能量",
										   "生成的能量1=>1~2。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TORCH), "火把",
										   "提供的光照回合250=>225。"));

		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.AMULET,true),"护符诡咒",
										   "移除后续对英雄的减速和伤害。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
										   "诅咒升级提升1+英雄等级/30~2+英雄等级/15=>标准武器诅咒等级提升。\n" +
										   "最小射击伤害1+等级/5=>1+等级/2*0.6f，最大射击伤害6+等级/2.5=>6+等级*0.6f。\n" +
										   "射击伤害其中75%为魔法伤害。\n" +
										   "可以升级了，且也能吃到强化等级，等级+20%英雄等级+10%魔力。\n" +
										   "灵能短弓箭矢能触发部分东西。"));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
										   "强化武器\n" +
										   "新增+37.5%最大命中的选项。\n" +
										   "+伤害50%-67%攻速x(等级+2)=>+12.5%伤害。\n" +
										   "+33%攻速-30%伤害x(等级+2)=>+25%攻速。\n",

										   "强化防具\n" +
										   "新增+25%移速的选项。\n" +
										   "+100%防御-200%最大闪避x(等级+2)=>+12.5%防御。\n" +
										   "+200%最大闪避-100%防御x(等级+2)=>+37.5%最大闪避。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ANKH), "十字架",
										   "未祝福效果\n" +
										   "满血复活并丢弃背包=>残血复活。\n\n" +

										   "祝福效果\n" +
										   "残血净化无敌复活=>满血净化无敌复活。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.CLOVER), "十三叶草",
										   "两段判定=>一段判定。\n" +
										   "概率60%x(25%+25%x等级)=>55%+4%x等级"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
										   "不能嬗变，自带法杖回收，且能转移灌注法杖等级1级。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.荣誉纹章),"荣誉纹章",
										   "破损纹章=>荣誉纹章。\n" +
										   "最大护甲+防具等级+防具阶级-1。\n" +
										   "移除受伤获得爆发屏障。\n" +
										   "提升防具的等级=>防具获得强化等级。\n" +
										   "可额外携带1级。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.水袋), "水袋",
										   "满的时候可以合成一瓶永生秘药。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.LIQUID_METAL), "金液",
										   "液金=>金液。\n" +
										   "价值1=>10。\n" +
										   "诅咒物品也能合成。\n" +
										   "可以为武器升级，消耗(阶级+等级+1)x3瓶。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARCANE_RESIN), "酶优树酯",
										   "奥术树脂=>酶优树酯。\n" +
										   "诅咒物品也能合成。\n" +
										   "专属的升级法杖等级=>增强法杖、神器的施法魔力收益10%。\n" +
										   "法杖转换数量2*(级-树脂级)=>级-树脂级+1，并且级-树脂级+1需要大于0才能转换。\n" +
										   "取消等级升级上限。"));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER,new ItemSprite.Glowing( 0xFFFFFF )), "附魔",
										   "死神\n" +
										   "根据已损失生命的次方=>根据已损失生命。\n"+
										   "喧闹\n"+
										   "文本添加更多，并且随机说话的性格。\n" +
										   "吸引敌人1/20=>1/10，以及一个额外效果，根据性格而定。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WEAPON_HOLDER), "武器",
//							     "生成概率1/2/3/4/5阶" +
//										   "1区{0,75,20,4,1}\n" +
//										   "2区{0,25,50,20,5}\n" +
//										   "3区{0,0,40,50,10}\n" +
//										   "4区{0,0,20,40,40}\n" +
//										   "5区{0,0,0,20,80}\n\n"+
//
//										   "改成\n" +
//								 			"{50,35,20,5,1}\n"+
//										   "{35,50,20,5,1}\n"+
//										   "{5,20,35,20,5}\n"+
//										   "{1,5,20,50,35}\n"+
//										   "{1,5,20,35,50}" ,

										   "装备和卸下时间1=>攻击延迟\n" +
										   "5阶都是特别物品，且都伤害x1.5\n" +
										   "武器战技重做\n" +
										   "最小攻击为1" ,

										   "暗杀之刃这类伏击武器不再是是最大攻击-最小攻击这样稳定伤害，而是额外造成伤害。\n"+
										   "匕首、镖15%伏击伤害，根据强度调整\n" +
										   "部分拥有15%流血伤害，根据强度调整\n" +
										   "钝器15%概率麻痹敌人10x概率回合，根据强度调整\n" +
										   "部分武器攻击15%概率冻结敌人10x概率回合，根据强度调整\n" +
										   "部分武器攻击额外造成15%魔法伤害，根据强度调整\n" +
										   "矛类首次攻击额外造成30%伤害，根据强度调整",

										   //										   "大部分武器的命中跟随攻击延迟，每0.25都会进行衰减，大于1的值比如1.75的值为1+0.25/2+0.25/4+0.25/8，小于1的值比如0.25为0.25+0.25/2+0.25/4\n" +
										   "大部分武器的伤害跟随攻击延迟\n" +
										   "武器最大充能(英雄等级+8)/3=>2+25%英雄等级\n" +
										   "武器投掷攻击能触发近战攻击效果\n" +
										   //										   "连招，武器范围>1时，那么设定x，x为范围。第x次攻击造成伤害+20%x(4-x)，1范围后会会重置为最初的范围。\n" +
										   "扔出装备的武器时，只花费扔出的时间，不花费卸下的时间。\n"+
										   "所有武器伤害倍率大于和小于1的最小和最大攻击，能从升级正确分配攻击。\n"+
										   "大部分武器属性所有调整，阶级有所下降或改变，一部分重叠类型武器删除。\n" +
										   "武器最小防御为等级，最大格挡量能根据阶级x等级同步提升，且最大防御等级计算+0.5，但是防御成长减半。" ,

										   "攻击和力量\n"+
										   "武器最小攻击=阶+级、最大攻击=5x(阶+1)+级x(阶+1)不同武器调整5的值=>原来的数值x伤害倍率来改动。\n" +
										   "武器投掷最小攻击2x阶+等级=>(阶+等级)x0.85。"
		));



		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER,new ItemSprite.Glowing( 0x666666 )), "刻印",
										   "恶臭\n" +
										   "释放的毒气元素能受到奥术之戒的效果。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARMOR_HOLDER), "防具",
										   "防具装备和卸下时间2=>攻速x2。\n"+
										   "最小防御等级=>阶级+等级。"
		));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_HOLDER), "神器",
										   "神器充能整数=>小数。\n"+
										   "装备和卸下花费1=>攻速。\n\n"+
										   "虚空锁链\n" +
										   "拉扯敌人会残废4回合。\n\n"+
										   "自然之履\n" +
										   "充能速度翻倍。\n\n"+
										   "暗影斗篷\n" +
										   "移除升级会根据英雄等级降低或增加获得的神器经验。\n\n"+
										   "丰饶之角\n" +
										   "最大充能5+等级/2=>3+等级/2。\n\n"+
										   "蓄血圣杯\n" +
										   "血祭消耗生命-5，并且扣血2.5~3.5系数=>1.5，生命再生加成(包括诅咒。",

										   "神偷袖章\n" +
										   "偷取1格内的敌人物品=>攻击范围内。\n" +
										   "可以窃取商人的商人信标。\n" +
										   "可以窃取魔法绵羊的随机种子。\n" +
										   "移除等级大于目标时不能窃取。\n" +
										   "不是敌人也能偷取。\n\n"+
										   "时光沙漏\n" +
										   "攻击不会打断时间冻结，以及一些行为操作。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RING_HOLDER), "戒指",
										   "升级25%概率祛邪=>必定\n" +
										   "诅咒等级-3=>-6。\n" +
										   "戒指的等级为等级x3次平方根等级，并且数值都从指数提升改成固定x等级的提升，以防止数据溢出。\n" +
										   "装备和卸下花费时间1=>攻速。\n" +
										   "取消物品还需要经验的鉴定，戒指是每装备1回合鉴定1/45次。",

										   "神射之戒=>神兵之戒\n" +
										   "增加投掷武器的等级=>装备的武器的0.75x等级增加" +
										   "奥术之戒\n" +
										   "1.175=>1+21025x(魔力-10)，每级不再提升附魔和刻印效果，而是+10魔力。\n\n"+
										   "根骨之戒\n" +
										   "1力量=>2力量。\n"+
										   "+生命系数1.035=>+(等级+1)x25生命。\n\n"+
										   "神射之戒\n" +
										   "等级加成=>2x等级加成。",

										   "狂怒之戒\n" +
										   "1.09051=>1.206。，且是总攻速加成，此外所有情况都能使用，还能在固定攻速时也能进行攻击提升。\n\n"+
										   "疾速之戒\n" +
										   "1.175=>1.12，且是总移速加成。\n\n"+
										   "财富之戒=>幸运之戒\n" +
										   "1.2=>1.15。\n"+
										   "现在自身的掉落机制会同时x怪物自身的掉率(最小为1)。\n"+
										   "0~20击杀触发前三档掉落=>5~30。\n"+
										   "5~10次前三档掉落回触发一次宝藏掉落=>8~15。\n"+
										   "英雄自带0级幸运之戒。\n"+
										   "暴击率+2%+2%x等级。",

										   "能量之戒\n" +
										   "1.2=>1.1845。\n\n"+
										   "精准之戒=>命中之戒\n" +
										   "1.3=>1.236，且是总命中加成。\n\n"+
										   "闪避之戒\n" +
										   "最大闪避提升1.125系数=>总闪避1.15875。",

										   "韧性之戒\n" +
										   "减少所有受到伤害，上取整=>减少所受攻击伤害，四舍五入。\n\n"+
										   "武力之戒\n" +
										   "能与空手+拳套+武力之戒空手+武力之戒武器叠加。\n" +
										   "最小和最大攻击/2。\n" +
										   "空手攻击1~力量-8=>5%力量~力量-8.5。\n" +
										   "最小和最大伤害不会因为等级为0不取最大阶伤。\n" +
										   "武器+等级增伤害=>武力之戒的最大攻击。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TRINKET_CATA),"魔能触媒",
										   "1区域生成一个=>3区。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.MISSILE_HOLDER), "投掷武器",
										   "移除投掷武器，投掷武器和近战武器归类为武器，都可以进行装备、投掷，但不可堆叠。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.轮刃), "轮刃",
										   "十字弩改成了轮刃。"));
		changes.addButton( new ChangeButton(Icons.ALCHEMY.get(),"炼金",
											"界面\n"+
											"最大配方3=>6。\n"+
											"顺序更好。\n"+
											"专属物品不能放入炼金和提炼。\n"+
											"现在正装备的装备以及背包不能放入炼金和提炼。\n" +
											"武器和防具、神器、法杖和戒指可以提炼，提炼量是金币。\n" +
											"饰物=>禁忌物，并且移除遗物，禁忌物提炼能量5=>4+等级x2。",

											"配方\n"+
											"念力结晶x8合成所需10金液和10能量=>6金液，6能量，3个。\n"+
											"返回晶柱5个=>3个。\n"+
											"诅咒菱晶4个=>3个。\n"+
											"根骨秘药6能量=>10能量，并且需要1精纯药液。\n"+
											"转换菱晶12能量合成12个=>6能量6个。\n"+
											"炼金菱晶合成8个，能量2=>6个，3能量。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_ROSE1), "干枯玫瑰",
										   "回归，并且同虫箭，不过没有1.1倍综合属性提升，而是闪避+30%，并且击杀敌人1/8会掉落干枯花瓣。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_BEACON), "时空道标",
										   "回归。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ARTIFACT_CAPE), "荆棘斗篷",
										   "回归。\n\n" +
										   "升级所需经验x2。\n" +
										   "所有受伤时判定=>防御判定。\n" +
										   "吸收伤害充能倍率50%+5%x神器等级=>50%+5%x神器等级。\n" +
										   "减伤和反伤为0~受伤=>减伤为50%+5%x等级，并且反伤为最大护甲x(1+等级/3)，或是攻击额外造成最大护甲x(1+等级/3)。\n" +
										   "移除反伤距离限制。"));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_HOLDER), "法杖",
										   "升级25%概率祛邪=>必定\n" +
										   "14x14贴图=>16x16\n" +
										   "法杖的数值都从魔力中收益，每级提升等级且还能提升法杖的魔力百分比收益。\n"+
										   "大部分的法杖施加Buff从等级收益改成固定。\n"+
										   "法杖的特效固定，不再从等级收益，防止后期特效炸满。\n"+
										   "法杖升级不再恢复1充能",

										   "灵壤法杖\n" +
										   "最小伤害不再固定，能收益等级，对深渊目标地施法如果是深渊那么就接壤。\n\n"+
										   "再生法杖\n" +
										   "可以对敌人造成伤害。\n\n"+
										   "注魂法杖\n" +
										   "不再给护盾，并且恶魔也能造成伤害。\n\n"+
										   "酸蚀法杖\n" +
										   "气体量50+10x级=>40+20x级。",

										   "雷霆法杖\n" +
										   "贴图改变，且使用更安全，现在会对敌人施加麻痹，自身残废。\n\n"+
										   "棱光法杖\n" +
										   "概率施加施加=>必定。\n\n"+
										   "魔弹法杖\n" +
										   "升级则会暂时强化其他法杖=>伤害等级收益+25%。\n\n"+
										   "冰霜法杖\n"+
										   "对冻伤和冻结的敌人减少伤害=>冻伤增加伤害且冻结敌人，如果冻结就减少伤害。\n" +
										   "在水中额外2回合冻伤=>直接冻结。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_HOLDER), "种子",
										   "速行蓟\n" +
										   "时间气泡攻击不会打断。\n\n" +

										   "烈焰花\n" +
										   "不会被烧毁。\n\n" +

										   "冰冠花\n" +
										   "不会被烧毁。\n" +
										   "如果有火，则扔出就能直接触发。"
		));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.POTION_HOLDER), "药剂",
										   "力量药剂=>潜力药剂\n" +
										   "特别物品，并且三选一属性提升。\n\n"+
										   "经验药剂\n" +
										   "特别物品。\n\n"+
										   "治疗药剂\n" +
										   "特别物品。\n" +
										   "最大生命80%+14=>90%。\n" +
										   "每次治疗护盾25%=>50%。\n\n"+
										   "奥术护盾合剂\n"+
										   "最大生命60%+10=>75%最大生命。",

										   "根骨秘药\n"+
										   "+暂时最大生命=>1主属性10永久最大生命。\n\n"+
										   "麻痹药剂\n"+
										   "范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。\n\n"+
										   "毒气药剂\n"+
										   "范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。",

										   "肌肉记忆合剂\n"+
										   "可以叠加使用，并且不再单单减少一个物品，是所有武器或所有护甲，供玩家自己选择。\n\n"+
										   "神意启发合剂\n"+
										   "天赋点+2=>1。\n\n"+
										   "抗魔秘药\n"+
										   "5+等级/2=>5+等级/1.5。\n\n"+
										   "大地护甲合剂\n"+
										   "2+等级/3=>2+等级/2.5。\n\n"+
										   "羽落秘药\n"+
										   "持续时间50=>100。跳楼减少10回合=>减少20回合。并且还能自动识别环境获得5回合浮空。"
		));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SCROLL_HOLDER), "卷轴",
										   "祛邪卷轴\n" +
										   "特别物品。\n\n" +
										   "蜕变秘卷\n" +
										   "移除蜕变天赋，而是改变英雄的特性。\n\n" +

										   "复仇卷轴\n" +
										   "4.5x阅读者已损失生命(最小4)x0.225=>阅读者已损失生命。\n\n" +

										   "嬗变卷轴\n" +
										   "特别物品。\n" +
										   "不能嬗变自身。\n" +
										   "护甲也能嬗变其他阶级护甲。\n\n" +

										   "注魔秘卷\n" +
										   "还附带一次升级。"
		));
	}

	public static void 重要(ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("重要改动", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton("","英雄数值",
											"10回合-1饥饿=>每回合-1。\n"+
											"空手攻速1=>2，最大命中+25%。\n" +
											"饥饿首次受伤450*最大生命/1000，后续受伤最大生命/1000=>首次和后续伤害皆为饥饿速率/2+√已损失生命/89，同时优化饥饿算法。\n"+
											"武器、法杖、神器充能速度-100%。\n"+
											"女性英雄大小-0.05，男性则+0.05。\n"+
											"专属物品金币和能量价值提升25%。\n"+
											"升级不再会同时恢复提升的生命。",

											"再生恢复的生命值为延迟值=>1，恢复值1=>√最大生命/100+0.1f，提升恢复速度是提升恢复值。\n"+
											"根据饥饿值，最多+15%再生速度，以及根据饱腹状态做多再生速度+15%。\n"+
											"升级所需经验初始10+5/级=>10+6/级，且最高升级所需限制在150，每5级所需经验x1.25。\n"+
											"提升最大生命时，不再恢复所提升的生命，并且在最大生命减少时也不会损失生命。\n"+
											"任何饱腹填充都能触发吃饭天赋，不过效果倍率是食物填充值/150。\n"+
											"武器在近距离最大命中不变，非近距离(抵近射击)-50%最大命中。\n"+
											"移除搜索、开锁等动作扣除饥饿值，同时搜索花费时间2=>搜索范围x2。\n"+
											"治疗护盾-10%。\n"+
											""));
		changes.addButton( new ChangeButton(new Image(avatar(HeroClass.WARRIOR,tier(HeroClass.WARRIOR))),"英雄机制",
											"英雄初始不携带口粮。\n"+
											"视野算法优化。\n"+
											"现在吃饭超过450的食物也会填充，施加饱腹Buff，但是饱腹最多450。\n" +
											"神器和戒指栏位=>两个杂项栏位。\n"+
											"浮空状态在深渊点击自身可以无伤掉下去。\n"+
											"燃烧获得火把效果。",
											"搜索会在搜索范围内拾取非陷阱上的物品和打开箱子，以及踩踏草。\n"+
											"先处理防御然后处理攻击=>先处理攻击然后处理防御。\n"+
											"各英雄都拥有特定的效果，武器装备更符合设定。\n"+
											"除了战士都移除水袋，防具都变成专属防具。"


		));

		changes.addButton( new ChangeButton(Icons.get(Icons.DISPLAY), "显示",
						"攻击动画倒数第二帧延迟1/10秒，提升打击手感，即攻击后摇。\n" +
						"点击文本根据文本识别来显示文本注解。\n" +
						"亮度调节只有-1-1=>0-1。\n" +
						"游戏地图地块颜色，视野内无色=>绿色，视野外且探索过的=>红色，探地过的蓝色变得更蓝。\n" +
						"大于2的回血、护盾、护甲、伤害才显示。\n" +
						"恐药异症中的过敏物品文本介绍以及图标毒粹。\n" +
						"死亡和超过3回合隐藏所有表情。\n" +
						"时间回合轮盘更明显。\n" +
						"护盾特效偏粉=>偏白。" ,

						"提示文本可以显示更多行，并且显示颜色优化。\n" +
						"设置界面和英雄属性界面更好。\n" +
						"显示更多信息。\n" +
						"拖条按钮更大。\n" +
						"Buff显示计数。\n" +
						"状态栏添加食物条。并且不显示Buff图标。\n"+
						"状态栏添加护盾条。\n"+
						"优化指标，新增食物栏、药剂栏、上下楼快速去往标，并移除排版的自动优化。",

						"物品信息显示+强化等级。\n" +
						"更好的物品图标和底色，数值显示。\n" +
						"优化显示面板以及显示更多内容。\n" +
						"设置UI更新，且添加和移除部分按钮。\n" +
						"Yendor护符结算时的英雄贴图采用另一种。\n" +
						"属性条动画更好。\n"+
						"伤害显示仅魔法和物理伤害。"));
		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), "杂项",
											"流血伤害不再只限于施加的伤害，而是可以堆叠。\n" +
											"属性更新不再是特定情况触发刷新，而是每秒刷新。\n" +
											"Buff伤害和防具刻印、炸弹、盟军、陷阱、地牢的伤害也能判定击杀。\n" +
											"陷阱数量生成后处理每5格地板不超过1个陷阱。\n" +
											"无限命中大于无限闪避。\n" +
											"武器、防具生成，生成特定阶级时，不再有机会生成其他阶级。\n" +
											"护盾类也算作治疗效果。\n" +
											//				"交互商人，英雄升级，打开英雄面板和背包面板会保存游戏，防止意外崩溃的丢失。\n" +

											"正常角色攻击和防御施加战斗状态5回合，静止时会施加静止回合。\n" +
											"一些根据最大防御减少伤害的机制都改为正常的经过最小防御~最大防御和防御。",

											"一些物品类别和怪物等的默认有使用技巧备注，如果未鉴定的卷轴、药剂、戒指则不显示物品。\n" +
											"显示危险点提示多显示1秒，并且大部分攻击危险点都提示。\n" +
											"新增存档可以备注。\n" +
											"自定义备注上限5=>250。\n" +
											"隐形时发现敌人不再中断。\n" +
											"攻速和移速都改为+-法(武器、防具、空手是直接设定初始值)。\n" +
											"法杖和神器充能改为+-法，特殊情况是如仅50%。",

											"新增地牢时间每天为2400回合，100回合一小时，以及天数，初始900回合，22-6点是夜晚会-1视野范围以及亮度-1，第一天。\n" +

											"冻伤每回合受到0.25~1+地牢层数/16的伤害。\n" +
											"冻结解冻时受到4~12+地牢层数。" ,

											"游戏中攻击、防御、生命整数类型=>小数类型，并且自动识别保留小数点后2、1、0位。\n" +
											"补充、通俗易懂的地牢探索指南，每秒闪烁1.5=>2.5，并更好的获得他们，且麻痹5秒。\n"+

											//				"3区没下矿墙壁镶嵌的是秘银，也能被镐子挖取。\n" +
											"火焰和冰霜相关的物品不会被烧毁。\n" +
											"毒气陷阱范围1格开始=>3x3，但是气体总量不变，单格气体从1000=>111。\n" +
											"优化武器的快捷默认使用，如果有战技会弹出使用按钮。" ,

											"一些勋章降低获取难度。\n" +
											"种子存档也能解锁勋章，不需要通关也能使用一些开关。\n" +
											"身上带有护符能使用传送。\n" +
											"大部分代码先+后x计算，除了特殊机制。\n"+
											"现在种子输入物品代码名可以直接生成鉴定的物品在背包，并且不会固定种子，但其他种子依旧会固定。\n" +
											"移除语言设置。"

		));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复,144,0,16,16), "修复",
										   "修复潜在可能的存储物品等级丢失。\n" +
										   "修复0受伤时还显示受伤图标。\n" +
										   "修复潜在没惊醒敌人，没有正确流浪、狩猎。\n" +
										   "修复缩餐节食两次计算丰饶之角的吃饭。\n" +
										   "修复炼金指南箭头检测到已装备的物品也高亮快捷合成按钮，然后无消耗合成。\n" +
										   "修复决斗秘卷只免疫攻击伤害。\n" +
										   "修复楼层入口和楼层出口文本反了。" ,

										   "修复酸蚀法杖高等级的施法会导致低等级的施法伤害没有正确分配级。\n" +
										   "修复背包格子不是正方形。\n" +
										   "修复傀儡和老鬼傀儡死亡潜在问题，傀儡和老鬼傀儡死亡不会触发联动效果，以及老鬼和小老鬼也不触发，如击杀掉落等。\n" +
										   "修复时间气泡潜在回合判定错误。" ,

										   "修复潜在相关透视可能黑幕。\n" +
										   "修复古神幼虫动画异常。\n" +
										   "修复饥饿速率不对受伤修正。\n" +
										   "修复魅魔站立动画异常有攻击的一帧，并且不是苍白皮肤色，也有恶魔角，淫纹。" ,

										   "修复虹光幻像不在英雄视野还在攻击。\n" +
										   "修复潜在无敌可能跳楼还会施加Buff和伤害。\n" +
										   "修复防具使用强化符石最小防御和最大防御没有正确分配。\n" +
										   "修复神偷袖章偷取没有掉落物的敌人崩溃。\n" +
										   "修复恶魔撕裂者有概率自己跳入深渊。"));


	}

	public static void 杂项(ArrayList<ChangeInfo> changeInfos) {

		ChangeInfo changes = new ChangeInfo("杂项改动", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

//		new RenderedText(cleanStr,size)
		changes.addButton( new ChangeButton(Icons.泉.get(),"井水房",
											"出现概率翻倍。"));
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢",
										   "1层下水道陷阱(老化飞镖陷阱1)=>激流陷阱1。\n"+
										   "下水道陷阱(寒气陷阱4,电击陷阱4,毒气陷阱4,老化飞镖陷阱4,警报陷阱2,淤泥陷阱2,致眩气体陷阱1,羊群陷阱1,召唤陷阱1,传送陷阱1)=>淤泥陷阱1、激流陷阱1。\n" +

										   "地牢陷阱数量/2。\n"+
										   "火房必定生成额外冰冠花物品\n"+
										   "木房必定生成额外烈焰花物品\n"+
										   "献祭房\n" +
										   "所需经验减少90%，并且可以站在祭坛上扔治疗药剂直接献祭完成。\n\n"+

										   "毒气房\n" +
										   "毒气房初始毒气30=>8，生产毒气12=>10。" ,

										   "地牢生成1\n" +
										   "物品生成不会生成在出口。\n"+
										   "3区每层必定3个石头。\n"+
										   "物品也能生成在怪物下。\n"+

										   "地牢生成2\n" +
										   "每区域偶数层必定生成一个小包口粮，且金币价值减半。\n" +
												   //										   "每区域的升级卷轴数量-1。\n" +
										   "每区域第3层必定生成治疗药剂。\n" +
										   "第四层必定生成一个烈焰花种子和液火药剂。"));

		changes.addButton( new ChangeButton(Icons.get(Icons.TALENT), "天赋",
											"移除一层和二层的一个天赋，一二层天赋是天赋树一样的设定。\n" +
											"两点天赋的效果点满不再是1.5倍，是2倍。\n" +
											"天赋解锁从2/7/13/21/30=>2/6/11/21。\n" +
											"天赋点数5/6/8=>4/5/10。\n" +
											"二层和三层最大天赋点+1。"));

		changes.addButton( new ChangeButton(Icons.get(Icons.AUDIO), "声音",
											"主题曲我的三体《夜航星》。\n" +
											"一些武器音效。\n" +
											"一些物品音效。\n" +
											"一些怪物音效。\n" +
											"一些杂项音效。" ));
		changes.addButton( new ChangeButton(new ShopkeeperSprite(),"商人房",
											"4区将是商士而不是商人，3区将是商机而不是商人，2区将是商鼠而不是商人。\n" +
											"最大回购3=>6。\n" +
											"神器法杖戒指中随机卖一样，如果斗不卖默认奥术刻印笔=>神器法杖戒指中随机卖一样，并且必卖奥术刻印笔。\n" +
											"炼金菱晶商店不卖。\n" +
											"1区域1层也有商店，卖1阶武器和防具。",

											"不再随机药剂和卷轴必卖净化药剂、极速药剂、隐形药剂和嬗变卷轴，鉴定卷轴和感知符石、治疗药剂和血药。\n" +
											"不能出售正在装备的物品。\n" +
											"非战士在第一个商店会卖水袋。\n" +
											"出售的戒指、法杖和神器现在是鉴定的。\n" +
											"只卖一个武器。\n" +
											"商店的武器和防具，戒指和法杖有概率拥有等级。\n" +
											"商店的小包口粮x2=>口粮+小包口粮。"));
		changes.addButton( new ChangeButton(new GhostSprite(),"NPC",
											"悲伤幽灵的任务奖励武器和防具的随机等级使用同一个随机=>不同随机。\n"+
											"老杖匠的奖励额外一个选择，获得3个酶优树酯。\n"+
											"巨魔铁匠任务要求数量40=>20，携带的数量越多，增加的好感度越多。\n" +
											"巨魔铁匠新增额外选项，不需要人情，会赠送锻造锤。\n"+
											"野心勃勃的小恶魔不再半透明。"));


		changes.addButton( new ChangeButton(Icons.get(Icons.BACKPACK_LRG), "背包",
											"绒布袋会让此背包的物品使用不消耗回合。\n" +
											"魔法筒袋现在也会给武器充能。\n" +
											"卷轴筒和药剂挎带会让能量价值永久提升50%。" ));
		changes.addButton( new ChangeButton(Icons.CHALLENGE_COLOR.get(), "挑战",
											"精英强敌，新增噩梦精英攻击+35%，攻速+50%。冰寒精英攻击+25%，且攻击15%概率冻结1.5回合，免疫冻伤和冻结。暴击率+30%，吸血+20%。\n" +
											"精英强敌，近战伤害=>攻击，敌法和巨型减伤改成+1/减免的百分最大生命。\n" +
											"精英强敌，生成不会惊醒，改成白，成长精英每4回合1%=>每回合0.25%。\n" +
											"精英强敌，统一光线数量5。" ,

											"缩餐节食，食物仅33%=>50%。\n" +
											"信念护体，不减少灵壤法杖的防御，武器、防具的最小防御不会为0，但是最大防御额外-1。\n" +
											"没入黑暗，视野范围1/4=>1/3，其他光源有效性降至1/5=>1/3(不会影响法杖Buff时间)。"));


	}

}
