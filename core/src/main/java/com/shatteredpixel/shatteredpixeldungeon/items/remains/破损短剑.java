

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class 破损短剑 extends RemainsItem {

	{
		image = 物品表.破损短剑;
		usesTargeting = true;
	}
	@Override
	public void execute(Hero hero, String action) {
		
		if (action.equals(AC_USE)){
			GameScene.cancel();
			curUser = hero;
			curItem = this;
			Catalog.countUse(getClass());
			doEffect(hero);
			return;
		}
		super.execute(hero, action);
	}
	@Override
	protected void doEffect(Hero hero) {
		GameScene.selectCell(attack);
	}
	protected CellSelector.Listener attack = new  CellSelector.Listener() {
		
		@Override
		public void onSelect(Integer target) {
			if (target == null) {
				return;
			}
			
			Char enemy = Actor.findChar(target);
			if (enemy == null || enemy == curUser || curUser.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
				GLog.w(Messages.get(Weapon.class,"ability_no_target"));
				return;
			}
			
			if (!curUser.canAttack(enemy)){
				GLog.w(Messages.get(Weapon.class, "ability_target_range"));
				return;
			}
			
			curUser.攻击(enemy,0,11);
			detach(curUser.belongings.backpack);
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};

}
