

package com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Flare;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;

public class PotionOfCleansing extends ExoticPotion {
	
	{
		icon = 物品表.Icons.POTION_CLEANSE;
	}
	
	@Override
	public void apply( Hero hero ) {
		鉴定();
		
		cleanse( hero );
		new Flare( 6, 32 ).color(0xFF4CD2, true).show( curUser.sprite, 2f );
	}
	
	@Override
	public void shatter(int cell) {
		if (Actor.findChar(cell) == null){
			super.shatter(cell);
		} else {
			splash( cell );
			if (Dungeon.level.heroFOV[cell]) {
				Sample.INSTANCE.play(Assets.Sounds.SHATTER);
				鉴定();
			}
			
			if (Actor.findChar(cell) != null){
				cleanse(Actor.findChar(cell));
			}
		}
	}

	public static void cleanse(Char ch){
		cleanse(ch, Cleanse.DURATION);
	}

	public static void cleanse(Char ch, float duration){
		for (Buff b : ch.buffs()){
			if (b.type == Buff.buffType.NEGATIVE
					&& !(b instanceof AllyBuff)
					&& !(b instanceof LostInventory)){
				b.detach();
			}
			if (b instanceof Hunger){
				((Hunger) b).吃饭(Hunger.STARVING);
			}
		}
		Buff.延长(ch, Cleanse.class, duration);
	}

	public static class Cleanse extends FlavourBuff {

		{
			type = buffType.POSITIVE;
		}

		public static final float DURATION = 5f;

		@Override
		public int icon() {
			return BuffIndicator.IMMUNITY;
		}

		@Override
		public void tintIcon(Image icon) {
			icon.hardlight(1f, 0f, 2f);
		}

		@Override
		public float iconFadePercent() {
			return Math.max(0, (DURATION - visualcooldown()) / DURATION);
		}

	}
}
