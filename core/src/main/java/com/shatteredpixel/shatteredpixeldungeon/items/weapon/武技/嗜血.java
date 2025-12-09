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
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 嗜血 extends 武技{
	{
		目标=true;
		desc="对攻击范围内的一个目标进行一次118%伤害必中的物理攻击，并花费攻击延迟的回合，如果击杀敌人则恢复10%已损失生命值";
	}
	
	@Override
	public void 武技(Hero hero,Weapon wep){
		this.hero=hero;
		this.wep=wep;
		GameScene.selectCell(attack);
	}
	
	protected CellSelector.Listener attack=new CellSelector.Listener(){
		
		@Override
		public void onSelect(Integer target){
			if(target==null){
				return;
			}
			
			Char enemy=Actor.findChar(target);
			if(enemy==null||enemy==hero||hero.isCharmedBy(enemy)||!Dungeon.level.heroFOV[target]){
				GLog.w(Messages.get(Weapon.class,"ability_no_target"));
				return;
			}
			
			hero.belongings.abilityWeapon=wep;
			if(!hero.canAttack(enemy)){
				GLog.w(Messages.get(Weapon.class,"ability_target_range"));
				hero.belongings.abilityWeapon=null;
				return;
			}
			hero.belongings.abilityWeapon=null;
			
			wep.消耗(hero);
			hero.sprite.attack(enemy.pos,new Callback(){
				@Override
				public void call(){
					AttackIndicator.target(enemy);
					if(hero.attack(enemy,伤害118,0,Char.INFINITE)){
						Sample.INSTANCE.play(wep.hitSound);
						if(!enemy.isAlive()){
							hero.回已损失血(0.1f);
							//击杀
						}
					}
					Invisibility.notimedispel();
					hero.spendAndNext(hero.攻击延迟());
					wep.技能使用(hero);
				}
			});
		}
		
		@Override
		public String prompt(){
			return Messages.get(Weapon.class,"prompt");
		}
	};
}
