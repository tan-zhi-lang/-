package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.交互;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;

public class 交互 extends Mob{
	{
		alignment = Alignment.NEUTRAL;
		properties.add(Property.IMMOVABLE);
	}
	@Override
	public int 最大闪避(Char enemy ) {
		return Char.INFINITE;
	}
	@Override
	public void 受伤时(float dmg, Object 来源) {
	}

	@Override
	public boolean add( Buff buff) {
		return false;
	}
	@Override
	protected boolean getCloser(int target) {
		return false;
	}

	@Override
	protected boolean getFurther(int target) {
		return false;
	}
	@Override
	public boolean interact(Char c) {

		sprite.turnTo(pos,Dungeon.hero.pos);

		if (c != Dungeon.hero){
			return true;
		}
		交互(c);
		return true;
	}
	public void 交互(Char c) {

	}
}
