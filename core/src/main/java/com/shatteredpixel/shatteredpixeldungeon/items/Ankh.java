

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite.Glowing;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Ankh extends Item {

	public static final String AC_BLESS = "BLESS";

	{
		image = 物品表.ANKH;

		//You tell the ankh no, don't revive me, and then it comes back to revive you again in another run.
		//I'm not sure if that's enthusiasm or passive-aggression.
		bones = true;
	}

	private boolean blessed = false;
	
	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions(hero);
		水袋 水袋 = hero.belongings.getItem(水袋.class);
		if (水袋 != null && 水袋.isFull() && !blessed)
			actions.add( AC_BLESS );
		return actions;
	}

	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_BLESS )) {

			水袋 水袋 = hero.belongings.getItem(水袋.class);
			if (水袋 != null){
				blessed = true;
				水袋.empty();
				GLog.p( Messages.get(this, "bless") );
				hero.spend( 1f );
				hero.busy();


				Sample.INSTANCE.play( Assets.Sounds.DRINK );
				CellEmitter.get(hero.pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
				hero.sprite.operate( hero.pos );
			}
		}
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
	public int value() {
		return 50 * quantity;
	}
}
