

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd巫术;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 灵月法杖 extends Wand {

	{
		image = 物品表.灵月法杖;
		特别= true;
		专属= true;
	}

	@Override
	public int 金币() {
		return 0;
	}
	@Override
	public int 能量() {
		return 0;
	}
	public void updateLevel() {
		if(Dungeon.hero()){
			maxCharges = Math.min( initialCharges() + Dungeon.hero.等级(0.33f), 10);
		}else{
			maxCharges = Math.min( initialCharges(), 10);
		}
		curCharges = Math.min( curCharges+1, maxCharges );
	}
	@Override
	public boolean 可升级() {
		return false;
	}
	public boolean canCast( Hero hero, 巫术 spell ){
		return hero.belongings.contains(this)
				&& hero.buff(MagicImmune.class) == null
				&& curCharges >= spell.chargeUse(hero)
				&& spell.canCast(hero);
	}
	public void charge( Char owner ) {
		if (charger == null) charger = new 灵月法杖.Charger();
		charger.attachTo( owner );
	}
	public 巫术 targetingSpell = null;

	private 巫术 quickSpell = null;
	public void setQuickSpell(巫术 spell){
		if (quickSpell == spell){
			quickSpell = null; //re-assigning the same spell clears the quick spell
			if (charger != null){
				ActionIndicator.clearAction((ActionIndicator.Action) charger);
			}
		} else {
			quickSpell = spell;
			if (charger != null){
				ActionIndicator.setAction((ActionIndicator.Action) charger);
			}
		}
	}
	
	public class Charger extends Wand.Charger implements ActionIndicator.Action{
		
		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				//if we're loading in and the hero has partially spent a turn, delay for 1 turn
				if (target instanceof Hero && Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
					spend(TICK);
				}
				if (quickSpell != null) ActionIndicator.setAction(this);
				return true;
			}
			return false;
		}
		
		@Override
		public void detach() {
			super.detach();
			ActionIndicator.clearAction(this);
		}
		
		@Override
		public String actionName() {
			return quickSpell.name();
		}
		
		@Override
		public int actionIcon() {
			return quickSpell.icon()+1;
		}
		@Override
		public Visual primaryVisual() {
			Image ico= new HeroIcon(this);;
			ico.width += 4; //shift slightly to the left to separate from smaller icon
			return ico;
		}
		@Override
		public Visual secondaryVisual() {
			Image ico= new ItemSprite(new 灵月法杖());
			ico.scale.set(PixelScene.align(0.51f));
			ico.brightness(0.6f);
			return ico;
		}
		
		@Override
		public int indicatorColor() {
			return 0x651f66;
		}
		
		public 灵月法杖 灵月法杖(){
			return 灵月法杖.this;
		}
		@Override
		public void doAction() {
			if (cursed){
				GLog.w(Messages.get(灵月法杖.class,"cursed"));
				return;
			}
			
			if (!canCast(Dungeon.hero, quickSpell)){
				GLog.w(Messages.get(灵月法杖.class, "fizzles"));
				return;
			}
			
			if (QuickSlotButton.targetingSlot!=-1&&
				Dungeon.quickslot.getItem(QuickSlotButton.targetingSlot) == 灵月法杖.this) {
				targetingSpell = quickSpell;
				int cell = QuickSlotButton.autoAim(QuickSlotButton.lastTarget, 灵月法杖.this);
				
				if (cell != -1){
					GameScene.handleCell(cell);
				} else {
					//couldn't auto-aim, just target the position and hope for the best.
					GameScene.handleCell( QuickSlotButton.lastTarget.pos );
				}
			} else {
				quickSpell.onCast(灵月法杖.this,Dungeon.hero);
				
				if (quickSpell.targetingFlags() != -1 && Dungeon.quickslot.contains(灵月法杖.this)){
					targetingSpell = quickSpell;
					QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(灵月法杖.this));
				}
			}
		}
	}
	
	
	@Override
	public void execute(Hero hero, String action ) {

		super.execute( hero, action );

		if (hero.buff(MagicImmune.class) != null){
			GLog.w( Messages.get(Wand.class, "no_magic") );
			return;
		}

		if (curCharges == 0){
			GLog.w( Messages.get(Wand.class, "fizzles") );
			return;
		}
		if (action.equals( AC_ZAP )) {
			curUser = hero;
			curItem = this;

			GameScene.show(new Wnd巫术(this, hero, false));
		}
	}

	@Override
	public void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
				MagicMissile.FROST,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play(Assets.Sounds.ZAP);
	}

	@Override
	public void onZap(Ballistica attack) {

	}


	@Override
	public int initialCharges() {
		return 3;
	}


}
