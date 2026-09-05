

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.连击;
import com.shatteredpixel.shatteredpixeldungeon.items.秘银;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 无限宝石拳套 extends Weapon {
	
	{
		image = 物品表.无限宝石拳套;
		hitSound = Assets.Sounds.无限宝石拳套;

		特别=true;
		黄色=true;
		tier = 5;
		技能=new 连击();
	}

	@Override
	public float 伤害(){
		return super.伤害()*2/5f;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage){

		ArrayList<Mob> enemies = new ArrayList<>();
		for(Mob m : Dungeon.level.mobs){
			if(m!=null&&m.alignment==Char.Alignment.ENEMY) enemies.add(m);
		}
		if(!enemies.isEmpty()){
			Random.shuffle(enemies);
			int n=enemies.size()/2;
			for(int i=0;i<n;i++){
				enemies.get(i).受伤时(enemies.get(i).最大生命);
			}

			attacker.受伤时(attacker.生命(0.5f));
		}
		return super.攻击时(attacker,defender,damage);
	}

	public static class Recipe extends com.shatteredpixel.shatteredpixeldungeon.items.Recipe.SimpleRecipe{

		{
			inputs=new Class[]{镶钉手套.class,
					秘银.class,};
			inQuantity=new int[]{1,
					1,};

			cost=12;

			output=无限宝石拳套.class;
			outQuantity=1;
		}
	}

	}
