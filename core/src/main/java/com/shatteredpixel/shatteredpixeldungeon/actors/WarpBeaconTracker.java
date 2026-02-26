package com.shatteredpixel.shatteredpixeldungeon.actors;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.AttackIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class WarpBeaconTracker extends Buff implements ActionIndicator.Action{

		{
			revivePersists = true;
		}

		public int pos;
		public int depth;
		public int branch;

		Emitter e;

		@Override
		public void fx(boolean on) {
			if(on&&depth==Dungeon.depth){
				e=CellEmitter.center(pos);
				e.pour(MagicMissile.WardParticle.UP,0.05f);
			}else{
				if(e!=null)
					e.on=false;
			}
		}

		public static final String POS = "pos";
		public static final String DEPTH = "depth";
		public static final String BRANCH = "branch";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(POS, pos);
			bundle.put(DEPTH, depth);
			bundle.put(BRANCH, branch);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			pos = bundle.getInt(POS);
			depth = bundle.getInt(DEPTH);
			branch = bundle.getInt(BRANCH);
			ActionIndicator.setAction(this);
			BuffIndicator.refreshHero();
		}

	@Override
	public boolean attachTo(Char target){
		ActionIndicator.setAction(this);
		BuffIndicator.refreshHero();
		return super.attachTo(target);
	}

	@Override
	public void detach() {
		super.detach();
		ActionIndicator.clearAction(this);
	}

	@Override
	public String actionName() {
		return Messages.get(WarpBeaconTracker.class, "window_tele");
	}

	@Override
	public int actionIcon() {
		return HeroIcon.空间信标;
	}

	@Override
	public int indicatorColor(){
		return 0x8800FF;
	}

	@Override
	public void doAction() {
		if(target instanceof Hero hero){
			if(depth!=Dungeon.depth&&false){//跨层
				GLog.w(Messages.get(WarpBeaconTracker.class,"depths"));
				return;
			}

			hero.buff(Hunger.class).吃饭(-160+hero.天赋点数(Talent.曲境折迁,40));
			if(depth==Dungeon.depth&&branch==Dungeon.branch){
				Char existing=Actor.findChar(pos);

				if(existing!=null&&!(existing instanceof Hero)){
					if(false){
						//                                    float heroHP = hero.生命 + hero.shielding();
						//                                    float heroDmg = 5 * hero.天赋点数(Talent.TELEFRAG);
						//                                    hero.受伤时(Math.min(heroDmg, heroHP-1), WarpBeacon.this);
						//
						//                                    float damage = Hero.heroDamage(10*hero.天赋点数(Talent.TELEFRAG),15*hero.天赋点数(Talent.TELEFRAG));
						//                                    existing.sprite.flash();
						//                                    existing.sprite.bloodBurstA(existing.sprite.center(), damage);
						//                                    existing.受伤时(damage, WarpBeacon.this);
						//
						//                                    Sample.INSTANCE.play(Assets.Sounds.HIT_CRUSH);
						//                                    Sample.INSTANCE.play(Assets.Sounds.HIT_STRONG);
					}

					if(existing.isAlive()){
						Char toPush=Char.hasProp(existing,Char.Property.IMMOVABLE)?
								hero:
								existing;

						ArrayList<Integer> candidates=new ArrayList<>();
						for(int n: PathFinder.相邻8){
							int cell=pos+n;
							if(!Dungeon.level.solid[cell]&&Actor.findChar(cell)==null&&(!Char.hasProp(toPush,Char.Property.LARGE)||Dungeon.level.openSpace[cell])){
								candidates.add(cell);
							}
						}
						Random.shuffle(candidates);

						if(!candidates.isEmpty()){
							传送卷轴.appear(hero,pos);
							Actor.add(new Pushing(toPush,toPush.pos,candidates.get(0)));

							toPush.pos=candidates.get(0);
							Dungeon.level.occupyCell(toPush);
							next();
						}else{
							GLog.w(Messages.get(传送卷轴.class,"no_tele"));
						}
					}else{
						传送卷轴.appear(hero,pos);
					}
				}else{
					传送卷轴.appear(hero,pos);
				}

				Invisibility.dispel();
				Dungeon.observe();
				GameScene.updateFog();
				hero.checkVisibleMobs();
				AttackIndicator.updateState();

			}else{

				if(!Dungeon.interfloorTeleportAllowed()){
					GLog.w(Messages.get(传送卷轴.class,"no_tele"));
					return;
				}

				//transition before dispel, to cancel out trap effects
				Level.beforeTransition();
				Invisibility.dispel();
				InterlevelScene.mode=InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth=depth;
				InterlevelScene.returnBranch=branch;
				InterlevelScene.returnPos=pos;
				Game.switchScene(InterlevelScene.class);
			}
		}
	}
	}