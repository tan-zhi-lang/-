

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
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
	
	private float momentumStacks = 0;
	public float freerunTurns = 0;


	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {
		freerunTurns = Math.max(freerunTurns -1, 0);

		spend(TICK);
		return true;
	}
	
	public void gainStack(){
		if (!freerunning()){
			if(target.hasbuff(Invisibility.class))
				momentumStacks = Math.min(momentumStacks + 1.5f, 9);

			momentumStacks = Math.min(momentumStacks + 1.5f, 9);
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}else {
			if(target instanceof Hero hero){
				hero.护甲(freerunTurns*Dungeon.hero.天赋点数(Talent.EVASIVE_ARMOR,0.75f));
			}
		}
	}

	public boolean freerunning(){
		return freerunTurns > 0;
	}
	
	public float speedMultiplier(){
		float x=freerunTurns*Dungeon.hero.天赋点数(Talent.SPEEDY_STEALTH,0.02f);
		if (freerunning()){
			return 2+x;
		} else if (target.invisible > 0 ){
			return 3+x;
		} else {
			return 1;
		}
	}
	
	public float evasionBonus(Hero hero){
		if (freerunTurns > 0) {
			return hero.移速()*hero.天赋点数(Talent.PROJECTILE_MOMENTUM,0.05f);
		} else {
			return hero.移速()*hero.天赋点数(Talent.PROJECTILE_MOMENTUM,0.05f);
		}
	}
	
	@Override
	public int icon() {
		if (momentumStacks > 0 )  return BuffIndicator.MOMENTUM;
		else                                            return BuffIndicator.MOMENTUM;
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
	public String iconTextDisplay() {
		if (freerunTurns > 0){
			return Float.toString(freerunTurns);
		}  else {
			return Float.toString(momentumStacks);
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
		momentumStacks = bundle.getFloat(STACKS);
		freerunTurns = bundle.getFloat(FREERUN_TURNS);
		if (momentumStacks > 0 && freerunTurns <= 0){
			ActionIndicator.setAction(this);
		}

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
		txt.text(Float.toString(momentumStacks) );
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
		if(target instanceof Hero hero&&hero.职业精通()){
			freerunTurns = momentumStacks*2;
		}else{
			freerunTurns = momentumStacks;
		}
		//cooldown is functionally 10+2*stacks when active effect ends
		Sample.INSTANCE.play(Assets.Sounds.MISS, 1f, 0.8f);
		target.sprite.emitter().burst(Speck.factory(Speck.JET), 5+ momentumStacks);
		SpellSprite.show(target, SpellSprite.HASTE, 1, 1, 0);
		momentumStacks = 0;
		BuffIndicator.refreshHero();
		ActionIndicator.clearAction(this);
	}

}
