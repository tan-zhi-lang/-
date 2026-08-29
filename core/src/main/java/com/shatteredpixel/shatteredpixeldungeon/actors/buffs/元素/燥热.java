

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.TimeStasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.时光沙漏;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class 燥热 extends FlavourBuff implements Hero.Doom{

	public static final float DURATION = 10f;

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	@Override
	public boolean act(){
		float damage = Random.NormalFloat(0.5f,1.5f+Dungeon.scalingDepth()/8f);
		if (target instanceof Hero hero
			&&target.buff(时光沙漏.timeStasis.class)==null
			&&target.buff(TimeStasis.class)==null){
			hero.受伤时(damage,this);
		}
		return super.act();
	}

	@Override
	public boolean attachTo(Char target) {
		Buff.detach(target,Chill.class);

		return super.attachTo(target);
	}

	//reduces speed by 10% for every turn remaining, capping at 50%
	public float speedFactor(){
		return Math.max(0.25f, 1 - cooldown()*0.05f);
	}

	@Override
	public int icon() {
		return BuffIndicator.燥热;
	}

	@Override
	public float iconFadePercent() {
		return Math.max(0, (DURATION - visualcooldown()) / DURATION);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.燥热);
		else target.sprite.remove(CharSprite.State.燥热);
	}

	@Override
	public void onDeath() {

		Badges.validateDeathFromFire();

		Dungeon.fail( this );
		GLog.红(Messages.get(this,"ondeath"));
	}
	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns(), Messages.decimalFormat("#.##", (1f-speedFactor())*100f));
	}
}
