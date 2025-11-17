

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SnakeSprite;
import com.watabou.noosa.audio.Sample;

public class Snake extends Mob {
	
	{
		spriteClass = SnakeSprite.class;
		
		生命 = 最大生命 = 4;
		defenseSkill = 25;
		
		经验 = 2;
		最大等级 = 7;
		
		loot = Generator.Category.SEED;
		lootChance = 0.25f;
	}
	
	@Override
	public int 最大攻击() {
		return 6;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 10;
	}

	private static int dodges = 0;
	
	@Override
	public int 攻击时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.蛇叫);
		return super.攻击时(enemy,damage);
	}
	@Override
	public String defenseVerb() {
		Sample.INSTANCE.play(Assets.Sounds.蛇叫);
		if (Dungeon.level.heroFOV[pos]) {
			dodges++;
		}
		if ((dodges >= 2 && !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_SURPRISE_ATKS))
				|| (dodges >= 4 && !Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_1))){
			GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_SURPRISE_ATKS);
			dodges = 0;
		}
		return super.defenseVerb();
	}
}
