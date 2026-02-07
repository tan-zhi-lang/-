

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.替身保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.替身动画;
import com.watabou.utils.Bundle;

public class 暗影替身 extends NPC {
	
	{
		spriteClass = 替身动画.class;
		
		alignment = Alignment.ALLY;
		intelligentAlly = true;
		state = HUNTING;
		
		WANDERING = new Wandering();
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
		properties.add(Property.INORGANIC);
	}
	
	private Hero hero;
	private int heroID;
	
	@Override
	protected boolean act() {
		
		if (!isAlive()){
			spend(TICK);
			return true;
		}
		
		if(!Dungeon.level.heroFOV[pos]){
			if ( hero==null)
				hero = (Hero) Actor.findById(heroID);

			Buff.施加(hero, 替身保护.class);
			destroy();

			sprite.die();

			return true;
		}
		
		if ( hero==null){
			hero = (Hero) Actor.findById(heroID);
			if ( hero == null||(hero!=null&&!hero.isAlive()) ){
				destroy();
				sprite.die();
				return true;
			}
		}
		
		
		return super.act();
	}
	
	@Override
	public boolean isActive() {
		return isAlive();
	}

	private static final String HEROID	= "hero_id";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( HEROID, heroID );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		heroID = bundle.getInt( HEROID );
	}
	
	public void duplicate( Hero hero) {
		this.hero = hero;
		heroID = this.hero.id();
	}
	
	@Override
	public float 移速(){
		if (hero != null) {
			return hero.移速();
		} else {
			return super.移速();
		}
	}
	
	@Override
	public float 攻击延迟(){
		if (hero != null) {
			return hero.攻击延迟()/(1+(hero.海克斯.get("力速双A替身")?0.25f:0));
		} else {
			return super.攻击延迟();
		}
	}
	
	@Override
	public float 最小攻击() {
		if (hero != null) {
			return hero.最小攻击()*(1+(hero.海克斯.get("力速双A替身")?0.25f:0));
		} else {
			return 0;
		}
	}
	@Override
	public float 最大攻击() {
		if (hero != null) {
			return hero.最大攻击()*(1+(hero.海克斯.get("力速双A替身")?0.25f:0));
		} else {
			return 0;
		}
	}
	
	@Override
	public int 最小命中(Char target ) {
		if (hero != null) {
			return hero.最小命中(target);
		}else{
			return 0;
		}
	}
	@Override
	public int 最大命中(Char target ) {
		if (hero != null) {
			return hero.最大命中(target);
		} else {
			return 0;
		}
	}
	@Override
	public int 最小闪避(Char target ) {
		if (hero != null) {
			return hero.最小闪避(target);
		} else {
			return 0;
		}
	}
	@Override
	public int 最大闪避(Char enemy) {
		if (hero != null) {
			return hero.最大闪避(enemy);
		} else {
			return 0;
		}
	}
	
	@Override
	public float 最小防御() {
		if (hero != null){
			return hero.最小防御();
		}else{
			return 0;
		}
	}
	@Override
	public float 最大防御() {
		if (hero != null){
			return hero.最大防御();
		}else{
			return 0;
		}
	}
	
	@Override
	public float 攻击时(final Char enemy, float damage) {
		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		if (hero != null){
			return hero.攻击时(enemy, damage);
		}else{
			return super.防御时(enemy, damage);
		}
	}
	@Override
	public float 防御时(Char enemy, float damage) {
		if (hero != null){
			return hero.防御时(enemy, damage);
		}else{
			return super.防御时(enemy, damage);
		}
	}
	
	@Override
	public void 受伤时(float dmg,Object src){
		if (hero != null){
			hero.受伤时(dmg,src);
		}
	}
	
	@Override
	public int glyphLevel(Class<? extends Armor.Glyph> cls) {
		if (hero != null){
			return hero.glyphLevel(cls);
		} else {
			return super.glyphLevel(cls);
		}
	}
	
	@Override
	public CharSprite sprite() {
		CharSprite s = super.sprite();
		hero = (Hero)Actor.findById(heroID);
		return s;
	}
	
	{
		immunities.add( AllyBuff.class );
	}
	
	private class Wandering extends Mob.Wandering{
		
		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			
			if (!enemyInFOV){
				if ( hero==null)
					hero = (Hero) Actor.findById(heroID);

				Buff.施加(hero,替身保护.class);
				destroy();

				sprite.die();

				return true;
			} else {
				return super.act(enemyInFOV, justAlerted);
			}
		}
		
	}
	
}
