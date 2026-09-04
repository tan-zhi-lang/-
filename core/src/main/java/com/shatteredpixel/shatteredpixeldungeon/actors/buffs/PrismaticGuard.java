

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.PrismaticImage;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

public class PrismaticGuard extends Buff {
	
	{
		type = buffType.POSITIVE;
	}
	
	private float 生命;
	private float 强度=1;

	private float powerOfManyTurns = 0;
	
	@Override
	public boolean act() {
		
		Hero hero = (Hero)target;
		
		Mob closest = null;
		int v = hero.visibleEnemies();
		for (int i=0; i < v; i++) {
			Mob mob = hero.视野敌人(i);
			if ( mob.isAlive() && !mob.是无敌(PrismaticImage.class)
					&& mob.state != mob.PASSIVE && mob.state != mob.WANDERING && mob.state != mob.SLEEPING && !hero.mindVisionEnemies.contains(mob)
					&& (closest == null ||Dungeon.level.距离(hero.pos,mob.pos)<Dungeon.level.距离(hero.pos,closest.pos))) {
				closest = mob;
			}
		}
		
		if (closest != null &&Dungeon.level.距离(hero.pos,closest.pos)<5){
			//spawn guardian
			int bestPos = -1;
			for (int i=0; i < PathFinder.相邻.length;i++) {
				int p = hero.pos + PathFinder.相邻[i];
				if (Actor.findChar( p ) == null && Dungeon.level.passable[p]) {
					if (bestPos == -1 || Dungeon.level.trueDistance(p, closest.pos) < Dungeon.level.trueDistance(bestPos, closest.pos)){
						bestPos = p;
					}
				}
			}
			if (bestPos != -1) {
				PrismaticImage pris = new PrismaticImage();
				pris.duplicate(hero);

				pris.state = pris.HUNTING;
				GameScene.add(pris, 1);
				传送卷轴.appear(pris,bestPos);
				
				detach();
			} else {
				spend( TICK );
			}
			
			
		} else {
			spend(TICK);
		}
		
		if (生命<maxHP()&&再生.regenOn()){
			if(Dungeon.hero()&&Dungeon.hero.hasbuff(再生.class))
			生命+= Dungeon.hero.buff(再生.class).再生生命()*强度;
		}
		if (powerOfManyTurns > 0){
			powerOfManyTurns--;
			if (powerOfManyTurns <= 0){
				powerOfManyTurns = 0;
				BuffIndicator.refreshHero();
			}
		}
		
		return true;
	}
	
	public void set( float HP ){
		this.生命= HP;
		powerOfManyTurns = 0;
	}

	public void set( PrismaticImage img){
		this.生命= img.生命;
		this.强度= img.强度();
		powerOfManyTurns = 0;
	}
	
	public float maxHP(){
		return maxHP((Hero)target);
	}
	
	public static float maxHP( Hero hero ){
		return hero.最大生命(0.1f); //half of hero's HP
	}

	public boolean isEmpowered(){
		return powerOfManyTurns > 0;
	}
	
	@Override
	public int icon() {
		return BuffIndicator.ARMOR;
	}
	
	@Override
	public void tintIcon(Image icon) {
		if (isEmpowered()){
			icon.hardlight(3f, 3f, 2f);
		} else {
			icon.hardlight(1f, 1f, 2f);
		}
	}

	@Override
	public float iconFadePercent() {
		return 1f-生命/maxHP();
	}

	@Override
	public String iconTextDisplay() {
		return Math.round(生命)+"";
	}
	
	@Override
	public String desc() {
		String desc = Messages.get(this,"desc",kw2(生命),kw2(maxHP()));
		if (isEmpowered()){
			desc += "\n\n" + Messages.get(this, "desc_many", (int)powerOfManyTurns);
		}
		return desc;
	}

	private static final String HEALTH = "hp";
	private static final String 强度x = "强度";
	private static final String POWER_TURNS = "power_turns";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(HEALTH,生命);
		bundle.put(强度x,强度);
		bundle.put(POWER_TURNS, powerOfManyTurns);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		生命= bundle.getFloat(HEALTH);
		强度= bundle.getFloat(强度x);
		powerOfManyTurns = bundle.getFloat(POWER_TURNS);
	}
}
