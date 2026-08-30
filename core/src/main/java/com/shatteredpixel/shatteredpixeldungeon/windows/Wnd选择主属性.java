

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class Wnd选择主属性 extends Window {

	private static final int WIDTH_P = 120;

	private static final int MARGIN  = 2;

	static  Wnd选择主属性 INSTANCE;
	public Wnd选择主属性(Hero hero){
		super();

		INSTANCE=this;

		int width =  WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择提升的属性",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;
		
		RedButton moveBtn=new RedButton("力量+1"){
			@Override
			protected void onClick(){
				super.onClick();

				GameScene.show(new WndOptions(
						Messages.titleCase("选择属性"),
						"你确定选择这个属性？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){

							hero.力量+=(算法.isDebug()?10:1);

							INSTANCE.hide();
						}
					}
				});
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;
		
		
		RedButton moveBtn2=new RedButton("敏捷+1"){
			@Override
			protected void onClick(){
				super.onClick();

				GameScene.show(new WndOptions(
						Messages.titleCase("选择属性"),
						"你确定选择这个属性？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){

							hero.敏捷+=(算法.isDebug()?10:1);

							INSTANCE.hide();
						}
					}
				});
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;

		RedButton moveBtn3=new RedButton("魔力+1"){
			@Override
			protected void onClick(){
				super.onClick();

				GameScene.show(new WndOptions(
						Messages.titleCase("选择属性"),
						"你确定选择这个属性？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){

							hero.魔力+=(算法.isDebug()?10:1);

							INSTANCE.hide();
						}
					}
				});
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
