

package com.shatteredpixel.shatteredpixeldungeon.scenes;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Archs;
import com.shatteredpixel.shatteredpixeldungeon.ui.ExitButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.ScrollPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.StyledButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.ChangeInfo;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.WndChanges;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.WndChangesTabbed;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_1_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_2_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_3_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_4_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_5_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_6_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_7_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_8_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v0_9_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v1_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v2_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.ui.changelist.v3_X_Changes;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.Scene;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class 改动界面 extends PixelScene {

	/**
	 天赋星
	 移除一层和二层的一个天赋。
	 天赋解锁从9/12 => 7/14
	 天赋点数5/6/8=>3/6/9
	 所有天赋增强。

	 二层最大天赋点+1
	 三层最大天赋点+1

	 卷轴
	 每5层升级卷轴数量+1

	 强化符石
	 效果
	 增加伤害50%减少67%攻速
	 增加33%攻速，减少30%伤害
	 改成
	 增加100%伤害减少50%攻速
	 增加100%攻速，减少50%伤害

	 最大等级-5
	 最大生命+5
	 每级所需经验+2和初始经验+2

	 皇冠
	 移除护甲技能

	 开局还有赠送一瓶经验药剂

	 命中增加10，闪避增加5
	 升级提供额外1生命值
	 伏击额外造成1伤害，吃饭恢复1生命值

	 ---
	 铠甲，2阶，力量需求-1
	 法袍，1阶，法杖充能速度加快25%
	 风衣，1阶，闪避+25%
	 披风，1阶，移速+25%
	 胸铠，1阶，但是最小防御级+阶+1
	 祭服，1阶，等级+1，免疫降级
	 除了战士都移除水袋
	 法师魔杖关注焰浪

	 盗贼武器变双刀

	 法袍免疫火焰
	 铠甲免疫寒意

	 战士 斧头图标 力量+1
	 法师 法杖充能图标 获得经验时提供4回合法杖充能
	 牧师 星图标 所需经验-2-1

	 投掷和弓在近处命中率增加50%，超过2格减半

	 老兵直觉，装备/拾取护甲鉴定

	 学者直觉，施法和拾取法杖鉴定

	 窃贼直觉，装备和拾取戒指鉴定

	 生存直觉
	 命中和拾取投掷鉴定

	 探险直觉
	 装备和拾取鉴定

	 神圣预知
	 鉴定有无诅咒/鉴定，有诅咒净化/净化+鉴定


	 所有英雄额外一个鉴定物
	 额外一个感知符石
	 //		SPEED   (0.7f, 2/3f),
	 //		DAMAGE  (1.5f, 5/3f),
	 //		NONE	(1.0f, 1f);

	 升级提供的命中+1
	 */
	public static int changesSelected = 0;

	private NinePatch rightPanel;
	private ScrollPane rightScroll;
	private IconTitle changeTitle;
	private RenderedTextBlock changeBody;
	
	@Override
	public void create() {
		super.create();

		Music.INSTANCE.playTracks(
				new String[]{Assets.Music.THEME_1, Assets.Music.THEME_2},
				new float[]{1, 1},
				false);

		int w = Camera.main.width;
		int h = Camera.main.height;

		IconTitle title = new IconTitle(Icons.CHANGES.get(), Messages.get(this, "title"));
		title.setSize(200, 0);
		title.setPos(
				(w - title.reqWidth()) / 2f,
				(20 - title.height()) / 2f
		);
		align(title);
		add(title);

		ExitButton btnExit = new ExitButton();
		btnExit.setPos( Camera.main.width - btnExit.width(), 0 );
		add( btnExit );

		NinePatch panel = Chrome.get(Chrome.Type.TOAST);

		int pw = 135 + panel.marginLeft() + panel.marginRight() - 2;
		int ph = h - 36;

		if (h >= PixelScene.MIN_HEIGHT_FULL && w >= 300) {
			panel.size( pw, ph );
			panel.x = (w - pw) / 2f - pw/2 - 1;
			panel.y = 20;

			rightPanel = Chrome.get(Chrome.Type.TOAST);
			rightPanel.size( pw, ph );
			rightPanel.x = (w - pw) / 2f + pw/2 + 1;
			rightPanel.y = 20;
			add(rightPanel);

			rightScroll = new ScrollPane(new Component());
			add(rightScroll);
			rightScroll.setRect(
					rightPanel.x + rightPanel.marginLeft(),
					rightPanel.y + rightPanel.marginTop()-1,
					rightPanel.innerWidth() + 2,
					rightPanel.innerHeight() + 2);
			rightScroll.scrollTo(0, 0);

			changeTitle = new IconTitle(Icons.get(Icons.CHANGES), Messages.get(this, "right_title"));
			changeTitle.setPos(0, 1);
			changeTitle.setSize(pw, 20);
			rightScroll.content().add(changeTitle);

			String body = Messages.get(this, "right_body");

			changeBody = PixelScene.renderTextBlock(body, 6);
			changeBody.maxWidth(pw - panel.marginHor());
			changeBody.setPos(0, changeTitle.bottom()+2);
			rightScroll.content().add(changeBody);

		} else {
			panel.size( pw, ph );
			panel.x = (w - pw) / 2f;
			panel.y = 20;
		}
		align( panel );
		add( panel );
		
		final ArrayList<ChangeInfo> changeInfos = new ArrayList<>();

		if (Messages.lang() != Languages.ENGLISH){
			ChangeInfo langWarn = new ChangeInfo("", true, Messages.get(this, "lang_warn"));
			langWarn.hardlight(CharSprite.WARNING);
			changeInfos.add(langWarn);
		}
		
		switch (changesSelected){
			case 0: default:
				v3_X_Changes.addAllChanges(changeInfos);
				break;
			case 1:
				v2_X_Changes.addAllChanges(changeInfos);
				break;
			case 2:
				v1_X_Changes.addAllChanges(changeInfos);
				break;
			case 3:
				v0_9_X_Changes.addAllChanges(changeInfos);
				break;
			case 4:
				v0_8_X_Changes.addAllChanges(changeInfos);
				break;
			case 5:
				v0_7_X_Changes.addAllChanges(changeInfos);
				break;
			case 6:
				v0_6_X_Changes.addAllChanges(changeInfos);
				break;
			case 7:
				v0_5_X_Changes.addAllChanges(changeInfos);
				v0_4_X_Changes.addAllChanges(changeInfos);
				v0_3_X_Changes.addAllChanges(changeInfos);
				v0_2_X_Changes.addAllChanges(changeInfos);
				v0_1_X_Changes.addAllChanges(changeInfos);
				break;
		}

		ScrollPane list = new ScrollPane( new Component() ){

			@Override
			public void onClick(float x, float y) {
				for (ChangeInfo info : changeInfos){
					if (info.onClick( x, y )){
						return;
					}
				}
			}

		};
		add( list );

		Component content = list.content();
		content.clear();

		float posY = 0;
		float nextPosY = 0;
		boolean second = false;
		for (ChangeInfo info : changeInfos){
			if (info.major) {
				posY = nextPosY;
				second = false;
				info.setRect(0, posY, panel.innerWidth(), 0);
				content.add(info);
				posY = nextPosY = info.bottom();
			} else {
				if (!second){
					second = true;
					info.setRect(0, posY, panel.innerWidth()/2f, 0);
					content.add(info);
					nextPosY = info.bottom();
				} else {
					second = false;
					info.setRect(panel.innerWidth()/2f, posY, panel.innerWidth()/2f, 0);
					content.add(info);
					nextPosY = Math.max(info.bottom(), nextPosY);
					posY = nextPosY;
				}
			}
		}

		content.setSize( panel.innerWidth(), (int)Math.ceil(posY) );

		list.setRect(
				panel.x + panel.marginLeft(),
				panel.y + panel.marginTop() - 1,
				panel.innerWidth() + 2,
				panel.innerHeight() + 2);
		list.scrollTo(0, 0);

		StyledButton btn3_X = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "3.X", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 0) {
					changesSelected = 0;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 0) btn3_X.textColor( 0xBBBBBB );
		btn3_X.setRect(list.left()-4f, list.bottom(), 19, changesSelected == 0 ? 19 : 15);
		addToBack(btn3_X);

		StyledButton btn2_X = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "2.X", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 1) {
					changesSelected = 1;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 1) btn2_X.textColor( 0xBBBBBB );
		btn2_X.setRect(btn3_X.right()-2, list.bottom(), 19, changesSelected == 1 ? 19 : 15);
		addToBack(btn2_X);

		StyledButton btn1_X = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "1.X", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 2) {
					changesSelected = 2;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 2) btn1_X.textColor( 0xBBBBBB );
		btn1_X.setRect(btn2_X.right()-2, list.bottom(), 19, changesSelected == 2 ? 19 : 15);
		addToBack(btn1_X);

		StyledButton btn0_9 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "0.9", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 3) {
					changesSelected = 3;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 3) btn0_9.textColor( 0xBBBBBB );
		btn0_9.setRect(btn1_X.right()-2, list.bottom(), 19, changesSelected == 3 ? 19 : 15);
		addToBack(btn0_9);

		StyledButton btn0_8 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "0.8", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 4) {
					changesSelected = 4;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 4) btn0_8.textColor( 0xBBBBBB );
		btn0_8.setRect(btn0_9.right()-2, list.bottom(), 19, changesSelected == 4 ? 19 : 15);
		addToBack(btn0_8);
		
		StyledButton btn0_7 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "0.7", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 5) {
					changesSelected = 5;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 5) btn0_7.textColor( 0xBBBBBB );
		btn0_7.setRect(btn0_8.right()-2, btn0_8.top(), 19, changesSelected == 5 ? 19 : 15);
		addToBack(btn0_7);
		
		StyledButton btn0_6 = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "0.6", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 6) {
					changesSelected = 6;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 6) btn0_6.textColor( 0xBBBBBB );
		btn0_6.setRect(btn0_7.right()-2, btn0_8.top(), 19, changesSelected == 6 ? 19 : 15);
		addToBack(btn0_6);
		
		StyledButton btnOld = new StyledButton(Chrome.Type.GREY_BUTTON_TR, "0.5-", 8){
			@Override
			protected void onClick() {
				super.onClick();
				if (changesSelected != 7) {
					changesSelected = 7;
					ShatteredPixelDungeon.seamlessResetScene();
				}
			}
		};
		if (changesSelected != 7) btnOld.textColor( 0xBBBBBB );
		btnOld.setRect(btn0_6.right()-2, btn0_8.top(), 22, changesSelected == 7 ? 19 : 15);
		addToBack(btnOld);

		Archs archs = new Archs();
		archs.setSize( Camera.main.width, Camera.main.height );
		addToBack( archs );

		fadeIn();
	}

	private void updateChangesText(Image icon, String title, String... messages){
		if (changeTitle != null){
			changeTitle.icon(icon);
			changeTitle.label(title);
			changeTitle.setPos(changeTitle.left(), changeTitle.top());

			String message = "";
			for (int i = 0; i < messages.length; i++){
				message += messages[i];
				if (i != messages.length-1){
					message += "\n\n";
				}
			}
			changeBody.text(message);
			rightScroll.content().setSize(rightScroll.width(), changeBody.bottom()+2);
			rightScroll.setSize(rightScroll.width(), rightScroll.height());
			rightScroll.scrollTo(0, 0);

		} else {
			if (messages.length == 1) {
				addToFront(new WndChanges(icon, title, messages[0]));
			} else {
				addToFront(new WndChangesTabbed(icon, title, messages));
			}
		}
	}

	public static void showChangeInfo(Image icon, String title, String... messages){
		Scene s = ShatteredPixelDungeon.scene();
		if (s instanceof 改动界面){
			((改动界面) s).updateChangesText(icon, title, messages);
			return;
		}
		if (messages.length == 1) {
			s.addToFront(new WndChanges(icon, title, messages[0]));
		} else {
			s.addToFront(new WndChangesTabbed(icon, title, messages));
		}
	}
	
	@Override
	protected void onBackPressed() {
		ShatteredPixelDungeon.switchNoFade(TitleScene.class);
	}

}
