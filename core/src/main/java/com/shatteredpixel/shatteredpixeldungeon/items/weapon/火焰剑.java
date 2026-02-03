

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 火焰剑 extends Weapon{
	{
		image = 物品表.火焰剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		伤害=0.8f;
		tier=5;
	}
	float add=0.05f;
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		float x=1;
		if(defender.hasbuff(火毒.class)){
			x+=add;
		}
		if(defender.hasbuff(燃烧.class)){
			x+=add;
		}
		Fire fire = (Fire) Dungeon.level.blobs.get(Fire.class);
		if (fire != null && fire.volume > 0) {
			x+=add*fire.volume;
		}
		
		MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
		if (eternalFire != null && eternalFire.volume > 0) {
			x+=add*fire.volume;
		}
		
		damage*=x;
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public float 投掷攻击时(Char attacker,Char defender,float damage) {
		
		float x=1;
		if(defender.hasbuff(火毒.class)){
			x+=add;
		}
		if(defender.hasbuff(燃烧.class)){
			x+=add;
		}
		if(defender.hasbuff(灵焰.class)){
			x+=add;
		}
		Fire fire = (Fire) Dungeon.level.blobs.get(Fire.class);
		if (fire != null && fire.volume > 0) {
			x+=add*fire.volume;
		}
		
		MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
		if (eternalFire != null && eternalFire.volume > 0) {
			x+=add*fire.volume;
		}
		
		damage*=x;
		return super.投掷攻击时( attacker, defender, damage );
	}
}
