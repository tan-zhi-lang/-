

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择海克斯;
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


	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_祈愿 );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_祈愿)&&使用上限()>=初始使用上限()) {
			String b="输入海克斯名字使其权重+1(不能叠加)";
			if(hero.符文("我让你海克斯随机"))b="输入海克斯名字并获得";
			GameScene.show(new WndTextInput("海克斯祈愿",
											b,
											"",
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					if (positive && !text.isEmpty()){
						if(hero.是海克斯(text)){

							if(hero.符文("我让你海克斯随机"))hero.选择海克斯(text);
							else
							hero.增加海克斯(text);
						}else
							GLog.w("你输入的海克斯名字不存在！");
					}
				}
			});
		}
	}
	public int 用过=0;
	private static final String 用过x=        "用过";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过x,用过);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过= bundle.getInt(用过x);
	}
	public int 使用上限(){
		return 1+(Dungeon.符文("骰子收集者")?2:0)+(Dungeon.符文("刷新海克斯")?5:0)+(Dungeon.符文("好记性如烂笔头")?4:0)-用过;
	}
	public static int 初始使用上限(){
		return 1+(Dungeon.符文("骰子收集者")?2:0)+(Dungeon.符文("刷新海克斯")?5:0)+(Dungeon.符文("好记性如烂笔头")?4:0);
	}
	@Override
	public void 使用(Hero hero){

		Game.runOnRenderThread(()->{
			Sample.INSTANCE.play(Assets.Sounds.海克斯);
			GameScene.show(new Wnd选择海克斯(this,hero));
			
		});


//		Dungeon.保存游戏();
		super.使用(hero);
	}

	@Override
	public int 金币(){
		return 100*((使用上限()-用过)>=0?(使用上限()-用过)*30:1);
	}
	@Override
	public int 能量(){
		return 15*((使用上限()-用过)>=0?(使用上限()-用过)*5:1);
	}
}
