

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.三重爪击;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.攻击力;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.新月护卫;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.无畏冲锋;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.果决攻击;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.生命力;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.防御力;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.风斩电刺;
import com.shatteredpixel.shatteredpixeldungeon.items.技能书;
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
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Wnd选择技能 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	static Wnd选择技能 INSTANCE;


	public Wnd选择技能(技能书 i,Hero hero){
		super();
		INSTANCE=this;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一个技能",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		Wand 技能1s;
		Wand 技能2s;
		Wand 技能3s;

		ArrayList<Wand> items = new ArrayList<>();
		items.add(new 生命力());
		items.add(new 防御力());
		items.add(new 攻击力());
		items.add(new 果决攻击());
		items.add(new 三重爪击());
		items.add(new 风斩电刺());
		items.add(new 无畏冲锋());
		items.add(new 新月护卫());
		do
		{
			技能1s=Random.element(items);
		}while(hero.belongings.hasItem(技能1s.getClass())&&hero.belongings.getItem(技能1s.getClass()).等级()>3);
		do
		{
			技能2s=Random.element(items);
		}while((hero.belongings.hasItem(技能2s.getClass())&&hero.belongings.getItem(技能2s.getClass()).等级()>3)||
			   技能1s==技能2s);

		do
		{
			技能3s=Random.element(items);
		}while((hero.belongings.hasItem(技能3s.getClass())&&hero.belongings.getItem(技能3s.getClass()).等级()>3)||
			   (技能1s==技能3s||技能2s==技能3s));

		Item final技能1s=技能1s;
		RedButton btnCls = new RedButton(技能1s.name(),6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
											  Messages.titleCase("选择技能"),
											  "你确定选择这个技能？",
											  "是",
											  "否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							if(hero.belongings.hasItem(final技能1s.getClass())){
								hero.belongings.getItem(final技能1s.getClass()).升级();
							}else
							final技能1s.放背包();
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

		Wand final技能1s1=技能1s;
		IconButton clsInfo = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.技能书),final技能1s1.name(),final技能1s1.statsDesc()));
			}
		};
		clsInfo.setRect(width-20, btnCls.top() + (btnCls.height()-20)/2, 20, 20);
		add(clsInfo);

		pos = btnCls.bottom() + MARGIN;

		Item final技能2s=技能2s;
		RedButton btnCls2 = new RedButton(技能2s.name(),6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
						Messages.titleCase("选择技能"),
						"你确定选择这个技能？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							if(hero.belongings.hasItem(final技能2s.getClass())){
								hero.belongings.getItem(final技能2s.getClass()).升级();
							}else
								final技能2s.放背包();
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

		Wand final技能2s1=技能2s;
		IconButton clsInfo2 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.技能书),final技能2s1.name(),final技能2s1.statsDesc()));
			}
		};
		clsInfo2.setRect(width-20, btnCls2.top() + (btnCls2.height()-20)/2, 20, 20);
		add(clsInfo2);
		pos = btnCls2.bottom() + MARGIN;


		Item final技能3s=技能3s;
		RedButton btnCls3 = new RedButton(技能3s.name(),6 ) {
			@Override
			protected void onClick() {
				GameScene.show(new WndOptions(
						Messages.titleCase("选择技能"),
						"你确定选择这个技能？",
						"是",
						"否"){
					@Override
					protected void onSelect(int index) {
						hide();
						if (index == 0){
							INSTANCE.hide();
							if(hero.belongings.hasItem(final技能3s.getClass())){
								hero.belongings.getItem(final技能3s.getClass()).升级();
							}else
								final技能3s.放背包();
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

		Wand final技能3s1=技能3s;
		IconButton clsInfo3 = new IconButton(Icons.get(Icons.INFO)){
			@Override
			protected void onClick() {
				GameScene.show(new WndTitledMessage(new ItemSprite(物品表.技能书),final技能3s1.name(),final技能3s1.statsDesc()));
			}
		};
		clsInfo3.setRect(width-20, btnCls3.top() + (btnCls3.height()-20)/2, 20, 20);
		add(clsInfo3);
		pos = btnCls3.bottom() + MARGIN;

		RedButton 刷新=new RedButton("刷新/重新获得(剩余"+i.使用上限()+"次)",6){
			@Override
			protected void onClick(){
				super.onClick();
				技能书 item=new 技能书();
				item.用过+=i.用过+1;
				item.放背包();

				hide();
			}
		};
		刷新.leftJustify=true;
		刷新.multiline=true;
		刷新.setSize(width,刷新.reqHeight());
		刷新.setRect(0,pos,width,刷新.reqHeight()+6);
		if(i.使用上限()>0){
			add(刷新);
			pos=刷新.bottom()+MARGIN;
		}

		RedButton 都要=new RedButton("我全都要(剩余"+i.都要上限()+"次)",6){
			@Override
			protected void onClick(){
				super.onClick();

				if(hero.belongings.hasItem(final技能1s.getClass())){
					hero.belongings.getItem(final技能1s.getClass()).升级();
				}else
					final技能1s.放背包();

				if(hero.belongings.hasItem(final技能2s.getClass())){
					hero.belongings.getItem(final技能2s.getClass()).升级();
				}else
					final技能2s.放背包();

				if(hero.belongings.hasItem(final技能3s.getClass())){
					hero.belongings.getItem(final技能3s.getClass()).升级();
				}else
					final技能3s.放背包();

				hide();
			}
		};
		都要.leftJustify=true;
		都要.multiline=true;
		都要.setSize(width,都要.reqHeight());
		都要.setRect(0,pos,width,都要.reqHeight()+6);
		if(i.都要<=i.都要上限()){
			add(都要);
			pos=都要.bottom()+MARGIN;
		}

		resize(width, (int)pos);

	}
	@Override
	public void onBackPressed() {

	}
}
