

package com.shatteredpixel.shatteredpixeldungeon.actors.buffs;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金玫苦无;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.ActionIndicator;
import com.shatteredpixel.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.noosa.Image;
import com.watabou.noosa.Visual;

public class 忍术技能 extends Buff implements ActionIndicator.Action {
	
	{
		type = buffType.POSITIVE;

		//acts before the hero
		actPriority = HERO_PRIO+1;
	}
	


	@Override
	public void detach() {
		super.detach();
	}
	
	@Override
	public boolean attachTo(Char target){
		ActionIndicator.setAction(this);
		BuffIndicator.refreshHero();
		return super.attachTo(target);
	}
	
	@Override
	public boolean act() {
		ActionIndicator.setAction(this);
		BuffIndicator.refreshHero();
		spend(TICK);
		return true;
	}
	
	public void gainStack(){
		ActionIndicator.setAction(this);
		BuffIndicator.refreshHero();
	}
	
	@Override
	public String actionName() {
		return Messages.get(this, "action_name");
	}
	
	@Override
	public Visual primaryVisual() {
		Image ico= new ItemSprite(new 金玫苦无());
		return ico;
	}

	@Override
	public int indicatorColor() {
		return 0x999999;
	}

	@Override
	public void doAction() {
//		GameScene.show(new Wnd忍术(Dungeon.hero,false));
	}

}
