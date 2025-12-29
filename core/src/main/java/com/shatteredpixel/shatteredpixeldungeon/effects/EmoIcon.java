

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.Icons;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.utils.Random;

public class EmoIcon extends Image {

	protected float minSize = 0.35f;
	protected float maxSize = 0.75f;
	protected float timeScale = 1;
	
	protected boolean growing	= true;
	
	protected CharSprite owner;
	
	public EmoIcon( CharSprite owner ) {
		super();
		
		this.owner = owner;
		x = owner.x + owner.width()/2 - width / 2;
		y = owner.y- owner.height()/2f - height/2f-0.5f;
		GameScene.add( this );
	}
	
	@Override
	public void update() {
		super.update();
		
		if (visible) {
			if (growing) {
				scale.set( Math.min(scale.x + Game.elapsed * timeScale, maxSize ));
				if (scale.x >= maxSize) {
					growing = false;
				}
			} else {
				scale.set( Math.max(scale.x - Game.elapsed * timeScale, maxSize ));//maxSize1
				if (scale.x <= maxSize) {//maxSize1
					growing = true;
				}
			}
			
			x = owner.x + owner.width()/2 - width / 2;
			y = owner.y- owner.height()/2f - height/2f-0.5f;
		}
		
		if(owner!=null&&(owner.invisible!=null||(owner.ch!=null&&owner.ch.hasbuff(Invisibility.class)))){
			alpha0();
		}else{
			alpha1();
		}
	}
	
	public static class Sleep extends EmoIcon {
		
		public Sleep( CharSprite owner ) {
			super( owner );
			copy( Icons.get( Icons.睡眠 ) );
//			copy( Icons.get( Icons.SLEEP ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );

		}
	}
	
	public static class Alert extends EmoIcon {
		
		public Alert( CharSprite owner ) {
			
			super( owner );
			
			copy( Icons.get( Icons.感叹 ) );
//			copy( Icons.get( Icons.ALERT ) );
			
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );

		}
	}
	
	public static class Lost extends EmoIcon {
		
		public Lost( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.问号 ) );
//			copy( Icons.get( Icons.LOST ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}

	public static class 无语 extends EmoIcon {
		
		public 无语( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.无语 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 扣6 extends EmoIcon {
		
		public 扣6( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.扣6 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 微笑 extends EmoIcon {
		
		public 微笑( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.微笑 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 愤怒 extends EmoIcon {
		
		public 愤怒( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.愤怒 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 爱心 extends EmoIcon {
		
		public 爱心( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.爱心 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	
	public static class 礼物 extends EmoIcon {
		
		public 礼物( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.礼物 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	
	public static class 滑稽 extends EmoIcon {
		
		public 滑稽( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.滑稽 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 歪嘴 extends EmoIcon {
		
		public 歪嘴( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.歪嘴 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 吃瓜 extends EmoIcon {
		
		public 吃瓜( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.吃瓜 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
	public static class 哭泣 extends EmoIcon {
		
		public 哭泣( CharSprite owner ){
			super( owner );
			
			copy( Icons.get( Icons.哭泣 ) );
			
			origin.set( width / 2, height / 2 );
			scale.set( Random.Float( minSize, maxSize ) );
			
		}
		
	}
}
