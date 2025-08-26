

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.badlogic.gdx.Gdx;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.services.news.News;
import com.shatteredpixel.shatteredpixeldungeon.services.updates.Updates;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.GameLog;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Toolbar;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.input.ControllerHandler;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class WndSettings extends WndTabbed {//WndSettings

	private static final int WIDTH_P	    = 122;
	private static final int WIDTH_L	    = 223;

	private static final int SLIDER_HEIGHT	= 21;
	private static final int BTN_HEIGHT	    = 16;
	private static final float GAP          = 1;

	private DisplayTab 显示设置;

	private 游戏设置 游戏设置;
	private InputTab    input;
	private DataTab     data;
	private AudioTab    audio;
	private LangsTab    langs;

	public static int last_index = 0;

	public WndSettings() {
		super();

		float height;

		int width = PixelScene.横屏() ? WIDTH_L : WIDTH_P;

		显示设置 = new DisplayTab();
		显示设置.setSize(width, 0);
		height = 显示设置.height();
		add(显示设置);

		add( new IconTab(Icons.get(Icons.DISPLAY)){
			@Override
			protected void select(boolean value) {
				super.select(value);
				显示设置.visible = 显示设置.active = value;
				if (value) last_index = 0;
			}
		});

		游戏设置 = new 游戏设置();
		游戏设置.setSize(width, 0);
		height = Math.max(height, 游戏设置.height());
		add(游戏设置);

		add( new IconTab(Icons.get(Icons.CONTROLLER)){
			@Override
			protected void select(boolean value) {
				super.select(value);
				游戏设置.visible = 游戏设置.active = value;
				if (value) last_index = 2;
			}
		});

		input = new InputTab();
		input.setSize(width, 0);
		height = Math.max(height, input.height());

		if (DeviceCompat.hasHardKeyboard() || ControllerHandler.isControllerConnected()) {
			add( input );
			Image icon;
			if (ControllerHandler.controllerActive || !DeviceCompat.hasHardKeyboard()){
				icon = Icons.get(Icons.CONTROLLER);
			} else {
				icon = Icons.get(Icons.KEYBOARD);
			}
			add(new IconTab(icon) {
				@Override
				protected void select(boolean value) {
					super.select(value);
					input.visible = input.active = value;
					if (value) last_index = 3;
				}
			});
		}

		data = new DataTab();
		data.setSize(width, 0);
		height = Math.max(height, data.height());
//		add( data );

//		add( new IconTab(Icons.get(Icons.DATA)){
//			@Override
//			protected void select(boolean value) {
//				super.select(value);
//				data.visible = data.active = value;
//				if (value) last_index = 3;
//			}
//		});

		audio = new AudioTab();
		audio.setSize(width, 0);
		height = Math.max(height, audio.height());
		add( audio );

		add( new IconTab(Icons.get(Icons.AUDIO)){
			@Override
			protected void select(boolean value) {
				super.select(value);
				audio.visible = audio.active = value;
				if (value) last_index = 4;
			}
		});

		langs = new LangsTab();
		langs.setSize(width, 0);
		height = Math.max(height, langs.height());
//		add( langs );


		IconTab langsTab = new IconTab(Icons.get(Icons.LANGS)){
			@Override
			protected void select(boolean value) {
				super.select(value);
				langs.visible = langs.active = value;
				if (value) last_index = 5;
			}

			@Override
			protected void createChildren() {
				super.createChildren();
				switch(Messages.lang().status()){
					case X_UNFINISH:
						icon.hardlight(1.5f, 0, 0);
						break;
					case __UNREVIEW:
						icon.hardlight(1.5f, 0.75f, 0f);
						break;
				}
			}

		};
//		add( langsTab );

		resize(width, (int)Math.ceil(height));

		layoutTabs();

		if (tabs.size() == 5 && last_index >= 3){
			//input tab isn't visible
			select(last_index-1);
		} else {
			select(last_index);
		}

	}

	@Override
	public void hide() {
		super.hide();
		//resets generators because there's no need to retain chars for languages not selected
		ShatteredPixelDungeon.seamlessResetScene(new Game.SceneChangeCallback() {
			@Override
			public void beforeCreate() {
				Game.platform.resetGenerators();
			}
			@Override
			public void afterCreate() {
				//do nothing
			}
		});
	}

	private static class DisplayTab extends Component {

		RenderedTextBlock title;
		ColorBlock sep1;
		RedButton 所有开关;
		ColorBlock sep2;
		RedButton 所有拖条;
		ColorBlock sep3;
		RedButton btnToolbarSettings;
		ColorBlock sep4;
		CheckBox chkFlipTags;
		
		CheckBox chkSaver;
		RedButton btnOrientation;
		OptionSlider optUIMode;
		OptionSlider optUIScale;
		
		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);
			所有开关= new RedButton("所有开关",9){
				@Override
				protected void onClick() {
					ShatteredPixelDungeon.scene().addToFront(new Window(){
						CheckBox chkFullscreen;
						CheckBox 动画加快;
						CheckBox chkFont;
						CheckBox chkVibrate;
						RenderedTextBlock 画面同步str;
						CheckBox 画面同步;
						
						{
							
							chkFullscreen = new CheckBox( Messages.get(DisplayTab.class, "fullscreen") ) {
								@Override
								protected void onClick() {
									super.onClick();
									SPDSettings.fullscreen(checked());
								}
							};
							if (DeviceCompat.supportsFullScreen()){
								chkFullscreen.checked(SPDSettings.fullscreen());
							} else {
								chkFullscreen.checked(true);
								chkFullscreen.enable(false);
							}
							add(chkFullscreen);
							
							resize(WIDTH_P, 0);
							动画加快 = new CheckBox("动画加快") {
								@Override
								protected void onClick() {
									super.onClick();
									SPDSettings.动画加快(checked());
								}
							};
							if (DeviceCompat.supportsFullScreen()){
								动画加快.checked(SPDSettings.动画加快());
							} else {
								动画加快.checked(true);
								动画加快.enable(false);
							}
							add(动画加快);
							
							chkFont = new CheckBox(Messages.get(DisplayTab.class, "system_font")){
								@Override
								protected void onClick() {
									super.onClick();
									ShatteredPixelDungeon.seamlessResetScene(new Game.SceneChangeCallback() {
										@Override
										public void beforeCreate() {
											SPDSettings.systemFont(checked());
										}
										
										@Override
										public void afterCreate() {
											//do nothing
										}
									});
								}
							};
							chkFont.checked(SPDSettings.systemFont());
							add(chkFont);
							
							chkVibrate = new CheckBox(Messages.get(DisplayTab.class, "vibration")){
								@Override
								protected void onClick() {
									super.onClick();
									SPDSettings.vibration(checked());
									if (checked()){
										Game.vibrate(250);
									}
								}
							};
							chkVibrate.enable(Game.platform.supportsVibration());
							if (chkVibrate.active) {
								chkVibrate.checked(SPDSettings.vibration());
							}
							add(chkVibrate);
							
							画面同步 = new CheckBox(Messages.get(WndSettings.DisplayTab.this, "画面同步")){
								@Override
								protected void onClick() {
									super.onClick();
									SPDSettings.画面同步(checked());
									Gdx.graphics.setVSync(checked());
								}
							};
							画面同步.checked(SPDSettings.画面同步());
							add(画面同步);
							画面同步str = PixelScene.renderTextBlock(Messages.get(WndSettings.DisplayTab.this, "画面同步str"), 5);
							画面同步str.hardlight(0x888888);
							add(画面同步str);
							
							//layout
							resize(WIDTH_P, 0);
							chkFullscreen.setRect(0,  GAP, width, BTN_HEIGHT);
							动画加快.setRect(0,  chkFullscreen.bottom()+GAP, width, BTN_HEIGHT);
							chkFont.setRect(0,  动画加快.bottom()+GAP, width, BTN_HEIGHT);
							chkVibrate.setRect(0,  chkFont.bottom()+GAP, width, BTN_HEIGHT);
							画面同步.setRect(0,  chkVibrate.bottom()+GAP, width, BTN_HEIGHT);
							画面同步str.maxWidth(width);
							画面同步str.setPos(0, 画面同步.bottom()+1);
							
							resize(WIDTH_P, (int) 画面同步str.bottom());
							
						}
					});
				}
			};
			add(所有开关);
			
			sep2 = new ColorBlock(1, 1, 0xFF000000);
			add(sep2);
			
			所有拖条= new RedButton("所有拖条",9){
				@Override
				protected void onClick() {
					ShatteredPixelDungeon.scene().addToFront(new Window(){
						OptionSlider optBrightness;
						OptionSlider optVisGrid;
						OptionSlider optFollowIntensity;
						OptionSlider optScreenShake;
						OptionSlider 游戏帧率;
						
						{
							
							
							optBrightness = new OptionSlider(Messages.get(DisplayTab.class, "brightness"),
															 Messages.get(DisplayTab.class, "dark"), Messages.get(DisplayTab.class, "bright"), -1, 1) {
								@Override
								protected void onChange() {
									SPDSettings.亮度(getSelectedValue());
								}
							};
							optBrightness.setSelectedValue(SPDSettings.亮度());
							add(optBrightness);
							
							optVisGrid = new OptionSlider(Messages.get(DisplayTab.class, "visual_grid"),
														  Messages.get(DisplayTab.class, "off"), Messages.get(DisplayTab.class, "high"), -1, 2) {
								@Override
								protected void onChange() {
									SPDSettings.网格可视度(getSelectedValue());
								}
							};
							optVisGrid.setSelectedValue(SPDSettings.网格可视度());
							add(optVisGrid);
							
							
							optFollowIntensity = new OptionSlider(Messages.get(DisplayTab.class, "camera_follow"),
																  Messages.get(DisplayTab.class, "low"), Messages.get(DisplayTab.class, "high"), 1, 4) {
								@Override
								protected void onChange() {
									SPDSettings.镜头追踪强度(getSelectedValue());
								}
							};
							optFollowIntensity.setSelectedValue(SPDSettings.镜头追踪强度());
							add(optFollowIntensity);
							
							optScreenShake = new OptionSlider(Messages.get(DisplayTab.class, "screenshake"),
															  Messages.get(DisplayTab.class, "off"), Messages.get(DisplayTab.class, "high"), 0, 4) {
								@Override
								protected void onChange() {
									SPDSettings.震屏强度(getSelectedValue());
								}
							};
							optScreenShake.setSelectedValue(SPDSettings.震屏强度());
							add(optScreenShake);
							
							
							游戏帧率 = new OptionSlider("游戏帧率",
														"30", "120", 1, 4) {//30 60 90 120
								@Override
								protected void onChange() {
									SPDSettings.游戏帧率(getSelectedValue());
									Gdx.graphics.setForegroundFPS(getSelectedValue()*30);
								}
							};
							游戏帧率.setSelectedValue(SPDSettings.游戏帧率());
							add(游戏帧率);
							
							//layout
							resize(WIDTH_P, 0);
							optBrightness.setRect(0,  GAP, width, BTN_HEIGHT);
							optVisGrid.setRect(0,  optBrightness.bottom()+GAP, width, BTN_HEIGHT);
							optFollowIntensity.setRect(0,  optVisGrid.bottom()+GAP, width, BTN_HEIGHT);
							optScreenShake.setRect(0,  optFollowIntensity.bottom()+GAP, width, BTN_HEIGHT);
							游戏帧率.setRect(0,  optScreenShake.bottom()+GAP, width, BTN_HEIGHT);
							
							resize(WIDTH_P, (int) 游戏帧率.bottom());
						}
					});
				}
			};
			add(所有拖条);
			
			sep3 = new ColorBlock(1, 1, 0xFF000000);
			add(sep3);
			
			if (DeviceCompat.isAndroid()) {
				Boolean landscape = SPDSettings.landscape();
				if (landscape == null){
					landscape = Game.width > Game.height;
				}
				Boolean finalLandscape = landscape;
				btnOrientation = new RedButton(finalLandscape ?
													   Messages.get(this, "portrait")
													   : Messages.get(this, "landscape")) {
					@Override
					protected void onClick() {
						SPDSettings.landscape(!finalLandscape);
					}
				};
				add(btnOrientation);
			}
			
			if (SPDSettings.interfaceSize() == 0) {
				btnToolbarSettings = new RedButton(Messages.get(DisplayTab.class, "toolbar_settings"), 9){
					@Override
					protected void onClick() {
						ShatteredPixelDungeon.scene().addToFront(new Window(){
							
							RedButton btnSplit; RedButton btnGrouped; RedButton btnCentered;
							CheckBox chkQuickSwapper;
							RenderedTextBlock swapperDesc;
							CheckBox chkFlipToolbar;
							CheckBox chkFlipTags;
							
							{
								btnSplit = new RedButton(Messages.get(WndSettings.DisplayTab.this, "split")) {
									@Override
									protected void onClick() {
										textColor(TITLE_COLOR);
										btnGrouped.textColor(WHITE);
										btnCentered.textColor(WHITE);
										SPDSettings.toolbarMode(Toolbar.Mode.SPLIT.name());
										Toolbar.updateLayout();
									}
								};
								if (SPDSettings.toolbarMode().equals(Toolbar.Mode.SPLIT.name())) {
									btnSplit.textColor(TITLE_COLOR);
								}
								add(btnSplit);
								
								btnGrouped = new RedButton(Messages.get(WndSettings.DisplayTab.this, "group")) {
									@Override
									protected void onClick() {
										btnSplit.textColor(WHITE);
										textColor(TITLE_COLOR);
										btnCentered.textColor(WHITE);
										SPDSettings.toolbarMode(Toolbar.Mode.GROUP.name());
										Toolbar.updateLayout();
									}
								};
								if (SPDSettings.toolbarMode().equals(Toolbar.Mode.GROUP.name())) {
									btnGrouped.textColor(TITLE_COLOR);
								}
								add(btnGrouped);
								
								btnCentered = new RedButton(Messages.get(WndSettings.DisplayTab.this, "center")) {
									@Override
									protected void onClick() {
										btnSplit.textColor(WHITE);
										btnGrouped.textColor(WHITE);
										textColor(TITLE_COLOR);
										SPDSettings.toolbarMode(Toolbar.Mode.CENTER.name());
										Toolbar.updateLayout();
									}
								};
								if (SPDSettings.toolbarMode().equals(Toolbar.Mode.CENTER.name())) {
									btnCentered.textColor(TITLE_COLOR);
								}
								add(btnCentered);
								
								chkQuickSwapper = new CheckBox(Messages.get(WndSettings.DisplayTab.this, "quickslot_swapper")) {
									@Override
									protected void onClick() {
										super.onClick();
										SPDSettings.quickSwapper(checked());
										Toolbar.updateLayout();
									}
								};
								chkQuickSwapper.checked(SPDSettings.quickSwapper());
								add(chkQuickSwapper);
								
								swapperDesc = PixelScene.renderTextBlock(Messages.get(WndSettings.DisplayTab.this, "swapper_desc"), 5);
								swapperDesc.hardlight(0x888888);
								add(swapperDesc);
								
								chkFlipToolbar = new CheckBox(Messages.get(WndSettings.DisplayTab.this, "flip_toolbar")) {
									@Override
									protected void onClick() {
										super.onClick();
										SPDSettings.flipToolbar(checked());
										Toolbar.updateLayout();
									}
								};
								chkFlipToolbar.checked(SPDSettings.flipToolbar());
								add(chkFlipToolbar);
								
								chkFlipTags = new CheckBox(Messages.get(WndSettings.DisplayTab.this, "flip_indicators")){
									@Override
									protected void onClick() {
										super.onClick();
										SPDSettings.flipTags(checked());
										GameScene.layoutTags();
									}
								};
								chkFlipTags.checked(SPDSettings.flipTags());
								add(chkFlipTags);
								
								//layout
								resize(WIDTH_P, 0);
								
								int btnWidth = (int) (width - 2 * GAP) / 3;
								btnSplit.setRect(0, GAP, btnWidth, BTN_HEIGHT-2);
								btnGrouped.setRect(btnSplit.right() + GAP, btnSplit.top(), btnWidth, BTN_HEIGHT-2);
								btnCentered.setRect(btnGrouped.right() + GAP, btnSplit.top(), btnWidth, BTN_HEIGHT-2);
								
								chkQuickSwapper.setRect(0, btnGrouped.bottom() + GAP, width, BTN_HEIGHT);
								
								swapperDesc.maxWidth(width);
								swapperDesc.setPos(0, chkQuickSwapper.bottom()+1);
								
								if (width > 200) {
									chkFlipToolbar.setRect(0, swapperDesc.bottom() + GAP, width / 2 - 1, BTN_HEIGHT);
									chkFlipTags.setRect(chkFlipToolbar.right() + GAP, chkFlipToolbar.top(), width / 2 - 1, BTN_HEIGHT);
								} else {
									chkFlipToolbar.setRect(0, swapperDesc.bottom() + GAP, width, BTN_HEIGHT);
									chkFlipTags.setRect(0, chkFlipToolbar.bottom() + GAP, width, BTN_HEIGHT);
								}
								
								resize(WIDTH_P, (int)chkFlipTags.bottom());
								
							}
						});
					}
				};
				add(btnToolbarSettings);
				
			} else {
				
				chkFlipTags = new CheckBox(Messages.get(DisplayTab.class, "flip_indicators")) {
					@Override
					protected void onClick() {
						super.onClick();
						SPDSettings.flipTags(checked());
						GameScene.layoutTags();
					}
				};
				chkFlipTags.checked(SPDSettings.flipTags());
				add(chkFlipTags);
				
			}
			
			sep4 = new ColorBlock(1, 1, 0xFF000000);
			add(sep4);
			
			//region 省电
			//power saver is being slowly phased out, only show it on old (4.3-) android devices
			// this is being phased out as the setting is useless on all but very old devices anyway
			// and support is going to be dropped for 4.3- in the forseeable future
			
			if (DeviceCompat.isAndroid() && PixelScene.maxScreenZoom >= 2
				&& (SPDSettings.powerSaver() || !DeviceCompat.supportsFullScreen())){
				chkSaver=new CheckBox(Messages.get(this,"saver")){
					@Override
					protected void onClick(){
						super.onClick();
						if(checked()){
							checked(!checked());
							ShatteredPixelDungeon.scene().add(new WndOptions(Icons.get(Icons.DISPLAY),Messages.get(DisplayTab.class,"saver"),Messages.get(DisplayTab.class,"saver_desc"),Messages.get(DisplayTab.class,"okay"),Messages.get(DisplayTab.class,"cancel")){
								@Override
								protected void onSelect(int index){
									if(index==0){
										checked(!checked());
										SPDSettings.powerSaver(checked());
									}
								}
							});
						}else{
							SPDSettings.powerSaver(checked());
						}
					}
				};
				chkSaver.checked(SPDSettings.powerSaver());
				add(chkSaver);
			}
			
			//endregion
			
			//region 可能关闭
			//add slider for UI size only if device has enough space to support it
			float wMin = Game.width / PixelScene.MIN_WIDTH_FULL;
			float hMin = Game.height / PixelScene.MIN_HEIGHT_FULL;
			
			if (Math.min(wMin, hMin) >= 2*Game.density){
				optUIMode = new OptionSlider(
						Messages.get(DisplayTab.class, "ui_mode"),
						Messages.get(DisplayTab.class, "mobile"),
						Messages.get(DisplayTab.class, "full"),
						0,
						2
				) {
					@Override
					protected void onChange() {
						SPDSettings.interfaceSize(getSelectedValue());
						ShatteredPixelDungeon.seamlessResetScene();
					}
				};
				optUIMode.setSelectedValue(SPDSettings.interfaceSize());
				add(optUIMode);
			}
			
			if ((int)Math.ceil(2* Game.density) < PixelScene.maxDefaultZoom) {
				optUIScale = new OptionSlider(Messages.get(DisplayTab.class, "scale"),
											  (int)Math.ceil(2* Game.density)+ "X",
											  PixelScene.maxDefaultZoom + "X",
											  (int)Math.ceil(2* Game.density),
											  PixelScene.maxDefaultZoom ) {
					@Override
					protected void onChange() {
						if (getSelectedValue() != SPDSettings.scale()) {
							SPDSettings.scale(getSelectedValue());
							ShatteredPixelDungeon.seamlessResetScene();
						}
					}
				};
				optUIScale.setSelectedValue(PixelScene.defaultZoom);
				add(optUIScale);
			}
			//endregion
			
		}

		@Override
		protected void layout() {

			float bottom = y;

			title.setPos((width - title.width())/2, bottom + GAP);
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;

			bottom = sep1.y + 1;
			
			if (所有开关!=null) {
				所有开关.setRect(0,bottom+GAP,width,BTN_HEIGHT);
				bottom = 所有开关.bottom();
			}
			sep2.size(width, 1);
			sep2.y = bottom + GAP;
			bottom = sep2.y + 1;
			
			if (所有拖条!=null) {
				所有拖条.setRect(0,bottom+GAP,width,BTN_HEIGHT);
				bottom = 所有拖条.bottom();
			}
			sep3.size(width, 1);
			sep3.y = bottom + GAP;
			bottom = sep3.y + 1;
			
			if (btnToolbarSettings != null) {
				btnToolbarSettings.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = btnToolbarSettings.bottom();
			}
			if (chkFlipTags != null) {
				chkFlipTags.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = chkFlipTags.bottom();
			}
			sep4.size(width, 1);
			sep4.y = bottom + GAP;
			bottom = sep4.y + 1;
			
			if (chkSaver != null) {
				chkSaver.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = chkSaver.bottom();
			}
			if (btnOrientation != null) {
				btnOrientation.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = btnOrientation.bottom();
			}
			if (optUIMode != null) {
				optUIMode.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = optUIMode.bottom();
			}
			if (optUIScale != null) {
				optUIScale.setRect(0, bottom + GAP, width, BTN_HEIGHT);
				bottom = optUIScale.bottom();
			}
			height = bottom;
		}

	}

	private static class 游戏设置 extends Component {

		RenderedTextBlock title;

		ColorBlock sep1;
		RedButton 所有开关;
		ColorBlock sep2;
		RedButton 所有拖条;

		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);

			所有开关= new RedButton("所有开关",9){
				@Override
				protected void onClick() {
					ShatteredPixelDungeon.scene().addToFront(new Window(){

						RenderedTextBlock 游戏提示str;
						CheckBox 游戏提示;

						{

							游戏提示 = new CheckBox(Messages.get(WndSettings.游戏设置.this, "游戏提示")){
								@Override
								protected void onClick() {
									super.onClick();
									SPDSettings.游戏提示(checked());
								}
							};
							游戏提示.checked(SPDSettings.游戏提示());
							add(游戏提示);
							游戏提示str = PixelScene.renderTextBlock(Messages.get(WndSettings.游戏设置.this, "游戏提示str"), 5);
							游戏提示str.hardlight(0x888888);
							add(游戏提示str);
							
							
							resize(WIDTH_P, 0);
							游戏提示.setRect(0,  GAP, width, BTN_HEIGHT);
							游戏提示str.maxWidth(width);
							游戏提示str.setPos(0, 游戏提示.bottom()+1);
							
							resize(WIDTH_P, (int) 游戏提示str.bottom());

						}
					});
				}
			};
			add(所有开关);

			sep2 = new ColorBlock(1, 1, 0xFF000000);
			add(sep2);
			
			所有拖条= new RedButton("所有拖条",9){
				@Override
				protected void onClick() {
					ShatteredPixelDungeon.scene().addToFront(new Window(){
						
						OptionSlider 固定移速;
						OptionSlider 休息速度;
						{
							
							固定移速 = new OptionSlider("固定移速",
														"1", "无限", 1, 5) {
								@Override
								protected void onChange() {
									SPDSettings.固定移速(getSelectedValue());
								}
							};
							固定移速.setSelectedValue(SPDSettings.固定移速());
							add(固定移速);
							
							休息速度 = new OptionSlider("休息速度",
														"1", "5", 1, 5) {
								@Override
								protected void onChange() {
									SPDSettings.休息速度(getSelectedValue());
								}
							};
							休息速度.setSelectedValue(SPDSettings.休息速度());
							add(休息速度);
							
							resize(WIDTH_P, 0);
							固定移速.setRect(0,  GAP, width, BTN_HEIGHT);
							休息速度.setRect(0,  固定移速.bottom()+GAP, width, BTN_HEIGHT);
							resize(WIDTH_P, (int) 休息速度.bottom());
							
						}
					});
				}
			};
			add(所有拖条);
			
		}

		@Override
		protected void layout() {
			title.setPos((width - title.width())/2, y + GAP);
			
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;
			height = sep1.y + 1;
			
			所有开关.setRect(0,height+GAP,width,BTN_HEIGHT);
			
			sep2.y =所有开关.bottom()+GAP;
			sep2.size(width, 1);
			height = sep2.y + 1;
			
			所有拖条.setRect(0,sep2.bottom()+GAP,width,BTN_HEIGHT);

			height = 所有拖条.bottom();

		}

	}

	private static class InputTab extends Component{

		RenderedTextBlock title;
		ColorBlock sep1;

		RedButton btnKeyBindings;
		RedButton btnControllerBindings;

		ColorBlock sep2;

		OptionSlider optControlSens;
		OptionSlider optHoldMoveSens;

		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);

			if (DeviceCompat.hasHardKeyboard()){

				btnKeyBindings = new RedButton(Messages.get(this, "key_bindings")){
					@Override
					protected void onClick() {
						super.onClick();
						ShatteredPixelDungeon.scene().addToFront(new WndKeyBindings(false));
					}
				};

				add(btnKeyBindings);
			}

			if (ControllerHandler.isControllerConnected()){
				btnControllerBindings = new RedButton(Messages.get(this, "controller_bindings")){
					@Override
					protected void onClick() {
						super.onClick();
						ShatteredPixelDungeon.scene().addToFront(new WndKeyBindings(true));
					}
				};

				add(btnControllerBindings);
			}

			sep2 = new ColorBlock(1, 1, 0xFF000000);
			add(sep2);


			optControlSens = new OptionSlider(
					Messages.get(this, "controller_sensitivity"),
					"1",
					"10",
					1,
					10
			) {
				@Override
				protected void onChange() {
					SPDSettings.controllerPointerSensitivity(getSelectedValue());
				}
			};
			optControlSens.setSelectedValue(SPDSettings.controllerPointerSensitivity());
			add(optControlSens);

			optHoldMoveSens = new OptionSlider(
					Messages.get(this, "movement_sensitivity"),
					Messages.get(this, "off"),
					Messages.get(this, "high"),
					0,
					4
			) {
				@Override
				protected void onChange() {
					SPDSettings.movementHoldSensitivity(getSelectedValue());
				}
			};
			optHoldMoveSens.setSelectedValue(SPDSettings.movementHoldSensitivity());
			add(optHoldMoveSens);
		}

		@Override
		protected void layout() {
			title.setPos((width - title.width())/2, y + GAP);
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;

			height = sep1.y+1;

			if (width > 200 && btnKeyBindings != null && btnControllerBindings != null){
				btnKeyBindings.setRect(0, height + GAP, width/2-1, BTN_HEIGHT);
				btnControllerBindings.setRect(width/2+1, height + GAP, width/2-1, BTN_HEIGHT);
				height = btnControllerBindings.bottom();
			} else {
				if (btnKeyBindings != null) {
					btnKeyBindings.setRect(0, height + GAP, width, BTN_HEIGHT);
					height = btnKeyBindings.bottom();
				}

				if (btnControllerBindings != null) {
					btnControllerBindings.setRect(0, height + GAP, width, BTN_HEIGHT);
					height = btnControllerBindings.bottom();
				}
			}

			sep2.size(width, 1);
			sep2.y = height+ GAP;

			if (width > 200){
				optControlSens.setRect(0, sep2.y + 1 + GAP, width/2-1, SLIDER_HEIGHT);
				optHoldMoveSens.setRect(width/2 + 1, optControlSens.top(), width/2 -1, SLIDER_HEIGHT);
			} else {
				optControlSens.setRect(0, sep2.y + 1 + GAP, width, SLIDER_HEIGHT);
				optHoldMoveSens.setRect(0, optControlSens.bottom() + GAP, width, SLIDER_HEIGHT);
			}

			height = optHoldMoveSens.bottom();

		}
	}

	private static class DataTab extends Component{

		RenderedTextBlock title;
		ColorBlock sep1;
		CheckBox chkNews;
		CheckBox chkUpdates;
		CheckBox chkBetas;
		CheckBox chkWifi;

		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);

			chkNews = new CheckBox(Messages.get(this, "news")){
				@Override
				protected void onClick() {
					super.onClick();
					SPDSettings.news(checked());
					News.clearArticles();
				}
			};
			chkNews.checked(SPDSettings.news());
			add(chkNews);

			if (Updates.supportsUpdates() && Updates.supportsUpdatePrompts()) {
				chkUpdates = new CheckBox(Messages.get(this, "updates")) {
					@Override
					protected void onClick() {
						super.onClick();
						SPDSettings.updates(checked());
						Updates.clearUpdate();
					}
				};
				chkUpdates.checked(SPDSettings.updates());
				add(chkUpdates);

				if (Updates.supportsBetaChannel()){
					chkBetas = new CheckBox(Messages.get(this, "betas")) {
						@Override
						protected void onClick() {
							super.onClick();
							SPDSettings.betas(checked());
							Updates.clearUpdate();
						}
					};
					chkBetas.checked(SPDSettings.betas());
					add(chkBetas);
				}
			}

			if (!DeviceCompat.isDesktop()){
				chkWifi = new CheckBox(Messages.get(this, "wifi")){
					@Override
					protected void onClick() {
						super.onClick();
						SPDSettings.WiFi(checked());
					}
				};
				chkWifi.checked(SPDSettings.WiFi());
				add(chkWifi);
			}
		}

		@Override
		protected void layout() {
			title.setPos((width - title.width())/2, y + GAP);
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;

			float pos;
			if (width > 200 && chkUpdates != null){
				chkNews.setRect(0, sep1.y + 1 + GAP, width/2-1, BTN_HEIGHT);
				chkUpdates.setRect(chkNews.right() + GAP, chkNews.top(), width/2-1, BTN_HEIGHT);
				pos = chkUpdates.bottom();
			} else {
				chkNews.setRect(0, sep1.y + 1 + GAP, width, BTN_HEIGHT);
				pos = chkNews.bottom();
				if (chkUpdates != null) {
					chkUpdates.setRect(0, chkNews.bottom() + GAP, width, BTN_HEIGHT);
					pos = chkUpdates.bottom();
				}
			}

			if (chkBetas != null){
				chkBetas.setRect(0, pos + GAP, width, BTN_HEIGHT);
				pos = chkBetas.bottom();
			}

			if (chkWifi != null){
				chkWifi.setRect(0, pos + GAP, width, BTN_HEIGHT);
				pos = chkWifi.bottom();
			}

			height = pos;

		}
	}

	private static class AudioTab extends Component {

		RenderedTextBlock title;
		ColorBlock sep1;
		OptionSlider optMusic;
		CheckBox chkMusicMute;
		ColorBlock sep2;
		OptionSlider optSFX;
		CheckBox chkMuteSFX;
		ColorBlock sep3;
		CheckBox chkIgnoreSilent;
		CheckBox chkMusicBG;

		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);

			optMusic = new OptionSlider(Messages.get(this, "music_vol"), "0", "10", 0, 10) {
				@Override
				protected void onChange() {
					SPDSettings.musicVol(getSelectedValue());
				}
			};
			optMusic.setSelectedValue(SPDSettings.musicVol());
			add(optMusic);

			chkMusicMute = new CheckBox(Messages.get(this, "music_mute")){
				@Override
				protected void onClick() {
					super.onClick();
					SPDSettings.music(!checked());
					Music.INSTANCE.volume( SPDSettings.musicVol()*SPDSettings.musicVol()/100f );
					Music.INSTANCE.enable( SPDSettings.music() );
				}
			};
			chkMusicMute.checked(!SPDSettings.music());
			add(chkMusicMute);

			sep2 = new ColorBlock(1, 1, 0xFF000000);
			add(sep2);

			optSFX = new OptionSlider(Messages.get(this, "sfx_vol"), "0", "10", 0, 10) {
				@Override
				protected void onChange() {
					SPDSettings.SFXVol(getSelectedValue());
					Sample.INSTANCE.enable( SPDSettings.soundFx() );
					Sample.INSTANCE.volume( SPDSettings.SFXVol()*SPDSettings.SFXVol()/100f );
					Sample.INSTANCE.play(Random.oneOf(Assets.Sounds.all));
				}
			};
			optSFX.setSelectedValue(SPDSettings.SFXVol());
			add(optSFX);

			chkMuteSFX = new CheckBox( Messages.get(this, "sfx_mute") ) {
				@Override
				protected void onClick() {
					super.onClick();
					SPDSettings.soundFx(!checked());
					Sample.INSTANCE.play( Assets.Sounds.CLICK );
				}
			};
			chkMuteSFX.checked(!SPDSettings.soundFx());
			add( chkMuteSFX );

			if (DeviceCompat.isiOS()){

				sep3 = new ColorBlock(1, 1, 0xFF000000);
				add(sep3);

				chkIgnoreSilent = new CheckBox( Messages.get(this, "ignore_silent") ){
					@Override
					protected void onClick() {
						super.onClick();
						SPDSettings.ignoreSilentMode(checked());
					}
				};
				chkIgnoreSilent.checked(SPDSettings.ignoreSilentMode());
				add(chkIgnoreSilent);

			} else if (DeviceCompat.isDesktop()){

				sep3 = new ColorBlock(1, 1, 0xFF000000);
				add(sep3);

				chkMusicBG = new CheckBox( Messages.get(this, "music_bg") ){
					@Override
					protected void onClick() {
						super.onClick();
						SPDSettings.playMusicInBackground(checked());
					}
				};
				chkMusicBG.checked(SPDSettings.playMusicInBackground());
				add(chkMusicBG);
			}
		}

		@Override
		protected void layout() {
			title.setPos((width - title.width())/2, y + GAP);
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;

			if (width > 200) {
				optMusic.setRect(0, sep1.y + 1 + GAP, width/2-1, SLIDER_HEIGHT);
				chkMusicMute.setRect(0, optMusic.bottom() + GAP, width/2-1, BTN_HEIGHT);

				sep2.size(width, 1);
				sep2.y = sep1.y; //just have them overlap

				optSFX.setRect(optMusic.right()+2, sep2.y + 1 + GAP, width/2-1, SLIDER_HEIGHT);
				chkMuteSFX.setRect(chkMusicMute.right()+2, optSFX.bottom() + GAP, width/2-1, BTN_HEIGHT);

			} else {
				optMusic.setRect(0, sep1.y + 1 + GAP, width, SLIDER_HEIGHT);
				chkMusicMute.setRect(0, optMusic.bottom() + GAP, width, BTN_HEIGHT);

				sep2.size(width, 1);
				sep2.y = chkMusicMute.bottom() + GAP;

				optSFX.setRect(0, sep2.y + 1 + GAP, width, SLIDER_HEIGHT);
				chkMuteSFX.setRect(0, optSFX.bottom() + GAP, width, BTN_HEIGHT);
			}

			height = chkMuteSFX.bottom();

			if (chkIgnoreSilent != null){
				sep3.size(width, 1);
				sep3.y = chkMuteSFX.bottom() + GAP;

				chkIgnoreSilent.setRect(0, sep3.y + 1 + GAP, width, BTN_HEIGHT);
				height = chkIgnoreSilent.bottom();
			} else if (chkMusicBG != null){
				sep3.size(width, 1);
				sep3.y = chkMuteSFX.bottom() + GAP;

				chkMusicBG.setRect(0, sep3.y + 1 + GAP, width, BTN_HEIGHT);
				height = chkMusicBG.bottom();
			}
		}

	}

	private static class LangsTab extends Component{

		final static int COLS_P = 3;
		final static int COLS_L = 6;

		final static int BTN_HEIGHT = 11;

		RenderedTextBlock title;
		ColorBlock sep1;
		RenderedTextBlock txtLangInfo;
		ColorBlock sep2;
		RedButton[] lanBtns;
		ColorBlock sep3;
		RenderedTextBlock txtTranifex;
		RedButton btnCredits;

		@Override
		protected void createChildren() {
			title = PixelScene.renderTextBlock(Messages.get(this, "title"), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			sep1 = new ColorBlock(1, 1, 0xFF000000);
			add(sep1);

			final ArrayList<Languages> langs = new ArrayList<>(Arrays.asList(Languages.values()));

			Languages nativeLang = Languages.matchLocale(Locale.getDefault());
			langs.remove(nativeLang);
			//move the native language to the top.
			langs.add(0, nativeLang);

			final Languages currLang = Messages.lang();

			txtLangInfo = PixelScene.renderTextBlock(6);
			String info = "_" + Messages.titleCase(currLang.nativeName()) + "_ - ";
			if (currLang == Languages.ENGLISH) info += "This is the source language, written by the developer.";
			else if (currLang.status() == Languages.Status.O_COMPLETE) info += Messages.get(this, "completed");
			else if (currLang.status() == Languages.Status.__UNREVIEW) info += Messages.get(this, "unreviewed");
			else if (currLang.status() == Languages.Status.X_UNFINISH) info += Messages.get(this, "unfinished");
			txtLangInfo.text(info);

			if (currLang.status() == Languages.Status.__UNREVIEW) txtLangInfo.setHightlighting(true, CharSprite.WARNING);
			else if (currLang.status() == Languages.Status.X_UNFINISH) txtLangInfo.setHightlighting(true, CharSprite.削弱);
			add(txtLangInfo);

			sep2 = new ColorBlock(1, 1, 0xFF000000);
			add(sep2);

			lanBtns = new RedButton[langs.size()];
			for (int i = 0; i < langs.size(); i++){
				final int langIndex = i;
				RedButton btn = new RedButton(Messages.titleCase(langs.get(i).nativeName()), 6){
					@Override
					protected void onClick() {
						super.onClick();
						Messages.setup(langs.get(langIndex));
						ShatteredPixelDungeon.seamlessResetScene(new Game.SceneChangeCallback() {
							@Override
							public void beforeCreate() {
								SPDSettings.language(langs.get(langIndex));
								GameLog.wipe();
								Game.platform.resetGenerators();
							}
							@Override
							public void afterCreate() {
								//do nothing
							}
						});
					}
				};
				if (currLang == langs.get(i)){
					btn.textColor(TITLE_COLOR);
				} else {
					switch (langs.get(i).status()) {
						case X_UNFINISH:
							btn.textColor(0x888888);
							break;
						case __UNREVIEW:
							btn.textColor(0xBBBBBB);
							break;
					}
				}
				lanBtns[i] = btn;
				add(btn);
			}

			sep3 = new ColorBlock(1, 1, 0xFF000000);
			add(sep3);

			txtTranifex = PixelScene.renderTextBlock(5);
			txtTranifex.text(Messages.get(this, "transifex"));
			add(txtTranifex);

			if (currLang != Languages.ENGLISH) {
				String credText = Messages.titleCase(Messages.get(this, "credits"));
				btnCredits = new RedButton(credText, credText.length() > 9 ? 6 : 9) {
					@Override
					protected void onClick() {
						super.onClick();
						String[] reviewers = currLang.reviewers();
						String[] translators = currLang.translators();

						int totalCredits = 2*reviewers.length + translators.length;
						int totalTokens = 2*totalCredits; //for spaces

						//additional space for titles, and newline chars
						if (reviewers.length > 0) totalTokens+=6;
						totalTokens +=4;

						String[] entries = new String[totalTokens];
						int index = 0;
						if (reviewers.length > 0){
							entries[0] = "_";
							entries[1] = Messages.titleCase(Messages.get(LangsTab.this, "reviewers"));
							entries[2] = "_";
							entries[3] = "\n";
							index = 4;
							for (int i = 0; i < reviewers.length; i++){
								entries[index] = reviewers[i];
								if (i < reviewers.length-1) entries[index] += ", ";
								entries[index+1] = " ";
								index += 2;
							}
							entries[index] = "\n";
							entries[index+1] = "\n";
							index += 2;
						}

						entries[index] = "_";
						entries[index+1] = Messages.titleCase(Messages.get(LangsTab.this, "translators"));
						entries[index+2] = "_";
						entries[index+3] = "\n";
						index += 4;

						//reviewers are also shown as translators
						for (int i = 0; i < reviewers.length; i++){
							entries[index] = reviewers[i];
							if (i < reviewers.length-1 || translators.length > 0) entries[index] += ", ";
							entries[index+1] = " ";
							index += 2;
						}

						for (int i = 0; i < translators.length; i++){
							entries[index] = translators[i];
							if (i < translators.length-1) entries[index] += ", ";
							entries[index+1] = " ";
							index += 2;
						}

						Window credits = new Window(0, 0, Chrome.get(Chrome.Type.TOAST));

						int w = PixelScene.横屏() ? 120 : 80;
						if (totalCredits >= 25) w *= 1.5f;

						RenderedTextBlock title = PixelScene.renderTextBlock(9);
						title.text(Messages.titleCase(Messages.get(LangsTab.this, "credits")), w);
						title.hardlight(TITLE_COLOR);
						title.setPos((w - title.width()) / 2, 0);
						credits.add(title);

						RenderedTextBlock text = PixelScene.renderTextBlock(7);
						text.maxWidth(w);
						text.tokens(entries);

						text.setPos(0, title.bottom() + 4);
						credits.add(text);

						credits.resize(w, (int) text.bottom() + 2);
						ShatteredPixelDungeon.scene().addToFront(credits);
					}
				};
				add(btnCredits);
			}

		}

		@Override
		protected void layout() {
			title.setPos((width - title.width())/2, y + GAP);
			sep1.size(width, 1);
			sep1.y = title.bottom() + 3*GAP;

			txtLangInfo.setPos(0, sep1.y + 1 + GAP);
			txtLangInfo.maxWidth((int)width);

			y = txtLangInfo.bottom() + 2*GAP;
			int x = 0;

			sep2.size(width, 1);
			sep2.y = y;
			y += 2;

			int cols = PixelScene.横屏() ? COLS_L : COLS_P;
			int btnWidth = (int)Math.floor((width - (cols-1)) / cols);
			for (RedButton btn : lanBtns){
				btn.setRect(x, y, btnWidth, BTN_HEIGHT);
				btn.setPos(x, y);
				x += btnWidth+1;
				if (x + btnWidth > width){
					x = 0;
					y += BTN_HEIGHT+1;
				}
			}
			if (x > 0){
				y += BTN_HEIGHT+1;
			}

			sep3.size(width, 1);
			sep3.y = y;
			y += 2;

			if (btnCredits != null){
				btnCredits.setSize(btnCredits.reqWidth() + 2, 16);
				btnCredits.setPos(width - btnCredits.width(), y);

				txtTranifex.setPos(0, y);
				txtTranifex.maxWidth((int)btnCredits.left());

				height = Math.max(btnCredits.bottom(), txtTranifex.bottom());
			} else {
				txtTranifex.setPos(0, y);
				txtTranifex.maxWidth((int)width);

				height = txtTranifex.bottom();
			}

		}
	}
}
