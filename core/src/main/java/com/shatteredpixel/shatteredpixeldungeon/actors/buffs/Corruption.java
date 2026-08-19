

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.臃肿;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

public class Corruption extends AllyBuff {

	{
		type = buffType.NEGATIVE;
		announced = true;
	}

	//corrupted enemies are usually fully healed and cleansed of most debuffs
	public static void corruptionHeal(Char target){
		target.回满血();
		
		for (Buff buff : target.buffs()) {
			if (buff.type == Buff.buffType.NEGATIVE
					&& !(buff instanceof 灵魂标记)) {
				buff.detach();
			}
		}
	}
	
	@Override
	public boolean act() {
		float x=0;
		boolean 扣血=true;
		if(Dungeon.hero()&&Dungeon.hero.glyphLevel(臃肿.class)>=0){
			扣血=false;
			x+=0.01f*Armor.Glyph.genericProcChanceMultiplier(Dungeon.hero)
			   *Dungeon.hero.glyphLevel(臃肿.class);
		}
		if(Dungeon.符文("破败之王")){
			扣血=false;
			x+=0.05f;
		}

			if(!扣血)
				target.回百分比血(x);

			if(扣血)
			target.受伤时(target.最大生命(0.01f), this);

		spend(TICK);

		return true;
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add(CharSprite.State.DARKENED);
		else if (target.invisible == 0) target.sprite.remove( CharSprite.State.DARKENED );
	}

	@Override
	public int icon() {
		return BuffIndicator.CORRUPT;
	}

	public float 综合属性 = 1;
	private static final String 综合属性x = "综合属性";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(综合属性x, 综合属性);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		综合属性 = bundle.getFloat(综合属性x);
	}

}
