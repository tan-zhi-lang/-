

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BloodParticle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.Holiday;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoMob;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Callback;

public class BossHealthBar extends Component {

	private Image bar;
	private Image shieldedHP;
	private Image hp;
	private BitmapText hpText;

	private Button bossInfo;
	private BuffIndicator buffs;

	private static Mob boss;

	private Image skull;
	private Emitter blood;

	private static String asset = switch(Holiday.getCurrentHoliday()){
		case NONE-> SPDSettings.透明界面()?Assets.Interfaces.BOSSHP透明:Assets.Interfaces.BOSSHP;
		default-> SPDSettings.透明界面()?Assets.Interfaces.BOSSHP透明:Assets.Interfaces.BOSSHP;
		case 愚人节-> Assets.Interfaces.BOSSHP愚人;
//		case 春节-> Assets.Interfaces.BOSSHP春节;
//		case 圣诞节-> Assets.Interfaces.BOSSHP圣诞;
		
	};

	private static BossHealthBar instance;
	private static boolean bleeding;
	public BossHealthBar() {
		super();
		visible = active = (boss != null);
		instance = this;
	}

	@Override
	public synchronized void destroy() {
		super.destroy();
		if (instance == this) instance = null;
		if (buffs != null) BuffIndicator.setBossInstance(null);
	}

	@Override
	protected void createChildren() {
		bar = new Image(asset, 0, 0, 64, 16);
		add(bar);

		width = bar.width;
		height = bar.height;
		
		
		hp = new Image(asset, 15, 19, 47, 4);
		add(hp);
		shieldedHP = new Image(asset, 15, 25, 47, 4);
		add(shieldedHP);

		hpText = new BitmapText(PixelScene.pixelFont);
		hpText.alpha(0.6f);
		add(hpText);

		bossInfo = new Button(){
			@Override
			protected void onClick() {
				super.onClick();
				if (boss != null){
					GameScene.show(new WndInfoMob(boss));
				}
			}

			@Override
			protected String hoverText() {
				if (boss != null){
					return boss.name();
				}
				return super.hoverText();
			}
		};
		add(bossInfo);

		if (boss != null) {
			buffs = new BuffIndicator(boss);
			BuffIndicator.setBossInstance(buffs);
			add(buffs);
		}

		
		skull = new Image(asset, 5, 18, 6, 6);
		
		add(skull);

		blood = new Emitter();
		blood.pos(skull);
		blood.pour(BloodParticle.FACTORY, 0.3f);
		blood.autoKill = false;
		blood.on = false;
		add( blood );
	}

	@Override
	protected void layout() {
		bar.x = x-5;
		bar.y = y+20;

		hp.x = shieldedHP.x = bar.x+15;
		hp.y = shieldedHP.y  = bar.y+3;

		hpText.scale.set(PixelScene.align(0.5f));
		hpText.x = hp.x + 1;
		hpText.y = hp.y + (hp.height - (hpText.baseLine()+hpText.scale.y))/2f;
		hpText.y -= 0.001f; //prefer to be slightly higher
		PixelScene.align(hpText);

		bossInfo.setRect(x, y, bar.width, bar.height);

		if (buffs != null) {
			buffs.setRect(hp.x, hp.y + 5, 47, 16);
		}

		int paneSize = 16;
		skull.x = bar.x + (paneSize - skull.width())/2f;
		skull.y = bar.y + (paneSize - skull.height())/2f;
	}
	float oldhp=0;
	float oldshield=0;
	private float HP缓冲=0;
	private float SHI缓冲=0;
	float 时间=0;
	@Override
	public void update() {
		super.update();
		if (boss != null){
			if (!boss.isAlive() || !Dungeon.level.mobs.contains(boss)){
				boss = null;
				visible = active = false;
				if (buffs != null) {
					BuffIndicator.setBossInstance(null);
					remove(buffs);
					buffs.destroy();
					buffs = null;
				}
			} else {

				float health = boss.生命;
				
				float shield = boss.shielding();
				float max = boss.最大生命;
				
				if(oldhp==0)
					oldhp = health;
				if(oldshield==0)
					oldshield = shield;
				
				HP缓冲=oldhp-health;
				SHI缓冲=oldshield-shield;
				if((时间+=Game.elapsed)>=0.33f){
					if(HP缓冲>0){
						oldhp-=HP缓冲/1.11f;
					}
					if(HP缓冲<0){
						oldhp-=HP缓冲/1.11f;
					}
					if(SHI缓冲>0){
						oldshield-=SHI缓冲/1.11f;
					}
					if(SHI缓冲<0){
						oldshield-=SHI缓冲/1.11f;
					}
				}
				hp.scale.x = Math.max( 0, oldhp/(float)max);
				shieldedHP.scale.x = Math.min( 1, oldshield/(float)max);
				if (bleeding != blood.on){
					if (bleeding)   skull.tint( 0xcc0000, 0.6f );
					else            skull.resetColor();
					bringToFront(blood);
					blood.pos(skull);
					blood.on = bleeding;
				}

				if (shield <= 0){
					hpText.text(health + "/" + max);
				} else {
					hpText.text(health + "+" + shield +  "/" + max);
				}
				hpText.measure();
				hpText.x = hp.x + 1;

			}
		}
	}

	public static void assignBoss(Mob boss){
		if (BossHealthBar.boss == boss) {
			return;
		}
		BossHealthBar.boss = boss;
		bleed(false);
		if (instance != null) {
			ShatteredPixelDungeon.runOnRenderThread(new Callback() {
				@Override
				public void call() {
			instance.visible = instance.active = true;
			if (boss != null){
				if (instance.buffs != null){
					instance.remove(instance.buffs);
					instance.buffs.destroy();
				}
						instance.buffs = new BuffIndicator(boss);
				BuffIndicator.setBossInstance(instance.buffs);
				instance.add(instance.buffs);
				instance.layout();
			}
		}
			});
		}
	}
	
	public static boolean isAssigned(){
		return boss != null && boss.isAlive() && Dungeon.level.mobs.contains(boss);
	}

	public static void bleed(boolean value){
		bleeding = value;
	}

	public static boolean isBleeding(){
		return isAssigned() && bleeding;
	}

}
