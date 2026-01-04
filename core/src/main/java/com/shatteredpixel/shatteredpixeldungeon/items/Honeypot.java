

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Bee;
import com.shatteredpixel.shatteredpixeldungeon.effects.Pushing;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredpixel.shatteredpixeldungeon.items.food.蜂蜜;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Honeypot extends Item {
	
	public static final String AC_SHATTER	= "SHATTER";
	
	{
		image = 物品表.HONEYPOT;

		defaultAction = AC_THROW;
		usesTargeting = true;

		黄色 = true;
		可堆叠= true;
		物品 = true;
	}
	
	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_SHATTER );
		return actions;
	}
	
	@Override
	public void execute( final Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals( AC_SHATTER )) {
			
			hero.sprite.zap( hero.pos );
			
			detach( hero.belongings.backpack );
			Catalog.countUse(getClass());

			Item item = shatter( hero, hero.pos );
			if (!item.放背包(hero.belongings.backpack)){
				if (item instanceof ShatteredPot){
					((ShatteredPot) item).dropPot(hero, hero.pos);
				}
			}

			hero.next();

		}
	}
	
	@Override
	protected void onThrow( int cell ) {
		if (Dungeon.level.pit[cell]) {
			super.onThrow( cell );
		} else {
			Catalog.countUse(getClass());
			Dungeon.level.drop(shatter( null, cell ), cell);
		}
	}
	
	public Item shatter( Char owner, int pos ) {
		
		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play( Assets.Sounds.SHATTER );
			Splash.at( pos, 0xffd500, 5 );
		}
		
		int newPos = pos;
		if (Actor.findChar( pos ) != null) {
			ArrayList<Integer> candidates = new ArrayList<>();
			
			for (int n : PathFinder.NEIGHBOURS4) {
				int c = pos + n;
				if (!Dungeon.level.solid[c] && Actor.findChar( c ) == null) {
					candidates.add( c );
				}
			}
	
			newPos = candidates.size() > 0 ? Random.element( candidates ) : -1;
		}
		
		if (newPos != -1) {
			Dungeon.level.drop(new 蜂蜜(),newPos).sprite().drop();
			Bee bee = new Bee();
			bee.spawn( Dungeon.scalingDepth() );
			bee.setPotInfo( pos, owner );
			bee.生命 = bee.最大生命;
			bee.pos = newPos;
			
			GameScene.add( bee );
			if (newPos != pos) Actor.add( new Pushing( bee, pos, newPos ) );

			bee.sprite.alpha( 0 );
			bee.sprite.parent.add( new AlphaTweener( bee.sprite, 1, 0.15f ) );
			
			Sample.INSTANCE.play( Assets.Sounds.BEE );
			Dungeon.level.drop(new 蜂蜜(),pos).sprite().drop();
			return new ShatteredPot();
		} else {
			return this;
		}
	}
	
	
	@Override
	public int 金币() {
		return 30 * quantity;
	}

	//The bee's broken 'home', all this item does is let its bee know where it is, and who owns it (if anyone).
	public static class ShatteredPot extends Item {

		{
			image = 物品表.SHATTPOT;
			可堆叠= true;
			defaultAction = AC_THROW;
		}
		
		public static final String AC_修复	= "修复";
		@Override
		public ArrayList<String> actions( Hero hero ) {
			ArrayList<String> actions = super.actions( hero );
			if(hero.subClass(HeroSubClass.养殖专家)&&Dungeon.energy>=6){
				actions.add( AC_修复 );
			}
			return actions;
		}
		@Override
		public void execute( final Hero hero, String action ) {
			
			super.execute( hero, action );
			
			if (action.equals( AC_修复 )) {
				if (Dungeon.energy <6){
					GLog.w(Messages.get(AlchemistsToolkit.class,"need_energy"));
				}else{
					Dungeon.energy(-6);
					
					detach( hero.belongings.backpack );
					hero.sprite.zap( hero.pos );
					new Honeypot().放背包();
					
					hero.next();
				}
			}
		}
		@Override
		public boolean doPickUp(Hero hero, int pos) {
			if ( super.doPickUp(hero, pos) ){
				pickupPot( hero );
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void doDrop(Hero hero) {
			super.doDrop(hero);
			dropPot(hero, hero.pos);
		}

		@Override
		protected void onThrow(int cell) {
			super.onThrow(cell);
			dropPot(curUser, cell);
		}

		public void pickupPot(Char holder){
			for (Bee bee : findBees(holder.pos)){
				updateBee(bee, -1, holder);
			}
		}
		
		public void dropPot( Char holder, int dropPos ){
			for (Bee bee : findBees(holder)){
				updateBee(bee, dropPos, null);
			}
		}

		public void movePot( int oldpos, int movePos){
			for (Bee bee : findBees(oldpos)){
				updateBee(bee, movePos, null);
			}
		}

		public void destroyPot( int potPos ){
			for (Bee bee : findBees(potPos)){
				updateBee(bee, -1, null);
			}
		}

		private void updateBee( Bee bee, int cell, Char holder ){
			if (bee != null && bee.alignment == Char.Alignment.ENEMY)
				bee.setPotInfo( cell, holder );
		}
		
		//returns up to quantity bees which match the current pot Pos
		private ArrayList<Bee> findBees( int potPos ){
			ArrayList<Bee> bees = new ArrayList<>();
			for (Char c : Actor.chars()){
				if (c instanceof Bee && ((Bee) c).potPos() == potPos){
					bees.add((Bee) c);
					if (bees.size() >= quantity) {
						break;
					}
				}
			}
			
			return bees;
		}
		
		//returns up to quantity bees which match the current pot holder
		private ArrayList<Bee> findBees( Char potHolder ){
			ArrayList<Bee> bees = new ArrayList<>();
			for (Char c : Actor.chars()){
				if (c instanceof Bee && ((Bee) c).potHolderID() == potHolder.id()){
					bees.add((Bee) c);
					if (bees.size() >= quantity) {
						break;
					}
				}
			}
			
			return bees;
		}

		@Override
		public boolean 可升级() {
			return false;
		}

		@Override
		public boolean 已鉴定() {
			return true;
		}
		
		@Override
		public int 金币() {
			return 5 * quantity;
		}
	}
}
