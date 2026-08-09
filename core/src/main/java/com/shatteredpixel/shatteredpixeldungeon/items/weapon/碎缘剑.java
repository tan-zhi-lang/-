

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
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 碎缘剑 extends Weapon{
	{
		image = 物品表.碎缘剑;
		hitSound = Assets.Sounds.镜刃;
		特别=true;
		粉色=true;
		伤害=1.1f;
		tier=5;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			if(算法.概率学(15)){


				if(defender.nobuff(Charm.class)){
					Buff.施加(defender,Charm.class,Charm.DURATION).object=defender.id();
					defender.sprite.centerEmitter().start(Speck.factory(Speck.HEART),0.2f,5);
				}else{

					new Flare(5,32).color(0xFF0000,true).show(defender.sprite,2f);

					int count=0;
					Mob affected=null;
					for(Mob mob: Dungeon.level.mobs.toArray(new Mob[0])){
						if(mob.alignment!=Char.Alignment.ALLY&&Dungeon.level.heroFOV[mob.pos]){
							Buff.施加(mob,Terror.class,Terror.DURATION).object=curUser.id();

							if(mob.buff(Terror.class)!=null){
								count++;
								affected=mob;
							}
						}
					}
				}
			}
		}
		return super.攻击时( attacker, defender, damage );
	}
}
