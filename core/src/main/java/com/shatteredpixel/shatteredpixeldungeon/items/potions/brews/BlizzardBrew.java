

package com.shatteredpixel.shatteredpixeldungeon.items.potions.brews;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blizzard;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.冰霜药剂;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;

public class BlizzardBrew extends Brew {
	
	{
		image = 物品表.BREW_BLIZZARD;
		icon = 物品表.Icons.冰暴;
	}
	
	@Override
	public void shatter(int cell) {
		splash( cell );
		if (Dungeon.level.heroFOV[cell]) {
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Sample.INSTANCE.play( Assets.Sounds.GAS );
		}

		int centerVolume = 120;
		for (int i : PathFinder.相邻8){
			if (!Dungeon.level.solid[cell+i]){
				GameScene.add( Blob.seed( cell+i, 120, Blizzard.class ) );
			} else {
				centerVolume += 120;
			}
		}

		GameScene.add( Blob.seed( cell, centerVolume, Blizzard.class ) );
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{冰霜药剂.class};
			inQuantity = new int[]{1};
			
			cost = 8;
			
			output = BlizzardBrew.class;
			outQuantity = 1;
		}
		
	}
}
