

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class Wnd选择天赋层 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;

	public Wnd选择天赋层(){
		super();

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一层天赋使其天赋点数+1", 5);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		for(int x=1;x<=3;x++){
			RedButton moveBtn=new RedButton(x+"层天赋",9){
				@Override
				protected void onClick(){
					super.onClick();
					Dungeon.hero.天赋[(int)x]=true;
					hide();
				}
			};
			moveBtn.leftJustify=true;
			moveBtn.multiline=true;
			moveBtn.setSize(width,moveBtn.reqHeight());
			moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
			add(moveBtn);
			pos=moveBtn.bottom()+MARGIN;
		}
			
		resize(width, (int)pos);

	}


}
