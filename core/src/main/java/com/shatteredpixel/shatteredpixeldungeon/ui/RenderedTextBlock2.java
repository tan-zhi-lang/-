

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wndinfo;
import com.watabou.noosa.Game;
import com.watabou.noosa.RenderedText;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class RenderedTextBlock2 extends Button{
	private static final LinkedHashMap<String, String> TERM_EXPLAIN = new LinkedHashMap<>();
	static {
		//英雄面板、Buff、改动、英雄信息、怪物、物品、信息、标题信息、天赋信息、解压系统派对、挑战炼狱赛季
		// 长词在前，避免短词提前匹配
		TERM_EXPLAIN.put("战斗状态", "_战斗状态_ :攻击和防御时获得5回合的战斗状态\n");
		TERM_EXPLAIN.put("连杀状态", "_连杀状态_ :击杀敌人会获得10回合连杀状态\n");
		TERM_EXPLAIN.put("首次攻击", "_首次攻击_ :对每个敌人的首次攻击\n");
		TERM_EXPLAIN.put("近战攻击", "_近战攻击_ :敌人受到攻击时距离你小于等于2\n");
		TERM_EXPLAIN.put("远程攻击", "_远程攻击_ :敌人受到攻击时距离你大于2\n");

		TERM_EXPLAIN.put("攻击", "==攻击== :正常来说是最小~最大攻击，物理攻击，如果有攻击效果则是空手、武器或武器投掷造成伤害触发\n");
		TERM_EXPLAIN.put("攻击伤害", "==攻击伤害== :物理攻击额外造成百分比伤害\n");
		TERM_EXPLAIN.put("狙击", "_狙击_ :敌人受到伤害时距离你大于7\n");
		TERM_EXPLAIN.put("防御", "++防御++ :正常来说是最小~最大防御，物理防御，在受到物理攻击经过防御减免伤时优先扣护盾>护甲>生命，如果有防御效果则是受到来自敌人的攻击触发\n");
		TERM_EXPLAIN.put("物理抗性", "++物理抗性++ :在受到物理攻击伤害经过百分比减免，先物理抗性后防御\n");
		TERM_EXPLAIN.put("元素抗性", "@@元素抗性@@ : 在受到魔法伤害经过百分比减免，并且大部分负面Buff施加也会百分比减免，先元素抗性后魔抗\n");
		TERM_EXPLAIN.put("魔抗", "@@魔抗@@ :正常来说是最小~最大防御，魔法防御，在受到魔法伤害经过防御减免\n");

		TERM_EXPLAIN.put("已损失", "_已损失_ :(最大属性-属性)/最大属性\n");
		TERM_EXPLAIN.put("大残", "_大残_ :生命值低于15%的状态\n");
		TERM_EXPLAIN.put("半血以下", "_半血以下_ :生命值低于50%的状态\n");
		TERM_EXPLAIN.put("半血以上", "_半血以上_ :生命值高于50%的状态\n");
		TERM_EXPLAIN.put("半血", "_半血_ :生命值低于等于60%并且高于等于40%的状态\n");
		TERM_EXPLAIN.put("残血", "_残血_ :生命值低于40%的状态\n");
		TERM_EXPLAIN.put("康血", "_康血_ :生命值高于60%的状态\n");

		TERM_EXPLAIN.put("综合属性", "++综合属性++ :影响生命和护甲、命中和闪避、攻速和移速\n");
		TERM_EXPLAIN.put("治疗护盾", "++治疗护盾++ :影响恢复生命、恢复护甲、获得护盾的加成\n");
		TERM_EXPLAIN.put("全能吸血", "**全能吸血** :敌人受到的大部分伤害为你恢复伤害x全能吸血的生命\n");
		TERM_EXPLAIN.put("吸血", "**吸血** :造成的物理攻击伤害为你恢复伤害x吸血的生命\n");

		TERM_EXPLAIN.put("穿甲", "==穿甲== :攻击伤害无视敌人固定值防御，先护甲穿透后穿甲\n");
		TERM_EXPLAIN.put("护甲穿透", "==护甲穿透== :攻击伤害无视敌人百分比防御，先护甲穿透后穿甲\n");
		TERM_EXPLAIN.put("法穿", "##法穿## :魔法伤害无视敌人固定值防御，先法术穿透后法穿\n");
		TERM_EXPLAIN.put("法术穿透", "##法术穿透## :魔法伤害无视敌人百分比防御，先法术穿透后法穿\n");
		TERM_EXPLAIN.put("幸运值", "^^幸运值^^ :影响你能想到的大部分概率事件\n");

		TERM_EXPLAIN.put("生命", "**生命** :能受到所有伤害的属性，生命为0即死亡\n");
		TERM_EXPLAIN.put("护甲", "@@护甲@@ :只能受到物理攻击伤害的属性，自带10%伤害减免\n");
		TERM_EXPLAIN.put("饥饿", "++饥饿++ :大于0生命值自然再生，为0则每回合扣血\n");
		TERM_EXPLAIN.put("饱腹", "++饱腹++ :吃下超过450饥饿的食物会将其转为饱腹Buff，并且会恢复更多生命\n");

		TERM_EXPLAIN.put("暴击率", "_暴击率_ :物理攻击有暴击率的概率造额外造成额外伤害，并且暴击率/600次没有触发暴击时，下次物理攻击必定暴击\n");
		TERM_EXPLAIN.put("暴击伤害", "_暴击伤害_ :物理攻击暴击造成的额外百分比伤害，并且超过100%的暴击率1/3转为暴击伤害\n");
		TERM_EXPLAIN.put("惊醒距离", "##惊醒距离## :你的行动把敌人从睡眠状态变成惊醒状态的距离\n");
		TERM_EXPLAIN.put("隐匿", "##隐匿## :敌人的行动，从地图上寻找到你的位置的机会\n");
		TERM_EXPLAIN.put("搜索范围", "!!搜索范围!! :双击放大镜搜索的范围\n");
		TERM_EXPLAIN.put("感知范围", "!!感知范围!! :透过墙体等，直接获取到范围内敌人的视野\n");

		TERM_EXPLAIN.put("主属性", "_主属性_ :最大攻击和防御+主属性-10\n");
		TERM_EXPLAIN.put("力量", "_力量_ :每点力量提供1%治疗护盾，0.4%暴击率，1最大生命，以及影响空手的攻击伤害，武器的适配条件和额外伤害\n");
		TERM_EXPLAIN.put("敏捷", "_敏捷_ :每点敏捷提供0.5最大护甲，以及武器、防具、空手、裸衣的命中、攻速、闪避、移速\n");
		TERM_EXPLAIN.put("魔力", "_魔力_ :每点魔力提供5%武器、法杖、神器充能速度，最大魔抗+魔力-10，以及法杖、法、巫、道、忍术的收益\n");
	}

	@Override
	protected void onClick() {
		//文本注解ps
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, String> e : TERM_EXPLAIN.entrySet()) {
			if (text.contains(e.getKey())) {
				s.append(e.getValue());
			}
		}
		if (s.length() > 0) {
			Game.scene().addToFront(new Wndinfo("文本注解", s.toString()));
		}
	}
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

	public RenderedTextBlock2(int size){
		this.size = size;
	}

	public RenderedTextBlock2(String text,int size){
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
		add(hotArea); // 重新加回触摸热区

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
					currentColor = isColorActive ? 0x00FF00 : COLOR_RESET;// 绿色
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

		//the ~ symbol is more commonly used in Chinese
//		if (Messages.lang() == Languages.CHI_SMPL || Messages.lang() == Languages.CHI_TRAD){
//			str = str.replace('-', '~');
//			str = str.replace('-', '~');
//		}
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
