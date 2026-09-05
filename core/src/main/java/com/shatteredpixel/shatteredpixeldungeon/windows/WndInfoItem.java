

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock2;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.ColorBlock;

public class WndInfoItem extends Window {
	
	private static final float GAP	= 2;

	private static final int WIDTH_MIN = 120;
	private static final int WIDTH_MAX = 220;

	//only one WndInfoItem can appear at a time
	private static WndInfoItem INSTANCE;

	public WndInfoItem( Heap heap ) {

		super();

		if (INSTANCE != null){
			INSTANCE.hide();
		}
		INSTANCE = this;

		if (heap.type == Heap.Type.HEAP) {
			fillFields( heap.peek() );

		} else {
			fillFields( heap );

		}
	}
	
	public WndInfoItem( Item item ) {
		super();

		if (INSTANCE != null){
			INSTANCE.hide();
		}
		INSTANCE = this;
		
		fillFields( item );
	}

	@Override
	public void hide() {
		super.hide();
		if (INSTANCE == this){
			INSTANCE = null;
		}
	}

	private void fillFields(Heap heap ) {

		IconTitle titlebar = new IconTitle( heap );
//		titlebar.color( TITLE_COLOR );

		RenderedTextBlock2 txtInfo = PixelScene.renderTextBlock2( heap.info(), 6 );

		layoutFields(titlebar, null, txtInfo);
	}

	private void fillFields( Item item ) {

//		int color = TITLE_COLOR;
//		if (item.levelKnown && item.等级() > 0) {
//			color = ItemSlot.UPGRADED;
//		} else if (item.levelKnown && item.等级() < 0) {
//			color = ItemSlot.DEGRADED;
//		}

		IconTitle titlebar = new IconTitle( item );
//		titlebar.color( color );
//		if(item.绿色)titlebar.color(0x44FF44);
//		else if(item.cursed&&item.cursedKnown){
//			titlebar.color( 0xFF4444 );//诅咒红文本
//		}else {
//			titlebar.color(0xFFFFFF);
//		}

		//物品元信息（备注/代码名/价值/分类）独立成块，像ResistanceIndicator一样单独渲染，显示在描述之前
		RenderedTextBlock2 txtMeta = PixelScene.renderTextBlock2( item.详细信息(), 6 );
		RenderedTextBlock2 txtInfo = PixelScene.renderTextBlock2( item.info()+item.扔出信息(), 6 );

		layoutFields(titlebar, txtMeta, txtInfo);
	}

	private void layoutFields(IconTitle title, RenderedTextBlock2 meta, RenderedTextBlock2 info){
		int width = WIDTH_MIN;

		if (meta != null) meta.maxWidth(width);
		info.maxWidth(width);

		//window can go out of the screen on landscape, so widen it as appropriate
		while (PixelScene.横屏()
				&& (meta == null ? 0 : meta.height()) + info.height() > 100
				&& width < WIDTH_MAX){
			width += 20;
			if (meta != null) meta.maxWidth(width);
			info.maxWidth(width);
		}

		//leaves some space to add the journal button in WndUseItem. This is messy I know.
		if (this instanceof WndUseItem){
			title.setRect( 0, 0, width-16, 0 );
		} else {
			title.setRect( 0, 0, width, 0 );
		}
		add( title );

		float bottom = title.bottom() + GAP;
		if (meta != null) {
			meta.setPos(title.left(), bottom);
			//半透明底色框住元信息块（同ResistanceIndicator），先加底色再加文字保证顺序
			ColorBlock bg = new ColorBlock(meta.width() + 2f, meta.height() + 2f, 0xd680876f);
			bg.x = meta.left() - 1f;
			bg.y = meta.top() - 1f;
			add( bg );
			add( meta );
			bottom = meta.bottom() + GAP;
		}

		info.setPos(title.left(), bottom);
		add( info );

		resize( width, (int)(info.bottom() + 2) );
	}
}
