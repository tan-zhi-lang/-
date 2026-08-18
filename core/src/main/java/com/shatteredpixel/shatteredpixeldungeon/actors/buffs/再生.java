

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ChaoticCenser;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.SaltCube;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.VaultLevel;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;

public class 再生 extends Buff {
	
	{
		//unlike other buffs, this one acts after the hero and takes priority against other effects
		//healing is much more useful if you get some of it off before taking damage
		actPriority = HERO_PRIO - 1;
	}

	@Override
	public boolean attachTo( Char target) {
		if (super.attachTo( target )) {
			//if we're loading in and the hero has partially spent a turn, delay for 1 turn
			if (target instanceof Hero hero&&
				hero == null && cooldown() == 0 && hero.cooldown() > 0) {
				spend(TICK);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean act() {
		if (target instanceof Hero hero&&hero.isAlive()) {
		
			//if other trinkets ever get buffs like this should probably make the buff attaching
			// behaviour more like wands/rings/artifacts
			if (ChaoticCenser.averageTurnsUntilGas() != -1){
				Buff.施加(hero, ChaoticCenser.CenserGasTracker.class);
			}

			if (regenOn() && !hero.满血() && !hero.isStarving()) {
				
				if(再生生命() > 0)hero.回血(再生生命());

				if (hero.满血()) {
					hero.resting = false;
				}
			}

			spend( TICK );
			
		} else {
			
			diactivate();
			
		}
		
		return true;
	}

	public float 再生生命(){
		if(target instanceof Hero hero){
			float 再生数值=(float)Math.sqrt(hero.最大生命(0.0005f));

			再生数值+=hero.再生成长;

			if(hero.符文("光合作用")&&Dungeon.level!=null){
				int 树=0;
				for(int i=0;i<Dungeon.level.length();i++){
					if(Dungeon.level.map[i]==Terrain.HIGH_GRASS||Dungeon.level.map[i]==Terrain.GRASS)
						树++;
				}
				再生数值+=(树+Dungeon.level.plants.size)*0.03f;
			}
			if(hero.符文("最大护甲转生命再生")){
				再生数值+=hero.最大护甲(0.01f);
			}
			if(hero.hasbuff(WellFed.class))
				再生数值+=1;

			if(hero.buff(ChaliceOfBlood.chaliceRegen.class)!=null){
				if(hero.符文("升级蓄血圣杯")){
					再生数值+=hero.已损失生命(0.0225f);
				}
				再生数值+=(0.133f+hero.buff(ChaliceOfBlood.chaliceRegen.class).itemLevel()*0.0667f)*1.5f;
			}

			//salt cube is turned off while regen is disabled.
			if(hero.buff(LockedFloor.class)==null){
				再生数值/=SaltCube.healthRegenMultiplier();
			}

			再生数值*=1+hero.天赋点数(Talent.坚韧);

			if(hero.heroClass(HeroClass.血鬼))
				再生数值/=2;
			if(hero.符文("大胃王"))
				再生数值*=3;

			if(hero.符文("恢复恢复"))
				再生数值*=3.5f;

			if(hero.种族天赋.equals("树妖"))
				再生数值*=5;

			if(Dungeon.赛季(赛季设置.地牢塔防)||hero.heroClass(HeroClass.机器)||hero.heroClass(HeroClass.凌云))
				再生数值=0;

			if(hero.符文("吸血习性"))
				再生数值=0;

			if(hero.符文("猩红诅咒"))
				再生数值=0;

			return 再生数值;
		}
		return 0;
	}

	public static boolean regenOn(){
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null && !lock.regenOn()){
			return false;
		}
		if (Dungeon.level instanceof VaultLevel){
			return false;
		}
		return true;
	}
	
}
