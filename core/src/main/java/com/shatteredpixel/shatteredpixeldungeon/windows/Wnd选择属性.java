

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.属性锻造器;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.utils.Random;

public class Wnd选择属性 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	public float 属性(String s){

		switch(s){
			case "最大攻击":
				return Random.Float(5,25);
			case "最大防御":
				return Random.Float(2,10);
			case "生命":
				return Random.Float(5,25);
			case "力量":
				return Random.Float(1,5);
			case "攻速":
				return Random.Float(0.075f,0.15f);
			case "移速":
				return Random.Float(0.075f,0.15f);
			case "最大命中":
				return Random.Float(2,10);
			case "最大闪避":
				return Random.Float(2,10);
			case "暴击伤害":
				return Random.Float(0.05f,0.1f);
			case "全能吸血":
				return Random.Float(0.05f,0.1f);
			case "暴击率":
				return Random.Float(0.05f,0.1f);
			case "穿甲":
				return Random.Float(2,10);
			case "治疗效果":
				return Random.Float(0.05f,0.1f);
			case "护甲穿透":
				return Random.Float(0.05f,0.1f);
		}

		return 0;
	}
	public String 随机属性(){
		return Random.oneOf(
				"最大攻击","最大防御",
				"最大攻击","最大防御",
				"最大攻击","最大防御",
				"最大攻击","最大防御",

							"生命","力量",
							"生命","力量",
							"生命","力量",
							"攻速","移速",
							"攻速","移速",
							"攻速","移速",

							"最大命中","最大闪避",
							"最大命中","最大闪避",
							"最大命中","最大闪避",
							"最大命中","最大闪避",

							"暴击伤害","全能吸血",

							"暴击率","穿甲",
							"暴击率","穿甲",

							"治疗效果","护甲穿透",
							"治疗效果","护甲穿透"
				);
	}
	public boolean 百分比(String s){
		return s.equals("攻速")||s.equals("移速")||s.equals("暴击伤害")||s.equals("全能吸血")||s.equals("暴击率")||s.equals("护甲穿透")||s.equals("治疗效果");
	}
	public Wnd选择属性(属性锻造器 i,Hero hero){
		super();

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一项属性",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		String rx1="";
		float rxv1=0;
		rx1=随机属性();
		rxv1=属性(rx1);

		String rx2="";
		float rxv2=0;
		do
		{
			rx2=随机属性();
			rxv2=属性(rx2);
		}while(rx2.equals(rx1));


		String rx3="";
		float rxv3=0;
		do
		{
			rx3=随机属性();
			rxv3=属性(rx3);
		}while(rx3.equals(rx1)||rx3.equals(rx2));

		pos = title.bottom() + 3*MARGIN;

		String finalrx1=rx1;
		String finalrx2=rx2;
		String finalrx3=rx3;

		float finalrxv1=rxv1;
		float finalrxv2=rxv2;
		float finalrxv3=rxv3;

		RedButton moveBtn=new RedButton((百分比(rx1)?Math.round(rxv1*100)+"%":String.format("%.2f",rxv1))+rx1){
			@Override
			protected void onClick(){
				super.onClick();
				hero.属性锻造(finalrx1,finalrxv1);
				hide();
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;
		
		
		RedButton moveBtn2=new RedButton((百分比(rx2)?Math.round(rxv2*100)+"%":String.format("%.2f",rxv2))+rx2){
			@Override
			protected void onClick(){
				super.onClick();
				hero.属性锻造(finalrx2,finalrxv2);
				hide();
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;
		
		
		RedButton moveBtn3=new RedButton((百分比(rx3)?Math.round(rxv3*100)+"%":String.format("%.2f",rxv3))+rx3){
			@Override
			protected void onClick(){
				super.onClick();

				hero.属性锻造(finalrx3,finalrxv3);
				hide();
			}
		};
		moveBtn3.leftJustify=true;
		moveBtn3.multiline=true;
		moveBtn3.setSize(width,moveBtn3.reqHeight());
		moveBtn3.setRect(0,pos,width,moveBtn3.reqHeight()+6);
		add(moveBtn3);
		pos=moveBtn3.bottom()+MARGIN;

		boolean 属性刷新=true;
		RedButton 刷新=new RedButton("重新获得一个属性锻造器(一次性，相当于刷新)",6){
			@Override
			protected void onClick(){
				super.onClick();
				属性锻造器 item=new 属性锻造器();
				item.用过=true;
				item.放背包();

				hide();
			}
		};
		刷新.leftJustify=true;
		刷新.multiline=true;
		刷新.setSize(width,刷新.reqHeight());
		刷新.setRect(0,pos,width,刷新.reqHeight()+6);
		if(属性刷新&&!i.用过){
			add(刷新);
			pos=刷新.bottom()+MARGIN;
		}

		resize(width, (int)pos);

	}

	@Override
	public void onBackPressed() {

	}
}
