

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

public class 奇袭 {

	public static void addAllChanges( ArrayList<ChangeInfo> changeInfos ){
		add_v1_0_0_Changes(changeInfos);
	}


	public static void add_v1_0_0_Changes( ArrayList<ChangeInfo> changeInfos ) {
		ChangeInfo changes = new ChangeInfo("v0.1.0-v1.8.5", true, "");
		changes.hardlight(Window.TITLE_COLOR);
		changeInfos.add(changes);
		changes.addButton(new ChangeButton(new ItemSprite(物品表.TOMB), "奇袭",
				"致遗失的版本更新。"));
	}

}
