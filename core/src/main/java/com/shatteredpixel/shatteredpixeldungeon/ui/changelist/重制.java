

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
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


	public static void add_v1_0_0_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.0.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton(new ChangeButton(new ItemSprite(物品表.FOOD_HOLDER), "食物条",
				"状态栏添加食物条。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.杂物袋), "杂物袋",
				"商店出售杂物袋。"));
		changes.addButton( new ChangeButton(Icons.SEED_POUCH.get(), "种子",
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

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"英雄\n\n" +
						"最大生命+10，最大命中+10，最大闪避+5。\n"+
						"各英雄都拥有特定的效果，武器装备更符合设定。\n"+
						"除了战士都移除水袋，护甲都变成专属护甲。\n"+
						"再生额外恢复1%最大生命。"
//						"自动拾取金币、能量晶体、水珠。"
						,
						"杂项\n\n" +
						"根骨之戒力量+1 => +2。提升生命系数1.035 => 1.07。\n" +
						"投掷和弓在近距离+50%最大命中，非近距离-50%最大命中。\n" +
						"优化感知、视野范围、光照范围计算。"
		));

		changes.addButton( new ChangeButton(new ImpSprite(), "野心勃勃的小恶魔",
				"不再半透明。"));
		changes.addButton( new ChangeButton(new GhostSprite(), "悲伤幽灵",
				"移速50% => 34%。"));
		changes.addButton( new ChangeButton(Icons.CHALLENGE_COLOR.get(), "挑战",
				"恐药异症 => 喝药会受到5%最大生命伤害。\n" ));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "升级",
				"升级提升的命中和生命+1.05。最大等级-5，升级所需经验初始+4，每级+2。\n" ));
		changes.addButton( new ChangeButton(new ThiefSprite(), "疯狂小偷",
				"偷后移速83% => 75%。\n" +
						"防御时掉落金币1 => 10"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ENERGY), "能量晶体",
				"武器和护甲可以提炼，法杖戒指可以提炼，提炼量是金币的15%。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.水袋), "水袋",
				"灌满的水袋可以合成一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.DUST), "尸尘",
				"金币数量1 => 自然生成金币。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAR_HAMMER), "武技",
				"武器充能最大充能8 => 10。\n" +
						"勇士职业不改变武器最大充能。\n" +
						"最大充能 2+根据英雄等级34% => 3+武器等级/2。",
				"顺劈统一+34%伤害。\n" +
				"连击统一攻速回合内攻击两次。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.CROWN), "护甲技能",
				"矮人国王不掉落，护甲技能移除。"));
		changes.addButton( new ChangeButton(Icons.INFO.get(), "信息面板",
				"优化。"));
		changes.addButton( new ChangeButton(Icons.LANGS.get(), "语言设置",
				"移除。"));
		changes.addButton( new ChangeButton(new SheepSprite(), "魔法绵羊",
				"彩蛋。"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);

//		changes.addButton( new ChangeButton(new HeroSprite(), "鼠王房间",
//				"金币数量x2。"));
		changes.addButton( new ChangeButton(new ShopkeeperSprite(), "商人房间",
						"非战士在6层会卖水袋。\n" +
						"商店的小包口粮 => 口粮。\n" +
						"随机一个药剂或卷轴 => 两张随机卷轴+一瓶随机药剂。"));
		changes.addButton( new ChangeButton(new RatKingSprite(), "鼠王房间",
				"金币数量x2。"));
		changes.addButton( new ChangeButton(new SnakeSprite(), "下水道巨蛇",
				"最大攻击+2。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "天赋系统",
				"移除一层和二层的一个天赋，增强大部分。\n" +
						"天赋解锁从2/7/13 => 2/5/11\n" +
						"天赋点数5/6/8 => 3/6/9\n" +
						"二层和三层最大天赋点+1"));
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢生成",
						"毒气房金币数量x2。\n" +
						"第一层必定生成一瓶经验药剂。\n" +
						"每个区域生成的升级卷轴数量+1。\n" +
						"3/8/13/18/23层必定一瓶治疗药剂。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_INTUITION), "感知符石",
					"额外生成一个感知符石。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.WAND_HOLDER), "法杖",
					"升级获得1充能。"
				));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SCROLL_HOLDER), "卷轴",
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
				"偶数层必定生成一个。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.SEED_FIREBLOOM), "烈焰花",
				"4层必定生成。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RATION), "吃饭机制",
				"吃饭恢复1+1%最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
				"改变等级时获得1充能。\n" +
				"自带+1强化等级。\n" +
						"自带法杖回收，且能吸收灌注法杖一半的等级。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.SUCKER_PUNCH), "伏击机制",
				"伏击+1+敌人1%最大生命伤害，隐形伏击+1+敌人1%最大生命伤害。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.神圣法典), "神圣法典",
				"+1最大充能。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ANKH), "十字架",
				"未祝福效果 => 残血无敌复活。\n" +
						"祝福效果 => 满血无敌复活"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_MIGHT), "根骨秘药",
				"+1力量+暂时生命 => +20最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.破损纹章), "破损纹章",
				"初始护盾3 => 1。\n" +
				"半血受伤获得 => 受伤获得。\n" +
				"提升护甲的等级改成强化等级。\n" +
							"可额外携带1级，冷却150 => 75。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
				"最小攻击+2，最大攻击+1。\n" +
						"能触发部分东西。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ALCHEMIZE), "炼金菱晶",
				"商店卖数量x4，价格-75%。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.时光沙漏), "时光沙漏",
				"攻击不会打断时间冻结。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
				"对武器的增强 增加伤害50%减少67%攻速 => 增加25%伤害\n" +
						"对武器的增强 增加33%攻速减少30%伤害 => 增加34%攻速",
				"对护甲的增强 增加100%防御减少200%闪避 => 增加25%防御\n" +
						"对护甲的增强 增加200%闪避减少100%防御 => 增加50%闪避"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.FIRE, true), "着火",
				"这个状态提供火把效果。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.ANKH, true), "无敌",
				"初始添加3回合 => 10回合。\n" +
						"十字架 祝福3回合=> 未祝福5回合，祝福10回合。"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.削弱);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new GooSprite(), "粘咕",
				"受到火焰伤害+1+1%最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"3个合成时消耗能量2 => 3。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.无声步伐), "无声步伐",
				"所有英雄的惊扰距离+1。"));

	}

}
