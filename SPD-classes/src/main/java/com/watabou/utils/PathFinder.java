

package com.watabou.utils;

import java.util.Arrays;
import java.util.LinkedList;

public class PathFinder {
	
	public static int[] distance;
	private static int[] maxVal;
	
	private static boolean[] goals;
	private static int[] queue;
	private static boolean[] queued; //currently only used in getStepBack, other can piggyback on distance
	
	private static int size = 0;
	private static int width = 0;
	public static int width(){
		return width;
	}

	private static int[] dir;
	private static int[] dirLR;

	//performance-light shortcuts for some common pathfinder cases
	//they are in array-access order for increased memory performance
	public static int[] 相邻4;
	public static int[] 相邻8;
	public static int[] 自相邻8;
	public static int[] 范围2;
	public static int[] 范围3;
	public static int[] 八卦;
	public static int 八卦开门;
	public static int 八卦休门;
	public static int 八卦生门;
	public static int 八卦伤门;
	public static int 八卦杜门;
	public static int 八卦景门;
	public static int 八卦死门;
	public static int 八卦惊门;
	public static int[] 范围4;
	public static int[] 范围5;
	public static int[] 范围6;
	public static int[] 范围7;
	public static int[] 范围8;

	//similar to their equivalent neighbour arrays, but the order is clockwise.
	//Useful for some logic functions, but is slower due to lack of array-access order.
	public static int[] CIRCLE4;
	public static int[] CIRCLE8;
	
	public static void setMapSize( int width, int height ) {
		
		PathFinder.width = width;
		PathFinder.size = width * height;
		
		distance = new int[size];
		goals = new boolean[size];
		queue = new int[size];
		queued = new boolean[size];

		maxVal = new int[size];
		Arrays.fill(maxVal, Integer.MAX_VALUE);

		dir = new int[]{-1, +1, -width, +width, -width-1, -width+1, +width-1, +width+1};
		dirLR = new int[]{-1-width, -1, -1+width, -width, +width, +1-width, +1, +1+width};

		相邻4= new int[]{-width, -1, +1, +width};
		相邻8= new int[]{-width-1, -width,-width+1, -1, +1,+width-1, +width,+width+1};
		自相邻8= new int[]{-width-1, -width,-width+1, -1, 0, +1,+width-1, +width,+width+1};
		
		范围2=x格(width,2);
		范围3=x格(width,3);
		八卦=new int[]{-width*3,
				-width*2-2,-width*2+2,
				-3, +3,
				+width*2-2,+width*2+2,
				+width*3};

		八卦开门=+width*2+2;
		八卦休门=+width*3;
		八卦生门=+width*2-2;
		八卦伤门=-3;
		八卦杜门=-width*2-2;
		八卦景门=-width*3;
		八卦死门=-width*2+2;
		八卦惊门=+3;

		范围4=x格(width,4);
		范围5=x格(width,5);
		范围6=x格(width,6);
		范围7=x格(width,7);
		范围8=x格(width,8);
		CIRCLE4 = new int[]{-width, +1, +width, -1};
		CIRCLE8 = new int[]{-width-1, -width, -width+1, +1, +width+1, +width, +width-1, -1};
	}
	public static int[] x格(int width,int range) {
		int[] S= new int[1 + 4 * range * (range + 1)];
		int n=0;
		for (int i = -range; i <= range; i++) {
			for (int j = -range; j <= range; j++) {
				S[n]=i * width + j;
				n++;
			}
		}
		return S;
	}

	public static Path find( int from, int to, boolean[] passable ) {

		if (!buildDistanceMap( from, to, passable )) {
			return null;
		}
		
		Path result = new Path();
		int s = from;

		// From the starting position we are moving downwards,
		// until we reach the ending point
		do {
			int minD = distance[s];
			int mins = s;
			
			for (int i=0; i < dir.length; i++) {
				
				int n = s + dir[i];
				
				int thisD = distance[n];
				if (thisD < minD) {
					minD = thisD;
					mins = n;
				}
			}
			s = mins;
			result.add( s );
		} while (s != to);
		
		return result;
	}
	
	public static int getStep( int from, int to, boolean[] passable ) {
		
		if (!buildDistanceMap( from, to, passable )) {
			return -1;
		}
		
		// From the starting position we are making one step downwards
		int minD = distance[from];
		int best = from;
		
		int step, stepD;
		
		for (int i=0; i < dir.length; i++) {

			if ((stepD = distance[step = from + dir[i]]) < minD) {
				minD = stepD;
				best = step;
			}
		}

		return best;
	}
	
	public static int getStepBack( int cur, int from, int lookahead, boolean[] passable, boolean canApproachFromPos ) {

		int d = buildEscapeDistanceMap( cur, from, lookahead, passable );
		if (d == 0) return -1;

		if (!canApproachFromPos) {
			//We can't approach the position we are retreating from
			//re-calculate based on this, and reduce the target distance if need-be
			int head = 0;
			int tail = 0;

			int newD = distance[cur];
			BArray.setFalse(queued);

			queue[tail++] = cur;
			queued[cur] = true;

			while (head < tail) {
				int step = queue[head++];

				if (distance[step] > newD) {
					newD = distance[step];
				}

				int start = (step % width == 0 ? 3 : 0);
				int end = ((step + 1) % width == 0 ? 3 : 0);
				for (int i = start; i < dirLR.length - end; i++) {

					int n = step + dirLR[i];
					if (n >= 0 && n < size && passable[n]) {
						if (distance[n] < distance[cur]) {
							passable[n] = false;
						} else if (distance[n] >= distance[step] && !queued[n]) {
							// Add to queue
							queue[tail++] = n;
							queued[n] = true;
						}
					}
				}

			}

			d = Math.min(newD, d);
		}

		for (int i=0; i < size; i++) {
			goals[i] = distance[i] == d;
		}
		if (!buildDistanceMap( cur, goals, passable )) {
			return -1;
		}

		int s = cur;
		
		// From the starting position we are making one step downwards
		int minD = distance[s];
		int mins = s;
		
		for (int i=0; i < dir.length; i++) {

			int n = s + dir[i];
			int thisD = distance[n];
			
			if (thisD < minD) {
				minD = thisD;
				mins = n;
			}
		}

		return mins;
	}
	
	private static boolean buildDistanceMap( int from, int to, boolean[] passable ) {
		
		if (from == to) {
			return false;
		}

		System.arraycopy(maxVal, 0, distance, 0, maxVal.length);
		
		boolean pathFound = false;
		
		int head = 0;
		int tail = 0;
		
		// Add to queue
		queue[tail++] = to;
		distance[to] = 0;
		
		while (head < tail) {
			
			// Remove from queue
			int step = queue[head++];
			if (step == from) {
				pathFound = true;
				break;
			}
			int nextDistance = distance[step] + 1;
			
			int start = (step % width == 0 ? 3 : 0);
			int end   = ((step+1) % width == 0 ? 3 : 0);
			for (int i = start; i < dirLR.length - end; i++) {

				int n = step + dirLR[i];
				if (n == from || (n >= 0 && n < size && passable[n] && (distance[n] > nextDistance))) {
					// Add to queue
					queue[tail++] = n;
					distance[n] = nextDistance;
				}
					
			}
		}
		
		return pathFound;
	}
	
	public static void buildDistanceMap( int to, boolean[] passable, int limit ) {
		
		System.arraycopy(maxVal, 0, distance, 0, maxVal.length);
		
		int head = 0;
		int tail = 0;
		
		// Add to queue
		queue[tail++] = to;
		distance[to] = 0;
		
		while (head < tail) {
			
			// Remove from queue
			int step = queue[head++];
			
			int nextDistance = distance[step] + 1;
			if (nextDistance > limit) {
				return;
			}
			
			int start = (step % width == 0 ? 3 : 0);
			int end   = ((step+1) % width == 0 ? 3 : 0);
			for (int i = start; i < dirLR.length - end; i++) {

				int n = step + dirLR[i];
				if (n >= 0 && n < size && passable[n] && (distance[n] > nextDistance)) {
					// Add to queue
					queue[tail++] = n;
					distance[n] = nextDistance;
				}
					
			}
		}
	}
	
	private static boolean buildDistanceMap( int from, boolean[] to, boolean[] passable ) {
		
		if (to[from]) {
			return false;
		}
		
		System.arraycopy(maxVal, 0, distance, 0, maxVal.length);
		
		boolean pathFound = false;
		
		int head = 0;
		int tail = 0;
		
		// Add to queue
		for (int i=0; i < size; i++) {
			if (to[i]) {
				queue[tail++] = i;
				distance[i] = 0;
			}
		}
		
		while (head < tail) {
			
			// Remove from queue
			int step = queue[head++];
			if (step == from) {
				pathFound = true;
				break;
			}
			int nextDistance = distance[step] + 1;
			
			int start = (step % width == 0 ? 3 : 0);
			int end   = ((step+1) % width == 0 ? 3 : 0);
			for (int i = start; i < dirLR.length - end; i++) {

				int n = step + dirLR[i];
				if (n == from || (n >= 0 && n < size && passable[n] && (distance[n] > nextDistance))) {
					// Add to queue
					queue[tail++] = n;
					distance[n] = nextDistance;
				}
					
			}
		}
		
		return pathFound;
	}

	//the lookahead is the target number of cells to retreat toward from our current position's
	// distance from the position we are escaping from. Returns the highest found distance, up to the lookahead
	private static int buildEscapeDistanceMap( int cur, int from, int lookAhead, boolean[] passable ) {
		
		System.arraycopy(maxVal, 0, distance, 0, maxVal.length);
		
		int destDist = Integer.MAX_VALUE;
		
		int head = 0;
		int tail = 0;
		
		// Add to queue
		queue[tail++] = from;
		distance[from] = 0;
		
		int dist = 0;
		
		while (head < tail) {
			
			// Remove from queue
			int step = queue[head++];
			dist = distance[step];
			
			if (dist > destDist) {
				return destDist;
			}
			
			if (step == cur) {
				destDist = dist + lookAhead;
			}
			
			int nextDistance = dist + 1;
			
			int start = (step % width == 0 ? 3 : 0);
			int end   = ((step+1) % width == 0 ? 3 : 0);
			for (int i = start; i < dirLR.length - end; i++) {

				int n = step + dirLR[i];
				if (n >= 0 && n < size && passable[n] && distance[n] > nextDistance) {
					// Add to queue
					queue[tail++] = n;
					distance[n] = nextDistance;
				}
					
			}
		}
		
		return dist;
	}
	
	public static void buildDistanceMap( int to, boolean[] passable ) {
		
		System.arraycopy(maxVal, 0, distance, 0, maxVal.length);
		
		int head = 0;
		int tail = 0;
		
		// Add to queue
		queue[tail++] = to;
		distance[to] = 0;
		
		while (head < tail) {
			
			// Remove from queue
			int step = queue[head++];
			int nextDistance = distance[step] + 1;
			
			int start = (step % width == 0 ? 3 : 0);
			int end   = ((step+1) % width == 0 ? 3 : 0);
			for (int i = start; i < dirLR.length - end; i++) {

				int n = step + dirLR[i];
				if (n >= 0 && n < size && passable[n] && (distance[n] > nextDistance)) {
					// Add to queue
					queue[tail++] = n;
					distance[n] = nextDistance;
				}
					
			}
		}
	}
	
	@SuppressWarnings("serial")
	public static class Path extends LinkedList<Integer> {
	}
}
