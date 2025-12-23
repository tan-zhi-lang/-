

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.ClericSpell;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.忍术;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.SheepSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 超级魔法绵羊 extends Mob {

	{
		spriteClass = SheepSprite.class;
		
		生命 = 最大生命 = Dungeon.层数(5);
		
		state = WANDERING;
		loot = Random.oneOf(Generator.randomArtifact(),
							Generator.randomWand(),Generator.randomRing());
	}
	private static final String[] LINE_KEYS = {"Baa!", "Baa?", "Baa.", "Baa...", "Baba"};

	@Override
	public Char chooseEnemy() {
		return null;
	}
	
	@Override
	public void 受伤时(int dmg, Object src ) {
		
		String s = Random.element(LINE_KEYS);
		sprite.showStatus(CharSprite.NEUTRAL,Messages.get(this,s));
		
		Badges.解锁兽灵();
		if(s.equals("Baba")){
			Dungeon.hero.经验(Dungeon.层数(0.5f));
		}
		Sample.INSTANCE.play(Assets.Sounds.SHEEP, 1, Random.Float(0.91f, 1.1f));
		
		Char dmgSource = null;
		if (src instanceof Char) dmgSource = (Char)src;
		if (src instanceof Wand||src instanceof ClericSpell||src instanceof 巫术||src instanceof 道术||src instanceof 忍术) dmgSource = Dungeon.hero;
		
		if (dmgSource == null || !Dungeon.level.adjacent(pos, dmgSource.pos)){
			dmg = Math.round(dmg/2f); //halve damage taken if we are going to teleport
		}
		super.受伤时(dmg, src);
		
		if (isAlive() && !(src instanceof Corruption)) {
			if (dmgSource != null) {
				if (!Dungeon.level.adjacent(pos, dmgSource.pos)) {
					ArrayList<Integer> candidates = new ArrayList<>();
					for (int i : PathFinder.NEIGHBOURS9) {
						PathFinder.buildDistanceMap(pos,BArray.or(Dungeon.level.passable,Dungeon.level.avoid,null));
						if (PathFinder.distance[pos] == Integer.MAX_VALUE
							|| (!Dungeon.level.passable[dmgSource.pos + i]&&!Dungeon.level.pit[dmgSource.pos+i] && !Dungeon.level.avoid[dmgSource.pos + i]&&Actor.findChar(dmgSource.pos+i)==null)) {
							candidates.add(dmgSource.pos + i);
						}
					}
					if (!candidates.isEmpty()) {
						ScrollOfTeleportation.appear(this,Random.element(candidates));
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
	private boolean teleportAway(){
		
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
			if (Dungeon.level.heroFOV[pos]) GLog.i(Messages.get(this,"teleport_away"));
			ScrollOfTeleportation.appear(this, Random.element(outFOVCandidates));
			return true;
		} else if (!inFOVCandidates.isEmpty()){
			ScrollOfTeleportation.appear(this, Random.element(inFOVCandidates));
			return true;
		}
		
		return false;
		
	}
}
