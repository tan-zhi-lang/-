

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯秘卷;
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

public class Wnd选择海克斯 extends Window {

	private static final int WIDTH_P = 125;//120
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	static  Wnd选择海克斯 INSTANCE;

	public Wnd选择海克斯(海克斯秘卷 i,Hero hero){
		this(i,"","","",hero);
	}
	public Wnd选择海克斯(海克斯秘卷 i,String s1,String s2,String s3,Hero hero){
		super();
		INSTANCE=this;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;
		float 刷新x=-32;
		float 刷新y=3;
		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一个海克斯",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		String 海克斯1=s1;
		String 海克斯2=s2;
		String 海克斯3=s3;

		String 海克斯1s;
		String 海克斯2s;
		String 海克斯3s;

		if(s1.equals("")){
			海克斯1=hero.随机海克斯();
			if(hero.符文("畸变选择"))
				海克斯1=hero.选择随机海克斯1();
		}

		海克斯1s=hero.海克斯级(海克斯1)+":";
		if(s2.equals(""))
		do
		{
				海克斯2=hero.随机海克斯();
			if(hero.符文("畸变选择"))
				海克斯2=hero.选择随机海克斯2();
		}while(海克斯1.equals(海克斯2));
		海克斯2s=hero.海克斯级(海克斯2)+":";

		if(s3.equals(""))
		do
		{
				海克斯3=hero.随机海克斯();
				if(hero.符文("畸变选择"))
					海克斯3=hero.选择随机海克斯3();
		}while(海克斯1.equals(海克斯3)||海克斯2.equals(海克斯3));

		海克斯3s=hero.海克斯级(海克斯3)+":";

		String final海克斯1=海克斯1;
		String final海克斯2=海克斯2;
		String final海克斯3=海克斯3;

		RedButton btnCls = new RedButton(海克斯1s+海克斯1,6 ) {
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
							hero.选择海克斯(final海克斯1);

							hero.删除海克斯(final海克斯2);
							hero.删除海克斯(final海克斯3);
							if(hero.符文("我全都要")){
								hero.选择海克斯(final海克斯2);
								hero.选择海克斯(final海克斯3);
							}
						}
					}
				});
			}
		};
		btnCls.leftJustify = true;
		btnCls.multiline = true;
		btnCls.text.setSize(1.5f,1.5f);
		btnCls.setSize(width-20, btnCls.reqHeight()+2);
		btnCls.setRect( 0, pos, width-20-14, btnCls.reqHeight()+6);
		add( btnCls );

		String final海克斯1s=海克斯1;
		IconButton clsInfo = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯秘卷),final海克斯1,hero.海克斯描述(final海克斯1)));
			}
		};
		clsInfo.setRect(width-20+3,btnCls.top()+ (btnCls.height()-20)/2,20,20);
		add(clsInfo);

		pos = btnCls.bottom() + MARGIN;


		RedButton 刷新1=new RedButton(i.使用1()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();

				hero.删除海克斯(final海克斯1);
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				海克斯秘卷 item=new 海克斯秘卷(false);
				item.用过1+=i.用过1+1;
				item.用过2+=i.用过2;
				item.用过3+=i.用过3;
				GameScene.show(new Wnd选择海克斯(item,"",final海克斯2,final海克斯3,hero));

				hide();
			}
		};
		刷新1.setRect(width+刷新x,btnCls.top()+(btnCls.height()-20)/2+刷新y,16,14);
		if(i.使用1()<=0||hero.符文("无法刷新海克斯")){
			刷新1.enable(false);
		}
		add(刷新1);


		RedButton btnCls2 = new RedButton(海克斯2s+海克斯2,6 ) {
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
							hero.选择海克斯(final海克斯2);

							hero.删除海克斯(final海克斯1);
							hero.删除海克斯(final海克斯3);
							if(hero.符文("我全都要")){
								hero.选择海克斯(final海克斯1);
								hero.选择海克斯(final海克斯3);
							}
						}
					}
				});
			}
		};
		btnCls2.leftJustify = true;
		btnCls2.multiline = true;
		btnCls2.text.setSize(1.5f,1.5f);
		btnCls2.setSize(width-20, btnCls2.reqHeight()+2);
		btnCls2.setRect( 0, pos, width-20-14, btnCls2.reqHeight()+6);
		add( btnCls2 );

		String final海克斯2s=海克斯2;
		IconButton clsInfo2 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯秘卷),final海克斯2,hero.海克斯描述(final海克斯2)));
			}
		};
		clsInfo2.setRect(width-20+3, btnCls2.top() + (btnCls2.height()-20)/2, 20, 20);
		add(clsInfo2);
		pos = btnCls2.bottom() + MARGIN;


		RedButton 刷新2=new RedButton(i.使用2()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();

				hero.删除海克斯(final海克斯2);
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				海克斯秘卷 item=new 海克斯秘卷(false);
				item.用过1+=i.用过1;
				item.用过2+=i.用过2+1;
				item.用过3+=i.用过3;
				GameScene.show(new Wnd选择海克斯(item,final海克斯1,"",final海克斯3,hero));

				hide();
			}
		};
		刷新2.setRect(width+刷新x,btnCls2.top()+(btnCls2.height()-20)/2+刷新y,16,14);
		if(i.使用2()<=0||hero.符文("无法刷新海克斯")){
			刷新2.enable(false);
		}
		add(刷新2);


		RedButton btnCls3 = new RedButton(海克斯3s+海克斯3,6 ) {
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
							hero.选择海克斯(final海克斯3);

							hero.删除海克斯(final海克斯1);
							hero.删除海克斯(final海克斯2);
							if(hero.符文("我全都要")){
								hero.选择海克斯(final海克斯1);
								hero.选择海克斯(final海克斯2);
							}
						}
					}
				});
			}
		};
		btnCls3.leftJustify = true;
		btnCls3.multiline = true;
		btnCls3.text.setSize(1.5f,1.5f);
		btnCls3.setSize(width-20, btnCls3.reqHeight()+2);
		btnCls3.setRect( 0, pos, width-20-14, btnCls3.reqHeight()+6);
		add( btnCls3 );

		String final海克斯3s=海克斯3;
		IconButton clsInfo3 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯秘卷),final海克斯3,hero.海克斯描述(final海克斯3)));
			}
		};
		clsInfo3.setRect(width-20+3,btnCls3.top()+ (btnCls3.height()-20)/2,20,20);
		add(clsInfo3);
		pos = btnCls3.bottom() + MARGIN;


		RedButton 刷新3=new RedButton(i.使用3()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();

				hero.删除海克斯(final海克斯3);
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				海克斯秘卷 item=new 海克斯秘卷(false);
				item.用过1+=i.用过1;
				item.用过2+=i.用过2;
				item.用过3+=i.用过3+1;
				GameScene.show(new Wnd选择海克斯(item,final海克斯1,final海克斯2,"",hero));

				hide();
			}
		};
		刷新3.setRect(width+刷新x,btnCls3.top()+(btnCls3.height()-20)/2+刷新y,16,14);
		if(i.使用3()<=0||hero.符文("无法刷新海克斯")){
			刷新3.enable(false);
		}
		add(刷新3);


		resize(width, (int)pos);

	}

	public Wnd选择海克斯(海克斯卷轴 i,Hero hero){
		this(i,"","",hero);
	}
	public Wnd选择海克斯(海克斯卷轴 i,String s1,String s2,Hero hero){
		super();
		INSTANCE=this;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;
		float 刷新x=-32;
		float 刷新y=3;
		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一个海克斯",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		String 海克斯1=s1;
		String 海克斯2=s2;

		String 海克斯1s;
		String 海克斯2s;

		if(s1.equals("")){
			海克斯1=hero.随机海克斯();
			if(hero.符文("畸变选择"))
				海克斯1=hero.选择随机海克斯1();
		}

		海克斯1s=hero.海克斯级(海克斯1)+":";
		if(s2.equals(""))
		do
		{
				海克斯2=hero.随机海克斯();
			if(hero.符文("畸变选择"))
				海克斯2=hero.选择随机海克斯2();
		}while(海克斯1.equals(海克斯2));
		海克斯2s=hero.海克斯级(海克斯2)+":";

		String final海克斯1=海克斯1;
		String final海克斯2=海克斯2;

		RedButton btnCls = new RedButton(海克斯1s+海克斯1,6 ) {
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
							hero.选择海克斯(final海克斯1);

							hero.删除海克斯(final海克斯2);
							if(hero.符文("我全都要")){
								hero.选择海克斯(final海克斯2);
							}
						}
					}
				});
			}
		};
		btnCls.leftJustify = true;
		btnCls.multiline = true;
		btnCls.text.setSize(1.5f,1.5f);
		btnCls.setSize(width-20, btnCls.reqHeight()+2);
		btnCls.setRect( 0, pos, width-20-14, btnCls.reqHeight()+6);
		add( btnCls );

		String final海克斯1s=海克斯1;
		IconButton clsInfo = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯秘卷),final海克斯1,hero.海克斯描述(final海克斯1)));
			}
		};
		clsInfo.setRect(width-20+3,btnCls.top()+ (btnCls.height()-20)/2,20,20);
		add(clsInfo);

		pos = btnCls.bottom() + MARGIN;


		RedButton 刷新1=new RedButton(i.使用1()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();

				hero.删除海克斯(final海克斯1);
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				海克斯卷轴 item=new 海克斯卷轴(false);
				item.用过1+=i.用过1+1;
				item.用过2+=i.用过2;
				GameScene.show(new Wnd选择海克斯(item,"",final海克斯2,hero));

				hide();
			}
		};
		刷新1.setRect(width+刷新x,btnCls.top()+(btnCls.height()-20)/2+刷新y,16,14);
		if(i.使用1()<=0||hero.符文("无法刷新海克斯")){
			刷新1.enable(false);
		}
		add(刷新1);


		RedButton btnCls2 = new RedButton(海克斯2s+海克斯2,6 ) {
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
							hero.选择海克斯(final海克斯2);

							hero.删除海克斯(final海克斯1);
							if(hero.符文("我全都要")){
								hero.选择海克斯(final海克斯1);
							}
						}
					}
				});
			}
		};
		btnCls2.leftJustify = true;
		btnCls2.multiline = true;
		btnCls2.text.setSize(1.5f,1.5f);
		btnCls2.setSize(width-20, btnCls2.reqHeight()+2);
		btnCls2.setRect( 0, pos, width-20-14, btnCls2.reqHeight()+6);
		add( btnCls2 );

		String final海克斯2s=海克斯2;
		IconButton clsInfo2 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.海克斯秘卷),final海克斯2,hero.海克斯描述(final海克斯2)));
			}
		};
		clsInfo2.setRect(width-20+3, btnCls2.top() + (btnCls2.height()-20)/2, 20, 20);
		add(clsInfo2);
		pos = btnCls2.bottom() + MARGIN;


		RedButton 刷新2=new RedButton(i.使用2()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();

				hero.删除海克斯(final海克斯2);
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				海克斯卷轴 item=new 海克斯卷轴(false);
				item.用过1+=i.用过1;
				item.用过2+=i.用过2+1;
				GameScene.show(new Wnd选择海克斯(item,final海克斯1,"",hero));

				hide();
			}
		};
		刷新2.setRect(width+刷新x,btnCls2.top()+(btnCls2.height()-20)/2+刷新y,16,14);
		if(i.使用2()<=0||hero.符文("无法刷新海克斯")){
			刷新2.enable(false);
		}
		add(刷新2);

		resize(width, (int)pos);

	}

	@Override
	public void onBackPressed() {

	}
}
