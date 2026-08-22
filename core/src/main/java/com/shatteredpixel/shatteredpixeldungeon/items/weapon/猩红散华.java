

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.Beam;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.真吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.算法;

public class 猩红散华 extends Weapon{
	
	{
		image = 物品表.猩红散华;
		hitSound = Assets.Sounds.攻击箭;
		范围=5;
		红色 = true;
		特别= true;

		命中=0.8f;
		伤害=0.8f;
		吸血=0.05f;
		tier = 5;
	}

	@Override
	public float 攻击时(Char attacker,Char defender,float damage) {

		if(defender!=null){
			attacker.扔出(defender.pos,new 真吸血刀(),()->{
				attacker.sprite.parent.add(new Beam.DeathRay(attacker,defender));
			});
			float x=0;
			if(算法.概率学(1/2f))x++;
			if(算法.概率学(1/2f))x++;
			if(算法.概率学(1/2f))x++;
			if(算法.概率学(1/2f))x++;

			if(算法.概率学(1/2f))x++;
			else if(算法.概率学(1/4f))x+=2;
			else if(算法.概率学(1/8f))x+=3;
			else if(算法.概率学(1/16f))x+=4;

			if(attacker instanceof Hero hero&&hero.符文("升级猩红散华")){
				x=8;
			}
			damage*=x;
		}

//
//			Ballistica b = new Ballistica(attacker.pos, defender.pos, Ballistica.WONT_STOP);
//			ConeAOE cone = new ConeAOE(b,360);
//
//			//cast to cells at the tip, rather than all cells, better performance.
////			Ballistica longestRay = null;
//
//			for (Ballistica ray : cone.outerRays){
//
//				int cell =findCell(ray,attacker);
//				Char ch = Actor.findChar(cell);
//
//				if(cell!=-1||ch!=null&&ch.isAlive()){
//					float finalDamage=damage;
//
//					attacker.扔出(cell,new 吸血刀(),()->{
//						if (ch != null){
//							if(ch.isAlive())ch.受伤时(finalDamage);
//
//							attacker.回血(0.07f*finalDamage);
//
////							if(ch.isAlive()){
////								attacker.sprite.parent.add(new Beam.DeathRay(attacker,ch));
////							}
//						}
//					});
//				}
//			}

		return super.攻击时( attacker, defender, damage );
	}

	public static int findCell(Ballistica path, Char hero){
		for (int cell : path.path){
			Char ch = Actor.findChar(cell);
			if (ch != null){
				if (ch == hero || ch.alignment == Char.Alignment.ALLY){
					continue;
				} else {
					return ch.pos;
				}
			}
			if (Dungeon.level.solid[cell]){
				return -1;
			}else return cell;
		}
		return -1;
	}
}
