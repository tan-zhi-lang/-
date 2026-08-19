

package com.shatteredpixel.shatteredpixeldungeon.items.用品;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ItemButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTextInput;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTitledMessage;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd物品代码名;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

import java.util.ArrayList;

public class 物品生成 extends 用品{
	
	
	{
		重复使用=true;
		defaultAction=AC_CHOOSE;
		黑色=true;
		白光=true;

		不能丢扔=true;
		金币价值=0;
		能量价值=0;
	}

	public String 代码="";
	private static final String 代码x=        "代码";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(代码x,代码);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		代码= bundle.getString(代码x);
	}
	protected static final String AC_5武器 = "5武器";
	protected static final String AC_法杖 = "法杖";
	protected static final String AC_神器 = "神器";
	protected static final String AC_禁忌物 = "禁忌物";
	protected static final String AC_装备 = "装备";
	protected static final String AC_药剂种子 = "药剂种子";
	protected static final String AC_卷轴符石 = "卷轴符石";
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_5武器 );
		actions.add( AC_法杖 );
		actions.add( AC_神器 );
		actions.add( AC_禁忌物 );
		actions.add( AC_装备 );
		actions.add( AC_药剂种子 );
		actions.add( AC_卷轴符石 );
		return actions;
	}
	@Override
	public void execute( Hero hero, String action ){

		super.execute(hero,action);

		if(action.equals(AC_5武器)){
			GameScene.show(new Wnd武器选择());
		}
		if(action.equals(AC_法杖)){
			GameScene.show(new Wnd法杖选择());
		}
		if(action.equals(AC_神器)){
			GameScene.show(new Wnd神器选择());
		}
		if(action.equals(AC_禁忌物)){
			GameScene.show(new Wnd禁忌物选择());
		}
		if(action.equals(AC_装备)){
			GameScene.show(new Wnd装备选择());
		}
		if(action.equals(AC_药剂种子)){
			GameScene.show(new Wnd药剂种子选择());
		}
		if(action.equals(AC_卷轴符石)){
			GameScene.show(new Wnd卷轴符石选择());
		}
	}
	@Override
	public void 使用(Hero hero){

		GameScene.show(new Wnd物品代码名("物品生成",
										 desc(),
										 代码,
										 50,
										 false,
										 "确定",
										 "取消"){
			@Override
			public void onSelect(boolean positive, String text) {
				if (positive && !text.isEmpty()){
					if(算法.物品(text)!=null){
						代码=text;
						Item item=算法.物品(text);
						item.鉴定(鉴定);
						if(item instanceof EquipableItem&&诅咒){
							item.cursed=诅咒;
							item.cursedKnown=诅咒;
						}
						if(item.等级()>0)
							GLog.黄("你生成了"+item.等级()+"级"+item.name());
						else
							GLog.黄("你生成了"+item.数量()+"个"+item.name());

						item.放背包();
					}else
						GLog.红("你输入的代码名不存在！");
				}
			}
		});
		super.使用(hero);
	}

	public static class Wnd武器选择 extends WndTitledMessage{

			public Wnd武器选择() {
				super(new ItemSprite(物品表.WEAPON_HOLDER),
					  "5阶武器",
					  "选择要获取的武器");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.WEP_T5.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndTextInput("待输入等级",
															"",
															"0",
															50,
															false,
															"确定",
															"取消"){
								@Override
								public void onSelect(boolean positive, String text) {
									if (positive && !text.isEmpty()){

										try{
											int x=Integer.parseInt(text);
											Item newi =item.getClass().newInstance();
											if(x!=-1){
												newi.升级(x);
											}
											newi.放背包();

											GLog.黄("你生成了"+x+"级"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}

	public static class Wnd法杖选择 extends WndTitledMessage{

			public Wnd法杖选择() {
				super(new ItemSprite(物品表.WAND_HOLDER),
					  "法杖",
					  "选择要获取的法杖");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.WAND.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndTextInput("待输入等级",
															"",
															"0",
															50,
															false,
															"确定",
															"取消"){
								@Override
								public void onSelect(boolean positive, String text) {
									if (positive && !text.isEmpty()){

										try{
											int x=Integer.parseInt(text);
											Item newi =item.getClass().newInstance();
											if(x!=-1){
												newi.升级(x);
											}
											newi.放背包();

											GLog.黄("你生成了"+x+"级"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}

	public static class Wnd神器选择 extends WndTitledMessage{

			public Wnd神器选择() {
				super(new ItemSprite(物品表.ARTIFACT_HOLDER),
					  "神器",
					  "选择要获取的神器");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.ARTIFACT.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick(){

							GameScene.show(new WndOptions("确定获取此神器？","","确定","取消"){
								@Override
								protected void onSelect(int index){
									if(index==0){
										try{
											Item newi=item.getClass().newInstance();

											newi.放背包();

											GLog.黄("你生成了"+newi.name());

										}catch(Exception e){

										}

										hide();
									}
								}
							});
						}

					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}

	public static class Wnd禁忌物选择 extends WndTitledMessage{

			public Wnd禁忌物选择() {
				super(new ItemSprite(物品表.ITEM),
					  "禁忌物",
					  "选择要获取的禁忌物");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.TRINKET.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndOptions("确定获取此禁忌物？",
														  "",
														  "确定",
														  "取消"){
								@Override
								protected void onSelect(int index) {
									if (index == 0){
										try{
											Item newi =item.getClass().newInstance();

											newi.放背包();

											GLog.黄("你生成了"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}

	public static class Wnd装备选择 extends WndTitledMessage{

			public Wnd装备选择() {
				super(new ItemSprite(物品表.ITEM),
					  "常规装备",
					  "选择要获取的装备");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.ARMOR.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.WEP_T1.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.WEP_T2.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.WEP_T3.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.WEP_T4.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndTextInput("待输入等级",
															"",
															"0",
															50,
															false,
															"确定",
															"取消"){
								@Override
								public void onSelect(boolean positive, String text) {
									if (positive && !text.isEmpty()){

										try{
											int x=Integer.parseInt(text);
											Item newi =item.getClass().newInstance();
											if(x!=-1){
												newi.升级(x);
											}
											newi.放背包();

											GLog.黄("你生成了"+x+"级"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}

	public static class Wnd药剂种子选择 extends WndTitledMessage{

			public Wnd药剂种子选择() {
				super(new ItemSprite(物品表.POTION_HOLDER),
					  "药剂种子",
					  "选择要获取的物品");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();
				for (Class<?> potionCls : Generator.Category.SEED.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.POTION.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Catalog.EXOTIC_POTIONS.items()) {
					items.add((Item) Reflection.newInstance(potionCls));
				}

				for (Class<?> potionCls : Catalog.BREWS_ELIXIRS.items()) {
					items.add((Item) Reflection.newInstance(potionCls));
				}

				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndTextInput("待输入物品数量",
															"",
															"1",
															50,
															false,
															"确定",
															"取消"){
								@Override
								public void onSelect(boolean positive, String text) {
									if (positive && !text.isEmpty()){

										try{
											int x=Integer.parseInt(text);
											Item newi =item.getClass().newInstance();
											if(x!=-1){
												newi.数量(x);
											}
											newi.放背包();

											GLog.黄("你生成了"+x+"个"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}
	public static class Wnd卷轴符石选择 extends WndTitledMessage{

			public Wnd卷轴符石选择() {
				super(new ItemSprite(物品表.SCROLL_HOLDER),
					  "卷轴、符石",
					  "选择要获取的物品");

				int top = height + 2;
				int left = 0;

				ArrayList<Item> items = new ArrayList<>();

				for (Class<?> potionCls : Generator.Category.STONE.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Generator.Category.SCROLL.classes) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Catalog.EXOTIC_SCROLLS.items()) {
					items.add((Item) Reflection.newInstance(potionCls));
				}
				for (Class<?> potionCls : Catalog.SPELLS.items()) {
					items.add((Item) Reflection.newInstance(potionCls));
				}

				for (Item item : items) {
					ItemButton itemButton = new ItemButton(){
						@Override
						protected void onClick() {
							GameScene.show(new WndTextInput("待输入物品数量",
															"",
															"1",
															50,
															false,
															"确定",
															"取消"){
								@Override
								public void onSelect(boolean positive, String text) {
									if (positive && !text.isEmpty()){

										try{
											int x=Integer.parseInt(text);
											Item newi =item.getClass().newInstance();
											if(x!=-1){
												newi.数量(x);
											}
											newi.放背包();

											GLog.黄("你生成了"+x+"个"+newi.name());

										}catch(Exception e){

											GLog.红("你输入的数字错误！");
										}
										
										hide();
									}
								}
							});
						}
					};
					itemButton.item(item);
					itemButton.setRect(left, top, 19, 19);
					add(itemButton);


					left += 20;
					if (left >= width - 19){
						top += 20;
						left = 0;
					}
				}
				if (left > 0){
					top += 20;
					left = 0;
				}

				resize(width, top);
			}
		}
}
