

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ShockingDart extends TippedDart {
	
	{
		image = 物品表.SHOCKING_DART;
	}
	
	@Override
	public float 攻击时(Char attacker, Char defender, float damage) {
		if(defender!=null){
			//when processing charged shot, only shock enemies
			if(!processingChargedShot||attacker.alignment!=defender.alignment){
				defender.受伤时(Random.NormalFloat(5+Dungeon.scalingDepth()/4f,10+Dungeon.scalingDepth()/4f),new Electricity());

				CharSprite s=defender.sprite;
				if(s!=null&&s.parent!=null){
					ArrayList<Lightning.Arc> arcs=new ArrayList<>();
					arcs.add(new Lightning.Arc(new PointF(s.x,s.y+s.height/2),new PointF(s.x+s.width,s.y+s.height/2)));
					arcs.add(new Lightning.Arc(new PointF(s.x+s.width/2,s.y),new PointF(s.x+s.width/2,s.y+s.height)));
					s.parent.add(new Lightning(arcs,null));
					Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
				}
			}
		}
		
		return super.攻击时(attacker, defender, damage);
	}
}
