

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.对话NPC;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class Wnd对话 extends Window {

	private static final int WIDTH      = 120;
	private static final int BTN_HEIGHT = 20;
	private static final int GAP        = 2;

	public Wnd对话(final 对话NPC npc,String mes,String r1,String r2) {
		
		super();

		RenderedTextBlock message = PixelScene.renderTextBlock( mes, 6 );
		message.maxWidth(WIDTH);
		message.setPos(0, 0);
		add( message );
		RedButton btnReward = new RedButton( r1 ) {
			@Override
			protected void onClick() {

			}
		};
		btnReward.setRect( 0, message.top() + message.height() + GAP, WIDTH, BTN_HEIGHT );
		add( btnReward );
		RedButton btnReward2 = new RedButton( r2 ) {
			@Override
			protected void onClick() {

			}
		};
		btnReward2.setRect( 0, btnReward.top() + btnReward.height() + GAP, WIDTH, BTN_HEIGHT );
		add( btnReward2 );
		
		resize( WIDTH, (int)btnReward2.bottom() );
	}
	
	private void takeReward(对话NPC npc) {
		
		hide();
		
		npc.flee();
		
		Imp.Quest.complete();
	}
}
