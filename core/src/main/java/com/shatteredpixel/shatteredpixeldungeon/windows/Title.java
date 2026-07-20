

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.ui.Component;

public class Title extends Component {

	private static final float FONT_SIZE = 9;

	private static final float GAP = 2;

	protected RenderedTextBlock tfLabel;

	private float healthLvl = Float.NaN;

	public Title() {
		super();
	}


	public Title(String label) {
		label( label );
		layout();
	}

	@Override
	protected void createChildren() {
		tfLabel = PixelScene.renderTextBlock( (int)FONT_SIZE );
		tfLabel.hardlight( Window.TITLE_COLOR );
		tfLabel.setHightlighting(false);
		add( tfLabel );

	}

	@Override
	protected void layout() {

		int imWidth = 0;
		int imHeight = 0;

		tfLabel.maxWidth((int)(width - (imWidth + GAP)));
		tfLabel.setPos(x + imWidth + GAP,
						imHeight > tfLabel.height() ? y +(imHeight - tfLabel.height()) / 2 : y);
		PixelScene.align(tfLabel);

		height = Math.max( imHeight, tfLabel.height() );

	}

	public float reqWidth(){
		return tfLabel.width() + GAP;
	}

	public void label( String label ) {
		tfLabel.text( label );
	}

	public void label( String label, int color ) {
		tfLabel.text( label );
		tfLabel.hardlight( color );
	}

	public void color( int color ) {
		tfLabel.hardlight( color );
	}
	public void alpha( float value ){
		tfLabel.alpha(value);
	}
}
