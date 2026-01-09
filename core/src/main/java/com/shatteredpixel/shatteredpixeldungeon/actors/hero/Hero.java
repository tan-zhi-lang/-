

package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Bones;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.SacrificialFire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AdrenalineSurge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Drowsy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Foresight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.GreaterHaste;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HeroDisguise;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.HoldFast;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Levitation;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shadows;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimeStasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.征服;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.怒气;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.护盾;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.时间能力;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.白猫保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.连击;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.隔天休息;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.DivineSense;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HallowedGround;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Smite;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.风刃;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DM0;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.TransmogRat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.毒气宝箱怪;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.超级魔法绵羊;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CheckedCell;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Ankh;
import com.shatteredpixel.shatteredpixeldungeon.items.Dewdrop;
import com.shatteredpixel.shatteredpixeldungeon.items.EnergyCrystal;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap.Type;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Stone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.巫服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.忍服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.披风;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.武服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.祭服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.训服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.连裙;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.铠甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.风衣;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.魔披;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.召唤物品;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.荆棘斗篷;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.虫箭;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.磨损钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfFeatherFall;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.根骨秘药;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.六神之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.命中之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.幸运之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.闪避之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.EyeOfNewt;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ThirteenLeafClover;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.传奇肛塞;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.巨大蟹钳;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.火毒箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.破损短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.神圣之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.虚无透纱;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.角斗链枷;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.遗失符石;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.骸骨左轮;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
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
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手里剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.MiningLevel;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.WeakFloorRoom;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ShadowCaster;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.StatusPane;
import com.shatteredpixel.shatteredpixeldungeon.ui.TargetHealthIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndHero;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndResurrect;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTradeItem;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.系统设置;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.Delayer;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Hero extends Char {

    {
        actPriority = HERO_PRIO;

        alignment = Alignment.ALLY;
    }

    public static final int 最大等级 = 25;

    private static final float TIME_TO_REST = 1f;
    private static final float TIME_TO_SEARCH = 2f;
    private static final float HUNGER_FOR_SEARCH = 6f;

    public HeroClass heroClass = HeroClass.NONE;
    public HeroClass heroClass蜕变 = HeroClass.NONE;
    public HeroSubClass subClass = HeroSubClass.NONE;
    public ArmorAbility armorAbility = null;
    public ArrayList<LinkedHashMap<Talent, Integer>> talents = new ArrayList<>();
    public LinkedHashMap<Talent, Talent> metamorphedTalents = new LinkedHashMap<>();

    private int 最大命中 = 10;
    private int 最大闪避 = 5;
    public float 之前健身= 0;
    public float 现在健身= 0;
    public boolean[] 天赋 = {false,false,false,false,false,};
    public boolean 单身 = false;
    public boolean 进阶 = false;
    public String 重生怪物 = "";
    public float 生命成长 = 0;
    public float 攻击成长 = 0;
    public float 防御成长 = 0;
    public float 之前攻击延迟 = 0;
    public float 吃饭触发 = 0;

    public boolean ready = false;
    public boolean damageInterrupt = true;
    public HeroAction curAction = null;
    public HeroAction lastAction = null;

    //reference to the enemy the hero is currently in the process of attacking
    private Char attackTarget;

    public boolean resting = false;

    public Belongings belongings;

    public int 力量;
    public int 武器力量= 0;
    public int 根骨 = 0;
    public int 连击 = 0;
    public int 护甲力量= 0;
    public int 投机之剑= 0;
    public int 英精英雄= -1;
    public boolean[] 特性之征=new boolean[]{
            false,//0吸血习性+7.5%全能吸血，全能吸血和吸血之外的治疗无效。
            false,//1生命溢流敌人受到伤害+10%最大生命/200
            false,//2移动恢复
    };
    public float 英精英雄成长= 0;
    public float 护甲恢复= 0;
    public boolean 地牢塔防开关 =false;
    public boolean 地牢塔防去下一层 =false;
    public boolean 地牢塔防更多更快开关 =false;
    public boolean 地牢塔防老鬼开关 =true;
    public int 地牢塔防生成速度= 0;
    public int 地牢塔防波次= 0;
    public int 蛇皮走位=0;
    public float 造成伤害=1;
    public float 受到伤害=1;

    public int 等级 = 1;
    public int 当前经验 = 0;

    public int HTBoost = 0;

    private ArrayList<Mob> visibleEnemies;

    //This list is maintained so that some logic checks can be skipped
    // for enemies we know we aren't seeing normally, resulting in better performance
    public ArrayList<Mob> mindVisionEnemies = new ArrayList<>();

    public Hero() {
        super();
        大小=1;
        生命 = 最大生命 = 20;
        护甲=最大护甲();
        力量 = 10;

        belongings = new Belongings(this);
        
        visibleEnemies = new ArrayList<>();
    }
    @Override
    protected synchronized void onRemove() {
        //same as super, except we retain charger for rankings purposes
        for (Buff buff : buffs()) {
//            if (buff instanceof MeleeWeapon.Charger){
//                Actor.remove(buff);
//            } else {
                buff.detach();//排名可能显示充能数量
//            }
        }
    }
    public void 更新属性() {

        最大生命 = 20 + Math.round(3* (等级 - 1)) + HTBoost;
        
        最大生命+=根骨 * 5;
        if (buff(根骨秘药.HTBoost.class) != null) {
            最大生命 += buff(根骨秘药.HTBoost.class).boost();
        }

        最大生命+=Math.round(生命成长);
        最大生命+=RingOfMight.strengthBonus(this)*25;
        最大生命+=天赋点数(Talent.勇武,5);
        
        if (belongings.armor() instanceof 巫服) {
            最大生命 *= 1.1f;
        }
        if (heroClass(HeroClass.重武)) {
            最大生命 *= 1.1f;
        }
        if(subClass(HeroSubClass.血法师)&&职业精通())最大生命*=1.3f;
        最大生命*=1+英精英雄成长;
        最大生命*=角斗链枷.减少();
        最大生命 *= 综合属性();

        生命 = Math.min(生命, 最大生命);
    }

    public int 力量() {

        int str = 力量 + RingOfMight.strengthBonus(this)*2;
        
        str+=天赋点数(Talent.健身);
        AdrenalineSurge buff = buff(AdrenalineSurge.class);
        if (buff != null) {
            str += buff.boost();
        }
        if(heroClass(HeroClass.兽灵)){
            str+=最大生命(0.04f);
        }
        float x = 1;
        x *= 1 + 天赋点数(Talent.力大无穷,0.1f);
        
        if (heroClass(HeroClass.行僧)&&裸衣()) {
            x *= 1.2f;
        }
        if (heroClass(HeroClass.WARRIOR)) {
            x *= 1.1f;
        }
        if(Holiday.getCurrentHoliday()==Holiday.妇女节&&(heroClass(HeroClass.HUNTRESS)||
                                                     heroClass(HeroClass.DUELIST)||
                                                     heroClass(HeroClass.巫女)||
                                                     heroClass(HeroClass.女忍)||
                                                     heroClass(HeroClass.逐姝)||
                                                     heroClass(HeroClass.罗兰)
                                                    )){
            x *= 1.1f;
        }

        x *= 综合属性();
        if(单身){
            x*=1.1f;
        }
        if(subClass(HeroSubClass.健身猛男)){
            if(hasbuff(隔天休息.class)&&hasbuff(Hunger.class)&&buff(Hunger.class).饥饿()){
                x/=2;
            }
            if(职业精通()){
                x*=1.2f;
            }
            x*=1+之前健身;
        }
        //最后结算
        str = Math.round(str * x);
        return str;
    }

    private static final String CLASS = "class";
    private static final String heroClass蜕变x = "heroClass蜕变";
    private static final String SUBCLASS = "subClass";
    private static final String ABILITY = "armorAbility";

    private static final String LEVEL = "lvl";
    private static final String 力量x = "力量";
    private static final String 武器力量x= "武器力量";
    private static final String 根骨x = "根骨";
    private static final String 连击x = "连击";
    private static final String 护甲力量x= "护甲力量";
    private static final String 投机之剑x= "投机之剑";
    private static final String 英精英雄x= "英精英雄";
    private static final String 护甲恢复x= "护甲恢复";
    private static final String 英精英雄成长x= "英精英雄成长";
    private static final String 特性之征x= "特性之征";
    private static final String 地牢塔防开关x= "地牢塔防开关";
    private static final String 地牢塔防去下一层x= "地牢塔防去下一层";
    private static final String 地牢塔防更多更快开关x= "地牢塔防更多更快开关";
    private static final String 地牢塔防老鬼开关x= "地牢塔防老鬼开关";
    private static final String 地牢塔防生成速度x= "地牢塔防生成速度";
    private static final String 地牢塔防波次x= "地牢塔防波次";
    private static final String 蛇皮走位x= "蛇皮走位";
    private static final String 造成伤害x = "造成伤害";
    private static final String 受到伤害x = "受到伤害";

    private static final String 之前健身x= "之前健身";
    private static final String 现在健身x= "现在健身";
    private static final String 单身x = "单身";
    private static final String 进阶x = "进阶";
    private static final String 重生怪物x = "重生怪物";
    private static final String 天赋x = "天赋";
    protected static final String 生命成长x    = "生命成长";
    protected static final String 攻击成长x    = "攻击成长";
    protected static final String 防御成长x    = "防御成长";
    protected static final String 之前攻击延迟x    = "之前攻击延迟";
    protected static final String 吃饭触发x    = "吃饭触发";
    private static final String EXPERIENCE = "exp";
    private static final String HTBOOST = "htboost";

    @Override
    public void storeInBundle(Bundle bundle) {

        super.storeInBundle(bundle);

        bundle.put(CLASS, heroClass);
        bundle.put(heroClass蜕变x, heroClass蜕变);
        bundle.put(SUBCLASS, subClass);
        bundle.put(ABILITY, armorAbility);
        Talent.storeTalentsInBundle(bundle, this);

        bundle.put(LEVEL, 等级);
        bundle.put(力量x, 力量);
        bundle.put(武器力量x,武器力量);
        bundle.put(根骨x, 根骨);
        bundle.put(连击x, 连击);
        bundle.put(护甲力量x,护甲力量);
        bundle.put(投机之剑x,投机之剑);
        bundle.put(英精英雄x,英精英雄);
        bundle.put(护甲恢复x,护甲恢复);
        bundle.put(英精英雄成长x,英精英雄成长);
        bundle.put(特性之征x,特性之征);
        bundle.put(地牢塔防开关x,地牢塔防开关);
        bundle.put(地牢塔防去下一层x,地牢塔防去下一层);
        bundle.put(地牢塔防更多更快开关x,地牢塔防更多更快开关);
        bundle.put(地牢塔防老鬼开关x,地牢塔防老鬼开关);
        bundle.put(地牢塔防生成速度x,地牢塔防生成速度);
        bundle.put(地牢塔防波次x,地牢塔防波次);
        bundle.put(蛇皮走位x,蛇皮走位);
        bundle.put(造成伤害x, 造成伤害);
        bundle.put(受到伤害x, 受到伤害);

        bundle.put(之前健身x,之前健身);
        bundle.put(现在健身x,现在健身);
        bundle.put(单身x, 单身);
        bundle.put(进阶x, 进阶);
        bundle.put(重生怪物x, 重生怪物);
        bundle.put(天赋x, 天赋);
        bundle.put( 生命成长x, 生命成长);
        bundle.put( 攻击成长x, 攻击成长);
        bundle.put( 防御成长x, 防御成长);
        bundle.put( 之前攻击延迟x, 之前攻击延迟);
        bundle.put( 吃饭触发x, 吃饭触发);
        bundle.put(EXPERIENCE, 当前经验);

        bundle.put(HTBOOST, HTBoost);

        belongings.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {

        等级 = bundle.getInt(LEVEL);
        力量 = bundle.getInt(力量x);
        武器力量= bundle.getInt(武器力量x);
        根骨 = bundle.getInt(根骨x);
        连击 = bundle.getInt(连击x);
        护甲力量= bundle.getInt(护甲力量x);
        投机之剑= bundle.getInt(投机之剑x);
        英精英雄= bundle.getInt(英精英雄x);
        护甲恢复= bundle.getFloat(护甲恢复x);
        英精英雄成长= bundle.getFloat(英精英雄成长x);
        特性之征= bundle.getBooleanArray(特性之征x);
        地牢塔防开关= bundle.getBoolean(地牢塔防开关x);
        地牢塔防去下一层= bundle.getBoolean(地牢塔防去下一层x);
        地牢塔防更多更快开关= bundle.getBoolean(地牢塔防更多更快开关x);
        地牢塔防老鬼开关= bundle.getBoolean(地牢塔防老鬼开关x);
        地牢塔防生成速度= bundle.getInt(地牢塔防生成速度x);
        地牢塔防波次= bundle.getInt(地牢塔防波次x);
        蛇皮走位= bundle.getInt(蛇皮走位x);
        造成伤害 = bundle.getFloat(造成伤害x);
        受到伤害 = bundle.getFloat(受到伤害x);
        
        之前健身= bundle.getFloat(之前健身x);
        现在健身= bundle.getFloat(现在健身x);
        单身 = bundle.getBoolean(单身x);
        进阶 = bundle.getBoolean(进阶x);
        重生怪物 = bundle.getString(重生怪物x);
        天赋 = bundle.getBooleanArray(天赋x);
        生命成长 = bundle.getFloat( 生命成长x );
        攻击成长 = bundle.getFloat( 攻击成长x );
        防御成长 = bundle.getFloat( 防御成长x );
        之前攻击延迟 = bundle.getFloat( 之前攻击延迟x );
        吃饭触发 = bundle.getFloat( 吃饭触发x );
        当前经验 = bundle.getInt(EXPERIENCE);

        HTBoost = bundle.getInt(HTBOOST);

        super.restoreFromBundle(bundle);

        heroClass = bundle.getEnum(CLASS, HeroClass.class);
        heroClass蜕变 = bundle.getEnum(heroClass蜕变x, HeroClass.class);
        subClass = bundle.getEnum(SUBCLASS, HeroSubClass.class);
        armorAbility = (ArmorAbility) bundle.get(ABILITY);
        Talent.restoreTalentsFromBundle(bundle, this);


        belongings.restoreFromBundle(bundle);
    }

    public static void preview(GamesInProgress.Info info, Bundle bundle) {
        info.level = bundle.getInt(LEVEL);
        info.exp = bundle.getInt(EXPERIENCE);
        info.heroClass = bundle.getEnum(CLASS, HeroClass.class);
        info.heroClass蜕变 = bundle.getEnum(heroClass蜕变x, HeroClass.class);
        info.subClass = bundle.getEnum(SUBCLASS, HeroSubClass.class);
        Belongings.preview(info, bundle);
    }

    public boolean 天赋(Talent talent) {
        return 天赋点数(talent) > 0;
    }

    public int 天赋点数(Talent talent) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) return tier.get(f);
            }
        }
        return 0;
    }

    public int 天赋生命力(Talent talent) {
        return 生命力(天赋点数(talent));
    }

    public int 天赋生命力(Talent talent, float x) {
        return 生命力(天赋点数(talent, x));
    }

    public boolean 满天赋(Talent talent) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) return tier.get(f) >= f.最大点数();
            }
        }
        return false;
    }

    public boolean 天赋概率(Talent talent, int x) {
        return Random.Int(1, 100) <= 天赋点数(talent) * x + (x == 33 ? 1 : 0);
    }

    public int 天赋点数(Talent talent, int x) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) {
//                    if (f.最大点数() == 2) {
//                        if (tier.get(f) == 1) {
//                            return x;
//                        } else if (tier.get(f) == 2) {
//                            return Math.round(x * 1.5f);
//                        }
//                    }
                    if (f.最大点数() >= 1) {
                        return tier.get(f) * x;
                    }
                }
            }
        }
        return 0;
    }

    public float 天赋点数(Talent talent, float x) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) {
//                    if (f.最大点数() == 2) {
//                        if(x==0.66f){
//                            if (tier.get(f) == 1) {
//                                return x;
//                            } else if (tier.get(f) == 2) {
//                                return 1.5f * x+0.01f;
//                            }
//                        }else{
//                            if (tier.get(f) == 1) {
//                                return x;
//                            } else if (tier.get(f) == 2) {
//                                return 1.5f * x;
//                            }
//                        }
//                    }
                    if (f.最大点数() >= 3) {
                        if(x==0.33f)
                        return tier.get(f) * x+0.01f;
                        else
                        return tier.get(f) * x;
                    }
                    if (f.最大点数() >= 1) {
                        return tier.get(f) * x;
                    }
                }
            }
        }
        return 0;
    }

    public void upgradeTalent(Talent talent) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) tier.put(talent, tier.get(talent) + 1);
            }
        }
        Talent.获得天赋时(this, talent);
    }

    public int talentPointsSpent(int tier) {
        int total = 0;
        for (int i : talents.get(tier - 1).values()) {
            total += i;
        }
        return total;
    }

    public int talentPointsAvailable(int tier) {
        if (等级 < (Talent.天赋解锁[tier] - 1)
                || (tier == 3 && subClass == HeroSubClass.NONE)
                || (tier == 4 && armorAbility == null)) {
            return 0;
        } else if (等级 >= Talent.天赋解锁[tier + 1]) {
            return Talent.天赋解锁[tier + 1] - Talent.天赋解锁[tier] - talentPointsSpent(tier) + bonusTalentPoints(tier);
        } else {
            return 1 + 等级 - Talent.天赋解锁[tier] - talentPointsSpent(tier) + bonusTalentPoints(tier);
        }
    }

    public int bonusTalentPoints(int tier) {
        int x=0;
        if(天赋[tier]){
            x++;
        }
        if(进阶&&tier==2){
            x+=2;
        }
        if(进阶&&tier==3){
            x+=3;
        }
        if (buff(PotionOfDivineInspiration.DivineInspirationTracker.class) != null
             && buff(PotionOfDivineInspiration.DivineInspirationTracker.class).isBoosted(tier)) {
            x++;
        }

        if (等级 < (Talent.天赋解锁[tier] - 1)
                || (tier == 3 && subClass == HeroSubClass.NONE)
                || (tier == 4 && armorAbility == null)) {
            return x;
        } else {
            return x;
        }
    }

    public String className() {
        if(heroClass蜕变==HeroClass.NONE){
            return subClass == null || subClass == HeroSubClass.NONE ? heroClass.title() : subClass.title();
        }
        return subClass == null || subClass == HeroSubClass.NONE ?
                heroClass.title()+"\n"+heroClass蜕变
                : subClass.title()+"\n"+heroClass蜕变;
    }

    @Override
    public String name() {
        if (buff(HeroDisguise.class) != null) {
            return buff(HeroDisguise.class).getDisguise().title();
        } else {
            return className();
        }
    }

    @Override
    public void hitSound(float pitch) {
        if (!武力之戒.fightingUnarmed(this)) {
            belongings.attackingWeapon().hitSound(pitch);
        } else if (武力之戒.getBuffedBonus(this,武力之戒.Force.class)>0) {
            //pitch deepens by 2.5% (additive) per point of strength, down to 75%
            super.hitSound(pitch * GameMath.gate(0.75f, 1.25f - 0.025f * 力量(), 1f));
        } else {
            super.hitSound(pitch * 1.1f);
        }
    }

    @Override
    public boolean blockSound(float pitch) {
        if (belongings.weapon() != null && belongings.weapon().defenseFactor(this) >= 4) {
            Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1, pitch);
            return true;
        }
        return super.blockSound(pitch);
    }

	public void live() {
        for (Buff b : buffs()) {
            if (!b.revivePersists) b.detach();
        }
        Buff.施加(this, 再生.class);
        Buff.施加(this, Hunger.class);
        Buff.施加(this,白猫保护.class);
    }
    public int tier() {
        Armor armor = belongings.armor();
        if (armor instanceof ClassArmor) {
            return 6;
        }
        if(heroClass(HeroClass.凌云)){
            return 0;
        }
//        if(heroSubClass(subClass.合金忍者)){
//            return 6;
//        }
        if(heroClass(HeroClass.灵猫)||heroClass(HeroClass.鼠弟)){
            if(armor!=null){
                return 1;
            }
            return 0;
        }
        if (armor != null&&armor.专属) {
            return 6;
        } else if (armor != null) {
            return armor.tier;
        } else {
            return 0;
        }
    }

    public static int tier(HeroClass hs) {
        if(hs==HeroClass.灵猫){
            return 1;
        }
        if(hs==HeroClass.鼠弟){
            return 1;
        }
        if(hs==HeroClass.血鬼||hs==HeroClass.来世||hs==HeroClass.凌云){
            return 0;
        }
        return 6;
    }

    public boolean shoot(Char enemy, Weapon wep) {

        attackTarget = enemy;
        boolean wasEnemy = enemy.alignment == Alignment.ENEMY
                || (enemy instanceof Mimic && enemy.alignment == Alignment.NEUTRAL);

        //temporarily set the hero's weapon to the missile weapon being used
        //TODO improve this!
        belongings.thrownWeapon = wep;
        boolean hit = attack(enemy);
        Invisibility.notimedispel();
        belongings.thrownWeapon = null;

        if (hit&& subClass == HeroSubClass.角斗士&&wasEnemy) {
            Buff.施加(this, 连击.class).hit(enemy);
        }

//        if (hit && heroClass(HeroClass.DUELIST) && wasEnemy) {
//            Buff.施加(this, 双剑.ComboStrikeTracker.class).addHit();
//        }

        attackTarget = null;
        return hit;
    }
	
	//region 攻击动作
	public void 攻击(Char enemy,float dmgMulti, float dmgBonus, float accMulti){
        sprite.attack(enemy.pos, new Callback() {
            @Override
            public void call() {
                AttackIndicator.target(enemy);
                if (attack(enemy,dmgMulti,dmgBonus,accMulti)) {
                    Sample.INSTANCE.play(Assets.Sounds.HIT);
                }
                Invisibility.notimedispel();
                spendAndNext(攻击延迟());
            }
        });
    }
    public void 攻击(Char enemy,float dmgMulti, float dmgBonus){
        sprite.attack(enemy.pos, new Callback() {
            @Override
            public void call() {
                AttackIndicator.target(enemy);
                if (attack(enemy,dmgMulti,dmgBonus,1)) {
                    Sample.INSTANCE.play(Assets.Sounds.HIT);
                }
                Invisibility.notimedispel();
                spendAndNext(攻击延迟());
            }
        });
    }
    public void 攻击(Char enemy,float dmgMulti){
        sprite.attack(enemy.pos, new Callback() {
            @Override
            public void call() {
                AttackIndicator.target(enemy);
                if (attack(enemy,dmgMulti,0,1)) {
                    Sample.INSTANCE.play(Assets.Sounds.HIT);
                }
                Invisibility.notimedispel();
                spendAndNext(攻击延迟());
            }
        });
    }
    public boolean 攻击(Char enemy){
        return attack(enemy,1,0,1);
    }
    public boolean attack(Char enemy,float dmgMulti){
        return attack(enemy,dmgMulti,0,1);
    }
    public boolean attack(Char enemy,float dmgMulti, float accMulti){
        return attack(enemy,dmgMulti,0,accMulti);
    }
    @Override
    public boolean attack(Char enemy, float dmgMulti, float dmgBonus, float accMulti) {
        boolean result = super.attack(enemy, dmgMulti, dmgBonus, accMulti);
        if (!(belongings.attackingWeapon() instanceof Weapon)) {
            if (buff(Talent.PreciseAssaultTracker.class) != null) {
                buff(Talent.PreciseAssaultTracker.class).detach();
            } else if (buff(Talent.LiquidAgilACCTracker.class) != null
                    && buff(Talent.LiquidAgilACCTracker.class).uses <= 0) {
                buff(Talent.LiquidAgilACCTracker.class).detach();
            }
        }
        return result;
    }
	
	//endregion
	
	//region 命中
	@Override
    public int 最小命中(Char target ) {
        float x=0;
        if(heroClass(HeroClass.镜魔))x+=2;

        x+=命中之戒.getBuffedBonus(this,命中之戒.Accuracy.class)*2;
        x*=增加命中();
        
        return Math.round(x);
    }
    @Override
    public float 增加命中() {
        float x=1;
        x+=天赋点数(Talent.集中,0.15f);
        return x;
    }
    @Override
    public int 最大命中(Char target) {
        KindOfWeapon wep = belongings.attackingWeapon();
        float accuracy=Math.round((最大命中 + (等级 - 1) * 1.2f));
        accuracy+=天赋点数(Talent.顶福精华, 10);
        
        accuracy *= 增加命中()*综合属性();
        if (belongings.armor() instanceof 武服) {
            accuracy *= 1.1f;
        }
        accuracy *= 1 + 天赋点数(Talent.顶福精华, 0.13f);
        
        if(wep==null){
            accuracy*=1+(力量()-10)*属性增幅;
        }
        accuracy*=神圣之剑.减少();
        
        if(英精英雄==4)accuracy*=4;
        accuracy*=1+英精英雄成长;
        //precise assault and liquid agility
        if (!(wep instanceof Weapon)) {
            if (false//使用武技能命中+
                    //does not trigger on ability attacks
                    && belongings.abilityWeapon != wep && buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) == null) {

                //non-duelist benefit for precise assault, can stack with liquid agility
                if (heroClass != HeroClass.DUELIST) {
                    //persistent +10%/20%/30% ACC for other heroes
                    accuracy *= 2;
                }
//                    if (wep instanceof 十字弩&&buff(十字弩.ChargedShot.class)!=null) {
//                    //do nothing, this is not a regular attack so don't consume talent fx
//                } else
                    if (buff(Talent.PreciseAssaultTracker.class) != null){
                        // 2x/5x/inf. ACC for duelist if she just used a weapon ability、
                        accuracy*=Float.POSITIVE_INFINITY;
                    }
            }
        }
        if (buff(Talent.LiquidAgilACCTracker.class) != null) {
                accuracy *= Float.POSITIVE_INFINITY;
            Talent.LiquidAgilACCTracker buff = buff(Talent.LiquidAgilACCTracker.class);
            buff.uses--;
        }

//        if (buff(弯刀.SwordDance.class)!=null) {
//            accuracy *= 1.50f;
//        }

        if(belongings.secondWep==null){
            
            if (!武力之戒.fightingUnarmed(this)&&target!=null) {
                accuracy*=wep.accuracyFactor(this, target);
            }
        }else{
            
            if (!武力之戒.fightingUnarmed(this)&&target!=null) {
                accuracy*=wep.accuracyFactor(this, target);
            }
            if (!武力之戒.fightingUnarmed(this)&&target!=null) {
                accuracy*=belongings.secondWep.accuracyFactor(this, target);
            }
        }
        
        
        return Math.max(1, Math.round(accuracy));
    }
	//endregion
	
	//region 闪避
	@Override
    public int 最小闪避(Char enemy ) {
        float evasion=0;
        evasion+=闪避之戒.getBuffedBonus(this,闪避之戒.Evasion.class)*2;
        
        if(heroClass(HeroClass.女忍)){
            evasion+=最大闪避(enemy)*0.1f;
        }
        
        evasion+=天赋点数(Talent.招架,2);
        evasion*=增加闪避();
        return Math.round(evasion);
    }

    @Override
    public float 增加闪避() {
        float x=1;
        if(重生怪物.equals("下水道巨蛇"))x+=5f;

        x+=天赋点数(Talent.躲避,0.15f);
        
        
        x+=天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        return x;
    }
    @Override
    public int 最大闪避(Char enemy) {

        if (buff(连击.ParryTracker.class)!=null&&enemy!=null) {
            if (canAttack(enemy) && !isCharmedBy(enemy)) {
                Buff.施加(this, 连击.RiposteTracker.class).enemy = enemy;
            }
            return Char.INFINITE;
        }

//        if (buff(圆盾.GuardTracker.class)!=null) {
//            return Char.INFINITE_ACCURACY;
//        }

        float evasion = (最大闪避 + Math.round((等级 - 1) * 1.2f));
        
        evasion+=闪避之戒.getBuffedBonus(this,闪避之戒.Evasion.class)*2;
        evasion += 天赋点数(Talent.顶福精华, 5);
        evasion *= 1 + 天赋点数(Talent.顶福精华, 0.08f);
        evasion *= 综合属性();
        evasion*=增加闪避();
        if (hasbuff(Momentum.class)){
            evasion *= buff(Momentum.class).evasionBonus();
        }
        if(裸衣()){
            evasion*=1+(力量()-10)*属性增幅;
        }else if(天赋(Talent.快速布阵)){
            evasion*=1+(力量()-10)*属性增幅*(天赋点数(Talent.快速布阵,0.5f));
        }
        if(英精英雄==4)evasion*=4;
        evasion*=1+英精英雄成长;
        if (belongings.armor instanceof 风衣) {
            evasion *= 1.1f;
        }
        
        if (paralysed > 0) {
            evasion /= 2;
        }
        if(heroClass(HeroClass.凌云)){
            evasion *= 2;
        }
        if (belongings.armor() != null) {
            evasion = belongings.armor().evasionFactor(this, evasion);
        }
        //stone specifically overrides to 0 always, guaranteed hit
        if (belongings.armor() != null&&belongings.armor().hasGlyph(Stone.class, this) && !Stone.testingEvasion()){
            return 0;
        }
        return Math.max(1, Math.round(evasion));
    }
	//endregion

    @Override
    public String defenseVerb() {
        连击.ParryTracker parry = buff(连击.ParryTracker.class);
        if (parry != null) {
            parry.parried = true;
            if (buff(连击.class).getComboCount()<9||天赋点数(Talent.连击强化)<2) {
                parry.detach();
            }
            return Messages.get(Monk.class, "parried");
        }


        if (buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class) != null) {
            buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class).detach();
            if (sprite != null && sprite.visible) {
                Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1, Random.Float(0.96f, 1.05f));
            }
            return Messages.get(Monk.class, "parried");
        }

        return super.defenseVerb();
    }

    @Override
    public int 最小防御() {
        float dr =super.最小防御();
        
        if (belongings.armor() != null) {
            int armDr = belongings.armor().最小防御();
            if (力量() < belongings.armor().力量() && !heroClass(HeroClass.重武)) {
                armDr -= 2 * (belongings.armor().力量() - 力量());
            }
            if (armDr > 0) dr += armDr;
        }
        if (belongings.weapon() != null && !武力之戒.fightingUnarmed(this)) {
            int wepDr = 0;
            
            if (力量() < ((Weapon) belongings.weapon()).力量() && !heroClass(HeroClass.重武)) {
                wepDr -= 2 * (((Weapon) belongings.weapon()).力量() - 力量());
            }
            if (wepDr > 0) dr += wepDr;
        }
        if (buff(HoldFast.class) != null) {
            dr += buff(HoldFast.class).armorBonus();
        }
        if(hasbuff(装甲之戒.装甲.class)){
            if(belongings.armor()==null){
                dr+=装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class)+装甲之戒.tier();
            }
        }
        dr+=天赋点数(Talent.武装);
        
        dr*=增加防御();
        return Math.round(dr);
    }
    
    @Override
    public float 增加防御(){
        float x=1;
        x+=天赋点数(Talent.全副武装,0.25f);
        x-=虚无透纱.减少();
        return x;
    }
    
    //变相削弱
    @Override
    public int 最大防御() {
        float dr =super.最大防御()+Math.round(防御成长);
        
        if(Dungeon.系统(系统设置.金币能力)){
            dr+=Math.round(Dungeon.gold/100);
        }
        if(天赋(Talent.双武格挡)){
            if(belongings.secondWep!=null){
                if(belongings.weapon!=null){
                    dr+=天赋点数(Talent.双武格挡,0.25f)*
                        (belongings.weapon.tier+belongings.secondWep.tier)*
                        (belongings.weapon.强化等级()+belongings.secondWep.强化等级());
                }
            }else{
                if(belongings.weapon!=null){
                    dr+=天赋点数(Talent.双武格挡,0.25f)*belongings.weapon.tier*belongings.weapon.强化等级();
                }
            }
        }
        if (belongings.armor() != null) {
            int armDr = belongings.armor().最大防御();
            if (力量() < belongings.armor().力量() && !heroClass(HeroClass.重武)) {
                armDr -= 2 * (belongings.armor().力量() - 力量());
            }
            if (armDr > 0) dr += armDr;
        }
        if (belongings.weapon() != null && !武力之戒.fightingUnarmed(this)) {
            int wepDr = Math.round(belongings.weapon().defenseFactor(this));
            if (力量() < ((Weapon) belongings.weapon()).力量() && !heroClass(HeroClass.重武)) {
                wepDr -= 2 * (((Weapon) belongings.weapon()).力量() - 力量());
            }
            if (wepDr > 0) dr += wepDr;
        }
        if (buff(HoldFast.class) != null) {
            dr += buff(HoldFast.class).armorBonus();
        }
        
        if (heroClass(HeroClass.血鬼)) {
            dr+=最大生命(0.05f);
        }
        dr+=天赋点数(Talent.坚韧,3);
        
        if(hasbuff(装甲之戒.装甲.class)){
            
            if(belongings.armor()!=null){
                dr+=belongings.armor().augment.defenseFactor(2*(1+装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class)/1.5f)
                        );
            }else{
                dr+=装甲之戒.tier()*2*(1+装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class)/1.5f);
            }
        }

        dr*=1+天赋点数(Talent.钢铁甲胄,0.5f);
        dr*=增加防御();
        return Math.round(dr);
    }
    public boolean 空手(){
        return 武力之戒.fightingUnarmed(this);
    }
    public boolean 裸衣(){
        return belongings.armor==null;
    }
    @Override
    public int 最小攻击() {
        Weapon wep = belongings.attackingWeapon();
        float dmg=0;
        
        if (武力之戒.fightingUnarmed(this)) {//空手
            dmg += 武力之戒.heromin();
//            if (武力之戒.unarmedGetsWeaponAugment(this)) {
//                dmg += belongings.attackingWeapon().augment.damageFactor(dmg);
//            }
            if(hasbuff(武力之戒.Force.class)){
                dmg += 武力之戒.min();
            }
        } else {
            if(belongings.thrownWeapon!=null){
                if(!Dungeon.炼狱(炼狱设置.无力投掷))
                dmg += wep.最小投掷攻击();
                
            }else if(wep!=null){
                if(belongings.secondWep==null){
                    dmg += wep.最小攻击();
                }else{
                    
                    dmg += wep.最小攻击()*(0.5+(职业精通()?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                    dmg += belongings.secondWep.最小攻击()*(0.5+(职业精通()?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                }
                if(wep.拳套){
                    dmg += 武力之戒.heromin();
                    if(hasbuff(武力之戒.Force.class)){
                        dmg += 武力之戒.min();
                    }
                }
            }
        }
        dmg+=最小防御()*(天赋点数(Talent.以攻为守,0.25f));
        dmg+=天赋点数(Talent.技巧,2);
        
        dmg*=破损短剑.增加();
        dmg*=增加攻击();
        if (dmg < 0) dmg = 0;
        return Math.round(dmg);
    }
    
    @Override
    public float 增加攻击(){
        float x=1;
        if(职业精通()&&subClass(HeroSubClass.狂战士)){
            x+=Buff.施加(this,怒气.class).怒气*0.0025f;
        }
        if(heroClass(HeroClass.行僧)){
            x+=(移速()-1)*0.15f;
        }
        if(subClass(HeroSubClass.灵月杀手)){
            x+=0.1f+(职业精通()?0.1f:0);
        }
        if(hasbuff(Invisibility.class))
            x+=天赋点数(Talent.蓄势待发,0.15f);
        x+=英精英雄成长;
        float delay = 之前攻击延迟;
        if (SPDSettings.固定攻速() == 1) {
            x/=delay;
        }
        return x;
    }
    
    @Override
    public int 最大攻击() {
        Weapon wep = belongings.attackingWeapon();
        float dmg=0;

        if (武力之戒.fightingUnarmed(this)) {//空手
            dmg+= 武力之戒.heromax();
//            if (武力之戒.unarmedGetsWeaponAugment(this)) {
//                dmg += belongings.attackingWeapon().augment.damageFactor(dmg);
//            }
            if(hasbuff(武力之戒.Force.class)){
                dmg+=武力之戒.max();
            }
        } else {
            if(belongings.thrownWeapon!=null){
                if(!Dungeon.炼狱(炼狱设置.无力投掷))
                dmg += wep.最大投掷攻击();
            }else if(wep!=null){
                if(belongings.secondWep==null){
                    dmg += wep.最大攻击();
                }else{
                    dmg += wep.最大攻击()*(0.5+(职业精通()?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                    dmg += belongings.secondWep.最大攻击()*(0.5+(职业精通()?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                }
                if(wep.拳套){
                    dmg+= 武力之戒.heromax();
                    if(hasbuff(武力之戒.Force.class)){
                        dmg += 武力之戒.max();
                    }
                }
                if(hasbuff(武力之戒.Force.class)){
                    dmg+= 武力之戒.max(武力之戒.getBuffedBonus(this,武力之戒.Force.class),1);
                }
            }
            
        }
        if(Dungeon.系统(系统设置.金币能力)){
            dmg+=Math.round(Dungeon.gold/50);
        }

        dmg+=最大防御()*(天赋点数(Talent.以攻为守,0.25f));
        征服 z=buff(征服.class);
        if(z!=null)dmg+=z.层数*天赋点数(Talent.战争热诚,3);
        
        dmg+=天赋点数(Talent.猛攻,5);
        dmg*=破损短剑.减少();
        dmg*=增加攻击();
        if (dmg < 0) dmg = 0;
        return Math.round(dmg);
    }

    //damage rolls that come from the hero can have their RNG influenced by clover
    public static int heroDamageIntRange(int min, int max) {
        if (Random.Float() < ThirteenLeafClover.alterHeroDamageChance()) {
            return ThirteenLeafClover.alterDamageRoll(min, max);
        } else {
            return Random.NormalIntRange(min, max);
        }
    }

    @Override
    public float 移速() {

        float speed = super.移速();
        
        if(hasbuff(Invisibility.class))
            speed*=1+天赋点数(Talent.偷袭姿态,0.2f);
        speed *= 综合属性();
        speed*=巨大蟹钳.减少();
        speed*=中国国旗.移速();
        speed*=1+天赋点数(Talent.速跑,0.1f);

        if(subClass(HeroSubClass.神兽之灵)&&职业精通()){
            boolean 移速=false;
            for(Mob m: getVisibleEnemies()){
                if(m.distance(this)>1){
                    移速=true;
                    break;
                }
            }
            if(移速)speed*=1.3f;
        }
                speed*=1+天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        if(裸衣()){
            speed*=1+(力量()-10)*属性增幅/2f;
        }else if(天赋(Talent.快速布阵)){
            speed*=1+(力量()-10)*属性增幅/2f*(天赋点数(Talent.快速布阵,0.5f));
        }
        if(天赋(Talent.追捕猎物)){
            boolean 残血=false;
            for(Mob m:getVisibleEnemies()){
                if(m.残血()){
                    残血=true;
                    break;
                }
            }
            if(残血)
            speed*=1+天赋点数(Talent.追捕猎物,0.3f);
        }
        if(Dungeon.hero.重生怪物.equals("下水道螃蟹")){
            speed*=2;
        }
        if (belongings.armor instanceof 披风) {
            speed *= 1.1f;
        }
        if (belongings.armor instanceof 忍服) {
            speed *= 1.05f;
        }
        if (heroClass(HeroClass.女忍)) {
            speed*=1+0.2f*根据已损失生命();
        }
        if (在水中()) {
            if (heroClass(HeroClass.盗贼)) {
                speed *= 1.1f;
            }
            if (heroClass(HeroClass.机器)) {
                speed *= 0.7f;
            }
        }

        if (belongings.armor() != null) {
            speed = belongings.armor().speedFactor(this, speed);
        }

        Momentum momentum = buff(Momentum.class);
        if (momentum != null) {
            ((HeroSprite) sprite).sprint(momentum.freerunning() ? 2 : 1f);
            speed *= momentum.speedMultiplier();
        } else {
            ((HeroSprite) sprite).sprint(1f);
        }

        NaturesPower.naturesPowerTracker natStrength = buff(NaturesPower.naturesPowerTracker.class);
        if (natStrength != null) {
            speed *= (2f + 0.25f * 天赋点数(Talent.GROWING_POWER));
        }
        if(speed>=2){
            Badges.解锁行僧();
        }

        
        if (SPDSettings.固定移速() == 5) {
            return speed;
        }
        if (SPDSettings.固定移速() == 4) {
            return speed >= 4 ? 4 : speed;
        }
        if (SPDSettings.固定移速() == 3) {
            return speed >= 3 ? 3 : speed;
        }
        if (SPDSettings.固定移速() == 2) {
            return speed >= 2 ? 2 : speed;
        }
        if (SPDSettings.固定移速() == 1) {
            return speed >= 1 ? 1 : speed;
        }
        //护符正在_使你减速_，并阻止了一切加速效果！
//        speed = AscensionChallenge.modifyHeroSpeed(speed);
        return speed;

    }

    @Override
    public boolean canSurpriseAttack() {
        KindOfWeapon w = belongings.attackingWeapon();
        if(subClass(HeroSubClass.武器大师)) return true;
        if (!(w instanceof Weapon)) return true;
        if (武力之戒.fightingUnarmed(this)) return true;
        if (力量() < ((Weapon) w).力量()) return false;

        return super.canSurpriseAttack();
    }

    public boolean canAttack(Char enemy) {
        if (enemy == null || pos == enemy.pos || !Actor.chars().contains(enemy)) {
            return false;
        }

        //can always attack adjacent enemies
        if (Dungeon.level.adjacent(pos, enemy.pos)) {
            return true;
        }

        KindOfWeapon wep = Dungeon.hero.belongings.attackingWeapon();
        if (enemy.hasbuff(TalismanOfForesight.CharAwareness.class)) {
            return true;
        }
        if (wep != null) {
            return wep.canReach(this, enemy.pos);
        } else if (buff(AscendedForm.AscendBuff.class) != null) {
            boolean[] passable = BArray.not(Dungeon.level.solid, null);
            for (Char ch : Actor.chars()) {
                if (ch != this) passable[ch.pos] = false;
            }

            PathFinder.buildDistanceMap(enemy.pos, passable, 3);

            return PathFinder.distance[pos] <= 3;
        } else {
            return false;
        }
    }
@Override
public float 攻击延迟() {
        if (buff(Talent.LethalMomentumTracker.class) != null) {
            buff(Talent.LethalMomentumTracker.class).detach();
            return 0;
        }

        float delay = 1/RingOfFuror.attackSpeedMultiplier(this);
        delay /= 综合属性();
    
    if(重生怪物.equals("矮人武僧"))
        delay /= 2;
        delay/=1+天赋点数(Talent.快攻,0.1f);
        
			if(空手()){
				delay/=2+(力量()-10)*属性增幅/2f;
			}
            if (!武力之戒.fightingUnarmed(this)) {
                delay *=belongings.attackingWeapon().delayFactor(this);
                
            } else{
                //Normally putting furor speed on unarmed attacks would be unnecessary
                //But there's going to be that one guy who gets a furor+force ring combo
                //This is for that one guy, you shall get your fists of fury!
                
                //and augments + brawler's stance! My goodness, so many options now compared to 2014!
                
                if(belongings.secondWep==null){
                    if(武力之戒.unarmedGetsWeaponAugment(this)){
                        delay=belongings.weapon.augment.delayFactor(delay);
                    }
                }else{
                    
                    if(武力之戒.unarmedGetsWeaponAugment(this)){
                        delay=belongings.weapon.augment.delayFactor(delay);
                    }
                    if(武力之戒.unarmedGetsWeaponAugment(this)){
                        delay=belongings.secondWep.augment.delayFactor(delay);
                    }
                }
            }
        
            if (天赋(Talent.连击强化)) {
                delay/=1+Buff.施加(this, 连击.class).count*天赋点数(Talent.连击强化,0.075f);
            }
            征服 z=buff(征服.class);
            if(z!=null)delay/=1+z.层数*天赋点数(Talent.致命节奏,0.04f);
            if(天赋(Talent.兽性猎手)){
                boolean 残血=false;
                for(Mob m:getVisibleEnemies()){
                    if(m.残血()){
                        残血=true;
                        break;
                    }
                }
                if(残血)
                delay/=1+天赋点数(Talent.兽性猎手,0.3f);
            }
    
    //ditto for furor + sword dance!
    //            if (buff(弯刀.SwordDance.class)!=null) {
    //                delay /= 1.6f;
    //            }
            if (SPDSettings.固定攻速() >= 5) {
                return delay;
            }
            if (SPDSettings.固定攻速() ==4) {
                return Math.max(delay,1/4f);
            }
            if (SPDSettings.固定攻速() ==3) {
                return Math.max(delay,1/3f);
            }
            if (SPDSettings.固定攻速() ==2) {
                return Math.max(delay,1/2f);
            }
            if (SPDSettings.固定攻速() == 1||subClass(HeroSubClass.灵月杀手)) {
                if(之前攻击延迟!=1)
                之前攻击延迟=delay;
                return delay <= 1 ? 1 : delay;
            }
            return delay;
    }

    @Override
    public void spend(float time) {
        super.spend(time);
    }

    @Override
    public void spendConstant(float time) {
        if(算法.isDebug()){
            time/=2f;
        }
        if(Dungeon.系统(系统设置.时间能力)){
            time*=0.2f;
        }
        if(belongings.armor() instanceof 魔披){
            time*=0.9f;
        }
        time *= RingOfHaste.speedMultiplier(this);

        super.spendConstant(time);
    }

    public void spendAndNextConstant(float time) {
        busy();
        spendConstant(time);
        next();
    }

    public void spendAndNext(float time) {
        busy();
        spend(time);
        next();
    }

    @Override
    public boolean 免疫(Class effect) {
        HashSet<Class> immunes = new HashSet<>(immunities);

        if (heroClass(HeroClass.机器)) {
            immunities.addAll(Property.机械.resistances());
            immunities.addAll(Property.ELECTRIC.resistances());
        }
        if (heroClass(HeroClass.灵猫)||heroClass(HeroClass.鼠弟)) {
            immunities.addAll(Property.动物.resistances());
        }
        if (heroClass(HeroClass.重武)) {
            immunes.add(Chill.class);
            immunes.add(Frost.class);
        }
        if (belongings.armor instanceof 铠甲) {
            immunes.add(Chill.class);
        }
        if (belongings.armor instanceof 法袍) {
            immunes.add(Fire.class);
        }
        if (belongings.armor instanceof 风衣) {
            immunes.add(ToxicGas.class);
        }
        if (belongings.armor instanceof 祭服) {
            immunes.add(Degrade.class);
        }
        if (heroClass(HeroClass.机器)) {
            immunes.add(流血.class);
        }
        if(belongings.armor() instanceof 训服){
            immunes.add(Cripple.class);
            immunes.add(Vulnerable.class);
        }
        if(belongings.weapon() instanceof 闪电双截棍){
            immunes.add(WandOfLightning.class);
            immunes.add(Shocking.class);
            immunes.add(Potential.class);
            immunes.add(Electricity.class);
            immunes.add(ShockingDart.class);
            immunes.add(Elemental.ShockElemental.class);
        }
        if(belongings.armor() instanceof 连裙){
            immunes.add(Charm.class);
        }


        for (Property p : properties()) {
            immunes.addAll(p.immunities());
        }
        for (Buff b : buffs()) {
            immunes.addAll(b.immunities());
        }
        if (glyphLevel(Brimstone.class) >= 0) {
            immunes.add(燃烧.class);
        }

        for (Class c : immunes) {
            if (c.isAssignableFrom(effect)) {
                return true;
            }
        }
        return false;
    }
    public void 更新数据(){
        
        if(heroClass(HeroClass.来世)){
            Dungeon.gold=Statistics.金币;
            Dungeon.energy=Statistics.能量;
        }

        更新属性();
        Dungeon.observe();
        Item.updateQuickslot();
        GameScene.updateFog();
    }
    public float 治疗效果(){
        float x=1;
        if(Dungeon.炼狱(炼狱设置.治疗禁令))x/=2;
        x+=天赋点数(Talent.死亡抗拒,0.15f)*根据已损失生命();
        return x;
    }
    @Override
    public boolean act() {
//        算法.调试(Holiday.getCurrentHoliday()+"");
        Dungeon.地牢时间++;

        if(visibleEnemies()>0){
            for(Mob m:getVisibleEnemies()){
                if(m.alignment==Alignment.ENEMY){
                    Buff.施加(m,被发现.class,1);
                }
            }
        }
        if(蛇皮走位<=8){
            ++蛇皮走位;
        }
        if(蛇皮走位==8){
            if(sprite!=null){
                sprite.说("状态很好！");
                sprite.歪嘴();
            }
        }
        if(++变脸>=3){
            变脸=0;
            sprite.hideEmo();
        }
        Dungeon.地牢天数=Dungeon.地牢时间/900+1;
        Dungeon.level.落石(this);
        
        if(骸骨左轮.减少()<0){
            生命流动+=骸骨左轮.减少();
        }

        if((int)Math.floor(生命流动)>=1){
            回血((int)Math.floor(生命流动));
            生命流动-=(int)Math.floor(生命流动);
        }else if(-(int)Math.ceil(生命流动)>=1){
            受伤((int)Math.floor(生命流动));
            生命流动-=(int)Math.floor(生命流动);
        }
        if(heroClass(HeroClass.血鬼)){
            if(天赋(Talent.适应身体)){
                生命流动+=0.01f*(天赋点数(Talent.适应身体,5));
            }else{
                生命流动-=0.01f;
            }
        }

        if(heroClass(HeroClass.机器)&&在水中()){
            生命流动-=0.1f;
        }
        
        if(Dungeon.赛季(赛季设置.地牢塔防)&&地牢塔防开关){
            地牢塔防生成速度++;
            Mob mob=new Rat();//第一波
            if(地牢塔防生成速度%(150)==0){
                Sample.INSTANCE.play( Assets.Sounds.BOSS );
                地牢塔防波次++;
                地牢塔防更多更快开关=Random.oneOf(true,false,false);
                if(地牢塔防波次<13){
                    if(地牢塔防更多更快开关){
                        GLog.w("第"+地牢塔防波次+"波，更多更快！");
                    }else{
                        GLog.w("第"+地牢塔防波次+"波");
                    }
                }
            }
            if(地牢塔防生成速度%(地牢塔防更多更快开关?2:8)==0){
                int 强度=(Dungeon.level.width()+1)/2;//15=>7 17=>8 19=>9 21=>10
                if(地牢塔防波次>=2){
//                    mob=new Gnoll();
                }
                if(地牢塔防波次>=3){
//                    mob=new Crab();
                }
                if(地牢塔防波次>=4){
//                    mob=new Slime();
                }
                if(地牢塔防波次>=13){
                    地牢塔防开关=false;
                    地牢塔防生成速度=0;
                    地牢塔防波次=0;
                    Dungeon.energy=10;
                    地牢塔防老鬼开关=true;
                    地牢塔防更多更快开关=false;
                    地牢塔防去下一层=true;
                    Sample.INSTANCE.play( Assets.Sounds.BOSS );
                    GLog.p("胜利！前往下一层！");
                }
                if(地牢塔防开关){
                    if(地牢塔防波次%3==0){
                        if(地牢塔防老鬼开关){
                            GLog.n("第"+地牢塔防波次+"波，老鬼出现！");
                            mob.viewDistance=Dungeon.level.width();
                            mob.大小=0.95f+地牢塔防波次*0.1f;
                            mob.生命=mob.最大生命=7*强度*地牢塔防波次;
                            mob.移速减半=true;
                            mob.state=mob.HUNTING;
                            mob.target(Dungeon.level.entrance());
                            GameScene.add(mob);
                            传送卷轴.瞬移(mob,Dungeon.level.exit());
                            地牢塔防老鬼开关=false;
                        }
                    }else{
                        地牢塔防老鬼开关=true;
                        if(地牢塔防更多更快开关){
                            mob.viewDistance=Dungeon.level.width();
                            mob.大小=0.85f;
                            mob.生命=mob.最大生命=强度/4*地牢塔防波次;
                            mob.移速翻倍=true;
                            mob.state=mob.HUNTING;
                            mob.target(Dungeon.level.entrance());
                            GameScene.add(mob);
                            传送卷轴.瞬移(mob,Dungeon.level.exit());
                        }else{
                            mob.viewDistance=Dungeon.level.width()+1;
                            mob.大小=0.95f+地牢塔防波次*0.05f;
                            mob.生命=mob.最大生命=强度*地牢塔防波次;
                            mob.state=mob.HUNTING;
                            mob.target(Dungeon.level.entrance());
                            GameScene.add(mob);
                            传送卷轴.瞬移(mob,Dungeon.level.exit());
                        }
                    }
                }
            }
        }
        float 恢复速度=1+天赋点数(Talent.硬肤,0.15f);
        if(Dungeon.赛季(赛季设置.鬼怨地牢)) 恢复速度*=1.4f;
        if(heroClass(HeroClass.WARRIOR))恢复速度*=1.4f;
        if(subClass(HeroSubClass.皇室卫兵))恢复速度*=2.5f+(职业精通()?2.5f:0);
        //+40%即2=>2.8，50=>35
        护甲恢复+=100/50f*恢复速度;
        if(护甲恢复>=100){
            护甲(1);
            护甲恢复=0;
        }
        
        if(hasbuff(ElixirOfFeatherFall.FeatherBuff.class))
        for (int n : PathFinder.NEIGHBOURS8){
            if(nobuff(Levitation.class)||(hasbuff(Levitation.class)&&buff(Levitation.class).cooldown()<=2.5f)){
                if(Dungeon.level.pit[pos+n]||
                   Dungeon.level.map[pos+n]==Terrain.CHASM||
                   Dungeon.level.map[pos+n]==Terrain.TRAP){
                    Buff.延长(this,Levitation.class,5);
                }
            }
        }
        if(heroClass(HeroClass.凌云)){
            flying=true;
        }
//        if (在草丛()&&天赋(Talent.自然丰收)) {
//            Buff.施加(this, Hunger.class).吃饭(天赋点数(Talent.自然丰收, 0.37f));
//        }
        if(SPDSettings.装备武器()){
            Heap heap = Dungeon.level.heaps.get(pos);
            if (heap != null && heap.type == Type.HEAP){
                Item item=heap.peek();
                if(belongings.weapon==null&&item instanceof Weapon w&&w.力量()<=力量()){
                    if(w.cursed){
                        if(heroClass(HeroClass.巫女)){
                            GameScene.pickUp(w,pos);
                            heap.pickUp();
                            
                            Sample.INSTANCE.play(Assets.Sounds.ITEM);
                            w.doEquip(this);
                            Dungeon.quickslot.alphaItem(w,false);
                            w.updateQuickslot();
                        }
                    }else{
                        GameScene.pickUp(w,pos);
                        heap.pickUp();
                        
                        Sample.INSTANCE.play(Assets.Sounds.ITEM);
                        w.doEquip(this);
                        Dungeon.quickslot.alphaItem(w,false);
                        w.updateQuickslot();
                    }
                }
            }
        }
        
		if(SPDSettings.自动拾取()){
			Heap heap = Dungeon.level.heaps.get(pos);
			if (heap != null && heap.type == Type.HEAP) {
//                Buff.施加(this, Hunger.class).吃饭(TIME_TO_SEARCH - HUNGER_FOR_SEARCH);
				Item item = heap.peek();
				boolean ok=false;
                if (belongings.contains(item)){
                    ok=true;
                }
				if (ok||item instanceof Plant.Seed ||
						item instanceof Key ||
						item instanceof Gold ||
						item instanceof EnergyCrystal ||
						item instanceof Dewdrop
				) {
                    
                    if (item.doPickUp(this)) {
                        heap.pickUp();
                        水袋 flask = belongings.getItem(水袋.class);
                        if (item instanceof Dewdrop&&flask != null && !flask.isFull()){
                            flask.collectDew((Dewdrop)item);
                        }
                    } else {
                        heap.sprite().drop();
                    }
				}
			}
		}
        //背包鉴定加快
//        for (Item item : belongings) {
//            float x=Talent.鉴定速度(this,item);
//            if (item instanceof Armor a) {
//                a.usesLeftToID -= x;
//                a.availableUsesToID -= x;
//            }
//            if (item instanceof Wand a) {
//                a.usesLeftToID -= x;
//                a.availableUsesToID -= x;
//            }
//            if (item instanceof Weapon a) {
//                a.usesLeftToID -= x;
//                a.availableUsesToID -= x;
//            }
//        }
        更新数据();
        
        //calls to dungeon.observe will also update hero's local FOV.
        fieldOfView = Dungeon.level.heroFOV;

        if (buff(Endure.EndureTracker.class) != null) {
            buff(Endure.EndureTracker.class).endEnduring();
        }

        if (!ready) {
            //do a full observe (including fog update) if not resting.
            if (!resting || buff(MindVision.class) != null || buff(Awareness.class) != null) {
                Dungeon.observe();
            } else {
                //otherwise just directly re-calculate FOV
                Dungeon.level.updateFieldOfView(this, fieldOfView);
            }
        }

        checkVisibleMobs();
        BuffIndicator.refreshHero();
        BuffIndicator.refreshBoss();

        if (paralysed > 0) {

            curAction = null;

            spendAndNext(TICK);
            return false;
        }

        boolean actResult;
        if (curAction == null) {

            if (resting) {
                if(Dungeon.赛季(赛季设置.地牢塔防)){
                    spendConstant(TIME_TO_REST*3);
                }else{
                    spendConstant(TIME_TO_REST*(SPDSettings.休息速度()));
                }
                next();
            } else {
                ready();
            }

            //if we just loaded into a level and have a search buff, make sure to process them
            if (Actor.now() == 0) {
                if (buff(Foresight.class) != null) {
                    search(false);
                } else if (buff(TalismanOfForesight.Foresight.class) != null) {
                    buff(TalismanOfForesight.Foresight.class).checkAwareness();
                }
            }

            actResult = false;

        } else {

            resting = false;

            ready = false;

            if (curAction instanceof HeroAction.Move) {
                actResult = actMove((HeroAction.Move) curAction);

            } else if (curAction instanceof HeroAction.Interact) {
                actResult = actInteract((HeroAction.Interact) curAction);

            } else if (curAction instanceof HeroAction.Buy) {
                actResult = actBuy((HeroAction.Buy) curAction);

            } else if (curAction instanceof HeroAction.PickUp) {
                actResult = actPickUp((HeroAction.PickUp) curAction);

            } else if (curAction instanceof HeroAction.OpenChest) {
                actResult = actOpenChest((HeroAction.OpenChest) curAction);

            } else if (curAction instanceof HeroAction.Unlock) {
                actResult = actUnlock((HeroAction.Unlock) curAction);

            } else if (curAction instanceof HeroAction.Mine) {
                actResult = actMine((HeroAction.Mine) curAction);

            } else if (curAction instanceof HeroAction.LvlTransition) {
                actResult = actTransition((HeroAction.LvlTransition) curAction);

            } else if (curAction instanceof HeroAction.Attack) {
                actResult = actAttack((HeroAction.Attack) curAction);

            } else if (curAction instanceof HeroAction.Alchemy) {
                actResult = actAlchemy((HeroAction.Alchemy) curAction);

            } else {
                actResult = false;
            }
        }


        return actResult;
    }
    @Override
    public int 最大护甲(){
        int 最大护甲=3+等级;
        if(进阶)最大护甲+=5;
        if(heroClass(HeroClass.逐姝))最大护甲+=等级/2;
        return 最大护甲;
    }
    public void busy() {
        ready = false;
    }

    private void ready() {
        if (sprite.looping()) sprite.idle();
        curAction = null;
        damageInterrupt = true;
        waitOrPickup = false;
        ready = true;
        canSelfTrample = true;

        AttackIndicator.updateState();

        GameScene.ready();
    }

    public void interrupt() {
        if (isAlive() && curAction != null &&
                ((curAction instanceof HeroAction.Move && curAction.dst != pos) ||
                        (curAction instanceof HeroAction.LvlTransition))) {
            lastAction = curAction;
        }
        curAction = null;
        GameScene.resetKeyHold();
        resting = false;
    }

    public void resume() {
        curAction = lastAction;
        lastAction = null;
        damageInterrupt = false;
        next();
    }

    private boolean canSelfTrample = false;

    public boolean canSelfTrample() {
        return canSelfTrample && !rooted && !flying &&
                //standing in high grass
                (Dungeon.level.map[pos] == Terrain.HIGH_GRASS ||
                        //standing in furrowed grass and not huntress
                        (heroClass != HeroClass.HUNTRESS && Dungeon.level.map[pos] == Terrain.FURROWED_GRASS) ||
                        //standing on a plant
                        Dungeon.level.plants.get(pos) != null);
    }

    private boolean actMove(HeroAction.Move action) {
//        if (Dungeon.level.map[action.dst] == Terrain.REGION_DECO||Dungeon.level.map[action.dst] == Terrain.REGION_DECO_ALT) {//木桶
//            Splash.at(action.dst, 0x555555, 5);
//            Sample.INSTANCE.play(Assets.Sounds.挖矿, 0.6f);
//            Level.set(action.dst, Terrain.EMPTY);
//            GameScene.updateMap(action.dst);
//        }
        if (getCloser(action.dst)) {
            canSelfTrample = false;
            return true;

            //Hero moves in place if there is grass to trample
        } else if (pos == action.dst && canSelfTrample()) {
            canSelfTrample = false;
            Dungeon.level.pressCell(pos);
            spendAndNext(1 / 移速());
            return false;
        } else {
            ready();
            return false;
        }
    }

    private boolean actInteract(HeroAction.Interact action) {

        Char ch = action.ch;

        if (ch.isAlive() && ch.canInteract(this)) {

            ready();
            sprite.turnTo(pos, ch.pos);
            return ch.interact(this);

        } else {

            if (fieldOfView[ch.pos] && getCloser(ch.pos)) {

                return true;

            } else {
                ready();
                return false;
            }

        }
    }

    private boolean actBuy(HeroAction.Buy action) {
        int dst = action.dst;
        if (pos == dst) {

            ready();

            Heap heap = Dungeon.level.heaps.get(dst);
            if (heap != null && heap.type == Type.FOR_SALE && heap.size() == 1) {
                Game.runOnRenderThread(new Callback() {
                    @Override
                    public void call() {
                        GameScene.show(new WndTradeItem(heap));
                    }
                });
            }

            return false;

        } else if (getCloser(dst)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actAlchemy(HeroAction.Alchemy action) {
        int dst = action.dst;
        if (Dungeon.level.distance(dst, pos) <= 1) {

            ready();

            AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
            if (kit != null && kit.isCursed()) {
                GLog.w(Messages.get(AlchemistsToolkit.class, "cursed"));
                return false;
            }

            AlchemyScene.clearToolkit();
            ShatteredPixelDungeon.switchScene(AlchemyScene.class);
            return false;

        } else if (getCloser(dst)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    //USED TO KEEP TRACK IF THE WAIT/PICKUP ACTION WAS USED
    // SO THAT THE HERO SPENDS A TURN EVEN IF THE FAIL TO PICK UP AN ITEM
    public boolean waitOrPickup = false;

    private boolean actPickUp(HeroAction.PickUp action) {
        int dst = action.dst;
        if (pos == dst) {

            Heap heap = Dungeon.level.heaps.get(pos);
            if (heap != null) {
                Item item = heap.peek();
                if (item.doPickUp(this)) {
                    heap.pickUp();

                    if (item instanceof Dewdrop
                            || item instanceof 时光沙漏.sandBag
                            || item instanceof DriedRose.Petal
                            || item instanceof Key
                            || item instanceof Guidebook) {
                        //Do Nothing
                    } else if (item instanceof DarkGold) {
                        DarkGold existing = belongings.getItem(DarkGold.class);
                        if (existing != null) {
							GLog.p(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                        }
                    } else {

                        //TODO make all unique items important? or just POS / SOU?
                        boolean important =item.特别&&item.已鉴定()&&
                                           (item instanceof Scroll || item instanceof Potion);
                        if (important) {
                            GLog.p(Messages.capitalize(Messages.get(this, "you_now_have", item.name())));
                        } else {
                            GLog.i(Messages.capitalize(Messages.get(this, "you_now_have", item.name())));
                        }
                    }

                    curAction = null;
                } else {

                    if (waitOrPickup) {
                        spendAndNextConstant(攻击延迟());
//                        spendAndNextConstant(TIME_TO_REST);
                    }

                    //allow the hero to move between levels even if they can't collect the item
                    if (Dungeon.level.getTransition(pos) != null) {
                        throwItems();
                    } else {
                        heap.sprite().drop();
                    }

                    if (item instanceof Dewdrop
                            || item instanceof 时光沙漏.sandBag
                            || item instanceof DriedRose.Petal
                            || item instanceof Key) {
                        //Do Nothing
                    } else {
                        GLog.newLine();
                        GLog.n(Messages.capitalize(Messages.get(this, "you_cant_have", item.name())));
                    }

                    ready();
                }
            } else {
                ready();
            }

            return false;

        } else if (getCloser(dst)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actOpenChest(HeroAction.OpenChest action) {
        int dst = action.dst;
        if (Dungeon.level.adjacent(pos, dst) || pos == dst) {
            path = null;

            Heap heap = Dungeon.level.heaps.get(dst);
            if (heap != null && (heap.type != Type.HEAP && heap.type != Type.FOR_SALE)) {

                if ((heap.type == Type.LOCKED_CHEST && Notes.keyCount(new GoldenKey(Dungeon.depth)) < 1)
                        || (heap.type == Type.CRYSTAL_CHEST && Notes.keyCount(new CrystalKey(Dungeon.depth)) < 1)) {

                    GLog.w(Messages.get(this, "locked_chest"));
                    ready();
                    return false;

                }

                switch (heap.type) {
                    case TOMB:
                        Sample.INSTANCE.play(Assets.Sounds.TOMB);
                        PixelScene.shake(1, 0.5f);
                        break;
                    case SKELETON:
                    case REMAINS:
                        break;
                    default:
                        Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
                }

                sprite.operate(dst);

            } else {
                ready();
            }

            return false;

        } else if (getCloser(dst)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actUnlock(HeroAction.Unlock action) {
        int doorCell = action.dst;
        if (Dungeon.level.adjacent(pos, doorCell)) {
            path = null;

            boolean hasKey = false;
            int door = Dungeon.level.map[doorCell];

            if (door == Terrain.LOCKED_DOOR
                    && Notes.keyCount(new IronKey(Dungeon.depth)) > 0) {

                hasKey = true;
            } else if (door == Terrain.HERO_LKD_DR){
                
                if (belongings.getItem(骷髅钥匙.class) != null
                    && !belongings.getItem(骷髅钥匙.class).cursed){
                    GLog.i(Messages.get(骷髅钥匙.class, "locked_with_key"));
                    ready();
                    return false;
                } else {
                    hasKey = true;
                }
            } else if (door == Terrain.CRYSTAL_DOOR
                    && Notes.keyCount(new CrystalKey(Dungeon.depth)) > 0) {

                hasKey = true;

            } else if (door == Terrain.LOCKED_EXIT
                    &&Notes.keyCount(new 磨损钥匙(Dungeon.depth))>0) {

                hasKey = true;

            }

            if (hasKey) {

                sprite.operate(doorCell);

                Sample.INSTANCE.play(Assets.Sounds.UNLOCK);

            } else {
                GLog.w(Messages.get(this, "locked_door"));
                ready();
            }

            return false;

        } else if (getCloser(doorCell)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actMine(HeroAction.Mine action) {
        if (Dungeon.level.adjacent(pos, action.dst)) {
            path = null;
            if ((Dungeon.level.map[action.dst] == Terrain.WALL
                    || Dungeon.level.map[action.dst] == Terrain.WALL_DECO
                    || Dungeon.level.map[action.dst] == Terrain.MINE_CRYSTAL
                    || Dungeon.level.map[action.dst] == Terrain.MINE_BOULDER)
                    && Dungeon.level.insideMap(action.dst)) {
                sprite.attack(action.dst, new Callback() {
                    @Override
                    public void call() {

                        boolean crystalAdjacent = false;
                        for (int i : PathFinder.NEIGHBOURS8) {
                            if (Dungeon.level.map[action.dst + i] == Terrain.MINE_CRYSTAL) {
                                crystalAdjacent = true;
                                break;
                            }
                        }

                        //1 hunger spent total
                        if (Dungeon.level.map[action.dst] == Terrain.WALL_DECO) {
                            DarkGold gold = new DarkGold();
                            if (gold.doPickUp(Dungeon.hero)) {
                                DarkGold existing = belongings.getItem(DarkGold.class);
                                if (existing != null) {
									GLog.p(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                                }
                                spend(-Actor.TICK); //picking up the gold doesn't spend a turn here
                            } else {
                                Dungeon.level.drop(gold, pos).sprite().drop();
                            }
                            PixelScene.shake(0.5f, 0.5f);
                            CellEmitter.center(action.dst).burst(Speck.factory(Speck.STAR), 7);
                            Sample.INSTANCE.play(Assets.Sounds.挖爆);
//                            Sample.INSTANCE.play(Assets.Sounds.EVOKE);
                            Level.set(action.dst, Terrain.EMPTY_DECO);

                            //mining gold doesn't break crystals
                            crystalAdjacent = false;

                            //4 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.WALL) {
//                            buff(Hunger.class).吃饭(-3);
                            PixelScene.shake(0.5f, 0.5f);
                            CellEmitter.get(action.dst).burst(Speck.factory(Speck.ROCK), 2);
                            Sample.INSTANCE.play(Assets.Sounds.挖矿);
                            Level.set(action.dst, Terrain.EMPTY_DECO);

                            //1 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.MINE_CRYSTAL) {
                            Splash.at(action.dst, 0xFFFFFF, 5);
                            Sample.INSTANCE.play(Assets.Sounds.SHATTER);
                            Level.set(action.dst, Terrain.EMPTY);

                            //1 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.MINE_BOULDER) {
                            Splash.at(action.dst, 0x555555, 5);
                            Sample.INSTANCE.play(Assets.Sounds.挖矿, 0.6f);
                            Level.set(action.dst, Terrain.EMPTY_DECO);
                        }

                        for (int i : PathFinder.NEIGHBOURS9) {
                            Dungeon.level.discoverable[action.dst + i] = true;
                        }
                        for (int i : PathFinder.NEIGHBOURS9) {
                            GameScene.updateMap(action.dst + i);
                        }

                        if (crystalAdjacent) {
                            sprite.parent.add(new Delayer(0.2f) {
                                @Override
                                protected void onComplete() {
                                    boolean broke = false;
                                    for (int i : PathFinder.NEIGHBOURS8) {
                                        if (Dungeon.level.map[action.dst + i] == Terrain.MINE_CRYSTAL) {
                                            Splash.at(action.dst + i, 0xFFFFFF, 5);
                                            Level.set(action.dst + i, Terrain.EMPTY);
                                            broke = true;
                                        }
                                    }
                                    if (broke) {
                                        Sample.INSTANCE.play(Assets.Sounds.SHATTER);
                                    }

                                    for (int i : PathFinder.NEIGHBOURS9) {
                                        GameScene.updateMap(action.dst + i);
                                    }
                                    spendAndNext(TICK);
                                    ready();
                                }
                            });
                        } else {
                            spendAndNext(TICK);
                            ready();
                        }

                        Dungeon.observe();
                    }
                });
            } else {
                ready();
            }
            return false;
        } else if (getCloser(action.dst)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actTransition(HeroAction.LvlTransition action) {
        int stairs = action.dst;
        LevelTransition transition = Dungeon.level.getTransition(stairs);
        if (rooted) {
            PixelScene.shake(1, 1f);
            ready();
            return false;

        } else if (!Dungeon.level.locked && transition != null && transition.inside(pos)) {

            if (Dungeon.level.activateTransition(this, transition)) {
                curAction = null;
            } else {
                ready();
            }

            return false;

        } else if (getCloser(stairs)) {

            return true;

        } else {
            ready();
            return false;
        }
    }

    private boolean actAttack(HeroAction.Attack action) {

        attackTarget = action.target;

        if (isCharmedBy(attackTarget)) {
            GLog.w(Messages.get(Charm.class, "cant_attack"));
            ready();
            return false;
        }

        if (attackTarget.isAlive() && canAttack(attackTarget) && attackTarget.invisible == 0) {

            //攻击动作
            //attack target cleared on onAttackComplete
            sprite.attack(attackTarget.pos);

            return false;

        } else {

            if (fieldOfView[attackTarget.pos] && getCloser(attackTarget.pos)) {

                attackTarget = null;
                return true;

            } else {
                ready();
                attackTarget = null;
                return false;
            }

        }
    }

    public Char attackTarget() {
        return attackTarget;
    }

    public void rest(boolean fullRest) {
        if(Dungeon.赛季(赛季设置.地牢塔防)){
            spendAndNextConstant(TIME_TO_REST*10);
        }else{
            spendAndNextConstant(TIME_TO_REST);
        }
        Talent.休息时(this,pos);
        if (!fullRest) {
            if (sprite != null) {
                sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "wait"));
            }
        }
        resting = fullRest;
    }
    @Override
    public int 暴击率(){
        
        int 暴击率=super.暴击率()-6;
        if (天赋(Talent.洪荒之怒)) {
            怒气 怒气= Buff.施加(this,怒气.class);
            暴击率+=Math.round(怒气.怒气*天赋点数(Talent.洪荒之怒,0.15f));
        }
        暴击率+=角斗链枷.暴击率()*根据已损失生命();
        暴击率+=天赋点数(Talent.破绽,6);
        if(heroClass(HeroClass.近卫)){
            暴击率+=6;
        }
        if(belongings.attackingWeapon() instanceof 无尽之刃){
            暴击率+=18;
        }
        if (在草丛()) {
            暴击率+=天赋点数(Talent.丛林法则,12);
        }
        暴击率+=幸运之戒.暴击率(this);
        
        return 暴击率;
    }
    @Override
    public float 暴击伤害(){
        float 暴击伤害=super.暴击伤害();

        if(belongings.attackingWeapon() instanceof 无尽之刃){
            暴击伤害+=0.3f;
        }
        
        return 暴击伤害;
    }
    @Override
    public int 暴击(final Char enemy,int dmg){
        if(enemy.第x次防御<=神圣之剑.增加())
            必暴=true;
        
        if((必暴||算法.概率学(暴击率()))){
            dmg=Math.round(dmg*暴击伤害());
            if (subClass == HeroSubClass.狂战士) {
                Buff.施加(this,怒气.class).damage();
            }
            if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.暴击)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.暴击);
            }
            if(sprite!=null){
                sprite.说("暴击！");
                sprite.歪嘴();
            }
            必暴=false;
            x次必暴=0;
        }else {
            x次必暴++;
            if(暴击率()!=0&&x次必暴>=600/暴击率()){
                必暴=true;
                sprite.说("手感火热！");
            }
        }
        return dmg;
    }
    public int 攻击范围(){
        int x =1;
        if(belongings.weapon!=null){
            x=belongings.weapon.reachFactor(this);
        }
        x +=命中之戒.getBuffedBonus(this,命中之戒.Accuracy.class);
        if(英精英雄==3)
            x++;
        
        if(英精英雄==1)
            x+=3;
        
        return Math.min(视野范围(),x);
    }
    public int 惊醒距离(){
        int x =4-天赋点数(Talent.静步);
        
        if(heroClass(HeroClass.镜魔))
            x--;
        return Math.max(2,x);
    }

    public static class 战斗状态 extends FlavourBuff{
        {name="战斗状态";}
        public int icon() { return BuffIndicator.WEAPON; }
    }
    public static class 被发现 extends FlavourBuff{
        {name="被发现回合";}
        public int icon() { return BuffIndicator.MIND_VISION; }
    }
    @Override
    public int 攻击时(final Char enemy, int damage) {
        damage = super.攻击时(enemy, damage);

        if(英精英雄==5)英精英雄成长+=0.01f;

        Buff.刷新(this,战斗状态.class,6);
        
        //		if(第x次攻击%2==0)
        //		if(第x次攻击%3==0)
        if(天赋(Talent.荣耀之证)){
            护甲(恢复百分比护甲(天赋点数(Talent.荣耀之证,0.075f)));
        }
        if (subClass == HeroSubClass.征服者) {
            Buff.施加(this, 征服.class).叠层();
        }
        if(Dungeon.系统(系统设置.攻击成长)){
            攻击成长+=Dungeon.depth/100f;
        }
        if(算法.isDebug()){
            damage+=enemy.最大生命/2;
        }
        
		//region +
        if(蛇皮走位==4){
            if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.走位)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.走位);
            }
            damage+=移速()*
                    (subClass(HeroSubClass.轻装步兵)&&职业精通()?15:1)
                    +heroDamageIntRange(最小防御(),最大防御())*天赋点数(Talent.步兵冲锋,0.5f);
        }
        蛇皮走位=0;
        if(Dungeon.level.distance(enemy.pos,pos)<=视野范围()/2){
            damage+=天赋点数(Talent.近视,3);
        }
        if(enemy.第x次防御==1)damage++;

        if(enemy.第x次防御==1){
            damage+=天赋点数(Talent.突袭,2);
        }
        if (heroClass(HeroClass.重武)) {
            damage+=最小防御();
        }
        
        if (buff(Kinetic.ConservedDamage.class) != null) {
            int conservedDamage = 0;
            conservedDamage = buff(Kinetic.ConservedDamage.class).damageBonus();
            buff(Kinetic.ConservedDamage.class).detach();
            //use a tracker so that we can know the true final damage
            Buff.施加(this, Kinetic.KineticTracker.class).conservedDamage = conservedDamage;
            damage += conservedDamage;
        }


		//endregion
		
		//region 附加效果
        if(重生怪物.equals("白化老鼠"))
            if (damage > 0 && Random.Int( 2 ) == 0) {
                Buff.施加( enemy, 流血.class ).set(damage);
            }
        if(subClass(HeroSubClass.盾之勇者)&&职业精通())
            if(enemy.第x次防御==1){
                Buff.延长(enemy,Vertigo.class,1);
            }

        if(subClass(HeroSubClass.实验狂鼠)&&算法.概率学(20+天赋点数(Talent.鼠爪刺击,20)))
            Buff.施加( enemy, 流血.class).set(damage*(0.25f));

        if(算法.概率学(天赋点数(Talent.外交鼠段,15)))
            鼠化(this,enemy.pos);

        if(火毒箭矢.增加()>0){
            
            int effect=Random.Int(4)+火毒箭矢.增加();
            
            if(effect>2){
                
                if(effect>=6&&enemy.buff(火毒.class)==null){
                    
                    if(Dungeon.level.flamable[enemy.pos]){
                        GameScene.add(Blob.seed(enemy.pos,4,Fire.class));
                    }
                    Buff.施加(enemy,火毒.class).reignite(enemy);
                    
                }else{
                    Buff.施加(enemy,Poison.class).set((effect-2));
                }
                
            }
        }
        if(subClass(HeroSubClass.金刚独狼))回已损失血(0.005f+天赋点数(Talent.嗜血如故,0.005f)+(职业精通()?0.005f:0));


        if ((enemy instanceof Rat||enemy instanceof Piranha)&&heroClass(HeroClass.灵猫)) {
            必中=true;
            必暴=true;
        }
        心之钢.心 钢 = buff(心之钢.心.class);
        if (钢 != null&&enemy.hasbuff(被发现.class)&&enemy.buff(被发现.class).visualcooldown()>1) {
            damage = 钢.proc(damage, this, enemy);
        }
        
        
        if (damage > 0 && subClass == HeroSubClass.狂战士) {
           Buff.施加(this,怒气.class).damage();
        }
        if (enemy.isAlive()&&天赋(Talent.星火符刃)&&enemy.第x次防御==1) {
            enemy.受伤(enemy.最大生命(天赋点数(Talent.星火符刃, 0.1f)));
        }
        
        PhysicalEmpower emp = buff(PhysicalEmpower.class);
        if (emp != null) {
            damage += emp.dmgBoost;
            emp.left--;
            if (emp.left <= 0) {
                emp.detach();
            }
            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG, 0.75f, 1.2f);
        }
        if(Dungeon.系统(系统设置.生命成长)){
            生命成长+=Dungeon.depth/100f;
            更新数据();
        }
        
        Weapon wep;
        if (武力之戒.fightingUnarmed(this)&&!武力之戒.unarmedGetsWeaponEnchantment(this)) {
            wep = null;
        } else {
            wep = belongings.attackingWeapon();
        }
        
        if (belongings.thrownWeapon != null) {
            if(!Dungeon.炼狱(炼狱设置.无力投掷))
            damage = belongings.thrownWeapon.投掷攻击时(this, enemy, Math.round(
                                                     damage*(heroClass(HeroClass.女忍)?1.15f:1)));
        }else if (wep != null) {
            damage = wep.攻击时(this, enemy, damage);
            if (belongings.secondWep != null) {
                damage = belongings.secondWep.攻击时(this, enemy, damage);
            }
        } else {
            boolean wasEnemy = enemy.alignment == Alignment.ENEMY;
            if (buff(BodyForm.BodyFormBuff.class) != null
                && buff(BodyForm.BodyFormBuff.class).enchant() != null) {
                damage = buff(BodyForm.BodyFormBuff.class).enchant().proc(new 短剑(),this,enemy,damage);
            }
            if (!wasEnemy || enemy.alignment == Alignment.ENEMY) {
                if (enemy.isAlive()&&buff(HolyWeapon.HolyWepBuff.class) != null) {
                    int dmg = subClass == HeroSubClass.PALADIN ? 8 : 2;
                    enemy.受伤时(Math.round(dmg * Weapon.Enchantment.genericProcChanceMultiplier(this)), HolyWeapon.INSTANCE);
                }
                if (enemy.isAlive()&&buff(Smite.SmiteTracker.class) != null) {
                    enemy.受伤时(Smite.bonusDmg(this, enemy), Smite.INSTANCE);
                }
            }
        }
        
        damage = Talent.攻击时(this, enemy, damage);
        
		if (subClass(HeroSubClass.狙击手)&&职业精通()&&!(wep instanceof 灵能短弓.SpiritArrow)) {
            Actor.add(new Actor() {

                {
                    actPriority = VFX_PRIO;
                }

                @Override
                protected boolean act() {
                    if (enemy.isAlive()) {
                            Buff.延长(Hero.this, SnipersMark.class, SnipersMark.DURATION).set(enemy.id(), 0);
                    }
                    Actor.remove(this);
                    return true;
                }
            });
        }
		//endregion
		
		//region x
        if(英精英雄==0||英精英雄==1){
            damage*=1.25f;
            if(enemy.nobuff(火毒.class))
                Buff.施加(enemy,火毒.class).reignite(enemy);
        }
        
        damage*=火毒箭矢.减少();
        
        if(enemy.海妖()){
            damage*=1+天赋点数(Talent.捕鱼达人,0.4f);
        }
        if(enemy.老鬼()){//老鬼因为机制问题，所以不好做增加生命
            damage=Math.round(damage*(1f/Dungeon.难度生命()));
        }
        if (heroClass(HeroClass.HUNTRESS)&&distance(enemy)<=感知范围()) {
            damage*=1.15f;
        }
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()&&heroClass(HeroClass.罗兰)){
                damage*=0.67f;
            }
        }
        damage*=造成伤害;
        
        if (enemy.恶魔亡灵()) {
            damage *=1+天赋点数(Talent.捉拿抓鬼,0.15f);
            if (heroClass(HeroClass.CLERIC)) {
                damage *=1.1f;
            }
        }
        if ((enemy instanceof Rat||enemy instanceof Piranha)&&heroClass(HeroClass.灵猫)) {
            damage *=2.5f;
            必中=false;
            必暴=false;
        }
        if(天赋(Talent.狙击弱点)){
            damage*=1+distance(enemy)*天赋点数(Talent.狙击弱点,0.05f);
        }
        if(subClass(HeroSubClass.狙击手)){
            damage*=(1+暴击率()*暴击伤害());
        }
		//endregion

        if(subClass(HeroSubClass.血法师))
            血魔法(this);

        if(visibleEnemies()==1&&subClass(HeroSubClass.神兽之灵)){
            damage+=enemy.最大生命(0.1f+天赋点数(Talent.狂暴爪击,0.05f));
        }
        if(visibleEnemies()>1&&subClass(HeroSubClass.神兽之灵)){
            int 最多攻击=0;
            for(Mob m:getVisibleEnemies()){
                if(m.alignment==Alignment.ENEMY&&m!=enemy){
                    最多攻击++;
                    if(最多攻击>2+天赋点数(Talent.极凌飓风,2))break;
                    attack(m,0.5f+天赋点数(Talent.极凌飓风,0.25f),0,0.5f+天赋点数(Talent.极凌飓风,0.25f));

 }
            }
        }

        return damage;
    }

    @Override
    public int 防御时(Char enemy, int damage) {

        if(天赋(Talent.皇室传承)){
            Dungeon.level.drop(new Gold().random(天赋点数(Talent.皇室传承,0.15f)),pos).sprite().drop();
        }
        if(heroClass(HeroClass.凌云)){
            enemy.生命流动-=damage;
        }
        if(英精英雄==5)英精英雄成长+=0.001f;
        Buff.刷新(this,战斗状态.class,6);
        if(Dungeon.系统(系统设置.防御成长)){
            防御成长+=Dungeon.depth/100f;
        }
        if(heroClass(HeroClass.机器))
            Sample.INSTANCE.play(Assets.Sounds.金属受伤);
        
        if(heroClass(HeroClass.灵猫)&&damage>=最大生命(0.25f))
            Sample.INSTANCE.play(Assets.Sounds.哈气猫);
        
        Statistics.物理防御++;
        Badges.解锁重武();
        
        if(重生怪物.equals("史莱姆"))
            damage=算法.固衰(damage,5);
        
        if(heroClass(HeroClass.近卫)){
            damage--;
        }
		//region 附带效果
        
        if(subClass(HeroSubClass.金刚独狼))回已损失血(0.005f+天赋点数(Talent.嗜血如故,0.005f)+(职业精通()?0.005f:0));
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()&&heroClass(HeroClass.罗兰)){
                damage*=0.67f;
            }
        }
		if (subClass == HeroSubClass.狂战士) {
            Buff.施加(this,怒气.class).damage();
        }
        
        荆棘斗篷.Thorns thorns = buff(荆棘斗篷.Thorns.class);
        if (thorns != null) {
            damage = thorns.proc(damage, enemy, this);
        }
        WandOfLivingEarth.RockArmor rockArmor = buff(WandOfLivingEarth.RockArmor.class);
        if (rockArmor != null) {
            damage = rockArmor.absorb(damage);
        }
        
        if (belongings.armor() != null) {
            damage = belongings.armor().防御时(enemy, this, damage);
        } else {
            if (buff(BodyForm.BodyFormBuff.class) != null
                && buff(BodyForm.BodyFormBuff.class).glyph() != null) {
                damage = buff(BodyForm.BodyFormBuff.class).glyph().proc(new ClothArmor(), enemy, this, damage);
            }
        }
        
        //endregion
		
		//region *
        if(Dungeon.level.distance(enemy.pos,pos)<=视野范围()/2){
            damage=Math.round(damage*(1-天赋点数(Talent.戒备,0.33f)));
        }
        
		if (enemy.恶魔亡灵() && heroClass(HeroClass.CLERIC)) {
            damage *=0.9f;
        }
        damage*=中国国旗.受伤();
        damage*=巨大蟹钳.受到();
        damage *= RingOfTenacity.damageMultiplier(this);
        damage*=受到伤害;
        if(英精英雄==2)damage/=2;
        if(英精英雄==3)damage*=0.2f;
        
        for (int n : PathFinder.NEIGHBOURS8){
            Char c=Actor.findChar(pos+n);
            int x=0;
            if(c!=null){
                x++;
                if(x>=2)
                damage*=传奇肛塞.受伤();
            }
        }
		//endregion
        
        
        if(heroClass(HeroClass.鼠弟)){
            Sample.INSTANCE.play(Assets.Sounds.RAT);
            for (int n : PathFinder.NEIGHBOURS8){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    
                    sprite.attack(enemy.pos, new Callback() {
                        @Override
                        public void call() {
                            AttackIndicator.target(enemy);
                            if (attack(enemy,0,heroDamageIntRange(武力之戒.heromin(),武力之戒.heromax()),1)) {
                                Sample.INSTANCE.play(Assets.Sounds.HIT);
                            }
                            Invisibility.notimedispel();
                            spendAndNext(0);
                        }
                    });
                }
            }
        }
        return super.防御时(enemy, damage);
    }

    @Override
    public int glyphLevel(Class<? extends Armor.Glyph> cls) {
        if (belongings.armor() != null && belongings.armor().hasGlyph(cls, this)) {
            return Math.max(super.glyphLevel(cls), belongings.armor.强化等级());
        } else if (buff(BodyForm.BodyFormBuff.class) != null
                && buff(BodyForm.BodyFormBuff.class).glyph() != null
                && buff(BodyForm.BodyFormBuff.class).glyph().getClass() == cls) {
            return belongings.armor() != null ? belongings.armor.强化等级() : 0;
        } else {
            return super.glyphLevel(cls);
        }
    }

    @Override
    public void 受伤时(int dmg, Object src) {
        
        if(算法.isDebug()){
            dmg=1;
        }
        if (buff(时光沙漏.timeStasis.class) != null
                || buff(TimeStasis.class) != null) {
            return;
        }

        //regular damage interrupt, triggers on any damage except specific mild DOT effects
        // unless the player recently hit 'continue moving', in which case this is ignored
        if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage) && damageInterrupt) {
            if(SPDSettings.打断英雄()&&dmg>0){
                interrupt();
            }
        }

        if (this.buff(Drowsy.class) != null) {
            Buff.detach(this, Drowsy.class);
            GLog.w(Messages.get(this, "pain_resist"));
        }
		//region 附带效果
		if (heroClass(HeroClass.巫女)&&算法.概率学(1/9f)) {
            经验((int)Math.sqrt(dmg), getClass());
        }
        if(Dungeon.系统(系统设置.波罗神盾)&&dmg<=3){
            Buff.施加(this,护盾.class).增加(1);
        }
        
		//endregion
        
        //temporarily assign to a float to avoid rounding a bunch
        float damage = dmg;
        
        Endure.EndureTracker endure = buff(Endure.EndureTracker.class);
        if (!(src instanceof Char)) {
            //reduce damage here if it isn't coming from a character (if it is we already reduced it)
            if (endure != null) {
                damage = endure.adjustDamageTaken(dmg);
            }
            //the same also applies to challenge scroll damage reduction
            if (buff(ScrollOfChallenge.ChallengeArena.class) != null) {
                damage *= 0.67f;
            }
            //and to monk meditate damage reduction
            if (buff(MonkEnergy.MonkAbility.Meditate.MeditateResistance.class) != null) {
                damage *= 0.2f;
            }
        }


        if (buff(Talent.WarriorFoodImmunity.class) != null) {
            if (heroClass(HeroClass.学士)) {
                damage = 0f;
            }
        }
//        if(Math.round(damage*0.3f)>0){
//            死舞 deferred = Buff.施加(this,死舞.class);
//            deferred.extend( Math.round(damage*0.3f));
//
//            sprite.showStatus( CharSprite.WARNING, Messages.get(死舞.class, "deferred", Math.round(damage*0.3f)) );
//            damage=Math.round(damage*0.7f);
//        }

        dmg = Math.round(damage);

        int preHP = 生命 + shielding();
        if (src instanceof Hunger) preHP -= shielding();
        super.受伤时(dmg, src);
        int postHP = 生命 + shielding();
        if (src instanceof Hunger) postHP -= shielding();
        int effectiveDamage = preHP - postHP;

        if (effectiveDamage <= 0) return;

        if (buff(Challenge.DuelParticipant.class) != null) {
            buff(Challenge.DuelParticipant.class).addDamage(effectiveDamage);
        }

        //flash red when hit for serious damage.
        float percentDMG = effectiveDamage / (float) preHP; //percent of current HP that was taken
        float percentHP = 1 - ((最大生命 - postHP) / (float) 最大生命); //percent health after damage was taken
        // The flash intensity increases primarily based on damage taken and secondarily on missing HP.
        float flashIntensity = 0.25f * (percentDMG * percentDMG) / percentHP;
        //if the intensity is very low don't flash at all
        if (flashIntensity >= 0.05f) {
            flashIntensity = Math.min(1 / 3f, flashIntensity); //cap intensity at 1/3
            GameScene.flash((int) (0xFF * flashIntensity) << 16);
            if (isAlive()) {
                if (flashIntensity >= 1 / 6f) {
                    Sample.INSTANCE.play(Assets.Sounds.HEALTH_CRITICAL, 1 / 3f + flashIntensity * 2f);
                } else {
                    Sample.INSTANCE.play(Assets.Sounds.HEALTH_WARN, 1 / 3f + flashIntensity * 4f);
                }
                //hero gets interrupted on taking serious damage, regardless of any other factor
                interrupt();
                damageInterrupt = true;
            }
        }
    }

    public void checkVisibleMobs() {
        ArrayList<Mob> visible = new ArrayList<>();

        boolean newMob = false;

        Mob target = null;
        for (Mob m : Dungeon.level.mobs.toArray(new Mob[0])) {
            if(m.hasbuff(Invisibility.class))continue;//隐形切换不到目标
            if (fieldOfView[m.pos] && m.landmark() != null) {
                Notes.add(m.landmark());
            }

            if (fieldOfView[m.pos] && m.alignment == Alignment.ENEMY) {
                visible.add(m);
                if (!visibleEnemies.contains(m)) {
                    newMob = true;
                }

                //only do a simple check for mind visioned enemies, better performance
                if ((!mindVisionEnemies.contains(m) && QuickSlotButton.autoAim(m) != -1)
                        || (mindVisionEnemies.contains(m) && new Ballistica(pos, m.pos, Ballistica.PROJECTILE).collisionPos == m.pos)) {
                    if (target == null) {
                        target = m;
                    } else if (distance(target) > distance(m)) {
                        target = m;
                    }
                    if (m instanceof Snake && Dungeon.level.distance(m.pos, pos) <= 4
                            && !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_EXAMINING)) {
                        GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_EXAMINING);
                        //we set to read here to prevent this message popping up a bunch
                        Document.ADVENTURERS_GUIDE.readPage(Document.GUIDE_EXAMINING);
                    }
                }
            }
        }

        Char lastTarget = QuickSlotButton.lastTarget;
        if (target != null && (lastTarget == null ||
                !lastTarget.isAlive() || !lastTarget.isActive() ||
                lastTarget.alignment == Alignment.ALLY ||
                !fieldOfView[lastTarget.pos])) {
            QuickSlotButton.target(target);
        }

        if (newMob) {
            if (resting) {
                Dungeon.observe();
            }
            if(SPDSettings.打断英雄()){//发现新敌人
                sprite.showAlert();//惊讶！
                if(!Dungeon.赛季(赛季设置.地牢塔防))
                interrupt();
            }
        }

        visibleEnemies = visible;

        //we also scan for blob landmarks here
        for (Blob b : Dungeon.level.blobs.values().toArray(new Blob[0])) {
            if (b.volume > 0 && b.landmark() != null && !Notes.contains(b.landmark())) {
                int cell;
                boolean found = false;
                //if a single cell within the blob is visible, we add the landmark
                for (int i = b.area.top; i < b.area.bottom; i++) {
                    for (int j = b.area.left; j < b.area.right; j++) {
                        cell = j + i * Dungeon.level.width();
                        if (fieldOfView[cell] && b.cur[cell] > 0) {
                            Notes.add(b.landmark());
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }

                //Clear blobs that only exist for landmarks.
                // Might want to make this a properly if it's used more
                if (found && b instanceof WeakFloorRoom.WellID) {
                    b.fullyClear();
                }
            }
        }
    }

    public int visibleEnemies() {
        return visibleEnemies.size();
    }

    public boolean 视野敌人() {
        return visibleEnemies.size() > 0;
    }

    public Mob visibleEnemy(int index) {
        return visibleEnemies.get(index % visibleEnemies.size());
    }

    public ArrayList<Mob> getVisibleEnemies() {
        return new ArrayList<>(visibleEnemies);
    }

    private boolean walkingToVisibleTrapInFog = false;

    private boolean getCloser(final int target) {
//        if(Actor.findChar(target)!=null&&Actor.findChar(target)!=this&&Actor.findChar(target).alignment!=Alignment.ALLY){
//            sprite.parent.add(new TargetedCell(target, 0xFF0000));
//        }else{
//            sprite.parent.add(new TargetedCell(target,0x44FF44));
//        }

        if (subClass == HeroSubClass.时间刺客&&hasbuff(时光沙漏.timeFreeze.class)&&天赋(Talent.穿越零界)) {
            if(Dungeon.level.solid[target]&&Dungeon.level.distance(pos,target)<=1&&实体墙(天赋点数(Talent.穿越零界))){
                sprite.move(pos,target);
                
                move(target);

                float delay = 1;

                if (buff(GreaterHaste.class) != null) {
                    delay = 0;
                }
                spend(delay/ 移速());
                
                search(false);
                interrupt();
                return true;
            }
        }
        if (target == pos){
            if(Dungeon.level.pit[target] && !Dungeon.level.solid[target]){
                if (flying || buff(Levitation.class) != null) {
                    if (!Chasm.jumpConfirmed) {
                        Chasm.heroJump(this);
                        interrupt();
                    }
                }
            }
            return false;
        }

        if (rooted) {
            PixelScene.shake(1, 1f);
            return false;
        }

        int step = -1;

        if (Dungeon.level.adjacent(pos, target)) {

            path = null;

            if (Actor.findChar(target) == null) {
                if (Dungeon.level.passable[target] || Dungeon.level.avoid[target]) {
                    step = target;
                }
                if (walkingToVisibleTrapInFog
                        && Dungeon.level.traps.get(target) != null
                        && Dungeon.level.traps.get(target).visible
                        && Dungeon.level.traps.get(target).active) {
                    return false;
                }
            }

        } else {

            boolean newPath = false;
            if (path == null || path.isEmpty() || !Dungeon.level.adjacent(pos, path.getFirst()))
                newPath = true;
            else if (path.getLast() != target)
                newPath = true;
            else {
                if (!Dungeon.level.passable[path.get(0)] || Actor.findChar(path.get(0)) != null) {
                    newPath = true;
                }
            }

            if (newPath) {

                int len = Dungeon.level.length();
                boolean[] p = Dungeon.level.passable;
                boolean[] v = Dungeon.level.visited;
                boolean[] m = Dungeon.level.mapped;
                boolean[] passable = new boolean[len];
                for (int i = 0; i < len; i++) {
                    passable[i] = p[i] && (v[i] || m[i]);
                }

                PathFinder.Path newpath = Dungeon.findPath(this, target, passable, fieldOfView, true);
                if (newpath != null && path != null && newpath.size() > 2 * path.size()) {
                    path = null;
                } else {
                    path = newpath;
                }
            }

            if (path == null) return false;
            step = path.removeFirst();

        }

        if (step != -1) {
            float delay = 1;

            if (buff(GreaterHaste.class) != null) {
                delay = 0;
            }

            if (Dungeon.level.pit[step] && !Dungeon.level.solid[step]
                    && (!flying || buff(Levitation.class) != null && buff(Levitation.class).detachesWithinDelay(delay/ 移速()))) {
                if (!Chasm.jumpConfirmed) {
                    Chasm.heroJump(this);
                    interrupt();
                } else {
                    flying = false;
                    remove(buff(Levitation.class)); //directly remove to prevent cell pressing
                    Chasm.heroFall(target);
                }
                canSelfTrample = false;
                return false;
            }

            if (buff(GreaterHaste.class) != null) {
                buff(GreaterHaste.class).spendMove();
            }

            if (subClass == HeroSubClass.疾行者) {
                Buff.施加(this, Momentum.class).gainStack();
            }
            if (subClass == HeroSubClass.时间刺客&&nobuff(时光沙漏.timeFreeze.class)) {
                Buff.施加(this, 时间能力.class).gainStack();
            }

            sprite.move(pos, step);

            move(step);

            spend(delay/ 移速());

            search(false);

            return true;

        } else {

            return false;

        }

    }

    public boolean handle(int cell) {
        
        if (cell == -1) {
            return false;
        }

        if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()) {
            fieldOfView = new boolean[Dungeon.level.length()];
            Dungeon.level.updateFieldOfView(this, fieldOfView);
        }

        if (!Dungeon.level.visited[cell] && !Dungeon.level.mapped[cell]
                && Dungeon.level.traps.get(cell) != null
                && Dungeon.level.traps.get(cell).visible
                && Dungeon.level.traps.get(cell).active) {
            walkingToVisibleTrapInFog = true;
        } else {
            walkingToVisibleTrapInFog = false;
        }

        Char ch = Actor.findChar(cell);
        Heap heap = Dungeon.level.heaps.get(cell);

        if (Dungeon.level.map[cell] == Terrain.ALCHEMY && cell != pos) {

            curAction = new HeroAction.Alchemy(cell);

        } else if (fieldOfView[cell] && ch instanceof Mob) {

            if (((Mob) ch).heroShouldInteract()) {
                curAction = new HeroAction.Interact(ch);
            } else {
                curAction = new HeroAction.Attack(ch);
            }

            //TODO perhaps only trigger this if hero is already adjacent? reducing mistaps
        } else if (Dungeon.level instanceof MiningLevel&&
                   belongings.getItem(镐子.class)!=null&&
                   (Dungeon.level.map[cell] == Terrain.WALL
                        || Dungeon.level.map[cell] == Terrain.WALL_DECO
                        || Dungeon.level.map[cell] == Terrain.MINE_CRYSTAL
                        || Dungeon.level.map[cell] == Terrain.MINE_BOULDER)) {

            curAction = new HeroAction.Mine(cell);

        } else if (heap != null
                //moving to an item doesn't auto-pickup when enemies are near...
                && (visibleEnemies.size() == 0 || cell == pos ||
                //...but only for standard heaps. Chests and similar open as normal.
                (heap.type != Type.HEAP && heap.type != Type.FOR_SALE))) {

            switch (heap.type) {
                case HEAP:
                    curAction = new HeroAction.PickUp(cell);
                    break;
                case FOR_SALE:
                    curAction = heap.size() == 1 && heap.peek().金币() > 0 ?
                            new HeroAction.Buy(cell) :
                            new HeroAction.PickUp(cell);
                    break;
                default:
                    curAction = new HeroAction.OpenChest(cell);
            }
            
        } else if (Dungeon.level.map[cell] == Terrain.LOCKED_DOOR
                   || Dungeon.level.map[cell] == Terrain.HERO_LKD_DR
                   || Dungeon.level.map[cell] == Terrain.CRYSTAL_DOOR
                   || Dungeon.level.map[cell] == Terrain.LOCKED_EXIT) {

            curAction = new HeroAction.Unlock(cell);

        } else if (Dungeon.level.getTransition(cell) != null
                //moving to a transition doesn't automatically trigger it when enemies are near
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (
//                        Dungeon.depth < 26 || //26+不能上楼
                    Dungeon.level.getTransition(cell).type == LevelTransition.Type.REGULAR_ENTRANCE)) {
            
            curAction = new HeroAction.LvlTransition(cell);

        }  else if (Dungeon.level.getTransition(cell) != null
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (
                    Dungeon.level.getTransition(cell).type == LevelTransition.Type.REGULAR_EXIT)) {
            //Evan没设计下楼if？！
            curAction = new HeroAction.LvlTransition(cell);

        } else if (Dungeon.level.getTransition(cell) != null
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (
                    Dungeon.level.getTransition(cell).type == LevelTransition.Type.SURFACE)) {
            curAction = new HeroAction.LvlTransition(cell);

        }else if (Dungeon.level.getTransition(cell) != null
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (
                    Dungeon.level.getTransition(cell).type == LevelTransition.Type.BRANCH_ENTRANCE)) {
            curAction = new HeroAction.LvlTransition(cell);

        }else if (Dungeon.level.getTransition(cell) != null
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (
                    Dungeon.level.getTransition(cell).type == LevelTransition.Type.BRANCH_EXIT)) {
            curAction = new HeroAction.LvlTransition(cell);

        } else {

            curAction = new HeroAction.Move(cell);
            lastAction = null;

        }

        return true;
    }

    public void 经验(int exp) {
        经验(exp, getClass());
    }
    
    @Override
    public void 回血(float x){
        
        if(belongings.attackingWeapon() instanceof 饮血之剑&&满血()){
            护甲((int)Math.floor(x));
//            if(hasbuff(Barrier.class)){
//                if(Buff.施加(this, Barrier.class).shielding+Math.round(x)<=最大生命(0.25f))
//                    Buff.施加(this, Barrier.class).增加(Math.round(x));
//            }else{
//                Buff.施加(this, Barrier.class).设置(Math.round(x));
//            }
        }
        super.回血(x);
    }
    
    public void 经验(int exp,Class source) {
        if(Dungeon.赛季(赛季设置.修罗血场)){
            exp=Dungeon.区域();
        }
        if(heroClass(HeroClass.DUELIST)){
            回血(exp);
        }
        if(heroClass(HeroClass.逐姝)){
            exp++;
        }
        exp=Math.round(exp*Dungeon.难度经验());
        if (sprite!=null&&exp > 0) {
           sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(exp),FloatingText.EXPERIENCE);
        }
        //xp granted by ascension challenge is only for on-exp gain effects
        if (source != AscensionChallenge.class) {
            this.当前经验 += exp;

            if (subClass(HeroSubClass.神秘学者)) {
                回血(exp);
            }
            
            if (heroClass(HeroClass.MAGE)) {
                Buff.延长(this, Recharging.class, exp);
                充能卷轴.charge(this);
                SpellSprite.show(this, SpellSprite.CHARGE);
            }
        }
        float percent = exp / (float) 升级所需();

        EtherealChains.chainsRecharge chains = buff(EtherealChains.chainsRecharge.class);
        if (chains != null) chains.gainExp(percent);

        HornOfPlenty.hornRecharge horn = buff(HornOfPlenty.hornRecharge.class);
        if (horn != null) horn.gainCharge(percent);

        AlchemistsToolkit.kitEnergy kit = buff(AlchemistsToolkit.kitEnergy.class);
        if (kit != null) kit.gainCharge(percent);

        MasterThievesArmband.Thievery armband = buff(MasterThievesArmband.Thievery.class);
        if (armband != null) armband.gainCharge(percent);


        if (source != 经验药剂.class) {
            for (Item i : belongings) {
                i.onHeroGainExp(percent, this);
            }
            if (buff(Talent.RejuvenatingStepsFurrow.class) != null) {
                buff(Talent.RejuvenatingStepsFurrow.class).set(percent * 200f);
                if (buff(Talent.RejuvenatingStepsFurrow.class).count <= 0) {
                    buff(Talent.RejuvenatingStepsFurrow.class).detach();
                }
            }
            if (buff(ElementalStrike.ElementalStrikeFurrowCount.class)!=null) {
                buff(ElementalStrike.ElementalStrikeFurrowCount.class).set(percent*20f);
                if (buff(ElementalStrike.ElementalStrikeFurrowCount.class).count<=0) {
                    buff(ElementalStrike.ElementalStrikeFurrowCount.class).detach();
                }
            }
            if (buff(HallowedGround.HallowedFurrowTracker.class) != null) {
                buff(HallowedGround.HallowedFurrowTracker.class).set(percent * 100f);
                if (buff(HallowedGround.HallowedFurrowTracker.class).count <= 0) {
                    buff(HallowedGround.HallowedFurrowTracker.class).detach();
                }
            }
        }

        while (this.当前经验 >= 升级所需()) {
            this.当前经验 -= 升级所需();

            if (buff(Talent.WandPreservationCount.class)!=null) {
                buff(Talent.WandPreservationCount.class).detach();
            }

            if (等级 < 最大等级) {
                升级();
            } else {
                Buff.延长(this, Bless.class, Bless.DURATION);
                this.当前经验 = 0;

                GLog.newLine();
                GLog.p(Messages.get(this, "level_cap"));
                Sample.INSTANCE.play(Assets.Sounds.LEVELUP);
            }

        }
    }

    public void 升级() {
        for (Item item : belongings) {
            if (item instanceof 灵月法杖 x) {
                x.updateLevel();
			}
        }
        if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.升级)){
            GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.升级);
        }
        等级++;

        if (buff(根骨秘药.HTBoost.class) != null) {
            buff(根骨秘药.HTBoost.class).onLevelUp();
        }
        
        更新数据();
        if(heroClass(HeroClass.学士)){
            回血(最大生命);
            更新数据();
        }
        if(职业精通())
        职业精通提示();
        if (sprite != null) {
            GLog.newLine();
            GLog.p(Messages.get(this, "new_level"));
            sprite.showStatus(CharSprite.增强, Messages.get(Hero.class, "level_up"));
            Sample.INSTANCE.play(Assets.Sounds.LEVELUP);
            if (等级 < Talent.天赋解锁[Talent.MAX_TALENT_TIERS + 1]) {
                GLog.newLine();
                GLog.p(Messages.get(this, "new_talent"));
                StatusPane.talentBlink = 10f;
                WndHero.lastIdx = 1;
            }
        }


        Badges.validateLevelReached();

    }

    public int 升级所需() {
        return 升级所需(等级);
    }

    public int 升级所需(float x) {
        return Math.round(升级所需(等级)*x);
    }

    public int 升级所需(int lvl) {
        int x = 10;
        int y = 5 + 1;
        lvl--;
//        if (heroClass(HeroClass.CLERIC)) {
//            return Math.round((x + lvl * y) * 0.9f);
//        }
        return x + lvl * y;
    }

    public boolean isStarving() {
        return Buff.施加(this, Hunger.class).isStarving();
    }

    @Override
    public boolean add(Buff buff) {
        if (buff.type == Buff.buffType.NEGATIVE &&
                (buff(时光沙漏.timeStasis.class) != null || buff(TimeStasis.class) != null)) {
            return false;
        }

        boolean added = super.add(buff);

        if (sprite != null && added) {
            String msg = buff.heroMessage();
            if (msg != null) {
                GLog.w(msg);
            }

            if (buff instanceof Paralysis || buff instanceof Vertigo) {
                interrupt();
            }

        }

        BuffIndicator.refreshHero();

        return added;
    }

    @Override
    public boolean remove(Buff buff) {
        if (super.remove(buff)) {
            BuffIndicator.refreshHero();
            return true;
        }
        return false;
    }

    @Override
    public void 死亡时(Object cause) {
        Badges.解锁近卫();
        
        
        生命流动=0;
        
        curAction = null;
        
        Ankh ankh = null;

        //look for ankhs in player inventory, prioritize ones which are blessed.
        for (Ankh i : belongings.getAllItems(Ankh.class)) {
            if (ankh == null || i.isBlessed()) {
                ankh = i;
            }
        }
        
        if (heroClass(HeroClass.罗兰)) {
            ankh=null;
        }
        if (ankh != null) {
            interrupt();
            Badges.解锁罗兰();
			if (true) {
                生命 = ankh.isBlessed() ? 最大生命 : 最大生命(0.25f);

                if(ankh.isBlessed()){
                    
                    治疗药剂.cure(this);
                    Buff.延长(this, Invulnerability.class,   Invulnerability.DURATION );
                    SpellSprite.show(this, SpellSprite.ANKH);
                    GameScene.flash(0x80FFFF40);
                }

                Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
                GLog.w(Messages.get(this, "revive"));
                Statistics.ankhsUsed++;
                Catalog.countUse(Ankh.class);

                ankh.detach(belongings.backpack);

                for (Char ch : Actor.chars()) {
                    if (ch instanceof DriedRose.GhostHero) {
                        ((DriedRose.GhostHero) ch).sayAnhk();
                        return;
                    }
                }
            } else {

                //this is hacky, basically we want to declare that a wndResurrect exists before
                //it actually gets created. This is important so that the game knows to not
                //delete the run or submit it to rankings, because a WndResurrect is about to exist
                //this is needed because the actual creation of the window is delayed here
                WndResurrect.instance = new Object();
                Ankh finalAnkh = ankh;
                Game.runOnRenderThread(()->GameScene.show(new WndResurrect(finalAnkh)));

                if (cause instanceof Hero.Doom) {
                    ((Hero.Doom) cause).onDeath();
                }

                SacrificialFire.Marked sacMark = buff(SacrificialFire.Marked.class);
                if (sacMark != null) {
                    sacMark.detach();
                }

            }
            return;
        }

        Actor.fixTime();
        super.死亡时(cause);
        reallyDie(cause);
    }

    public static void reallyDie(Object cause) {

        int length = Dungeon.level.length();
        int[] map = Dungeon.level.map;
        boolean[] visited = Dungeon.level.visited;
        boolean[] discoverable = Dungeon.level.discoverable;

        for (int i = 0; i < length; i++) {

            int terr = map[i];

            if (discoverable[i]) {

                visited[i] = true;
                if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
                    Dungeon.level.discover(i);
                }
            }
        }

        Bones.leave();

        Dungeon.observe();
        GameScene.updateFog();

        Dungeon.hero.belongings.identify();

        int pos = Dungeon.hero.pos;

        ArrayList<Integer> passable = new ArrayList<>();
        for (Integer ofs : PathFinder.NEIGHBOURS8) {
            int cell = pos + ofs;
            if ((Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]) && Dungeon.level.heaps.get(cell) == null) {
                passable.add(cell);
            }
        }
        Collections.shuffle(passable);

        ArrayList<Item> items = new ArrayList<>(Dungeon.hero.belongings.backpack.items);
        for (Integer cell : passable) {
            if (items.isEmpty()) {
                break;
            }

            Item item = Random.element(items);
            Dungeon.level.drop(item, cell).sprite().drop(pos);
            items.remove(item);
        }

        for (Char c : Actor.chars()) {
            if (c instanceof DriedRose.GhostHero) {
                ((DriedRose.GhostHero) c).sayHeroKilled();
            }
        }

        Game.runOnRenderThread(()->{
			GameScene.gameOver();
			Sample.INSTANCE.play(Assets.Sounds.DEATH);
		});

        if (cause instanceof Hero.Doom) {
            ((Hero.Doom) cause).onDeath();
        }

        Dungeon.deleteGame(GamesInProgress.curSlot, true);
    }

    //effectively cache this buff to prevent having to call buff(...) a bunch.
    //This is relevant because we call isAlive during drawing, which has both performance
    //and thread coordination implications if that method calls buff(...) frequently
 
    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    @Override
    public void move(int step, boolean travelling) {
        boolean wasHighGrass = Dungeon.level.map[step] == Terrain.HIGH_GRASS;

        super.move(step, travelling);

        if (!flying && travelling) {
            if (Dungeon.level.water[pos]) {
                Sample.INSTANCE.play(Assets.Sounds.WATER, 1, Random.Float(0.8f, 1.25f));
            } else if (Dungeon.level.map[pos] == Terrain.EMPTY_SP) {
                Sample.INSTANCE.play(Assets.Sounds.STURDY, 1, Random.Float(0.96f, 1.05f));
            } else if (Dungeon.level.map[pos] == Terrain.GRASS
                    || Dungeon.level.map[pos] == Terrain.EMBERS
                    || Dungeon.level.map[pos] == Terrain.FURROWED_GRASS) {
                if (step == pos && wasHighGrass) {
                    Sample.INSTANCE.play(Assets.Sounds.TRAMPLE, 1, Random.Float(0.96f, 1.05f));
                } else {
                    Sample.INSTANCE.play(Assets.Sounds.GRASS, 1, Random.Float(0.96f, 1.05f));
                }
            } else {
                Sample.INSTANCE.play(Assets.Sounds.STEP, 1, Random.Float(0.96f, 1.05f));
            }
        }
    }

    @Override
    public void onAttackComplete() {

        if (attackTarget == null) {
            curAction = null;
            super.onAttackComplete();
            return;
        }

        AttackIndicator.target(attackTarget);
        boolean wasEnemy = attackTarget.alignment == Alignment.ENEMY
                || (attackTarget instanceof Mimic && attackTarget.alignment == Alignment.NEUTRAL);

        boolean hit = attack(attackTarget);

        Invisibility.notimedispel();
        spend(攻击延迟());

        if (hit&& subClass == HeroSubClass.角斗士&&wasEnemy) {
            Buff.施加(this, 连击.class).hit(attackTarget);
        }

//        if (hit && heroClass(HeroClass.DUELIST) && wasEnemy) {
//            Buff.施加(this, 双剑.ComboStrikeTracker.class).addHit();
//        }

        curAction = null;
        attackTarget = null;

        super.onAttackComplete();
    }

    @Override
    public void onMotionComplete() {
        GameScene.checkKeyHold();
    }

    @Override
    public void onOperateComplete() {

        if (curAction instanceof HeroAction.Unlock) {

            int doorCell = ((HeroAction.Unlock) curAction).dst;
            int door = Dungeon.level.map[doorCell];
            骷髅钥匙.keyRecharge skele = buff(骷髅钥匙.keyRecharge.class);
            骷髅钥匙.KeyReplacementTracker keyUseTrack = buff(骷髅钥匙.KeyReplacementTracker.class);
            
            if (skele != null && skele.isCursed() && Random.Int(6) != 0){
                GLog.n(Messages.get(this, "key_distracted"));
                spendAndNext(2*Key.TIME_TO_UNLOCK);
//                Buff.施加(this, Hunger.class).吃饭(-4);
            } else if (Dungeon.level.distance(pos, doorCell) <= 1) {
                boolean hasKey = true;
                if (door == Terrain.LOCKED_DOOR) {
                    hasKey = Notes.remove(new IronKey(Dungeon.depth));
                    if (hasKey) {
                        if (keyUseTrack != null){
                            keyUseTrack.processIronLockOpened();
                        }
                        Level.set(doorCell, Terrain.DOOR);
                    }
                } else if (door == Terrain.HERO_LKD_DR) {
                    hasKey = true;
                    Level.set(doorCell, Terrain.DOOR);
                    GLog.i( Messages.get(骷髅钥匙.class, "force_lock"));
                } else if (door == Terrain.CRYSTAL_DOOR) {
                    hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
                    if (hasKey) {
                        if (keyUseTrack != null){
							keyUseTrack.processCrystalLockOpened();
                        }
                        Level.set(doorCell, Terrain.EMPTY);
                        Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
                        CellEmitter.get(doorCell).start(Speck.factory(Speck.DISCOVER), 0.025f, 20);
                        
                    }
                } else {
                    hasKey = Notes.remove(new 磨损钥匙(Dungeon.depth));
                    if (hasKey) {
                        Level.set(doorCell, Terrain.UNLOCKED_EXIT);
                    }
                }

                if (hasKey) {
                    GameScene.updateKeyDisplay();
                    GameScene.updateMap(doorCell);
                    spend(Key.TIME_TO_UNLOCK);
                }
            }

        } else if (curAction instanceof HeroAction.OpenChest) {

            Heap heap = Dungeon.level.heaps.get(((HeroAction.OpenChest) curAction).dst);
            
            骷髅钥匙.keyRecharge skele = buff(骷髅钥匙.keyRecharge.class);
            骷髅钥匙.KeyReplacementTracker keyUseTrack = buff(骷髅钥匙.KeyReplacementTracker.class);
            
            
            if (skele != null && skele.isCursed()
                && (heap.type == Type.LOCKED_CHEST || heap.type == Type.CRYSTAL_CHEST)
                && Random.Int(6) != 0){
                GLog.n(Messages.get(this, "key_distracted"));
                spend(2*Key.TIME_TO_UNLOCK);
//                Buff.施加(this, Hunger.class).吃饭(-4);
            } else if (Dungeon.level.distance(pos, heap.pos) <= 1){
                boolean hasKey = true;
                if (heap.type == Type.SKELETON || heap.type == Type.REMAINS) {
                    Sample.INSTANCE.play(Assets.Sounds.BONES);
                } else if (heap.type == Type.LOCKED_CHEST) {
                    hasKey = Notes.remove(new GoldenKey(Dungeon.depth));
					if (hasKey && keyUseTrack != null){
						keyUseTrack.processGoldLockOpened();
					}
                } else if (heap.type == Type.CRYSTAL_CHEST) {
                    hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
					if (hasKey && keyUseTrack != null){
						keyUseTrack.processCrystalLockOpened();
					}
                }

                if (hasKey) {
                    GameScene.updateKeyDisplay();
                    heap.open(this);
                    spend(Key.TIME_TO_UNLOCK);
                }
            }

        }
        curAction = null;

        if (!ready) {
            super.onOperateComplete();
        }
    }

    public int 搜索范围() {
        int x = 1;
        
        x += 天赋点数(Talent.寻觅);
        x+=遗失符石.减少();
        if (buff(Foresight.class) != null) {
            x = Foresight.DISTANCE;
        }
        return Math.max(1,x);
    }

    public int 感知范围() {
        int x =1;
        if(heroClass(HeroClass.戒老))x+=视野范围()/2;
        x += 天赋点数(Talent.寻觅);
        x += EyeOfNewt.mindVisionRange();
        if (buff(DivineSense.DivineSenseTracker.class) != null) {
            x = 天赋点数(Talent.神圣感知,5);
        }
        x*=1+天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        return x;
    }
    
    public int 光照范围() {
        int x=0;
        x += 天赋点数(Talent.夜视);
        if (hasbuff(Light.class)) {
            x += Light.DISTANCE;
        }
        if (hasbuff(燃烧.class)) {
            x += Light.DISTANCE;
        }
        if (heroClass(HeroClass.CLERIC)) {
            x += Light.DISTANCE/2;
        }
        return x;
    }
    @Override
    public int 视野范围() {
        float x = super.视野范围();
		
		x +=天赋点数(Talent.夜视);
        if(Dungeon.isChallenged(Challenges.DARKNESS)||heroClass(HeroClass.戒老)){
            x/=4;
        }
        x *= 1f + 天赋点数(Talent.鹰眼远视,0.25f);
        x *= EyeOfNewt.visionRangeMultiplier();
        
        x*=1+天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        
        if (hasbuff(MagicalSight.class)) {
            x= Math.max(MagicalSight.DISTANCE,x);
        }
        if(hasbuff(Blindness.class)){
            x=1;
        }
        if(hasbuff(Shadows.class)){
            x=1;
        }
        if(Dungeon.赛季(赛季设置.地牢塔防)){
            x=Dungeon.level.width()+1;
        }
        if(x<=1){
            Badges.解锁戒老();
        }
        return Math.round(Math.max(1,x+光照范围()));
    }

    public boolean search(boolean intentional) {

        if (!isAlive()) return false;

        boolean smthFound = false;

        boolean circular = false;//圆探索

        int distance = 搜索范围();
        boolean foresight = buff(Foresight.class) != null;
        if (foresight) {
            circular = true;
        }
        boolean foresightScan = buff(Foresight.class) != null && !Dungeon.level.mapped[pos];

        if (foresightScan) {
            Dungeon.level.mapped[pos] = true;
        }

        Point c = Dungeon.level.cellToPoint(pos);

        TalismanOfForesight.Foresight talisman = buff(TalismanOfForesight.Foresight.class);
        boolean cursed = talisman != null && talisman.isCursed();

        int[] rounding = ShadowCaster.rounding[distance];

        int left, right;
        int curr;
        for (int y = Math.max(0, c.y - distance); y <= Math.min(Dungeon.level.height() - 1, c.y + distance); y++) {
            if (!circular) {
                left = c.x - distance;
            } else if (rounding[Math.abs(c.y - y)] < Math.abs(c.y - y)) {
                left = c.x - rounding[Math.abs(c.y - y)];
            } else {
                left = distance;
                while (rounding[left] < rounding[Math.abs(c.y - y)]) {
                    left--;
                }
                left = c.x - left;
            }
            right = Math.min(Dungeon.level.width() - 1, c.x + c.x - left);
            left = Math.max(0, left);
            for (curr = left + y * Dungeon.level.width(); curr <= right + y * Dungeon.level.width(); curr++) {
                if (intentional) {
                    if(Dungeon.level.map[curr]!=Terrain.SECRET_TRAP&&Dungeon.level.map[curr]!=Terrain.TRAP){
                        Heap heap = Dungeon.level.heaps.get(curr);//搜索拾取
                        if (heap != null && (heap.type == Heap.Type.HEAP || heap.type == Heap.Type.CHEST)) {
                            Item item = heap.peek();
                            
                            if (item.doPickUp(this)) {
                                heap.pickUp();
                            } else {
                                heap.sprite().drop();
                            }
                        }
                    }
                    
                    if(true){//踩踏草
                        Dungeon.level.pressCellgrass(curr);
                    }
                    if(Actor.hasfindChar(curr)&&Actor.findChar(curr)!=this){
                        if(Actor.findChar(curr) instanceof Mimic m){
                            m.interact(this);
                        }
                        if(subClass(HeroSubClass.神偷无影)){
                            attack(Actor.findChar(curr),0.2f+天赋点数(Talent.边搜边打,0.2f));
                        }
                    }
                }
                if ((foresight || fieldOfView[curr]) && curr != pos) {
                    if ((foresight && (!Dungeon.level.mapped[curr] || foresightScan))) {
                        GameScene.effectOverFog(new CheckedCell(curr, foresightScan ? pos : curr));
                    } else if (intentional) {
                        GameScene.effectOverFog(new CheckedCell(curr, pos));
                    }

                    if (foresight) {
                        Dungeon.level.mapped[curr] = true;
                    }

                    if (Dungeon.level.secret[curr]) {

                        Trap trap = Dungeon.level.traps.get(curr);
                        float chance;

                        //searches aided by foresight always succeed, even if trap isn't searchable
                        if (foresight) {
                            chance = 1f;

                            //otherwise if the trap isn't searchable, searching always fails
                        } else if (trap != null && !trap.canBeSearched) {
                            chance = 0f;

                            //intentional searches always succeed against regular traps and doors
                        } else if (intentional) {
                            chance = 1f;

                            //unintentional searches always fail with a cursed talisman
                        } else if (cursed) {
                            chance = 0f;

                            //unintentional trap detection scales from 40% at floor 0 to 30% at floor 25
                        } else if (Dungeon.level.map[curr] == Terrain.SECRET_TRAP) {
                            chance = 0.4f - (Dungeon.depth / 250f);

                            //unintentional door detection scales from 20% at floor 0 to 0% at floor 20
                        } else {
                            chance = 0.2f - (Dungeon.depth / 100f);
                        }

                        //don't want to let the player search though hidden doors in tutorial
                        if (SPDSettings.intro()) {
                            chance = 0;
                        }

                        if (Random.Float() < chance) {

                            int oldValue = Dungeon.level.map[curr];

                            GameScene.discoverTile(curr, oldValue);

                            Dungeon.level.discover(curr);

                            探地卷轴.discover(curr);

                            if (fieldOfView[curr]) smthFound = true;

                            if (talisman != null) {
                                if (oldValue == Terrain.SECRET_TRAP) {
                                    talisman.charge(2);
                                } else if (oldValue == Terrain.SECRET_DOOR) {
                                    talisman.charge(10);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (intentional) {
            float x=1;
            sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "search"));
            sprite.operate(pos);
            if (!Dungeon.level.locked) {
                if (cursed) {
                    x*=2;
                    GLog.n(Messages.get(this, "search_distracted"));
                }
            }
            if(heroClass(HeroClass.盗贼)){
                x/=2;
            }
            if(subClass(HeroSubClass.神偷无影))
            x*=1-0.2f-天赋点数(Talent.边搜边打,0.1f);
            int 增加=0;
            if(搜索范围()<=1){
                增加+=遗失符石.减少()*3;
            }
            spendAndNext(搜索范围()*2*x+增加);
            if(遗失符石.增加()>0){
                int DIST=遗失符石.增加();
                boolean[] FOV = new boolean[Dungeon.level.length()];
                Point cc= Dungeon.level.cellToPoint(pos);
                ShadowCaster.castShadow(cc.x,cc.y,Dungeon.level.width(),FOV,Dungeon.level.losBlocking,DIST);
                
                int sX = Math.max(0,cc.x-DIST);
                int eX = Math.min(Dungeon.level.width()-1,cc.x+DIST);
                
                int sY = Math.max(0,cc.y-DIST);
                int eY = Math.min(Dungeon.level.height()-1,cc.y+DIST);
                
                ArrayList<Trap> disarmCandidates = new ArrayList<>();
                
                for (int y = sY; y <= eY; y++){
                    int curr2=y*Dungeon.level.width()+sX;
                    for (int xx=sX;xx<=eX;xx++){
                        
                        if (FOV[curr2]){
                            
                            Trap t = Dungeon.level.traps.get(curr2);
                            if (t != null && t.active){
                                disarmCandidates.add(t);
                            }
                            
                        }
                        curr2++;
                    }
                }
                
                Collections.shuffle(disarmCandidates);
                Collections.sort(disarmCandidates, new Comparator<Trap>() {
                    @Override
                    public int compare(Trap o1, Trap o2) {
                        float diff = Dungeon.level.trueDistance(pos, o1.pos) - Dungeon.level.trueDistance(pos, o2.pos);
                        if (diff < 0){
                            return -1;
                        } else if (diff == 0){
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
                
                //disarms at most nine traps
                while (disarmCandidates.size() > 9){
                    disarmCandidates.remove(9);
                }
                
                for ( Trap t : disarmCandidates){
                    t.reveal();
                    t.disarm();
                    CellEmitter.get(t.pos).burst(Speck.factory(Speck.STEAM),6);
                }
                
                Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
            }
        }

        if (smthFound) {
            sprite.showAlert();//惊讶！
            GLog.w(Messages.get(this, "noticed_smth"));
            Sample.INSTANCE.play(Assets.Sounds.SECRET);
            interrupt();
            if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.搜索)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.搜索);
            }
        }

        if (foresight) {
            GameScene.updateFog(pos, Foresight.DISTANCE + 1);
        }

        if (talisman != null) {
            talisman.checkAwareness();
        }

        return smthFound;
    }

    public void resurrect() {
        生命 = 最大生命;
        护甲=最大护甲();
        live();

        MagicalHolster holster = belongings.getItem(MagicalHolster.class);

        Buff.施加(this, LostInventory.class);
        Buff.施加(this, Invisibility.class, 3f);
        //lost inventory is dropped in interlevelscene

        //activate items that persist after lost inventory
        //FIXME this is very messy, maybe it would be better to just have one buff that
        // handled all items that recharge over time?
        for (Item i : belongings) {
            if(i instanceof 召唤物品){
                ((召唤物品) i).activate(this);
            }
            if (i instanceof EquipableItem && i.isEquipped(this)) {
                ((EquipableItem) i).activate(this);
            } else if (i instanceof CloakOfShadows&&i.keptThroughLostInventory()&&天赋(Talent.轻便斗篷)) {
                ((CloakOfShadows) i).activate(this);
            } else if (i instanceof 神圣法典&&i.keptThroughLostInventory()&&天赋(Talent.轻量阅读)) {
                ((神圣法典) i).activate(this);
            } else if (i instanceof 本命玉佩&&i.keptThroughLostInventory()&&天赋(Talent.轻便玉佩)) {
                ((本命玉佩) i).activate(this);
            } else if (i instanceof 叛忍护额&&i.keptThroughLostInventory()&&天赋(Talent.轻便护额)) {
                ((叛忍护额) i).activate(this);
            } else if (i instanceof Wand && i.keptThroughLostInventory()) {
                if (holster != null && holster.contains(i)) {
                    ((Wand) i).charge(this, MagicalHolster.HOLSTER_SCALE_FACTOR);
                } else {
                    ((Wand) i).charge(this);
                }
            } else if (i instanceof 法师魔杖 && i.keptThroughLostInventory()) {
                ((法师魔杖) i).applyWandChargeBuff(this);
            }
        }
    }

    @Override
    public void next() {
        if (isAlive())
            super.next();
    }

    public static interface Doom {
        public void onDeath();
    }

    public boolean heroClass(HeroClass hc) {
        if(heroClass蜕变!=HeroClass.NONE){
            return heroClass蜕变 == hc||heroClass == hc;
        }
        return heroClass == hc;
    }

    public boolean subClass(HeroSubClass sc) {
        return subClass == sc;
    }

    public int 等级(float x) {
        return Math.round(x * 等级);
    }

    public int 力量(float x) {
        return Math.round(x * 力量());
    }
    @Override
    public float stealth() {
        float stealth = super.stealth();
        stealth+=虚无透纱.增加();
        return stealth;
    }
    public float 综合属性() {
        float x = 1 + 天赋点数(Talent.任督二脉, 0.03f);
        if(belongings.hasItem(三叉戟.class)&&在水中()){
            x+=0.1f;
        }
        if(heroClass(HeroClass.来世)){
            x+=0.1f;
        }
        虫箭.力量 虫箭= buff(虫箭.力量.class);
        if (虫箭 != null) {
            x+=虫箭.itemLevel()*0.03f;
        }
        if (subClass(HeroSubClass.潜能觉醒)) {
            x += 0.03f;
            if(职业精通())x+=0.03f;
        }
        x+=天赋点数(Talent.黑猫主导,0.05f);
        时间能力 s=buff(时间能力.class);
        if(s!=null)x*=s.综合属性();
        x*=六神之戒.六神之力(this);
        return x;
    }

    @Override
    public float 全能吸血() {
        
        float 全能吸血 = 0;

        征服 z=buff(征服.class);
        if(z!=null&&z.满层())全能吸血+=0.025f+天赋点数(Talent.征服之姿,0.025f)+(职业精通()?0.025f:0);
        if (heroClass(HeroClass.血鬼)) {
            全能吸血 += 0.06f;
        }
        return 全能吸血;
    }
    @Override
    public float 吸血() {

        float 吸血 = 0;

        if(subClass(HeroSubClass.神兽之灵)){
            吸血+=0.025f+天赋点数(Talent.坚铁甲胄,0.025f);
        }
        if(重生怪物.equals("吸血蝙蝠"))吸血+=0.5f;
        if (subClass(HeroSubClass.黑魔导师)) {
            吸血 += 0.04f;
        }
        if(Dungeon.赛季(赛季设置.修罗血场)){
            吸血 += 0.075f;
        }
        吸血 += 天赋点数(Talent.高级吸血, 0.04f);
        
        if(belongings.weapon()!=null){
            吸血 +=belongings.weapon().吸血;
        }
        return 吸血+super.吸血();
    }
    public void 血魔法(Hero hero) {
        Ballistica aim;
        //The direction of the aim only matters if it goes outside the map
        //So we try to aim in the cardinal direction that has the most space
        int x = hero.pos % Dungeon.level.width();
        int y = hero.pos / Dungeon.level.width();

        if (Math.max(x, Dungeon.level.width()-x) >= Math.max(y, Dungeon.level.height()-y)){
            if (x > Dungeon.level.width()/2){
                aim = new Ballistica(hero.pos, hero.pos - 1, Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + 1, Ballistica.WONT_STOP);
            }
        } else {
            if (y > Dungeon.level.height()/2){
                aim = new Ballistica(hero.pos, hero.pos - Dungeon.level.width(), Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + Dungeon.level.width(), Ballistica.WONT_STOP);
            }
        }

        int aoeSize = 1+天赋点数(Talent.血之潮汐);
        ConeAOE aoe = new ConeAOE(aim, aoeSize, 360, Ballistica.STOP_TARGET);

        for (Ballistica ray : aoe.outerRays){
            ((MagicMissile)hero.sprite.parent.recycle( MagicMissile.class )).reset(
                    MagicMissile.BLOOD_CONE,
                    hero.sprite,
                    ray.path.get(ray.dist),
                    null
                                                                                  );
        }

        ((MagicMissile)hero.sprite.parent.recycle( MagicMissile.class )).reset(
                MagicMissile.BLOOD_CONE,
                hero.sprite,
                aim.path.get(Math.min(aoeSize / 2, aim.path.size()-1)),
                new Callback() {
                    @Override
                    public void call() {
                        int charsHit=0;
                        for (int cell : aoe.cells) {
                            Char mob = Actor.findChar(cell);
                            int damage = Math.round(最大生命(0.05f+天赋点数(Talent.血色契约,0.03f)));
                            if (mob != null && damage > 0 && mob.alignment != Char.Alignment.ALLY){
                                charsHit++;
                                mob.受伤(damage);
                            }
                        }

                        if (charsHit > 0 && hero.天赋(Talent.鲜血转换)){
                            回百分比血(天赋点数(Talent.鲜血转换,0.01f)*charsHit);
                        }
                    }
                }
        );
        Invisibility.notimedispel();
    }

    public void 元素爆炸(Hero hero, Integer target) {
        Ballistica aim;
        //The direction of the aim only matters if it goes outside the map
        //So we try to aim in the cardinal direction that has the most space
        int x = hero.pos % Dungeon.level.width();
        int y = hero.pos / Dungeon.level.width();

        if (Math.max(x, Dungeon.level.width()-x) >= Math.max(y, Dungeon.level.height()-y)){
            if (x > Dungeon.level.width()/2){
                aim = new Ballistica(hero.pos, hero.pos - 1, Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + 1, Ballistica.WONT_STOP);
            }
        } else {
            if (y > Dungeon.level.height()/2){
                aim = new Ballistica(hero.pos, hero.pos - Dungeon.level.width(), Ballistica.WONT_STOP);
            } else {
                aim = new Ballistica(hero.pos, hero.pos + Dungeon.level.width(), Ballistica.WONT_STOP);
            }
        }

        Class<? extends Wand> wandCls = null;
        if (hero.belongings.getItem(法师魔杖.class) != null) {
            wandCls = hero.belongings.getItem(法师魔杖.class).wandClass();
        }

        if (wandCls == null){
            GLog.w(Messages.get(this, "no_staff"));
            return;
        }

        int aoeSize = 1;

        int projectileProps = Ballistica.STOP_SOLID | Ballistica.STOP_TARGET;

        //### Special Projectile Properties ###
        //*** Wand of Disintegration ***
        if (wandCls == WandOfDisintegration.class){
            projectileProps = Ballistica.STOP_TARGET;

            //*** Wand of Fireblast ***
        } else if (wandCls == 焰浪法杖.class){
            projectileProps = projectileProps | Ballistica.IGNORE_SOFT_SOLID;

            //*** Wand of Warding ***
        } else if (wandCls == WandOfWarding.class){
            projectileProps = Ballistica.STOP_TARGET;

        }

       HashMap<Class<?extends Wand>, Integer>
                effectTypes = new HashMap<>();
        {
            effectTypes.put(WandOfMagicMissile.class,   MagicMissile.MAGIC_MISS_CONE);
            effectTypes.put(WandOfLightning.class,      MagicMissile.SPARK_CONE);
            effectTypes.put(WandOfDisintegration.class, MagicMissile.PURPLE_CONE);
            effectTypes.put(焰浪法杖.class,      MagicMissile.FIRE_CONE);
            effectTypes.put(WandOfCorrosion.class,      MagicMissile.CORROSION_CONE);
            effectTypes.put(WandOfBlastWave.class,      MagicMissile.FORCE_CONE);
            effectTypes.put(WandOfLivingEarth.class,    MagicMissile.EARTH_CONE);
            effectTypes.put(WandOfFrost.class,          MagicMissile.FROST_CONE);
            effectTypes.put(WandOfPrismaticLight.class, MagicMissile.RAINBOW_CONE);
            effectTypes.put(WandOfWarding.class,        MagicMissile.WARD_CONE);
            effectTypes.put(WandOfTransfusion.class,    MagicMissile.BLOOD_CONE);
            effectTypes.put(WandOfCorruption.class,     MagicMissile.SHADOW_CONE);
            effectTypes.put(WandOfRegrowth.class,       MagicMissile.FOLIAGE_CONE);
        }
        ConeAOE aoe = new ConeAOE(aim, aoeSize, 360, projectileProps);

        for (Ballistica ray : aoe.outerRays){
            ((MagicMissile)hero.sprite.parent.recycle( MagicMissile.class )).reset(
                    effectTypes.get(wandCls),
                    hero.sprite,
                    ray.path.get(ray.dist),
                    null
                                                                                  );
        }

        final float effectMulti = 1f;

        //cast a ray 2/3 the way, and do effects
        Class<? extends Wand> finalWandCls = wandCls;
        ((MagicMissile)hero.sprite.parent.recycle( MagicMissile.class )).reset(
                effectTypes.get(wandCls),
                hero.sprite,
                aim.path.get(Math.min(aoeSize / 2, aim.path.size()-1)),
                new Callback() {
                    @Override
                    public void call() {

                        int charsHit = 0;
                        Freezing
                                freeze = (Freezing)Dungeon.level.blobs.get(Freezing.class);
                        Fire fire = (Fire)Dungeon.level.blobs.get( Fire.class );
                        for (int cell : aoe.cells) {

                            //### Cell effects ###
                            //*** Wand of Lightning ***
                            if (finalWandCls == WandOfLightning.class){
                                if (Dungeon.level.water[cell]){
                                    GameScene.add( Blob.seed( cell, 4, Electricity.class ) );
                                }

                                //*** Wand of Fireblast ***
                            } else if (finalWandCls == 焰浪法杖.class){
                                if (Dungeon.level.map[cell] == Terrain.DOOR){
                                    Level.set(cell, Terrain.OPEN_DOOR);
                                    GameScene.updateMap(cell);
                                }
                                if (freeze != null){
                                    freeze.clear(cell);
                                }
                                if (Dungeon.level.flamable[cell]){
                                    GameScene.add( Blob.seed( cell, 4, Fire.class ) );
                                }

                                //*** Wand of Frost ***
                            } else if (finalWandCls == WandOfFrost.class){
                                if (fire != null){
                                    fire.clear(cell);
                                }

                                //*** Wand of Prismatic Light ***
                            } else if (finalWandCls == WandOfPrismaticLight.class){
                                for (int n : PathFinder.NEIGHBOURS9) {
                                    int c = cell+n;

                                    if (Dungeon.level.discoverable[c]) {
                                        Dungeon.level.mapped[c] = true;
                                    }

                                    int terr = Dungeon.level.map[c];
                                    if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {

                                        Dungeon.level.discover(c);

                                        GameScene.discoverTile(c, terr);
                                        探地卷轴.discover(c);

                                    }
                                }

                                //*** Wand of Regrowth ***
                            } else if (finalWandCls == WandOfRegrowth.class){
                                //TODO: spend 3 charges worth of regrowth energy from staff?
                                int t = Dungeon.level.map[cell];
                                if (Random.Float() < 0.33f*effectMulti) {
                                    if ((t == Terrain.EMPTY || t == Terrain.EMPTY_DECO || t == Terrain.EMBERS
                                         || t == Terrain.GRASS || t == Terrain.FURROWED_GRASS)
                                        && Dungeon.level.plants.get(cell) == null) {
                                        Level.set(cell, Terrain.HIGH_GRASS);
                                        GameScene.updateMap(cell);
                                    }
                                }
                            }

                            //### Deal damage ###
                            Char mob = Actor.findChar(cell);
                            int damage = Math.round(Hero.heroDamageIntRange(15, 25)
                                                    * effectMulti);

                            if (mob != null && damage > 0 && mob.alignment != Char.Alignment.ALLY){
                                mob.受伤时(damage,Reflection.newInstance(finalWandCls));
                                charsHit++;
                            }

                            //### Other Char Effects ###
                            if (mob != null && mob != hero){
                                //*** Wand of Lightning ***
                                if (finalWandCls == WandOfLightning.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.施加( mob, Paralysis.class, effectMulti*Paralysis.DURATION/2 );
                                    }

                                    //*** Wand of Fireblast ***
                                } else if (finalWandCls == 焰浪法杖.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.施加( mob, 燃烧.class ).reignite( mob );
                                    }

                                    //*** Wand of Corrosion ***
                                } else if (finalWandCls == WandOfCorrosion.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.施加( mob, Corrosion.class).set(4,Math.round(6*effectMulti));
                                        charsHit++;
                                    }

                                    //*** Wand of Blast Wave ***
                                } else if (finalWandCls == WandOfBlastWave.class){
                                    if (mob.alignment != Char.Alignment.ALLY) {
                                        Ballistica aim = new Ballistica(hero.pos, mob.pos, Ballistica.WONT_STOP);
                                        int knockback = aoeSize + 1 - (int)Dungeon.level.trueDistance(hero.pos, mob.pos);
                                        knockback *= effectMulti;
                                        WandOfBlastWave.throwChar(mob,
                                                                  new Ballistica(mob.pos, aim.collisionPos, Ballistica.MAGIC_BOLT),
                                                                  knockback,
                                                                  true,
                                                                  true,
                                                                  this);
                                    }

                                    //*** Wand of Frost ***
                                } else if (finalWandCls == WandOfFrost.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.施加( mob, Frost.class, effectMulti*Frost.DURATION );
                                    }

                                    //*** Wand of Prismatic Light ***
                                } else if (finalWandCls == WandOfPrismaticLight.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.延长(mob, Blindness.class, effectMulti*Blindness.DURATION/2);
                                        charsHit++;
                                    }

                                    //*** Wand of Warding ***
                                } else if (finalWandCls == WandOfWarding.class){
                                    if (mob instanceof WandOfWarding.Ward){
                                        ((WandOfWarding.Ward) mob).wandHeal(0, effectMulti);
                                        charsHit++;
                                    }

                                    //*** Wand of Transfusion ***
                                } else if (finalWandCls == WandOfTransfusion.class){
                                    if(mob.alignment == Char.Alignment.ALLY || mob.buff(Charm.class) != null){
                                        int healing = Math.round(10*effectMulti);
                                        int shielding = (mob.生命 + healing) - mob.最大生命;
                                        if (shielding > 0){
                                            healing -= shielding;
                                            Buff.施加(mob, Barrier.class).设置(shielding);
                                        } else {
                                            shielding = 0;
                                        }
                                        mob.回血(healing);
                                        mob.sprite.emitter().burst(Speck.factory(Speck.HEALING), 4);

                                    } else {
                                        if (!mob.properties().contains(Char.Property.UNDEAD)) {
                                            Charm charm = Buff.施加(mob, Charm.class, effectMulti*Charm.DURATION/2f);
                                            charm.object = hero.id();
                                            charm.ignoreHeroAllies = true;
                                            mob.sprite.centerEmitter().start(Speck.factory(Speck.HEART), 0.2f, 3);
                                        } else {
                                            damage = Math.round(Hero.heroDamageIntRange(15, 25) * effectMulti);
                                            mob.受伤时(damage, Reflection.newInstance(finalWandCls));
                                            mob.sprite.emitter().start(ShadowParticle.UP,0.05f,10);
                                        }
                                    }
                                    charsHit++;

                                    //*** Wand of Corruption ***
                                } else if (finalWandCls == WandOfCorruption.class){
                                    if (mob.isAlive() && mob.alignment != Char.Alignment.ALLY) {
                                        Buff.延长(mob,Amok.class,effectMulti*5f);
                                        charsHit++;
                                    }

                                    //*** Wand of Regrowth ***
                                } else if (finalWandCls == WandOfRegrowth.class){
                                    if (mob.alignment != Char.Alignment.ALLY) {
                                        Buff.延长(mob,Roots.class,effectMulti*Roots.DURATION);
                                        charsHit++;
                                    }
                                }
                            }

                        }

                        //### Self-Effects ###
                        //*** Wand of Magic Missile ***
                        if (finalWandCls == WandOfMagicMissile.class) {
                            Buff.施加(hero, Recharging.class, effectMulti* Recharging.DURATION / 2f);
                            SpellSprite.show( hero, SpellSprite.CHARGE );

                            //*** Wand of Living Earth ***
                        } else if (finalWandCls == WandOfLivingEarth.class && charsHit > 0){
                            for (Mob m : Dungeon.level.mobs){
                                if (m instanceof WandOfLivingEarth.EarthGuardian){
                                    ((WandOfLivingEarth.EarthGuardian) m).setInfo(hero, 0, Math.round(effectMulti*charsHit*5));
                                    m.sprite.centerEmitter().burst(MagicMissile.EarthParticle.ATTRACT, 8 + charsHit);
                                    break;
                                }
                            }

                            //*** Wand of Frost ***
                        } else if (finalWandCls == WandOfFrost.class){
                            if ((hero.buff(燃烧.class)) != null) {
                                hero.buff(燃烧.class).detach();
                            }

                            //*** Wand of Prismatic Light ***
                        } else if (finalWandCls == WandOfPrismaticLight.class){
                            if (Dungeon.isChallenged(Challenges.DARKNESS)){
                                Buff.延长(hero, Light.class, effectMulti * 10f);
                            } else {
                                Buff.延长(hero, Light.class, effectMulti * 50f);
                            }

                        }

                        charsHit = Math.min(4 + hero.天赋点数(Talent.REACTIVE_BARRIER), charsHit);
                        if (charsHit > 0 && hero.天赋(Talent.REACTIVE_BARRIER)){
                            int shielding = Math.round(charsHit*2.5f*hero.天赋点数(Talent.REACTIVE_BARRIER));
                            Buff.施加(hero, Barrier.class).设置(shielding);
                        }

                        hero.spendAndNext(Actor.TICK);
                    }
                }
                                                                              );

//        hero.sprite.operate( hero.pos );
        Invisibility.notimedispel();
//        hero.busy();

//        Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );

    }

    public void 风刃(Hero hero,Integer target){

        if (target == null){
            return;
        }

        if (Actor.findChar(target) == hero){
            GLog.w(Messages.get(this, "self_target"));
            return;
        }

        Ballistica b = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);
        final HashSet<Char> targets = new HashSet<>();

        Char enemy = 风刃.findChar(b,hero, 2+hero.术提升(),targets);

        if (enemy == null || !hero.fieldOfView[enemy.pos]){
            GLog.w(Messages.get(this, "no_target"));
            return;
        }

        targets.add(enemy);

        ConeAOE cone = new ConeAOE(b,60+hero.术提升(60));
        for (Ballistica ray : cone.rays){
            Char toAdd = 风刃.findChar(ray,hero,2+hero.术提升(),targets);
            if (toAdd != null && hero.fieldOfView[toAdd.pos]){
                targets.add(toAdd);
            }
        }
        while (targets.size() > 1 + hero.术提升()){
            Char furthest = null;
            for (Char ch : targets){
                if (furthest == null){
                    furthest = ch;
                } else if (Dungeon.level.trueDistance(enemy.pos, ch.pos) >
                           Dungeon.level.trueDistance(enemy.pos, furthest.pos)){
                    furthest = ch;
                }
            }
            targets.remove(furthest);
        }
        Item.updateQuickslot();

        Item proto = new 手里剑();

        final HashSet<Callback> callbacks = new HashSet<>();

        for (Char ch : targets) {
            Callback callback = new Callback() {
                @Override
                public void call() {
                    float dmgMulti = ch == enemy ? 1f : 0.5f;
                    hero.attack( ch, dmgMulti, 0, 1 );
                    callbacks.remove( this );
                    if (callbacks.isEmpty()) {
                        Invisibility.notimedispel();
                        hero.spendAndNext( hero.攻击延迟());
                    }
                }
            };

            MissileSprite
                    m = ((MissileSprite)hero.sprite.parent.recycle(MissileSprite.class));
            m.reset( hero.sprite, ch.pos, proto, callback );
            m.hardlight(0.6f, 1f, 1f);
            m.alpha(0.8f);

            callbacks.add( callback );
        }

        hero.sprite.zap( enemy.pos );
        hero.busy();
    }
    public void 召唤(Class m){

        ArrayList<Integer> spawnPoints = new ArrayList<>();

        for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
            int p = pos + PathFinder.NEIGHBOURS8[i];
            if (Actor.findChar( p ) == null && Dungeon.level.passable[p]) {
                spawnPoints.add( p );
            }
        }

        int ratsToSpawn = 1;

        while (ratsToSpawn > 0 && spawnPoints.size() > 0) {
            int index = Random.index( spawnPoints );

            Mob mob = (Mob)Reflection.newInstance(m);
            mob.alignment = Char.Alignment.ALLY;
            mob.state = mob.HUNTING;
            Buff.施加(mob, AscensionChallenge.AscensionBuffBlocker.class);
            GameScene.add( mob );
            传送卷轴.appear(mob,spawnPoints.get(index));

            spawnPoints.remove( index );
            ratsToSpawn--;
        }

    }

    public void 鼠化(Hero hero,Integer target) {

        if (target == null){
            return;
        }

        Char ch = Actor.findChar(target);

        if (ch == null || !Dungeon.level.heroFOV[target]) {
            GLog.w(Messages.get(this, "no_target"));
            return;
        } else if (ch.alignment != Char.Alignment.ENEMY||!(ch instanceof Mob)||ch instanceof 超级魔法绵羊||ch instanceof DM0||ch instanceof 毒气宝箱怪||ch instanceof NPC||ch instanceof Rat){
            GLog.w(Messages.get(this, "cant_transform"));
            return;
        } else if (ch instanceof TransmogRat){
            if (((TransmogRat) ch).allied ){
                GLog.w(Messages.get(this, "cant_transform"));
                return;
            } else {
                ((TransmogRat) ch).makeAlly();
                ch.sprite.emitter().start(Speck.factory(Speck.HEART), 0.2f, 5);
                Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
            }
        } else if (Char.hasProp(ch, Char.Property.MINIBOSS) || Char.hasProp(ch, Char.Property.BOSS)){
            GLog.w(Messages.get(this, "too_strong"));
            return;
        } else {
            TransmogRat rat = new TransmogRat();
            rat.setup((Mob)ch);
            rat.pos = ch.pos;

            //preserve some buffs
            HashSet<Buff> persistentBuffs = new HashSet<>();
            for (Buff b : ch.buffs()){
                if (b.revivePersists){
                    persistentBuffs.add(b);
                }
            }

            Actor.remove( ch );
            ch.sprite.killAndErase();
            Dungeon.level.mobs.remove(ch);

            for (Buff b : persistentBuffs){
                ch.add(b);
            }

            GameScene.add(rat);

            TargetHealthIndicator.instance.target(null);
            CellEmitter.get(rat.pos).burst(Speck.factory(Speck.WOOL), 4);
            Sample.INSTANCE.play(Assets.Sounds.PUFF);

            //for rare cases where a buff was keeping a mob alive (e.g. gnoll brute rage)
            if (!rat.isAlive()){
                rat.死亡时(this);
            } else {
                Dungeon.level.occupyCell(rat);
            }
        }

        Invisibility.notimedispel();
    }
    public void 跳跃攻击( Hero hero, Integer target ) {
        if (target != null) {
            
            if (hero.rooted){
                PixelScene.shake( 1, 1f );
                return;
            }
            
            Ballistica route = new Ballistica(hero.pos, target,Ballistica.STOP_TARGET);
            int cell = route.collisionPos;
            
            //can't occupy the same cell as another char, so move back one.
            int backTrace = route.dist-1;
            while (Actor.findChar( cell ) != null && cell != hero.pos) {
                cell = route.path.get(backTrace);
                backTrace--;
            }
            if(!(Actor.findChar(cell) == null
                 && (Dungeon.level.passable[cell] &&!Dungeon.level.solid[cell]))){
                for(int i: PathFinder.NEIGHBOURS8){
                    if(Actor.findChar(cell+i)==null&&(Dungeon.level.passable[cell+i]&&!Dungeon.level.solid[cell+i])){
                        if(cell!=-1){
                            cell=cell+i;
                        }
                    }
                }
            }
            final int dest = cell;
            hero.busy();
            hero.sprite.jump(hero.pos, cell, new Callback() {
                @Override
                public void call() {
                    hero.move(dest);
                    Dungeon.level.occupyCell(hero);
                    Dungeon.observe();
                    GameScene.updateFog();
                    
                    Char ch = Actor.findChar(dest);
                    if (ch != null && ch.alignment != hero.alignment){
                        hero.attack(ch, 1, 0, Char.INFINITE);
                        hero.spendAndNext(0);
                    }
                    
                    Invisibility.notimedispel();
                    hero.spendAndNext(攻击延迟());
                    
                }
            });
        }
    }
    
    public void 九头蛇( Hero hero, Integer target ) {
        if (target != null) {
            
            hero.busy();
            for (int i : PathFinder.NEIGHBOURS8) {
                Char mob = Actor.findChar(hero.pos + i);
                if (mob != null && mob != hero && mob.alignment != Char.Alignment.ALLY) {
                    
                    hero.attack(mob, 1, 0, Char.INFINITE);
                    if (mob.pos == hero.pos + i && false){
                        Ballistica trajectory = new Ballistica(mob.pos, mob.pos + i, Ballistica.MAGIC_BOLT);
                        int strength = 1;//击退
                        WandOfBlastWave.throwChar(mob,trajectory,strength,true,true,this);
                    }
                }
                
            }
            WandOfBlastWave.BlastWave.blast(target);
            PixelScene.shake(2, 0.5f);
            
            Invisibility.notimedispel();
            hero.spendAndNext(Actor.TICK);
        }
    }
    
    public void 巨型九头蛇(Hero hero, Integer target) {
        if (target == null){
            return;
        }
        if (target == hero.pos){
            GLog.w(Messages.get(this, "self_target"));
            return;
        }
        hero.busy();
        
        
        Ballistica aim = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);
        
        int maxDist = 3;//格子
        int dist = Math.min(aim.dist, maxDist);
        
        ConeAOE cone = new ConeAOE(aim,
                                   dist,
                                   90,//角度
                                   Ballistica.STOP_SOLID | Ballistica.STOP_TARGET);
        
        //cast to cells at the tip, rather than all cells, better performance.
        for (Ballistica ray : cone.outerRays){
            ((MagicMissile)hero.sprite.parent.recycle(MagicMissile.class)).reset(
                    MagicMissile.FORCE_CONE,
                    hero.sprite,
                    ray.path.get(ray.dist),
                    null
                                                                                );
        }
        
        hero.sprite.zap(target);
        Sample.INSTANCE.play(Assets.Sounds.BLAST, 1f, 0.5f);
        PixelScene.shake(2, 0.5f);
        //final zap at 2/3 distance, for timing of the actual effect
        
        for (int cell : cone.cells){
            Char ch = Actor.findChar(cell);
            if (ch != null && ch.alignment != hero.alignment){
                hero.attack(ch, 1, 0, Char.INFINITE);
            }
        }
        MagicMissile.boltFromChar(hero.sprite.parent,
                                  MagicMissile.FORCE_CONE,
                                  hero.sprite,
                                  cone.coreRay.path.get(dist * 2 / 3),
                                  new Callback() {
                                      @Override
                                      public void call() {
                                          Invisibility.notimedispel();
                                          hero.spendAndNext(攻击延迟());
                                      }
                                  });
    }
    public void 穿越攻击( Hero hero, Integer target ) {
        if (target != null) {
            
            if (hero.rooted){
                PixelScene.shake( 1, 1f );
                return;
            }
            
            Ballistica route = new Ballistica(hero.pos, target,Ballistica.STOP_TARGET);
            int cell = route.collisionPos;
            
            //can't occupy the same cell as another char, so move back one.
            int backTrace = route.dist+1;
            while (Actor.findChar( cell ) != null && cell != hero.pos) {
                cell = route.path.get(backTrace);
                backTrace--;
            }
            if(!(Actor.findChar(cell) == null
                 && (Dungeon.level.passable[cell] &&!Dungeon.level.solid[cell]))){
                for(int i: PathFinder.NEIGHBOURS8){
                    if(Actor.findChar(cell+i)==null&&(Dungeon.level.passable[cell+i]&&!Dungeon.level.solid[cell+i])){
                        if(cell!=-1){
                            cell=cell+i;
                        }
                    }
                }
            }
            final int dest = cell;
            hero.busy();
            hero.sprite.jump(hero.pos, cell,0,0, new Callback() {
                @Override
                public void call() {
                    hero.move(dest);
                    Dungeon.level.occupyCell(hero);
                    Dungeon.observe();
                    GameScene.updateFog();
                    
                    Char ch = Actor.findChar(dest);
                    if (ch != null && ch.alignment != hero.alignment){
                        hero.attack(ch, 1, 0, Char.INFINITE);
                        hero.spendAndNext(0);
                    }
                     
                     Invisibility.notimedispel();
                      hero.spendAndNext(攻击延迟());
                    
                }
            });
        }
    }
    
    public boolean 连击(final Char enemy, float dmgMulti, float dmgBonus, float accMulti,Weapon wep) {

        AttackIndicator.target(enemy);

        boolean wasAlly = enemy.alignment == alignment;
        boolean hit = attack(enemy, dmgMulti, dmgBonus, accMulti);

        Invisibility.notimedispel();

        连击--;
        //fury attacks as many times as you have combo count
        if (连击 > 0 && enemy.isAlive() && canAttack(enemy) &&
                (wasAlly || enemy.alignment != alignment)) {
            sprite.attack(enemy.pos, new Callback() {
                @Override
                public void call() {
                    连击(enemy, dmgMulti, dmgBonus, accMulti,wep);
                }
            });
        } else {
            Sample.INSTANCE.play(wep.hitSound);
            spendAndNext(攻击延迟());
        }
        return hit;
    }
    
    public int 术提升(int x){
        return 术提升()*x;
    }
    public float 术提升(float x){
        return 术提升()*x;
    }
    public int 术提升(){
        return 等级/5;
    }
    public boolean 职业精通(){
        return 等级>20&&Badges.local.contains(Badges.Badge.BOSS_SLAIN_4);
    }
    public void 职业精通提示(){
        String s="";
        if(name().equals("职业精通")){
            
            switch(Dungeon.hero.subClass){
                case 潜能觉醒: default:
                    s= "综合属性+3%。";
                    break;
                case 狂战士:
                    s= "每点怒气+0.25%攻击力。";
                    break;
                case 角斗士:
                    s= "连击重置回合+3，6+连击时连击技获得强化。";
                    break;
                case 战斗法师:
                    s= "魔杖物理攻击会恢复0.5充能。";
                    break;
                case 术士:
                    s= "灵魂标记+10回合。";
                    break;
                case 刺客:
                    s= "每回合隐形会额外获得1阶段潜伏。";
                    break;
                case 神偷无影:
                    s= "拾取物品获得是时间气泡+2回合。";
                    break;
                case 狙击手:
                    s= "除灵能短弓外的物理攻击会施加狙击标记，灵能短弓对狙击标记敌人攻击取决于不同的强化方式。";
                    break;
                case 守望者:
                    s= "踩踏植物会获得额外效果取代原本效果，所有植株对其无害。";
                    break;
                case 武器大师:
                    s= "主武器和副武器攻击效率+10%。";
                    break;
                case 武者:
                    s= "最大内力+5。";
                    break;
                case 疾行者:
                    s= "每点动能额外提供1逸动回合。";
                    break;
                case 征服者:
                    s= "征服者全能吸血+2.5%。";
                    break;
                case 皇室卫兵:
                    s= "护甲修复速度+2.5倍。";
                    break;
                case 神兽之灵:
                    s= "你与视野内的任意一个敌人距离大于1格，那么获得30%移速。";
                    break;
                case 黑白双子:
                    s= "白猫受到伤害反馈-15%。";
                    break;
                case 巫咒王鼠:
                    s= "击杀掉落生肉概率为必定。";
                    break;
                case 实验狂鼠:
                    s= "流血伤害+25%。";
                    break;
                case 金刚独狼:
                    s= "物理攻击和物理防御时额外恢复0.5%已损失生命。";
                    break;
                case 血法师:
                    s= "最大生命+30%。";
                    break;
                case 时间刺客:
                    s= "时停额外提供25%。";
                    break;
                case PRIEST:
                    s= " ";
                    break;
                case PALADIN:
                    s= " ";
                    break;
                case 神秘学者:
                    s= " ";
                    break;
                case 黑魔导师:
                    s= " ";
                    break;
                case 健身猛男:
                    s= "力量+20%。";
                    break;
                case 盾之勇者:
                    s= "首次攻击施加1回合眩晕。";
                    break;
                case 轻装步兵:
                    s= "蛇皮走位下次物理攻击+移速x15。";
                    break;
                case 灵月杀手:
                    s= "+10%攻击力。";
                    break;
            }
        }
    }
}
