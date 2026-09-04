

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
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
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes.Landmark;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class 玄武之泉 extends WellWater {
	
	@Override
	protected boolean affectHero( Hero hero ) {

		
		if (!hero.isAlive()) return false;
		
		Sample.INSTANCE.play( Assets.Sounds.DRINK );

		hero.防御成长+=5;
		hero.sprite.showStatusWithIcon(CharSprite.增强绿,Integer.toString(1),FloatingText.玄武泉);
		CellEmitter.get( hero.pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );

		Dungeon.hero.interrupt();
	
		GLog.绿(Messages.get(this,"procced"));
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item, int pos ) {

		if((item instanceof Weapon && !((Weapon) item).神力)
				|| (item instanceof Armor && !((Armor) item).神力)){
			if (item instanceof Weapon) {
				((Weapon) item).防御收益++;
				GLog.绿(Messages.get(PotionOfMastery.class,"weapon_easier"));
			} else if (item instanceof Armor) {
				((Armor) item).防御收益++;
				GLog.绿(Messages.get(PotionOfMastery.class,"armor_easier"));
			}
		}
		return item;
	}
	
	@Override
	public Landmark landmark() {
		return Landmark.玄武之泉;
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( Speck.factory( Speck.STENCH ), 0.5f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
