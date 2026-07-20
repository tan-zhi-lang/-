

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.util.ArrayList;

public class 缝合 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		addChanges(changeInfos);
	}


	public static void addChanges(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("缝合重制版本", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo("v1.0.0", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.海克斯宝典), "重制",
										   "致重制的海克斯版本。"));
		changes.addButton(new ChangeButton(Icons.CATALOG.get(),"新生",
										   "因为群友的帮助，作者活了下来，本来不会再打算制作了，因为遗憾，我还是打算重启了。\n" +
										   "最后这是比老版更好玩，有LOL海克斯、优化更好的版本，虽然花了大半年时间慢慢改的。"));


		changes = new ChangeInfo("缝合老版本", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes = new ChangeInfo("v3.2.0-v3.7.0", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TOMB), "停更",
										   "致遗失的版本更新。"));
		changes.addButton(new ChangeButton(Icons.CATALOG.get(),"遗憾",
										   "这时候因为作者用笔记本电脑打游戏关机没注意温度，放书包里在太阳下乘朋友摩托车，最后打开笔记本电脑发现主板被烧坏。\n\n" +
										   "最后游戏代码也没备份，几年的心血就此陨落，我也因为一些事情，步入了人生低谷，游戏便解散了。"));

		changes = new ChangeInfo("v1.8.5-v3.2.0", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.GRAVE), "缝合",
				"致未记载的版本更新。"));
		changes.addButton(new ChangeButton(Icons.CATALOG.get(),"梗音",
										   "这个版本因为群友的几句点拨，开始了改名，之后加了很多东西。\n\n" +
										   "最有意思的还是梗音，即各种各种的梗的音乐融入游戏。"));


	}

}
