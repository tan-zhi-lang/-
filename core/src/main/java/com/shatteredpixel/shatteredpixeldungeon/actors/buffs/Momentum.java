

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Momentum extends Buff implements ActionIndicator.Action {
	
	{
		type = buffType.POSITIVE;

		//acts before the hero
		actPriority = HERO_PRIO+1;
	}
	
	private int momentumStacks = 0;
	private int freerunTurns = 0;

	private boolean movedLastTurn = true;

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {

		if (!freerunning() && target.invisible > 0 ){
			momentumStacks = Math.min(momentumStacks + 2, 10);
			movedLastTurn = true;
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}

		if (freerunTurns > 0){
			if (target.invisible == 0) {
				freerunTurns--;
			}
		} else if (!movedLastTurn){
			momentumStacks -=2;
			if (momentumStacks <= 0) {
				ActionIndicator.clearAction(this);
				BuffIndicator.refreshHero();
			} else {
				ActionIndicator.refresh();
			}
		}
		movedLastTurn = false;

		spend(TICK);
		return true;
	}
	
	public void gainStack(){
		movedLastTurn = true;
		if (!freerunning()){
			postpone(target.cooldown()+(1/target.移速()));
			momentumStacks = Math.min(momentumStacks + 1, 10);
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}
	}

	public boolean freerunning(){
		return freerunTurns > 0;
	}
	
	public float speedMultiplier(){
		float x=Dungeon.hero.天赋点数(Talent.SPEEDY_STEALTH,0.15f);
		if (freerunning()){
			return 1.5f+x;
		} else if (target.invisible > 0 ){
			return 2+x;
		} else {
			return 1;
		}
	}
	
	public float evasionBonus(){
		if (freerunTurns > 0) {
			return 1+Dungeon.hero.天赋点数(Talent.EVASIVE_ARMOR,0.3f);
		} else {
			return 1;
		}
	}
	
	@Override
	public int icon() {
		if (momentumStacks > 0 )  return BuffIndicator.MOMENTUM;
		else                                            return BuffIndicator.NONE;
	}
	
	@Override
	public void tintIcon(Image icon) {
		if (freerunTurns > 0){
			icon.hardlight(1,1,0);
		} else {
			icon.hardlight(0.5f,0.5f,1);
		}
	}

	@Override
	public float iconFadePercent() {
		if (freerunTurns > 0){
			return (20 - freerunTurns) / 20f;
		}else {
			return 0;
		}
	}

	@Override
	public String iconTextDisplay() {
		if (freerunTurns > 0){
			return Integer.toString(freerunTurns);
		}  else {
			return "";
		}
	}

	@Override
	public String name() {
		if (freerunTurns > 0){
			return Messages.get(this, "running");
		}else {
			return Messages.get(this, "momentum");
		}
	}
	
	@Override
	public String desc() {
		if (freerunTurns > 0){
			return Messages.get(this, "running_desc", freerunTurns);
		} else {
			return Messages.get(this, "momentum_desc", momentumStacks);
		}
	}
	
	private static final String STACKS =        "stacks";
	private static final String FREERUN_TURNS = "freerun_turns";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STACKS, momentumStacks);
		bundle.put(FREERUN_TURNS, freerunTurns);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		momentumStacks = bundle.getInt(STACKS);
		freerunTurns = bundle.getInt(FREERUN_TURNS);
		if (momentumStacks > 0 && freerunTurns <= 0){
			ActionIndicator.setAction(this);
		}
		movedLastTurn = false;
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.MOMENTUM;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text(Integer.toString((int)momentumStacks) );
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {
		return 0x444444;
	}

	@Override
	public void doAction() {
		freerunTurns = 2*momentumStacks;
		//cooldown is functionally 10+2*stacks when active effect ends
		Sample.INSTANCE.play(Assets.Sounds.MISS, 1f, 0.8f);
		target.sprite.emitter().burst(Speck.factory(Speck.JET), 5+ momentumStacks);
		SpellSprite.show(target, SpellSprite.HASTE, 1, 1, 0);
		momentumStacks = 0;
		BuffIndicator.refreshHero();
		ActionIndicator.clearAction(this);
	}

}
