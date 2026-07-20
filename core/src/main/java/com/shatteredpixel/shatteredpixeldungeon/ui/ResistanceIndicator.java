
package com.shatteredpixel.shatteredpixeldungeon.ui;

import static com.shatteredpixel.shatteredpixeldungeon.算法.zf;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;

public class ResistanceIndicator extends Component {
	private Char charRef;

	private static final int FONT_SIZE = 32;
	private static final float GAP = 3f;

	private static final int ICON_COLS = 2;
	private static final float ICON_HEIGHT = 8;//12

	public ResistanceIndicator(Char charRef) {
		this.charRef = charRef;
	}

	@Override
	protected void layout() {
		RenderedText elementalText = new RenderedText("受到",FONT_SIZE);
		elementalText.scale(0.15f);
		elementalText.setPos(this.x,this.y);
		add(elementalText);
		for (int i = 0; i < 4; i++) {
			int r = i / ICON_COLS;
			int c = i % ICON_COLS;
			String s="";
			float x1=0;
			if(i==0){
				s="火焰伤害";
				x1=charRef.d火焰();
			}
			if(i==1){
				s="冰霜伤害";
				x1=charRef.d冰霜();
			}
			if(i==2){
				s="酸性伤害";
				x1=charRef.d酸性();
			}
			if(i==3){
				s="无机伤害";
				x1=charRef.d无机();
			}

			RenderedText txt = makePercentText(s,x1);
			txt.setPos(GAP + (width - GAP * 2f) / ICON_COLS * c + GAP ,
					   elementalText.y + elementalText.height() + GAP +ICON_HEIGHT * r
					   + (- txt.height()) / 2f);
			add(txt);
			height = elementalText.height() + ICON_HEIGHT
					 + GAP + txt.height();
		}

//		RenderedText magicalLine = new RenderedText("造成伤害",FONT_SIZE);
//		magicalLine.hardlight(0xFF8800);
//		magicalLine.scale=new PointF(0.27f,0.27f);
//		magicalLine.setPos(elementalText.x+ GAP,elementalText.y + elementalText.height() + ICON_HEIGHT * 2f + GAP);
//		add(magicalLine);

//		RenderedText magicalTxt = makePercentText(charRef.伤害());
//		magicalTxt.x = magicalLine.x + magicalLine.width() + GAP;
//		magicalTxt.y = magicalLine.y;
//		add(magicalTxt);

//		RenderedText magicalLine2 = new RenderedText("元素抗性",FONT_SIZE);
//		magicalLine2.hardlight(0x8800FF);
//		magicalLine2.scale=new PointF(0.27f,0.27f);
//		magicalLine2.setPos(elementalText.x+elementalText.width+ GAP,elementalText.y + elementalText.height() + ICON_HEIGHT * 2f + GAP);
//		add(magicalLine2);
//
//		RenderedText magicalTxt2 = makePercentText(RingOfElements.resist(charRef,Dungeon.class)-1);
//		magicalTxt2.x = magicalLine2.x + magicalLine2.width()+ GAP;
//		magicalTxt2.y = magicalLine.y;
//		add(magicalTxt2);
//
//		height = elementalText.height() + ICON_HEIGHT * 2.25f//2
//				 + GAP + magicalLine.height();

		ColorBlock bg = new ColorBlock(width + 2f, height + 2f, 0xd680876f);
		bg.x = x - 1f;
		bg.y = y - 2f;
		add(bg);
		sendToBack(bg);
	}

	private RenderedText makePercentText(String ts,float value) {
		RenderedText line = new RenderedText(ts+
			 zf(value)+"倍",FONT_SIZE);
		line.scale(0.15f);
		if(ts.equals("火焰伤害"))
			line.hardlight(0xFF8800);
		else if(ts.equals("冰霜伤害"))
			line.hardlight(0x3399FF);
		else if(ts.equals("酸性伤害"))
			line.hardlight(0x44FF44);
		else if(ts.equals("无机伤害"))
			line.hardlight(0xff0000);
		return line;
	}

}