

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.ChangesScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
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


	public static void add_v1_0_0_Changes( ArrayList<ChangeInfo> changeInfos ) {

		ChangeInfo changes = new ChangeInfo("v1.0.0", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "new"), false, null);
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.DISPLAY.get(), "动画速度",
				"添加可调整动画速度按钮"));
		changes.addButton( new ChangeButton(Icons.AUDIO.get(), "主题曲",
				"我的三体《夜航星》。"));
		changes = new ChangeInfo(Messages.get(ChangesScene.class, "changes"), false, null);
		changes.hardlight(CharSprite.WARNING);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.get(Icons.PREFS), Messages.get(ChangesScene.class, "misc"),
				"英雄相关\n\n" +
						"最大生命+10，最大命中+10，最大闪避+5。\n" +
						"升级提升的命中+1。最大等级-5，升级所需经验+2/+1。\n" +
						"伏击额外造成1伤害，吃饭恢复1生命值。\n"+
						"各英雄都拥有特定的效果，一些武器装备更符合设定。\n"+
						"除了战士都移除水袋，护甲都变成专属护甲。"
						,
						"地牢相关\n\n" +
						"第一层必定生成一瓶经验药剂，额外一个感知符石。\n" +
						"每个区域生成的升级卷轴数量+1"
						,
						"杂项相关\n\n" +
						"投掷和弓在近距离+50%最大命中，非近距离-50%最大命中。\n" +
						"优化信息面板，优化感知、视野范围计算。"
		));

		changes.addButton(new ChangeButton(new ItemSprite(物品表.CROWN), "护甲技能",
				"矮人国王不掉落，护甲技能移除。"));
		changes.addButton( new ChangeButton(Icons.LANGS.get(), "语言设置",
				"移除。"));

		changes.addButton(new ChangeButton(new Image(Assets.Sprites.修复, 144, 0, 16, 16), Messages.get(ChangesScene.class, "bugfixes"),
				""));

		changes = new ChangeInfo(Messages.get(ChangesScene.class, "buffs"), false, null);
		changes.hardlight(CharSprite.增强);
		changeInfos.add(changes);

		changes.addButton( new ChangeButton(Icons.TALENT.get(), "天赋系统",
				"移除一层和二层的一个天赋，增强大部分。\n" +
						"天赋解锁从9/12 => 7/14\n" +
						"天赋点数5/6/8=>3/6/9\n" +
						"二层和三层最大天赋点+1"));
		changes.addButton(new ChangeButton(new ItemSprite(物品表.ELIXIR_MIGHT), "根骨秘药",
				"改成永久+1力量和+10最大生命。"));
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


	}

}
