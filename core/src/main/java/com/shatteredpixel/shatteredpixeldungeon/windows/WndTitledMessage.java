

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock2;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

public class WndTitledMessage extends Window {

	protected static final int WIDTH_MIN    = 120;
	protected static final int WIDTH_MAX    = 220;
	protected static final int GAP	= 2;

	public WndTitledMessage( Image icon, String title, String message ) {
		
		this( new IconTitle( icon, title ), message );

	}
	
	public WndTitledMessage( Component titlebar, String message ) {

		this( titlebar, null, message );

	}

	//metaMessage非空时作为独立文本块显示在message之前
	public WndTitledMessage( Component titlebar, String metaMessage, String message ) {

		super();

		int width = WIDTH_MIN;

		titlebar.setRect( 0, 0, width, 0 );
		add(titlebar);

		RenderedTextBlock2 meta = null;
		ColorBlock metaBg = null;
		if (metaMessage != null && !metaMessage.isEmpty()){
			meta = PixelScene.renderTextBlock2( 6 );
			if (!useHighlighting()) meta.setHightlighting(false);
			meta.text( metaMessage, width );
			meta.setPos( titlebar.left(), titlebar.bottom() + 2*GAP );
			//半透明底色框住元信息块（同ResistanceIndicator），先加底色再加文字保证顺序
			metaBg = new ColorBlock(meta.width() + 2f, meta.height() + 2f, 0xd680876f);
			metaBg.x = meta.left() - 1f;
			metaBg.y = meta.top() - 1f;
			add( metaBg );
			add( meta );
		}

		RenderedTextBlock2 text = PixelScene.renderTextBlock2( 6 );
		if (!useHighlighting()) text.setHightlighting(false);
		text.text( message, width );
		text.setPos( titlebar.left(), (meta != null ? meta.bottom() : titlebar.bottom()) + 2*GAP );
		add( text );

		while (PixelScene.横屏()
				&& text.bottom() > targetHeight()
				&& width < WIDTH_MAX){
			width += 20;
			titlebar.setRect(0, 0, width, 0);
			if (meta != null){
				meta.maxWidth(width);
				meta.setPos( titlebar.left(), titlebar.bottom() + 2*GAP );
				metaBg.size( meta.width() + 2f, meta.height() + 2f );
				metaBg.x = meta.left() - 1f;
				metaBg.y = meta.top() - 1f;
			}
			text.setPos( titlebar.left(), (meta != null ? meta.bottom() : titlebar.bottom()) + 2*GAP );
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
