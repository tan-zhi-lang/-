package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class TransmogRat extends Mob {

		{
			spriteClass = RatSprite.class;

			//always false, as we derive stats from what we are transmogging from (which was already added)
			firstAdded = false;
		}

		private Mob original;
		public boolean allied;

		public void setup(Mob original) {
			this.original = original;
			original.clearTime();

			生命 = original.生命;
			最大生命 = original.最大生命;

			defenseSkill = original.defenseSkill;

			经验 = original.经验;
			最大等级 = original.最大等级;

			if(original.state==original.SLEEPING){
				state=
						SLEEPING;
			}else{
				if(original.state==original.HUNTING){
					state=
							HUNTING;
				}else{
					state=
							WANDERING;
				}
			}

		}

		public Mob getOriginal(){
			if (original != null) {
				original.生命 = 生命;
				original.pos = pos;
			}
			return original;
		}

		private float timeLeft = 6f;

		@Override
		protected boolean act() {
			if (timeLeft <= 0){
				Mob original = getOriginal();
				this.original = null;
				GameScene.add(original);

				经验 = 0;
				destroy();
				sprite.killAndErase();
				CellEmitter.get(original.pos).burst(Speck.factory(Speck.WOOL),4);
				Sample.INSTANCE.play(Assets.Sounds.PUFF);
				return true;
			} else {
				return super.act();
			}
		}

		@Override
		protected void spend(float time) {
			if (!allied) timeLeft -= time;
			super.spend(time);
		}

		public void makeAlly() {
			allied = true;
			alignment = Alignment.ALLY;
			timeLeft = Float.POSITIVE_INFINITY;
			Bestiary.setSeen(original.getClass());
			Bestiary.countEncounter(original.getClass());
		}

		public int 最大命中(Char target) {
			return original.最大命中(target);
		}

		public float 最大防御() {
			return original.最大防御();
		}

		@Override
		public float 最大攻击() {
			float damage = original.最大攻击();
			return damage;
		}

	@Override
	public float 攻击时(Char enemy,float damage){
		if (enemy instanceof Hero hero&&hero.天赋(Talent.鼠手鼠脚)){
			damage *= 1-hero.天赋点数(Talent.鼠手鼠脚,0.1f);
		}
		return super.攻击时(enemy,damage);
	}

	@Override
	public void 死亡时(Object cause){
		if(算法.概率学(Dungeon.hero.天赋点数(Talent.吱援部队,10))){
			回满血();
			makeAlly();
		}else {
			死亡时(cause);
		}
	}

	@Override
		public float 攻击延迟() {
			return original.攻击延迟();
		}

		@Override
		public void rollToDropLoot() {
			original.pos = pos;
			original.rollToDropLoot();
		}

		@Override
		public void destroy() {
			super.destroy();
			if (alignment == Alignment.ENEMY && original != null) {
				Bestiary.setSeen(original.getClass());
				Bestiary.countEncounter(original.getClass());
			}
			if (original instanceof Statue){
				Notes.remove(original.landmark());
			}
		}

		@Override
		public String name() {
			return Messages.get(this,"name",original.name());
		}

		{
			immunities.add(AllyBuff.class);
		}

		private static final String ORIGINAL = "original";
		private static final String ALLIED = "allied";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(ORIGINAL, original);
			bundle.put(ALLIED, allied);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);

			original = (Mob) bundle.get(ORIGINAL);
			defenseSkill = original.defenseSkill;
			经验 = original.经验;

			allied = bundle.getBoolean(ALLIED);
			if (allied) alignment = Alignment.ALLY;
		}
	}