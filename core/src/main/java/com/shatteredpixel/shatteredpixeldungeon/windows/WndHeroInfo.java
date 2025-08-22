

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.WraithSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.TalentsPane;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WndHeroInfo extends WndTabbed {

	private HeroInfoTab heroInfo;
	private TalentInfoTab talentInfo;
	private SubclassInfoTab subclassInfo;
	private ArmorAbilityInfoTab abilityInfo;

	private static int WIDTH = 120;
	private static int MIN_HEIGHT = 125;
	private static int MARGIN = 2;

	public WndHeroInfo( HeroClass cl ){

		Image tabIcon;
		switch (cl){
			default:
				tabIcon = new ItemSprite(物品表.BACKPACK, null);
				break;
			case WARRIOR:
				tabIcon = new ItemSprite(物品表.破损纹章, null);
				break;
			case MAGE:
				tabIcon = new ItemSprite(物品表.法师魔杖, null);
				break;
			case 盗贼:
				tabIcon = new ItemSprite(物品表.ARTIFACT_CLOAK, null);
				break;
			case HUNTRESS:
				tabIcon = new ItemSprite(物品表.灵能短弓, null);
				break;
			case DUELIST:
				tabIcon = new ItemSprite(物品表.RAPIER, null);
				break;
			case CLERIC:
				tabIcon = new ItemSprite(物品表.神圣法典, null);
				break;
			case 巫女:
				tabIcon = new ItemSprite(物品表.灵月法杖, null);
				break;
			case 重武:
				tabIcon = new ItemSprite(物品表.冰门重盾, null);
				break;
		}

		int finalHeight = MIN_HEIGHT;

		heroInfo = new HeroInfoTab(cl);
		add(heroInfo);
		heroInfo.setSize(WIDTH, MIN_HEIGHT);
		finalHeight = (int)Math.max(finalHeight, heroInfo.height());

		add( new IconTab( tabIcon ){
			@Override
			protected void select(boolean value) {
				super.select(value);
				heroInfo.visible = heroInfo.active = value;
			}
		});

		talentInfo = new TalentInfoTab(cl);
		add(talentInfo);
		talentInfo.setSize(WIDTH, MIN_HEIGHT);
		finalHeight = (int)Math.max(finalHeight, talentInfo.height());

		add( new IconTab( Icons.get(Icons.TALENT) ){
			@Override
			protected void select(boolean value) {
				super.select(value);
				talentInfo.visible = talentInfo.active = value;
			}
		});

		if (Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_2) || 算法.isDebug()) {
			subclassInfo = new SubclassInfoTab(cl);
			add(subclassInfo);
			subclassInfo.setSize(WIDTH, MIN_HEIGHT);
			finalHeight = (int)Math.max(finalHeight, subclassInfo.height());

			add(new IconTab(new ItemSprite(物品表.MASK, null)) {
				@Override
				protected void select(boolean value) {
					super.select(value);
					subclassInfo.visible = subclassInfo.active = value;
				}
			});
		}

		if (Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_4) || 算法.isDebug()) {
			abilityInfo = new ArmorAbilityInfoTab(cl);
//			add(abilityInfo);
// 护甲技能
			abilityInfo.setSize(WIDTH, MIN_HEIGHT);
			finalHeight = (int)Math.max(finalHeight, abilityInfo.height());

			/*add(new IconTab(new ItemSprite(ItemSpriteSheet.CROWN, null)) {
				@Override
				protected void select(boolean value) {
					super.select(value);
					abilityInfo.visible = abilityInfo.active = value;
				}
			});*/
		}

		resize(WIDTH, finalHeight);

		layoutTabs();
		talentInfo.layout();

		select(0);

	}

	@Override
	public void offset(int xOffset, int yOffset) {
		super.offset(xOffset, yOffset);
		talentInfo.layout();
	}

	private static class HeroInfoTab extends Component {

		private RenderedTextBlock title;
		private RenderedTextBlock[] info;
		private Image[] icons;

		public HeroInfoTab(HeroClass cls){
			super();
			title = PixelScene.renderTextBlock(Messages.titleCase(cls.title()), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			String[] desc_entries = cls.desc().split("\n\n");

			info = new RenderedTextBlock[desc_entries.length];

			for (int i = 0; i < desc_entries.length; i++){
				info[i] = PixelScene.renderTextBlock(desc_entries[i], 6);
				add(info[i]);
			}

			switch (cls){
				default:
					icons = new Image[]{ new ItemSprite(物品表.BACKPACK),

							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case WARRIOR:
					icons = new Image[]{ new ItemSprite(物品表.BACKPACK),
							new ItemSprite(物品表.巨斧),
							new ItemSprite(物品表.RATION),
							new TalentIcon(Talent.钢铁意志),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case MAGE:
					icons = new Image[]{ new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.RECHARGING, true),
							new ItemSprite(物品表.SCROLL_HOLDER),
							new ItemSprite(物品表.WAND_HOLDER),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 盗贼:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							Icons.get(Icons.上楼),
							Icons.锁门.get(),
							new BuffIcon(BuffIndicator.HASTE, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case HUNTRESS:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							Icons.GRASS.get(),
							new TalentIcon(Talent.HEIGHTENED_SENSES),
							new ItemSprite(物品表.地牢浆果),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case DUELIST:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new ItemSprite(物品表.WAR_HAMMER),
							new BuffIcon(BuffIndicator.CORRUPT, true),
							Icons.TALENT.get(),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case CLERIC:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							Icons.TALENT.get(),
							new ItemSprite(物品表.REMAINS),
							new BuffIcon(BuffIndicator.LIGHT, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 巫女:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.HEALING, true),
							Icons.TALENT.get(),
							new BuffIcon(BuffIndicator.HEX, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 重武:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.HEALING, true),
							new BuffIcon(BuffIndicator.FROST, true),
							new ItemSprite(物品表.巨斧),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 道士:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new WraithSprite(),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 行僧:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.HASTE, true),
							new TalentIcon(Talent.HEIGHTENED_SENSES),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 机器:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.BLEEDING, true),
							new BuffIcon(BuffIndicator.POISON, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 戒老:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new TalentIcon(Talent.HEIGHTENED_SENSES),
							new TalentIcon(Talent.FARSIGHT),
							new BuffIcon(BuffIndicator.BLINDNESS, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 灵猫:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new ItemSprite(物品表.ARMOR_HOLDER),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 鼠弟:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new ItemSprite(物品表.ARMOR_HOLDER),
							new RatSprite(),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 凌云:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new ItemSprite(物品表.ARMOR_HOLDER),
							new BuffIcon(BuffIndicator.LEVITATION, true),
							new TalentIcon(Talent.灵敏机动),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
				case 血鬼:
					icons = new Image[]{  new ItemSprite(物品表.BACKPACK),
							new BuffIcon(BuffIndicator.HEALING, true),
							new BuffIcon(BuffIndicator.BLEEDING, true),
							new BuffIcon(BuffIndicator.HEALING, true),
							new ItemSprite(物品表.SCROLL_ISAZ)};
					break;
			}
			for (Image im : icons) {
				add(im);
			}

		}

		@Override
		protected void layout() {
			super.layout();

			title.setPos((width-title.width())/2, MARGIN);

			float pos = title.bottom()+5*MARGIN;

			for (int i = 0; i < info.length; i++){
				info[i].maxWidth((int)width - 20);
				info[i].setPos(20, pos);

				icons[i].x = (20-icons[i].width())/2;
				icons[i].y = info[i].top() + (info[i].height() - icons[i].height())/2;
				PixelScene.align(icons[i]);

				pos = info[i].bottom() + 5*MARGIN;
			}

			height = Math.max(height, pos - 5*MARGIN);

		}
	}

	private static class TalentInfoTab extends Component {

		private RenderedTextBlock title;
		private RenderedTextBlock message;
		private TalentsPane talentPane;

		public TalentInfoTab( HeroClass cls ){
			super();
			title = PixelScene.renderTextBlock(Messages.titleCase(Messages.get(WndHeroInfo.class, "talents")), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			message = PixelScene.renderTextBlock(Messages.get(WndHeroInfo.class, "talents_msg"), 6);
			add(message);

			ArrayList<LinkedHashMap<Talent, Integer>> talents = new ArrayList<>();
			Talent.initClassTalents(cls, talents);
			talents.get(2).clear(); //we show T3 talents with subclasses

			talentPane = new TalentsPane(TalentButton.Mode.INFO, talents);
			add(talentPane);
		}

		@Override
		protected void layout() {
			super.layout();

			title.setPos((width-title.width())/2, MARGIN);
			message.maxWidth((int)width);
			message.setPos(0, title.bottom()+5*MARGIN);

			talentPane.setRect(0, message.bottom() + 3*MARGIN, width, 85);

			height = Math.max(height, talentPane.bottom());
		}
	}

	private static class SubclassInfoTab extends Component {

		private RenderedTextBlock title;
		private RenderedTextBlock message;
		private RenderedTextBlock[] subClsDescs;
		private IconButton[] subClsInfos;

		public SubclassInfoTab( HeroClass cls ){
			super();
			title = PixelScene.renderTextBlock(Messages.titleCase(Messages.get(WndHeroInfo.class, "subclasses")), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			message = PixelScene.renderTextBlock(Messages.get(WndHeroInfo.class, "subclasses_msg"), 6);
			add(message);

			HeroSubClass[] subClasses = cls.subClasses();

			subClsDescs = new RenderedTextBlock[subClasses.length];
			subClsInfos = new IconButton[subClasses.length];

			for (int i = 0; i < subClasses.length; i++){
				subClsDescs[i] = PixelScene.renderTextBlock(subClasses[i].shortDesc(), 6);
				int finalI = i;
				subClsInfos[i] = new IconButton( Icons.get(Icons.INFO) ){
					@Override
					protected void onClick() {
						Game.scene().addToFront(new WndInfoSubclass(cls, subClasses[finalI]));
					}
				};
				add(subClsDescs[i]);
				add(subClsInfos[i]);
			}

		}

		@Override
		protected void layout() {
			super.layout();

			title.setPos((width-title.width())/2, MARGIN);
			message.maxWidth((int)width);
			message.setPos(0, title.bottom()+5*MARGIN);

			float pos = message.bottom()+5*MARGIN;

			for (int i = 0; i < subClsDescs.length; i++){
				subClsDescs[i].maxWidth((int)width - 20);
				subClsDescs[i].setPos(0, pos);

				subClsInfos[i].setRect(width-20, subClsDescs[i].top() + (subClsDescs[i].height()-20)/2, 20, 20);

				pos = subClsDescs[i].bottom() + 5*MARGIN;
			}

			height = Math.max(height, pos - 5*MARGIN);

		}
	}

	private static class ArmorAbilityInfoTab extends Component {

		private RenderedTextBlock title;
		private RenderedTextBlock message;
		private RenderedTextBlock[] abilityDescs;
		private IconButton[] abilityInfos;

		public ArmorAbilityInfoTab(HeroClass cls){
			super();
			title = PixelScene.renderTextBlock(Messages.titleCase(Messages.get(WndHeroInfo.class, "abilities")), 9);
			title.hardlight(TITLE_COLOR);
			add(title);

			message = PixelScene.renderTextBlock(Messages.get(WndHeroInfo.class, "abilities_msg"), 6);
			add(message);

			ArmorAbility[] abilities = cls.armorAbilities();

			abilityDescs = new RenderedTextBlock[abilities.length];
			abilityInfos = new IconButton[abilities.length];

			for (int i = 0; i < abilities.length; i++){
				abilityDescs[i] = PixelScene.renderTextBlock(abilities[i].shortDesc(), 6);
				int finalI = i;
				abilityInfos[i] = new IconButton( Icons.get(Icons.INFO) ){
					@Override
					protected void onClick() {
						Game.scene().addToFront(new WndInfoArmorAbility(cls, abilities[finalI]));
					}
				};
				add(abilityDescs[i]);
				add(abilityInfos[i]);
			}

		}

		@Override
		protected void layout() {
			super.layout();

			title.setPos((width-title.width())/2, MARGIN);
			message.maxWidth((int)width);
			message.setPos(0, title.bottom()+5*MARGIN);

			float pos = message.bottom()+5*MARGIN;

			for (int i = 0; i < abilityDescs.length; i++){
				abilityDescs[i].maxWidth((int)width - 20);
				abilityDescs[i].setPos(0, pos);

				abilityInfos[i].setRect(width-20, abilityDescs[i].top() + (abilityDescs[i].height()-20)/2, 20, 20);

				pos = abilityDescs[i].bottom() + 5*MARGIN;
			}

			height = Math.max(height, pos - 5*MARGIN);

		}
	}

}
