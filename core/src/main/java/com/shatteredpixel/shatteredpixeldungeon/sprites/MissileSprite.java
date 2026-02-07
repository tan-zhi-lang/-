

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.HolyLance;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGeomancer;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.关刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.单手剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.双剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.回旋镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.大肉棒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰鱼剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.小刺;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手里剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无影剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.暗杀之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武士刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流星索;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流火;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.真铜短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.硬头锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碎缘剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.符文之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.臻冰刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.苦无;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.草剃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蜜剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.轮刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.重锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长矛;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.Visual;
import com.watabou.noosa.tweeners.PosTweener;
import com.watabou.noosa.tweeners.Tweener;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;

import java.util.HashMap;

public class MissileSprite extends ItemSprite implements Tweener.Listener {

	private static final float SPEED	= 240f;
	
	private Callback callback;
	
	public void reset( int from, int to, Item item, Callback listener ) {
		reset(Dungeon.level.solid[from] ? DungeonTilemap.raisedTileCenterToWorld(from) : DungeonTilemap.raisedTileCenterToWorld(from),
				Dungeon.level.solid[to] ? DungeonTilemap.raisedTileCenterToWorld(to) : DungeonTilemap.raisedTileCenterToWorld(to),
				item, listener);
	}

	public void reset( Visual from, int to, Item item, Callback listener ) {
		reset(from.center(),
				Dungeon.level.solid[to] ? DungeonTilemap.raisedTileCenterToWorld(to) : DungeonTilemap.raisedTileCenterToWorld(to),
				item, listener );
	}

	public void reset( int from, Visual to, Item item, Callback listener ) {
		reset(Dungeon.level.solid[from] ? DungeonTilemap.raisedTileCenterToWorld(from) : DungeonTilemap.raisedTileCenterToWorld(from),
				to.center(),
				item, listener );
	}

	public void reset( Visual from, Visual to, Item item, Callback listener ) {
		reset(from.center(), to.center(), item, listener );
	}

	public void reset( PointF from, PointF to, Item item, Callback listener) {
		revive();

		if (item == null)   view(0, null);
		else                view( item );

		setup( from,
				to,
				item,
				listener );
	}
	
	private static final int DEFAULT_ANGULAR_SPEED = 720;
	
	private static final HashMap<Class<?extends Item>, Integer> ANGULAR_SPEEDS = new HashMap<>();
	static {
		ANGULAR_SPEEDS.put(飞镖.class,0);
		ANGULAR_SPEEDS.put(小刺.class,0);
		ANGULAR_SPEEDS.put(苦无.class,0);
		ANGULAR_SPEEDS.put(三叉戟.class,0);
		ANGULAR_SPEEDS.put(吸血刀.class,0);
		
		ANGULAR_SPEEDS.put(短剑.class,0);
		ANGULAR_SPEEDS.put(法师魔杖.class,0);
		ANGULAR_SPEEDS.put(镜刃.class,0);
		ANGULAR_SPEEDS.put(铜钱剑.class,0);
		ANGULAR_SPEEDS.put(匕首.class,0);
		ANGULAR_SPEEDS.put(长匕首.class,0);
		ANGULAR_SPEEDS.put(碧蓝巨剑.class,0);
		ANGULAR_SPEEDS.put(大肉棒.class,0);
		ANGULAR_SPEEDS.put(重锤.class,0);
		ANGULAR_SPEEDS.put(无影剑.class,0);
		ANGULAR_SPEEDS.put(英雄断剑.class,0);
		ANGULAR_SPEEDS.put(单手剑.class,0);
		ANGULAR_SPEEDS.put(长剑.class,0);
		ANGULAR_SPEEDS.put(双剑.class,0);
		ANGULAR_SPEEDS.put(巨剑.class,0);
		ANGULAR_SPEEDS.put(长矛.class,0);
		ANGULAR_SPEEDS.put(关刀.class,0);
		ANGULAR_SPEEDS.put(战斧.class,0);
		ANGULAR_SPEEDS.put(寒冰鱼剑.class,0);
		ANGULAR_SPEEDS.put(草剃.class,0);
		ANGULAR_SPEEDS.put(碎缘剑.class,0);
		ANGULAR_SPEEDS.put(臻冰刃.class,0);
		ANGULAR_SPEEDS.put(无尽之刃.class,0);
		ANGULAR_SPEEDS.put(蜜剑.class,0);
		ANGULAR_SPEEDS.put(火焰剑.class,0);
		ANGULAR_SPEEDS.put(真铜短剑.class,0);
		ANGULAR_SPEEDS.put(流火.class,0);
		ANGULAR_SPEEDS.put(饮血之剑.class,0);
		ANGULAR_SPEEDS.put(硬头锤.class,0);
		ANGULAR_SPEEDS.put(权杖.class,0);
		ANGULAR_SPEEDS.put(战锤.class,0);
		
		ANGULAR_SPEEDS.put(巨斧.class,0);
		ANGULAR_SPEEDS.put(武士刀.class,0);
		ANGULAR_SPEEDS.put(符文之刃.class,0);
		ANGULAR_SPEEDS.put(暗杀之刃.class,0);
		
		ANGULAR_SPEEDS.put(灵能短弓.SpiritArrow.class,       0);
		ANGULAR_SPEEDS.put(ScorpioSprite.ScorpioShot.class,   0);
		ANGULAR_SPEEDS.put(HolyLance.HolyLanceVFX.class,      0);
		ANGULAR_SPEEDS.put(手枪.子弹.class,0);
		
		//720 is default

		ANGULAR_SPEEDS.put(GnollGeomancer.Boulder.class,   90);
		
		ANGULAR_SPEEDS.put(回旋镖.class,1440);
		ANGULAR_SPEEDS.put(轮刃.class,1440);
		ANGULAR_SPEEDS.put(流星索.class,1440);
		
		ANGULAR_SPEEDS.put(手里剑.class,2160);
		ANGULAR_SPEEDS.put(寒冰镖.class,2160);
		ANGULAR_SPEEDS.put(TenguSprite.TenguShuriken.class, 2160);
	}

	//TODO it might be nice to have a source and destination angle, to improve thrown weapon visuals
	private void setup( PointF from, PointF to, Item item, Callback listener ){

		originToCenter();

		//adjust points so they work with the center of the missile sprite, not the corner
		from.x -= width()/2;
		to.x -= width()/2;
		from.y -= height()/2;
		to.y -= height()/2;

		this.callback = listener;

		point( from );

		PointF d = PointF.diff( to, from );
		speed.set(d).normalize().scale(SPEED);
		
		angularSpeed = DEFAULT_ANGULAR_SPEED;
		for (Class<?extends Item> cls : ANGULAR_SPEEDS.keySet()){
			if (cls.isAssignableFrom(item.getClass())){
				angularSpeed = ANGULAR_SPEEDS.get(cls);
				break;
			}
		}
		
		angle = 135 - (float)(Math.atan2( d.x, d.y ) / 3.1415926 * 180);
		
		if (d.x >= 0){
			flipHorizontal = false;
			updateFrame();
			
		} else {
			angularSpeed = -angularSpeed;
			angle += 90;
			flipHorizontal = true;
			updateFrame();
		}

		if (item instanceof GnollGeomancer.Boulder){
			angle = 0;
			flipHorizontal = false;
			updateFrame();
		}
		
		float speed = SPEED;
		if(SPDSettings.加快()>1){
			speed*=1.5f;
		}
		if (item instanceof 飞镖
				&& (Dungeon.hero.belongings.weapon() instanceof 十字弩
				|| Dungeon.hero.belongings.secondWep() instanceof 十字弩)){
			speed *= 3f;
			
		} else if (item instanceof 灵能短弓.SpiritArrow
				|| item instanceof ScorpioSprite.ScorpioShot
				|| item instanceof TenguSprite.TenguShuriken){
			speed *= 1.5f;
		}
		
		PosTweener tweener = new PosTweener( this, to, d.length() / speed );
		tweener.listener = this;
		parent.add( tweener );
	}

	@Override
	public void onComplete( Tweener tweener ) {
		kill();
		if (callback != null) {
			callback.call();
		}
	}
}
