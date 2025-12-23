package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.火爆结晶动画;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;

public class 火爆结晶 extends NPC {
	
	public int tier = 1;
	
	public int totalZaps = 0;
	
	{
		spriteClass = 火爆结晶动画.class;
		
		alignment = Alignment.ALLY;
		
		properties.add(Property.IMMOVABLE);
		properties.add(Property.INORGANIC);
		
		viewDistance = 3;
		state = WANDERING;
	}
	
	public void upgrade(){
		
		if (tier < 3){
			tier++;
			大小=0.75f+tier*0.25f;
			viewDistance++;
			if (sprite != null){
				((火爆结晶动画)sprite).updateTier(tier);
				sprite.place(pos);
			}
			GameScene.updateFog(pos,viewDistance+1);
		}
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica(pos,enemy.pos,Ballistica.MAGIC_BOLT).collisionPos==enemy.pos;
	}
	
	@Override
	protected boolean doAttack(Char enemy) {
		boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
		if (visible) {
			sprite.zap( enemy.pos );
		} else {
			zap();
		}
		
		return !visible;
	}
	@Override
	public float 攻击延迟() {
		return super.攻击延迟();
	}
	private void zap() {
		spend( 攻击延迟() );
		Char enemy = this.enemy;
		enemy.受伤时( 2*tier, this );
		
		CellEmitter.get(enemy.pos).burst(SmokeParticle.FACTORY,4);
		
		for (int n : PathFinder.NEIGHBOURS9){
			Char c= Actor.findChar(enemy.pos+n);
			if(c!=null){
				enemy.受伤时( 2*tier, this );
			}
		}
		totalZaps++;
	}
	
	public void onZapComplete() {
		zap();
		next();
	}
	
	@Override
	protected boolean getCloser(int target) {
		return false;
	}
	
	@Override
	protected boolean getFurther(int target) {
		return false;
	}
	
	@Override
	public CharSprite sprite() {
		火爆结晶动画 sprite = (火爆结晶动画) super.sprite();
		sprite.linkVisuals(this);
		return sprite;
	}
	
	@Override
	public void updateSpriteState() {
		super.updateSpriteState();
		((火爆结晶动画)sprite).updateTier(tier);
		sprite.place(pos);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		Dungeon.observe();
		GameScene.updateFog(pos, viewDistance+1);
	}
	
	@Override
	public boolean canInteract(Char c) {
		return true;
	}
	
	@Override
	public boolean interact( Char c ) {
		if (c != Dungeon.hero){
			return true;
		}
		Game.runOnRenderThread(new Callback() {
			@Override
			public void call() {
				GameScene.show(new WndOptions(sprite(),
											  "要驱散这个结晶吗？",
											  "驱散返回5能量。",
											  "是",
											  "否"){
					@Override
					protected void onSelect(int index) {
						if (index == 0){
							Dungeon.energy(5);
							死亡时(null);
						}
					}
				});
			}
		});
		return true;
	}
	
	@Override
	public String description() {
			return Messages.get(this, "desc", tier,攻击延迟(),viewDistance,2*tier);
	}
	
	{
		immunities.add( Sleep.class);
		immunities.add( Terror.class);
		immunities.add( Dread.class);
		immunities.add( Vertigo.class);
		immunities.add( AllyBuff.class);
	}
	
	private static final String TIER = "tier";
	private static final String TOTAL_ZAPS = "total_zaps";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(TIER, tier);
		bundle.put(TOTAL_ZAPS, totalZaps);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		tier = bundle.getInt(TIER);
		viewDistance = 3 + tier;
		totalZaps = bundle.getInt(TOTAL_ZAPS);
	}
	
	
}