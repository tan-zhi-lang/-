package com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;

public class 吃下 extends 武技{
	{
		desc="恢复150饱腹，并花费攻击延迟的回合";
	}
	@Override
	public void 武技(Hero hero,Weapon wep){
		this.hero=hero;
		this.wep=wep;
		wep.消耗(hero);
		hero.sprite.operate(hero.pos);
		hero.spend(1f);
		hero.busy();

		Dungeon.hero.buff(Hunger.class).吃饭(150f);
		wep.技能使用(hero);
	}
}
