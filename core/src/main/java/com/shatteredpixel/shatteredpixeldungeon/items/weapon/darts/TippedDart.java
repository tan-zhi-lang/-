

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Blindweed;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Fadeleaf;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Mageroyal;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredpixel.shatteredpixeldungeon.plants.Starflower;
import com.shatteredpixel.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sungrass;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class TippedDart extends 飞镖{
	
	{
		tier = 2;
	}
	
	private static final String AC_CLEAN = "CLEAN";
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.remove( AC_TIP );
		actions.add( AC_CLEAN );
		return actions;
	}
	
	@Override
	public void execute(final Hero hero, String action) {
		super.execute(hero, action);
		if (action.equals( AC_CLEAN )){

			String[] options = new String[]{
					Messages.get(this, "clean"),
					Messages.get(this, "cancel")
				};
			
			GameScene.show(new WndOptions(new ItemSprite(this),
					Messages.titleCase(name()),
					Messages.get(this, "clean_desc"),
					options){
				@Override
				protected void onSelect(int index) {
					if (index == 0){
						detach(hero.belongings.backpack);
						new 飞镖().放背包();
						
						hero.spend( 1f );
						hero.busy();
						hero.sprite.operate();
					}
				}
			});
			
		}
	}
	
	//exact same damage as regular darts, despite being higher tier.

	@Override
	protected void rangedHit(Char enemy, int cell) {
		targetPos = cell;
		super.rangedHit( enemy, cell);
		
		//need to spawn a dart
		if (!spawnedForEffect){
			//attempt to stick the dart to the enemy, just drop it if we can't.
			飞镖 d = new 飞镖();
			d.数量(1);
			Catalog.countUse(getClass());
			if (enemy != null && enemy.isAlive() && enemy.alignment != Char.Alignment.ALLY){
				PinCushion p = Buff.施加(enemy, PinCushion.class);
				if (p.target == enemy){
					p.stick(d);
					return;
				}
			}
			Dungeon.level.drop( d, enemy.pos ).sprite().drop();
		}
	}

	private static int targetPos = -1;
	
	@Override
	public int 金币() {
		//value of regular dart plus half of the seed
		return Math.round(7.5f * quantity);
	}
	
	public static final LinkedHashMap<Class<?extends Plant.Seed>, Class<?extends TippedDart>> types = new LinkedHashMap<>();
	static {
		types.put(Rotberry.Seed.class,      RotDart.class);
		types.put(Sungrass.Seed.class,      HealingDart.class);
		types.put(Fadeleaf.Seed.class,      DisplacingDart.class);
		types.put(Icecap.Seed.class,        ChillingDart.class);
		types.put(Firebloom.Seed.class,     IncendiaryDart.class);
		types.put(Sorrowmoss.Seed.class,    PoisonDart.class);
		types.put(Swiftthistle.Seed.class,  AdrenalineDart.class);
		types.put(Blindweed.Seed.class,     BlindingDart.class);
		types.put(Stormvine.Seed.class,     ShockingDart.class);
		types.put(Earthroot.Seed.class,     ParalyticDart.class);
		types.put(Mageroyal.Seed.class,     CleansingDart.class);
		types.put(Starflower.Seed.class,    HolyDart.class);
	}
	
	public static TippedDart getTipped( Plant.Seed s, int quantity ){
		return (TippedDart) Reflection.newInstance(types.get(s.getClass())).数量(quantity);
	}
	
	public static TippedDart randomTipped( int quantity ){
		Plant.Seed s;
		do{
			s = (Plant.Seed) Generator.randomUsingDefaults(Generator.Category.SEED);
		} while (!types.containsKey(s.getClass()));
		
		return getTipped(s, quantity );
		
	}
	
}
