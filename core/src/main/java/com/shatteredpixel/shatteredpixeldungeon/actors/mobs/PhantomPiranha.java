

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.items.food.PhantomMeat;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PhantomPiranhaSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PhantomPiranha extends Piranha {

	{
		spriteClass = PhantomPiranhaSprite.class;

		loot = PhantomMeat.class;
		lootChance = 1f;
	}

	@Override
	public void 受伤时(int dmg, Object src) {
		Char dmgSource = null;
		if (src instanceof Char) dmgSource = (Char)src;
		if (src instanceof Wand || src instanceof ClericSpell) dmgSource = Dungeon.hero;

		if (dmgSource == null || !Dungeon.level.adjacent(pos, dmgSource.pos)){
			dmg = Math.round(dmg/2f); //halve damage taken if we are going to teleport
		}
		super.受伤时(dmg, src);

		if (isAlive() && !(src instanceof Corruption)) {
			if (dmgSource != null) {
				if (!Dungeon.level.adjacent(pos, dmgSource.pos)) {
					ArrayList<Integer> candidates = new ArrayList<>();
					for (int i : PathFinder.NEIGHBOURS8) {
						if (Dungeon.level.water[dmgSource.pos + i] && Actor.findChar(dmgSource.pos + i) == null) {
							candidates.add(dmgSource.pos + i);
						}
					}
					if (!candidates.isEmpty()) {
						ScrollOfTeleportation.appear(this, Random.element(candidates));
						aggro(dmgSource);
					} else {
						teleportAway();
					}
				}
			} else {
				teleportAway();
			}
		}
	}

	@Override
	public int 防御时(Char enemy, int damage) {
		return super.防御时(enemy, damage);
	}

	@Override
	public void dieOnLand() {
		if (!teleportAway()){
			super.dieOnLand();
		}
	}

	private boolean teleportAway(){

		if (flying){
			return false;
		}

		ArrayList<Integer> inFOVCandidates = new ArrayList<>();
		ArrayList<Integer> outFOVCandidates = new ArrayList<>();
		for (int i = 0; i < Dungeon.level.length(); i++){
			if (Dungeon.level.water[i] && Actor.findChar(i) == null){
				if (Dungeon.level.heroFOV[i]){
					inFOVCandidates.add(i);
				} else {
					outFOVCandidates.add(i);
				}
			}
		}

		if (!outFOVCandidates.isEmpty()){
			if (Dungeon.level.heroFOV[pos]) GLog.i(Messages.get(this, "teleport_away"));
			ScrollOfTeleportation.appear(this, Random.element(outFOVCandidates));
			return true;
		} else if (!inFOVCandidates.isEmpty()){
			ScrollOfTeleportation.appear(this, Random.element(inFOVCandidates));
			return true;
		}

		return false;

	}
}
