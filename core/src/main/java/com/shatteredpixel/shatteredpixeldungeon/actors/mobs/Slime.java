

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SlimeSprite;

public class Slime extends Mob {
	
	{
		spriteClass = SlimeSprite.class;
		
		生命 = 最大生命 = 20;
		defenseSkill = 5;
		
		经验 = 4;
		最大等级 = 9;
		
		lootChance = 0.2f; //by default, see lootChance()
	}
	
	@Override
	public int 最小攻击() {
		return 2;
	}
	@Override
	public int 最大攻击() {
		return 5;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 12;
	}
	
	@Override
	public void 受伤时(int dmg, Object src) {
		float scaleFactor = AscensionChallenge.statModifier(this);
		int scaledDmg = Math.round(dmg/scaleFactor);
		if (scaledDmg >= 5){
			//takes 5/6/7/8/9/10 dmg at 5/7/10/14/19/25 incoming dmg
			scaledDmg = 4 + (int)(Math.sqrt(8*(scaledDmg - 4) + 1) - 1)/2;
		}
		dmg = (int)(scaledDmg*AscensionChallenge.statModifier(this));
		super.受伤时(dmg, src);
	}

	@Override
	public float lootChance(){
		//each drop makes future drops 1/4 as likely
		// so loot chance looks like: 1/5, 1/20, 1/80, 1/320, etc.
		return super.lootChance() * (float)Math.pow(1/4f, Dungeon.LimitedDrops.SLIME_WEP.count);
	}
	
	@Override
	public Item createLoot() {
		Dungeon.LimitedDrops.SLIME_WEP.count++;
		Generator.Category c = Generator.Category.WEP_T2;
		Weapon w = (Weapon)Generator.randomUsingDefaults(Generator.Category.WEP_T2);
		w.等级(0);
		return w;
	}
}
