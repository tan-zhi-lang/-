

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Bundle;

import java.util.ArrayList;
import java.util.Collection;

public class PinCushion extends Buff {

	private ArrayList<Weapon> items = new ArrayList<>();
	public void stick(Weapon projectile){
		for (int i = 0; i < items.size(); i++) {
//			if (projectile.isSimilar(items.get(i))) {
//				projectile.merge(items.get(i));
				items.set(i, projectile);
				return;
//			}
		}
		items.add(projectile);
	}

	public Item grabOne(){
		Item item = items.remove(0);
		if (items.isEmpty()){
			detach();
		}
		return item;
	}

	public ArrayList<KindOfWeapon> getStuckItems(){
		return new ArrayList<>(items);
	}

	@Override
	public void detach() {
		for (Item item : items)
			Dungeon.level.drop( item, target.pos).sprite().drop();
		super.detach();
	}

	private static final String ITEMS = "items";

	@Override
	public void storeInBundle(Bundle bundle) {
		bundle.put( ITEMS , items );
		super.storeInBundle(bundle);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		items = new ArrayList<>((Collection<Weapon>) ((Collection<?>) bundle.getCollection(ITEMS)));
		super.restoreFromBundle( bundle );
	}

	@Override
	public int icon() {
		return BuffIndicator.PINCUSHION;
	}

	@Override
	public String desc() {
		String desc = Messages.get(this, "desc");
		for (Item i : items){
			desc += "\n" + i.title();
		}
		return desc;
	}

}
