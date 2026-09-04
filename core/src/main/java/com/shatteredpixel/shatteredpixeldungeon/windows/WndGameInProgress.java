

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.badlogic.gdx.Gdx;
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
		title.icon( HeroSprite.avatar(info.heroClass,info.armorTier) );
		title.label((Messages.get(this,"title",Math.round(info.level), className)).toUpperCase(Locale.ENGLISH));
		title.color(Window.TITLE_COLOR);
		title.setRect( 0, 0, WIDTH, 0 );
		add(title);
		
		if (info.challenges > 0) GAP -= 2;
		
		pos = title.bottom() + GAP;
		
		// ========== 模式按钮：自动每行排 2 个 ==========
		java.util.ArrayList<RedButton> modeBtns = new java.util.ArrayList<>();

		if (info.challenges > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "challenges") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new WndChallenges( info.challenges, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.CHALLENGE_COLOR));
		}
		if (info.炼狱 > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "炼狱") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 炼狱( info.炼狱, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.炼狱开));
		}
		if (info.解压 > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "解压") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 解压( info.解压, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.解压开));
		}
		if (info.系统 > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "系统") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 系统( info.系统, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.系统开));
		}
		if (info.派对 > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "派对") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 派对( info.派对, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.派对开));
		}
		if (info.赛季 > 0) {
			modeBtns.add(new RedButton( Messages.get(this, "赛季") ) {
				@Override
				protected void onClick() {
					Game.scene().add( new 赛季( info.赛季, false ) );
				}
			});
			modeBtns.get(modeBtns.size()-1).icon(Icons.get(Icons.赛季开));
		}

		//两列布局：奇数个时最后一行占半宽，不足整行靠左
		float colW = (WIDTH - GAP) / 2f;
		for (int i = 0; i < modeBtns.size(); i += 2) {
			RedButton left = modeBtns.get(i);
			left.setRect(0, pos, colW, 18);
			add(left);

			if (i + 1 < modeBtns.size()) {
				RedButton right = modeBtns.get(i + 1);
				right.setRect(colW + GAP, pos, WIDTH - colW - GAP, 18);
				add(right);
			}
			pos += 18 + GAP;
		}
		if (!modeBtns.isEmpty()) {
			pos += GAP;
		}else{
			pos -= GAP;
		}
		
		pos += GAP;

		statSlot( Messages.get(this, "depth"), info.maxDepth );
		statSlot( "难度", Dungeon.难度名称(info.难度));
		pos += GAP;
		if (info.daily) {
			if (info.dailyReplay) {
				statSlot(Messages.get(this, "replay_for"), "_" + info.customSeed + "_");
			} else {
				statSlot(Messages.get(this, "daily_for"), "_" + info.customSeed + "_");
			}
		} else if (!info.customSeed.isEmpty()){
			statSlot( Messages.get(this, "custom_seed"), "_" + info.customSeed + "_" );
		} else {
			//种子行：数值列留出右侧空间，行尾放复制按钮（同 WndTextInput 的 btnCopy）
			String seedCode = DungeonSeed.convertToCode(info.seed);

			int size = 8;
			RenderedTextBlock txt;
			do {
				txt = PixelScene.renderTextBlock( Messages.get(this, "dungeon_seed"), size );
				size--;
			} while (txt.width() >= WIDTH * 0.55f);
			txt.setPos(0, pos + (6 - txt.height())/2);
			PixelScene.align(txt);
			add( txt );

			//行内布局：标签(左) | 种子码(中) | 复制按钮(右)，三者互不重叠
			float seedX = WIDTH * 0.55f;
			float copyX = WIDTH - 18;
			float seedMax = copyX - GAP - seedX;

			size = 8;
			do {
				txt = PixelScene.renderTextBlock( seedCode, size );
				size--;
			} while (txt.width() >= seedMax);
			txt.setPos(seedX, pos + (6 - txt.height())/2);
			PixelScene.align(txt);
			add( txt );

			RedButton btnSeedCopy = new RedButton("") {
				@Override
				protected void onClick() {
					super.onClick();
					Gdx.app.getClipboard().setContents(seedCode);
					Game.scene().add(new Wndinfo("复制成功",seedCode));
				}
			};
			btnSeedCopy.icon(Icons.get(Icons.COPY));
			add( btnSeedCopy );
			btnSeedCopy.setRect(copyX, pos + (6 - 18)/2f, 18, 18);

			//行高以复制按钮 18px 为准，避免与下方继续/删除按钮重叠
			pos += GAP + Math.max(txt.height(), 18);
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
		
		int size = 8;
		RenderedTextBlock txt;
		do {
			txt = PixelScene.renderTextBlock( label, size );
			size--;
		} while (txt.width() >= WIDTH * 0.55f);
		txt.setPos(0, pos + (6 - txt.height())/2);
		PixelScene.align(txt);
		add( txt );
		
		size = 8;
		do {
			txt = PixelScene.renderTextBlock( value, size );
			size--;
		} while (txt.width() >= WIDTH * 0.45f);
		
		txt.setPos(WIDTH * 0.55f, pos + (6 - txt.height())/2);
		PixelScene.align(txt);
		add( txt );
		
		pos += GAP + txt.height();
	}
	
	private void statSlot( String label, int value ) {
		statSlot( label, Integer.toString( value ) );
	}
}
