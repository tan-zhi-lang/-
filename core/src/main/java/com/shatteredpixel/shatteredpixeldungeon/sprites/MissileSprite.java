

package com.shatteredpixel.shatteredpixeldungeon.sprites;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.GnollGeomancer;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.彩虹猫;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.拳击手套;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.真吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.神圣长枪;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.符箓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.飞镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.mis.魔法箭矢;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.下界合金剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.关刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.单手剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.双剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.吸血刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.回旋之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.回旋镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.圣剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.大肉棒;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.妖刀村正;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.枪弹;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.火炮子弹;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰镖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.寒冰鱼剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.小刺;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.巨斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.彩虹猫之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战斧;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.战锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.手里剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无尽之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无影剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.无限之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.暗杀之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.木棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.未知武器;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.权杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.十字弩;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械.枪械;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.武士刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.法师魔杖;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流星索;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.流火;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.海神三叉戟;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.火焰剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.灵能短弓;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.炼狱铲;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.玉龙;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.真铜短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.短剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.破败王剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.硬头锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碎缘剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.碧蓝巨剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.符文之刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.臻冰刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.苦无;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.英雄断剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.草剃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蘑菇长矛;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.虚哭神去;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.蜜剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.血荆棘;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.裂天剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.誓刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.轮刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.重锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.金铲铲;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铁头棍;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.铜钱剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.锯齿剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.镜刃;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长匕首;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.长矛;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.雷神锤;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.饮血之剑;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.骨刀;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.魄罗;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.黑暗剑;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.Visual;
import com.watabou.noosa.tweeners.PosTweener;
import com.watabou.noosa.tweeners.Tweener;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;

import java.util.HashMap;


public class MissileSprite extends ItemSprite implements Tweener.Listener {

	private static final float SPEED	= 1;
	
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
		ANGULAR_SPEEDS.put(海神三叉戟.class,0);
		ANGULAR_SPEEDS.put(妖刀村正.class,0);
		ANGULAR_SPEEDS.put(大肉棒.class,0);
		ANGULAR_SPEEDS.put(重锤.class,0);
		ANGULAR_SPEEDS.put(炼狱铲.class,0);
		ANGULAR_SPEEDS.put(雷神锤.class,0);
		ANGULAR_SPEEDS.put(骨刀.class,0);
		ANGULAR_SPEEDS.put(裂天剑.class,0);
		ANGULAR_SPEEDS.put(虚哭神去.class,0);
		ANGULAR_SPEEDS.put(回旋之刃.class,0);
		ANGULAR_SPEEDS.put(未知武器.class,0);
		ANGULAR_SPEEDS.put(无影剑.class,0);
		ANGULAR_SPEEDS.put(锯齿剑.class,0);
		ANGULAR_SPEEDS.put(英雄断剑.class,0);
		ANGULAR_SPEEDS.put(单手剑.class,0);
		ANGULAR_SPEEDS.put(长剑.class,0);
		ANGULAR_SPEEDS.put(双剑.class,0);
		ANGULAR_SPEEDS.put(巨剑.class,0);
		ANGULAR_SPEEDS.put(蘑菇长矛.class,0);
		ANGULAR_SPEEDS.put(木棍.class,0);
		ANGULAR_SPEEDS.put(铁头棍.class,0);
		ANGULAR_SPEEDS.put(无限之剑.class,0);
		ANGULAR_SPEEDS.put(长矛.class,0);
		ANGULAR_SPEEDS.put(关刀.class,0);
		ANGULAR_SPEEDS.put(战斧.class,0);
		ANGULAR_SPEEDS.put(金铲铲.class,0);
		ANGULAR_SPEEDS.put(寒冰鱼剑.class,0);
		ANGULAR_SPEEDS.put(玉龙.class,0);
		ANGULAR_SPEEDS.put(草剃.class,0);
		ANGULAR_SPEEDS.put(碎缘剑.class,0);
		ANGULAR_SPEEDS.put(臻冰刃.class,0);
		ANGULAR_SPEEDS.put(彩虹猫之刃.class,0);
		ANGULAR_SPEEDS.put(誓刃.class,0);
		ANGULAR_SPEEDS.put(无尽之刃.class,0);
		ANGULAR_SPEEDS.put(蜜剑.class,0);
		ANGULAR_SPEEDS.put(火焰剑.class,0);
		ANGULAR_SPEEDS.put(真铜短剑.class,0);
		ANGULAR_SPEEDS.put(流火.class,0);
		ANGULAR_SPEEDS.put(下界合金剑.class,0);
		ANGULAR_SPEEDS.put(饮血之剑.class,0);
		ANGULAR_SPEEDS.put(圣剑.class,0);
		ANGULAR_SPEEDS.put(黑暗剑.class,0);
		ANGULAR_SPEEDS.put(破败王剑.class,0);
		ANGULAR_SPEEDS.put(硬头锤.class,0);
		ANGULAR_SPEEDS.put(权杖.class,0);
		ANGULAR_SPEEDS.put(战锤.class,0);
		
		ANGULAR_SPEEDS.put(巨斧.class,0);
		ANGULAR_SPEEDS.put(武士刀.class,0);
		ANGULAR_SPEEDS.put(符文之刃.class,0);
		ANGULAR_SPEEDS.put(暗杀之刃.class,0);
		ANGULAR_SPEEDS.put(血荆棘.class,0);

		ANGULAR_SPEEDS.put(灵能短弓.SpiritArrow.class,       0);
		ANGULAR_SPEEDS.put(ScorpioSprite.ScorpioShot.class,   0);

		ANGULAR_SPEEDS.put(枪械.子弹.class,0);

		ANGULAR_SPEEDS.put(符箓.class,0);
		ANGULAR_SPEEDS.put(神圣长枪.class,0);
		ANGULAR_SPEEDS.put(魔法箭矢.class,0);
		ANGULAR_SPEEDS.put(拳击手套.class,0);
		ANGULAR_SPEEDS.put(彩虹猫.class,0);
		ANGULAR_SPEEDS.put(真吸血刀.class,0);
		ANGULAR_SPEEDS.put(飞镖.class,0);

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


		boolean 正上=false;
		boolean 正右=false;
		boolean 正左上=false;

		if (item instanceof 魄罗
				 ||item instanceof 拳击手套
				 ||item instanceof 彩虹猫
				||item instanceof 枪械.子弹 子弹&&(
				子弹.子弹 instanceof 火炮子弹
		))正右=true;
		else if (item instanceof 符箓
				 ||item instanceof GnollGeomancer.Boulder
				 ||item instanceof 骨刀
				 ||item instanceof 誓刃
				 ||item instanceof 血荆棘
				 ||item instanceof 真吸血刀
				 ||item instanceof 枪械.子弹 子弹&&(
					子弹.子弹 instanceof 枪弹
			)
			)正上=true;
		else if (item instanceof 无尽之刃)正左上=true;

		if(正上){
			angle = 180 - (float)(Math.atan2( d.x, d.y ) / 3.1415926 * 180);
		}else if(正右){
			angle = 90 - (float)(Math.atan2( d.x, d.y ) / 3.1415926 * 180);
		}else if(正左上){
			angle = -135 - (float)(Math.atan2( d.x, d.y ) / 3.1415926 * 180);
		}else angle = 135 - (float)(Math.atan2( d.x, d.y ) / 3.1415926 * 180);
		//默认正右上角+135即正左

		if (d.x >= 0){
			flipHorizontal = false;
			updateFrame();
			
		} else {
			angularSpeed = -angularSpeed;
			if(正上) angle += 0;
			else if(正右) angle += 180;
			else if(正左上) angle -= 90;
			else angle += 90;
			//默认正右上角+90即正右
			flipHorizontal = true;
			updateFrame();
		}

		float speed = SPEED;
		if(SPDSettings.加快()>1){
			speed*=2;
		}
		speed*=SPDSettings.弹道速度调整();
		if (item instanceof 十字弩.子弹
				||item instanceof 灵能短弓.SpiritArrow
				|| item instanceof ScorpioSprite.ScorpioShot
				|| item instanceof TenguSprite.TenguShuriken){
			speed *= 2;
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
