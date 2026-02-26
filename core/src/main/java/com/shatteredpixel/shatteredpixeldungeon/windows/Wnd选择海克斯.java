

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;
import java.util.List;

public class Wnd选择海克斯 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	static  Wnd选择海克斯 INSTANCE;

	public void 数据收集(String s) {

	}


	public Wnd选择海克斯(海克斯宝典 i,Hero hero){
		super();
		INSTANCE=this;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一个海克斯",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		String 海克斯1s;
		String 海克斯2s;
		String 海克斯3s;
		String 海克斯1;
		String 海克斯2;
		String 海克斯3;

		List<String> selectedKeys = new ArrayList<>();
		selectedKeys.addAll(hero.随机海克斯(3));

		海克斯1s=selectedKeys.get(0);
		海克斯1=hero.海克斯级(海克斯1s)+"级:";

		海克斯2s=selectedKeys.get(1);
		海克斯2=hero.海克斯级(海克斯2s)+"级:";

		海克斯3s=selectedKeys.get(2);
		海克斯3=hero.海克斯级(海克斯3s)+"级:";

		String final海克斯1s=海克斯1s;

		RedButton btnCls = new RedButton( 海克斯1+final海克斯1s, 6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
											  Messages.titleCase("选择海克斯"),
											  "你确定选择这个海克斯？",
											  "是",
											  "否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							hero.选择海克斯(final海克斯1s);
							数据收集(final海克斯1s);
						}
					}
				});
			}
		};
		btnCls.leftJustify = true;
		btnCls.multiline = true;
		btnCls.text.setSize(1.5f,1.5f);
		btnCls.setSize(width-20, btnCls.reqHeight()+2);
		btnCls.setRect( 0, pos, width-20, btnCls.reqHeight()+6);
		add( btnCls );

		IconButton clsInfo = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯宝典),final海克斯1s,hero.海克斯描述(final海克斯1s)));
			}
		};
		clsInfo.setRect(width-20, btnCls.top() + (btnCls.height()-20)/2, 20, 20);
		add(clsInfo);

		pos = btnCls.bottom() + MARGIN;

		String final海克斯2s= 海克斯2s;

		RedButton btnCls2 = new RedButton( 海克斯2+final海克斯2s, 6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
						Messages.titleCase("选择海克斯"),
						"你确定选择这个海克斯？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							hero.选择海克斯(final海克斯2s);
							数据收集(final海克斯2s);
						}
					}
				});
			}
		};
		btnCls2.leftJustify = true;
		btnCls2.multiline = true;
		btnCls2.text.setSize(1.5f,1.5f);
		btnCls2.setSize(width-20, btnCls2.reqHeight()+2);
		btnCls2.setRect( 0, pos, width-20, btnCls2.reqHeight()+6);
		add( btnCls2 );

		IconButton clsInfo2 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯宝典),final海克斯2s,hero.海克斯描述(final海克斯2s)));
			}
		};
		clsInfo2.setRect(width-20, btnCls2.top() + (btnCls2.height()-20)/2, 20, 20);
		add(clsInfo2);
		pos = btnCls2.bottom() + MARGIN;

		String final海克斯3s=海克斯3s;
		RedButton btnCls3 = new RedButton( 海克斯3+final海克斯3s, 6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
						Messages.titleCase("选择海克斯"),
						"你确定选择这个海克斯？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							hero.选择海克斯(final海克斯3s);
							数据收集(final海克斯3s);
						}
					}
				});
			}
		};
		btnCls3.leftJustify = true;
		btnCls3.multiline = true;
		btnCls3.text.setSize(1.5f,1.5f);
		btnCls3.setSize(width-20, btnCls3.reqHeight()+2);
		btnCls3.setRect( 0, pos, width-20, btnCls3.reqHeight()+6);
		add( btnCls3 );

		IconButton clsInfo3 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯宝典),final海克斯3s,hero.海克斯描述(final海克斯3s)));
			}
		};
		clsInfo3.setRect(width-20, btnCls3.top() + (btnCls3.height()-20)/2, 20, 20);
		add(clsInfo3);
		pos = btnCls3.bottom() + MARGIN;

		RedButton 刷新=new RedButton("重新获得一个海克斯法典(初始可以刷新2次)",6){
			@Override
			protected void onClick(){
				super.onClick();
				Sample.INSTANCE.play(Assets.Sounds.海克斯,1.75f);
				海克斯宝典 item=new 海克斯宝典();
				item.用过+=i.用过+1;
				item.放背包();

				hide();
			}
		};
		刷新.leftJustify=true;
		刷新.multiline=true;
		刷新.setSize(width,刷新.reqHeight());
		刷新.setRect(0,pos,width,刷新.reqHeight()+6);
		if(i.用过<i.使用上限()){
			if(!hero.符文("无法刷新海克斯")){
				add(刷新);
				pos=刷新.bottom()+MARGIN;
			}
		}

		resize(width, (int)pos);

	}
	@Override
	public void onBackPressed() {

	}
}
