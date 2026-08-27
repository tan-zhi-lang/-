

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
	public static int 百分之内(int min,int value,int max) {
		if (value < min*value) {
			return min*value;
		} else if (value > max*value) {
			return max*value;
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
	public static float 百分之内(float min,float value,float max) {
		if (value < min*value) {
			return min*value;
		} else if (value > max*value) {
			return max*value;
		} else {
			return value;
		}
	}
	public static float 变1值(float value) {

		if(value==1.0f){
			value=0.0f;
		}
		if(value==0.0f){
			value=0.1f;
		}
		if(value==0.1){
			value=0.2f;
		}
		if(value==0.2){
			value=0.3f;
		}
		if(value==0.4){
			value=0.5f;
		}
		if(value==0.5){
			value=0.6f;
		}
		if(value==0.5){
			value=0.6f;
		}
		if(value==0.6){
			value=0.7f;
		}
		if(value==0.7){
			value=0.8f;
		}
		if(value==0.8){
			value=0.9f;
		}
		if(value==0.9){
			value=1.0f;
		}
		return value;
	}
	public static float 变2值(float value) {

		if(value==0.1f){
			value=0.00f;
		}
		if(value==0.00f){
			value=0.01f;
		}
		if(value==0.01){
			value=0.02f;
		}
		if(value==0.02){
			value=0.03f;
		}
		if(value==0.04){
			value=0.05f;
		}
		if(value==0.05){
			value=0.06f;
		}
		if(value==0.05){
			value=0.06f;
		}
		if(value==0.06){
			value=0.07f;
		}
		if(value==0.07){
			value=0.08f;
		}
		if(value==0.08){
			value=0.09f;
		}
		if(value==0.09){
			value=0.1f;
		}
		return value;
	}
}
