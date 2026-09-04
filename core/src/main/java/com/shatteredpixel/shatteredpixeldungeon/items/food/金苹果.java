

package com.shatteredpixel.shatteredpixeldungeon.items.food;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.LostInventory;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.修理;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.永生秘药;
import com.shatteredpixel.shatteredpixeldungeon.items.水袋;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 金苹果 extends Food{

	public static final String AC_BLESS = "BLESS";
	private boolean blessed = false;

	{
		image = 物品表.金苹果;
		energy =Hunger.STARVING*2;

		//You tell the ankh no, don't revive me, and then it comes back to revive you again in another run.
		//I'm not sure if that's enthusiasm or passive-aggression.
		黄色 = blessed;
		物品 = true;
	}

	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions(hero);
		水袋 水袋 = hero.belongings.getItem(水袋.class);
		永生秘药 永生秘药 = hero.belongings.getItem(永生秘药.class);
		if (((水袋 != null && 水袋.isFull())||(永生秘药 != null)) && !blessed)
			actions.add( AC_BLESS );

		return actions;
	}

	@Override
	protected void satisfy(Hero hero) {
		for (Buff b : hero.buffs()){
			if(b.type==Buff.buffType.NEGATIVE&&!(b instanceof AllyBuff)&&!(b instanceof LostInventory)){
				b.detach();
			}
		}
		float 治疗=0.8f*2;
		float 修复=1.6f*4f*2;
		if(isBlessed()){
			hero.belongings.uncurseEquipped();
			治疗*=2;
			修复*=2;
		}
		Healing healing = Buff.施加(hero,Healing.class);
		healing.setHeal(hero.最大生命(治疗), 0.05f*2, 0);

		修理 护甲修理 = Buff.施加(hero,修理.class);
		护甲修理.setHeal(hero.最大护甲(修复), 0.05f*2, 0);


		super.satisfy(hero);
		}
	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_BLESS )) {

			水袋 水袋 = hero.belongings.getItem(水袋.class);
			永生秘药 永生秘药 = hero.belongings.getItem(永生秘药.class);
			if (水袋 != null&&水袋.isFull()||永生秘药!=null){
				blessed = true;
				if(水袋 != null&&水袋.isFull())
					水袋.empty();
				else if(永生秘药!=null)
					永生秘药.detach(hero.belongings.backpack);

				GLog.绿(Messages.get(this,"bless"));
				hero.spend( 1f );
				hero.busy();


				Sample.INSTANCE.play( Assets.Sounds.DRINK );
				CellEmitter.get(hero.pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
				hero.sprite.operate( hero.pos );
			}
		}
	}
	
	@Override
	public String name(){
		if (blessed)
			return "附魔"+super.name();
		else
			return super.name();
	}
	
	@Override
	public String desc() {
		if (blessed)
			return Messages.get(this, "desc_blessed");
		else
			return super.desc();
	}

	public boolean isBlessed(){
		return blessed;
	}

	public void bless(){
		blessed = true;
	}

	private static final Glowing WHITE = new Glowing( 0xFFFFCC );

	@Override
	public Glowing glowing() {
		return isBlessed() ? WHITE : null;
	}

	private static final String BLESSED = "blessed";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( BLESSED, blessed );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		blessed	= bundle.getBoolean( BLESSED );
	}
	
	@Override
	public int 金币() {
		return 50;
	}
}
