

package com.shatteredpixel.shatteredpixeldungeon.ui;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.Game;
import com.watabou.noosa.ui.Component;

public class HealthBar extends Component {

	private static final int COLOR_BG	= 0xFFCC0000;
	private static final int COLOR_HP	= 0xFF00EE00;
	private static final int COLOR_SHLD = 0xFFFFFFFF;
	
	private static final int HEIGHT	= 2;
	
	private ColorBlock Bg;
	private ColorBlock Shld;
	private ColorBlock Hp;
	private BitmapText hpText;
	private BitmapText 护盾t;

	private float health;
	private float 生命;
	private float 护盾;
	private float max;
	private float shield;
	private boolean 隐形;

	private float oldHealth = -1;
	private float oldShield = -1;
	
	@Override
	protected void createChildren() {
		
		Bg = new ColorBlock( 1, 1, COLOR_BG );
		add( Bg );

		Shld = new ColorBlock( 1, 1, COLOR_SHLD );
		add( Shld );
		
		Hp = new ColorBlock( 1, 1, COLOR_HP );
		add( Hp );
		
		
		hpText = new BitmapText(PixelScene.pixelFont);
		hpText.alpha(1);
		add(hpText);

		护盾t = new BitmapText(PixelScene.pixelFont);
		护盾t.alpha(1);
		add(护盾t);
		
		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
			护盾t.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
			护盾t.alpha(0.6f);
		}
		height = HEIGHT;
	}

	float 框大小=1.25f;//1
	@Override
	public synchronized void update(){

		Bg.x = Shld.x = Hp.x = x;
		Bg.y = Shld.y = Hp.y = y;
		Bg.size( width, height*框大小 );

		//缓冲：数值变化时缓慢跟随（掉血和回血都缓冲）
		if (oldHealth < 0) oldHealth = health;
		if (oldShield < 0) oldShield = shield;
		float k = (float)Math.pow(Dungeon.缓冲(),Game.elapsed);
		oldHealth = health + (oldHealth - health) * k;
		oldShield = shield + (oldShield - shield) * k;

		//logic here rounds up to the nearest pixel
		float pixelWidth = width;
		if (camera() != null) pixelWidth *= camera().zoom;
		Shld.size( width * (float)Math.ceil(oldShield * pixelWidth)/pixelWidth, height*框大小 );
		Hp.size( width * (float)Math.ceil(oldHealth * pixelWidth)/pixelWidth, height*框大小 );

		hpText.scale.set(PixelScene.align(文本大小));
		hpText.text(kw2(生命)+"/"+kw2(max));
//		hpText.measure();
		hpText.x = Bg.x+0.5f;
		hpText.y = Bg.y +0.5f+ (Bg.height - (hpText.baseLine()+hpText.scale.y))/2f;
		if(护盾>=max*(SPDSettings.数值显示()*0.02f)&&false){
			护盾t.scale.set(PixelScene.align(文本大小));
			护盾t.text(kw2(护盾));
//			护盾t.measure();
			护盾t.x=Bg.x+width-护盾t.width+0.5f;
			护盾t.y=Bg.y+0.5f+(Bg.height-(护盾t.baseLine()+护盾t.scale.y))/2f;
		}
		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
			护盾t.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
			护盾t.alpha(0.6f);
		}
		super.update();
	}

	float 文本大小=0.5f;//0.34
	@Override
	protected void layout() {
		Bg.x = Shld.x = Hp.x = x;
		Bg.y = Shld.y = Hp.y = y;
		Bg.size( width, height*框大小 );

		hpText.scale.set(PixelScene.align(文本大小));
		hpText.x = Bg.x+0.5f;
		hpText.y = Bg.y +0.5f+ (Bg.height - (hpText.baseLine()+hpText.scale.y))/2f;

		护盾t.scale.set(PixelScene.align(文本大小));
		护盾t.x = Bg.x+width-护盾t.width+0.5f;
		护盾t.y = Bg.y +0.5f+ (Bg.height - (护盾t.baseLine()+护盾t.scale.y))/2f;

		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
			护盾t.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
			护盾t.alpha(0.6f);
		}
		
	}
	
	public void level( float value ) {
		level( value, 0f ,0f,0f,0f,false);
	}

	public void level(float health, float shield , float health2, float shield2 , float max ,boolean 隐形){
		this.health = health;
		this.生命 = health2;
		this.护盾 = shield2;
		this.max = max;
		this.shield = shield;
		this.隐形 = 隐形;
		layout();
	}

	public void level(Char c){
		float health = c.生命;
		float maxx = c.最大生命;
		float shield = c.shielding();
		float max = Math.max(health+shield, c.最大生命);
		boolean 隐形=c.hasbuff(Invisibility.class);
		level(health/max, (health+shield)/max,health,shield,maxx,隐形);
	}
}
