

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手里剑;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.ConeAOE;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MissileSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;

import java.util.HashSet;

public class 风刃 extends 目标忍术 {

	public static final 风刃 INSTANCE = new 风刃();

	@Override
	public int icon() {
		return HeroIcon.风刃;
	}
	
	@Override
	protected void onTargetSelected(叛忍护额 tome,Hero hero,Integer target){
		
		if (target == null){
			return;
		}
		
		if (Actor.findChar(target) == hero){
			GLog.w(Messages.get(this, "self_target"));
			return;
		}
		
		Ballistica b = new Ballistica(hero.pos, target, Ballistica.WONT_STOP);
		final HashSet<Char> targets = new HashSet<>();
		
		Char enemy = findChar(b,hero, 0/*穿墙*/,targets);
		
		if (enemy == null || !hero.fieldOfView[enemy.pos]){
			GLog.w(Messages.get(this, "no_target"));
			return;
		}
		
		targets.add(enemy);
		
			ConeAOE cone = new ConeAOE(b,60);
			for (Ballistica ray : cone.rays){
				Char toAdd = findChar(ray, hero, 0/*穿墙*/, targets);
				if (toAdd != null && hero.fieldOfView[toAdd.pos]){
					targets.add(toAdd);
				}
			}
			while (targets.size() > 1+3){
				Char furthest = null;
				for (Char ch : targets){
					if (furthest == null){
						furthest = ch;
					} else if (Dungeon.level.trueDistance(enemy.pos, ch.pos) >
							   Dungeon.level.trueDistance(enemy.pos, furthest.pos)){
						furthest = ch;
					}
				}
				targets.remove(furthest);
			}
		Item.updateQuickslot();
		
		Item proto = new 手里剑();
		
		final HashSet<Callback> callbacks = new HashSet<>();
		
		for (Char ch : targets) {
			Callback callback = new Callback() {
				@Override
				public void call() {
					float dmgMulti = ch == enemy ? 1f : 0.5f;
					hero.attack( ch, dmgMulti, 0, 1 );
					callbacks.remove( this );
					if (callbacks.isEmpty()) {
						Invisibility.notimedispel();
						hero.spendAndNext( hero.攻击延迟());
					}
				}
			};
			
			MissileSprite
					m = ((MissileSprite)hero.sprite.parent.recycle(MissileSprite.class));
			m.reset( hero.sprite, ch.pos, proto, callback );
			m.hardlight(0.6f, 1f, 1f);
			m.alpha(0.8f);
			
			callbacks.add( callback );
		}
		
		onSpellCast(tome, hero);
		hero.sprite.zap( enemy.pos );
		hero.busy();
	}
	
	public static Char findChar(Ballistica path, Hero hero, int wallPenetration, HashSet<Char> existingTargets){
		for (int cell : path.path){
			Char ch = Actor.findChar(cell);
			if (ch != null){
				if (ch == hero || existingTargets.contains(ch) || ch.alignment == Char.Alignment.ALLY){
					continue;
				} else {
					return ch;
				}
			}
			if (Dungeon.level.solid[cell]){
				wallPenetration--;
				if (wallPenetration < 0){
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public String desc(){
		String desc = Messages.get(this, "desc"
		);
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
