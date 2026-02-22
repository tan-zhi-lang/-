

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 死血 extends 巫术 {

	public static final 死血 INSTANCE = new 死血();

	@Override
	public int icon() {
		return HeroIcon.死血;
	}

	@Override
	public void onCast(灵月法杖 tome,Hero hero){

		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC,1,Random.Float(0.87f,1.15f));


		hero.回已损失血(0.075f+hero.天赋点数(Talent.高级死血,0.05f));

		onSpellCast(tome, hero);

	}

	@Override
	public int chargeUse(Hero hero) {
		return 1;
	}

	public String desc(){
		String desc = Messages.get(this, "desc",String.format("%.2f",
															  Dungeon.hero.已损失生命(0.075f+Dungeon.hero.天赋点数(Talent.高级死血,0.05f))));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
