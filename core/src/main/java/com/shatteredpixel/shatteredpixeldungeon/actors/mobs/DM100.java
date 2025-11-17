

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.DM100Sprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class DM100 extends Mob implements Callback {

	private static final float TIME_TO_ZAP	= 1f;
	
	{
		spriteClass = DM100Sprite.class;
		
		生命 = 最大生命 = 20;
		defenseSkill = 8;
		
		经验 = 6;
		最大等级 = 13;
		
		loot = Generator.Category.SCROLL;
		lootChance = 0.25f;
		
		properties.add(Property.ELECTRIC);
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public int 最小攻击() {
		return 2;
	}
	
	@Override
	public int 最大攻击() {
		return 8;
	}
	
	@Override
	public int 最大命中(Char target ) {
		return 11;
	}
	
	@Override
	public int 最大防御() {
		return super.最大防御()+4;
	}
	
	@Override
	public int 防御时(Char enemy,int damage){
		Sample.INSTANCE.play(Assets.Sounds.金属受伤);
		return super.防御时(enemy,damage);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return super.canAttack(enemy)
				|| new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class LightningBolt{}
	
	@Override
	protected boolean doAttack( Char enemy ) {
		
		Badges.解锁机器();
		if (Dungeon.level.adjacent( pos, enemy.pos )
				|| new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos != enemy.pos) {
			
			return super.doAttack( enemy );
			
		} else {
			
			spend( TIME_TO_ZAP );

			Invisibility.dispel(this);
			if (hit( this, enemy, true )) {
				int dmg = Random.NormalIntRange(3, 10);
				dmg = Math.round(dmg * AscensionChallenge.statModifier(this));
				enemy.受伤时( dmg, new LightningBolt() );

				if (enemy.sprite.visible) {
					enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
					enemy.sprite.flash();
				}
				
				if (enemy == Dungeon.hero) {
					
					PixelScene.shake( 2, 0.3f );
					
					if (!enemy.isAlive()) {
						Badges.validateDeathFromEnemyMagic();
						Dungeon.fail( this );
						GLog.n( Messages.get(this, "zap_kill") );
					}
				}
			} else {
				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}
			
			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap( enemy.pos );
				return false;
			} else {
				return true;
			}
		}
	}
	
	@Override
	public void call() {
		next();
	}
	
}
