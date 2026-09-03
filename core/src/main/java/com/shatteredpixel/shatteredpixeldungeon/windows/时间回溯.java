
package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class 时间回溯 extends Window{

	private static final int WIDTH		= 130;
	private static final int BTN_HEIGHT	= 20;
	private static final int GAP		= 2;

	private int pos;

	public 时间回溯() {
		super();

		加按钮("回档", "回档");

		if(Dungeon.符文("Boss阴到没边但是我能回档"))
		加按钮("海克斯回档", "海克斯回档");

		if (pos == 0) {
			GLog.橙("回档失败：没有可用的快照");
			hide();
			return;
		}
		resize(WIDTH, pos);
	}

	private void 加按钮(String 快照名, String 前缀){
		Bundle meta = Dungeon.回溯元数据(快照名);
		if (meta == null) return;
		int 层数 = meta.getInt("depth");
		String label = 前缀 + "：回到第" + 层数 + "层";
		RedButton btn = new RedButton(label) {
			@Override
			protected void onClick() {
				hide();
				if (Dungeon.恢复回溯存档(快照名)) {
					//死亡时 delete() 会在缓存里留 null，必须先清缓存再 check
					GamesInProgress.setUnknown(GamesInProgress.curSlot);
					if (GamesInProgress.check(GamesInProgress.curSlot) != null) {
						Dungeon.hero = null;
						Dungeon.level = null;
						Dungeon.daily = Dungeon.dailyReplay = false;
						InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
						ShatteredPixelDungeon.switchScene(InterlevelScene.class);
						return;
					}
				}
				GLog.橙("回档失败：快照不存在或已损坏");
			}
		};
		add(btn);
		btn.setRect(0, pos > 0 ? pos += GAP : 0, WIDTH, BTN_HEIGHT);
		pos += BTN_HEIGHT;
	}
}
