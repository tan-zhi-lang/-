

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.祛邪卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.鉴定卷轴;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.交易NPC动画;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 交易NPC extends NPC {

	{
		spriteClass = 交易NPC动画.class;
		属性表.add(Property.IMMOVABLE);
	}

	public 交易NPC(){
		do {
			heroClass = Random.oneOf(HeroClass.values());
		} while (heroClass == HeroClass.NONE
				 ||heroClass==HeroClass.机器
				 ||heroClass==HeroClass.灵猫
				 ||heroClass==HeroClass.鼠弟
				 ||heroClass==HeroClass.凌云
		);
	}

	@Override
	public CharSprite sprite() {
		CharSprite sprite = super.sprite();
		((交易NPC动画) sprite).setArmor(heroClass);
		return sprite;
	}

	public HeroClass heroClass=HeroClass.盗贼;
	private static final String HEROCLASS = "HEROCLASS";

	@Override
	public void storeInBundle(Bundle bundle) {
		bundle.put(HEROCLASS, heroClass);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		heroClass = bundle.getEnum(HEROCLASS, HeroClass.class);
	}
	@Override
	public int 最大闪避(Char enemy ) {
		return Char.INFINITE;
	}

	@Override
	public void 受伤时(float dmg, Object 来源) {
		//do nothing
	}

	@Override
	public boolean add( Buff buff ) {
		return false;
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public boolean interact(Char c) {
		
		sprite.turnTo( pos, Dungeon.hero.pos );

		if (c != Dungeon.hero){
			return true;
		}
		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				GameScene.show( new Wnd交易(交易NPC.this,
											"你如果有，可以来我找交易！",
											"治疗药剂->经验药剂",
											"鉴定卷轴->祛邪卷轴"));
			}
		});

		return true;
	}
	public class Wnd交易 extends Window{

		private static final int WIDTH      = 120;
		private static final int BTN_HEIGHT = 20;
		private static final int GAP        = 2;

		public Wnd交易(final 交易NPC npc,String mes,String r1,String r2) {

			super();

			RenderedTextBlock message = PixelScene.renderTextBlock(mes,6);
			message.maxWidth(WIDTH);
			message.setPos(0, 0);
			add( message );
			RedButton btnReward = new RedButton(r1 ) {
				@Override
				protected void onClick() {
					交易1(npc);
				}
			};
			btnReward.setRect( 0, message.top() + message.height() + GAP, WIDTH, BTN_HEIGHT );
			add( btnReward );
			RedButton btnReward2 = new RedButton( r2 ) {
				@Override
				protected void onClick() {
						交易2(npc);
				}
			};
			btnReward2.setRect( 0, btnReward.top() + btnReward.height() + GAP, WIDTH, BTN_HEIGHT );
			add( btnReward2 );

			resize( WIDTH, (int)btnReward2.bottom() );
		}

		private void 交易1(交易NPC npc) {

			if(Dungeon.hero.belongings.hasItem(治疗药剂.class)){
				Dungeon.hero.belongings.getItem(治疗药剂.class).detach();
				Dungeon.level.drop(new 经验药剂(),pos).sprite.drop();
				完成交易(npc);
			}
		}
		private void 交易2(交易NPC npc) {

			if(Dungeon.hero.belongings.hasItem(鉴定卷轴.class)){
				Dungeon.hero.belongings.getItem(鉴定卷轴.class).detach();
				Dungeon.level.drop(new 祛邪卷轴(),pos).sprite.drop();
				完成交易(npc);
			}
		}
		private void 完成交易(交易NPC npc) {

			hide();

			npc.flee();

		}
	}
	public void flee() {
		
		yell("达成交易！");
		
		destroy();
		sprite.die();
	}

}
