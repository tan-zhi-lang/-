

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.奥术之戒;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

public class 碎缘剑 extends Weapon{
	{
		image = 物品表.碎缘剑;
		hitSound = Assets.Sounds.镜刃;
		间隔= 1.25f;
		伤害= 1.25f;
		tier=5;
	}
	@Override
	public int 攻击时(Char attacker,Char defender,int damage) {
		
		int level = Math.max(0, 强化等级());
		
		// lvl 0 - 15%
		// lvl 1 ~ 19%
		// lvl 2 ~ 23%
		float procChance = (level+3f)/(level+20f)*奥术之戒.enchantPowerMultiplier(attacker);
		if (Random.Float()<procChance) {
			
			float powerMulti = Math.max(1f, procChance);
			
			if(defender.nobuff(Charm.class)){
				Buff.施加(defender,Charm.class,Math.round(Charm.DURATION*powerMulti)).object=
						defender.id();
				defender.sprite.centerEmitter().start(Speck.factory(Speck.HEART),0.2f,5);
			}else{
				
				new Flare(5,32 ).color(0xFF0000,true).show(defender.sprite,2f);
				
				int count = 0;
				Mob affected = null;
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
						Buff.施加(mob,Terror.class,Terror.DURATION).object = curUser.id();
						
						if (mob.buff(Terror.class) != null){
							count++;
							affected = mob;
						}
					}
				}
			}
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 投掷攻击时(Char attacker,Char defender,int damage) {
		
		int level = Math.max(0, 强化等级());
		
		// lvl 0 - 15%
		// lvl 1 ~ 19%
		// lvl 2 ~ 23%
		float procChance = (level+3f)/(level+20f)*奥术之戒.enchantPowerMultiplier(attacker);
		if (Random.Float()<procChance) {
			
			float powerMulti = Math.max(1f, procChance);
			if(defender.nobuff(Charm.class)){
				Buff.施加(defender,Charm.class,Math.round(Charm.DURATION*powerMulti)).object=
						defender.id();
				defender.sprite.centerEmitter().start(Speck.factory(Speck.HEART),0.2f,5);
			}else{
				
				new Flare(5,32 ).color(0xFF0000,true).show(defender.sprite,2f);
				
				int count = 0;
				Mob affected = null;
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.alignment != Char.Alignment.ALLY && Dungeon.level.heroFOV[mob.pos]) {
						Buff.施加(mob,Terror.class,Terror.DURATION).object = curUser.id();
						
						if (mob.buff(Terror.class) != null){
							count++;
							affected = mob;
						}
					}
				}
			}
		}
		return super.投掷攻击时( attacker, defender, damage );
	}
}
