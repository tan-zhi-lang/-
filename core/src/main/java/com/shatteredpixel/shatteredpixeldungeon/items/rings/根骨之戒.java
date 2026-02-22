

package com.shatteredpixel.shatteredpixeldungeon.items.rings;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 根骨之戒 extends Ring {

	{
		icon = 物品表.Icons.RING_MIGHT;
		buffClass = Might.class;
	}

	@Override
	public boolean doEquip(Hero hero) {
		if (super.doEquip(hero)){
			hero.更新属性();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		if (super.doUnequip(hero, collect, single)){
			hero.更新属性();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Item 升级() {
		super.升级();
		updateTargetHT();
		return this;
	}

	@Override
	public void 等级(int value) {
		super.等级(value);
		updateTargetHT();
	}
	
	private void updateTargetHT(){
		if (buff != null && buff.target instanceof Hero){
			((Hero) buff.target).更新属性();
		}
	}
	
	public String statsInfo() {
		if (已鉴定()){
			String info = Messages.get(this, "stats",
									   2*soloBuffedBonus(), soloBuffedBonus()*25);
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
											  2+2*combinedBuffedBonus(Dungeon.hero), 25+combinedBuffedBonus(Dungeon.hero)*25);
			}
			return info;
		} else {
			return Messages.get(this, "stats", 2, 25);
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(2+2*level);
	}

	@Override
	public String upgradeStat2(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-6);
		return ""+(25+level*25);
	}
	
	public static int strengthBonus( Char target ){
		return getBuffedBonus( target, Might.class );
	}
	
	
	@Override
	protected RingBuff buff( ) {
		return new Might();
	}
	public class Might extends RingBuff {
	}
}

