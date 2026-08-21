

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.effects.Lightning;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PointF;

import java.util.ArrayList;

public class 电击药物 extends 涂药{

	
	@Override
	public float 触发(Char c,float damage) {
		CharSprite s=c.sprite;
		if(s!=null&&s.parent!=null){
			ArrayList<Lightning.Arc> arcs=new ArrayList<>();
			arcs.add(new Lightning.Arc(new PointF(s.x,s.y+s.height/2),new PointF(s.x+s.width,s.y+s.height/2)));
			arcs.add(new Lightning.Arc(new PointF(s.x+s.width/2,s.y),new PointF(s.x+s.width/2,s.y+s.height)));
			s.parent.add(new Lightning(arcs,null));
			Sample.INSTANCE.play(Assets.Sounds.LIGHTNING);
		}

		
		return super.触发( c, damage);
	}
}
