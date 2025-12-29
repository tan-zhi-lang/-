

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Degrade;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hex;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredpixel.shatteredpixeldungeon.items.food.噩梦粮食;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.武力之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.装甲之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.仇鬼动画;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 仇鬼 extends Mob implements Callback {
	
	private static final float TIME_TO_ZAP	= 1f;
	
	{
		spriteClass = 仇鬼动画.class;
		
		生命 = 最大生命 = Dungeon.层数(3)+Dungeon.区域(1);
		
		经验 = Dungeon.层数(0.5f);
		最大等级 = Dungeon.区域()*5;
		viewDistance=4;
		flying = true;
		loot = 噩梦粮食.class;
		lootChance = 1/8f;
		
		properties.add(Property.UNDEAD);
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public void 死亡时(Object cause){
		Sample.INSTANCE.play(Assets.Sounds.鬼叫);
		super.死亡时(cause);
	}
	
	@Override
	public int 最小攻击() {
		return Dungeon.层数(1);
	}
	@Override
	public int 最大攻击() {
		return  Dungeon.层数(1.5f)+Dungeon.区域(1.5f);
	}
	@Override
	public int 最大命中(Char target ) {
		if(target instanceof Hero hero){
		   if(hero.belongings.armor!=null
		   &&(hero.belongings.armor.强化等级()>0||hero.belongings.armor.hasGlyph()))
			return Dungeon.区域(8);
		 
			if(hero.hasbuff(装甲之戒.装甲.class))
			return Dungeon.区域(8);
		}
		return Char.INFINITE;
	}
	
	@Override
	public int 最大闪避(Char target ) {
		if(target instanceof Hero hero){
			
			if(hero.belongings.weapon!=null
		   &&(hero.belongings.weapon.强化等级()>0||hero.belongings.weapon.hasEnchant()))
			return Dungeon.层数(0.5f)+Dungeon.区域(0.5f);
			
			if(hero.hasbuff(武力之戒.BrawlersStance.class))
			return Dungeon.层数(0.5f)+Dungeon.区域(0.5f);
		}
		return Char.INFINITE;
	}
	@Override
	public int 最大防御() {
		return super.最大防御()+Dungeon.层数(0.5f);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return super.canAttack(enemy)
				|| new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}
	
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.adjacent( pos, enemy.pos )
				|| new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos != enemy.pos) {
			
			return super.doAttack( enemy );
			
		} else {
			
			if (sprite != null && (sprite.visible || enemy.sprite.visible)) {
				sprite.zap( enemy.pos );
				return false;
			} else {
				zap();
				return true;
			}
		}
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class DarkBolt{}
	
	protected void zap() {
		spend( TIME_TO_ZAP );

		Invisibility.dispel(this);
		Char enemy = this.enemy;
		if (hit( this, enemy, true )) {
			//TODO would be nice for this to work on ghost/statues too
			if (enemy == Dungeon.hero && Random.Int( 2 ) == 0) {
				Buff.延长( enemy, Degrade.class, Degrade.DURATION );
				Sample.INSTANCE.play( Assets.Sounds.DEGRADE );
			}
			
			int dmg = Random.NormalIntRange( Dungeon.区域(2), Dungeon.区域(5) );
			dmg=Math.round(dmg*Dungeon.难度攻击());
			dmg = Math.round(dmg * AscensionChallenge.statModifier(this));

			//logic for DK taking 1/2 damage from aggression stoned minions
			if ( enemy.buff(StoneOfAggression.Aggression.class) != null
					&& enemy.alignment == alignment
					&& (Char.hasProp(enemy, Property.BOSS) || Char.hasProp(enemy, Property.MINIBOSS))){
				dmg *= 0.5f;
			}

			enemy.受伤时( dmg, new DarkBolt() );
			if(算法.概率学(1/8f)){
				Buff.施加(enemy,Weakness.class,5);
				Buff.施加(enemy,Vulnerable.class,5);
				Buff.施加(enemy,Slow.class,5);
				Buff.施加(enemy,Hex.class,5);
			}
			if (enemy == Dungeon.hero && !enemy.isAlive()) {
				Badges.validateDeathFromEnemyMagic();
				Dungeon.fail( this );
				GLog.n( Messages.get(this, "bolt_kill") );
			}
		} else {
			enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
		}
	}
	
	public void onZapComplete() {
		zap();
		next();
	}
	
	@Override
	public void call() {
		next();
	}
	@Override
	public void rollToDropLoot() {
		Imp.Quest.process(this);
		super.rollToDropLoot();
	}
}
