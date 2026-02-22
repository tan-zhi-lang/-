package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class 无情铁手 extends 武技{
	{
		目标=true;
		desc="对攻击范围内的一个目标进行一次131%伤害必中的物理攻击，将目标拉至身旁，并花费攻击延迟的回合";
	}
	@Override
	public void 武技(Hero hero,Weapon wep){
		this.hero=hero;
		this.wep=wep;
		GameScene.selectCell(attack);
	}
	
	protected CellSelector.Listener attack = new  CellSelector.Listener() {
		
		@Override
		public void onSelect(Integer target) {
			if (target == null) {
				return;
			}
			
			Char enemy = Actor.findChar(target);
			
			if (enemy == null||enemy instanceof NPC||enemy==hero||hero.isCharmedBy(enemy)||!Dungeon.level.heroFOV[target]) {
				GLog.w(Messages.get(Weapon.class,"ability_no_target"));
				return;
			}
			
			if (hero.rooted || Dungeon.level.distance(hero.pos, target) > hero.攻击范围()+1){
				GLog.w(Messages.get(wep, "ability_target_range"));
				if (hero.rooted) PixelScene.shake(1,1f);
				return;
			}
			
			int lungeCell = -1;
			for (int i : PathFinder.相邻8){
				if (Dungeon.level.distance(hero.pos+i, target) <= hero.攻击范围()
					&& Actor.findChar(hero.pos+i) == null
					&& (Dungeon.level.passable[hero.pos+i] || (Dungeon.level.avoid[hero.pos+i] && hero.flying))){
					if (lungeCell == -1 || Dungeon.level.trueDistance(hero.pos + i, target) < Dungeon.level.trueDistance(lungeCell, target)){
						lungeCell = hero.pos + i;
					}
				}
			}
			final int dest = lungeCell;
			if(dest!=-1)
			enemy.sprite.jump(enemy.pos, dest, 0, 0.1f, new Callback() {
				@Override
				public void call(){
					if(Dungeon.level.map[enemy.pos]==Terrain.OPEN_DOOR){
						Door.leave(enemy.pos);
					}
					enemy.pos=dest;
					Dungeon.level.occupyCell(enemy);
					Dungeon.observe();
				}
			});
			wep.消耗(hero);
			hero.sprite.attack(enemy.pos, new Callback() {
				@Override
				public void call() {
					AttackIndicator.target(enemy);
					
					if (hero.attack(enemy,伤害131,0,Char.INFINITE)) {
						Sample.INSTANCE.play(wep.hitSound);
					}
					Invisibility.notimedispel();
					hero.spendAndNext(hero.攻击延迟());
					wep.技能使用(hero);
				}
			});
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};
	
}
