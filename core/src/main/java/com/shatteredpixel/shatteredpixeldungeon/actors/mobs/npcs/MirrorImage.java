

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MirrorSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class MirrorImage extends NPC {
	
	{
		spriteClass = MirrorSprite.class;

		defenseSkill = 1;
		
		alignment = Alignment.ALLY;
		state = HUNTING;
		跟随英雄=true;
		跟随强度=0.5f;
		//before other mobs
		actPriority = MOB_PRIO + 1;
	}

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

	public void duplicate( Hero hero ) {
		this.hero = hero;
		生命 = 最大生命 = Dungeon.hero.最大生命(0.05f);
		heroID = this.hero.id();
		Buff.施加(this, MirrorInvis.class, Short.MAX_VALUE);
	}


	@Override
	public float 攻击时(final Char enemy, float damage ) {
		damage = super.攻击时( enemy, damage );

		MirrorImage.MirrorInvis buff = buff(MirrorImage.MirrorInvis.class);
		if (buff != null){
			buff.detach();
		}

		if (enemy instanceof Mob) {
			((Mob)enemy).aggro( this );
		}
		if (!enemy.isAlive() && enemy == Dungeon.hero){
			Dungeon.fail(this);
			GLog.红(Messages.capitalize(Messages.get(Char.class,"kill",name())));
		}
		return damage;
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
		免疫表.add(ToxicGas.class);
		免疫表.add(CorrosiveGas.class);
		免疫表.add(燃烧.class);
		免疫表.add(AllyBuff.class);
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