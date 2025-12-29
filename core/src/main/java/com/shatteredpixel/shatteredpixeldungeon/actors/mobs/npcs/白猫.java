

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.白猫保护;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.白猫动画;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 白猫 extends NPC {
	
	{
		spriteClass = 白猫动画.class;
		
		alignment = Alignment.ALLY;
		intelligentAlly = true;
		state = HUNTING;
		
		WANDERING = new Wandering();
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
		properties.add(Property.动物);
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
			Buff.施加(hero, 白猫保护.class);
			destroy();
			CellEmitter.get(pos).start(Speck.factory(Speck.LIGHT),0.2f,3);
			sprite.die();
			Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
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
			return hero.攻击延迟();
		} else {
			return super.攻击延迟();
		}
	}
	
	@Override
	public int 最小攻击() {
		if (hero != null) {
			return hero.最小攻击();
		} else {
			return 0;
		}
	}
	@Override
	public int 最大攻击() {
		if (hero != null) {
			return hero.最大攻击();
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
	public int 最小防御() {
		if (hero != null){
			return hero.最小防御();
		}else{
			return 0;
		}
	}
	@Override
	public int 最大防御() {
		if (hero != null){
			return hero.最大防御();
		}else{
			return 0;
		}
	}
	
	@Override
	public int 攻击时(Char enemy, int damage) {
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
	public int 防御时(Char enemy, int damage) {
		if (hero != null){
			return hero.防御时(enemy, damage);
		}else{
			return super.防御时(enemy, damage);
		}
	}
	
	@Override
	public void 受伤时(int dmg,Object src){
		if (hero != null){
			hero.受伤时(Math.round(dmg*(1-
										(hero.subClass(HeroSubClass.黑白双子)?0.075f:0)
										-hero.天赋点数(Talent.白猫主导,0.15f)
										-hero.天赋点数(Talent.职业精通,0.15f)
										)),src);
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
				Buff.施加(hero, 白猫保护.class);
				destroy();
				CellEmitter.get(pos).start(Speck.factory(Speck.LIGHT),0.2f,3);
				sprite.die();
				Sample.INSTANCE.play(Assets.Sounds.TELEPORT);
				return true;
			} else {
				return super.act(enemyInFOV, justAlerted);
			}
		}
		
	}
	
}
