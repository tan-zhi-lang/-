

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SheepSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 超级魔法绵羊 extends Mob {

	{
		spriteClass = SheepSprite.class;
		
		生命 = 最大生命 = Dungeon.层数(1);
		state = WANDERING;
		loot = Random.oneOf(Generator.randomArtifact(),
							Generator.randomWand(),Generator.randomRing());
	}
	private static final String[] LINE_KEYS = {"Baa!", "Baa?", "Baa.", "Baa...", "Baba"};

	@Override
	public Char chooseEnemy() {
		return null;
	}
	
	@Override
	public void 受伤时(int dmg, Object src ){
		
		String s=Random.element(LINE_KEYS);
		sprite.showStatus(CharSprite.NEUTRAL,Messages.get(this,s));
		
		Badges.解锁兽灵();
		if(s.equals("Baba")){
			Dungeon.hero.经验(Dungeon.层数(0.5f));
		}
		Sample.INSTANCE.play(Assets.Sounds.SHEEP,1,Random.Float(0.91f,1.1f));
		
		super.受伤时(dmg,src);
		
		if(isAlive()){
			传送卷轴.teleportChar(this);
		}
	}
}
