

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.utils.Bundle;

public class 怒气 extends Buff implements ActionIndicator.Action {

	{
		type = buffType.POSITIVE;
	}

	public float 怒气= 0;

	private static final String 怒气x = "怒气";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(怒气x,怒气);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		怒气= bundle.getFloat(怒气x);
		if (怒气>0){
			ActionIndicator.setAction(this);
		}
	}
	@Override
	public boolean act() {
		float x=1;
		if(target instanceof Hero hero){
			x=1-hero.天赋点数(Talent.血气旺盛,0.2f);
		}
		怒气=Math.max(0,怒气-x);

		if (怒气<=0){
			ActionIndicator.clearAction(this);
		}

		if (怒气<=0) {
			detach();
		}
		
		spend(TICK);
		return true;
	}
	
	@Override
	public boolean attachTo(Char target) {
		ActionIndicator.setAction(this);
		return super.attachTo(target);
	}
	
	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	public void damage(){
		怒气= Math.min(100,怒气+10);
		if (怒气>0){
			ActionIndicator.setAction(this);
		}
		BuffIndicator.refreshHero(); //show new power immediately
	}


	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.BERSERK;
	}

	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text(""+Math.round(怒气));
		txt.hardlight(CharSprite.增强);
		txt.measure();
		return txt;
	}

	@Override
	public int indicatorColor() {
		return 0x660000;
	}

	@Override
	public void doAction() {
		if(target instanceof Hero hero){
			hero.回血(怒气*(0.1f+hero.天赋点数(Talent.嗜血成性,0.1f)));
		}
		怒气=0;
		ActionIndicator.clearAction(this);
	}

	@Override
	public int icon() {
		return BuffIndicator.BERSERK;
	}
	
	@Override
	public void tintIcon(Image icon) {
//		if (怒气>=75){
//			icon.hardlight(1f,0.5f,0f);
//		}else if(怒气>50){
			icon.hardlight(1f, 0f, 0f);
//		}else{
//			icon.hardlight(0, 0, 1f);
//		}
	}
	
	@Override
	public float iconFadePercent() {
		return 怒气/100;
	}

	public String iconTextDisplay(){
		return ""+Math.round(怒气);
	}


	@Override
	public String desc(){
		return Messages.get(this,"desc",Math.round(怒气));
	}
}
