

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
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.命中之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.闪避之戒;
import com.shatteredpixel.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.PrismaticSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class PrismaticImage extends NPC {
	
	{
		spriteClass = PrismaticSprite.class;
		
		生命 = 最大生命 = 10;
		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		intelligentAlly = true;
		state = HUNTING;
		
		WANDERING = new Wandering();
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}
	
	private Hero hero;
	private int heroID;
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
	public void 死亡时(Object cause) {
		if (deathTimer == -1) {
			if (cause == Chasm.class){
				super.死亡时( cause );
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
	
	public void duplicate( Hero hero, int HP ) {
		this.hero = hero;
		heroID = this.hero.id();
		this.生命 = HP;
		最大生命 = PrismaticGuard.maxHP( hero );
	}
	
	@Override
	public float 最大攻击() {
		if (hero != null) {
			return Random.NormalFloat( 2 + hero.等级 /4f, 4 + hero.等级 /2f );
		} else {
			return Random.NormalFloat( 2, 4 );
		}
	}
	
	@Override
	public int 最小命中(Char target ) {
		if (hero != null) {
			return 命中之戒.getBuffedBonus(hero,命中之戒.Accuracy.class)*2;
		}else{
			return 0;
		}
	}
	@Override
	public int 最大命中(Char target ) {
		if (hero != null) {
			//same base attack skill as hero, benefits from accuracy ring
			return 9 + Math.round(hero.等级*1.25f);
		} else {
			return 0;
		}
	}
	@Override
	public int 最小闪避(Char target ) {
		if (hero != null) {
			return 闪避之戒.getBuffedBonus(hero,闪避之戒.Evasion.class)*2;
		} else {
			return 0;
		}
	}
	@Override
	public int 最大闪避(Char enemy) {
		if (hero != null) {
			int baseEvasion = 4 + Math.round(hero.等级*1.25f);
			if (hero.belongings.armor() != null){
				baseEvasion = (int)hero.belongings.armor().evasionFactor(this, baseEvasion);
			}

			//if the hero has more/less evasion, 50% of it is applied
			//includes ring of evasion and armor boosts
			return baseEvasion;
		} else {
			return 0;
		}
	}
	
	@Override
	public float 最大防御() {
		float dr = super.最大防御();
		if (hero != null){
			return dr + hero.最大防御();
		} else {
			return dr;
		}
	}
	
	@Override
	public float 防御时(Char enemy, float damage) {
		if (hero != null && hero.belongings.armor() != null){
			damage = hero.belongings.armor().防御时( enemy, this, damage );
		}
		return super.防御时(enemy, damage);
	}

	@Override
	public int glyphLevel(Class<? extends Armor.Glyph> cls) {
		if (hero != null){
			return Math.max(super.glyphLevel(cls), hero.glyphLevel(cls));
		} else {
			return super.glyphLevel(cls);
		}
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
		immunities.add( ToxicGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( 燃烧.class );
		immunities.add( AllyBuff.class );
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
