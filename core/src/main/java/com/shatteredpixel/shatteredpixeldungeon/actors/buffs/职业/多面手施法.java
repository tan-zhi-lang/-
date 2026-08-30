

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.职业;

import static com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand.wandProc;
import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.mage.WildMagic;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 多面手施法 extends Buff implements ActionIndicator.Action {
	
	{
		type = buffType.POSITIVE;

		//acts before the hero
		actPriority = HERO_PRIO+1;
	}
	
	public float 施法= 10;

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {
		spend(TICK);
		return true;
	}

	private static final float BASE_CHARGE_DELAY = 10f;
	private static final float SCALING_CHARGE_ADDITION = 40f;
	private static final float NORMAL_SCALE_FACTOR = 0.75f;

	float scalingFactor = NORMAL_SCALE_FACTOR;
	public void gainStack(){
		float missingCharges = 10 - 施法;
		missingCharges = Math.max(0, missingCharges);
		float turnsToCharge = (float) (BASE_CHARGE_DELAY
									   + ((SCALING_CHARGE_ADDITION) * Math.pow(scalingFactor, missingCharges)));

		if (再生.regenOn())
		施法= Math.min(施法+(1/turnsToCharge)*能量之戒.wandChargeMultiplier(target),10);

		if (施法>=1){
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}
	}
	
	
	@Override
	public int icon() {
		return BuffIndicator.WAND;
	}
	
	@Override
	public String desc() {
		return Messages.get(this,"desc"
				,kw2(Dungeon.hero.魔力(0.3f+0.15f*Dungeon.hero.等级)
					 *(1+Dungeon.hero.天赋点数(Talent.魔法攻击,0.25f)))
				,kw2(Dungeon.hero.魔力(0.9f+0.3f*Dungeon.hero.等级)
					 *(1+Dungeon.hero.天赋点数(Talent.魔法攻击,0.25f)))
						   );
	}
	
	private static final String 施法x =        "施法";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(施法x,施法);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		施法= bundle.getFloat(施法x);
		if (施法>=1){
			ActionIndicator.setAction(this);
		}
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.多面手施法;
	}
	
	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text(""+Math.round(施法));
		txt.hardlight(CharSprite.增强绿);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {
		return 0xffffff;
	}

	@Override
	public void doAction() {
	if(tryToZap(Dungeon.hero)){
		GameScene.selectCell(zapper);
		施法=Math.max(0,--施法);
		BuffIndicator.refreshHero();
		ActionIndicator.clearAction(this);
	}
	}

	public boolean tryToZap(Hero owner){

		if (owner.buff(WildMagic.WildMagicTracker.class)==null&&owner.buff(MagicImmune.class)!=null){
			GLog.橙(Messages.get(Wand.class,"no_magic"));
			return false;
		}

		//if we're using wild magic, then assume we have charges
		if ( owner.buff(WildMagic.WildMagicTracker.class) != null||施法>=1){
			return true;
		}else {
			GLog.橙(Messages.get(Wand.class,"fizzles"));
			return false;
		}
	}

	public static void onZap(Ballistica bolt) {

		Char ch = Actor.findChar(bolt.collisionPos);
		if (ch != null) {

			wandProc(ch,Dungeon.hero.等级, 1);
			ch.受伤时(Random.NormalFloat(
					Dungeon.hero.魔力(0.3f+0.15f*Dungeon.hero.等级)
					*(1+Dungeon.hero.天赋点数(Talent.魔法攻击,0.25f)),
					Dungeon.hero.魔力(0.9f+0.3f*Dungeon.hero.等级)
					*(1+Dungeon.hero.天赋点数(Talent.魔法攻击,0.25f))
										), 多面手施法.class);
			if(!ch.isAlive()){

			}
			Sample.INSTANCE.play(Assets.Sounds.攻击魔法,1,Random.Float(0.87f,1.15f));

			ch.sprite.burst(0x000000);

		} else {
			Dungeon.level.pressCell(bolt.collisionPos);
		}
	}
	public static void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar( Dungeon.hero.sprite.parent,
								   MagicMissile.MAGIC_MISSILE,
								   Dungeon.hero.sprite,
								   bolt.collisionPos,
								   callback);
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
	}
	public static void wandUsed(Hero hero) {

		hero.sprite.operate( hero.pos );
		hero.busy();

		Invisibility.notimedispel();

		hero.spendAndNext( 1 );
	}
	protected static CellSelector.Listener zapper = new  CellSelector.Listener() {

		@Override
		public void onSelect( Integer target ) {

			if (target != null&&Dungeon.hero()) {
				Hero hero=Dungeon.hero;

				final Ballistica
						shot = new Ballistica(hero.pos,target,Ballistica.MAGIC_BOLT);
				int cell = shot.collisionPos;

				if (target == hero.pos || cell == hero.pos) {
					GLog.白(Messages.get(Wand.class,"self_target"));
					return;
				}

				hero.sprite.zap(cell);

				//attempts to target the cell aimed at if something is there, otherwise targets the collision pos.
				if (Actor.findChar(target)!=null)
					QuickSlotButton.target(Actor.findChar(target));
				else
					QuickSlotButton.target(Actor.findChar(cell));

				if (true) {

					hero.busy();

					//backup barrier logic
					//This triggers before the wand zap, mostly so the barrier helps vs skeletons
					//					if (curWand.curCharges == curWand.chargesPerCast()
					//							&& curWand.charger != null && curWand.charger.target == hero){
					//
					//					}

						fx(shot, new Callback() {
							public void call() {
								onZap(shot);
								wandUsed(hero);
							}
						});

					}
				}

			}

		@Override
		public String prompt() {
			return Messages.get(Wand.class, "prompt");
		}
	};

}
