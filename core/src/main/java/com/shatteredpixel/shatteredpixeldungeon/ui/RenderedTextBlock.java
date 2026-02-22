

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;

public class RenderedTextBlock extends Component {

	private int maxWidth = Integer.MAX_VALUE;
	public int nLines;

	private static final RenderedText SPACE = new RenderedText();
	private static final RenderedText NEWLINE = new RenderedText();
	
	protected String text;
	protected String[] tokens = null;
	protected ArrayList<RenderedText> words = new ArrayList<>();
	protected boolean multiline = false;

	private int size;
	private float zoom;
	private int color = -1;
	
	private int hightlightColor = Window.TITLE_COLOR;
	private boolean highlightingEnabled = true;

	public static final int LEFT_ALIGN = 1;
	public static final int CENTER_ALIGN = 2;
	public static final int RIGHT_ALIGN = 3;
	private int alignment = LEFT_ALIGN;
	
	public RenderedTextBlock(int size){
		this.size = size;
	}

	public RenderedTextBlock(String text, int size){
		this.size = size;
		text(text);
	}
	public void text(String text){
		this.text = text;

		if (text != null && !text.equals("")) {
			
			tokens = Game.platform.splitforTextBlock(text, multiline);
			
			build();
		}
	}

	//for manual text block splitting, a space between each word is assumed
	public void tokens(String... words){
		StringBuilder fullText = new StringBuilder();
		for (String word : words) {
			fullText.append(word);
		}
		text = fullText.toString();

		tokens = words;
		build();
	}

	public void text(String text, int maxWidth){
		this.maxWidth = maxWidth;
		multiline = true;
		text(text);
	}

	public String text(){
		return text;
	}

	public void maxWidth(int maxWidth){
		if (this.maxWidth != maxWidth){
			this.maxWidth = maxWidth;
			multiline = true;
			text(text);
		}
	}

	public int maxWidth(){
		return maxWidth;
	}

	private synchronized void build(){
		if (tokens == null) return;
		
		clear();
		words = new ArrayList<>();
		boolean 颜色在用 = false;
		for (String str : tokens){

			//if highlighting is enabled, '_' or '**' is used to toggle highlighting on or off
			// the actual symbols are not rendered
			if (str.equals("_")&& highlightingEnabled){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=hightlightColor;//黄色
			} else if (str.equals("**")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0xFF4444;//红色
			}else if (str.equals("@@")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0x3399FF;//蓝色
			}else if (str.equals("++")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0x44FF44;//绿色
			}else if (str.equals("^^")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0xFF4488;//粉色
			}else if (str.equals("##")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0x8800FF;//紫色
			}else if (str.equals("--")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0x999999;//灰色
			}else if (str.equals(",,")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0x000000;//黑色
			}else if (str.equals("==")){
				if(颜色在用)颜色在用=false;
				else 颜色在用=true;
				color=0xFF8800;//橙色
			} else if (str.equals("\n")){
				words.add(NEWLINE);
			} else if (str.equals(" ")){
				words.add(SPACE);
			} else {
				//以下注释是模拟渲染
				//==攻击==数值
				//检测到==开启橙色，并跳过渲染
				//开始渲染橙色文本
				//检测到==关闭橙色
				//颜色没使用就重置颜色
				//清理残余==
				//但仍然是橙色 攻击数值
				//未找到问题

				if(!颜色在用)color=-1;
//
//				//清理后缀
				str=str.replaceAll("_", "");
				str=str.replaceAll("@@", "");
				str=str.replaceAll("\\+\\+", "");
				str=str.replaceAll("\\^\\^", "");
				str=str.replaceAll("##", "");
				str=str.replaceAll("--", "");
				str=str.replaceAll(",,", "");
				str=str.replaceAll("==", "");

				RenderedText word = new RenderedText(str, size);

				if (颜色在用&&color!=-1) {
					word.hardlight(color);
				}else word.hardlight(0xffffff);

				word.scale.set(zoom*(1+SPDSettings.字体大小()*0.25f));

				words.add(word);
				add(word);

				if (height < word.height()) height = word.height();
			}
		}
		layout();
	}

	public synchronized void zoom(float zoom){
		this.zoom = zoom;
		for (RenderedText word : words) {
			if (word != null) word.scale.set(zoom);
		}
		layout();
	}

	public synchronized void hardlight(int color){
		this.color = color;
		for (RenderedText word : words) {
			if (word != null) word.hardlight( color );
		}
	}
	
	public synchronized void resetColor(){
		this.color = -1;
		for (RenderedText word : words) {
			if (word != null) word.resetColor();
		}
	}
	
	public synchronized void alpha(float value){
		for (RenderedText word : words) {
			if (word != null) word.alpha( value );
		}
	}
	
	public synchronized void setHightlighting(boolean enabled){
		setHightlighting(enabled, Window.TITLE_COLOR);
	}
	
	public synchronized void setHightlighting(boolean enabled, int color){
		if (enabled != highlightingEnabled || color != hightlightColor) {
			hightlightColor = color;
			highlightingEnabled = enabled;
			build();
		}
	}

	public synchronized void invert(){
		if (words != null) {
			for (RenderedText word : words) {
				if (word != null) {
					word.ra = 0.77f;
					word.ga = 0.73f;
					word.ba = 0.62f;
					word.rm = -0.77f;
					word.gm = -0.73f;
					word.bm = -0.62f;
				}
			}
		}
	}

	public synchronized void align(int align){
		alignment = align;
		layout();
	}

	@Override
	protected synchronized void layout() {
		super.layout();
		float x = this.x;
		float y = this.y;
		float height = 0;
		nLines = 1;

		ArrayList<ArrayList<RenderedText>> lines = new ArrayList<>();
		ArrayList<RenderedText> curLine = new ArrayList<>();
		lines.add(curLine);

		width = 0;
		for (int i = 0; i < words.size(); i++){
			RenderedText word = words.get(i);
			if (word == SPACE){
				x += 1.667f;
			} else if (word == NEWLINE) {
				//newline
				y += height+2f;
				x = this.x;
				nLines++;
				curLine = new ArrayList<>();
				lines.add(curLine);
			} else {
				if (word.height() > height) height = word.height();

				float fullWidth = word.width();
				int j = i+1;

				//this is so that words split only by highlighting are still grouped in layout
				//Chinese/Japanese always render every character separately without spaces however
				while (Messages.lang() != Languages.CHI_SMPL && Messages.lang() != Languages.CHI_TRAD
						&& Messages.lang() != Languages.JAPANESE
						&& j < words.size() && words.get(j) != SPACE && words.get(j) != NEWLINE){
					fullWidth += words.get(j).width() - 0.667f;
					j++;
				}

				if ((x - this.x) + fullWidth - 0.001f > maxWidth && !curLine.isEmpty()){
					y += height+2f;
					x = this.x;
					nLines++;
					curLine = new ArrayList<>();
					lines.add(curLine);
				}

				word.x = x;
				word.y = y;
				PixelScene.align(word);
				x += word.width();
				curLine.add(word);

				if ((x - this.x) > width) width = (x - this.x);
				
				//Note that spacing currently doesn't factor in halfwidth and fullwidth characters
				//(e.g. Ideographic full stop)
				x -= 0.667f;

			}
		}
		this.height = (y - this.y) + height;

		if (alignment != LEFT_ALIGN){
			for (ArrayList<RenderedText> line : lines){
				if (line.size() == 0) continue;
				float lineWidth = line.get(line.size()-1).width() + line.get(line.size()-1).x - this.x;
				if (alignment == CENTER_ALIGN){
					for (RenderedText text : line){
						text.x += (width() - lineWidth)/2f;
						PixelScene.align(text);
					}
				} else if (alignment == RIGHT_ALIGN) {
					for (RenderedText text : line){
						text.x += width() - lineWidth;
						PixelScene.align(text);
					}
				}
			}
		}
	}
}
