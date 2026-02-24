

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.法术;
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
	public static final int GLADIATOR   = BERSERKER+1;

	public static final int 冰结师  = 8;
	public static final int 元素法师  = 冰结师+1;

	public static final int ASSASSIN    = 2*8;
	public static final int 神偷无影    = ASSASSIN+1;
	
	public static final int SNIPER      = 3*8;
	public static final int WARDEN      = SNIPER+1;
	
	public static final int CHAMPION    = 4*8;
	public static final int MONK        = CHAMPION+1;
	
	public static final int PALADIN      = 5*8;
	public static final int PRIEST= PALADIN+1;

	public static final int 黑魔导师     = 6*8;
	public static final int BATTLEMAGE  = 黑魔导师+1;
	
	public static final int 盾之勇者     = 7*8;
	public static final int 轻装步兵     = 盾之勇者+1;

	public static final int 灵月杀手     = 8*8;
	public static final int 不灭战士     = 灵月杀手+1;

	public static final int WARLOCK     = 9*8;
	public static final int 真人     = WARLOCK+1;

	public static final int FREERUNNER  = 10*8;
	
	public static final int 征服者     = 11*8;
	public static final int 皇室卫兵     = 征服者+1;

	public static final int 神兽之灵     = 12*8;
	public static final int 养殖专家     = 神兽之灵+1;

	public static final int 灵魂武者     = 14*8;
	public static final int 土影     = 灵魂武者+1;

	public static final int 阿修罗     = 15*8;
	public static final int 指环王     = 阿修罗+1;

	public static final int 黑白双子     = 20*8;

	public static final int 巫咒王鼠     = 21*8;
	public static final int 实验狂鼠     = 巫咒王鼠+1;

	public static final int 金刚独狼     = 23*8;
	public static final int 血法师     = 金刚独狼+1;
	public static final int 时间刺客     = 24*8;

	public static final int ELEMENTAL_BLAST = 19;
	public static final int WILD_MAGIC      = 20;
	public static final int WARP_BEACON     = 21;
	public static final int SHADOW_CLONE    = 24;
	public static final int SPIRIT_HAWK     = 27;
	public static final int CHALLENGE       = 28;
	public static final int ELEMENTAL_STRIKE= 29;
	public static final int FEINT           = 30;

	//action indicator visuals
	public static final int BERSERK         = 25*8;
	public static final int COMBO           = BERSERK+1;
	public static final int PREPARATION     = BERSERK+2;
	public static final int MOMENTUM        = BERSERK+3;
	public static final int SNIPERS_MARK    = BERSERK+4;
	public static final int WEAPON_SWAP     = BERSERK+5;
	public static final int MONK_ABILITIES  = BERSERK+6;
	public static final int 时间能力  = BERSERK+7;

	public static final int 火球术=27*8;
	public static final int 火墙术=火球术+2;
	public static final int 破冰飞刃=火球术+4;
	public static final int 冰魄之弓=火球术+6;
	public static final int 圣光   = 28*8;
	public static final int 赐福=圣光+2;

	public static final int 痛命= 29*8;
	public static final int 死血= 痛命+2;

	public static final int 符咒= 30*8;
	public static final int 掌心雷= 符咒+2;

	public static final int 风刃= 31*8;
	public static final int 木遁= 风刃+2;
	public static final int 尘遁= 风刃+4;
	public static final int 土遁= 风刃+6;

	public static final int 潜能觉醒     = 32*8;

	public HeroIcon(HeroSubClass subCls){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(subCls.icon()));
	}

	public HeroIcon(ArmorAbility abil){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(abil.icon()));
	}

	public HeroIcon(ActionIndicator.Action action){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(action.actionIcon()));
	}

	public HeroIcon(ClericSpell spell){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}

	public HeroIcon(巫术 spell){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}
	public HeroIcon(道术 spell){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}
	public HeroIcon(忍术 spell){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}
	public HeroIcon(法术 spell){
		super( Assets.Interfaces.HERO_ICONS );
		if (film == null){
			film = new TextureFilm(texture, SIZE, SIZE);
		}
		frame(film.get(spell.icon()));
	}

}
