

package com.shatteredpixel.shatteredpixeldungeon.items.remains;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ShadowCaster;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class 遗失符石 extends RemainsItem {

	{
		image = 物品表.遗失符石;
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
	private static final int DIST = 8;
	protected CellSelector.Listener attack = new  CellSelector.Listener() {
		
		@Override
		public void onSelect(Integer target) {
			if (target == null) {
				return;
			}
			
			boolean[] FOV = new boolean[Dungeon.level.length()];
			Point c = Dungeon.level.cellToPoint(target);
			ShadowCaster.castShadow(c.x,c.y,Dungeon.level.width(),FOV,Dungeon.level.losBlocking,DIST);
			
			int sX = Math.max(0, c.x - DIST);
			int eX = Math.min(Dungeon.level.width()-1, c.x + DIST);
			
			int sY = Math.max(0, c.y - DIST);
			int eY = Math.min(Dungeon.level.height()-1, c.y + DIST);
			
			ArrayList<Trap> disarmCandidates = new ArrayList<>();
			
			for (int y = sY; y <= eY; y++){
				int curr = y*Dungeon.level.width() + sX;
				for ( int x = sX; x <= eX; x++){
					
					if (FOV[curr]){
						
						Trap t = Dungeon.level.traps.get(curr);
						if (t != null && t.active){
							disarmCandidates.add(t);
						}
						
					}
					curr++;
				}
			}
			
			Collections.shuffle(disarmCandidates);
			Collections.sort(disarmCandidates, new Comparator<Trap>() {
				@Override
				public int compare(Trap o1, Trap o2) {
					float diff = Dungeon.level.trueDistance(target, o1.pos) - Dungeon.level.trueDistance(target, o2.pos);
					if (diff < 0){
						return -1;
					} else if (diff == 0){
						return 0;
					} else {
						return 1;
					}
				}
			});
			
			//disarms at most nine traps
			while (disarmCandidates.size() > 9){
				disarmCandidates.remove(9);
			}
			
			for ( Trap t : disarmCandidates){
				t.reveal();
				t.disarm();
				CellEmitter.get(t.pos).burst(Speck.factory(Speck.STEAM),6);
			}
			
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
			if (Actor.findChar(target) == null) Dungeon.level.pressCell( target );
			
			Invisibility.dispel();
			
			curUser.spendAndNext(curUser.攻击延迟());
			detach(curUser.belongings.backpack);
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};

}
