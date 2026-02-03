

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.叛忍护额;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.本命玉佩;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.神圣法典;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
import com.watabou.noosa.audio.Sample;

public class LostBackpack extends Item {

	{
		image = 物品表.BACKPACK;
		
		特别= true;
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		if (hero.buff(LostInventory.class) != null){
			hero.buff(LostInventory.class).detach();
		}

		MagicalHolster holster = hero.belongings.getItem(MagicalHolster.class);
		for (Item i : hero.belongings){
			if (i.keptThroughLostInventory()){
				i.keptThoughLostInvent = false; //don't reactivate, was previously activated
			} else {
				if (i instanceof EquipableItem && i.isEquipped(hero)){
					((EquipableItem) i).activate(hero);
				} else if (i instanceof 心之钢&&Dungeon.派对(派对设置.钢门联盟)){
					((心之钢) i).activate(hero);
				} else if ( i instanceof CloakOfShadows && hero.天赋(Talent.轻便斗篷)){
					((CloakOfShadows) i).activate(hero);
				} else if (i instanceof 神圣法典&&hero.天赋(Talent.轻量阅读)){
					((神圣法典) i).activate(hero);
				} else if (i instanceof 本命玉佩&&hero.天赋(Talent.轻便玉佩)){
					((本命玉佩) i).activate(hero);
				} else if (i instanceof 叛忍护额&&hero.天赋(Talent.轻便护额)){
					((叛忍护额) i).activate(hero);
				} else if (i instanceof Wand){
					if (holster != null && holster.contains(i)){
						((Wand) i).charge(hero, MagicalHolster.HOLSTER_SCALE_FACTOR);
					} else {
						((Wand) i).charge(hero);
					}
				}else if (i instanceof 法师魔杖){
					((法师魔杖) i).applyWandChargeBuff(hero);
				}
			}
		}

		hero.更新属性();

		Item.updateQuickslot();
		Sample.INSTANCE.play( Assets.Sounds.DEWDROP );
		hero.spendAndNext(hero.攻击延迟());
		GameScene.pickUp( this, pos );
		((HeroSprite)hero.sprite).updateArmor();

		Notes.remove(Notes.Landmark.LOST_PACK);
		return true;
	}
}
