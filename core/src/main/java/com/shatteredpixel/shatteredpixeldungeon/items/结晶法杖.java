

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.冰爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.刺青结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.毒素结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.火爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.磐石结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.蓄能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.造能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.重力结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.雷爆结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.魔能结晶;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.黑暗结晶;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择结晶;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;

public class 结晶法杖 extends 用品 {
	
	
	{
		image = 物品表.WAND_WARDING;
		特别物品=true;
		重复使用=true;
		动作=false;
	}


	@Override
	public void 使用(Hero hero){
		GameScene.selectCell(wand);
		super.使用(hero);
	}

	protected CellSelector.Listener wand= new  CellSelector.Listener() {
		
		@Override
		public void onSelect(Integer target) {
			if (target == null) {
				return;
			}

			Char enemy = Actor.findChar(target);
			if(enemy instanceof NPC){
				升级结晶(curUser,enemy);
			}else if(enemy!=null){

			}else{
				if (Dungeon.energy<10){
					GLog.w("能量不足！");
					return;
				}
				Game.runOnRenderThread(()->{
					GameScene.show(new Wnd选择结晶(target,curUser));
				});
			}
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
								x.upgrade();
							}
						}
					});
				}
			});
			return;
		}
		
		if((enemy instanceof 重力结晶 x&&x.tier<3)){
			Game.runOnRenderThread(new Callback() {
				@Override
				public void call() {
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
					if (Dungeon.energy<x.tier*5+5){
						GLog.w("能量不足！");
						return;
					}
					GameScene.show(new WndOptions(x.sprite(),
												  "要升级这个结晶吗？",
												  "升级消耗"+(x.tier*5+5)+"能量。",
												  "是",
												  "否"){
						@Override
						protected void onSelect(int index) {
							if (index == 0){
								Dungeon.energy(-x.tier*5-5);
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
