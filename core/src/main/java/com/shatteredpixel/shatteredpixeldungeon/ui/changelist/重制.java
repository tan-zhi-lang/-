

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GhostSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GooSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ImpSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatKingSprite;
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

		changes.addButton( new ChangeButton(Icons.DISPLAY.get(), "动画速度",
				"添加可调整动画速度按钮。"));
		changes.addButton( new ChangeButton(Icons.CONTROLLER.get(), "固定移速",
				"可调整固定移速，例如在1.05移速时固定1。"));
		changes.addButton( new ChangeButton(Icons.AUDIO.get(), "主题曲",
				"我的三体《夜航星》。"));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"英雄相关\n\n" +
						"最大生命+10，最大命中+10，最大闪避+5。\n"+
						"各英雄都拥有特定的效果，武器装备更符合设定。\n"+
						"除了战士都移除水袋，护甲都变成专属护甲。\n"+
						"再生额外恢复1%最大生命。\n"
						,
						"杂项相关\n\n" +
						"根骨之戒力量+1 => +2。提升生命系数1.035 => 1.07。\n" +
						"投掷和弓在近距离+50%最大命中，非近距离-50%最大命中。\n" +
						"优化感知、视野范围、光照范围计算。"
						,
						"商店相关\n\n" +
						"非战士在6层会卖水袋。\n" +
						"商店的小包口粮 => 口粮。\n" +
						"随机一个药剂或卷轴 => 两张随机卷轴+一瓶随机药剂。"
		));

		changes.addButton( new ChangeButton(new ImpSprite(), "野心勃勃的小恶魔",
				"不再半透明。"));
		changes.addButton( new ChangeButton(new GhostSprite(), "悲伤幽灵",
				"移速50% => 34%。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "升级相关",
				"升级提升的命中和生命+1。最大等级-5，升级所需经验初始+2，每级+1。\n" ));
		changes.addButton( new ChangeButton(new SnakeSprite(), "疯狂小偷",
				"偷后移速83% => 75%。\n" +
						"防御时掉落金币1 => 10"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.CROWN), "护甲技能",
				"矮人国王不掉落，护甲技能移除。"));
		changes.addButton( new ChangeButton(Icons.INFO.get(), "信息面板",
				"优化。"));
		changes.addButton( new ChangeButton(Icons.LANGS.get(), "语言设置",
				"移除。"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new RatKingSprite(), "鼠王房间",
				"金币数量x2。"));
		changes.addButton( new ChangeButton(new ThiefSprite(), "下水道巨蛇",
				"最大攻击+2。"));
		changes.addButton( new ChangeButton(Icons.TALENT.get(), "天赋系统",
				"移除一层和二层的一个天赋，增强大部分。\n" +
						"天赋解锁从2/7/13 => 2/5/11\n" +
						"天赋点数5/6/8 => 3/6/9\n" +
						"二层和三层最大天赋点+1"));
		changes.addButton(new ChangeButton(Icons.get(Icons.下楼), "地牢生成",
						"第一层必定生成一瓶经验药剂，额外一个感知符石。\n" +
						"每个区域生成的升级卷轴数量+1。\n" +
						"每层额外生成一个小包口粮。\n"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.Icons.SCROLL_ENCHANT), "注魔秘卷",
				"还附带一次升级。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.Icons.POTION_STAMINA), "精力回复合剂",
				"移速1.5 => 2.5。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.Icons.POTION_SHIELDING), "奥术护盾合剂",
				"最大生命护盾60% => 75%。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.Icons.POTION_HEALING), "治疗药剂",
				"最大生命80% => 90%。\n" +
						"恢复速度25% => 34%。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.RATION), "吃饭机制",
				"吃饭恢复1+1%最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.法师魔杖), "法师魔杖",
				"自带+1强化等级。\n" +
						"自带法杖回收，且能吸收灌注法杖一半的等级。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.SUCKER_PUNCH), "伏击机制",
				"伏击+1伤害，隐形伏击+1伤害。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.神圣法典), "神圣法典",
				"+1最大充能。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_MIGHT), "根骨秘药",
				"改成永久+1力量和+10最大生命。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.破损纹章), "破损纹章",
				"初始护盾3 => 1。\n" +
				"提升护甲的等级改成强化等级。\n" +
							"可额外携带1级，冷却150 => 75。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.灵能短弓), "灵能短弓",
				"最小攻击+2，最大攻击+1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.时光沙漏), "时光沙漏",
				"攻击不会打断时间冻结。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STONE_AUGMENTATION), "强化符石",
				"对武器的增强 增加伤害50%减少67%攻速 => 增加100%伤害减少50%攻速\n" +
						"对武器的增强 增加33%攻速，减少30%伤害 => 增加100%攻速，减少50%伤害"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.IMMUNITY, true), "净化药剂",
				"持续回合+5。"));
		changes.addButton(new ChangeButton(new BuffIcon(BuffIndicator.HASTE, true), "极速药剂",
				"移速300% => 400%。"));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "nerfs"), false, null);
		changes.hardlight(CharSprite.削弱);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(new GooSprite(), "粘咕",
				"受到火焰伤害+1。"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.STEWED), "炖肉",
				"3个合成时消耗能量2 => 3。"));
		changes.addButton(new ChangeButton(new TalentIcon(Talent.无声步伐), "无声步伐",
				"所有英雄的惊扰距离+1。"));

	}

}
