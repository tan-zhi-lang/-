

package com.shatteredpixel.shatteredpixeldungeon.items.rings;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class RingOfMight extends Ring {

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
					soloBonus(), Messages.decimalFormat("#.##", 100f * (Math.pow(1.1f, soloBuffedBonus()) - 1f)));
			if (isEquipped(Dungeon.hero) && soloBuffedBonus() != combinedBuffedBonus(Dungeon.hero)){
				info += "\n\n" + Messages.get(this, "combined_stats",
						getBonus(Dungeon.hero, Might.class), Messages.decimalFormat("#.##", 100f * (Math.pow(1.1f, combinedBuffedBonus(Dungeon.hero)) - 1f)));
			}
			return info;
		} else {
			return Messages.get(this, "typical_stats", 1, Messages.decimalFormat("#.##", 10f));
		}
	}

	@Override
	public String upgradeStat1(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Integer.toString(level+1);
	}

	@Override
	public String upgradeStat2(int level) {
		if (cursed && cursedKnown) level = Math.min(-1, level-3);
		return Messages.decimalFormat("#.##", 100f * (Math.pow(1.1f, level+1)-1f)) + "%";
	}

	@Override
	protected RingBuff buff( ) {
		return new Might();
	}
	
	public static int strengthBonus( Char target ){
		return getBonus( target, Might.class );
	}
	
	public static float HTMultiplier( Char target ){
		return (float)Math.pow(1.1f, getBuffedBonus(target, Might.class));
	}

	public class Might extends RingBuff {
	}
}

