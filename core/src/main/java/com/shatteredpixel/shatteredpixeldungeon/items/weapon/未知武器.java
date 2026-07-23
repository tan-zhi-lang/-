

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class 未知武器 extends Weapon{
	{
		image = 物品表.WEAPON_HOLDER;
		tier=0;
		特别=true;

		黑色=true;
		白光=true;

		不能丢扔=true;

		金币价值=0;
		能量价值=0;
		defaultAction=AC_切换;
	}

	protected static final String AC_切换 = "切换";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_切换 );
		return actions;
	}
	private static final int WIDTH_P	    = 122;
	private static final float GAP          = 1;
	private static final int BTN_HEIGHT	    = 16;
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_切换)) {

			ShatteredPixelDungeon.scene().addToFront(new Window(){
				OptionSlider 阶级;
				OptionSlider 伤害;
				OptionSlider 攻速;
				OptionSlider 命中;
				OptionSlider 范围;

				{


					阶级 = new OptionSlider("阶级",
													 "1","5",1,5) {
						@Override
						protected void onChange() {
							put("阶级",getSelectedValue());
						}
					};
					阶级.setSelectedValue(属性.get("阶级"));
					add(阶级);

					伤害 = new OptionSlider("伤害",
													 "1","2",0,4) {
						@Override
						protected void onChange() {
							put("伤害",getSelectedValue());
						}
					};
					伤害.setSelectedValue(属性.get("伤害"));
					add(伤害);

					攻速 = new OptionSlider("攻速",
													 "1","2",0,4) {
						@Override
						protected void onChange() {
							put("攻速",getSelectedValue());
						}
					};
					攻速.setSelectedValue(属性.get("攻速"));
					add(攻速);

					命中 = new OptionSlider("命中",
													 "1","2",0,4) {
						@Override
						protected void onChange() {
							put("命中",getSelectedValue());
						}
					};
					命中.setSelectedValue(属性.get("命中"));
					add(命中);

					范围 = new OptionSlider("范围",
													 "1","5",0,4) {
						@Override
						protected void onChange() {
							put("范围",getSelectedValue());
						}
					};
					范围.setSelectedValue(属性.get("范围"));
					add(范围);


					//layout
					resize(WIDTH_P, 0);
					阶级.setRect(0,  GAP, width, BTN_HEIGHT);
					伤害.setRect(0,  阶级.bottom()+GAP, width, BTN_HEIGHT);
					攻速.setRect(0,  伤害.bottom()+GAP, width, BTN_HEIGHT);
					命中.setRect(0,  攻速.bottom()+GAP, width, BTN_HEIGHT);
					范围.setRect(0,  命中.bottom()+GAP, width, BTN_HEIGHT);

					resize(WIDTH_P, (int) 范围.bottom());
				}
			});
		}

	}

	@Override
	public int tier(){
		return super.tier()+Math.round(属性.get("阶级"));
	}
	@Override
	public float 伤害(){
		return super.伤害()+属性.get("伤害")*0.25f;
	}
	@Override
	public float 延迟(){
		return super.延迟()/(1+属性.get("攻速")*0.25f);
	}
	@Override
	public float 命中(){
		return super.延迟()*(1+属性.get("命中")*0.25f);
	}
	@Override
	public int 范围(){
		return super.范围()+属性.get("范围");
	}

	public void put(String s,int x){
		属性.put(s,x);
	}
	public HashMap<String, Integer> 属性 = new HashMap<>(){
		{
			put("阶级",5);
			put("伤害",2);
			put("攻速",0);
			put("命中",0);
			put("范围",0);
		}
	};
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		Bundle 属性x = new Bundle();
		for(String s:属性.keySet()){
			属性x.put(s, 属性.get(s));
		}
		bundle.put("属性", 属性x);

	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		if (bundle.contains("属性")){
			Bundle replacements = bundle.getBundle("属性");
			for (String s : replacements.getKeys()){
				Integer i = replacements.getInt(s);
				try {
					属性.put(s, i);
				} catch (Exception e) {
				}
			}
		}
	}
}
