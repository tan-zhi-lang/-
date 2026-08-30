package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.火毒元素;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.灵焰元素;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.职业.多面手施法;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.燃烧;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogFist;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制.延迟1回合伤害;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.荆棘;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.ArcaneBomb;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.神圣炸弹;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
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
import com.shatteredpixel.shatteredpixeldungeon.items.wands.潮霆法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.烈焰法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.落石法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.死神;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.电击;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.电击药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.神圣药物;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;

import java.util.HashSet;

public class 元素类型{

	//region 怪物属性
	public static final HashSet<Class> 小老鬼 = new HashSet<>();
	static{
		小老鬼.add(AllyBuff.class);//盟友
		小老鬼.add(Dread.class);//魂飞魄散
		小老鬼.add(Corruption.class);//腐化
	}
	public static final HashSet<Class> 老鬼 = new HashSet<>();
	static{
		老鬼.add(死神.class);//秒杀
		老鬼.add(GrimTrap.class);//即死

		老鬼.addAll(小老鬼);

		//		老鬼.add(复仇卷轴.class);//
		//		老鬼.add(ScrollOfPsionicBlast.class);//灵爆秘卷
	}
	public static final HashSet<Class> 不能移动 = new HashSet<>();
	static{
		不能移动.add(Vertigo.class);//眩晕
	}
	public static final HashSet<Class> 静态 = new HashSet<>();
	static{
		静态.add(AllyBuff.class);//盟友
		静态.add(Dread.class);//魂飞魄散
		静态.add(Terror.class);//恐惧
		静态.add(Amok.class);//狂乱
		静态.add(Charm.class);
		静态.add(Sleep.class);//魔法睡眠
		静态.add(Chill.class);//冻僵
		静态.add(Frost.class);//冻结
		静态.add(Slow.class);//迟缓
		静态.add(Paralysis.class);//麻痹
	}
	//endregion

	public static final HashSet<Class> 火焰 = new HashSet<>();
	static {
		火焰.add( 燃烧.class);
		火焰.add( Fire.class);
		火焰.add( 灵焰.class);
		火焰.add( 灵焰元素.class);
		火焰.add( 火毒.class);
		火焰.add( 火球术.class);
		火焰.add( 火毒元素.class);
		火焰.add( 烈焰法杖.class);
		火焰.add( 焰浪法杖.class);
		火焰.add( Elemental.FireElemental.class);
		火焰.add( Elemental.NewbornFireElemental.class);

	}
	public static final HashSet<Class> 冰霜 = new HashSet<>();
	static {
		冰霜.add( Chill.class);//冰霜
		冰霜.add( Frost.class);//冻结
		冰霜.add( Freezing.class);
		冰霜.add( WandOfFrost.class);
		冰霜.add( 冰海法杖.class);
		冰霜.add( Elemental.FrostElemental.class);
	}
	public static final HashSet<Class> 水 = new HashSet<>();
	static {
		水.add( 水元素.class);
		水.add( GeyserTrap.class);
		水.add( StormCloud.class);
	}
	public static class 水元素{}

	public static final HashSet<Class> 毒= new HashSet<>();
	static {
		毒.add(Ooze.class);//淤泥
		毒.add(Corrosion.class);//酸蚀
		毒.add(ToxicGas.class);//毒气
		毒.add(Poison.class);//中毒

	}

	public static final HashSet<Class> 电 = new HashSet<>();
	static {
		电.add( Paralysis.class);//麻痹
		电.add( Electricity.class);//电场
		电.add( WandOfLightning.class);
		电.add( 潮霆法杖.class);
		电.add( 电击.class);
		电.add( 电击药物.class);
		电.add( 掌心雷.class);
		电.add( DM100.LightningBolt.class);
		电.add( Elemental.ShockElemental.class);
	}
	public static final HashSet<Class> 光 = new HashSet<>();
	static {
		光.add( WandOfPrismaticLight.class);
		光.add( 棱镜法杖.class);
		光.add( 圣光.class);
		光.add( 赐福.class);
		光.add( 神圣药物.class);
		光.add( 神圣炸弹.HolyDamage.class);
		光.add( YogFist.BrightFist.LightBeam.class);
	}
	public static final HashSet<Class> 暗 = new HashSet<>();
	static {
		暗.add( YogFist.DarkFist.DarkBolt.class );
		暗.add( Warlock.DarkBolt.class);
		暗.add( Eye.DeathGaze.class);
		暗.add( DisintegrationTrap.class);
		暗.add( WandOfDisintegration.class);
		暗.add( 影织法杖.class);
	}

	public static final HashSet<Class> 木 = new HashSet<>();
	static {
		木.add( WandOfRegrowth.class);
		木.add( 荆棘.class);
	}
	public static final HashSet<Class> 土 = new HashSet<>();
	static {
		土.add( WandOfLivingEarth.class);
		土.add( 落石法杖.class);
		土.add( 尘遁.class);
	}
	public static final HashSet<Class> 风 = new HashSet<>();
	static {
		风.add( 风刃.class);
		风.add( WandOfBlastWave.class);
	}
	//region 不常见

	public static final HashSet<Class> 无机免疫= new HashSet<>();
	static {
		无机免疫.add(流血.class);
		无机免疫.add(ToxicGas.class);
		无机免疫.add(Poison.class);
	}

	public static final HashSet<Class> 血肉害怕 = new HashSet<>();
	static {
		血肉害怕.addAll(无机免疫);
		血肉害怕.addAll(火焰);
		血肉害怕.addAll(冰霜);
		血肉害怕.addAll(毒);
		血肉害怕.addAll(电);
		血肉害怕.addAll(毒);
	}


	public static final HashSet<Class> 雷光 = new HashSet<>();
	static {
		雷光.addAll(电);
		雷光.addAll(光);
	}
	public static final HashSet<Class> 毒电= new HashSet<>();
	static {
		毒电.addAll(电);
		毒电.addAll(毒);
	}
	public static final HashSet<Class> 雷火= new HashSet<>();
	static {
		雷火.addAll(电);
		雷火.addAll(火焰);
	}
	//endregion

	public static final HashSet<Class> 怪物魔法伤害= new HashSet<>();
	static {
		怪物魔法伤害.add( Elemental.FireElemental.class);
		怪物魔法伤害.add( Elemental.FrostElemental.class);
		怪物魔法伤害.add( Elemental.ShockElemental.class);
		怪物魔法伤害.add( Elemental.NewbornFireElemental.class);

		怪物魔法伤害.add( DM100.LightningBolt.class);
		怪物魔法伤害.add( Shaman.EarthenBolt.class);
		怪物魔法伤害.add( CrystalWisp.LightBeam.class);
		怪物魔法伤害.add( Warlock.DarkBolt.class);
		怪物魔法伤害.add( Eye.DeathGaze.class);
		怪物魔法伤害.add( YogFist.BrightFist.LightBeam.class);
		怪物魔法伤害.add( YogFist.DarkFist.DarkBolt.class );
	}
	public static final HashSet<Class> 魔法伤害= new HashSet<>();
	static {
		魔法伤害.add(痛命.class);
		魔法伤害.add(符咒.class);
		魔法伤害.add(多面手施法.class);

		魔法伤害.add(WandOfMagicMissile.class);
		魔法伤害.add(WandOfTransfusion.class);
		魔法伤害.add(WandOfCorruption.class);

		魔法伤害.add(WandOfWarding.class);
		魔法伤害.add(WandOfWarding.Ward.class);

		魔法伤害.add(ArcaneBomb.class);
		魔法伤害.add(Dungeon.class);
		魔法伤害.add(延迟1回合伤害.class);

	}
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static{
		RESISTS.add(MagicalSleep.class);//魔法睡眠
		RESISTS.add(Charm.class);//魅惑
		RESISTS.add(Weakness.class);//虚弱
		RESISTS.add(Vulnerable.class);//易伤
		RESISTS.add(Hex.class);//幻惑
		RESISTS.add(Degrade.class);//降级

		RESISTS.add(DisintegrationTrap.class);//解离
		RESISTS.add(GrimTrap.class);//即死

		RESISTS.add(复仇卷轴.class);
		RESISTS.add(ScrollOfPsionicBlast.class);//灵爆

		RESISTS.addAll(魔法伤害);
		RESISTS.addAll(怪物魔法伤害);

		RESISTS.addAll(火焰);
		RESISTS.addAll(冰霜);
		RESISTS.addAll(毒);
		RESISTS.addAll(电);

		RESISTS.addAll(光);
		RESISTS.addAll(暗);

		RESISTS.addAll(水);
		RESISTS.addAll(木);
		RESISTS.addAll(土);
		RESISTS.addAll(风);



	}

}
