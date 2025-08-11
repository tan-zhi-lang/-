

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Identification;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class ShardOfOblivion extends Trinket {

	{
		image = 物品表.OBLIVION_SHARD;
	}

	public static final String AC_IDENTIFY = "IDENTIFY";

	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (isIdentified()){
			return Messages.get(this, "stats_desc", 强化等级()+1);
		} else {
			return Messages.get(this, "stats_desc", 1);
		}
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_IDENTIFY);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_IDENTIFY)){
			curUser = hero;
			curItem = this;
			GameScene.selectItem(identifySelector);
		} else {
			super.execute(hero, action);
		}
	}

	public static WndBag.ItemSelector identifySelector = new WndBag.ItemSelector() {
		@Override
		public String textPrompt() {
			return Messages.get(ShardOfOblivion.class, "identify_prompt");
		}

		@Override
		public boolean itemSelectable(Item item) {
			return !item.isIdentified() && item.isUpgradable();
		}

		@Override
		public void onSelect(Item item) {
			if (item == null){
				return;
			}

			boolean ready = false;
			if (item instanceof Weapon){
				ready = ((Weapon) item).readyToIdentify();
				if (item.isEquipped(curUser) && curUser.天赋点数(Talent.ADVENTURERS_INTUITION) == 1){
					ready = true;
				}
			} else if (item instanceof Armor){
				ready = ((Armor) item).readyToIdentify();
				if (item.isEquipped(curUser) && curUser.天赋点数(Talent.VETERANS_INTUITION) == 1){
					ready = true;
				}
			} else if (item instanceof Ring){
				ready = ((Ring) item).readyToIdentify();
				if (item.isEquipped(curUser) && curUser.天赋点数(Talent.THIEFS_INTUITION) == 2){
					ready = true;
				}
			} else if (item instanceof Wand){
				ready = ((Wand) item).readyToIdentify();
			}

			if (ready){
				item.鉴定();
				Badges.validateItemLevelAquired(item);
				curUser.sprite.operate(curUser.pos);
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				curUser.sprite.parent.add( new Identification( curUser.sprite.center().offset( 0, -16 ) ) );
				GLog.p(Messages.get(ShardOfOblivion.class, "identify"));
			} else {
				GLog.w(Messages.get(ShardOfOblivion.class, "identify_not_yet"));
			}
		}
	};

	public static boolean passiveIDDisabled(){
		return trinketLevel(ShardOfOblivion.class) >= 0;
	}

	public static class WandUseTracker extends FlavourBuff{

		{
			type = buffType.POSITIVE;
		}

		public static float DURATION = 50f;

		@Override
		public int icon() {
			return BuffIndicator.WAND;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0, 0.6f, 1);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}

	public static class ThrownUseTracker extends FlavourBuff{

		{
			type = buffType.POSITIVE;
		}

		public static float DURATION = 50f;

		@Override
		public int icon() {
			return BuffIndicator.THROWN_WEP;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(0, 0.6f, 1);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}

	public static float lootChanceMultiplier(){
		return lootChanceMultiplier(trinketLevel(ShardOfOblivion.class));
	}

	public static float lootChanceMultiplier(int level){
		if (level < 0) return 1f;

		int wornUnIDed = 0;
		if (Dungeon.hero.belongings.weapon() != null && !Dungeon.hero.belongings.weapon().isIdentified()){
			wornUnIDed++;
		}
		if (Dungeon.hero.belongings.armor() != null && !Dungeon.hero.belongings.armor().isIdentified()){
			wornUnIDed++;
		}
		if (Dungeon.hero.belongings.ring() != null && !Dungeon.hero.belongings.ring().isIdentified()){
			wornUnIDed++;
		}
		if (Dungeon.hero.belongings.misc() != null && !Dungeon.hero.belongings.misc().isIdentified()){
			wornUnIDed++;
		}
		if (Dungeon.hero.buff(WandUseTracker.class) != null){
			wornUnIDed++;
		}
		if (Dungeon.hero.buff(ThrownUseTracker.class) != null){
			wornUnIDed++;
		}

		wornUnIDed = Math.min(wornUnIDed, level+1);
		return 1f + .2f*wornUnIDed;

	}
}
