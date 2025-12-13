

package com.shatteredpixel.shatteredpixeldungeon.items;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass.NONE;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.ArmorAbility;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.abilities.Ratmogrify;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.ClassArmor;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndChooseAbility;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 矮人国王的皇冠 extends Item {
	
	private static final String AC_WEAR = "WEAR";
	private static final String AC_精通 = "精通";
	
	{
		image = 物品表.CROWN;

		defaultAction = AC_精通;
		可以空间=false;
		
		特别= true;
		物品 = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
//		actions.add( AC_WEAR );
		actions.add( AC_精通 );
		return actions;
	}
	
	@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_精通)&&hero.subClass!=NONE) {
			detach(hero.belongings.backpack);
			hero.精通=true;
			
			switch(hero.subClass){
				case 潜能觉醒: default:
					GLog.p("最大命中+！"+"综合属性+10%。");
					break;
				case 狂战士:
					GLog.p("最大命中+！"+"每点怒气+0.25%攻击力。");
					break;
				case 角斗士:
					GLog.p("最大命中+！"+"连击重置回合+3，6+连击时连击技获得强化。");
					break;
				case 战斗法师:
					GLog.p("最大命中+！"+"魔杖物理攻击会恢复0.5充能。");
					break;
				case 术士:
					GLog.p("最大命中+！"+"灵魂标记+10回合。");
					break;
				case 刺客:
					GLog.p("最大命中+！"+"每回合隐形会额外获得1阶段潜伏。");
					break;
				case 疾行者:
					GLog.p("最大命中+！"+"每点动能额外提供1逸动回合。");
					break;
				case 狙击手:
					GLog.p("最大命中+！"+"除灵能短弓外的物理攻击会施加狙击标记，灵能短弓对狙击标记敌人攻击取决于不同的强化方式。");
					break;
				case 守望者:
					GLog.p("最大命中+！"+"踩踏植物会获得额外效果取代原本效果，所有植株对其无害。");
					break;
				case 勇士:
					GLog.p("最大命中+！"+"主武器和副武器攻击效率+10%。");
					break;
				case 武者:
					GLog.p("最大命中+！"+"最大内力+5。");
					break;
				case PRIEST:
					GLog.p("最大命中+！"+"综合属性+15%");
					break;
				case PALADIN:
					GLog.p("最大命中+！"+"综合属性+15%");
					break;
				case 神秘学者:
					GLog.p("最大命中+！"+"综合属性+15%");
					break;
				case 黑魔导师:
					GLog.p("最大命中+！"+"综合属性+15%");
					break;
				case 健身猛男:
					GLog.p("最大命中+！"+"力量+20%。");
					break;
				case 盾之勇者:
					GLog.p("最大命中+！"+"综合属性+15%");
					break;
			}
		}
		
		if (action.equals(AC_WEAR)) {

			if (hero.belongings.armor() != null){
				GameScene.show( new WndChooseAbility(this, hero.belongings.armor(), hero));
			} else {
				GLog.w( Messages.get(this, "naked"));
			}
			
		}
	}
	
	public void upgradeArmor(Hero hero, Armor armor, ArmorAbility ability) {

		detach(hero.belongings.backpack);
		Catalog.countUse( getClass() );

		hero.sprite.emitter().burst( Speck.factory( Speck.CROWN), 12 );
		hero.spend(Actor.TICK);
		hero.busy();

		if (armor != null){

			if (ability instanceof Ratmogrify){
				GLog.p(Messages.get(this, "ratgraded"));
			} else {
				GLog.p(Messages.get(this, "upgraded"));
			}

			ClassArmor classArmor = ClassArmor.upgrade(hero, armor);
			if (hero.belongings.armor == armor) {

				hero.belongings.armor = classArmor;
				((HeroSprite) hero.sprite).updateArmor();
				classArmor.activate(hero);

			} else {

				armor.detach(hero.belongings.backpack);
				classArmor.放背包(hero.belongings.backpack);

			}
		}

		hero.armorAbility = ability;
		Talent.initArmorTalents(hero);

		hero.sprite.operate( hero.pos );
		Sample.INSTANCE.play( Assets.Sounds.MASTERY );
	}

}
