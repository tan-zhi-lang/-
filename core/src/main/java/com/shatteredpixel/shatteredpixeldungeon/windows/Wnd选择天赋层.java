

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class Wnd选择天赋层 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;

	public Wnd选择天赋层(Hero hero){
		super();

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一层天赋使其天赋点数+1",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;
		
		RedButton moveBtn=new RedButton("1层天赋"){
			@Override
			protected void onClick(){
				super.onClick();
				hero.天赋[1]=true;
				hide();
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;
		
		
		RedButton moveBtn2=new RedButton("2层天赋"){
			@Override
			protected void onClick(){
				super.onClick();
				hero.天赋[2]=true;
				hide();
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;
		
		
		RedButton moveBtn3=new RedButton("3层天赋"){
			@Override
			protected void onClick(){
				super.onClick();
				hero.天赋[3]=true;
				hide();
			}
		};
		moveBtn3.leftJustify=true;
		moveBtn3.multiline=true;
		moveBtn3.setSize(width,moveBtn3.reqHeight());
		moveBtn3.setRect(0,pos,width,moveBtn3.reqHeight()+6);
		add(moveBtn3);
		pos=moveBtn3.bottom()+MARGIN;
		
		resize(width, (int)pos);

	}
	@Override
	public void onBackPressed() {

	}
	
}
