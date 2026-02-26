package com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs;


import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Dread;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Sleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.冰爆结晶动画;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;

public class 冰爆结晶 extends NPC {
	
	public int tier = 1;
	
	public int totalZaps = 0;
	
	{
		spriteClass = 冰爆结晶动画.class;
		
		alignment = Alignment.ALLY;
		
		properties.add(Property.IMMOVABLE);
		properties.add(Property.INORGANIC);
		
		viewDistance = 4;
		state = WANDERING;
	}

	@Override
	protected boolean act(){
		sprite.领域(0x3399FF,4+tier);
		return super.act();
	}
	public void upgrade(){
		
		if (tier < 3){
			tier++;
			大小=0.75f+tier*0.25f;
			viewDistance++;
			if (sprite != null){
				((冰爆结晶动画)sprite).updateTier(tier);
				sprite.place(pos);
			}
			GameScene.updateFog(pos,viewDistance+1);
		}
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica(pos,enemy.pos,Ballistica.MAGIC_BOLT).collisionPos==enemy.pos&&
			   (enemy.nobuff(Chill.class)||enemy.nobuff(Frost.class)||
				(enemy.hasbuff(Chill.class)&&enemy.buff(Chill.class).speedFactor()<=0.25f));
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
		if(enemy.hasbuff(Chill.class)){
			Buff.施加(enemy,Frost.class,Frost.DURATION);
		}else{
			Buff.施加(enemy,Chill.class,Chill.DURATION);
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
		冰爆结晶动画 sprite = (冰爆结晶动画) super.sprite();
		sprite.linkVisuals(this);
		return sprite;
	}
	
	@Override
	public void updateSpriteState() {
		super.updateSpriteState();
		((冰爆结晶动画)sprite).updateTier(tier);
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
			return Messages.get(this, "desc", tier,攻击延迟(),viewDistance);
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