

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class Wnd选择海克斯 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;

	public Wnd选择海克斯(海克斯宝典 i,Hero hero){
		super();

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一个海克斯\n不要退出，你会后悔的",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;

		String 海克斯1s;
		String 海克斯2s;
		String 海克斯3s;

		// 获取所有键并转换为列表
		List<String> keysList = new ArrayList<>(hero.海克斯.keySet());

		// 使用Random类生成随机索引
		Random random = new Random();
		List<String> selectedKeys = new ArrayList<>();

		// 生成3个不同的随机索引
		while (selectedKeys.size() < 3) {
			int index = random.Int(keysList.size());
			String key = keysList.get(index);
			if (!selectedKeys.contains(key)) {
				selectedKeys.add(key);
			}
		}

		海克斯1s=selectedKeys.get(0);
		海克斯2s=selectedKeys.get(1);
		海克斯3s=selectedKeys.get(2);

		String
				final海克斯1s=
				海克斯1s;
		RedButton moveBtn=new RedButton(final海克斯1s+hero.海克斯(final海克斯1s)){
			@Override
			protected void onClick(){
				super.onClick();

				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				hero.海克斯.put(final海克斯1s,true);
				hero.海克斯触发(final海克斯1s);
				hide();
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;


		String
				final海克斯2s=
				海克斯2s;
		RedButton moveBtn2=new RedButton(final海克斯2s+hero.海克斯(final海克斯2s)){
			@Override
			protected void onClick(){
				super.onClick();
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				hero.海克斯.put(final海克斯2s,true);
				hero.海克斯触发(final海克斯2s);
				hide();
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;


		String
				final海克斯3s=
				海克斯3s;
		RedButton moveBtn3=new RedButton(final海克斯3s+hero.海克斯(final海克斯3s)){
			@Override
			protected void onClick(){
				super.onClick();
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				hero.海克斯.put(final海克斯3s,true);
				hero.海克斯触发(final海克斯3s);
				hide();
			}
		};
		moveBtn3.leftJustify=true;
		moveBtn3.multiline=true;
		moveBtn3.setSize(width,moveBtn3.reqHeight());
		moveBtn3.setRect(0,pos,width,moveBtn3.reqHeight()+6);
		add(moveBtn3);
		pos=moveBtn3.bottom()+MARGIN;

		boolean 海克斯刷新=true;
		RedButton 刷新=new RedButton("重新获得一个海克斯法典(一次性，相当于刷新)"){
			@Override
			protected void onClick(){
				super.onClick();
				海克斯宝典 item=new 海克斯宝典();
				item.用过=true;
				item.放背包();

				hide();
			}
		};
		刷新.leftJustify=true;
		刷新.multiline=true;
		刷新.setSize(width,刷新.reqHeight());
		刷新.setRect(0,pos,width,刷新.reqHeight()+6);
		if(海克斯刷新&&!i.用过){
			add(刷新);
			pos=刷新.bottom()+MARGIN;
		}

		resize(width, (int)pos);

	}
	
}
