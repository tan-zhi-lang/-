

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.LiquidMetal;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.紫色心情;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Charm extends FlavourBuff {

	public int object = 0;
	public boolean ignoreHeroAllies = false;

	public static final float DURATION = 10f;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public boolean act(){
		spend( 1 );
		if(紫色心情.概率()>0&&算法.概率学(紫色心情.概率())){
			if(target instanceof Hero hero){
				if(hero.女人())
					Dungeon.level.dropRandomCell(new Dewdrop(),target.pos);
				else
					Dungeon.level.dropRandomCell(new LiquidMetal(),target.pos);
			}else {

				Dungeon.level.dropRandomCell(Random.oneOf(new Dewdrop(),new LiquidMetal()),target.pos);
			}
		}
		return true;
	}

	private static final String OBJECT          = "object";
	private static final String IGNORE_ALLIES    = "ignore_allies";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( OBJECT, object );
		bundle.put( IGNORE_ALLIES, ignoreHeroAllies );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		object = bundle.getInt( OBJECT );
		ignoreHeroAllies = bundle.getBoolean( IGNORE_ALLIES );
	}

	@Override
	public int icon() {
		return BuffIndicator.HEART;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public boolean attachTo(Char target){
		if(target instanceof Hero hero&&hero.符文("我是色批"))
			算法.修复效果(()->{
				Buff.施加(hero,Paralysis.class,Paralysis.DURATION);
			});
		if(target.sprite!=null)target.sprite.爱心();
		return super.attachTo(target);
	}

	public boolean ignoreNextHit = false;

	public void recover(Object src) {
		if (ignoreHeroAllies && src instanceof Char){
			if (src != Dungeon.hero && ((Char) src).alignment == Char.Alignment.ALLY){
				return;
			}
		}

		if (ignoreNextHit){
			ignoreNextHit = false;
			return;
		}
		spend(-5f);
		if (cooldown() <= 0){
			detach();
		}
	}
}
