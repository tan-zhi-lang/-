

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.净除道术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.RightClickMenu;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.utils.DeviceCompat;
import com.watabou.utils.PointF;

import java.util.ArrayList;

public class Wnd道术 extends Window {

	protected static final int WIDTH    = 120;

	public static int BTN_SIZE = 20;

	public Wnd道术(铜钱剑 tome,Hero cleric,boolean info){

		IconTitle title;
		if (!info){
			title = new IconTitle(new ItemSprite(tome), Messages.titleCase(Messages.get(this, "cast_title")));
		} else {
			title = new IconTitle(Icons.INFO.get(), Messages.titleCase(Messages.get(this, "info_title")));
		}

		title.setRect(0, 0, WIDTH, 0);
		add(title);

		IconButton btnInfo = new IconButton(info ? new ItemSprite(tome) : Icons.INFO.get()){
			@Override
			protected void onClick() {
				GameScene.show(new Wnd道术(tome,cleric,!info));
				hide();
			}
		};
		btnInfo.setRect(WIDTH-16, 0, 16, 16);
		add(btnInfo);

		RenderedTextBlock msg;
		if (info){
			msg = PixelScene.renderTextBlock( Messages.get( this, "info_desc"), 6);
		} else if (DeviceCompat.isDesktop()){
			msg = PixelScene.renderTextBlock( Messages.get( this, "cast_desc_desktop"), 6);
		} else {
			msg = PixelScene.renderTextBlock( Messages.get( this, "cast_desc_mobile"), 6);
		}
		msg.maxWidth(WIDTH);
		msg.setPos(0, title.bottom()+4);
		add(msg);

		int top = (int)msg.bottom()+4;

		for (int i = 1; i <= Talent.MAX_TALENT_TIERS; i++) {

			ArrayList<道术> spells = 道术.getSpellList(cleric, i);

			if (!spells.isEmpty() && i != 1){
				top += BTN_SIZE + 2;
				ColorBlock sep = new ColorBlock(WIDTH, 1, 0xFF000000);
				sep.y = top;
				add(sep);
				top += 3;
			}

			ArrayList<IconButton> spellBtns = new ArrayList<>();

			for (道术 spell : spells) {
				IconButton spellBtn = new SpellButton(spell, tome, info);
				add(spellBtn);
				spellBtns.add(spellBtn);
			}

			int left = 2 + (WIDTH - spellBtns.size() * (BTN_SIZE + 4)) / 2;
			for (IconButton btn : spellBtns) {
				btn.setRect(left, top, BTN_SIZE, BTN_SIZE);
				left += btn.width() + 4;
			}

		}

		resize(WIDTH, top + BTN_SIZE);

		//if we are on mobile, offset the window down to just above the toolbar
		if (SPDSettings.interfaceSize() != 2){
			offset(0, (int) (GameScene.uiCamera.height/2 - 30 - height/2));
		}

	}

	public class SpellButton extends IconButton {

		道术 spell;
		铜钱剑 tome;
		boolean info;

		NinePatch bg;

		public SpellButton(道术 spell,铜钱剑 tome,boolean info){
			super(new HeroIcon(spell));

			this.spell = spell;
			this.tome = tome;
			this.info = info;

			if (!tome.canCast(Dungeon.hero, spell)){
				icon.alpha( 0.3f );
			} else if (spell==净除道术.INSTANCE&&spell.chargeUse(Dungeon.hero)==0){
				icon.brightness(3);
			}

			bg = Chrome.get(Chrome.Type.TOAST);
			addToBack(bg);
		}

		@Override
		protected void onPointerDown() {
			super.onPointerDown();
			if (spell==净除道术.INSTANCE&&spell.chargeUse(Dungeon.hero)==0){
				icon.brightness(4);
			}
		}

		@Override
		protected void onPointerUp() {
			super.onPointerUp();
			if (!tome.canCast(Dungeon.hero, spell)){
				icon.alpha( 0.3f );
			} else if (spell==净除道术.INSTANCE&&spell.chargeUse(Dungeon.hero)==0){
				icon.brightness(3);
			}
		}

		@Override
		protected void layout() {
			super.layout();

			if (bg != null) {
				bg.size(width, height);
				bg.x = x;
				bg.y = y;
			}
		}

		@Override
		protected void onClick() {
			if (info){
				GameScene.show(new WndTitledMessage(new HeroIcon(spell), Messages.titleCase(spell.name()), spell.desc()));
			} else {
				hide();


				if(!tome.canCast(Dungeon.hero, spell)){
					GLog.w(Messages.get(神圣法典.class, "no_spell"));
				} else {
					spell.onCast(tome, Dungeon.hero);

					if (spell.targetingFlags() != -1 && Dungeon.quickslot.contains(tome)){
						tome.targetingSpell = spell;
						QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(tome));
					}
				}

			}
		}

		@Override
		protected boolean onLongClick() {
			hide();
			tome.setQuickSpell(spell);
			return true;
		}

		@Override
		protected void onRightClick() {
			super.onRightClick();
			RightClickMenu r = new RightClickMenu(new Image(icon),
					Messages.titleCase(spell.name()),
					Messages.get(Wnd道术.class,"cast"),
					Messages.get(Wnd道术.class,"info"),
					Messages.get(Wnd道术.class,"quick_cast")){
				@Override
				public void onSelect(int index) {
					switch (index){
						default:
							//do nothing
							break;
						case 0:
							hide();
							if(!tome.canCast(Dungeon.hero, spell)){
								GLog.w(Messages.get(神圣法典.class, "no_spell"));
							} else {
								spell.onCast(tome, Dungeon.hero);

								if (spell.targetingFlags() != -1 && Dungeon.quickslot.contains(tome)){
									tome.targetingSpell = spell;
									QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(tome));
								}
							}
							break;
						case 1:
							GameScene.show(new WndTitledMessage(new HeroIcon(spell), Messages.titleCase(spell.name()), spell.desc()));
							break;
						case 2:
							hide();
							tome.setQuickSpell(spell);
							break;
					}
				}
			};
			parent.addToFront(r);
			r.camera = camera();
			PointF mousePos = PointerEvent.currentHoverPos();
			mousePos = camera.screenToCamera((int)mousePos.x, (int)mousePos.y);
			r.setPos(mousePos.x-3, mousePos.y-3);
		}

		@Override
		protected String hoverText() {
			return "_" + Messages.titleCase(spell.name()) + "_\n" + spell.shortDesc();
		}
	}

}
