

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
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
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class Wnd选择结晶 extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 180;

	private static final int MARGIN  = 2;

	public Wnd选择结晶(int target,Hero hero){
		super();

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		float pos = MARGIN;
		RenderedTextBlock title = PixelScene.renderTextBlock("选择结晶",9);
		title.hardlight(TITLE_COLOR);
		title.setPos((width-title.width())/2, pos);
		title.maxWidth(width - MARGIN * 2);
		add(title);

		pos = title.bottom() + 3*MARGIN;
		
		RedButton moveBtn=new RedButton("魔能结晶"){
			@Override
			protected void onClick(){
				super.onClick();
				Dungeon.energy(-10);
				魔能结晶 mob = new 魔能结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}
				
				hide();
			}
		};
		moveBtn.leftJustify=true;
		moveBtn.multiline=true;
		moveBtn.setSize(width,moveBtn.reqHeight());
		moveBtn.setRect(0,pos,width,moveBtn.reqHeight()+6);
		add(moveBtn);
		pos=moveBtn.bottom()+MARGIN;
		
		
		RedButton moveBtn2=new RedButton("蓄能结晶"){
			@Override
			protected void onClick(){
				super.onClick();
				
				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				蓄能结晶 mob = new 蓄能结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn2.leftJustify=true;
		moveBtn2.multiline=true;
		moveBtn2.setSize(width,moveBtn2.reqHeight());
		moveBtn2.setRect(0,pos,width,moveBtn2.reqHeight()+6);
		add(moveBtn2);
		pos=moveBtn2.bottom()+MARGIN;


		RedButton moveBtn3=new RedButton("冰爆结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				冰爆结晶 mob = new 冰爆结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn3.leftJustify=true;
		moveBtn3.multiline=true;
		moveBtn3.setSize(width,moveBtn3.reqHeight());
		moveBtn3.setRect(0,pos,width,moveBtn3.reqHeight()+6);
		add(moveBtn3);
		pos=moveBtn3.bottom()+MARGIN;

		RedButton moveBtn4=new RedButton("火爆结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				火爆结晶 mob = new 火爆结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn4.leftJustify=true;
		moveBtn4.multiline=true;
		moveBtn4.setSize(width,moveBtn4.reqHeight());
		moveBtn4.setRect(0,pos,width,moveBtn4.reqHeight()+6);
		add(moveBtn4);
		pos=moveBtn4.bottom()+MARGIN;

		RedButton moveBtn5=new RedButton("雷爆结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				雷爆结晶 mob = new 雷爆结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn5.leftJustify=true;
		moveBtn5.multiline=true;
		moveBtn5.setSize(width,moveBtn5.reqHeight());
		moveBtn5.setRect(0,pos,width,moveBtn5.reqHeight()+6);
		add(moveBtn5);
		pos=moveBtn5.bottom()+MARGIN;

		RedButton moveBtn6=new RedButton("刺青结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				刺青结晶 mob = new 刺青结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn6.leftJustify=true;
		moveBtn6.multiline=true;
		moveBtn6.setSize(width,moveBtn6.reqHeight());
		moveBtn6.setRect(0,pos,width,moveBtn6.reqHeight()+6);
		add(moveBtn6);
		pos=moveBtn6.bottom()+MARGIN;

		RedButton moveBtn7=new RedButton("重力结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				重力结晶 mob = new 重力结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn7.leftJustify=true;
		moveBtn7.multiline=true;
		moveBtn7.setSize(width,moveBtn7.reqHeight());
		moveBtn7.setRect(0,pos,width,moveBtn7.reqHeight()+6);
		add(moveBtn7);
		pos=moveBtn7.bottom()+MARGIN;

		RedButton moveBtn8=new RedButton("黑暗结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				黑暗结晶 mob = new 黑暗结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn8.leftJustify=true;
		moveBtn8.multiline=true;
		moveBtn8.setSize(width,moveBtn8.reqHeight());
		moveBtn8.setRect(0,pos,width,moveBtn8.reqHeight()+6);
		add(moveBtn8);
		pos=moveBtn8.bottom()+MARGIN;

		RedButton moveBtn9=new RedButton("造能结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				造能结晶 mob = new 造能结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}

				hide();
			}
		};
		moveBtn9.leftJustify=true;
		moveBtn9.multiline=true;
		moveBtn9.setSize(width,moveBtn9.reqHeight());
		moveBtn9.setRect(0,pos,width,moveBtn9.reqHeight()+6);
		add(moveBtn9);
		pos=moveBtn9.bottom()+MARGIN;

		RedButton moveBtn10=new RedButton("毒素结晶"){
			@Override
			protected void onClick(){
				super.onClick();

				if(Dungeon.level.map[target]!=Terrain.EMPTY_SP)return;
				毒素结晶 mob = new 毒素结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}
				
				hide();
			}
		};
		moveBtn10.leftJustify=true;
		moveBtn10.multiline=true;
		moveBtn10.setSize(width,moveBtn10.reqHeight());
		moveBtn10.setRect(0,pos,width,moveBtn10.reqHeight()+6);
		add(moveBtn10);
		pos=moveBtn10.bottom()+MARGIN;
		
		RedButton moveBtn11=new RedButton("磐石结晶"){
			@Override
			protected void onClick(){
				super.onClick();
				
				if(Dungeon.level.map[target] != Terrain.GRASS)return;
				磐石结晶 mob = new 磐石结晶();
				GameScene.add(mob);
				传送卷轴.瞬移(mob,target);
				if(hero.地牢塔防波次==0&&!hero.地牢塔防开关){
					hero.地牢塔防波次++;
					GLog.w("第"+hero.地牢塔防波次+"波");
					hero.地牢塔防开关=true;
				}
				
				hide();
			}
		};
		moveBtn11.leftJustify=true;
		moveBtn11.multiline=true;
		moveBtn11.setSize(width,moveBtn11.reqHeight());
		moveBtn11.setRect(0,pos,width,moveBtn11.reqHeight()+6);
		add(moveBtn11);
		pos=moveBtn11.bottom()+MARGIN;
		
		resize(width, (int)pos);

	}
	
}
