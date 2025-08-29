

package com.shatteredpixel.shatteredpixeldungeon.items.wands;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.巫术;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd巫术;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class 灵月法杖 extends Wand {

	{
		image = 物品表.灵月法杖;
		unique = true;
		bones = false;
	}
	public int initialCharges() {
		return 2;
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
	public void onHit(法师魔杖 staff, Char attacker, Char defender, int damage) {

	}



}
