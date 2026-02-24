

package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Amok;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ArcaneArmor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barkskin;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ChampionEnemy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Daze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Doom;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FireImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FrostImbue;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Fury;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LifeLink;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MonkEnergy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.SnipersMark;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Speed;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Stamina;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.守御灵光;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.极速;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.流血;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.潜伏;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.duelist.Challenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.尘遁;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.法术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Brute;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.CrystalSpire;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Elemental;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Gnoll;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollExile;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGeomancer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGuard;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollSapper;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollTrickster;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Necromancer;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Tengu;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.YogDzewa;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.MirrorImage;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.白猫;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.黑暗结晶;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.curses.Bulk;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Flow;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Obfuscation;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.道袍;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic.PotionOfCleansing;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfChallenge;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.FerretTuft;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.投机之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.冰海法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.棱镜法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.焰浪法杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.ShockingDart;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Kinetic;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Shocking;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镐子;
import com.shatteredpixel.shatteredpixeldungeon.items.荣誉纹章;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Door;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GeyserTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GnollRockfallTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.messages.Languages;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.shatteredpixel.shatteredpixeldungeon.赛季设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public abstract class Char extends Actor {
	
	public int pos = 0;
	
	public CharSprite sprite;
	
	public float 最大生命;
	public float 生命;
	public float 护甲=0;
	public int x次必暴=0;
	public int 变脸=0;
	public int 未命中=0;

	public float 大小=1;
	public boolean 首次死亡=true;
	public boolean 史莱姆=false;
	public boolean 吸血鬼飞刀=false;
	public int 第x次攻击=0;
	public int 第x次防御 =0;
	public int 第x次背袭 =0;
	public boolean 产卵=true;
	public boolean 诡异 =false;
	public boolean 必暴 =false;
	public boolean 必中 =false;
	public boolean 必闪 =false;
	public boolean 移速翻倍=false;
	public boolean 移速减半=false;
	public float 属性增幅 = 0.02f;

	protected float 攻击延迟	= 1;
	protected float baseSpeed	= 1;
	protected PathFinder.Path path;

	public int paralysed	    = 0;
	public boolean rooted		= false;
	public boolean flying		= false;
	public int invisible		= 0;

	//these are relative to the hero
	public enum Alignment{
		ENEMY,
		NEUTRAL,
		ALLY
	}
	public Alignment alignment;
	
	public int viewDistance	= 8;
	
	public boolean[] fieldOfView = null;
	
	private LinkedHashSet<Buff> buffs = new LinkedHashSet<>();
	
	@Override
	protected boolean act() {
		if(++变脸>=3){
			变脸=0;
			sprite.hideEmo();
		}

		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
		}
		Dungeon.level.updateFieldOfView( this, fieldOfView );

		//throw any items that are on top of an immovable char
		if (properties().contains(Property.IMMOVABLE)){
			throwItems();
		}
		return false;
	}

	protected void throwItems(){
		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null && heap.type == Heap.Type.HEAP
				&& !(heap.peek() instanceof Tengu.BombAbility.BombItem)
				&& !(heap.peek() instanceof Tengu.ShockerAbility.ShockerItem)) {
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int n : PathFinder.相邻8){
				if (Dungeon.level.passable[pos+n]){
					candidates.add(pos+n);
				}
			}
			if (!candidates.isEmpty()){
				Dungeon.level.drop( heap.pickUp(), Random.element(candidates) ).sprite().drop( pos );
			}
		}
	}

	public String name(){
		return Messages.get(this, "name");
	}

	public boolean canInteract(Char c){
		if (c instanceof Hero hero&&c.alignment!=Alignment.ENEMY&&adjacent(c)){
			return true;
		}
		if (false&&c instanceof Hero hero
				&& alignment == Alignment.ALLY
				&& !hasProp(this, Property.IMMOVABLE)
				&& Dungeon.level.distance(pos, c.pos) <= 2){//移形换位
			return true;
		}

			return false;
	}
	
	//swaps places by default
	public boolean interact(Char c){

		//don't allow char to swap onto hazard unless they're flying
		//you can swap onto a hazard though, as you're not the one instigating the swap
		if (!Dungeon.level.passable[pos] && !c.flying){
			return true;
		}

		//can't swap into a space without room
		if (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[c.pos]
			|| c.properties().contains(Property.LARGE) && !Dungeon.level.openSpace[pos]){
			return true;
		}

		//we do a little raw position shuffling here so that the characters are never
		// on the same cell when logic such as occupyCell() is triggered
		int oldPos = pos;
		int newPos = c.pos;

		//can't swap or ally warp if either char is immovable
		if (hasProp(this, Property.IMMOVABLE) || hasProp(c, Property.IMMOVABLE)){
			return true;
		}

		//warp instantly with allies in this case
		if (c == Dungeon.hero && false){//移形换位
			PathFinder.buildDistanceMap(c.pos, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
			if (PathFinder.distance[pos] == Integer.MAX_VALUE){
				return true;
			}
			pos = newPos;
			c.pos = oldPos;
			传送卷轴.appear(this,newPos);
			传送卷轴.appear(c,oldPos);
			Dungeon.observe();
			GameScene.updateFog();
			return true;
		}

		//can't swap places if one char has restricted movement
		if (paralysed > 0 || c.paralysed > 0 || rooted || c.rooted
				|| buff(Vertigo.class) != null || c.buff(Vertigo.class) != null){
			return true;
		}

		c.pos = oldPos;
		moveSprite( oldPos, newPos );
		move( newPos );

		c.pos = newPos;
		c.sprite.move( newPos, oldPos );
		c.move( oldPos );
		
		c.spend( 1 / c.移速() );

		if (c == Dungeon.hero){
			Dungeon.hero.busy();
		}
		
		return true;
	}
	
	protected boolean moveSprite( int from, int to ) {
		
		if (sprite.isVisible() && sprite.parent != null && (Dungeon.level.heroFOV[from] || Dungeon.level.heroFOV[to])) {
			sprite.move( from, to );
			return true;
		} else {
			sprite.turnTo(from, to);
			sprite.place( to );
			return true;
		}
	}

	public void hitSound( float pitch ){
		Sample.INSTANCE.play(Assets.Sounds.HIT, 1, pitch);
	}

	public boolean blockSound( float pitch ) {
		return false;
	}
	
	protected static final String POS       = "pos";
	protected static final String TAG_HP    = "HP";
	protected static final String TAG_HT    = "HT";
	protected static final String 护甲x    = "护甲";
	protected static final String BUFFS	    = "buffs";
	protected static final String x次必暴x 	    = "x次必暴";
	protected static final String 变脸x 	    = "变脸";
	protected static final String 未命中x 	    = "未命中";
	protected static final String 第x次攻击x 	    = "第x次攻击";
	protected static final String 第x次防御x 	    = "第x次防御";
	protected static final String 第x次背袭x 	    = "第x次背袭";
	protected static final String 产卵x 	    = "产卵";
	protected static final String 诡异x 	    = "诡异";
	protected static final String 大小x 	    = "大小";

	@Override
	public void storeInBundle( Bundle bundle ) {
		
		super.storeInBundle( bundle );
		
		bundle.put( POS, pos );
		bundle.put( TAG_HP, 生命);
		bundle.put( TAG_HT, 最大生命);
		bundle.put( 护甲x, 护甲);
		bundle.put( BUFFS, buffs );
		bundle.put( x次必暴x, x次必暴);
		bundle.put( 变脸x, 变脸);
		bundle.put( 未命中x, 未命中);
		bundle.put( 第x次攻击x, 第x次攻击);
		bundle.put( 第x次防御x, 第x次防御);
		bundle.put( 第x次背袭x, 第x次背袭);
		bundle.put( 产卵x, 产卵);
		bundle.put( 诡异x, 诡异);
		bundle.put( 大小x, 大小);
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		
		super.restoreFromBundle( bundle );
		
		pos = bundle.getInt( POS );
		生命 = bundle.getInt( TAG_HP );
		最大生命 = bundle.getInt( TAG_HT );
		护甲 = bundle.getFloat( 护甲x );
		x次必暴 = bundle.getInt( x次必暴x );
		变脸 = bundle.getInt( 变脸x );
		未命中 = bundle.getInt( 未命中x );
		第x次攻击 = bundle.getInt( 第x次攻击x );
		第x次防御 = bundle.getInt( 第x次防御x );
		第x次背袭 = bundle.getInt( 第x次背袭x );
		产卵 = bundle.getBoolean( 产卵x );
		诡异 = bundle.getBoolean( 诡异x );
		大小 = bundle.getFloat( 大小x );

		for (Bundlable b : bundle.getCollection( BUFFS )) {
			if (b != null) {
				((Buff)b).attachTo( this );
			}
		}
	}

	final public boolean attack( Char enemy ){
		return attack(enemy, 1f, 0f, 1f);
	}
	
	public boolean attack( Char enemy, float dmgMulti, float dmgBonus, float accMulti ) {

		if (enemy == null) return false;
		
		boolean visibleFight = Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[enemy.pos];

		if (enemy.是无敌(getClass())) {

			if (visibleFight) {
				enemy.sprite.showStatus( CharSprite.增强, Messages.get(this, "invulnerable") );

				Sample.INSTANCE.play(Assets.Sounds.HIT_PARRY, 1f, Random.Float(0.96f, 1.05f));
			}

			return false;

		} else if (hit( this, enemy, accMulti, false )) {
			
			float dr = Random.NormalFloat( enemy.最小防御(), enemy.最大防御())
					*AscensionChallenge.statModifier(enemy);

			if(!(enemy instanceof Hero&&enemy instanceof NPC)){
				dr*=Dungeon.难度防御();
				if(enemy.hasbuff(灵焰.class))
					dr*=0.7f;
			}
			if (this instanceof Hero hero){
				if (hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class)!=null){
					dr *= 0.7f;
				}
			}

			//we use a float here briefly so that we don't have to constantly round while
			// potentially applying various multiplier effects
			float dmg;
			潜伏 prep = buff(潜伏.class);
			if (prep != null){
				dmg = prep.damageRoll(this);
				if (this == Dungeon.hero && Dungeon.hero.天赋(Talent.BOUNTY_HUNTER)) {
					Buff.施加(Dungeon.hero, Talent.BountyHunterTracker.class, 0.0f);
				}
			} else {
				if(this instanceof Hero hero){
					dmg=hero.heroDamage(最小攻击(),最大攻击());
				}else{
					dmg=Random.NormalFloat(最小攻击(),最大攻击());
					dmg=dmg*Dungeon.难度攻击();
				}
				if(Dungeon.赛季(赛季设置.英雄联盟)){
					dmg=最小攻击()+最大攻击();
				}
			}

			dmg = dmg*dmgMulti;

			//flat damage bonus is affected by multipliers
			dmg += dmgBonus;

			if (buff( Fury.class ) != null) {
				dmg *= 1.5f;
			}
			for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
				dmg *= buff.meleeDamageFactor();
			}

			dmg *= AscensionChallenge.statModifier(this);


			if (enemy.buff(ScrollOfChallenge.ChallengeArena.class) != null){
				dmg *= 0.67f;
			}

			if (enemy.buff(MonkEnergy.MonkAbility.Meditate.MeditateResistance.class) != null){
				dmg *= 0.2f;
			}

			if ( buff(Weakness.class) != null ){
				dmg *= 0.67f;
			}

			//characters influenced by aggression deal 1/2 damage to bosses
			if ( enemy.buff(StoneOfAggression.Aggression.class) != null
					&& enemy.alignment == alignment
					&& (Char.hasProp(enemy, Property.BOSS) || Char.hasProp(enemy, Property.MINIBOSS))){
				dmg *= 0.5f;
				//yog-dzewa specifically takes 1/4 damage
				if (enemy instanceof YogDzewa){
					dmg *= 0.5f;
				}
			}

			float effectiveDamage = enemy.防御时( this, dmg );
			//do not trigger on-hit logic if defenseProc returned a negative value
			if (effectiveDamage >= 0) {
				//LOL护甲

				if(护甲穿透()>0){
					dr*=护甲穿透();
				}
				if(穿甲()>0){
					dr-=穿甲();
				}
				if(Dungeon.赛季(赛季设置.英雄联盟)){
					effectiveDamage = Math.max(effectiveDamage*(1f-dr/(dr+2.5f)), 0);
				}else effectiveDamage = Math.max(effectiveDamage - dr, 0);

				if (enemy.buff(Viscosity.ViscosityTracker.class) != null) {
					effectiveDamage = enemy.buff(Viscosity.ViscosityTracker.class).deferDamage(effectiveDamage);
					enemy.buff(Viscosity.ViscosityTracker.class).detach();
				}

				//vulnerable specifically applies after armor reductions
				if (enemy.buff(Vulnerable.class) != null) {
					effectiveDamage *= 1.33f;
				}
				
				effectiveDamage = 攻击时(enemy, effectiveDamage);
			}
			if (visibleFight) {
				if (effectiveDamage > 0 || !enemy.blockSound(Random.Float(0.96f, 1.05f))) {
					hitSound(Random.Float(0.87f, 1.15f));
				}
			}

			// If the enemy is already dead, interrupt the attack.
			// This matters as defence procs can sometimes inflict self-damage, such as armor glyphs.
			if (!enemy.isAlive()){
				return true;
			}
			if(enemy.distance(this)>viewDistance/2){
				enemy.sprite.愤怒();//你别让我接近！远距离受伤
			}
			enemy.受伤时( effectiveDamage, this );

			if (buff(FireImbue.class) != null)  buff(FireImbue.class).proc(enemy);
			if (buff(FrostImbue.class) != null) buff(FrostImbue.class).proc(enemy);

			if (enemy.isAlive() && enemy.alignment != alignment && prep != null && prep.canKO(enemy)){
				enemy.生命 = 0;
				if (enemy.buff(Brute.BruteRage.class) != null){
					enemy.buff(Brute.BruteRage.class).detach();
				}
				if (!enemy.isAlive()) {
					enemy.死亡时(this);
				}
				if (enemy.sprite != null) {
					enemy.sprite.showStatus(CharSprite.削弱, Messages.get(潜伏.class,"assassinated"));
				}
			}


			if (enemy.sprite != null) {
				enemy.sprite.bloodBurstA(sprite.center(), effectiveDamage);
				enemy.sprite.flash();
			}

			if (!enemy.isAlive() && visibleFight) {
				if (enemy == Dungeon.hero) {
					
					if (this == Dungeon.hero) {
						return true;
					}

					if (this instanceof WandOfLivingEarth.EarthGuardian
							|| this instanceof MirrorImage || this instanceof PrismaticImage|| this instanceof 白猫){
						Badges.validateDeathFromFriendlyMagic();
					}
					Dungeon.fail( this );
					GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
					
				} else if (this == Dungeon.hero) {
					GLog.i( Messages.capitalize(Messages.get(Char.class, "defeat", enemy.name())) );
				}
			}
			
			return true;
			
		} else {

			if (enemy.sprite != null){
				if (hitMissIcon != -1){
					//dooking is a playful sound Ferrets can make, like low pitched chirping
					// I doubt this will translate, so it's only in English
					if (hitMissIcon == FloatingText.MISS_TUFT && Messages.lang() == Languages.ENGLISH && Random.Int(10) == 0) {
						enemy.sprite.showStatusWithIcon(CharSprite.NEUTRAL, "dooked", hitMissIcon);
					} else {
						enemy.sprite.showStatusWithIcon(CharSprite.NEUTRAL, enemy.defenseVerb(), hitMissIcon);
					}
					hitMissIcon = -1;
				} else {
					enemy.sprite.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
				}
			}
			if (visibleFight) {
				//TODO enemy.defenseSound? currently miss plays for monks/crab even when they parry
				Sample.INSTANCE.play(Assets.Sounds.MISS);
			}
			
			return false;
			
		}

	}

	public static int INFINITE= 1_000_000;

	final public static boolean hit( Char attacker, Char defender ) {
		return hit(attacker, defender, 1, false);
	}

	final public static boolean hit( Char attacker, Char defender, boolean magic ) {
		return hit(attacker, defender, magic ? 2f : 1f, magic);
	}

	public static boolean hit( Char attacker, Char defender, float accMulti, boolean magic ) {
		float acuStat = Random.Float(attacker.最小命中( attacker ),attacker.最大命中( defender ));
		float defStat = Random.Float(defender.最小闪避( attacker ),defender.最大闪避( attacker ));

		if(Dungeon.赛季(赛季设置.英雄联盟)){
			acuStat = attacker.最小命中( attacker )+attacker.最大命中( defender );
			defStat = defender.最小闪避( attacker )+defender.最大闪避( attacker );
		}

		if ( attacker instanceof Hero hero) {
			if (magic&&!Document.ADVENTURERS_GUIDE.isPageRead(Document.法伤)){
				GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.法伤);
			}
			if(defender.恶魔亡灵()&&hero.heroClass(HeroClass.道士)){
				defStat=0;
			}
			if (defender.hasbuff(TalismanOfForesight.CharAwareness.class)){
				defStat=0;
			}
			if (算法.isDebug()){
				defStat=0;
			}

			if(hero.欧皇())defStat/=2;
			if(hero.非酋())defStat*=2;
		}else {
			if(attacker.诡异)acuStat*=10;
		}
		if ( defender instanceof Hero hero) {
			if(attacker.恶魔亡灵()&&hero.belongings.armor() instanceof 道袍){
				acuStat*=0.85f;
			}

			if(hero.欧皇())acuStat*=2;
			if(hero.非酋())acuStat/=2;

			if(hero.damageInterrupt){
				hero.interrupt();
			}
		}else {
			if(defender.诡异)defStat*=10;
		}
		
		if(attacker.必中){
			defStat=0;
			attacker.必中=false;
		}
		if(defender.必闪){
			acuStat=0;
			defender.必闪=false;
		}
		//invisible chars always hit (for the hero this is surprise attacking)
		if (attacker.invisible > 0 && attacker.canSurpriseAttack()){
			defStat=0;
		}

		if (defender.buff(MonkEnergy.MonkAbility.Focus.FocusBuff.class) != null){
			defStat =INFINITE;
		}

		//if accuracy or evasion are large enough, treat them as infinite.
		//note that infinite evasion beats infinite accuracy
		if (defStat>=INFINITE||acuStat==0){
			attacker.未命中++;
			if(attacker instanceof Hero hero&&投机之剑.增加()>0){
				hero.投机之剑-=Math.round(hero.投机之剑*投机之剑.减少());
			}
			if(attacker.未命中>=3&&attacker.未命中%3==0){
				if(attacker.sprite!=null)
					attacker.sprite.哭泣();
			}
			hitMissIcon = FloatingText.getMissReasonIcon(attacker,acuStat,defender,INFINITE);
			return false;
		} else if (acuStat>=INFINITE||defStat==0){
			attacker.未命中=0;
			if(attacker instanceof Hero hero&&投机之剑.增加()>0&&算法.概率学(投机之剑.增加())){
				hero.投机之剑+=new Gold().random().数量();
			}
			hitMissIcon = FloatingText.getHitReasonIcon(attacker,INFINITE,defender,defStat);
			return true;
		}
		
		//region 命中
		if (attacker.buff(Bless.class) != null) acuStat *= 1.25f;
		if (attacker.buff(  Hex.class) != null) acuStat *= 0.8f;
		if (attacker.buff( Daze.class) != null) acuStat *= 0.5f;
		for (ChampionEnemy buff : attacker.buffs(ChampionEnemy.class)){
			acuStat *= buff.evasionAndAccuracyFactor();
		}
		acuStat *= AscensionChallenge.statModifier(attacker);

		acuStat *= accMulti;
		//endregion
		
		//region 闪避
		if (defender.buff(Bless.class) != null) defStat *= 1.25f;
		if (defender.buff(  Hex.class) != null) defStat *= 0.8f;
		if (defender.buff( Daze.class) != null) defStat *= 0.5f;
		for (ChampionEnemy buff : defender.buffs(ChampionEnemy.class)){
			defStat *= buff.evasionAndAccuracyFactor();
		}
		defStat *= AscensionChallenge.statModifier(defender);

		defStat *= FerretTuft.evasionMultiplier();
		//endregion

		if(Dungeon.赛季(赛季设置.英雄联盟)){
			acuStat = acuStat/(acuStat+5f);
			defStat = defStat/(defStat+7.5f);

			if (acuStat>=defStat&&Random.Float()<=acuStat){
				attacker.未命中++;
				if(attacker instanceof Hero hero&&投机之剑.增加()>0){
					hero.投机之剑-=Math.round(hero.投机之剑*投机之剑.减少());
				}
				if(attacker.未命中>=3&&attacker.未命中%3==0){
					if(attacker.sprite!=null)
						attacker.sprite.哭泣();
				}
				hitMissIcon = FloatingText.getHitReasonIcon(attacker,INFINITE,defender,defStat);
				return true;
			} else{
				if (Random.Float()<=defStat){
					attacker.未命中=0;
					if(attacker instanceof Hero hero&&投机之剑.增加()>0&&算法.概率学(投机之剑.增加())){
						hero.投机之剑+=new Gold().random().数量();
					}
					hitMissIcon = FloatingText.getMissReasonIcon(attacker,acuStat,defender,INFINITE);
					return false;
				}else{

					hitMissIcon = FloatingText.getHitReasonIcon(attacker,INFINITE,defender,defStat);
					return true;
				}
			}
		}

		if (acuStat >= defStat){
			attacker.未命中=0;
			if(attacker instanceof Hero hero){
				if(投机之剑.增加()>0&&算法.概率学(投机之剑.增加())){
					hero.投机之剑+=new Gold().random().数量();
				}
			}
			hitMissIcon = FloatingText.getHitReasonIcon(attacker, acuStat, defender, defStat);
			return true;
		} else {
			attacker.未命中++;
			if(defender instanceof Hero hero){

				if(hero.符文("闪避的宠爱"))hero.闪避成长+=0.5f;
			}
			if(attacker instanceof Hero hero){
				if(投机之剑.增加()>0)
				hero.投机之剑-=Math.round(hero.投机之剑*投机之剑.减少());
			}
			if(attacker.未命中>=3&&attacker.未命中%3==0){
				if(attacker.sprite!=null)
					attacker.sprite.哭泣();
			}
			hitMissIcon = FloatingText.getMissReasonIcon(attacker, acuStat, defender, defStat);
			return false;
		}
	}

	private static int hitMissIcon = -1;
	public float 增加命中(Char target){
		float x=1;
		return x;
	}
	public float 增加闪避(Char enemy){
		float x=1;
		return x;
	}

	public int 最小命中(Char target ) {
		return 0;
	}
	public float 增加命中() {
		return 1;
	}
	
	public int 最大命中(Char target ) {
		return 0;
	}
	
	public int 最小闪避(Char enemy ) {
		return 0;
	}
	
	public int 最大闪避(Char enemy ) {
		return 0;
	}
	public float 增加闪避() {
		return 1;
	}
	
	public String defenseVerb() {
		return Messages.get(this, "def_verb");
	}
	
	public float 最小防御() {
		int dr = 0;

		return dr;
	}
	public float 增加防御(){
		float x=1;
		return x;
	}
	public float 最大防御() {
		int dr = 0;
		dr += Barkskin.currentLevel(this);

		return dr;
	}
	public float 增加攻击(){
		float x=1;
		return x;
	}
	
	public float 最小攻击() {
		return 1;
	}
	public float 最大攻击() {
		return 1;
	}
	
	//TODO it would be nice to have a pre-armor and post-armor proc.
	// atm attack is always post-armor and defence is already pre-armor
	
	public int 暴击率(){
		int 暴击率=6;
//		if(Dungeon.赛季(赛季设置.英雄联盟)){
//			暴击率+=18;
//		}
		return 暴击率;
	}
	public float 暴击伤害(){
		float 暴击伤害=1.45f;
//		if(Dungeon.赛季(赛季设置.英雄联盟)){
//			暴击伤害+=0.3f;
//		}
		return 暴击伤害;
	}
	public float 穿甲(){
		return 0;
	}
	public float 护甲穿透(){
		return 0;
	}
	public float 伤害(){
		float 伤害=1f;
		return 伤害;
	}
	public float 暴击(final Char enemy,float dmg){
		if((必暴||算法.概率学(暴击率()))){
			dmg=Math.round(dmg*暴击伤害());
			必暴=false;
			x次必暴=0;
			if(sprite!=null){
				sprite.说("暴击！");
				sprite.歪嘴();
			}
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
		int x= 1;
		return x;
	}
	public float 攻击时(final Char enemy, float damage ) {
		if(enemy!=null)
		第x次攻击++;
		if(enemy!=null)
		Buff.刷新(this,战斗状态.class,5);

		if(enemy!=null&&Dungeon.符文("友好老鬼")&&(老鬼()||小老鬼()))Dungeon.hero.回百分比血(0.05f);
		damage=暴击(enemy,damage);
		
		if(enemy!=null&&吸血()>0){
			if(this instanceof Hero hero&&(hero.符文("猛攻")||hero.符文("升级全能吸血"))){

			}else
			回血(damage * 吸血());
		}
		if(enemy!=null)
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			buff.onAttackProc( enemy );
		}
		if(诡异)damage*=10;
		return damage;
	}
	
	public float 防御时(Char enemy, float damage ) {
		if(enemy!=null)
		第x次防御++;
		if(enemy!=null)
		Buff.刷新(this,战斗状态.class,5);

		Earthroot.Armor armor = buff( Earthroot.Armor.class );
		if (enemy!=null&&armor != null) {
			damage = armor.absorb( damage );
		}

		float scaleFactor = AscensionChallenge.statModifier(this);
		float scaledDmg = damage/scaleFactor;
		if(史莱姆)
			damage=算法.固衰(scaledDmg,5);


		//if dmg is from a character we already reduced it in Char.attack
		if(Dungeon.hero.alignment==alignment&&Dungeon.hero.buff(守御灵光.class)!=null&&
		   Dungeon.level.distance(pos,Dungeon.hero.pos)<=2){
			damage*=1f-Dungeon.hero.天赋点数(Talent.守御灵光,0.1f);
		}
		if(enemy!=null)
		damage=护甲伤害(damage);

		return damage;
	}

	//Returns the level a glyph is at for a char, or -1 if they are not benefitting from that glyph
	//This function is needed as (unlike enchantments) many glyphs trigger in a variety of cases
	public int glyphLevel(Class<? extends Armor.Glyph> cls){
		if (Dungeon.hero() && Dungeon.level != null
				&& this != Dungeon.hero && Dungeon.hero.alignment == alignment
				&& Dungeon.hero.buff(守御灵光.class) != null
				&& (Dungeon.level.distance(pos, Dungeon.hero.pos) <= 2)) {
			return Dungeon.hero.glyphLevel(cls);
		} else {
			return -1;
		}
	}
	
	public float 移速() {
		float speed = baseSpeed;
		if ( buff( Cripple.class ) != null ) speed /= 2f;
		if ( buff( Stamina.class ) != null) speed *= 1.5f;
		if ( buff( Adrenaline.class ) != null) speed *= 2f;
		if ( buff( 极速.class ) != null) speed *= 3f;
		if ( buff( Dread.class ) != null) speed *= 2f;

		if(诡异)speed*=10f;
		speed *= Swiftness.speedBoost(this, glyphLevel(Swiftness.class));
		speed *= Flow.speedBoost(this, glyphLevel(Flow.class));
		speed *= Bulk.speedBoost(this, glyphLevel(Bulk.class));
		if(this instanceof Hero hero){
			if(hero.英精英雄==3)
				if ((Dungeon.level.map[hero.pos] != Terrain.DOOR && Dungeon.level.map[hero.pos] != Terrain.OPEN_DOOR )) {
					return 1;
				} else {
					if (hero.sprite != null){
						hero.sprite.emitter().startDelayed(ShadowParticle.UP,0.02f,5,0.05f);
					}
					speed/=2;
				}
		}
		if(移速翻倍){
			speed*=2;
		}
		if(移速减半){
			speed/=2;
		}
		return speed;
	}

	//currently only used by invisible chars, or by the hero
	public boolean canSurpriseAttack(){
		return true;
	}
	
	//used so that buffs(Shieldbuff.class) isn't called every time unnecessarily
	private float cachedShield = 0;
	public boolean needsShieldUpdate = true;
	
	public float shielding(){
		if (!needsShieldUpdate){
			return cachedShield;
		}
		
		cachedShield = 0;
		for (ShieldBuff s : buffs(ShieldBuff.class)){
			cachedShield += s.护盾量();
		}
		needsShieldUpdate = false;
		return cachedShield;
	}
	public boolean 火焰伤害(Object src){
		return Property.FIERY.immunities().contains(src)||Property.FIERY.resistances().contains(src);
	}
	public boolean 冰霜伤害(Object src){
		return Property.ICY.immunities().contains(src)||Property.ICY.resistances().contains(src);
	}
	public boolean 酸性伤害(Object src){
		return Property.ACIDIC.immunities().contains(src)||Property.ACIDIC.resistances().contains(src);
	}
	public boolean 无机伤害(Object src){
		return Property.INORGANIC.immunities().contains(src)||Property.INORGANIC.resistances().contains(src);
	}
	public void 受伤(float dmg){
		受伤时(dmg,魔法伤害.class);
	}
	public static class 魔法伤害{}
	public void 受伤时(float dmg, Object src ){


		if(!isAlive()||dmg<0){
			return;
		}

		if(是无敌(src.getClass())){
			sprite.showStatus(CharSprite.增强,Messages.get(this,"invulnerable"));
			return;
		}

		if(!(src instanceof LifeLink||src instanceof Hunger)&&buff(LifeLink.class)!=null){
			HashSet<LifeLink> links=buffs(LifeLink.class);
			for(LifeLink link: links.toArray(new LifeLink[0])){
				if(Actor.findById(link.object)==null){
					links.remove(link);
					link.detach();
				}
			}
			dmg=dmg/(links.size()+1);
			for(LifeLink link: links){
				Char ch=(Char)Actor.findById(link.object);
				if(ch!=null){
					ch.受伤时(dmg,link);
					if(!ch.isAlive()){
						link.detach();
						if(ch==Dungeon.hero){
							Badges.validateDeathFromFriendlyMagic();
							Dungeon.fail(src);
							GLog.n(Messages.get(LifeLink.class,"ondeath"));
						}
					}
				}
			}
		}


		if(Dungeon.hero()){
			if(Dungeon.hero.nobuff(战斗状态.class)&&Dungeon.hero.符文("虔焚之热")&&火焰伤害(src)){
				if(Dungeon.hero.暴击(null,1)>1)
					dmg*=Dungeon.hero.暴击伤害();
				Dungeon.hero.回血(dmg);
			}

			if(Dungeon.hero.符文("裁决使")&&残血())
				dmg*=1.1f;

			if(src instanceof Wand){
				if(Dungeon.hero.符文("珠光护手"))
					if(暴击(null,1)>1)
						dmg*=暴击伤害();
			}
			if(src instanceof 法术||src instanceof ClericSpell||src instanceof 道术||src instanceof 巫术||src instanceof 忍术){

			}
			if(src instanceof Weapon){
				if(Dungeon.hero.符文("易损"))
					if(暴击(null,1)>1)
						dmg*=暴击伤害();
			}
		}

		if(Dungeon.赛季(赛季设置.地牢塔防))
			for(int n: PathFinder.范围6){
				Char c=Actor.findChar(pos+n);
				if(c instanceof 黑暗结晶 x&&Dungeon.level.distance(pos,x.pos)<=x.viewDistance&&Dungeon.level.heroFOV[c.pos]){
					dmg*=1.2f+x.tier*0.1f;
				}
			}

		Terror t=buff(Terror.class);
		if(t!=null){
			t.recover();
		}
		Dread d=buff(Dread.class);
		if(d!=null){
			d.recover();
		}
		Charm c=buff(Charm.class);
		if(c!=null){
			c.recover(src);
		}
		if(this.buff(Frost.class)!=null){
			Buff.detach(this,Frost.class);
		}
		if(this.buff(MagicalSleep.class)!=null){
			Buff.detach(this,MagicalSleep.class);
		}
		if(this.buff(Doom.class)!=null&&!免疫(Doom.class)){
			dmg*=1.67f;
		}


		//		if (buff(短柄镰.HarvestBleedTracker.class)!=null){
		//			buff(短柄镰.HarvestBleedTracker.class).detach();
		//
		//			if (!免疫(流血.class)){
		//				流血 b = buff(流血.class);
		//				if (b == null){
		//					b = new 流血();
		//				}
		//				b.announced = false;
		//				b.set(dmg, 短柄镰.HarvestBleedTracker.class);
		//				b.attachTo(this);
		//				sprite.showStatus(CharSprite.WARNING, Messages.titleCase(b.name()) + " " + (int)b.level());
		//				return;
		//			}
		//		}
		if(src instanceof Char cm){
			if(this instanceof Hero){

				dmg*=cm.伤害();
			}else if(Dungeon.hero()){
				dmg*=Dungeon.hero.伤害();
				if(老鬼()){//老鬼因为机制问题，所以不好做增加生命
					dmg*=(1f/Dungeon.难度生命());
				}
				if(诡异)dmg/=10f;
			}
		}

		Class<?> srcClass = src.getClass();
		if (免疫( srcClass )) {
			dmg = 0;
		} else {
			dmg *= resist( srcClass );
		}

		//we ceil these specifically to favor the player vs. champ dmg reduction
		// most important vs. giant champions in the earlygame
		for (ChampionEnemy buff : buffs(ChampionEnemy.class)){
			dmg = dmg * buff.damageTakenFactor();
		}
		
		//TODO improve this when I have proper damage source logic
		if (AntiMagic.RESISTS.contains(src.getClass())){
			dmg -= AntiMagic.drRoll(this, glyphLevel(AntiMagic.class));
			if (buff(ArcaneArmor.class) != null) {
				dmg -= Random.NormalFloat(0, buff(ArcaneArmor.class).level());
			}
			if (dmg < 0) dmg = 0;
		}
		
		if (buff( Paralysis.class ) != null) {
			buff( Paralysis.class ).processDamage(dmg);
		}

		荣誉纹章.WarriorShield shield = buff(荣誉纹章.WarriorShield.class);
		if (!(src instanceof Hunger)
				&& dmg > 0
				&& shield != null && !shield.coolingDown()){
			shield.activate();
		}

		float shielded = dmg;
		dmg = ShieldBuff.processDamage(this, dmg, src);
		shielded -= dmg;

		生命 -= dmg;

		if(sprite!=null&&(dmg>=最大生命(0.34f)||生命<=最大生命(0.34f)))
			sprite.哭泣();
		
		if (生命 > 0 && buff(Grim.GrimTracker.class) != null){

			float finalChance = buff(Grim.GrimTracker.class).maxChance;
			finalChance *= Math.pow( ((最大生命 - 生命) /最大生命), 2);

			if (Random.Float() < finalChance) {
				float extraDmg = 生命 *resist(Grim.class);
				dmg += extraDmg;
				生命 -= extraDmg;

				sprite.emitter().burst( ShadowParticle.UP, 5 );
				if (!isAlive() && buff(Grim.GrimTracker.class).qualifiesForBadge){
					Badges.validateGrimWeapon();
				}
			}
		}

		if (生命 < 0 && src instanceof Char && alignment == Alignment.ENEMY){
			if (((Char) src).buff(Kinetic.KineticTracker.class) != null){
				float dmgToAdd = -生命;
				dmgToAdd -= ((Char) src).buff(Kinetic.KineticTracker.class).conservedDamage;
				dmgToAdd = dmgToAdd * Weapon.Enchantment.genericProcChanceMultiplier((Char) src);
				if (dmgToAdd > 0) {
					Buff.施加((Char) src, Kinetic.ConservedDamage.class).setBonus(dmgToAdd);
				}
				((Char) src).buff(Kinetic.KineticTracker.class).detach();
			}
		}
		
		if (sprite != null) {
			//defaults to normal damage icon if no other ones apply
			int                                                         icon = FloatingText.PHYS_DMG;
			if (NO_ARMOR_PHYSICAL_SOURCES.contains(src.getClass()))     icon = FloatingText.PHYS_DMG_NO_BLOCK;
			if (AntiMagic.RESISTS.contains(src.getClass()))             icon = FloatingText.MAGIC_DMG;
			if (src instanceof 镐子) icon = FloatingText.PICK_DMG;

			//special case for sniper when using ranged attacks
			if (src == Dungeon.hero
					&& Dungeon.hero.subClass == HeroSubClass.狙击手){
				icon = FloatingText.PHYS_DMG_NO_BLOCK;
			}

			//special case for monk using unarmed abilities
			if (src == Dungeon.hero
					&& Dungeon.hero.buff(MonkEnergy.MonkAbility.UnarmedAbilityTracker.class) != null){
				icon = FloatingText.PHYS_DMG_NO_BLOCK;
			}

			if (src instanceof Hunger)                                  icon = FloatingText.HUNGER;
			if (src instanceof 燃烧)                                 icon = FloatingText.BURNING;
			if (src instanceof Chill || src instanceof Frost)           icon = FloatingText.FROST;
			if (src instanceof GeyserTrap || src instanceof StormCloud) icon = FloatingText.WATER;
			if (src instanceof 燃烧)                                 icon = FloatingText.BURNING;
			if (src instanceof Electricity)                             icon = FloatingText.SHOCKING;
			if (src instanceof 流血)                                icon = FloatingText.BLEEDING;
			if (src instanceof ToxicGas)                                icon = FloatingText.TOXIC;
			if (src instanceof Corrosion)                               icon = FloatingText.CORROSION;
			if (src instanceof Poison)                                  icon = FloatingText.POISON;
			if (src instanceof Ooze)                                    icon = FloatingText.OOZE;
			if (src instanceof Viscosity.DeferedDamage)                 icon = FloatingText.DEFERRED;
			if (src instanceof Corruption)                              icon = FloatingText.CORRUPTION;
			if (src instanceof AscensionChallenge)                      icon = FloatingText.AMULET;

			if ((icon == FloatingText.PHYS_DMG || icon == FloatingText.PHYS_DMG_NO_BLOCK) && hitMissIcon != -1){
				if (icon == FloatingText.PHYS_DMG_NO_BLOCK) hitMissIcon += 18; //extra row
				icon = hitMissIcon;
			}
			hitMissIcon = -1;
			if(dmg + shielded>=1&&!Dungeon.赛季(赛季设置.地牢塔防))
				sprite.showStatusWithIcon(CharSprite.削弱, dmg + shielded, icon);
		}

		if (生命 < 0) 生命 = 0;

		if (!isAlive()) {
			死亡时( src );
		}
	}

	//these are misc. sources of physical damage which do not apply armor, they get a different icon
	private static HashSet<Class> NO_ARMOR_PHYSICAL_SOURCES = new HashSet<>();
	{
		NO_ARMOR_PHYSICAL_SOURCES.add(CrystalSpire.SpireSpike.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollGeomancer.Boulder.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollGeomancer.GnollRockFall.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(GnollRockfallTrap.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DwarfKing.KingDamager.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DwarfKing.Summoning.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(LifeLink.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(Chasm.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(WandOfBlastWave.Knockback.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(Heap.class); //damage from wraiths attempting to spawn from heaps
		NO_ARMOR_PHYSICAL_SOURCES.add(Necromancer.SummoningBlockDamage.class);
		NO_ARMOR_PHYSICAL_SOURCES.add(DriedRose.GhostHero.NoRoseDamage.class);
	}
	
	public void destroy() {
		生命 = 0;
		Actor.remove( this );

		for (Char ch : Actor.chars().toArray(new Char[0])){
			if (ch.buff(Charm.class) != null && ch.buff(Charm.class).object == id()){
				ch.buff(Charm.class).detach();
			}
			if (ch.buff(Dread.class) != null && ch.buff(Dread.class).object == id()){
				ch.buff(Dread.class).detach();
			}
			if (ch.buff(Terror.class) != null && ch.buff(Terror.class).object == id()){
				ch.buff(Terror.class).detach();
			}
			if (ch.buff(SnipersMark.class) != null && ch.buff(SnipersMark.class).object == id()){
				ch.buff(SnipersMark.class).detach();
			}
			
		}
	}
	
	public void 死亡时(Object src ) {
		destroy();
		if (src != Chasm.class) {
			if(src instanceof 尘遁)
				sprite.速度die();
			else
			sprite.die();
			if (!flying && Dungeon.level != null && sprite instanceof MobSprite && Dungeon.level.map[pos] == Terrain.CHASM){
				((MobSprite) sprite).fall();
			}
		}
	}

	//we cache this info to prevent having to call buff(...) in isAlive.
	//This is relevant because we call isAlive during drawing, which has both performance
	//and thread coordination implications
	public boolean deathMarked = false;
	
	public boolean isAlive() {
		return 生命 > 0 || deathMarked;
	}

	public boolean isActive() {
		return isAlive();
	}
	
	public void spendConstant() {
		spendConstant(1);
	}
	@Override
	protected void spendConstant(float time) {
		时光沙漏.timeFreeze freeze = buff(时光沙漏.timeFreeze.class);
		if (freeze != null) {
			freeze.processTime(time);
			return;
		}

		Swiftthistle.TimeBubble bubble = buff(Swiftthistle.TimeBubble.class);
		if (bubble != null){
			bubble.processTime(time);
			return;
		}

		super.spendConstant(time);
	}
	
	public void spend() {
		spend(1);
	}
	
	public float 攻击延迟() {
		float delay = 攻击延迟;
		if(诡异)delay/=10f;
		return delay;
	}
	
	@Override
	protected void spend( float time ) {

		float timeScale = 1f;
		if (buff( Slow.class ) != null) {
			timeScale *= 0.5f;
			//slowed and chilled do not stack
		} else if (buff( Chill.class ) != null) {
			timeScale *= buff( Chill.class ).speedFactor();
		}
		if (buff( Speed.class ) != null) {
			timeScale *= 2.0f;
		}
		
		super.spend( time / timeScale );
	}
	
	public synchronized LinkedHashSet<Buff> buffs() {
		return new LinkedHashSet<>(buffs);
	}
	
	@SuppressWarnings("unchecked")
	//returns all buffs assignable from the given buff class
	public synchronized <T extends Buff> HashSet<T> buffs( Class<T> c ) {
		HashSet<T> filtered = new HashSet<>();
		for (Buff b : buffs) {
			if (c.isInstance( b )) {
				filtered.add( (T)b );
			}
		}
		return filtered;
	}

	@SuppressWarnings("unchecked")
	//returns an instance of the specific buff class, if it exists. Not just assignable
	public synchronized  <T extends Buff> T buff( Class<T> c ) {
		for (Buff b : buffs) {
			if (b.getClass() == c) {
				return (T)b;
			}
		}
		return null;
	}
	public boolean hasbuff( Class c ){
		return buff(c)!= null;
	}
	public boolean nobuff( Class c ){
		return buff(c)== null;
	}

	public synchronized boolean isCharmedBy( Char ch ) {
		int chID = ch.id();
		for (Buff b : buffs) {
			if (b instanceof Charm && ((Charm)b).object == chID) {
				return true;
			}
		}
		return false;
	}

	public synchronized boolean add( Buff buff ) {

		if (buff(PotionOfCleansing.Cleanse.class) != null) { //cleansing buff
			if (buff.type == Buff.buffType.NEGATIVE
					&& !(buff instanceof AllyBuff)
					&& !(buff instanceof LostInventory)){
				return false;
			}
		}

		if (sprite != null && buff(Challenge.SpectatorFreeze.class) != null){
			return false; //can't add buffs while frozen and game is loaded
		}

		buffs.add( buff );
		if (Actor.chars().contains(this)) Actor.add( buff );

		if (sprite != null && buff.announced) {
			switch (buff.type) {
				case POSITIVE:
					sprite.showStatus(CharSprite.增强, Messages.titleCase(buff.name()));
					break;
				case NEGATIVE:
					吞噬灵魂(this);
					sprite.showStatus(CharSprite.WARNING, Messages.titleCase(buff.name()));
					break;
				case NEUTRAL:
				default:
					sprite.showStatus(CharSprite.NEUTRAL, Messages.titleCase(buff.name()));
					break;
			}
		}

		return true;

	}

	public static void 吞噬灵魂(Char target){
		if(target instanceof Hero){

		}else{
			if(target.alignment==Char.Alignment.ENEMY){
				if(Dungeon.符文("吞噬灵魂")){
					Dungeon.hero.生命成长+=3;
				}
			}
		}
	}
	public synchronized boolean remove( Buff buff ) {
		
		buffs.remove( buff );
		Actor.remove( buff );

		return true;
	}
	
	public synchronized void remove( Class<? extends Buff> buffClass ) {
		for (Buff buff : buffs( buffClass )) {
			remove( buff );
		}
	}
	
	@Override
	protected synchronized void onRemove() {
		for (Buff buff : buffs.toArray(new Buff[buffs.size()])) {
			buff.detach();
		}
	}
	
	public synchronized void updateSpriteState() {
		for (Buff buff:buffs) {
			buff.fx( true );
		}
	}
	
	public float stealth() {
		float stealth = 0;

		stealth += Obfuscation.stealthBoost(this, glyphLevel(Obfuscation.class));

		return stealth;
	}

	public final void move( int step ) {
		move( step, true );
	}

	//travelling may be false when a character is moving instantaneously, such as via teleportation
	public void move( int step, boolean travelling ) {

		if (travelling && Dungeon.level.adjacent( step, pos ) && buff( Vertigo.class ) != null) {
			sprite.interruptMotion();
			int newPos = pos + PathFinder.相邻8[Random.Int(8)];
			if (!(Dungeon.level.passable[newPos] || Dungeon.level.avoid[newPos])
					|| (properties().contains(Property.LARGE) && !Dungeon.level.openSpace[newPos])
					|| Actor.findChar( newPos ) != null)
				return;
			else {
				sprite.move(pos, newPos);
				step = newPos;
			}
		}

		if (Dungeon.level.map[pos] == Terrain.OPEN_DOOR) {
			Door.leave( pos );
		}

		pos = step;
		
		if (this != Dungeon.hero) {
			sprite.visible = Dungeon.level.heroFOV[pos];
		}
		
		Dungeon.level.occupyCell(this );
	}
	
	public boolean adjacent( Char other ) {
		return Dungeon.level.adjacent( pos, other.pos );
	}
	public int distance( Char other ) {
		return Dungeon.level.distance( pos, other.pos );
	}
	public boolean[] modifyPassable( boolean[] passable){
		//do nothing by default, but some chars can pass over terrain that others can't
		return passable;
	}
	
	public void onMotionComplete() {
		//Does nothing by default
		//The main actor thread already accounts for motion,
		// so calling next() here isn't necessary (see Actor.process)
	}
	
	public void onAttackComplete() {
		next();
	}
	
	public void onOperateComplete() {
		next();
	}
	
	protected final HashSet<Class> 害怕 = new HashSet<>();
	protected final HashSet<Class> resistances = new HashSet<>();

	//returns percent effectiveness after resistances
	//TODO currently resistances reduce effectiveness by a static 50%, and do not stack.
	public float resist( Class effect ){
		HashSet<Class> resists = new HashSet<>(resistances);
		for (Property p : properties()){
			resists.addAll(p.resistances());
		}
		for (Buff b : buffs()){
			resists.addAll(b.resistances());
		}
		float result = 1f;
		for (Class c : resists){
			if (c.isAssignableFrom(effect)){
				result *= 0.5f;
			}
		}
		HashSet<Class> 害怕x = new HashSet<>(害怕);

		for (Property p : properties()){
			resists.addAll(p.害怕());
		}
		for (Class c : 害怕x){
			if (c.isAssignableFrom(effect)){
				result *= 1.5f;
			}
		}
		return result * RingOfElements.resist(this, effect);
	}
	
	protected final HashSet<Class> immunities = new HashSet<>();
	
	public boolean 免疫(Class effect ){
		HashSet<Class> immunes = new HashSet<>(immunities);
		for (Property p : properties()){
			immunes.addAll(p.immunities());
		}
		for (Buff b : buffs()){
			immunes.addAll(b.immunities());
		}
		if (glyphLevel(Brimstone.class) >= 0){
			immunes.add(燃烧.class);
		}
		
		for (Class c : immunes){
			if (c.isAssignableFrom(effect)){
				return true;
			}
		}
		return false;
	}

	//similar to isImmune, but only factors in damage.
	//Is used in AI decision-making
	public boolean 是无敌(Class effect ){
		return buff(Challenge.SpectatorFreeze.class) != null || buff(Invulnerability.class) != null;
	}

	public HashSet<Property> properties = new HashSet<>();

	public HashSet<Property> properties() {
		HashSet<Property> props = new HashSet<>(properties);
		//TODO any more of these and we should make it a property of the buff, like with resistances/immunities
		if (buff(ChampionEnemy.Giant.class) != null) {
			props.add(Property.LARGE);
		}
		return props;
	}

	public enum Property{
		BOSS ( new HashSet<Class>( Arrays.asList(Grim.class,GrimTrap.class,复仇卷轴.class,ScrollOfPsionicBlast.class)),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) ),new HashSet<Class>()),
		MINIBOSS ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class) ),new HashSet<Class>()),
		BOSS_MINION,
		傀儡,
		UNDEAD,
		DEMONIC,
		昆虫,
		动物(new HashSet<Class>(),new HashSet<Class>(),new HashSet<Class>(Arrays.asList(
				流血.class, ToxicGas.class, Poison.class,

				Corrosion.class,
				Ooze.class,

				WandOfFrost.class,冰海法杖.class, Elemental.FrostElemental.class,
				Frost.class, Chill.class,

				焰浪法杖.class,火毒.class,Elemental.FireElemental.class,
				燃烧.class, Blazing.class))),
		植物(new HashSet<Class>(),new HashSet<Class>(),new HashSet<Class>(Arrays.asList(焰浪法杖.class,火毒.class,Elemental.FireElemental.class,
																						燃烧.class, Blazing.class))),
		海妖,
		光明( new HashSet<Class>(),
			  new HashSet<Class>( Arrays.asList(WandOfPrismaticLight.class,棱镜法杖.class) ),new HashSet<Class>()),
		机械,
		INORGANIC ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(流血.class, ToxicGas.class, Poison.class) ),new HashSet<Class>()),
		FIERY ( new HashSet<Class>( Arrays.asList(焰浪法杖.class,火毒.class,Elemental.FireElemental.class)),
				new HashSet<Class>( Arrays.asList(燃烧.class, Blazing.class)),new HashSet<Class>(
				Arrays.asList(WandOfFrost.class,冰海法杖.class, Elemental.FrostElemental.class,
							  Frost.class, Chill.class))),
		ICY ( new HashSet<Class>( Arrays.asList(WandOfFrost.class,冰海法杖.class,Elemental.FrostElemental.class)),
				new HashSet<Class>( Arrays.asList(Frost.class, Chill.class)),new HashSet<Class>(
				Arrays.asList(焰浪法杖.class,火毒.class,Elemental.FireElemental.class,
				燃烧.class, Blazing.class))),
		ACIDIC ( new HashSet<Class>( Arrays.asList(Corrosion.class)),
				new HashSet<Class>( Arrays.asList(Ooze.class)),new HashSet<Class>(Arrays.asList(焰浪法杖.class,火毒.class,Elemental.FireElemental.class,
																								燃烧.class, Blazing.class))),
		ELECTRIC ( new HashSet<Class>( Arrays.asList(WandOfLightning.class, Shocking.class, Potential.class,
										Electricity.class, ShockingDart.class, Elemental.ShockElemental.class )),new HashSet<Class>(),
				new HashSet<Class>()),
		LARGE,
		IMMOVABLE ( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(Vertigo.class) ),new HashSet<Class>()),
		//A character that acts in an unchanging manner. immune to AI state debuffs or stuns/slows
		STATIC( new HashSet<Class>(),
				new HashSet<Class>( Arrays.asList(AllyBuff.class, Dread.class, Terror.class, Amok.class, Charm.class, Sleep.class,
									Paralysis.class, Frost.class, Chill.class, Slow.class, Speed.class) ),new HashSet<Class>());

		private HashSet<Class> resistances;
		private HashSet<Class> immunities;
		private HashSet<Class> 害怕;

		Property(){
			this(new HashSet<Class>(), new HashSet<Class>(), new HashSet<Class>());
		}
		
		Property( HashSet<Class> resistances, HashSet<Class> immunities, HashSet<Class> 害怕){
			this.resistances = resistances;
			this.immunities = immunities;
			this.害怕 = 害怕;
		}
		
		public HashSet<Class> resistances(){
			return new HashSet<>(resistances);
		}
		
		public HashSet<Class> immunities(){
			return new HashSet<>(immunities);
		}
		public HashSet<Class> 害怕(){
			return new HashSet<>(害怕);
		}
	}

	public static boolean hasProp( Char ch, Property p){
		return (ch != null && ch.properties().contains(p));
	}
	public float 生命力(){
		return (float)Math.sqrt(最大生命);
	}
	public float 生命力(float x){
		return 生命力()*x;
	}
	public float 护甲(float x){
		护甲=Math.min(Math.max(护甲+x,0),最大护甲());
		return 护甲;
	}
	public float 最大护甲(float x){
		return 最大护甲()*x;
	}
	public float 恢复百分比护甲(float x){
		return 护甲(最大护甲()*x);
	}

	public float 护甲伤害(float dmg){
		if(this instanceof Hero hero){
			dmg-=Random.NormalFloat(最小防御(),最大防御())*hero.天赋点数(Talent.金刚不坏,0.075f);
		}
		if(dmg>0&&护甲>0){
			if (护甲<=最大护甲()/2&&!Document.ADVENTURERS_GUIDE.isPageRead(Document.护甲)){
				GameScene.flashForDocument(Document.ADVENTURERS_GUIDE,Document.护甲);
			}
			if(护甲>=dmg){
				护甲(-dmg);
				dmg=0;
			}else{
				dmg-=护甲;
				护甲=0;
			}
		}
		return dmg;
	}
	public float 回满护甲(){
		护甲=最大护甲();
		return 护甲;
	}
	public float 最大护甲(){
		float 最大护甲=最大生命(0.25f);
		
		return 最大护甲;
	}
	public float 已损失护甲(){
		return 最大护甲()-护甲;
	}
	public float 已损失护甲(float x){
		return 已损失护甲()*x;
	}
	public float 根据已损失护甲(){
		return 已损失护甲()/最大护甲();
	}
	public float 已损失生命(){
		return 最大生命-生命;
	}
	public float 根据已损失生命(){
		return 已损失生命()/最大生命;
	}
	public float 已损失生命(float x){
		return 已损失生命()*x;
	}
	public float 生命(float x){
		return 生命*x;
	}
	public float 最大生命(float x){
		return 最大生命*x;
	}
	public boolean 残血(){
		return 生命/最大生命<=0.33f;
	}
	public boolean 半血(){
		return 生命/最大生命>0.33f&&(float)生命/最大生命<=0.66f;
	}
	public boolean 半血以下(){
		return 生命/最大生命<=0.5f;
	}
	public boolean 康血(){
		return 生命/最大生命>=0.66f;
	}
	public boolean 满血(){
		return 生命==最大生命;
	}
	public void 回满血(){
		回血(最大生命);
	}
	public void 回百分比血(float x){
		回血(最大生命(x));
	}
	public void 回已损失血(float x){
		回血(已损失生命(x));
	}
	public float 治疗效果(){
		return 1;
	}
	public void 回血(float x){
		x*=治疗效果();
		if(x>0){
			生命=Math.min(生命+x,最大生命);
			if (Dungeon.level.heroFOV[pos]){
				if(sprite!=null&&sprite.visible&&x>=1&&!Dungeon.赛季(赛季设置.地牢塔防)){
					sprite.showStatusWithIcon(CharSprite.增强,x,FloatingText.HEALING);
					sprite.emitter().burst(Speck.factory(Speck.HEALING),1+x/10);
				}
			}

		}else if(x<0){
			受伤(x);
		}
	}
	public float 防御(Char enemy, float damage){
		damage-=Random.NormalFloat(最小防御(),最大防御());
		damage=防御时(enemy,damage);
		return damage;
	}
	public int 视野范围(){
		int x=viewDistance;
		return x;
	}

	public boolean 在地板(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.EMPTY_SP;
	}
	public boolean 在陷阱(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.TRAP|| Dungeon.level.map[pos] == Terrain.INACTIVE_TRAP;
	}
	public boolean 在水中(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.WATER;
	}
	public boolean 在草丛(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.GRASS||
				Dungeon.level.map[pos] == Terrain.HIGH_GRASS||
				Dungeon.level.map[pos] == Terrain.FURROWED_GRASS;
	}
	public boolean 在深渊(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.CHASM;
	}
	public boolean 在门上(){
		if(Dungeon.level==null)return false;
		else
		return Dungeon.level.map[pos] == Terrain.OPEN_DOOR;
	}
	public boolean 在狭窄(){
		int 墙 = 0;
		for (int i : PathFinder.相邻8) {
			if (Dungeon.level.solid[pos + i]) {
				墙 ++;
			}
		}
		if(墙>=5){
			return true;
		}
		return false;
	}
	public boolean 实体墙(int x){
		int 墙 = 0;
		for (int i : PathFinder.相邻8) {
			if (Dungeon.level.solid[pos + i]) {
				墙 ++;
			}
		}
		if(墙<=x){//周围4墙横穿是最好的，5包括以上都会卡
			return true;
		}
		return false;
	}
	public float 吸血(){
		return 0;
	}
	public float 全能吸血(){
		return 0;
	}
	public boolean 恶魔(){
		return properties().contains(Property.DEMONIC);
	}
	public boolean 亡灵(){
		return properties().contains(Property.UNDEAD);
	}
	public boolean 恶魔亡灵(){
		return properties().contains(Property.UNDEAD)||properties().contains(Property.DEMONIC);
	}
	public boolean 老鬼(){
		return properties().contains(Property.BOSS);
	}
	public boolean 豺狼(){
		if(this instanceof Brute||
		   this instanceof Gnoll||
		   this instanceof GnollExile||
		   this instanceof GnollGeomancer||
		   this instanceof GnollGuard||
		   this instanceof GnollSapper||
		   this instanceof GnollTrickster||
		   this instanceof Shaman
		   )return true;

		return false;
	}
	public boolean 老鬼傀儡(){
		return properties().contains(Property.BOSS_MINION);
	}
	public boolean 防刷(){
		if(Dungeon.符文("叠角龙"))return true;

		return !老鬼()&&!小老鬼()&&!傀儡()&&!老鬼傀儡();
	}
	public boolean 傀儡(){
		return properties().contains(Property.傀儡);
	}
	public boolean 小老鬼(){
		return properties().contains(Property.MINIBOSS);
	}
	public boolean 庞大(){
		return properties().contains(Property.LARGE);
	}
	public boolean 无机物(){
		return properties().contains(Property.INORGANIC);
	}
	public boolean 火焰(){
		return properties().contains(Property.FIERY);
	}
	public boolean 寒冰(){
		return properties().contains(Property.ICY);
	}
	public boolean 酸性(){
		return properties().contains(Property.ACIDIC);
	}
	public boolean 闪电(){
		return properties().contains(Property.ELECTRIC);
	}
	public boolean 低活动度生物(){
		return properties().contains(Property.STATIC);
	}
	public boolean 静物(){
		return properties().contains(Property.IMMOVABLE);
	}
	public boolean 动物(){
		return properties().contains(Property.动物);
	}
	public boolean 昆虫(){
		return properties().contains(Property.昆虫);
	}
	public boolean 植物(){
		return properties().contains(Property.植物);
	}
	public boolean 海妖(){
		return properties().contains(Property.海妖);
	}
	public boolean 机械(){
		return properties().contains(Property.机械);
	}
}
