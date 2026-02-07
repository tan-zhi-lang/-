package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 防御姿态 extends 武技{
	{
		desc="恢复一半护甲，并花费攻击延迟的回合";
	}
	@Override
	public void 武技(Hero hero,Weapon wep){
		this.hero=hero;
		this.wep=wep;
		wep.消耗(hero);
		hero.sprite.operate(hero.pos);
		hero.spend(1f);
		hero.busy();
		hero.护甲(hero.最大护甲()/2);
		wep.技能使用(hero);
	}
}
