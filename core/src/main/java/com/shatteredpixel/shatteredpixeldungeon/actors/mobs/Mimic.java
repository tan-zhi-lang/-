

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.MimicTooth;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MimicSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Mimic extends Mob {
	
	private int level;
	
	{
		spriteClass = MimicSprite.class;

		properties.add(Property.DEMONIC);

		经验 = 0;
		
		//mimics are neutral when hidden
		alignment = Alignment.NEUTRAL;
		state = PASSIVE;
	}
	
	public ArrayList<Item> items;

	private boolean stealthy = false;
	
	private static final String LEVEL	= "level";
	private static final String ITEMS	= "items";
	private static final String STEALTHY= "stealthy";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		if (items != null) bundle.put( ITEMS, items );
		bundle.put( LEVEL, level );
		bundle.put( STEALTHY, stealthy );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		if (bundle.contains( ITEMS )) {
			items = new ArrayList<>((Collection<Item>) ((Collection<?>) bundle.getCollection(ITEMS)));
		}
		level = bundle.getInt( LEVEL );
		adjustStats(level);
		stealthy = bundle.getBoolean(STEALTHY);
		super.restoreFromBundle(bundle);
		if (state != PASSIVE && alignment == Alignment.NEUTRAL){
			alignment = Alignment.ENEMY;
		}
	}

	@Override
	public boolean add(Buff buff) {
		if (super.add(buff)) {
			if (buff.type == Buff.buffType.NEGATIVE && alignment == Alignment.NEUTRAL) {
				alignment = Alignment.ENEMY;
				stopHiding();
				if (sprite != null) sprite.idle();
			}
			return true;
		}
		return false;
	}

	@Override
	public String name() {
		if (alignment == Alignment.NEUTRAL){
			return Messages.get(Heap.class, "chest");
		} else {
			return super.name();
		}
	}

	@Override
	public String description() {
		if (alignment == Alignment.NEUTRAL){
			if (MimicTooth.stealthyMimics()){
				return Messages.get(Heap.class, "chest_desc");
			} else {
				return Messages.get(Heap.class, "chest_desc") + "\n\n" + Messages.get(this, "hidden_hint");
			}
		} else {
			return super.description();
		}
	}

	@Override
	protected boolean act() {
		if (alignment == Alignment.NEUTRAL && state != PASSIVE){
			alignment = Alignment.ENEMY;
			if (sprite != null) sprite.idle();
			if (Dungeon.level.heroFOV[pos]) {
				GLog.w(Messages.get(this, "reveal") );
				CellEmitter.get(pos).burst(Speck.factory(Speck.STAR), 10);
				Sample.INSTANCE.play(Assets.Sounds.MIMIC);
			}
		}
		return super.act();
	}

	@Override
	public CharSprite sprite() {
		MimicSprite sprite = (MimicSprite) super.sprite();
		if (alignment == Alignment.NEUTRAL) sprite.hideMimic(this);
		return sprite;
	}

	@Override
	public boolean interact(Char c) {
		if (alignment != Alignment.NEUTRAL || c != Dungeon.hero){
			return super.interact(c);
		}
		stopHiding();

		Dungeon.hero.busy();
		Dungeon.hero.sprite.operate(pos);
		if (Dungeon.hero.invisible <= 0
				&& Dungeon.hero.buff(Swiftthistle.TimeBubble.class) == null
				&& Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class) == null){
			return doAttack(Dungeon.hero);
		} else {
			sprite.idle();
			alignment = Alignment.ENEMY;
			Dungeon.hero.spendAndNext(1f);
			return true;
		}
	}

	@Override
	public void onAttackComplete() {
		super.onAttackComplete();
		if (alignment == Alignment.NEUTRAL){
			alignment = Alignment.ENEMY;
			Dungeon.hero.spendAndNext(1f);
		}
	}

	@Override
	public int 防御时(Char enemy, int damage) {
		if (state == PASSIVE){
			alignment = Alignment.ENEMY;
			stopHiding();
		}
		return super.防御时(enemy, damage);
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		if (state == PASSIVE){
			alignment = Alignment.ENEMY;
			stopHiding();
		}
		super.受伤时(dmg, src);
	}

	@Override
	public void 死亡时(Object cause) {
		if (state == PASSIVE){
			alignment = Alignment.ENEMY;
			stopHiding();
		}
		super.死亡时(cause);
	}

	public void stopHiding(){
		state = HUNTING;
		if (sprite != null) sprite.idle();
		if (Actor.chars().contains(this) && Dungeon.level.heroFOV[pos]) {
			enemy = Dungeon.hero;
			target = Dungeon.hero.pos;
			GLog.w(Messages.get(this, "reveal") );
			CellEmitter.get(pos).burst(Speck.factory(Speck.STAR), 10);
			Sample.INSTANCE.play(Assets.Sounds.MIMIC);
		}
	}

	//stealthy mimics have changes to visual behaviour that make them much harder to detect
	public boolean stealthy(){
		return stealthy;
	}

	@Override
	public int 攻击() {
		if (alignment == Alignment.NEUTRAL){
			return Random.NormalIntRange( 2 + 2*level, 2 + 2*level);
		} else {
			return Random.NormalIntRange( 1 + level, 2 + 2*level);
		}
	}

	@Override
	public int drRoll() {
		return super.drRoll() + Random.NormalIntRange(0, 1 + level/2);
	}

	@Override
	public void beckon( int cell ) {
		if (alignment != Alignment.NEUTRAL) {
			super.beckon(cell);
		}
	}

	@Override
	public int 最大命中(Char target ) {
		if (target != null && alignment == Alignment.NEUTRAL && target.invisible <= 0){
			return INFINITE_ACCURACY;
		} else {
			return 6 + level;
		}
	}

	public void setLevel( int level ){
		this.level = level;
		adjustStats(level);
	}
	
	public void adjustStats( int level ) {
		生命 = 最大生命 = (1 + level) * 6;
		defenseSkill = 2 + level/2;
		
		enemySeen = true;
	}
	
	@Override
	public void rollToDropLoot(){
		
		if (items != null) {
			for (Item item : items) {
				Dungeon.level.drop( item, pos ).sprite.drop();
			}
			items = null;
		}
		super.rollToDropLoot();
	}

	@Override
	public float spawningWeight() {
		return 0f;
	}

	@Override
	public boolean reset() {
		if (state != PASSIVE) state = WANDERING;
		return true;
	}

	public static Mimic spawnAt( int pos, Item... items){
		return spawnAt(pos, Mimic.class, items);
	}

	public static Mimic spawnAt( int pos, Class mimicType, Item... items){
		return spawnAt(pos, mimicType, true, items);
	}

	public static Mimic spawnAt( int pos, boolean useDecks, Item... items){
		return spawnAt(pos, Mimic.class, useDecks, items);
	}

	public static Mimic spawnAt( int pos, Class mimicType, boolean useDecks, Item... items){
		Mimic m;
		if (mimicType == GoldenMimic.class){
			m = new GoldenMimic();
		} else if (mimicType == CrystalMimic.class) {
			m = new CrystalMimic();
		} else if (mimicType == EbonyMimic.class) {
			m = new EbonyMimic();
		} else {
			m = new Mimic();
		}

		m.items = new ArrayList<>( Arrays.asList(items) );
		m.setLevel( Dungeon.scalingDepth() );
		m.pos = pos;

		//generate an extra reward for killing the mimic
		m.generatePrize(useDecks);

		if (MimicTooth.stealthyMimics()){
			m.stealthy = true;
		}

		return m;
	}

	protected void generatePrize( boolean useDecks ){
		Item reward = null;
		do {
			switch (Random.Int(5)) {
				case 0:
					reward = new Gold().random();
					break;
				case 1:
					reward = Generator.randomMissile(!useDecks);
					break;
				case 2:
					reward = Generator.randomArmor();
					break;
				case 3:
					reward = Generator.randomWeapon(!useDecks);
					break;
				case 4:
					reward = useDecks ? Generator.random(Generator.Category.RING) : Generator.randomUsingDefaults(Generator.Category.RING);
					break;
			}
		} while (reward == null || Challenges.isItemBlocked(reward));
		items.add(reward);

		if (MimicTooth.stealthyMimics()){
			//add an extra random item if player has a mimic tooth
			items.add(Generator.randomUsingDefaults());
		}
	}

}
