

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.圣光;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.尘遁;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.掌心雷;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.火球术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.痛命;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.符咒;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.赐福;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.风刃;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.CrystalWisp;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DM100;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogFist;
import com.shatteredpixel.shatteredpixeldungeon.actors.伤害;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.ArcaneBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.神圣炸弹;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.DamageWand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.冰海法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.影织法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.棱镜法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.浓毒法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.潮霆法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.烈焰法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.落石法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.HolyDart;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.死神;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.电击;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;

import java.util.HashSet;

public class 敌法 extends Armor.Glyph {

	public static final HashSet<Class> RESISTS = new HashSet<>();
	static{
		RESISTS.add(MagicalSleep.class);
		RESISTS.add(Charm.class);
		RESISTS.add(Weakness.class);
		RESISTS.add(Vulnerable.class);
		RESISTS.add(Hex.class);
		RESISTS.add(Degrade.class);

		RESISTS.add(DisintegrationTrap.class);
		RESISTS.add(GrimTrap.class);

		RESISTS.add(ArcaneBomb.class);
		RESISTS.add(神圣炸弹.HolyDamage.class);
		RESISTS.add(复仇卷轴.class);
		RESISTS.add(ScrollOfPsionicBlast.class);
		RESISTS.add(传送卷轴.class);
		RESISTS.add(HolyDart.class);

		RESISTS.add(Dungeon.class);
		RESISTS.add(伤害.class);

		//术
		{
			RESISTS.add(圣光.class);
			RESISTS.add(赐福.class);

			RESISTS.add(火球术.class);

			RESISTS.add(痛命.class);

			RESISTS.add(符咒.class);
			RESISTS.add(掌心雷.class);

			RESISTS.add(风刃.class);
			RESISTS.add(尘遁.class);
		}

		//法杖
		{
		RESISTS.add(DamageWand.class);
		RESISTS.add(CursedWand.class);
		RESISTS.add(WandOfBlastWave.class);
		RESISTS.add(WandOfDisintegration.class);
		RESISTS.add(影织法杖.class);
		RESISTS.add(焰浪法杖.class);
		RESISTS.add(冰海法杖.class);
		RESISTS.add(WandOfFrost.class);
		RESISTS.add(烈焰法杖.class);
		RESISTS.add(潮霆法杖.class);
		RESISTS.add(WandOfLightning.class);
		RESISTS.add(WandOfLivingEarth.class);
		RESISTS.add(落石法杖.class);
		RESISTS.add(WandOfMagicMissile.class);
		RESISTS.add(WandOfPrismaticLight.class);
		RESISTS.add(棱镜法杖.class);
		RESISTS.add(WandOfTransfusion.class);
		RESISTS.add(浓毒法杖.class);
		RESISTS.add(WandOfCorrosion.class);
		RESISTS.add(WandOfCorruption.class);
		RESISTS.add(WandOfRegrowth.class);
		RESISTS.add(WandOfWarding.class);
		RESISTS.add(WandOfWarding.Ward.class);
	}

		
		RESISTS.add( ElementalStrike.class );
		RESISTS.add( 电击.class);
		RESISTS.add( 死神.class);
		
		RESISTS.add( DM100.LightningBolt.class );
		RESISTS.add( Shaman.EarthenBolt.class );
		RESISTS.add( CrystalWisp.LightBeam.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( YogFist.BrightFist.LightBeam.class );
		RESISTS.add( YogFist.DarkFist.DarkBolt.class );
	}
	
	@Override
	public float proc(Armor armor, Char attacker, Char defender, float damage) {
		//no proc effect, triggers in Char.damage
		return damage;
	}

}