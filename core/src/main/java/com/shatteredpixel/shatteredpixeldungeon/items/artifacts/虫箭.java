

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.替身保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 虫箭 extends Artifact {

	{
		image = 物品表.虫箭;
	}

	@Override
	public String desc() {
		String desc = Messages.get(this, "desc");

		return desc;
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new 保护();
	}
	@Override
	public boolean doEquip( final Hero hero) {
			if (super.doEquip( hero )){
				if(!cursed){
					hero.受伤时(hero.最大生命(0.35f),this);

					if(hero.isAlive())
					Buff.施加(hero,替身保护.class);
					if(hero.belongings.misc instanceof 虫箭)hero.belongings.misc=null;
					if(hero.belongings.misc2 instanceof 虫箭)hero.belongings.misc2=null;
					if(hero.belongings.misc3 instanceof 虫箭)hero.belongings.misc3=null;
				}
				return true;
			} else {
				return false;

			}
	}
	public class 保护 extends ArtifactBuff{

	}

}
