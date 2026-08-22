

package com.shatteredpixel.shatteredpixeldungeon.items.涂药;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.传送卷轴;
import com.watabou.utils.BArray;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public class 传送药物 extends 涂药{

	@Override
	public float 触发(Char c,float damage) {

			//attempts to teleport the enemy to a position 8-10 cells away from the hero
			//prioritizes the closest visible cell to the defender, or closest non-visible if no visible are present
			//grants vision on the defender if teleport goes to non-visible
			if(!c.属性表().contains(Char.Property.IMMOVABLE)){

				ArrayList<Integer> visiblePositions=new ArrayList<>();
				ArrayList<Integer> nonVisiblePositions=new ArrayList<>();

				PathFinder.buildDistanceMap(Dungeon.hero.pos,BArray.or(Dungeon.level.passable,Dungeon.level.avoid,null));

				for(int pos=0;pos<Dungeon.level.length();pos++){
					if(Dungeon.level.passable[pos]&&PathFinder.distance[pos]>=8&&PathFinder.distance[pos]<=10&&(!Char.hasProp(c,Char.Property.LARGE)||Dungeon.level.openSpace[pos])&&Actor.findChar(pos)==null){

						if(Dungeon.level.heroFOV[pos]){
							visiblePositions.add(pos);
						}else{
							nonVisiblePositions.add(pos);
						}

					}
				}

				int chosenPos=-1;

				if(!visiblePositions.isEmpty()){
					for(int pos: visiblePositions){
						if(chosenPos==-1||Dungeon.level.trueDistance(c.pos,chosenPos)>Dungeon.level.trueDistance(c.pos,pos)){
							chosenPos=pos;
						}
					}
				}else{
					for(int pos: nonVisiblePositions){
						if(chosenPos==-1||Dungeon.level.trueDistance(c.pos,chosenPos)>Dungeon.level.trueDistance(c.pos,pos)){
							chosenPos=pos;
						}
					}
				}

				if(chosenPos!=-1){
					传送卷轴.appear(c,chosenPos);
					Dungeon.level.occupyCell(c);
				}

			}

		return super.触发( c, damage);
	}
}
