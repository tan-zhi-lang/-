package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.黑暗结晶动画;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class 黑暗结晶 extends NPC {
	
	public int tier = 1;
	
	public int totalZaps = 0;
	
	{
		spriteClass = 黑暗结晶动画.class;
		
		alignment = Alignment.ALLY;
		
		properties.add(Property.IMMOVABLE);
		properties.add(Property.INORGANIC);
		
		viewDistance = 4;
		state = WANDERING;
	}

	@Override
	protected boolean act(){
		sprite.领域(0x999999,4+tier);
		return super.act();
	}
	public void upgrade(){
		
		if (tier < 3){
			tier++;
			大小=0.75f+tier*0.25f;
			viewDistance++;
			if (sprite != null){
				((黑暗结晶动画)sprite).updateTier(tier);
				sprite.place(pos);
			}
			GameScene.updateFog(pos,viewDistance+1);
		}
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica(pos,enemy.pos,Ballistica.MAGIC_BOLT).collisionPos==enemy.pos&&
			   false;
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

	private void zap() {
		spend( 攻击延迟() );
		Char enemy = this.enemy;
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
		黑暗结晶动画 sprite = (黑暗结晶动画) super.sprite();
		sprite.linkVisuals(this);
		return sprite;
	}
	
	@Override
	public void updateSpriteState() {
		super.updateSpriteState();
		((黑暗结晶动画)sprite).updateTier(tier);
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
											  "驱散返回"+(tier*5+5)/2+"能量。",
											  "是",
											  "否"){
					@Override
					protected void onSelect(int index) {
						if (index == 0){
							Dungeon.energy((tier*5+5)/2);
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
			return Messages.get(this, "desc", tier,viewDistance,20+10*tier);
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
		viewDistance = 4 + tier;
		totalZaps = bundle.getInt(TOTAL_ZAPS);
	}
	
	
}