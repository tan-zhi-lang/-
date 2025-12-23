package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

public class 除草 extends 武技{
	
	{
		desc="对攻击范围内的一个可踩踏草丛进行一次踩踏，3倍概率和3倍掉落数量";
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
			if(Dungeon.level.map[target]!=Terrain.HIGH_GRASS||
			   Dungeon.level.map[target]!=Terrain.FURROWED_GRASS){
				return;
			}
			PathFinder.buildDistanceMap(Dungeon.hero.pos,BArray.or(Dungeon.level.passable,Dungeon.level.avoid,null),hero.攻击范围());
			if (PathFinder.distance[target]==Integer.MAX_VALUE||!Dungeon.level.heroFOV[target]||hero.rooted) {
				GLog.w(Messages.get(wep,"ability_target_range"));
				if (Dungeon.hero.rooted) PixelScene.shake(1,1f);
				return;
			}

			if (Actor.findChar(target)!=null) {
				GLog.w(Messages.get(wep, "ability_occupied"));
				return;
			}
			
			wep.消耗(hero);
			
			Dungeon.level.pressCellgrass3(target);
			
			hero.sprite.operate(hero.pos);
			hero.spend( 1f );
			hero.busy();
			wep.技能使用(hero);
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};
	
	
}
