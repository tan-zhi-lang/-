

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.彩虹猫;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武技.斩击;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class 彩虹猫之刃 extends Weapon{

	{
		image = 物品表.彩虹猫之刃;
		hitSound = Assets.Sounds.攻击砍;
		伤害=0.8f;
		技能=new 斩击();
		彩光=true;
		tier = 5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			attacker.扔出(defender.pos,new 彩虹猫(),()->{
				Sample.INSTANCE.play(Random.oneOf(Assets.Sounds.彩虹猫1,Assets.Sounds.彩虹猫2));
			});
			damage*=1.45f;
		}
		return super.攻击时( attacker, defender, damage );
	}
}
