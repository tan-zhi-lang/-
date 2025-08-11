

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

import java.util.HashSet;

public class Buff extends Actor {
	
	public Char target;

	//whether this buff was already extended by the mnemonic prayer spell
	public boolean mnemonicExtended = false;

	{
		actPriority = BUFF_PRIO; //low priority, towards the end of a turn
	}

	//determines how the buff is announced when it is shown.
	public enum buffType {POSITIVE, NEGATIVE, NEUTRAL}
	public buffType type = buffType.NEUTRAL;
	
	//whether or not the buff announces its name
	public boolean announced = false;

	//whether a buff should persist through revive effects or similar (e.g. transmogrify)
	public boolean revivePersists = false;
	
	protected HashSet<Class> resistances = new HashSet<>();
	
	public HashSet<Class> resistances() {
		return new HashSet<>(resistances);
	}
	
	protected HashSet<Class> immunities = new HashSet<>();
	
	public HashSet<Class> immunities() {
		return new HashSet<>(immunities);
	}
	
	public boolean attachTo( Char target ) {

		if (target.免疫( getClass() )) {
			return false;
		}
		
		this.target = target;

		if (target.add( this )){
			if (target.sprite != null) fx( true );
			return true;
		} else {
			this.target = null;
			return false;
		}
	}
	
	public void detach() {
		if (target.remove( this ) && target.sprite != null) fx( false );
	}
	
	@Override
	public boolean act() {
		diactivate();
		return true;
	}
	
	public int icon() {
		return BuffIndicator.NONE;
	}

	//some buffs may want to tint the base texture color of their icon
	public void tintIcon( Image icon ){
		//do nothing by default
	}

	//percent (0-1) to fade out out the buff icon, usually if buff is expiring
	public float iconFadePercent(){
		return 0;
	}

	//text to display on large buff icons in the desktop UI
	public String iconTextDisplay(){
		return "";
	}

	//visual effect usually attached to the sprite of the character the buff is attacked to
	public void fx(boolean on) {
		//do nothing by default
	}

	public String heroMessage(){
		String msg = Messages.get(this, "heromsg");
		if (msg.isEmpty()) {
			return null;
		} else {
			return msg;
		}
	}

	public String name() {
		return Messages.get(this, "name");
	}

	public String desc(){
		return Messages.get(this, "desc");
	}

	//to handle the common case of showing how many turns are remaining in a buff description.
	protected String dispTurns(float input){
		return Messages.decimalFormat("#.##", input);
	}

	//buffs act after the hero, so it is often useful to use cooldown+1 when display buff time remaining
	public float visualcooldown(){
		return cooldown()+1f;
	}

	private static final String MNEMONIC_EXTENDED    = "mnemonic_extended";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		if (mnemonicExtended) bundle.put(MNEMONIC_EXTENDED, mnemonicExtended);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(MNEMONIC_EXTENDED)) {
			mnemonicExtended = bundle.getBoolean(MNEMONIC_EXTENDED);
		}
	}

	//creates a fresh instance of the buff and attaches that, this allows duplication.
	public static<T extends Buff> T 新增(Char target, Class<T> buffClass ) {//重复
		T buff = Reflection.newInstance(buffClass);
		buff.attachTo( target );
		return buff;
	}

	public static<T extends FlavourBuff> T 新增(Char target, Class<T> buffClass, float duration ) {
		T buff = 新增( target, buffClass );
		buff.spend( duration * target.resist(buffClass) );
		return buff;
	}

	//same as append, but prevents duplication.
	public static<T extends Buff> T 施加(Char target, Class<T> buffClass ) {//不重复
		T buff = target.buff( buffClass );
		if (buff != null) {
			return buff;
		} else {
			return 新增( target, buffClass );
		}
	}
	
	public static<T extends FlavourBuff> T 施加(Char target, Class<T> buffClass, float duration ) {
		T buff = 施加( target, buffClass );
		buff.spend( duration * target.resist(buffClass) );
		return buff;
	}

	//postpones an already active buff, or creates & attaches a new buff and delays that.
	public static<T extends FlavourBuff> T 延长(Char target, Class<T> buffClass, float duration ) {//没有新增
		T buff = 施加( target, buffClass );
		buff.postpone( duration * target.resist(buffClass) );
		return buff;
	}

	public static<T extends CounterBuff> T count( Char target, Class<T> buffclass, float count ) {
		T buff = 施加( target, buffclass );
		buff.countUp( count );
		return buff;
	}
	
	public static void detach( Char target, Class<? extends Buff> cl ) {
		for ( Buff b : target.buffs( cl )){
			b.detach();
		}
	}
}
