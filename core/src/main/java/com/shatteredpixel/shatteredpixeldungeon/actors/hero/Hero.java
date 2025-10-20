

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
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.SacrificialFire;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AdrenalineSurge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Berserk;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Combo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Drowsy;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimeStasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.忍术技能;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.cleric.AscendedForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.huntress.NaturesPower;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.warrior.Endure;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.DivineSense;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HallowedGround;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWard;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Smite;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CheckedCell;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
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
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Stone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.勇装;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.巫服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.忍服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.战甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.披风;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.武服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.法袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.祭服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.背心;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.胸铠;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.能袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.训服;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.连裙;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.道袍;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.铠甲;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.风衣;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.魔披;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CapeOfThorns;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.召唤物品;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.根骨秘药;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.Pickaxe;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.全知之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.六神之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.心力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.时间之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.EyeOfNewt;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ThirteenLeafClover;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Crossbow;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Flail;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Quarterstaff;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Sai;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
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
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ShadowCaster;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.StatusPane;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndHero;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndResurrect;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndTradeItem;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.玩法设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.系统设置;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
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

import java.util.ArrayList;
import java.util.Collections;
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

    public HeroClass heroClass = HeroClass.盗贼;
    public HeroSubClass subClass = HeroSubClass.NONE;
    public ArmorAbility armorAbility = null;
    public ArrayList<LinkedHashMap<Talent, Integer>> talents = new ArrayList<>();
    public LinkedHashMap<Talent, Talent> metamorphedTalents = new LinkedHashMap<>();

    private int 最大命中 = 10;
    private int 最大闪避 = 5;
    public int 神力 = 0;
    public int 额外伤害 = 0;
    public boolean[] 天赋 = {false,false,false};
    public boolean 单身 = false;
    public float 生命成长 = 0;
    public float 防御成长 = 0;
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
    public int 根骨 = 0;
    public int 连击 = 0;
    public int 流血 = 1;
    public int 水伤 = 1;
    public int 法力;
    public int 最大法力;
    public float 污蔑狂宴=0;
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

        生命 = 最大生命 = 15;
        法力 = 最大法力 = 4;
        力量 = 10;

        belongings = new Belongings(this);

        visibleEnemies = new ArrayList<>();
    }

    public void 更新属性() {

        最大生命 = 15 + Math.round((5 + 0.625f) * (等级 - 1)) + 根骨 * 15 + HTBoost;
        最大法力 = 4 + Math.round(4 * (等级 - 1)) ;
        
        最大生命 += 天赋点数(Talent.凝结心神, 20);
        if (buff(根骨秘药.HTBoost.class) != null) {
            最大生命 += buff(根骨秘药.HTBoost.class).boost();
        }
        if(天赋(Talent.污蔑狂宴)){
            最大生命+=Math.round(污蔑狂宴/1500f);
        }
        最大生命+=Math.round(生命成长);

        float multiplier = RingOfMight.HTMultiplier(this);

        if (belongings.armor() instanceof 巫服) {
            multiplier *= 1.1f;
        }
        if (heroClass(HeroClass.重武)) {
            multiplier *= 1.1f;
        }
        multiplier *= 1 + 天赋点数(Talent.强壮体魄, 0.12f);
        multiplier *= 1 - 天赋点数(Talent.声之一型, 0.06f);
        if(Dungeon.玩法(玩法设置.修罗血场)){
            multiplier *=1.25f;
        }
        multiplier *= 综合属性();
        最大生命 = Math.round(最大生命* multiplier);

        生命 = Math.min(生命, 最大生命);
    }

    public int 力量() {

        int str = 力量 + RingOfMight.strengthBonus(this) * 2;
        
        if(天赋(Talent.污蔑狂宴)){
            str+=污蔑狂宴/1500f*天赋点数(Talent.污蔑狂宴,0.1f);
        }
        AdrenalineSurge buff = buff(AdrenalineSurge.class);
        if (buff != null) {
            str += buff.boost();
        }
        if(heroClass(HeroClass.兽灵)){
            str+=Math.round(Math.sqrt(最大生命)*0.13f);
        }
        if(heroClass(HeroClass.近卫)){
            str+=2;
        }
        float x = 1;
        x *= 1 + 天赋点数(Talent.STRONGMAN, 0.1f);
        
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
        x *= 1 + 神力 * 0.05f;
        if(单身){
            x*=1.1f;
        }
        //最后结算
        str = Math.round(str * x);
        return str;
    }

    private static final String CLASS = "class";
    private static final String SUBCLASS = "subClass";
    private static final String ABILITY = "armorAbility";

    private static final String LEVEL = "lvl";
    private static final String 力量x = "力量";
    private static final String 根骨x = "根骨";
    private static final String 连击x = "连击";
    private static final String 流血x = "流血";
    private static final String 水伤x = "水伤";
    private static final String 法力x = "法力";
    private static final String 最大法力x = "法力";
    private static final String 污蔑狂宴x = "污蔑狂宴";
    private static final String 造成伤害x = "造成伤害";
    private static final String 受到伤害x = "受到伤害";

    private static final String 神力x = "神力";
    private static final String 额外伤害x = "额外伤害";
    private static final String 单身x = "单身";
    private static final String 天赋x = "天赋";
    protected static final String 生命成长x    = "生命成长";
    protected static final String 防御成长x    = "防御成长";
    protected static final String 吃饭触发x    = "吃饭触发";
    private static final String EXPERIENCE = "exp";
    private static final String HTBOOST = "htboost";

    @Override
    public void storeInBundle(Bundle bundle) {

        super.storeInBundle(bundle);

        bundle.put(CLASS, heroClass);
        bundle.put(SUBCLASS, subClass);
        bundle.put(ABILITY, armorAbility);
        Talent.storeTalentsInBundle(bundle, this);

        bundle.put(LEVEL, 等级);
        bundle.put(力量x, 力量);
        bundle.put(根骨x, 根骨);
        bundle.put(连击x, 连击);
        bundle.put(流血x, 流血);
        bundle.put(水伤x, 水伤);
        bundle.put(法力x, 法力);
        bundle.put(最大法力x, 最大法力);
        bundle.put(污蔑狂宴x, 污蔑狂宴);
        bundle.put(造成伤害x, 造成伤害);
        bundle.put(受到伤害x, 受到伤害);

        bundle.put(神力x, 神力);
        bundle.put(额外伤害x, 额外伤害);
        bundle.put(单身x, 单身);
        bundle.put(天赋x, 天赋);
        bundle.put( 生命成长x, 生命成长);
        bundle.put( 防御成长x, 防御成长);
        bundle.put( 吃饭触发x, 吃饭触发);
        bundle.put(EXPERIENCE, 当前经验);

        bundle.put(HTBOOST, HTBoost);

        belongings.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {

        等级 = bundle.getInt(LEVEL);
        力量 = bundle.getInt(力量x);
        根骨 = bundle.getInt(根骨x);
        连击 = bundle.getInt(连击x);
        流血 = bundle.getInt(流血x);
        水伤 = bundle.getInt(水伤x);
        法力 = bundle.getInt(法力x);
        最大法力 = bundle.getInt(最大法力x);
        污蔑狂宴 = bundle.getFloat(污蔑狂宴x);
        造成伤害 = bundle.getFloat(造成伤害x);
        受到伤害 = bundle.getFloat(受到伤害x);
        
        神力 = bundle.getInt(神力x);
        额外伤害 = bundle.getInt(额外伤害x);
        单身 = bundle.getBoolean(单身x);
        天赋 = bundle.getBooleanArray(天赋x);
        生命成长 = bundle.getFloat( 生命成长x );
        防御成长 = bundle.getFloat( 防御成长x );
        吃饭触发 = bundle.getFloat( 吃饭触发x );
        当前经验 = bundle.getInt(EXPERIENCE);

        HTBoost = bundle.getInt(HTBOOST);

        super.restoreFromBundle(bundle);

        heroClass = bundle.getEnum(CLASS, HeroClass.class);
        subClass = bundle.getEnum(SUBCLASS, HeroSubClass.class);
        armorAbility = (ArmorAbility) bundle.get(ABILITY);
        Talent.restoreTalentsFromBundle(bundle, this);


        belongings.restoreFromBundle(bundle);
    }

    public static void preview(GamesInProgress.Info info, Bundle bundle) {
        info.level = bundle.getInt(LEVEL);
        info.exp = bundle.getInt(EXPERIENCE);
        info.heroClass = bundle.getEnum(CLASS, HeroClass.class);
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
                    if (f.最大点数() == 2) {
                        if (tier.get(f) == 1) {
                            return x;
                        } else if (tier.get(f) == 2) {
                            return Math.round(x * 1.5f);
                        }
                    }
                    if (f.最大点数() >= 3) {
                        return Math.round(tier.get(f) * x);
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
                    if (f.最大点数() == 2) {
                        if(x==0.66f){
                            if (tier.get(f) == 1) {
                                return x;
                            } else if (tier.get(f) == 2) {
                                return 1.5f * x+0.01f;
                            }
                        }else{
                            if (tier.get(f) == 1) {
                                return x;
                            } else if (tier.get(f) == 2) {
                                return 1.5f * x;
                            }
                        }
                    }
                    if (f.最大点数() >= 3) {
                        if(x==0.33f)
                        return tier.get(f) * x+0.01f;
                        else
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
        if(Dungeon.解压(解压设置.天赋武神)){
            x++;
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
        return subClass == null || subClass == HeroSubClass.NONE ? heroClass.title() : subClass.title();
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
        if(heroClass(HeroClass.女忍)){
            Buff.施加(this,忍术技能.class).gainStack();
        }
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
        if (armor instanceof 铠甲||
            armor instanceof 法袍||
            armor instanceof 风衣||
            armor instanceof 披风||
            armor instanceof 胸铠||
            armor instanceof 祭服||
            armor instanceof 巫服||
            armor instanceof 武服||
            armor instanceof 道袍||
            armor instanceof 战甲||
            armor instanceof 忍服||
            armor instanceof 能袍||
            armor instanceof 勇装||
            armor instanceof 连裙||
            armor instanceof 训服||
            armor instanceof 背心||
            armor instanceof 魔披
            
        ) {
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
        if(hs==HeroClass.血鬼||hs==HeroClass.枪手||hs==HeroClass.凌云){
            return 0;
        }
        return 6;
    }

    public boolean shoot(Char enemy, MissileWeapon wep) {

        attackTarget = enemy;
        boolean wasEnemy = enemy.alignment == Alignment.ENEMY
                || (enemy instanceof Mimic && enemy.alignment == Alignment.NEUTRAL);

        //temporarily set the hero's weapon to the missile weapon being used
        //TODO improve this!
        belongings.thrownWeapon = wep;
        boolean hit = attack(enemy);
        Invisibility.notimedispel();
        belongings.thrownWeapon = null;

        if (hit && subClass == HeroSubClass.GLADIATOR && wasEnemy) {
            Buff.施加(this, Combo.class).hit(enemy);
        }

        if (hit && heroClass == HeroClass.DUELIST && wasEnemy) {
            Buff.施加(this, Sai.ComboStrikeTracker.class).addHit();
        }

        attackTarget = null;
        return hit;
    }

    @Override
    public boolean attack(Char enemy, float dmgMulti, float dmgBonus, float accMulti) {
        boolean result = super.attack(enemy, dmgMulti, dmgBonus, accMulti);
        if (!(belongings.attackingWeapon() instanceof MissileWeapon)) {
            if (buff(Talent.PreciseAssaultTracker.class) != null) {
                buff(Talent.PreciseAssaultTracker.class).detach();
            } else if (buff(Talent.LiquidAgilACCTracker.class) != null
                    && buff(Talent.LiquidAgilACCTracker.class).uses <= 0) {
                buff(Talent.LiquidAgilACCTracker.class).detach();
            }
        }
        return result;
    }

    public int 最大命中(float x) {
        return Math.round(最大命中(null) * x);
    }

    @Override
    public int 最大命中(Char target) {
        KindOfWeapon wep = belongings.attackingWeapon();

        float accuracy = 1;

        accuracy *= 综合属性();
        if (belongings.armor() instanceof 武服) {
            accuracy *= 1.1f;
        }
        accuracy *= 1 + 天赋点数(Talent.顶福精华, 0.13f);
        accuracy *= RingOfAccuracy.accuracyMultiplier(this);
        if(heroClass(HeroClass.戒老)){
            accuracy*=0.75f;
        }
        if(Dungeon.炼狱(炼狱设置.诅咒之戒)){
            accuracy*=0.67f;
        }
        if(wep==null){
            accuracy*=1+Math.sqrt(力量())*属性增幅;
        }
        
        //precise assault and liquid agility
        if (!(wep instanceof MissileWeapon)) {
            if (false//使用武技能命中+
                    //does not trigger on ability attacks
                    && belongings.abilityWeapon != wep && buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) == null) {

                //non-duelist benefit for precise assault, can stack with liquid agility
                if (heroClass != HeroClass.DUELIST) {
                    //persistent +10%/20%/30% ACC for other heroes
                    accuracy *= 2;
                }

                if (wep instanceof Flail && buff(Flail.SpinAbilityTracker.class) != null) {
                    //do nothing, this is not a regular attack so don't consume talent fx
                } else if (wep instanceof Crossbow && buff(Crossbow.ChargedShot.class) != null) {
                    //do nothing, this is not a regular attack so don't consume talent fx
                } else if (buff(Talent.PreciseAssaultTracker.class) != null) {
                    // 2x/5x/inf. ACC for duelist if she just used a weapon ability
                    switch (2) {
                        default:
                        case 1:
                            accuracy *= 2;
                            break;
                        case 2:
                            accuracy *= 4;
                            break;
                        case 3:
                            accuracy *= 6;
                            break;
                        case 4:
                            accuracy *= Float.POSITIVE_INFINITY;
                            break;
                    }
                }
            }
        } else {
            if (buff(Momentum.class) != null && buff(Momentum.class).freerunning()) {
                accuracy *= 1f + 天赋点数(Talent.PROJECTILE_MOMENTUM);
            }
        }
        if (buff(Talent.LiquidAgilACCTracker.class) != null) {
            if (天赋点数(Talent.灵敏机动) == 1) {
                accuracy *= 2;
            } else if (天赋点数(Talent.灵敏机动) == 2) {
                accuracy *= 4;
            } else if (天赋点数(Talent.灵敏机动) == 3) {
                accuracy *= 6;
            } else if (天赋点数(Talent.灵敏机动) == 4) {
                accuracy *= Float.POSITIVE_INFINITY;
            }
            Talent.LiquidAgilACCTracker buff = buff(Talent.LiquidAgilACCTracker.class);
            buff.uses--;
        }

        if (buff(Scimitar.SwordDance.class) != null) {
            accuracy *= 1.50f;
        }

        accuracy *= 1 + 天赋点数(Talent.用盾诀窍, 0.35f);

        if (!武力之戒.fightingUnarmed(this)&&target!=null) {
            return Math.max(1, 天赋点数(Talent.顶福精华, 10) + Math.round((最大命中 + (等级 - 1) * 1.21f) * accuracy * wep.accuracyFactor(this, target)));
        } else {
            return Math.max(1, 天赋点数(Talent.顶福精华, 10) + Math.round((最大命中 + (等级 - 1) * 1.21f) * accuracy));
        }
    }

    public int 最大闪避(float x) {
        return Math.round(最大闪避(null) * x);
    }

    @Override
    public int 最大闪避(Char enemy) {

        if (buff(Combo.ParryTracker.class) != null && enemy != null) {
            if (canAttack(enemy) && !isCharmedBy(enemy)) {
                Buff.施加(this, Combo.RiposteTracker.class).enemy = enemy;
            }
            return INFINITE_EVASION;
        }

        if (buff(RoundShield.GuardTracker.class) != null) {
            return INFINITE_EVASION;
        }

        float evasion = (最大闪避 + Math.round((等级 - 1) * 1.21f));

        evasion += 天赋点数(Talent.顶福精华, 5);
        evasion *= 1 + 天赋点数(Talent.顶福精华, 0.08f);
        evasion *= 综合属性();
        if(belongings.armor==null){
            if(Math.sqrt(力量())>=1){
                evasion*=1+Math.sqrt(力量())*属性增幅;
            }
        }
        if(heroClass(HeroClass.女忍)){
            evasion*=1.1f;
        }
        if (belongings.armor instanceof 风衣) {
            evasion *= 1.1f;
        }
        evasion *= RingOfEvasion.evasionMultiplier(this);

        if (buff(Talent.LiquidAgilEVATracker.class) != null) {
            if (天赋点数(Talent.灵敏机动) == 1) {
                evasion *= 2;
            } else if (天赋点数(Talent.灵敏机动) == 2) {
                evasion *= 4;
            } else if (天赋点数(Talent.灵敏机动) == 3) {
                evasion *= 6;
            } else if (天赋点数(Talent.灵敏机动) == 4) {
                return INFINITE_EVASION;
            }
        }

        if (buff(Quarterstaff.DefensiveStance.class) != null) {
            evasion *= 3;
        }
        if (天赋(Talent.灵敏机动)) {
            evasion *= 1+天赋点数(Talent.灵敏机动, 0.3f);
        }
        if (paralysed > 0) {
            evasion /= 2;
        }

        if (belongings.armor() != null) {
            evasion = belongings.armor().evasionFactor(this, evasion);
        }
        //stone specifically overrides to 0 always, guaranteed hit
        if (belongings.armor().hasGlyph(Stone.class, this) && !Stone.testingEvasion()){
            return 0;
        }
        return Math.max(1, Math.round(evasion));
    }

    @Override
    public String defenseVerb() {
        Combo.ParryTracker parry = buff(Combo.ParryTracker.class);
        if (parry != null) {
            parry.parried = true;
            if (buff(Combo.class).getComboCount() < 9 || 天赋点数(Talent.ENHANCED_COMBO) < 2) {
                parry.detach();
            }
            return Messages.get(Monk.class, "parried");
        }

        if (buff(RoundShield.GuardTracker.class) != null) {
            buff(RoundShield.GuardTracker.class).hasBlocked = true;
            BuffIndicator.refreshHero();
            Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1, Random.Float(0.96f, 1.05f));
            return Messages.get(RoundShield.GuardTracker.class, "guarded");
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
    public int 防御() {
        int dr = super.防御()+Random.NormalIntRange(0,Math.round(防御成长));

        if (belongings.armor() != null) {
            int armDr = Random.NormalIntRange(belongings.armor().最小防御(),
                                  belongings.armor().最大防御()
                                );
            if(Dungeon.玩法(玩法设置.简单战斗)){
                armDr=0;
                armDr=(Math.round((belongings.armor().最小防御()+
                                                  belongings.armor().最大防御()
                                                 )/2f));
            }
            if (力量() < belongings.armor().力量() && !heroClass(HeroClass.重武)) {
                armDr -= 2 * (belongings.armor().力量() - 力量());
            }
            if (armDr > 0) dr += armDr;
        }
        if (belongings.weapon() != null && !武力之戒.fightingUnarmed(this)) {
            int wepDr = Random.NormalIntRange(0,
                      Math.round(belongings.weapon().defenseFactor(this)));
            
            if(Dungeon.玩法(玩法设置.简单战斗)){
                wepDr=0;
                wepDr=(Math.round(belongings.weapon().defenseFactor(this)/2f));
            }
            if (力量() < ((Weapon) belongings.weapon()).力量() && !heroClass(HeroClass.重武)) {
                wepDr -= 2 * (((Weapon) belongings.weapon()).力量() - 力量());
            }
            if (wepDr > 0) dr += wepDr;
        }
        if (buff(HoldFast.class) != null) {
            dr += buff(HoldFast.class).armorBonus();
        }
        if(在草丛()&&天赋(Talent.自然灵体))
        dr+=Random.NormalIntRange(0,天赋生命力(Talent.自然灵体,0.5f));
        if (天赋(Talent.最佳防御)) {
            dr *=1 + 天赋点数(Talent.最佳防御, 0.12f);
        }
        return dr;
    }

    @Override
    public int 攻击() {
        KindOfWeapon wep = belongings.attackingWeapon();
        int dmg=0;

        if (武力之戒.fightingUnarmed(this)) {//空手
            dmg = 武力之戒.damageRoll(this);
            if (武力之戒.unarmedGetsWeaponAugment(this)) {
                dmg = ((Weapon) belongings.attackingWeapon()).augment.damageFactor(dmg);
            }
        } else {
            if(Dungeon.炼狱(炼狱设置.诅咒投掷)){
                if(wep instanceof MissileWeapon||
                   wep instanceof 灵能短弓){
                   
                }else{
                    dmg = wep.damageRoll(this);
                }
            }else{
                if(wep instanceof MissileWeapon||
                   wep instanceof 灵能短弓){
                    dmg = wep.damageRoll(this)
                          +heroDamageIntRange(天赋生命力(Talent.精准射击,0.05f),天赋生命力(Talent.精准射击,0.12f))
                          +heroDamageIntRange(天赋生命力(Talent.持久忍战,0.04f),天赋生命力(Talent.持久忍战,0.1f))
                                             ;
                }else{
                    dmg = wep.damageRoll(this);
                }
            }
            if(hasbuff(武力之戒.Force.class))
           dmg +=武力之戒.armedDamageBonus(this)+武力之戒.heromin();
        }
        if(Dungeon.系统(系统设置.金币能力)){
            dmg+=heroDamageIntRange(0, Math.round(Dungeon.gold/100));
        }
        dmg+=heroDamageIntRange(0,天赋生命力(Talent.誓死捍卫,0.2f));
        dmg+=heroDamageIntRange(0,天赋生命力(Talent.矢量重击,0.3f));

        PhysicalEmpower emp = buff(PhysicalEmpower.class);
        if (emp != null) {
            dmg += emp.dmgBoost;
            emp.left--;
            if (emp.left <= 0) {
                emp.detach();
            }
            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG, 0.75f, 1.2f);
        }

        if (heroClass != HeroClass.DUELIST
            &&天赋(Talent.WEAPON_RECHARGING)
            &&(buff(Recharging.class) != null || buff(ArtifactRecharge.class) != null)) {
            dmg = Math.round(dmg * 天赋点数(Talent.WEAPON_RECHARGING, 0.08f));
        }

        dmg *= 1 + 天赋点数(Talent.勇士之证, 0.15f);
        
        if (dmg < 0) dmg = 0;
        return dmg;
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

        speed *= 综合属性();
        speed*=中国国旗.移速();
        if(belongings.armor==null){
            speed*=1+Math.sqrt(力量())*属性增幅*8;
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
        if (heroClass(HeroClass.行僧)) {
            speed *= 1.1f;
        }
        if (在水中()) {
            if (heroClass(HeroClass.盗贼)) {
                speed *= 1.1f;
            }
            speed*=1+天赋点数(Talent.雨露均沾,0.2f);
        }
        if (天赋(Talent.窄区突围)){
            if(在狭窄()){
               speed*=1+天赋点数(Talent.窄区突围,0.16f);
            }
        }
        speed *= RingOfHaste.speedMultiplier(this);

        if (belongings.armor() != null) {
            speed = belongings.armor().speedFactor(this, speed);
        }

        Momentum momentum = buff(Momentum.class);
        if (momentum != null) {
            ((HeroSprite) sprite).sprint(momentum.freerunning() ? 1.5f : 1f);
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

    public float 攻速() {
        if (buff(Talent.LethalMomentumTracker.class) != null) {
            buff(Talent.LethalMomentumTracker.class).detach();
            return 0;
        }

        float delay = 1/RingOfFuror.attackSpeedMultiplier(this);
        delay /= 综合属性();
        if(每2次攻击==2){
            delay/=1+天赋点数(Talent.接连攻击,0.2f);
        }
        delay/=1-天赋点数(Talent.额外计算,0.1f);
        delay/=1-天赋点数(Talent.矢量重击,0.06f);
        
        delay /= 1 + 天赋点数(Talent.DEATHLESS_FURY, 0.15f);
        if (天赋(Talent.暴性猎食)&&attackTarget!=null){
            delay*=1+attackTarget.根据已损失生命()*天赋点数(Talent.暴性猎食,0.3f);
        }
        if(belongings.weapon==null&&nobuff(武力之戒.BrawlersStance.class)){
            delay/=1+Math.sqrt(力量())*属性增幅*8;
        }
        if (!武力之戒.fightingUnarmed(this)) {
            delay *=belongings.attackingWeapon().delayFactor(this);
            
            if (SPDSettings.固定攻速() >= 5) {
                return delay;
            }
            if (SPDSettings.固定攻速() ==4) {
                return delay <= 1/4f ? 1/4f : delay;
            }
            if (SPDSettings.固定攻速() ==3) {
                return delay <= 1/3f ? 1/3f : delay;
            }
            if (SPDSettings.固定攻速() ==2) {
                return delay <= 1/2f ? 1/2f : delay;
            }
            if (SPDSettings.固定攻速() == 1) {
                return delay <= 1 ? 1 : delay;
            }
            return delay;
        } else {
            //Normally putting furor speed on unarmed attacks would be unnecessary
            //But there's going to be that one guy who gets a furor+force ring combo
            //This is for that one guy, you shall get your fists of fury!
            float speed = 1;

            //ditto for furor + sword dance!
            if (buff(Scimitar.SwordDance.class) != null) {
                speed *= 1.6f;
            }

            //and augments + brawler's stance! My goodness, so many options now compared to 2014!
            if (武力之戒.unarmedGetsWeaponAugment(this)) {
                delay = ((Weapon) belongings.weapon).augment.delayFactor(delay);
            }

            delay /=speed;
            if (SPDSettings.固定攻速() >= 5) {
                return delay;
            }
            if (SPDSettings.固定攻速() ==4) {
                return delay <= 1/4f ? 1/4f : delay;
            }
            if (SPDSettings.固定攻速() ==3) {
                return delay <= 1/3f ? 1/3f : delay;
            }
            if (SPDSettings.固定攻速() ==2) {
                return delay <= 1/2f ? 1/2f : delay;
            }
            if (SPDSettings.固定攻速() == 1) {
                return delay <= 1 ? 1 : delay;
            }
            return delay;
        }
    }

    @Override
    public void spend(float time) {
        super.spend(time);
    }

    @Override
    public void spendConstant(float time) {
        if(算法.isDebug()){
            time/=3f;
        }
        if(Dungeon.系统(系统设置.时间能力)){
            time/=3f;
        }
        if(belongings.armor() instanceof 魔披){
            time*=0.9f;
        }
        time*=时间之戒.时间(this);
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
        更新属性();
        Dungeon.observe();
        Item.updateQuickslot();
        GameScene.updateFog();
    }
    @Override
    public boolean act() {
        Dungeon.level.落石(this);
        
        if(heroClass(HeroClass.女忍)){
            Buff.施加(this,忍术技能.class).gainStack();
        }
        if(在水中()&&天赋(Talent.雨后春笋)){
            回血(天赋点数(Talent.雨后春笋,0.014f)*生命力());
        }
        if(生命流动>=1){
            回血(Math.round(生命流动));
            生命流动-=Math.round(生命流动);
        }else if(生命流动<0){
            受伤(Math.round(生命流动));
            生命流动+=Math.round(生命流动);
        }
        if(heroClass(HeroClass.血鬼)){
            流血++;
            if(流血==18) {
                流血=1;
                受伤(生命力(0.25f));
            }
        }
        if(heroClass(HeroClass.机器)&&在水中()){
            水伤++;
            if(水伤==7) {
                水伤=1;
                受伤(生命力(0.25f));
            }
        }
        
        
        if(heroClass(HeroClass.凌云)){
            flying=true;
        }
        if(heroClass(HeroClass.鼠弟)){
            for (int n : PathFinder.NEIGHBOURS8){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    c.受伤(heroDamageIntRange(1,2));
                }
            }
        }
        if (在草丛()&&天赋(Talent.自然丰收)) {
            float shield = 天赋点数(Talent.自然丰收, 0.275f);
            Buff.施加(this, Hunger.class).吃饭(shield);
        }
		if(SPDSettings.自动拾取()){
			Heap heap = Dungeon.level.heaps.get(pos);
			if (heap != null && heap.type == Type.HEAP) {
				//自动拾取
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
                        heap.sprite.drop();
                    }
				}
			}
		}
        
        if (天赋(Talent.忍者直觉)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
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
                spendConstant(TIME_TO_REST * SPDSettings.休息速度());
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

        if (天赋(Talent.BARKSKIN)&&Dungeon.level.map[pos]==Terrain.FURROWED_GRASS) {
            Barkskin.conditionallyAppend(this, Math.round(等级 * 天赋点数(Talent.BARKSKIN, 0.5f)), 1);
        }

        return actResult;
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

    //used to keep track if the wait/pickup action was used
    // so that the hero spends a turn even if the fail to pick up an item
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
                            || item instanceof Guidebook
                            || (item instanceof MissileWeapon && !MissileWeapon.UpgradedSetTracker.pickupValid(this, (MissileWeapon) item))) {
                        //Do Nothing
                    } else if (item instanceof DarkGold) {
                        DarkGold existing = belongings.getItem(DarkGold.class);
                        if (existing != null) {
                            if (existing.数量()>=20) {
                                GLog.p(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                            } else {
                                GLog.i(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                            }
                        }
                    } else {

                        //TODO make all unique items important? or just POS / SOU?
                        boolean important = item.unique && item.已鉴定() &&
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
                        spendAndNextConstant(TIME_TO_REST);
                    }

                    //allow the hero to move between levels even if they can't collect the item
                    if (Dungeon.level.getTransition(pos) != null) {
                        throwItems();
                    } else {
                        heap.sprite.drop();
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

            } else if (door == Terrain.CRYSTAL_DOOR
                    && Notes.keyCount(new CrystalKey(Dungeon.depth)) > 0) {

                hasKey = true;

            } else if (door == Terrain.LOCKED_EXIT
                    && Notes.keyCount(new 骷髅钥匙(Dungeon.depth)) > 0) {

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
                                DarkGold existing = Dungeon.hero.belongings.getItem(DarkGold.class);
                                if (existing != null &&existing.数量()%5==0) {
                                    if (existing.数量()>=20) {
                                        GLog.p(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                                    } else {
                                        GLog.i(Messages.get(DarkGold.class, "you_now_have", existing.数量()));
                                    }
                                }
                                spend(-Actor.TICK); //picking up the gold doesn't spend a turn here
                            } else {
                                Dungeon.level.drop(gold, pos).sprite.drop();
                            }
                            PixelScene.shake(0.5f, 0.5f);
                            CellEmitter.center(action.dst).burst(Speck.factory(Speck.STAR), 7);
                            Sample.INSTANCE.play(Assets.Sounds.EVOKE);
                            Level.set(action.dst, Terrain.EMPTY_DECO);

                            //mining gold doesn't break crystals
                            crystalAdjacent = false;

                            //4 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.WALL) {
                            buff(Hunger.class).吃饭(-3);
                            PixelScene.shake(0.5f, 0.5f);
                            CellEmitter.get(action.dst).burst(Speck.factory(Speck.ROCK), 2);
                            Sample.INSTANCE.play(Assets.Sounds.MINE);
                            Level.set(action.dst, Terrain.EMPTY_DECO);

                            //1 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.MINE_CRYSTAL) {
                            Splash.at(action.dst, 0xFFFFFF, 5);
                            Sample.INSTANCE.play(Assets.Sounds.SHATTER);
                            Level.set(action.dst, Terrain.EMPTY);

                            //1 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.MINE_BOULDER) {
                            Splash.at(action.dst, 0x555555, 5);
                            Sample.INSTANCE.play(Assets.Sounds.MINE, 0.6f);
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

            if (heroClass != HeroClass.DUELIST
                &&天赋(Talent.AGGRESSIVE_BARRIER)
                && buff(Talent.AggressiveBarrierCooldown.class) == null) {
                int shieldAmt = 天赋生命力(Talent.AGGRESSIVE_BARRIER, 0.8f);
                Buff.施加(this, Barrier.class).设置(shieldAmt);
                sprite.showStatusWithIcon(CharSprite.增强, Integer.toString(shieldAmt), FloatingText.SHIELDING);
                Buff.施加(this, Talent.AggressiveBarrierCooldown.class, 50f);

            }
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
        spendAndNextConstant(TIME_TO_REST);
        Talent.休息时(this,pos);
        if (!fullRest) {
            if (sprite != null) {
                sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "wait"));
            }
        }
        resting = fullRest;
    }
    public int 暴击(final Char enemy,int dmg){
        int 暴击率=0;
        暴击率+=天赋点数(Talent.英勇战斗);
        if(heroClass(HeroClass.近卫)){
            暴击率+=15;
        }
        if(算法.概率学(暴击率)){
            dmg=Math.round(dmg*1.75f);
        }
        return dmg;
    }
    @Override
    public int 攻击时(final Char enemy, int damage) {
        damage*=造成伤害;
        damage = super.攻击时(enemy, damage);
        damage = 暴击(enemy, damage);
        if(天赋概率(Talent.误打误撞,20)){
            damage=Math.round(damage*(1+天赋生命力(Talent.误打误撞,0.2f)));
        }
        if(enemy.第一次防御&&天赋(Talent.锋利爪击)){
            damage+=天赋生命力(Talent.锋利爪击,0.3f);
        }
        if(每2次攻击==2){
            damage+=天赋生命力(Talent.接连攻击,0.14f);
        }
        if (天赋(Talent.严傲之意)){
            damage+=额外伤害;
            额外伤害=0;
        }
        if (天赋(Talent.辟路先锋)){
            if(在狭窄()){
                int shieldToGive=Dungeon.hero.天赋生命力(Talent.辟路先锋,0.3f);
                Buff.施加(this,Barrier.class).设置(shieldToGive);
                sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(shieldToGive),FloatingText.SHIELDING);
            }
        }
        if (天赋(Talent.狂暴血气)){
            Buff.施加(enemy,流血.class).set(生命力(天赋点数(Talent.狂暴血气,0.16f)));
        }
        if (天赋(Talent.侵血向受)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=天赋点数(Talent.侵血向受,0.05f)*damage*Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
        if(Dungeon.系统(系统设置.生命成长)){
            生命成长+=Dungeon.depth/100f;
            更新数据();
        }
        if(Dungeon.系统(系统设置.防御成长)){
            防御成长+=Dungeon.depth/100f;
        }
        if(Dungeon.系统(系统设置.经验成长)){
            经验(Dungeon.depth());
        }
        
        if (天赋(Talent.孤注一掷)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=天赋点数(Talent.孤注一掷,1f)*Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
        if (buff(Kinetic.ConservedDamage.class) != null) {
            int conservedDamage = 0;
            conservedDamage = buff(Kinetic.ConservedDamage.class).damageBonus();
            buff(Kinetic.ConservedDamage.class).detach();
            //use a tracker so that we can know the true final damage
            Buff.施加(this, Kinetic.KineticTracker.class).conservedDamage = conservedDamage;
            damage += conservedDamage;
        }


        if (damage > 0 && subClass == HeroSubClass.BERSERKER) {
            Berserk berserk = Buff.施加(this, Berserk.class);
            berserk.damage(damage);
        }
        if (天赋(Talent.星火符刃)) {
            enemy.受伤(天赋生命力(Talent.星火符刃, 0.7f));
        }

        if (天赋(Talent.致命打击)&&enemy.第一次防御) {
            damage += 天赋生命力(Talent.致命打击, 0.25f);
        }
        if (天赋(Talent.盾举冲击)) {
            damage += 天赋生命力(Talent.盾举冲击, 0.14f);
        }
        if (enemy.properties().contains(Property.UNDEAD) && heroClass(HeroClass.CLERIC)) {
            damage = Math.round(damage * 1.1f);
        }
        if (天赋(Talent.额外计算))
            damage=Math.round(damage*(1+天赋生命力(Talent.额外计算,0.2f)/(10+天赋生命力(Talent.额外计算,0.2f))));
        
        KindOfWeapon wep;
        if (武力之戒.fightingUnarmed(this)&&!武力之戒.unarmedGetsWeaponEnchantment(this)) {
            wep = null;
        } else {
            wep = belongings.attackingWeapon();
        }
        damage = Talent.攻击时(this, enemy, damage);

        if (wep != null) {
            damage = wep.攻击时(this, enemy, damage);
        } else {
            boolean wasEnemy = enemy.alignment == Alignment.ENEMY;
            if (buff(BodyForm.BodyFormBuff.class) != null
                    && buff(BodyForm.BodyFormBuff.class).enchant() != null) {
                damage = buff(BodyForm.BodyFormBuff.class).enchant().proc(new WornShortsword(), this, enemy, damage);
            }
            if (!wasEnemy || enemy.alignment == Alignment.ENEMY) {
                if (buff(HolyWeapon.HolyWepBuff.class) != null) {
                    int dmg = subClass == HeroSubClass.PALADIN ? Dungeon.hero.生命力() : Dungeon.hero.生命力(0.5f);
                    enemy.受伤时(Math.round(dmg * Weapon.Enchantment.genericProcChanceMultiplier(this)), HolyWeapon.INSTANCE);
                }
                if (buff(Smite.SmiteTracker.class) != null) {
                    enemy.受伤时(Smite.bonusDmg(this, enemy), Smite.INSTANCE);
                }
            }
        }

        switch (subClass) {
            case SNIPER:
                if (wep instanceof MissileWeapon && !(wep instanceof 灵能短弓.SpiritArrow) && enemy != this) {
                    Actor.add(new Actor() {

                        {
                            actPriority = VFX_PRIO;
                        }

                        @Override
                        protected boolean act() {
                            if (enemy.isAlive()) {
                                if (天赋(Talent.SHARED_UPGRADES)) {
                                    int levelBonus = Math.min(天赋点数(Talent.SHARED_UPGRADES, 3), wep.强化等级());
                                    // bonus dmg is 16.67% x weapon level, max of 2/4/6
                                    float bonusDmg = levelBonus * 0.1f + 0.1f;
                                    Buff.延长(Hero.this, SnipersMark.class, SnipersMark.DURATION + levelBonus).set(enemy.id(), bonusDmg);
                                } else {
                                    Buff.延长(Hero.this, SnipersMark.class, SnipersMark.DURATION).set(enemy.id(), 0);
                                }
                            }
                            Actor.remove(this);
                            return true;
                        }
                    });
                }
                break;
            default:
        }

        return damage;
    }

    @Override
    public int 防御时(Char enemy, int damage) {
        damage*=受到伤害;
        Statistics.物理防御++;
        Badges.解锁重武();
        damage*=1-天赋点数(Talent.血液凝聚,0.06f);
        damage*=中国国旗.受伤();
        if(heroClass(HeroClass.近卫)){
            damage*=0.9f;
        }
        if (天赋(Talent.严傲之意)) {
            额外伤害+=天赋点数(Talent.严傲之意, 0.12f)*damage;
        }
        if (天赋(Talent.坚守鉴定)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=天赋点数(Talent.坚守鉴定,1f)*Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
        if (enemy.properties().contains(Property.UNDEAD) && heroClass(HeroClass.CLERIC)) {
            damage = Math.round(damage * 0.9f);
        }
        if (damage > 0 && subClass == HeroSubClass.BERSERKER) {
            Berserk berserk = Buff.施加(this, Berserk.class);
            berserk.damage(damage);
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
            if (buff(HolyWard.HolyArmBuff.class) != null) {
                int blocking = subClass == HeroSubClass.PALADIN ? 生命力(0.5f) : 生命力(0.25f);
                damage -= Math.round(blocking * Armor.Glyph.genericProcChanceMultiplier(enemy));
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
        if (buff(时光沙漏.timeStasis.class) != null
                || buff(TimeStasis.class) != null) {
            return;
        }

        //regular damage interrupt, triggers on any damage except specific mild DOT effects
        // unless the player recently hit 'continue moving', in which case this is ignored
        if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage) && damageInterrupt) {
            interrupt();
        }

        if (this.buff(Drowsy.class) != null) {
            Buff.detach(this, Drowsy.class);
            GLog.w(Messages.get(this, "pain_resist"));
        }
        if(src instanceof Hunger){
        
        }else{
            dmg-=Random.NormalIntRange(0,天赋点数(Talent.痛忍觉悟));
        }
        if(天赋(Talent.绝望安息)){
            dmg=Math.round(dmg*(1-天赋点数(Talent.绝望安息,0.06f)));
        }
        if (天赋(Talent.侵血向受)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=天赋点数(Talent.侵血向受,0.05f)*dmg*Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
        
        if (heroClass(HeroClass.巫女)) {
            经验(算法.固衰(dmg,5), getClass());
        }
        if(Dungeon.系统(系统设置.波罗神盾)&&dmg<=3){
            Buff.施加(this, Barrier.class).设置(5);
        }

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

        //unused, could be removed
        CapeOfThorns.Thorns thorns = buff(CapeOfThorns.Thorns.class);
        if (thorns != null) {
            damage = thorns.proc((int) damage, (src instanceof Char ? (Char) src : null), this);
        }

        if (buff(Talent.WarriorFoodImmunity.class) != null) {
            if (heroClass(HeroClass.WARRIOR)) {
                damage = 0f;
            }
        }

        dmg = Math.round(damage);

        //we ceil this one to avoid letting the player easily take 0 dmg from tenacity early
        dmg = (int) Math.ceil(dmg * RingOfTenacity.damageMultiplier(this));

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
            interrupt();
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
            float delay = 1 / 移速();

            if (buff(GreaterHaste.class) != null) {
                delay = 0;
            }

            if (Dungeon.level.pit[step] && !Dungeon.level.solid[step]
                    && (!flying || buff(Levitation.class) != null && buff(Levitation.class).detachesWithinDelay(delay))) {
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

            if (subClass == HeroSubClass.FREERUNNER) {
                Buff.施加(this, Momentum.class).gainStack();
            }

            sprite.move(pos, step);

            move(step);

            spend(delay);

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
        } else if (Dungeon.level instanceof MiningLevel &&
                belongings.getItem(Pickaxe.class) != null &&
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

        } else if (Dungeon.level.map[cell] == Terrain.LOCKED_DOOR || Dungeon.level.map[cell] == Terrain.CRYSTAL_DOOR || Dungeon.level.map[cell] == Terrain.LOCKED_EXIT) {

            curAction = new HeroAction.Unlock(cell);

        } else if (Dungeon.level.getTransition(cell) != null
                //moving to a transition doesn't automatically trigger it when enemies are near
                && (visibleEnemies.size() == 0 || cell == pos)
                && !Dungeon.level.locked
                && !Dungeon.level.plants.containsKey(cell)
                && (Dungeon.depth < 26 || Dungeon.level.getTransition(cell).type == LevelTransition.Type.REGULAR_ENTRANCE)) {

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

    public void 经验(int exp, Class source) {
        if(Dungeon.玩法(玩法设置.修罗血场)){
            exp=Dungeon.depth();
        }
        if(heroClass(HeroClass.逐姝)){
            exp=Math.round(exp*1.1f);
        }
        
        if (天赋(Talent.万变魁斗)) {
            for (Item item : Dungeon.hero.belongings) {
                float x=天赋点数(Talent.万变魁斗,0.1f)*exp*Talent.鉴定速度(this,item);
                if (item instanceof Armor a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof Wand a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MeleeWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
                if (item instanceof MissileWeapon a) {
                    a.usesLeftToID -= x;
                    a.availableUsesToID -= x;
                }
            }
        }
        //xp granted by ascension challenge is only for on-exp gain effects
        if (source != AscensionChallenge.class) {
            this.当前经验 += exp;

            if (heroSubClass(HeroSubClass.神秘学者)) {
                回血(exp);
            }

            if (heroClass(HeroClass.MAGE)) {
                Buff.延长(this, Recharging.class, 2);
                ScrollOfRecharging.charge(this);
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

        Berserk berserk = buff(Berserk.class);
        if (berserk != null) berserk.recover(percent);

        if (source != 经验药剂.class) {
            for (Item i : belongings) {
                i.onHeroGainExp(percent, this);
            }
            if (buff(Talent.RejuvenatingStepsFurrow.class) != null) {
                buff(Talent.RejuvenatingStepsFurrow.class).countDown(percent * 200f);
                if (buff(Talent.RejuvenatingStepsFurrow.class).count() <= 0) {
                    buff(Talent.RejuvenatingStepsFurrow.class).detach();
                }
            }
            if (buff(ElementalStrike.ElementalStrikeFurrowCounter.class) != null) {
                buff(ElementalStrike.ElementalStrikeFurrowCounter.class).countDown(percent * 20f);
                if (buff(ElementalStrike.ElementalStrikeFurrowCounter.class).count() <= 0) {
                    buff(ElementalStrike.ElementalStrikeFurrowCounter.class).detach();
                }
            }
            if (buff(HallowedGround.HallowedFurrowTracker.class) != null) {
                buff(HallowedGround.HallowedFurrowTracker.class).countDown(percent * 100f);
                if (buff(HallowedGround.HallowedFurrowTracker.class).count() <= 0) {
                    buff(HallowedGround.HallowedFurrowTracker.class).detach();
                }
            }
        }

        while (this.当前经验 >= 升级所需()) {
            this.当前经验 -= 升级所需();

            if (buff(Talent.WandPreservationCounter.class) != null) {
                buff(Talent.WandPreservationCounter.class).detach();
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
        for (Item item : Dungeon.hero.belongings) {
            if (item instanceof 灵月法杖 x) {
                x.updateLevel();
                ;
            }
        }
        等级++;

        if (buff(根骨秘药.HTBoost.class) != null) {
            buff(根骨秘药.HTBoost.class).onLevelUp();
        }
        
        更新数据();

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

        Item.updateQuickslot();

        Badges.validateLevelReached();

    }

    public int 升级所需() {
        return 升级所需(等级);
    }

    public int 升级所需(float x) {
        return Math.round(升级所需(等级));
    }

    public int 升级所需(int lvl) {
        int x = 10;
        int y = 5 + 5;
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
//			if (ankh.isBlessed()) {
                生命 = ankh.isBlessed() ? 最大生命 : 最大生命(0.25f);

                治疗药剂.cure(this);
                Buff.延长(this, Invulnerability.class, ankh.isBlessed() ? Invulnerability.DURATION : Invulnerability.DURATION / 2);

                SpellSprite.show(this, SpellSprite.ANKH);
                GameScene.flash(0x80FFFF40);
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
                Game.runOnRenderThread(new Callback() {
                    @Override
                    public void call() {
                        GameScene.show(new WndResurrect(finalAnkh));
                    }
                });

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
            Dungeon.level.drop(item, cell).sprite.drop(pos);
            items.remove(item);
        }

        for (Char c : Actor.chars()) {
            if (c instanceof DriedRose.GhostHero) {
                ((DriedRose.GhostHero) c).sayHeroKilled();
            }
        }

        Game.runOnRenderThread(new Callback() {
            @Override
            public void call() {
                GameScene.gameOver();
                Sample.INSTANCE.play(Assets.Sounds.DEATH);
            }
        });

        if (cause instanceof Hero.Doom) {
            ((Hero.Doom) cause).onDeath();
        }

        Dungeon.deleteGame(GamesInProgress.curSlot, true);
    }

    //effectively cache this buff to prevent having to call buff(...) a bunch.
    //This is relevant because we call isAlive during drawing, which has both performance
    //and thread coordination implications if that method calls buff(...) frequently
    private Berserk berserk;

    @Override
    public boolean isAlive() {

        if (生命 <= 0) {
            if (berserk == null) berserk = buff(Berserk.class);
            return berserk != null && berserk.berserking();
        } else {
            berserk = null;
            return super.isAlive();
        }
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
        spend(攻速());

        if (hit && subClass == HeroSubClass.GLADIATOR && wasEnemy) {
            Buff.施加(this, Combo.class).hit(attackTarget);
        }

        if (hit && heroClass == HeroClass.DUELIST && wasEnemy) {
            Buff.施加(this, Sai.ComboStrikeTracker.class).addHit();
        }

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

            if (Dungeon.level.distance(pos, doorCell) <= 1) {
                boolean hasKey = true;
                if (door == Terrain.LOCKED_DOOR) {
                    hasKey = Notes.remove(new IronKey(Dungeon.depth));
                    if (hasKey) Level.set(doorCell, Terrain.DOOR);
                } else if (door == Terrain.CRYSTAL_DOOR) {
                    hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
                    if (hasKey) {
                        Level.set(doorCell, Terrain.EMPTY);
                        Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
                        CellEmitter.get(doorCell).start(Speck.factory(Speck.DISCOVER), 0.025f, 20);
                    }
                } else {
                    hasKey = Notes.remove(new 骷髅钥匙(Dungeon.depth));
                    if (hasKey) Level.set(doorCell, Terrain.UNLOCKED_EXIT);
                }

                if (hasKey) {
                    GameScene.updateKeyDisplay();
                    GameScene.updateMap(doorCell);
                    spend(Key.TIME_TO_UNLOCK);
                }
            }

        } else if (curAction instanceof HeroAction.OpenChest) {

            Heap heap = Dungeon.level.heaps.get(((HeroAction.OpenChest) curAction).dst);

            if (Dungeon.level.distance(pos, heap.pos) <= 1) {
                boolean hasKey = true;
                if (heap.type == Type.SKELETON || heap.type == Type.REMAINS) {
                    Sample.INSTANCE.play(Assets.Sounds.BONES);
                } else if (heap.type == Type.LOCKED_CHEST) {
                    hasKey = Notes.remove(new GoldenKey(Dungeon.depth));
                } else if (heap.type == Type.CRYSTAL_CHEST) {
                    hasKey = Notes.remove(new CrystalKey(Dungeon.depth));
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
        int x = 全知之戒.全知之力(this);

        if (buff(Foresight.class) != null) {
            x = Foresight.DISTANCE;
        }
        x += heroClass == HeroClass.盗贼 ? 2 : 1;
        x += 天赋点数(Talent.WIDE_SEARCH);
        return x;
    }

    public int 感知范围() {
        int x =heroClass(HeroClass.戒老)?4/视野范围():1;
        if (buff(DivineSense.DivineSenseTracker.class) != null) {
            if (heroClass == HeroClass.CLERIC) {
                x = 天赋点数(Talent.DIVINE_SENSE, 5);
            } else {
                x = 天赋点数(Talent.DIVINE_SENSE, 2);
            }
        }
        if (heroClass(HeroClass.HUNTRESS)) {
            x++;
        }
        x += 天赋点数(Talent.声之一型,3);
        if (天赋(Talent.HEIGHTENED_SENSES)) {
            x += 天赋点数(Talent.HEIGHTENED_SENSES);
        }
        x += EyeOfNewt.mindVisionRange();
        x += 全知之戒.全知之力(this);
        return x;
    }
    
    public int 光照范围() {
        int x=0;
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
        
        if(Dungeon.isChallenged(Challenges.DARKNESS)||heroClass(HeroClass.戒老)){
            x/=4;
        }
        if (heroClass(HeroClass.CLERIC)) {
            x*=1.38f;
        }
        x *= 1f + 天赋点数(Talent.FARSIGHT, 0.25f);
        x *= EyeOfNewt.visionRangeMultiplier();
        
        x+= 天赋点数(Talent.惊觉一现);
        x+=全知之戒.全知之力(this);
        if (Dungeon.hero.buff(MagicalSight.class) != null) {
            x= MagicalSight.DISTANCE;
        }
        x=Math.max(x,1);
        if(x<=1||hasbuff(Blindness.class)){
            Badges.解锁戒老();
        }
        
        if (!heroClass(HeroClass.戒老)) x=Math.min(x,x+光照范围());
        
        return Math.round(x);
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
                    if(Dungeon.level.map[curr]!=Terrain.SECRET_TRAP){
                        Heap heap = Dungeon.level.heaps.get(curr);
                        if (heap != null && (heap.type == Heap.Type.HEAP || heap.type == Heap.Type.CHEST)) {
                            Item item = heap.peek();
                            
                            if (item.doPickUp(this)) {
                                heap.pickUp();
                            } else {
                                heap.sprite.drop();
                            }
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
            sprite.showStatus(CharSprite.DEFAULT, Messages.get(this, "search"));
            sprite.operate(pos);
            if (!Dungeon.level.locked) {
                if (cursed) {
                    GLog.n(Messages.get(this, "search_distracted"));
                    Buff.施加(this, Hunger.class).吃饭(TIME_TO_SEARCH - (2 * HUNGER_FOR_SEARCH));
                } else {
                    Buff.施加(this, Hunger.class).吃饭(TIME_TO_SEARCH - HUNGER_FOR_SEARCH);
                }
            }
            spendAndNext(TIME_TO_SEARCH);

        }

        if (smthFound) {
            GLog.w(Messages.get(this, "noticed_smth"));
            Sample.INSTANCE.play(Assets.Sounds.SECRET);
            interrupt();
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
            } else if (i instanceof CloakOfShadows&&i.keptThroughLostInventory()&&天赋(Talent.LIGHT_CLOAK)) {
                ((CloakOfShadows) i).activate(this);
            } else if (i instanceof 神圣法典&&i.keptThroughLostInventory()&&天赋(Talent.LIGHT_READING)) {
                ((神圣法典) i).activate(this);
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
        return heroClass == hc;
    }

    public boolean heroSubClass(HeroSubClass sc) {
        return subClass == sc;
    }

    public int 等级(float x) {
        return Math.round(x * 等级);
    }

    public int 力量(float x) {
        return Math.round(x * 力量());
    }

    public float 综合属性() {
        float x = 1 + 天赋点数(Talent.任督二脉, 0.07f);
        if(Dungeon.系统(系统设置.钢铁超人)){
            x+=等级/15f;
        }
        x+=六神之戒.六神之力(this);
        if (heroSubClass(HeroSubClass.潜能觉醒)) {
            x *= 1.07f;
        }
        return x;
    }

    @Override
    public float 吸血() {

        float 吸血 = 0;

        if (heroSubClass(HeroSubClass.黑魔导师)) {
            吸血 += 0.03f;
        }
        if (heroClass(HeroClass.血鬼)) {
            吸血 += 0.05f;
        }
        if(Dungeon.玩法(玩法设置.修罗血场)){
            吸血 += 0.05f;
        }
        吸血 += 天赋点数(Talent.高级吸血, 0.03f);
        吸血 += 天赋点数(Talent.志矢不渝, 0.05f)*根据已损失生命();
        
        return 吸血+super.吸血();
    }

    public boolean 连击(final Char enemy, float dmgMulti, float dmgBonus, float accMulti) {

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
                    连击(enemy, dmgMulti, dmgBonus, accMulti);
                }
            });
        } else {
            Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
            spendAndNext(攻速());
        }
        return hit;
    }
    public int 生命力(){
        float x=心力之戒.心力(this);
        
        if (heroClass(HeroClass.血鬼)) {
            x *= 1.3f;
        }
        int x2=根骨;
        x2+=天赋点数(Talent.兽灵之力,0.1f)*力量();
        return (int) Math.round(Math.sqrt(最大生命)*x+x2);
    }
    public boolean canCast(忍术 spell){
        return buff(MagicImmune.class)==null
               && 法力 >= spell.chargeUse(this)
               && spell.canCast(this);
    }
    
    public void 法力(int x){
        法力 = Math.min(法力 + x, 最大法力);
    }
    
}
