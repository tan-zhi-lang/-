

package com.shatteredpixel.shatteredpixeldungeon.windows;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Group;

public class Wnd属性 extends Window {

	private static final int WIDTH			= 115;

	private int GAP	= 4;

	public Wnd属性(){

		IconTitle title = new IconTitle( Icons.get(Icons.INFO), "英雄属性");
		title.setRect(0, 0, WIDTH, 16);
		add(title);

		float pos = title.bottom()+2;

			pos = statSlot(this,"最大生命",kw2(Dungeon.hero.最大生命),pos,false);
//			pos = addInfo(this, Messages.get(this, "treasure_desc_old"), pos);

		resize(WIDTH, (int)pos);

	}


	private float statSlot(Group parent, String label, String value, float pos, boolean highlight ) {

		RenderedTextBlock txt = PixelScene.renderTextBlock( label, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(0, pos);
		parent.add( txt );

		txt = PixelScene.renderTextBlock( value, 7 );
		if (highlight) txt.hardlight(Window.TITLE_COLOR);
		txt.setPos(WIDTH * 0.7f, pos);
		PixelScene.align(txt);
		parent.add( txt );

		return pos + GAP + txt.height();
	}

	private float addInfo(Group parent, String info, float pos){

		RenderedTextBlock txt = PixelScene.renderTextBlock( info, 5 );
		txt.maxWidth(WIDTH);
		txt.hardlight(0x999999);
		txt.setPos(0, pos-2);
		parent.add( txt );

		return pos - 2 + GAP + txt.height();

	}


}
