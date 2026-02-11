

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.防御姿态;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

import java.util.ArrayList;

public class 冰门重盾 extends Weapon{

	{
		image = 物品表.冰门重盾;
		技能=new 防御姿态();
		特别= true;
		遗产= false;
		缴械= false;
		嬗变= false;
		专属=true;
		
		延迟= 1.25f;
		伤害= 0.85f;
		tier = 1;
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if(!hero.subClass(HeroSubClass.盾之勇者)) {
			actions.remove(AC_UNEQUIP);
			actions.remove(AC_THROW);
		}
		return actions;
	}
	@Override
	public int 能量() {
		return 0;
	}
	@Override
	public int 金币() {
		return 0;
	}
	@Override
	public float 最小攻击(int lvl) {
		return  Math.round(super.最小攻击(lvl)*(1+Dungeon.hero.天赋点数(Talent.冰门高攻,0.5f)));               //+2 per level, down from +4
	}
	@Override
	public float 最大攻击(int lvl) {
		tier=1+Dungeon.hero.天赋点数(Talent.高阶盾武);
		命中=1+Dungeon.hero.天赋点数(Talent.用盾诀窍,0.075f);
		吸血=Dungeon.hero.天赋点数(Talent.用盾诀窍,0.03f);
		return  Math.round(super.最大攻击(lvl)*(1+Dungeon.hero.天赋点数(Talent.冰门高攻,0.5f)));
	}

	@Override
	public float 最大防御(int lvl){
		return (1+Dungeon.hero.天赋点数(Talent.冰门高防)) + lvl*(1+Dungeon.hero.天赋点数(Talent.冰门高防));
	}
}