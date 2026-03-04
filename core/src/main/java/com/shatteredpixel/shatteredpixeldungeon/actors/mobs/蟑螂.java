

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.蟑螂动画;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 蟑螂 extends Mob {
	
	{
		spriteClass = 蟑螂动画.class;
		
		生命 = 最大生命 = 3;
		defenseSkill = 25;
		经验 = 1;
		最大等级 = 5;
		baseSpeed=1.5f;

		WANDERING = new 蟑螂.Wandering();
		FLEEING = new 蟑螂.Fleeing();
		properties.add(Property.动物);
	}

	@Override
	public int 最大闪避(Char enemy){
		if(enemy!=null&&Dungeon.level.distance( enemy.pos, pos )<=2)return 0;

		return super.最大闪避(enemy);
	}

	@Override
	public float 大小(){
		return super.大小()*0.45f;
	}

	@Override
	public float 最小攻击() {
		return 1;
	}
	
	@Override
	public float 最大攻击() {
		return 2;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 8;
	}

	@Override
	public float 攻击时(Char enemy,float damage){
		if (alignment == Alignment.ENEMY&&!攻击) {
			state = FLEEING;
			攻击=true;
		}
		return super.攻击时(enemy,damage);
	}

	@Override
	public float 防御时(Char enemy,float damage){
		damage*=1-已损失生命()*0.8f;
		damage=Math.max(最大生命(0.35f),damage);
		return super.防御时(enemy,damage);
	}
	public boolean 攻击=false;
	private static final String 攻击x = "攻击";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( 攻击x, 攻击 );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		攻击 = bundle.getBoolean(攻击x);
	}
	private class Wandering extends Mob.Wandering {

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			super.act(enemyInFOV, justAlerted);

			//if an enemy is just noticed and the thief posses an item, run, don't fight.
			if (state == HUNTING && 攻击){
				state = FLEEING;
			}

			return true;
		}
	}

	private class Fleeing extends Mob.Fleeing {
		@Override
		protected void escaped() {
			if (攻击&& !Dungeon.level.heroFOV[pos]
				&& Dungeon.level.distance(Dungeon.hero.pos, pos) >= 6) {

				int count = 32;
				int newPos;
				do {
					newPos = Dungeon.level.randomRespawnCell( 蟑螂.this );
					if (count-- <= 0) {
						break;
					}
				} while (newPos == -1 || Dungeon.level.heroFOV[newPos] || Dungeon.level.distance(newPos, pos) < (count/3));

				if (newPos != -1) {

					pos = newPos;
					sprite.place( pos );
					sprite.visible = Dungeon.level.heroFOV[pos];
					if (Dungeon.level.heroFOV[pos]) CellEmitter.get(pos).burst(Speck.factory(Speck.WOOL),6);

				}


				if(Dungeon.hero.sprite!=null)Dungeon.hero.sprite.哭泣();

				攻击=false;
				state = WANDERING;
			} else {
				state = WANDERING;
			}
		}
	}
	private static int dodges = 0;

	@Override
	public String defenseVerb() {
		Sample.INSTANCE.play(Assets.Sounds.蛇叫);
		if (Dungeon.level.heroFOV[pos]) {
			dodges++;
		}
		if ((dodges >= 2 && !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_SURPRISE_ATKS))
				|| (dodges >= 4 && !Badges.isUnlocked(Badges.Badge.BOSS_SLAIN_1))){
			GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_SURPRISE_ATKS);
			dodges = 0;
		}
		return super.defenseVerb();
	}
}
