

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredpixel.shatteredpixeldungeon.items.food.噩梦粮食;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.sprites.鬼怨动画;
import com.watabou.noosa.audio.Sample;

public class 鬼怨 extends Mob {

	private static final float SPAWN_DELAY	= 2f;
	
	{
		spriteClass = 鬼怨动画.class;
		
		生命 = 最大生命 = Dungeon.层数(5)+Dungeon.区域(3);
		
		经验 = Dungeon.层数(0.5f);
		最大等级 = Dungeon.区域()*5;
		
		viewDistance=6;
		loot = 噩梦粮食.class;
		lootChance = 1/8f;
		flying = true;

		properties.add(Property.UNDEAD);
		properties.add(Property.INORGANIC);
	}
	
	
	@Override
	public void 死亡时(Object cause){
		Sample.INSTANCE.play(Assets.Sounds.鬼叫);
		super.死亡时(cause);
	}
	
	@Override
	public int 最小攻击() {
		return Dungeon.层数(1);
	}
	@Override
	public int 最大攻击() {
		return  Dungeon.层数(1.5f)+Dungeon.区域(1.5f);
	}
	
	
	@Override
	public int 最大命中(Char target ) {
		if(target instanceof Hero hero){
			if(hero.belongings.armor!=null
			   &&(hero.belongings.armor.强化等级()>0||hero.belongings.armor.hasGlyph()))
				return Dungeon.区域(8);
			
			if(hero.hasbuff(装甲之戒.装甲.class))
				return Dungeon.区域(8);
		}
		return Char.INFINITE;
	}
	
	@Override
	public int 最大闪避(Char target ) {
		if(target instanceof Hero hero){
			
			if(hero.belongings.weapon!=null
			   &&(hero.belongings.weapon.强化等级()>0||hero.belongings.weapon.hasEnchant()))
				return Dungeon.层数(0.5f)+Dungeon.区域(0.5f);
			
			if(hero.hasbuff(武力之戒.BrawlersStance.class))
				return Dungeon.层数(0.5f)+Dungeon.区域(0.5f);
		}
		return Char.INFINITE;
	}
	@Override
	public int 最大防御() {
		return super.最大防御()+Dungeon.层数(0.5f);
	}
	@Override
	public void rollToDropLoot() {
		Imp.Quest.process(this);
		super.rollToDropLoot();
	}
}
