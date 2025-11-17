

package com.shatteredpixel.shatteredpixeldungeon.items.potions;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class 隐形药剂 extends Potion {

	{
		icon = 物品表.Icons.POTION_INVIS;
	}

	@Override
	public void apply( Hero hero ) {
		鉴定();
		Buff.延长( hero, Invisibility.class, Invisibility.DURATION );
		GLog.i( Messages.get(this, "invisible") );
		Sample.INSTANCE.play( Assets.Sounds.MELD );
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 40 * quantity : super.金币();
	}

}
