

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.TormentedSpiritSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class TormentedSpirit extends Wraith {

	{
		spriteClass = TormentedSpiritSprite.class;
	}

	//50% more damage scaling than regular wraiths
	@Override
	public int 攻击() {
		return Random.NormalIntRange( 1 + Math.round(1.5f*level)/2, 2 + Math.round(1.5f*level) );
	}

	//50% more accuracy (and by extension evasion) scaling than regular wraiths
	@Override
	public int 最大命中(Char target ) {
		return 10 + Math.round(1.5f*level);
	}

	public void cleanse(){
		Sample.INSTANCE.play( Assets.Sounds.GHOST );
		yell(Messages.get(this, "thank_you"));

		//50/50 between weapon or armor, always uncursed & enchanted, 50% chance to be +1 if level 0
		Item prize;
		if (Random.Int(2) == 0){
			prize = Generator.randomWeapon(true);
			((Weapon)prize).enchant();
		} else {
			prize = Generator.randomArmor();
			((Armor) prize).inscribe();
		}
		prize.cursed = false;
		prize.cursedKnown = true;

		if (prize.等级() == 0 && Random.Int(2) == 0){
			prize.升级();
		}

		Dungeon.level.drop(prize, pos).sprite.drop();

		destroy();
		sprite.die();
		sprite.tint(1, 1, 1, 1);
		sprite.emitter().start( ShaftParticle.FACTORY, 0.3f, 4 );
		sprite.emitter().start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );

	}

}
