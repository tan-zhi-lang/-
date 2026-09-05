package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RenderedTextBlock extends Component{

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

	private synchronized void build() {
		if (tokens == null) return;

		clear();

		words = new ArrayList<>();
		boolean highlighting = false;
		int currentDynamicColor = -1; // 当前动态颜色，-1表示未激活

		for (String str : expandColorMarkers(tokens)) {

			// 原有高亮标记 _ 和 ** (切换高亮)
			if ((str.equals("_")) && highlightingEnabled){
				highlighting = !highlighting;
				continue; // 跳过标记本身
			}

			// ---- 新增颜色标记 ----
			if (str.equals("**")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0xFF4444  : -1; // 红色
				continue;
			}
			if (str.equals("@@")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x3399FF : -1; // 蓝色
				continue;
			}
			if (str.equals("++")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x00FF00 : -1; // 绿色
				continue;
			}
			if (str.equals("^^")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0xFF4488 : -1; // 粉色
				continue;
			}
			if (str.equals("##")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x8800FF : -1; // 紫色
				continue;
			}
			if (str.equals("--")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x999999 : -1; // 灰色
				continue;
			}
			if (str.equals(",,")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x000000 : -1; // 黑色
				continue;
			}
			if (str.equals("==")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0xFF8800 : -1; // 橙色
				continue;
			}
			if (str.equals(";;")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x8F4E35 : -1; // 棕色
				continue;
			}
			if (str.equals("!!")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0xb2f2ff : -1; // 青色
				continue;
			}
			if (str.equals("??")) {
				currentDynamicColor = (currentDynamicColor == -1) ? 0x2c0d49 : -1; // 靛色
				continue;
			}

			// 普通文本或换行、空格处理
			if (str.equals("\n")){
				words.add(NEWLINE);
			} else if (str.equals(" ")){
				words.add(SPACE);
				// 空格不渲染（保持原逻辑）
			} else {
				RenderedText word = new RenderedText(str, size);

				// 颜色优先级：高亮 > 动态颜色 > 全局硬编码颜色
				if (highlighting) {
					word.hardlight(hightlightColor);
				} else if (currentDynamicColor != -1) {
					word.hardlight(currentDynamicColor);
				} else if (color != -1) {
					word.hardlight(color);
				}
				word.scale.set(zoom);

				words.add(word);
				add(word);

				if (height < word.height()) height = word.height();
			}
		}
		layout();
	}

	//把粘连在文本中的颜色标记拆分成独立token，使标记无需用空格分隔也能被识别
	private static final Pattern MARKER_SPLITTER = Pattern.compile(
			"(?<=_)|(?=_)|(?<=\\*\\*)|(?=\\*\\*)" +
					"|(?<===)|(?===)" +
					"|(?<=\\+\\+)|(?=\\+\\+)" +
					"|(?<=@@)|(?=@@)" +
					"|(?<=\\^\\^)|(?=\\^\\^)" +
					"|(?<=##)|(?=##)" +
					"|(?<=--)|(?=--)" +
					"|(?<=,,)|(?=,,)" +
					"|(?<=;;)|(?=;;)" +
					"|(?<=!!)|(?=!!)" +
					"|(?<=\\?\\?)|(?=\\?\\?)");

	private ArrayList<String> expandColorMarkers( String[] tokens ){
		ArrayList<String> result = new ArrayList<>();
		for (String token : tokens) {
			if (token == null || token.length() <= 1) {
				result.add(token);
				continue;
			}
			for (String part : MARKER_SPLITTER.split(token)){
				if (!part.isEmpty()) result.add(part);
			}
		}
		return result;
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
				x += 1.667f;//空格宽度
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