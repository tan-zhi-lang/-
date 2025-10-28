

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Barrier;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.items.trinkets.VialOfBlood;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class Dewdrop extends Item {
	
	{
		image = 物品表.DEWDROP;
		dropsDownHeap = true;
	}
	
	@Override
	public boolean doPickUp(Hero hero, int pos) {
		
		水袋 flask = hero.belongings.getItem( 水袋.class );//为什么找不到？
		Catalog.setSeen(getClass());
		Statistics.itemTypesDiscovered.add(getClass());
		if (flask != null && !flask.isFull()){

			flask.collectDew( this );
			GameScene.pickUp( this, pos );

		} else {

			int terr = Dungeon.level.map[pos];
			if (!consumeDew(1, hero, terr == Terrain.ENTRANCE || terr == Terrain.ENTRANCE_SP
					|| terr == Terrain.EXIT || terr == Terrain.UNLOCKED_EXIT)){
				return false;
			} else {
				Catalog.countUse(getClass());
			}
			
		}
		
		Sample.INSTANCE.play( Assets.Sounds.DEWDROP );
		hero.spendAndNext( pickupDelay() );
		
		return true;
	}

	public static boolean consumeDew(int quantity, Hero hero, boolean force){
		//20 drops for a full heal
		int effect = Math.round( hero.最大生命 * 0.05f * quantity );

		int heal = Math.min( hero.最大生命 - hero.生命, effect );

		if (quantity > 1 &&hero.天赋(Talent.SHIELDING_DEW)){
			int shield = hero.最大生命(hero.天赋点数(Talent.SHIELDING_DEW,0.25f)*quantity);
			Buff.施加(hero, Barrier.class).增加(shield);
			hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(shield), FloatingText.SHIELDING );
		}

		if (heal > 0 ) {
			if (quantity > 1 && VialOfBlood.delayBurstHealing()){
				Healing healing = Buff.施加(hero, Healing.class);
				healing.setHeal(heal, 0, VialOfBlood.maxHealPerTurn());
				healing.applyVialEffect();
			} else {
				hero.生命 += heal;
				if (heal > 0){
					hero.sprite.showStatusWithIcon( CharSprite.增强, Integer.toString(heal), FloatingText.HEALING);
				}
			}
			
		} else if (!force) {
			GLog.i( Messages.get(Dewdrop.class, "already_full") );
			return false;
		}

		return true;
	}

	@Override
	public boolean 可升级() {
		return false;
	}

	@Override
	public boolean 已鉴定() {
		return true;
	}

	//max of one dew in a stack

	@Override
	public Item merge( Item other ){
		if (isSimilar( other )){
			quantity = 1;
			other.quantity = 0;
		}
		return this;
	}

	@Override
	public Item 数量(int value) {
		quantity = Math.min( value, 1);
		return this;
	}

}
