package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class 海克斯表 extends 海克斯 implements Bundlable{

	public ArrayList<海克斯> 海克斯表=new ArrayList<>();
	private static final String 海克斯表x = "海克斯表";

	@Override
	public void storeInBundle(Bundle bundle){
		bundle.put(海克斯表x, 海克斯表);
		super.storeInBundle(bundle);
	}

	@Override
	public void restoreFromBundle(Bundle bundle){
		super.restoreFromBundle(bundle);

		for (Bundlable item : bundle.getCollection(海克斯表x)) {
			if (item != null){
				//force-add the item if necessary, such as if its item category changed after an update
				海克斯表.add((海克斯) item);
			}
		}
	}
}
