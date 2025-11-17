

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDAction;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.StatusPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentsPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.watabou.input.KeyBindings;
import com.watabou.input.KeyEvent;
import com.watabou.noosa.Gizmo;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.Locale;

public class WndHero extends WndTabbed {
	
	private static final int WIDTH		= 120+5;
	private static final int HEIGHT		= 120;
	
	private StatsTab stats;
	private StatsTab2 stats2;

	private TalentsTab talents;
	private BuffsTab buffs;

	public static int lastIdx = 0;

	public WndHero() {
		
		super();
		
		resize( WIDTH, HEIGHT );
		
		stats = new StatsTab();
		add( stats );
		
		stats2 = new StatsTab2();
		add( stats2 );

		talents = new TalentsTab();
		add(talents);
		talents.setRect(0, 0, WIDTH, HEIGHT);

		buffs = new BuffsTab();
		add( buffs );
		buffs.setRect(0, 0, WIDTH, HEIGHT);
		buffs.setupList();
		
		add( new IconTab( Icons.get(Icons.JOURNAL) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) {
					lastIdx = 0;
					if (!stats.visible) {
						stats.initialize();
					}
				}
				stats.visible = stats.active = selected;
			}
		} );
		add( new IconTab( Icons.get(Icons.RANKINGS) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) {
					lastIdx = 0;
					if (!stats2.visible) {
						stats2.initialize();
					}
				}
				stats2.visible = stats2.active = selected;
			}
		} );
		add( new IconTab( Icons.get(Icons.TALENT) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) lastIdx = 1;
				if (selected) StatusPane.talentBlink = 0;
				talents.visible = talents.active = selected;
			}
		} );
		add( new IconTab( Icons.get(Icons.BUFFS) ) {
			protected void select( boolean value ) {
				super.select( value );
				if (selected) lastIdx = 2;
				buffs.visible = buffs.active = selected;
			}
		} );

		layoutTabs();

		talents.setRect(0, 0, WIDTH, HEIGHT);
		talents.pane.scrollTo(0, talents.pane.content().height() - talents.pane.height());
		talents.layout();

		select( lastIdx );
	}

	@Override
	public boolean onSignal(KeyEvent event) {
		if (event.pressed && KeyBindings.getActionForKey( event ) == SPDAction.HERO_INFO) {
			onBackPressed();
			return true;
		} else {
			return super.onSignal(event);
		}
	}

	@Override
	public void offset(int xOffset, int yOffset) {
		super.offset(xOffset, yOffset);
		talents.layout();
		buffs.layout();
	}

	private class StatsTab extends Group {
		
		private static final int GAP = 6;
		
		private float pos;
		
		public StatsTab() {
			initialize();
		}

		public void initialize(){

			for (Gizmo g : members){
				if (g != null) g.destroy();
			}
			clear();
			
			Hero hero = Dungeon.hero;

			IconTitle title = new IconTitle();
			title.icon( HeroSprite.avatar(hero) );
			if (hero.name().equals(hero.className()))
				title.label( Messages.get(this, "title", hero.等级, hero.className() ).toUpperCase( Locale.ENGLISH ) );
			else
				title.label((hero.name() + "\n" + Messages.get(this, "title", hero.等级, hero.className())).toUpperCase(Locale.ENGLISH));
			title.color(Window.TITLE_COLOR);
			title.setRect( 0, 0, WIDTH-16, 0 );
			add(title);

			IconButton infoButton = new IconButton(Icons.get(Icons.INFO)){
				@Override
				protected void onClick() {
					super.onClick();
					if (ShatteredPixelDungeon.scene() instanceof GameScene){
						GameScene.show(new WndHeroInfo(hero.heroClass));
					} else {
						ShatteredPixelDungeon.scene().addToFront(new WndHeroInfo(hero.heroClass));
					}
				}

				@Override
				protected String hoverText() {
					return Messages.titleCase(Messages.get(WndKeyBindings.class, "hero_info"));
				}

			};
			infoButton.setRect(title.right(), 0, 16, 16);
			add(infoButton);

			pos = title.bottom() + GAP;

			statSlot( Messages.get(this, "str"), hero.力量());
			statSlot( "攻击力", hero.最小攻击()+"~"+hero.最大攻击());
			statSlot( "防御力", hero.最小防御()+"~"+hero.最大防御());
			pos += GAP;
			
			statSlot( "命中", hero.最小命中(null)+"~"+hero.最大命中(null));
			statSlot( "闪避", hero.最小闪避(null)+"~"+hero.最大闪避(null));
			statSlot( "攻速/移速", String.format("%.2f",1/hero.攻击延迟())
					+"/"+String.format("%.2f",hero.移速()));
			
			pos += GAP;
			statSlot( "暴击率/暴击伤害", hero.暴击率()+"%/"+Math.round(hero.暴击伤害()*100)+"%");
			
			pos += GAP;
		}

		private void statSlot( String label, String value ) {
			
			RenderedTextBlock txt = PixelScene.renderTextBlock( label, 8 );
			txt.setPos(0, pos);
			add( txt );
			int size = 8;
			if (value.length() >= 14) size -=2;
			if (value.length() >= 18) size -=1;
			txt = PixelScene.renderTextBlock( value, size );
			txt.setPos(WIDTH * (0.6f), pos);
			PixelScene.align(txt);
			add( txt );
			
			pos += GAP + txt.height();
		}
		
		private void statSlot( String label, int value ) {
			statSlot( label, Integer.toString( value ) );
		}
		
		public float height() {
			return pos;
		}
	}
	private class StatsTab2 extends Group {
		
		private static final int GAP = 6;
		
		private float pos;
		
		public StatsTab2() {
			initialize();
		}

		public void initialize(){

			for (Gizmo g : members){
				if (g != null) g.destroy();
			}
			clear();
			
			Hero hero = Dungeon.hero;
			
			pos = GAP*2;
			
			statSlot( "搜索/感知范围", +hero.搜索范围()+"/"+hero.感知范围());
			statSlot( "惊醒距离", hero.惊醒距离());
			pos += GAP;
			
			statSlot( "地牢视野", Dungeon.level.视野范围);
			statSlot( "视野>光照范围", hero.视野范围()+"+"+hero.光照范围());
			statSlot( "攻击范围",hero.攻击范围());
			pos += GAP;
			if (Dungeon.daily){
				if (!Dungeon.dailyReplay) {
					statSlot(Messages.get(StatsTab.class, "daily_for"), "_" + Dungeon.customSeedText + "_");
				} else {
					statSlot(Messages.get(StatsTab.class, "replay_for"), "_" + Dungeon.customSeedText + "_");
				}
			} else if (!Dungeon.customSeedText.isEmpty()){
				statSlot( Messages.get(StatsTab.class, "custom_seed"), "_" + Dungeon.customSeedText + "_" );
			} else {
				statSlot( Messages.get(StatsTab.class, "dungeon_seed"), DungeonSeed.convertToCode(Dungeon.seed) );
			}

			pos += GAP;
		}

		private void statSlot( String label, String value ) {
			
			RenderedTextBlock txt = PixelScene.renderTextBlock( label, 8 );
			txt.setPos(0, pos);
			add( txt );
			int size = 8;
			if (value.length() >= 14) size -=2;
			if (value.length() >= 18) size -=1;
			txt = PixelScene.renderTextBlock( value, size );
			txt.setPos(WIDTH * (0.55f-0.05f), pos);
			PixelScene.align(txt);
			add( txt );
			
			pos += GAP + txt.height();
		}
		
		private void statSlot( String label, int value ) {
			statSlot( label, Integer.toString( value ) );
		}
		
		public float height() {
			return pos;
		}
	}

	public class TalentsTab extends Component {

		TalentsPane pane;

		@Override
		protected void createChildren() {
			super.createChildren();
			pane = new TalentsPane(TalentButton.Mode.UPGRADE);
			add(pane);
		}

		@Override
		protected void layout() {
			super.layout();
			pane.setRect(x, y, width, height);
		}

	}
	
	private class BuffsTab extends Component {
		
		private static final int GAP = 2;
		
		private float pos;
		private ScrollPane buffList;
		private ArrayList<BuffSlot> slots = new ArrayList<>();

		@Override
		protected void createChildren() {

			super.createChildren();

			buffList = new ScrollPane( new Component() ){
				@Override
				public void onClick( float x, float y ) {
					int size = slots.size();
					for (int i=0; i < size; i++) {
						if (slots.get( i ).onClick( x, y )) {
							break;
						}
					}
				}
			};
			add(buffList);
		}
		
		@Override
		protected void layout() {
			super.layout();
			buffList.setRect(0, 0, width, height);
		}
		
		private void setupList() {
			Component content = buffList.content();
			for (Buff buff : Dungeon.hero.buffs()) {
				if (buff.icon() != BuffIndicator.NONE) {
					BuffSlot slot = new BuffSlot(buff);
					slot.setRect(0, pos, WIDTH, slot.icon.height());
					content.add(slot);
					slots.add(slot);
					pos += GAP + slot.height();
				}
			}
			content.setSize(buffList.width(), pos);
			buffList.setSize(buffList.width(), buffList.height());
		}

		private class BuffSlot extends Component {

			private Buff buff;

			Image icon;
			RenderedTextBlock txt;

			public BuffSlot( Buff buff ){
				super();
				this.buff = buff;

				icon = new BuffIcon(buff, true);
				icon.y = this.y;
				add( icon );

				txt = PixelScene.renderTextBlock( Messages.titleCase(buff.name()), 8 );
				txt.setPos(
						icon.width + GAP,
						this.y + (icon.height - txt.height()) / 2
				);
				PixelScene.align(txt);
				add( txt );

			}

			@Override
			protected void layout() {
				super.layout();
				icon.y = this.y;
				txt.maxWidth((int)(width - icon.width()));
				txt.setPos(
						icon.width + GAP,
						this.y + (icon.height - txt.height()) / 2
				);
				PixelScene.align(txt);
			}
			
			protected boolean onClick ( float x, float y ) {
				if (inside( x, y )) {
					GameScene.show(new WndInfoBuff(buff));
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
