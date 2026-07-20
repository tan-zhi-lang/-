

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.ui.Component;

public class Wndinfo extends Window {

	protected static final int WIDTH_MIN    = 120;
	protected static final int WIDTH_MAX    = 220;
	protected static final int GAP	= 2;

	public Wndinfo(String title,String message) {

		this( new Title(title), message );

	}

	public Wndinfo(String message) {

		this( new Title(), message );

	}

	public Wndinfo(Component titlebar,String message) {

		super();

		int width = WIDTH_MIN;

		titlebar.setRect( 0, 0, width, 0 );
		add(titlebar);

		RenderedTextBlock text = PixelScene.renderTextBlock( 6 );
		if (!useHighlighting()) text.setHightlighting(false);
		text.text( message, width );
		text.setPos( titlebar.left(), titlebar.bottom() + 2*GAP );
		add( text );

		while (PixelScene.横屏()
			   && text.bottom() > targetHeight()
			   && width < WIDTH_MAX){
			width += 20;
			titlebar.setRect(0, 0, width, 0);
			text.setPos( titlebar.left(), titlebar.bottom() + 2*GAP );
			text.maxWidth(width);
		}

		bringToFront(titlebar);

		resize( width, (int)text.bottom() + 2 );
	}

	protected boolean useHighlighting(){
		return true;
	}

	protected float targetHeight() {
		return PixelScene.MIN_HEIGHT_L - 10;
	}
}
