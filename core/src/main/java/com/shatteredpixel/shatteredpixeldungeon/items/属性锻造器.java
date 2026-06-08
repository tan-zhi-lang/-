

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择属性;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 属性锻造器 extends 用品 {
	
	
	{
		image = 物品表.属性锻造器;
		嬗变=false;
		可以空间=false;
		可堆叠=false;
		特别= true;
	}

	public int 用过1=0;
	public int 用过2=0;
	public int 用过3=0;
	private static final String 用过1x=        "用过1";
	private static final String 用过2x=        "用过2";
	private static final String 用过3x=        "用过3";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(用过1x,用过1);
		bundle.put(用过2x,用过2);
		bundle.put(用过3x,用过3);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		用过1= bundle.getInt(用过1x);
		用过2= bundle.getInt(用过2x);
		用过3= bundle.getInt(用过3x);
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
	public int 使用上限(){
		return 1+(Dungeon.符文("骰子收集者")?1:0)+(Dungeon.符文("刷新海克斯")?3:0);
	}
	public int 使用上限1(){
		return 使用上限();
	}
	public int 使用上限2(){
		return 使用上限();
	}
	public int 使用上限3(){
		return 使用上限();
	}
	@Override
	public void 使用(Hero hero){
		Sample.INSTANCE.play(Assets.Sounds.海克斯);

		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择属性(this,hero));
		});
		super.使用(hero);
	}

	@Override
	public int 金币() {
		return 75;
	}
}
