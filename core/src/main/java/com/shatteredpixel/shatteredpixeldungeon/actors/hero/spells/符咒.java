

package com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 符咒 extends 目标道术 {

	public static final 符咒 INSTANCE = new 符咒();

	@Override
	public int icon() {
		return HeroIcon.符咒;
	}
	
	@Override
	protected void onTargetSelected(本命玉佩 tome,Hero hero,Integer target){
		
		if (target == null){
			return;
		}
		
		Ballistica aim = new Ballistica(hero.pos,target,targetingFlags());
		
		if (Actor.findChar(aim.collisionPos)==hero){
			GLog.i(Messages.get(Wand.class,"self_target"));
			return;
		}
		if (Actor.findChar(aim.collisionPos) != null) {
			QuickSlotButton.target(Actor.findChar(aim.collisionPos));
		} else {
			QuickSlotButton.target(Actor.findChar(target));
		}
		hero.busy();
		Sample.INSTANCE.play(Assets.Sounds.ZAP,1,Random.Float(0.87f,1.15f));
		
		hero.sprite.zap(target);
		MagicMissile.boltFromChar(hero.sprite.parent,MagicMissile.LIGHT_MISSILE,hero.sprite,aim.collisionPos,new Callback() {
			@Override
			public void call() {
				
				Char ch = Actor.findChar(aim.collisionPos);
				if (ch != null) {
					Cripple.延长(ch,Slow.class,4f);
					Cripple.延长(ch,Vulnerable.class,4f);
					Cripple.延长(ch,Weakness.class,4f);
					Cripple.延长(ch,Hex.class,4f);
					if(ch.恶魔亡灵())
					ch.受伤时(Random.NormalIntRange(
							5+
							hero.术提升()
							,
							10+
							hero.术提升(5)
												   ), 符咒.this);
					
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
		String desc = Messages.get(this, "desc",5+Dungeon.hero.术提升(),
								   10+Dungeon.hero.术提升(5));
		return desc + "\n\n" + Messages.get(this, "charge_cost", chargeUse(Dungeon.hero));
	}
}
