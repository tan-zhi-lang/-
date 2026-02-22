

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TalentsPane extends ScrollPane {

	ArrayList<TalentTierPane> panes = new ArrayList<>();
	ArrayList<ColorBlock> separators = new ArrayList<>();

	ColorBlock sep;
	ColorBlock blocker;
	RenderedTextBlock blockText;

	public TalentsPane( TalentButton.Mode mode ) {
		this( mode, Dungeon.hero.talents );
	}

	public TalentsPane( TalentButton.Mode mode, ArrayList<LinkedHashMap<Talent, Integer>> talents ) {
		super(new Component());

		int tiersAvailable = 1;

		if (mode == TalentButton.Mode.INFO){
			if (Badges.isUnlocked(Badges.Badge.LEVEL_REACHED_1)||算法.isDebug()){
				tiersAvailable = 1;
				if (Badges.isUnlocked(Badges.Badge.LEVEL_REACHED_2) || Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_1)||算法.isDebug()){
					tiersAvailable = 2;
//					  if (Badges.isUnlocked(Badges.Badge.LEVEL_REACHED_3)){
//						tiersAvailable = 3;
//						if(Badges.isUnlocked(Badges.Badge.LEVEL_REACHED_4)||Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_4)){
//							tiersAvailable = Talent.MAX_TALENT_TIERS;
//						}
//					}
				}
			}
		} else {
			while (tiersAvailable < Talent.MAX_TALENT_TIERS
					&& Dungeon.hero.等级 +1 > Talent.天赋解锁[tiersAvailable+1]
			){
				tiersAvailable++;
			}
			if (tiersAvailable > 1&&!Badges.local.contains(Badges.Badge.BOSS_SLAIN_1)&&!算法.isDebug())
				tiersAvailable = 1;
			
			if(tiersAvailable > 2&&!Badges.local.contains(Badges.Badge.BOSS_SLAIN_2)&&Dungeon.hero.subClass==HeroSubClass.NONE)
				tiersAvailable = 2;
			
//			if(tiersAvailable > 3)
//				tiersAvailable = 3;
		}

		tiersAvailable = Math.min(tiersAvailable, talents.size());

		for (int i = 0; i < Math.min(tiersAvailable, talents.size()); i++){
			if (talents.get(i).isEmpty()) continue;

			TalentTierPane pane = new TalentTierPane(talents.get(i), i+1, mode);
			panes.add(pane);
			content.add(pane);

			ColorBlock sep = new ColorBlock(0, 1, 0xFF000000);
			separators.add(sep);
			content.add(sep);
		}

		sep = new ColorBlock(0, 1, 0xFF000000);
		content.add(sep);

		blocker = new ColorBlock(0, 0, 0xFF222222);
		content.add(blocker);

		if (tiersAvailable == 1) {
			blockText = PixelScene.renderTextBlock(Messages.get(this, "unlock_tier2"), 6);
			content.add(blockText);
		} else if (tiersAvailable == 2) {
			blockText = PixelScene.renderTextBlock(Messages.get(this, "unlock_tier3"), 6);
			content.add(blockText);
		} else if (tiersAvailable == 3) {
			blockText = PixelScene.renderTextBlock(Messages.get(this, "unlock_tier4"), 6);
			content.add(blockText);
		} else {
			blockText = null;
		}

		for (int i = panes.size()-1; i >= 0; i--){
			content.bringToFront(panes.get(i));
		}
	}

	@Override
	protected void layout() {
		super.layout();

		float top = 0;
		for (int i = 0; i < panes.size(); i++){
			top += 2;
			panes.get(i).setRect(x, top, width, 0);
			top = panes.get(i).bottom();

			separators.get(i).x = 0;
			separators.get(i).y = top + 2;
			separators.get(i).size(width, 1);

			top += 3;

		}

		float bottom;
		if (blockText != null) {
			bottom = Math.max(height, top + 20);

			blocker.x = 0;
			blocker.y = top;
			blocker.size(width, bottom - top);

			blockText.maxWidth((int) width);
			blockText.align(RenderedTextBlock.CENTER_ALIGN);
			blockText.setPos((width - blockText.width()) / 2f, blocker.y + (bottom - blocker.y - blockText.height()) / 2);
		} else {
			bottom = Math.max(height, top);

			blocker.visible = false;
		}

		content.setSize(width, bottom);
	}

	public static class TalentTierPane extends Component {

		private int tier;

		public RenderedTextBlock title;
		ArrayList<TalentButton> buttons;

		ArrayList<Image> stars = new ArrayList<>();
		IconButton random;

		public TalentTierPane(LinkedHashMap<Talent, Integer> talents, int tier, TalentButton.Mode mode){
			super();

			this.tier = tier;

			title = PixelScene.renderTextBlock(Messages.titleCase(Messages.get(TalentsPane.class, "tier", tier)), 9);
			title.hardlight(Window.TITLE_COLOR);
			add(title);

			if (mode == TalentButton.Mode.UPGRADE) {
				setupStars();
				if (Dungeon.hero.talentPointsAvailable(tier) > 0){

					random = new IconButton(Icons.SHUFFLE.get()){
						@Override
						protected void onClick() {
							super.onClick();
							GameScene.show(new WndOptions(
									Icons.SHUFFLE.get(),
									Messages.get(TalentsPane.class, "random_title"),
									Messages.get(TalentsPane.class, "random_sure"),
									Messages.get(TalentsPane.class, "random_yes"),
									Messages.get(TalentsPane.class, "random_one"),
									Messages.get(TalentsPane.class, "random_no")) {
								@Override
								protected void onSelect(int index) {
									super.onSelect(index);
									//safety check to ensure previous UI is still there
									if (TalentTierPane.this.parent == null){
										return;
									}
									if (index == 0 || index == 1){
										while (Dungeon.hero.talentPointsAvailable(tier) > 0){
											TalentButton button = Random.element(buttons);
											if (Dungeon.hero.天赋点数(button.talent) < button.talent.最大点数()){
												button.upgradeTalent();
												if (index == 1){
													break;
												}
											}
										};
										setupStars();
										TalentTierPane.this.layout();
									}
								}
							});
						};
					};
//					add(random);//随机天赋
				}
			}

			buttons = new ArrayList<>();
			for (Talent talent : talents.keySet()){
				TalentButton btn = new TalentButton(tier, talent, talents.get(talent), mode){
					@Override
					public void upgradeTalent() {
						super.upgradeTalent();
						if (parent != null) {
							setupStars();
							TalentTierPane.this.layout();
						}
					}
				};
				if(Dungeon.hero()){
					if(Dungeon.hero.天赋(Talent.知识)){
						if(talent==Talent.勇武||talent==Talent.备战)
							continue;
						if(talent==Talent.技巧||talent==Talent.久战||talent==Talent.突袭||talent==Talent.武装)
							continue;
					}else{
						if(talent==Talent.埋伏||talent==Talent.招架)
							continue;
					}
					if(Dungeon.hero.天赋(Talent.勇武)){
						if(talent==Talent.知识||talent==Talent.备战)
							continue;
						if(talent==Talent.埋伏||talent==Talent.招架||talent==Talent.突袭||talent==Talent.武装)
							continue;
					}else{
						if(talent==Talent.技巧||talent==Talent.久战)
							continue;
					}
					if(Dungeon.hero.天赋(Talent.备战)){
						if(talent==Talent.知识||talent==Talent.勇武)
							continue;
						if(talent==Talent.埋伏||talent==Talent.招架||talent==Talent.技巧||talent==Talent.久战)
							continue;
					}else{
						if(talent==Talent.突袭||talent==Talent.武装)
							continue;
					}
					
					//******************************
					
					if(Dungeon.hero.天赋(Talent.健身)){
						if(talent==Talent.破绽||talent==Talent.寻觅||talent==Talent.静步)
							continue;
						if(talent==Talent.集中||talent==Talent.财富||talent==Talent.躲避
						   
						   ||talent==Talent.近视||talent==Talent.夜视||talent==Talent.戒备
						   
						   ||talent==Talent.快攻||talent==Talent.丝路||talent==Talent.速跑)
							continue;
					}else{
						if(talent==Talent.猛攻||talent==Talent.硬肤||talent==Talent.坚韧)
							continue;
					}
					if(Dungeon.hero.天赋(Talent.破绽)){
						if(talent==Talent.健身||talent==Talent.寻觅||talent==Talent.静步)
							continue;
						if(talent==Talent.猛攻||talent==Talent.硬肤||talent==Talent.坚韧
						   
						   ||talent==Talent.近视||talent==Talent.夜视||talent==Talent.戒备
						   
						   ||talent==Talent.快攻||talent==Talent.丝路||talent==Talent.速跑)
							continue;
					}else{
						if(talent==Talent.集中||talent==Talent.财富||talent==Talent.躲避)
							continue;
					}
					if(Dungeon.hero.天赋(Talent.寻觅)){
						if(talent==Talent.健身||talent==Talent.破绽||talent==Talent.静步)
							continue;
						if(talent==Talent.猛攻||talent==Talent.硬肤||talent==Talent.坚韧
						   
						   ||talent==Talent.集中||talent==Talent.财富||talent==Talent.躲避
						   
						   ||talent==Talent.快攻||talent==Talent.丝路||talent==Talent.速跑)
							continue;
					}else{
						if(talent==Talent.近视||talent==Talent.夜视||talent==Talent.戒备)
							continue;
					}
					if(Dungeon.hero.天赋(Talent.静步)){
						if(talent==Talent.健身||talent==Talent.破绽||talent==Talent.寻觅)
							continue;
						if(talent==Talent.猛攻||talent==Talent.硬肤||talent==Talent.坚韧
						   
						   ||talent==Talent.集中||talent==Talent.财富||talent==Talent.躲避
						   
						   ||talent==Talent.近视||talent==Talent.夜视||talent==Talent.戒备)
							continue;
					}else{
						if(talent==Talent.快攻||talent==Talent.丝路||talent==Talent.速跑)
							continue;
					}
				}else{
						if(talent==Talent.埋伏||talent==Talent.招架
						   
						   ||talent==Talent.技巧||talent==Talent.久战
						   
						   ||talent==Talent.突袭||talent==Talent.武装)
							continue;
						
						
					if(talent==Talent.猛攻||talent==Talent.硬肤||talent==Talent.坚韧
					
					||talent==Talent.集中||talent==Talent.财富||talent==Talent.躲避
					
					||talent==Talent.近视||talent==Talent.夜视||talent==Talent.戒备
					
					||talent==Talent.快攻||talent==Talent.丝路||talent==Talent.速跑
					)
						continue;
				}
				buttons.add(btn);
				add(btn);
			}

		}

		private void setupStars(){
			if (!stars.isEmpty()){
				for (Image im : stars){
					im.killAndErase();
				}
				stars.clear();
			}

			int totStars = Talent.天赋解锁[tier+1] - Talent.天赋解锁[tier] + Dungeon.hero.bonusTalentPoints(tier);

			int openStars = Dungeon.hero.talentPointsAvailable(tier);
			int usedStars = Dungeon.hero.talentPointsSpent(tier);
			for (int i = 0; i < totStars; i++){
				Image im = new Speck().image(Speck.STAR);
				stars.add(im);
				add(im);
				if (i >= openStars && i < (openStars + usedStars)){
					im.tint(0.75f, 0.75f, 0.75f, 0.9f);
				} else if (i >= (openStars + usedStars)){
					im.tint(0f, 0f, 0f, 0.9f);
				}
			}
			if (random != null && openStars == 0){
				random.killAndErase();
				random.destroy();
				random = null;
			}
		}

		@Override
		protected void layout() {
			super.layout();

			int regStars = Talent.天赋解锁[tier+1] - Talent.天赋解锁[tier];

			float titleWidth = title.width();
			titleWidth += 2 + Math.min(stars.size(), regStars)*6;
			title.setPos(x + (width - titleWidth)/2f, y);

			float left = title.right() + 2;

			float starTop = title.top();
			if (regStars < stars.size()) starTop -= 2;

			for (Image star : stars){
				star.x = left;
				star.y = starTop;
				PixelScene.align(star);
				left += 6;
				regStars--;
				if (regStars == 0){
					starTop += 6;
					left = title.right() + 2;
				}
			}

			if (random != null){
				random.setRect(width - 16, y-2, 16, 14);
			}
			float gap = (width - buttons.size()*TalentButton.WIDTH)/(buttons.size()+1);
			left = x + gap;
			for (TalentButton btn : buttons){
				btn.setPos(left, title.bottom() + 4);
				PixelScene.align(btn);
				left += btn.width() + gap;
			}

			height = buttons.get(0).bottom() - y;

		}

	}
}
