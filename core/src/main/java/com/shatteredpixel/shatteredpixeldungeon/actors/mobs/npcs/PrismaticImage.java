

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PrismaticGuard;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PrismaticSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class PrismaticImage extends NPC {
	
	{
		spriteClass = PrismaticSprite.class;
		
		生命 = 最大生命 = 10;
		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		intelligentAlly = true;
		state = HUNTING;
		
		WANDERING = new Wandering();
		跟随英雄=true;
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}

	public int armTier;
	
	private int deathTimer = -1;
	
	@Override
	protected boolean act() {
		
		if (!isAlive()){
			deathTimer--;
			
			if (deathTimer > 0) {
				sprite.alpha((deathTimer + 3) / 8f);
				spend(TICK);
			} else {
				destroy();
				sprite.die();
			}
			return true;
		}
		
		if (deathTimer != -1){
			if (paralysed == 0) sprite.remove(CharSprite.State.PARALYSED);
			deathTimer = -1;
			sprite.resetColor();
		}
		
		if(!Dungeon.level.heroFOV[pos]){
			Buff.施加(hero, PrismaticGuard.class).set( PrismaticImage.this );
			destroy();
			CellEmitter.get(pos).start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
			sprite.die();
			Sample.INSTANCE.play( Assets.Sounds.TELEPORT );
			return true;
		}
		if ( hero == null ){
			hero = (Hero) Actor.findById(heroID);
			if ( hero == null ){
				destroy();
				sprite.die();
				return true;
			}
		}
		
		if (hero.tier() != armTier){
			armTier = hero.tier();
			((PrismaticSprite)sprite).updateArmor( armTier );
		}
		
		return super.act();
	}
	
	@Override
	public void 死亡时(Object 来源) {
		if (deathTimer == -1) {
			if (来源==Chasm.class){
				super.死亡时(来源);
			} else {
				deathTimer = 5;
				sprite.add(CharSprite.State.PARALYSED);
			}
		}
	}

	@Override
	public boolean isActive() {
		return isAlive() || deathTimer > 0;
	}

	private static final String HEROID	= "hero_id";
	private static final String TIMER	= "timer";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( HEROID, heroID );
		bundle.put( TIMER, deathTimer );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		heroID = bundle.getInt( HEROID );
		deathTimer = bundle.getInt( TIMER );
	}
	
	public void duplicate( Hero hero) {
		this.hero = hero;
		heroID = this.hero.id();
		this.生命 = 最大生命 = hero.最大生命(0.1f);
	}

	@Override
	public float 攻击时(final Char enemy, float damage ) {
		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		
		return super.攻击时( enemy, damage );
	}
	
	@Override
	public CharSprite sprite() {
		CharSprite s = super.sprite();
		
		hero = (Hero)Actor.findById(heroID);
		if (hero != null) {
			armTier = hero.tier();
		} else {
			armTier = 1;
		}
		((PrismaticSprite)s).updateArmor( armTier );
		return s;
	}
	
	{
		免疫表.add(ToxicGas.class);
		免疫表.add(CorrosiveGas.class);
		免疫表.add(燃烧.class);
		免疫表.add(AllyBuff.class);
	}
	
	private class Wandering extends Mob.Wandering{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			if (!enemyInFOV){
				Buff.施加(hero, PrismaticGuard.class).set( PrismaticImage.this );
				destroy();
				CellEmitter.get(pos).start( Speck.factory(Speck.LIGHT), 0.2f, 3 );
				sprite.die();
				Sample.INSTANCE.play( Assets.Sounds.TELEPORT );
				return true;
			} else {
				return super.act(enemyInFOV, justAlerted);
			}
		}
		
	}
	
}
