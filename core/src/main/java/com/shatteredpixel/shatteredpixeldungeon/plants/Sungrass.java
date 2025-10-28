

package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class Sungrass extends Plant {
	
	{
		image = 3;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( Char ch ) {
		
		if (ch != null){
			if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN) {
				Buff.施加(ch, Healing.class).setHeal(ch.最大生命, 0, 1);
			} else {
				Buff.施加(ch, Health.class).boost(ch.最大生命);
			}
		}
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = 物品表.SEED_SUNGRASS;

			plantClass = Sungrass.class;
			
			遗产= true;
		}
	}
	
	public static class Health extends Buff {
		
		private static final float STEP = 1f;
		
		private int pos;
		private float partialHeal;
		private int level;

		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		@Override
		public boolean act() {
			if (target.pos != pos) {
				detach();
			}
			
			//for the hero, full heal takes ~50/93/111/120 turns at levels 1/10/20/30
			partialHeal += (40 + target.最大生命)/150f;
			
			if (partialHeal > 1){
				int healThisTurn = (int)partialHeal;
				partialHeal -= healThisTurn;
				level -= healThisTurn;

				if (target.生命 < target.最大生命) {

					target.生命 += healThisTurn;
					target.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(healThisTurn), FloatingText.HEALING);

					if (target.生命 >= target.最大生命) {
						target.生命 = target.最大生命;
						if (target instanceof Hero) {
							((Hero) target).resting = false;
						}
					}
				}
			}
			
			if (level <= 0) {
				detach();
				if (target instanceof Hero){
					((Hero)target).resting = false;
				}
			}
			spend( STEP );
			return true;
		}

		public void boost( int amount ){
			if (target != null) {
				level += amount;
				pos = target.pos;
			}
		}
		
		@Override
		public int icon() {
			return BuffIndicator.HERB_HEALING;
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (target.最大生命 - level) / (float)target.最大生命);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString(level);
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", level);
		}

		private static final String POS	= "pos";
		private static final String PARTIAL = "partial_heal";
		private static final String LEVEL = "level";

		@Override
		public void storeInBundle( Bundle bundle ) {
			super.storeInBundle( bundle );
			bundle.put( POS, pos );
			bundle.put( PARTIAL, partialHeal);
			bundle.put( LEVEL, level);
		}
		
		@Override
		public void restoreFromBundle( Bundle bundle ) {
			super.restoreFromBundle( bundle );
			pos = bundle.getInt( POS );
			partialHeal = bundle.getFloat( PARTIAL );
			level = bundle.getInt( LEVEL );

		}
	}
}
