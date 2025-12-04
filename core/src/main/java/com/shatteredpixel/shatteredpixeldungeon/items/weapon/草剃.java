

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class 草剃 extends Weapon {

	{
		image = 物品表.草剃;
		hitSound = Assets.Sounds.叶子;
		
		延迟= 0.8f;
		伤害= 0.8f;
		
		伏击=0.5f;
		tier = 5;
	}
	
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		for (int n : PathFinder.NEIGHBOURS8){
			int cell=attacker.pos+n;
			Plant plant = Dungeon.level.plants.get(cell);
			
			时光沙漏.timeFreeze timeFreeze =
					Dungeon.hero.buff(时光沙漏.timeFreeze.class);
			Swiftthistle.TimeBubble bubble =
					Dungeon.hero.buff(Swiftthistle.TimeBubble.class);
			
			if (plant != null) {
				if (bubble != null){
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE,1,Random.Float(0.96f,1.05f));
					bubble.setDelayedPress(cell);
					
				} else if (timeFreeze != null){
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE, 1, Random.Float( 0.96f, 1.05f ) );
					timeFreeze.setDelayedPress(cell);
					
				} else {
					plant.trigger();
					
				}
			}
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		for (int n : PathFinder.NEIGHBOURS8){
			int cell=attacker.pos+n;
			Plant plant = Dungeon.level.plants.get(cell);
			
			时光沙漏.timeFreeze timeFreeze =
					Dungeon.hero.buff(时光沙漏.timeFreeze.class);
			Swiftthistle.TimeBubble bubble =
					Dungeon.hero.buff(Swiftthistle.TimeBubble.class);
			
			if (plant != null) {
				if (bubble != null){
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE,1,Random.Float(0.96f,1.05f));
					bubble.setDelayedPress(cell);
					
				} else if (timeFreeze != null){
					Sample.INSTANCE.play(Assets.Sounds.TRAMPLE, 1, Random.Float( 0.96f, 1.05f ) );
					timeFreeze.setDelayedPress(cell);
					
				} else {
					plant.trigger();
					
				}
			}
		}
		return super.投掷攻击时( attacker, defender, damage );
	}

}