

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.BodyForm;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyWeapon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.闪避之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.命中之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MirrorSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;

public class MirrorImage extends NPC {
	
	{
		spriteClass = MirrorSprite.class;
		
		生命 = 最大生命 = 1;
		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		state = HUNTING;
		
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}
	
	private Hero hero;
	private int heroID;
	public int armTier;
	
	@Override
	protected boolean act() {
		
		if ( hero == null ){
			hero = (Hero)Actor.findById(heroID);
			if ( hero == null ){
				死亡时(null);
				sprite.killAndErase();
				return true;
			}
		}
		
		if (hero.tier() != armTier){
			armTier = hero.tier();
			((MirrorSprite)sprite).updateArmor( armTier );
		}
		
		return super.act();
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
	
	public void duplicate( Hero hero ) {
		this.hero = hero;
		heroID = this.hero.id();
		Buff.施加(this, MirrorInvis.class, Short.MAX_VALUE);
	}
	
	@Override
	public int 最小攻击() {
		int damage;
		if (hero.belongings.weapon() != null){
			damage = hero.belongings.weapon().最小攻击();
		} else {
			damage = hero.最小攻击(); //handles ring of force
		}
		return (damage+1)/2; //half hero damage, rounded up
	}
	@Override
	public int 最大攻击() {
		int damage;
		if (hero.belongings.weapon() != null){
			damage = hero.belongings.weapon().最大攻击();
		} else {
			damage = hero.最大攻击(); //handles ring of force
		}
		return (damage+1)/2; //half hero damage, rounded up
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
		//same base attack skill as hero, benefits from accuracy ring and weapon
		int attackSkill = 9 + Math.round(hero.等级*1.25f);
		if (hero.belongings.attackingWeapon() != null){
			attackSkill *= hero.belongings.attackingWeapon().accuracyFactor(this, target);
		}
		return attackSkill;
	}
	
	@Override
	public int 最小闪避(Char target ) {
		if (hero != null) {
			return 闪避之戒.getBuffedBonus(hero,闪避之戒.Evasion.class)*2;
		} else {
			return 0;
		}
	}
	public int 最大闪避(Char enemy) {
		if (hero != null) {
			return 4 + Math.round(hero.等级*1.25f);
		} else {
			return 0;
		}
	}
	
	@Override
	public float 攻击延迟() {
		return hero.攻击延迟(); //handles ring of furor
	}
	
	@Override
	protected boolean canAttack(Char enemy) {
		return super.canAttack(enemy) || (hero.belongings.weapon() != null && hero.belongings.weapon().canReach(this, enemy.pos));
	}
	
	@Override
	public int 最小防御() {
		int dr = super.最小防御();
		if (hero != null && hero.belongings.weapon() != null){
			return dr ;
		} else {
			return dr;
		}
	}
	@Override
	public int 最大防御() {
		int dr = super.最大防御();
		if (hero != null && hero.belongings.weapon() != null){
			return dr +hero.belongings.weapon().defenseFactor(this)/2;
		} else {
			return dr;
		}
	}
	
	@Override
	public int 攻击时(Char enemy, int damage ) {
		damage = super.攻击时( enemy, damage );
		
		MirrorInvis buff = buff(MirrorInvis.class);
		if (buff != null){
			buff.detach();
		}
		
		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		if (hero.belongings.weapon() != null){
			damage = hero.belongings.weapon().攻击时( this, enemy, damage );
			if (!enemy.isAlive() && enemy == Dungeon.hero){
				Dungeon.fail(this);
				GLog.n( Messages.capitalize(Messages.get(Char.class, "kill", name())) );
			}
			return damage;
		} else {
			//hero benefits from holy weapon and body form when unarmed, so do mirror images
			boolean wasEnemy = enemy.alignment == Alignment.ENEMY;
			if (hero.buff(BodyForm.BodyFormBuff.class) != null
					&& hero.buff(BodyForm.BodyFormBuff.class).enchant() != null){
				damage = hero.buff(BodyForm.BodyFormBuff.class).enchant().proc(new 短剑(),this,enemy,damage);
			}
			if (!wasEnemy || enemy.alignment == Alignment.ENEMY) {
				if (hero.buff(HolyWeapon.HolyWepBuff.class) != null) {
					int dmg = hero.subClass == HeroSubClass.PALADIN ? 6 : 2;
					enemy.受伤时(Math.round(dmg * Weapon.Enchantment.genericProcChanceMultiplier(this)), HolyWeapon.INSTANCE);
				}
			}
			return damage;
		}
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
		((MirrorSprite)s).updateArmor( armTier );
		return s;
	}
	
	{
		immunities.add( ToxicGas.class );
		immunities.add( CorrosiveGas.class );
		immunities.add( 燃烧.class );
		immunities.add( AllyBuff.class );
	}
	
	public static class MirrorInvis extends Invisibility {
		
		{
			announced = false;
		}
		
		@Override
		public int icon() {
			return BuffIndicator.NONE;
		}
	}
}