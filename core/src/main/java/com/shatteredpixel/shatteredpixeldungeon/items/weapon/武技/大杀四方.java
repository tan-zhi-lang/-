package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

import java.util.ArrayList;

public class 大杀四方 extends 武技{
	{
		目标=true;
		desc="对攻击范围内的所有目标进行一次100%伤害必中的物理攻击，并花费攻击延迟的回合";
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
			if (enemy == null || enemy == hero || hero.isCharmedBy(enemy) || !Dungeon.level.heroFOV[target]) {
				GLog.w(Messages.get(Weapon.class,"ability_no_target"));
				return;
			}
			
			
			ArrayList<Char> targets = new ArrayList<>();
			Char closest = null;
			
			hero.belongings.abilityWeapon = wep;
			for (Char ch : Actor.chars()){
				if (ch.alignment == Char.Alignment.ENEMY
					&& !hero.isCharmedBy(ch)
					&& Dungeon.level.heroFOV[ch.pos]
					&& hero.canAttack(ch)){
					targets.add(ch);
					if (closest == null || Dungeon.level.trueDistance(hero.pos, closest.pos) > Dungeon.level.trueDistance(hero.pos, ch.pos)){
						closest = ch;
					}
				}
			}
			hero.belongings.abilityWeapon = null;
			
			if (targets.isEmpty()) {
				GLog.w(Messages.get(this, "ability_no_target"));
				return;
			}
			
			Sample.INSTANCE.play(wep.hitSound);
			Char finalClosest = closest;
			wep.消耗(hero);
			hero.sprite.attack(hero.pos, new Callback() {
				@Override
				public void call() {
					for (Char ch : targets) {
						//ability does no extra damage
						hero.attack(ch, 伤害100, 0, Char.INFINITE);
						if (!ch.isAlive()){
						
						}
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
