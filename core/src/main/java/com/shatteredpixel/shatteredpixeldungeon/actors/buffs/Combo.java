

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.DwarfKing;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndCombo;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class Combo extends Buff implements ActionIndicator.Action {

	{
		type = buffType.POSITIVE;
	}
	
	private int count = 0;
	private float comboTime = 0f;
	private float initialComboTime = 5f;

	@Override
	public int icon() {
		return BuffIndicator.COMBO;
	}
	
	@Override
	public void tintIcon(Image icon) {
		ComboMove move = getHighestMove();
		if (move != null){
			icon.hardlight(move.tintColor);
		} else {
			icon.resetColor();
		}
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (initialComboTime - comboTime)/ initialComboTime);
	}

	@Override
	public String iconTextDisplay() {
		return Integer.toString((int)comboTime);
	}
	
	public void hit( Char enemy ) {

		count+=Dungeon.hero.有天赋(Talent.ENHANCED_COMBO)?2:1;
		comboTime = 2+ ((Hero)target).天赋点数(Talent.CLEAVE,5);

		if (!enemy.isAlive() || (enemy.buff(Corruption.class) != null && enemy.生命 == enemy.最大生命)){
			comboTime = 5 + ((Hero)target).天赋点数(Talent.CLEAVE,10);
		}

		initialComboTime = comboTime;

		if ((getHighestMove() != null)) {

			ActionIndicator.setAction( this );
			Badges.validateMasteryCombo( count );

			GLog.p( Messages.get(this, "combo", count) );
			
		}

		BuffIndicator.refreshHero(); //refresh the buff visually on-hit

	}

	public void addTime( float time ){
		comboTime += time;
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {
		comboTime -= TICK * HoldFast.buffDecayFactor(target);
		spend(TICK);
		if (comboTime <= 0) {
			detach();
		}
		return true;
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", count, dispTurns(comboTime));
	}

	private static final String COUNT = "count";
	private static final String TIME  = "combotime";
	private static final String INITIAL_TIME  = "initialComboTime";

	private static final String CLOBBER_USED = "clobber_used";
	private static final String PARRY_USED   = "parry_used";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(COUNT, count);
		bundle.put(TIME, comboTime);
		bundle.put(INITIAL_TIME, initialComboTime);

		bundle.put(CLOBBER_USED, clobberUsed);
		bundle.put(PARRY_USED, parryUsed);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		count = bundle.getInt( COUNT );
		comboTime = bundle.getFloat( TIME );

		initialComboTime = bundle.getFloat( INITIAL_TIME );

		clobberUsed = bundle.getBoolean(CLOBBER_USED);
		parryUsed = bundle.getBoolean(PARRY_USED);

		if (getHighestMove() != null) ActionIndicator.setAction(this);
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.COMBO;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text( Integer.toString(count) );
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {
		ComboMove best = getHighestMove();
		if (best == null) {
			return 0xDFDFDF;
		} else {
			//take the tint color and darken slightly to match buff icon
			int r = (int) ((best.tintColor >> 16) * 0.875f);
			int g = (int) (((best.tintColor >> 8) & 0xFF) * 0.875f);
			int b = (int) ((best.tintColor & 0xFF) * 0.875f);
			return (r << 16) + (g << 8) + b;
		}
	}

	@Override
	public void doAction() {
		GameScene.show(new WndCombo(this));
	}

	public enum ComboMove {
		CLOBBER(2, 0x00FF00),
		SLAM   (4, 0xCCFF00),
		PARRY  (6, 0xFFFF00),
		CRUSH  (8, 0xFFCC00),
		FURY   (10, 0xFF0000);

		public int comboReq, tintColor;

		ComboMove(int comboReq, int tintColor){
			this.comboReq = comboReq;
			this.tintColor = tintColor;
		}

		public String title(){
			return Messages.get(this, name() + ".name");
		}

		public String desc(int count){
			switch (this){
				case CLOBBER: default:
					if (count >= 7&& Dungeon.hero.天赋点数(Talent.ENHANCED_COMBO) >= 2){
						return Messages.get(this, name() + ".empower_desc");
					} else {
						return Messages.get(this, name() + ".desc");
					}
				case SLAM:
					if (count >= 3&& Dungeon.hero.天赋点数(Talent.ENHANCED_COMBO) >= 4){
						return Messages.get(this, name() + ".empower_desc", count/3, count*20);
					} else {
						return Messages.get(this, name() + ".desc", count*20);
					}
				case PARRY:
					if (count >= 9&& Dungeon.hero.天赋点数(Talent.ENHANCED_COMBO) >= 3){
						return Messages.get(this, name() + ".empower_desc");
					} else {
						return Messages.get(this, name() + ".desc");
					}
				case CRUSH:
					if (count >= 3&& Dungeon.hero.天赋点数(Talent.ENHANCED_COMBO) >= 4){
						return Messages.get(this, name() + ".empower_desc", count/3, count*25);
					} else {
						return Messages.get(this,  name() + ".desc", count*25);
					}
				case FURY:
					if (count >= 3&& Dungeon.hero.天赋点数(Talent.ENHANCED_COMBO) >= 4){
						return Messages.get(this, name() + ".empower_desc", count/3);
					} else {
						return Messages.get(this,  name() + ".desc");
					}
			}
		}

	}

	private boolean clobberUsed = false;
	private boolean parryUsed = false;

	public ComboMove getHighestMove(){
		ComboMove best = null;
		for (ComboMove move : ComboMove.values()){
			if (count >= move.comboReq){
				best = move;
			}
		}
		return best;
	}

	public int getComboCount(){
		return count;
	}

	public boolean canUseMove(ComboMove move){
		if (move == ComboMove.CLOBBER && clobberUsed)   return false;
		if (move == ComboMove.PARRY && parryUsed)       return false;
		return move.comboReq <= count;
	}

	public void useMove(ComboMove move){
		if (move == ComboMove.PARRY){
			parryUsed = true;
			comboTime = 5f;
			Invisibility.notimedispel();
			Buff.施加(target, ParryTracker.class, Actor.TICK);
			((Hero)target).spendAndNext(Actor.TICK);
			Dungeon.hero.busy();
		} else {
			moveBeingUsed = move;
			GameScene.selectCell(listener);
		}
	}

	public static class ParryTracker extends FlavourBuff{
		{ actPriority = HERO_PRIO+1;}

		public boolean parried;

		@Override
		public void detach() {
			if (!parried && target.buff(Combo.class) != null) target.buff(Combo.class).detach();
			super.detach();
		}
	}

	public static class RiposteTracker extends Buff{
		{ actPriority = VFX_PRIO;}

		public Char enemy;

		@Override
		public boolean act() {
			if (target.buff(Combo.class) != null) {
				moveBeingUsed = ComboMove.PARRY;
				target.sprite.attack(enemy.pos, new Callback() {
					@Override
					public void call() {
						target.buff(Combo.class).doAttack(enemy);
						next();
					}
				});
				detach();
				return false;
			} else {
				detach();
				return true;
			}
		}
	}

	private static ComboMove moveBeingUsed;

	private void doAttack(final Char enemy) {

		AttackIndicator.target(enemy);

		boolean wasAlly = enemy.alignment == target.alignment;
		Hero hero = (Hero) target;

		float dmgMulti = 1f;
		int dmgBonus = 0;

		//variance in damage dealt
		switch (moveBeingUsed) {
			case CLOBBER:
				dmgMulti = 0;
				break;
			case SLAM:
				dmgBonus = Math.round(target.防御() * count / 5f);
				break;
			case CRUSH:
				dmgMulti = 0.25f * count;
				break;
			case FURY:
				dmgMulti = 0.6f;
				break;
		}

		int oldPos = enemy.pos;
		if (hero.attack(enemy, dmgMulti, dmgBonus, Char.INFINITE_ACCURACY)){
			//special on-hit effects
			switch (moveBeingUsed) {
				case CLOBBER:
					if (!wasAlly) hit(enemy);
					//trace a ballistica to our target (which will also extend past them
					Ballistica trajectory = new Ballistica(target.pos, enemy.pos, Ballistica.STOP_TARGET);
					//trim it to just be the part that goes past them
					trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size() - 1), Ballistica.PROJECTILE);
					//knock them back along that ballistica, ensuring they don't fall into a pit
					int dist = 2;
					if (enemy.isAlive() && count >= 7 && hero.天赋点数(Talent.ENHANCED_COMBO) >= 2) {
						dist++;
						Buff.延长(enemy, Vertigo.class, 3);
					} else if (!enemy.flying) {
						while (dist > trajectory.dist ||
								(dist > 0 && Dungeon.level.pit[trajectory.path.get(dist)])) {
							dist--;
						}
					}
					if (enemy.pos == oldPos) {
						WandOfBlastWave.throwChar(enemy, trajectory, dist, true, false, hero);
					}
					break;
				case PARRY:
					hit(enemy);
					break;
				case CRUSH:
					WandOfBlastWave.BlastWave.blast(enemy.pos);
					PathFinder.buildDistanceMap(target.pos, BArray.not(Dungeon.level.solid, null), 3);
					for (Char ch : Actor.chars()) {
						if (ch != enemy && ch.alignment == Char.Alignment.ENEMY
								&& PathFinder.distance[ch.pos] < Integer.MAX_VALUE) {
							int aoeHit = Math.round(target.攻击() * 0.25f * count);
							aoeHit /= 2;
							aoeHit -= ch.防御();
							if (ch.buff(Vulnerable.class) != null) aoeHit *= 1.33f;
							if (ch instanceof DwarfKing){
								//change damage type for DK so that crush AOE doesn't count for DK's challenge badge
								ch.受伤时(aoeHit, this);
							} else {
								ch.受伤时(aoeHit, target);
							}
							ch.sprite.bloodBurstA(target.sprite.center(), aoeHit);
							ch.sprite.flash();

						}
					}
					break;
				default:
					//nothing
					break;
			}
		}

		Invisibility.notimedispel();

		//Post-attack behaviour
		switch(moveBeingUsed){
			case CLOBBER:
				clobberUsed = true;
				if (getHighestMove() == null) ActionIndicator.clearAction(Combo.this);
				hero.spendAndNext(hero.攻速());
				break;

			case PARRY:
				//do nothing
				break;

			case FURY:
				count--;
				//fury attacks as many times as you have combo count
				if (count > 0 && enemy.isAlive() && hero.canAttack(enemy) &&
						(wasAlly || enemy.alignment != target.alignment)){
					target.sprite.attack(enemy.pos, new Callback() {
						@Override
						public void call() {
							doAttack(enemy);
						}
					});
				} else {
					detach();
					Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					ActionIndicator.clearAction(Combo.this);
					hero.spendAndNext(hero.攻速());
				}
				break;

			default:
				detach();
				ActionIndicator.clearAction(Combo.this);
				hero.spendAndNext(hero.攻速());
				break;
		}

		if (!enemy.isAlive() || (!wasAlly && enemy.alignment == target.alignment)) {

		}

	}

	private CellSelector.Listener listener = new CellSelector.Listener() {

		@Override
		public void onSelect(Integer cell) {
			if (cell == null) return;
			final Char enemy = Actor.findChar( cell );
			if (enemy == null
					|| enemy == target
					|| !Dungeon.level.heroFOV[cell]
					|| target.isCharmedBy( enemy )) {
				GLog.w(Messages.get(Combo.class, "bad_target"));

			} else if (!((Hero)target).canAttack(enemy)){
				if (((Hero) target).天赋点数(Talent.ENHANCED_COMBO) < 4
					|| Dungeon.level.distance(target.pos, enemy.pos) > 1 + target.buff(Combo.class).count/3){
					GLog.w(Messages.get(Combo.class, "bad_target"));
				} else {
					Ballistica c = new Ballistica(target.pos, enemy.pos, Ballistica.PROJECTILE);
					if (c.collisionPos == enemy.pos){
						final int leapPos = c.path.get(c.dist-1);
						if (!Dungeon.level.passable[leapPos] && !(target.flying && Dungeon.level.avoid[leapPos])){
							GLog.w(Messages.get(Combo.class, "bad_target"));
						} else if (Dungeon.hero.rooted) {
							PixelScene.shake( 1, 1f );
							GLog.w(Messages.get(Combo.class, "bad_target"));
						} else {
							Dungeon.hero.busy();
							target.sprite.jump(target.pos, leapPos, new Callback() {
								@Override
								public void call() {
									target.move(leapPos);
									Dungeon.level.occupyCell(target);
									Dungeon.observe();
									GameScene.updateFog();
									target.sprite.attack(cell, new Callback() {
										@Override
										public void call() {
											doAttack(enemy);
										}
									});
								}
							});
						}
					} else {
						GLog.w(Messages.get(Combo.class, "bad_target"));
					}
				}

			} else {
				Dungeon.hero.busy();
				target.sprite.attack(cell, new Callback() {
					@Override
					public void call() {
						doAttack(enemy);
					}
				});
			}
		}

		@Override
		public String prompt() {
			return Messages.get(Combo.class, "prompt");
		}
	};
}
