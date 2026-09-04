

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.journal.Bestiary;
import com.watabou.utils.Bundle;

public abstract class NPC extends Mob {

	{
		生命 = 最大生命 = 1;

		alignment = Alignment.NEUTRAL;
		state = PASSIVE;
	}

	public boolean 跟随英雄=false;
	public float 跟随强度=1;
	public Hero hero;

	public int heroID;
	private static final String HEROID	= "hero_id";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( HEROID, heroID );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		heroID = bundle.getInt( HEROID );
	}

	@Override
	protected boolean act() {
		if (Dungeon.level.heroFOV[pos]){
			Bestiary.setSeen(getClass());
		}
		return super.act();
	}

	@Override
	public void beckon( int cell ) {
	}

	@Override
	public float 最小攻击() {
		if (跟随英雄&&hero != null) {
			return hero.最小攻击()*强度();
		}
		return super.最小攻击();
	}
	@Override
	public float 最大攻击() {

		if (跟随英雄&&hero != null) {
			return hero.最大攻击()*强度();
		}
		return super.最大攻击();

	}

	@Override
	public float 最小防御() {
		float dr = super.最小防御();
		if (跟随英雄&&hero != null){
			return dr+hero.最小防御()/2f*强度();
		}
		return dr;

	}
	@Override
	public float 最大防御() {
		float dr = super.最大防御();
		if (跟随英雄&&hero != null){
			return dr +hero.最大防御()/2f*强度();
		}
		return dr;

	}
	@Override
	public int 最小命中(Char target) {

		if (跟随英雄&&hero != null) {
			return Math.round(hero.最小命中(target)*强度());
		}
		return super.最小命中(target);

	}

	@Override
	public int 最大命中(Char target ) {

		if (跟随英雄&&hero != null) {
			return Math.round(hero.最大命中(target)*强度());
		}
		return super.最大命中(target);

	}

	@Override
	public int 最小闪避(Char target ) {
		if (跟随英雄&&hero != null) {
			return Math.round(hero.最小闪避(enemy)*强度());
		}
		return super.最小闪避(enemy);

	}
	public int 最大闪避(Char enemy) {
		if (跟随英雄&&hero != null) {
			return Math.round(hero.最大闪避(enemy)*强度());
		}
		return super.最大闪避(enemy);

	}

	@Override
	public float 攻击延迟() {
		if (跟随英雄&&hero != null)
			return hero.攻击延迟(); //handles ring of furor

		return super.攻击延迟(); //handles ring of furor
	}

	@Override
	public float 移速() {
		if (跟随英雄&&hero != null)
			return hero.移速()*强度(); //handles ring of furor

		return super.移速(); //handles ring of furor
	}

	@Override
	protected boolean canAttack(Char enemy) {
		if (跟随英雄&&hero != null)
			return hero.canAttack(enemy);

		return super.canAttack(enemy);
	}

	public float 强度(){
		return 跟随强度+Dungeon.hero.天赋点数(Talent.分身升力,0.1f);
	}
	@Override
	public float 攻击时(final Char enemy, float damage ) {
		if(跟随英雄&&hero != null)damage=hero.攻击时(enemy,damage*强度());
		damage = super.攻击时( enemy, damage );

		return damage;
	}
	@Override
	public float 防御时(final Char enemy, float damage ) {
		if(跟随英雄&&hero != null)damage=hero.防御时(enemy,damage*强度());
		damage = super.防御时( enemy, damage );

		return damage;
	}
}