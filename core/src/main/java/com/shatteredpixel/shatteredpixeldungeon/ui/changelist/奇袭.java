

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

import java.util.ArrayList;

public class 奇袭 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		addChanges(changeInfos);
	}


	public static void addChanges(ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v0.1.0-v1.8.5", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TOMB), "奇袭",
				"致遗失的版本更新。"));
	}

}
