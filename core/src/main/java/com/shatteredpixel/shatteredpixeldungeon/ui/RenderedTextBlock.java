

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

	//region 颜色文本渲染
	private static final int COLOR_WHITE = 0xFFFFFF;      // 默认白色
	private static final int COLOR_RESET = -1;            // 颜色重置值
	//使用文本渲染时建议空格隔开，如a == abc == c
	private synchronized void build() {
		if (tokens == null) return;

		clear();
		words = new ArrayList<>();
		boolean isColorActive = false; // 规范化变量名，替换中文“颜色在用”
		int currentColor = COLOR_RESET; // 独立维护当前颜色，避免color全局残留

		for (String str : tokens) {
			// 统一判断highlightingEnabled：所有颜色标记符都需要该开关生效
//			System.out.println(str);
			if (highlightingEnabled) {
				if (str.equals("_")) {
					isColorActive = !isColorActive; // 简化toggle逻辑
					currentColor = isColorActive ? 0xFFFF00 : COLOR_RESET;
					continue;
				}
				if (str.equals("**")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0xFF4444 : COLOR_RESET;// 红色
					continue;
				}
				if (str.equals("@@")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x3399FF : COLOR_RESET;// 蓝色
					continue;
				}  if (str.equals("++")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x44FF44 : COLOR_RESET;// 绿色
					continue;
				}  if (str.equals("^^")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0xFF4488 : COLOR_RESET;// 粉色
					continue;
				}  if (str.equals("##")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x8800FF : COLOR_RESET;// 紫色
					continue;
				}  if (str.equals("--")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x999999 : COLOR_RESET;// 灰色
					continue;
				}  if (str.equals(",,")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x000000 : COLOR_RESET;// 黑色
					continue;
				}  if (str.equals("==")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0xFF8800 : COLOR_RESET;// 橙色
					continue;
				}if (str.equals(";;")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x8F4E35 : COLOR_RESET;// 棕色
					continue;
				}if (str.equals("!!")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0xb2f2ff : COLOR_RESET;// 青色
					continue;
				}if (str.equals("??")) {
					isColorActive = !isColorActive;
					currentColor = isColorActive ? 0x2c0d49 : COLOR_RESET;// 靛色
					continue;
				}
					// 非颜色标记符，正常处理
					processNormalToken(str, isColorActive, currentColor);
			} else {
				// 高亮关闭时，直接处理普通token（不解析颜色标记符）
				processNormalToken(str, false, COLOR_RESET);
			}
		}
		layout();
	}

	/**
	 * 抽离普通token处理逻辑，提升代码复用性和可读性
	 */
	private void processNormalToken(String str, boolean isColorActive, int currentColor) {
		if (str.equals("\n")) {
			words.add(NEWLINE);
			return;
		}
		if (str.equals(" ")) {
//			words.add(SPACE);
			return;
		}

		// 清理所有颜色标记符（补充**的替换，修正转义）
		String cleanStr = str.replaceAll("_", "")
				.replaceAll("\\*\\*", "")
				.replaceAll("@@", "")
				.replaceAll("\\+\\+", "")
				.replaceAll("\\^\\^", "")
				.replaceAll("##", "")
				.replaceAll("--", "")
				.replaceAll(",,", "")
				.replaceAll("==", "")
				.replaceAll(";;", "")
				.replaceAll("!!", "")
				.replaceAll("\\?\\?", "");

		// 避免空字符串生成无效的RenderedText
		if (cleanStr.isEmpty()) {
			return;
		}

		RenderedText word = new RenderedText(cleanStr, size);

		// 颜色逻辑：激活且颜色有效时用指定颜色，否则用白色
		if (isColorActive && currentColor != COLOR_RESET) {
			word.hardlight(currentColor);
		} else {
			word.hardlight(COLOR_WHITE);
		}

		// 缩放计算：提取魔法值为注释，提升可读性
		float scaleFactor = zoom * (1 + SPDSettings.字体大小() * 0.25f);
		word.scale.set(scaleFactor);

		words.add(word);
		add(word);

		// 更新最大高度
		if (height < word.height()) {
			height = word.height();
		}
	}
	//endregion

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
