

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.TextureFilm;

public class 物品表 {

    private static final int WIDTH = 32;
    public static final int SIZE = 16;

    public static TextureFilm film = new TextureFilm(Assets.Sprites.ITEMS, SIZE, SIZE);

    private static int xy(int x, int y) {
        x -= 1;
        y -= 1;
        return x + WIDTH * y;
    }

    private static void assignItemRect(int item) {
        int x = (item % WIDTH) * SIZE;
        int y = (item / WIDTH) * SIZE;
        film.add(item, x, y, x + 16, y + 16);
    }

    private static void assignItemRect(int item, int wh) {
        int x = (item % WIDTH) * SIZE;
        int y = (item / WIDTH) * SIZE;
        film.add(item, x, y, x + wh, y + wh);
    }

    private static void assignItemRect(int item, int width, int height) {
        int x = (item % WIDTH) * SIZE;
        int y = (item / WIDTH) * SIZE;
        film.add(item, x, y, x + width, y + height);
    }

    //region 预备和物品堆
    private static final int PLACEHOLDERS = xy(1, 1);   //18 slots
    //SOMETHING is the default item sprite at position 0. May show up ingame if there are bugs.
    public static final int SOMETHING = PLACEHOLDERS + 0;
    public static final int WEAPON_HOLDER = PLACEHOLDERS + 1;
    public static final int ARMOR_HOLDER = PLACEHOLDERS + 2;
    public static final int MISSILE_HOLDER = PLACEHOLDERS + 3;
    public static final int WAND_HOLDER = PLACEHOLDERS + 4;
    public static final int RING_HOLDER = PLACEHOLDERS + 5;
    public static final int ARTIFACT_HOLDER = PLACEHOLDERS + 6;
    public static final int TRINKET_HOLDER = PLACEHOLDERS + 7;
    public static final int FOOD_HOLDER = PLACEHOLDERS + 8;
    public static final int BOMB_HOLDER = PLACEHOLDERS + 9;
    public static final int POTION_HOLDER = PLACEHOLDERS + 10;
    public static final int SEED_HOLDER = PLACEHOLDERS + 11;
    public static final int SCROLL_HOLDER = PLACEHOLDERS + 12;
    public static final int STONE_HOLDER = PLACEHOLDERS + 13;
    public static final int ELIXIR_HOLDER = PLACEHOLDERS + 14;
    public static final int SPELL_HOLDER = PLACEHOLDERS + 15;
    public static final int MOB_HOLDER = PLACEHOLDERS + 16;
    public static final int DOCUMENT_HOLDER = PLACEHOLDERS + 17;

    static {
        assignItemRect(SOMETHING, 8, 13);
        assignItemRect(WEAPON_HOLDER, 14);
        assignItemRect(ARMOR_HOLDER, 14, 12);
        assignItemRect(MISSILE_HOLDER, 15);
        assignItemRect(WAND_HOLDER, 14);
        assignItemRect(RING_HOLDER, 8, 10);
        assignItemRect(ARTIFACT_HOLDER, 15);
        assignItemRect(TRINKET_HOLDER, 16, 11);
        assignItemRect(FOOD_HOLDER, 15, 11);
        assignItemRect(BOMB_HOLDER, 10, 13);
        assignItemRect(POTION_HOLDER, 12, 14);
        assignItemRect(SEED_HOLDER, 10);
        assignItemRect(SCROLL_HOLDER, 15, 14);
        assignItemRect(STONE_HOLDER, 14, 12);
        assignItemRect(ELIXIR_HOLDER, 12, 14);
        assignItemRect(SPELL_HOLDER, 8, 16);
        assignItemRect(MOB_HOLDER, 15, 14);
        assignItemRect(DOCUMENT_HOLDER, 10, 11);
    }

    private static final int UNCOLLECTIBLE = xy(1, 2);   //14 slots
    public static final int GOLD = UNCOLLECTIBLE + 0;
    public static final int ENERGY = UNCOLLECTIBLE + 1;

    public static final int DEWDROP = UNCOLLECTIBLE + 2;
    public static final int PETAL = UNCOLLECTIBLE + 3;
    public static final int SANDBAG = UNCOLLECTIBLE + 4;

    public static final int TENGU_BOMB = UNCOLLECTIBLE + 5;
    public static final int TENGU_SHOCKER = UNCOLLECTIBLE + 6;
    public static final int GEO_BOULDER = UNCOLLECTIBLE + 7;
    public static final int SPIRIT_ARROW = UNCOLLECTIBLE + 8;
    public static final int 子弹 = UNCOLLECTIBLE + 9;

    static {
        assignItemRect(GOLD, 15, 13);
        assignItemRect(ENERGY, 16);

        assignItemRect(DEWDROP, 10);
        assignItemRect(PETAL, 8, 8);
        assignItemRect(SANDBAG, 10);

        assignItemRect(TENGU_BOMB, 10);
        assignItemRect(TENGU_SHOCKER, 10);
        assignItemRect(GEO_BOULDER, 16, 14);

        assignItemRect(SPIRIT_ARROW, 11);
        assignItemRect(子弹, 5);
    }
    //endregion

    //region 物品
    private static final int CONTAINERS = xy(1, 3);   //16 slots
    public static final int BONES = CONTAINERS + 0;
    public static final int REMAINS = CONTAINERS + 1;
    public static final int TOMB = CONTAINERS + 2;
    public static final int GRAVE = CONTAINERS + 3;
    public static final int CHEST = CONTAINERS + 4;
    public static final int LOCKED_CHEST = CONTAINERS + 5;
    public static final int CRYSTAL_CHEST = CONTAINERS + 6;
    public static final int EBONY_CHEST = CONTAINERS + 7;

    static {
        assignItemRect(BONES, 14, 11);
        assignItemRect(REMAINS, 14, 11);
        assignItemRect(TOMB, 14, 15);
        assignItemRect(GRAVE, 14, 15);
        assignItemRect(CHEST, 16, 14);
        assignItemRect(LOCKED_CHEST, 16, 14);
        assignItemRect(CRYSTAL_CHEST, 16, 14);
        assignItemRect(EBONY_CHEST, 16, 14);
    }

    private static final int MISC_CONSUMABLE = xy(1, 4);   //32 slots
    public static final int ANKH = MISC_CONSUMABLE + 0;
    public static final int STYLUS = MISC_CONSUMABLE + 1;
    public static final int 破损纹章 = MISC_CONSUMABLE + 2;
    public static final int TORCH = MISC_CONSUMABLE + 3;
    public static final int BEACON = MISC_CONSUMABLE + 4;
    public static final int HONEYPOT = MISC_CONSUMABLE + 5;
    public static final int SHATTPOT = MISC_CONSUMABLE + 6;
    public static final int IRON_KEY = MISC_CONSUMABLE + 7;
    public static final int GOLDEN_KEY = MISC_CONSUMABLE + 8;
    public static final int CRYSTAL_KEY = MISC_CONSUMABLE + 9;
    public static final int SKELETON_KEY = MISC_CONSUMABLE + 10;
    public static final int MASK = MISC_CONSUMABLE + 11;
    public static final int CROWN = MISC_CONSUMABLE + 12;
    public static final int AMULET = MISC_CONSUMABLE + 13;
    public static final int MASTERY = MISC_CONSUMABLE + 14;
    public static final int KIT = MISC_CONSUMABLE + 15;


    static {
        assignItemRect(ANKH, 10, 16);
        assignItemRect(STYLUS, 12, 13);

        assignItemRect(破损纹章, 13);
        assignItemRect(TORCH, 12, 15);
        assignItemRect(BEACON, 16, 15);

        assignItemRect(HONEYPOT, 14, 12);
        assignItemRect(SHATTPOT, 14, 12);
        assignItemRect(IRON_KEY, 8, 14);
        assignItemRect(GOLDEN_KEY, 8, 14);
        assignItemRect(CRYSTAL_KEY, 8, 14);
        assignItemRect(SKELETON_KEY, 8, 14);
        assignItemRect(MASK, 11, 9);
        assignItemRect(CROWN, 13, 7);
        assignItemRect(AMULET);
        assignItemRect(MASTERY, 13, 16);
        assignItemRect(KIT, 16, 15);
    }
    //endregion

    //region 炸弹遗物
    public static final int TRINKET_CATA = xy(1, 5);
    public static final int SEAL_SHARD = TRINKET_CATA + 1;
    public static final int BROKEN_STAFF = TRINKET_CATA + 2;
    public static final int CLOAK_SCRAP = TRINKET_CATA + 3;
    public static final int BOW_FRAGMENT = TRINKET_CATA + 4;
    public static final int BROKEN_HILT = TRINKET_CATA + 5;
    public static final int TORN_PAGE = TRINKET_CATA + 6;
    public static final int 残缺灵杖 = TRINKET_CATA + 7;
    public static final int 残缺重盾 = TRINKET_CATA + 8;

    static {
        assignItemRect(TRINKET_CATA, 12, 11);

        assignItemRect(SEAL_SHARD, 12);

        assignItemRect(BROKEN_STAFF, 14, 10);

        assignItemRect(CLOAK_SCRAP, 9, 9);

        assignItemRect(BOW_FRAGMENT, 12, 9);

        assignItemRect(BROKEN_HILT, 9, 9);

        assignItemRect(TORN_PAGE, 11, 13);

        assignItemRect(残缺灵杖, 9, 11);

        assignItemRect(残缺重盾, 11, 15);
    }

    private static final int BOMBS = xy(1, 6);   //16 slots
    public static final int BOMB = BOMBS + 0;
    public static final int DBL_BOMB = BOMBS + 1;
    public static final int FIRE_BOMB = BOMBS + 2;
    public static final int FROST_BOMB = BOMBS + 3;
    public static final int REGROWTH_BOMB = BOMBS + 4;
    public static final int SMOKE_BOMB = BOMBS + 5;
    public static final int FLASHBANG = BOMBS + 6;
    public static final int HOLY_BOMB = BOMBS + 7;
    public static final int WOOLY_BOMB = BOMBS + 8;
    public static final int NOISEMAKER = BOMBS + 9;
    public static final int ARCANE_BOMB = BOMBS + 10;
    public static final int SHRAPNEL_BOMB = BOMBS + 11;

    static {
        assignItemRect(BOMB, 10, 13);
        assignItemRect(DBL_BOMB, 14, 13);
        assignItemRect(FIRE_BOMB, 13, 12);
        assignItemRect(FROST_BOMB, 13, 12);
        assignItemRect(REGROWTH_BOMB, 13, 12);
        assignItemRect(SMOKE_BOMB, 13, 12);
        assignItemRect(FLASHBANG, 10, 13);
        assignItemRect(HOLY_BOMB, 10, 13);
        assignItemRect(WOOLY_BOMB, 10, 13);
        assignItemRect(NOISEMAKER, 10, 13);
        assignItemRect(ARCANE_BOMB, 10, 13);
        assignItemRect(SHRAPNEL_BOMB, 10, 13);
    }
    //endregion


    //region 近战武器
    public static final int WORN_SHORTSWORD = xy(1, 7);   //8 slots
    public static final int DAGGER = WORN_SHORTSWORD + 1;
    public static final int GLOVES = WORN_SHORTSWORD + 2;
    public static final int RAPIER = WORN_SHORTSWORD + 3;
    public static final int CUDGEL = WORN_SHORTSWORD + 4;
    public static final int 镜刃 = WORN_SHORTSWORD + 5;
    public static final int 铜钱剑 = WORN_SHORTSWORD + 6;
    public static final int 白带 = WORN_SHORTSWORD + 7;
    public static final int 矛盾 = WORN_SHORTSWORD + 8;
    public static final int 臂铠 = WORN_SHORTSWORD + 9;
    public static final int 碧蓝巨剑 = WORN_SHORTSWORD + 10;
    public static final int 灵鞭 = WORN_SHORTSWORD + 11;
    public static final int 血姬 = WORN_SHORTSWORD + 12;
    public static final int 书包 = WORN_SHORTSWORD + 13;
    public static final int 吸血飞刀 = WORN_SHORTSWORD + 14;

    static {
        assignItemRect(WORN_SHORTSWORD, 13);
        assignItemRect(DAGGER);
        assignItemRect(GLOVES, 16);
        assignItemRect(RAPIER, 16);
        assignItemRect(CUDGEL, 15);
        assignItemRect(镜刃, 15, 16);
        assignItemRect(铜钱剑, 15);
        assignItemRect(白带, 15,16);

        assignItemRect(矛盾, 16);
        assignItemRect(臂铠, 16);

        assignItemRect(碧蓝巨剑, 16);
        assignItemRect(灵鞭, 14);
        assignItemRect(血姬, 14);
        assignItemRect(灵鞭, 15,14);
        assignItemRect(书包, 14,16);
        assignItemRect(吸血飞刀,11);

    }


    private static final int WEP_TIER2 = xy(1, 8);   //8 slots
    public static final int SHORTSWORD = WEP_TIER2 + 0;
    public static final int HAND_AXE = WEP_TIER2 + 1;
    public static final int SPEAR = WEP_TIER2 + 2;
    public static final int QUARTERSTAFF = WEP_TIER2 + 3;
    public static final int DIRK = WEP_TIER2 + 4;
    public static final int SICKLE = WEP_TIER2 + 5;
    public static final int 爪 = WEP_TIER2 + 6;

    static {
        assignItemRect(SHORTSWORD, 13);
        assignItemRect(HAND_AXE, 12, 14);
        assignItemRect(SPEAR);
        assignItemRect(QUARTERSTAFF);
        assignItemRect(DIRK, 13, 14);
        assignItemRect(SICKLE, 15);
        assignItemRect(爪, 16);
    }

    private static final int WEP_TIER3 = xy(1, 9);   //8 slots
    public static final int SWORD = WEP_TIER3 + 0;
    public static final int MACE = WEP_TIER3 + 1;
    public static final int SCIMITAR = WEP_TIER3 + 2;
    public static final int ROUND_SHIELD = WEP_TIER3 + 3;
    public static final int SAI = WEP_TIER3 + 4;
    public static final int WHIP = WEP_TIER3 + 5;
    public static final int 半月刃 = WEP_TIER3 + 6;

    static {
        assignItemRect(SWORD, 14);
        assignItemRect(MACE, 15);
        assignItemRect(SCIMITAR, 13, 16);
        assignItemRect(ROUND_SHIELD);
        assignItemRect(SAI);
        assignItemRect(WHIP, 14);
        assignItemRect(半月刃, 15);
    }

    private static final int WEP_TIER4 = xy(1, 10);   //8 slots
    public static final int LONGSWORD = WEP_TIER4 + 0;
    public static final int BATTLE_AXE = WEP_TIER4 + 1;
    public static final int FLAIL = WEP_TIER4 + 2;
    public static final int RUNIC_BLADE = WEP_TIER4 + 3;
    public static final int ASSASSINS_BLADE = WEP_TIER4 + 4;
    public static final int CROSSBOW = WEP_TIER4 + 5;
    public static final int KATANA = WEP_TIER4 + 6;

    static {
        assignItemRect(LONGSWORD, 15);
        assignItemRect(BATTLE_AXE);
        assignItemRect(FLAIL, 14);
        assignItemRect(RUNIC_BLADE, 14);
        assignItemRect(ASSASSINS_BLADE, 14, 15);
        assignItemRect(CROSSBOW, 15);
        assignItemRect(KATANA, 15, 16);
    }

    private static final int WEP_TIER5 = xy(1, 11);   //8 slots
    public static final int GREATSWORD = WEP_TIER5 + 0;
    public static final int WAR_HAMMER = WEP_TIER5 + 1;
    public static final int GLAIVE = WEP_TIER5 + 2;
    public static final int 巨斧 = WEP_TIER5 + 3;
    public static final int GREATSHIELD = WEP_TIER5 + 4;
    public static final int GAUNTLETS = WEP_TIER5 + 5;
    public static final int WAR_SCYTHE = WEP_TIER5 + 6;

    static {
        assignItemRect(GREATSWORD);
        assignItemRect(WAR_HAMMER);
        assignItemRect(GLAIVE);
        assignItemRect(巨斧, 12, 16);
        assignItemRect(GREATSHIELD, 12, 16);
        assignItemRect(GAUNTLETS, 16, 15);
        assignItemRect(WAR_SCYTHE, 14, 16);
    }
    //endregion

    //region 特殊和传说武器
    private static final int x = xy(1, 12);
    public static final int 法师魔杖 = x;
    public static final int 灵能短弓 = x + 1;
    public static final int 血砍刀 = x + 2;
    public static final int 灵月法杖 = x + 3;
    public static final int 冰门重盾 = x + 4;
    public static final int 修理扳手=x+5;
    public static final int 金玫苦无 = x + 6;
    public static final int 简易驽 = x + 7;
    public static final int 手枪 = x + 8;

    static {

        assignItemRect(法师魔杖);
        assignItemRect(灵能短弓, 16);

        assignItemRect(血砍刀, 16);
        assignItemRect(灵月法杖, 16);

        assignItemRect(冰门重盾, 15, 16);

        assignItemRect(修理扳手,16);
        assignItemRect(金玫苦无, 15, 16);
        assignItemRect(简易驽, 7, 11);

        assignItemRect(手枪, 13, 7);
    }

    public static final int 英雄断剑 = xy(1, 13);  //16 slots
    public static final int 草剃 = 英雄断剑+1;
    public static final int 死神镰刀 = 英雄断剑+2;
    public static final int 碎缘剑 = 英雄断剑+3;
    public static final int 臻冰刃 = 英雄断剑+4;
    public static final int 神农锄 = 英雄断剑+5;
    public static final int 黄金雷鞭 = 英雄断剑+6;
    public static final int 黄金冰镖 = 英雄断剑+7;
    public static final int 黄金地镰 = 英雄断剑+8;
    public static final int 黄金火剑 = 英雄断剑+9;
    public static final int 流火 = 英雄断剑+10;
    static {
        assignItemRect(英雄断剑, 12, 13);
        assignItemRect(草剃, 15, 16);
        assignItemRect(死神镰刀, 16);
        assignItemRect(碎缘剑, 15, 16);
        assignItemRect(臻冰刃, 15, 16);
        assignItemRect(神农锄, 14, 16);
        assignItemRect(黄金雷鞭, 15, 16);
        assignItemRect(黄金冰镖, 16);
        assignItemRect(黄金地镰, 15, 16);
        assignItemRect(黄金火剑, 15, 16);
        assignItemRect(流火, 16);

    }
    //endregion

    //region 护甲
    private static final int ARMOR = xy(1, 14);  //16 slots
    public static final int ARMOR_CLOTH = ARMOR + 0;
    public static final int ARMOR_LEATHER = ARMOR + 1;
    public static final int ARMOR_MAIL = ARMOR + 2;
    public static final int ARMOR_SCALE = ARMOR + 3;
    public static final int ARMOR_PLATE = ARMOR + 4;
    public static final int ARMOR_WARRIOR = ARMOR + 5;
    public static final int ARMOR_MAGE = ARMOR + 6;
    public static final int ARMOR_ROGUE = ARMOR + 7;
    public static final int ARMOR_HUNTRESS = ARMOR + 8;
    public static final int ARMOR_DUELIST = ARMOR + 9;
    public static final int ARMOR_CLERIC = ARMOR + 10;
    public static final int 巫服 = ARMOR + 11;
    public static final int 武服 = ARMOR + 12;
    public static final int 道袍 = ARMOR+13;
    public static final int 战甲 = ARMOR + 14;
    
    public static final int 忍服 = ARMOR + 15;
    public static final int 能袍 = ARMOR + 16;
    public static final int 勇装 = ARMOR + 17;
    public static final int 连裙 = ARMOR + 18;
    
    public static final int 训服 = ARMOR + 19;
    public static final int 背心 = ARMOR + 20;
    public static final int 魔披 = ARMOR + 21;

    static {
        assignItemRect(ARMOR_CLOTH, 15, 12);
        assignItemRect(ARMOR_LEATHER, 14, 13);
        assignItemRect(ARMOR_MAIL, 14, 12);
        assignItemRect(ARMOR_SCALE, 14, 11);
        assignItemRect(ARMOR_PLATE, 12);
        assignItemRect(ARMOR_WARRIOR, 12);
        assignItemRect(ARMOR_MAGE, 15);
        assignItemRect(ARMOR_ROGUE, 14, 13);
        assignItemRect(ARMOR_HUNTRESS, 13, 15);
        assignItemRect(ARMOR_DUELIST, 12, 13);
        assignItemRect(ARMOR_CLERIC, 13, 14);
        assignItemRect(巫服, 15, 14);
        assignItemRect(武服, 13, 16);
        assignItemRect(道袍,15);
        assignItemRect(战甲, 12);
        assignItemRect(忍服, 14,13);
        assignItemRect(能袍, 15);
        assignItemRect(勇装, 14,12);
        assignItemRect(连裙, 16);
        assignItemRect(训服, 14,12);
        assignItemRect(背心, 16,14);
        assignItemRect(魔披, 13,12);
    }
    //endregion

    //region 法杖戒指
    private static final int WANDS = xy(1, 15);  //16 slots
    public static final int WAND_MAGIC_MISSILE = WANDS + 0;
    public static final int 焰浪法杖 = WANDS + 1;
    public static final int WAND_FROST = WANDS + 2;
    public static final int WAND_LIGHTNING = WANDS + 3;
    public static final int WAND_DISINTEGRATION = WANDS + 4;
    public static final int WAND_PRISMATIC_LIGHT = WANDS + 5;
    public static final int WAND_CORROSION = WANDS + 6;
    public static final int WAND_LIVING_EARTH = WANDS + 7;
    public static final int WAND_BLAST_WAVE = WANDS + 8;
    public static final int WAND_CORRUPTION = WANDS + 9;
    public static final int WAND_WARDING = WANDS + 10;
    public static final int WAND_REGROWTH = WANDS + 11;
    public static final int WAND_TRANSFUSION = WANDS + 12;

    static {
        for (int i = WANDS; i < WANDS + 16; i++)
            assignItemRect(i, 14);
    }

    private static final int RINGS = xy(1, 16);  //16 slots
    public static final int RING_GARNET = RINGS + 0;
    public static final int RING_RUBY = RINGS + 1;
    public static final int RING_TOPAZ = RINGS + 2;
    public static final int RING_EMERALD = RINGS + 3;
    public static final int RING_ONYX = RINGS + 4;
    public static final int RING_OPAL = RINGS + 5;
    public static final int RING_TOURMALINE = RINGS + 6;
    public static final int RING_SAPPHIRE = RINGS + 7;
    public static final int RING_AMETHYST = RINGS + 8;
    public static final int RING_QUARTZ = RINGS + 9;
    public static final int RING_AGATE = RINGS + 10;
    public static final int RING_DIAMOND = RINGS + 11;

    static {
        for (int i = RINGS; i < RINGS + 16; i++)
            assignItemRect(i, 8, 10);
    }
    //endregion

    //region 神器饰物
    private static final int ARTIFACTS = xy(1, 17);  //24 slots
    public static final int ARTIFACT_CLOAK = ARTIFACTS + 0;
    public static final int ARTIFACT_ARMBAND = ARTIFACTS + 1;
    public static final int ARTIFACT_CAPE = ARTIFACTS + 2;
    public static final int ARTIFACT_TALISMAN = ARTIFACTS + 3;
    public static final int 时光沙漏 = ARTIFACTS + 4;
    public static final int ARTIFACT_TOOLKIT = ARTIFACTS + 5;
    public static final int ARTIFACT_SPELLBOOK = ARTIFACTS + 6;
    public static final int ARTIFACT_BEACON = ARTIFACTS + 7;
    public static final int ARTIFACT_CHAINS = ARTIFACTS + 8;
    public static final int ARTIFACT_HORN1 = ARTIFACTS + 9;
    public static final int ARTIFACT_HORN2 = ARTIFACTS + 10;
    public static final int ARTIFACT_HORN3 = ARTIFACTS + 11;
    public static final int ARTIFACT_HORN4 = ARTIFACTS + 12;
    public static final int ARTIFACT_CHALICE1 = ARTIFACTS + 13;
    public static final int ARTIFACT_CHALICE2 = ARTIFACTS + 14;
    public static final int ARTIFACT_CHALICE3 = ARTIFACTS + 15;
    public static final int ARTIFACT_SANDALS = ARTIFACTS + 16;
    public static final int ARTIFACT_SHOES = ARTIFACTS + 17;
    public static final int ARTIFACT_BOOTS = ARTIFACTS + 18;
    public static final int ARTIFACT_GREAVES = ARTIFACTS + 19;
    public static final int ARTIFACT_ROSE1 = ARTIFACTS + 20;
    public static final int ARTIFACT_ROSE2 = ARTIFACTS + 21;
    public static final int ARTIFACT_ROSE3 = ARTIFACTS + 22;
    public static final int 神圣法典 = ARTIFACTS + 23;

    static {
        assignItemRect(ARTIFACT_CLOAK, 9, 15);
        assignItemRect(ARTIFACT_ARMBAND, 16, 13);
        assignItemRect(ARTIFACT_CAPE, 16, 14);
        assignItemRect(ARTIFACT_TALISMAN, 15, 13);
        assignItemRect(时光沙漏, 13, 16);
        assignItemRect(ARTIFACT_TOOLKIT, 15, 13);
        assignItemRect(ARTIFACT_SPELLBOOK, 13, 16);
        assignItemRect(ARTIFACT_BEACON, 16);
        assignItemRect(ARTIFACT_CHAINS, 16);
        assignItemRect(ARTIFACT_HORN1, 15);
        assignItemRect(ARTIFACT_HORN2, 15);
        assignItemRect(ARTIFACT_HORN3, 15);
        assignItemRect(ARTIFACT_HORN4, 15);
        assignItemRect(ARTIFACT_CHALICE1, 12, 15);
        assignItemRect(ARTIFACT_CHALICE2, 12, 15);
        assignItemRect(ARTIFACT_CHALICE3, 12, 15);
        assignItemRect(ARTIFACT_SANDALS, 16,12);
        assignItemRect(ARTIFACT_SHOES, 16,12);
        assignItemRect(ARTIFACT_BOOTS, 16, 9);
        assignItemRect(ARTIFACT_GREAVES, 16, 14);
        assignItemRect(ARTIFACT_ROSE1, 14);
        assignItemRect(ARTIFACT_ROSE2, 14);
        assignItemRect(ARTIFACT_ROSE3, 14);
        assignItemRect(神圣法典, 14, 16);
    }

    public static final int 虫箭 = xy(1, 18);
    public static final int 虫箭2 = 虫箭+1;
    public static final int 虫箭3 = 虫箭+2;
    static {
        assignItemRect(虫箭,16);
        assignItemRect(虫箭2,16);
        assignItemRect(虫箭3,16);
    }
    private static final int TRINKETS = xy(1, 19);  //24 slots
    public static final int RAT_SKULL = TRINKETS + 0;
    public static final int PARCHMENT_SCRAP = TRINKETS + 1;
    public static final int PETRIFIED_SEED = TRINKETS + 2;
    public static final int EXOTIC_CRYSTALS = TRINKETS + 3;
    public static final int MOSSY_CLUMP = TRINKETS + 4;
    public static final int SUNDIAL = TRINKETS + 5;
    public static final int CLOVER = TRINKETS + 6;
    public static final int TRAP_MECHANISM = TRINKETS + 7;
    public static final int MIMIC_TOOTH = TRINKETS + 8;
    public static final int WONDROUS_RESIN = TRINKETS + 9;
    public static final int EYE_OF_NEWT = TRINKETS + 10;
    public static final int SALT_CUBE = TRINKETS + 11;
    public static final int BLOOD_VIAL = TRINKETS + 12;
    public static final int OBLIVION_SHARD = TRINKETS + 13;
    public static final int CHAOTIC_CENSER = TRINKETS + 14;
    public static final int FERRET_TUFT = TRINKETS + 15;

    static {
        assignItemRect(RAT_SKULL, 16, 11);
        assignItemRect(PARCHMENT_SCRAP, 10, 14);
        assignItemRect(PETRIFIED_SEED, 10);
        assignItemRect(EXOTIC_CRYSTALS, 14, 13);
        assignItemRect(MOSSY_CLUMP, 12, 11);
        assignItemRect(SUNDIAL, 16, 12);
        assignItemRect(CLOVER, 11, 15);
        assignItemRect(TRAP_MECHANISM, 13, 15);
        assignItemRect(MIMIC_TOOTH, 8, 15);
        assignItemRect(WONDROUS_RESIN, 12, 11);
        assignItemRect(EYE_OF_NEWT, 12);
        assignItemRect(SALT_CUBE, 12, 13);
        assignItemRect(BLOOD_VIAL, 6, 15);
        assignItemRect(OBLIVION_SHARD, 7, 14);
        assignItemRect(CHAOTIC_CENSER, 13, 15);
        assignItemRect(FERRET_TUFT, 16, 15);
    }
    //endregion

    //region 卷轴符石
    private static final int SCROLLS = xy(1, 20);  //16 slots
    public static final int SCROLL_KAUNAN = SCROLLS + 0;
    public static final int SCROLL_SOWILO = SCROLLS + 1;
    public static final int SCROLL_LAGUZ = SCROLLS + 2;
    public static final int SCROLL_YNGVI = SCROLLS + 3;
    public static final int SCROLL_GYFU = SCROLLS + 4;
    public static final int SCROLL_RAIDO = SCROLLS + 5;
    public static final int SCROLL_ISAZ = SCROLLS + 6;
    public static final int SCROLL_MANNAZ = SCROLLS + 7;
    public static final int SCROLL_NAUDIZ = SCROLLS + 8;
    public static final int SCROLL_BERKANAN = SCROLLS + 9;
    public static final int SCROLL_ODAL = SCROLLS + 10;
    public static final int SCROLL_TIWAZ = SCROLLS + 11;

    public static final int ARCANE_RESIN = SCROLLS + 13;

    static {
        for (int i = SCROLLS; i < SCROLLS + 16; i++)
            assignItemRect(i, 15, 14);
        assignItemRect(ARCANE_RESIN, 12, 11);
    }

    private static final int EXOTIC_SCROLLS = xy(1, 21);  //16 slots
    public static final int EXOTIC_KAUNAN = EXOTIC_SCROLLS + 0;
    public static final int EXOTIC_SOWILO = EXOTIC_SCROLLS + 1;
    public static final int EXOTIC_LAGUZ = EXOTIC_SCROLLS + 2;
    public static final int EXOTIC_YNGVI = EXOTIC_SCROLLS + 3;
    public static final int EXOTIC_GYFU = EXOTIC_SCROLLS + 4;
    public static final int EXOTIC_RAIDO = EXOTIC_SCROLLS + 5;
    public static final int EXOTIC_ISAZ = EXOTIC_SCROLLS + 6;
    public static final int EXOTIC_MANNAZ = EXOTIC_SCROLLS + 7;
    public static final int EXOTIC_NAUDIZ = EXOTIC_SCROLLS + 8;
    public static final int EXOTIC_BERKANAN = EXOTIC_SCROLLS + 9;
    public static final int EXOTIC_ODAL = EXOTIC_SCROLLS + 10;
    public static final int EXOTIC_TIWAZ = EXOTIC_SCROLLS + 11;

    static {
        for (int i = EXOTIC_SCROLLS; i < EXOTIC_SCROLLS + 16; i++)
            assignItemRect(i, 15, 14);
    }

    private static final int STONES = xy(1, 22);  //16 slots
    public static final int STONE_AGGRESSION = STONES + 0;
    public static final int STONE_AUGMENTATION = STONES + 1;
    public static final int STONE_FEAR = STONES + 2;
    public static final int STONE_BLAST = STONES + 3;
    public static final int STONE_BLINK = STONES + 4;
    public static final int STONE_CLAIRVOYANCE = STONES + 5;
    public static final int STONE_SLEEP = STONES + 6;
    public static final int STONE_DETECT = STONES + 7;
    public static final int STONE_ENCHANT = STONES + 8;
    public static final int STONE_FLOCK = STONES + 9;
    public static final int STONE_INTUITION = STONES + 10;
    public static final int STONE_SHOCK = STONES + 11;

    static {
        for (int i = STONES; i < STONES + 16; i++)
            assignItemRect(i, 14, 12);
    }
    //endregion

    //region 药剂种子
    private static final int POTIONS = xy(1, 23);  //16 slots
    public static final int POTION_CRIMSON = POTIONS + 0;
    public static final int POTION_AMBER = POTIONS + 1;
    public static final int POTION_GOLDEN = POTIONS + 2;
    public static final int POTION_JADE = POTIONS + 3;
    public static final int POTION_TURQUOISE = POTIONS + 4;
    public static final int POTION_AZURE = POTIONS + 5;
    public static final int POTION_INDIGO = POTIONS + 6;
    public static final int POTION_MAGENTA = POTIONS + 7;
    public static final int POTION_BISTRE = POTIONS + 8;
    public static final int POTION_CHARCOAL = POTIONS + 9;
    public static final int POTION_SILVER = POTIONS + 10;
    public static final int POTION_IVORY = POTIONS + 11;

    public static final int LIQUID_METAL = POTIONS + 13;

    static {
        for (int i = POTIONS; i < POTIONS + 16; i++)
            assignItemRect(i, 12, 14);
        assignItemRect(LIQUID_METAL, 8, 15);
    }

    private static final int EXOTIC_POTIONS = xy(1, 24);  //16 slots
    public static final int EXOTIC_CRIMSON = EXOTIC_POTIONS + 0;
    public static final int EXOTIC_AMBER = EXOTIC_POTIONS + 1;
    public static final int EXOTIC_GOLDEN = EXOTIC_POTIONS + 2;
    public static final int EXOTIC_JADE = EXOTIC_POTIONS + 3;
    public static final int EXOTIC_TURQUOISE = EXOTIC_POTIONS + 4;
    public static final int EXOTIC_AZURE = EXOTIC_POTIONS + 5;
    public static final int EXOTIC_INDIGO = EXOTIC_POTIONS + 6;
    public static final int EXOTIC_MAGENTA = EXOTIC_POTIONS + 7;
    public static final int EXOTIC_BISTRE = EXOTIC_POTIONS + 8;
    public static final int EXOTIC_CHARCOAL = EXOTIC_POTIONS + 9;
    public static final int EXOTIC_SILVER = EXOTIC_POTIONS + 10;
    public static final int EXOTIC_IVORY = EXOTIC_POTIONS + 11;

    static {
        for (int i = EXOTIC_POTIONS; i < EXOTIC_POTIONS + 16; i++)
            assignItemRect(i, 12, 13);
    }

    private static final int SEEDS = xy(1, 25);  //16 slots
    public static final int SEED_ROTBERRY = SEEDS + 0;
    public static final int SEED_FIREBLOOM = SEEDS + 1;
    public static final int SEED_SWIFTTHISTLE = SEEDS + 2;
    public static final int SEED_SUNGRASS = SEEDS + 3;
    public static final int SEED_ICECAP = SEEDS + 4;
    public static final int SEED_STORMVINE = SEEDS + 5;
    public static final int SEED_SORROWMOSS = SEEDS + 6;
    public static final int SEED_MAGEROYAL = SEEDS + 7;
    public static final int SEED_EARTHROOT = SEEDS + 8;
    public static final int SEED_STARFLOWER = SEEDS + 9;
    public static final int SEED_FADELEAF = SEEDS + 10;
    public static final int SEED_BLINDWEED = SEEDS + 11;

    static {
        for (int i = SEEDS; i < SEEDS + 16; i++)
            assignItemRect(i, 10);
    }
    //endregion

    //region 秘药结晶
    private static final int BREWS = xy(1, 26);  //8 slots
    public static final int BREW_INFERNAL = BREWS + 0;
    public static final int BREW_BLIZZARD = BREWS + 1;
    public static final int BREW_SHOCKING = BREWS + 2;
    public static final int BREW_CAUSTIC = BREWS + 3;
    public static final int BREW_AQUA = BREWS + 4;
    public static final int BREW_UNSTABLE = BREWS + 5;

    private static final int ELIXIRS = xy(9, 26);  //8 slots
    public static final int ELIXIR_HONEY = ELIXIRS + 0;
    public static final int ELIXIR_AQUA = ELIXIRS + 1;
    public static final int ELIXIR_MIGHT = ELIXIRS + 2;
    public static final int ELIXIR_DRAGON = ELIXIRS + 3;
    public static final int ELIXIR_TOXIC = ELIXIRS + 4;
    public static final int ELIXIR_ICY = ELIXIRS + 5;
    public static final int ELIXIR_ARCANE = ELIXIRS + 6;
    public static final int ELIXIR_FEATHER = ELIXIRS + 7;

    static {
        for (int i = BREWS; i < BREWS + 16; i++)
            assignItemRect(i, 12, 14);

        assignItemRect(BREW_AQUA, 9, 11);
    }

//16 free slots

    private static final int SPELLS = xy(1, 27);  //16 slots
    public static final int WILD_ENERGY = SPELLS + 0;
    public static final int PHASE_SHIFT = SPELLS + 1;
    public static final int TELE_GRAB = SPELLS + 2;
    public static final int UNSTABLE_SPELL = SPELLS + 3;

    public static final int CURSE_INFUSE = SPELLS + 5;
    public static final int MAGIC_INFUSE = SPELLS + 6;
    public static final int ALCHEMIZE = SPELLS + 7;
    public static final int RECYCLE = SPELLS + 8;

    public static final int RECLAIM_TRAP = SPELLS + 10;
    public static final int RETURN_BEACON = SPELLS + 11;
    public static final int SUMMON_ELE = SPELLS + 12;

    static {
        assignItemRect(WILD_ENERGY, 12, 11);
        assignItemRect(PHASE_SHIFT, 12, 11);
        assignItemRect(TELE_GRAB, 12, 11);
        assignItemRect(UNSTABLE_SPELL, 12, 13);

        assignItemRect(CURSE_INFUSE, 10, 15);
        assignItemRect(MAGIC_INFUSE, 10, 15);
        assignItemRect(ALCHEMIZE, 10, 15);
        assignItemRect(RECYCLE, 10, 15);

        assignItemRect(RECLAIM_TRAP, 8, 16);
        assignItemRect(RETURN_BEACON, 8, 16);
        assignItemRect(SUMMON_ELE, 8, 16);
    }
    //endregion

    //region 食物节日
    private static final int FOOD = xy(1, 28);  //16 slots
    public static final int MEAT = FOOD + 0;
    public static final int STEAK = FOOD + 1;
    public static final int STEWED = FOOD + 2;
    public static final int OVERPRICED = FOOD + 3;
    public static final int CARPACCIO = FOOD + 4;
    public static final int RATION = FOOD + 5;
    public static final int PASTY = FOOD + 6;
    public static final int MEAT_PIE = FOOD + 7;
    public static final int BLANDFRUIT = FOOD + 8;
    public static final int BLAND_CHUNKS = FOOD + 9;
    public static final int 地牢浆果 = FOOD + 10;
    public static final int PHANTOM_MEAT = FOOD + 11;
    public static final int SUPPLY_RATION = FOOD + 12;

    static {
        assignItemRect(MEAT, 15, 11);
        assignItemRect(STEAK, 15, 11);
        assignItemRect(STEWED, 15, 11);
        assignItemRect(OVERPRICED, 14, 11);
        assignItemRect(CARPACCIO, 15, 11);
        assignItemRect(RATION, 16, 12);
        assignItemRect(PASTY, 16, 11);
        assignItemRect(MEAT_PIE, 16, 12);
        assignItemRect(BLANDFRUIT, 9, 12);
        assignItemRect(BLAND_CHUNKS, 14, 6);
        assignItemRect(地牢浆果, 9, 11);
        assignItemRect(PHANTOM_MEAT, 15, 11);
        assignItemRect(SUPPLY_RATION, 16, 12);
    }

    private static final int HOLIDAY_FOOD = xy(1, 29);  //16 slots
    public static final int STEAMED_FISH = HOLIDAY_FOOD + 0;
    public static final int FISH_LEFTOVER = HOLIDAY_FOOD + 1;
    public static final int CHOC_AMULET = HOLIDAY_FOOD + 2;
    public static final int EASTER_EGG = HOLIDAY_FOOD + 3;
    public static final int RAINBOW_POTION = HOLIDAY_FOOD + 4;
    public static final int SHATTERED_CAKE = HOLIDAY_FOOD + 5;
    public static final int PUMPKIN_PIE = HOLIDAY_FOOD + 6;
    public static final int VANILLA_CAKE = HOLIDAY_FOOD + 7;
    public static final int CANDY_CANE = HOLIDAY_FOOD + 8;
    public static final int SPARKLING_POTION = HOLIDAY_FOOD + 9;
    public static final int 单身狗粮 = HOLIDAY_FOOD + 10;

    static {
        assignItemRect(STEAMED_FISH, 16, 12);
        assignItemRect(FISH_LEFTOVER, 16, 12);
        assignItemRect(CHOC_AMULET, 16);
        assignItemRect(EASTER_EGG, 12, 14);
        assignItemRect(RAINBOW_POTION, 12, 14);
        assignItemRect(SHATTERED_CAKE, 14, 13);
        assignItemRect(PUMPKIN_PIE, 16, 12);
        assignItemRect(VANILLA_CAKE, 14, 13);
        assignItemRect(CANDY_CANE, 13, 16);
        assignItemRect(SPARKLING_POTION, 7, 16);
        assignItemRect(单身狗粮, 12, 14);
    }
    //endregion

    //region 任务背包日志
    private static final int QUEST = xy(1, 30);  //16 slots
    public static final int 尸尘 = QUEST;
    public static final int CANDLE = QUEST + 1;
    public static final int EMBER = QUEST + 2;
    public static final int PICKAXE = QUEST + 3;
    public static final int ORE = QUEST + 4;
    public static final int TOKEN = QUEST + 5;
    public static final int BLOB = QUEST + 6;
    public static final int SHARD = QUEST + 7;

    static {
        assignItemRect(尸尘, 12, 11);
        assignItemRect(CANDLE, 12);
        assignItemRect(EMBER, 12, 11);
        assignItemRect(PICKAXE, 14);
        assignItemRect(ORE, 15);
        assignItemRect(TOKEN, 12);
        assignItemRect(BLOB, 10, 9);
        assignItemRect(SHARD, 8, 10);
    }
    private static final int BAGS = xy(1, 31);  //16 slots
    public static final int BACKPACK = BAGS;
    public static final int 绒布袋 = BAGS + 1;
    public static final int HOLDER = BAGS + 2;
    public static final int BANDOLIER = BAGS + 3;
    public static final int HOLSTER = BAGS + 4;
    public static final int 杂物袋 = BAGS + 5;

    public static final int 水袋 = BAGS + 6;
    public static final int VIAL = BAGS + 7;

    static {
        assignItemRect(BACKPACK, 16);
        assignItemRect(绒布袋, 14, 15);
        assignItemRect(HOLDER, 16);
        assignItemRect(BANDOLIER, 15, 16);
        assignItemRect(HOLSTER, 15, 16);
        assignItemRect(杂物袋, 13, 14);

        assignItemRect(水袋, 16, 14);
        assignItemRect(VIAL, 12);
    }
    private static final int DOCUMENTS = xy(1, 32);  //16 slots
    public static final int GUIDE_PAGE = DOCUMENTS + 0;
    public static final int ALCH_PAGE = DOCUMENTS + 1;
    public static final int SEWER_PAGE = DOCUMENTS + 2;
    public static final int PRISON_PAGE = DOCUMENTS + 3;
    public static final int CAVES_PAGE = DOCUMENTS + 4;
    public static final int CITY_PAGE = DOCUMENTS + 5;
    public static final int HALLS_PAGE = DOCUMENTS + 6;
    public static final int JANE_PAGE = DOCUMENTS + 7;

    static {
        assignItemRect(GUIDE_PAGE, 10, 11);
        assignItemRect(ALCH_PAGE, 10, 11);
        assignItemRect(SEWER_PAGE, 10, 11);
        assignItemRect(PRISON_PAGE, 10, 11);
        assignItemRect(CAVES_PAGE, 10, 11);
        assignItemRect(CITY_PAGE, 10, 11);
        assignItemRect(HALLS_PAGE, 10, 11);
        assignItemRect(JANE_PAGE, 10, 11);
    }
    //endregion

    //region 投掷
    public static final int THROWING_SPIKE = xy(1, 33);//1
    public static final int THROWING_KNIFE = THROWING_SPIKE + 1;
    public static final int THROWING_STONE = THROWING_SPIKE + 2;
    public static final int 雪球=THROWING_SPIKE+3;


    static {
        assignItemRect(THROWING_SPIKE, 11, 10);
        assignItemRect(THROWING_KNIFE, 11);
        assignItemRect(THROWING_STONE, 12, 10);
        assignItemRect(雪球,7,7);
    }

    public static final int FISHING_SPEAR = xy(1, 34);//2
    public static final int SHURIKEN = FISHING_SPEAR + 1;
    public static final int THROWING_CLUB = FISHING_SPEAR + 2;
    public static final int 投骨 = FISHING_SPEAR + 3;

    static {
        assignItemRect(FISHING_SPEAR, 11);
        assignItemRect(SHURIKEN, 12);
        assignItemRect(THROWING_CLUB, 12);
        assignItemRect(投骨, 10);
    }

    public static final int THROWING_SPEAR = xy(1, 35);//3
    public static final int BOLAS = THROWING_SPEAR + 1;
    public static final int KUNAI = THROWING_SPEAR + 2;
    public static final int 回旋镖 = THROWING_SPEAR + 3;

    static {

        assignItemRect(THROWING_SPEAR, 13);

        assignItemRect(BOLAS, 15, 14);

        assignItemRect(KUNAI, 15);
        assignItemRect(回旋镖, 9,16);
    }

    public static final int JAVELIN = xy(1, 36);//4
    public static final int TOMAHAWK = JAVELIN + 1;
    public static final int BOOMERANG = JAVELIN + 2;
    public static final int 毒性飞刀 = JAVELIN + 3;

    static {
        assignItemRect(JAVELIN);

        assignItemRect(TOMAHAWK, 13);

        assignItemRect(BOOMERANG, 14);
        assignItemRect(毒性飞刀, 7,14);
    }

    public static final int TRIDENT = xy(1, 37);//5
    public static final int THROWING_HAMMER = TRIDENT + 1;
    public static final int FORCE_CUBE = TRIDENT + 2;
    public static final int 星刺=TRIDENT+3;

    static {
        assignItemRect(TRIDENT);
        assignItemRect(THROWING_HAMMER, 12);
        assignItemRect(FORCE_CUBE, 11, 12);
        assignItemRect(星刺,11);
    }
    //endregion

    //region 飞镖
    public static final int DARTS = xy(1, 38);  //16 slots
    public static final int DART = DARTS + 0;
    public static final int ROT_DART = DARTS + 1;
    public static final int INCENDIARY_DART = DARTS + 2;
    public static final int ADRENALINE_DART = DARTS + 3;
    public static final int HEALING_DART = DARTS + 4;
    public static final int CHILLING_DART = DARTS + 5;
    public static final int SHOCKING_DART = DARTS + 6;
    public static final int POISON_DART = DARTS + 7;
    public static final int CLEANSING_DART = DARTS + 8;
    public static final int PARALYTIC_DART = DARTS + 9;
    public static final int HOLY_DART = DARTS + 10;
    public static final int DISPLACING_DART = DARTS + 11;
    public static final int BLINDING_DART = DARTS + 12;

    static {
        for (int i = DARTS; i < DARTS + 16; i++)
            assignItemRect(i, 15);
    }
    //endregion

    //region 8x8 Icon
    public static class Icons {

        private static final int WIDTH = 16;
        public static final int SIZE = 8;

        public static TextureFilm film = new TextureFilm(Assets.Sprites.ITEM_ICONS, SIZE, SIZE);

        private static int xy(int x, int y) {
            x -= 1;
            y -= 1;
            return x + WIDTH * y;
        }

        private static void assignIconRect(int item, int wh) {
            int x = (item % WIDTH) * SIZE;
            int y = (item / WIDTH) * SIZE;
            film.add(item, x, y, x + wh, y + wh);
        }

        private static void assignIconRect(int item, int width, int height) {
            int x = (item % WIDTH) * SIZE;
            int y = (item / WIDTH) * SIZE;
            film.add(item, x, y, x + width, y + height);
        }

        private static final int RINGS = xy(1, 1);  //16 slots
        public static final int RING_ACCURACY = RINGS + 0;
        public static final int RING_ARCANA = RINGS + 1;
        public static final int RING_ELEMENTS = RINGS + 2;
        public static final int RING_ENERGY = RINGS + 3;
        public static final int RING_EVASION = RINGS + 4;
        public static final int RING_FORCE = RINGS + 5;
        public static final int RING_FUROR = RINGS + 6;
        public static final int RING_HASTE = RINGS + 7;
        public static final int RING_MIGHT = RINGS + 8;
        public static final int RING_SHARPSHOOT = RINGS + 9;
        public static final int RING_TENACITY = RINGS + 10;
        public static final int RING_WEALTH = RINGS + 11;

        static {
            assignIconRect(RING_ACCURACY, 7);
            assignIconRect(RING_ARCANA, 7);
            assignIconRect(RING_ELEMENTS, 7);
            assignIconRect(RING_ENERGY, 7, 5);
            assignIconRect(RING_EVASION, 7);
            assignIconRect(RING_FORCE, 5, 6);
            assignIconRect(RING_FUROR, 7, 6);
            assignIconRect(RING_HASTE, 6);
            assignIconRect(RING_MIGHT, 7);
            assignIconRect(RING_SHARPSHOOT, 7);
            assignIconRect(RING_TENACITY, 6);
            assignIconRect(RING_WEALTH, 7, 6);
        }

        //16 free slots

        private static final int SCROLLS = xy(1, 3);  //16 slots
        public static final int SCROLL_UPGRADE = SCROLLS + 0;
        public static final int SCROLL_IDENTIFY = SCROLLS + 1;
        public static final int SCROLL_REMCURSE = SCROLLS + 2;
        public static final int SCROLL_MIRRORIMG = SCROLLS + 3;
        public static final int SCROLL_RECHARGE = SCROLLS + 4;
        public static final int SCROLL_TELEPORT = SCROLLS + 5;
        public static final int SCROLL_LULLABY = SCROLLS + 6;
        public static final int SCROLL_MAGICMAP = SCROLLS + 7;
        public static final int SCROLL_RAGE = SCROLLS + 8;
        public static final int SCROLL_RETRIB = SCROLLS + 9;
        public static final int SCROLL_TERROR = SCROLLS + 10;
        public static final int SCROLL_TRANSMUTE = SCROLLS + 11;

        static {
            assignIconRect(SCROLL_UPGRADE, 7);
            assignIconRect(SCROLL_IDENTIFY, 4, 7);
            assignIconRect(SCROLL_REMCURSE, 7);
            assignIconRect(SCROLL_MIRRORIMG, 7, 5);
            assignIconRect(SCROLL_RECHARGE, 7, 5);
            assignIconRect(SCROLL_TELEPORT, 7);
            assignIconRect(SCROLL_LULLABY, 7, 6);
            assignIconRect(SCROLL_MAGICMAP, 7);
            assignIconRect(SCROLL_RAGE, 6);
            assignIconRect(SCROLL_RETRIB, 5, 6);
            assignIconRect(SCROLL_TERROR, 5, 7);
            assignIconRect(SCROLL_TRANSMUTE, 7);
        }

        private static final int EXOTIC_SCROLLS = xy(1, 4);  //16 slots
        public static final int SCROLL_ENCHANT = EXOTIC_SCROLLS + 0;
        public static final int SCROLL_DIVINATE = EXOTIC_SCROLLS + 1;
        public static final int SCROLL_ANTIMAGIC = EXOTIC_SCROLLS + 2;
        public static final int SCROLL_PRISIMG = EXOTIC_SCROLLS + 3;
        public static final int SCROLL_MYSTENRG = EXOTIC_SCROLLS + 4;
        public static final int SCROLL_PASSAGE = EXOTIC_SCROLLS + 5;
        public static final int SCROLL_SIREN = EXOTIC_SCROLLS + 6;
        public static final int SCROLL_FORESIGHT = EXOTIC_SCROLLS + 7;
        public static final int SCROLL_CHALLENGE = EXOTIC_SCROLLS + 8;
        public static final int SCROLL_PSIBLAST = EXOTIC_SCROLLS + 9;
        public static final int SCROLL_DREAD = EXOTIC_SCROLLS + 10;
        public static final int SCROLL_METAMORPH = EXOTIC_SCROLLS + 11;

        static {
            assignIconRect(SCROLL_ENCHANT, 7);
            assignIconRect(SCROLL_DIVINATE, 7, 6);
            assignIconRect(SCROLL_ANTIMAGIC, 7);
            assignIconRect(SCROLL_PRISIMG, 5, 7);
            assignIconRect(SCROLL_MYSTENRG, 7, 5);
            assignIconRect(SCROLL_PASSAGE, 5, 7);
            assignIconRect(SCROLL_SIREN, 7, 6);
            assignIconRect(SCROLL_FORESIGHT, 7, 5);
            assignIconRect(SCROLL_CHALLENGE, 7);
            assignIconRect(SCROLL_PSIBLAST, 5, 6);
            assignIconRect(SCROLL_DREAD, 5, 7);
            assignIconRect(SCROLL_METAMORPH, 7);
        }

        //16 free slots

        private static final int POTIONS = xy(1, 6);  //16 slots
        public static final int POTION_STRENGTH = POTIONS + 0;
        public static final int POTION_HEALING = POTIONS + 1;
        public static final int POTION_MINDVIS = POTIONS + 2;
        public static final int POTION_FROST = POTIONS + 3;
        public static final int POTION_LIQFLAME = POTIONS + 4;
        public static final int POTION_TOXICGAS = POTIONS + 5;
        public static final int POTION_HASTE = POTIONS + 6;
        public static final int POTION_INVIS = POTIONS + 7;
        public static final int POTION_LEVITATE = POTIONS + 8;
        public static final int POTION_PARAGAS = POTIONS + 9;
        public static final int POTION_PURITY = POTIONS + 10;
        public static final int POTION_EXP = POTIONS + 11;

        static {
            assignIconRect(POTION_STRENGTH, 7);
            assignIconRect(POTION_HEALING, 6, 7);
            assignIconRect(POTION_MINDVIS, 7, 5);
            assignIconRect(POTION_FROST, 7);
            assignIconRect(POTION_LIQFLAME, 5, 7);
            assignIconRect(POTION_TOXICGAS, 7);
            assignIconRect(POTION_HASTE, 6);
            assignIconRect(POTION_INVIS, 5, 7);
            assignIconRect(POTION_LEVITATE, 6, 7);
            assignIconRect(POTION_PARAGAS, 7);
            assignIconRect(POTION_PURITY, 5, 7);
            assignIconRect(POTION_EXP, 7);
        }

        private static final int EXOTIC_POTIONS = xy(1, 7);  //16 slots
        public static final int POTION_MASTERY = EXOTIC_POTIONS + 0;
        public static final int POTION_SHIELDING = EXOTIC_POTIONS + 1;
        public static final int POTION_MAGISIGHT = EXOTIC_POTIONS + 2;
        public static final int POTION_SNAPFREEZ = EXOTIC_POTIONS + 3;
        public static final int POTION_DRGBREATH = EXOTIC_POTIONS + 4;
        public static final int POTION_CORROGAS = EXOTIC_POTIONS + 5;
        public static final int POTION_STAMINA = EXOTIC_POTIONS + 6;
        public static final int POTION_SHROUDFOG = EXOTIC_POTIONS + 7;
        public static final int POTION_STRMCLOUD = EXOTIC_POTIONS + 8;
        public static final int POTION_EARTHARMR = EXOTIC_POTIONS + 9;
        public static final int POTION_CLEANSE = EXOTIC_POTIONS + 10;
        public static final int POTION_DIVINE = EXOTIC_POTIONS + 11;

        static {
            assignIconRect(POTION_MASTERY, 7);
            assignIconRect(POTION_SHIELDING, 6);
            assignIconRect(POTION_MAGISIGHT, 7, 5);
            assignIconRect(POTION_SNAPFREEZ, 7);
            assignIconRect(POTION_DRGBREATH, 7);
            assignIconRect(POTION_CORROGAS, 7);
            assignIconRect(POTION_STAMINA, 6);
            assignIconRect(POTION_SHROUDFOG, 7);
            assignIconRect(POTION_STRMCLOUD, 7);
            assignIconRect(POTION_EARTHARMR, 6);
            assignIconRect(POTION_CLEANSE, 7);
            assignIconRect(POTION_DIVINE, 7);
        }

        //16 free slots

    }
    //endregion
}
