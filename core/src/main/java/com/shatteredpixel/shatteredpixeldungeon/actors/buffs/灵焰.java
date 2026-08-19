

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.灵焰元素;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class 灵焰 extends 燃烧 implements Hero.Doom {
	
	public static final float DURATION = 8f;
	
	private float left;
	private boolean acted = false; //whether the debuff has done any damage at all yet
	
	private static final String LEFT	= "left";
	private static final String ACTED	= "acted";

	{
		type = buffType.NEGATIVE;
		announced = true;
	}
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( LEFT, left );
		bundle.put( ACTED, acted );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		left = bundle.getFloat( LEFT );
		acted = bundle.getBoolean( ACTED );
	}

	@Override
	public boolean attachTo(Char target) {
		Buff.detach( target, Chill.class);

		return super.attachTo(target);
	}

	@Override
	public boolean act() {

		if (acted && Dungeon.level.water[target.pos] && !target.flying){
			detach();
		} else if (target.isAlive() && !target.免疫(getClass())) {

			acted = true;
			float damage = Random.NormalFloat( 2, 6 + Dungeon.scalingDepth()/2f );
			Buff.detach( target, Chill.class);

			if (target instanceof Hero hero
					&& target.buff(时光沙漏.timeStasis.class) == null
					&& target.buff(TimeStasis.class) == null) {
				hero.受伤时( damage, this );
			} else {
				target.受伤时( damage, this );
			}
		} else {

			detach();
		}

		if (Dungeon.level.flamable[target.pos]&&Blob.volumeAt(target.pos,灵焰元素.class)==0) {
			GameScene.add(Blob.seed(target.pos,4,灵焰元素.class));
		}
		spend( TICK );
		left -= TICK;
		
		if (left <= 0 ||
			(Dungeon.level.water[target.pos] && !target.flying)) {
			
			detach();
		}
		
		return true;
	}
	
	public void reignite( Char ch ) {
		reignite( ch, DURATION );
	}
	
	public void reignite( Char ch, float duration ) {
		if (ch.免疫(灵焰.class))
			return;

		if (left < duration) left = duration;
		acted = false;
	}

	public void extend( float duration ) {
		left += duration;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.SACRIFICE;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - left) / DURATION);
	}

	@Override
	public String iconTextDisplay() {
		return Math.round(left)+"";
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.灵焰);
		else target.sprite.remove(CharSprite.State.灵焰);
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(left));
	}

	@Override
	public void onDeath() {
		
		Badges.validateDeathFromFire();
		
		Dungeon.fail( this );
		GLog.红(Messages.get(this,"ondeath"));
	}
}
