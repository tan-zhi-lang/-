

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.StartScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.DungeonSeed;
import com.watabou.noosa.Game;

import java.util.Locale;

public class WndGameInProgress extends Window {
	
	private static final int WIDTH    = 120;
	
	private int GAP	  = 6;
	
	private float pos;
	
	public WndGameInProgress(final int slot){
		
		final GamesInProgress.Info info = GamesInProgress.check(slot);
		
		String className = null;
		if (info.subClass != HeroSubClass.NONE){
			className = info.subClass.title();
		} else {
			className = info.heroClass.title();
		}
		
		IconTitle title = new IconTitle();
		title.icon( HeroSprite.avatar(info.heroClass, info.armorTier) );
		title.label((Messages.get(this, "title", info.level, className)).toUpperCase(Locale.ENGLISH));
		title.color(Window.TITLE_COLOR);
		title.setRect( 0, 0, WIDTH, 0 );
		add(title);
		
		if (info.challenges > 0) GAP -= 2;
		
		pos = title.bottom() + GAP;
		
		if (info.challenges > 0) {
			RedButton btnChallenges = new RedButton( Messages.get(this, "challenges") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new WndChallenges( info.challenges, false ) );
				}
			};
			btnChallenges.icon(Icons.get(Icons.CHALLENGE_COLOR));
			float btnW = btnChallenges.reqWidth() + 2;
			btnChallenges.setRect( (WIDTH - btnW)/2, pos, btnW , 18 );
			add( btnChallenges );
			
			pos = btnChallenges.bottom() + GAP;
		}
		if (info.炼狱 > 0) {
			RedButton btnChallenges = new RedButton( Messages.get(this, "炼狱") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 炼狱( info.炼狱, false ) );
				}
			};
			btnChallenges.icon(Icons.get(Icons.炼狱开));
			float btnW = btnChallenges.reqWidth() + 2;
			btnChallenges.setRect( (WIDTH - btnW)/2, pos, btnW , 18 );
			add( btnChallenges );
			
			pos = btnChallenges.bottom() + GAP;
		}
		if (info.解压 > 0) {
			RedButton btnChallenges = new RedButton( Messages.get(this, "解压") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 解压( info.解压, false ) );
				}
			};
			btnChallenges.icon(Icons.get(Icons.解压开));
			float btnW = btnChallenges.reqWidth() + 2;
			btnChallenges.setRect( (WIDTH - btnW)/2, pos, btnW , 18 );
			add( btnChallenges );
			
			pos = btnChallenges.bottom() + GAP;
		}
		if (info.系统 > 0) {
			RedButton btnChallenges = new RedButton( Messages.get(this, "系统") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 系统( info.系统, false ) );
				}
			};
			btnChallenges.icon(Icons.get(Icons.系统开));
			float btnW = btnChallenges.reqWidth() + 2;
			btnChallenges.setRect( (WIDTH - btnW)/2, pos, btnW , 18 );
			add( btnChallenges );
			
			pos = btnChallenges.bottom() + GAP;
		}
		if (info.玩法 > 0) {
			RedButton btnChallenges = new RedButton( Messages.get(this, "玩法") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 玩法( info.玩法, false ) );
				}
			};
			btnChallenges.icon(Icons.get(Icons.玩法开));
			float btnW = btnChallenges.reqWidth() + 2;
			btnChallenges.setRect( (WIDTH - btnW)/2, pos, btnW , 18 );
			add( btnChallenges );
			
			pos = btnChallenges.bottom() + GAP;
		}
		
		pos += GAP;

		statSlot( Messages.get(this, "depth"), info.maxDepth );
		if (info.daily) {
			if (info.dailyReplay) {
				statSlot(Messages.get(this, "replay_for"), "_" + info.customSeed + "_");
			} else {
				statSlot(Messages.get(this, "daily_for"), "_" + info.customSeed + "_");
			}
		} else if (!info.customSeed.isEmpty()){
			statSlot( Messages.get(this, "custom_seed"), "_" + info.customSeed + "_" );
		} else {
			statSlot( Messages.get(this, "dungeon_seed"), DungeonSeed.convertToCode(info.seed) );
		}
		
		pos += GAP;
		
		RedButton cont = new RedButton(Messages.get(this, "continue")){
			@Override
			protected void onClick() {
				super.onClick();
				
				GamesInProgress.curSlot = slot;
				
				Dungeon.hero = null;
				Dungeon.daily = Dungeon.dailyReplay = false;
				ActionIndicator.clearAction();
				InterlevelScene.mode = InterlevelScene.Mode.CONTINUE;
				ShatteredPixelDungeon.switchScene(InterlevelScene.class);
			}
		};
		
		RedButton erase = new RedButton( Messages.get(this, "erase")){
			@Override
			protected void onClick() {
				super.onClick();
				
				ShatteredPixelDungeon.scene().add(new WndOptions(Icons.get(Icons.WARNING),
						Messages.get(WndGameInProgress.class, "erase_warn_title"),
						Messages.get(WndGameInProgress.class, "erase_warn_body"),
						Messages.get(WndGameInProgress.class, "erase_warn_yes"),
						Messages.get(WndGameInProgress.class, "erase_warn_no") ) {
					@Override
					protected void onSelect( int index ) {
						if (index == 0) {
							Dungeon.deleteGame(slot, true);
							ShatteredPixelDungeon.switchNoFade(StartScene.class);
						}
					}
				} );
			}
		};

		cont.icon(Icons.get(Icons.下楼));
		cont.setRect(0, pos, WIDTH/2 -1, 20);
		add(cont);

		erase.icon(Icons.get(Icons.CLOSE));
		erase.setRect(WIDTH/2 + 1, pos, WIDTH/2 - 1, 20);
		add(erase);
		
		resize(WIDTH, (int)cont.bottom()+1);
	}
	
	private void statSlot( String label, String value ) {
		
		RenderedTextBlock txt = PixelScene.renderTextBlock( label, 8 );
		txt.setPos(0, pos);
		add( txt );

		int size = 8;
		if (value.length() >= 14) size -=2;
		if (value.length() >= 18) size -=1;
		txt = PixelScene.renderTextBlock( value, size );
		txt.setPos(WIDTH * 0.55f, pos + (6 - txt.height())/2);
		PixelScene.align(txt);
		add( txt );
		
		pos += GAP + txt.height();
	}
	
	private void statSlot( String label, int value ) {
		statSlot( label, Integer.toString( value ) );
	}
}
