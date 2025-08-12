

package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.极速;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Swiftthistle extends Plant {
	
	{
		image = 2;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( Char ch ) {
		if (ch != null) {
			Buff.施加(ch, TimeBubble.class).reset();
			if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN){
				Buff.施加(ch, 极速.class, 1f);
			}
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = 物品表.SEED_SWIFTTHISTLE;
			
			plantClass = Swiftthistle.class;
		}
	}
	
	//FIXME lots of copypasta from time freeze here
	
	public static class TimeBubble extends Buff {
		
		{
			type = buffType.POSITIVE;
			announced = true;
		}
		
		private float left;
		ArrayList<Integer> presses = new ArrayList<>();
		
		@Override
		public int icon() {
			return BuffIndicator.TIME;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1f, 1f, 0);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (6f - left) / 6f);
		}

		@Override
		public String iconTextDisplay() {
			return Integer.toString((int)(left + 0.001f));
		}

		public void reset(){
			reset(6);
		}

		public void reset(int turns){
			left = turns + 1; //add 1 as we're spending it on our action
		}
		
		@Override
		public String desc() {
			return Messages.get(this, "desc", dispTurns(Math.max(0, left)));
		}
		
		public void processTime(float time){
			left -= time;

			//use 1/1,000 to account for rounding errors
			if (left < -0.001f){
				detach();
			}
			
		}
		
		public void setDelayedPress(int cell){
			if (!presses.contains(cell)) {
				presses.add(cell);
			}
		}

		public void triggerPresses(){
			ArrayList<Integer> toTrigger = presses;
			presses = new ArrayList<>();
			Actor.add(new Actor() {
				{
					actPriority = VFX_PRIO;
				}

				@Override
				protected boolean act() {
					for (int cell : toTrigger){
						Plant p = Dungeon.level.plants.get(cell);
						if (p != null){
							p.trigger();
						}
						Trap t = Dungeon.level.traps.get(cell);
						if (t != null){
							t.trigger();
						}
					}
					Actor.remove(this);
					return true;
				}
			});
		}

		public void disarmPresses(){
			for (int cell : presses){
				Plant p = Dungeon.level.plants.get(cell);
				if (p != null && !(p instanceof Rotberry)) {
					Dungeon.level.uproot(cell);
				}
				Trap t = Dungeon.level.traps.get(cell);
				if (t != null && t.disarmedByActivation) {
					t.disarm();
				}
			}

			presses = new ArrayList<>();
		}
		
		@Override
		public void detach(){
			super.detach();
			triggerPresses();
			target.next();
		}

		@Override
		public void fx(boolean on) {
			if (!(target instanceof Hero)) return;
			Emitter.freezeEmitters = on;
			if (on){
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.sprite != null) mob.sprite.add(CharSprite.State.PARALYSED);
				}
			} else {
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					if (mob.paralysed <= 0) mob.sprite.remove(CharSprite.State.PARALYSED);
				}
			}
		}
		
		private static final String PRESSES = "presses";
		private static final String LEFT = "left";
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			
			int[] values = new int[presses.size()];
			for (int i = 0; i < values.length; i ++)
				values[i] = presses.get(i);
			bundle.put( PRESSES , values );
			
			bundle.put( LEFT, left);
		}
		
		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			
			int[] values = bundle.getIntArray( PRESSES );
			for (int value : values)
				presses.add(value);
			
			left = bundle.getFloat(LEFT);
		}
		
	}
}
