

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ItemButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class Wnd选择物品 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int WIDTH		= 120;
	private static final int BTN_SIZE	= 32;
	private static final int BTN_GAP	= 5;
	private static final int GAP		= 2;

	private static final int MARGIN  = 2;
	Hero hero;
	Item i1;
	Item i2;
	Item i3;
	public Wnd选择物品(Hero hero,Item i1,Item i2,Item i3){
		super();
		this.hero=hero;
		this.i1=i1;
		this.i2=i2;
		this.i2=i3;
		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择物品",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		ItemButton btnItem1= new ItemButton(){
			@Override
			protected void onClick() {
				i1.放背包();
				hide();
			}
		};
		btnItem1.item(i1);
		btnItem1.setRect((WIDTH-BTN_GAP)/3-BTN_SIZE,title.bottom() + 3*MARGIN,BTN_SIZE,BTN_SIZE);
		add(btnItem1);
		pos=btnItem1.bottom()+MARGIN;

		ItemButton btnItem2= new ItemButton(){
			@Override
			protected void onClick() {
				i2.放背包();
				hide();
			}
		};
		btnItem2.item(i2);
		btnItem2.setRect((WIDTH-BTN_GAP)/3*2-BTN_SIZE,title.bottom() + 3*MARGIN,BTN_SIZE,BTN_SIZE);
		add(btnItem2);
		pos=btnItem2.bottom()+MARGIN;

		ItemButton btnItem3= new ItemButton(){
			@Override
			protected void onClick() {
				i3.放背包();
				hide();
			}
		};
		btnItem3.item(i3);
		btnItem3.setRect((WIDTH-BTN_GAP)-BTN_SIZE,title.bottom() + 3*MARGIN,BTN_SIZE,BTN_SIZE);
		add(btnItem3);
		pos=btnItem3.bottom()+MARGIN;

		resize(width, (int)pos);

	}

	@Override
	public void onBackPressed() {

	}
}
