package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class 割草 extends 武技{
	
	{
		desc="对攻击范围内一个可踩踏草丛产生冲击波震击前方90度扇形范围3格距离内的区踩踏，3倍概率和3倍掉落数量";
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
			if (target == hero.pos){
				GLog.w(Messages.get(ArmorAbility.class,"self_target"));
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
			
			hero.busy();
			
			
			Ballistica aim = new Ballistica(hero.pos,target,Ballistica.WONT_STOP);
			
			int maxDist = 3;//格子
			int dist = Math.min(aim.dist, maxDist);
			
			ConeAOE cone = new ConeAOE(aim,
									   dist,
									   90,//角度
									   Ballistica.STOP_SOLID | Ballistica.STOP_TARGET);
			
			//cast to cells at the tip, rather than all cells, better performance.
			for (Ballistica ray : cone.outerRays){
				((MagicMissile)hero.sprite.parent.recycle(MagicMissile.class)).reset(
						MagicMissile.FORCE_CONE,
						hero.sprite,
						ray.path.get(ray.dist),
						null
																					);
			}
			
			hero.sprite.zap(target);
			Sample.INSTANCE.play(Assets.Sounds.BLAST,1f,0.5f);
			PixelScene.shake(2, 0.5f);
			//final zap at 2/3 distance, for timing of the actual effect
			
			for (int cell : cone.cells){
				Dungeon.level.pressCellgrass3(target);
			}
			MagicMissile.boltFromChar(hero.sprite.parent,
									  MagicMissile.FORCE_CONE,
									  hero.sprite,
									  cone.coreRay.path.get(dist * 2 / 3),
									  new Callback() {
										  @Override
										  public void call() {
											  Invisibility.notimedispel();
											  hero.spendAndNext( hero.攻击延迟() );
										  }
									  });
			//
			wep.技能使用(hero);
		}
		
		@Override
		public String prompt() {
			return Messages.get(Weapon.class, "prompt");
		}
	};
	
	
}
