

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDAction;
import com.shatteredpixel.shatteredpixeldungeon.Statistics;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.CircleArc;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndHero;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndKeyBindings;
import com.watabou.input.GameAction;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.ColorMath;
import com.watabou.utils.GameMath;

public class StatusPane extends Component {

	private NinePatch bg;
	private Image avatar;
	private Button heroInfo;
	public static float talentBlink;
	private float warning;

	public static final float FLASH_RATE = (float)(Math.PI*1.5f); //1.5 blinks per second

	private int lastTier = 0;
	private Image 护盾;
	private Image 血条;
	private BitmapText 血条文本;
	private Image 绿条框;
	private Image 绿条;
	private BitmapText 绿条文本;
	private Image 法力框;
	private Image 法力条;
	private BitmapText 法力文本;
	private Button heroInfoOnBar;

	private Image exp;
	private BitmapText expText;

	private int lastLvl = -1;

	private BitmapText level;

	private BuffIndicator buffs;
	private Compass compass;

	private BusyIndicator busy;
	private CircleArc counter;

	private boolean 横屏;

	public StatusPane( boolean 横屏){
		super();

		String asset = Assets.Interfaces.STATUS;

		this.横屏 = 横屏;
		
		bg = new NinePatch( asset, 0, 0, 128, 38, 85, 0, 45, 0 );
		add( bg );

		heroInfo = new Button(){
			@Override
			protected void onClick () {
				Camera.main.panTo( Dungeon.hero.sprite.center(), 5f );
				GameScene.show( new WndHero() );
			}
			
			@Override
			public GameAction keyAction() {
				return SPDAction.HERO_INFO;
			}

			@Override
			protected String hoverText() {
				return Messages.titleCase(Messages.get(WndKeyBindings.class, "hero_info"));
			}
		};
		add(heroInfo);

		avatar = HeroSprite.avatar( Dungeon.hero );
		add( avatar );

		talentBlink = 0;

		compass = new Compass( Statistics.amuletObtained ? Dungeon.level.entrance() : Dungeon.level.exit() );
		add( compass );
		
		血条 = new Image(asset, 0, 42, 50, 4);
		add(血条);

		护盾 = new Image(asset, 0, 46, 50, 4);
		add(护盾);


		血条文本 = new BitmapText(PixelScene.pixelFont);
		血条文本.alpha(0.6f);
		add(血条文本);

		绿条 = new Image(asset, 0, 50, 50, 4);
		add(绿条);
		绿条框 = new Image(asset, 0, 58, 53, 8);
		addToBack(绿条框);
		
		绿条文本 = new BitmapText(PixelScene.pixelFont);
		绿条文本.alpha(0.6f);
		add(绿条文本);
		
		法力条= new Image(asset,0,54,50,4);
		add(法力条);
		法力框= new Image(asset,0,58,53,8);
		addToBack(法力框);
		
		法力文本= new BitmapText(PixelScene.pixelFont);
		法力文本.alpha(0.6f);
		add(法力文本);
		
		heroInfoOnBar = new Button(){
			@Override
			protected void onClick () {
				Camera.main.panTo( Dungeon.hero.sprite.center(), 5f );
				GameScene.show( new WndHero() );
			}
		};
		add(heroInfoOnBar);

		exp = new Image(asset, 0, 38, 16, 4);
		add( exp );
		
		expText = new BitmapText(PixelScene.pixelFont);
		expText.hardlight( 0xFFFFAA );
		expText.alpha(0.6f);
		add(expText);

		level = new BitmapText( PixelScene.pixelFont);
		level.hardlight( 0xFFFFAA );
		add( level );

		buffs = new BuffIndicator( Dungeon.hero, 横屏);
		add( buffs );

		busy = new BusyIndicator();
		add( busy );

		counter = new CircleArc(18, 4.25f);
		counter.color( 0x808080, true );
		counter.show(this, busy.center(), 0f);
	}

	@Override
	protected void layout() {

		height = 横屏 ? 39 : 35;

		bg.x = x;
		bg.y = y;
		if (横屏)  bg.size( 160, bg.height ); //HP bars must be 128px wide atm
		else        bg.size( width, bg.height );

		avatar.x = bg.x - avatar.width / 2f + 15;
		avatar.y = bg.y - avatar.height / 2f + (横屏 ? 15 : 14);
		PixelScene.align(avatar);
		
		heroInfo.setRect( x, y, 30, 横屏 ? 40 : 35 );

		compass.x = avatar.x + avatar.width / 2f - compass.origin.x;
		compass.y = avatar.y + avatar.height / 2f - compass.origin.y;
		PixelScene.align(compass);
		
			exp.x = x+2;
			exp.y = y+30;

			血条.x = 护盾.x = x + 30;
			血条.y = 护盾.y  = y+2;

			血条文本.scale.set(PixelScene.align(0.5f));
			血条文本.x = 血条.x + 1;
			血条文本.y = 血条.y + (血条.height - (血条文本.baseLine()+ 血条文本.scale.y))/2f;
			血条文本.y -= 0.001f; //prefer to be slightly higher
			PixelScene.align(血条文本);
			
			绿条框.x= 血条.x-1;
			绿条框.y= 血条.y+ 绿条.height()+1;
			绿条.x= 血条.x;
			绿条.y  = 血条.y + 绿条.height()+2;

			绿条文本.scale.set(PixelScene.align(0.5f));
			绿条文本.x = 绿条.x + 1;
			绿条文本.y = 绿条.y + (绿条.height - (绿条文本.baseLine()+ 绿条文本.scale.y))/2f;
			绿条文本.y -= 0.001f; //prefer to be slightly higher
			PixelScene.align(绿条文本);
			
			if(Dungeon.hero.heroClass(HeroClass.机器)||Dungeon.hero.heroClass(HeroClass.凌云)){
				法力框.x= 血条.x-1;
				法力框.y= 血条.y-2+ 法力条.height()+3;
				法力条.x= 血条.x;
				法力条.y = 血条.y + 法力条.height()+2;
				
				法力文本.scale.set(PixelScene.align(0.5f));
				法力文本.x = 法力条.x + 1;
				法力文本.y = 法力条.y + (法力条.height - (法力文本.baseLine()+ 法力文本.scale.y))/2f;
				法力文本.y -= 0.001f; //prefer to be slightly higher
				PixelScene.align(法力文本);
			}else{
				法力框.x=绿条框.x;
				法力框.y=绿条框.y-2+绿条框.height();
				法力条.x=绿条.x;
				法力条.y=绿条.y+法力条.height()+2;
				
				法力文本.scale.set(PixelScene.align(0.5f));
				法力文本.x=法力条.x+1;
				法力文本.y=
						法力条.y+(法力条.height-(法力文本.baseLine()+法力文本.scale.y))/2f;
				法力文本.y-=0.001f; //prefer to be slightly higher
				PixelScene.align(法力文本);
			}
			if(Dungeon.hero.heroClass(HeroClass.机器)||Dungeon.hero.heroClass(HeroClass.凌云)){
				绿条.alpha0();
				绿条文本.alpha0();
				绿条框.alpha0();
			}else{
				法力条.alpha0();
				法力文本.alpha0();
				法力框.alpha0();
			}
			heroInfoOnBar.setRect(heroInfo.right(), y, 50, 9);
			
		expText.scale.set(PixelScene.align(0.5f));
		expText.x = exp.x + 1;
		expText.y = exp.y + (exp.height - (expText.baseLine()+expText.scale.y))/2f;
		expText.y -= 0.001f; //prefer to be slightly higher
		PixelScene.align(expText);
		
		buffs.setRect( x + 31+3+1, y + 7+9+ 绿条.height()+1, 50, 8 );

		busy.x = x + 1;
		busy.y = y + 37;

		counter.point(busy.center());
	}
	
	private static final int[] warningColors = new int[]{0x660000, 0xCC0000, 0x660000};

	private float oldHP = 0;
	private float HP缓冲=0;
	private float old绿 = 0;
	private float 绿缓冲=0;
	private float old法力 = 0;
	private float 法力缓冲=0;
	private float 时间=0;

	@Override
	public void update() {
		super.update();
		
		int health = Dungeon.hero.生命;
		int hunger = 450-
				(Dungeon.hero.hasbuff(Hunger.class)?
						Dungeon.hero.buff(Hunger.class).hunger():450);
		int shield = Dungeon.hero.shielding();
		int max = Dungeon.hero.最大生命;
		int 法力=Dungeon.hero.法力;
		int 法力max=Dungeon.hero.最大法力;
		if (!Dungeon.hero.isAlive()) {
			avatar.tint(0x000000, 0.5f);
		} else if ((health/(float)max) < 0.334f) {
			warning += Game.elapsed * 5f *(0.4f - (health/(float)max));
			warning %= 1f;
			avatar.tint(ColorMath.interpolate(warning, warningColors), 0.5f );
		} else if (talentBlink > 0.33f){ //stops early so it doesn't end in the middle of a blink
			talentBlink -= Game.elapsed;
			avatar.tint(1, 1, 0, (float)Math.abs(Math.cos(talentBlink*FLASH_RATE))/2f);
		} else {
			avatar.resetColor();
		}
		
		if (oldHP ==0)
			oldHP = health;
		if (old绿 ==0)
			old绿 = hunger;
		if (old法力 ==0)
			old法力 = 法力;
		
		HP缓冲=oldHP-health;
		绿缓冲=old绿-hunger;
		法力缓冲=old法力-法力;
		if((时间+=Game.elapsed)>=0.33f){
			if(HP缓冲>0){
				oldHP-=HP缓冲/1.11f;
			}
			if(HP缓冲<0){
				oldHP-=HP缓冲/1.11f;
			}
			if(绿缓冲>0){
				old绿-=绿缓冲/1.11f;
			}
			if(绿缓冲<0){
				old绿-=绿缓冲/1.11f;
			}
			if(法力缓冲>0){
				old法力=法力缓冲/1.11f;
			}
			if(法力缓冲<0){
				old法力-=法力缓冲/1.11f;
			}
			
			血条.scale.x = Math.max( 0, oldHP/(float)max);
			绿条.scale.x = Math.max( 0, old绿/450f);
			法力条.scale.x = Math.max(0,old法力/(float)法力max);
			护盾.scale.x = Math.min( 1, shield/(float)max);
			
			时间=0;
		}
		
		if (shield <= 0) {
			血条文本.text(health + "/" + max);
		} else {
			血条文本.text(health + "+" + shield + "/" + max);
		}
		绿条文本.text(hunger + "/" + 450);
	法力文本.text(法力+"/"+法力max);
	
		exp.scale.x = (17 / exp.width) * Dungeon.hero.当前经验 / Dungeon.hero.升级所需();
		expText.text(Dungeon.hero.当前经验 + "/" + Dungeon.hero.升级所需());

		if (Dungeon.hero.等级 != lastLvl) {

			if (lastLvl != -1) {
				showStarParticles();
			}

			lastLvl = Dungeon.hero.等级;
			
				level.text( Integer.toString( lastLvl ) );
				level.measure();
				level.x = x + 25.5f - level.width() / 2f;
				level.y = y + 31 - level.baseLine() / 2f;
			
			PixelScene.align(level);
		}

		int tier = Dungeon.hero.tier();
		if (tier != lastTier) {
			lastTier = tier;
			avatar.copy( HeroSprite.avatar( Dungeon.hero ) );
		}

		counter.setSweep((1f - Actor.now()%1f)%1f);
	}

	public void updateAvatar(){
		avatar.copy( HeroSprite.avatar( Dungeon.hero ) );
	}

	public void alpha( float value ){
		value = GameMath.gate(0, value, 1f);
		bg.alpha(value);
		avatar.alpha(value);
		护盾.alpha(value);
		血条.alpha(value);
		血条文本.alpha(0.6f*value);
		绿条.alpha(value);
		绿条框.alpha(value);
		绿条文本.alpha(0.6f*value);
		法力条.alpha(value);
		法力框.alpha(value);
		法力文本.alpha(0.6f*value);
		exp.alpha(value);
		if (expText != null) expText.alpha(0.6f*value);
		level.alpha(value);
		compass.alpha(value);
		busy.alpha(value);
		counter.alpha(value);
		
	}

	public void showStarParticles(){
		Emitter emitter = (Emitter)recycle( Emitter.class );
		emitter.revive();
		emitter.pos( avatar.center() );
		emitter.burst( Speck.factory( Speck.STAR ), 12 );
	}

}
