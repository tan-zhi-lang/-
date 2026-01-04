

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 时间能力 extends Buff implements ActionIndicator.Action {
	
	{
		type = buffType.POSITIVE;

		//acts before the hero
		actPriority = HERO_PRIO+1;
	}
	
	public int 时间能力= 0;

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public boolean act() {

		if (target.nobuff(时光沙漏.timeFreeze.class) && target.invisible > 0 ){
			时间能力= Math.min(时间能力+2,10);
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}
		spend(TICK);
		return true;
	}
	
	public void gainStack(){
		if (target.nobuff(时光沙漏.timeFreeze.class)){
			postpone(target.cooldown()+(1/target.移速()));
			时间能力= Math.min(时间能力+1,10);
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}
	}

	
	public float 综合属性(){
		if (target.hasbuff(时光沙漏.timeFreeze.class)){
			return 1+Dungeon.hero.天赋点数(Talent.关键时刻,0.075f);
		}  else {
			return 1;
		}
	}
	
	@Override
	public int icon() {
		return BuffIndicator.TIME;
	}
	
	@Override
	public String desc() {
		return Messages.get(this,"desc",时间能力);
	}
	
	private static final String 时间能力x =        "时间能力";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(时间能力x,时间能力);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		时间能力= bundle.getInt(时间能力x);
		if (时间能力>0){
			ActionIndicator.setAction(this);
		}
	}

	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.时间刺客;
	}
	
	@Override
	public Visual secondaryVisual() {
		BitmapText txt = new BitmapText(PixelScene.pixelFont);
		txt.text(Integer.toString((int)时间能力));
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
		if(target instanceof Hero hero){
			
			Buff.施加(target,时光沙漏.timeFreeze.class).processTime(时间能力*(1+(hero.职业精通()?0.25f:0)+hero.天赋点数(Talent.时间控制,0.25f)));
		}
		
		//cooldown is functionally 10+2*stacks when active effect ends
		Sample.INSTANCE.play(Assets.Sounds.MISS, 1f, 0.8f);
		target.sprite.emitter().burst(Speck.factory(Speck.JET),5+时间能力);
		SpellSprite.show(target, SpellSprite.HASTE, 1, 1, 0);
		时间能力= 0;
		BuffIndicator.refreshHero();
		ActionIndicator.clearAction(this);
	}

}
