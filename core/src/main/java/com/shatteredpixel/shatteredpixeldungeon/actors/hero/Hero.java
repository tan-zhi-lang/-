

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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Awareness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.替身保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.死舞;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.灵魂标记;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.白猫保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.连击;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.隔天休息;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.ElementalStrike;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.赐福;
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
import com.shatteredpixel.shatteredpixeldungeon.actors.专注;
import com.shatteredpixel.shatteredpixeldungeon.actors.伤害;
import com.shatteredpixel.shatteredpixeldungeon.actors.冥想;
import com.shatteredpixel.shatteredpixeldungeon.actors.外交豁免;
import com.shatteredpixel.shatteredpixeldungeon.actors.快炖;
import com.shatteredpixel.shatteredpixeldungeon.actors.慢炖;
import com.shatteredpixel.shatteredpixeldungeon.actors.战斗状态;
import com.shatteredpixel.shatteredpixeldungeon.actors.无终恨意冷却;
import com.shatteredpixel.shatteredpixeldungeon.actors.星蚀;
import com.shatteredpixel.shatteredpixeldungeon.actors.星蚀冷却;
import com.shatteredpixel.shatteredpixeldungeon.actors.疾射火炮;
import com.shatteredpixel.shatteredpixeldungeon.actors.被发现;
import com.shatteredpixel.shatteredpixeldungeon.actors.踢踏舞;
import com.shatteredpixel.shatteredpixeldungeon.actors.连杀状态;
import com.shatteredpixel.shatteredpixeldungeon.actors.鬼刀;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
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
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap.Type;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ScaleArmor;
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
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.九龙针筒;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.召唤物品;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.荆棘斗篷;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.骷髅钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.journal.Guidebook;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.CrystalKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.Key;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.磨损钥匙;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfFeatherFall;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfDivineInspiration;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.力量药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.极速药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.治疗药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.经验药剂;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.六神之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.命中之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.幸运之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.恢复之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.根骨之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.狂怒之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.疾速之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.闪避之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfSirensSong;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.蜕变秘卷;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.充能卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.升级卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.恐惧卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.探地卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.EyeOfNewt;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.ThirteenLeafClover;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.中国国旗;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.传奇肛塞;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.巨大蟹钳;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.火毒箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.狂妄皇冠;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.男人国徽章;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.真正护符;
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
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.魔法箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.修理扳手;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.变态刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.大肉棒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.闪电双截棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.坠牢之星;
import com.shatteredpixel.shatteredpixeldungeon.items.属性锻造器;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.items.海克斯宝典;
import com.shatteredpixel.shatteredpixeldungeon.items.红包;
import com.shatteredpixel.shatteredpixeldungeon.items.超级升级符石;
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
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
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
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择物品;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.系统设置;
import com.shatteredpixel.shatteredpixeldungeon.解压设置;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.Delayer;
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
import java.util.List;

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

    public static LinkedHashMap<String, Boolean> 海克斯1=new LinkedHashMap<String, Boolean>(){};

    public static LinkedHashMap<String, Boolean> 海克斯2=new LinkedHashMap<String, Boolean>(){};

    public static LinkedHashMap<String, Boolean> 海克斯3=new LinkedHashMap<String, Boolean>(){};
    public static void put1(String s,boolean b){
        海克斯1.put(s,b);
    }
    public static void put2(String s,boolean b){
        海克斯2.put(s,b);
    }
    public static void put3(String s,boolean b){
        海克斯3.put(s,b);
    }
    public static void 海克斯重置(){
        {
            put1("升级综合属性",false);
            put1("缩小射线",false);
            put1("药剂赌狗",false);
            put1("物品赌狗",false);
            put1("卷轴赌狗",false);
            put1("鬼刀一开看不见",false);
            put1("先攻",false);
            put1("装备死亡之舞",false);
            put1("屠夫",false);
            put1("血气方刚",false);
            put1("升级死神镰刀",false);
            put1("电能震荡",false);
            put1("升级破碎像素地牢",false);
            put1("专注",false);
            put1("骰子收集者",false);
            put1("升级全能吸血",false);
            put1("吸吸物质为俊杰",false);
            put1("力速双A替身",false);
            put1("小怪猎手",false);
            put1("猛攻",false);
            put1("风语者的祝福",false);
            put1("残暴之力",false);
            put1("唯快不破",false);
            put1("什么都没有",false);
            put1("橡皮长枪手",false);
            put1("佯攻",false);
            put1("捐赠",false);
            put1("接二连三",false);
            put1("珠光护手",false);
            put1("易损",false);
            put1("嗜血",false);
            put1("虚幻武器",false);
            put1("左拳伤害高右拳高伤害",false);
            put1("会心防御",false);
            put1("会心治疗",false);
            put1("会心闪避",false);
            put1("穿甲的宠爱",false);
            put1("远视人启动",false);
            put1("安内",false);
            put1("乌龟",false);
            put1("失败的Man",false);
            put1("止戈",false);
            put1("末法",false);
            put1("力量的爆发",false);
            put1("速度的承载",false);
            put1("战场Boss",false);
            put1("树懒转世",false);
            put1("升级荆棘斗篷",false);
            put1("走完红色路线",false);
            put1("升级蓄血圣杯",false);
            put1("升级丰饶之角",false);
            put1("升级法师魔杖",false);
            put1("铁布衫",false);
            put1("龙鳞甲",false);
            put1("升级荣耀纹章",false);
            put1("升级暗杀之刃",false);
            put1("升级饮血之剑",false);
            put1("升级破败王剑",false);
            put1("升级腥红散华",false);
            put1("升级无影剑",false);
            put1("急救用具",false);
            put1("一板一眼",false);
            put1("暴风吸入",false);
            put1("神圣的宠爱",false);
            put1("灵巧",false);
            put1("装备黄金之心",false);
            put1("星界躯体",false);
            put1("瞄准镜",false);
            put1("虚空之眼",false);
            put1("升级变态刀",false);
            put1("升级无尽之刃",false);
            put1("升级自然之履",false);
            put1("纪元",false);
            put1("饭桶",false);
            put1("鉴定的宠爱",false);
            put1("祛邪的宠爱",false);
            put1("最终祝福",false);
            put1("浪客之道",false);
            put1("灵魂虹吸",false);
            put1("由暴生急",false);
            put1("交锋",false);
            put1("钢铁意志",false);
            put1("战斗狂怒",false);
            put1("魔法转物理",false);
            put1("点亮他们",false);
            put1("尖端发明家",false);
            put1("死神",false);
            put1("我有一剑",false);
            put1("我懂个锤子",false);
            put1("盘古开天",false);
            put1("齐天大圣",false);
            put1("闪电五连鞭",false);
            put1("我身为汽车维修",false);
            put1("站在布隆后面",false);
            put1("对冲基金",false);
            put1("货币互通",false);
            put1("千里眼顺风耳",false);
            put1("轮回",false);
            put1("额外闪现",false);
            put1("古式佳酿",false);
            put1("虔焚之热",false);
            put1("我是Evan",false);
            put1("摸金校尉",false);
            put1("羽落的宠爱",false);
            put1("豪赌",false);
            put1("我是色批",false);
            put1("自杀式攻击",false);
            put1("大难不死必有后福",false);
            put1("冰霜之书",false);
            put1("怕光",false);
            put1("心灵净化",false);
            put1("火把之神的馈赠",false);
            put1("全能石",false);
            put1("玻璃大炮",false);
            put1("不全能吸血",false);
            put1("升级便无敌的我",false);
            put1("永不动摇",false);
            put1("太有用了",false);
            put1("5阶武器库",false);
            put1("法杖库",false);
            put1("戒指库",false);
            put1("黑幕",false);

            put2("谁说女子不如男",false);
            put2("高级5阶武器库",false);
            put2("高级法杖库",false);
            put2("高级戒指库",false);
            put2("宝库",false);
            put2("300颗够吗",false);
            put2("海克斯收集家",false);
            put2("台风",false);
            put2("吞噬星空",false);
            put2("冥想",false);
            put2("升级经验",false);
            put2("升级海克斯",false);
            put2("全副武装",false);
            put2("淬体",false);
            put2("深渊的宠爱",false);
            put2("陷阱的宠爱",false);
            put2("流水的宠爱",false);
            put2("范围的宠爱",false);
            put2("轻音战士",false);
            put2("草木的宠爱",false);
            put2("临终轻语",false);
            put2("暴击率的宠爱",false);
            put2("玩命",false);
            put2("防御转魔抗",false);
            put2("防御转魔抗",false);
            put2("狂暴",false);
            put2("即死",false);
            put2("升级水袋",false);
            put2("死亡之环",false);
            put2("我的刀盾",false);
            put2("我不是药神",false);
            put2("时间之神白野",false);
            put2("恢复恢复",false);
            put2("轻装上阵",false);
            put2("巨人杀手",false);
            put2("友好老鬼",false);
            put2("刷新海克斯",false);
            put2("嘟嘟哒嘟嘟哒",false);
            put2("灵感咕力咕力咕力灵感菇",false);
            put2("夺金",false);
            put2("不是哥们我海克斯呢",false);
            put2("骷髅打金服",false);
            put2("护甲是生命",false);
            put2("力量的护甲",false);
            put2("护甲的力量",false);
            put2("装备疾射火炮",false);
            put2("砍伤",false);
            put2("狂怒",false);
            put2("裁决使",false);
            put2("老练狙神",false);
            put2("叠角龙",false);
            put2("繁花的宠爱",false);
            put2("草丛伦",false);
            put2("!!!",false);
            put2("???",false);
            put2("献血",false);
            put2("亮出你的剑",false);
            put2("击杀击杀",false);
            put2("痛苦",false);
            put2("重量级打击手",false);
            put2("真战士",false);
            put2("混乱",false);
            put2("小型打击",false);
            put2("吸血鬼",false);
            put2("伤害伤害",false);
            put2("渴血",false);
            put2("装备无穷饥渴",false);
            put2("关键暴击",false);
            put2("超级玻璃大炮",false);
            put2("装备无终恨意",false);
            put2("慢炖",false);
            put2("快炖",false);
            put2("万用瞄准镜",false);
            put2("攻击转防御",false);
            put2("防御转攻击",false);
            put2("武器收集家",false);
            put2("防具收集家",false);
            put2("法杖收集家",false);
            put2("神器收集家",false);
            put2("家园卫士",false);
            put2("闪电打击",false);
            put2("来吧老弟",false);
            put2("动物园园长",false);
            put2("你肩上的恶魔",false);
            put2("强健体魄",false);
            put2("正义右拳",false);
            put2("无极剑道",false);
            put2("最后生还者",false);
            put2("伤害转嫁",false);
            put2("祖母辣椒油",false);
            put2("腺上激素",false);
            put2("多克特",false);
            put2("暴伤暴伤",false);
            put2("幸运",false);
            put2("选牌",false);
            put2("日蚀",false);
            put2("眼球收集者",false);
            put2("回归基本功",false);
            put2("武器赌狗",false);
            put2("防具赌狗",false);
            put2("法杖赌狗",false);
            put2("海克斯赌狗",false);
            put2("戒指赌狗",false);
            put2("吃胀到了",false);
            put2("升级等级",false);
            put2("Man!",false);
            put2("暴击暴击",false);
            put2("辐射",false);
            put2("命中的宠爱",false);
            put2("生命的宠爱",false);
            put2("防御的宠爱",false);
            put2("攻击的宠爱",false);
            put2("破败之王",false);
            put2("暗黑破坏神",false);
            put2("精巧狙击手",false);

            put3("我开挂了",false);
            put3("外交豁免",false);
            put3("坚固壁垒",false);
            put3("双刀流",false);
            put3("恢复的宠爱",false);
            put3("物理转魔法",false);
            put3("孤注一掷",false);
            put3("潘多拉魔盒",false);
            put3("血之饥渴",false);
            put3("当务之急安装洋柿子",false);
            put3("我秒升级",false);
            put3("最大护甲转生命再生",false);
            put3("暴击不暴击",false);
            put3("究极玻璃大炮",false);
            put3("踢踏舞",false);
            put3("装备星蚀",false);
            put3("出售防具Bug",false);
            put3("出售武器Bug",false);
            put3("无法刷新海克斯",false);
            put3("物法皆修",false);
            put3("闪避的宠爱",false);
            put3("吞噬灵魂",false);
            put3("属性转换",false);
            put3("+10086",false);
            put3("大饥霸",false);
            put3("歌尽云梦",false);
            put3("红包",false);
            put3("飞身踢",false);
            put3("科学狂人",false);
            put3("衰退的堕落",false);
            put3("缩小引擎",false);
            put3("坦克引擎",false);
            put3("均衡打击",false);
            put3("解除人体限制的诅咒",false);
            put3("超凡邪恶",false);
            put3("人不犯我我不犯人",false);
            put3("我等你",false);
//            put3("升级天赋",false);
            put3("属性叠属性叠",false);
            put3("吸血习性",false);
            put3("电火迸射",false);
            put3("玄武",false);
            put3("作弊我能复活",false);
            put3("我无限回档洞悉所有底牌",false);
            put3("歌利亚巨人",false);
            put3("巨型坦克",false);
            put3("蛋白粉奶昔",false);
            put3("我独自升级",false);
            put3("古神",false);
            put3("杀戮之神",false);
            put3("沃尔夫冈",false);
            put3("空手道",false);
            put3("升级升级",false);
            put3("毒素",false);
            put3("炒能力",false);
            put3("智慧的宠爱",false);
            put3("属性买买买",false);
            put3("铜皮铁骨",false);
            put3("最终Boss",false);
            put3("生命的祝福",false);
            put3("你赢了老弟",false);

        }
    }

    public String 海克斯描述(String 选择){
        switch(选择){
            default:return "What?";
            //1
            case "虔焚之热":return "未战斗状态时，敌人受到的火焰伤害可以暴击额外造成暴击伤害，并恢复造成伤害的生命";
            case "冰霜之书":return "暴击时对敌人施加冻结，且冻结伤害可以暴击额外造成暴击伤害，并恢复造成伤害的生命";
            case "怕光":return "对视野范围内的敌人物理攻击+10%伤害，游戏亮度低，且网格可视度关闭";
            case "心灵净化":return "击杀敌人会对相邻的敌人造成20%敌人当前生命伤害";
            case "古式佳酿":return "法杖施法、神器使用、武技使用都会恢复4.5%最大生命";
            case "额外闪现":return "如果没有闪现符石，在蛇皮走位满层后获得1个闪现符石";
            case "轮回":return "满级后，升级依旧会获得升级的属性(最大生命、最大命中、最大闪避)";
            case "吸吸物质为俊杰":return "+5%全能吸血";
            case "对冲基金":return "每天金币+2.5%x天数";
            case "货币互通":return "获得金币+1%能量，获得能量+50倍金币";
            case "千里眼顺风耳":return "感知范围+7";
            case "我有一剑":return "剑类武器攻击+20%";
            case "我懂个锤子":return "锤类武器攻击+30%";
            case "盘古开天":return "斧类武器攻击+25%";
            case "齐天大圣":return "棍类武器攻击+35%";
            case "闪电五连鞭":return "鞭类武器攻击+50%";
            case "我身为汽车维修":return "修理扳手攻击+650%";
            case "站在布隆后面":return "盾类武器攻击+50%防御+50%";
            case "死神":return "物理攻击对残血敌人+30%伤害";
            case "交锋":return "物理攻击对满血敌人必定暴击";
            case "升级综合属性":return "综合属性现在会改变攻击，防御";
            case "缩小射线":return "战斗状态的敌人大小-0.15，且敌人的伤害为大小";
            case "卷轴赌狗":return "随机60张卷轴";
            case "药剂赌狗":return "随机60瓶药剂";
            case "物品赌狗":return "随机60个物品";
            case "鬼刀一开看不见":return "使用武技后总移速x4，持续5回合";
            case "先攻":return "对敌人的首次物理攻击造成额外10%伤害，并获得20%伤害的金币";
            case "装备死亡之舞":return "受到的物理攻击30%，像流血那样";
            case "血气方刚":return "防御+吸血+全能吸血";
            case "屠夫":return "物理攻击生物类型敌人时候1/8概率掉落生肉";
            case "升级死神镰刀":return "无需残血也能造成暴击伤害，且残血也能再次x暴击伤害";
            case "电能震荡":return "冲击波法杖额外造成最大攻击力的伤害";
            case "升级破碎像素地牢":return "地牢敌人数量生成速度x3，但是掉率几率x4";
            case "专注":return "物理攻击前3回合也物理攻击过的敌人时，物理攻击伤害+15%";
            case "力速双A替身":return "替身+25%攻速和攻击";
            case "骰子收集者":return "海克斯宝典可以额外刷新1次";
            case "升级全能吸血":return "全能+2倍吸血，但是吸血无效";
            case "小怪猎手":return "物理攻击对非BOSS+15%伤害";
            case "猛攻":return "吸血和全能吸血无效，但伤害+50%吸血+50%全能吸血";
            case "风语者的祝福":return "防御+150%治疗效果";
            case "远视人启动":return "视野范围+5";
            case "安内":return "对1格内的敌人造成物理攻击可以暴击额外75%暴击伤害，对之外的敌人仅67%伤害";
            case "乌龟": return "总移速-10%，防御+50%";
            case "失败的Man":return "力量+40%";
            case "止戈":return "物理攻击伤害+20%，受到的物理攻击伤害+10%";
            case "末法":return "无法使用法杖，受到的伤害-20%";
            case "力量的爆发": return "使用力量药剂时，力量x10，持续900回合，持续时间结束时永久+10%力量";
            case "速度的承载": return "使用极速药剂时，移度和攻速+750%，持续900回合，持续时间结束时永久+15%攻速和移速";
            case "战场Boss":return "Boss层+20%伤害";
            case "树懒转世":return "饥饿速度-50%，隐匿+600%，动画加快关闭";
            case "小型打击":return "物理攻击伤害仅30%，但是攻击4次";
            case "升级暗影斗篷":return "每层充能额外获得4回合隐形，充能速度+45%";
            case "升级荆棘斗篷":return "最大防御+最大护甲x(1+神器等级)，充能速度+45%";
            case "走完红色路线":return "中国国旗不再减速，受到的物理攻击额外减少20%";
            case "升级蓄血圣杯":return "生命再生额外恢复2.25%已损失生命";
            case "升级丰饶之角":return "每层充能填充+100%，充能速度+45%";
            case "升级法师魔杖":return "+50%魔杖等级";
            case "铁布衫":return "防御+550%";
            case "龙鳞甲":return "防御+200%";
            case "升级荣耀纹章":return "恢复护甲速h度+650%，最大护甲+防具阶级+防具等级";
            case "升级暗杀之刃":return "伏击率能暴击";
            case "升级饮血之剑":return "+5%吸血，同时无需满血也能恢复护甲";
            case "升级破败王剑":return "+2.5%吸血，同时造成物理攻击时额外造成3.5%敌人最大生命";
            case "升级腥红散华":return "+2.5%吸血，必定8把飞刀";
            case "升级无影剑":return "无影剑+20%伤害";
            case "升级灵能短弓":return "射击伤害+350%";
            case "急救用具":return "治疗效果+25%";
            case "一板一眼":return "攻速固定1，攻速+25%，攻速变为提升攻击";
            case "暴风吸入":return "吸血+75%暴击伤害";
            case "神圣的宠爱":return "使饮用经验药剂+40最大命中和40最大闪避";
            case "灵巧":return "攻速+60%";
            case "装备黄金之心":return "最大生命+25，10最大防御，且每回合获得5金币";
            case "星界躯体":return "+125生命，-10%伤害";
            case "瞄准镜":return "攻击范围+2，如果攻击范围大于3则攻击范围+1";
            case "虚空之眼":return "攻击范围+1/3视野范围";
            case "升级变态刀":return "不再减总移速，且隐形时暴击伤害再次x1.2";
            case "升级无尽之刃":return "+25%暴击率，暴击伤害+50%暴击率";
            case "升级自然之履":return "踩草的自然层+2，自然层=神器等级+1，种子掉率=1/(25f - 自然层 *4f)，露珠掉率=1/(6f - 自然层 /2f)";
            case "会心治疗":return "+25%暴击率，治疗效果可以暴击然后额外恢复75%暴击伤害";
            case "会心防御":return "+25%暴击率，所受物理攻击根据暴击几率减少最多20%受到的伤害";
            case "会心闪避":return "暴击率+25%，闪避可以暴击额外+暴击伤害";
            case "穿甲的宠爱":return "穿甲+15%最大防御";
            case "左拳伤害高右拳高伤害":return "每第二次物理攻击伤害和攻速+15%";
            case "嗜血":return "视野内每一个未满血单位都会为你提供30%攻速和10%移速";
            case "易损":return "+25%暴击率，武技造成伤害可以暴击，造成额外暴击伤害";
            case "珠光护手":return "+25%暴击率，法杖造成伤害可以暴击，造成额外暴击伤害";
            case "虚幻武器":return "对目标施法同时会施加100%伤害的物理攻击";
            case "纪元":return "物理攻击伤害+敌人被发现的回合x5";
            case "饭桶":return "吃饭恢复5%最大生命和10%最大护甲";
            case "祛邪的宠爱":return "祛邪物品会同时升级2次(武器、防具、戒指、法杖)";
            case "鉴定的宠爱":return "鉴定物品会同时升级1次(武器、防具、戒指、法杖)";
            case "最终祝福":return "满级之后一直拥有祝福";
            case "接二连三":return "每第三次攻击会额外攻击一次";
            case "捐赠":return "金币+5000，能量+100";
            case "佯攻":return "首次物理攻击伤害仅0%，之后的物理攻击伤害+15%";
            case "唯快不破":return "物理攻击根据移速差距增伤，每比敌人多3%+1%伤害";
            case "橡皮长枪手":return "搜索范围+2倍攻击范围";
            case "残暴之力":return "最大攻击+35，穿甲+16";
            case "裁决使":return "对残血敌人+10%伤害，同时在武器击杀会恢复武器所有，法杖则法杖所有充能";
            case "老练狙神":return "法杖施法敌人在攻击范围外会恢复法杖所有充能";
            case "浪客之道":return "+25%暴击率，并且总暴击几率x2.5，但是暴击伤害x0.9";
            case "灵魂虹吸":return "+25%暴击率，作用于暴击的12.5%吸血";
            case "由暴生急":return "+50%暴击率的法杖、神器、武器充能";
            case "尖端发明家":return "法杖、神器、武器充能速度+35%";
            case "钢铁意志":return "根据已损失生命最多+300%最大护甲";
            case "战斗狂怒":return "根据已损失生命最多+50%暴击率";
            case "魔法转物理":return "所有法杖强化等级转换为武器等级";
            case "点亮他们":return "每第四次物理攻击额外最大攻击的魔法伤害";
            case "不全能吸血":return "全能吸血无效，吸血+200%全能吸血。";
            case "升级便无敌的我":return "升级会获得等级回合的无敌";
            case "永不动摇":return "静止状态超过5回合时，每回合恢复5%已损失生命";
            case "太有用了":return "随机出高等级的海克斯概率翻倍";
            case "5阶武器库":return "随机+5等级的三选一5阶武器";
            case "法杖库":return "随机+5等级的三选一法杖";
            case "戒指库":return "随机+5等级的三选一戒指";
            case "黑幕":return "属性面板不显示，综合属性+30%";
            case "全能石":return "获得10张蜕变秘卷";
            case "火把之神的馈赠":return "光照范围永久+3";
            case "玻璃大炮":return "最大生命-30%，+15%伤害";
            case "自杀式攻击":return "死亡会对视野内的所有敌人造成自身最大生命的无视防御伤害，对老鬼只有25%效果";
            case "我是Evan":return "最大护甲-50%，所有法杖等级+3，对非相邻敌人的物理攻击伤害+25%";
            case "大难不死必有后福":return "死亡时会在其位置生成60个随机物品";
            case "摸金校尉":return "翻开坟墓时获得地牢层数x500的金币";
            case "羽落的宠爱":return "获得1瓶羽落秘药，羽落Buff消失时会再次获得1瓶羽落秘药";
            case "豪赌":return "随机3级海克斯，并加入5个没啥用在海克斯组，但是之后的海克斯升级概率减半";
            case "我是色批":return "击杀魅魔会永久+10力量，但是被魅惑时会同时施加麻痹";
            //2
            case "高级5阶武器库":return "随机+15等级的三选一5阶武器，可以让5阶武器库也是+15";
            case "高级法杖库":return "随机+15等级的三选一法杖，可以让法杖库也是+15";
            case "高级戒指库":return "随机+15等级的三选一戒指，可以让戒指库也是+15";
            case "谁说女子不如男":return "如果男人英雄，总力量-50%，如果是女人总力量+50%";
            case "300颗够吗":return "坠牢之星掉落几率x300";
            case "宝库":return "随机+18的5阶武器，法杖，戒指三选一";
            case "骷髅打金服":return "骷髅的掉率x10倍，包括幸运之戒触发，循环播放专属BGM";
            case "刷新海克斯":return "海克斯宝典可以额外刷新3次";
            case "不是哥们我海克斯呢":return "移除所有海克斯，并根据移除的海克斯数量获得双倍数量的随机海克斯";
            case "夺金":return "对敌人的首次物理攻击额外造成40%最大攻击的魔法伤害，并获得金币";
            case "灵感咕力咕力咕力灵感菇":return "踩草必定随机额外获得一个红蓝绿其一蘑菇";
            case "嘟嘟哒嘟嘟哒":return "击杀骷髅会额外掉落5张魅音秘卷";
            case "友好老鬼":return "老鬼的物理攻击会使你恢复5%最大生命";
            case "护甲是生命":return "护甲恢复也能吃到200%的生命再生";
            case "伤害转嫁":return "你受到的物理攻击将给视野内所有敌人造成魔法伤害";
            case "力量的护甲":return "最大护甲+力量";
            case "护甲的力量":return "力量+最大护甲";
            case "狂怒":return "你的暴击机制会判定两次";
            case "选牌":return "物理攻击选其中一张牌的效果，每张牌都是1/3概率，蓝牌(恢复30%已损失护甲)，红牌:炸弹爆炸(不过不会和炸弹一样炸弹英雄以及破坏物品，且伤害减半)，黄牌(施加4回合麻痹)";
            case "日蚀":return "吸血+10%攻速";
            case "眼球收集者":return "击杀区域的BOSS+10%伤害(1~4区域)";
            case "幸运":return "15级幸运之戒效果";
            case "暴伤暴伤":return "溢出的暴击率转暴击伤害100%";
            case "多克特":return "治疗效果+60%";
            case "腺上激素":return "生命值低于7.5%时，+350%综合属性";
            case "祖母辣椒油":return "火焰元素在接触敌人每回合造成英雄7.5%最大生命伤害，接触英雄则恢复生命";
            case "最后生还者":return "半血以下时，移速x1.25，闪避x1.45";
            case "全副武装":return "-70%最大生命，210%给到护甲";
            case "淬体":return "生命+1000%最大护甲，最大护甲为0";
            case "草木的宠爱":return "新楼层必定草木层，在草丛上综合属性+25%";
            case "轻音战士":return "综合属性+(音乐音量+音效音量)x0.5%";
            case "陷阱的宠爱":return "新楼层必定陷阱层，在陷阱上综合属性+60%";
            case "流水的宠爱":return "新楼层必定流水层，在水上综合属性+25%";
            case "范围的宠爱":return "攻击范围+武器等级";
            case "防御转魔抗":return "元素抗性+最大防御/(2.5+最大防御)";
            case "魔抗转防御":return "防御+元素抗性";
            case "玩命":return "你残血以下时，伤害+50%";
            case "狂暴":return "你半血以下时，伤害+35%";
            case "即死":return "物理攻击秒杀生命值低于25%的敌人";
            case "升级水袋":return "拾取露珠额外获得2露珠";
            case "死亡之环":return "获得治疗同时对2格内内敌人造成100%治疗量的伤害";
            case "恢复恢复":return "生命再生和护甲恢复速度+250%";
            case "我的刀盾":return "发现豺狼类型的怪物会使其腐化";
            case "时间之神白野":return "时间停止时，总综合属性+200%";
            case "我不是药神":return "身上每个Buff都会为你提供10%综合属性";
            case "轻装上阵":return "总移速+30%，-20%护甲";
            case "巨人杀手":return "大小-0.1，根据与敌人大小差距，物理攻击额外造成最多35%伤害";
            case "来吧老弟":return "伤害+45%，敌人伤害+35%";
            case "混乱":return "物理攻击以及受到物理攻击，都会有1/5概率使敌人获得一个6回合的狂乱";
            case "真战士":return "+2.5%吸血+75最大生命";
            case "电火迸射":return "物理攻击伤害仅20%，但是攻击6次";
            case "临终轻语":return "+25%暴击率，物理攻击额外造成敌人9%已损生命值的魔法伤害";
            case "伤害伤害":return "伤害+10%";
            case "暴击率的宠爱":return "暴击率+0.1%英雄物理攻击次数";
            case "吸血鬼":return "治疗效果+吸血+全能吸血";
            case "渴血":return "+15%全能吸血";
            case "装备无穷饥渴":return "+5%全能吸血，连杀状态额外+15%全能吸血";
            case "关键暴击":return "暴击率+50%";
            case "装备疾射火炮":return "+25%暴击率，+35%攻速，每10回合下次物理攻击的攻击范围+50%";
            case "砍伤":return "+50%攻速，物理攻击额外造成50%最大防御的魔法伤害";
            case "超级玻璃大炮":return "-50%最大生命，+35%伤害";
            case "装备无终恨意":return "每5回合对2格敌人造成10%最大生命，并在造成伤害时每个敌人恢复10%最大生命";
            case "慢炖":return "每回合对2格内敌人造成0.5%最大生命的无限叠加伤害";
            case "快炖":return "每回合损失1%生命，并对2格内内敌人造成1%最大生命的无限叠加伤害";
            case "万用瞄准镜":return "攻击范围+4，如果攻击范围大于3则攻击范围+2";
            case "痛苦":return "物理攻击+死亡次数x50魔法伤害";
            case "重量级打击手":return "物理攻击伤害+5%最大生命";
            case "攻击转防御":return "最大防御+50%最大攻击";
            case "防御转攻击":return "最大攻击+最大防御";
            case "武器收集家":return "背包每把武器的阶级+等级，+5最大攻击";
            case "防具收集家":return "背包每件防具的阶级+等级，+3最大防御";
            case "法杖收集家":return "背包每把法杖1+等级，+10%法杖充能";
            case "神器收集家":return "背包每件神器的1+等级，+20%神器充能";
            case "击杀击杀":return "击杀敌人会额外触发一次击杀";
            case "亮出你的剑":return "攻击范围固定为1，每点额外攻击范围+10%攻速，10%攻击，10%最大生命，7.5%吸血";
            case "献血":return "物理攻击消耗1.5%当前生命，同时额外造成7.5%自身最大生命伤害";
            case "家园卫士":return "未战斗状态时，移速+100%";
            case "???":return "使用放大镜消耗4回合，如果是敌人对进行物理攻击且为造成15%已损失生命的伤害，如果是友军，则恢复5%最大生命";
            case "!!!":return "惊醒敌人同时会对其造成攻速倍的必定物理攻击";
            case "草丛伦":return "在草丛时获得隐形，隐形时综合属性+10%";
            case "繁花的宠爱":return "踩草时必定额外获得回春草之种";
            case "叠角龙":return "击杀老鬼、傀儡也能触发死亡联动效果";
            case "闪电打击":return "总攻速+20%，物理攻击额外造成20魔法伤害";
            case "动物园园长":return "对动物的物理攻击+35%伤害";
            case "你肩上的恶魔":return "每回合损失0.5%生命，+25%伤害，+5%吸血，有敌人在视野内时提升至5%生命";
            case "强健体魄":return "最大生命+15倍力量";
            case "正义右拳":return "物理攻击额外造成350%力量的伤害";
            case "无极剑道":return "物理攻击额外造成20%最大攻击的魔法伤害";
            case "回归基本功":return "无法使用使用天狗的面具，但是综合属性+150%";
            case "武器赌狗":return "随机阶级+8的武器";
            case "防具赌狗":return "随机阶级+8的防具";
            case "法杖赌狗":return "随机+8的法杖";
            case "戒指赌狗":return "随机+8的戒指";
            case "吃胀到了":return "吃饭超过450时，恢复等值饱腹的生命";
            case "海克斯赌狗":return "随机获得2个海克斯";
            case "升级等级":return "获得等级数量的升级卷轴，升级时会获得升级卷轴";
            case "生命的宠爱":return "最大生命+10%英雄物理防御次数";
            case "防御的宠爱":return "最大防御+5%英雄物理防御次数";
            case "攻击的宠爱":return "最大攻击+5%物理攻击次数";
            case "Man!":return "力量+80%";
            case "辐射":return "敌人在出生时都会赋予3+60%地牢层数伤害的中毒，如果分离时没有死亡，则该目标的伤害+35%";
            case "升级海克斯":return "获得一个不能刷新的海克斯宝典，并且海克斯的等级+1";
            case "台风":return "物理攻击额外投掷一个无视防御的魔法箭矢，造成30%物理攻击伤害";
            case "海克斯收集家":return "每有一个海克斯，综合属性+10%";
            case "吞噬星空":return "暗金有吞噬按钮，吞噬之后消耗掉暗金并1%综合属性";
            case "升级经验":return "获得的经验x0.35x等级";
            case "冥想":return "等待回合x4，但是恢复25%已损失圣生命，且所受伤害-50%";
            case "暗黑破坏神":return "镐子破坏墙体时永久+0.35%综合属性";
            case "破败之王":return "腐化敌人不会每回合损失1%最大生命，并且每回合恢复5%最大生命";
            case "精巧狙击手":return "投掷敌人在攻击范围外时，花费的时间/3";
            case "命中的宠爱":return "物理攻击时+0.5最大闪避";
            case "暴击暴击":return "暴击伤害能再次暴击x1.45";
            //3
            case "无法刷新海克斯":return "海克斯宝典无法刷新，但是根据海克斯刷新上限额外获得海克斯宝典";
            case "双刀流":return "总攻速+20%，物理攻击额外投掷一个无视防御的魔法箭矢，造成40%物理攻击伤害";
            case "外交豁免":return "物理攻击目标会5回合内免疫目标之外的物理攻击，且对目标造成物理攻击+25%伤害，仅只能有一个目标";
            case "我开挂了":return "物理攻击必定秒杀，但是受到伤害x3";
            case "坚固壁垒":return "超过10%生命的所受伤害将减少50%";
            case "恢复的宠爱":return "饮用永生秘药+25%治疗效果";
            case "物理转魔法":return "所有法杖等级+15%力量";
            case "血之饥渴":return "物理攻击额外造成10%最大攻击的魔法伤害，在半血以下时候同时恢复伤害的生命，在残血以下时恢复的生命x2.5";
            case "孤注一掷":return "综合属性-45%，并随机获得5个海克斯";
            case "潘多拉魔盒":return "随机获得4个海克斯，但是海克斯组加入10个没啥用";
            case "当务之急安装洋柿子":return "随机获得3个海克斯";
            case "我秒升级":return "升级所需经验改为等级";
            case "最大护甲转生命再生":return "生命再生+1%最大护甲";
            case "暴击不暴击":return "未暴击也能提升伤害，只不过x1.45";
            case "踢踏舞":return "攻速+15%移速，战斗状态下每次物理攻击+10%移速，可以无限叠加，脱离战斗状态会移除所有";
            case "装备星蚀":return "2回合内对物理攻击并使用法杖施法或武器技能，则对敌人造成20%最大生命的伤害，并恢复20%最大护甲，之后需要冷却5回合";
            case "物法皆修":return "战斗状态下物理攻击+所有武器和法杖0.2等级，脱离战斗状态移除所有";
            case "出售防具Bug":return "出售防具时，永久+防具的最大防御";
            case "出售武器Bug":return "出售武器时，永久+武器的最大攻击";
            case "闪避的宠爱":return "闪避时+0.5最大闪避";
            case "吞噬灵魂":return "敌人被施加负面效果时永久+3最大生命";
            case "衰退的堕落":return "升级武器、防具、法杖、戒指能够额外升级两次，获得150回合降级效果";
            case "科学狂人":return "物理攻击5%概率随机加或减0.3大小，+40%最大生命+30%力量，+60%总移速";
            case "生命的祝福":return "饮用治疗药剂永久+5%最大生命";
            case "属性转换":return "最大命中+最大闪避，最大攻击+最大防御，攻速+移速";
            case "究极玻璃大炮":return "-70%最大生命，敌人伤害+50%，伤害+65%";
            case "+10086":return "你的总综合属性减70%，但是获得一个超级升级符石可以选择一个武器或防具，法杖，戒指的等级固定在10086级";
            case "歌尽云梦":return "获得1张魅音秘卷，且魅音秘卷阅读75%概率获得2张魅音秘卷，否则获得1张催眠卷轴";
            case "大饥霸":return "力量+175%";
            case "你赢了老弟":return "获得Yendor护符";
            case "最终Boss":return "Boss层+75%伤害";
            case "铜皮铁骨":return "最大防御+450%力量";
            case "飞身踢":return "物理攻击秒杀生命值低于20%自身最大生命的敌人，并恢复5%最大生命";
            case "红包":return "每回合1/240概率掉落红包，红包使用同时会获得一个属性锻造器";
            case "缩小引擎":return "大小-英雄等级x0.02，综合属性+英雄等级x1%";
            case "坦克引擎":return "大小+英雄等级x0.01，最大生命+英雄等级x4%";
            case "均衡打击":return "物理攻击伤害减半，但是额外造成另一半的魔法伤害";
            case "战神":return "吸血+15%，最大生命+40%";
            case "法神":return "所有法杖等级+60%";
            case "解除人体限制的诅咒":return "综合属性+每回合+0.1%，你的寿命只有6天，6天爆体而亡(不能复活)";
            case "超凡邪恶":return "击杀敌人时，所有法杖永久+0.05强化等级";
            case "人不犯我我不犯人":return "战斗状态时+30%综合属性";
            case "我等你":return "综合属性-50%，休息时获得无敌";
            case "升级升级":return "升级武器、防具、法杖、戒指能够额外升级一次";
            case "空手道":return "空手总攻速-50%，攻击+750%";
            case "升级天赋":return "天赋仅需升级一次即满级";
            case "属性叠属性叠":return "综合属性+3.5%，同时获得一个属性锻造器，在击杀敌人1/6概率获得一个属性锻造器";
            case "吸血习性":return "移除生命再生，+25%全能吸血";
            case "玄武": return "总移速-30%，防御+150%";
            case "作弊我能复活":return "综合属性-30%，死亡会复活，所有未装备的道具都会丢失，允许选择两件物品在本层某一处复活，其余物品掉落在死亡地点";
            case "我无限回档洞悉所有底牌":return "死亡会复活随机传送，但是地牢将有1/10概率生成的是隐形怪，且当你灵视发现时他们的物理攻击+10倍，受伤仅10%，命中闪避x10，攻速移速x10";
            case "歌利亚巨人":return "最大生命+60%，+15%力量，+0.1大小";
            case "巨型坦克":return "最大生命+80%，+0.2大小";
            case "古神":return "+100%最大生命，+0.2大小，-15%总移速";
            case "蛋白粉奶昔":return "+17.5%治疗效果，每2最大防御+1%治疗效果";
            case "我独自升级":return "获得等级x1.5数量的升级卷轴，并升级至满级";
            case "毒素":return "物理攻击对施加34%伤害的中毒";
            case "炒能力":return "综合属性+金币/15000";
            case "属性买买买":return "商店出售属性锻造器";
            case "智慧的宠爱":return "消耗能量x12倍，炼金第一次之后的合成不消耗材料";
            case "杀戮之神":return "物理攻击对非BOSS+50%伤害";
            case "沃尔夫冈":return "根据150以内弱小-25%力量，中间总移速+10%，300以上强大+100%力量，大小+0.1，生命+50%";

        }
    }

    @Override
    public float 伤害(){
        float x=1;
        if(符文("辐射")&&首次死亡)x+=0.35f;
        if(符文("你肩上的恶魔"))x+=0.25f;
        if(符文("眼球收集者")){
            if(Badges.local.contains(Badges.Badge.BOSS_SLAIN_1))x+=0.1f;
            if(Badges.local.contains(Badges.Badge.BOSS_SLAIN_2))x+=0.1f;
            if(Badges.local.contains(Badges.Badge.BOSS_SLAIN_3))x+=0.1f;
            if(Badges.local.contains(Badges.Badge.BOSS_SLAIN_4))x+=0.1f;
        }
        if(符文("来吧老弟"))x+=0.45f;
        if(符文("战场Boss")&&Dungeon.bossLevel())x+=0.2f;
        if(符文("最终Boss")&&Dungeon.bossLevel())x+=0.75f;
        if(符文("星界躯体"))x-=0.1f;
        if(符文("腺上激素")&&生命<=最大生命(0.0075f))x+=3.5f;
        if(符文("玩命")&&残血())x+=0.5f;
        if(符文("狂暴")&&半血以下())x+=0.35f;
        if(符文("伤害伤害"))x+=0.1f;
        if(符文("玻璃大炮"))x+=0.15f;
        if(符文("超级玻璃大炮"))x+=0.35f;
        if(符文("究极玻璃大炮"))x+=0.65f;
        if(符文("猛攻"))x+=0.5f*(吸血()+全能吸血());
        return x;
    }
    public void 海克斯触发(String 选择){
        switch(选择){
            default:
                if(Dungeon.hero())
                更新数据();
                return;
            case "不是哥们我海克斯呢":
                int 数量=0;
                for(boolean b:海克斯1.values()){
                    if(b)数量++;
                }
                for(boolean b:海克斯2.values()){
                    if(b)数量++;
                }
                for(boolean b:海克斯3.values()){
                    if(b)数量++;
                }

                for(String k:海克斯1.keySet()){
                    Notes.remove(Notes.findCustomRecord(k));
                    海克斯1.put(k,false);
                }
                for(String k:海克斯2.keySet()){
                    Notes.remove(Notes.findCustomRecord(k));
                    海克斯2.put(k,false);
                }
                for(String k:海克斯3.keySet()){
                    Notes.remove(Notes.findCustomRecord(k));
                    海克斯3.put(k,false);
                }


                for(int x=0;x<=数量*2;x++){
                    选择海克斯(随机海克斯());
                }
                return;
            case "歌尽云梦":
                new ScrollOfSirensSong().放背包();
                return;
            case "+10086":
                new 超级升级符石().放背包();
                return;
            case "骷髅打金服":
                Dungeon.level.playLevelMusic();//刷新
                return;
            case "羽落的宠爱":
                    new ElixirOfFeatherFall().放背包();
                return;
            case "5阶武器库":
            case "高级5阶武器库":
                Item wm1;
                    wm1=Generator.randomWeapon(5).升级(符文("高级5阶武器库")?15:5);
                Item wm2;
                do{
                    wm2=Generator.randomWeapon(5).升级(符文("高级5阶武器库")?15:5);
                }while(wm1==wm2);
                Item wm3;
                do{
                    wm3=Generator.randomWeapon(5).升级(符文("高级5阶武器库")?15:5);
                }while(wm1==wm3||wm2==wm3);
                Item finalIwm=wm2;
                Item finalwm1wm=wm3;
                Game.runOnRenderThread(()->{
                    GameScene.show(new Wnd选择物品(this,wm1,finalIwm,finalwm1wm));
                });
            case "法杖库":
            case "高级法杖库":
                Item w1;
                w1=Generator.randomWand().升级(符文("高级法杖库")?15:5);
                Item w2;
                do{
                    w2=Generator.randomWand().升级(符文("高级法杖库")?15:5);
                }while(w1==w2);
                Item w3;
                do{
                    w3=Generator.randomWand().升级(符文("高级法杖库")?15:5);
                }while(w1==w3||w2==w3);
                Item finalIw=w2;
                Item finalI1w=w3;
                Game.runOnRenderThread(()->{
                    GameScene.show(new Wnd选择物品(this,w1,finalIw,finalI1w));
                });
                return;
            case "戒指库":
            case "高级戒指库":
                Item r1;
                r1=Generator.randomWand().升级(符文("高级戒指库")?15:5);
                Item r2;
                do{
                    r2=Generator.randomWand().升级(符文("高级戒指库")?15:5);
                }while(r1==r2);
                Item r3;
                do{
                    r3=Generator.randomWand().升级(符文("高级戒指库")?15:5);
                }while(r1==r3||r2==r3);
                Item finalIr=r2;
                Item finalI1r=r3;
                Game.runOnRenderThread(()->{
                    GameScene.show(new Wnd选择物品(this,r1,finalIr,finalI1r));
                });
                return;
            case "宝库":
                Item i1;
                i1=Generator.randomWeapon(5).升级(18);
                Item i2;
                    i2=Generator.randomWand().升级(18);
                Item i3;
                    i3=Generator.randomRing().升级(18);
                Item finalI=i2;
                Item finalI1=i3;
                Game.runOnRenderThread(()->{
                    GameScene.show(new Wnd选择物品(this,i1,finalI,finalI1));
                });
                return;
            case "火把之神的馈赠":
                火把神+=3;
                return;
            case "豪赌":
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                return;
            case "潘多拉魔盒":
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                return;
            case "孤注一掷":
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                return;
            case "当务之急安装洋柿子":
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                return;
            case "海克斯赌狗":
                选择海克斯(随机海克斯());
                选择海克斯(随机海克斯());
                return;
            case "无法刷新海克斯":
                for(int x=0;x<=海克斯宝典.使用上限();x++){
                    new 海克斯宝典().放背包();
                }
                return;
            case "升级等级":
                    new 升级卷轴().数量(等级).放背包();
                return;
            case "全能石":
                    new 蜕变秘卷().数量(10).放背包();
                return;
            case "我独自升级":
                    new 升级卷轴().数量(Math.round(等级*1.5f)).放背包();

                do
                {
                    升级(true);
                }while(等级<最大等级);
                return;
            case "武器赌狗":
                Item w= Random.oneOf(Generator.randomWeapon(1),
                                     Generator.randomWeapon(2),
                                     Generator.randomWeapon(3),
                                     Generator.randomWeapon(4),
                                     Generator.randomWeapon(5));
                w.升级(8);
                w.鉴定().放背包();
                return;
            case "防具赌狗":
                Item a= Random.oneOf(Generator.randomArmor(1),
                                     Generator.randomArmor(2),
                                     Generator.randomArmor(3),
                                     Generator.randomArmor(4),
                                     Generator.randomArmor(5));
                a.升级(8);
                a.鉴定().放背包();
                return;
            case "法杖赌狗":
                Item wa= Generator.randomWand();
                wa.升级(8);
                wa.鉴定().放背包();
                return;
            case "戒指赌狗":
                Item r= Generator.randomRing();
                r.升级(8);
                r.鉴定().放背包();
                return;
            case "我是Evan":
                智力+=3;
                return;
            case "属性叠属性叠":
                new 属性锻造器().放背包();
                return;
            case "升级海克斯":
                海克斯宝典 item=new 海克斯宝典();
                item.用过=item.使用上限();
                item.放背包();
                return;
            case "你赢了老弟":
                new 真正护符().放背包();
                return;
            case "幸运":
                belongings.幸运.升级(15);
                return;
            case "卷轴赌狗":
                for(int x=0;x<=60;x++){
                    Item s=Generator.randomUsingDefaults(Generator.Category.SCROLL);
                    Dungeon.level.drop(s,pos).sprite.drop();
                }
                return;
            case "药剂赌狗":
                for(int x=0;x<=60;x++){
                    Item s=Generator.randomUsingDefaults(Generator.Category.POTION);
                    Dungeon.level.drop(s,pos).sprite.drop();
                }
                return;
            case "物品赌狗":
                for(int x=0;x<=60;x++){
                    Item s=Generator.random();
                    Dungeon.level.drop(s,pos).sprite.drop();
                }
                return;
            case "解除人体限制的诅咒":
                Dungeon.地牢寿命=Dungeon.地牢天数+6;
                return;
            case "捐赠":
                Dungeon.gold(7500);
                Dungeon.energy(1500);
                return;
        }
    }

    public void 选择海克斯(String s){

        Sample.INSTANCE.play(Assets.Sounds.海克斯,1.75f);

        Notes.备注(s,海克斯描述(s));
        GLog.w("你已备注了选择的海克斯。");

        if(海克斯级(s)==1)
        海克斯1.put(s,true);

        if(海克斯级(s)==2)
        海克斯2.put(s,true);

        if(海克斯级(s)==3)
        海克斯3.put(s,true);

        海克斯触发(s);
    }
    public List<String> 随机海克斯(int 数量){

        List<String> selectedKeys = new ArrayList<>();

        while (selectedKeys.size() < 数量) {
            String key=随机海克斯();
            if (!selectedKeys.contains(key)){//三个不相同
                selectedKeys.add(key);
            }
        }
        return selectedKeys;
    }

    public String 随机海克斯(){
            String select="";
            //region 海克斯等级随机
            // 使用Random类生成随机索引

            int 次数=1;
            if(等级==7)次数=Math.min(++次数,3);
            if(等级==13)次数=Math.min(++次数,3);
            if(等级==19)次数=Math.min(++次数,3);

            int 等级=1;
            int 概率=10;
            if(符文("太有用了"))
                概率*=2;
            if(符文("豪赌"))
                概率/=2;

            if(符文("升级海克斯"))
                等级=Math.min(++等级,3);
            if(算法.概率学(次数*概率+等级*概率)){
                等级=Math.min(++等级,3);
                if(算法.概率学(次数*概率+等级*概率)){
                    等级=Math.min(++等级,3);
                }
            }

            //endregion

            能随机海克斯();

            // 获取所有键并转换为列表
            List<String> keysList = new ArrayList<>(海克斯1.keySet());
            if(等级==2)keysList = new ArrayList<>(海克斯2.keySet());
            if(等级==3)keysList = new ArrayList<>(海克斯3.keySet());

            do
            {
                int index = Random.Int(海克斯1.size());
                if(等级==2)index = Random.Int(海克斯2.size());
                if(等级==3)index = Random.Int(海克斯3.size());
                if(index>0){
                    String key=keysList.get(index);

                    if(不能海克斯(key)){//不能共生
                        算法.调试("共生"+key);
                    }else
                        if(符文(key)){//已有
                            算法.调试("已有"+key);
                        }else{
                            select=key;
                        }
                }else return "";
            }while(select.equals(""));//重新寻找

        return select;
    }

    public void 能随机海克斯(){
        if(heroClass(HeroClass.WARRIOR)&&!符文("战神")){
            put3("战神",false);
        }
        if(heroClass(HeroClass.MAGE)&&!符文("法神")){
            put3("法神",false);
        }
        if(heroClass(HeroClass.HUNTRESS)&&!符文("升级灵能短弓")){
            put1("升级灵能短弓",false);
        }
        if(heroClass(HeroClass.盗贼)&&!符文("升级暗影斗篷")){
            put1("升级暗影斗篷",false);
        }
        if(符文("豪赌")){
            put1("没卵用1",false);
            put1("没卵用2",false);
            put1("没卵用3",false);
            put1("没卵用4",false);
            put1("没卵用5",false);
        }
        if(符文("潘多拉魔盒")){
            put1("没啥用1",false);
            put1("没啥用2",false);
            put1("没啥用3",false);
            put1("没啥用4",false);
            put1("没啥用5",false);
            put1("没啥用6",false);
            put1("没啥用7",false);
            put1("没啥用8",false);
            put1("没啥用9",false);
            put1("没啥用10",false);
        }
    }
    public boolean 不能海克斯(String no){
        return (!subClass(HeroSubClass.NONE)&&no.equals("回归基本功"))||

        (符文("攻击转防御")&&no.equals("防御转攻击"))||
        (符文("攻击转防御")&&no.equals("属性转换"))||
        (符文("防御转攻击")&&no.equals("攻击转防御"))||

        (符文("作弊我能复活")&&no.equals("我无限回档洞悉所有底牌"))||
        (符文("我无限回档洞悉所有底牌")&&no.equals("作弊我能复活"))||

        (符文("防御转魔抗")&&no.equals("魔抗转防御"))||
        (符文("魔抗转防御")&&no.equals("防御转魔抗"))||

        (符文("不全能吸血")&&no.equals("升级全能吸血"))||
        (符文("升级全能吸血")&&no.equals("不全能吸血"))||


        (符文("蛋白粉奶昔")&&no.equals("风语者的祝福"))||
        (符文("风语者的祝福")&&no.equals("蛋白粉奶昔"))||

        (符文("会心治疗")&&no.equals("灵魂虹吸"))||
        (符文("灵魂虹吸")&&no.equals("会心治疗"))||

        (符文("护甲的力量")&&no.equals("力量的护甲"))||
        (符文("力量的护甲")&&no.equals("护甲的力量"))||

        (符文("草木的宠爱")&&no.equals("流水的宠爱"))||
        (符文("草木的宠爱")&&no.equals("深渊的宠爱"))||
        (符文("草木的宠爱")&&no.equals("陷阱的宠爱"))||

        (符文("深渊的宠爱")&&no.equals("流水的宠爱"))||
        (符文("深渊的宠爱")&&no.equals("草木的宠爱"))||
        (符文("深渊的宠爱")&&no.equals("陷阱的宠爱"))||

        (符文("陷阱的宠爱")&&no.equals("流水的宠爱"))||
        (符文("陷阱的宠爱")&&no.equals("草木的宠爱"))||
        (符文("陷阱的宠爱")&&no.equals("深渊的宠爱"))||

        (符文("流水的宠爱")&&no.equals("草木的宠爱"))||
        (符文("流水的宠爱")&&no.equals("深渊的宠爱"))||
        (符文("流水的宠爱")&&no.equals("陷阱的宠爱"))||

        (符文("淬体")&&no.equals("全副武装"))||
        (符文("全副武装")&&no.equals("淬体"));

    }
    public static boolean 是海克斯(String s){
        return 海克斯级(s)==1||海克斯级(s)==2||海克斯级(s)==3;
    }
    public static int 海克斯级(String s){
        if(海克斯1.get(s)!=null)
            return 1;

        if(海克斯2.get(s)!=null)
            return 2;

        if(海克斯3.get(s)!=null)
            return 3;

        return 0;
    }

    public boolean 符文(String s){
        if(海克斯1.get(s)!=null)
            return 海克斯1.get(s);

        if(海克斯2.get(s)!=null)
            return 海克斯2.get(s);

        if(海克斯3.get(s)!=null)
            return 海克斯3.get(s);

        return false;
    }

    public float 科学狂人= 0.3f;
    public int 幸运转世= 0;
    public boolean 欧皇(){
        return 幸运转世==1;
    }
    public boolean 非酋(){
        return 幸运转世==2;
    }
    public String 重生怪物 = "";
    public int 攻击范围get = 0;
    public int 视野范围get = 0;
    public float 最大生命get = 0;
    public float 最大护甲get = 0;
    public int 死亡次数 = 0;
    public int 破坏物体 = 0;
    public float 生命成长 = 0;
    public float 攻击成长 = 0;
    public float 防御成长 = 0;
    public float 攻速成长 = 0;
    public float 移速成长 = 0;
    public float 命中成长 = 0;
    public float 闪避成长 = 0;
    public float 暴伤成长= 0;
    public float 吸血成长 = 0;
    public float 属性成长 = 0;
    public float 暴率成长 = 0;
    public float 治疗成长 = 0;
    public float 穿甲成长 = 0;
    public float 穿透成长 = 0;
    public float 之前攻击延迟 = 0;

    public boolean ready = false;
    public boolean damageInterrupt = true;
    public HeroAction curAction = null;
    public HeroAction lastAction = null;

    //reference to the enemy the hero is currently in the process of attacking
    private Char attackTarget;

    public boolean resting = false;

    public Belongings belongings;

    public float 力量;
    public int 魔力;
    public float 武器力量= 0;
    public int 根骨 = 0;
    public int 火把神 = 0;
    public float 智力 = 0;
    public int 连击 = 0;
    public float 护甲力量= 0;
    public int 投机之剑= 0;
    public int 英精英雄= -1;
    public float 英精英雄成长= 0;
    public boolean 地牢塔防开关 =false;
    public boolean 地牢塔防去下一层 =false;
    public boolean 地牢塔防更多更快开关 =false;
    public boolean 地牢塔防老鬼开关 =true;
    public int 地牢塔防生成速度= 0;
    public int 地牢塔防波次= 0;
    public int 蛇皮走位=0;

    public int 等级 = 1;
    public int 当前经验 = 0;

    public int HTBoost = 0;

    private ArrayList<Mob> visibleEnemies;

    //This list is maintained so that some logic checks can be skipped
    // for enemies we know we aren't seeing normally, resulting in better performance
    public ArrayList<Mob> mindVisionEnemies = new ArrayList<>();

    public Hero() {
        super();
        生命 = 最大生命 = 20;
        护甲=最大护甲();
        力量 = 10;
        魔力 = 1;

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

        最大生命 = 20 + 3* (等级 - 1) + HTBoost;

        最大生命+=生命成长;
        最大生命+=根骨 * 5;
        if(符文("生命的宠爱"))最大生命+=第x次防御/10f;
        if(符文("装备黄金之心"))最大生命+=25;
        if(符文("强健体魄"))最大生命+=力量()*1.5f;
        if(符文("真战士"))最大生命+=75;
        if(符文("星界躯体"))最大生命+=125;
        if(符文("淬体"))最大生命+=最大护甲get*10;
        最大生命+=根骨之戒.strengthBonus(this)*25;
        最大生命+=天赋点数(Talent.勇武,5);
        
        if (belongings.armor() instanceof 巫服) {
            最大生命 *= 1.15f;
        }

        if(符文("沃尔夫冈")&&hasbuff(Hunger.class)){
            if(buff(Hunger.class).level<150)
                最大生命 *= 1.5f;
        }

        if(符文("科学狂人")&&科学狂人==0.3f)最大生命*=1.4f;
        if(符文("战神"))最大生命*=1.4f;
        if(符文("歌利亚巨人"))最大生命*=1.6f;
        if(符文("坦克引擎"))最大生命*=1+0.04f*等级;
        if(符文("古神"))最大生命*=2f;
        if(符文("巨型坦克"))最大生命*=1.8f;

        if(subClass(HeroSubClass.血法师)&&职业精通())最大生命*=1.35f;

        if(符文("玻璃大炮"))最大生命*=0.7f;
        if(符文("究极玻璃大炮"))最大生命*=0.3f;
        if(符文("超级玻璃大炮"))最大生命/=2f;
        最大生命*=1+英精英雄成长;
        最大生命*=角斗链枷.减少();
        最大生命 *= 综合属性();

        if(符文("亮出你的剑"))最大生命*=1+0.1f*攻击范围get;

        if(符文("全副武装")){
            最大生命get=最大生命;
            最大生命*=0.3f;
        }

        生命 = Math.min(生命, 最大生命);
    }

    public int 魔力() {
        int m=魔力;

        return m;
    }
    public float 力量() {

        float str =力量+根骨之戒.strengthBonus(this)*2;

        if(nobuff(九龙针筒.力量.class)&&belongings.hasItem(九龙针筒.class)&&belongings.getItem(九龙针筒.class).cursed)
            str-=力量/2;

        str+=天赋点数(Talent.健身);

        AdrenalineSurge buff = buff(AdrenalineSurge.class);
        if (buff != null) {
            str += buff.boost();
        }
        if(heroClass(HeroClass.兽灵)){
            str+=最大生命(0.035f);
        }

        float strx=1;
        strx += 天赋点数(Talent.力大无穷,0.1f);
        if(符文("护甲的力量"))strx+=最大护甲();
        if(符文("失败的Man"))strx+=0.4f;
        if(符文("Man!"))strx+=0.8f;
        if(符文("大饥霸"))strx+=1.75f;
        if(符文("歌利亚巨人"))strx+=0.15f;
        if(符文("科学狂人")&&科学狂人==0.3f)str+=0.3f;
        if(符文("沃尔夫冈")&&hasbuff(Hunger.class)){
            if(buff(Hunger.class).level>300)
            strx-=0.25;
            if(buff(Hunger.class).level<150)
            strx+=1;
        }

        if (heroClass(HeroClass.行僧)&&裸衣()) {
            strx+=0.15f;
        }
        if(Holiday.getCurrentHoliday()==Holiday.妇女节&&女人()){
            strx+=0.1f;
        }

        if (buff(力量药剂.HTBoost.class)!=null) {
            strx+=9f;
        }

        if(单身){
            strx+=0.1f;
        }
        if(subClass(HeroSubClass.健身猛男)){
            if(hasbuff(隔天休息.class)&&hasbuff(Hunger.class)&&buff(Hunger.class).饥饿()){
                str/=2;
            }
            if(职业精通()){
                strx+=0.2f;
            }
            strx+=之前健身;
        }
        if(符文("谁说女子不如男")){
            if(女人())
                strx*=1.5f;
            else strx/=2f;
        }

        if(!女人())
            strx*=男人国徽章.力量();

        strx*=综合属性();

        str*=strx;
        //最后结算
        return str;
    }

    private static final String CLASS = "class";
    private static final String heroClass蜕变x = "heroClass蜕变";
    private static final String SUBCLASS = "subClass";
    private static final String ABILITY = "armorAbility";

    private static final String LEVEL = "lvl";
    private static final String 力量x = "力量";
    private static final String 魔力x = "魔力";
    private static final String 武器力量x= "武器力量";
    private static final String 根骨x = "根骨";
    private static final String 火把神x = "火把神";
    private static final String 智力x = "智力";
    private static final String 连击x = "连击";
    private static final String 护甲力量x= "护甲力量";
    private static final String 投机之剑x= "投机之剑";
    private static final String 英精英雄x= "英精英雄";
    private static final String 英精英雄成长x= "英精英雄成长";
    private static final String 地牢塔防开关x= "地牢塔防开关";
    private static final String 地牢塔防去下一层x= "地牢塔防去下一层";
    private static final String 地牢塔防更多更快开关x= "地牢塔防更多更快开关";
    private static final String 地牢塔防老鬼开关x= "地牢塔防老鬼开关";
    private static final String 地牢塔防生成速度x= "地牢塔防生成速度";
    private static final String 地牢塔防波次x= "地牢塔防波次";
    private static final String 蛇皮走位x= "蛇皮走位";

    private static final String 之前健身x= "之前健身";
    private static final String 现在健身x= "现在健身";
    private static final String 单身x = "单身";
    private static final String 进阶x = "进阶";
    private static final String 幸运转世x= "幸运转世";
    private static final String 科学狂人x= "科学狂人";
    private static final String 重生怪物x = "重生怪物";
    private static final String 天赋x = "天赋";
    protected static final String 攻击范围getx    = "攻击范围get";
    protected static final String 视野范围getx    = "视野范围get";
    protected static final String 最大生命getx    = "最大生命get";
    protected static final String 最大护甲getx    = "最大护甲get";
    protected static final String 死亡次数x    = "死亡次数";
    protected static final String 破坏物体x    = "破坏物体";
    protected static final String 生命成长x    = "生命成长";
    protected static final String 攻击成长x    = "攻击成长";
    protected static final String 防御成长x    = "防御成长";
    protected static final String 攻速成长x    = "攻速成长";
    protected static final String 移速成长x    = "移速成长";
    protected static final String 命中成长x    = "命中成长";
    protected static final String 闪避成长x    = "闪避成长";
    protected static final String 暴伤成长x= "暴伤成长";
    protected static final String 吸血成长x    = "吸血成长";
    protected static final String 属性成长x    = "属性成长";
    protected static final String 暴率成长x    = "暴率成长";
    protected static final String 治疗成长x    = "治疗成长";
    protected static final String 穿甲成长x    = "穿甲成长";
    protected static final String 穿透成长x    = "穿透成长";
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
        bundle.put(魔力x, 魔力);
        bundle.put(武器力量x,武器力量);
        bundle.put(根骨x, 根骨);
        bundle.put(火把神x, 火把神);
        bundle.put(智力x, 智力);
        bundle.put(连击x, 连击);
        bundle.put(护甲力量x,护甲力量);
        bundle.put(投机之剑x,投机之剑);
        bundle.put(英精英雄x,英精英雄);
        bundle.put(英精英雄成长x,英精英雄成长);
        bundle.put(地牢塔防开关x,地牢塔防开关);
        bundle.put(地牢塔防去下一层x,地牢塔防去下一层);
        bundle.put(地牢塔防更多更快开关x,地牢塔防更多更快开关);
        bundle.put(地牢塔防老鬼开关x,地牢塔防老鬼开关);
        bundle.put(地牢塔防生成速度x,地牢塔防生成速度);
        bundle.put(地牢塔防波次x,地牢塔防波次);
        bundle.put(蛇皮走位x,蛇皮走位);

        bundle.put(之前健身x,之前健身);
        bundle.put(现在健身x,现在健身);
        bundle.put(单身x, 单身);
        bundle.put(进阶x, 进阶);
        bundle.put(幸运转世x,幸运转世);
        bundle.put(科学狂人x,科学狂人);
        bundle.put(重生怪物x, 重生怪物);
        bundle.put(天赋x, 天赋);
        bundle.put( 攻击范围getx, 攻击范围get);
        bundle.put( 视野范围getx, 视野范围get);
        bundle.put( 最大生命getx, 最大生命get);
        bundle.put( 最大护甲getx, 最大护甲get);
        bundle.put( 死亡次数x, 死亡次数);
        bundle.put( 破坏物体x, 破坏物体);

        bundle.put( 生命成长x, 生命成长);
        bundle.put( 攻击成长x, 攻击成长);
        bundle.put( 防御成长x, 防御成长);
        bundle.put( 攻速成长x, 攻速成长);
        bundle.put( 移速成长x, 移速成长);
        bundle.put( 命中成长x, 命中成长);
        bundle.put( 闪避成长x, 闪避成长);
        bundle.put(暴伤成长x,暴伤成长);
        bundle.put( 吸血成长x, 吸血成长);
        bundle.put( 属性成长x, 属性成长);
        bundle.put( 暴率成长x, 暴率成长);
        bundle.put( 治疗成长x, 治疗成长);
        bundle.put( 穿甲成长x, 穿甲成长);
        bundle.put( 穿透成长x, 穿透成长);

        bundle.put( 之前攻击延迟x, 之前攻击延迟);
        bundle.put(EXPERIENCE, 当前经验);

        bundle.put(HTBOOST, HTBoost);

        Bundle replacementsBundle1 = new Bundle();
        for(String s: 海克斯1.keySet()){
            replacementsBundle1.put(s, 符文(s));
        }
		bundle.put("海克斯1", replacementsBundle1);

        Bundle replacementsBundle2 = new Bundle();
        for(String s: 海克斯2.keySet()){
            replacementsBundle2.put(s, 符文(s));
        }
		bundle.put("海克斯2", replacementsBundle2);

        Bundle replacementsBundle3 = new Bundle();
        for(String s: 海克斯3.keySet()){
            replacementsBundle3.put(s, 符文(s));
        }
		bundle.put("3", replacementsBundle3);

        belongings.storeInBundle(bundle);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {

        等级 = bundle.getInt(LEVEL);
        力量 = bundle.getFloat(力量x);
        魔力 = bundle.getInt(魔力x);
        武器力量= bundle.getInt(武器力量x);
        根骨 = bundle.getInt(根骨x);
        火把神 = bundle.getInt(火把神x);
        智力 = bundle.getFloat(智力x);
        连击 = bundle.getInt(连击x);
        护甲力量= bundle.getInt(护甲力量x);
        投机之剑= bundle.getInt(投机之剑x);
        英精英雄= bundle.getInt(英精英雄x);
        英精英雄成长= bundle.getFloat(英精英雄成长x);
        地牢塔防开关= bundle.getBoolean(地牢塔防开关x);
        地牢塔防去下一层= bundle.getBoolean(地牢塔防去下一层x);
        地牢塔防更多更快开关= bundle.getBoolean(地牢塔防更多更快开关x);
        地牢塔防老鬼开关= bundle.getBoolean(地牢塔防老鬼开关x);
        地牢塔防生成速度= bundle.getInt(地牢塔防生成速度x);
        地牢塔防波次= bundle.getInt(地牢塔防波次x);
        蛇皮走位= bundle.getInt(蛇皮走位x);
        
        之前健身= bundle.getFloat(之前健身x);
        现在健身= bundle.getFloat(现在健身x);
        单身 = bundle.getBoolean(单身x);
        进阶 = bundle.getBoolean(进阶x);
        幸运转世= bundle.getInt(幸运转世x);
        科学狂人= bundle.getFloat(科学狂人x);
        重生怪物 = bundle.getString(重生怪物x);
        天赋 = bundle.getBooleanArray(天赋x);
        攻击范围get = bundle.getInt( 攻击范围getx );
        视野范围get = bundle.getInt( 视野范围getx );
        最大生命get = bundle.getFloat( 最大生命getx );
        最大护甲get = bundle.getFloat( 最大护甲getx );
        死亡次数 = bundle.getInt( 死亡次数x );
        破坏物体 = bundle.getInt( 破坏物体x );

        生命成长 = bundle.getFloat( 生命成长x );
        攻击成长 = bundle.getFloat( 攻击成长x );
        防御成长 = bundle.getFloat( 防御成长x );
        攻速成长 = bundle.getFloat( 攻速成长x );
        移速成长 = bundle.getFloat( 移速成长x );
        命中成长 = bundle.getFloat( 命中成长x );
        闪避成长 = bundle.getFloat( 闪避成长x );
        暴伤成长= bundle.getFloat(暴伤成长x);
        吸血成长 = bundle.getFloat( 吸血成长x );
        属性成长 = bundle.getFloat( 属性成长x );
        暴率成长 = bundle.getFloat( 暴率成长x );
        治疗成长 = bundle.getFloat( 治疗成长x );
        穿甲成长 = bundle.getFloat( 穿甲成长x );
        穿透成长 = bundle.getFloat( 穿透成长x );
        之前攻击延迟 = bundle.getFloat( 之前攻击延迟x );
        当前经验 = bundle.getInt(EXPERIENCE);

        HTBoost = bundle.getInt(HTBOOST);

        if (bundle.contains("海克斯1")){
            Bundle replacements = bundle.getBundle("海克斯1");

            for (String s : replacements.getKeys()){
                Boolean b = replacements.getBoolean(s);
                if (海克斯1.containsKey(s)) b = 符文(s);
                    try {
                        海克斯1.put(s,b);
                    } catch (Exception e) {
                        ShatteredPixelDungeon.reportException(e);
                    }
            }
        }
        if (bundle.contains("海克斯2")){
            Bundle replacements = bundle.getBundle("海克斯2");

            for (String s : replacements.getKeys()){
                Boolean b = replacements.getBoolean(s);
                if (海克斯2.containsKey(s)) b = 符文(s);
                    try {
                        海克斯2.put(s,b);
                    } catch (Exception e) {
                        ShatteredPixelDungeon.reportException(e);
                    }
            }
        }
        if (bundle.contains("海克斯3")){
            Bundle replacements = bundle.getBundle("海克斯3");

            for (String s : replacements.getKeys()){
                Boolean b = replacements.getBoolean(s);
                if (海克斯3.containsKey(s)) b = 符文(s);
                    try {
                        海克斯3.put(s,b);
                    } catch (Exception e) {
                        ShatteredPixelDungeon.reportException(e);
                    }
            }
        }

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
                if (f == talent) return 天赋点数修正(tier.get(f),f.最大点数());
            }
        }
        return 0;
    }

    public int 天赋点数修正(int value,int max) {
//        升级天赋
        return GameMath.之内(0,value,max);
    }

    public float 天赋生命力(Talent talent) {
        return 生命力(天赋点数(talent));
    }

    public float 天赋生命力(Talent talent, float x) {
        return 生命力(天赋点数(talent, x));
    }

    public boolean 满天赋(Talent talent) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) return 天赋点数修正(tier.get(f),f.最大点数()) >= f.最大点数();
            }
        }
        return false;
    }

    public boolean 天赋概率(Talent talent, int x) {
        return Random.Int(1, 100) <= 天赋点数(talent,x) + (x == 33 ? 1 : 0);
    }

    public int 天赋点数(Talent talent, int x) {
        for (LinkedHashMap<Talent, Integer> tier : talents) {
            for (Talent f : tier.keySet()) {
                if (f == talent) {
//                    if (f.最大点数() == 2) {
//                        if (天赋点数修正(tier.get(f),f.最大点数()) == 1) {
//                            return x;
//                        } else if (天赋点数修正(tier.get(f),f.最大点数()) == 2) {
//                            return Math.round(x * 1.5f);
//                        }
//                    }
                    if (f.最大点数() >= 1) {
                        return 天赋点数修正(tier.get(f),f.最大点数()) * x;
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
//                            if (天赋点数修正(tier.get(f),f.最大点数()) == 1) {
//                                return x;
//                            } else if (天赋点数修正(tier.get(f),f.最大点数()) == 2) {
//                                return 1.5f * x+0.01f;
//                            }
//                        }else{
//                            if (天赋点数修正(tier.get(f),f.最大点数()) == 1) {
//                                return x;
//                            } else if (天赋点数修正(tier.get(f),f.最大点数()) == 2) {
//                                return 1.5f * x;
//                            }
//                        }
//                    }
                    if (f.最大点数() == 3) {
                        if(x==0.33f)
                        return 天赋点数修正(tier.get(f),f.最大点数()) * x+0.01f;
                        else
                        return 天赋点数修正(tier.get(f),f.最大点数()) * x;
                    }
                    if (f.最大点数() >= 1) {
                        return 天赋点数修正(tier.get(f),f.最大点数()) * x;
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
            super.hitSound(pitch * GameMath.之内(0.75f,1.25f-0.025f*力量(),1f));
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
    public static int tier(HeroClass heroClass,Armor armor) {
        if (armor instanceof ClassArmor) {
            return 6;
        }
        if(heroClass==HeroClass.凌云){
            return 0;
        }
        //        if(heroSubClass(subClass.合金忍者)){
        //            return 6;
        //        }
        if(heroClass==HeroClass.灵猫||heroClass==HeroClass.鼠弟){
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

        x*=增加命中();


        return Math.round(x);
    }
    @Override
    public float 增加命中() {
        float x=0;
        x+=天赋点数(Talent.集中,0.1f);
        x *= 综合属性();
        x*=命中之戒.命中(this);
        return x;
    }
    @Override
    public int 最大命中(Char target) {
        KindOfWeapon wep = belongings.attackingWeapon();
        float accuracy=Math.round((最大命中 + (等级 - 1) * 1.2f));
        accuracy+=命中成长;
        accuracy+=天赋点数(Talent.顶福精华, 10);
        if(符文("属性转换"))accuracy+=最大闪避(target);

        float accuracyx=1;
        if (belongings.armor() instanceof 武服) {
            accuracyx+=.15f;
        }
        accuracyx+= 天赋点数(Talent.顶福精华, 0.13f);

        if(wep==null){
            accuracyx+=(力量()-10)*属性增幅;
        }
        accuracyx+= 增加命中();

        accuracyx+=英精英雄成长;

        accuracy*=神圣之剑.减少();
        if(英精英雄==4)accuracyx*=4;

        accuracy*=accuracyx;
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
        
        if(heroClass(HeroClass.女忍)){
            evasion+=最大闪避(enemy)*0.1f;
        }
        
        evasion+=天赋点数(Talent.招架);
        evasion*=增加闪避();
        return Math.round(evasion);
    }

    @Override
    public float 增加闪避() {
        float x=0;
        if(重生怪物.equals("下水道巨蛇"))x+=5f;
        x+=天赋点数(Talent.躲避,0.075f);

        x+=天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        if(符文("最后生还者")&&半血以下())x*=1.45;
        if(符文("会心闪避"))x=暴击(null,x);
        x *= 综合属性();
        x*=闪避之戒.闪避(this);

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

        evasion += 天赋点数(Talent.顶福精华, 5);

        float evasionx=1;
        evasionx+= 天赋点数(Talent.顶福精华, 0.08f);

        if(裸衣()){
            evasionx+=(力量()-10)*属性增幅;
        }else if(天赋(Talent.快速布阵)){
            evasionx+=(力量()-10)*属性增幅*(天赋点数(Talent.快速布阵,0.5f));
        }
        evasionx+=英精英雄成长;
        if (belongings.armor instanceof 风衣) {
            evasionx+=.1f;
        }
        evasionx+=增加闪避();

        if(英精英雄==4)evasionx*=4;
        if (paralysed > 0) {
            evasionx /= 2;
        }
        if(heroClass(HeroClass.凌云)){
            evasionx *= 2;
        }
        evasion*=evasionx;

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
    public float 最小防御() {
        float dr =super.最小防御();
        
        if (belongings.armor() != null) {
            float armDr = belongings.armor().最小防御();
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
        return dr;
    }
    
    @Override
    public float 增加防御(){
        float x=1;
        x+=天赋点数(Talent.全副武装,0.2f);
        if(天赋(Talent.砂之守护)){
            int 墙体数量=0;
            for (int i : PathFinder.相邻8){
                if(Dungeon.level.在墙体(i+pos))墙体数量++;
            }
            x+=天赋点数(Talent.砂之守护,0.1f)*墙体数量;
        }
        x-=虚无透纱.减少();
        if(符文("魔抗转防御"))
        x+=1/RingOfElements.resist(this,Char.魔法伤害.class);

        if(符文("乌龟")) x+=0.5f;
        if(符文("玄武")) x+=1.5f;
        if(符文("血气方刚"))x+=吸血()+全能吸血();
        if(符文("风语者的祝福")) x+=治疗效果()*1.5f;
        if(符文("站在布隆后面")&&belongings.weapon!=null&belongings.weapon.盾()) x+=0.5f;
        if(belongings.armor instanceof ClothArmor&&符文("铁布衫"))x+=5.5;
        if(belongings.armor instanceof ScaleArmor&&符文("龙鳞甲")) x+=2;

        if(符文("升级综合属性"))x*=综合属性();
        return x;
    }
    
    //变相削弱
    @Override
    public float 最大防御() {
        float dr =super.最大防御()+防御成长;

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
            float armDr = belongings.armor().最大防御();
            if (力量() < belongings.armor().力量() && !heroClass(HeroClass.重武)) {
                armDr -= 2 * (belongings.armor().力量() - 力量());
            }
            if (armDr > 0) dr += armDr;
        }
        if (belongings.weapon() != null && !武力之戒.fightingUnarmed(this)) {
            float wepDr = belongings.weapon().defenseFactor(this);
            if (力量() < belongings.weapon().力量() && !heroClass(HeroClass.重武)) {
                wepDr -= 2 * (belongings.weapon().力量() - 力量());
            }
            if (wepDr > 0) dr += wepDr;
        }
        if (buff(HoldFast.class) != null) {
            dr += buff(HoldFast.class).armorBonus();
        }

        if(Dungeon.系统(系统设置.金币能力)){
            dr+=Dungeon.gold/100f;
        }

        荆棘斗篷.Thorns thorns = buff(荆棘斗篷.Thorns.class);
        if(符文("升级荆棘斗篷"))
        if (thorns != null) {
            dr+=(thorns.itemLevel()+1)*最大护甲();
        }
        if(符文("防御的宠爱"))dr+=第x次防御*0.05f;
        if(符文("装备黄金之心"))dr+=10;
        if(符文("铜皮铁骨"))dr+=力量()*4.5f;
        if(符文("攻击转防御"))dr+=最大攻击()/2f;

        if(天赋(Talent.戒护环法)){
            int 戒指数量=0;
            for(Item i: belongings.backpack){
                if(i instanceof Ring r)
                    戒指数量+=1+r.强化等级();
            }
            dr+=戒指数量*天赋点数(Talent.戒护环法,0.35f);
        }
        if(符文("防具收集家")){
            int 防具数量=0;
            for(Item i: belongings.backpack){
                if(i instanceof Armor a)
                    防具数量+=a.tier+a.强化等级();
            }
            dr+=防具数量*3;
        }
        if (heroClass(HeroClass.血鬼)) {
            dr+=最大生命(0.05f);
        }
        dr+=天赋点数(Talent.坚韧,1.5f);
        
        if(hasbuff(装甲之戒.装甲.class)){
            
            if(belongings.armor()!=null){
                dr+=belongings.armor().augment.defenseFactor(2*(1+装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class)/1.5f)
                        );
            }else{
                dr+=装甲之戒.tier()*2*(1+装甲之戒.getBuffedBonus(this,装甲之戒.装甲.class)/1.5f);
            }
        }

        dr*=1+天赋点数(Talent.钢铁甲胄,0.4f);
        dr*=增加防御();
        return dr;
    }
    public boolean 空手(){
        return 武力之戒.fightingUnarmed(this);
    }
    public boolean 裸衣(){
        return belongings.armor==null;
    }
    public boolean 女人(){
        return heroClass(HeroClass.HUNTRESS)||
               heroClass(HeroClass.DUELIST)||
               heroClass(HeroClass.巫女)||
               heroClass(HeroClass.女忍)||
               heroClass(HeroClass.逐姝)||
               heroClass(HeroClass.罗兰)
                ;
    }
    @Override
    public float 最小攻击() {
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
                if(wep.拳套()){
                    dmg += 武力之戒.heromin();
                    if(hasbuff(武力之戒.Force.class)){
                        dmg += 武力之戒.min();
                    }
                }
            }
        }

        dmg+=最小防御()*(天赋点数(Talent.以攻为守,0.25f));
        dmg+=天赋点数(Talent.技巧,1.5f);
        
        dmg*=破损短剑.增加();
        dmg*=增加攻击();
        if (dmg < 0) dmg = 0;
        return dmg;
    }
    
    @Override
    public float 增加攻击(){
        float x=1;
        if(职业精通()&&subClass(HeroSubClass.狂战士)){
            x+=Buff.施加(this,怒气.class).怒气*0.0025f;
        }
        if(heroClass(HeroClass.行僧)){
            x+=移速()*0.15f;
        }

        x+=狂妄皇冠.攻击();
        if(subClass(HeroSubClass.灵月杀手)){
            x+=0.1f+(职业精通()?0.1f:0);
        }
        if (hasbuff(Momentum.class)){
            x += buff(Momentum.class).evasionBonus(this);
        }
        if(hasbuff(Invisibility.class))
            x+=天赋点数(Talent.蓄势待发,0.15f);
        x+=英精英雄成长;
        if(符文("空手道")&&空手())x+=7.5f;
        if(符文("亮出你的剑"))x+=0.1f*攻击范围get;
        if(符文("我身为汽车维修")&&belongings.weapon instanceof 修理扳手) x+=6.5f;

        if(符文("站在布隆后面")&&belongings.weapon!=null&belongings.weapon.盾()) x+=0.5f;
        if(符文("我有一剑")&&belongings.weapon!=null&belongings.weapon.剑()) x+=0.3f;
        if(符文("我懂个锤子")&&belongings.weapon!=null&belongings.weapon.锤()) x+=0.4f;
        if(符文("盘古开天")&&belongings.weapon!=null&belongings.weapon.斧()) x+=0.35f;
        if(符文("齐天大圣")&&belongings.weapon!=null&belongings.weapon.棍()) x+=0.45f;
        if(符文("闪电五连鞭")&&belongings.weapon!=null&belongings.weapon.鞭()) x+=0.5f;


        float delay = 之前攻击延迟;
        if (SPDSettings.固定攻速() == 1) {
            x/=delay;
        }
        if(符文("升级综合属性"))x*=综合属性();
        return x;
    }
    
    @Override
    public float 最大攻击() {
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
                if(wep.拳套()){
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
            dmg+=Dungeon.gold/50f;
        }
        if(符文("武器收集家")){
            int 武器数量=0;
            for(Item i: belongings.backpack){
                if(i instanceof Weapon a)
                    武器数量+=a.tier+a.强化等级();
            }
            dmg+=武器数量*5;
        }
        if(天赋(Talent.破刺戒冲)){
            int 戒指数量=0;
            for(Item i: belongings.backpack){
                if(i instanceof Ring r)
                    戒指数量+=1+r.强化等级();
            }
            dmg+=戒指数量*天赋点数(Talent.破刺戒冲,0.5f);
        }
        if(符文("攻击的宠爱"))dmg+=第x次攻击*0.05f;
        if(符文("防御转攻击"))dmg+=最大防御();

        if(符文("属性转换"))dmg+=最大防御();
        if(符文("残暴之力"))dmg+=35f;
        dmg+=最大防御()*(天赋点数(Talent.以攻为守,0.25f));
        征服 z=buff(征服.class);
        if(z!=null)dmg+=z.层数*天赋点数(Talent.战争热诚,3);
        dmg+=攻击成长;
        dmg+=天赋点数(Talent.猛攻,2.5f);
        dmg*=破损短剑.减少();
        dmg*=增加攻击();
        if (dmg < 0) dmg = 0;
        return dmg;
    }

    //damage rolls that come from the hero can have their RNG influenced by clover
    public static float heroDamage(float min,float max) {
        if (Random.Float() < ThirteenLeafClover.alterHeroDamageChance()) {
            return ThirteenLeafClover.alterDamageRoll(min, max);
        } else {
            return Random.NormalFloat(min, max);
        }
    }

    @Override
    public float 移速() {

        float speed = super.移速();
        if (belongings.armor() != null) {
            speed = belongings.armor().speedFactor(this, speed);
        }
        //region +
        float 移速=1;

        if(裸衣()){
            移速+=(力量()-10)*属性增幅/2f;
        }else if(天赋(Talent.快速布阵)){
            移速+=(力量()-10)*属性增幅/2f*(天赋点数(Talent.快速布阵,0.5f));
        }

        if(天赋(Talent.浮石冲浪)){
            int 墙体数量=0;
            for (int i : PathFinder.相邻8){
                if(Dungeon.level.在墙体(i+pos))墙体数量++;
            }
            移速+=天赋点数(Talent.浮石冲浪,0.1f)*墙体数量;
        }
        if (buff(极速药剂.HTBoost.class)!=null) {
            移速+= 7.5f;
        }
        if(符文("踢踏舞")&&hasbuff(战斗状态.class)&&hasbuff(踢踏舞.class)){
            移速+= buff(踢踏舞.class).count/10f;
        }
        if(hasbuff(Invisibility.class))
            移速+=天赋点数(Talent.偷袭姿态,0.2f);

        移速+=天赋点数(Talent.速跑,0.075f);

        if(符文("嗜血")){
            for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                if (mob.alignment == Alignment.ENEMY && Dungeon.level.heroFOV[mob.pos]) {
                    if(!mob.满血())
                        移速+=0.1f;
                }
            }
        }
        移速+=移速成长;
        if(subClass(HeroSubClass.神兽之灵)&&职业精通()){
            boolean 移速提升=false;
            for(Mob m: getVisibleEnemies()){
                if(m.distance(this)>1){
                    移速提升=true;
                    break;
                }
            }
            if(移速提升)移速+=.3f;
        }
        移速+=天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        if(天赋(Talent.追捕猎物)){
            boolean 残血=false;
            boolean 半血=false;
            for(Mob m:getVisibleEnemies()){
                if(m.残血()){
                    残血=true;
                    break;
                }
                if(m.半血()){
                    半血=true;
                    break;
                }
            }
            if(残血)
                移速+=天赋点数(Talent.追捕猎物,0.3f);
            else if(半血)
                移速+=天赋点数(Talent.追捕猎物,0.15f);
        }
        if(nobuff(战斗状态.class)&&符文("家园卫士"))移速+=1;

        if(Dungeon.hero.重生怪物.equals("下水道螃蟹")){
            移速+=1;
        }
        if (belongings.armor instanceof 披风) {
            移速+=.1f;
        }
        if (belongings.armor instanceof 忍服) {
            移速+= .05f;
        }
        if (heroClass(HeroClass.女忍)) {
            移速+=.2f*根据已损失生命();
        }
        if (在水中()) {
            if (heroClass(HeroClass.盗贼)) {
                移速+= .1f;
            }
            if (heroClass(HeroClass.机器)) {
                移速-= 0.3f;
            }
        }

        //endregion

        Momentum momentum = buff(Momentum.class);
        if (momentum != null) {
            ((HeroSprite) sprite).sprint(momentum.freerunning() ? momentum.speedMultiplier() : 1f);
            speed *= momentum.speedMultiplier();
        } else {
            ((HeroSprite) sprite).sprint(1f);
        }
        移速*=巨大蟹钳.减少();

        if(!符文("走完红色路线"))
        移速*=中国国旗.移速();
        if(hasbuff(鬼刀.class))移速*=4;
        移速 *= 疾速之戒.speedMultiplier(this);

        移速*=1+天赋点数(Talent.诡异身法,0.125f);

        if(符文("科学狂人")&&科学狂人==-0.3f)
            移速*=1.6f;

        移速*= 综合属性();
        //region x
        speed*=移速;

        if(hasbuff(Invisibility.class)){
            if(belongings.weapon instanceof 变态刀&&!符文("升级变态刀"))
                speed=0.75f;
        }
        if(符文("古神"))speed*=0.85f;
        if(符文("乌龟"))speed*=0.9f;
        if(符文("玄武"))speed*=0.7f;
        if(符文("最后生还者")&&半血以下())speed*=1.25f;
        if(符文("最后生还者")&&半血以下())speed*=1.25f;
        if(符文("沃尔夫冈")&&hasbuff(Hunger.class)){
            if(buff(Hunger.class).level>150&&buff(Hunger.class).level<300)
                speed*=1.1f;
        }
        if(符文("轻装上阵"))speed*=1.3f;
        //endregion

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

        float delay = 1;
        if(空手())delay=0.5f;

    if (!武力之戒.fightingUnarmed(this)) {
        delay =belongings.attackingWeapon().delayFactor(this);

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

    //region +
    float 攻速=1;
    攻速+=天赋点数(Talent.快攻,0.1f);
    攻速+=狂怒之戒.attackSpeedMultiplier(this)-1;
    if(空手()){
        攻速+=(力量()-10)*属性增幅/2f;
    }
    if(符文("嗜血")){
        for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
            if (mob.alignment == Alignment.ENEMY && Dungeon.level.heroFOV[mob.pos]) {
                if(!mob.满血())
                    攻速+=0.3f;
            }
        }
    }
    if(重生怪物.equals("矮人武僧"))
        攻速+=1;
    if(符文("装备疾射火炮"))攻速+=0.35f;
    if(符文("砍伤"))攻速+=0.5f;
    if(符文("属性转换"))攻速+=移速();
    if(第x次攻击%2==0){
        if(符文("左拳伤害高右拳高伤害"))
            攻速+=0.15f;
    }

    if (天赋(Talent.连击强化)) {
        攻速+=Buff.施加(this, 连击.class).count*天赋点数(Talent.连击强化,0.075f);
    }

    if(符文("踢踏舞"))
        攻速+=移速()*0.15f;
    if(符文("灵巧"))
        攻速+=.6f;

    if(符文("亮出你的剑"))攻速+=0.1f*攻击范围get;
    if(符文("一板一眼"))攻速+=0.25f;

    攻速+=攻速成长;

    if (buff(极速药剂.HTBoost.class)!=null) {
        攻速+= 7.5f;
    }
    征服 z=buff(征服.class);
    if(z!=null)攻速+=z.层数*天赋点数(Talent.致命节奏,0.04f);

    if(天赋(Talent.兽性猎手)){
        boolean 残血=false;
        boolean 半血=false;
        for(Mob m:getVisibleEnemies()){
            if(m.残血()){
                残血=true;
                break;
            }
            if(m.半血()){
                半血=true;
                break;
            }
        }
        if(残血)
            攻速+=天赋点数(Talent.兽性猎手,0.4f);
        else if(半血)
            攻速+=天赋点数(Talent.兽性猎手,0.2f);
    }

    //endregion
    //region x
    delay/=攻速;

    delay/=综合属性();

    if(符文("双刀流"))delay/=1.2f;
    if(符文("闪电打击"))delay/=1.2f;
    if(符文("空手道"))delay*=2;
    //endregion

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
            if (SPDSettings.固定攻速() == 1||subClass(HeroSubClass.灵月杀手)||符文("一板一眼")) {
                if(之前攻击延迟!=1)
                之前攻击延迟=delay;
                return delay <= 1 ? 1 : delay;
            }
            return delay;
    }

    @Override
    public void spend(float time) {

        if(Dungeon.系统(系统设置.时间能力)){
            time*=0.2f;
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
        if (subClass(HeroSubClass.冰结师)) {
            immunes.add(Chill.class);
            immunes.add(Frost.class);
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
    public void 属性锻造(String s,float x){

        switch(s){
            case "最大攻击":
                攻击成长+=x;
                break;
            case "最大防御":
                防御成长+=x;
                break;
            case "生命":
                生命成长+=x;
                更新数据();
                break;
            case "力量":
                力量+=x;
                break;
            case "攻速":
                攻速成长+=x;
                break;
            case "移速":
                移速成长+=x;
                break;
            case "最大命中":
                命中成长+=x;
                break;
            case "最大闪避":
                闪避成长+=x;
                break;
            case "暴击伤害":
                暴伤成长+=x;
                break;
            case "全能吸血":
                吸血成长+=x;
                break;
            case "暴击率":
                暴率成长+=x;
                break;
            case "穿甲":
                穿甲成长+=x;
                break;
            case "治疗效果":
                治疗成长+=x;
                break;
            case "护甲穿透":
                穿透成长+=x;
                break;
        }
    }
    public void 更新数据(){

        if(heroClass(HeroClass.来世)){
            Dungeon.gold=Statistics.金币;
            Dungeon.energy=Statistics.能量;
        }
        float x=0;
        if(符文("科学狂人"))x+=科学狂人;
        if(符文("歌利亚巨人"))x+=0.1f;
        if(符文("古神"))x+=0.3f;
        if(符文("巨型坦克"))x+=0.2f;
        if(符文("巨人杀手"))x-=0.1f;
        if(符文("沃尔夫冈")&&hasbuff(Hunger.class)){
            if(buff(Hunger.class).level<150)
                x+=0.1f;
        }
        if(女人())x-=0.1f;
        if(符文("缩小引擎"))x-=0.02f*等级;
        if(符文("坦克引擎"))x+=0.01f*等级;
        if(belongings.hasItem(心之钢.class))x+=(1+belongings.getItem(心之钢.class).等级())*0.025f;
        大小=1+x;

        更新属性();
        Dungeon.observe();
        Item.updateQuickslot();
        GameScene.updateFog();
    }
    public float 治疗效果(){
        float x=1;
        x+=治疗成长;
        if(符文("吸血鬼"))x+=吸血()+全能吸血();
        if(符文("急救用具"))x+=0.25f;
        if(符文("多克特"))x+=0.60f;
        if(符文("蛋白粉奶昔"))x+=0.175f+最大防御()/200f;
        if(Dungeon.炼狱(炼狱设置.治疗禁令))x-=0.5f;
        x+=天赋点数(Talent.死亡抗拒,0.15f)*根据已损失生命();

        return x;
    }
    @Override
    public boolean act(){
        //        算法.调试(Holiday.getCurrentHoliday()+"");

        if(subClass(HeroSubClass.冰结师)){
            GameScene.add(Blob.seed(pos,Math.round(5*(1+天赋点数(Talent.寒冰之境,0.25f))),Freezing.class));
            if(职业精通())
            for (int n : PathFinder.相邻4)
                GameScene.add(Blob.seed(pos+n, Math.round(5*(1+天赋点数(Talent.寒冰之境,0.25f))), Freezing.class));
        }
        Dungeon.地牢时间++;
        if(Dungeon.地牢时间%2400==0){
            Dungeon.地牢天数++;
            int 地牢天数=Dungeon.地牢天数;

            if(符文("解除人体限制的诅咒")){
                属性成长+=0.001f;
                if(Dungeon.地牢天数>=Dungeon.地牢寿命)
                    死亡时(null);
            }
            if(符文("对冲基金"))
                Dungeon.gold(Math.round(Dungeon.gold*0.025f*Dungeon.地牢天数));
        }

        if (符文("最终祝福")&&等级 >= 最大等级)
            Buff.延长(this, Bless.class, 1);

        if(符文("装备黄金之心"))Dungeon.gold(5);
        if(符文("你肩上的恶魔")){
            if(视野敌人())
            受伤(生命(0.05f));
            else
            受伤(生命(0.005f));
        }
        if(Dungeon.派对(派对设置.海克斯)&&算法.isDebug()){
            if(Random.Int(19)==0)
            选择海克斯(随机海克斯());
            int x1=海克斯1.size();
            int x2=海克斯2.size();
            int x3=海克斯3.size();
            for(boolean b:海克斯1.values()){
                if(b)x1--;
            }
            for(boolean b:海克斯2.values()){
                if(b)x2--;
            }
            for(boolean b:海克斯3.values()){
                if(b)x3--;
            }
            算法.调试("海克斯1级还剩"+x1+"个");
            算法.调试("海克斯2级还剩"+x2+"个");
            算法.调试("海克斯3级还剩"+x3+"个");
        }



        if(belongings.weapon instanceof 大肉棒 x){
            x.保鲜--;
            if(x.保鲜==0){
                belongings.weapon=null;
            }
        }
        if (Dungeon.地牢时间>=1950||Dungeon.地牢时间<=450) {
            if(算法.概率学(1/2400f*(符文("300颗够吗")?300:1)))
                Dungeon.level.dropRandomCell(new 坠牢之星(),pos);
        }
        if(符文("红包")&&算法.概率学(1/240f))
            new 红包().放背包();

        if(符文("慢炖")){
            for (int n : PathFinder.范围2){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    Buff.施加(c,慢炖.class).set(1);
                    c.受伤(最大生命(0.005f)*c.buff(慢炖.class).count);
                }
            }
        }
        if(符文("装备无终恨意")&&nobuff(无终恨意冷却.class)){
            for (int n : PathFinder.范围2){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    c.受伤(最大生命(0.1f));
                    回百分比血(0.1f);
                    Buff.施加(this,无终恨意冷却.class,5);
                }
            }
        }
        if(符文("快炖")){
            受伤(生命(0.01f));
            for (int n : PathFinder.范围2){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                    Buff.施加(c,快炖.class).set(1);
                    c.受伤(最大生命(0.01f)*c.buff(快炖.class).count);
                }
            }
        }

        if(符文("草丛伦")&&在草丛())
            Buff.延长(this,Invisibility.class,1);

        if (subClass == HeroSubClass.疾行者) {
            Buff.施加(this, Momentum.class).gainStack();
        }
        if (subClass == HeroSubClass.时间刺客&&nobuff(Swiftthistle.TimeBubble.class)) {
            Buff.施加(this, 时间能力.class).gainStack();
        }
        if(visibleEnemies()>0){
            for(Mob m:getVisibleEnemies()){
                if(m.alignment==Alignment.ENEMY){
                    Buff.施加(m,被发现.class,1);
                }
            }
        }
        if(蛇皮走位<6){
            ++蛇皮走位;
            if(蛇皮走位==6&&sprite!=null){
                if(符文("额外闪现")&&!belongings.hasItem(StoneOfBlink.class))new StoneOfBlink().放背包();

                sprite.说("状态很好！");
                sprite.歪嘴();
            }
        }


        if(++变脸>=3){
            变脸=0;
            sprite.hideEmo();
        }
        Dungeon.level.落石(this);
        
        if(骸骨左轮.减少()<0){
            受伤(骸骨左轮.减少());
        }

        if(heroClass(HeroClass.血鬼)){
            if(天赋(Talent.适应身体)){
                回百分比血(天赋点数(Talent.适应身体,0.0025f));
            }else{
                受伤(0.01f);
            }
        }

        if(heroClass(HeroClass.机器)&&在水中()){
            受伤(0.1f);
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
                int 强度=(Dungeon.level.width()+1)/2+Dungeon.区域();//15=>7 17=>8 19=>9 21=>10
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
                            传送卷轴.周身瞬移(mob,Dungeon.level.exit());
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
                            传送卷轴.周身瞬移(mob,Dungeon.level.exit());
                        }else{
                            mob.viewDistance=Dungeon.level.width()+1;
                            mob.大小=0.95f+地牢塔防波次*0.05f;
                            mob.生命=mob.最大生命=强度*地牢塔防波次;
                            mob.state=mob.HUNTING;
                            mob.target(Dungeon.level.entrance());
                            GameScene.add(mob);
                            传送卷轴.周身瞬移(mob,Dungeon.level.exit());
                        }
                    }
                }
            }
        }
        float 护甲恢复=1+天赋点数(Talent.硬肤,0.125f);
        if(belongings.armor!=null&&belongings.armor.荣誉纹章!=null)护甲恢复+=belongings.armor.荣誉纹章.等级()*0.15f;
        if(符文("护甲是生命"))
            护甲恢复+=buff(再生.class).partialRegen*2;
        if(Dungeon.赛季(赛季设置.鬼怨地牢)) 护甲恢复*=1.5f;
        if(subClass(HeroSubClass.皇室卫兵))
            护甲恢复*=5+(职业精通()?4:0);
        if(符文("恢复恢复"))护甲恢复*=3.5f;
        if(符文("升级荣耀纹章"))护甲恢复*=6.5f;
        护甲恢复*=恢复之戒.恢复(this);
        护甲(1/150f*护甲恢复);
        
        if(hasbuff(ElixirOfFeatherFall.FeatherBuff.class))
        for (int n : PathFinder.相邻8){
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
                    spendConstant(TIME_TO_REST*3*SPDSettings.休息速度());
                }else{
                    spendConstant(TIME_TO_REST*3*SPDSettings.休息速度());
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
    public float 最大护甲(){
        float 最大护甲=3+等级;
        if(进阶)最大护甲+=5;
        if(heroClass(HeroClass.逐姝))最大护甲+=等级/2f;
        if(符文("力量的护甲"))最大护甲+=力量();
        if(符文("全副武装"))最大护甲+=最大生命get*0.7f*3;
        if(符文("升级荣耀纹章")&&belongings.armor!=null&&belongings.armor.荣誉纹章!=null)最大护甲+=belongings.armor.tier+belongings.armor.强化等级();


        if(符文("钢铁意志"))最大护甲*=1+根据已损失生命()*3;
        if(符文("轻装上阵"))最大护甲*=0.8f;
        if(符文("我是Evan"))最大护甲/=2f;
        最大护甲*=综合属性();

        if(符文("淬体")){
            最大护甲get=最大护甲;
            最大护甲=0;
        }
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
                        for (int i : PathFinder.相邻8) {
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
                            破坏物体++;
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
                            破坏物体++;
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

                        for (int i : PathFinder.自相邻8) {
                            Dungeon.level.discoverable[action.dst + i] = true;
                        }
                        for (int i : PathFinder.自相邻8) {
                            GameScene.updateMap(action.dst + i);
                        }

                        if (crystalAdjacent) {
                            sprite.parent.add(new Delayer(0.2f) {
                                @Override
                                protected void onComplete() {
                                    boolean broke = false;
                                    for (int i : PathFinder.相邻8) {
                                        if (Dungeon.level.map[action.dst + i] == Terrain.MINE_CRYSTAL) {
                                            Splash.at(action.dst + i, 0xFFFFFF, 5);
                                            Level.set(action.dst + i, Terrain.EMPTY);
                                            broke = true;
                                        }
                                    }
                                    if (broke) {
                                        Sample.INSTANCE.play(Assets.Sounds.SHATTER);
                                    }

                                    for (int i : PathFinder.自相邻8) {
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
            if(符文("冥想")){
                Buff.施加(this,冥想.class,4);
            spendAndNextConstant(TIME_TO_REST*4);
            回已损失血(0.25f);
            }
            else
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
        if(符文("暴击率的宠爱")) 暴击率+=第x次攻击*0.001f;
        if(符文("战斗狂怒")) 暴击率+=根据已损失生命()*100;
        if(符文("浪客之道")) 暴击率+=25;
        if(符文("临终轻语")) 暴击率+=25;
        if(符文("装备疾射火炮")) 暴击率+=25;
        if(符文("关键暴击")) 暴击率+=50;
        if(符文("会心治疗")) 暴击率+=25;
        if(符文("会心闪避")) 暴击率+=25;
        if(符文("灵魂虹吸")) 暴击率+=25;
        if(符文("珠光护手")) 暴击率+=25;
        if(符文("易损")) 暴击率+=25;
        if(符文("会心防御")) 暴击率+=25;
        if(符文("升级无尽之刃")) 暴击率+=25;
        暴击率+=暴率成长;
        if (天赋(Talent.洪荒之怒)) {
            怒气 怒气= Buff.施加(this,怒气.class);
            暴击率+=怒气.怒气*天赋点数(Talent.洪荒之怒,0.15f);
        }
        暴击率+=角斗链枷.暴击率()*根据已损失生命();
        暴击率+=天赋点数(Talent.破绽,5);
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

        if(符文("浪客之道")) 暴击率*=2.5f;
        return 暴击率;
    }
    @Override
    public float 暴击伤害(){
        float 暴击伤害=super.暴击伤害();
        if(暴击率()>100){
            if(符文("暴伤暴伤"))
            暴击伤害+=暴击率()/100f;
            else 暴击伤害+=暴击率()/300f;
        }
        if(belongings.attackingWeapon() instanceof 无尽之刃){
            暴击伤害+=0.3f;
        }
        暴击伤害+= 暴伤成长;


        if(符文("升级无尽之刃")&&belongings.weapon instanceof 无尽之刃)暴击伤害+=暴击率()/200f;

        if(hasbuff(Invisibility.class)){
            if(belongings.weapon instanceof 变态刀)
                暴击伤害*=1.2f;
            if(符文("升级变态刀"))
                暴击伤害*=1.2f;
        }

        if(符文("浪客之道"))
            暴击伤害*=0.9f;

        return 暴击伤害;
    }
    @Override
    public float 暴击(final Char enemy,float dmg){
        boolean 暴击了=false;
        if(enemy!=null){
            if(enemy.第x次防御<=神圣之剑.增加())
                必暴=true;

            if(符文("交锋")&&enemy.满血())
                必暴= true;
        }
        if(hasbuff(Invisibility.class)){
            if(belongings.weapon instanceof 变态刀)
                必暴=true;
        }
        if(符文("狂怒")&&算法.概率学(暴击率()))
            dmg*=暴击伤害();

        if((必暴||算法.概率学(暴击率()))){
            if(符文("会心治疗")) dmg*=0.75f;

            dmg*=暴击伤害();
            if(符文("暴击暴击")&&(必暴||算法.概率学(暴击率()))) dmg*=1.45f;
            if (subClass == HeroSubClass.狂战士) {
                Buff.施加(this,怒气.class).damage();
            }
            if(符文("暴击不暴击"))dmg*=1.45f;
            if (!Document.ADVENTURERS_GUIDE.isPageRead(Document.暴击)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.暴击);
            }
            if(sprite!=null){
                sprite.说("暴击！");
                sprite.歪嘴();
            }
            暴击了=true;
            必暴=false;
            x次必暴=0;
        }else {

            x次必暴++;
            if(暴击率()!=0&&x次必暴>=600/暴击率()){
                必暴=true;
                sprite.说("手感火热！");
            }
        }
        if(暴击了&&符文("冰霜之书")&&enemy!=null)算法.修复效果(()->{
            Buff.施加(enemy,Frost.class,Frost.DURATION);
        });
        if(符文("灵魂虹吸")) 回血(dmg*0.125f);
        return dmg;
    }

    public boolean 攻击范围(Char c) {
        return distance(c)<=攻击范围();
    }
    @Override
    public int 攻击范围(){
        int x =1;
        if(belongings.weapon!=null){
            x=belongings.weapon.reachFactor(this);
        }

        if(英精英雄==3)
            x++;
        if(英精英雄==1)
            x+=3;

        x+=天赋点数(Talent.控物术法,2);
        x+=天赋点数(Talent.杀意波动);
        if(符文("范围的宠爱")&&belongings.weapon!=null){
            x+=belongings.weapon.强化等级();
        }
        if(符文("虚空之眼")){
            x+=视野范围()/3;
        }
        if(符文("瞄准镜")){
            if(x>3)x+=1;
            else x+=2;
        }
        if(符文("万用瞄准镜")){
            if(x>3)x+=2;
            else x+=4;
        }
        if(符文("装备疾射火炮")&&nobuff(疾射火炮.class)){
            x*=1.5f;
        }
        if(符文("亮出你的剑")){
            攻击范围get=x;
            return 1;
        }
        return x;
    }
    public int 惊醒距离(){
        int x =4-天赋点数(Talent.静步);
        x+=狂妄皇冠.增加();
        if(heroClass(HeroClass.镜魔))
            x--;
        if(Dungeon.解压(解压设置.偷偷变强))x-=3;
        return Math.max(1,x);
    }
    public void 扔出(int pos,Item item,Callback c){
        if(item instanceof Weapon w)w.hitSound(1);
        ((MissileSprite) sprite.parent.recycle(MissileSprite.class)).
                reset(sprite,
                      pos,
                      item,
                      c
                      );
    }

    @Override
    public float 攻击时(final Char enemy, float damage) {
        damage = super.攻击时(enemy, damage);
        damage = Talent.攻击时(this, enemy, damage);

        if(enemy!=null&&英精英雄==5)英精英雄成长+=0.01f;
        if (enemy!=null&&subClass(HeroSubClass.不灭战士)&&hasbuff(战斗状态.class)) {
            生命成长+=天赋点数(Talent.不灭之魂,0.05f);
            攻击成长+=天赋点数(Talent.怀恨在心,0.025f);
            更新数据();
        }
        if(enemy!=null&&Dungeon.系统(系统设置.攻击成长)){
            攻击成长+=Dungeon.depth/100f;
        }


        Weapon wep;

        if (武力之戒.fightingUnarmed(this)&&!武力之戒.unarmedGetsWeaponEnchantment(this)) {
            wep = null;
        } else {
            wep = belongings.attackingWeapon();
        }
        //region +

        Wand w=belongings.getItem(灵月法杖.class);
        if(w!=null)
        damage*=1f+w.curCharges*Dungeon.hero.天赋点数(Talent.EMPOWERED_STRIKE,0.25f);

        if(enemy!=null&&subClass(HeroSubClass.战斗法师)&&职业精通()){
            if(w.curCharges<w.maxCharges){
                w.partialCharge+=0.5f;
                充能卷轴.charge(this);
            }
            SpellSprite.show(this,SpellSprite.CHARGE);
            for(Wand.Charger wc: buffs(Wand.Charger.class)){
                if(wc.wand()!=null){
                    wc.gainCharge(0.5f);
                }
            }
        }

        if (天赋(Talent.MYSTICAL_CHARGE)){
            ArtifactRecharge.chargeArtifacts(this,天赋点数(Talent.MYSTICAL_CHARGE,0.5f));
        }

        if(enemy!=null&&Dungeon.符文("装备星蚀")&&nobuff(星蚀冷却.class))Buff.施加(this,星蚀.class,2).set(1,enemy);

        if (enemy!=null&&符文("纪元")&&hasbuff(被发现.class))damage+=enemy.buff(被发现.class).visualcooldown()*5;

        if (enemy!=null&&heroClass(HeroClass.HUNTRESS)&&视野范围(enemy.pos)) {
            damage+=感知范围();
        }
        if(enemy!=null&&符文("痛苦")&&enemy.isAlive()) enemy.受伤(50*死亡次数);
        if(符文("重量级打击手")) damage+=最大生命(0.05f);

        if(符文("献血")) {
            if(enemy!=null)
            受伤(生命(0.015f));

            damage+=最大生命(0.075f);
        }

        if(enemy!=null&&符文("科学狂人")&&算法.概率学(5))科学狂人=Random.oneOf(-0.3f,0.3f);
        if(符文("正义右拳"))damage+=力量()*3.5f;

        if(enemy!=null&&visibleEnemies()==1&&subClass(HeroSubClass.神兽之灵)){
            damage+=enemy.最大生命(0.1f+天赋点数(Talent.狂暴爪击,0.05f));
        }
        if(蛇皮走位==6){
            float x=移速();
            if (enemy!=null&&!Document.ADVENTURERS_GUIDE.isPageRead(Document.走位)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.走位);
            }
            if (buff(Momentum.class)!=null&&buff(Momentum.class).freerunning()) {
                x *=1+buff(Momentum.class).freerunTurns;
            }

            damage+=heroDamage(最小防御(),最大防御())*天赋点数(Talent.步兵冲锋,0.25f);
            if(subClass(HeroSubClass.轻装步兵)&&职业精通()){
                x*=10f;
            }
            damage+=x;

        }
        if(enemy!=null)
        蛇皮走位=0;

        if(enemy!=null&&Dungeon.level.distance(enemy.pos,pos)<=视野范围()/2){
            damage+=天赋点数(Talent.近视,1.5f);
        }
        if(enemy!=null&&enemy.第x次防御==1)damage+=0.5f;

        if(enemy!=null&&enemy.第x次防御==1){
            damage+=天赋点数(Talent.突袭,1.5f);
        }
        if (heroClass(HeroClass.重武)) {
            damage+=最小防御();
        }
        if(天赋(Talent.灵魂摄击))
        damage+=力量()*天赋点数(Talent.灵魂摄击,0.25f);

        if (buff(Kinetic.ConservedDamage.class) != null) {
            float conservedDamage = 0;
            conservedDamage = buff(Kinetic.ConservedDamage.class).damageBonus();
            if(enemy!=null){
                buff(Kinetic.ConservedDamage.class).detach();
                //use a tracker so that we can know the true final damage
                Buff.施加(this,Kinetic.KineticTracker.class).conservedDamage=conservedDamage;
            }
            damage += conservedDamage;
        }


        //endregion


		//region 附加效果


        if(belongings.thrownWeapon!=null){
            if(!Dungeon.炼狱(炼狱设置.无力投掷))
                damage=belongings.thrownWeapon.投掷攻击时(this,enemy,damage*(heroClass(HeroClass.女忍)?
                                                                                     1.15f:
                                                                                     1));
        }else
            if(wep!=null){
                damage=wep.攻击时(this,enemy,damage);
                if(belongings.secondWep!=null){
                    damage=belongings.secondWep.攻击时(this,enemy,damage);
                }
            }
        荆棘斗篷.Thorns thorns = buff(荆棘斗篷.Thorns.class);
        if (thorns != null) {
            damage = thorns.proc(damage, enemy);
        }
        if(enemy!=null&&符文("外交豁免")){
            for (Mob mob : Dungeon.level.mobs) {
                if(mob.hasbuff(外交豁免.class)){
                    Buff.detach(mob,外交豁免.class);

                }
            }
            if(enemy.isAlive())
            Buff.施加(enemy,外交豁免.class,5);
        }
        if(enemy!=null&&符文("装备疾射火炮")&&nobuff(疾射火炮.class)){
            Buff.施加(this,疾射火炮.class,10);
        }
        if(enemy!=null&&(符文("飞身踢")||符文("即死"))){
            回百分比血(0.05f);
            if(enemy.生命<=
               (符文("即死")?enemy.最大生命(0.25f):0)
               +(符文("飞身踢")?最大生命(0.2f):0))
                Buff.施加(enemy, Grim.GrimTracker.class).maxChance = 100f;
        }

        if(符文("我开挂了"))
            Buff.施加(enemy, Grim.GrimTracker.class).maxChance = 100f;

        if (enemy!=null&&subClass == HeroSubClass.征服者) {
            Buff.施加(this, 征服.class).叠层();
        }
        if (enemy!=null&&subClass(HeroSubClass.死灵术士)){
            灵魂标记.延长(enemy,灵魂标记.class,灵魂标记.DURATION+(Dungeon.hero.职业精通()?10:5));
        }

        if(enemy!=null&&天赋(Talent.荣耀之证)){
            护甲(恢复百分比护甲(天赋点数(Talent.荣耀之证,0.075f)));
        }
        if(enemy!=null&&enemy.吸血鬼飞刀){
            回血(damage*0.07f);
            enemy.吸血鬼飞刀=false;
        }
        if(enemy!=null&&天赋(Talent.杀意波动))回血(感知范围()*天赋点数(Talent.杀意波动,0.35f));

        if(enemy!=null&&天赋概率(Talent.大暗黑天,10))
            Buff.施加(enemy, Blindness.class, 4f);

        if(enemy!=null&&符文("台风")){
            float finalDamage=damage;
            扔出(enemy.pos,new 魔法箭矢(),()->{
                Buff.施加( enemy, 伤害.class).level+=finalDamage*0.3f;
            });
        }
        if(enemy!=null&&符文("双刀流")){
            float finalDamage=damage;
            扔出(enemy.pos,new 魔法箭矢(),()->{
                Buff.施加( enemy, 伤害.class).level+=finalDamage*0.4f;
            });
        }
        if(enemy!=null&&符文("选牌")){
            int x=Random.Int(2);
            if(x==0)护甲(已损失护甲(0.3f));
            if(x==1)new Bomb.ConjuredBomb().heroexplode(enemy.pos);
            if(x==2)Buff.施加(enemy,Paralysis.class,4);
        }
        if(enemy!=null&&符文("屠夫")&&算法.概率学(1/8f))
            new MysteryMeat().放背包();

        if(enemy!=null&&符文("混乱")&&算法.概率学(1/5f))
            Buff.施加(enemy, Amok.class, 6);

        if(enemy!=null&&subClass(HeroSubClass.真人)){
            boolean 触发=false;
            if(enemy.pos==pos+PathFinder.八卦开门){
                Dungeon.gold(new Gold().random().数量());
                sprite.说("开门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦休门){
                算法.修复效果(()->{
                    Buff.施加(enemy,Frost.class,Frost.DURATION);
                });
                sprite.说("休门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦生门){
                回百分比血(0.45f);
                sprite.说("生门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦伤门){
                if(enemy.isAlive())enemy.受伤(damage);
                damage=0;
                sprite.说("伤门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦杜门){
                算法.修复效果(()->{
                    Buff.延长(this,Invisibility.class,Invisibility.DURATION);
                });
                sprite.说("杜门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦景门){
                Buff.延长(this,MindVision.class,MindVision.DURATION);
                sprite.说("景门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦死门){
                Buff.施加(enemy,Grim.GrimTracker.class).maxChance=100f;
                sprite.说("死门");
                触发=true;
            }
            if(enemy.pos==pos+PathFinder.八卦惊门){
                new 恐惧卷轴().doRead();
                sprite.说("惊门");
                触发=true;
            }
            if(触发&&天赋(Talent.金光护罩))Buff.施加(this,护盾.class).增加(天赋点数(Talent.金光护罩));
        }
        if(enemy!=null&&符文("命中的宠爱"))命中成长+=0.5f;
        if(enemy!=null&&符文("毒素"))
        Buff.施加( enemy, Poison.class ).set( damage/3f);

        if(enemy!=null&&subClass(HeroSubClass.血法师))
            血魔法(this);

        if(enemy!=null&&visibleEnemies()>1&&subClass(HeroSubClass.神兽之灵)){
            int 最多攻击=0;
            for(Mob m:getVisibleEnemies()){
                if(m.alignment==Alignment.ENEMY&&m!=enemy){
                    最多攻击++;
                    if(最多攻击>2+天赋点数(Talent.极凌飓风,2))break;
                    attack(m,0.5f+天赋点数(Talent.极凌飓风,0.25f),0,0.5f+天赋点数(Talent.极凌飓风,0.25f));

                }
            }
        }

        if(enemy!=null&&重生怪物.equals("白化老鼠"))
            if (enemy!=null&&damage > 0 && Random.Int( 2 ) == 0) {
                Buff.施加( enemy, 流血.class ).set(damage);
            }
        if(enemy!=null&&subClass(HeroSubClass.盾之勇者)&&职业精通())
            if(enemy.第x次防御==1){
                Buff.延长(enemy,Vertigo.class,1);
            }

        if(enemy!=null&&enemy.第x次防御==1&&天赋(Talent.水漫火狱)){
            算法.修复效果(()->{
                Buff.延长(enemy,Frost.class,Frost.DURATION);
            });
        }

        if(enemy!=null&&subClass(HeroSubClass.实验狂鼠)&&算法.概率学(20+天赋点数(Talent.鼠爪刺击,20)))
            Buff.施加( enemy, 流血.class).set(damage*(0.25f));

        if(enemy!=null&&算法.概率学(天赋点数(Talent.外交鼠段,15)))
            鼠化(this,enemy.pos);

        if(enemy!=null&&算法.概率学(天赋点数(Talent.灵魂烈焰,15)))
            Buff.施加( enemy, 灵焰.class).reignite(enemy);

        if(enemy!=null&&火毒箭矢.增加()>0){
            
            int effect=Random.Int(4)+火毒箭矢.增加();

            if(effect>=6&&enemy.buff(火毒.class)==null){
                Buff.施加(enemy,火毒.class).reignite(enemy);
            }else if(effect>=2){
                Buff.施加(enemy,Poison.class).set((effect-2));
            }
        }
        if(enemy!=null&&subClass(HeroSubClass.金刚独狼))
            回已损失血(0.005f+天赋点数(Talent.嗜血如故,0.005f)+(职业精通()?0.005f:0));


        if (enemy!=null&&(enemy instanceof Rat||enemy instanceof Piranha)&&heroClass(HeroClass.灵猫)) {
            必中=true;
            必暴=true;
        }
        心之钢.心 钢 = buff(心之钢.心.class);
        if (enemy!=null&&钢 != null&&enemy.hasbuff(被发现.class)&&enemy.buff(被发现.class).visualcooldown()>=3) {
            damage = 钢.proc(damage, this, enemy);
        }
        
        
        if (enemy!=null&&damage > 0 && subClass == HeroSubClass.狂战士) {
           Buff.施加(this,怒气.class).damage();
        }

        PhysicalEmpower emp = buff(PhysicalEmpower.class);
        if (emp != null) {
            damage += emp.dmgBoost;
            if(enemy!=null){
                emp.left--;
                if(emp.left<=0){
                    emp.detach();
                }
                Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG,0.75f,1.2f);
            }
        }
        if(enemy!=null&&Dungeon.系统(系统设置.生命成长)){
            生命成长+=Dungeon.depth/100f;
            更新数据();
        }

        if (enemy!=null&&enemy.isAlive()&&buff(赐福.效果.class)!=null) {
            if(enemy.恶魔亡灵())
                enemy.受伤时(光照范围()*2,赐福.INSTANCE);
            else
                enemy.受伤时(光照范围(),赐福.INSTANCE);
        }
        if(enemy!=null&&enemy.isAlive()&&符文("血之饥渴")){
            enemy.受伤(0.1f*最大攻击());
            if(半血以下())
                回血(0.1f*最大攻击());
            else if(残血())
                回血(0.1f*最大攻击()*2.5f);
        }
        if(enemy!=null&&enemy.isAlive()&&符文("无极剑道"))
            enemy.受伤(0.2f*最大攻击());

        if(enemy!=null&&enemy.isAlive()&&符文("砍伤"))
            enemy.受伤(0.5f*最大防御());

        if(enemy!=null&&enemy.isAlive()&&符文("临终轻语"))
            enemy.受伤(enemy.已损失生命(0.09f));

        if(enemy!=null&&enemy.isAlive()&&符文("闪电打击")&&1f/攻击延迟()>=1.5f)
            enemy.受伤(20);

		if (enemy!=null&&subClass(HeroSubClass.狙击手)&&职业精通()&&!(wep instanceof 灵能短弓.SpiritArrow)) {
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
        float 伤害倍率=1;
        if(enemy!=null&&符文("专注")&&enemy.isAlive()){
            if(enemy.hasbuff(专注.class))
                伤害倍率+=0.15f;
            else
            Buff.施加(enemy,专注.class,3);
        }
        if(enemy!=null&&Dungeon.符文("怕光")&&视野范围(enemy.pos))伤害倍率+=0.1f;
        if(enemy!=null&&符文("动物园园长")&&enemy.动物()) 伤害倍率+=0.35f;

        if(enemy!=null&&符文("踢踏舞")&&hasbuff(战斗状态.class)){
            Buff.施加(this,踢踏舞.class).set(1);
        }
        if(enemy!=null&&符文("佯攻")){
            if(enemy.第x次防御==1)
                伤害倍率=0;
            else 伤害倍率+=0.15f;
        }

        if(enemy!=null&&符文("我是Evan")&&!adjacent(enemy))伤害倍率+=0.25f;


        if(enemy!=null&&连击==0){
            if(符文("小型打击")&&符文("电火迸射")){//1.2
                伤害倍率=0.15f;
                连击=8;
                无动画连击(enemy,0.15f,0,1);
            }else if(符文("小型打击")){//1.2
                伤害倍率=0.3f;
                连击=4;
                无动画连击(enemy,0.3f,0,1);
            }else if(符文("电火迸射")){//1.2
                伤害倍率=0.2f;
                连击=6;
                无动画连击(enemy,0.2f,0,1);
            }
        }

        if(enemy!=null&&符文("安内")) {
            if(Dungeon.level.adjacent(this,enemy)){
                if(暴击(enemy,1)>1)
                    伤害倍率*=0.75f*暴击伤害();
            }else 伤害倍率/=3f;
        }
        if(enemy!=null&&符文("小怪猎手")&&(!enemy.老鬼()||!enemy.老鬼()))伤害倍率+=.15f;
        if(enemy!=null&&符文("杀戮之神")&&(!enemy.老鬼()||!enemy.老鬼()))伤害倍率+=.5f;
        if(enemy!=null&&符文("死神")&&enemy.残血())伤害倍率+=.3f;
        if(enemy!=null&&符文("唯快不破"))伤害倍率+=Math.min(0,(移速()/enemy.移速()-1)*1/3f);
        if(符文("止戈"))伤害倍率+=.2f;
        if(enemy!=null&&(英精英雄==0||英精英雄==1)){
            伤害倍率+=.25f;
            if(enemy.nobuff(火毒.class))
                Buff.施加(enemy,火毒.class).reignite(enemy);
        }

        if(第x次攻击%2==0){
            if(符文("左拳伤害高右拳高伤害"))damage*=1.15f;
        }
        if(第x次攻击%3==0)
            if(enemy!=null&&符文("接二连三"))
                attack(enemy);

        if(第x次攻击%4==0)
            if(enemy!=null&&enemy.isAlive()&&符文("点亮他们"))
            enemy.受伤(最大攻击());

        if(enemy!=null&&enemy.海妖()){
            伤害倍率+=天赋点数(Talent.捕鱼达人,0.4f);
        }

        if(heroClass(HeroClass.罗兰))
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()){
                伤害倍率+=.67f;
            }
        }

        if (enemy!=null&&enemy.恶魔亡灵()) {
            伤害倍率+=天赋点数(Talent.捉拿抓鬼,0.2f);
            if (heroClass(HeroClass.CLERIC)) {
                伤害倍率+=.1f;
            }
        }
        if (enemy!=null&&(enemy instanceof Rat||enemy instanceof Piranha)&&heroClass(HeroClass.灵猫)) {
            伤害倍率+=1.5f;
            必中=false;
            必暴=false;
        }
        if(enemy!=null&&天赋(Talent.波动印记)&&感知范围(enemy)){
            伤害倍率+=天赋点数(Talent.波动印记,0.1f);
        }
        if(enemy!=null&&天赋(Talent.狙击弱点)){
            伤害倍率+=distance(enemy)*天赋点数(Talent.狙击弱点,0.05f);
        }
        if(subClass(HeroSubClass.狙击手)){
            伤害倍率+=暴击率()/100f*暴击伤害();
        }


        伤害倍率*=火毒箭矢.减少();

        if(符文("均衡打击"))伤害倍率/=2f;
        if(enemy!=null&&符文("巨人杀手"))伤害倍率*=Math.min(1.35f,Math.max(1,enemy.大小*enemy.sprite.width*enemy.sprite.height/
                                                                sprite.width*sprite.height*大小));
        //endregion

        damage*=伤害倍率;
        if(enemy!=null&&enemy.第x次防御==1&&符文("先攻")){
            Dungeon.gold(Math.round(damage/10f));
            damage*=1.1f;
        }
        if(enemy!=null&&enemy.isAlive()&&enemy.第x次防御==1&&符文("夺金")){
            enemy.受伤(damage*0.4f);
            Dungeon.gold(new Gold().random().数量());
        }
        if(enemy!=null&&enemy.isAlive()&&符文("均衡打击")){
            enemy.受伤(damage);
        }
        return damage;
    }

    @Override
    public float 防御时(Char enemy, float damage) {

        if(enemy!=null&&天赋(Talent.皇室传承)){
            Dungeon.level.drop(new Gold().random(天赋点数(Talent.皇室传承,0.15f)),pos).sprite().drop();
        }
        if(enemy!=null&&英精英雄==5)英精英雄成长+=0.001f;

        if(enemy!=null&&Dungeon.系统(系统设置.防御成长)){
            防御成长+=Dungeon.depth/100f;
        }
        if(enemy!=null&&heroClass(HeroClass.机器))
            Sample.INSTANCE.play(Assets.Sounds.金属受伤);
        
        if(enemy!=null&&heroClass(HeroClass.灵猫)&&damage>=最大生命(0.25f))
            Sample.INSTANCE.play(Assets.Sounds.哈气猫);
        if(enemy!=null)
        Statistics.物理防御++;
        if(enemy!=null)
        Badges.解锁重武();

        if(enemy!=null&&重生怪物.equals("史莱姆"))
            damage=算法.固衰(damage,5);

		//region *

        if(算法.isDebug()){
            damage/=2f;
        }
        if(enemy!=null&&符文("外交豁免")){
            if(enemy.nobuff(外交豁免.class))damage/=2f;
        }
        if(enemy!=null&&Dungeon.level.distance(enemy.pos,pos)<=视野范围()/2){
            damage*=(1-天赋点数(Talent.戒备,0.33f));
        }
        if(符文("会心防御"))damage*=1-暴击率()*0.2f;
		if (enemy!=null&&enemy.恶魔亡灵() && heroClass(HeroClass.CLERIC)) {
            damage *=0.9f;
        }
        damage*=中国国旗.受伤();
        if(符文("走完红色路线")&&中国国旗.受伤()<1)
            damage*=0.8f;


        if(enemy!=null&&符文("伤害转嫁")){
            for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                if (mob.isAlive()&&mob.alignment == Alignment.ENEMY && Dungeon.level.heroFOV[mob.pos]) {
                    mob.受伤(damage);
                }
            }
        }
        damage*=巨大蟹钳.受到();
        damage *= RingOfTenacity.damageMultiplier(this);

        if(符文("安内"))damage*=1.1f;
        if(符文("止戈"))damage*=1.1f;
        if(英精英雄==2)damage/=2;
        if(英精英雄==3)damage*=0.2f;

        if(enemy!=null)
        for (int n : PathFinder.相邻8){
            Char c=Actor.findChar(pos+n);
            int x=0;
            if(c!=null){
                x++;
                if(x>=2)
                damage*=传奇肛塞.受伤();
            }
        }
		//endregion

        //region 附带效果

        if(符文("装备死亡之舞"))
            if(damage*0.3f>0){
                if(enemy!=null){
                    死舞 deferred=Buff.施加(this,死舞.class);
                    deferred.extend(damage*0.3f);

                    sprite.showStatus(CharSprite.WARNING,Messages.get(死舞.class,"deferred",String.format("%.2f",damage*0.3f)));
                }
                damage=damage*0.7f;
            }
        if(enemy!=null&&符文("混乱")&&算法.概率学(1/5f))
            Buff.施加(enemy, Amok.class, 6);
        if(enemy!=null&&heroClass(HeroClass.鼠弟)){
            Sample.INSTANCE.play(Assets.Sounds.RAT);
            for (int n : PathFinder.相邻8){
                Char c= Actor.findChar(pos+n);
                if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){

                    sprite.attack(enemy.pos, new Callback() {
                        @Override
                        public void call() {
                            AttackIndicator.target(enemy);
                            if (attack(enemy,0,heroDamage(武力之戒.heromin(),武力之戒.heromax()),1)) {
                                Sample.INSTANCE.play(Assets.Sounds.HIT);
                            }
                            Invisibility.notimedispel();
                            spendAndNext(0);
                        }
                    });
                }
            }
        }
        if(enemy!=null&&heroClass(HeroClass.凌云)){
            enemy.受伤(damage);
        }
        if(enemy!=null&&subClass(HeroSubClass.金刚独狼))回已损失血(0.005f+天赋点数(Talent.嗜血如故,0.005f)+(职业精通()?0.005f:0));
        if(hasbuff(Hunger.class)){
            if(buff(Hunger.class).饥饿()&&heroClass(HeroClass.罗兰)){
                damage*=0.67f;
            }
        }
        if (enemy!=null&&subClass == HeroSubClass.狂战士) {
            Buff.施加(this,怒气.class).damage();
        }

        荆棘斗篷.Thorns thorns = buff(荆棘斗篷.Thorns.class);
        if (enemy!=null&&thorns != null) {
            damage = thorns.proc(damage, enemy, this);
        }
        WandOfLivingEarth.RockArmor rockArmor = buff(WandOfLivingEarth.RockArmor.class);
        if (enemy!=null&&rockArmor != null) {
            damage = rockArmor.absorb(damage);
        }

        if (belongings.armor() != null) {
            damage = belongings.armor().防御时(enemy, this, damage);
        }

        //endregion

        if(!女人())
        damage+=男人国徽章.受伤();
        if(heroClass(HeroClass.近卫)){
            damage--;
        }
        return super.防御时(enemy, damage);
    }

    @Override
    public int glyphLevel(Class<? extends Armor.Glyph> cls) {
        if (belongings.armor() != null && belongings.armor().hasGlyph(cls, this)) {
            return Math.max(super.glyphLevel(cls), belongings.armor.强化等级());
        } else {
            return super.glyphLevel(cls);
        }
    }

    @Override
    public void 受伤时(float dmg, Object src) {

        if (buff(时光沙漏.timeStasis.class) != null
            || buff(TimeStasis.class) != null) {
            return;
        }
        //regular damage interrupt, triggers on any damage except specific mild DOT effects
        // unless the player recently hit 'continue moving', in which case this is ignored
        if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage) && damageInterrupt) {
            if(SPDSettings.打断英雄()&&dmg>=1){
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

        if(src instanceof Mob mob){
            dmg*=mob.伤害();
        }
        if(符文("我开挂了"))dmg*=3;
        if(符文("末法"))dmg*=0.8f;

        if(hasbuff(冥想.class))dmg/=2f;

        if(符文("坚固壁垒")&&dmg>=生命(0.1f)){
            dmg/=2f;
        }
        if (buff(Talent.WarriorFoodImmunity.class) != null) {
            if (heroClass(HeroClass.学士)) {
                dmg = 0f;
            }
        }
        float preHP = 生命 + shielding();
        if (src instanceof Hunger) preHP -= shielding();
        super.受伤时(dmg, src);
        float postHP = 生命 + shielding();
        if (src instanceof Hunger) postHP -= shielding();
        float effectiveDamage = preHP - postHP;

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
        if (effectiveDamage>=1&&flashIntensity >= 0.05f) {
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
            if(符文("我的刀盾")&&target!=null&&target.豺狼())
                Buff.施加(target, Corruption.class);

            if(符文("!!!"))
            attack(target,1/攻击延迟(),0,Char.INFINITE);

            if(SPDSettings.打断英雄()&&nobuff(Invisibility.class)){//发现新敌人
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
        
        if(belongings.attackingWeapon() instanceof 饮血之剑){
            if(满血()||符文("升级饮血之剑")) 护甲(x);
//            if(hasbuff(Barrier.class)){
//                if(Buff.施加(this, Barrier.class).shielding+Math.round(x)<=最大生命(0.25f))
//                    Buff.施加(this, Barrier.class).增加(Math.round(x));
//            }else{
//                Buff.施加(this, Barrier.class).设置(Math.round(x));
//            }
        }

        if(符文("会心治疗"))x=暴击(null,x);

        if(符文("死亡之环"))
        for (int n : PathFinder.范围2){
            Char c= Actor.findChar(pos+n);
            if(c!=null&&c.alignment == Alignment.ENEMY&&Dungeon.level.heroFOV[c.pos]){
                c.受伤(x);
            }
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
        if(符文("升级经验")){
            exp*=等级*0.35;
        }
        exp=Math.round(exp*Dungeon.难度经验());
        if (sprite!=null&&exp > 0) {
           sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(exp),FloatingText.EXPERIENCE);
        }
        //xp granted by ascension challenge is only for on-exp gain effects
        if (source != AscensionChallenge.class) {
            this.当前经验 += exp;

            if (subClass(HeroSubClass.战斗法师)) {
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
        }

        while (this.当前经验 >= 升级所需()) {
            this.当前经验 -= 升级所需();

            if (buff(Talent.WandPreservationCount.class)!=null) {
                buff(Talent.WandPreservationCount.class).detach();
            }

            if (等级 < 最大等级) {
                升级(true);
            } else {
                升级(false);
                Buff.延长(this, Bless.class, Bless.DURATION);
                this.当前经验 = 0;

                GLog.newLine();
                GLog.p(Messages.get(this, "level_cap"));
                Sample.INSTANCE.play(Assets.Sounds.LEVELUP);
            }

        }
    }

    public void 升级(boolean 可以) {

        if(可以){
            for(Item item: belongings){
                if(item instanceof 灵月法杖 x){
                    x.updateLevel();
                }
            }
            if(!Document.ADVENTURERS_GUIDE.isPageRead(Document.升级)){
                GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.升级);
            }
            if(可以)
                等级++;


            if(等级==21&&职业精通())
                职业精通提示();

            if(Dungeon.派对(派对设置.海克斯)){
                if(等级==7) {
                    new 海克斯宝典().放背包();
                    if(符文("无法刷新海克斯"))
                    for(int x=0;x<=海克斯宝典.使用上限();x++){
                        new 海克斯宝典().放背包();
                    }
                }
                if(等级==13) {
                    new 海克斯宝典().放背包();
                    if(符文("无法刷新海克斯"))
                        for(int x=0;x<=海克斯宝典.使用上限();x++){
                            new 海克斯宝典().放背包();
                        }
                }
                if(等级==19) {
                    new 海克斯宝典().放背包();
                    if(符文("无法刷新海克斯"))
                        for(int x=0;x<=海克斯宝典.使用上限();x++){
                            new 海克斯宝典().放背包();
                        }
                }
            }


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
        }else {

            if(符文("轮回")){
                生命成长+=3;
                命中成长+=1.2f;
                闪避成长+=1.2f;
            }
        }

        if(符文("升级等级"))new 升级卷轴().放背包();

        if(符文("升级便无敌的我")){
            Buff.延长(this,Invulnerability.class,等级);
            SpellSprite.show(this,SpellSprite.ANKH);
            GameScene.flash(0x80FFFF40);
        }

        if(heroClass(HeroClass.学士)){
            回满血();
        }

        更新数据();
    }

    public int 升级所需() {
        return 升级所需(等级);
    }

    public int 升级所需(float x) {
        return Math.round(升级所需(等级)*x);
    }

    public int 升级所需(int lvl) {
        if(符文("我秒升级"))return lvl;
        int x = 10;
        int y = 5 + 1;
        if(lvl<25)
        lvl=Math.min(--lvl,25);
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
        if(符文("自杀式攻击")){
            for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
                if (mob.alignment == Alignment.ENEMY && Dungeon.level.heroFOV[mob.pos]) {
                    if(mob.老鬼())
                    mob.受伤(最大生命/4f);
                    else
                    mob.受伤(最大生命);
                }
            }
        }
        if(符文("大难不死必有后福")){
            for(int x=0;x<=60;x++){
                Dungeon.level.drop(Generator.random(),pos).sprite.drop();
            }
        }
        死亡次数++;
        if(cause!=null){
            Ankh ankh = null;
            //look for ankhs in player inventory, prioritize ones which are blessed.
            for (Ankh i : belongings.getAllItems(Ankh.class)) {
                if (ankh == null || i.isBlessed()) {
                    ankh = i;
                }
            }
            if(heroClass(HeroClass.罗兰)){
                if(ankh!=null){
                    if(!ankh.isBlessed())
                    ankh=null;
                }
            }
            if(ankh!=null||符文("作弊我能复活")||符文("我无限回档洞悉所有底牌")){
                interrupt();
                Badges.解锁罗兰();

                if(subClass(HeroSubClass.不灭战士)){
                    生命成长+=天赋点数(Talent.慷慨赴死,12);
                    攻击成长+= 天赋点数(Talent.慷慨赴死,6);
                    更新数据();
                }
                if(ankh!=null){
                    if(ankh.isBlessed())回满血();
                    else
                    回百分比血(0.25f);

                    if(ankh.isBlessed()){

                        治疗药剂.cure(this);
                        Buff.延长(this,Invulnerability.class,Invulnerability.DURATION);
                        SpellSprite.show(this,SpellSprite.ANKH);
                        GameScene.flash(0x80FFFF40);
                    }

                    Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
                    GLog.w(Messages.get(this,"revive"));
                    Statistics.ankhsUsed++;
                    Catalog.countUse(Ankh.class);

                    ankh.detach(belongings.backpack);

                    for(Char ch: Actor.chars()){
                        if(ch instanceof DriedRose.GhostHero){
                            ((DriedRose.GhostHero)ch).sayAnhk();
                            return;
                        }
                    }
                }else

                    if(符文("我无限回档洞悉所有底牌")){回满血();
                        传送卷轴.teleportPreferringUnseen(this);
                    }
                    if(符文("作弊我能复活")){

                        //this is hacky, basically we want to declare that a wndResurrect exists before
                        //it actually gets created. This is important so that the game knows to not
                        //delete the run or submit it to rankings, because a WndResurrect is about to exist
                        //this is needed because the actual creation of the window is delayed here
                        WndResurrect.instance=
                                new Object();
                        Game.runOnRenderThread(()->GameScene.show(new WndResurrect()));

                        if(cause instanceof Hero.Doom){
                            ((Hero.Doom)cause).onDeath();
                        }

                        SacrificialFire.Marked
                                sacMark=
                                buff(SacrificialFire.Marked.class);
                        if(sacMark!=null){
                            sacMark.detach();
                        }

                    }
                return;
            }
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
        for (Integer ofs : PathFinder.相邻8) {
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
                if(Dungeon.区域()==4){
                    Sample.INSTANCE.play(Assets.Sounds.地毯, 1, Random.Float(0.96f, 1.05f));
                }else if(Dungeon.区域()==3){
                    Sample.INSTANCE.play(Assets.Sounds.金属板,1,Random.Float(0.96f,1.05f));
                }else if(Dungeon.区域()==2){
                    Sample.INSTANCE.play(Assets.Sounds.金属网,1,Random.Float(0.96f,1.05f));
                }else
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

        if(heroClass(HeroClass.盗贼)){
            x++;
        }
        x += 天赋点数(Talent.寻觅);
        x+=遗失符石.减少();
        if(符文("橡皮长枪手"))x+=2*攻击范围();
        if (buff(Foresight.class) != null) {
            x = Foresight.DISTANCE;
        }
        return Math.max(1,x);
    }

    public boolean 感知范围(Char c) {
        return distance(c)<=感知范围();
    }
    public int 感知范围() {
        int x =1;
        if(heroClass(HeroClass.戒老))x+=视野范围get/2;
        x += 天赋点数(Talent.命运罗盘);
        x += 天赋点数(Talent.寻觅);
        x += EyeOfNewt.mindVisionRange();
        if(subClass(HeroSubClass.阿修罗)){
            x+=攻击范围();
        }
        if(符文("千里眼顺风耳"))x+=7;
        x*=1+天赋点数(Talent.猫反应7,0.175f)*根据已损失生命();
        return x;
    }
    
    public int 光照范围() {
        int x=火把神;
        x += 天赋点数(Talent.夜视);
        if (hasbuff(Light.class)) {
            x += Light.DISTANCE;
        }
        if (hasbuff(燃烧.class)) {
            x += Light.DISTANCE;
        }
        if (heroClass(HeroClass.CLERIC)) {
            x += 3;
        }
        if (subClass(HeroSubClass.圣骑士)) {
            if(职业精通())x+=力量()*0.3f;
            x += 5;
        }
        return x;
    }

    public boolean 视野范围(int target){
        return Dungeon.level.heroFOV[target];
    }
    @Override
    public int 视野范围() {
        float x = super.视野范围();
		
		x +=天赋点数(Talent.命运罗盘);
		x +=天赋点数(Talent.夜视);
        if(符文("远视人启动"))x+=5;
        if(Dungeon.isChallenged(Challenges.DARKNESS)||heroClass(HeroClass.戒老)){
            x/=4;
        }
        if(subClass(HeroSubClass.阿修罗)){
            if(职业精通())
                x+=攻击范围();
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
        视野范围get=Math.round(Math.max(1,x));
        return Math.round(x+光照范围());
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
                            attack(Actor.findChar(curr),0.15f+天赋点数(Talent.边搜边打,0.15f));
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
            if(subClass(HeroSubClass.神偷无影)){
                x*=1-0.25f;
                if(职业精通())x*=0.75f;
            }
            x*=1-天赋点数(Talent.边搜边打,0.15f);
            float 增加=0;
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
            } else if (i instanceof 心之钢&&i.keptThroughLostInventory()&&Dungeon.派对(派对设置.钢门联盟)) {
                ((心之钢) i).activate(this);
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

    @Override
    public float stealth() {
        float 隐匿= super.stealth();//6+
        隐匿+=虚无透纱.增加();
        if(符文("树懒转世"))
            隐匿+=6;
        if(Dungeon.解压(解压设置.偷偷变强))隐匿+=10;
        return 隐匿;
    }

    public float 综合属性() {
        float x = 1 + 天赋点数(Talent.任督二脉, 0.035f);
        if(belongings!=null&&belongings.hasItem(三叉戟.class)&&在水中()){
            x+=0.1f;
        }
        if (heroClass(HeroClass.WARRIOR)) {
            x+=0.1f;
        }
        if(subClass(HeroSubClass.指环王))x-=0.3f;
        if(符文("轻音战士")&&SPDSettings.music()&&SPDSettings.soundFx()){
            x+=0.5*SPDSettings.musicVol();
            x+=0.5*SPDSettings.SFXVol();
        }
        x+=真正护符.综合();
        if(符文("黑幕")){
            x+=0.3f;
        }
        if(符文("我不是药神")){
            x+=0.1f* buffs().size();
        }
        if(符文("海克斯收集家")){

            List<String> keysList = new ArrayList<>(海克斯1.keySet());
            int 海克斯数量=0;
            for(String s:keysList){
                if(符文(s))海克斯数量++;
            }
            keysList = new ArrayList<>(海克斯2.keySet());
            for(String s:keysList){
                if(符文(s))海克斯数量++;
            }
            keysList = new ArrayList<>(海克斯3.keySet());
            for(String s:keysList){
                if(符文(s))海克斯数量++;
            }
            x+=海克斯数量*0.1f;
        }
        if(符文("暗黑破坏神"))x+=破坏物体*0.0035f;
        if(符文("陷阱的宠爱")&&在陷阱())x+=0.6f;
        if(符文("深渊的宠爱")&&在深渊())x+=0.45f;
        if(符文("流水的宠爱")&&在水中())x+=0.25f;
        if(符文("草木的宠爱")&&在草丛())x+=0.25f;
        if(符文("炒能力"))x+=Dungeon.gold/15000f;
        if(符文("缩小引擎"))x-=0.01f*等级;
        if(符文("草丛伦")&&hasbuff(Invisibility.class))
            x+=0.1f;
        x+=属性成长;
        if(符文("人不犯我我不犯人")){
            if(hasbuff(战斗状态.class))x+=0.3f;
        }


        if(符文("属性叠属性叠"))x+=0.035f;
        if(符文("回归基本功"))x+=1.5f;
        if(符文("作弊我能复活"))x-=0.3f;
        if(符文("我等你"))x-=0.5f;
        if(heroClass(HeroClass.来世)){
            x+=0.1f;
        }
        if(符文("孤注一掷"))x-=0.45f;
        if(hasbuff(替身保护.class)) x+=0.2f;

        if (subClass(HeroSubClass.不灭战士)&&hasbuff(战斗状态.class)) {
            x += 0.1f;
        }
        if(heroClass(HeroClass.兽灵))x+=0.05f;
        x+=天赋点数(Talent.黑猫主导,0.05f);
        时间能力 s=buff(时间能力.class);
        if(s!=null)x+=s.综合属性();

        if (subClass(HeroSubClass.潜能觉醒)) {
            if(职业精通())x*=1.1f;
            x*=1.1f;
        }
        if(符文("+10086"))x*=0.3f;
        if(符文("时间之神白野"))
        if(hasbuff(Swiftthistle.TimeBubble.class)||hasbuff(时光沙漏.timeFreeze.class))x*=3f;
        x*=六神之戒.六神之力(this);
        return x;
    }

    @Override
    public float 吸血() {
        float 吸血 = 0;
        if(heroClass(HeroClass.WARRIOR))吸血+=0.025f;
        if(符文("日蚀"))吸血+=1f/攻击延迟()*0.05f;
        if(符文("不全能吸血"))吸血+=2*全能吸血();
        if(符文("战神"))吸血+=0.15f;
        if(符文("真战士"))吸血+=0.025f;
        if(符文("升级饮血之剑"))吸血+=0.05f;
        if(符文("升级破败王剑"))吸血+=0.025f;
        if(符文("升级腥红散华"))吸血+=0.025f;
        if(符文("暴风吸入"))吸血+=0.75f*暴击伤害();
        if(符文("亮出你的剑"))吸血+=0.075f*攻击范围get;
        if(subClass(HeroSubClass.神兽之灵)){
            吸血+=0.025f+天赋点数(Talent.坚铁甲胄,0.025f);
        }
        if(重生怪物.equals("吸血蝙蝠"))吸血+=0.5f;
        if(Dungeon.赛季(赛季设置.修罗血场)){
            吸血 += 0.075f;
        }
        吸血 += 天赋点数(Talent.生命汲取, 0.03f);
        if(belongings.weapon()!=null){
            吸血 +=belongings.weapon().吸血;
        }
        return 吸血+super.吸血();
    }
    @Override
    public float 全能吸血() {
        
        float 全能吸血 = 0;

        if (subClass(HeroSubClass.黑魔导师)) {
            全能吸血 += 0.05f;
            if(职业精通())
            全能吸血 += 0.05f;
        }
        征服 z=buff(征服.class);
        if(z!=null&&z.满层())全能吸血+=0.025f+天赋点数(Talent.征服之姿,0.025f)+(职业精通()?0.025f:0);
        if (heroClass(HeroClass.血鬼)) {
            全能吸血 += 0.05f;
        }
        if (subClass(HeroSubClass.不灭战士)&&hasbuff(战斗状态.class)&&职业精通()) {
            全能吸血 += 0.05f;
        }
        if(符文("吸吸物质为俊杰"))全能吸血+=0.05f;
        if(符文("你肩上的恶魔"))全能吸血+=0.05f;
        if(符文("渴血"))全能吸血+=0.15f;
        if(符文("装备无穷饥渴")){
            if(hasbuff(连杀状态.class))
            全能吸血+=0.15f;

            全能吸血+=0.05f;
        }
        if(符文("升级全能吸血"))全能吸血+=2*吸血();
        if(符文("吸血习性"))全能吸血+=0.25f;
        全能吸血 += 天赋点数(Talent.高级吸血, 0.015f);
        全能吸血+=吸血成长;
        return 全能吸血;
    }
    @Override
    public float 穿甲() {

        float 穿甲 = 穿甲成长;
        if(符文("穿甲的宠爱"))穿甲+=最大防御()*0.15f;
        if(符文("残暴之力"))穿甲+=16;
        if(heroClass(HeroClass.兽灵))穿甲+=3;

        return 穿甲;
    }
    @Override
    public float 护甲穿透() {

        float 护甲穿透 = 1;

        if (subClass==HeroSubClass.狙击手){
            护甲穿透*= 0.65f;
        }

        if (heroClass(HeroClass.罗兰)) {
            护甲穿透*= 0.8f;
        }
        护甲穿透*=1-穿透成长;
        return 护甲穿透;
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
                            float damage = 最大生命(0.03f+天赋点数(Talent.血色契约,0.015f));
                            if (mob != null && damage > 0 && mob.alignment != Char.Alignment.ALLY){
                                charsHit++;
                                mob.受伤(damage);
                            }
                        }

                        if (charsHit > 0 && hero.天赋(Talent.鲜血转换)){
                            回百分比血(天赋点数(Talent.鲜血转换,0.005f)*charsHit);
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
                                for (int n : PathFinder.自相邻8) {
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
                            int damage = Math.round(Hero.heroDamage(15,25)
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
                                        float healing = Math.round(10*effectMulti);
                                        float shielding = (mob.生命 + healing) - mob.最大生命;
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
                                            damage = Math.round(Hero.heroDamage(15,25)*effectMulti);
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
                            float shielding = charsHit*2.5f*hero.天赋点数(Talent.REACTIVE_BARRIER);
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

    public void 风刃(Hero hero,Integer target,int 角度,int 数量,Item item){

        if (target == null){
            return;
        }

        if (Actor.findChar(target) == hero){
            GLog.w(Messages.get(this, "self_target"));
            return;
        }

        Ballistica b = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);
        final HashSet<Char> targets = new HashSet<>();

        Char enemy = 风刃.findChar(b,hero, 0/*穿墙*/,targets);

        if (enemy == null || !hero.fieldOfView[enemy.pos]){
            GLog.w(Messages.get(this, "no_target"));
            return;
        }

        targets.add(enemy);

        ConeAOE cone = new ConeAOE(b,角度);
        for (Ballistica ray : cone.rays){
            Char toAdd = 风刃.findChar(ray,hero,0/*穿墙*/,targets);
            if (toAdd != null && hero.fieldOfView[toAdd.pos]){
                targets.add(toAdd);
            }
        }

        if(item instanceof 吸血刀){
            if(符文("升级腥红散华")){
                数量+=4;
            }else{
                if(算法.概率学(1/2))
                    数量++;
                if(算法.概率学(1/4))
                    数量++;
                if(算法.概率学(1/8))
                    数量++;
                if(算法.概率学(1/16))
                    数量++;
            }
        }

        while (targets.size() > 1+数量){
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

        Item proto = item;

        final HashSet<Callback> callbacks = new HashSet<>();
        for (Char ch : targets) {
            Callback callback = new Callback() {
                @Override
                public void call() {
                    float dmgMulti = ch == enemy ? 1f : 0.5f;

                    if(item instanceof 吸血刀){
                        ch.吸血鬼飞刀=true;
                        hero.sprite.zap(ch.pos);
                        hero.sprite.parent.add(
                                new Beam.HealthRay(hero.sprite.center(), ch.sprite.center()));

                    }

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

        for (int i=0; i < PathFinder.相邻8.length;i++) {
            int p = pos + PathFinder.相邻8[i];
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
                for(int i: PathFinder.相邻8){
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
            for (int i : PathFinder.相邻8) {
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
                for(int i: PathFinder.相邻8){
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
    
    public boolean 无动画连击(final Char enemy, float dmgMulti, float dmgBonus, float accMulti) {

        AttackIndicator.target(enemy);

        boolean wasAlly = enemy.alignment == alignment;
        boolean hit = hit(this,enemy);

        Invisibility.notimedispel();

        连击--;
        //fury attacks as many times as you have combo count
        if (连击 > 0 && enemy.isAlive() && canAttack(enemy) &&
                (wasAlly || enemy.alignment != alignment)) {
                    连击(enemy, dmgMulti, dmgBonus, accMulti);
        } else {
            连击=0;
        }
        return hit;
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
            连击=0;
            spendAndNext(攻击延迟());
        }
        return hit;
    }
    public boolean 职业精通(){
        return 等级>20&&Badges.local.contains(Badges.Badge.BOSS_SLAIN_4);
    }
    public void 职业精通提示(){
        String s="你因为升到21级并击杀第四个Boss已职业精通！\n";
        if(职业精通()){
            switch(Dungeon.hero.subClass){
                case 潜能觉醒: default:
                    s+= "综合属性x110%。";
                    break;
                case 狂战士:
                    s+= "每点怒气+0.25%攻击。";
                    break;
                case 角斗士:
                    s+= "连击重置回合+3，6+连击时连击技获得强化。";
                    break;
                case 冰结师:
                    s+= "每回合额外在周身4格生成。";
                    break;
                case 元素法师:
                    s+= "敌人-30%元素抗性。";
                    break;
                case 死灵术士:
                    s+= "灵魂标记+10回合。";
                    break;
                case 真人:
                    s+= "休息时，额外恢复10%静止状态回合的护甲。";
                    break;
                case 刺客:
                    s+= "每回合隐形会额外获得1阶段潜伏。";
                    break;
                case 神偷无影:
                    s+= "搜索花费时间-25%。";
                    break;
                case 狙击手:
                    s+= "除灵能短弓外的物理攻击会施加狙击标记，灵能短弓对狙击标记敌人攻击取决于不同的强化方式。";
                    break;
                case 守望者:
                    s+= "踩踏植物会获得额外效果取代原本效果，所有植株对其无害。";
                    break;
                case 武器大师:
                    s+= "主武器和副武器攻击效率+10%。";
                    break;
                case 武者:
                    s+= "最大内力+5。";
                    break;
                case 黑魔导师:
                    s+= "全能吸血+5%";
                    break;
                case 战斗法师:
                    s+= "物理攻击会恢复所有法杖0.5充能。";
                    break;
                case 疾行者:
                    s+= "每点动能额外提供1逸动回合。";
                    break;
                case 征服者:
                    s+= "征服者全能吸血+2.5%。";
                    break;
                case 皇室卫兵:
                    s+= "护甲恢复速度x4倍。";
                    break;
                case 神兽之灵:
                    s+= "你与视野内的任意一个敌人距离大于1格，那么获得30%移速。";
                    break;
                case 灵魂武者:
                    s+= "击杀敌人永久额外+0.1力量。";
                    break;
                case 土影:
                    s+= "透视墙体。";
                    break;
                case 阿修罗:
                    s+= "视野范围+攻击范围。";
                    break;
                case 指环王:
                    s+= "所有戒指等级+3。";
                    break;
                case 黑白双子:
                    s+= "白猫受到伤害反馈-15%。";
                    break;
                case 巫咒王鼠:
                    s+= "击杀掉落生肉概率为必定。";
                    break;
                case 实验狂鼠:
                    s+= "流血伤害+25%。";
                    break;
                case 金刚独狼:
                    s+= "物理攻击和物理防御时额外恢复0.5%已损失生命。";
                    break;
                case 血法师:
                    s+= "最大生命+35%。";
                    break;
                case 时间刺客:
                    s+= "时停额外提供25%。";
                    break;
                case 祭司:
                    s+= " ";
                    break;
                case 圣骑士:
                    s+= "光照范围+30%力量";
                    break;
                case 健身猛男:
                    s+= "力量+20%。";
                    break;
                case 盾之勇者:
                    s+= "首次攻击施加1回合眩晕。";
                    break;
                case 轻装步兵:
                    s+= "蛇皮走位下次物理攻击+移速x10。";
                    break;
                case 灵月杀手:
                    s+= "+10%攻击。";
                    break;
                case 不灭战士:
                    s+= "战斗状态时，获得5%全能吸血。";
                    break;
            }
        }
        GLog.w(s);
    }
}
