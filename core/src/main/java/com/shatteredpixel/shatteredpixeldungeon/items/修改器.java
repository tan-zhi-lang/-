

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;

import java.util.ArrayList;

public class 修改器 extends Item {
	
	
	public static final String AC_伤害	= "伤害";
	public static final String AC_受伤	= "受伤";
	
	{
		image = 物品表.BEACON;
		物品 = true;
		defaultAction = AC_伤害;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_伤害 );
		actions.add( AC_受伤 );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );
		
		if (action.equals( AC_伤害 )){
			GameScene.show(new WndTextInput("伤害",
											"",
											"",
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					if (positive && !text.isEmpty()){
						Dungeon.hero.造成伤害=Float.parseFloat(text);
					}
				}
			});
		}
		if (action.equals( AC_受伤 )){
			GameScene.show(new WndTextInput("受伤",
											"",
											"",
											50,
											false,
											"确定",
											"取消"){
				@Override
				public void onSelect(boolean positive, String text) {
					if (positive && !text.isEmpty()){
						Dungeon.hero.受到伤害=Float.parseFloat(text);
					}
				}
			});
		}
//			GameScene.show( new Wnd());
	}
	
	private static class Wnd extends Window{
		
		private static final int BUTTON_HEIGHT= 16;
		private static final float GAP		= 2;
		private static final float BTN_GAP	= 12;
		private static final int WIDTH		= 112;
		
		private RedButton 造成x;
		private RedButton 受伤x;
		private RedButton 确认;
		
		Wnd(){
			造成x= new RedButton("x伤害"){
				@Override
				protected void onClick() {
				}
				
			};
			造成x.setRect(  BTN_GAP/2-1,GAP,BUTTON_HEIGHT*3,BUTTON_HEIGHT);
			add(造成x);
			
			受伤x= new RedButton("x受伤"){
				@Override
				protected void onClick() {
				}
				
			};
			受伤x.setRect(造成x.right()+BTN_GAP/2,造成x.top(),BUTTON_HEIGHT*3,BUTTON_HEIGHT);
			add(受伤x);
			
			确认= new RedButton("确认"){
				@Override
				protected void onClick() {
					hide();
				}
				
			};
			确认.setRect(  GAP*2,受伤x.top(),BUTTON_HEIGHT*6+GAP*4,BUTTON_HEIGHT);
			add(确认);
			
			resize(WIDTH, (int)(确认.bottom() + GAP*2));
		}
		
	}
}
