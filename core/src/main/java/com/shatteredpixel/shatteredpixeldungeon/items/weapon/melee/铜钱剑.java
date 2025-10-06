

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.道术;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.HeroIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.QuickSlotButton;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd道术;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;

import java.util.ArrayList;

public class 铜钱剑 extends MeleeWeapon {

	{
		image = 物品表.铜钱剑;
		hitSound = Assets.Sounds.HIT_SLASH;
		hitSoundPitch = 1.1f;

		tier = 1;
		命中= 1.3f;
		间隔= 0.7f;
		伤害= 0.6f;
		
		bones = false;
	}
	
	public static String AC_道术 = "道术";
	@Override
	public int 攻击时(Char attacker,Char defender,int damage){
		
		if(defender.properties().contains(Char.Property.UNDEAD)&&attacker instanceof Hero hero&&hero.heroClass(HeroClass.道士)){
			damage=Math.round(damage*1.1f);
		}
		return super.攻击时(attacker,defender,damage);
	}
	
	
	@Override
	public String status() {
		if (charger!=null) {
			return charger.charges + "/" + charger.chargeCap();
		} else {
			return super.status();
		}
	}
	@Override
	public void activate(Char ch) {
		super.activate(ch);
		if (ch instanceof Hero hero && hero.heroClass(HeroClass.道士)){
			if (charger == null) charger = new 铜钱剑.Charger();
			charger.attachTo( hero );
			Buff.施加(ch, 铜钱剑.Charger.class);
			curItem=this;
		}
	}
	
	@Override
	public String defaultAction() {
		if (Dungeon.hero() &&Dungeon.hero.heroClass(HeroClass.道士)){
			return AC_道术;
		} else {
			return super.defaultAction();
		}
	}
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		
		if (hero.heroClass(HeroClass.道士)){
			actions.add(AC_道术);
		}
		return actions;
	}
	
	@Override
	public String actionName(String action, Hero hero) {
		if (action.equals(AC_道术)){
			return "道术";
		} else {
			return super.actionName(action, hero);
		}
	}
	@Override
	public void execute(Hero hero,String action){
		super.execute(hero,action);
		if (action.equals(AC_道术)){
			usesTargeting = true;
			if (hero.heroClass(HeroClass.道士)){
				curUser = hero;
				curItem = this;
				
				GameScene.show(new Wnd道术(this,hero,false));
			}
		}
	}
	
	public 道术 targetingSpell = null;
	
	public 道术 quickSpell = null;
	public void setQuickSpell(道术 spell){
		if (quickSpell == spell){
			quickSpell = null; //re-assigning the same spell clears the quick spell
			if (charger != null){
				ActionIndicator.clearAction((ActionIndicator.Action) charger);
			}
		} else {
			quickSpell = spell;
			if (charger!= null){
				ActionIndicator.setAction((ActionIndicator.Action) charger);
			}
		}
	}
	public boolean canCast( Hero hero, 道术 spell){
		return hero.belongings.contains(this)
			   &&hero.buff(MagicImmune.class)==null
			   && (charger.charges + charger.partialCharge) >= spell.chargeUse(hero)
			   && spell.canCast(hero);
	}
	public class Charger extends MeleeWeapon.Charger implements ActionIndicator.Action {
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
			return quickSpell.icon()+8;
		}
		@Override
		public Visual primaryVisual() {
			Image ico= new HeroIcon(this);;
			ico.width += 4; //shift slightly to the left to separate from smaller icon
			return ico;
		}
		@Override
		public Visual secondaryVisual() {
			Image ico= new ItemSprite(new 铜钱剑());
			ico.scale.set(PixelScene.align(0.51f));
			ico.brightness(0.6f);
			return ico;
		}
		@Override
		public int indicatorColor() {
			return 0x5a5151;
		}
		public 铜钱剑 铜钱剑(){
			return 铜钱剑.this;
		}
		@Override
		public void doAction() {
			
			if (!canCast(Dungeon.hero, quickSpell)){
				GLog.w(Messages.get(this,"ability_no_charge道术"));
				return;
			}
			
			if (QuickSlotButton.targetingSlot!=-1&&
				Dungeon.quickslot.getItem(QuickSlotButton.targetingSlot) == 铜钱剑.this) {
				targetingSpell = quickSpell;
				int cell = QuickSlotButton.autoAim(QuickSlotButton.lastTarget, 铜钱剑.this);
				
				if (cell != -1){
					GameScene.handleCell(cell);
				} else {
					//couldn't auto-aim, just target the position and hope for the best.
					GameScene.handleCell( QuickSlotButton.lastTarget.pos );
				}
			} else {
				quickSpell.onCast(铜钱剑.this,Dungeon.hero);
				
				if (quickSpell.targetingFlags() != -1 && Dungeon.quickslot.contains(铜钱剑.this)){
					targetingSpell = quickSpell;
					QuickSlotButton.useTargeting(Dungeon.quickslot.getSlot(铜钱剑.this));
				}
			}
		}
	}

}
