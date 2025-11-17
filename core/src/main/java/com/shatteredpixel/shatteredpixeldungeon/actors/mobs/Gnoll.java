

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollSprite;
import com.watabou.noosa.audio.Sample;

public class Gnoll extends Mob {
	
	{
		spriteClass = GnollSprite.class;
		
		生命 = 最大生命 = 12;
		defenseSkill = 4;
		
		经验 = 2;
		最大等级 = 8;
		
		loot = Gold.class;
		lootChance = 0.5f;
	}
	
	@Override
	public int 最大攻击() {
		return 6;
	}
	
	@Override
	public int 攻击时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.狗叫);
		return super.攻击时(enemy,damage);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 10;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+2;
	}
}
