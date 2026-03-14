

package com.shatteredpixel.shatteredpixeldungeon.items.scrolls;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 复仇卷轴 extends Scroll {

	{
		icon = 物品表.Icons.SCROLL_RETRIB;
	}
	
	@Override
	public void doRead() {

		detach(curUser.belongings.backpack);
		GameScene.flash( 0x80FFFFFF );

		float power = curUser.根据已损失生命();
		
		Sample.INSTANCE.play( Assets.Sounds.BLAST );
		GLog.i(Messages.get(this, "blast"));

		ArrayList<Mob> targets = new ArrayList<>();

		//calculate targets first, in case damaging/blinding a target affects hero vision
		for (Mob mob : Dungeon.level.mobs.toArray( new Mob[0] )) {
			if (Dungeon.level.heroFOV[mob.pos]) {
				targets.add(mob);
			}
		}

		for (Mob mob : targets){
			//deals 10%HT, plus 0-90%HP based on scaling
			mob.受伤时(mob.最大生命(0.1f) + (mob.生命 * power), this);
			if (mob.isAlive()) {
				Buff.延长(mob, Blindness.class, Blindness.DURATION);
			}
		}
		
		Buff.延长(curUser, Weakness.class, Weakness.DURATION);
		Buff.延长(curUser, Blindness.class, Blindness.DURATION);
		Dungeon.observe();

		鉴定();
		
		readAnimation();
		
	}
	
	@Override
	public int 金币() {
		return isKnown() ? 40 * quantity : super.金币();
	}
}
