

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.badlogic.gdx.Gdx;
import com.shatteredpixel.shatteredpixeldungeon.Chrome;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.CheckBox;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.Game;
import com.watabou.noosa.TextInput;
import com.watabou.utils.DeviceCompat;

public class Wnd物品代码名 extends Window {

	private static final int WIDTH = 135;
	private static final int MARGIN = 1;
	public static final int BUTTON_HEIGHT = 16;

	protected TextInput textBox;

	protected RedButton btnCopy;
	protected RedButton btnPaste;

	private static final int GAP		= 2;
	private float pos=0;
	public float BUTTON_W = 0;
	public boolean 鉴定=true;
	public boolean 诅咒=false;

	public Wnd物品代码名(final String title,final String body,final String initialValue,final int maxLength,
						 final boolean multiLine,final String posTxt,final String negTxt) {
		super();

		final int width= WIDTH;
		pos=2;

		if (title != null) {
			final RenderedTextBlock txtTitle = PixelScene.renderTextBlock(title, 9);
			txtTitle.maxWidth(width);
			txtTitle.hardlight(Window.TITLE_COLOR);
			txtTitle.setPos((width - txtTitle.width()) / 2, 2);
			add(txtTitle);

			pos = txtTitle.bottom() + 4 * MARGIN;
		}

		if (body != null) {
			final RenderedTextBlock txtBody = PixelScene.renderTextBlock(body, 6);
			txtBody.maxWidth(width);
			txtBody.setPos(0, pos);
			add(txtBody);

			pos = txtBody.bottom() + 2 * MARGIN;
		}

		int textSize = (int)PixelScene.uiCamera.zoom * (multiLine ? 6 : 9);
		textBox = new TextInput(Chrome.get(Chrome.Type.TOAST_WHITE), multiLine, textSize){
			@Override
			public void enterPressed() {
				//triggers positive action on enter pressed, only with non-multiline though.
				onSelect(true, getText());
				hide();
			}

			@Override
			public void onChanged() {
				super.onChanged();
				if (btnCopy != null) btnCopy.enable(!getText().isEmpty());
			}

			@Override
			public void onClipBoardUpdate() {
				super.onClipBoardUpdate();
				btnPaste.enable(Gdx.app.getClipboard().hasContents());
			}
		};
		if (initialValue != null) textBox.setText(initialValue);
		textBox.setMaxLength(maxLength);

		//sets different height depending on whether this is a single or multi line input.
		final float inputHeight;
		if (multiLine) {
			inputHeight = 64; //~8 lines of text
		} else {
			inputHeight = 16;
		}

		float textBoxWidth = width-3*MARGIN-BUTTON_HEIGHT;

		add(textBox);
		textBox.setRect(MARGIN, pos, textBoxWidth, inputHeight);

		btnCopy = new RedButton(""){
			@Override
			protected void onPointerDown() {
				super.onPointerDown();
				PointerEvent.clearKeyboardThisPress = false;
			}

			@Override
			protected void onPointerUp() {
				super.onPointerUp();
				PointerEvent.clearKeyboardThisPress = false;
			}

			@Override
			protected void onClick() {
				super.onClick();
				textBox.copyToClipboard();
			}
		};
		btnCopy.icon(Icons.COPY.get());
		btnCopy.enable(!textBox.getText().isEmpty());
		add(btnCopy);

		btnPaste = new RedButton(""){
			@Override
			protected void onPointerDown() {
				super.onPointerDown();
				PointerEvent.clearKeyboardThisPress = false;
			}

			@Override
			protected void onPointerUp() {
				super.onPointerUp();
				PointerEvent.clearKeyboardThisPress = false;
			}

			@Override
			protected void onClick() {
				super.onClick();
				if (Gdx.app.getClipboard().hasContents()) {
					textBox.pasteFromClipboard();
				} else {
					enable(false);
				}
			}

		};
		btnPaste.icon(Icons.PASTE.get());
		btnPaste.enable(Gdx.app.getClipboard().hasContents());
		add(btnPaste);

		btnCopy.setRect(textBoxWidth + 2*MARGIN, pos, BUTTON_HEIGHT, BUTTON_HEIGHT);
		btnPaste.setRect(textBoxWidth + 2*MARGIN, btnCopy.bottom()+MARGIN, BUTTON_HEIGHT, BUTTON_HEIGHT);

		pos += inputHeight + MARGIN;
		CheckBox curBtn;
		CheckBox curBtn2;

		float btnWidth = multiLine ? width-2*MARGIN : textBoxWidth;
		BUTTON_W=(btnWidth - MARGIN) / 2;
		addButtons( curBtn = new CheckBox("鉴定") {
			@Override
			protected void onClick() {
				super.onClick();
				鉴定=checked();
			}
		} ,
					curBtn2 = new CheckBox("诅咒") {
			@Override
			protected void onClick() {
				super.onClick();
				诅咒=checked();
			}
		});
		curBtn.checked(鉴定);
		curBtn2.checked(诅咒);
//		curBtn.icon(new ItemSprite(物品表.ITEM));
//		curBtn2.icon(new ItemSprite(物品表.感叹));

		pos += MARGIN*2;

		final RedButton positiveBtn = new RedButton(posTxt) {
			@Override
			protected void onClick() {
				onSelect(true, textBox.getText());
				hide();
			}
		};

		final RedButton negativeBtn;
		if (negTxt != null) {
			negativeBtn = new RedButton(negTxt) {
				@Override
				protected void onClick() {
					onSelect(false, textBox.getText());
					hide();
				}
			};
		} else {
			negativeBtn = null;
		}

		if (negTxt != null) {
			positiveBtn.setRect(MARGIN, pos, (btnWidth - MARGIN) / 2, BUTTON_HEIGHT);
			add(positiveBtn);
			negativeBtn.setRect(positiveBtn.right() + MARGIN, pos, (btnWidth - MARGIN) / 2, BUTTON_HEIGHT);
			add(negativeBtn);
		} else {
			positiveBtn.setRect(MARGIN, pos, btnWidth, BUTTON_HEIGHT);
			add(positiveBtn);
		}

		pos += BUTTON_HEIGHT;

		//need to resize first before laying out the text box, as it depends on the window's camera
		resize(width, (int) pos);

		//offset 50% up to give space for the soft keyboard
		if (!DeviceCompat.hasHardKeyboard()) {
			offset(0, -(int)(Game.height/(4*camera.zoom)));
			boundOffsetWithMargin(0);
		}

		textBox.setRect(MARGIN, textBox.top(), textBoxWidth, inputHeight);

		PointerEvent.clearKeyboardThisPress = false;

	}

	@Override
	public void offset(int xOffset, int yOffset) {
		super.offset(xOffset, yOffset);
		if (textBox != null){
			textBox.setRect(textBox.left(), textBox.top(), textBox.width(), textBox.height());
		}
	}

	private void addButton( CheckBox btn ) {
		add( btn );
		btn.setRect( MARGIN, pos > 0 ? pos += GAP : 0, BUTTON_W, BUTTON_HEIGHT );
		pos += BUTTON_HEIGHT;
	}

	private void addButtons( CheckBox btn1, CheckBox btn2 ) {
		add( btn1 );
		btn1.setRect( MARGIN, pos > 0 ? pos += GAP : 0, BUTTON_W, BUTTON_HEIGHT );
		add( btn2 );
		btn2.setRect( btn1.right() + MARGIN, btn1.top(), BUTTON_W, BUTTON_HEIGHT );
		pos += BUTTON_HEIGHT;
	}
	public void onSelect(boolean positive, String text){ }

	@Override
	public void onBackPressed() {
		//Do nothing, prevents accidentally losing writing
	}
}
