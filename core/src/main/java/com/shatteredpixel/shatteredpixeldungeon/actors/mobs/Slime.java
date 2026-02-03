

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SlimeSprite;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.noosa.audio.Sample;

public class Slime extends Mob {
	
	{
		spriteClass = SlimeSprite.class;
		
		生命 = 最大生命 = 20;
		defenseSkill = 5;
		
		经验 = 4;
		最大等级 = 9;
		史莱姆=true;
		lootChance = 0.2f; //by default, see lootChance()
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public float 最小攻击() {
		return 2;
	}
	@Override
	public float 最大攻击() {
		return 5;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 12;
	}
	
	@Override
	public void 死亡时(Object cause){
		Sample.INSTANCE.play(Assets.Sounds.史莱姆);
		super.死亡时(cause);
	}
	
	
	@Override
	public float lootChance(){
		//each drop makes future drops 1/4 as likely
		// so loot chance looks like: 1/5, 1/20, 1/80, 1/320, etc.
		return super.lootChance() * (float)Math.pow(1/4f, Dungeon.LimitedDrops.SLIME_WEP.count);
	}
	
	@Override
	public Item createLoot() {
		if(!Dungeon.赛季(赛季设置.刷子地牢))
		Dungeon.LimitedDrops.SLIME_WEP.count++;
		
		Generator.Category c = Generator.Category.WEP_T2;
		Weapon w = (Weapon)Generator.randomUsingDefaults(Generator.Category.WEP_T2);
		
		return w;
	}
}
