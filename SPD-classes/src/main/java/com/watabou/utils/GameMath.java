

package com.watabou.utils;

import com.watabou.noosa.Game;

public class GameMath {
	
	public static float speed( float speed, float acc ) {
		
		if (acc != 0) {
			speed += acc * Game.elapsed;
		}
		
		return speed;
	}
	//gate
	public static int 之内(int min,int value,int max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		} else {
			return value;
		}
	}
	public static float 之内(float min,float value,float max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		} else {
			return value;
		}
	}
}
