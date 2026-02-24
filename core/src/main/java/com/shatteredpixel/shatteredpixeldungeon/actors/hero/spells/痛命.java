

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.灵月法杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 痛命 extends 目标巫术 {

	public static final 痛命 INSTANCE = new 痛命();

	@Override
	public int icon() {
		return HeroIcon.痛命;
	}

	@Override
	protected void onTargetSelected(灵月法杖 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target, targetingFlags());
		
		if (Actor.findChar( aim.collisionPos ) == hero){
			GLog.i(Messages.get(Wand.class,"self_target"));
			return;
		}
		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}
		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f));
		
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.SHADOW, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null) {
					float f=Random.NormalFloat(
							Dungeon.hero.魔力(0.3f)+
							hero.天赋点数(Talent.高级痛命,0.15f)*hero.最小攻击()
							,
							Dungeon.hero.魔力(1.2f)+
							hero.天赋点数(Talent.高级痛命,0.15f)*hero.最大攻击()
												 );
					ch.受伤时(f, 痛命.this);
					hero.受伤(hero.生命(0.015f));
				
				} else {
					Dungeon.level.pressCell(aim.collisionPos);
				}

				hero.spend( 1f );
				hero.next();
				
				onSpellCast(tome, hero);
			}
		});
	}

	

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc",String.format("%.2f",Dungeon.hero.力量()/2f+
									Dungeon.hero.天赋点数(Talent.高级痛命,0.15f)*Dungeon.hero.最小攻击()),
								   String.format("%.2f",Dungeon.hero.生命(0.015f)));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}

}
