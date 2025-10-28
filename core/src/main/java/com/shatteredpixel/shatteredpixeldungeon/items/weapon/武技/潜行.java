package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

public class 潜行 extends 武技{
	/*
	
	@Override
	public String targetingPrompt() {
		return Messages.get(this, "prompt");
	}

	public boolean useTargeting(){
		return false;
	}

	*/
	@Override
	public void 武技(Hero hero,Integer target){
		if (target == null) {
			return;
		}
		
		PathFinder.buildDistanceMap(Dungeon.hero.pos,BArray.or(Dungeon.level.passable,Dungeon.level.avoid,null),wep.范围+1);
		if (PathFinder.distance[target] == Integer.MAX_VALUE || !Dungeon.level.heroFOV[target] || hero.rooted) {
			GLog.w(Messages.get(wep,"ability_target_range"));
			if (Dungeon.hero.rooted) PixelScene.shake(1,1f);
			return;
		}
		
		if (Actor.findChar(target)!=null) {
			GLog.w(Messages.get(wep, "ability_occupied"));
			return;
		}
		
		wep.beforeAbilityUsed(hero, null);
		Buff.延长(hero,Invisibility.class,2);
		
		Dungeon.hero.sprite.turnTo( Dungeon.hero.pos, target);
		Dungeon.hero.pos = target;
		Dungeon.level.occupyCell(Dungeon.hero);
		Dungeon.observe();
		GameScene.updateFog();
		Dungeon.hero.checkVisibleMobs();
		
		Dungeon.hero.sprite.place( Dungeon.hero.pos );
		CellEmitter.get(Dungeon.hero.pos).burst(Speck.factory(Speck.WOOL),6);
		Sample.INSTANCE.play(Assets.Sounds.PUFF);
		
		hero.next();
		wep.afterAbilityUsed(hero);
		super.武技(hero,target);
	}
	
}
