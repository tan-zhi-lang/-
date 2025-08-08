

package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ChampionEnemy;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Daze;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.FerretTuft;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.GameMath;

public class Stone extends Armor.Glyph {

	private static ItemSprite.Glowing GREY = new ItemSprite.Glowing( 0x222222 );

	@Override
	public int proc(Armor armor, Char attacker, Char defender, int damage) {
		
		testing = true;
		float accuracy = attacker.最大命中(defender);
		float evasion = defender.defenseSkill(attacker);
		testing = false;

		//FIXME this is duplicated here because these apply in hit(), not in attack/defenseskill
		// the true solution is probably to refactor accuracy/evasion code a little bit
		if (attacker.buff(Bless.class) != null) accuracy *= 1.25f;
		if (attacker.buff(  Hex.class) != null) accuracy *= 0.8f;
		if (attacker.buff( Daze.class) != null) accuracy *= 0.5f;
		for (ChampionEnemy buff : attacker.buffs(ChampionEnemy.class)){
			accuracy *= buff.evasionAndAccuracyFactor();
		}
		accuracy *= AscensionChallenge.statModifier(attacker);
		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.BLESS)
				&& attacker.alignment == Char.Alignment.ALLY){
			// + 3%/5%
			accuracy *= 1+Dungeon.hero.天赋点数(Talent.BLESS,0.03f);
		}

		if (defender.buff(Bless.class) != null) evasion *= 1.25f;
		if (defender.buff(  Hex.class) != null) evasion *= 0.8f;
		if (defender.buff( Daze.class) != null) evasion *= 0.5f;
		for (ChampionEnemy buff : defender.buffs(ChampionEnemy.class)){
			evasion *= buff.evasionAndAccuracyFactor();
		}
		evasion *= AscensionChallenge.statModifier(defender);
		if (Dungeon.hero.heroClass != HeroClass.CLERIC
				&& Dungeon.hero.有天赋(Talent.BLESS)
				&& defender.alignment == Char.Alignment.ALLY){
			// + 3%/5%
			evasion *= 1 + Dungeon.hero.天赋点数(Talent.BLESS,0.03f);
		}
		evasion *= FerretTuft.evasionMultiplier();

		// end of copy-pasta

		evasion *= genericProcChanceMultiplier(defender);
		
		float hitChance;
		if (evasion >= accuracy){
			hitChance = (accuracy/evasion)/2f;
		} else {
			hitChance = 1f - (evasion/accuracy)/2f;
		}
		
		//75% of dodge chance is applied as damage reduction
		// we clamp in case accuracy or evasion were negative
		hitChance = GameMath.gate(0.25f, (1f + 3f*hitChance)/4f, 1f);
		
		damage = (int)Math.ceil(damage * hitChance);
		
		return damage;
	}
	
	private static boolean testing = false;
	
	public static boolean testingEvasion(){
		return testing;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return GREY;
	}

}
