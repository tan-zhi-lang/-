

package com.shatteredpixel.shatteredpixeldungeon.items.trinkets;

import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Challenges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AscensionChallenge;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.AmuletScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.Game;

import java.io.IOException;
import java.util.ArrayList;

public class 真正护符 extends Trinket {

	{
		image = 物品表.AMULET;//Amulet
		黄色 = true;
		特别= true;
		可堆叠=true;
		可以空间=false;
	}

	private static final String AC_END = "END";

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		if (hero.buff(AscensionChallenge.class)!=null){
			actions.clear();
		} else {
			actions.add(AC_END);
		}
		return actions;
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_END)) {
			showAmuletScene( false );
		}
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		if (super.doPickUp( hero, pos )) {

			if (!Statistics.amuletObtained) {
				Statistics.amuletObtained = true;
				hero.spend(-hero.cooldown());

				//delay with an actor here so pickup behaviour can fully process.
				Actor.add(new Actor(){

					{
						actPriority = VFX_PRIO;
					}

					@Override
					protected boolean act() {
						Actor.remove(this);
						showAmuletScene( true );
						return false;
					}
				});
			}

			return true;
		} else {
			return false;
		}
	}

	private void showAmuletScene( boolean showText ) {
		AmuletScene.noText = !showText;
		Game.switchScene(AmuletScene.class,new Game.SceneChangeCallback() {
			@Override
			public void beforeCreate() {

			}

			@Override
			public void afterCreate() {
				Badges.validateVictory();
				Badges.validateChampion(Challenges.activeChallenges());
				try {
					Dungeon.saveAll();
					Badges.saveGlobal();
				} catch (
						IOException e) {
					ShatteredPixelDungeon.reportException(e);
				}
			}
		});
	}

	@Override
	public String desc() {
		String desc = super.desc();

		if (Dungeon.hero == null || Dungeon.hero.buff(AscensionChallenge.class) == null){
			desc += "\n\n" + Messages.get(this, "desc_origins");
		} else {
			desc += "\n\n" + Messages.get(this, "desc_ascent");
		}

		return desc;
	}
	@Override
	protected int upgradeEnergyCost() {
		//6 -> 8(14) -> 10(24) -> 12(36)
		return 6+2* 等级();
	}

	@Override
	public String statsDesc() {
		if (已鉴定()){
			return Messages.get(this,"stats_desc",
								Messages.decimalFormat("#.##",100*综合()),
								Messages.decimalFormat("#.##",100*增加())
							   );
		} else {
			return Messages.get(this,"stats_desc",
								
								Messages.decimalFormat("#.##",100*综合(0)),
								Messages.decimalFormat("#.##",100*增加(0))
							   );
		}
	}

	public static float 综合(){
		return 综合(trinketLevel(真正护符.class));
	}

	public static float 综合(int level){
		if (level < 0){
			return 0;
		} else {
			return .1f+0.5f*level;
		}
	}

	public static float 增加(){
		return 增加(trinketLevel(真正护符.class));
	}

	public static float 增加(int level){
		if (level < 0){
			return 0;
		} else {
			return (10f+5f*level)/Dungeon.相对层数()/100f;
		}
	}

}
