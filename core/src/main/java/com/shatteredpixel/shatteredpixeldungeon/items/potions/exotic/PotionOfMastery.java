

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择肌肉记忆;
import com.watabou.noosa.Game;

public class PotionOfMastery extends ExoticPotion {
	
	{
		icon = 物品表.Icons.POTION_MASTERY;
		
		特别= true;

		talentFactor = 2f;
	}
	
	@Override
	public void apply(Hero hero) {
		鉴定();
		
		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择肌肉记忆());
		});
	}
}
