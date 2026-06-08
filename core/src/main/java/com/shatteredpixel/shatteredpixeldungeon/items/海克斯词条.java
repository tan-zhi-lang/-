

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class 海克斯词条 extends 用品 {
	
	
	{
		image = 物品表.海克斯词条;
		不能丢扔=true;
	}

	public String 海克斯="";
	public boolean 词条=false;
	private static final String 海克斯x=        "海克斯";
	private static final String 词条x=        "词条";
	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(海克斯x,海克斯);
		bundle.put(词条x,词条);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);
		海克斯= bundle.getString(海克斯x);
		词条= bundle.getBoolean(词条x);
	}

	@Override
	public String desc(){
		return Messages.get(this,"desc",海克斯,(词条?"开启":"关闭"));
	}
	public Item 海克斯(String s){
		词条=true;
		return this;
	}
	@Override
	public void 使用(Hero hero){

		Sample.INSTANCE.play(Assets.Sounds.海克斯);
		词条=!词条;
		super.使用(hero);
	}

}
