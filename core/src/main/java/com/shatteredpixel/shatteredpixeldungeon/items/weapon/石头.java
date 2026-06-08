

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.重击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 石头 extends Weapon{
	
	{
		image = 物品表.THROWING_STONE;
		tier = 1;
		延迟=0.8f;
		伤害=0.6f;
		技能=new 重击();
	}
	public int tier(){
		return super.tier()+(Dungeon.符文("升级石头")?4:0);
	}
	@Override
	public float pickupDelay() {
		return 0;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(attacker instanceof Hero){
			if(defender!=null&&defender.第x次防御==1){
				Buff.延长(defender,Paralysis.class,1);

				if(Dungeon.符文("冰雹"))
					算法.修复效果(()->{
						Buff.施加(defender,Frost.class,Frost.DURATION);
					});

				if(Dungeon.符文("圣石"))
					Buff.延长(attacker,Invulnerability.class,2);
			}
			if(Dungeon.符文("碎石"))
				Buff.施加(defender,流血.class).set(damage);
			if(Dungeon.符文("石头剪刀布")&&算法.概率学(1/3f))
				damage*=9;
			if(Dungeon.符文("巨石强森"))
				damage*=30;
			if(Dungeon.符文("陨石"))
				damage*=50;
		}
		return super.攻击时( attacker, defender, damage );
	}
	@Override
	public int 能量() {
		return 1;
	}
	@Override
	public int 金币() {
		return 0;
	}
}
