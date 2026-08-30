

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes.Landmark;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 幸运之泉 extends WellWater {
	
	@Override
	protected boolean affectHero( Hero hero ) {
		Badges.解锁逐姝();
		
		if (!hero.isAlive()) return false;
		
		Sample.INSTANCE.play( Assets.Sounds.DRINK );

		Dungeon.hero.幸运值++;

		hero.sprite.showStatusWithIcon(CharSprite.增强绿,Integer.toString(1),FloatingText.GOLD);
		CellEmitter.get( hero.pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );

		Dungeon.hero.interrupt();
	
		GLog.绿(Messages.get(this,"procced"));
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item, int pos ) {
		if(usableOnItem(item)){
			item=changeItem(item);
		}

		return item;
	}

	protected boolean usableOnItem(Item item) {

		if(item instanceof Bag){
			return false;
		}
		if(!item.嬗变){
			return false;
		}
		if (item instanceof Weapon){
			//all melee weapons, except pickaxe when in a mining level
			return !(item instanceof 镐子&&Dungeon.level instanceof MiningLevel);

			//all missile weapons except untipped darts
		} else {
			return item instanceof Ring||item instanceof Armor||item instanceof Wand;
		}
	}
	public static Item changeItem( Item item ){
		if (item instanceof Weapon ) {
			return changeWeapon( (Weapon)item );
		} else if (item instanceof Armor ) {
			return changeArmor( (Armor)item );
		}  else if (item instanceof Ring) {
			return changeRing( (Ring)item );
		} else if (item instanceof Wand) {
			return changeWand( (Wand)item );
		}else {
			return item;
		}
	}

	private static Weapon changeWeapon( Weapon w ) {
		Weapon n;
		Generator.Category c= Generator.wepTiers[w.tier() - 1];

		do {
			n = (Weapon)Generator.randomUsingDefaults(c);
		} while (Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		n.等级(0);
		int level = w.真等级()+Math.round(Random.Float(Dungeon.hero.幸运值(),Dungeon.hero.幸运值()*3));
		if (level > 0) {
			n.升级( level );
		} else if (level < 0) {
			n.降级( -level );
		}

		n.enchantment = w.enchantment;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.神力 = w.神力;
		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.augment = w.augment;

		return n;

	}

	private static Armor changeArmor( Armor a) {
		Armor n = Generator.randomArmor();

		n.等级(0);
		int level = a.真等级()+Math.round(Random.Float(Dungeon.hero.幸运值(),Dungeon.hero.幸运值()*3));
		if (level > 0) {
			n.升级( level );
		} else if (level < 0) {
			n.降级( -level );
		}

		n.glyph = a.glyph;
		n.curseInfusionBonus = a.curseInfusionBonus;
		n.神力 = a.神力;
		n.levelKnown = a.levelKnown;
		n.cursedKnown = a.cursedKnown;
		n.cursed = a.cursed;
		n.augment = a.augment;
		n.荣誉纹章= a.荣誉纹章;

		return n;

	}

	private static Ring changeRing( Ring r ) {
		Ring n;
		do {
			n = (Ring)Generator.randomUsingDefaults( Generator.Category.RING );
		} while (Challenges.isItemBlocked(n) || n.getClass() == r.getClass());

		n.等级(0);

		int level =r.等级()+Math.round(Random.Float(Dungeon.hero.幸运值(),Dungeon.hero.幸运值()*3));
		if (level > 0) {
			n.升级( level );
		} else if (level < 0) {
			n.降级( -level );
		}

		n.levelKnown = r.levelKnown;
		n.cursedKnown = r.cursedKnown;
		n.cursed = r.cursed;

		return n;
	}

	private static Wand changeWand( Wand w ) {
		Wand n;
		do {
			n = (Wand)Generator.randomUsingDefaults( Generator.Category.WAND );
		} while ( Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		n.等级( 0 );
		int level = w.真等级()+Math.round(Random.Float(Dungeon.hero.幸运值(),Dungeon.hero.幸运值()*3));
		n.升级( level );

		n.levelKnown = w.levelKnown;
		n.curChargeKnown = w.curChargeKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.resinBonus = w.resinBonus;

		n.curCharges =  w.curCharges;
		n.updateLevel();

		return n;
	}

	@Override
	public Landmark landmark() {
		return Landmark.幸运之泉;
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( Speck.factory( Speck.COIN ), 0.5f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
