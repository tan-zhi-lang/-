

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 飞镖 extends Weapon{

	{
		物品 = true;

		image = 物品表.DART;
		hitSound = Assets.Sounds.HIT_ARROW;
		
		伤害=0.7f;
		tier = 1;
	}
	
	protected static final String AC_TIP = "TIP";
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions( hero );
		actions.add( AC_TIP );
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		if (action.equals(AC_TIP)){
			GameScene.selectItem(itemSelector);
		}
	}
	
	
	protected static 十字弩 bow;
	
	private void updateCrossbow(){
		if (Dungeon.hero == null) {
			bow = null;
		} else if (Dungeon.hero.belongings.weapon() instanceof 十字弩){
			bow = (十字弩) Dungeon.hero.belongings.weapon();
		} else if (Dungeon.hero.belongings.secondWep() instanceof 十字弩) {
			//player can instant swap anyway, so this is just QoL
			bow = (十字弩) Dungeon.hero.belongings.secondWep();
		} else {
			bow = null;
		}
	}
	
	@Override
	public int 最小投掷攻击(int lvl) {
		updateCrossbow();
		if(bow!=null){
			return super.最小投掷攻击(lvl)*2;
		}
		return super.最小投掷攻击(lvl);
	}
	@Override
	public int 最大投掷攻击(int lvl) {
		updateCrossbow();
		if(bow!=null){
			return super.最大投掷攻击(lvl)*2;
		}
		return super.最大投掷攻击(lvl);
	}
	
	public boolean crossbowHasEnchant(Char owner){
		return bow != null && bow.enchantment != null && owner.buff(MagicImmune.class) == null;
	}
	
	@Override
	public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
		if (bow != null && bow.hasEnchant(type, owner)){
			return true;
		} else {
			return super.hasEnchant(type, owner);
		}
	}

	@Override
	public int 投掷攻击时(Char attacker, Char defender, int damage) {
		if (bow != null && !processingChargedShot){
			damage = bow.投掷攻击时(attacker, defender, damage);
		}

		int dmg = super.投掷攻击时(attacker, defender, damage);
		if (!processingChargedShot) {
			processChargedShot(defender, damage);
		}
		return dmg;
	}

	@Override
	public int throwPos(Hero user, int dst) {
		updateCrossbow();
		return super.throwPos(user, dst);
	}

	@Override
	protected void onThrow(int cell) {
		updateCrossbow();
		//we have to set this here, as on-hit effects can move the target we aim at
		chargedShotPos = cell;
		super.onThrow(cell);
	}

	protected boolean processingChargedShot = false;
	private int chargedShotPos;
	protected void processChargedShot( Char target, int dmg ){
		//don't update xbow here, as dart may be the active weapon atm
		processingChargedShot = true;
		if (chargedShotPos != -1 && bow != null
//			&&Dungeon.hero.buff(十字弩.ChargedShot.class)!=null
		) {
			PathFinder.buildDistanceMap(chargedShotPos, Dungeon.level.passable, 3);
			//necessary to clone as some on-hit effects use Pathfinder
			int[] distance = PathFinder.distance.clone();
			for (Char ch : Actor.chars()){
				if (ch == target){
					Actor.add(new Actor() {
						{ actPriority = VFX_PRIO; }
						@Override
						protected boolean act() {
							if (!ch.isAlive()){
							
							}
							Actor.remove(this);
							return true;
						}
					});
				} else if (distance[ch.pos] != Integer.MAX_VALUE){
					this.投掷攻击时(Dungeon.hero, ch, dmg);
				}
			}
		}
		chargedShotPos = -1;
		processingChargedShot = false;
	}

	@Override
	public void throwSound() {
		updateCrossbow();
		if (bow != null) {
			Sample.INSTANCE.play(Assets.Sounds.ATK_CROSSBOW, 1, Random.Float(0.87f, 1.15f));
		} else {
			super.throwSound();
		}
	}
	
	@Override
	public String info() {
		updateCrossbow();
		if (bow != null && !bow.已鉴定()){
			十字弩 realBow = bow;
			//create a temporary bow for IDing purposes
			bow = new 十字弩();
			String info = super.info();
			bow = realBow;
			return info;
		} else {
			return super.info();
		}
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
		return Math.round(super.金币()/2f); //half normal value
	}
	
	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return Messages.get(飞镖.class,"prompt");
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return 绒布袋.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Plant.Seed;
		}

		@Override
		public void onSelect(final Item item) {
			
			if (item == null) return;
			
			final String[] options;
				options = new String[]{
						Messages.get(飞镖.class,"tip_one"),
						Messages.get(飞镖.class,"tip_cancel")};
			
			TippedDart tipResult = TippedDart.getTipped((Plant.Seed) item, 1);
			
			GameScene.show(new WndOptions( new ItemSprite(item),
					Messages.titleCase(item.name()),
										   Messages.get(飞镖.class,"tip_desc",tipResult.name())+"\n\n"+tipResult.desc(),
					options){
				
				@Override
				protected void onSelect(int index) {
					super.onSelect(index);
					
					if (index == 0 && options.length == 3){
							item.detach( curUser.belongings.backpack );
							curItem.detach(curUser.belongings.backpack);
						
						TippedDart newDart = TippedDart.getTipped((Plant.Seed) item, 1);
						if (!newDart.放背包()) Dungeon.level.drop(newDart, curUser.pos).sprite.drop();
						
						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate();
					}
				}
			});
			
		}
		
	};
}
