

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.RatKing;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.watabou.noosa.TextureFilm;

public class RatKingSprite extends MobSprite {

	public boolean festive;
	
	public RatKingSprite() {
		super();

		resetAnims();
	}

	public void resetAnims(){

		int c;
		switch (Holiday.getCurrentHoliday()){
			default:
				c = 0;
				break;
			case 愚人节:
				c = 8;
				break;
			case 圣诞节:
				c = 16;
				break;
		}

		if (Dungeon.hero() && (Dungeon.hero.subClass(HeroSubClass.巫咒王鼠)||!RatKing.库存)){
			c = 24;
			if (parent != null) aura(0xFFFF00, 5);
		}

		texture( Assets.Sprites.RATKING );

		TextureFilm frames = new TextureFilm( texture, 16, 17 );

		idle = new Animation( 2, true );
		idle.frames( frames, c+0, c+0, c+0, c+1 );

		run = new Animation( 10, true );
		run.frames( frames, c+2, c+3, c+4, c+5, c+6 );

		attack = new Animation( 15, false );
		attack.frames( frames, c+0 );

		die = new Animation( 10, false );
		die.frames( frames, c+0 );

		play( idle );

	}


	@Override
	public void link(Char ch) {
		super.link(ch);
		if (Dungeon.hero() && !RatKing.库存){
			aura(0xFFFF00, 5);
		}
	}
}
