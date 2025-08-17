

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.RatSkull;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon.Enchantment;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.StatueSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Statue extends Mob {
	
	{
		spriteClass = StatueSprite.class;

		经验 = 0;
		state = PASSIVE;
		
		properties.add(Property.INORGANIC);
	}
	
	protected Weapon weapon;

	public boolean levelGenStatue = true;
	
	public Statue() {
		super();
		
		生命 = 最大生命 = 15 + Dungeon.depth * 5;
		defenseSkill = 4 + Dungeon.depth;
	}

	public void createWeapon( boolean useDecks ){
		if (useDecks) {
			weapon = (MeleeWeapon) Generator.random(Generator.Category.WEAPON);
		} else {
			weapon = (MeleeWeapon) Generator.randomUsingDefaults(Generator.Category.WEAPON);
		}
		levelGenStatue = useDecks;
		weapon.cursed = false;
		weapon.enchant( Enchantment.random() );
	}

	public Weapon weapon(){
		return weapon;
	}
	
	private static final String WEAPON	= "weapon";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( WEAPON, weapon );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		weapon = (Weapon)bundle.get( WEAPON );
	}
	
	@Override
	public int 攻击() {
		return weapon.damageRoll(this);
	}
	
	@Override
	public int 最大命中(Char target ) {
		return (int)((9 + Dungeon.depth) * weapon.accuracyFactor( this, target ));
	}
	
	@Override
	public float attackDelay() {
		return super.attackDelay()*weapon.delayFactor( this );
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return super.canAttack(enemy) || weapon.canReach(this, enemy.pos);
	}

	@Override
	public int 防御() {
		return super.防御() + Random.NormalIntRange(0, Dungeon.depth + weapon.defenseFactor(this));
	}
	
	@Override
	public boolean add(Buff buff) {
		if (super.add(buff)) {
			if (state == PASSIVE && buff.type == Buff.buffType.NEGATIVE) {
				state = HUNTING;
			}
			return true;
		}
		return false;
	}

	@Override
	public void 受伤时(int dmg, Object src ) {

		if (state == PASSIVE) {
			state = HUNTING;
		}
		
		super.受伤时( dmg, src );
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		damage = weapon.攻击时( this, enemy, damage );
		if (!enemy.isAlive() && enemy == Dungeon.hero){
			Dungeon.fail(this);
			GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
		}
		return damage;
	}
	
	@Override
	public void beckon( int cell ) {
		if (state != PASSIVE){
			super.beckon(cell);
		}
	}
	
	@Override
	public void 死亡时(Object cause ) {
		weapon.鉴定(false);
		Dungeon.level.drop( weapon, pos ).sprite.drop();
		super.死亡时( cause );
	}

	@Override
	public Notes.Landmark landmark() {
		return levelGenStatue ? Notes.Landmark.STATUE : null;
	}

	@Override
	public void destroy() {
		if (landmark() != null) {
			Notes.remove( landmark() );
		}
		super.destroy();
	}

	@Override
	public float spawningWeight() {
		return 0f;
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	public String description() {
		String desc = Messages.get(this, "desc");
		if (weapon != null){
			desc += "\n\n" + Messages.get(this, "desc_weapon", weapon.name());
		}
		return desc;
	}
	
	{
		resistances.add(Grim.class);
	}

	public static Statue random(){
		return random( true );
	}

	public static Statue random( boolean useDecks ){
		Statue statue;
		float altChance = 1/10f * RatSkull.exoticChanceMultiplier();
		if (altChance > 0.1f) altChance = (altChance+0.1f)/2f; //rat skull is 1/2 as effective here
		if (Random.Float() < altChance){
			statue = new ArmoredStatue();
		} else {
			statue = new Statue();
		}
		statue.createWeapon(useDecks);
		return statue;
	}
	
}
