package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Image;

public class 赞助 extends Window{


	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	static  赞助 INSTANCE;

	public 赞助(){
		super();
		INSTANCE=this;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;

		Image background = new Image(Assets.赞助);
		background.scale.set(0.09f);
		background.x=(width-background.width())/2;
		background.y=pos;
		add(background);
		pos=background.height()+MARGIN+3;


		RedButton 确定=new RedButton("自行截图二维码并微信扫码",6){
			@Override
			protected void onClick(){
				super.onClick();

				hide();
			}
		};
		确定.leftJustify=true;
		确定.multiline=true;
		确定.setSize(width,确定.reqHeight());
		确定.setRect(0,pos,width*4/5f,确定.reqHeight()+6);
		add(确定);

		RedButton 取消=new RedButton("取消",6){
			@Override
			protected void onClick(){
				super.onClick();

				hide();
			}
		};
		取消.leftJustify=true;
		取消.multiline=true;
		取消.setSize(width,取消.reqHeight());
		取消.setRect(确定.width()+1,pos,width*1f/5f,取消.reqHeight()+6);
		add(取消);
		pos=取消.bottom()+MARGIN;

		resize(width, (int)pos);

	}
	@Override
	public void onBackPressed() {

	}
}
