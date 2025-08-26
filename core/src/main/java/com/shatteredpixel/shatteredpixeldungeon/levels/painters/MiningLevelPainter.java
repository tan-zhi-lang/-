

package com.shatteredpixel.shatteredpixeldungeon.levels.painters;

import com.shatteredpixel.shatteredpixeldungeon.items.Heap;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.quest.DarkGold;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredpixel.shatteredpixeldungeon.levels.rooms.quest.MineSecretRoom;
import com.watabou.utils.Graph;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;

public class MiningLevelPainter extends CavesPainter {

	@Override
	protected int padding(Level level) {
		return 3;
	}

	private int goldToAdd = 0;

	public RegularPainter setGold(int amount){
		goldToAdd = amount;
		return this;
	}

	@Override
	protected void generateGold(Level level, ArrayList<Room> rooms) {
		//we start by counting all the gold purposefully made by rooms
		for (int i = 0; i < level.length(); i++){
			if (level.map[i] == Terrain.WALL_DECO) {
				goldToAdd--;
			}
		}
		for (Heap h : level.heaps.valueList()){
			for (Item i : h.items){
				if (i instanceof DarkGold) goldToAdd -= i.set数量();
			}
		}

		int[] map = level.map;
		do {
			Random.shuffle(rooms);
			for (Room r : rooms) {

				if (r instanceof MineSecretRoom) continue;

				ArrayList<Integer> goldPosCandidates = new ArrayList<>();
				for (Point p : r.getPoints()){
					int i = level.pointToCell(p);

					if (level.insideMap(i) && goldToAdd > 0 && map[i] == Terrain.WALL){

						for (int j : PathFinder.NEIGHBOURS4){
							if (level.insideMap(i+j) && map[i+j] != Terrain.WALL){
								goldPosCandidates.add(i);
								break;
							}
						}
					}
				}

				if (goldToAdd > 0 && !goldPosCandidates.isEmpty()){
					int pos = Random.element(goldPosCandidates);

					map[pos] = Terrain.WALL_DECO;
					goldToAdd--;

					if (goldToAdd > 0){
						int i = PathFinder.NEIGHBOURS4[Random.Int(4)];
						if (level.insideMap(pos+i) && map[pos+i] == Terrain.WALL){
							map[pos+i] = Terrain.WALL_DECO;
							goldToAdd--;
						}
						if (Random.Int(2) == 0){
							i = PathFinder.NEIGHBOURS4[Random.Int(4)];
							if (level.insideMap(pos+i) && map[pos+i] == Terrain.WALL){
								map[pos+i] = Terrain.WALL_DECO;
								goldToAdd--;
							}
						}
					}

				}

			}
		} while (goldToAdd > 0);

	}

	@Override
	protected void paintDoors(Level l, ArrayList<Room> rooms) {
		HashMap<Room, Room> roomMerges = new HashMap<>();

		float hiddenDoorChance = 0.90f;

		//wall doors will still be wall
		//hidden doors become wall tiles a bit later in painting
		//everything else usually becomes empty, but can be wall sometimes
		for (Room r : rooms) {
			for (Room n : r.connected.keySet()) {

				Room.Door d = r.connected.get(n);
				int door = d.x + d.y * l.width();

				if (d.type == Room.Door.Type.WALL || d.type == Room.Door.Type.HIDDEN){
					l.map[door] = Terrain.WALL;
				} else {
					//some of these are randomly hidden, using the same rules as regular levels
					if (Random.Float() < hiddenDoorChance) {
						d.type = Room.Door.Type.HIDDEN;
						Graph.buildDistanceMap(rooms, r);
						if (n.distance == Integer.MAX_VALUE){
							l.map[door] = Terrain.EMPTY;
							d.type = Room.Door.Type.EMPTY;
						} else {
							l.map[door] = Terrain.WALL;
						}
					} else {
						l.map[door] = Terrain.EMPTY;
						d.type = Room.Door.Type.EMPTY;
					}

				}

				//if the door is empty, always merge the rooms
				if (l.map[door] == Terrain.EMPTY){
					if (roomMerges.get(r) == n || roomMerges.get(n) == r){
						continue;
					} else if (mergeRooms(l, r, n, r.connected.get(n), Terrain.EMPTY)) {
						roomMerges.put(r, n);
						roomMerges.put(n, r);
					}
				}

			}
		}
	}

	@Override
	protected void decorate(Level level, ArrayList<Room> rooms) {
		super.decorate(level, rooms);

		//no chasms allowed, replace with ground!
		for (int i = 0; i < level.length(); i++){
			if (level.map[i] == Terrain.CHASM){
				level.map[i] = Terrain.EMPTY;
			}
		}
	}
}
