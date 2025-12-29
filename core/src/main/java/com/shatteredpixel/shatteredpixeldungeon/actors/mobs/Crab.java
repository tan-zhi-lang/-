

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CrabSprite;
import com.watabou.noosa.audio.Sample;

public class Crab extends Mob {

	{
		spriteClass = CrabSprite.class;
		
		生命 = 最大生命 = 15;
		defenseSkill = 5;
		baseSpeed = 2f;
		
		经验 = 4;
		最大等级 = 9;
		
		loot = MysteryMeat.class;
		lootChance = 0.167f;
		properties.add(Property.海妖);
	}
	
	@Override
	public int 最小攻击() {
		return 1;
	}
	@Override
	public int 最大攻击() {
		return 7;
	}
	
	@Override
	public int 攻击时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.螃蟹);
		return super.攻击时(enemy,damage);
	}
	@Override
	public int 最大命中(Char target ) {
		return 12;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+4;
	}
}
