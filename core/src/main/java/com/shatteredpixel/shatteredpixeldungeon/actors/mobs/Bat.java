

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BatSprite;
import com.watabou.noosa.audio.Sample;

public class Bat extends Mob {

	{
		spriteClass = BatSprite.class;
		
		生命 = 最大生命 = Dungeon.老鼠蝙蝠?7:30;
		defenseSkill = Dungeon.老鼠蝙蝠?2:15;
		baseSpeed = Dungeon.老鼠蝙蝠?1f:2f;
		
		经验 = Dungeon.老鼠蝙蝠?1:7;
		最大等级 = Dungeon.老鼠蝙蝠?5:15;
		
		flying = true;
		
		loot = 治疗药剂.class;
		lootChance = 0.1667f; //by default, see lootChance()
		properties.add(Property.动物);
	}
	
	@Override
	public int 最小攻击() {
		return Dungeon.老鼠蝙蝠?1:5;
	}
	@Override
	public int 最大攻击() {
		return Dungeon.老鼠蝙蝠?4:18;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return Dungeon.老鼠蝙蝠?8:16;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+(Dungeon.老鼠蝙蝠?1:4);
	}
	
	@Override
	public float 吸血(){
		return super.吸血()+0.5f;
	}
	
	@Override
	public void 死亡时(Object cause) {
		Sample.INSTANCE.play(Assets.Sounds.BAT);
		flying = false;
		super.死亡时(cause);
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
//		int reg = Math.min( damage - 4, 最大生命 - 生命);
//		回血(reg);
		return damage;
	}
	
	@Override
	public float lootChance(){
		if(Dungeon.老鼠蝙蝠){
			return 0;
		}
		return super.lootChance() * ((7f - Dungeon.LimitedDrops.BAT_HP.count) / 7f);
	}
	
	@Override
	public Item createLoot(){
		if(Dungeon.老鼠蝙蝠)
			return null;
		Dungeon.LimitedDrops.BAT_HP.count++;
		return super.createLoot();
	}
	
}
