package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.watabou.noosa.audio.Sample;

public class 发射 extends 武技{
	{
		目标=true;
		desc="对攻一个目标发射特定物品，造成特定伤害，并花费攻击延迟的回合";
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

			Ballistica b = new Ballistica(hero.pos,target,Ballistica.STOP_TARGET);

			//			Char enemy = Actor.findChar(target);
//			if (enemy == null||enemy instanceof NPC||enemy==hero||hero.isCharmedBy(enemy)||!Dungeon.level.heroFOV[target]) {
//				GLog.橙(Messages.get(Weapon.class,"ability_no_target"));
//				return;
//			}

			Char enemy = Actor.findChar(b.collisionPos);


//			if (!hero.canAttack(enemy)){
//				GLog.橙(Messages.get(Weapon.class,"ability_target_range"));
//				
//				return;
//			}
			
			
			wep.消耗(hero);
			hero.扔出(b.collisionPos,wep.发射物,()->{

				if(enemy!=null){
					AttackIndicator.target(enemy);
					if(hero.attack(enemy,0,hero.heroDamage(wep.发射器最小攻击(),wep.发射器最大攻击()),1)){
						Sample.INSTANCE.play(wep.hitSound);
						if(!enemy.isAlive()){

							//击杀
						}
					}
				}else{

					if (Dungeon.level!=null&&ShatteredPixelDungeon.scene() instanceof GameScene) {
						Dungeon.level.pressCellmin( b.collisionPos);
					}
				}
				Invisibility.notimedispel();
				hero.spendAndNext(hero.攻击延迟());
				wep.技能使用(hero);
			});
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};
}
