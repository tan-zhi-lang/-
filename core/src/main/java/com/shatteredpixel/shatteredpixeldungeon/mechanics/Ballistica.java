

package com.shatteredpixel.shatteredpixeldungeon.mechanics;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Ballistica {

	//note that the path is the FULL path of the projectile, including tiles after collision.
	//make sure to generate a subPath for the common case of going source to collision.
	public ArrayList<Integer> path = new ArrayList<>();
	public Integer sourcePos = null;
	public Integer collisionPos = null;
	public Integer collisionProperties = null;
	public Integer dist = 0;

	//parameters to specify the colliding cell
	public static final int STOP_TARGET = 1;    //ballistica will stop at the target cell
	public static final int STOP_CHARS = 2;     //ballistica will stop on first char hit
	public static final int STOP_SOLID = 4;     //ballistica will stop on solid terrain
	public static final int IGNORE_SOFT_SOLID = 8; //ballistica will ignore soft solid terrain, such as doors and webs

	public static final int PROJECTILE =  	STOP_TARGET	| STOP_CHARS	| STOP_SOLID;

	public static final int MAGIC_BOLT =    STOP_CHARS  | STOP_SOLID;

	public static final int WONT_STOP =     0;


	public Ballistica(int from, int to, int params) {
		sourcePos = from;
		collisionProperties = params;
		build(from, to,
			  (params & STOP_TARGET) > 0,
			  (params & STOP_CHARS) > 0,
			  (params & STOP_SOLID) > 0,
			  (params & IGNORE_SOFT_SOLID) > 0);

		if (collisionPos != null) {
			dist = path.indexOf(collisionPos);
		} else if (!path.isEmpty()) {
			collisionPos = path.get(dist = path.size() - 1);
		} else {
			path.add(from);
			collisionPos = from;
			dist = 0;
		}
	}

	private void build(int from, int to, boolean stopTarget, boolean stopChars, boolean stopTerrain, boolean ignoreSoftSolid) {
		int w = Dungeon.level.width();

		// 1. 坐标转换（一维索引 → 二维坐标）
		int x0 = from % w;
		int y0 = from / w;
		int x1 = to % w;
		int y1 = to / w;

		// 2. 标准Bresenham算法参数初始化
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		int sx = x0 < x1 ? 1 : -1; // X方向步进（±1）
		int sy = y0 < y1 ? 1 : -1; // Y方向步进（±1）
		int err = dx - dy; // 初始误差项

		int cell;
		while (true) {
			// 3. 计算当前格子的一维索引
			cell = y0 * w + x0;

			// 超出地图边界则终止
			if (!Dungeon.level.insideMap(cell)) break;

			// 4. 碰撞检测（保留原逻辑，仅调整顺序）
			Integer prevCell = path.isEmpty() ? null : path.get(path.size() - 1);
			if (collisionPos == null && stopTerrain && cell != sourcePos
				&& !Dungeon.level.passable[cell] && !Dungeon.level.avoid[cell]
				&& Actor.findChar(cell) == null) {
				if (prevCell != null) collide(prevCell);
			}

			// 5. 将当前格子加入路径
			path.add(cell);

			// 6. 其他碰撞检测（固体、角色、目标）
			if (collisionPos == null && stopTerrain && cell != sourcePos && Dungeon.level.solid[cell]) {
				if (!(ignoreSoftSolid && (Dungeon.level.passable[cell] || Dungeon.level.avoid[cell]))) {
					collide(cell);
				}
			}
			if (collisionPos == null && cell != sourcePos && stopChars && Actor.findChar(cell) != null) {
				collide(cell);
			}
			if (collisionPos == null && cell == to && stopTarget) {
				collide(cell);
			}

			// 7. 到达目标点则终止（避免继续生成多余路径）
			if (x0 == x1 && y0 == y1) break;

			// 8. 标准Bresenham步进逻辑（核心修复！）
			int e2 = 2 * err;
			if (e2 > -dy) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dx) {
				err += dx;
				y0 += sy;
			}
		}
	}
	//we only want to record the first position collision occurs at.
	private void collide(int cell){
		if (collisionPos == null) {
			collisionPos = cell;
		}
	}

	//returns a segment of the path from start to end, inclusive.
	//if there is an error, returns an empty arraylist instead.
	public List<Integer> subPath(int start, int end){
		try {
			end = Math.min( end, path.size()-1);
			return path.subList(start, end+1);
		} catch (Exception e){
			ShatteredPixelDungeon.reportException(e);
			return new ArrayList<>();
		}
	}
}
