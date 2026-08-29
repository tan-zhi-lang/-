

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Fire;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.火毒;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.灵焰;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.元素.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs.ElixirOfDragonsBlood;
import com.shatteredpixel.shatteredpixeldungeon.items.器灵;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.special.MagicalFireRoom;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 下界合金剑 extends Weapon{
	{
		image = 物品表.下界合金剑;
		hitSound = Assets.Sounds.攻击砍;

		tier=5;
		特别=true;
		黑色=true;
	}
	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{长剑.class,
					ElixirOfDragonsBlood.class,
					器灵.class,};
			inQuantity = new int[]{1,1,1,};

			cost = 15;

			output = 下界合金剑.class;
			outQuantity = 1;
		}

	}
	float add=0.05f;
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		float x=1;
		if(defender!=null&&defender.hasbuff(火毒.class)){
			x+=add;
		}
		if(defender!=null&&defender.hasbuff(燃烧.class)){
			x+=add;
		}
		if(defender!=null&&defender.hasbuff(灵焰.class)){
			x+=add;
		}
		if(Dungeon.level!=null&&Dungeon.level.blobs!=null){
			Fire fire = (Fire) Dungeon.level.blobs.get(Fire.class);
			if (fire != null && fire.volume > 0)
			x+=add*fire.volume/2f;
		}
		if(Dungeon.level!=null&&Dungeon.level.blobs!=null){
			MagicalFireRoom.EternalFire eternalFire = (MagicalFireRoom.EternalFire)Dungeon.level.blobs.get(MagicalFireRoom.EternalFire.class);
			if (eternalFire != null && eternalFire.volume > 0) {
				x+=add*eternalFire.volume/2f;
			}
		}

		
		damage*=x;
		return super.攻击时( attacker, defender, damage );
	}
}
