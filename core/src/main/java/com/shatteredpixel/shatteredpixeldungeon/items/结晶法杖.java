

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.光明结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.冰爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.刺青结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.毒素结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.火爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.磐石结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.蓄能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.造能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.雷爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.魔能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.黑暗结晶;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择结晶;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

public class 结晶法杖 extends 用品 {
	
	
	{
		image = 物品表.WAND_WARDING;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_USE)&&Dungeon.energy>=10) {
			
			GameScene.selectCell(wand);
		}
	}
	
	protected CellSelector.Listener wand= new  CellSelector.Listener() {
		
		@Override
		public void onSelect(Integer target) {
			if (target == null) {
				return;
			}

			Char enemy = Actor.findChar(target);
			
			升级结晶(curUser,enemy);
			
			if (enemy != null || enemy == curUser || !Dungeon.level.heroFOV[target]) {
				return;
			}
			Game.runOnRenderThread(()->{
				GameScene.show(new Wnd选择结晶(target,curUser));
			});
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};
	
	public void 升级结晶(Hero hero,Char enemy){
		
		if((enemy instanceof 魔能结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 蓄能结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 冰爆结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 火爆结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 雷爆结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 刺青结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 光明结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 黑暗结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 造能结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 毒素结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		
		if((enemy instanceof 磐石结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗10能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-10);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		
		
		
		
		
	}
}
