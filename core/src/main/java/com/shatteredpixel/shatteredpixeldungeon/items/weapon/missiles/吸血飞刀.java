

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 吸血飞刀 extends MissileWeapon {
	
	{
		image = 物品表.吸血飞刀;
		hitSound = Assets.Sounds.HIT_SLASH;
		
		红色 = true;
		unique = true;
		bones = false;
		命中=0.9f;
		间隔=0.8f;
		伤害=0.7f;
		
		tier = 1;
		baseUses = 1000;
	}
	@Override
	public int 最小攻击(int lvl) {
		return  4 * tier +                      //base
				lvl;                            //level scaling
	}
	@Override
	public int 最大攻击(int lvl) {
		return  9 * tier +                      //base
				tier*lvl;                       //level scaling
	}
	@Override
	public int 攻击时(Char attacker, Char defender, int damage ) {
		damage = super.攻击时(attacker,defender,damage);
		if(attacker instanceof Hero hero){
			
			float x =damage * 0.07f;
			if(x>0){
				float y = x;
				y-=Math.round(x);
				if(y>0){
					hero.生命流动+=y;
				}
				hero.回血(Math.round(x));
			}
		}
		return damage;
	}
}
