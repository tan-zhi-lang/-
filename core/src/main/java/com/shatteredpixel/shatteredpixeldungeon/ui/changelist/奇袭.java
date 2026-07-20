

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.util.ArrayList;

public class 奇袭 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		addChanges(changeInfos);
	}


	public static void addChanges(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("奇袭版本", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);

		changes = new ChangeInfo("v0.1.0-v1.8.5", false, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TOMB), "奇袭",
				"致古老的版本更新。"));
		changes.addButton(new ChangeButton(Icons.CATALOG.get(),"起源",
										   "因为最开始玩《破碎像素地牢》打不过，感觉太肉鸽了，打算自己魔改一个自己简单玩玩。\n\n" +
										   "最后改多了，抱着赤子之心，希望自己做一款自己心意的类破碎像素地牢游戏然后就开始了废寝忘食的更新。"));
	}

}
