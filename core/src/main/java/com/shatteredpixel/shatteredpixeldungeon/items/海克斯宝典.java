

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择海克斯;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 海克斯宝典 extends 用品 {
	
	
	{
		image = 物品表.海克斯宝典;
		可以空间=false;
		嬗变=false;
		可堆叠=false;
		特别= true;
	}

	public 海克斯宝典(){

	}
	public 海克斯宝典(boolean 非正常获取){
		if(非正常获取){
			if(Dungeon.符文("任务:海克斯获取")){
				new 海克斯宝典(false).放背包();
				new 海克斯宝典(false).放背包();
			}
		}
	}

	protected static final String AC_祈愿 = "祈愿";
	protected static final String AC_移除 = "移除";


	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_祈愿 );

		if(hero.符文("海克斯宝典移除器"))
		actions.add( AC_移除 );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_移除)&&使用()>0) {
			用过1++;
			用过2++;
			用过3++;
			String b=new 海克斯移除器().desc();
			GameScene.show(new WndTextInput("海克斯移除",
											b,
											移除,
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					移除=text;
					if (positive && !移除.isEmpty()){
						Sample.INSTANCE.play(Assets.Sounds.海克斯);
						if(hero.是海克斯(移除)){
							if(hero.符文(移除))hero.删除海克斯(移除);
							else hero.重置海克斯(移除);
							return;
						}
					}

					GLog.橙("你输入的海克斯名字不存在！");
					用过1--;
					用过2--;
					用过3--;
				}
			});

			if(使用()<0)detach();
		}

		if (action.equals(AC_祈愿)&&使用()>0) {
			String b="输入海克斯名字使其权重大大增加(不能叠加)";
			if(hero.符文("我让你海克斯随机"))b="输入海克斯名字并获得";
			GameScene.show(new WndTextInput("海克斯祈愿",
											b,
											祈愿,
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					祈愿=text;
					if (positive && !祈愿.isEmpty()){
						if(hero.是海克斯(祈愿)){
							Sample.INSTANCE.play(Assets.Sounds.海克斯);
							if(hero.符文("我让你海克斯随机"))hero.选择海克斯(祈愿);
							else hero.增加海克斯(祈愿);
						}else
							GLog.橙("你输入的海克斯名字不存在！");
					}
				}
			});
		}
	}
	public int 用过1=0;
	public int 用过2=0;
	public int 用过3=0;
	public String 祈愿="";
	public String 移除="";
	private static final String 用过1x=        "用过1";
	private static final String 用过2x=        "用过2";
	private static final String 用过3x=        "用过3";
	private static final String 祈愿x=        "祈愿";
	private static final String 移除x=        "移除";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过1x,用过1);
		bundle.put(用过2x,用过2);
		bundle.put(用过3x,用过3);
		bundle.put(祈愿x,祈愿);
		bundle.put(移除x,移除);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过1= bundle.getInt(用过1x);
		用过2= bundle.getInt(用过2x);
		用过3= bundle.getInt(用过3x);
		祈愿= bundle.getString(祈愿x);
		移除= bundle.getString(移除x);
	}
	public Item 清空使用(){
		用过1=使用上限1();
		用过2=使用上限2();
		用过3=使用上限3();
		return this;
	}
	public void 用过(){
		用过1=使用1();
		用过2=使用2();
		用过3=使用3();
	}
	public int 使用(){
		return 使用1()+使用2()+使用3();
	}
	public int 使用1(){
		return 使用上限1()-用过1;
	}
	public int 使用2(){
		return 使用上限2()-用过2;
	}
	public int 使用3(){
		return 使用上限3()-用过3;
	}
	public static int 使用上限(){
		return 1+(Dungeon.符文("骰子收集者")?2:0)
			   +(算法.isDebug()?100:0)+
			   +(Dungeon.符文("刷新海克斯")?5:0)+
							 (Dungeon.符文("好记性如烂笔头")?4:0);
	}
	public static int 使用上限1(){
		return 使用上限();
	}
	public static int 使用上限2(){
		return 使用上限();
	}
	public static int 使用上限3(){
		return 使用上限();
	}
	@Override
	public void 使用(Hero hero){


		hero.分配海克斯();
		Game.runOnRenderThread(()->{
			Sample.INSTANCE.play(Assets.Sounds.海克斯);
			GameScene.show(new Wnd选择海克斯(this,hero));
			
		});
		if(hero.符文("超级海克斯宝典"))new 海克斯宝典(true).清空使用().放背包();

//		Dungeon.保存游戏();
		super.使用(hero);
	}

	@Override
	public int 金币(){
		return 75*(使用()>=0?
							使用()*8:1);
	}
	@Override
	public int 能量(){
		return 15*(使用()>=0?
						   使用()*2:1);
	}
}
