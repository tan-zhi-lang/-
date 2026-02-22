

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 圣光 extends TargetedClericSpell {

	public static final 圣光 INSTANCE = new 圣光();

	@Override
	public int icon() {
		return HeroIcon.圣光;
	}

	@Override
	protected void onTargetSelected(神圣法典 tome, Hero hero, Integer target) {
		if (target == null){
			return;
		}

		Ballistica aim = new Ballistica(hero.pos, target, targetingFlags());

		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}

		hero.busy();
		Sample.INSTANCE.play( Assets.Sounds.ZAP );
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent, MagicMissile.LIGHT_MISSILE, hero.sprite, aim.collisionPos, new Callback() {
			@Override
			public void call() {

				Char ch = Actor.findChar( aim.collisionPos );
				if (ch != null) {
					if(ch.恶魔亡灵()){
						ch.受伤时(hero.光照范围()*4+hero.天赋点数(Talent.神圣之触,hero.光照范围()*1.5f),圣光.this);
						Sample.INSTANCE.play(Assets.Sounds.HIT_MAGIC, 1, Random.Float(0.87f, 1.15f));
						ch.sprite.burst(0xFFFFFF44, 3);
					}else{
						ch.回血(hero.光照范围()*2+hero.天赋点数(Talent.神圣之触,hero.光照范围()*0.75f));
					}
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
	public int chargeUse(Hero hero) {
		return 1;
	}

	public String desc(){
		String desc = Messages.get(this, "desc",Dungeon.hero.光照范围()*2+Dungeon.hero.天赋点数(Talent.神圣之触,Dungeon.hero.光照范围()*0.75f),
										Dungeon.hero.光照范围()*4+Dungeon.hero.天赋点数(Talent.神圣之触,Dungeon.hero.光照范围()*1.5f));
		return desc + "\n\n" + Messages.get(this, "charge_cost", (int)chargeUse(Dungeon.hero));
	}
}
