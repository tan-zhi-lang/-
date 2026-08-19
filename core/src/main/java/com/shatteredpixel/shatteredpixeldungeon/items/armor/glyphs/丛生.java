package com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.enchantments.Blooming;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class 丛生 extends Armor.Glyph {

		@Override
		public float proc(Armor armor,Char attacker,Char defender,float damage) {

			if(defender!=null){
				float procChance=1/10f*procChanceMultiplier(defender)*defender.glyphLevel(丛生.class);
				if(Random.Float()<procChance){
					CellEmitter.get(defender.pos).burst(LeafParticle.LEVEL_SPECIFIC,10);


					if(Random.Float()<procChance){
						if(procChance>8){
							for(int n: PathFinder.范围8){
								Blooming.plantGrass(attacker.pos+n);
							}
						}else{
							if(procChance>7){
								for(int n: PathFinder.范围7){
									Blooming.plantGrass(attacker.pos+n);
								}
							}else{
								if(procChance>6){
									for(int n: PathFinder.范围6){
										Blooming.plantGrass(attacker.pos+n);
									}
								}else{
									if(procChance>5){
										for(int n: PathFinder.范围5){
											Blooming.plantGrass(attacker.pos+n);
										}
									}else{
										if(procChance>4){
											for(int n: PathFinder.范围4){
												Blooming.plantGrass(attacker.pos+n);
											}
										}else{
											if(procChance>3){
												for(int n: PathFinder.范围3){
													Blooming.plantGrass(attacker.pos+n);
												}
											}else{
												if(procChance>2){
													for(int n: PathFinder.范围2){
														Blooming.plantGrass(attacker.pos+n);
													}
												}else{
													for(int n: PathFinder.相邻){
														Blooming.plantGrass(attacker.pos+n);
													}
												}
											}
										}
									}
								}
							}
						}

					}


				}
			}
			return damage;
		}

	}