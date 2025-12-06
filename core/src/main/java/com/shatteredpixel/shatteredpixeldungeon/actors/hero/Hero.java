

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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSight;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MindVision;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Momentum;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PhysicalEmpower;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shadows;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimeStasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.怒气;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.死舞;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.白猫保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.连击;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Snake;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CheckedCell;
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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.召唤物品;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.荆棘斗篷;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.磨损钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
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
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.闪避之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.EyeOfNewt;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ThirteenLeafClover;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.传奇肛塞;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
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
    public boolean[] 天赋 = {false,false,false,false,false,false};
    public boolean 单身 = false;
    public boolean 精通 = false;
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
    public int 血气 = 0;
    public int 根骨 = 0;
    public int 连击 = 0;
    public int 饥荒= 0;
    public int 护甲恢复= 0;
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
        生命 = 最大生命 = 15;
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

        最大生命 = 15 + Math.round(4.59f * (等级 - 1)) + HTBoost;
        
        最大生命+=根骨 * 25;
        最大生命+=血气*5;
        if (buff(根骨秘药.HTBoost.class) != null) {
            最大生命 += buff(根骨秘药.HTBoost.class).boost();
        }
        心之钢.心 钢 = buff(心之钢.心.class);
        if (钢 != null) {
            最大生命+=belongings.getItem(心之钢.class).心之钢生命;
        }
        
        最大生命+=Math.round(生命成长);
        最大生命+=RingOfMight.strengthBonus(this)*25;
        
        
        if (heroClass(HeroClass.血鬼)) {
            最大生命 *= 1.3f;
        }
        if (belongings.armor() instanceof 巫服) {
            最大生命 *= 1.1f;
        }
        if (heroClass(HeroClass.重武)) {
            最大生命 *= 1.1f;
        }
        if(Dungeon.玩法(玩法设置.修罗血场)){
            最大生命 *=1.25f;
        }
        最大生命 *= 综合属性();

        生命 = Math.min(生命, 最大生命);
    }

    public int 力量() {

        int str = 力量 + RingOfMight.strengthBonus(this)+神力;
        
        str+=天赋点数(Talent.杀伐果决);
        AdrenalineSurge buff = buff(AdrenalineSurge.class);
        if (buff != null) {
            str += buff.boost();
        }
        if(heroClass(HeroClass.兽灵)){
            str+=最大生命(0.04f);
        }
        float x = 1;
        x *= 1 + 天赋点数(Talent.力大无穷,0.1f);
        
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
        //最后结算
        str = Math.round(str * x);
        return str;
    }

    private static final String CLASS = "class";
    private static final String SUBCLASS = "subClass";
    private static final String ABILITY = "armorAbility";

    private static final String LEVEL = "lvl";
    private static final String 力量x = "力量";
    private static final String 血气x = "血气";
    private static final String 根骨x = "根骨";
    private static final String 连击x = "连击";
    private static final String 饥荒x= "饥荒";
    private static final String 护甲恢复x= "护甲恢复";
    private static final String 造成伤害x = "造成伤害";
    private static final String 受到伤害x = "受到伤害";

    private static final String 神力x = "神力";
    private static final String 额外伤害x = "额外伤害";
    private static final String 单身x = "单身";
    private static final String 精通x = "精通";
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
        bundle.put(血气x, 血气);
        bundle.put(根骨x, 根骨);
        bundle.put(连击x, 连击);
        bundle.put(饥荒x,饥荒);
        bundle.put(护甲恢复x,护甲恢复);
        bundle.put(造成伤害x, 造成伤害);
        bundle.put(受到伤害x, 受到伤害);

        bundle.put(神力x, 神力);
        bundle.put(额外伤害x, 额外伤害);
        bundle.put(单身x, 单身);
        bundle.put(精通x, 精通);
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
        血气 = bundle.getInt(血气x);
        根骨 = bundle.getInt(根骨x);
        连击 = bundle.getInt(连击x);
        饥荒= bundle.getInt(饥荒x);
        护甲恢复= bundle.getInt(护甲恢复x);
        造成伤害 = bundle.getFloat(造成伤害x);
        受到伤害 = bundle.getFloat(受到伤害x);
        
        神力 = bundle.getInt(神力x);
        额外伤害 = bundle.getInt(额外伤害x);
        单身 = bundle.getBoolean(单身x);
        精通 = bundle.getBoolean(精通x);
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
//                    if (f.最大点数() == 2) {
//                        if (tier.get(f) == 1) {
//                            return x;
//                        } else if (tier.get(f) == 2) {
//                            return Math.round(x * 1.5f);
//                        }
//                    }
                    if (f.最大点数() >= 2) {
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
                    if (f.最大点数() >= 2) {
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

//        if (hit && heroClass == HeroClass.DUELIST && wasEnemy) {
//            Buff.施加(this, 双剑.ComboStrikeTracker.class).addHit();
//        }

        attackTarget = null;
        return hit;
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
    @Override
    public int 最小命中(Char target ) {
        int x=0;
        if(heroClass(HeroClass.镜魔))x+=2;
        x+=命中之戒.getBuffedBonus(this,命中之戒.Accuracy.class)*2;
        x*=增加命中();
        return x;
    }
    @Override
    public float 增加命中() {
        float x=1;
        
        x+=天赋点数(Talent.技巧敏捷,0.05f);
        return x;
    }
    @Override
    public int 最大命中(Char target) {
        KindOfWeapon wep = belongings.attackingWeapon();
        int x=Math.round((最大命中 + (等级 - 1) * 1.25f));
        x+=天赋点数(Talent.顶福精华, 10);
        x+=命中之戒.getBuffedBonus(this,命中之戒.Accuracy.class)*2;
        if(精通)
        x+=Math.round(1+精通(1));
        float accuracy = 1;

        accuracy *= 综合属性();
        if (belongings.armor() instanceof 武服) {
            accuracy *= 1.1f;
        }
        accuracy *= 1 + 天赋点数(Talent.顶福精华, 0.13f);
        if(Dungeon.炼狱(炼狱设置.诅咒之戒)){
            accuracy*=0.67f;
        }
        if(wep==null){
            accuracy*=1+Math.sqrt(力量()-8)*属性增幅;
        }
        
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

//                if (wep instanceof 链枷&&buff(链枷.SpinAbilityTracker.class)!=null) {
//                    //do nothing, this is not a regular attack so don't consume talent fx
//                } else if (wep instanceof 十字弩&&buff(十字弩.ChargedShot.class)!=null) {
//                    //do nothing, this is not a regular attack so don't consume talent fx
//                } else
                    if (buff(Talent.PreciseAssaultTracker.class) != null){
                        // 2x/5x/inf. ACC for duelist if she just used a weapon ability、
                        accuracy*=Float.POSITIVE_INFINITY;
                    }
            }
        } else {
            if (buff(Momentum.class) != null && buff(Momentum.class).freerunning()) {
                accuracy *= 1f + buff(Momentum.class).freerunTurns*天赋点数(Talent.PROJECTILE_MOMENTUM,0.1f);
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
        
        
        x*=增加命中();
        x *= accuracy;
        return Math.max(1, x);
    }
    
    @Override
    public int 最小闪避(Char enemy ) {
        int evasion=0;
        evasion+=闪避之戒.getBuffedBonus(this,闪避之戒.Evasion.class)*2;
        
        evasion*=增加闪避();
        return evasion;
    }

    @Override
    public float 增加闪避() {
        float x=1;
        x+=天赋点数(Talent.技巧敏捷,0.05f);
        return x;
    }
    @Override
    public int 最大闪避(Char enemy) {

        if (buff(连击.ParryTracker.class)!=null&&enemy!=null) {
            if (canAttack(enemy) && !isCharmedBy(enemy)) {
                Buff.施加(this, 连击.RiposteTracker.class).enemy = enemy;
            }
            return INFINITE_EVASION;
        }

//        if (buff(圆盾.GuardTracker.class)!=null) {
//            return INFINITE_EVASION;
//        }

        float evasion = (最大闪避 + Math.round((等级 - 1) * 1.25f));
        
        evasion+=闪避之戒.getBuffedBonus(this,闪避之戒.Evasion.class)*2;
        evasion += 天赋点数(Talent.顶福精华, 5);
        evasion *= 1 + 天赋点数(Talent.顶福精华, 0.08f);
        evasion *= 综合属性();
        if (hasbuff(Momentum.class)){
            evasion *= buff(Momentum.class).evasionBonus();
        }
        if(belongings.armor==null){
            if(Math.sqrt(力量())>=1){
                evasion*=1+Math.sqrt(力量()-8)*属性增幅;
            }
        }
        if(heroClass(HeroClass.女忍)){
            evasion*=1.1f;
        }
        if (belongings.armor instanceof 风衣) {
            evasion *= 1.1f;
        }

//        if (buff(铁头棍.DefensiveStance.class)!=null) {
//            evasion *= 3;
//        }
        
        if (paralysed > 0) {
            evasion /= 2;
        }
        evasion*=增加闪避();
        if (belongings.armor() != null) {
            evasion = belongings.armor().evasionFactor(this, evasion);
        }
        //stone specifically overrides to 0 always, guaranteed hit
        if (belongings.armor() != null&&belongings.armor().hasGlyph(Stone.class, this) && !Stone.testingEvasion()){
            return 0;
        }
        return Math.max(1, Math.round(evasion));
    }

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

//        if (buff(圆盾.GuardTracker.class)!=null) {
//            buff(圆盾.GuardTracker.class).hasBlocked = true;
//            BuffIndicator.refreshHero();
//            Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1, Random.Float(0.96f, 1.05f));
//            return Messages.get(圆盾.GuardTracker.class,"guarded");
//        }

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
        int dr =super.最小防御();
        
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
                dr+=装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class);
            }
        }
        dr*=增加防御();
        return dr;
    }
    
    @Override
    public float 增加防御(){
        float x=0;
        x+=天赋点数(Talent.最佳防御,0.12f);
        return super.增加防御()+x;
    }
    
    //变相削弱
    @Override
    public int 最大防御() {
        int dr =super.最大防御()+Math.round(防御成长);
        if(天赋(Talent.双武格挡)){
            if(belongings.weapon!=null)
            dr+=天赋点数(Talent.双武格挡,0.5f)*belongings.weapon.强化等级();
            if(belongings.secondWep!=null)
            dr+=天赋点数(Talent.双武格挡,0.5f)*belongings.secondWep.强化等级();
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
        
        if(hasbuff(装甲之戒.装甲.class)){
            if(belongings.armor()==null){
                dr+=(1+装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class))*装甲之戒.tier();
            }else{
                dr+=装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class);
            }
        }
        
        dr*=增加防御();
        return dr;
    }

    @Override
    public int 最小攻击() {
        Weapon wep = belongings.attackingWeapon();
        int dmg=0;
        
        if (heroClass(HeroClass.WARRIOR)) {
            dmg++;
        }
        if (武力之戒.fightingUnarmed(this)) {//空手
            dmg += 武力之戒.heromin();
            if (武力之戒.unarmedGetsWeaponAugment(this)) {
                dmg += belongings.attackingWeapon().augment.damageFactor(dmg);
            }
        } else {
            if(belongings.thrownWeapon!=null){
                dmg += wep.最小投掷攻击();
            }else if(wep!=null){
                
                if(belongings.secondWep==null){
                    
                    dmg += wep.最小攻击();
                }else{
                    
                    dmg += wep.最小攻击()*(0.5+(精通?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                    dmg += belongings.secondWep.最小攻击()*(0.5+(精通?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                }
                if(wep.拳套){
                    dmg += 武力之戒.min()+武力之戒.heromin();
                }
                if(hasbuff(武力之戒.Force.class))
                    dmg ++;
            }else{
                dmg += 武力之戒.min()+武力之戒.heromin();
            }
        }
        
        
        dmg*=增加攻击();
        if (dmg < 0) dmg = 0;
        return dmg;
    }
    
    @Override
    public float 增加攻击(){
        float x=1;
        if(精通&&SubClass(HeroSubClass.狂战士)){
            x+=Buff.施加(this,怒气.class).怒气*精通(0.0025f);
        }
        if(heroClass(HeroClass.行僧)){
            x+=(移速()-1)*0.15f;
        }
        return x;
    }
    
    @Override
    public int 最大攻击() {
        Weapon wep = belongings.attackingWeapon();
        int dmg=0;

        if (武力之戒.fightingUnarmed(this)) {//空手
            dmg+= 武力之戒.heromax();
            if (武力之戒.unarmedGetsWeaponAugment(this)) {
                dmg += belongings.attackingWeapon().augment.damageFactor(dmg);
            }
        } else {
            if(belongings.thrownWeapon!=null){
                dmg += wep.最大投掷攻击();
            }else if(wep!=null){
                if(belongings.secondWep==null){
                    
                    dmg += wep.最大攻击();
                }else{
                    
                    dmg += wep.最大攻击()*(0.5+(精通?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                    dmg += belongings.secondWep.最大攻击()*(0.5+(精通?0.1f:0)+天赋点数(Talent.复合损伤,0.1f));
                }
                if(wep.拳套){
                    dmg+= 武力之戒.max()+武力之戒.heromax();
                }
                if(hasbuff(武力之戒.Force.class))
                    dmg +=武力之戒.getBuffedBonus(this,武力之戒.Force.class)*2+1;
            }else{
                dmg+= 武力之戒.max()+武力之戒.heromax();
            }
            
        }
        if(Dungeon.系统(系统设置.金币能力)){
            dmg+=Math.round(Dungeon.gold/100);
        }
        
        
        dmg*=增加攻击();
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
        speed*=1+天赋点数(Talent.技巧敏捷,0.05f);
        if(belongings.armor==null){
            speed*=1+Math.sqrt(力量()-8)*属性增幅;
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
            if (heroClass(HeroClass.机器)) {
                speed *= 0.7f;
            }
        }
        speed *= RingOfHaste.speedMultiplier(this);

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
        if(heroClass(HeroClass.DUELIST)) return true;
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
        
        delay/=1+天赋点数(Talent.技巧敏捷,0.05f);
        
        if(belongings.weapon==null&&nobuff(武力之戒.BrawlersStance.class)){
            delay/=2+Math.sqrt(力量()-8)*属性增幅;
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
            if (SPDSettings.固定攻速() == 1) {
                return delay <= 1 ? 1 : delay;
            }
            return delay;
    }

    @Override
    public void spend(float time) {
        if(算法.isDebug()){
            time/=3f;
        }
        if(Dungeon.系统(系统设置.时间能力)){
            time/=4f;
        }
        if(belongings.armor() instanceof 魔披){
            time*=0.9f;
        }
        
        super.spend(time);
    }

    @Override
    public void spendConstant(float time) {
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
            immunities.addAll(Property.ELECTRIC.resistances());
            回血(1);
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
        x+=天赋点数(Talent.死亡抗拒,0.2f)*根据已损失生命();
        return x;
    }
    @Override
    public boolean act() {
        Dungeon.地牢时间++;
        Dungeon.level.落石(this);
        
        if(生命流动>=1){
            回血((int)Math.ceil(生命流动));
            生命流动-=(int)Math.ceil(生命流动);
        }else if(-生命流动>=1){
            受伤((int)Math.ceil(生命流动));
            生命流动+=(int)Math.ceil(生命流动);
        }
        if(-饥荒>=1){
            受伤时((int)Math.ceil(饥荒),Hunger.class);
            饥荒+=(int)Math.ceil(饥荒);
        }
        if(heroClass(HeroClass.血鬼)){
            生命流动-=0.03f;
        }
        if(heroClass(HeroClass.机器)&&在水中()){
            生命流动-=0.1f;
        }
        if(heroClass(HeroClass.WARRIOR)){
            护甲恢复+=根据已损失生命()*10;
            if(护甲恢复>=100){
                护甲(1);
                护甲恢复=0;
            }
        }
        
        
        if(heroClass(HeroClass.凌云)){
            flying=true;
        }
        if(heroClass(HeroClass.鼠弟)){
            for (int n : PathFinder.NEIGHBOURS8){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    c.受伤(heroDamageIntRange(武力之戒.heromin(),武力之戒.heromax()));
                }
            }
        }
//        if (在草丛()&&天赋(Talent.自然丰收)) {
//            Buff.施加(this, Hunger.class).吃饭(天赋点数(Talent.自然丰收, 0.37f));
//        }
        if(SPDSettings.装备武器()){
            Heap heap = Dungeon.level.heaps.get(pos);
            if (heap != null && heap.type == Type.HEAP){
                Item item=heap.peek();
                if(belongings.weapon==null&&item instanceof Weapon w){
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
                Buff.施加(this, Hunger.class).吃饭(TIME_TO_SEARCH - HUNGER_FOR_SEARCH);
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
                spendConstant(TIME_TO_REST * (0.5f+SPDSettings.休息速度()*0.5f));
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
                            || item instanceof Guidebook) {
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
                            Sample.INSTANCE.play(Assets.Sounds.挖爆);
//                            Sample.INSTANCE.play(Assets.Sounds.EVOKE);
                            Level.set(action.dst, Terrain.EMPTY_DECO);

                            //mining gold doesn't break crystals
                            crystalAdjacent = false;

                            //4 hunger spent total
                        } else if (Dungeon.level.map[action.dst] == Terrain.WALL) {
                            buff(Hunger.class).吃饭(-3);
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
        spendAndNextConstant(TIME_TO_REST);
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
        
        int 暴击率=super.暴击率()+4;
        if (天赋(Talent.洪荒之怒)) {
            怒气 怒气= Buff.施加(this,怒气.class);
            暴击率+=Math.round(怒气.怒气*天赋点数(Talent.洪荒之怒,0.1f));
        }
        暴击率+=天赋点数(Talent.杀意感知,3);
        if(heroClass(HeroClass.近卫)){
            暴击率+=6;
        }
        if(belongings.attackingWeapon() instanceof 无尽之刃){
            暴击率+=15;
        }
        if (在草丛()) {
            暴击率+=天赋点数(Talent.丛林法则,8);
        }
        return 暴击率;
    }
    @Override
    public float 暴击伤害(){
        float 暴击伤害=1.75f;
        
        if(belongings.attackingWeapon() instanceof 无尽之刃){
            暴击伤害+=0.35f;
        }
        if (在草丛()) {
            暴击伤害+=天赋点数(Talent.丛林法则,0.05f);
        }
        return 暴击伤害;
    }
    @Override
    public int 暴击(final Char enemy,int dmg){
        if(必暴||算法.概率学(暴击率())){
            dmg=Math.round(dmg*暴击伤害());
            if (subClass == HeroSubClass.狂战士) {
                怒气 怒气= Buff.施加(this,怒气.class);
                怒气.damage();
            }
            必暴=false;
        }
        return dmg;
    }
    public int 攻击范围(){
        int x =1;
        if(belongings.weapon!=null){
            x=belongings.weapon.reachFactor(this);
        }
        return Math.min(视野范围(),x);
    }
    public int 惊醒距离(){
        int x =4-天赋点数(Talent.随风而动);
        
        if(heroClass(HeroClass.镜魔))
            x--;
        return Math.max(2,x);
    }
    @Override
    public int 攻击时(final Char enemy, int damage) {
        if (enemy instanceof Rat&&heroClass(HeroClass.灵猫)) {
            必中=true;
            必暴=true;
        }
        damage = super.攻击时(enemy, damage);
        
		//region +
        
        if (heroClass(HeroClass.重武)) {
            damage+=最小防御();
        }
        if (heroClass(HeroClass.HUNTRESS)&&distance(enemy)<=感知范围()) {
            damage++;
        }
        damage+=天赋点数(Talent.攻击强化);
        
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
        心之钢.心 钢 = buff(心之钢.心.class);
        if (钢 != null) {
            damage = 钢.proc(damage, this, enemy);
        }
        
        
        if (damage > 0 && subClass == HeroSubClass.狂战士) {
            怒气 怒气= Buff.施加(this,怒气.class);
            怒气.damage();
        }
        if (enemy.isAlive()&&天赋(Talent.星火符刃)&&enemy.第一次防御) {
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
        if(Dungeon.系统(系统设置.防御成长)){
            防御成长+=Dungeon.depth/100f;
        }
        if(Dungeon.系统(系统设置.经验成长)){
            经验(Dungeon.区域());
        }
        
        
        Weapon wep;
        if (武力之戒.fightingUnarmed(this)&&!武力之戒.unarmedGetsWeaponEnchantment(this)) {
            wep = null;
        } else {
            wep = belongings.attackingWeapon();
        }
        if (belongings.secondWep != null) {
            damage = belongings.secondWep.攻击时(this, enemy, damage);
        }
        if (wep != null) {
            damage = wep.攻击时(this, enemy, damage);
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
        
		if (SubClass(HeroSubClass.狙击手)&&精通&&!(wep instanceof 灵能短弓.SpiritArrow)) {
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
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()&&heroClass(HeroClass.罗兰)){
                damage*=0.67f;
            }
        }
        damage*=造成伤害;
        
        if (enemy.恶魔亡灵() && heroClass(HeroClass.CLERIC)) {
            damage *=1.1f;
        }
        if (enemy instanceof Rat&&heroClass(HeroClass.灵猫)) {
            damage *=2.5f;
            必中=false;
            必暴=false;
        }
        if(天赋(Talent.狙击弱点)){
            damage*=1+distance(enemy)*天赋点数(Talent.狙击弱点,0.05f);
        }
        if(SubClass(HeroSubClass.狙击手)){
            damage*=(1+暴击率()*暴击伤害());
        }
		//endregion
        
        return damage;
    }

    @Override
    public int 防御时(Char enemy, int damage) {
        
        Statistics.物理防御++;
        Badges.解锁重武();
        
        damage-=天赋点数(Talent.防御强化);
        
		//region 附带效果
        
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()&&heroClass(HeroClass.罗兰)){
                damage*=0.67f;
            }
        }
		if (subClass == HeroSubClass.狂战士) {
            怒气 怒气= Buff.施加(this,怒气.class);
            怒气.damage();
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
        
        
        if(heroClass(HeroClass.近卫)&&damage*0.3f>0){
            死舞 deferred = Buff.施加(this,死舞.class);
            deferred.extend( damage*0.3f);
            
            sprite.showStatus( CharSprite.WARNING, Messages.get(死舞.class, "deferred", Math.round(damage*0.3f)) );
            damage*=0.7f;
        }
        if (enemy.恶魔亡灵() && heroClass(HeroClass.CLERIC)) {
            damage *=0.9f;
        }
        damage*=中国国旗.受伤();
        damage *= RingOfTenacity.damageMultiplier(this);
        damage*=受到伤害;
        
        for (int n : PathFinder.NEIGHBOURS8){
            Char c=Actor.findChar(pos+n);
            int x=0;
            if(c!=null){
                x++;
                if(x>=2)
                damage*=传奇肛塞.受伤();
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
            if(SPDSettings.受伤打断()&&dmg>Dungeon.区域()*2){
                interrupt();
            }
        }

        if (this.buff(Drowsy.class) != null) {
            Buff.detach(this, Drowsy.class);
            GLog.w(Messages.get(this, "pain_resist"));
        }
		//region 附带效果
		if (heroClass(HeroClass.巫女)) {
            经验(算法.固衰(dmg,5), getClass());
        }
        if(Dungeon.系统(系统设置.波罗神盾)&&dmg<=3){
            护甲(5);
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
//        if(Actor.findChar(target)!=null&&Actor.findChar(target)!=this&&Actor.findChar(target).alignment!=Alignment.ALLY){
//            sprite.parent.add(new TargetedCell(target, 0xFF0000));
//        }else{
//            sprite.parent.add(new TargetedCell(target,0x44FF44));
//        }
        
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
    
    @Override
    public void 回血(float x){
        
        if(belongings.attackingWeapon() instanceof 饮血之剑&&满血()){
            护甲((int)Math.ceil(x));
            if(hasbuff(Barrier.class)){
                if(Buff.施加(this, Barrier.class).shielding+Math.round(x)<=最大生命(0.15f))
                Buff.施加(this, Barrier.class).增加(Math.round(x));
            }else{
                Buff.施加(this, Barrier.class).设置(Math.round(x));
            }
        }
        super.回血(x);
    }
    
    public void 经验(int exp,Class source) {
        if(Dungeon.玩法(玩法设置.修罗血场)){
            exp=Dungeon.区域();
        }
        if(heroClass(HeroClass.逐姝)){
            exp++;
        }
        
        //xp granted by ascension challenge is only for on-exp gain effects
        if (source != AscensionChallenge.class) {
            this.当前经验 += exp;

            if (SubClass(HeroSubClass.神秘学者)) {
                回血(exp);
            }
            
            if (heroClass(HeroClass.MAGE)) {
                Buff.延长(this, Recharging.class, 1);
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
        for (Item item : belongings) {
            if (item instanceof 灵月法杖 x) {
                x.updateLevel();
			}
        }
        等级++;

        if (buff(根骨秘药.HTBoost.class) != null) {
            buff(根骨秘药.HTBoost.class).onLevelUp();
        }
        if(等级>21){
            switch(subClass){
                case 潜能觉醒: default:
                    GLog.p("最大命中+！"+"综合属性+10%。");
                    break;
                case 狂战士:
                    GLog.p("最大命中+！"+"每点怒气+0.25%攻击力。");
                    break;
                case 角斗士:
                    GLog.p("最大命中+！"+"连击重置回合+3，6+连击时连击技获得强化。");
                    break;
                case 战斗法师:
                    GLog.p("最大命中+！"+"魔杖物理攻击会恢复0.5充能。");
                    break;
                case 术士:
                    GLog.p("最大命中+！"+"灵魂标记+10回合。");
                    break;
                case 刺客:
                    GLog.p("最大命中+！"+"每回合隐身会额外获得1阶段潜伏。");
                    break;
                case 疾行者:
                    GLog.p("最大命中+！"+"每点动能额外提供1逸动回合。");
                    break;
                case 狙击手:
                    GLog.p("最大命中+！"+"除灵能短弓外的物理攻击会施加狙击标记，灵能短弓对狙击标记敌人攻击取决于不同的强化方式。");
                    break;
                case 守望者:
                    GLog.p("最大命中+！"+"踩踏植物会获得额外效果取代原本效果，所有植株对其无害。");
                    break;
                case 勇士:
                    GLog.p("最大命中+！"+"主武器和副武器攻击效率+10%。");
                    break;
                case 武者:
                    GLog.p("最大命中+！"+"最大内力+5。");
                    break;
                case PRIEST:
                    GLog.p("最大命中+！"+"综合属性+15%");
                    break;
                case PALADIN:
                    GLog.p("最大命中+！"+"综合属性+15%");
                    break;
                case 神秘学者:
                    GLog.p("最大命中+！"+"综合属性+15%");
                    break;
                case 黑魔导师:
                    GLog.p("最大命中+！"+"综合属性+15%");
                    break;
                case 盾之勇者:
                    GLog.p("最大命中+！"+"综合属性+15%");
                    break;
            }
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
        if(heroClass(HeroClass.鼠弟))
            Sample.INSTANCE.play(Assets.Sounds.RAT);
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
            Dungeon.level.drop(item, cell).sprite.drop(pos);
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

//        if (hit && heroClass == HeroClass.DUELIST && wasEnemy) {
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
                Buff.施加(this, Hunger.class).吃饭(-4);
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
                Buff.施加(this, Hunger.class).吃饭(-4);
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
        
        x += 天赋点数(Talent.杀意感知);
        if (buff(Foresight.class) != null) {
            x = Foresight.DISTANCE;
        }
        return x;
    }

    public int 感知范围() {
        int x =1;
        if(heroClass(HeroClass.戒老))x+=视野范围()/2;
        x += 天赋点数(Talent.杀意感知);
        x += EyeOfNewt.mindVisionRange();
        if (buff(DivineSense.DivineSenseTracker.class) != null) {
            x = 天赋点数(Talent.神圣感知,5);
        }
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
        x *= 1f + 天赋点数(Talent.鹰眼远视,0.25f);
        x *= EyeOfNewt.visionRangeMultiplier();
        
        if (hasbuff(MagicalSight.class)) {
            x= MagicalSight.DISTANCE;
        }
        if(hasbuff(Blindness.class)){
            x=1;
        }
        if(hasbuff(Shadows.class)){
            x=1;
        }
        x=Math.max(x,1);
        if(x<=1){
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
            spendAndNext(TIME_TO_SEARCH*x);

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

    public boolean SubClass(HeroSubClass sc) {
        return subClass == sc;
    }

    public int 等级(float x) {
        return Math.round(x * 等级);
    }

    public int 力量(float x) {
        return Math.round(x * 力量());
    }
    public int 精通(int x){
        if(等级>21){
            return (等级-21)*x;
        }else{
            return 0;
        }
    }
    public float 精通(float x){
        if(等级>21){
            return (等级-21)*x;
        }else{
            return 0;
        }
    }

    public float 综合属性() {
        float x = 1 + 天赋点数(Talent.任督二脉, 0.05f);
        if(Dungeon.系统(系统设置.钢铁超人)){
            x+=等级/15f;
        }
        if(belongings.hasItem(三叉戟.class)&&在水中()){
            x+=0.1f;
        }
        x+=六神之戒.六神之力(this);
        if(heroClass(HeroClass.来世)){
            x+=0.1f;
        }
        if (SubClass(HeroSubClass.潜能觉醒)) {
            x += 0.1f;
            if(精通){
                x+=0.1f;
            }
            x+=精通(0.15f);
        }
        return x;
    }

    @Override
    public float 吸血() {

        float 吸血 = 0;

        if (SubClass(HeroSubClass.黑魔导师)) {
            吸血 += 0.04f;
        }
        if (heroClass(HeroClass.血鬼)) {
            吸血 += 0.08f;
        }
        if(Dungeon.玩法(玩法设置.修罗血场)){
            吸血 += 0.05f;
        }
        吸血 += 天赋点数(Talent.高级吸血, 0.04f);
        
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
}
