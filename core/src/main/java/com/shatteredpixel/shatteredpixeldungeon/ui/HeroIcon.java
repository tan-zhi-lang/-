

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

//icons for hero subclasses and abilities atm, maybe add classes?
public class HeroIcon extends Image {

	private static TextureFilm film;
	private static final int SIZE = 16;

	//transparent icon
	public static final int NONE    = 111;

	//subclasses
	public static final int BERSERKER   = 0;
	public static final int GLADIATOR   = 1;
	public static final int BATTLEMAGE  = 2;
	public static final int WARLOCK     = 3;
	public static final int ASSASSIN    = 4;
	public static final int FREERUNNER  = 5;
	public static final int SNIPER      = 6;
	public static final int WARDEN      = 7;
	public static final int CHAMPION    = 8;
	public static final int MONK        = 9;
	public static final int PRIEST      = 10;
	public static final int PALADIN     = 11;
	public static final int 神秘学者     = 12;
	public static final int 黑魔导师     = 13;
	public static final int 盾之勇者     = 14;

	//abilities
	public static final int HEROIC_LEAP     = 16;
	public static final int SHOCKWAVE       = 17;
	public static final int ENDURE          = 18;
	public static final int ELEMENTAL_BLAST = 19;
	public static final int WILD_MAGIC      = 20;
	public static final int WARP_BEACON     = 21;
	public static final int SMOKE_BOMB      = 22;
	public static final int DEATH_MARK      = 23;
	public static final int SHADOW_CLONE    = 24;
	public static final int SPECTRAL_BLADES = 25;
	public static final int NATURES_POWER   = 26;
	public static final int SPIRIT_HAWK     = 27;
	public static final int CHALLENGE       = 28;
	public static final int ELEMENTAL_STRIKE= 29;
	public static final int FEINT           = 30;
	public static final int ASCENDED_FORM   = 31;
	public static final int TRINITY         = 32;
	public static final int POWER_OF_MANY   = 33;
	public static final int RATMOGRIFY      = 34;

	//cleric spells
	public static final int GUIDING_LIGHT   = 40;
	public static final int HOLY_WEAPON     = 41;
	public static final int HOLY_INTUITION  = 42;
	public static final int SUNRAY          = 43;
	public static final int DIVINE_SENSE    = 44;
	public static final int BLESS           = 45;
	public static final int CLEANSE         = 46;
	
	public static final int RADIANCE        = 47;
	public static final int HOLY_LANCE      = 48;
	public static final int HALLOWED_GROUND = 49;
	public static final int MNEMONIC_PRAYER = 50;
	public static final int SMITE           = 51;
	public static final int LAY_ON_HANDS    = 52;
	public static final int AURA_OF_PROTECTION = 53;
	public static final int WALL_OF_LIGHT   = 54;
	public static final int DIVINE_INTERVENTION = 55;
	public static final int JUDGEMENT       = 56;
	public static final int FLASH           = 57;
	public static final int BODY_FORM       = 58;
	public static final int MIND_FORM       = 59;
	public static final int SPIRIT_FORM     = 60;
	public static final int BEAMING_RAY     = 61;
	public static final int LIFE_LINK       = 62;
	public static final int STASIS          = 63;

	//all cleric spells have a separate icon with no background for the action indicator
	public static final int SPELL_ACTION_OFFSET      = 32;
	
	
	public static final int 血爆巫术= 100;
	public static final int 物到巫术= 103;
	//action indicator visuals
	public static final int BERSERK         = 0;
	public static final int COMBO           = 1;
	public static final int PREPARATION     = 2;
	public static final int MOMENTUM        = 3;
	public static final int SNIPERS_MARK    = 4;
	public static final int WEAPON_SWAP     = 5;
	public static final int MONK_ABILITIES  = 6;
	
	public static final int 圣光   = 8;
	public static final int 痛命= 16;
	
	public static final int 符咒= 24;
	
	public static final int 风刃= 32;

	public HeroIcon(HeroSubClass subCls){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(subCls.icon()));
	}

	public HeroIcon(ArmorAbility abil){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(abil.icon()));
	}

	public HeroIcon(ActionIndicator.Action action){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(action.actionIcon()));
	}

	public HeroIcon(ClericSpell spell){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}

	public HeroIcon(巫术 spell){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}
	public HeroIcon(道术 spell){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}
	public HeroIcon(忍术 spell){
		super( Assets.Interfaces.HERO_SPELL );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}

}
