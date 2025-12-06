

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.ShieldBuff;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.BruteSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Brute extends Mob {
	
	{
		spriteClass = BruteSprite.class;
		
		生命 = 最大生命 = 40;
		defenseSkill = 15;
		
		经验 = 8;
		最大等级 = 16;
		
		loot = Gold.class;
		lootChance = 0.5f;
	}
	
	protected boolean hasRaged = false;
	
	@Override
	public int 最小攻击() {
		return buff(BruteRage.class) != null ?
				15 :
				5;
	}
	
	@Override
	public int 最大攻击() {
		return buff(BruteRage.class) != null ?
				40 :
				25;
	}
	@Override
	public int 攻击时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.狗叫);
		return super.攻击时(enemy,damage);
	}
	@Override
	public int 最大命中(Char target ) {
		return 20;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+8;
	}

	@Override
	public void 死亡时(Object cause) {
		super.死亡时(cause);

		if (cause == Chasm.class){
			hasRaged = true; //don't let enrage trigger for chasm deaths
		}
	}
	//cache this buff to prevent having to call buff(...) a bunch in isAlive
	private BruteRage rage;
	@Override
	public boolean isAlive() {
		if (super.isAlive()){
			return true;
		} else {
			if (!hasRaged){
				triggerEnrage();
			}
			if (rage == null){
				for (BruteRage b : buffs(BruteRage.class)){
					rage = b;
				}
			}
			return rage != null && rage.护盾量() > 0;
		}
	}
	
	protected void triggerEnrage(){
		rage = Buff.施加(this, BruteRage.class);
		rage.设置(最大生命/2 + 4);
		sprite.showStatusWithIcon(CharSprite.增强,Integer.toString(最大生命/2+4),FloatingText.SHIELDING);
		if (Dungeon.level.heroFOV[pos]) {
			SpellSprite.show( this, SpellSprite.BERSERK);
		}
		spend( TICK );
		hasRaged = true;
	}
	
	private static final String HAS_RAGED = "has_raged";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(HAS_RAGED, hasRaged);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		hasRaged = bundle.getBoolean(HAS_RAGED);
	}
	
	public static class BruteRage extends ShieldBuff {
		
		{
			type = buffType.POSITIVE;
		}
		
		@Override
		public boolean act() {
			
			if (target.生命 > 0){
				detach();
				return true;
			}
			
			absorbDamage( Math.round(4*AscensionChallenge.statModifier(target)));
			
			if (护盾量() <= 0){
				target.死亡时(null);
			}
			
			spend( TICK );
			
			return true;
		}
		
		@Override
		public void detach() {
			super.detach();
			减少(护盾量()); //clear shielding to track that this was detached
		}
		@Override
		public int icon () {
			return BuffIndicator.FURY;
		}
		
		@Override
		public String desc () {
			return Messages.get(this, "desc", 护盾量());
		}

	}
}
