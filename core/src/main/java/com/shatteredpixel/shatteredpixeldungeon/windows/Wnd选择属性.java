

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.属性锻造器;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Wnd选择属性 extends Window {

	private static final int WIDTH_P = 125;//120
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;
	public float 属性(String s){
		float x = switch(s){
			default -> 1;
			case "最大攻击"-> 35f;
			case "最大防御"-> 25;
			case "最大魔抗"-> 15;
			case "最大生命"-> 25;
			case "力量"-> 5;
			case "魔力"-> 5;
			case "攻速"-> 0.2f;
			case "移速"-> 0.15f;

			case "最大命中"-> 30;
			case "最大闪避"-> 15;

			case "全能吸血"-> 0.1f;

			case "暴击伤害"-> 0.25f;
			case "暴击率"-> 0.25f;

			case "穿甲"-> 15;
			case "法穿"-> 8;

			case "治疗护盾"->0.25f;

			case "护甲穿透"->0.2f;
			case "法术穿透"->0.2f;
		};
		if(Dungeon.符文("属性叠属性"))
			return x;
		return Random.NormalFloat(x/3f,x);
	}
	public String 随机属性(){
		return Random.oneOf(
				"最大攻击",
				"最大攻击",

				"最大魔抗","最大防御",
				"最大魔抗","最大防御",

							"攻速","移速",
							"攻速","移速",
							"攻速","移速",


				"最大生命","最大生命",
				"最大生命","最大生命",
				"最大生命","最大生命",

							"最大命中","最大闪避",
							"最大命中","最大闪避",
							"最大命中","最大闪避",
							"最大命中","最大闪避",
							"最大命中","最大闪避",

							"暴击伤害","暴击率",
							"治疗护盾","全能吸血",

							"护甲穿透","穿甲",
							"护甲穿透","穿甲",

							"法术穿透","法穿",
							"法术穿透","法穿",

				"魔力","力量",
				"魔力","力量"
				);
	}
	public boolean 百分比(String s){
		return s.equals("攻速")||s.equals("移速")||
			   s.equals("暴击伤害")||s.equals("全能吸血")||
			   s.equals("暴击率")||s.equals("护甲穿透")||s.equals("法术穿透")||
			   s.equals("治疗护盾");
	}
	static  Wnd选择属性 INSTANCE;
	public Wnd选择属性(属性锻造器 i,Hero hero){
		this(i,"",0,"",0,"",0,hero);
	}
	public Wnd选择属性(属性锻造器 i,String s1,float x1,String s2,float x2,String s3,float x3,Hero hero){
		super();

		INSTANCE=this;
		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择一项属性",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		String rx1=s1;
		float rxv1=x1;
		if(s1.equals("")){
			rx1=随机属性();
			rxv1=属性(rx1);
		}

		String rx2=s2;
		float rxv2=x2;
		if(s2.equals(""))
		do
		{
			rx2=随机属性();
			rxv2=属性(rx2);
		}while(rx2.equals(rx1));


		String rx3=s3;
		float rxv3=x3;
		if(s3.equals(""))
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
		float 刷新x=-16;
		float 刷新y=9;

		RedButton moveBtn=new RedButton((百分比(rx1)?Math.round(rxv1*100)+"%":rxv1)+rx1){
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
							hero.属性锻造(finalrx1,finalrxv1);
							INSTANCE.hide();
						}
					}
				});
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width-18,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;

		RedButton 刷新1=new RedButton(i.使用1()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();
				属性锻造器 item=new 属性锻造器();
				item.用过1+=i.用过1+1;
				item.用过2+=i.用过2;
				item.用过3+=i.用过3;
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				GameScene.show(new Wnd选择属性(item,"",0,finalrx2,finalrxv2,finalrx3,finalrxv3,hero));

				hide();
			}
		};
		刷新1.setRect(width+刷新x,moveBtn.top() + (刷新1.height()-16)/2+刷新y,16,14);

		if(i.使用1()<=0||hero.符文("无法刷新海克斯")){
			刷新1.enable(false);
		}
		add(刷新1);
		
		RedButton moveBtn2=new RedButton((百分比(rx2)?Math.round(rxv2*100)+"%":rxv2)+rx2){
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
							hero.属性锻造(finalrx2,finalrxv2);
							INSTANCE.hide();
						}
					}
				});
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width-18,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;

		RedButton 刷新2=new RedButton(i.使用2()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();
				属性锻造器 item=new 属性锻造器();
				item.用过1+=i.用过1;
				item.用过2+=i.用过2+1;
				item.用过3+=i.用过3;
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				GameScene.show(new Wnd选择属性(item,finalrx1,finalrxv1,"",0,finalrx3,finalrxv3,hero));

				hide();
			}
		};
		刷新2.setRect(width+刷新x,moveBtn2.top() + (刷新2.height()-16)/2+刷新y,16,14);

		if(i.使用2()<=0||hero.符文("无法刷新海克斯")){
			刷新2.enable(false);
		}
		add(刷新2);
		
		RedButton moveBtn3=new RedButton((百分比(rx3)?Math.round(rxv3*100)+"%":rxv3)+rx3){
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
							hero.属性锻造(finalrx3,finalrxv3);
							INSTANCE.hide();
						}
					}
				});
			}
		};
		moveBtn3.leftJustify=true;
		moveBtn3.multiline=true;
		moveBtn3.setSize(width,moveBtn3.reqHeight());
		moveBtn3.setRect(0,pos,width-18,moveBtn3.reqHeight()+6);
		add(moveBtn3);
		pos=moveBtn3.bottom()+MARGIN;

		RedButton 刷新3=new RedButton(i.使用3()+"刷新",6){
			@Override
			protected void onClick(){
				super.onClick();
				属性锻造器 item=new 属性锻造器();
				item.用过1+=i.用过1;
				item.用过2+=i.用过2;
				item.用过3+=i.用过3+1;
				Sample.INSTANCE.play(Assets.Sounds.海克斯);
				GameScene.show(new Wnd选择属性(item,finalrx1,finalrxv1,finalrx2,finalrxv2,"",0,hero));

				hide();
			}
		};
		刷新3.setRect(width+刷新x,moveBtn3.top() + (刷新3.height()-16)/2+刷新y,16,14);

		if(i.使用3()<=0||hero.符文("无法刷新海克斯")){
			刷新3.enable(false);
		}
		add(刷新3);

		resize(width, (int)pos);

	}

	@Override
	public void onBackPressed() {

	}
}
