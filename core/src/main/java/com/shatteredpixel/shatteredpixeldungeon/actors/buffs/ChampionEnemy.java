

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bat;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Crab;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Guard;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Thief;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.Image;
import com.watabou.utils.BArray;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public abstract class ChampionEnemy extends Buff {

	{
		type = buffType.POSITIVE;
		revivePersists = true;
	}

	protected int color;
	protected int rays=5;

	@Override
	public int icon() {
		return BuffIndicator.CORRUPT;
	}

	@Override
	public void tintIcon(Image icon) {
		icon.hardlight(color);
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.aura( color, rays );
		else target.sprite.clearAura();
	}

	public void onAttackProc(Char enemy ){

	}

	public boolean canAttackWithExtraReach( Char enemy ){
		return false;
	}

	public float DamageFactor(){
		return 1f;
	}

	public float 攻速(){
		return 1f;
	}


	public float 暴击率(){
		return 0;
	}

	public float 吸血(){
		return 0;
	}

	public float damageTakenFactor(){
		return 1f;
	}

	public float 最大生命(){
		return 1f;
	}

	public float evasionAndAccuracyFactor(){
		return 1f;
	}

	{
		immunities.add(AllyBuff.class);
	}

	public static void rollForChampion(Mob m){

		Dungeon.mobsToChampion--;

		//we roll for a champion enemy even if we aren't spawning one to ensure that
		//mobsToChampion does not affect levelgen RNG (number of calls to Random.Int() is constant)
		Class<?extends ChampionEnemy> buffCls;
		switch (Random.Int(6+3)){
			case 0: default:    buffCls = Blazing.class;      break;
			case 1:             buffCls = Projecting.class;   break;
			case 2:             buffCls = AntiMagic.class;    break;
			case 3:             buffCls = Giant.class;        break;
			case 4:             buffCls = Blessed.class;      break;
			case 5:             buffCls = Growing.class;      break;

			case 6:             buffCls = 噩梦.class;      break;
			case 7:             buffCls = 冰寒.class;      break;
			case 8:             buffCls = 嗜血.class;      break;
		}

		if (Dungeon.mobsToChampion <= 0 && Dungeon.isChallenged(Challenges.CHAMPION_ENEMIES)) {
			//we block certain standout enemies on floor <10 from becoming champions
			if (m instanceof Crab&&Dungeon.scalingDepth()<=3) return;
			if (m instanceof Thief&&Dungeon.scalingDepth()<=4) return;
			if (m instanceof Guard&&Dungeon.scalingDepth()<=7) return;
			if (m instanceof Bat&&Dungeon.scalingDepth()<=9) return;
			Buff.施加(m, buffCls);
			//numbers of mobs until a champion scales from 1/8 to 1/6 as depths increases
			
			Dungeon.mobsToChampion += 8 - Math.min(20, Dungeon.scalingDepth()-1)/10f;
//			if (m.state != m.PASSIVE) {
//				m.state = m.WANDERING;
//			}
		}
	}

	public static class Blazing extends ChampionEnemy {

		{
			color = 0xFF8800;
		}

		@Override
		public void onAttackProc(Char enemy) {
			if (!Dungeon.level.water[enemy.pos]) {
				Buff.施加(enemy, 燃烧.class).reignite(enemy);
			}
		}

		@Override
		public void detach() {
			//don't trigger when killed by being knocked into a pit
			if (target.flying || !Dungeon.level.pit[target.pos]) {
				for (int i : PathFinder.自相邻) {
					if (!Dungeon.level.solid[target.pos + i] && !Dungeon.level.water[target.pos + i]) {
						GameScene.add(Blob.seed(target.pos + i, 2, Fire.class));
					}
				}
			}
			super.detach();
		}

		@Override
		public float DamageFactor() {
			return 1.25f;
		}

		{
			immunities.add(燃烧.class);
		}
	}
	public static class 冰寒 extends ChampionEnemy {

		{
			color = 0x3399FF;
		}

		@Override
		public void onAttackProc(Char enemy) {
			if (算法.概率学(15)) {
				算法.修复效果(()->{
					Buff.施加(enemy,Frost.class,Frost.DURATION*0.15f);
				});
			}
		}

		@Override
		public float DamageFactor() {
			return 1.25f;
		}

		{
			immunities.add(Chill.class);
			immunities.add(Frost.class);
		}
	}

	public static class Projecting extends ChampionEnemy {

		{
			color = 0x8800FF;
		}

		@Override
		public float DamageFactor() {
			return 1.25f;
		}

		@Override
		public boolean canAttackWithExtraReach(Char enemy) {
			if (Dungeon.level.距离(target.pos,enemy.pos)>4){
				return false;
			} else {
				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				for (Char ch : Actor.chars()) {
					//our own tile is always passable
					passable[ch.pos] = ch == target;
				}

				PathFinder.buildDistanceMap(enemy.pos, passable, 4);

				return PathFinder.distance[target.pos] <= 4;
			}
		}
	}

	public static class AntiMagic extends ChampionEnemy {

		{
			color = 0x00FF00;
		}

		@Override
		public float 最大生命() {
			return 2;
		}

		{
			immunities.addAll(com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic.RESISTS);
		}

	}

	//Also makes target large, see Char.properties()
	public static class Giant extends ChampionEnemy {

		{
			color = 0x0088FF;
		}

		@Override
		public float 最大生命() {
			return 5;
		}

		@Override
		public boolean canAttackWithExtraReach(Char enemy) {
			if (Dungeon.level.距离(target.pos,enemy.pos)>2){
				return false;
			} else {
				boolean[] passable = BArray.not(Dungeon.level.solid, null);
				for (Char ch : Actor.chars()) {
					//our own tile is always passable
					passable[ch.pos] = ch == target;
				}

				PathFinder.buildDistanceMap(enemy.pos, passable, 2);

				return PathFinder.distance[target.pos] <= 2;
			}
		}
	}

	public static class Blessed extends ChampionEnemy {

		{
			color = 0xFFFF00;
		}

		@Override
		public float evasionAndAccuracyFactor() {
			return 4f;
		}
	}

	public static class Growing extends ChampionEnemy {

		{
			color = 0x111111;
		}

		private float multiplier = 1.2f;

		@Override
		public boolean act() {
			multiplier += 0.01f/4f;
			spend(TICK);
			return true;
		}

		@Override
		public float DamageFactor() {
			return multiplier;
		}

		@Override
		public float 最大生命() {
			return multiplier;
		}

		@Override
		public float evasionAndAccuracyFactor() {
			return multiplier;
		}

		@Override
		public String desc() {
			return Messages.get(this, "desc", (int)(100*(multiplier-1)), (int)(100*(1 - 1f/multiplier)));
		}

		private static final String MULTIPLIER = "multiplier";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(MULTIPLIER, multiplier);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			multiplier = bundle.getFloat(MULTIPLIER);
		}
	}

	public static class 噩梦 extends ChampionEnemy {

		{
			color = 0xFFFFFF;
		}


		@Override
		public float DamageFactor() {
			return 1.35f;
		}

		@Override
		public float 攻速() {
			return 1.5f;
		}

	}
	public static class 嗜血 extends ChampionEnemy {

		{
			color = 0xFF2222; //a little white helps it stick out from background
		}


		@Override
		public float 暴击率() {
			return 0.3f;
		}

		@Override
		public float 吸血() {
			return 0.25f;
		}

	}

}
