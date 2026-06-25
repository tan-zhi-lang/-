

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.curses;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Annoying extends Weapon.Enchantment {

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x000000 );

	@Override
	public float proc( Weapon weapon, Char attacker, Char defender, float damage ) {
		if(性格==0)性格=Random.Int(1,5);
		float procChance = 1/10f * procChanceMultiplier(attacker);
		if(性格==1)procChance=0;
		if (Random.Float() < procChance) {
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				mob.beckon(attacker.pos);
			}
			attacker.sprite.centerEmitter().start(Speck.factory(Speck.SCREAM), 0.3f, 3);
			Sample.INSTANCE.play(Assets.Sounds.MIMIC);
			Invisibility.notimedispel();
			//~1/100 for each rare line, ~1/10 for each common line

			if(性格==1){
				GLog.绿("喧闹附魔:"+Messages.get(this,"msg_"+Random.IntRange(1,14)));
			}
			if(性格==2&&defender!=null&&defender.第x次防御==1){
				Buff.延长(defender,Vertigo.class,3);
				GLog.黄("喧闹附魔:"+Messages.get(this,"玩梗_"+Random.IntRange(1,14)));
			}
			if(性格==3){
				damage*=1.3f;
				GLog.红("喧闹附魔:"+Messages.get(this,"暴躁_"+Random.IntRange(1,14)));
			}
			if(性格==4&&defender!=null){
				attacker.回血(0.07f);
				GLog.蓝("喧闹附魔:"+Messages.get(this,"暴躁_"+Random.IntRange(1,14)));
			}

		}

		return damage;
	}
	public String desc() {
		String s=switch(性格){
			default -> "";
			case 1->"";
			case 2->"首次攻击施加3回合眩晕";
			case 3->"攻击伤害+30%";
			case 4->"恢复7%伤害的生命";
		};
		if(s.equals(""))return "";
		return Messages.get(this, "desc",s);
	}

	public int 性格=0;
	private static final String 性格x=        "性格";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(性格x,性格);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		性格= bundle.getInt(性格x);
	}
	@Override
	public boolean curse() {
		return true;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return BLACK;
	}

}