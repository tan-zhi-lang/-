

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfMastery;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes.Landmark;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class 神力之泉 extends WellWater {
	
	@Override
	protected boolean affectHero( Hero hero ) {
		Badges.解锁逐姝();
		
		if (!hero.isAlive()) return false;
		
		Sample.INSTANCE.play( Assets.Sounds.DRINK );

		hero.神力++;
		hero.sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(hero.力量(0.1f)), FloatingText.STRENGTH);
		CellEmitter.get( hero.pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );

		Dungeon.hero.interrupt();
	
		GLog.p( Messages.get(this, "procced") );
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item, int pos ) {
		if((item instanceof Weapon && !(item instanceof 灵能短弓) && !((Weapon) item).神力)
				|| (item instanceof Armor && !((Armor) item).神力)){
			if (item instanceof Weapon) {
				((Weapon) item).神力 = true;
				GLog.p( Messages.get(PotionOfMastery.class, "weapon_easier") );
			} else if (item instanceof Armor) {
				((Armor) item).神力 = true;
				GLog.p( Messages.get(PotionOfMastery.class, "armor_easier") );
			}
		}
		return item;
	}
	
	@Override
	public Landmark landmark() {
		return Landmark.神力之泉;
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( Speck.factory( Speck.UP ), 0.5f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
