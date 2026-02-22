
package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.PointF;

public class ResistanceIndicator extends Component {
	private Char charRef;

	private static final int FONT_SIZE = 16;
	private static final float GAP = 3f;

	private static final int ICON_COLS = 3;
	private static final float ICON_HEIGHT = 12f;

	public ResistanceIndicator(Char charRef) {
		this.charRef = charRef;
	}

	@Override
	protected void layout() {
		RenderedText elementalText = new RenderedText("受到",FONT_SIZE);
		elementalText.scale=new PointF(0.27f,0.27f);
		elementalText.setPos(this.x,this.y);
		add(elementalText);
		int x=0;
		if(charRef.酸性()||charRef.植物()||charRef.寒冰()){
			x=1;
		}
		if(charRef.火焰()){
			x=1;
		}
		if(charRef.动物()){
			x=4;
		}
		for (int i = 0; i < x; i++) {
			int r = i / ICON_COLS;
			int c = i % ICON_COLS;
			String s="";
			float v=1;
			if(charRef.酸性()||charRef.植物()){
				if(i==0){
					s= "火焰伤害";
					v=0.5f;
				}
			}
			if(charRef.寒冰()){
				if(i==0){
					s="火焰伤害";
					v=0.5f;
				}
				if(i==1){
					s="冰霜伤害";
					v=-0.5f;
				}
			}
			if(charRef.火焰()){
				if(i==0){
					s="冰霜伤害";
					v=0.5f;
				}
				if(i==1){
					s="火焰伤害";
					v=-0.5f;
				}
			}
			if(charRef.动物()){
				if(i==0){
					s="火焰伤害";
					v=0.5f;
				}
				if(i==1){
					s="冰霜伤害";
					v=0.5f;
				}
				if(i==2){
					s="酸性伤害";
					v=0.5f;
				}
				if(i==3){
					s="无机伤害";
					v=0.5f;
				}
			}

			RenderedText txt = makePercentText(s,v);;
			txt.setPos(GAP + (width - GAP * 2f) / ICON_COLS * c + GAP ,
					   elementalText.y + elementalText.height() + GAP + ICON_HEIGHT * r
					   + (- txt.height()) / 2f);
			add(txt);
		}

		RenderedText magicalLine = new RenderedText("造成伤害",FONT_SIZE);
		magicalLine.hardlight(0xFF8800);
		magicalLine.scale=new PointF(0.27f,0.27f);
		magicalLine.setPos(elementalText.x+ GAP,elementalText.y + elementalText.height() + ICON_HEIGHT * 2f + GAP);
		add(magicalLine);

		RenderedText magicalTxt = makePercentText(charRef.伤害());
		magicalTxt.x = magicalLine.x + magicalLine.width() + GAP;
		magicalTxt.y = magicalLine.y;
		add(magicalTxt);

		RenderedText magicalLine2 = new RenderedText("元素抗性",FONT_SIZE);
		magicalLine2.hardlight(0x8800FF);
		magicalLine2.scale=new PointF(0.27f,0.27f);
		magicalLine2.setPos(elementalText.x+elementalText.width+ GAP,elementalText.y + elementalText.height() + ICON_HEIGHT * 2f + GAP);
		add(magicalLine2);

		RenderedText magicalTxt2 = makePercentText(RingOfElements.resist(charRef,Char.魔法伤害.class)-1);
		magicalTxt2.x = magicalLine2.x + magicalLine2.width()+ GAP;
		magicalTxt2.y = magicalLine.y;
		add(magicalTxt2);

		height = elementalText.height() + ICON_HEIGHT * 2.25f//2
				 + GAP + magicalLine.height();

		ColorBlock bg = new ColorBlock(width + 2f, height + 2f, 0xd680876f);
		bg.x = x - 1f;
		bg.y = y - 2f;
		add(bg);
		sendToBack(bg);
	}

	private RenderedText makePercentText(String ts,float value) {
		int percent = Math.round(value * 100f);
		RenderedText line = new RenderedText(ts+" "+percent+"%",FONT_SIZE);
		line.scale=new PointF(0.27f,0.27f);
		if (percent > 0) {
			line = new RenderedText(ts+" +"+percent+"%",FONT_SIZE);
			line.scale=new PointF(0.27f,0.27f);
		}else if (percent == 0) {
			line = new RenderedText(ts+" "+percent+"%",FONT_SIZE);
			line.scale=new PointF(0.27f,0.27f);
		} else if (percent < 0) {
			line = new RenderedText(ts+" -"+percent+"%",FONT_SIZE);
			line.scale=new PointF(0.27f,0.27f);
		}
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
	private RenderedText makePercentText(float value) {
		int percent = Math.round(value * 100f);
		RenderedText line = new RenderedText(percent+"%",FONT_SIZE);
		line.scale=new PointF(0.27f,0.27f);
		if (percent > 0) {
			line.hardlight(0x44FF44);
		}else if (percent == 0) {
			line.hardlight(0xf1ca3e);
		} else if (percent < 0) {
			line.hardlight(0xff0000);
		}
		return line;
	}
}