

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class IncendiaryDart extends TippedDart {

	{
		image = 物品表.INCENDIARY_DART;
	}
	
	@Override
	protected void onThrow( int cell ) {
		Char enemy = Actor.findChar( cell );
		if ((enemy == null || enemy == curUser) && Dungeon.level.flamable[cell]) {
			GameScene.add(Blob.seed(cell, 4, Fire.class));
			
		Dungeon.level.drop(new 飞镖(),cell).sprite().drop();
		
		} else{
			super.onThrow(cell);
		}
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage ) {
		//when processing charged shot, only burn enemies
		if (!processingChargedShot || attacker.alignment != defender.alignment) {
			Buff.施加(defender, 燃烧.class).reignite(defender);
		}
		return super.攻击时( attacker, defender, damage );
	}
	
}
