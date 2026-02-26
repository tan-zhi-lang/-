

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mimic;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BloodParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfTransfusion extends DamageWand {

	{
		image = 物品表.WAND_TRANSFUSION;

		collisionProperties = Ballistica.PROJECTILE;
	}

	@Override
	public float min(int level) {
		return 魔力(0.3f,0.333f);
	}

	@Override
	public float max(int level) {
		return 魔力(0.6f,0.333f);
	}

	private boolean freeCharge = false;

	@Override
	public void onZap(Ballistica beam) {

		for (int c : beam.subPath(0, beam.dist))
			CellEmitter.center(c).burst( BloodParticle.BURST );

		int cell = beam.collisionPos;

		Char ch = Actor.findChar(cell);

		if (ch instanceof Mob){
			
			wandProc(ch, chargesPerCast());
			
			//this wand does different things depending on the target.
			
			//heals/shields an ally or a charmed enemy while damaging self
			if (ch.alignment == Char.Alignment.ALLY || ch.buff(Charm.class) != null){
				
				// 5% of max hp
				float selfDmg = Math.round(curUser.最大生命 *0.05f);
				
				float healing = selfDmg + 3* 强化等级();
				float shielding = (ch.生命 + healing) - ch.最大生命;
				if (shielding > 0){
					healing -= shielding;
					Buff.施加(ch, Barrier.class).设置(shielding);
				} else {
					shielding = 0;
				}
				
				ch.回血(healing);
				
				ch.sprite.emitter().burst(Speck.factory(Speck.HEALING), 2 + 强化等级() / 2);
				
				
				if (!freeCharge) {
					damageHero(selfDmg);
				} else {
					freeCharge = false;
				}

			//for enemies...
			//(or for mimics which are hiding, special case)
			} else if (ch.alignment == Char.Alignment.ENEMY || ch instanceof Mimic) {

				//grant a self-shield, and...
				Buff.施加(curUser, Barrier.class).设置(魔力(0.5f,0.2f));
				
				
				//charms living enemies
				if (!ch.恶魔亡灵()) {
					Charm charm = Buff.施加(ch, Charm.class, Charm.DURATION/2f);
					charm.object = curUser.id();
					charm.ignoreHeroAllies = true;
					ch.sprite.centerEmitter().start( Speck.factory( Speck.HEART ), 0.2f );
				
				//harms the undead
				} else {
					ch.受伤时(damageRoll(), this);
					ch.sprite.emitter().start(ShadowParticle.UP, 0.05f);
					Sample.INSTANCE.play(Assets.Sounds.BURNING);
				}

			}
			
		}
		
	}

	//this wand costs health too
	private void damageHero(float damage){
		
		curUser.受伤时(damage, this);

		if (!curUser.isAlive()){
			Badges.validateDeathFromFriendlyMagic();
			Dungeon.fail( this );
			GLog.n( Messages.get(this, "ondeath") );
		}
	}

	@Override
	public void fx(Ballistica beam, Callback callback) {
		curUser.sprite.parent.add(
				new Beam.HealthRay(curUser.sprite.center(), DungeonTilemap.raisedTileCenterToWorld(beam.collisionPos)));
		callback.call();
	}

	@Override
	public void staffFx(法师魔杖.StaffParticle particle) {
		particle.color( 0xCC0000 );
		particle.am = 0.6f;
		particle.setLifespan(1f);
		particle.speed.polar( Random.Float(PointF.PI2), 2f );
		particle.setSize( 1f, 2f);
		particle.radiateXY(0.5f);
	}

	@Override
	public String statsDesc() {
		int selfDMG = Dungeon.hero() ? Math.round(Dungeon.hero.最大生命 *0.05f): 1;
		if (levelKnown)
			return Messages.get(this, "stats_desc", selfDMG, selfDMG + 3* 强化等级(), 魔力(0.5f,0.2f), min(), max());
		else
			return Messages.get(this, "stats_desc", selfDMG, selfDMG, 5, min(0), max(0));
	}

	@Override
	public String upgradeStat1(int level) {
		int selfDMG = Dungeon.hero() ? Math.round(Dungeon.hero.最大生命 *0.05f): 1;
		return Integer.toString(selfDMG + 3*level);
	}

	@Override
	public String upgradeStat2(int level) {
		return Integer.toString(5 + level);
	}

	@Override
	public String upgradeStat3(int level) {
		return super.upgradeStat1(level); //damage
	}

	private static final String FREECHARGE = "freecharge";

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		freeCharge = bundle.getBoolean( FREECHARGE );
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put( FREECHARGE, freeCharge );
	}

}
