

package com.watabou.noosa;

import com.watabou.gltextures.TextureCache;
import com.watabou.utils.Random;

public class ColorBlock extends Image implements Resizable {
	
	public ColorBlock( float width, float height, int color ) {
		super( TextureCache.createSolid( color ) );
		scale.set( width, height );
		origin.set( 0, 0 );
	}

	@Override
	public void size( float width, float height ) {
		scale.set( width, height );
	}
	
	public float left() {
		return x;
	}
	
	public float right() {
		return x + width;
	}
	
	public float centerX() {
		return x + width / 2;
	}
	
	public float top() {
		return y;
	}
	
	public float bottom() {
		return y + height;
	}
	@Override
	public float width() {
		return scale.x;
	}
	
	@Override
	public float height() {
		return scale.y;
	}
	public void 白色(){
		ra = +0.3f;
		ga = +0.3f;
		ba = +0.3f;
	}
	public void 蓝色(){
		ba = +0.3f;
	}
	public void 绿色(){
		ga = +0.3f;
	}
	public void 红色(){
		ra = +0.3f;
	}
	public void 黄色(){
		ra = +0.3f;
		ga = +0.3f;
	}
	public void 紫色(){
		ra = +0.3f;
		ba = +0.3f;
	}
	public void 青色(){
		ga = +0.3f;
		ba = +0.3f;
	}
	public void 橙色(){
		color(0xFFA500);
	}
	public void 棕色(){
		color(0x8F4E35);
	}
	public void 粉色(){
		ra = +0.3f;
		ba = +0.3f;
	}
	public void 靛色(){
		color(0x2c0d49);
	}
	public void 彩色(){

		color(Random.oneOf(
				0xFF4444,
				0xFFFF00,
				0x3399FF,
				0x44FF44,

				0xb2f2ff,
				0x2c0d49,
				0x8800FF
						  ));
	}
	public void 黑色(){
		ra = -0.3f;
		ga = -0.3f;
		ba = -0.3f;
	}
	public void 诅咒(){
		ra = +0.3f;
		ga = -0.15f;
		ba = -0.15f;
	}
	public void 无诅咒(){
		ba = +0.3f;
		ra = -0.1f;
	}
	public void 诅咒未知(){
		ra = +0.35f;
		ba = +0.35f;
	}
}
