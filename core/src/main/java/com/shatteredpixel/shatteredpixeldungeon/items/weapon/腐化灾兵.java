

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.小吞噬怪;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class 腐化灾兵 extends Weapon{

	{
		image = 物品表.腐化灾兵;
		hitSound = Assets.Sounds.长枪;

		tier = 5;
		专属=true;
		特别=true;
		绿色=true;
		伤害= 0.8f;
//		连招范围=2;
		范围 = 2;
	}
	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {
		if(defender!=null){
			for(int x=1;x<=2;x++){
				if(传送卷轴.周身瞬移(attacker.pos)){
					小吞噬怪 mob=new 小吞噬怪();
					if(attacker instanceof Hero)
						Buff.施加(mob,Corruption.class);
					mob.攻击=damage*0.6f;
					mob.target(defender.pos);
					GameScene.add(mob);
					传送卷轴.周身瞬移(mob,attacker.pos);
				}
			}
		}
		return super.攻击时( attacker, defender, damage );
	}

}
