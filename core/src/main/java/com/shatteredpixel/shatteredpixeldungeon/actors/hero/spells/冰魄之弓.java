

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.伤害;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.四叶草法典;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.魔法箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.配刺剑;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class 冰魄之弓 extends 目标法术 {

	public static final 冰魄之弓
			INSTANCE = new 冰魄之弓();

	@Override
	public int icon() {
		return HeroIcon.冰魄之弓;
	}

	@Override
	protected void onTargetSelected(四叶草法典 tome,Hero hero,Integer target) {

		if (target == null) {
			return;
		}

		Char enemy = Actor.findChar(target);
		if (enemy == null||enemy instanceof NPC||enemy==hero||hero.isCharmedBy(enemy)||!Dungeon.level.heroFOV[target]) {
			GLog.w(Messages.get(Weapon.class,"ability_no_target"));
			return;
		}

		if(enemy.isAlive())
			Buff.施加(enemy,伤害.class).level+=hero.魔力(0.2f)*(1+hero.天赋点数(Talent.冰魄之弓,0.25f));
		hero.扔出(enemy.pos,new 魔法箭矢(),()->{
			if(enemy.isAlive())
				Buff.施加(enemy,伤害.class).level+=hero.魔力(0.2f)*(1+hero.天赋点数(Talent.冰魄之弓,0.25f));
			hero.扔出(enemy.pos,new 魔法箭矢(),()->{

				hero.扔出(enemy.pos,new 魔法箭矢(),()->{
					if(enemy.isAlive())
						Buff.施加(enemy,伤害.class).level+=hero.魔力(0.2f)*(1+hero.天赋点数(Talent.冰魄之弓,0.25f));
				});
			});
		});

		if (hero.rooted || Dungeon.level.distance(hero.pos, target) > hero.攻击范围()+3){
			if (hero.rooted) PixelScene.shake(1,1f);
			return;
		}


		int lungeCell = -1;
		for (int i : PathFinder.相邻8){
			if (Dungeon.level.distance(hero.pos+i, target) <= hero.攻击范围()+3
				&& Actor.findChar(hero.pos+i) == null
				&& (Dungeon.level.passable[hero.pos+i] || (Dungeon.level.avoid[hero.pos+i] && hero.flying))){
				if (lungeCell == -1 || Dungeon.level.trueDistance(hero.pos + i, target) < Dungeon.level.trueDistance(lungeCell, target)){
					lungeCell = hero.pos + i;
				}
			}
		}

		if (lungeCell == -1){
			GLog.w(Messages.get(Weapon.class, "ability_target_range"));
			return;
		}

		final int dest = lungeCell;

		hero.busy();
		if(enemy.isAlive())
		hero.sprite.jump(hero.pos, dest, 0, 0.1f, new Callback() {
			@Override
			public void call() {
				if (Dungeon.level.map[hero.pos]==Terrain.OPEN_DOOR) {
					Door.leave(hero.pos);
				}
				hero.pos = dest;
				Dungeon.level.occupyCell(hero);
				Dungeon.observe();


				if (enemy != null && hero.canAttack(enemy)) {
					hero.sprite.attack(enemy.pos, new Callback() {
						@Override
						public void call() {

							AttackIndicator.target(enemy);
							hero.attack(enemy, 1,0,Char.INFINITE);
						}
					});
				} else {

					GLog.w(Messages.get(配刺剑.class,"ability_no_target"));

				}
			}
		});
		Invisibility.notimedispel();
		hero.spendAndNext( hero.攻击延迟());
	}

	

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.魔力(0.2f)*(1+Dungeon.hero.天赋点数(Talent.冰魄之弓,0.25f)));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}
