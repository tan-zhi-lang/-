

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长矛;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.GnollGuardSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class GnollGuard extends Mob {

	{
		spriteClass = GnollGuardSprite.class;

		生命 = 最大生命 = 35;
		defenseSkill = 15;

		经验 = 7;
		最大等级 = -2;

		loot = 长矛.class;
		lootChance = 0.1f;

		WANDERING = new Wandering();
	}

	private int sapperID = -1;

	public void linkSapper( GnollSapper sapper){
		this.sapperID = sapper.id();
		if (sprite instanceof GnollGuardSprite){
			((GnollGuardSprite) sprite).setupArmor();
		}
	}

	public boolean hasSapper(){
		return sapperID != -1
				&& Actor.findById(sapperID) instanceof GnollSapper
				&& ((GnollSapper)Actor.findById(sapperID)).isAlive();
	}

	public void loseSapper(){
		if (sapperID != -1){
			sapperID = -1;
			if (sprite instanceof GnollGuardSprite){
				((GnollGuardSprite) sprite).loseArmor();
			}
		}
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		if (hasSapper()) dmg /= 4;
		super.受伤时(dmg, src);
	}

	@Override
	public int 最小攻击() {
		if (enemy != null && !Dungeon.level.adjacent(pos, enemy.pos)){
			return 16;
		} else {
			return 6;
		}
	}
	@Override
	public int 最大攻击() {
		if (enemy != null && !Dungeon.level.adjacent(pos, enemy.pos)){
			return 22;
		} else {
			return 12;
		}
	}

	@Override
	public int 攻击时(Char enemy, int damage) {
		int dmg = super.攻击时(enemy, damage);
		if (enemy == Dungeon.hero && !Dungeon.level.adjacent(pos, enemy.pos) && dmg > 12){
			GLog.n(Messages.get(this, "spear_warn"));
		}
		return dmg;
	}

	@Override
	public int 最大命中(Char target ) {
		return 20;
	}

	@Override
	public int 最大防御() {
		return super.最大防御()+6;
	}

	@Override
	protected boolean canAttack( Char enemy ) {
		//cannot 'curve' spear hits like the hero, requires fairly open space to hit at a distance
		return Dungeon.level.distance(enemy.pos, pos) <= 2
				&& new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == enemy.pos
				&& new Ballistica( enemy.pos, pos, Ballistica.PROJECTILE).collisionPos == pos;
	}

	@Override
	public String description() {
		if (hasSapper()){
			return super.description() + "\n\n" + Messages.get(this, "desc_armor");
		} else {
			return super.description();
		}
	}

	private static final String SAPPER_ID = "sapper_id";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(SAPPER_ID, sapperID);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		sapperID = bundle.getInt(SAPPER_ID);
	}

	public class Wandering extends Mob.Wandering {
		@Override
		protected int randomDestination() {
			if (hasSapper()){
				return ((GnollSapper)Actor.findById(sapperID)).pos;
			} else {
				return super.randomDestination();
			}
		}
	}

}
